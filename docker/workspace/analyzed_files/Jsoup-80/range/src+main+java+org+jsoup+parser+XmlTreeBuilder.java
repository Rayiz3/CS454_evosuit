{
  "filepath": "/tmp/Jsoup-80b/src/main/java/org/jsoup/parser/XmlTreeBuilder.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "XmlTreeBuilder",
      "is_interface": false,
      "parent_types": [
        "org.jsoup.parser.TreeBuilder"
      ],
      "begin_line": 18,
      "end_line": 143,
      "comment": "\n * Use the {@code XmlTreeBuilder} when you want to parse XML without any of the HTML DOM rules being applied to the\n * document.\n * \u003cp\u003eUsage example: {@code Document xmlDoc \u003d Jsoup.parse(html, baseUrl, Parser.xmlParser());}\u003c/p\u003e\n *\n * @author Jonathan Hedley\n "
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.defaultSettings()",
      "begin_line": 19,
      "end_line": 21,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.parse(java.io.Reader, java.lang.String)",
      "begin_line": 23,
      "end_line": 25,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.parse(java.lang.String, java.lang.String)",
      "begin_line": 27,
      "end_line": 29,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.initialiseParse(java.io.Reader, java.lang.String, org.jsoup.parser.ParseErrorList, org.jsoup.parser.ParseSettings)",
      "begin_line": 31,
      "end_line": 36,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.process(org.jsoup.parser.Token)",
      "begin_line": 38,
      "end_line": 63,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.insertNode(org.jsoup.nodes.Node)",
      "begin_line": 65,
      "end_line": 67,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.insert(org.jsoup.parser.Token.StartTag)",
      "begin_line": 69,
      "end_line": 81,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.insert(org.jsoup.parser.Token.Comment)",
      "begin_line": 83,
      "end_line": 97,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.insert(org.jsoup.parser.Token.Character)",
      "begin_line": 99,
      "end_line": 102,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.insert(org.jsoup.parser.Token.Doctype)",
      "begin_line": 104,
      "end_line": 108,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.popStackToClose(org.jsoup.parser.Token.EndTag)",
      "begin_line": 116,
      "end_line": 136,
      "comment": "\n     * If the stack contains an element with this tag\u0027s name, pop up the stack to remove the first occurrence. If not\n     * found, skips.\n     *\n     * @param endTag tag to close\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.parser.XmlTreeBuilder.parseFragment(java.lang.String, java.lang.String, org.jsoup.parser.ParseErrorList, org.jsoup.parser.ParseSettings)",
      "begin_line": 138,
      "end_line": 142,
      "comment": ""
    }
  ]
}