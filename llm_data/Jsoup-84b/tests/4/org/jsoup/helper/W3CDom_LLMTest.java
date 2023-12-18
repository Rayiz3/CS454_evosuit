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
        org.jsoup.nodes.Document in = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(in);

        assertNull(wDoc);
    }
    @Test
    public void test1() {
        org.jsoup.nodes.Document doc = org.jsoup.nodes.Document.createShell("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test2() {
        String html = "";

        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        Document w3Doc = new W3CDom().fromJsoup(doc);
        Node htmlEl = w3Doc.getFirstChild();

        assertNull(htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());
    }
    @Test
    public void test3() {
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        assertNull(wDoc);
    }
    @Test
    public void test4() {
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        assertEquals("", out);
    }
    @Test
    public void test5() {
        org.jsoup.nodes.Document jsoupDoc = null;

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        assertNull(doc);
    }
    @Test
    public void test6() {
        org.jsoup.nodes.Document jsoupDoc = null;
        
        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);

        assertNull(w3Doc);
    }
    @Test
    public void test7() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.setOutputSettings(new Document.OutputSettings().syntax(Document.OutputSettings.Syntax.xml).charset("UTF-8"));

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(null, wDoc.getDocumentURI() );
    }
    @Test
    public void test8() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.select("body").empty();

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
    public void test9() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        doc.outputSettings().charset("ISO-8859-1");

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
    public void test10() throws IOException {
        String html = "<html></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines("<html></html>");
        assertEquals(expected, out);
    }
    @Test
    public void test11() throws IOException {
        String html = "<html><!-- This is a comment --></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines("<html><!-- This is a comment --></html>");
        assertEquals(expected, out);
    }
@Test
public void test12() {
    Document doc = new Document();
    Element root = doc.createElement("root");

    W3CDom w3c = new W3CDom();
    org.jsoup.nodes.Document jsoupDoc = w3c.toJsoup(org.jsoup.parser.Parser.parse("<root/>", ""));
    Node source = jsoupDoc.childNodes().get(0);

    w3c.head(source, 0);

    String expected = "<" + root.getNodeName() + "/>";
    String actual = w3c.asString(doc);
    
    assertEquals(expected, actual);
}
@Test
public void test13() {
    org.jsoup.nodes.Document doc = new org.jsoup.nodes.Document("");
    
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String expected = "<html/>";
    String actual = w3c.asString(wDoc);
    
    assertEquals(expected, actual);
}
@Test
public void test14() {
    org.jsoup.nodes.Document doc = Jsoup.parse("");
    
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String expected = "<html/>";
    String actual = w3c.asString(wDoc);
    
    assertEquals(expected, actual);
}
@Test 
public void test15() {
    org.jsoup.nodes.Document doc = null;
    
    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String expected = "<html/>";
    String actual = w3c.asString(wDoc);
    
    assertEquals(expected, actual);
}
    @Test
    public void test16() {
        // Setup
        Element source = new Element("div");
        source.appendChild(new Element("p"));
        Element dest = new Element("a");
        dest.appendChild(new Element("span"));

        // Act
        tail(source, dest, 0);

        // Assert
        assertEquals(source, dest.getParentNode());
    }
    @Test
    public void test17() {
        // Setup
        Element source = new Element("div");
        source.appendChild(new Element("p"));
        TextNode dest = new TextNode("text");
        dest.setParentNode(new Element("a"));

        // Act
        tail(source, dest, 0);

        // Assert
        assertNotEquals(source, dest.getParentNode());
    }
    @Test
    public void test18() {
        // Setup
        Element source = new Element("div");
        source.appendChild(new Element("p"));
        Node dest = new Element("a");
        Stack<String> namespacesStack = new Stack<>();
        namespacesStack.push("namespace1");
        namespacesStack.push("namespace2");
        namespacesStack.push("namespace3");

        // Act
        tail(source, dest, 2);

        // Assert
        assertEquals(2, namespacesStack.size());
    }
    @Test
    public void test19() {
        // Setup
        Element source = new Element("div");
        source.appendChild(new Element("p"));
        Node dest = new Element("a");
        Stack<String> namespacesStack = new Stack<>();
        namespacesStack.push("namespace1");
        namespacesStack.push("namespace2");
        namespacesStack.push("namespace3");

        // Act
        tail(source, dest, -1);

        // Assert
        assertEquals(3, namespacesStack.size());
    }
    @Test
    public void test20() { // regression test for null key
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Node();
        Element el = new Element();

        // bypass exception by setting null key
        source.setKey(null);
        source.addValue("value");

        copyAttributes(source, el);

        assertFalse(el.hasAttributes()); // should not copy attribute
    }
    @Test
    public void test21() { // regression test for null value
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Node();
        Element el = new Element();

        // bypass exception by setting null value
        source.setKey("key");
        source.addValue(null);

        copyAttributes(source, el);

        assertFalse(el.hasAttributes()); // should not copy attribute
    }
    @Test
    public void test22() { // regression test for empty key
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Node();
        Element el = new Element();

        // bypass exception by setting empty key
        source.setKey("");
        source.addValue("value");

        copyAttributes(source, el);

        assertFalse(el.hasAttributes()); // should not copy attribute
    }
    @Test
    public void test23() { // regression test for non-ascii key
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Node();
        Element el = new Element();

        // bypass exception by setting non-ascii key
        source.setKey("key\u00ea");
        source.addValue("value");

        copyAttributes(source, el);

        assertFalse(el.hasAttributes()); // should not copy attribute
    }
    @Test
    public void test24() { // regression test for non-ascii value
        org.jsoup.nodes.Node source = new org.jsoup.nodes.Node();
        Element el = new Element();

        // bypass exception by setting non-ascii value
        source.setKey("key");
        source.addValue("value\u00ea");

        copyAttributes(source, el);

        assertTrue(el.hasAttributes()); // should copy attribute
        assertEquals("value\u00ea", el.getAttributeValue("key")); // attribute value should be copied correctly
    }
@Test
public void test25() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals(doc.location(), wDoc.getDocumentURI() );
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
}
@Test
public void test31() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals(doc.location(), wDoc.getDocumentURI() );
}
@Test
public void test32() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals(doc.location(), wDoc.getDocumentURI() );
}
@Test public void test33() {
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
public void test34() throws IOException {
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
public void test35() {
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
public void test36() throws IOException {
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
public void test37() {
    String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
    org.jsoup.nodes.Document jsoupDoc;
    jsoupDoc = Jsoup.parse(html);
    Element body = jsoupDoc.select("body").first();
    assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
    assertTrue(body.hasAttr("name\""));

    Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
}
@Test
public void test38() throws IOException {
    File in = ParseTest.getFile("/htmltests/google-ipod.html");
    org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

    W3CDom w3c = new W3CDom();
    Document wDoc = w3c.fromJsoup(doc);

    String out = w3c.asString(wDoc);
    assertEquals(doc.location(), wDoc.getDocumentURI() );
}
@Test public void test39() {
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
public void test40() throws IOException {
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
public void test41() {
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
public void test42() throws IOException {
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
    public void test44() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("\n"));
    }
    @Test
    public void test45() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("\n"));
    }
    @Test
    public void test46() {
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
        assertTrue(out.contains("\n"));
    }
    @Test
    public void test47()  {
        org.jsoup.nodes.Document doc = Jsoup.parse("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test48()  {
        String html = "<html><head></head><body><p>&copy; &amp; &gt;</p></body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("&copy; &amp; &gt;"));
    }
}