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
        String html = "";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertEquals(null, wDoc.getFirstChild());
    }
    @Test
    public void test1() {
        String html = "<html></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertEquals("html", wDoc.getFirstChild().getLocalName());
        assertEquals(null, wDoc.getFirstChild().getFirstChild());
    }
    @Test
    public void test2() {
        String html = "<html><head></head><body></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertEquals(null, wDoc.getFirstChild().getFirstChild());
    }
    @Test
    public void test3() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id='12'>Text</p><div><span>Span Text</span></div></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p><div><span>Span Text</span></div></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test4() {
        String html = "<html><!-- Comment --><head><title>W3c</title></head><body><p class='one' id='12'>Text</p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><!-- Comment --><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test5() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id='12'>Text<script>alert('!');<div></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text<script>alert('!');<div></div></script></p></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test6() {
        String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>W3C XHTML Documentation</title></head><body><p>Check some info.</p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        Node head = htmlEl.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", head.getNamespaceURI());
        assertEquals("head", head.getLocalName());
        assertEquals("head", head.getNodeName());
        
        Node title = head.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", title.getNamespaceURI());
        assertEquals("title", title.getLocalName());
        assertEquals("title", title.getNodeName());
        assertEquals("W3C XHTML Documentation", title.getTextContent());
        
        Node body = htmlEl.getLastChild();
        assertEquals("http://www.w3.org/1999/xhtml", body.getNamespaceURI());
        assertEquals("body", body.getLocalName());
        assertEquals("body", body.getNodeName());
        
        Node p = body.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", p.getNamespaceURI());
        assertEquals("p", p.getLocalName());
        assertEquals("p", p.getNodeName());
        assertEquals("Check some info.", p.getTextContent());
    }
    @Test
    public void test7() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        Node body = htmlEl.getLastChild();
        assertEquals("http://www.w3.org/1999/xhtml", body.getNamespaceURI());
        assertEquals("body", body.getLocalName());
        assertEquals("body", body.getNodeName());
        assertTrue(body.hasAttributes());
    }
    @Test
    public void test8() {
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(null);
        assertNull(wDoc);
    }
    @Test
    public void test9() {
        org.jsoup.nodes.Document doc = Jsoup.parse("");
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        assertNull(wDoc);
    }
    @Test
    public void test10() {
        org.jsoup.nodes.Document doc = Jsoup.parse("<!DOCTYPE html>");
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        assertNull(wDoc);
    }
    @Test
    public void test11() {
        String html = "<html></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        assertNull(wDoc.getDocumentURI());
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
    public void test18() throws IOException {
        File in = ParseTest.getFile("/htmltests/invalid.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals(null, htmlEl.getLocalName());
        assertEquals(null, htmlEl.getNodeName());

        // inherits default namespace
        Node head = htmlEl.getFirstChild();
        assertEquals(null, head.getNamespaceURI());
        assertEquals(null, head.getLocalName());
        assertEquals(null, head.getNodeName());

        Node epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        assertEquals(null, epubTitle.getNamespaceURI());
        assertEquals(null, epubTitle.getLocalName());
        assertEquals(null, epubTitle.getNodeName());

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals(null, xSection.getNamespaceURI());
        assertEquals(null, xSection.getLocalName());
        assertEquals(null, xSection.getNodeName());

        // https://github.com/jhy/jsoup/issues/977
        // does not keep last set namespace
        Node svg = xSection.getNextSibling().getNextSibling();
        assertEquals(null, svg.getNamespaceURI());
        assertEquals(null, svg.getLocalName());
        assertEquals(null, svg.getNodeName());

        Node path = svg.getChildNodes().item(1);
        assertEquals(null, path.getNamespaceURI());
        assertEquals(null, path.getLocalName());
        assertEquals(null, path.getNodeName());

        Node clip = path.getChildNodes().item(1);
        assertEquals(null, clip.getNamespaceURI());
        assertEquals(null, clip.getLocalName());
        assertEquals(null, clip.getNodeName());
        assertEquals("456", clip.getTextContent());

        Node picture = svg.getNextSibling().getNextSibling();
        assertEquals(null, picture.getNamespaceURI());
        assertEquals(null, picture.getLocalName());
        assertEquals(null, picture.getNodeName());

        Node img = picture.getFirstChild();
        assertEquals(null, img.getNamespaceURI());
        assertEquals(null, img.getLocalName());
        assertEquals(null, img.getNodeName());

    }
    @Test
    public void test19() throws IOException {
        File in = ParseTest.getFile("/htmltests/empty.html");
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
    public void test20() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12></<body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\"></p></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test21() {
        String html = "<html><head></head><body style=\"color: 'red\" ' name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test22() throws IOException {
        File in = ParseTest.getFile("/htmltests/mockup.xhthml");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(null, wDoc.getDocumentURI());
    }
    @Test
    public void test23() throws IOException {
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
    public void test24() throws IOException {
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
    }
    @Test public void test26() {
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
    public void test28() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test29() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("test");
        Element dest = new Element("test");
        dest.appendChild(source);

        Document document = new Document();
        document.appendChild(dest);

        // Call the method under test
        tail(source, 0);

        // Test that the dest parent is an Element after the method call
        assertEquals(Element.class, dest.getParentNode().getClass());
    }
    @Test
    public void test30() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("test");
        Element dest = new Element("test");
        TextNode parent = new TextNode("test");
        dest.appendChild(source);
        dest.appendChild(parent);

        Document document = new Document();
        document.appendChild(dest);

        // Call the method under test
        tail(source, 0);

        // Test that the dest parent is the non-Element parent after the method call
        assertEquals(TextNode.class, dest.getParentNode().getClass());
    }
    @Test
    public void test31() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("test");
        Element dest = new Element("test");
        dest.appendChild(source);

        Document document = new Document();
        document.appendChild(dest);

        // Set up an empty namespace stack
        namespacesStack = new Stack<>();

        // Call the method under test
        tail(source, 0);

        // Test that the namespaces stack is still empty after the method call
        assertTrue(namespacesStack.isEmpty());
    }
    @Test
    public void test32() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("test");
        Element dest = new Element("test");
        dest.appendChild(source);

        Document document = new Document();
        document.appendChild(dest);

        // Add a namespace to the stack
        namespacesStack.push("http://www.example.com");

        // Call the method under test
        tail(source, 0);

        // Test that the namespaces stack is empty after the method call
        assertTrue(namespacesStack.isEmpty());
    }
    @Test
    public void test33() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("test");
        Element dest = new Element("test");
        dest.appendChild(source);

        Document document = new Document();
        document.appendChild(dest);

        // Call the method under test
        tail(source, 0);

        // Test that the dest node has no namespace URI after the method call
        assertNull(dest.getNamespaceURI());
    }
    @Test
    public void test34() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("test");
        Element dest = new Element("test");
        dest.appendChild(source);

        Document document = new Document();
        document.appendChild(dest);

        // Add a namespace to the stack
        namespacesStack.push("http://www.example.com");

        // Call the method under test
        tail(source, 0);

        // Test that the dest node does not have a namespace URI after the method call
        assertNull(dest.getNamespaceURI());
    }
    @Test
    public void test35() throws IOException {
        // Change the location of the file being parsed
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test public void test36() {
        // Change the html tag with a different namespace
        String html = "<custom:like>One</custom:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
        assertEquals("like", fb.getLocalName());
        assertEquals("custom:like", fb.getNodeName());

    }
    @Test
    public void test37() throws IOException {
        // Change the location of the file being parsed
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
    public void test38() {
        // Change the html tag with attributes
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=13>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"13\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test39() throws IOException {
        // Change the location of the file being parsed
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

        // Change the namespace of the head tag
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
    public void test40() {
        // Change the html attribute values
        String html = "<html><head></head><body style=\"color: blue\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test41() {
        String html = "<empty />";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<empty />", out);
    }
    @Test
    public void test42() {
        String html = "<root xmlns:ns=\"http://namespace\" />";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertTrue(out.contains("xmlns:ns=\"http://namespace\""));
    }
    @Test
    public void test43() {
        String html = "<prefix:tag />";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertTrue(out.contains("prefix:tag"));
    }
    @Test
    public void test44() {
        String html = "<prefix:tag>Text</prefix:tag>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertTrue(out.contains("<prefix:tag>Text</prefix:tag>"));
    }
@Test
public void test45() {
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("");
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    assertEquals("", out);
}
@Test
public void test46() {
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("   ");
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    assertEquals("", out);
}
@Test
public void test47() {
    String html = "<html><head><title>$#@&amp;\"'</title></head><body><p>@#$%^&amp;*()</p><a>!@#$%^&amp;*()'</a></body></html>";
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    assertEquals(html, out);
}
@Test
public void test48() {
    String html = "<html><head><title>W3c</title></head><body><![CDATA[<p>Hello World!</p>]]></body></html>";
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    assertEquals(html, out);
}
@Test
public void test49() {
    String html = "<html><head><title>W3c</title></head><body><p class='one' id=12></p></body></html>";
    org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse(html);
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    assertEquals(html, out);
}
}