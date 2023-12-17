{
  "filepath": "/tmp/Jsoup-93b/src/main/java/org/jsoup/nodes/FormElement.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "FormElement",
      "is_interface": false,
      "parent_types": [
        "org.jsoup.nodes.Element"
      ],
      "begin_line": 17,
      "end_line": 114,
      "comment": "\n * A HTML Form Element provides ready access to the form fields/controls that are associated with it. It also allows a\n * form to easily be submitted.\n "
    },
    {
      "type": "field",
      "varNames": [
        "elements"
      ],
      "begin_line": 18,
      "end_line": 18,
      "comment": ""
    },
    {
      "type": "constructor",
      "signature": "org.jsoup.nodes.FormElement.FormElement(org.jsoup.parser.Tag, java.lang.String, org.jsoup.nodes.Attributes)",
      "begin_line": 27,
      "end_line": 29,
      "comment": "\n     * Create a new, standalone form element.\n     *\n     * @param tag        tag of this element\n     * @param baseUri    the base URI\n     * @param attributes initial attributes\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.FormElement.elements()",
      "begin_line": 35,
      "end_line": 37,
      "comment": "\n     * Get the list of form control elements associated with this form.\n     * @return form controls associated with this element.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.FormElement.addElement(org.jsoup.nodes.Element)",
      "begin_line": 44,
      "end_line": 47,
      "comment": "\n     * Add a form control element to this form.\n     * @param element form control to add\n     * @return this form element, for chaining\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.FormElement.removeChild(org.jsoup.nodes.Node)",
      "begin_line": 49,
      "end_line": 53,
      "comment": ""
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.FormElement.submit()",
      "begin_line": 62,
      "end_line": 71,
      "comment": "\n     * Prepare to submit this form. A Connection object is created with the request set up from the form values. You\n     * can then set up other options (like user-agent, timeout, cookies), then execute it.\n     * @return a connection prepared from the values of this form.\n     * @throws IllegalArgumentException if the form\u0027s absolute action URL cannot be determined. Make sure you pass the\n     * document\u0027s base URI when parsing.\n     "
    },
    {
      "type": "method",
      "signature": "org.jsoup.nodes.FormElement.formData()",
      "begin_line": 78,
      "end_line": 113,
      "comment": "\n     * Get the data that this form submits. The returned list is a copy of the data, and changes to the contents of the\n     * list will not be reflected in the DOM.\n     * @return a list of key vals\n     "
    }
  ]
}