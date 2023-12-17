{
  "filepath": "/tmp/Jsoup-86b/src/main/java/org/jsoup/nodes/Comment.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "Comment",
      "is_interface": false,
      "parent_types": [
        "org.jsoup.nodes.LeafNode"
      ],
      "begin_line": 12,
      "end_line": 85,
      "comment": "\n A comment node.\n\n @author Jonathan Hedley, jonathan@hedley.net "
    },
    {
      "type": "field",
      "varNames": [
        "COMMENT_KEY"
      ],
      "begin_line": 13,
      "end_line": 13,
      "comment": ""
    },
    {
      "type": "constructor",
      "signature": "org.jsoup.nodes.Comment.Comment(java.lang.String)",
      "begin_line": 19,
      "end_line": 21,
      "comment": "\n     Create a new comment node.\n     @param data The contents of the comment\n     "
    },
    {
      "type": "constructor",
      "signature": "org.jsoup.nodes.Comment.Comment(java.lang.String, java.lang.String)",
      "begin_line": 29,
      "end_line": 31,
      "comment": "\n     Create a new comment node.\n     @param data The contents of the comment\n     @param baseUri base URI not used. This is a leaf node.\n     @deprecated\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.Comment.nodeName()",
      "begin_line": 33,
      "end_line": 35,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.Comment.getData()",
      "begin_line": 41,
      "end_line": 43,
      "comment": "\n     Get the contents of the comment.\n     @return comment content\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.Comment.outerHtmlHead(java.lang.Appendable, int, org.jsoup.nodes.Document.OutputSettings)",
      "begin_line": 45,
      "end_line": 52,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.Comment.outerHtmlTail(java.lang.Appendable, int, org.jsoup.nodes.Document.OutputSettings)",
      "begin_line": 54,
      "end_line": 54,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.Comment.toString()",
      "begin_line": 56,
      "end_line": 59,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.Comment.isXmlDeclaration()",
      "begin_line": 65,
      "end_line": 68,
      "comment": "\n     * Check if this comment looks like an XML Declaration.\n     * @return true if it looks like, maybe, it\u0027s an XML Declaration.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.Comment.asXmlDeclaration()",
      "begin_line": 74,
      "end_line": 84,
      "comment": "\n     * Attempt to cast this comment to an XML Declaration note.\n     * @return an XML declaration if it could be parsed as one, null otherwise.\n     "
    }
  ]
}