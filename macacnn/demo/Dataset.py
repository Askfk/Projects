import numpy as np
import skimage.io
import skimage.color

from absl import logging


class Dataset(object):

    def __init__(self):
        self.image_ids = []
        self.image_info = []

        # Background is always the first class
        self.class_info = [{"source": "", "id": 0, "name": "BG"}]
        self.source_class_ids = {}

    def add_image(self, source, image_id, path, **kwargs):
        image_info = {
            'id': image_id,
            'source': source,
            'path': path,
        }
        image_info.update(**kwargs)
        self.image_info.append(image_info)

    def prepare(self):
        self.num_images = len(self.image_info)
        self.image_ids = np.arange(self.num_images)

    def load_image(self, image_id):
        """Load the specified image and return a [H,W,3] Numpy array.
        """
        # Load image
        image = skimage.io.imread(self.image_info[image_id]['path'])
        # If grayscale. Convert to RGB for consistency
        if image.ndim != 3:
            image = skimage.color.gray2rgb(image)
        # If has an alpha channel, remove it for consistency
        if image.shape[-1] == 4:
            image = image[..., :3]
        return image
