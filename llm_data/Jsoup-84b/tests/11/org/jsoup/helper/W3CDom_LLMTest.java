package org.jsoup.helper;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.integration.ParseTest;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class W3CDom_LLMTest {
    @Test
    public void test0() {
        org.jsoup.nodes.Document doc = org.jsoup.nodes.Document.createShell("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test1() {
        String html = "<html></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals(html, out);
    }
    @Test
    public void test2() {
        String html = "<html><head></head><body></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals(html, out);
    }
    @Test
    public void test3() {
        String html = "<html>Hello World!</html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals(html, out);
    }
    @Test
    public void test4() {
        String html = "<html lang=\"en\"></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals(html, out);
    }
    @Test
    public void test5() {
        String html = "<html><head><title>Test</title></head><body><div>Hello</div></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals(html, out);
    }
    @Test
    public void test6() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", wDoc.getDocumentURI() );
    }
    @Test
    public void test7() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        
        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test8() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        doc.setBaseUri("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test9() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");
        jsoupDoc.setBaseUri("");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        // inherits default namespace
        Node head = htmlEl.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", head.getNamespaceURI());
        assertEquals("head", head.getLocalName());
        assertEquals("head", head.getNodeName());

        Node epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        assertEquals("Check", epubTitle.getTextContent());
        assertEquals("http://www.idpf.org/2007/ops", epubTitle.getNamespaceURI());
        assertEquals("title", epubTitle.getLocalName());
        assertEquals("epub:title", epubTitle.getNodeName());

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals("urn:test", xSection.getNamespaceURI());
        assertEquals("section", xSection.getLocalName());
        assertEquals("x:section", xSection.getNodeName());

        // does not keep last set namespace
        Node svg = xSection.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/2000/svg", svg.getNamespaceURI());
        assertEquals("svg", svg.getLocalName());
        assertEquals("svg", svg.getNodeName());

        Node path = svg.getChildNodes().item(1);
        assertEquals("http://www.w3.org/2000/svg", path.getNamespaceURI());
        assertEquals("path", path.getLocalName());
        assertEquals("path", path.getNodeName());

        Node clip = path.getChildNodes().item(1);
        assertEquals("http://example.com/clip", clip.getNamespaceURI());
        assertEquals("clip", clip.getLocalName());
        assertEquals("clip", clip.getNodeName());
        assertEquals("456", clip.getTextContent());

        Node picture = svg.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/1999/xhtml", picture.getNamespaceURI());
        assertEquals("picture", picture.getLocalName());
        assertEquals("picture", picture.getNodeName());

        Node img = picture.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", img.getNamespaceURI());
        assertEquals("img", img.getLocalName());
        assertEquals("img", img.getNodeName());
    }
    @Test
    public void test10() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));
        jsoupDoc.setBaseUri("");

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test11() {
        org.jsoup.nodes.Element sourceEl = new Element("head");
        sourceEl.setNamespaceURI("http://www.w3.org/1999/xhtml");
        org.jsoup.nodes.Node source = sourceEl;
        int depth = 0;

        Element dest = null;

        W3CDom w3c = new W3CDom();
        Document doc = w3c.getNewDocument();

        SourceConverter.head(source, depth);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("head", htmlEl.getLocalName());
        assertEquals("head", htmlEl.getNodeName());
    }
    @Test
    public void test12() {
        org.jsoup.nodes.TextNode sourceText = new TextNode("This is a text node");
        org.jsoup.nodes.Node source = sourceText;
        int depth = 1;

        Element dest = new Element("head");

        W3CDom w3c = new W3CDom();
        Document doc = w3c.getNewDocument();

        SourceConverter.head(source, depth);

        Text text = doc.createTextNode(sourceText.getWholeText());

        assertEquals(text, doc.getChildNodes().item(1));
    }
    @Test
    public void test13() {
        org.jsoup.nodes.Comment sourceComment = new Comment("This is a comment");
        org.jsoup.nodes.Node source = sourceComment;
        int depth = 1;

        Element dest = new Element("head");

        W3CDom w3c = new W3CDom();
        Document doc = w3c.getNewDocument();

        SourceConverter.head(source, depth);

        Comment comment = doc.createComment(sourceComment.getData());

        assertEquals(comment, doc.getChildNodes().item(1));
    }
    @Test
    public void test14() {
        org.jsoup.nodes.DataNode sourceData = new DataNode("This is a data node", "");
        org.jsoup.nodes.Node source = sourceData;
        int depth = 1;

        Element dest = new Element("head");

        W3CDom w3c = new W3CDom();
        Document doc = w3c.getNewDocument();

        SourceConverter.head(source, depth);

        Text node = doc.createTextNode(sourceData.getWholeData());

        assertEquals(node, doc.getChildNodes().item(1));
    }
    @Test
    public void test15() {
        org.jsoup.nodes.Node source = null;
        int depth = 1;

        Element dest = new Element("head");

        W3CDom w3c = new W3CDom();
        Document doc = w3c.getNewDocument();

        SourceConverter.head(source, depth);

        assertEquals(null, doc.getChildNodes().item(1));
    }
    @Test
    public void test16() {
        // Change the depth value of the method
        tail(org.jsoup.nodes.Node, 0);
    }
    @Test
    public void test17() {
        // Change the source value of the method to null
        tail(null, 2);
    }
    @Test
    public void test18() {
        // Change the instance of source to be an instance of org.jsoup.nodes.Document instead of org.jsoup.nodes.Element to fail the instanceof check
        tail(new org.jsoup.nodes.Document(""), 3);
    }
    @Test
    public void test19() {
        // Change the instance of dest.getParentNode() to be an instance of org.w3c.dom.Node instead of org.jsoup.nodes.Element to fail the instanceof check
        tail(new org.jsoup.nodes.Element(""), 4);
    }
    @Test
    public void test20() {
        // Change the instance of namespacesStack to an empty stack to throw an exception when popping
        namespacesStack = new Stack<>();
        tail(new org.jsoup.nodes.Element(""), 5);
    }
@Test
public void test21() {
   org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
   source.attr("invalid@attribute", "value");

   Element el = new Element("div");
   copyAttributes(source, el);

   assertFalse(el.hasAttribute("invalid@attribute"));
}
@Test
public void test22() {
   org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
   source.attr("valid_attribute", "value");

   Element el = new Element("div");
   copyAttributes(source, el);

   assertTrue(el.hasAttribute("valid_attribute"));
   assertEquals("value", el.getAttribute("valid_attribute"));
}
@Test
public void test23() {
   org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
   source.attr("attribute", null);

   Element el = new Element("div");
   copyAttributes(source, el);

   assertTrue(el.hasAttribute("attribute"));
   assertEquals("", el.getAttribute("attribute"));
}
@Test
public void test24() {
    String html = "<html xmlns:fb=\"http://www.facebook.com/2008/fbml\"><fb:like>One</fb:like></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node fbLike = htmlEl.getFirstChild().getFirstChild();
    assertEquals("http://www.facebook.com/2008/fbml", fbLike.getNamespaceURI());
    assertEquals("like", fbLike.getLocalName());
    assertEquals("fb:like", fbLike.getNodeName());
}
@Test
public void test25() {
    String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><div>Content</div></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node div = htmlEl.getFirstChild().getFirstChild();
    assertNull(div.getNamespaceURI());
    assertEquals("div", div.getLocalName());
    assertEquals("div", div.getNodeName());
}
@Test
public void test26() {
    String html = "<html xmlns:prefix=\"http://www.example.com/namespace\"><prefix:element>Value</prefix:element></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node prefixElement = htmlEl.getFirstChild().getFirstChild();
    assertEquals("http://www.example.com/namespace", prefixElement.getNamespaceURI());
    assertEquals("element", prefixElement.getLocalName());
    assertEquals("prefix:element", prefixElement.getNodeName());
}
@Test
public void test27() {
    String html = "<html><div>Content</div></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    String elementPrefix = w3c.updateNamespaces(doc.select("div").first());
    assertEquals("", elementPrefix);
}
@Test
public void test28() {
    String html = "<html xmlns:prefix=\"http://www.example.com/namespace\"><prefix:element>Value</prefix:element></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    String elementPrefix = w3c.updateNamespaces(doc.select("prefix\\:element").first());
    assertEquals("prefix", elementPrefix);
}
@Test
public void test29() {
    String html = "<html xmlns:prefix=\"http://www.example.com/namespace\"><prefix:element attr=\"value\"></prefix:element></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node element = htmlEl.getFirstChild().getFirstChild();
    assertEquals("http://www.example.com/namespace", element.getNamespaceURI());
    assertEquals("element", element.getLocalName());
    assertEquals("prefix:element", element.getNodeName());
    assertEquals("value", element.getAttributes().getNamedItem("attr").getNodeValue());
}
    @Test
    public void test30() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test31() {
        org.jsoup.nodes.Document doc = Jsoup.parse("");
        
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test32() {
        String html = "<html><head><title>W3c</title><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test33() {
        String html = "<html><head><title>&lt;&gt;&amp;&quot;&apos;</title></head><body><p>&lt;&gt;&amp;&quot;&apos;</p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertTrue(out.contains("&#60;&#62;&#38;&#34;&#39;"));
    }
}