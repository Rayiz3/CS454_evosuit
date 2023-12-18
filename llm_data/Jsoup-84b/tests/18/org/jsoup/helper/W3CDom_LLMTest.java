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
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test1() {
        org.jsoup.nodes.Document doc = Jsoup.parse("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test2() {
        String html = "<html xmlns=http://www.w3.org/1999/xhtml><head><title>W3c</title></head><body></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("http://www.w3.org/1999/xhtml"));
    }
    @Test
    public void test3() {
        String html = "<html><head><title>&lt;W3c&gt;</title></head><body></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("&lt;W3c&gt;"));
    }
    @Test
    public void test4() {
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        assertNull(wDoc);
    }
    @Test
    public void test5() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        Document wDoc = new DocumentImpl();

        new W3CDom().convert(doc, wDoc);

        assertEquals("", wDoc.getDoctype());
        assertNull(wDoc.getDocumentElement());
    }
    @Test
    public void test6() {
        String html = "<html><head></head><body><div><span></span></div></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document wDoc = new DocumentImpl();

        new W3CDom().convert(doc, wDoc);

        assertNotNull(wDoc.getDocumentElement());
        assertEquals("html", wDoc.getDocumentElement().getTagName());
        assertEquals("head", wDoc.getDocumentElement().getFirstChild().getTagName());
        assertEquals("body", wDoc.getDocumentElement().getFirstChild().getNextSibling().getTagName());
        assertEquals("div", wDoc.getDocumentElement().getFirstChild().getNextSibling().getFirstChild().getTagName());
        assertEquals("span", wDoc.getDocumentElement().getFirstChild().getNextSibling().getFirstChild().getFirstChild().getTagName());
    }
    @Test
    public void test7() {
        String html = "<html lang=\"en\" dir=\"ltr\"><head><title>Test</title></head><body></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document wDoc = new DocumentImpl();

        new W3CDom().convert(doc, wDoc);

        assertNotNull(wDoc.getDocumentElement());
        assertEquals("html", wDoc.getDocumentElement().getTagName());
        assertEquals("en", wDoc.getDocumentElement().getAttribute("lang"));
        assertEquals("ltr", wDoc.getDocumentElement().getAttribute("dir"));
    }
    @Test
    public void test8() {
        String html = "<html><head></head><body>Test</body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document wDoc = new DocumentImpl();

        new W3CDom().convert(doc, wDoc);

        assertNotNull(wDoc.getDocumentElement());
        assertEquals("html", wDoc.getDocumentElement().getTagName());
        assertEquals("Test", wDoc.getDocumentElement().getFirstChild().getNextSibling().getTextContent());
    }
    @Test
    public void test9() {
        org.jsoup.nodes.Element sourceEl = new org.jsoup.nodes.Element("div");
        sourceEl.tagName("div");
        sourceEl.attr("class", "test");
        
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Node source = (Node) sourceEl;
        int depth = 0;
        
        // Test the creation of a new element
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        w3c.head(source, depth);
        Node createdElement = wDoc.getDocumentElement().getChildNodes().item(0);
        
        assertEquals("div", createdElement.getNodeName());
        assertEquals(null, createdElement.getNamespaceURI());
        assertEquals("div", createdElement.getLocalName());
    }
    @Test
    public void test10() {
        org.jsoup.nodes.TextNode sourceText = new org.jsoup.nodes.TextNode("This is a test");
        
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Node source = (Node) sourceText;
        int depth = 0;
        
        // Test the creation of a new text node
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        w3c.head(source, depth);
        Node createdText = wDoc.getDocumentElement().getChildNodes().item(0);
        
        assertEquals("This is a test", createdText.getNodeValue());
    }
    @Test
    public void test11() {
        org.jsoup.nodes.Comment sourceComment = new org.jsoup.nodes.Comment("This is a comment");
        
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Node source = (Node) sourceComment;
        int depth = 0;
        
        // Test the creation of a new comment node
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        w3c.head(source, depth);
        Node createdComment = wDoc.getDocumentElement().getChildNodes().item(0);
        
        assertEquals("This is a comment", createdComment.getNodeValue());
    }
    @Test
    public void test12() {
        org.jsoup.nodes.DataNode sourceData = new org.jsoup.nodes.DataNode("This is a data node");
        
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Node source = (Node) sourceData;
        int depth = 0;
        
        // Test the creation of a new data node
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        w3c.head(source, depth);
        Node createdDataNode = wDoc.getDocumentElement().getChildNodes().item(0);
        
        assertEquals("This is a data node", createdDataNode.getNodeValue());
    }
    @Test
    public void test13() {
        org.jsoup.nodes.Node sourceNode = new org.jsoup.nodes.Node(org.jsoup.parser.Parser.parse("&"));
        
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Node source = (Node) sourceNode;
        int depth = 0;
        
        // Test the handling of unhandled nodes
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        w3c.head(source, depth);
        NodeList children = wDoc.getChildNodes();
        
        assertEquals(0, children.getLength());
    }
    @Test
    public void test14() {
        // Mutant 1: Change dest.getParentNode() instanceof Element to dest.getParentNode() instanceof Node
        // Mutant 2: Change dest.getParentNode() instanceof Element to source instanceof Node
        // Mutant 3: Change dest.getParentNode() instanceof Element to source instanceof Element

        org.jsoup.nodes.Element sourceElement = new org.jsoup.nodes.Element("div", "");

        org.jsoup.nodes.Node destParentElement = new org.jsoup.nodes.Element("span", "");
        org.jsoup.nodes.Node dest = destParentElement;

        org.jsoup.nodes.Node source = sourceElement;
        
        TailTester(source, dest);
        
        assertEquals(sourceElement, dest);
    }
    private void TailTester(org.jsoup.nodes.Node source, org.jsoup.nodes.Node dest) {
        if (source instanceof org.jsoup.nodes.Element && dest.getParentNode() instanceof Element) {
            dest = (Element) dest.getParentNode(); // undescend. cromulent.
        }
        namespacesStack.pop();
    }
@Test
public void test15() {
    org.jsoup.nodes.Node source = new Element("div").attr("$$$", "value");
    Element el = new Element("div");
    
    copyAttributes(source, el);
    
    assertFalse(el.hasAttr("$$$"));
}
@Test
public void test16() {
    org.jsoup.nodes.Node source = new Element("div").attr("validKey", "\"");
    Element el = new Element("div");
    
    copyAttributes(source, el);
    
    assertFalse(el.hasAttr("validKey"));
}
@Test
public void test17() {
    org.jsoup.nodes.Node source = new Element("div").attr("validKey", "validValue");
    Element el = new Element("div");
    
    copyAttributes(source, el);
    
    assertTrue(el.hasAttr("validKey"));
    assertEquals("validValue", el.attr("validKey"));
}
    @Test
    public void test18() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test19() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        doc.location("https://www.google.com/");

        String out = w3c.asString(wDoc);
        assertNotEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test20() {
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
    public void test21() {
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
        assertNotEquals("f:like", fb.getNodeName());

    }
    @Test
    public void test22() throws IOException {
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
    public void test23() {
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
    public void test24() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('~')</script>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('~')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test25() throws IOException {
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
    public void test26() throws IOException {
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
        assertNotEquals("x:section", xSection.getNodeName());

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
    public void test27() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test28() {
        String html = "<html><head></head><body style=\"color: red\" \" name\" test=\"value\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test29() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        
        doc.body().removeAll();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertFalse(out.contains("<body>"));
        assertFalse(out.contains("ipod"));
    }
    @Test
    public void test30() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        doc.select("html").remove();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node bodyEl = wDoc.getChildNodes().item(0);
        assertEquals(null, bodyEl.getNamespaceURI());
        assertEquals("body", bodyEl.getLocalName());
        assertEquals("body", bodyEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertFalse(out.contains("<html>"));
        assertFalse(out.contains("html"));
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test31() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        doc.select("doctype").remove();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getFirstChild();
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertFalse(out.contains("<!DOCTYPE"));
        assertTrue(out.contains("ipod"));
    }
}