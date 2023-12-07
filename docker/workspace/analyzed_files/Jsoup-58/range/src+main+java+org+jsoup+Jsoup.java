{
  "filepath": "/tmp/Jsoup-58b/src/main/java/org/jsoup/Jsoup.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "Jsoup",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 19,
      "end_line": 253,
      "comment": "\n The core public access point to the jsoup functionality.\n\n @author Jonathan Hedley "
    },
    {
      "type": "constructor",
      "signature": "org.jsoup.Jsoup.Jsoup()",
      "begin_line": 20,
      "end_line": 20,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.lang.String, java.lang.String)",
      "begin_line": 30,
      "end_line": 32,
      "comment": "\n     Parse HTML into a Document. The parser will make a sensible, balanced document tree out of any HTML.\n\n     @param html    HTML to parse\n     @param baseUri The URL where the HTML was retrieved from. Used to resolve relative URLs to absolute URLs, that occur\n     before the HTML declares a {@code \u003cbase href\u003e} tag.\n     @return sane HTML\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.lang.String, java.lang.String, org.jsoup.parser.Parser)",
      "begin_line": 44,
      "end_line": 46,
      "comment": "\n     Parse HTML into a Document, using the provided Parser. You can provide an alternate parser, such as a simple XML\n     (non-HTML) parser.\n\n     @param html    HTML to parse\n     @param baseUri The URL where the HTML was retrieved from. Used to resolve relative URLs to absolute URLs, that occur\n     before the HTML declares a {@code \u003cbase href\u003e} tag.\n     @param parser alternate {@link Parser#xmlParser() parser} to use.\n     @return sane HTML\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.lang.String)",
      "begin_line": 57,
      "end_line": 59,
      "comment": "\n     Parse HTML into a Document. As no base URI is specified, absolute URL detection relies on the HTML including a\n     {@code \u003cbase href\u003e} tag.\n\n     @param html HTML to parse\n     @return sane HTML\n\n     @see #parse(String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.connect(java.lang.String)",
      "begin_line": 72,
      "end_line": 74,
      "comment": "\n     * Creates a new {@link Connection} to a URL. Use to fetch and parse a HTML page.\n     * \u003cp\u003e\n     * Use examples:\n     * \u003cul\u003e\n     *  \u003cli\u003e\u003ccode\u003eDocument doc \u003d Jsoup.connect(\"http://example.com\").userAgent(\"Mozilla\").data(\"name\", \"jsoup\").get();\u003c/code\u003e\u003c/li\u003e\n     *  \u003cli\u003e\u003ccode\u003eDocument doc \u003d Jsoup.connect(\"http://example.com\").cookie(\"auth\", \"token\").post();\u003c/code\u003e\u003c/li\u003e\n     * \u003c/ul\u003e\n     * @param url URL to connect to. The protocol must be {@code http} or {@code https}.\n     * @return the connection. You can add data, cookies, and headers; set the user-agent, referrer, method; and then execute.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.io.File, java.lang.String, java.lang.String)",
      "begin_line": 87,
      "end_line": 89,
      "comment": "\n     Parse the contents of a file as HTML.\n\n     @param in          file to load HTML from\n     @param charsetName (optional) character set of file contents. Set to {@code null} to determine from {@code http-equiv} meta tag, if\n     present, or fall back to {@code UTF-8} (which is often safe to do).\n     @param baseUri     The URL where the HTML was retrieved from, to resolve relative links against.\n     @return sane HTML\n\n     @throws IOException if the file could not be found, or read, or if the charsetName is invalid.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.io.File, java.lang.String)",
      "begin_line": 102,
      "end_line": 104,
      "comment": "\n     Parse the contents of a file as HTML. The location of the file is used as the base URI to qualify relative URLs.\n\n     @param in          file to load HTML from\n     @param charsetName (optional) character set of file contents. Set to {@code null} to determine from {@code http-equiv} meta tag, if\n     present, or fall back to {@code UTF-8} (which is often safe to do).\n     @return sane HTML\n\n     @throws IOException if the file could not be found, or read, or if the charsetName is invalid.\n     @see #parse(File, String, String)\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.io.InputStream, java.lang.String, java.lang.String)",
      "begin_line": 117,
      "end_line": 119,
      "comment": "\n     Read an input stream, and parse it to a Document.\n\n     @param in          input stream to read. Make sure to close it after parsing.\n     @param charsetName (optional) character set of file contents. Set to {@code null} to determine from {@code http-equiv} meta tag, if\n     present, or fall back to {@code UTF-8} (which is often safe to do).\n     @param baseUri     The URL where the HTML was retrieved from, to resolve relative links against.\n     @return sane HTML\n\n     @throws IOException if the file could not be found, or read, or if the charsetName is invalid.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.io.InputStream, java.lang.String, java.lang.String, org.jsoup.parser.Parser)",
      "begin_line": 134,
      "end_line": 136,
      "comment": "\n     Read an input stream, and parse it to a Document. You can provide an alternate parser, such as a simple XML\n     (non-HTML) parser.\n\n     @param in          input stream to read. Make sure to close it after parsing.\n     @param charsetName (optional) character set of file contents. Set to {@code null} to determine from {@code http-equiv} meta tag, if\n     present, or fall back to {@code UTF-8} (which is often safe to do).\n     @param baseUri     The URL where the HTML was retrieved from, to resolve relative links against.\n     @param parser alternate {@link Parser#xmlParser() parser} to use.\n     @return sane HTML\n\n     @throws IOException if the file could not be found, or read, or if the charsetName is invalid.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parseBodyFragment(java.lang.String, java.lang.String)",
      "begin_line": 147,
      "end_line": 149,
      "comment": "\n     Parse a fragment of HTML, with the assumption that it forms the {@code body} of the HTML.\n\n     @param bodyHtml body HTML fragment\n     @param baseUri  URL to resolve relative URLs against.\n     @return sane HTML document\n\n     @see Document#body()\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parseBodyFragment(java.lang.String)",
      "begin_line": 159,
      "end_line": 161,
      "comment": "\n     Parse a fragment of HTML, with the assumption that it forms the {@code body} of the HTML.\n\n     @param bodyHtml body HTML fragment\n     @return sane HTML document\n\n     @see Document#body()\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.parse(java.net.URL, int)",
      "begin_line": 180,
      "end_line": 184,
      "comment": "\n     Fetch a URL, and parse it as HTML. Provided for compatibility; in most cases use {@link #connect(String)} instead.\n     \u003cp\u003e\n     The encoding character set is determined by the content-type header or http-equiv meta tag, or falls back to {@code UTF-8}.\n\n     @param url           URL to fetch (with a GET). The protocol must be {@code http} or {@code https}.\n     @param timeoutMillis Connection and read timeout, in milliseconds. If exceeded, IOException is thrown.\n     @return The parsed HTML.\n\n     @throws java.net.MalformedURLException if the request URL is not a HTTP or HTTPS URL, or is otherwise malformed\n     @throws HttpStatusException if the response is not OK and HTTP response errors are not ignored\n     @throws UnsupportedMimeTypeException if the response mime type is not supported and those errors are not ignored\n     @throws java.net.SocketTimeoutException if the connection times out\n     @throws IOException if a connection or read error occurs\n\n     @see #connect(String)\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.clean(java.lang.String, java.lang.String, org.jsoup.safety.Whitelist)",
      "begin_line": 197,
      "end_line": 202,
      "comment": "\n     Get safe HTML from untrusted input HTML, by parsing input HTML and filtering it through a white-list of permitted\n     tags and attributes.\n\n     @param bodyHtml  input untrusted HTML (body fragment)\n     @param baseUri   URL to resolve relative URLs against\n     @param whitelist white-list of permitted HTML elements\n     @return safe HTML (body fragment)\n\n     @see Cleaner#clean(Document)\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.clean(java.lang.String, org.jsoup.safety.Whitelist)",
      "begin_line": 214,
      "end_line": 216,
      "comment": "\n     Get safe HTML from untrusted input HTML, by parsing input HTML and filtering it through a white-list of permitted\n     tags and attributes.\n\n     @param bodyHtml  input untrusted HTML (body fragment)\n     @param whitelist white-list of permitted HTML elements\n     @return safe HTML (body fragment)\n\n     @see Cleaner#clean(Document)\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.clean(java.lang.String, java.lang.String, org.jsoup.safety.Whitelist, org.jsoup.nodes.Document.OutputSettings)",
      "begin_line": 232,
      "end_line": 238,
      "comment": "\n     * Get safe HTML from untrusted input HTML, by parsing input HTML and filtering it through a white-list of\n     * permitted tags and attributes.\n     * \u003cp\u003eThe HTML is treated as a body fragment; it\u0027s expected the cleaned HTML will be used within the body of an\n     * existing document. If you want to clean full documents, use {@link Cleaner#clean(Document)} instead, and add\n     * structural tags (\u003ccode\u003ehtml, head, body\u003c/code\u003e etc) to the whitelist.\n     *\n     * @param bodyHtml input untrusted HTML (body fragment)\n     * @param baseUri URL to resolve relative URLs against\n     * @param whitelist white-list of permitted HTML elements\n     * @param outputSettings document output settings; use to control pretty-printing and entity escape modes\n     * @return safe HTML (body fragment)\n     * @see Cleaner#clean(Document)\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.Jsoup.isValid(java.lang.String, org.jsoup.safety.Whitelist)",
      "begin_line": 249,
      "end_line": 251,
      "comment": "\n     Test if the input body HTML has only tags and attributes allowed by the Whitelist. Useful for form validation.\n     \u003cp\u003eThe input HTML should still be run through the cleaner to set up enforced attributes, and to tidy the output.\n     \u003cp\u003eAssumes the HTML is a body fragment (i.e. will be used in an existing HTML document body.)\n     @param bodyHtml HTML to test\n     @param whitelist whitelist to test against\n     @return true if no tags or attributes were removed; false otherwise\n     @see #clean(String, org.jsoup.safety.Whitelist) \n     "
    }
  ]
}