{
  "filepath": "/tmp/Codec-18b/src/main/java/org/apache/commons/codec/binary/StringUtils.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "StringUtils",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 39,
      "end_line": 421,
      "comment": "\n * Converts String to and from bytes using the encodings required by the Java specification. These encodings are\n * specified in \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003e\n * Standard charsets\u003c/a\u003e.\n *\n * \u003cp\u003eThis class is immutable and thread-safe.\u003c/p\u003e\n *\n * @see CharEncoding\n * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n * @version $Id$\n * @since 1.4\n "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.equals(java.lang.CharSequence, java.lang.CharSequence)",
      "begin_line": 71,
      "end_line": 82,
      "comment": "\n     * \u003cp\u003e\n     * Compares two CharSequences, returning \u003ccode\u003etrue\u003c/code\u003e if they represent equal sequences of characters.\n     * \u003c/p\u003e\n     *\n     * \u003cp\u003e\n     * \u003ccode\u003enull\u003c/code\u003es are handled without exceptions. Two \u003ccode\u003enull\u003c/code\u003e references are considered to be equal.\n     * The comparison is case sensitive.\n     * \u003c/p\u003e\n     *\n     * \u003cpre\u003e\n     * StringUtils.equals(null, null)   \u003d true\n     * StringUtils.equals(null, \"abc\")  \u003d false\n     * StringUtils.equals(\"abc\", null)  \u003d false\n     * StringUtils.equals(\"abc\", \"abc\") \u003d true\n     * StringUtils.equals(\"abc\", \"ABC\") \u003d false\n     * \u003c/pre\u003e\n     *\n     * \u003cp\u003e\n     * Copied from Apache Commons Lang r1583482 on April 10, 2014 (day of 3.3.2 release).\n     * \u003c/p\u003e\n     *\n     * @see Object#equals(Object)\n     * @param cs1\n     *            the first CharSequence, may be \u003ccode\u003enull\u003c/code\u003e\n     * @param cs2\n     *            the second CharSequence, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return \u003ccode\u003etrue\u003c/code\u003e if the CharSequences are equal (case-sensitive), or both \u003ccode\u003enull\u003c/code\u003e\n     * @since 1.10\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytes(java.lang.String, java.nio.charset.Charset)",
      "begin_line": 93,
      "end_line": 98,
      "comment": "\n     * Calls {@link String#getBytes(Charset)}\n     *\n     * @param string\n     *            The string to encode (if null, return null).\n     * @param charset\n     *            The {@link Charset} to encode the \u003ccode\u003eString\u003c/code\u003e\n     * @return the encoded bytes\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getByteBuffer(java.lang.String, java.nio.charset.Charset)",
      "begin_line": 110,
      "end_line": 115,
      "comment": "\n     * Calls {@link String#getBytes(Charset)}\n     *\n     * @param string\n     *            The string to encode (if null, return null).\n     * @param charset\n     *            The {@link Charset} to encode the \u003ccode\u003eString\u003c/code\u003e\n     * @return the encoded bytes\n     * @since 1.11\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getByteBufferUtf8(java.lang.String)",
      "begin_line": 131,
      "end_line": 133,
      "comment": "\n     * Encodes the given string into a byte buffer using the UTF-8 charset, storing the result into a new byte\n     * array.\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_8} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n     * @see #getBytesUnchecked(String, String)\n     * @since 1.11\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytesIso8859_1(java.lang.String)",
      "begin_line": 149,
      "end_line": 151,
      "comment": "\n     * Encodes the given string into a sequence of bytes using the ISO-8859-1 charset, storing the result into a new\n     * byte array.\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#ISO_8859_1} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n     * @see #getBytesUnchecked(String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytesUnchecked(java.lang.String, java.lang.String)",
      "begin_line": 173,
      "end_line": 182,
      "comment": "\n     * Encodes the given string into a sequence of bytes using the named charset, storing the result into a new byte\n     * array.\n     * \u003cp\u003e\n     * This method catches {@link UnsupportedEncodingException} and rethrows it as {@link IllegalStateException}, which\n     * should never happen for a required charset name. Use this method when the encoding is required to be in the JRE.\n     * \u003c/p\u003e\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @param charsetName\n     *            The name of a required {@link java.nio.charset.Charset}\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws IllegalStateException\n     *             Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen for a\n     *             required charset name.\n     * @see CharEncoding\n     * @see String#getBytes(String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytesUsAscii(java.lang.String)",
      "begin_line": 198,
      "end_line": 200,
      "comment": "\n     * Encodes the given string into a sequence of bytes using the US-ASCII charset, storing the result into a new byte\n     * array.\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#US_ASCII} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n     * @see #getBytesUnchecked(String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytesUtf16(java.lang.String)",
      "begin_line": 216,
      "end_line": 218,
      "comment": "\n     * Encodes the given string into a sequence of bytes using the UTF-16 charset, storing the result into a new byte\n     * array.\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_16} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n     * @see #getBytesUnchecked(String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytesUtf16Be(java.lang.String)",
      "begin_line": 234,
      "end_line": 236,
      "comment": "\n     * Encodes the given string into a sequence of bytes using the UTF-16BE charset, storing the result into a new byte\n     * array.\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_16BE} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n     * @see #getBytesUnchecked(String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytesUtf16Le(java.lang.String)",
      "begin_line": 252,
      "end_line": 254,
      "comment": "\n     * Encodes the given string into a sequence of bytes using the UTF-16LE charset, storing the result into a new byte\n     * array.\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_16LE} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n     * @see #getBytesUnchecked(String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.getBytesUtf8(java.lang.String)",
      "begin_line": 270,
      "end_line": 272,
      "comment": "\n     * Encodes the given string into a sequence of bytes using the UTF-8 charset, storing the result into a new byte\n     * array.\n     *\n     * @param string\n     *            the String to encode, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return encoded bytes, or \u003ccode\u003enull\u003c/code\u003e if the input string was \u003ccode\u003enull\u003c/code\u003e\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_8} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     * @see \u003ca href\u003d\"http://download.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html\"\u003eStandard charsets\u003c/a\u003e\n     * @see #getBytesUnchecked(String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newIllegalStateException(java.lang.String, java.io.UnsupportedEncodingException)",
      "begin_line": 274,
      "end_line": 277,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newString(byte[], java.nio.charset.Charset)",
      "begin_line": 291,
      "end_line": 293,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the given charset.\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters\n     * @param charset\n     *            The {@link Charset} to encode the \u003ccode\u003eString\u003c/code\u003e; not {@code null}\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the given charset,\n     *         or \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws NullPointerException\n     *             Thrown if charset is {@code null}\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newString(byte[], java.lang.String)",
      "begin_line": 314,
      "end_line": 323,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the given charset.\n     * \u003cp\u003e\n     * This method catches {@link UnsupportedEncodingException} and re-throws it as {@link IllegalStateException}, which\n     * should never happen for a required charset name. Use this method when the encoding is required to be in the JRE.\n     * \u003c/p\u003e\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters, may be \u003ccode\u003enull\u003c/code\u003e\n     * @param charsetName\n     *            The name of a required {@link java.nio.charset.Charset}\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the given charset,\n     *         or \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws IllegalStateException\n     *             Thrown when a {@link UnsupportedEncodingException} is caught, which should never happen for a\n     *             required charset name.\n     * @see CharEncoding\n     * @see String#String(byte[], String)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newStringIso8859_1(byte[])",
      "begin_line": 337,
      "end_line": 339,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the ISO-8859-1 charset.\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters, may be \u003ccode\u003enull\u003c/code\u003e\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the ISO-8859-1 charset, or\n     *         \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#ISO_8859_1} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newStringUsAscii(byte[])",
      "begin_line": 353,
      "end_line": 355,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the US-ASCII charset.\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the US-ASCII charset,\n     *         or \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#US_ASCII} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newStringUtf16(byte[])",
      "begin_line": 369,
      "end_line": 371,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the UTF-16 charset.\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the UTF-16 charset\n     *         or \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_16} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newStringUtf16Be(byte[])",
      "begin_line": 385,
      "end_line": 387,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the UTF-16BE charset.\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the UTF-16BE charset,\n     *         or \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_16BE} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newStringUtf16Le(byte[])",
      "begin_line": 401,
      "end_line": 403,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the UTF-16LE charset.\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the UTF-16LE charset,\n     *         or \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_16LE} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.codec.binary.StringUtils.newStringUtf8(byte[])",
      "begin_line": 417,
      "end_line": 419,
      "comment": "\n     * Constructs a new \u003ccode\u003eString\u003c/code\u003e by decoding the specified array of bytes using the UTF-8 charset.\n     *\n     * @param bytes\n     *            The bytes to be decoded into characters\n     * @return A new \u003ccode\u003eString\u003c/code\u003e decoded from the specified array of bytes using the UTF-8 charset,\n     *         or \u003ccode\u003enull\u003c/code\u003e if the input byte array was \u003ccode\u003enull\u003c/code\u003e.\n     * @throws NullPointerException\n     *             Thrown if {@link Charsets#UTF_8} is not initialized, which should never happen since it is\n     *             required by the Java platform specification.\n     * @since As of 1.7, throws {@link NullPointerException} instead of UnsupportedEncodingException\n     "
    }
  ]
}