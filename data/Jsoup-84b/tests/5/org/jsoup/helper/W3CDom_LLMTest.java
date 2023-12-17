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
    try {
        Document wDoc = w3c.fromJsoup(doc);
    } catch (NullPointerException e) {
        assertTrue(true);
    }
}
@Test
public void test1() {
    org.jsoup.nodes.Document doc = null;

    W3CDom w3c = new W3CDom();
    try {
        Document wDoc = w3c.fromJsoup(doc);
    } catch (NullPointerException e) {
        assertTrue(true);
    }
}
@Test
public void test2() {
    String html = null;
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    body.attr("\"", "");
    body.attr("name\"", "");

    W3CDom w3c = new W3CDom();
    try {
        Document w3Doc = w3c.fromJsoup(jsoupDoc);
    } catch (NullPointerException e) {
        assertTrue(true);
    }
}
@Test
public void test3() {
    String html = null;
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    try {
        Document wDoc = w3c.fromJsoup(doc);
    } catch (NullPointerException e) {
        assertTrue(true);
    }
}
@Test
public void test4() {
    String html = null;
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    try {
        Document w3Doc = w3c.fromJsoup(doc);
    } catch (NullPointerException e) {
        assertTrue(true);
    }
}
@Test
public void test5() throws IOException {
    org.jsoup.nodes.Document jsoupDoc = null;

    W3CDom w3c = new W3CDom();
    try {
        Document doc = w3c.fromJsoup(jsoupDoc);
    } catch (NullPointerException e) {
        assertTrue(true);
    }
}
    @Test
    public void test6() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test7() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("http://example.com");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test public void test8() {
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
    @Test public void test9() {
        String html = "<fb:like>One <span>Two</span> Three</fb:like>";
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
    public void test10() throws IOException {
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
    public void test11() throws IOException {
      File in = ParseTest.getFile("/htmltests/google-ipod.html");
      org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
      doc.select("title").text("New Title");

      W3CDom w3c = new W3CDom();
      Document wDoc = w3c.fromJsoup(doc);
      Node htmlEl = wDoc.getChildNodes().item(0);
      assertEquals(null, htmlEl.getNamespaceURI());
      assertEquals("html", htmlEl.getLocalName());
      assertEquals("html", htmlEl.getNodeName());

      String out = w3c.asString(wDoc);
      assertTrue(out.contains("New Title"));
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
    @Test
    public void test13() throws IOException {
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
        //Updated input HTML file with different namespace declaration
        File in = ParseTest.getFile("/htmltests/namespaces_new.xhtml");
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
    public void test16() throws IOException {
        //Updated input HTML file with different content
        File in = ParseTest.getFile("/htmltests/google-ipod_new.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("mobile phone"));
    }
    @Test
    public void test17() {
        //Updated input HTML file with different content
        String html = "<html><head><title>Test</title></head><body><p class='two' id=14>Text</p><!-- comment --><invalid><em>What</em><script>alert('!')</script></invalid></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Test</title>" +
                "</head><body><p class=\"two\" id=\"14\">Text</p><!-- comment --><invalid><em>What</em><script>alert('!')</script></invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test public void test18() {
        //Updated input HTML with different undeclared namespace
        String html = "<fb:share>This</fb:share>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
        assertEquals("share", fb.getLocalName());
        assertEquals("fb:share", fb.getNodeName());

    }
    @Test
    public void test19() {
        //Updated input HTML with different invalid attribute names
        String html = "<html><head></head><body style=\"font-size: 12px\" _unknown></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("_unknown"));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test20() throws IOException {
        //Updated input HTML file with different content
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test21() throws IOException {
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
        assertFalse(doc.getNamespacesStack().isEmpty());

        // Normal execution
        tail(htmlEl, 3);

        assertEquals("http://www.idpf.org/2001/ops", doc.getNamespacesStack().peek());
    }
    @Test
    public void test22() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node source = doc.getFirstChild();
        int depth = 0;
        
        tail(source, depth);

        assertNull(doc.getNamespacesStack().peek());
    }
    @Test
    public void test23() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        assertFalse(wDoc.getNamespacesStack().isEmpty());

        // Normal execution
        tail(htmlEl, 2);

        assertEquals("http://www.w3.org/1999/xhtml", wDoc.getNamespacesStack().peek());
    }
    @Test
    public void test24() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        Node source = wDoc.getFirstChild();
        int depth = 1;

        tail(source, depth);

        assertEquals("http://www.w3.org/1999/xhtml", wDoc.getNamespacesStack().peek());
    }
    @Test
    public void test25() {
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
        assertFalse(wDoc.getNamespacesStack().isEmpty());

        // Normal execution
        tail(wDoc, 3);

        assertEquals("http://www.w3.org/1999/xhtml", wDoc.getNamespacesStack().peek());
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
        assertEquals(expected, out);
        assertFalse(wDoc.getNamespacesStack().isEmpty());

        // edge case
        tail(wDoc, -1);

        assertEquals("http://www.w3.org/1999/xhtml", wDoc.getNamespacesStack().peek());
    }
    @Test public void test27() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);

        Node htmlEl = w3Doc.getFirstChild();
        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        assertFalse(w3Doc.getNamespacesStack().isEmpty());

        // Normal execution
        tail(htmlEl, 2);

        assertEquals("http://www.w3.org/1999/xhtml", w3Doc.getNamespacesStack().peek());
    }
    @Test public void test28() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);

        Node source = w3Doc.getFirstChild();
        int depth = 1;

        tail(source, depth);

        assertEquals("http://www.w3.org/1999/xhtml", w3Doc.getNamespacesStack().peek());
    }
    @Test
    public void test29() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
        assertFalse(w3Doc.getNamespacesStack().isEmpty());

        // Normal execution
        tail(w3Doc, 1);

        assertNull(w3Doc.getNamespacesStack().peek());
    }
    @Test
    public void test30() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
        assertFalse(w3Doc.getNamespacesStack().isEmpty());

        // edge case
        tail(w3Doc, -1);

        assertNull(w3Doc.getNamespacesStack().peek());
    }
    @Test
    public void test31() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
        assertFalse(wDoc.getNamespacesStack().isEmpty());

        // Normal execution
        tail(wDoc, 2);

        assertNull(wDoc.getNamespacesStack().peek());
    }
    @Test
    public void test32() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        Node source = wDoc.getFirstChild();
        int depth = 1;

        tail(source, depth);

        assertNull(wDoc.getNamespacesStack().peek());
    }
    @Test
    public void test33() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        source.attr("class", "container");
        source.attr("id", "123");
        source.attr(":style", "color: blue");
        source.attr("data-info", "12345");

        org.w3c.dom.Element el = new org.apache.xerces.dom.DocumentImpl().createElement("div");
        copyAttributes(source, el);

        assertEquals("container", el.getAttribute("class"));
        assertEquals("123", el.getAttribute("id"));
        assertEquals("color: blue", el.getAttribute(":style"));
        assertEquals("12345", el.getAttribute("data-info"));
    }
    @Test
    public void test34() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        source.attr("class", "container");
        source.attr("id", "123");
        source.attr("   ", "invalid");
        source.attr("!@#$%", "special");

        org.w3c.dom.Element el = new org.apache.xerces.dom.DocumentImpl().createElement("div");
        copyAttributes(source, el);

        assertEquals("container", el.getAttribute("class"));
        assertEquals("123", el.getAttribute("id"));
        assertEquals("special", el.getAttribute("!@#$%"));
        assertFalse(el.hasAttribute("   "));
    }
    @Test
    public void test35() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        source.attr("class", "container");
        source.attr("id", "123");
        source.attr("\n\n  ", "test");
        source.attr("   invalid   ", "12345");

        org.w3c.dom.Element el = new org.apache.xerces.dom.DocumentImpl().createElement("div");
        copyAttributes(source, el);

        assertEquals("container", el.getAttribute("class"));
        assertEquals("123", el.getAttribute("id"));
        assertFalse(el.hasAttribute("\n\n  "));
        assertFalse(el.hasAttribute("   invalid   "));
    }
    @Test
    public void test36() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        source.attr("class", "container");
        source.attr("id", "123");
        source.attr("data-info", "a\"b\"c");
        source.attr("data-invalid", "\"12345\"");

        org.w3c.dom.Element el = new org.apache.xerces.dom.DocumentImpl().createElement("div");
        copyAttributes(source, el);

        assertEquals("container", el.getAttribute("class"));
        assertEquals("123", el.getAttribute("id"));
        assertTrue(el.hasAttribute("data-info"));
        assertEquals("a\"b\"c", el.getAttribute("data-info"));
        assertFalse(el.hasAttribute("data-invalid"));
    }
    @Test
    public void test37() throws IOException {
        org.jsoup.nodes.Element el = null;
        String result = updateNamespaces(el);
        assertEquals("", result);
    }
    @Test
    public void test38() throws IOException {
        org.jsoup.nodes.Element el = new org.jsoup.nodes.Element("");
        String result = updateNamespaces(el);
        assertEquals("", result);
    }
    @Test
    public void test39() throws IOException {
        org.jsoup.nodes.Element el = new org.jsoup.nodes.Element("html");
        String result = updateNamespaces(el);
        assertEquals("", result);
    }
    @Test
    public void test40() throws IOException {
        org.jsoup.nodes.Element el = new org.jsoup.nodes.Element("html");
        el.attributes().put("xmlns", "http://www.w3.org/1999/xhtml");
        String result = updateNamespaces(el);
        assertEquals("", result);
    }
    @Test
    public void test41() throws IOException {
        org.jsoup.nodes.Element el = new org.jsoup.nodes.Element("html");
        el.attributes().put("xmlns:prefix", "http://www.example.com/");
        String result = updateNamespaces(el);
        assertEquals("prefix", result);
    }
    @Test
    public void test42() throws IOException {
        org.jsoup.nodes.Element el = new org.jsoup.nodes.Element("html");
        el.attributes().put("xmlns", "http://www.w3.org/1999/xhtml");
        el.attributes().put("xmlns:prefix1", "http://www.example.com/");
        el.attributes().put("xmlns:prefix2", "http://www.example.com/2");
        String result = updateNamespaces(el);
        assertEquals("prefix1", result);
    }
@Test
public void test43() {
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("");
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines("<html><head></head><body></body></html>");
    assertEquals(expected, out);
}
@Test
public void test44() {
    String html = "<html><head><title>Special Characters: &amp; &lt; &gt; &quot; &apos;</title></head><body></body></html>";
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Special Characters: &amp; &lt; &gt; &quot; &apos;</title></head><body></body></html>");
    assertEquals(expected, out);
}
@Test
public void test45() {
    String html = "<html><head></head><body><div class=\"class1\"></div><div class=\"class1 class2\"></div><div class=\"class3\"></div></body></html>";
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines("<html><head></head><body><div class=\"class1\"></div><div class=\"class1 class2\"></div><div class=\"class3\"></div></body></html>");
    assertEquals(expected, out);
}
}