{
  "filepath": "/tmp/Jsoup-58b/src/main/java/org/jsoup/safety/Cleaner.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "Cleaner",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 34,
      "end_line": 165,
      "comment": "\n The whitelist based HTML cleaner. Use to ensure that end-user provided HTML contains only the elements and attributes\n that you are expecting; no junk, and no cross-site scripting attacks!\n \u003cp\u003e\n The HTML cleaner parses the input as HTML and then runs it through a white-list, so the output HTML can only contain\n HTML that is allowed by the whitelist.\n \u003c/p\u003e\n \u003cp\u003e\n It is assumed that the input HTML is a body fragment; the clean methods only pull from the source\u0027s body, and the\n canned white-lists only allow body contained tags.\n \u003c/p\u003e\n \u003cp\u003e\n Rather than interacting directly with a Cleaner object, generally see the {@code clean} methods in {@link org.jsoup.Jsoup}.\n \u003c/p\u003e\n "
    },
    {
      "type": "field",
      "varNames": [
        "whitelist"
      ],
      "begin_line": 35,
      "end_line": 35,
      "comment": ""
    },
    {
      "type": "constructor",
      "signature": "org.jsoup.safety.Cleaner.Cleaner(org.jsoup.safety.Whitelist)",
      "begin_line": 41,
      "end_line": 44,
      "comment": "\n     Create a new cleaner, that sanitizes documents using the supplied whitelist.\n     @param whitelist white-list to clean with\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.safety.Cleaner.clean(org.jsoup.nodes.Document)",
      "begin_line": 52,
      "end_line": 60,
      "comment": "\n     Creates a new, clean document, from the original dirty document, containing only elements allowed by the whitelist.\n     The original document is not modified. Only elements from the dirt document\u0027s \u003ccode\u003ebody\u003c/code\u003e are used.\n     @param dirtyDocument Untrusted base document to clean.\n     @return cleaned document.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.safety.Cleaner.isValid(org.jsoup.nodes.Document)",
      "begin_line": 73,
      "end_line": 79,
      "comment": "\n     Determines if the input document \u003cb\u003ebody\u003c/b\u003eis valid, against the whitelist. It is considered valid if all the tags and attributes\n     in the input HTML are allowed by the whitelist, and that there is no content in the \u003ccode\u003ehead\u003c/code\u003e.\n     \u003cp\u003e\n     This method can be used as a validator for user input. An invalid document will still be cleaned successfully\n     using the {@link #clean(Document)} document. If using as a validator, it is recommended to still clean the document\n     to ensure enforced attributes are set correctly, and that the output is tidied.\n     \u003c/p\u003e\n     @param dirtyDocument document to test\n     @return true if no tags or attributes need to be removed; false if they do\n     "
    },
    {
      "type": "class_interface",
      "name": "CleaningVisitor",
      "is_interface": false,
      "parent_types": [
        "org.jsoup.select.NodeVisitor"
      ],
      "begin_line": 85,
      "end_line": 127,
      "comment": "\n     Iterates the input and copies trusted nodes (tags, attributes, text) into the destination.\n     "
    },
    {
      "type": "field",
      "varNames": [
        "numDiscarded"
      ],
      "begin_line": 86,
      "end_line": 86,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "root"
      ],
      "begin_line": 87,
      "end_line": 87,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "destination"
      ],
      "begin_line": 88,
      "end_line": 88,
      "comment": " current element to append nodes to"
    },
    {
      "type": "constructor",
      "signature": "org.jsoup.safety.Cleaner.CleaningVisitor.CleaningVisitor(org.jsoup.nodes.Element, org.jsoup.nodes.Element)",
      "begin_line": 90,
      "end_line": 93,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.safety.Cleaner.CleaningVisitor.head(org.jsoup.nodes.Node, int)",
      "begin_line": 95,
      "end_line": 120,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.safety.Cleaner.CleaningVisitor.tail(org.jsoup.nodes.Node, int)",
      "begin_line": 122,
      "end_line": 126,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.safety.Cleaner.copySafeNodes(org.jsoup.nodes.Element, org.jsoup.nodes.Element)",
      "begin_line": 129,
      "end_line": 134,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.safety.Cleaner.createSafeElement(org.jsoup.nodes.Element)",
      "begin_line": 136,
      "end_line": 153,
      "comment": ""
    },
    {
      "type": "class_interface",
      "name": "ElementMeta",
      "is_interface": false,
      "parent_types": [],
      "begin_line": 155,
      "end_line": 163,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "el"
      ],
      "begin_line": 156,
      "end_line": 156,
      "comment": ""
    },
    {
      "type": "field",
      "varNames": [
        "numAttribsDiscarded"
      ],
      "begin_line": 157,
      "end_line": 157,
      "comment": ""
    },
    {
      "type": "constructor",
      "signature": "org.jsoup.safety.Cleaner.ElementMeta.ElementMeta(org.jsoup.nodes.Element, int)",
      "begin_line": 159,
      "end_line": 162,
      "comment": ""
    }
  ]
}