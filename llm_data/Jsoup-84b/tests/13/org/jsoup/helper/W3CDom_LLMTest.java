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
        // Change the input file to another valid HTML file
        File in = ParseTest.getFile("/htmltests/google-ipod-another.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI());
    }
    @Test public void test1() {
        // Change the HTML content to a different HTML content
        String html = "<fb:like>Two</fb:like>";
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
        // Change the input file to another valid HTML file
        File in = ParseTest.getFile("/htmltests/google-ipod-another.html");
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
    public void test3() {
        // Change the HTML content to a different HTML content
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
    public void test4() throws IOException {
        // Change the input file to another valid XHTML file
        File in = ParseTest.getFile("/htmltests/namespaces-another.xhtml");
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
        // Change the HTML content to a different HTML content
        String html = "<html><head></head><body style=\"color: green\" \" name\"></body></html>";
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
        Document wDoc = new Document();

        W3CDom w3c = new W3CDom();
        w3c.convert(doc, wDoc);

        assertEquals("", wDoc.getDocumentURI() );
    }
    @Test
    public void test7() throws IOException {
        Document wDoc = new Document();

        W3CDom w3c = new W3CDom();
        w3c.convert(null, wDoc);

        assertEquals(null, wDoc.getDocumentURI() );
    }
    @Test
    public void test8() throws IOException {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        Document wDoc = new Document();

        W3CDom w3c = new W3CDom();
        w3c.convert(doc, wDoc);

        assertEquals("", w3c.asString(wDoc) );
    }
    @Test
    public void test9() throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.parse("<html></html>");
        Document wDoc = new Document();

        W3CDom w3c = new W3CDom();
        w3c.convert(doc, wDoc);

        assertEquals("<html xmlns=\"http://www.w3.org/1999/xhtml\"></html>", w3c.asString(wDoc) );
    }
    @Test
    public void test10() throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.parse("<head></head>");
        Document wDoc = new Document();

        W3CDom w3c = new W3CDom();
        w3c.convert(doc, wDoc);

        assertEquals("<head></head>", w3c.asString(wDoc) );
    }
    @Test
    public void test11() throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.parse("<body></body>");
        Document wDoc = new Document();

        W3CDom w3c = new W3CDom();
        w3c.convert(doc, wDoc);

        assertEquals("<body></body>", w3c.asString(wDoc) );
    }
    @Test
    public void test12() throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.parse("<title></title>");
        Document wDoc = new Document();

        W3CDom w3c = new W3CDom();
        w3c.convert(doc, wDoc);

        assertEquals("<title></title>", w3c.asString(wDoc) );
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
    public void test14() throws IOException {
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
    public void test15() {
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
    @Test public void test16() {
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
    public void test17() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
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
    public void test19() {
        org.jsoup.nodes.Node source = null;
        int depth = 0;

        // Change source and depth to null
        tail(source, depth);

        // Check that namespacesStack.pop() is not called
        // ...

    }
    @Test
    public void test20() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.TextNode("text", "");
        int depth = 0;

        // Change source to a non-Element node
        // ...

        // Change rest of the code to handle non-Element source node
        // ...
    }
    @Test
    public void test21() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
        int depth = 0;
        org.w3c.dom.Node dest = null;

        // Change dest to a non-Element node
        // ...

        // Change rest of the code to handle non-Element destination node
        // ...
    }
@Test
public void test22() {
    org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
    source.attr("9invalid", "value");
    Element el = new Element("div");
    
    copyAttributes(source, el);
    
    assertFalse(el.hasAttributes());
}
@Test
public void test23() {
    org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
    source.attr("valid_attribute", "value");
    Element el = new Element("div");
    
    copyAttributes(source, el);
    
    assertTrue(el.hasAttr("valid_attribute"));
    assertEquals("value", el.attr("valid_attribute"));
}
@Test
public void test24() {
    org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
    source.attr("$#invalid#&", "value");
    Element el = new Element("div");
    
    copyAttributes(source, el);
    
    assertFalse(el.hasAttributes());
}
@Test
public void test25() throws IOException {
    org.jsoup.nodes.Document doc = Jsoup.parse("", "");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals("", out);
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
public void test27() throws IOException {
    org.jsoup.nodes.Document doc = Jsoup.parse("", "");

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
public void test28() {
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
public void test29() throws IOException {
    org.jsoup.nodes.Document jsoupDoc = Jsoup.parse("");

    Document doc;
    org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
    doc = jDom.fromJsoup(jsoupDoc);

    Node htmlEl = doc.getChildNodes().item(0);
    assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    String out = jDom.asString(doc);
    assertEquals("<html xmlns=\"http://www.w3.org/1999/xhtml\"/>", out);
}
@Test
public void test30() {
    String html = "<html><head></head><body style=\"color: red\" age\"></body></html>";
    org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    assertTrue(body.hasAttr("age\""));

    Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
}
    @Test
    public void test31() {
        String html = "";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test32() {
        String html = "<html></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html></html>", out);
    }
    @Test
    public void test33() {
        String html = "<html><head><title>Special Characters</title></head><body>Special characters: &, <, >, \", ', \n</body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Special Characters</title>" +
                "</head><body>Special characters: &amp;, &lt;, &gt;, &quot;, &apos;, \n</body></html>", out);
    }
    @Test
    public void test34() {
        String html = "<html><!-- This is a comment --></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><!-- This is a comment --></html>", out);
    }
    @Test
    public void test35() {
        String html = "<html><script>alert('Hello World');</script></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html><script>alert('Hello World');</script></html>", out);
    }
}