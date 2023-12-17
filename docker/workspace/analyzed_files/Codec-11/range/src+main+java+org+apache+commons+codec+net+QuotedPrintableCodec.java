{
  "filepath": "/tmp/Codec-11b/src/main/java/org/apache/commons/codec/net/QuotedPrintableCodec.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "QuotedPrintableCodec",
      "is_interface": false,
      "parent_types": [
        "org.apache.commons.codec.BinaryEncoder",
        "org.apache.commons.codec.BinaryDecoder",
        "org.apache.commons.codec.StringEncoder",
        "org.apache.commons.codec.StringDecoder"
      ],
      "begin_line": 53,
      "end_line": 431,
      "comment": "\n * \u003cp\u003e\n * Codec for the Quoted-Printable section of \u003ca href\u003d\"http://www.ietf.org/rfc/rfc1521.txt\"\u003eRFC 1521\u003c/a\u003e.\n * \u003c/p\u003e\n * \u003cp\u003e\n * The Quoted-Printable encoding is intended to represent data that largely consists of octets that correspond to\n * printable characters in the ASCII character set. It encodes the data in such a way that the resulting octets are\n * unlikely to be modified by mail transport. If the data being encoded are mostly ASCII text, the encoded form of the\n * data remains largely recognizable by humans. A body which is entirely ASCII may also be encoded in Quoted-Printable\n * to ensure the integrity of the data should the message pass through a character- translating, and/or line-wrapping\n * gateway.\n * \u003c/p\u003e\n *\n * @see \u003ca href\u003d\"http://www.ietf.org/rfc/rfc1521.txt\"\u003e RFC 1521 MIME (Multipurpose Internet Mail Extensions) Part One:\n *          Mechanisms for Specifying and Describing the Format of Internet Message Bodies \u003c/a\u003e\n *\n * @author Apache Software Foundation\n * @since 1.3\n * @version $Id$\n "
    },
    {
      "type": "field",
      "varNames": [
        "charset"
      ],
      "begin_line": 57,
      "end_line": 57,
      "comment": "\n     * The default charset used for string decoding and encoding.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "PRINTABLE_CHARS"
      ],
      "begin_line": 62,
      "end_line": 62,
      "comment": "\n     * BitSet of printable characters as defined in RFC 1521.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "ESCAPE_CHAR"
      ],
      "begin_line": 64,
      "end_line": 64,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "TAB"
      ],
      "begin_line": 66,
      "end_line": 66,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "SPACE"
      ],
      "begin_line": 68,
      "end_line": 68,
      "comment": ""
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.QuotedPrintableCodec()",
      "begin_line": 90,
      "end_line": 92,
      "comment": "\n     * Default constructor.\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.QuotedPrintableCodec(java.lang.String)",
      "begin_line": 100,
      "end_line": 103,
      "comment": "\n     * Constructor which allows for the selection of a default charset\n     * \n     * @param charset\n     *                  the default string charset to use.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(int, java.io.ByteArrayOutputStream)",
      "begin_line": 114,
      "end_line": 120,
      "comment": "\n     * Encodes byte into its quoted-printable representation.\n     * \n     * @param b\n     *            byte to encode\n     * @param buffer\n     *            the buffer to write to\n     * @return The number of bytes written to the \u003ccode\u003ebuffer\u003c/code\u003e\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.encodeQuotedPrintable(java.util.BitSet, byte[])",
      "begin_line": 167,
      "end_line": 202,
      "comment": "\n     * Encodes an array of bytes into an array of quoted-printable 7-bit characters. Unsafe characters are escaped.\n     *\n     * \u003cp\u003e\n     * This function fully implements the quoted-printable encoding specification (rule #1 through rule #5)\n     * as defined in RFC 1521 and is suitable for encoding binary data and unformatted text.\n     * \u003c/p\u003e\n     *\n     * @param printable\n     *                  bitset of characters deemed quoted-printable\n     * @param bytes\n     *                  array of bytes to be encoded\n     * @return array of bytes containing quoted-printable data\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.decodeQuotedPrintable(byte[])",
      "begin_line": 219,
      "end_line": 241,
      "comment": "\n     * Decodes an array quoted-printable characters into an array of original bytes. Escaped characters are\n     * converted back to their original representation.\n     *\n     * \u003cp\u003e\n     * This function fully implements the quoted-printable encoding specification (rule #1 through rule #5) as\n     * defined in RFC 1521.\n     * \u003c/p\u003e\n     *\n     * @param bytes\n     *                  array of quoted-printable characters\n     * @return array of original bytes\n     * @throws DecoderException\n     *                  Thrown if quoted-printable decoding is unsuccessful\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.encode(byte[])",
      "begin_line": 255,
      "end_line": 257,
      "comment": "\n     * Encodes an array of bytes into an array of quoted-printable 7-bit characters. Unsafe characters are escaped.\n     * \n     * \u003cp\u003e\n     * This function fully implements the quoted-printable encoding specification (rule #1 through rule #5)\n     * as defined in RFC 1521 and is suitable for encoding binary data and unformatted text.\n     * \u003c/p\u003e\n     * \n     * @param bytes\n     *                  array of bytes to be encoded\n     * @return array of bytes containing quoted-printable data\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.decode(byte[])",
      "begin_line": 274,
      "end_line": 276,
      "comment": "\n     * Decodes an array of quoted-printable characters into an array of original bytes. Escaped characters are converted\n     * back to their original representation.\n     * \n     * \u003cp\u003e\n     * This function fully implements the quoted-printable encoding specification (rule #1 through rule #2)\n     * as defined in RFC 1521.\n     * \u003c/p\u003e\n     * \n     * @param bytes\n     *                  array of quoted-printable characters\n     * @return array of original bytes\n     * @throws DecoderException\n     *                  Thrown if quoted-printable decoding is unsuccessful\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.encode(java.lang.String)",
      "begin_line": 295,
      "end_line": 304,
      "comment": "\n     * Encodes a string into its quoted-printable form using the default string charset. Unsafe characters are escaped.\n     * \n     * \u003cp\u003e\n     * This function fully implements the quoted-printable encoding specification (rule #1 through rule #2)\n     * as defined in RFC 1521 and is suitable for encoding binary data.\n     * \u003c/p\u003e\n     * \n     * @param pString\n     *                  string to convert to quoted-printable form\n     * @return quoted-printable string\n     * \n     * @throws EncoderException\n     *                  Thrown if quoted-printable encoding is unsuccessful\n     * \n     * @see #getDefaultCharset()\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.decode(java.lang.String, java.lang.String)",
      "begin_line": 320,
      "end_line": 325,
      "comment": "\n     * Decodes a quoted-printable string into its original form using the specified string charset. Escaped characters\n     * are converted back to their original representation.\n     * \n     * @param pString\n     *                  quoted-printable string to convert into its original form\n     * @param charset\n     *                  the original string charset\n     * @return original string\n     * @throws DecoderException\n     *                  Thrown if quoted-printable decoding is unsuccessful\n     * @throws UnsupportedEncodingException\n     *                  Thrown if charset is not supported\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.decode(java.lang.String)",
      "begin_line": 339,
      "end_line": 348,
      "comment": "\n     * Decodes a quoted-printable string into its original form using the default string charset. Escaped characters are\n     * converted back to their original representation.\n     * \n     * @param pString\n     *                  quoted-printable string to convert into its original form\n     * @return original string\n     * @throws DecoderException\n     *                  Thrown if quoted-printable decoding is unsuccessful.\n     *                  Thrown if charset is not supported.\n     * @see #getDefaultCharset()\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.encode(java.lang.Object)",
      "begin_line": 360,
      "end_line": 372,
      "comment": "\n     * Encodes an object into its quoted-printable safe form. Unsafe characters are escaped.\n     * \n     * @param pObject\n     *                  string to convert to a quoted-printable form\n     * @return quoted-printable object\n     * @throws EncoderException\n     *                  Thrown if quoted-printable encoding is not applicable to objects of this type or if encoding is\n     *                  unsuccessful\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.decode(java.lang.Object)",
      "begin_line": 385,
      "end_line": 397,
      "comment": "\n     * Decodes a quoted-printable object into its original form. Escaped characters are converted back to their original\n     * representation.\n     * \n     * @param pObject\n     *                  quoted-printable object to convert into its original form\n     * @return original object\n     * @throws DecoderException\n     *                  Thrown if the argument is not a \u003ccode\u003eString\u003c/code\u003e or \u003ccode\u003ebyte[]\u003c/code\u003e. Thrown if a failure condition is\n     *                  encountered during the decode process.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.getDefaultCharset()",
      "begin_line": 404,
      "end_line": 406,
      "comment": "\n     * Returns the default charset used for string decoding and encoding.\n     * \n     * @return the default string charset.\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.net.QuotedPrintableCodec.encode(java.lang.String, java.lang.String)",
      "begin_line": 425,
      "end_line": 430,
      "comment": "\n     * Encodes a string into its quoted-printable form using the specified charset. Unsafe characters are escaped.\n     * \n     * \u003cp\u003e\n     * This function fully implements the quoted-printable encoding specification (rule #1 through rule #2)\n     * as defined in RFC 1521 and is suitable for encoding binary data and unformatted text.\n     * \u003c/p\u003e\n     * \n     * @param pString\n     *                  string to convert to quoted-printable form\n     * @param charset\n     *                  the charset for pString\n     * @return quoted-printable string\n     * \n     * @throws UnsupportedEncodingException\n     *                  Thrown if the charset is not supported\n     "
    }
  ]
}