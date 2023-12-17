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
    org.jsoup.nodes.Document doc = Jsoup.parse("");
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    assertNotNull(wDoc);
    assertNull(wDoc.getDocumentElement());
}
@Test
public void test1() {
    String html = "<html><body><p>Content</p></body></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    assertNotNull(wDoc);
    assertNotNull(wDoc.getDocumentElement());
    assertNull(wDoc.getDocumentElement().getElementsByTagName("head"));
}
@Test
public void test2() {
    String html = "<html><head></head></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    assertNotNull(wDoc);
    assertNotNull(wDoc.getDocumentElement());
    assertNull(wDoc.getDocumentElement().getElementsByTagName("body"));
}
@Test
public void test3() {
    String html = "<html><head><title>&lt;Title&gt;</title></head><body>&lt;Body&gt;</body></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    assertNotNull(wDoc);
    assertNotNull(wDoc.getDocumentElement());
    Element head = (Element) wDoc.getDocumentElement().getElementsByTagName("head").item(0);
    assertEquals("<Title>", head.getElementsByTagName("title").item(0).getTextContent());
    Element body = (Element) wDoc.getDocumentElement().getElementsByTagName("body").item(0);
    assertEquals("<Body>", body.getTextContent());
}
@Test
public void test4() {
    String html = "<html<<head>Incomplete <Title>Tag<head></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    assertNotNull(wDoc);
    assertNotNull(wDoc.getDocumentElement());
    Element head = (Element) wDoc.getDocumentElement().getElementsByTagName("head").item(0);
    assertEquals("Incomplete", head.getTextContent());
    Element title = (Element) wDoc.getDocumentElement().getElementsByTagName("title").item(0);
    assertNotNull(title);
    assertEquals("Tag", title.getTextContent());
}
@Test
public void test5() {
    org.jsoup.nodes.Document doc = Jsoup.parse("<html></html>");
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    assertNotNull(wDoc);
    assertNotNull(wDoc.getDocumentElement());
}
@Test
public void test6() {
    String html = "<html><head><title>Title</title></head><body><h1>Heading</h1><p>Content</p><div>Extra</div></body></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    assertNotNull(wDoc);
    assertNotNull(wDoc.getDocumentElement());

    Element head = (Element) wDoc.getDocumentElement().getElementsByTagName("head").item(0);
    assertNotNull(head);
    Element title = (Element) head.getElementsByTagName("title").item(0);
    assertNotNull(title);
    assertEquals("Title", title.getTextContent());

    Element body = (Element) wDoc.getDocumentElement().getElementsByTagName("body").item(0);
    assertNotNull(body);

    Element h1 = (Element) body.getElementsByTagName("h1").item(0);
    assertNotNull(h1);
    assertEquals("Heading", h1.getTextContent());

    Element p = (Element) body.getElementsByTagName("p").item(0);
    assertNotNull(p);
    assertEquals("Content", p.getTextContent());

    Element div = (Element) body.getElementsByTagName("div").item(0);
    assertNotNull(div);
    assertEquals("Extra", div.getTextContent());
}
@Test
public void test7() {
    org.jsoup.nodes.Document doc = Jsoup.parse("");
    
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    
    assertEquals("", out);
}
@Test
public void test8() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
    doc.setBaseUri("");
    
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    
    assertNull(wDoc.getDocumentURI());
    assertFalse(out.contains("ipod"));
}
@Test
public void test9() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
    
    org.jsoup.nodes.Element rootEl = new org.jsoup.nodes.Element("root");
    rootEl.appendChild(doc.body());
    rootEl.appendChild(doc.head());
    
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    NodeTraversor.traverse(new W3CBuilder(wDoc), rootEl);
    String out = w3c.asString(wDoc);
    
    assertTrue(out.contains("ipod"));
}
    @Test
    public void test10() throws IOException {
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
    public void test11() throws IOException {
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
    public void test12() {
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
    @Test public void test13() {
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
    public void test14() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test15() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
@Test
public void test16() {
    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    Text textNode = doc.createTextNode("Hello World");
    Element element = doc.createElement("div");
    element.appendChild(textNode);
    doc.appendChild(element);

    W3CDom w3c = new W3CDom();
    org.jsoup.nodes.Document jsoupDoc = w3c.toJsoup(doc);

    org.jsoup.nodes.Node source = jsoupDoc.childNode(0);
    int depth = 1;
    w3c.tail(source, depth);

    assertNull(jsoupDoc.body().text());
}
@Test
public void test17() {
    Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    Element element = doc.createElement("div");
    doc.appendChild(element);

    W3CDom w3c = new W3CDom();
    org.jsoup.nodes.Document jsoupDoc = w3c.toJsoup(doc);

    org.jsoup.nodes.Node source = jsoupDoc.childNode(0);
    int depth = 1;
    w3c.tail(source, depth);

    assertNull(jsoupDoc.body().text());
}
    @Test
    public void test18() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );

        // Test with source having no attributes
        org.jsoup.nodes.Document emptySourceDoc = Jsoup.parse("");
        Document emptySourceWDoc = w3c.fromJsoup(emptySourceDoc);

        String emptySourceOut = w3c.asString(emptySourceWDoc);
        assertEquals("", emptySourceOut);
    }
    @Test public void test19() {
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

        // Test with different HTML structure
        String html2 = "<p>Text</p>";
        org.jsoup.nodes.Document doc2 = Jsoup.parse(html2);

        Document w3Doc2 = new W3CDom().fromJsoup(doc2);
        Node htmlEl2 = w3Doc2.getFirstChild();

        assertNull(htmlEl2.getNamespaceURI());
        assertEquals("html", htmlEl2.getLocalName());
        assertEquals("html", htmlEl2.getNodeName());

        Node p = htmlEl2.getFirstChild();
        assertNull(p.getNamespaceURI());
        assertEquals("p", p.getLocalName());
        assertEquals("p", p.getNodeName());
    }
    @Test
    public void test20() throws IOException {
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

        // Test with different source document
        String html2 = "<html><head><title>Testing</title></head><body><p>Text</p></body></html>";
        org.jsoup.nodes.Document doc2 = Jsoup.parse(html2);

        Document wDoc2 = w3c.fromJsoup(doc2);
        Node htmlEl2 = wDoc2.getChildNodes().item(0);
        assertEquals(null, htmlEl2.getNamespaceURI());
        assertEquals("html", htmlEl2.getLocalName());
        assertEquals("html", htmlEl2.getNodeName());

        Node p = htmlEl2.getFirstChild().getFirstChild();
        assertEquals(null, p.getNamespaceURI());
        assertEquals("p", p.getLocalName());
        assertEquals("p", p.getNodeName());
    }
    @Test
    public void test21() {
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

        // Test with empty HTML document
        String html2 = "<html><head><title>Empty</title></head><body></body></html>";
        org.jsoup.nodes.Document doc2 = Jsoup.parse(html2);

        Document wDoc2 = w3c.fromJsoup(doc2);
        String out2 = TextUtil.stripNewlines(w3c.asString(wDoc2));
        String expected2 = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Empty</title>" +
                "</head><body></body></html>"
        );
        assertEquals(expected2, out2);
    }
    @Test
    public void test22() throws IOException {
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

        // Test with different XHTML document
        File in2 = ParseTest.getFile("/htmltests/namespaces2.xhtml");
        org.jsoup.nodes.Document jsoupDoc2;
        jsoupDoc2 = Jsoup.parse(in2, "UTF-8");

        Document doc2;
        org.jsoup.helper.W3CDom jDom2 = new org.jsoup.helper.W3CDom();
        doc2 = jDom2.fromJsoup(jsoupDoc2);

        Node htmlEl2 = doc2.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl2.getNamespaceURI());
        assertEquals("html", htmlEl2.getLocalName());
        assertEquals("html", htmlEl2.getNodeName());

        Node p = htmlEl2.getFirstChild().getNextSibling().getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", p.getNamespaceURI());
        assertEquals("p", p.getLocalName());
        assertEquals("p", p.getNodeName());
    }
    @Test
    public void test23() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);

        // Test with empty HTML document and different invalid attribute names
        String html2 = "<html><head></head><body style=\"color: red\" name=5%></body></html>";
        org.jsoup.nodes.Document jsoupDoc2;
        jsoupDoc2 = Jsoup.parse(html2);
        Element body2 = jsoupDoc2.select("body").first();
        assertTrue(body2.hasAttr("name"));

        Document w3Doc2 = new W3CDom().fromJsoup(jsoupDoc2);
    }
    @Test
    public void test24() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        // Update the element to have a namespace declaration
        doc.getElementsByTag("html").attr("xmlns", "http://www.w3.org/1999/xhtml");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI());
    }
    @Test
    public void test25() {
        String html = "<fb:like xmlns:fb=\"http://www.facebook.com\">One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        // Update the element to have a namespace declaration
        doc.getElementsByTag("fb:like").attr("xmlns:fb", "http://www.facebook.com");

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertEquals("http://www.facebook.com", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertEquals("http://www.facebook.com", fb.getNamespaceURI());
        assertEquals("like", fb.getLocalName());
        assertEquals("fb:like", fb.getNodeName());
    }
    @Test
    public void test26() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        // Update the element to have a namespace declaration
        doc.getElementsByTag("html").attr("xmlns", "http://www.w3.org/1999/xhtml");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test27() {
        String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        // Update the element to have a namespace declaration
        doc.getElementsByTag("html").attr("xmlns", "http://www.w3.org/1999/xhtml");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                        "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                        "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test28() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        // Update the element to have a namespace declaration
        jsoupDoc.select("html").attr("xmlns", "http://www.w3.org/1999/xhtml");
        jsoupDoc.select("head").attr("xmlns", "http://www.w3.org/1999/xhtml");
        jsoupDoc.select("epub\\:title").attr("xmlns:epub", "http://www.idpf.org/2007/ops");
        jsoupDoc.select("x\\:section").attr("xmlns:x", "urn:test");
        jsoupDoc.select("svg").attr("xmlns", "http://www.w3.org/2000/svg");
        jsoupDoc.select("path").attr("xmlns", "http://www.w3.org/2000/svg");
        jsoupDoc.select("clip").attr("xmlns", "http://example.com/clip");
        jsoupDoc.select("picture").attr("xmlns", "http://www.w3.org/1999/xhtml");
        jsoupDoc.select("img").attr("xmlns", "http://www.w3.org/1999/xhtml");

        Document doc = new W3CDom().fromJsoup(jsoupDoc);

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
    public void test29() {
        String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(html);

        // Update the element to have a namespace declaration
        jsoupDoc.select("html").attr("xmlns", "http://www.w3.org/1999/xhtml");

        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\""));
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test30() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-no-ipod.html");
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
    public void test31() throws IOException {
        String html = "<html><head><title>Google</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Google</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test32() throws IOException {
        String html = "<html><head><title>W3c</title></head><body></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body></body></html>"
        );
        assertEquals(expected, out);
    }
}