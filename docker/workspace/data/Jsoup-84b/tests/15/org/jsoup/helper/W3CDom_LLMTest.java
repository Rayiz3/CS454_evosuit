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
        assertNotNull(wDoc);
        assertEquals("html", wDoc.getDocumentElement().getNodeName());
    }
    @Test
    public void test2() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom();
        w3c.factory = null;
        assertThrows(IllegalStateException.class, () -> w3c.fromJsoup(doc));
    }
    @Test
    public void test3() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom();
        w3c.fromJsoup(doc);
        assertNull(w3c.builder);
    }
    @Test
    public void test4() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom();
        w3c.builder = null;
        assertThrows(IllegalStateException.class, () -> w3c.fromJsoup(doc));
    }
    @Test
    public void test5() throws ParserConfigurationException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom();
        w3c.factory.newDocumentBuilder();
        Document wDoc = w3c.fromJsoup(doc);
        assertNull(wDoc);
    }
    @Test
    public void test6() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom() {
    	    @Override
    	    protected void convert(org.jsoup.nodes.Document in, Document out) {
    	        in = null;
    	    }
    	};
        Document wDoc = w3c.fromJsoup(doc);
        assertNotNull(wDoc);
        assertEquals("html", wDoc.getDocumentElement().getNodeName());
    }
    @Test
    public void test7() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom() {
    	    @Override
    	    protected void convert(org.jsoup.nodes.Document in, Document out) {
    	        out = null;
    	    }
    	};
        Document wDoc = w3c.fromJsoup(doc);
        assertNotNull(wDoc);
        assertEquals("html", wDoc.getDocumentElement().getNodeName());
    }
    @Test
    public void test8() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom() {
    	    @Override
    	    protected void convert(org.jsoup.nodes.Document in, Document out) {
    	        throw new RuntimeException();
    	    }
    	};
        assertThrows(RuntimeException.class, () -> w3c.fromJsoup(doc));
    }
    @Test
    public void test9() throws ParserConfigurationException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        W3CDom w3c = new W3CDom() {
    	    @Override
    	    protected void convert(org.jsoup.nodes.Document in, Document out) throws ParserConfigurationException {
    	        throw new ParserConfigurationException();
    	    }
    	};
        assertThrows(IllegalStateException.class, () -> w3c.fromJsoup(doc));
    }
    @Test
    public void test10() {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom() {
            @Override
            protected DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
                throw new ParserConfigurationException();
            }
        };
        Document wDoc = w3c.fromJsoup(doc);
        assertNull(wDoc);
    }
    @Test
    public void test11() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        
        W3CDom w3c = new W3CDom() {
            @Override
            protected DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
                throw new ParserConfigurationException();
            }
        };
        Document wDoc = w3c.fromJsoup(doc);
        assertNull(wDoc);
    }
    @Test
    public void test12() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom() {
            @Override
            protected DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
                throw new ParserConfigurationException();
            }
        };
        Document doc = jDom.fromJsoup(jsoupDoc);
        assertNull(doc);
    }
    @Test
    public void test13() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        W3CDom w3c = new W3CDom() {
            @Override
            protected DocumentBuilder createDocumentBuilder() throws ParserConfigurationException {
                throw new ParserConfigurationException();
            }
        };
        assertThrows(IllegalStateException.class, () -> w3c.fromJsoup(jsoupDoc));
    }
@Test
public void test14() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    doc.setBaseUri("https://www.google.com");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals("https://www.google.com", wDoc.getDocumentURI());
}
@Test
public void test15() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    doc.setBaseUri("");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals("", wDoc.getDocumentURI());
}
@Test
public void test16() {
    String html = "";
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
public void test17() {
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
public void test18() {
    String html = "<fb:like xmlns:fb=\"http://www.facebook.com/\">One</fb:like>";
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
    public void test19() throws IOException {
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
        assertEquals("", epubTitle.getTextContent());
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
        assertEquals("", clip.getTextContent());

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
    public void test20() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals("http://randomnamespace", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertFalse(out.contains("ipod"));
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
        assertNotEquals(expected, out);
    }
    @Test public void test22() {
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
    public void test23() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertFalse(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertFalse(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test24() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertNotEquals(doc.location(), wDoc.getDocumentURI());
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
    public void test27() {
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
    @Test public void test28() {
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
    public void test29() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test30() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test31() {
        Node source = new org.jsoup.nodes.Document("");
        int depth = 0;
        
        // Create an instance of the class that contains the method
        // Call the method using the given test case
        // Assert the expected output
        
    }
    @Test
    public void test32() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("", "");
        int depth = 1;
        
        // Create an instance of the class that contains the method
        // Call the method using the given test case
        // Assert the expected output
        
    }
    @Test
    public void test33() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("", "");
        int depth = 1;
        
        // Create an instance of the class that contains the method
        // Call the method using the given test case
        // Assert the expected output
        
    }
    @Test
    public void test34() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("", "");
        int depth = 1;
        
        // Create an instance of the class that contains the method
        // Call the method using the given test case
        // Assert the expected output
        
    }
    @Test
    public void test35() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        
        // Create a copy of the document with attribute order swapped
        org.jsoup.nodes.Document swappedAttributesDoc = swapAttributesOrder(doc);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(swappedAttributesDoc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test36() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        
        // Create a copy of the document with attribute order swapped
        org.jsoup.nodes.Document swappedAttributesDoc = swapAttributesOrder(doc);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(swappedAttributesDoc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test37() {
        String html = "<html><head><title>W3c</title></head><body><p id=12 class='one'>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        // Create a copy of the document with attribute order swapped
        org.jsoup.nodes.Document swappedAttributesDoc = swapAttributesOrder(doc);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(swappedAttributesDoc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p id=\"12\" class=\"one\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test38() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");
        
        // Create a copy of the document with attribute order swapped
        org.jsoup.nodes.Document swappedAttributesDoc = swapAttributesOrder(jsoupDoc);

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(swappedAttributesDoc);

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
    public void test39() {
        String html = "<html><head></head><body \" style=\"color: red\" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test40() {
        Element el = null;

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertTrue(namespacesStack.isEmpty());
    }
    @Test
    public void test41() {
        Element el = new Element(Tag.valueOf("tag"), "");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertTrue(namespacesStack.isEmpty());
    }
    @Test
    public void test42() {
        Element el = new Element(Tag.valueOf("tag"), "");
        el.attr("attribute1", "value1");
        el.attr("attribute2", "value2");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertTrue(namespacesStack.isEmpty());
    }
    @Test
    public void test43() {
        Element el = new Element(Tag.valueOf("tag"), "");
        el.attr("xmlns:prefix1", "namespace1");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertFalse(namespacesStack.isEmpty());
        assertEquals("prefix1", namespacesStack.peek().keySet().iterator().next());
        assertEquals("namespace1", namespacesStack.peek().values().iterator().next());
    }
    @Test
    public void test44() {
        Element el = new Element(Tag.valueOf("tag"), "");
        el.attr("xmlns:prefix1", "namespace1");
        el.attr("xmlns:prefix2", "namespace2");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertFalse(namespacesStack.isEmpty());
        assertTrue(namespacesStack.peek().containsKey("prefix1"));
        assertTrue(namespacesStack.peek().containsKey("prefix2"));
        assertTrue(namespacesStack.peek().containsValue("namespace1"));
        assertTrue(namespacesStack.peek().containsValue("namespace2"));
    }
    @Test
    public void test45() {
        Element el = new Element(Tag.valueOf("tag"), "");
        el.attr("xmlns", "namespace");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertFalse(namespacesStack.isEmpty());
        assertEquals("", namespacesStack.peek().keySet().iterator().next());
        assertEquals("namespace", namespacesStack.peek().values().iterator().next());
    }
    @Test
    public void test46() {
        Element el = new Element(Tag.valueOf("tag"), "");
        el.attr("xmlns:prefix", "namespace");
        el.attr("xmlns", "defaultNamespace");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertFalse(namespacesStack.isEmpty());
        assertTrue(namespacesStack.peek().containsKey("prefix"));
        assertTrue(namespacesStack.peek().containsKey(""));
        assertTrue(namespacesStack.peek().containsValue("namespace"));
        assertTrue(namespacesStack.peek().containsValue("defaultNamespace"));
    }
    @Test
    public void test47() {
        Element el = new Element(Tag.valueOf("tag"), "");
        el.attr("xmlns:", "namespace");
        el.attr("xmlns", "defaultNamespace");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertFalse(namespacesStack.isEmpty());
        assertEquals("", namespacesStack.peek().keySet().iterator().next());
        assertEquals("defaultNamespace", namespacesStack.peek().values().iterator().next());
    }
    @Test
    public void test48() {
        Element el = new Element(Tag.valueOf("prefix:tag"), "");

        String result = updateNamespaces(el);

        assertEquals("prefix", result);
        assertTrue(namespacesStack.isEmpty());
    }
    @Test
    public void test49() {
        Element el = new Element(Tag.valueOf("tag"), "");

        String result = updateNamespaces(el);

        assertEquals("", result);
        assertTrue(namespacesStack.isEmpty());
    }
    @Test
    public void test50() throws IOException {
        // Change the input document to an empty document
        org.jsoup.nodes.Document doc = Jsoup.parse("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test51() throws IOException {
        // Change the input document to contain only one node
        String html = "<p>Text</p>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines("<p>Text</p>");
        assertEquals(expected, out);
    }
    @Test
    public void test52() throws IOException {
        // Change the input document to have a different encoding
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF16");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
    }
}