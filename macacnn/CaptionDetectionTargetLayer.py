import tensorflow as tf
from tensorflow import keras

from macacnn import utils_graph, utils


def trim_zeros_graph(boxes, name='trim_zeros'):
    """Often boxes are represented with matrices of shape [N, 4] and
    are padded with zeros. This removes zero boxes.

    boxes: [N, 4] matrix of boxes.
    non_zeros: [N] a 1D boolean mask identifying the rows to keep
    """
    non_zeros = tf.cast(tf.reduce_sum(input_tensor=tf.abs(boxes), axis=1), tf.bool)
    boxes = tf.boolean_mask(tensor=boxes, mask=non_zeros, name=name)
    return boxes, non_zeros


def overlaps_graph(boxes1, boxes2):
    """Computes IoU overlaps between two sets of boxes.
    boxes1, boxes2: [N, (y1, x1, y2, x2)].
    """
    # 1. Tile boxes2 and repeat boxes1. This allows us to compare
    # every boxes1 against every boxes2 without loops.
    # TF doesn't have an equivalent to np.repeat() so simulate it
    # using tf.tile() and tf.reshape.
    b1 = tf.reshape(tf.tile(tf.expand_dims(boxes1, 1),
                            [1, 1, tf.shape(input=boxes2)[0]]), [-1, 4])
    b2 = tf.tile(boxes2, [tf.shape(input=boxes1)[0], 1])
    # 2. Compute intersections
    b1_y1, b1_x1, b1_y2, b1_x2 = tf.split(b1, 4, axis=1)
    b2_y1, b2_x1, b2_y2, b2_x2 = tf.split(b2, 4, axis=1)
    y1 = tf.maximum(b1_y1, b2_y1)
    x1 = tf.maximum(b1_x1, b2_x1)
    y2 = tf.minimum(b1_y2, b2_y2)
    x2 = tf.minimum(b1_x2, b2_x2)
    intersection = tf.maximum(x2 - x1, 0) * tf.maximum(y2 - y1, 0)
    # 3. Compute unions
    b1_area = (b1_y2 - b1_y1) * (b1_x2 - b1_x1)
    b2_area = (b2_y2 - b2_y1) * (b2_x2 - b2_x1)
    union = b1_area + b2_area - intersection
    # 4. Compute IoU and reshape to [boxes1, boxes2]
    iou = intersection / union
    overlaps = tf.reshape(iou, [tf.shape(input=boxes1)[0], tf.shape(input=boxes2)[0]])
    return overlaps


def detection_targets_graph(proposals, gt_boxes, gt_captions, scores, config):

    # Assertions
    asserts = [
        tf.Assert(tf.greater(tf.shape(proposals)[0], 0), [proposals],
                  name='roi_assertion')]
    with tf.control_dependencies(asserts):
        proposals = tf.identity(proposals)
    # Remove zero padding
    proposals, _ = trim_zeros_graph(proposals, name='trim_proposals')
    gt_boxes, non_zeros = trim_zeros_graph(gt_boxes, name='trim_gt_boxes')
    # print(gt_captions.shape)
    gt_captions = tf.boolean_mask(gt_captions, non_zeros, name='trim_gt_captions')
    # print(gt_captions.shape)
    scores = tf.boolean_mask(scores, non_zeros, name='trim_scores')

    # Handle COCO crowds
    # A crowd box in COCO is a bounding box around several instances. Exclude
    # them from training. A crowd box is given a negative class ID.
    # crowd_ix = tf.where(gt_class_ids < 0)[:, 0]
    # non_crowd_ix = tf.where(gt_class_ids > 0)[:, 0]
    # crowd_boxes = tf.gather(gt_boxes, crowd_ix)
    # gt_class_ids = tf.gather(gt_class_ids, non_crowd_ix)
    # gt_boxes = tf.gather(gt_boxes, non_crowd_ix)
    # gt_masks = tf.gather(gt_masks, non_crowd_ix, axis=2)

    # Compute overlaps matrix [proposals, gt_boxes]
    overlaps = overlaps_graph(proposals, gt_boxes)

    # Compute overlaps with crowd boxes [proposals, crowd_boxes]
    # crowd_overlaps = overlaps_graph(proposals, crowd_boxes)
    # crowd_iou_max = tf.reduce_max(input_tensor=crowd_overlaps, axis=1)
    # no_crowd_bool = (crowd_iou_max < 0.001)

    # Determine positive and negative ROIs
    roi_iou_max = tf.reduce_max(input_tensor=overlaps, axis=1)
    # 1. Positive ROIs are those with >= 0.5 IoU with a GT box
    positive_roi_bool = (roi_iou_max >= 0.5)
    positive_indices = tf.compat.v1.where(positive_roi_bool)[:, 0]
    # 2. Negative ROIs are those with < 0.5 with every GT box. Skip crowds.
    negative_indices = tf.compat.v1.where(roi_iou_max < 0.5)[:, 0]
    # Subsample ROIs. Aim for 33% positive
    # Positive ROIs
    positive_count = int(config.TRAIN_ROIS_PER_IMAGE *
                         config.ROI_POSITIVE_RATIO)
    positive_indices = tf.random.shuffle(positive_indices)[:positive_count]
    positive_count = tf.shape(positive_indices)[0]
    # Negative ROIs. Add enough to maintain positive:negative ratio.
    r = 1.0 / config.ROI_POSITIVE_RATIO
    negative_count = tf.cast(r * tf.cast(positive_count, tf.float32), tf.int32) - positive_count
    negative_indices = tf.random.shuffle(negative_indices)[:negative_count]
    # Gather selected ROIs
    positive_rois = tf.gather(proposals, positive_indices)
    negative_rois = tf.gather(proposals, negative_indices)
    # Assign positive ROIs to GT boxes
    positive_overlaps = tf.gather(overlaps, positive_indices)
    roi_gt_box_assignment = tf.cond(
        pred=tf.greater(tf.shape(input=positive_overlaps)[1], 0),
        true_fn=lambda: tf.argmax(input=positive_overlaps, axis=1),
        false_fn=lambda: tf.cast(tf.constant([]), tf.int64))
    roi_gt_boxes = tf.gather(gt_boxes, roi_gt_box_assignment)
    # roi_gt_class_ids = tf.gather(gt_class_ids, roi_gt_box_assignment)
    # print(gt_captions.shape)
    roi_gt_captions = tf.gather(gt_captions, roi_gt_box_assignment)
    roi_gt_boxes_scores = tf.gather(scores, roi_gt_box_assignment)

    # Compute bbox refinement for positive ROIs
    deltas = utils_graph.box_refinement_graph(positive_rois, roi_gt_boxes)
    deltas /= config.BBOX_STD_DEV
    rois = tf.concat([positive_rois, negative_rois], axis=0)
    N = tf.shape(input=negative_rois)[0]
    P = tf.maximum(config.TRAIN_ROIS_PER_IMAGE - tf.shape(input=rois)[0], 0)
    rois = tf.pad(tensor=rois, paddings=[(0, P), (0, 0)])
    roi_gt_boxes = tf.pad(tensor=roi_gt_boxes, paddings=[(0, N + P), (0, 0)])
    # print('Running here 1')
    # print(roi_gt_captions.shape)
    roi_gt_captions = tf.pad(tensor=roi_gt_captions, paddings=[(0, N + P), (0, 0)])
    # print('Running here 2')
    # print(roi_gt_boxes_scores.shape)
    roi_gt_boxes_scores = tf.pad(tensor=roi_gt_boxes_scores, paddings=[(0, N + P)])
    # print('Running here 3')
    deltas = tf.pad(tensor=deltas, paddings=[(0, N + P), (0, 0)])

    return rois, deltas, roi_gt_captions, roi_gt_boxes_scores


class CaptionDetectionTargetLayer(keras.layers.Layer):

    def __init__(self, config, **kwargs):
        super(CaptionDetectionTargetLayer, self).__init__(**kwargs)
        self.config = config

    def get_config(self):
        config = super(CaptionDetectionTargetLayer, self).get_config()
        config["config"] = self.config.to_dict()
        return config

    def call(self, inputs):
        proposals = inputs[0]
        gt_boxes = inputs[1]
        gt_captions = inputs[2]
        scores = inputs[3]

        # Slice the batch and run a graph for each slice
        names = ["caption_rois", "caption_target_deltas", "target_caption", "target_deltas_scores"]
        outputs = utils.batch_slice(
            [proposals, gt_boxes, gt_captions, scores],
            lambda x, y, z, a: detection_targets_graph(x, y, z, a, self.config),
            self.config.IMAGES_PER_GPU, names=names)

        return outputs

    def compute_output_shape(self, input_shape):
        return [
            (None, self.config.TRAIN_ROIS_PER_IMAGE, 4),  # rois
            (None, self.config.TRAIN_ROIS_PER_IMAGE, 4),  # deltas
            (None, self.config.TRAIN_ROIS_PER_IMAGE, self.config.MAX_LENGTH),  # captions
            (None, self.config.TRAIN_ROIS_PER_IMAGE),   # scores
        ]

    def compute_mask(self, inputs, mask=None):
        return [None, None, None, None]



