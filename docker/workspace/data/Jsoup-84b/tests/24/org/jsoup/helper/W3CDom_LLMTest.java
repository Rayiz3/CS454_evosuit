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
        // Test with null input
        try {
            org.jsoup.nodes.Document doc = null;
            W3CDom w3c = new W3CDom();
            Document wDoc = w3c.fromJsoup(doc);
            // Test fails if no exception is thrown
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // Test passes if NullPointerException is thrown
        }
    }
    @Test
    public void test1() {
        // Test with empty input
        org.jsoup.nodes.Document doc = Jsoup.parse("");
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        // Test passes if no exception is thrown
    }
    @Test
    public void test2() {
        // Test with non-HTML input
        org.jsoup.nodes.Document doc = Jsoup.parse("This is not HTML");
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        // Test passes if no exception is thrown
    }
    @Test
    public void test3() {
        // Test with invalid XML configuration
        try {
            org.jsoup.nodes.Document doc = Jsoup.parse("<html><head><title>W3c</title></head></html>");
            W3CDom w3c = new W3CDom();
            // Set invalid configuration by not setting factory to be namespace-aware
            // factory.setNamespaceAware(true);
            Document wDoc = w3c.fromJsoup(doc);
            // Test fails if no exception is thrown
            fail("IllegalStateException expected");
        } catch (IllegalStateException e) {
            // Test passes if IllegalStateException is thrown
        }
    }
    @Test
    public void test4() {
        // Test with invalid namespace
        try {
            String html = "<fb:like>One</fb:like>";
            org.jsoup.nodes.Document doc = Jsoup.parse(html);
            W3CDom w3c = new W3CDom();
            Document wDoc = w3c.fromJsoup(doc);
            // Test fails if no exception is thrown
            fail("AssertionError expected");
        } catch (AssertionError e) {
            // Test passes if AssertionError is thrown
        }
    }
    @Test
    public void test5() {
        // Test with null input
        try {
            org.jsoup.nodes.Document doc = null;
            W3CDom w3c = new W3CDom();
            Document wDoc = w3c.fromJsoup(doc);
            // Test fails if no exception is thrown
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // Test passes if NullPointerException is thrown
        }
    }
    @Test
    public void test6() {
        // Test with empty input
        org.jsoup.nodes.Document doc = Jsoup.parse("");
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        // Test passes if no exception is thrown
    }
    @Test
    public void test7() {
        // Test with non-HTML input
        org.jsoup.nodes.Document doc = Jsoup.parse("This is not HTML");
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        // Test passes if no exception is thrown
    }
    @Test
    public void test8() {
        // Test with empty input
        org.jsoup.nodes.Document doc = Jsoup.parse("");
        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test9() {
        // Test with invalid input
        try {
            org.jsoup.nodes.Document jsoupDoc = null;
            W3CDom jDom = new W3CDom();
            Document doc = jDom.fromJsoup(jsoupDoc);
            // Test fails if no exception is thrown
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // Test passes if NullPointerException is thrown
        }
    }
    @Test
    public void test10() {
        // Test with empty input
        org.jsoup.nodes.Document jsoupDoc = Jsoup.parse("");
        W3CDom w3c = new W3CDom();
        try {
            Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
            // Test fails if no exception is thrown
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
            // Test passes if NullPointerException is thrown
        }
    }
    @Test
    public void test11() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("http://example.com");
        doc.appendChild(new org.jsoup.nodes.TextNode("Hello World"));
        Document out = new Document();
        
        convert(doc, out);
        
        assertEquals("http://example.com", out.getDocumentURI());
        assertEquals("root", out.getFirstChild().getLocalName());
    }
    @Test
    public void test12() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("http://example.com");
        org.jsoup.nodes.Element root = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("root"), "");
        doc.appendChild(root);
        Document out = new Document();
        
        convert(doc, out);
        
        assertEquals("http://example.com", out.getDocumentURI());
        assertNull(out.getFirstChild());
    }
    @Test
    public void test13() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("http://example.com");
        org.jsoup.nodes.Element root = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("root"), "");
        root.appendChild(new org.jsoup.nodes.TextNode("Hello World"));
        doc.appendChild(root);
        Document out = new Document();
        
        convert(doc, out);
        
        assertEquals("http://example.com", out.getDocumentURI());
        assertEquals("root", out.getFirstChild().getLocalName());
        assertEquals("Hello World", out.getFirstChild().getTextContent());
    }
    @Test
    public void test14() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("http://example.com");
        org.jsoup.nodes.Element root = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("root"), "");
        root.attr("key1", "value1");
        root.attr("key2", "value2");
        doc.appendChild(root);
        Document out = new Document();
        
        convert(doc, out);
        
        assertEquals("http://example.com", out.getDocumentURI());
        assertEquals("root", out.getFirstChild().getLocalName());
        assertEquals("value1", out.getFirstChild().getAttributes().getNamedItem("key1").getNodeValue());
        assertEquals("value2", out.getFirstChild().getAttributes().getNamedItem("key2").getNodeValue());
    }
    @Test
    public void test15() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("http://example.com");
        org.jsoup.nodes.Element root = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("root"), "");
        root.appendChild(new org.jsoup.nodes.TextNode("Hello"));
        root.appendChild(new org.jsoup.nodes.Comment("Comment"));
        root.appendChild(new org.jsoup.nodes.DocumentType("html", "", "", ""));
        doc.appendChild(root);
        Document out = new Document();
        
        convert(doc, out);
        
        assertEquals("http://example.com", out.getDocumentURI());
        assertEquals("root", out.getFirstChild().getLocalName());
        assertEquals("Hello", out.getFirstChild().getFirstChild().getTextContent());
        assertEquals("Comment", out.getFirstChild().getFirstChild().getNextSibling().getTextContent());
        assertEquals("html", out.getFirstChild().getFirstChild().getNextSibling().getNextSibling().getNodeName());
    }
    @Test
    public void test16() {
        org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("http://example.com");
        org.jsoup.nodes.Element root = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("root"), "");
        org.jsoup.nodes.Element child1 = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("child1"), "");
        org.jsoup.nodes.Element child2 = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("child2"), "");
        org.jsoup.nodes.Element grandchild1 = new org.jsoup.nodes.Element(org.jsoup.parser.Tag.valueOf("grandchild1"), "");
        grandchild1.appendChild(new org.jsoup.nodes.TextNode("Hello"));
        child2.appendChild(grandchild1);
        child1.appendChild(child2);
        root.appendChild(child1);
        doc.appendChild(root);
        Document out = new Document();
        
        convert(doc, out);
        
        assertEquals("http://example.com", out.getDocumentURI());
        assertEquals("root", out.getFirstChild().getLocalName());
        assertEquals("child1", out.getFirstChild().getFirstChild().getLocalName());
        assertEquals("child2", out.getFirstChild().getFirstChild().getFirstChild().getLocalName());
        assertEquals("grandchild1", out.getFirstChild().getFirstChild().getFirstChild().getFirstChild().getLocalName());
        assertEquals("Hello", out.getFirstChild().getFirstChild().getFirstChild().getFirstChild().getTextContent());
    }
    @Test
    public void test17() {
        Node source = null;
        int depth = 0;

        W3CDom w3c = new W3CDom();
        Document doc = w3c.createDocument();
        w3c.head(source, depth);

        NodeList nodes = doc.getElementsByTagName("*");
        assertEquals(0, nodes.getLength());
    }
    @Test
    public void test18() {
        Node source = new org.jsoup.nodes.DocumentType("html", "", "", "");
        int depth = 0;

        W3CDom w3c = new W3CDom();
        Document doc = w3c.createDocument();
        w3c.head(source, depth);

        NodeList nodes = doc.getElementsByTagName("*");
        assertEquals(0, nodes.getLength());
    }
    @Test
    public void test19() {
        Node source = new org.jsoup.nodes.Element("div");
        int depth = 0;

        W3CDom w3c = new W3CDom();
        Document doc = w3c.createDocument();
        w3c.head(source, depth);

        NodeList nodes = doc.getElementsByTagName("*");
        assertEquals(1, nodes.getLength());
        assertEquals("div", nodes.item(0).getNodeName());
    }
    @Test
    public void test20() {
        Node source = new org.jsoup.nodes.TextNode("text", "");
        int depth = 0;

        W3CDom w3c = new W3CDom();
        Document doc = w3c.createDocument();
        w3c.head(source, depth);

        NodeList nodes = doc.getElementsByTagName("*");
        assertEquals(1, nodes.getLength());
        assertEquals("#text", nodes.item(0).getNodeName());
        assertEquals("text", nodes.item(0).getTextContent());
    }
    @Test
    public void test21() {
        Node source = new org.jsoup.nodes.Comment("comment");
        int depth = 0;

        W3CDom w3c = new W3CDom();
        Document doc = w3c.createDocument();
        w3c.head(source, depth);

        NodeList nodes = doc.getElementsByTagName("*");
        assertEquals(1, nodes.getLength());
        assertEquals("#comment", nodes.item(0).getNodeName());
        assertEquals("comment", nodes.item(0).getTextContent());
    }
    @Test
    public void test22() {
        Node source = new org.jsoup.nodes.DataNode("data", "");
        int depth = 0;

        W3CDom w3c = new W3CDom();
        Document doc = w3c.createDocument();
        w3c.head(source, depth);

        NodeList nodes = doc.getElementsByTagName("*");
        assertEquals(1, nodes.getLength());
        assertEquals("#text", nodes.item(0).getNodeName());
        assertEquals("data", nodes.item(0).getTextContent());
    }
    @Test
    public void test23() {
        // Create a source node that is not an Element
        Node source = new TextNode("Hello", "");
        int depth = 0;

        // Create a namespacesStack with some initial values
        Stack<String> namespacesStack = new Stack<>();
        namespacesStack.push("http://www.w3.org/1999/xhtml");
        namespacesStack.push("http://www.idpf.org/2007/ops");

        // Call the method
        tail(source, depth);

        // Assert that the namespacesStack is not modified
        assertEquals(2, namespacesStack.size());
        assertEquals("http://www.w3.org/1999/xhtml", namespacesStack.pop());
        assertEquals("http://www.idpf.org/2007/ops", namespacesStack.pop());
    }
    @Test
    public void test24() {
        // Create a source node that is an Element
        Node source = new Element("img", "");
        int depth = 0;
        
        // Create a dest node that is not an Element
        Node dest = new TextNode("Hello", "");

        // Create a namespacesStack with some initial values
        Stack<String> namespacesStack = new Stack<>();
        namespacesStack.push("http://www.w3.org/1999/xhtml");
        namespacesStack.push("http://www.idpf.org/2007/ops");

        // Call the method
        tail(source, depth);

        // Assert that the dest is not modified
        assertEquals(dest, dest.getParentNode());

        // Assert that the namespacesStack is not modified
        assertEquals(2, namespacesStack.size());
        assertEquals("http://www.w3.org/1999/xhtml", namespacesStack.pop());
        assertEquals("http://www.idpf.org/2007/ops", namespacesStack.pop());
    }
    @Test
    public void test25() {
        // Create a source node that is an Element
        Node source = new Element("p", "");
        int depth = 1;

        // Create a dest node that is an Element
        Node dest = new Element("div", "");

        // Create a namespacesStack with some initial values
        Stack<String> namespacesStack = new Stack<>();
        namespacesStack.push("http://www.w3.org/1999/xhtml");
        namespacesStack.push("http://www.idpf.org/2007/ops");

        // Call the method
        tail(source, depth);

        // Assert that the dest is modified to be the parent element
        assertEquals(dest.getParentNode(), dest);

        // Assert that the namespacesStack is not modified
        assertEquals(2, namespacesStack.size());
        assertEquals("http://www.w3.org/1999/xhtml", namespacesStack.pop());
        assertEquals("http://www.idpf.org/2007/ops", namespacesStack.pop());
    }
    @Test
    public void test26() {
        // Create a source node that is an Element
        Node source = new Element("p", "");
        int depth = -1;

        // Create a dest node that is an Element
        Node dest = new Element("div", "");

        // Create a namespacesStack with some initial values
        Stack<String> namespacesStack = new Stack<>();
        namespacesStack.push("http://www.w3.org/1999/xhtml");
        namespacesStack.push("http://www.idpf.org/2007/ops");

        // Call the method
        tail(source, depth);

        // Assert that the dest is not modified
        assertEquals(dest, dest.getParentNode());

        // Assert that the namespacesStack is not modified
        assertEquals(2, namespacesStack.size());
        assertEquals("http://www.w3.org/1999/xhtml", namespacesStack.pop());
        assertEquals("http://www.idpf.org/2007/ops", namespacesStack.pop());
    }
    @Test
    public void test27() {
        // Create a source node that is an Element
        Node source = new Element("p", "");
        int depth = 0;

        // Create a namespacesStack with no initial values
        Stack<String> namespacesStack = new Stack<>();

        // Call the method
        tail(source, depth);

        // Assert that the namespacesStack is not modified
        assertEquals(0, namespacesStack.size());
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
    String html = "<fb_like>One</fb_like>";
    org.jsoup.nodes.Document doc = Jsoup.parse(html);

    Document w3Doc = new W3CDom().fromJsoup(doc);
    Node htmlEl = w3Doc.getFirstChild();

    assertNull(htmlEl.getNamespaceURI());
    assertEquals("html", htmlEl.getLocalName());
    assertEquals("html", htmlEl.getNodeName());

    Node fb = htmlEl.getFirstChild().getNextSibling().getFirstChild();
    assertNull(fb.getNamespaceURI());
    assertEquals("like", fb.getLocalName());
    assertEquals("fb_like", fb.getNodeName());
}
@Test
public void test30() throws IOException {
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
public void test31() {
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
public void test32() throws IOException {
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
    assertEquals("epub_title", epubTitle.getNodeName());

    Node xSection = epubTitle.getNextSibling().getNextSibling();
    assertEquals("urn:test", xSection.getNamespaceURI());
    assertEquals("section", xSection.getLocalName());
    assertEquals("x_section", xSection.getNodeName());

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
public void test33() {
    String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
    assertTrue(body.hasAttr("name\""));

    Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
}
    @Test
    public void test34() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test35() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        org.jsoup.nodes.Element modifiedElement = doc.getElementsByTag("title").first();
        modifiedElement.attr("xmlns", "custom");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test36() {
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
    public void test37() {
        String html = "<fb:like>One</fb:like>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        org.jsoup.nodes.Element modifiedElement = doc.getElementsByTag("fb:like").first();
        modifiedElement.attr("xmlns", "custom");

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
    public void test38() throws IOException {
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
    public void test39() {
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
    public void test40() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What<script>alert('!')";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        org.jsoup.nodes.Element modifiedElement = doc.getElementsByTag("title").first();
        modifiedElement.attr("xmlns", "custom");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title xmlns=\"custom\">W3c</title>" +
                        "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What<script>alert('!')</script>" +
                        "</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
    @Test
    public void test41() throws IOException {
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
    public void test42() throws IOException {
        File in = ParseTest.getFile("/htmltests/namespaces.xhtml");
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(in, "UTF-8");

        org.jsoup.nodes.Element htmlElement = jsoupDoc.getElementsByTag("html").first();
        htmlElement.attr("xmlns", "custom");

        org.jsoup.nodes.Element headElement = jsoupDoc.getElementsByTag("head").first();
        headElement.attr("xmlns", "custom");

        org.jsoup.nodes.Element epubTitleElement = jsoupDoc.getElementsByTag("epub:title").first();
        epubTitleElement.attr("xmlns", "custom");

        org.jsoup.nodes.Element xSectionElement = jsoupDoc.getElementsByTag("x:section").first();
        xSectionElement.attr("xmlns", "custom");

        org.jsoup.nodes.Element svgElement = jsoupDoc.getElementsByTag("svg").first();
        svgElement.attr("xmlns", "custom");

        org.jsoup.nodes.Element pathElement = jsoupDoc.getElementsByTag("path").first();
        pathElement.attr("xmlns", "custom");

        org.jsoup.nodes.Element clipElement = jsoupDoc.getElementsByTag("clip").first();
        clipElement.attr("xmlns", "new custom");

        org.jsoup.nodes.Element pictureElement = jsoupDoc.getElementsByTag("picture").first();
        pictureElement.attr("xmlns", "custom");

        org.jsoup.nodes.Element imgElement = jsoupDoc.getElementsByTag("img").first();
        imgElement.attr("xmlns", "custom");

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertEquals("custom", htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        // inherits default namespace
        Node head = htmlEl.getFirstChild();
        assertEquals("custom", head.getNamespaceURI());
        assertEquals("head", head.getLocalName());
        assertEquals("head", head.getNodeName());

        Node epubTitle = htmlEl.getChildNodes().item(2).getChildNodes().item(3);
        assertEquals("Check", epubTitle.getTextContent());
        assertEquals("custom", epubTitle.getNamespaceURI());
        assertEquals("title", epubTitle.getLocalName());
        assertEquals("epub:title", epubTitle.getNodeName());

        Node xSection = epubTitle.getNextSibling().getNextSibling();
        assertEquals("custom", xSection.getNamespaceURI());
        assertEquals("section", xSection.getLocalName());
        assertEquals("x:section", xSection.getNodeName());

        // https://github.com/jhy/jsoup/issues/977
        // does not keep last set namespace
        Node svg = xSection.getNextSibling().getNextSibling();
        assertEquals("custom", svg.getNamespaceURI());
        assertEquals("svg", svg.getLocalName());
        assertEquals("svg", svg.getNodeName());

        Node path = svg.getChildNodes().item(1);
        assertEquals("custom", path.getNamespaceURI());
        assertEquals("path", path.getLocalName());
        assertEquals("path", path.getNodeName());

        Node clip = path.getChildNodes().item(1);
        assertEquals("new custom", clip.getNamespaceURI());
        assertEquals("clip", clip.getLocalName());
        assertEquals("clip", clip.getNodeName());
        assertEquals("456", clip.getTextContent());

        Node picture = svg.getNextSibling().getNextSibling();
        assertEquals("custom", picture.getNamespaceURI());
        assertEquals("picture", picture.getLocalName());
        assertEquals("picture", picture.getNodeName());

        Node img = picture.getFirstChild();
        assertEquals("custom", img.getNamespaceURI());
        assertEquals("img", img.getLocalName());
        assertEquals("img", img.getNodeName());

    }
    @Test
    public void test43() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test44() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);

        org.jsoup.nodes.Element modifiedElement = jsoupDoc.select("body").first();
        modifiedElement.attr("xmlns", "custom");

        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test45() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.select("title").remove();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertFalse(out.contains("<title>"));
    }
    @Test
    public void test46() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.select("body").remove();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertFalse(out.contains("<body>"));
    }
    @Test
    public void test47() {
        String html = "<html><head><title>W3c</title></head><body><p class='one' id=12>Text</p><!-- comment --><invalid>What</invalid></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        doc.select("script").remove();

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                        "</head><body><p class=\"one\" id=\"12\">Text</p><!-- comment --><invalid>What</invalid></body></html>"
        );
        assertEquals(expected, out);
    }
}