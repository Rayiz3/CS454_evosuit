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
        W3CDom w3c = new W3CDom();
        try {
            Document wDoc = w3c.fromJsoup(null);
            fail("Should have thrown NullPointerException");
        } catch (NullPointerException e) {
            // expected, do nothing
        } catch (Exception e) {
            fail("Should have thrown NullPointerException");
        }
    }
    @Test
    public void test1() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html></html>", out);
    }
    @Test
    public void test2() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html><![CDATA[<p>CDATA content here.</p>]]></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><![CDATA[<p>CDATA content here.</p>]]></html>", out);
    }
    @Test
    public void test3() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html><head></head><body><div></div></body></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><head></head><body><div></div></body></html>", out);
    }
    @Test
    public void test4() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html><!-- Comment here --></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><!-- Comment here --></html>", out);
    }
    @Test
    public void test5() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html><head><script>alert(\"test\")</script></head><body></body></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><script>alert(\"test\")" +
                "</script></head><body></body></html>", out);
    }
    @Test
    public void test6() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html><body class='test'></body></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body class=\"test\">" +
                "</body></html>", out);
    }
    @Test
    public void test7() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html xmlns='http://www.w3.org/1999/xhtml'></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head></html>", out);
    }
    @Test
    public void test8() throws IOException {
        org.jsoup.nodes.Document doc = org.jsoup.Jsoup.parse("<html><body><p>Test</p></body></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body><p>Test</p></body></html>", out);
    }
    @Test
    public void test9() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", wDoc.getDocumentURI() );
    }
    @Test
    public void test10() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri(null);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(null, wDoc.getDocumentURI() );
    }
    @Test
    public void test11() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("https://example.com");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("https://example.com", wDoc.getDocumentURI() );
    }
    @Test
    public void test12() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("Invalid URL");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("Invalid URL", wDoc.getDocumentURI() );
    }
    @Test
    public void test13() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.empty();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        
        assertNull(wDoc.getDocumentURI() );
    }
    @Test
    public void test14() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setBaseUri("   ");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("   ", wDoc.getDocumentURI() );
    }
    @Test
    public void test15() {
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
    public void test16() throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.parse("", "");

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
    public void test17() {
        String html = "<fb:like></fb:like>";
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
    public void test18() throws IOException {
        File in = new File("test1.html"); // Change to a different HTML file
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
    public void test19() throws IOException {
        File in = new File("test2.html"); // Change to a different HTML file
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
    public void test20() {
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
    @Test public void test21() {
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
    public void test22() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test23() throws IOException {
        File in = new File("test3.html"); // Change to a different HTML file
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test24() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("div", "");
        int depth = 5;
        
        // No assertions here, the purpose is to kill the mutant
        tail(source, depth);
    }
    @Test
    public void test25() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Entity("nbsp", "");
        int depth = 0;
        
        // No assertions here, the purpose is to kill the mutant
        tail(source, depth);
    }
    @Test
    public void test26() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("a", "");
        int depth = 1;
        
        // No assertions here, the purpose is to kill the mutant
        tail(source, depth);
    }
    @Test
    public void test27() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.TextNode("Hello World", "");
        int depth = 3;
        
        // No assertions here, the purpose is to kill the mutant
        tail(source, depth);
    }
    @Test
    public void test28() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
        source.attributes().put("attributename1", "value1");
        source.attributes().put("attribute$name2", "value2");
        Element el = new Element("div");

        copyAttributes(source, el);

        assertNull(el.getAttribute("attributename1"));
        assertNull(el.getAttribute("attribute$name2"));
    }
    @Test
    public void test29() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
        source.attributes().put("attribute$name", "value");
        Element el = new Element("div");

        copyAttributes(source, el);

        assertNull(el.getAttribute("attribute$name"));
    }
    @Test
    public void test30() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
        source.attributes().put("-attributename", "value");
        Element el = new Element("div");

        copyAttributes(source, el);

        assertNull(el.getAttribute("-attributename"));
    }
    @Test
    public void test31() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("http://www.google.com/", wDoc.getDocumentURI() );
    }
    @Test public void test32() {
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
    public void test33() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html:html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test34() {
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
    public void test35() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("xhtml:html", htmlEl.getNodeName());

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
    public void test36() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
@Test
public void test37() {
    org.jsoup.nodes.Document doc = Jsoup.parse("");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = w3c.asString(wDoc);
    assertEquals("", out);
}
@Test
public void test38() {
    String html = "<html><head></head><body><p class='one' id=12>Text</p></body></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines(
            "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head>" +
            "<body><p class=\"one\" id=\"12\">Text</p></body></html>"
    );
    assertEquals(expected, out);
}
@Test
public void test39() {
    String html = "<html><head><title>W3c</title></head></html>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines(
            "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title></head><body></body></html>"
    );
    assertEquals(expected, out);
}
@Test
public void test40() {
    String html = "<head><title>W3c</title></head><body><p class='one' id=12>Text</p></body>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);
    String out = TextUtil.stripNewlines(w3c.asString(wDoc));
    String expected = TextUtil.stripNewlines(
            "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
            "</head><body><p class=\"one\" id=\"12\">Text</p></body></html>"
    );
    assertEquals(expected, out);
}
}