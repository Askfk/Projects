import tensorflow as tf
from tensorflow import keras
import numpy as np
import math
import os
import datetime
import re
from absl import logging
from collections import OrderedDict

from Project.config import Config as cng
from Project.backbone import build_backbone_net_graph
from Project.RPNModel import build_rpn_model
from Project.ProposalLayer import ProposalLayer
from Project.FPNHeads import fpn_classifier_graph, build_fpn_mask_graph
from Project.DetectionLayer import DetectionLayer
from Project.DetectionTargetLayer import DetectionTargetLayer
from Project.CaptionLayer import build_caption_layer_graph
from Project.DataGenerator import DataGenerator

from Project import losses, utils, utils_graph

tf.compat.v1.disable_eager_execution()


def log(text, array=None):
    """Prints a text message. And, optionally, if a Numpy array is provided it
        prints it's shape, min, and max values.
    """
    if array is not None:
        text = text.ljust(25)
        text += ("Shape: {:20}   ".format(str(array.shape)))
        if array.size:
            text += ("min: {:10.5f}   max: {:10.5f}".format(array.min(), array.max()))
        else:
            text += ("min: {:10}   max: {:10}".format("", ""))

        text += "  {}".format(array.dtype)
    print(text)


# A hack to get around Keras's bad support for constants
# This class returns a constant layer
class ConstLayer(tf.keras.layers.Layer):
    def __init__(self, x, name=None):
        super(ConstLayer, self).__init__(name=name)
        self.x = tf.Variable(x)

    def get_config(self):
        config = super().get_config()
        return config

    def call(self, inputs):
        return self.x


class MACA():

    def __init(self, mode, config, model_dir):

        assert mode in ['training', 'inference']
        self.mode = mode
        self.config = config
        self.model_dir = model_dir
        self.set_log_dir()
        self.model = self.build(mode=mode, config=config)

    def build(self, mode, config):
        assert mode in ['training', 'inference']

        h, w = config.IMAGE_SHAPE[:2]
        if h / 2 ** 6 != int(h / 2 ** 6) or w / 2 ** 6 != int(w / 2 ** 6):
            raise Exception("Image size must be dividable by 2 at least 6 times "
                            "to avoid fractions when downscaling and upscaling."
                            "For example, use 256, 320, 384, 448, 512, ... etc. ")

        # Inputs
        input_image = keras.layers.Input(
            shape=[None, None, config.IMAGE_SHAPE[2]], name='input_image')
        input_image_meta = keras.layers.Input(shape=[config.IMAGE_META_SIZE],
                                              name='input_image_meta')

        if mode == 'training':
            # RPN GT
            # 1. Masking Part
            input_mask_rpn_match = keras.layers.Input(
                shape=[None, 1], name='input_mask_rpn_match', dtype=tf.int32)
            input_mask_rpn_bbox = keras.layers.Input(
                shape=[None, 4], name='input_mask_rpn_bbox', dtype=tf.float32)
            # 2. Captioning Part
            input_caption_rpn_match = keras.layers.Input(
                shape=[None, 1], name='input_caption_rpn_match', dtype=tf.int32)
            input_caption_rpn_bbox = keras.layers.Input(
                shape=[None, 4], name='input_rpn_rpn_bbox', dtype=tf.float32)

            # Detection GT (captions, bounding boxes, masks)
            # 1. Mask Part (Zero padded)
            # [batch, height, width, MAX_GT_INSTANCES]
            if config.USE_MINI_MASK:
                input_gt_masks = keras.layers.Input(
                    shape=[config.MINI_MASK_SHAPE[0],
                           config.MINI_MASK_SHAPE[1], None],
                    name='input_gt_masks', dtype=bool)
            else:
                input_gt_masks = keras.layers.Input(
                    shape=[config.IMAGE_SHAPE[0], config.IMAGE_SHAPE[1], None],
                    name='input_gt_masks', dtype=bool)
            # 2. Caption Part
            # [batch, MAX_GT_INSTANCES, (y1, x1, y2, x2)] in image coordinates
            input_gt_boxes = keras.layers.Input(
                shape=[None, 4], name='input_gt_boxes', dtype=tf.float32)
            # Normalize coordinates
            gt_boxes = keras.layers.Lambda(
                lambda x: utils_graph.norm_boxes_graph(
                    x, keras.backend.shape(input_image)[1:3]))(input_gt_boxes)
            input_gt_captions = keras.layers.Input(
                shape=[None], name='input_gt_captions', dtype=tf.int32)

        elif mode == 'inference':
            input_anchors = keras.layers.Input(
                shape=[None, 4], name='input_anchors')

        # Build the shared convolutional layers.
        P2, P3, P4, P5, P6 = build_backbone_net_graph(input_image, self.config.BACKBONE, self.config)

        rpn_feature_maps = [P2, P3, P4, P5, P6]
        mrcnn_feature_maps = [P2, P3, P4, P5]

        # Anchors
        if mode == 'training':
            anchors = self.get_anchors(config.IMAGE_SHAPE)
            # Duplicate across the batch dimension because Keras requires it
            # TODO: can this be optimized to avoid duplicating the anchors?
            anchors = np.broadcast_to(anchors, (config.BATCH_SIZE,) + anchors.shape)

            # A hack to get around Keras's bad support for constants
            # This class returns a constant layer
            anchors = ConstLayer(anchors, name="anchors")(input_image)
        else:
            anchors = input_anchors

        # RPN Model
        rpn_mask = build_rpn_model(config.RPN_ANCHOR_STRIDE,
                              len(config.RPN_ANCHOR_RATIOS),
                              config.TOP_DOWN_PYRAMID_SIZE)
        rpn_caption = build_rpn_model(config.RPN_ANCHOR_STRIDE,
                              len(config.RPN_ANCHOR_RATIOS),
                              config.TOP_DOWN_PYRAMID_SIZE)
        # Loop through pyramid layers
        mask_layer_outputs = []
        caption_layer_outputs = []
        for p in rpn_feature_maps:
            mask_layer_outputs.append(rpn_mask([p]))
            caption_layer_outputs.append(rpn_caption([p]))

        # Concatenate layer outputs
        # Convert from list of lists of level outputs to list of lists
        # of outputs across levels.
        # e.g. [[a1, b1, c1], [a2, b2, c2]] => [[a1, a2], [b1, b2], [c1, c2]]
        mask_output_names = ["mask_rpn_class_logits", "mask_rpn_class", "mask_rpn_bbox"]
        caption_output_names = ["caption_rpn_class_logits", "caption_rpn_class", "caption_rpn_bbox"]

        mask_outputs = list(zip(*mask_layer_outputs))
        caption_outputs = list(zip(*caption_layer_outputs))

        mask_outputs = [keras.layers.Concatenate(axis=1, name=n)(list(o))
                   for o, n in zip(mask_outputs, mask_output_names)]
        caption_outputs = [keras.layers.Concatenate(axis=1, name=n)(list(o))
                   for o, n in zip(caption_outputs, caption_output_names)]

        mask_rpn_class_logits, mask_rpn_class, mask_rpn_bbox = mask_outputs
        caption_rpn_class_logits, caption_rpn_class, caption_rpn_bbox = caption_outputs

        # Generate proposals
        # Proposals are [batch, N, (y1, x1, y2, x2)] in normalized coordinates
        # and zero padded.
        mask_proposal_count = config.POST_NMS_ROIS_TRAINING if mode == "training" \
            else config.POST_NMS_ROIS_INFERENCE
        caption_proposal_count = config.POST_NMS_ROIS_TRAINING if mode == "training" \
            else config.POST_NMS_ROIS_INFERENCE

        mask_rpn_rois = ProposalLayer(
            proposal_count=mask_proposal_count,
            nms_threshold=config.RPN_NMS_THRESHOLD,
            config=config,
            name="ROI")([mask_rpn_class, mask_rpn_bbox, anchors])
        caption_rpn_rois = ProposalLayer(
            proposal_count=caption_proposal_count,
            nms_threshold=config.RPN_NMS_THRESHOLD,
            config=config,
            name="ROI")([caption_rpn_class, caption_rpn_bbox, anchors])

        if mode == 'training':
            # Class ID mask to mark class IDs supported by the dataset the image
            # came from.
            active_class_ids = keras.layers.Lambda()
            pass



