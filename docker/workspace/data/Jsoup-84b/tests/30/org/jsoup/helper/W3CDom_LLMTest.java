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
    public void test0() throws IOException {
        // Change the input file
        File in = ParseTest.getFile("/htmltests/google-search.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test public void test1() {
        // Change the HTML string
        String html = "<fb:like>One</fb:like><fb:like>Two</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
        assertEquals("like", fb.getLocalName());
        assertEquals("fb:like", fb.getNodeName());
    }
    @Test
    public void test2() throws IOException {
        // Change the input file
        File in = ParseTest.getFile("/htmltests/google-search.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("Google Search"));
    }
    @Test
    public void test3() {
        // Change the HTML string
        String html = "<html><head><title>W3c Test</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c Test</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test4() throws IOException {
        // Change the input file
        File in = ParseTest.getFile("/htmltests/namespaces-2.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

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

        // https://github.com/jhy/jsoup/issues/977
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
        assertEquals("789", clip.getTextContent());

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
    public void test5() {
        // Change the HTML string
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test6() throws IOException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");

        W3CDom w3c = new W3CDom();
        Document wDoc = new Document();

        w3c.convert(doc, wDoc);

        assertEquals("", wDoc.getDocumentURI());
    }
    @Test
    public void test7() throws IOException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document(null);

        W3CDom w3c = new W3CDom();
        Document wDoc = new Document();

        w3c.convert(doc, wDoc);

        assertNull(wDoc.getDocumentURI());
    }
    @Test
    public void test8() {
        String html = "<like>One</like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new Document();
        new W3CDom().convert(doc, w3Doc);

        Node htmlEl = w3Doc.getChildNodes().item(0);

        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
        assertEquals("like", fb.getLocalName());
        assertEquals("like", fb.getNodeName());

    }
    @Test
    public void test9() throws IOException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");

        W3CDom w3c = new W3CDom();
        Document wDoc = new Document();

        w3c.convert(doc, wDoc);

        Node htmlEl = wDoc.getChildNodes().item(0);
        
        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        assertFalse(w3c.asString(wDoc).contains("ipod"));
    }
    @Test
    public void test10() {
        String html = "";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = new Document();

        w3c.convert(doc, wDoc);

        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", w3c.asString(wDoc));
    }
    @Test
    public void test11() throws IOException {
        org.jsoup.nodes.Document jsoupDoc = new org.jsoup.nodes.Document("");

        Document doc = new Document();

        new W3CDom().convert(jsoupDoc, doc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        assertEquals(1, doc.getChildNodes().getLength());
    }
    @Test
    public void test12() {
        String html = "";
        org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(html);
        Element body = new Element(org.jsoup.parser.Tag.valueOf("body"), "");
        body.attr("\"", "");
        body.attr("name\"", "");

        Document w3Doc = new Document();
        new W3CDom().convert(jsoupDoc, w3Doc);
    }
    @Test
    public void test13() {
        org.jsoup.nodes.Node source = null;
        int depth = 0;

        // Execution
        head(source, depth);

        // Assertion
        // The method should handle the case when the source node is null.
    }
    @Test
    public void test14() {
        // Setup
        org.jsoup.nodes.Element sourceEl = new org.jsoup.nodes.Element("div");

        // Execution
        head(sourceEl, 1);

        // Assertion
        // The method should create an Element node in the document.
    }
    @Test
    public void test15() {
        // Setup
        org.jsoup.nodes.TextNode sourceText = new org.jsoup.nodes.TextNode("Hello");

        // Execution
        head(sourceText, 1);

        // Assertion
        // The method should create a Text node in the document.
    }
    @Test
    public void test16() {
        // Setup
        org.jsoup.nodes.Comment sourceComment = new org.jsoup.nodes.Comment("This is a comment");

        // Execution
        head(sourceComment, 1);

        // Assertion
        // The method should create a Comment node in the document.
    }
    @Test
    public void test17() {
        // Setup
        org.jsoup.nodes.DataNode sourceData = new org.jsoup.nodes.DataNode("12345");

        // Execution
        head(sourceData, 1);

        // Assertion
        // The method should create a Text node in the document.
    }
    @Test
    public void test18() {
        // Test when source is an instance of org.jsoup.nodes.Element and dest.getParentNode() is an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("element");
        Node dest = new Element("element");
        
        // Add assertions here
        
        tail(source, 1);
        
        // Add additional assertions here
    }
    @Test
    public void test19() {
        // Test when source is not an instance of org.jsoup.nodes.Element and dest.getParentNode() is an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.TextNode("text");
        Node dest = new Element("element");
        
        // Add assertions here
        
        tail(source, 1);
        
        // Add additional assertions here
    }
    @Test
    public void test20() {
        // Test when source is an instance of org.jsoup.nodes.Element and dest.getParentNode() is not an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("element");
        Node dest = new TextNode("text");
        
        // Add assertions here
        
        tail(source, 1);
        
        // Add additional assertions here
    }
    @Test
    public void test21() {
        // Test when source is not an instance of org.jsoup.nodes.Element and dest.getParentNode() is not an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.TextNode("text");
        Node dest = new TextNode("text");
        
        // Add assertions here
        
        tail(source, 1);
        
        // Add additional assertions here
    }
    @Test
    public void test22() {
        // Test when source is an instance of org.jsoup.nodes.Element and dest.getParentNode() is an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("element");
        Node dest = new Element("element");
        
        // Add assertions here
        
        tail(source, 0);
        
        // Add additional assertions here
    }
    @Test
    public void test23() {
        // Test when source is not an instance of org.jsoup.nodes.Element and dest.getParentNode() is an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.TextNode("text");
        Node dest = new Element("element");
        
        // Add assertions here
        
        tail(source, 0);
        
        // Add additional assertions here
    }
    @Test
    public void test24() {
        // Test when source is an instance of org.jsoup.nodes.Element and dest.getParentNode() is not an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("element");
        Node dest = new TextNode("text");
        
        // Add assertions here
        
        tail(source, 0);
        
        // Add additional assertions here
    }
    @Test
    public void test25() {
        // Test when source is not an instance of org.jsoup.nodes.Element and dest.getParentNode() is not an instance of Element
        org.jsoup.nodes.Node source = new org.jsoup.nodes.TextNode("text");
        Node dest = new TextNode("text");
        
        // Add assertions here
        
        tail(source, 0);
        
        // Add additional assertions here
    }
@Test
public void test26() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals(doc.location(), wDoc.getDocumentURI() );
}
@Test
public void test27() {
    String html = "<fb:like>One</fb:like>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    Document w3Doc = new W3CDom().fromJsoup(doc);
    Node htmlEl = w3Doc.getFirstChild();

    assertNull(htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
    assertNull(fb.getNamespaceURI());
    assertEquals("like", fb.getLocalName());
    assertEquals("fb:like", fb.getNodeName());

}
@Test
public void test28() throws IOException {
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
public void test29() {
    String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
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
public void test30() throws IOException {
    File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(in, "UTF-8");

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

    // https://github.com/jhy/jsoup/issues/977
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
public void test31() {
    String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
    assertTrue(body.hasAttr("name\""));

    Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
}
@Test
public void test32() throws IOException {
    org.jsoup.nodes.Document doc = Jsoup.parse("");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals("", out);
}
@Test
public void test33() {
    String html = "<fb:like></fb:like>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    Document w3Doc = new W3CDom().fromJsoup(doc);
    Node htmlEl = w3Doc.getFirstChild();

    assertNull(htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());
}
@Test
public void test34() throws IOException {
    org.jsoup.nodes.Document doc = Jsoup.parse("");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals(null, htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    String out = w3c.asString(wDoc);
    assertTrue(out.isEmpty());
}
@Test
public void test35() {
    String html = "";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines(
            "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
            "</head><body></body></html>"
    );
    assertEquals(expected, out);
}
@Test
public void test36() throws IOException {
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse("");

    Document doc;
    org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
    doc = jDom.fromJsoup(jsoupDoc);

    Node htmlEl = doc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());
}
@Test
public void test37() {
    String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
    assertTrue(body.hasAttr("name\""));

    Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
}
@Test
public void test38() throws IOException {
    org.jsoup.nodes.Document doc = null;

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertNull(out);
}
@Test
public void test39() throws IOException {
    String html = "";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals("", out);
}
@Test
public void test40() throws IOException {
    String html = "<html><head></head><body></body></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals(html, out);
}
@Test
public void test41() {
    String html = "<fb:like>One</fb:like><invalid";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    Document w3Doc = new W3CDom().fromJsoup(doc);
    Node htmlEl = w3Doc.getFirstChild();

    assertNull(htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
    assertNull(fb.getNamespaceURI());
    assertEquals("like", fb.getLocalName());
    assertEquals("fb:like", fb.getNodeName());

}
@Test
public void test42() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals(null, htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    String out = w3c.asString(wDoc);
    assertFalse(out.contains("ipod"));
}
@Test
public void test43() {
    String html = "<html><head><title>W3c</title></head><body><invalid>What<script>alert('!')";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines(
            "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
            "</head><body><!-- comment --><invalid>What<script>alert('!')</script>" +
            "</invalid></body></html>"
    );
    assertEquals(expected, out);
}
@Test
public void test44() throws IOException {
    File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(in, "UTF-8");

    Document doc;
    org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
    doc = jDom.fromJsoup(jsoupDoc);

    Node htmlEl = doc.getChildNodes().item(0);
    assertEquals(null, htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node head = htmlEl.getFirstChild();
    assertEquals(null, head.getNamespaceURI());
    assertEquals("head", head.getLocalName());
    assertEquals("head", head.getNodeName());

    Node epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
    assertEquals("Check", epubTitle.getTextContent());
    assertEquals(null, epubTitle.getNamespaceURI());
    assertEquals("title", epubTitle.getLocalName());
    assertEquals("epub:title", epubTitle.getNodeName());

    Node xSection = epubTitle.getNextSibling().getNextSibling();
    assertEquals(null, xSection.getNamespaceURI());
    assertEquals("section", xSection.getLocalName());
    assertEquals("x:section", xSection.getNodeName());

    Node svg = xSection.getNextSibling().getNextSibling();
    assertEquals("http://www.w3.org/2000/svg", svg.getNamespaceURI());
    assertEquals("svg", svg.getLocalName());
    assertEquals("svg", svg.getNodeName());

    Node path = svg.getChildNodes().item(1);
    assertEquals("http://www.w3.org/2000/svg", path.getNamespaceURI());
    assertEquals("path", path.getLocalName());
    assertEquals("path", path.getNodeName());

    Node clip = path.getChildNodes().item(1);
    assertEquals(null, clip.getNamespaceURI());
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
public void test45() {
    String html = "<html><head></head><body style=\"color: red\" name\"></body></html>";
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    assertTrue(body.hasAttr("name\""));

    Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
}
    @Test
    public void test46() throws IOException {
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(new org.jsoup.nodes.Document(""));
        String out = w3c.asString(wDoc);
        assertTrue(out.isEmpty());
    }
    @Test
    public void test47() throws IOException {
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(null);
        String out = w3c.asString(wDoc);
        assertTrue(out.isEmpty());
    }
    @Test
    public void test48() throws IOException {
        String html = "<html><head><title>W3c</title></head></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head></html>"
        );
        assertEquals(expected, out);
    }
}