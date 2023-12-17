{
  "filepath": "/tmp/Jsoup-84b/src/main/java/org/jsoup/helper/W3CDom.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "W3CDom",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 28,
      "end_line": 178,
      "comment": "\n * Helper class to transform a {@link org.jsoup.nodes.Document} to a {@link org.w3c.dom.Document org.w3c.dom.Document},\n * for integration with toolsets that use the W3C DOM.\n "
    },
    {
      "type": "field",
      "varNames": [
        "factory"
      ],
      "begin_line": 29,
      "end_line": 29,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.helper.W3CDom.fromJsoup(org.jsoup.nodes.Document)",
      "begin_line": 36,
      "end_line": 49,
      "comment": "\n     * Convert a jsoup Document to a W3C Document.\n     * @param in jsoup doc\n     * @return w3c doc\n     "
    },
    {
      "type": "method",
      "signature": "convert(org.jsoup.nodes.Document, Document)",
      "begin_line": 58,
      "end_line": 64,
      "comment": "\n     * Converts a jsoup document into the provided W3C Document. If required, you can set options on the output document\n     * before converting.\n     * @param in jsoup doc\n     * @param out w3c doc\n     * @see org.jsoup.helper.W3CDom#fromJsoup(org.jsoup.nodes.Document)\n     "
    },
    {
      "type": "class_interface",
      "name": "W3CBuilder",
      "is_interface": false,
      "parent_types": [
        "org.jsoup.select.NodeVisitor"
      ],
      "begin_line": 69,
      "end_line": 158,
      "comment": "\n     * Implements the conversion by walking the input.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "xmlnsKey"
      ],
      "begin_line": 70,
      "end_line": 70,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "xmlnsPrefix"
      ],
      "begin_line": 71,
      "end_line": 71,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "doc"
      ],
      "begin_line": 73,
      "end_line": 73,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "namespacesStack"
      ],
      "begin_line": 74,
      "end_line": 74,
      "comment": " stack of namespaces, prefix \u003d\u003e urn"
    },
    {
      "type": "field",
      "varNames": [
        "dest"
      ],
      "begin_line": 75,
      "end_line": 75,
      "comment": ""
    },
    {
      "type": "constructor",
      "signature": "W3CBuilder(Document)",
      "begin_line": 77,
      "end_line": 80,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.helper.W3CDom.W3CBuilder.head(org.jsoup.nodes.Node, int)",
      "begin_line": 82,
      "end_line": 115,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.helper.W3CDom.W3CBuilder.tail(org.jsoup.nodes.Node, int)",
      "begin_line": 117,
      "end_line": 122,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "copyAttributes(org.jsoup.nodes.Node, Element)",
      "begin_line": 124,
      "end_line": 131,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.helper.W3CDom.W3CBuilder.updateNamespaces(org.jsoup.nodes.Element)",
      "begin_line": 136,
      "end_line": 156,
      "comment": "\n         * Finds any namespaces defined in this element. Returns any tag prefix.\n         "
    },
    {
      "type": "method",
      "signature": "asString(Document)",
      "begin_line": 165,
      "end_line": 177,
      "comment": "\n     * Serialize a W3C document to a String.\n     * @param doc Document\n     * @return Document as string\n     "
    }
  ]
}