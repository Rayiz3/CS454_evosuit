{
  "filepath": "/tmp/Compress-40b/src/main/java/org/apache/commons/compress/utils/BitInputStream.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "BitInputStream",
      "is_interface": false,
      "parent_types": [
        "java.io.Closeable"
      ],
      "begin_line": 31,
      "end_line": 110,
      "comment": "\n * Reads bits from an InputStream.\n * @since 1.10\n * @NotThreadSafe\n "
    },
    {
      "type": "field",
      "varNames": [
        "MAXIMUM_CACHE_SIZE"
      ],
      "begin_line": 32,
      "end_line": 32,
      "comment": " bits in long minus sign bit"
    },
    {
      "type": "field",
      "varNames": [
        "MASKS"
      ],
      "begin_line": 33,
      "end_line": 33,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "in"
      ],
      "begin_line": 41,
      "end_line": 41,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "byteOrder"
      ],
      "begin_line": 42,
      "end_line": 42,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "bitsCached"
      ],
      "begin_line": 43,
      "end_line": 43,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "bitsCachedSize"
      ],
      "begin_line": 44,
      "end_line": 44,
      "comment": ""
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.compress.utils.BitInputStream.BitInputStream(java.io.InputStream, java.nio.ByteOrder)",
      "begin_line": 52,
      "end_line": 55,
      "comment": "\n     * Constructor taking an InputStream and its bit arrangement. \n     * @param in the InputStream\n     * @param byteOrder the bit arrangement across byte boundaries,\n     *      either BIG_ENDIAN (aaaaabbb bb000000) or LITTLE_ENDIAN (bbbaaaaa 000000bb)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.compress.utils.BitInputStream.close()",
      "begin_line": 57,
      "end_line": 60,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.apache.commons.compress.utils.BitInputStream.clearBitCache()",
      "begin_line": 66,
      "end_line": 69,
      "comment": "\n     * Clears the cache of bits that have been read from the\n     * underlying stream but not yet provided via {@link #readBits}.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.compress.utils.BitInputStream.readBits(int)",
      "begin_line": 81,
      "end_line": 109,
      "comment": "\n     * Returns at most 63 bits read from the underlying stream.\n     *\n     * @param count the number of bits to read, must be a positive\n     * number not bigger than 63.\n     * @return the bits concatenated as a long using the stream\u0027s byte order.\n     *         -1 if the end of the underlying stream has been reached before reading\n     *         the requested number of bits\n     * @throws IOException on error\n     "
    }
  ]
}