import tensorflow as tf
from macacnn_improvement.layers.ROIAlignLayer import ROIAlign


class MultiHeadAttention(tf.keras.layers.Layer):
    def __init__(self, embedding_dim, num_heads):
        super(MultiHeadAttention, self).__init__()
        self.num_heads = num_heads
        self.embedding_dim = embedding_dim

        assert embedding_dim % self.num_heads == 0

        self.depth = embedding_dim // self.num_heads

        self.wq = tf.keras.layers.Dense(embedding_dim)
        self.wk = tf.keras.layers.Dense(embedding_dim)
        self.wv = tf.keras.layers.Dense(embedding_dim)

        self.dense = tf.keras.layers.Dense(embedding_dim)

    def get_config(self):
        config = super(MultiHeadAttention, self).get_config()
        config['embedding_dim'] = self.embedding_dim
        config['num_heads'] = self.num_heads
        return config

    def split_heads(self, x):
        """Split the last dimension into (num_heads, depth).
        Transpose the result such that the shape is (batch_size * num_rois, num_heads, seq_len, depth)
        """
        x = tf.reshape(x, (-1, x.shape[1], self.num_heads, self.depth))
        return tf.transpose(x, perm=[0, 2, 1, 3])

    def call(self, inputs):
        v = inputs[0]
        k = inputs[1]
        q = inputs[2]

        q = self.wq(q)  # (batch_size * num_rois, seq_len, embedding_dim)
        k = self.wk(k)  # (batch_size * num_rois, seq_len, embedding_dim)
        v = self.wv(v)  # (batch_size * num_rois, seq_len, embedding_dim)

        q = self.split_heads(q)  # (batch_size * num_rois, num_heads, seq_len_q, depth)
        k = self.split_heads(k)  # (batch_size * num_rois, num_heads, seq_len_k, depth)
        v = self.split_heads(v)  # (batch_size * num_rois, num_heads, seq_len_v, depth)

        # scaled_attention.shape == (batch_size * num_rois, num_heads, seq_len_q, depth)
        # attention_weights.shape == (batch_size * num_rois, num_heads, seq_len_q, seq_len_k)
        scaled_attention, attention_weights = self.scaled_dot_product_attention(q, k, v)

        scaled_attention = tf.transpose(scaled_attention,
                                        perm=[0, 2, 1, 3])  # (batch_size * num_rois, seq_len_q, num_heads, depth)

        concat_attention = tf.reshape(scaled_attention,
                                      (batch_size, -1, self.embedding_dim))  # (batch_size * num_rois, seq_len_q, embedding_dim)

        output = self.dense(concat_attention)  # (batch_size * num_rois, seq_len_q, embedding_dim)

        return [output, attention_weights]

    def scaled_dot_product_attention(self, q, k, v):
        """Calculate the attention weights.
        q, k, v must have matching leading dimensions.
        k, v must have matching penultimate dimension, i.e.: seq_len_k = seq_len_v.

        Args:
          q: query shape == (..., seq_len_q, depth)
          k: key shape == (..., seq_len_k, depth)
          v: value shape == (..., seq_len_v, depth_v)

        Returns:
          output, attention_weights
        """

        matmul_qk = tf.matmul(q, k, transpose_b=True)  # (..., seq_len_q, seq_len_k)

        # scale matmul_qk
        dk = tf.cast(k.shape[-1], tf.float32)
        scaled_attention_logits = matmul_qk / tf.math.sqrt(dk)

        # softmax is normalized on the last axis (seq_len_k) so that the scores
        # add up to 1.
        attention_weights = tf.nn.softmax(scaled_attention_logits, axis=-1)  # (..., seq_len_q, seq_len_k)

        output = tf.matmul(attention_weights, v)  # (..., seq_len_q, depth_v)

        return output, attention_weights


def point_wise_feed_forward_network(embedding_dim, dff):
    return tf.keras.Sequential([
        tf.keras.layers.Dense(dff, activation='relu'),  # (batch_size * num_rois, seq_len, dff)
        tf.keras.layers.Dense(embedding_dim)  # (batch_size * num_rois, seq_len, embedding_dim)
    ])


class EncoderLayer(tf.keras.layers.Layer):
    def __init__(self, embedding_dim, num_heads, dff, rate=0.1):
        super(EncoderLayer, self).__init__()

        self.embedding_dim = embedding_dim
        self.num_heads = num_heads
        self.dff = dff
        self.rate = rate

        self.mha = MultiHeadAttention(embedding_dim, num_heads)
        self.ffn1 = tf.keras.layers.Dense(dff, activation='relu')  # (batch_size * num_rois, seq_len, dff)
        self.ffn2 = tf.keras.layers.Dense(embedding_dim)  # (batch_size * num_rois, seq_len, embedding_dim)

        self.layernorm1 = tf.keras.layers.LayerNormalization(epsilon=1e-6)
        self.layernorm2 = tf.keras.layers.LayerNormalization(epsilon=1e-6)

        self.dropout1 = tf.keras.layers.Dropout(rate)
        self.dropout2 = tf.keras.layers.Dropout(rate)

    def get_config(self):
        config = super(EncoderLayer, self).get_config()
        config['embedding_dim'] = self.embedding_dim
        config['num_heads'] = self.num_heads
        config['dff'] = self.dff
        config['rate'] = self.rate
        return config

    def call(self, inputs):
        # [batch_size * num_rois, input_seq_len, embedding_dim]
        x = inputs[0]
        training = inputs[1]

        attn_output, _ = self.mha([x, x, x])  # (batch_size * num_rois, input_seq_len, embedding_dim)
        attn_output = self.dropout1(attn_output, training=training)
        out1 = self.layernorm1(x + attn_output)  # (batch_size * num_rois, input_seq_len, embedding_dim)

        ffn_output = self.ffn1(out1)  # (batch_size * num_rois, input_seq_len, embedding_dim)
        ffn_output = self.ffn2(ffn_output)
        ffn_output = self.dropout2(ffn_output, training=training)
        out2 = self.layernorm2(out1 + ffn_output)  # (batch_size * num_rois, input_seq_len, embedding_dim)

        return out2


class DecoderLayer(tf.keras.layers.Layer):
    def __init__(self, embedding_dim, num_heads, dff, rate=0.1):
        super(DecoderLayer, self).__init__()

        self.embedding_dim = embedding_dim
        self.num_heads = num_heads
        self.dff = dff
        self.rate = rate

        self.mha1 = MultiHeadAttention(embedding_dim, num_heads)
        self.mha2 = MultiHeadAttention(embedding_dim, num_heads)

        self.ffn1 = tf.keras.layers.Dense(dff, activation='relu')  # (batch_size * num_rois, seq_len, dff)
        self.ffn2 = tf.keras.layers.Dense(embedding_dim)  # (batch_size * num_rois, seq_len, embedding_dim)

        self.layernorm1 = tf.keras.layers.LayerNormalization(epsilon=1e-6)
        self.layernorm2 = tf.keras.layers.LayerNormalization(epsilon=1e-6)
        self.layernorm3 = tf.keras.layers.LayerNormalization(epsilon=1e-6)

        self.dropout1 = tf.keras.layers.Dropout(rate)
        self.dropout2 = tf.keras.layers.Dropout(rate)
        self.dropout3 = tf.keras.layers.Dropout(rate)

    def get_config(self):
        config = super(DecoderLayer, self).get_config()
        config['embedding_dim'] = self.embedding_dim
        config['num_heads'] = self.num_heads
        config['dff'] = self.dff
        config['rate'] = self.rate
        return config

    def call(self, inputs):
        # (batch_size * num_rois, target_seq_len, embedding_dim)
        x = inputs[0]
        # enc_output.shape == (batch_size * num_rois, input_seq_len, embedding_dim)
        enc_output = inputs[1]
        training = inputs[2]

        # (batch_size * num_rois, target_seq_len, embedding_dim)
        attn1, attn_weights_block1 = self.mha1([x, x, x])
        attn1 = self.dropout1(attn1, training=training)
        out1 = self.layernorm1(attn1 + x)

        # (batch_size * num_rois, target_seq_len, embedding_dim)
        attn2, attn_weights_block2 = self.mha2([enc_output, enc_output, out1])
        attn2 = self.dropout2(attn2, training=training)
        # (batch_size * num_rois, target_seq_len, embedding_dim)
        out2 = self.layernorm2(attn2 + out1)

        # (batch_size * num_rois, target_seq_len, embedding_dim)
        ffn_output = self.ffn1(out2)
        ffn_output = self.ffn2(ffn_output)
        ffn_output = self.dropout3(ffn_output, training=training)
        # (batch_size * num_rois, target_seq_len, embedding_dim)
        out3 = self.layernorm3(ffn_output + out2)

        return out3, attn_weights_block1, attn_weights_block2


class Encoder(tf.keras.layers.Layer):
    def __init__(self, num_layers, embedding_dim, num_heads, dff, input_vocab_size, rate=0.1):
        super(Encoder, self).__init__()

        self.embedding_dim = embedding_dim
        self.num_layers = num_layers
        self.dff = dff
        self.input_vocab_size = input_vocab_size
        self.rate = rate

        self.embedding = tf.keras.layers.Embedding(input_vocab_size, embedding_dim)

        self.enc_layers = [EncoderLayer(embedding_dim, num_heads, dff, rate)
                           for _ in range(num_layers)]

        self.dropout = tf.keras.layers.Dropout(rate)

    def get_config(self):
        config = super(Encoder, self).get_config()
        config['embedding_dim'] = self.embedding_dim
        config['num_layers'] = self.num_layers
        config['dff'] = self.dff
        config['input_vocab_size'] = self.input_vocab_size
        config['rate'] = self.rate
        return config

    def call(self, inputs):
        # [batch * num_rois, 49]
        x = inputs[0]
        training = inputs[1]

        # adding embedding and position encoding.
        # (batch_size * num_rois, input_seq_len, embedding_dim)
        x = self.embedding(x)
        x *= tf.math.sqrt(tf.cast(self.embedding_dim, tf.float32))

        x = self.dropout(x, training=training)
        for i in range(self.num_layers):
            x = self.enc_layers[i]([x, training])

        # (batch_size * num_rois, input_seq_len, embedding_dim)
        return x


class Decoder(tf.keras.layers.Layer):
    def __init__(self, num_layers, embedding_dim, num_heads, dff, target_vocab_size, rate=0.1):
        super(Decoder, self).__init__()

        self.embedding_dim = embedding_dim
        self.num_layers = num_layers
        self.dff = dff
        self.target_vocab_size = target_vocab_size
        self.rate = rate

        self.embedding = tf.keras.layers.Embedding(target_vocab_size, embedding_dim)
        # self.pos_encoding = positional_encoding(maximum_position_encoding, embedding_dim)

        self.dec_layers = [DecoderLayer(embedding_dim, num_heads, dff, rate)
                           for _ in range(num_layers)]
        self.dropout = tf.keras.layers.Dropout(rate)

    def get_config(self):
        config = super(Encoder, self).get_config()
        config['embedding_dim'] = self.embedding_dim
        config['num_layers'] = self.num_layers
        config['dff'] = self.dff
        config['target_vocab_size'] = self.target_vocab_size
        config['rate'] = self.rate
        return config

    def call(self, inputs):
        # [batch * num_rois, 0~11]
        x = inputs[0]
        # (batch_size * num_rois, inp_seq_len, embedding_dim)
        enc_output = inputs[1]
        training = inputs[2]

        attention_weights = {}

        # (batch_size * num_rois, target_seq_len, embedding_dim)
        x = self.embedding(x)
        x *= tf.math.sqrt(tf.cast(self.embedding_dim, tf.float32))
        # x += self.pos_encoding[:, :seq_len, :]

        x = self.dropout(x, training=training)

        for i in range(self.num_layers):
            x, block1, block2 = self.dec_layers[i]([x, enc_output, training])

            attention_weights['decoder_layer{}_block1'.format(i + 1)] = block1
            attention_weights['decoder_layer{}_block2'.format(i + 1)] = block2

        # x.shape == (batch_size * num_rois, target_seq_len, embedding_dim)
        return x, attention_weights


class Transformer(tf.keras.Model):
    """
    num_layers: EncoderLayer and DecoderLayer 的个数
    embedding_dim: embedding 的维度
    num_heads: Attention head 的个数
    dff: point_wise_feed_forward_network 第一个dense层的维度
    input_vocab_size: pool ** 2
    target_vocab_size: 5001
    rate: dropout rate
    """
    def __init__(self, num_layers, embedding_dim, num_heads, dff, input_vocab_size,
                 target_vocab_size, rate=0.1, **kwargs):
        super(Transformer, self).__init__(**kwargs)

        self.encoder = Encoder(num_layers, embedding_dim, num_heads, dff,
                               input_vocab_size, rate)

        self.decoder = Decoder(num_layers, embedding_dim, num_heads, dff,
                               target_vocab_size, rate)

        self.final_layer = tf.keras.layers.Dense(target_vocab_size)

    def call(self, inputs):
        """
        inp: [batch * num_rois, 49]
        tar: [batch * num_rois, 0~11]
             then tar is '<start> I like shopping', our goal is 'I like shopping <end>'. Just used for training.
        training: whether to train dropout layer
        input, our inputs are image features.
        """
        inp = inputs[0]
        tar = inputs[1]
        training = inputs[2]
        # (batch_size * num_rois, inp_seq_len, embedding_dim)
        enc_output = self.encoder([inp, training])

        # dec_output.shape == (batch_size * num_rois, tar_seq_len, embedding_dim)
        dec_output, attention_weights = self.decoder([tar, enc_output, training])

        # (batch_size * num_rois, tar_seq_len, vocab_size)
        final_output = self.final_layer(dec_output)

        return final_output, attention_weights


def build_caption_layer_graph(rois, feature_maps, image_meta, pool_size,
                              mode, config, tokenizer=None, target_caption=None):
    """

    :param rois: [batch, num_rois, 4]
    :param feature_maps:
    :param image_meta:
    :param pool_size: 7
    :param tokenizer:
    :param mode:
    :param config:
    :param target_caption: [batch, num_rois, MAX_LENGTH]
    :return:
    """
    assert mode in ['training', 'inference']

    # Shape: [batch, num_rois, POOL_SIZE, POOL_SIZE, channels]
    x = ROIAlign([pool_size, pool_size],
                 name="roi_align_caption")([rois, image_meta] + feature_maps)

    x = tf.keras.layers.TimeDistributed(tf.keras.layers.Conv2D(64, (1, 1)))(x)
    x = tf.keras.layers.TimeDistributed(tf.keras.layers.Conv2D(16, (1, 1)))(x)
    # [batch, num_rois, pool, pool, 1]
    x = tf.keras.layers.TimeDistributed(tf.keras.layers.Conv2D(1, (1, 1)))(x)

    # Reshape x Shape: [batch * num_rois, pool_size * pool_size]
    processed_x = tf.keras.layers.Lambda(lambda x: tf.reshape(x, [-1, pool_size ** 2]),
                                         name='macacnn_caption_reshape_roialign')(x)

    transformer = Transformer(config.NUM_LAYERS,
                              config.EMBEDDING_DIM,
                              config.NUM_HEADS,
                              config.DFF,
                              pool_size ** 2,
                              config.VOCAB_SIZE,
                              config.DROPOUT_RATE,
                              name='macacnn_transformer')

    if mode == 'training':
        # assert target_caption != None, "In training mode you must provide target_caption."
        # [batch_size * num_rois, MAX_LENGTH]
        target_caption_after_reshaped = tf.keras.layers.Lambda(
            lambda x: tf.reshape(x, [-1, x.shape[2]]))(target_caption)
        # [batch * num_rois, 0~11]
        tar_inp = target_caption_after_reshaped[:, :-1]

        # [batch * num_rois, MAX_LENGTH - 1, vocab_size]
        predictions, _ = transformer([processed_x, tar_inp, True])

        return predictions, _
    else:
        assert tokenizer is not None, "In inference mode you must provide tokenizer."
        decoder_input = [tokenizer.word_index['<start>']]
        # [batch * num_rois, 1]
        output = tf.expand_dims(decoder_input * processed_x.shape[0], 1)
        for i in range(config.MAX_LENGTH):
            # [batch * num_rois, 1, vocab_size]
            predictions, attention_weights = transformer([processed_x, output, False])
            # [batch * num_rois, vocab_size]
            predictions = predictions[:, -1:, :]

            # [batch * num_rois, 1]
            predicted_id = tf.cast(tf.argmax(predictions, axis=-1), tf.int32)
            # [batch * num_rois, i + 1]
            output = tf.concat([output, predicted_id], axis=-1)
        print('sign')
        return output, attention_weights


if __name__ == '__main__':
    from macacnn.config import Config

    # tf.compat.v1.disable_eager_execution()
    # config = Config()
    # mode = 'training'
    # inputs = tf.keras.layers.Input([100, 7, 7, 256])
    # target_caption = tf.keras.layers.Input([100, 12])
    # x = tf.keras.layers.TimeDistributed(tf.keras.layers.Conv2D(64, (1, 1)))(inputs)
    # x = tf.keras.layers.TimeDistributed(tf.keras.layers.Conv2D(16, (1, 1)))(x)
    # # [batch, num_rois, pool, pool, 1]
    # x = tf.keras.layers.TimeDistributed(tf.keras.layers.Conv2D(1, (1, 1)))(x)
    #
    # # Reshape x Shape: [batch * num_rois, pool_size * pool_size]
    # processed_x = tf.keras.layers.Lambda(lambda x: tf.reshape(x, [-1, config.POOL_SIZE ** 2]),
    #                                      name='macacnn_caption_reshape_roialign')(x)
    #
    # transformer = Transformer(config.NUM_LAYERS,
    #                           config.EMBEDDING_DIM,
    #                           config.NUM_HEADS,
    #                           config.DFF,
    #                           config.POOL_SIZE ** 2,
    #                           config.VOCAB_SIZE,
    #                           config.DROPOUT_RATE,
    #                           name='macacnn_transformer')
    # if mode == 'training':
    #     target_caption_after_reshaped = tf.keras.layers.Lambda(lambda x: tf.reshape(x, [-1, x.shape[2]]))(target_caption)
    #     # [batch * num_rois, 0~11]
    #     tar_inp = target_caption_after_reshaped[:, :-1]
    #
    #     # [batch * num_rois, MAX_LENGTH - 1, vocab_size]
    #     predictions, _ = transformer([processed_x, tar_inp, True])
    #     print("Running Sign")
    #     model = tf.keras.Model([inputs, target_caption], predictions)
    # else:
    #     decoder_input = [1]  # Start token
    #     # [batch * num_rois, 1]
    #     output = tf.expand_dims(decoder_input * 100, 1)
    #     for i in range(config.MAX_LENGTH):
    #         # [batch * num_rois, 1, vocab_size]
    #         predictions, attention_weights = transformer([processed_x, output, False])
    #         # [batch * num_rois, vocab_size]
    #         predictions = predictions[:, -1:, :]
    #
    #         # [batch * num_rois, 1]
    #         predicted_id = tf.cast(tf.argmax(predictions, axis=-1), tf.int32)
    #         # [batch * num_rois, i + 1]
    #         output = tf.concat([output, predicted_id], axis=-1)
    #     print("Running Sign")
    #     model = tf.keras.Model([inputs], output)

