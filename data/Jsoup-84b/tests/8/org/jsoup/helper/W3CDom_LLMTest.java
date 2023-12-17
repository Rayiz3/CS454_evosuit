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
        W3CDom w3c = new W3CDom();
        try {
            w3c.fromJsoup(null);
            fail("NullPointerException should be thrown");
        } catch (NullPointerException e) {
            //pass
        }
    }
    @Test
    public void test1() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertNull(wDoc.getDocumentElement());
    }
    @Test
    public void test2() {
        org.jsoup.nodes.Document doc = null;
        
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertNull(wDoc.getDocumentElement());
    }
    @Test
    public void test3() {
        W3CDom w3c = new W3CDom();
        try {
            w3c.fromJsoup(null);
            fail("NullPointerException should be thrown");
        } catch (NullPointerException e) {
            //pass
        }
    }
    @Test
    public void test4() {
        String html = "";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertNull(wDoc.getDocumentElement());
    }
    @Test
    public void test5() {
        W3CDom w3c = new W3CDom();
        try {
            w3c.fromJsoup(null);
            fail("NullPointerException should be thrown");
        } catch (NullPointerException e) {
            //pass
        }
    }
    @Test
    public void test6() {
        String html = "";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertNull(wDoc.getDocumentElement());
    }
    @Test
    public void test7() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("http://www.google.com");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("http://www.google.com", wDoc.getDocumentURI());
    }
    @Test
    public void test8() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", wDoc.getDocumentURI());
    }
    @Test
    public void test9() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri(null);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertNull(wDoc.getDocumentURI());
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

        // Additional test with invalid tagName
        Node invalid = img.getNextSibling();
        assertEquals(null, invalid.getNamespaceURI());
        assertEquals("invalid", invalid.getLocalName());
        assertEquals("invalid", invalid.getNodeName());
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

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
        assertEquals("like", fb.getLocalName());
        assertEquals("fb:like", fb.getNodeName());
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

        // Additional test with invalid attribute name
        Element body = doc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = w3c.fromJsoup(doc);
    }
    @Test
    public void test13() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        // Change the location
        wDoc.setDocumentURI("www.google.com");
        String out = w3c.asString(wDoc);
        assertEquals("www.google.com", wDoc.getDocumentURI());
    }
    @Test
    public void test14() throws IOException {
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
        namespacesStack.push(xSection.getNamespaceURI());
        tail(epubTitle.getNextSibling().getNextSibling(), 1);
        assertTrue(namespacesStack.empty());

    }
    @Test
    public void test15() throws IOException {
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

        // Node is not an instance of org.jsoup.nodes.Element
        tail(htmlEl.getNextSibling(), 1);
        assertEquals(head.getNamespaceURI(), namespacesStack.peek());

    }
    @Test
    public void test16() throws IOException {
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

        // Destination is not an instance of org.w3c.dom.Element
        tail(epubTitle, 1);
        assertEquals(head, dest);
    }
    @Test
    public void test17() throws IOException {
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

        // Node is not an instance of org.jsoup.nodes.Element and destination is not an instance of org.w3c.dom.Element
        tail(epubTitle.getNextSibling(), 1);
        assertEquals(head, dest);
        assertEquals(head.getNamespaceURI(), namespacesStack.peek());
    }
    @Test
    public void test18() {
        // Create a source node with attributes
        org.jsoup.nodes.Element sourceElement = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        sourceElement.attr("attr1", "value1");
        sourceElement.attr("attr2", "value2");
        org.jsoup.nodes.Attributes sourceAttributes = sourceElement.attributes();

        // Create a target element
        Element targetElement = new Element();

        // Call the copyAttributes method with the source node and target element
        copyAttributes(sourceAttributes, targetElement);

        // Assert that all attributes are copied
        assertEquals("value1", targetElement.getAttribute("attr1"));
        assertEquals("value2", targetElement.getAttribute("attr2"));
    }
    @Test
    public void test19() {
        // Create a source node with attributes
        org.jsoup.nodes.Element sourceElement = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        sourceElement.attr("attr1", "value1");
        sourceElement.attr("attr2", "value2");
        org.jsoup.nodes.Attributes sourceAttributes = sourceElement.attributes();

        // Create a target element with a matching namespace
        Element targetElement = new Element();
        targetElement.setNamespaceURI("");

        // Call the copyAttributes method with the source node and target element
        copyAttributes(sourceAttributes, targetElement);

        // Assert that all attributes are copied
        assertEquals("value1", targetElement.getAttribute("attr1"));
        assertEquals("value2", targetElement.getAttribute("attr2"));
    }
    @Test
    public void test20() {
        // Create a source node with attributes
        org.jsoup.nodes.Element sourceElement = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        sourceElement.attr("attr1", "value1");
        sourceElement.attr("attr2", "value2");
        org.jsoup.nodes.Attributes sourceAttributes = sourceElement.attributes();

        // Create a target element with a non-matching namespace
        Element targetElement = new Element();
        targetElement.setNamespaceURI("http://example.com");

        // Call the copyAttributes method with the source node and target element
        copyAttributes(sourceAttributes, targetElement);

        // Assert that no attributes are copied
        assertNull(targetElement.getAttribute("attr1"));
        assertNull(targetElement.getAttribute("attr2"));
    }
    @Test
    public void test21() {
        // Create a source node with attributes
        org.jsoup.nodes.Element sourceElement = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        sourceElement.attr("attr1", "value1");
        sourceElement.attr("attr-2", "value2");
        org.jsoup.nodes.Attributes sourceAttributes = sourceElement.attributes();

        // Create a target element
        Element targetElement = new Element();

        // Call the copyAttributes method with the source node and target element
        copyAttributes(sourceAttributes, targetElement);

        // Assert that all attributes with valid names are copied
        assertEquals("value1", targetElement.getAttribute("attr1"));
        assertEquals("value2", targetElement.getAttribute("attr-2"));
    }
    @Test
    public void test22() {
        // Create a source node with attributes
        org.jsoup.nodes.Element sourceElement = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("div"), "");
        sourceElement.attr("@tt$r1", "value1");
        sourceElement.attr("attr:2", "value2");
        org.jsoup.nodes.Attributes sourceAttributes = sourceElement.attributes();

        // Create a target element
        Element targetElement = new Element();

        // Call the copyAttributes method with the source node and target element
        copyAttributes(sourceAttributes, targetElement);

        // Assert that no attributes with invalid names are copied
        assertNull(targetElement.getAttribute("@tt$r1"));
        assertNull(targetElement.getAttribute("attr:2"));
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

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test24() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
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
    @Test
    public void test26() {
        String html = "<html><head></head><body></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test27() {
        String html = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

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
    public void test28() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>&lt;What's going on&gt;</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">&lt;What's going on&gt;</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test29() {
        String html = "<html><head><title>W3c</title></head><body><p class='one'>Text 1</p><p class='two'>Text 2</p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\">Text 1</p><p class=\"two\">Text 2</p></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test30() {
        String html = "<html><head><title>W3c</title></head><body><p class='one'>Text</p><p class='two'><![CDATA[<p>This is a paragraph</p>]]></p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\">Text</p><p class=\"two\"><![CDATA[<p>This is a paragraph</p>]]></p></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test31() {
        String html = "<html><head><title>W3c</title><meta charset=\"UTF-8\"></meta></head><body><p class='one'>Text</p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title><meta charset=\"UTF-8\"></meta>" +
                "</head><body><p class=\"one\">Text</p></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test32() {
        String html = "<!DOCTYPE html><html><head><title>W3c</title></head><body><p class='one'>Text</p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<!DOCTYPE html><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\">Text</p></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test33() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12><div>Text</p><!-- comment --><invalid>What<script>alert('!')</script>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\"><div>Text</p><!-- comment --><invalid>What<script>alert('!')</script></invalid></div>" +
                "</body></html>"
        );
        assertEquals(expected, out);
    }
}