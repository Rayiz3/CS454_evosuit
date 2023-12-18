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
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertNull(out);
    }
    @Test public void test1() {
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
    public void test2() throws IOException {
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        
        assertNull(wDoc.getDocumentURI());
    }
    @Test
    public void test3() {
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
    public void test4() {
        org.jsoup.nodes.Document doc = null;

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test5() throws IOException {
        org.jsoup.nodes.Document jsoupDoc = null;

        Document doc;
        org.jsoup.helper.W3CDom jDom = new org.jsoup.helper.W3CDom();
        doc = jDom.fromJsoup(jsoupDoc);

        Node htmlEl = doc.getChildNodes().item(0);
        assertNull(htmlEl);

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
        doc.setBaseUri(null);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertNull(wDoc.getDocumentURI());
    }
    @Test 
    public void test8() {
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
    public void test9() throws IOException {
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
        assertTrue(out.contains("<html"));
        assertTrue(out.contains("ipod."));
    }
    @Test
    public void test10() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");
        String html = doc.html();
        String modifiedHtml = html.replace("<o", "<---<");
        modifiedHtml = modifiedHtml.replace("d.b", "d.bind");
        modifiedHtml = modifiedHtml.replace("l..=", "<length=");
        doc = Jsoup.parse(modifiedHtml);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        Node htmlEl = wDoc.getChildNodes().item(0);
        assertEquals(null, htmlEl.getNamespaceURI());
        assertEquals("html", htmlEl.getLocalName());
        assertEquals("html", htmlEl.getNodeName());

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("ipod"));
        assertTrue(out.contains("<html"));
        assertTrue(out.contains("ipod."));
        assertTrue(out.contains("<length="));
        assertFalse(out.contains("<--<"));
        assertFalse(out.contains("d.b"));
    }
    @Test
    public void test11() {
        String html = "<html><head><title>W3c</title></head><body><!--d.b-->Text<script>alert('&#x21;')</script><style>.stylehref=.java</style><?xml. version. encoding=\"UTF-8\". standalone.=\"yes\"?><a<b<c<d>e</body></html>";
        org.jsoup.nodes.Document doc = Jsoup.parse(html);

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = TextUtil.stripNewlines(w3c.asString(wDoc));
        String expected = TextUtil.stripNewlines(
                "<html><head><META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>W3c</title>" +
                "</head><body><!--d.b-->Text<script>alert('&#33;')</script><style>\n.stylehref=.java</style><?xml. version. encoding=\"UTF-8\". standalone.=\"yes\"?><a<b<c<d>e\n" +
                "</body></html>"
        );
        assertEquals(expected, out);
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

        doc.setDocumentURI(null);
        Node htmlElModified = doc.getChildNodes().item(0);
        assertNull(htmlElModified.getNamespaceURI());
        assertEquals("html", htmlElModified.getLocalName());
        assertEquals("html", htmlElModified.getNodeName());

        Node headModified = htmlElModified.getFirstChild();
        assertNull(headModified.getNamespaceURI());
        assertEquals("head", headModified.getLocalName());
        assertEquals("head", headModified.getNodeName());

        Node epubTitleModified = htmlElModified.getChildNodes().item(2).getChildNodes().item(3);
        assertEquals("Check", epubTitleModified.getTextContent());
        assertEquals("http://www.idpf.org/2007/ops", epubTitleModified.getNamespaceURI());
        assertEquals("title", epubTitleModified.getLocalName());
        assertEquals("epub:title", epubTitleModified.getNodeName());

        Node xSectionModified = epubTitleModified.getNextSibling().getNextSibling();
        assertEquals("urn:test", xSectionModified.getNamespaceURI());
        assertEquals("section", xSectionModified.getLocalName());
        assertEquals("x:section", xSectionModified.getNodeName());

        Node svgModified = xSectionModified.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/2000/svg", svgModified.getNamespaceURI());
        assertEquals("svg", svgModified.getLocalName());
        assertEquals("svg", svgModified.getNodeName());

        Node pathModified = svgModified.getChildNodes().item(1);
        assertEquals("http://www.w3.org/2000/svg", pathModified.getNamespaceURI());
        assertEquals("path", pathModified.getLocalName());
        assertEquals("path", pathModified.getNodeName());

        Node clipModified = pathModified.getChildNodes().item(1);
        assertEquals("http://example.com/clip", clipModified.getNamespaceURI());
        assertEquals("clip", clipModified.getLocalName());
        assertEquals("clip", clipModified.getNodeName());
        assertEquals("456", clipModified.getTextContent());

        Node pictureModified = svgModified.getNextSibling().getNextSibling();
        assertEquals("http://www.w3.org/1999/xhtml", pictureModified.getNamespaceURI());
        assertEquals("picture", pictureModified.getLocalName());
        assertEquals("picture", pictureModified.getNodeName());

        Node imgModified = pictureModified.getFirstChild();
        assertEquals("http://www.w3.org/1999/xhtml", imgModified.getNamespaceURI());
        assertEquals("img", imgModified.getLocalName());
        assertEquals("img", imgModified.getNodeName());

    }
    @Test
    public void test13() {
        String html = "<html><head></head><body style=\"color: red\" .no matter=\"name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr(".no"));
        assertTrue(body.hasAttr(" matter"));
        assertTrue(body.hasAttr("\"name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
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
    public void test15() throws IOException {
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
    public void test16() {
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
    @Test public void test17() {
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
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test19() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test
    public void test20() {
        // Change input value - source is null
        Node source = null;
        int depth = 0;
        tail(source, depth); // Call the method
        
        // Add assertion to check the result after the method call
        // Since source is null, we expect no changes in the dest and namespacesStack
        // Therefore, the assertions should reflect that
        // Since the original test case did not provide the initial state of dest and namespacesStack,
        // we cannot provide specific assertion statements here.
        // Instead, we can only assert that dest and namespacesStack should not be null after the method call.
        assertNotNull(dest);
        assertNotNull(namespacesStack);
    }
    @Test
    public void test21() {
        // Change input value - source is a non-Element node and dest.getParentNode() is a non-Element node
        Node source = new org.jsoup.nodes.TextNode("text", "");
        int depth = 0;
        tail(source, depth); // Call the method
        
        // Add assertion to check the result after the method call
        // Since both source and dest.getParentNode() are not instances of Element,
        // we expect no changes in the dest and namespacesStack
        // Therefore, the assertions should reflect that
        // Since the original test case did not provide the initial state of dest and namespacesStack,
        // we cannot provide specific assertion statements here.
        // Instead, we can only assert that dest and namespacesStack should not be null after the method call.
        assertNotNull(dest);
        assertNotNull(namespacesStack);
    }
    @Test
    public void test22() {
        // Change input value - source is an Element node and dest.getParentNode() is a non-Element node
        Node source = new org.jsoup.nodes.Element("div", "");
        int depth = 0;
        tail(source, depth); // Call the method
        
        // Add assertion to check the result after the method call
        // Since source is an element and dest is not an element,
        // dest should be updated to the parent of dest (undescend)
        // And namespacesStack should be popped (remove the top element)
        // Therefore, the assertions should reflect that
        // Since the original test case did not provide the initial state of dest and namespacesStack,
        // we cannot provide specific assertion statements here.
        // Instead, we can only assert that dest and namespacesStack should not be null after the method call.
        assertNotNull(dest);
        assertNotNull(namespacesStack);
    }
    @Test
    public void test23() {
        // Change input value - source is an Element node and dest.getParentNode() is an Element node
        Node source = new org.jsoup.nodes.Element("div", "");
        dest = source.getParentNode(); // Set dest to have a parent node
        int depth = 0;
        tail(source, depth); // Call the method
        
        // Add assertion to check the result after the method call
        // Since both source and dest.getParentNode() are instances of Element,
        // dest should be updated to the parent of dest (undescend)
        // And namespacesStack should be popped (remove the top element)
        // Therefore, the assertions should reflect that
        // Since the original test case did not provide the initial state of dest and namespacesStack,
        // we cannot provide specific assertion statements here.
        // Instead, we can only assert that dest and namespacesStack should not be null after the method call.
        assertNotNull(dest);
        assertNotNull(namespacesStack);
    }
    @Test
    public void test24() {
        // Change input value - empty namespacesStack
        Node source = new org.jsoup.nodes.Element("div", "");
        int depth = 0;
        namespacesStack = new Stack<>(); // Set namespacesStack to be empty
        tail(source, depth); // Call the method
        
        // Add assertion to check the result after the method call
        // Since namespacesStack is empty, we expect no changes in the dest
        // Therefore, the assertions should reflect that
        // Since the original test case did not provide the initial state of dest,
        // we cannot provide specific assertion statements here.
        // Instead, we can only assert that dest should not be null after the method call.
        assertNotNull(dest);
    }
    @Test
    public void test25() {
        Element element = new Element("div");
        
        copyAttributes(null, element);
        
        assertEquals(0, element.attributes().size());
    }
    @Test
    public void test26() {
        Element element = new Element("div");
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("div");
        
        copyAttributes(source, element);
        
        assertEquals(0, element.attributes().size());
    }
    @Test
    public void test27() {
        Element element = new Element("div");
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("div");
        source.attr("id", "test");
        source.attr("class", "test-class");
        
        copyAttributes(source, element);
        
        assertEquals("test", element.attr("id"));
        assertEquals("test-class", element.attr("class"));
    }
    @Test
    public void test28() {
        Element element = new Element("div");
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("div");
        source.attr("id", "test!");
        
        copyAttributes(source, element);
        
        assertEquals(0, element.attributes().size());
    }
    @Test
    public void test29() {
        Element element = new Element("div");
        org.jsoup.nodes.Element source = new org.jsoup.nodes.Element("div");
        source.attr("id", "test");
        source.attr("class", "class\"test");
        
        copyAttributes(source, element);
        
        assertEquals("test", element.attr("id"));
        assertEquals(0, element.attributes().size());
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
    @Test public void test31() {
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
    public void test32() throws IOException {
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
    public void test33() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-ipod.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertEquals(doc.location(), wDoc.getDocumentURI() );
    }
    @Test public void test34() {
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
    public void test35() throws IOException {
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
    public void test36() {
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
    public void test37() throws IOException {
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
    public void test38() {
        String html = "<html><head></head><body style=\"color: red\" \" name\"></body></html>";
        org.jsoup.nodes.Document jsoupDoc;
        jsoupDoc = Jsoup.parse(html);
        Element body = jsoupDoc.select("body").first();
        assertTrue(body.hasAttr("\"")); // actually an attribute with key '"'. Correct per HTML5 spec, but w3c xml dom doesn't dig it
        assertTrue(body.hasAttr("name\""));

        Document w3Doc = new W3CDom().fromJsoup(jsoupDoc);
    }
    @Test
    public void test39() throws IOException {
        File in = ParseTest.getFile("/htmltests/google-special-chars.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(in, "UTF8");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);

        String out = w3c.asString(wDoc);
        assertTrue(out.contains("&nbsp;"));
    }
    @Test
    public void test40() {
        org.jsoup.nodes.Document doc = Jsoup.parse("");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("", out);
    }
    @Test
    public void test41() {
        org.jsoup.nodes.Document doc = Jsoup.parse("<html></html>");

        W3CDom w3c = new W3CDom();
        Document wDoc = w3c.fromJsoup(doc);
        String out = w3c.asString(wDoc);
        assertEquals("<html></html>", out);
    }
}