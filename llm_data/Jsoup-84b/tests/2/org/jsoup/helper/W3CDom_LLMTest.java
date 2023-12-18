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
    org.jsoup.nodes.Document doc = null;

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    assertNull(wDoc);
}
@Test
public void test1() {
    org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    assertEquals("", wDoc.getDocumentURI());
}
@Test
public void test2() {
    String html = "<invalid>One</invalid>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    Document w3Doc = new W3CDom().fromJsoup(doc);
    Node htmlEl = w3Doc.getFirstChild();

    assertEquals(null, htmlEl.getNamespaceURI());
    assertEquals("invalid", htmlEl.getLocalName());
    assertEquals("invalid", htmlEl.getNodeName());
}
@Test
public void test3() {
    String html = "<fb:like>One</fb:like>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    Node htmlEl = wDoc.getChildNodes().item(0);
    assertEquals(null, htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    String out = w3c.asString(wDoc);
    assertFalse(out.contains("One"));
}
@Test
public void test4() {
    String html = "";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));

    assertEquals("", out);
}
@Test
public void test5() {
    org.jsoup.nodes.Document jsoupDoc = null;

    W3CDom jDom = new W3CDom();
    Document doc = jDom.fromJsoup(jsoupDoc);

    assertNull(doc);
}
@Test
public void test6() {
    String html = "<html><head></head><body name\"></body></html>";
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    assertTrue(body.hasAttr("name\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it

    Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
}
    @Test
    public void test7() throws IOException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("", "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", wDoc.getDocumentURI() );
    }
    @Test
    public void test8() throws IOException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("", "UTF8");

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
    public void test9() {
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(null);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        assertEquals("", out);
    }
    @Test
    public void test10() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc = null;

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals(null, htmlEl.getLocalName());
        assertEquals(null, htmlEl.getNodeName());
    }
    @Test
    public void test11() {
        org.jsoup.nodes.Document jsoupDoc = null;
        Element body = null;

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test12() throws IOException {
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
    public void test13() throws IOException {
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
    public void test14() {
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
    @Test public void test15() {
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
    public void test16() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test17() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test18() {
        org.jsoup.nodes.Node source = new Element("source");
        int depth = 0;
        org.w3c.dom.Element dest = new org.w3c.dom.Element("dest");

        tail(source, depth, dest);

        // check that dest is not modified
        assertEquals("dest", dest.getNodeName());
    }
    @Test
    public void test19() {
        org.jsoup.nodes.Node source = new Element("source");
        int depth = 2;
        org.w3c.dom.Element dest = new org.w3c.dom.Element("dest");

        tail(source, depth, dest);

        // check that dest is not modified
        assertEquals("dest", dest.getNodeName());
    }
    @Test
    public void test20() {
        org.jsoup.nodes.Node source = new Element("source");
        int depth = 0;
        org.w3c.dom.Element dest = new org.w3c.dom.Element("dest");
        dest.setParentNode(new org.w3c.dom.Element("parent"));

        tail(source, depth, dest);

        // check that dest is modified to its parent
        assertEquals("parent", dest.getNodeName());
    }
    @Test
    public void test21() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.TextNode("source");
        int depth = 0;
        org.w3c.dom.Element dest = new org.w3c.dom.Element("dest");

        tail(source, depth, dest);

        // check that dest is not modified
        assertEquals("dest", dest.getNodeName());
    }
    @Test
    public void test22() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Document("");
        int depth = 0;
        org.w3c.dom.Element dest = new org.w3c.dom.Element("dest");

        tail(source, depth, dest);

        // check that dest is not modified
        assertEquals("dest", dest.getNodeName());
    }
    @Test
    public void test23() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertNotEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test24() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertNull(htmlEl.getNamespaceURI());
        assertNotEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
        assertNotEquals("like", fb.getLocalName());
        assertEquals("fb:like", fb.getNodeName());

    }
    @Test
    public void test25() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertNotEquals(null, htmlEl.getNamespaceURI());
        assertNotEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertFalse(out.contains("ipod"));
    }
    @Test
    public void test26() {
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
        assertNotEquals(expected, out);
    }
    @Test
    public void test27() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertNotEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertNotEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        // inherits default namespace
        Node head = htmlEl.getFirstChild();
        assertNotEquals("http://www.w3.org/1999/xhtml", head.getNamespaceURI());
        assertNotEquals("head", head.getLocalName());
        assertEquals("head", head.getNodeName());

        Node epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        assertNotEquals("Check", epubTitle.getTextContent());
        assertNotEquals("http://www.idpf.org/2007/ops", epubTitle.getNamespaceURI());
        assertNotEquals("title", epubTitle.getLocalName());
        assertEquals("epub:title", epubTitle.getNodeName());

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertNotEquals("urn:test", xSection.getNamespaceURI());
        assertNotEquals("section", xSection.getLocalName());
        assertEquals("x:section", xSection.getNodeName());

        // https://github.com/jhy/jsoup/issues/977
        // does not keep last set namespace
        Node svg = xSection.getNextSibling().getNextSibling();
        assertNotEquals("http://www.w3.org/2000/svg", svg.getNamespaceURI());
        assertNotEquals("svg", svg.getLocalName());
        assertEquals("svg", svg.getNodeName());

        Node path = svg.getChildNodes().item(1);
        assertNotEquals("http://www.w3.org/2000/svg", path.getNamespaceURI());
        assertNotEquals("path", path.getLocalName());
        assertEquals("path", path.getNodeName());

        Node clip = path.getChildNodes().item(1);
        assertNotEquals("http://example.com/clip", clip.getNamespaceURI());
        assertNotEquals("clip", clip.getLocalName());
        assertEquals("clip", clip.getNodeName());
        assertEquals("456", clip.getTextContent());

        Node picture = svg.getNextSibling().getNextSibling();
        assertNotEquals("http://www.w3.org/1999/xhtml", picture.getNamespaceURI());
        assertNotEquals("picture", picture.getLocalName());
        assertEquals("picture", picture.getNodeName());

        Node img = picture.getFirstChild();
        assertNotEquals("http://www.w3.org/1999/xhtml", img.getNamespaceURI());
        assertNotEquals("img", img.getLocalName());
        assertEquals("img", img.getNodeName());

    }
    @Test
    public void test28() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
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

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test30() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-iphone.html"); // changed file path
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test31() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF16"); // changed character encoding

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test32() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test33() {
        String html = "<html><head></head><body style=\"color: blue\" \" name\"></body></html>"; // changed attribute value
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test34() {
        String html = "";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
                "<body></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test35() {
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
                "<body></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test36() {
        String html = "<html><head><title>W3c</title></head><body><p class='' id=><Text></p><!-- comment --><invalid>What<script></script>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"\" id=\"\"><Text></p><!-- comment --><invalid>What<script></script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
}