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

        String out = w3c.asString(wDoc);
        assertEquals(null, wDoc.getDocumentURI() );
    }
    @Test public void test1() {
        org.jsoup.nodes.Document doc = null;

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertNull(htmlEl.getNamespaceURI());
        assertEquals(null, htmlEl.getLocalName());
        assertEquals(null, htmlEl.getNodeName());

        Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertNull(fb.getNamespaceURI());
        assertEquals(null, fb.getLocalName());
        assertEquals(null, fb.getNodeName());

    }
    @Test
    public void test2() {
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals(null, htmlEl.getLocalName());
        assertEquals(null, htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(!out.contains("ipod"));
    }
    @Test
    public void test3() {
        String html = null;
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
    public void test4() {
        org.jsoup.nodes.Document jsoupDoc = null;

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
        assertEquals("Check", epubTitle.getTextContent());
        assertEquals(null, epubTitle.getNamespaceURI());
        assertEquals(null, epubTitle.getLocalName());
        assertEquals(null, epubTitle.getNodeName());

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals(null, xSection.getNamespaceURI());
        assertEquals(null, xSection.getLocalName());
        assertEquals(null, xSection.getNodeName());

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
    public void test5() {
        String html = null;
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test6() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertFalse(doc.location().isEmpty());
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test7() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertNotNull(htmlEl);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertNotNull(out);
        assertTrue(out.contains("ipod"));
    }
    @Test
    public void test8() {
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
    public void test9() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertNotNull(htmlEl);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        // inherits default namespace
        Node head = htmlEl.getFirstChild();
        assertNotNull(head);
        assertEquals("http://www.w3.org/1999/xhtml", head.getNamespaceURI());
        assertEquals("head", head.getLocalName());
        assertEquals("head", head.getNodeName());

        Node epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        assertNotNull(epubTitle);
        assertEquals("Check", epubTitle.getTextContent());
        assertEquals("http://www.idpf.org/2007/ops", epubTitle.getNamespaceURI());
        assertEquals("title", epubTitle.getLocalName());
        assertEquals("epub:title", epubTitle.getNodeName());

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertNotNull(xSection);
        assertEquals("urn:test", xSection.getNamespaceURI());
        assertEquals("section", xSection.getLocalName());
        assertEquals("x:section", xSection.getNodeName());

        // https://github.com/jhy/jsoup/issues/977
        // does not keep last set namespace
        Node svg = xSection.getNextSibling().getNextSibling();
        assertNotNull(svg);
        assertEquals("http://www.w3.org/2000/svg", svg.getNamespaceURI());
        assertEquals("svg", svg.getLocalName());
        assertEquals("svg", svg.getNodeName());

        Node path = svg.getChildNodes().item(1);
        assertNotNull(path);
        assertEquals("http://www.w3.org/2000/svg", path.getNamespaceURI());
        assertEquals("path", path.getLocalName());
        assertEquals("path", path.getNodeName());

        Node clip = path.getChildNodes().item(1);
        assertNotNull(clip);
        assertEquals("http://example.com/clip", clip.getNamespaceURI());
        assertEquals("clip", clip.getLocalName());
        assertEquals("clip", clip.getNodeName());
        assertEquals("456", clip.getTextContent());

        Node picture = svg.getNextSibling().getNextSibling();
        assertNotNull(picture);
        assertEquals("http://www.w3.org/1999/xhtml", picture.getNamespaceURI());
        assertEquals("picture", picture.getLocalName());
        assertEquals("picture", picture.getNodeName());

        Node img = picture.getFirstChild();
        assertNotNull(img);
        assertEquals("http://www.w3.org/1999/xhtml", img.getNamespaceURI());
        assertEquals("img", img.getLocalName());
        assertEquals("img", img.getNodeName());
    }
    @Test
    public void test10() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test11() {
        Document doc = new Document("");

        Element sourceEl = new Element("div");
        sourceEl.attr("id", "test");
        sourceEl.attr("class", "class1 class2");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        wDoc.head(sourceEl, 0);

        Node div = wDoc.getElementsByTagName("div").item(0);
        assertNotNull(div);
        assertEquals("div", div.getLocalName());
        assertEquals("test", div.getAttributes().getNamedItem("id").getNodeValue());
        assertEquals("class1 class2", div.getAttributes().getNamedItem("class").getNodeValue());
    }
    @Test
    public void test12() {
        Document doc = new Document("");

        TextNode sourceText = new TextNode("This is a text node", "");
    
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        wDoc.head(sourceText, 0);

        Node text = wDoc.getElementsByTagName("#text").item(0);
        assertNotNull(text);
        assertEquals("#text", text.getNodeName());
        assertEquals("This is a text node", text.getTextContent());
    }
    @Test
    public void test13() {
        Document doc = new Document("");

        Comment sourceComment = new Comment("This is a comment");
    
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        wDoc.head(sourceComment, 0);

        Node comment = wDoc.getElementsByTagName("#comment").item(0);
        assertNotNull(comment);
        assertEquals("#comment", comment.getNodeName());
        assertEquals("This is a comment", comment.getTextContent());
    }
    @Test
    public void test14() {
        Document doc = new Document("");

        DataNode sourceData = new DataNode("This is a data node", "");
    
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        wDoc.head(sourceData, 0);

        Node data = wDoc.getElementsByTagName("#data").item(0);
        assertNotNull(data);
        assertEquals("#data", data.getNodeName());
        assertEquals("This is a data node", data.getTextContent());
    }
    @Test
    public void test15() {
        Document doc = new Document("");

        Node sourceNode = new Node() {
            public String getNodeName() {
                return "unknown";
            }
            public String getNodeValue() {
                return null;
            }
            public void setNodeValue(String nodeValue) {
            }
            public short getNodeType() {
                return 0;
            }
            public Node getParentNode() {
                return null;
            }
            public NodeList getChildNodes() {
                return null;
            }
            public Node getFirstChild() {
                return null;
            }
            public Node getLastChild() {
                return null;
            }
            public Node getPreviousSibling() {
                return null;
            }
            public Node getNextSibling() {
                return null;
            }
            public NamedNodeMap getAttributes() {
                return null;
            }
            public Document getOwnerDocument() {
                return null;
            }
            public Node insertBefore(Node newChild, Node refChild) {
                return null;
            }
            public Node replaceChild(Node newChild, Node oldChild) {
                return null;
            }
            public Node removeChild(Node oldChild) {
                return null;
            }
            public Node appendChild(Node newChild) {
                return null;
            }
            public boolean hasChildNodes() {
                return false;
            }
            public Node cloneNode(boolean deep) {
                return null;
            }
            public void test16() {
            }
            public boolean isSupported(String feature, String version) {
                return false;
            }
            public String getNamespaceURI() {
                return null;
            }
            public String getPrefix() {
                return null;
            }
            public void setPrefix(String prefix) {
            }
            public String getLocalName() {
                return null;
            }
            public boolean hasAttributes() {
                return false;
            }
            public String getBaseURI() {
                return null;
            }
            public short compareDocumentPosition(Node other) {
                return 0;
            }
            public String getTextContent() {
                return null;
            }
            public void setTextContent(String textContent) {
            }
            public boolean isSameNode(Node other) {
                return false;
            }
            public String lookupPrefix(String namespaceURI) {
                return null;
            }
            public boolean isDefaultNamespace(String namespaceURI) {
                return false;
            }
            public String lookupNamespaceURI(String prefix) {
                return null;
            }
            public boolean isEqualNode(Node arg) {
                return false;
            }
            public Object getFeature(String feature, String version) {
                return null;
            }
            public Object setUserData(String key, Object data, UserDataHandler handler) {
                return null;
            }
            public Object getUserData(String key) {
                return null;
            }
        };
    
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        wDoc.head(sourceNode, 0);

        Node unknown = wDoc.getElementsByTagName("unknown").item(0);
        assertNull(unknown);
    }
    @Test
    public void test17() {
        org.jsoup.nodes.Node source = null;
        int depth = 0;
        // Add test case here
    }
    @Test
    public void test18() {
        org.jsoup.nodes.Node source = null;
        int depth = 1;
        // Add test case here
    }
    @Test
    public void test19() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element();
        int depth = 0;
        // Add test case here
    }
    @Test
    public void test20() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element();
        int depth = 1;
        // Add test case here
    }
    @Test
    public void test21() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element();
        int depth = -1;
        // Add test case here
    }
    @Test
    public void test22() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("div");
        Element el = new Element("div");
        copyAttributes(source, el);
        // assert something
    }
    @Test
    public void test23() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Element("span");
        Element el = new Element("span");
        copyAttributes(source, el);
        // assert something
    }
    @Test
    public void test24() {
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Attribute("class", "test-class");
        Element el = new Element("attribute");
        copyAttributes(source, el);
        // assert something
    }
    @Test
    public void test25() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );

        // Change the input element to have xmlns declarations
        org.jsoup.nodes.Element el = doc.select("html").first();
        el.attr("xmlns", "http://www.w3.org/1999/xhtml");
        el.attr("xmlns:fb", "http://www.facebook.com/2008/fbml");

        wDoc = w3c.fromJsoup(doc);
        assertEquals("http://www.w3.org/1999/xhtml", wDoc.getDocumentElement().getNamespaceURI());
        assertEquals("http://www.facebook.com/2008/fbml", wDoc.getDocumentElement().getAttribute("xmlns:fb"));

        out = w3c.asString(wDoc);
        assertTrue(out.contains("google-ipod.html"));
    }
    @Test
    public void test26() {
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

        // Change the input to have declared namespace
        html = "<fb:like xmlns:fb=\"http://www.facebook.com/2008/fbml\">One</fb:like>";
        doc = Jsoup.parse(html);

        w3Doc = new W3CDom().fromJsoup(doc);
        htmlEl = w3Doc.getFirstChild();

        assertEquals("http://www.facebook.com/2008/fbml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
        assertEquals("http://www.facebook.com/2008/fbml", fb.getNamespaceURI());
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

        // Change the input element to have xmlns declarations
        org.jsoup.nodes.Element el = doc.select("html").first();
        el.attr("xmlns", "http://www.w3.org/1999/xhtml");
        el.attr("xmlns:fb", "http://www.facebook.com/2008/fbml");

        wDoc = w3c.fromJsoup(doc);
        assertEquals("http://www.w3.org/1999/xhtml", wDoc.getDocumentElement().getNamespaceURI());
        assertEquals("http://www.facebook.com/2008/fbml", wDoc.getDocumentElement().getAttribute("xmlns:fb"));

        out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
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

        // Change the input to have declared namespace
        html = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        doc = Jsoup.parse(html);

        w3c = new W3CDom();
        wDoc = w3c.fromJsoup(doc);
        out = TextUtil.stripNewlines(w3c.asString(wDoc));
        assertEquals(expected, out);
    }
    @Test
    public void test29() throws IOException {
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

        // Change the input' epubTitle element to have xmlns declarations
        Node epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        ((Element)epubTitle).attr("xmlns:epub", "http://www.idpf.org/2007/ops");
        ((Element)epubTitle).attr("xmlns:custom", "http://www.example.com/custom");

        doc = jDom.fromJsoup(jsoupDoc);
        htmlEl = doc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        head = htmlEl.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", head.getNamespaceURI());
        assertEquals("head", head.getLocalName());
        assertEquals("head", head.getNodeName());

        epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        assertEquals("Check", epubTitle.getTextContent());
        assertEquals("http://www.idpf.org/2007/ops", epubTitle.getNamespaceURI());
        assertEquals("title", epubTitle.getLocalName());
        assertEquals("epub:title", epubTitle.getNodeName());

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals("urn:test", xSection.getNamespaceURI());
        assertEquals("section", xSection.getLocalName());
        assertEquals("x:section", xSection.getNodeName());

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

        // Change the input element svg to have xmlns declaration
        svg = xSection.getNextSibling().getNextSibling();
        ((Element)svg).attr("xmlns:svg", "http://www.w3.org/2000/svg");
        ((Element)svg).attr("xmlns:custom", "http://www.example.com/custom");

        doc = jDom.fromJsoup(jsoupDoc);
        htmlEl = doc.getChildNodes().item(0);
        assertEquals("http://www.w3.org/1999/xhtml", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        head = htmlEl.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", head.getNamespaceURI());
        assertEquals("head", head.getLocalName());
        assertEquals("head", head.getNodeName());

        epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        assertEquals("Check", epubTitle.getTextContent());
        assertEquals("http://www.idpf.org/2007/ops", epubTitle.getNamespaceURI());
        assertEquals("title", epubTitle.getLocalName());
        assertEquals("epub:title", epubTitle.getNodeName());

        xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals("urn:test", xSection.getNamespaceURI());
        assertEquals("section", xSection.getLocalName());
        assertEquals("x:section", xSection.getNodeName());

        svg = xSection.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/2000/svg", svg.getNamespaceURI());
        assertEquals("svg", svg.getLocalName());
        assertEquals("svg", svg.getNodeName());

        path = svg.getChildNodes().item(1);
        assertEquals("http://www.w3.org/2000/svg", path.getNamespaceURI());
        assertEquals("path", path.getLocalName());
        assertEquals("path", path.getNodeName());

        clip = path.getChildNodes().item(1);
        assertEquals("http://example.com/clip", clip.getNamespaceURI());
        assertEquals("clip", clip.getLocalName());
        assertEquals("clip", clip.getNodeName());
        assertEquals("456", clip.getTextContent());

        picture = svg.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/1999/xhtml", picture.getNamespaceURI());
        assertEquals("picture", picture.getLocalName());
        assertEquals("picture", picture.getNodeName());

        img = picture.getFirstChild();
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

        // Change the input element body to have an invalid attribute name
        html = "<html><head></head><body <??style=\"color: red\" \" name\"></body></html>";
        jsoupDoc = Jsoup.parse(html);
        body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\""));
        assertTrue(body.hasAttr("name\""));

        w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test31() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("&lt;meta&gt;"));
    }
    @Test
    public void test32() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        Element head = (Element)htmlEl.getFirstChild();
        head.removeChild(head.getFirstChild()); // remove title element
        
        String out = w3c.asString(wDoc);
        assertFalse(out.contains("<title>W3c</title>"));
    }
    @Test
    public void test33() throws IOException {
        String html = "";

        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        String out = w3c.asString(wDoc);
        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"></head><body></body></html>", out);
    }
    @Test
    public void test34() throws IOException {
        String html = "<!DOCTYPE html><html><head><title>W3c</title></head><body><p class='one' id=12>Text</p></body></html>";

        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        String out = w3c.asString(wDoc);
        assertEquals("<!DOCTYPE html><html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><p class=\"one\" id=\"12\">Text</p></body></html>", out);
    }
    @Test
    public void test35() throws IOException {
        String html = "<html><head><title>W3c</title></head><body><!--This is a comment--><p class='one' id=12>Text</p></body></html>";

        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
        
        String out = w3c.asString(wDoc);
        assertEquals("<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><!--This is a comment--><p class=\"one\" id=\"12\">Text</p></body></html>", out);
    }
}