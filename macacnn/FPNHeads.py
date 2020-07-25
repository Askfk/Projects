"""Build FPN heads."""

import tensorflow as tf
from tensorflow import keras
from macacnn.ROIAlignLayer import ROIAlign
from macacnn.BatchNorm import BatchNorm


def fpn_bbox_graph(rois, feature_maps, image_meta,
                   pool_size, train_bn=True,
                   fc_layers_size=512):
    # TODO: fc_layer_size may need to be changed to reduce params
    """Builds the computation graph of the feature pyramid network classifier
        and regressor heads.

        rois: [batch, num_rois, (y1, x1, y2, x2)] Proposal boxes in normalized
              coordinates.
        feature_maps: List of feature maps from different layers of the pyramid,
                  [P2, P3, P4, P5]. Each has a different resolution.

        image_meta: [batch, (meta data)] Image details. See compose_image_meta()
        pool_size: The width of the square feature map generated from ROI Pooling.
        num_classes: number of classes, which determines the depth of the results
        train_bn: Boolean. Train or freeze Batch Norm layers
        fc_layers_size: Size of the 2 FC layers

        Returns:
            logits: [batch, num_rois, NUM_CLASSES] classifier logits (before softmax)
            probs: [batch, num_rois, NUM_CLASSES] classifier probabilities
            bbox_deltas: [batch, num_rois, NUM_CLASSES, (dy, dx, log(dh), log(dw))] Deltas to apply to
                         proposal boxes
    """

    # ROI Pooling
    # Shape: [batch, num_rois, POOL_SIZE, POOL_SIZE, channels]
    x = ROIAlign([pool_size, pool_size],
                 name="roi_align_classifier")([rois, image_meta] + feature_maps)
    # Two 1024 FC layers (implemented with Conv2D for consistency)
    x = keras.layers.TimeDistributed(keras.layers.Conv2D(fc_layers_size,
                                                         (pool_size, pool_size),
                                                         padding='valid'),
                                     name='macacnn_class_conv1')(x)
    x = keras.layers.TimeDistributed(BatchNorm(), name='macacnn_class_bn1')(x, training=train_bn)
    x = keras.layers.Activation('relu')(x)
    x = keras.layers.TimeDistributed(keras.layers.Conv2D(fc_layers_size, (1, 1)),
                                     name='macacnn_class_conv2')(x)
    x = keras.layers.TimeDistributed(BatchNorm(), name='macacnn_class_bn2')(x, training=train_bn)
    x = keras.layers.Activation('relu')(x)

    shared = keras.layers.Lambda(lambda f: keras.backend.squeeze(
        keras.backend.squeeze(f, 3), 2), name='pool_squeeze')(x)

    # Generate scores for every bbox, shape = [batch, num_rois, 1]
    scores = keras.layers.TimeDistributed(
        keras.layers.Dense(1, activation='sigmoid'),
        name='macacnn_bbox_scores_fc')(shared)

    # BBox head
    # [batch, num_rois, (dy, dx, log(dh), log(dw)]
    maca_bbox = keras.layers.TimeDistributed(
        keras.layers.Dense(4, activation='linear'),
        name='macacnn_bbox_fc')(shared)
    # # Reshape to [batch, num_rois, NUM_CLASSES, (dy, dx, log(dh), log(dw)]
    # s = keras.backend.int_shape(x)
    #
    # if s[1] == None:
    #     mrcnn_bbox = keras.layers.Reshape((-1, num_classes, 4), name="mrcnn_bbox")(x)
    # else:
    #     mrcnn_bbox = keras.layers.Reshape((s[1], num_classes, 4), name="mrcnn_bbox")(x)

    return maca_bbox, scores


def build_fpn_mask_graph(rois, feature_maps, image_meta,
                         pool_size, num_classes, train_bn=True):
    """Builds the computation graph of the mask head of Feature Pyramid Network.

        rois: [batch, num_rois, (y1, x1, y2, x2)] Proposal boxes in normalized
              coordinates.
        feature_maps: List of feature maps from different layers of the pyramid,
                      [P2, P3, P4, P5]. Each has a different resolution.
        image_meta: [batch, (meta data)] Image details. See compose_image_meta()
        pool_size: The width of the square feature map generated from ROI Pooling.
        num_classes: number of classes, which determines the depth of the results
        train_bn: Boolean. Train or freeze Batch Norm layers

        Returns: Masks [batch, num_rois, MASK_POOL_SIZE, MASK_POOL_SIZE, NUM_CLASSES]
    """
    # ROI Pooling
    # Shape [batch, num_rois, MASK_POOL_SIZE, MASK_POOL_SIZE, channels]
    x = ROIAlign([pool_size, pool_size],
                 name='roi_align_mask')([rois, image_meta] + feature_maps)
    # print('mask_fpn')
    # print(x.shape)

    # Conv layers
    for i in range(1, 5):
        x = keras.layers.TimeDistributed(keras.layers.Conv2D(256, (3, 3), padding='same'),
                                         name='macacnn_mask_conv{}'.format(i))(x)
        x = keras.layers.TimeDistributed(BatchNorm(),
                                         name='macacnn_mask_bn{}'.format(i))(x, training=train_bn)
        x = keras.layers.Activation('relu')(x)

    x = keras.layers.TimeDistributed(keras.layers.Conv2DTranspose(256, (2, 2), strides=2, activation='relu'),
                                     name='macacnn_mask_deconv')(x)
    x = keras.layers.TimeDistributed(keras.layers.Conv2D(num_classes, (1, 1), strides=1, activation='sigmoid'),
                                     name='macacnn_mask')(x)
    return x
