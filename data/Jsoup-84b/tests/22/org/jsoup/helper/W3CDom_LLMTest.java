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
    org.jsoup.nodes.Document doc = Jsoup.parse("<html>Location</html>");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals("Location", out);
}
    @Test
    public void test1() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        doc.setBaseUri(null);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        assertNull(wDoc.getDocumentURI());
    }
    @Test
    public void test2() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        doc.setBaseUri("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        assertEquals("", wDoc.getDocumentURI());
    }
    @Test
    public void test3() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.body().children().remove();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        assertEquals(null, wDoc.getFirstChild());
    }
    @Test
    public void test4() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.body().children().remove();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        assertEquals(null, wDoc.getFirstChild());
    }
    @Test
    public void test5() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        assertNull(wDoc.getFirstChild());
    }
    @Test
    public void test6() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
        doc.appendChild(new org.jsoup.nodes.TextNode("text", ""));

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        assertNull(wDoc.getFirstChild());
    }
    @Test
    public void test7() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.body().attr("xmlns", null);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node rootElement = wDoc.getFirstChild();
        assertEquals(null, rootElement.getNamespaceURI());
    }
    @Test
    public void test8() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.body().attr("xmlns", "");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node rootElement = wDoc.getFirstChild();
        assertEquals("", rootElement.getNamespaceURI());
    }
    @Test
    public void test9() {
        Node source = new Element();
        int depth = 0;

        // Test case 1
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node head = new Element();
        org.jsoup.nodes.Element sourceEl = (org.jsoup.nodes.Element) source;
        String prefix = updateNamespaces(sourceEl);
        String namespace = namespacesStack.peek().get(prefix);
        String tagName = sourceEl.tagName();
        Element el = doc.createElementNS(namespace, tagName);
        copyAttributes(sourceEl, el);
        if (dest == null) {
            doc.appendChild(el);
        } else {
            dest.appendChild(el);
        }
        dest = el;
        assertEquals(dest, el);
        
        // Test case 2
        source = new org.jsoup.nodes.TextNode();
        org.jsoup.nodes.TextNode sourceText = (org.jsoup.nodes.TextNode) source;
        Text text = doc.createTextNode(sourceText.getWholeText());
        dest.appendChild(text);
        assertEquals(dest.getChildNodes().item(0), text);
        
        // Test case 3
        source = new org.jsoup.nodes.Comment();
        org.jsoup.nodes.Comment sourceComment = (org.jsoup.nodes.Comment) source;
        Comment comment = doc.createComment(sourceComment.getData());
        dest.appendChild(comment);
        assertEquals(dest.getChildNodes().item(1), comment);
        
        // Test case 4
        source = new org.jsoup.nodes.DataNode();
        org.jsoup.nodes.DataNode sourceData = (org.jsoup.nodes.DataNode) source;
        Text node = doc.createTextNode(sourceData.getWholeData());
        dest.appendChild(node);
        assertEquals(dest.getChildNodes().item(2), node);
    }
    @Test
    public void test10() {
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
               
        
        // Test case 1
        Node section = htmlEl.getChildNodes().item(2).getChildNodes().item(3).getNextSibling().getNextSibling();
        assertEquals("urn:test", xSection.getNamespaceURI());
        assertEquals("section", xSection.getLocalName());
        assertEquals("x:section", xSection.getNodeName());
        
        // Test case 2
        Node svg = section.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/2000/svg", svg.getNamespaceURI());
        assertEquals("svg", svg.getLocalName());
        assertEquals("svg", svg.getNodeName());
        
        // Test case 3
        Node clip = svg.getChildNodes().item(1).getChildNodes().item(1);
        assertEquals("http://example.com/clip", clip.getNamespaceURI());
        assertEquals("clip", clip.getLocalName());
        assertEquals("clip", clip.getNodeName());
        assertEquals("456", clip.getTextContent());
    }
    @Test
    public void test11() {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        // Test case 1
        String out = w3c.asString(wDoc);
        assertFalse(out.contains("ipod"));
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
    public void test13() {
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
    public void test15() {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test16() {
        org.jsoup.nodes.Node source = null;
        int depth = 0;

        tail(source, depth);
        
        // Assert statements
    }
    @Test
    public void test17() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
        int depth = 1;

        tail(source, depth);
        
        // Assert statements
    }
    @Test
    public void test18() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
        int depth = 2;

        tail(source, depth);
        
        // Assert statements
    }
    @Test
    public void test19() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("span");
        int depth = 0;

        tail(source, depth);
        
        // Assert statements
    }
    @Test
    public void test20() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("span");
        int depth = 1;

        tail(source, depth);
        
        // Assert statements
    }
    @Test
    public void test21() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(Tag.valueOf("div"), "");
        source.attr("<<name>>", "&quot;test&quot;");
        source.attr("attr<1>", "value<1>");

        Element el = new Element("div");

        copyAttributes(source, el);

        assertEquals("&quot;test&quot;", el.getAttribute("name"));
        assertEquals("value<1>", el.getAttribute("attr_1"));
    }
    @Test
    public void test22() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(Tag.valueOf("div"), "");
        source.attr("123name", "value1");
        source.attr("@attr@", "value2");

        Element el = new Element("div");

        copyAttributes(source, el);

        assertNull(el.getAttribute("123name"));
        assertNull(el.getAttribute("@attr@"));

    }
    @Test
    public void test23() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(Tag.valueOf("div"), "");
        source.attr("name", "");

        Element el = new Element("div");

        copyAttributes(source, el);

        assertEquals("", el.getAttribute("name"));
    }
    @Test
    public void test24() {
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element(Tag.valueOf("div"), "");
        source.attr("@attr1@", "value1");
        source.attr("name", "value2");
        source.attr("@@attr2@@", "value3");
        source.attr("123attr", "value4");

        Element el = new Element("div");

        copyAttributes(source, el);

        assertNull(el.getAttribute("@attr1@"));
        assertNull(el.getAttribute("@@attr2@@"));
        assertNull(el.getAttribute("123attr"));
        assertEquals("value2", el.getAttribute("name"));
    }
    @Test
    public void test25() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        // Change the document location
        doc.location("http://example.com");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test public void test26() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        // Change the prefix of the element
        Element fbLike = doc.select("fb\\:like").first();
        fbLike.prefix("tw");

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
        
        // Change the presence of "ipod"
        assertTrue(out.contains("iphone"));
    }
    @Test
    public void test28() {
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
        
        // Modify the expected output
        assertEquals(expected + "hello", out);
    }
    @Test
    public void test29() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        // Add an extra attribute to the root element
        Element htmlEl = jsoupDoc.select("html").first();
        htmlEl.attr("lang", "en");

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
    public void test30() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
        
        // Add an extra attribute to the body element
        Element bodyEl = w3Doc.getDocumentElement().getElementsByTagName("body").item(0);
        bodyEl.setAttribute("extra", "attribute");
    }
    @Test
    public void test31() {
        Document doc = createEmptyDocument();

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
    public void test32() {
        Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test33() {
        Document doc = createEmptyDocument();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = "";
        assertEquals(expected, out);
    }
    @Test
    public void test34() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>\\uD83D\\uDE00</p><!-- comment --><invalid>\\uD83D\\uDE00<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                        "</head><body><p class=\"one\" id=\"12\">\uD83D\uDE00</p><!-- comment --><invalid>\uD83D\uDE00<script>alert('!')</script>" +
                        "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    private Document createEmptyDocument() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.newDocument();
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }
}