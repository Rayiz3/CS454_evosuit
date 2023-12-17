{
  "filepath": "/tmp/Codec-16b/src/main/java/org/apache/commons/codec/binary/Base32.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "Base32",
      "is_interface": false,
      "parent_types": [
        "org.apache.commons.codec.binary.BaseNCodec"
      ],
      "begin_line": 44,
      "end_line": 539,
      "comment": "\n * Provides Base32 encoding and decoding as defined by \u003ca href\u003d\"http://www.ietf.org/rfc/rfc4648.txt\"\u003eRFC 4648\u003c/a\u003e.\n *\n * \u003cp\u003e\n * The class can be parameterized in the following manner with various constructors:\n * \u003c/p\u003e\n * \u003cul\u003e\n * \u003cli\u003eWhether to use the \"base32hex\" variant instead of the default \"base32\"\u003c/li\u003e\n * \u003cli\u003eLine length: Default 76. Line length that aren\u0027t multiples of 8 will still essentially end up being multiples of\n * 8 in the encoded data.\n * \u003cli\u003eLine separator: Default is CRLF (\"\\r\\n\")\u003c/li\u003e\n * \u003c/ul\u003e\n * \u003cp\u003e\n * This class operates directly on byte streams, and not character streams.\n * \u003c/p\u003e\n * \u003cp\u003e\n * This class is thread-safe.\n * \u003c/p\u003e\n *\n * @see \u003ca href\u003d\"http://www.ietf.org/rfc/rfc4648.txt\"\u003eRFC 4648\u003c/a\u003e\n *\n * @since 1.5\n * @version $Id$\n "
    },
    {
      "type": "field",
      "varNames": [
        "BITS_PER_ENCODED_BYTE"
      ],
      "begin_line": 51,
      "end_line": 51,
      "comment": "\n     * BASE32 characters are 5 bits in length.\n     * They are formed by taking a block of five octets to form a 40-bit string,\n     * which is converted into eight BASE32 characters.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "BYTES_PER_ENCODED_BLOCK"
      ],
      "begin_line": 52,
      "end_line": 52,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "BYTES_PER_UNENCODED_BLOCK"
      ],
      "begin_line": 53,
      "end_line": 53,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "CHUNK_SEPARATOR"
      ],
      "begin_line": 60,
      "end_line": 60,
      "comment": "\n     * Chunk separator per RFC 2045 section 2.1.\n     *\n     * @see \u003ca href\u003d\"http://www.ietf.org/rfc/rfc2045.txt\"\u003eRFC 2045 section 2.1\u003c/a\u003e\n     "
    },
    {
      "type": "field",
      "varNames": [
        "DECODE_TABLE"
      ],
      "begin_line": 67,
      "end_line": 75,
      "comment": "\n     * This array is a lookup table that translates Unicode characters drawn from the \"Base32 Alphabet\" (as specified\n     * in Table 3 of RFC 4648) into their 5-bit positive integer equivalents. Characters that are not in the Base32\n     * alphabet but fall within the bounds of the array are translated to -1.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "ENCODE_TABLE"
      ],
      "begin_line": 81,
      "end_line": 85,
      "comment": "\n     * This array is a lookup table that translates 5-bit positive integer index values into their \"Base32 Alphabet\"\n     * equivalents as specified in Table 3 of RFC 4648.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "HEX_DECODE_TABLE"
      ],
      "begin_line": 92,
      "end_line": 100,
      "comment": "\n     * This array is a lookup table that translates Unicode characters drawn from the \"Base32 Hex Alphabet\" (as\n     * specified in Table 4 of RFC 4648) into their 5-bit positive integer equivalents. Characters that are not in the\n     * Base32 Hex alphabet but fall within the bounds of the array are translated to -1.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "HEX_ENCODE_TABLE"
      ],
      "begin_line": 106,
      "end_line": 110,
      "comment": "\n     * This array is a lookup table that translates 5-bit positive integer index values into their\n     * \"Base32 Hex Alphabet\" equivalents as specified in Table 4 of RFC 4648.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "MASK_5BITS"
      ],
      "begin_line": 113,
      "end_line": 113,
      "comment": " Mask used to extract 5 bits, used when encoding Base32 bytes "
    },
    {
      "type": "field",
      "varNames": [
        "decodeSize"
      ],
      "begin_line": 128,
      "end_line": 128,
      "comment": "\n     * Convenience variable to help us determine when our buffer is going to run out of room and needs resizing.\n     * \u003ccode\u003edecodeSize \u003d {@link #BYTES_PER_ENCODED_BLOCK} - 1 + lineSeparator.length;\u003c/code\u003e\n     "
    },
    {
      "type": "field",
      "varNames": [
        "decodeTable"
      ],
      "begin_line": 133,
      "end_line": 133,
      "comment": "\n     * Decode table to use.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "encodeSize"
      ],
      "begin_line": 139,
      "end_line": 139,
      "comment": "\n     * Convenience variable to help us determine when our buffer is going to run out of room and needs resizing.\n     * \u003ccode\u003eencodeSize \u003d {@link #BYTES_PER_ENCODED_BLOCK} + lineSeparator.length;\u003c/code\u003e\n     "
    },
    {
      "type": "field",
      "varNames": [
        "encodeTable"
      ],
      "begin_line": 144,
      "end_line": 144,
      "comment": "\n     * Encode table to use.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "lineSeparator"
      ],
      "begin_line": 149,
      "end_line": 149,
      "comment": "\n     * Line separator for encoding. Not used when decoding. Only used if lineLength \u0026gt; 0.\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32()",
      "begin_line": 158,
      "end_line": 160,
      "comment": "\n     * Creates a Base32 codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length is 0 (no chunking).\n     * \u003c/p\u003e\n     *\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32(byte)",
      "begin_line": 169,
      "end_line": 171,
      "comment": "\n     * Creates a Base32 codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length is 0 (no chunking).\n     * \u003c/p\u003e\n     * @param pad byte used as padding byte.\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32(boolean)",
      "begin_line": 180,
      "end_line": 182,
      "comment": "\n     * Creates a Base32 codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length is 0 (no chunking).\n     * \u003c/p\u003e\n     * @param useHex if {@code true} then use Base32 Hex alphabet\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32(boolean, byte)",
      "begin_line": 192,
      "end_line": 194,
      "comment": "\n     * Creates a Base32 codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length is 0 (no chunking).\n     * \u003c/p\u003e\n     * @param useHex if {@code true} then use Base32 Hex alphabet\n     * @param pad byte used as padding byte.\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32(int)",
      "begin_line": 207,
      "end_line": 209,
      "comment": "\n     * Creates a Base32 codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length is given in the constructor, the line separator is CRLF.\n     * \u003c/p\u003e\n     *\n     * @param lineLength\n     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of\n     *            8). If lineLength \u0026lt;\u003d 0, then the output will not be divided into lines (chunks). Ignored when\n     *            decoding.\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32(int, byte[])",
      "begin_line": 229,
      "end_line": 231,
      "comment": "\n     * Creates a Base32 codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length and line separator are given in the constructor.\n     * \u003c/p\u003e\n     * \u003cp\u003e\n     * Line lengths that aren\u0027t multiples of 8 will still essentially end up being multiples of 8 in the encoded data.\n     * \u003c/p\u003e\n     *\n     * @param lineLength\n     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of\n     *            8). If lineLength \u0026lt;\u003d 0, then the output will not be divided into lines (chunks). Ignored when\n     *            decoding.\n     * @param lineSeparator\n     *            Each line of encoded data will end with this sequence of bytes.\n     * @throws IllegalArgumentException\n     *             The provided lineSeparator included some Base32 characters. That\u0027s not going to work!\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32(int, byte[], boolean)",
      "begin_line": 254,
      "end_line": 256,
      "comment": "\n     * Creates a Base32 / Base32 Hex codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length and line separator are given in the constructor.\n     * \u003c/p\u003e\n     * \u003cp\u003e\n     * Line lengths that aren\u0027t multiples of 8 will still essentially end up being multiples of 8 in the encoded data.\n     * \u003c/p\u003e\n     *\n     * @param lineLength\n     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of\n     *            8). If lineLength \u0026lt;\u003d 0, then the output will not be divided into lines (chunks). Ignored when\n     *            decoding.\n     * @param lineSeparator\n     *            Each line of encoded data will end with this sequence of bytes.\n     * @param useHex\n     *            if {@code true}, then use Base32 Hex alphabet, otherwise use Base32 alphabet\n     * @throws IllegalArgumentException\n     *             The provided lineSeparator included some Base32 characters. That\u0027s not going to work! Or the\n     *             lineLength \u0026gt; 0 and lineSeparator is null.\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.binary.Base32.Base32(int, byte[], boolean, byte)",
      "begin_line": 280,
      "end_line": 311,
      "comment": "\n     * Creates a Base32 / Base32 Hex codec used for decoding and encoding.\n     * \u003cp\u003e\n     * When encoding the line length and line separator are given in the constructor.\n     * \u003c/p\u003e\n     * \u003cp\u003e\n     * Line lengths that aren\u0027t multiples of 8 will still essentially end up being multiples of 8 in the encoded data.\n     * \u003c/p\u003e\n     *\n     * @param lineLength\n     *            Each line of encoded data will be at most of the given length (rounded down to nearest multiple of\n     *            8). If lineLength \u0026lt;\u003d 0, then the output will not be divided into lines (chunks). Ignored when\n     *            decoding.\n     * @param lineSeparator\n     *            Each line of encoded data will end with this sequence of bytes.\n     * @param useHex\n     *            if {@code true}, then use Base32 Hex alphabet, otherwise use Base32 alphabet\n     * @param pad byte used as padding byte.\n     * @throws IllegalArgumentException\n     *             The provided lineSeparator included some Base32 characters. That\u0027s not going to work! Or the\n     *             lineLength \u0026gt; 0 and lineSeparator is null.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.Base32.decode(byte[], int, int, org.apache.commons.codec.binary.BaseNCodec.Context)",
      "begin_line": 335,
      "end_line": 414,
      "comment": "\n     * \u003cp\u003e\n     * Decodes all of the provided data, starting at inPos, for inAvail bytes. Should be called at least twice: once\n     * with the data to decode, and once with inAvail set to \"-1\" to alert decoder that EOF has been reached. The \"-1\"\n     * call is not necessary when decoding, but it doesn\u0027t hurt, either.\n     * \u003c/p\u003e\n     * \u003cp\u003e\n     * Ignores all non-Base32 characters. This is how chunked (e.g. 76 character) data is handled, since CR and LF are\n     * silently ignored, but has implications for other bytes, too. This method subscribes to the garbage-in,\n     * garbage-out philosophy: it will not check the provided data for validity.\n     * \u003c/p\u003e\n     *\n     * @param in\n     *            byte[] array of ascii data to Base32 decode.\n     * @param inPos\n     *            Position to start reading data from.\n     * @param inAvail\n     *            Amount of bytes available from input for encoding.\n     * @param context the context to be used\n     *\n     * Output is written to {@link Context#buffer} as 8-bit octets, using {@link Context#pos} as the buffer position\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.Base32.encode(byte[], int, int, org.apache.commons.codec.binary.BaseNCodec.Context)",
      "begin_line": 431,
      "end_line": 526,
      "comment": "\n     * \u003cp\u003e\n     * Encodes all of the provided data, starting at inPos, for inAvail bytes. Must be called at least twice: once with\n     * the data to encode, and once with inAvail set to \"-1\" to alert encoder that EOF has been reached, so flush last\n     * remaining bytes (if not multiple of 5).\n     * \u003c/p\u003e\n     *\n     * @param in\n     *            byte[] array of binary data to Base32 encode.\n     * @param inPos\n     *            Position to start reading data from.\n     * @param inAvail\n     *            Amount of bytes available from input for encoding.\n     * @param context the context to be used\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.Base32.isInAlphabet(byte)",
      "begin_line": 535,
      "end_line": 538,
      "comment": "\n     * Returns whether or not the {@code octet} is in the Base32 alphabet.\n     *\n     * @param octet\n     *            The value to test\n     * @return {@code true} if the value is defined in the the Base32 alphabet {@code false} otherwise.\n     "
    }
  ]
}