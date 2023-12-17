package org.jsoup.parser;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import static org.jsoup.nodes.Document.OutputSettings.Syntax;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Comment_LLMTest {
    @Test public void test0() {
        String xml = " ";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("#comment", nodes.get(0).nodeName());
    }
    @Test public void test1() {
        String xml = "<one src='/foo/'/>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
    }
    @Test public void test2() {
        String xml = "<one src=''>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("", nodes.get(0).attr("src"));
    }
    @Test public void test3() {
        String xml = "< ![ENTITY[ ]]>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("#cdata-section", nodes.get(0).nodeName());
        assertEquals(" ", nodes.get(0).wholeData());
    }
    @Test public void test4() {
        String xml = "<![CDATA[]]>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("#cdata-section", nodes.get(0).nodeName());
        assertEquals("", nodes.get(0).wholeData());
    }
    @Test
    public void test5() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test
    public void test6() {
        String xml = "<one src='/bar/' />Three<four />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Three", ((TextNode)nodes.get(1)).text());
    }
    @Test
    public void test7() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test8() {
        String xml = "<?xml version='1.1' encoding='ISO-8859-1' something='param'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1.1", decl.attr("version"));
        assertEquals("ISO-8859-1", decl.attr("encoding"));
        assertEquals("param", decl.attr("something"));
        assertEquals("version=\"1.1\" encoding=\"ISO-8859-1\" something=\"param\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1.1\" encoding=\"ISO-8859-1\" something=\"param\"?>", decl.outerHtml());
    }
    @Test public void test9() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test
    public void test10() {
        String xml = "<div id=1><![CDATA[\n<page><header>Test</header></page>]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<page><header>Test</header></page>", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<page><header>Test</header></page>]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<page><header>Test</header></page>", cdata.text());
    }
    @Test
    public void test11() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test12() {
        String xml = "<doc id=3 href='/foo'>Bar <br /><link>Three</link><link>Four</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"3\" href=\"/foo\">Bar <br /><link>Three</link><link>Four</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://foo.com/foo");
    }
    @Test
    public void test13() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test14() {
        // test: </value> closes Two, </bar> ignored
        String xml = "<doc><value>One<value>Two</value></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><value>One<value>Two</value>Three</value></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test15() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test16() {
        String html = "<?xml encoding='ISO-8859-1' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test17() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test18() {
        String xml = "<doc><value>One<value>Two</value></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><value>One<value>Two</value>Three</value></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test19() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test20() {
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test21() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test22() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test23() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test24() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<<\"; var b=\">>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <<\"; var b=\">>\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test public void test25() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test26() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><br></br>", "", parser);
        assertEquals("<div>\n test\n</div>\n<br>", document.html());
        // was failing -> toString() = "<div>\n test\n <br></br>\n</div>"
    }
    @Test
    public void test27() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test28() {
        String xml = "<TEST>One</CHECK><CHECK ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<TEST>One</CHECK><CHECK ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test29() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test30() {
        // html will force "<img>one</img>" to logically "<img />One<img />". XML should be stay "<img>one</img> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<img>one</img>");
        assertEquals("<img>one\n<img>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<img>one</img>", "", Parser.xmlParser());
        assertEquals("<img>one</img>", xmlDoc.html());
    }
    @Test public void test31() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test32() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test33() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test34() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><value>One<value>Two</value>Three</value></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test35() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test36() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test37() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test38() {
        String xml = "<CHECK id=1>Test</CHECK>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<check id=\"1\">Test</check>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test39() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test40() {
        String xml = "<Two>Two</Two>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements two = doc.select("Two");
        two.append("<One ID=1>One</One>");
        assertEquals("<Two>Two<One ID=\"1\">One</One></Two>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test41() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test42() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[    var a = 'test';]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("    var a = 'test';", doc.selectFirst("script").text());
    }
    @Test
    public void test43() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test44() {
        String xml = "<!DOCTYPE HTML><!-- a new comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a new comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test45() {
        String xml = "<!-- -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", TextUtil.stripNewlines(doc.html()));
        assertEquals("", doc.text());
    }
    @Test
    public void test46() {
        String xml = "<!--comment-->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        doc.outputSettings().prettyPrint(true);
        assertEquals("<!--\n comment\n-->", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test47() {
        String xml = "<!--comment-->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        doc.outputSettings().prettyPrint(false);
        assertEquals("<!--comment-->", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test48() {
        String xml = "<!-- comment -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        doc.outputSettings().prettyPrint(true);
        assertEquals("<!-- comment -->", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test49() {
        String xml = "<!-- -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        doc.outputSettings().prettyPrint(true);
        assertEquals("<!-- -->", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test50() {
        String xml = "<!--comment -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        doc.outputSettings().prettyPrint(true);
        assertEquals("<!--comment -->", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test51() {
        String xml = "<!-- comment -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<!-- comment -->", doc.html());
    }
    @Test
    public void test52() {
        String xml = "<!---->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<!---->", doc.html());
    }
@Test
public void test53() {
    String xml = "";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("", doc.outerHtml());
}
@Test
public void test54() {
    String xml = "This is a sentence.";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("This is a sentence.", doc.outerHtml());
}
@Test
public void test55() {
    String xml = "<tag>&lt;This&gt;</tag>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<tag>&lt;This&gt;</tag>", doc.outerHtml());
}
@Test
public void test56() {
    String xml = "<parent><child></child></parent>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<parent><child></child></parent>", doc.outerHtml());
}
@Test
public void test57() {
    String xml = "<tag attribute=\"value\"></tag>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<tag attribute=\"value\"></tag>", doc.outerHtml());
}
@Test
public void test58() {
    String xml = "<tag>Some text content</tag>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<tag>Some text content</tag>", doc.outerHtml());
}
@Test
public void test59() {
    String xml = "<root1></root1><root2></root2>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<root1></root1><root2></root2>", doc.outerHtml());
}
@Test
public void test60() {
    String xml = "<tag1></tag2>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<tag1></tag1>", doc.outerHtml());
}
@Test
public void test61() {
    String xml = "<tag>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<tag></tag>", doc.outerHtml());
}
    @Test
    public void test62() {
        // Test case 1: Empty XML declaration
        String xml1 = "<?xml version='1.0' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc1 = Jsoup.parse(xml1, "", Parser.xmlParser());
        XmlDeclaration decl1 = (XmlDeclaration) doc1.childNode(0);
        assertEquals("1.0", decl1.attr("version"));
        assertEquals("UTF-8", decl1.attr("encoding"));
        assertEquals("else", decl1.attr("something"));
        assertEquals("version=\"1.0\" encoding=\"UTF-8\" something=\"else\"", decl1.getWholeDeclaration());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" something=\"else\"?>", decl1.outerHtml());
    }
    @Test
    public void test63() {
        // Test case 2: Case-sensitive XML declaration
        String xml2 = "<?Xml version='1' encoding='UTF-8' something='else'?>";
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
        XmlDeclaration decl2 = (XmlDeclaration) doc2.childNode(0);
        assertEquals("1", decl2.attr("version"));
        assertEquals("UTF-8", decl2.attr("encoding"));
        assertEquals("else", decl2.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl2.getWholeDeclaration());
        assertEquals("<?Xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl2.outerHtml());
    }
    @Test
    public void test64() {
        // Test case 3: XML declaration missing the attributes
        String xml3 = "<?xml version='1.0'><val>One</val>";
        Document doc3 = Jsoup.parse(xml3, "", Parser.xmlParser());
        XmlDeclaration decl3 = (XmlDeclaration) doc3.childNode(0);
        assertEquals("version=\"1.0\"", decl3.getWholeDeclaration());
        assertEquals("<?xml version=\"1.0\"?>", decl3.outerHtml());
    }
    @Test
    public void test65() {
        // Test case 4: XML declaration within the body
        String html4 = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc4 = Jsoup.parse(html4, "", Parser.xmlParser());
        XmlDeclaration decl4 = (XmlDeclaration) doc4.childNode(0);
        assertEquals("encoding=\"UTF-8\"", decl4.getWholeDeclaration());
        assertEquals("<?xml encoding=\"UTF-8\"?>", decl4.outerHtml());
    }
    @Test
    public void test66() throws IOException, URISyntaxException {
        // Test case 5: XML declaration with a different charset encoding
        File xmlFile5 = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream5 = new FileInputStream(xmlFile5);
        Document doc5 = Jsoup.parse(inStream5, null, "http://example.com/", Parser.xmlParser());
        XmlDeclaration decl5 = (XmlDeclaration) doc5.childNode(0);
        assertEquals("ISO-8859-1", doc5.charset().name());
        assertEquals("version=\"1.0\" encoding=\"ISO-8859-1\"", decl5.getWholeDeclaration());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>", decl5.outerHtml());
    }
    @Test
    public void test67() {
        // Test case 6: XML declaration within a script tag
        String html6 = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc6 = Jsoup.parse(html6, "", Parser.xmlParser());
        XmlDeclaration decl6 = (XmlDeclaration) doc6.childNode(0);
        assertEquals("version=\"1.0\"", decl6.getWholeDeclaration());
        assertEquals("<?xml version=\"1.0\"?>", decl6.outerHtml());
    }
    @Test
    public void test68() {
        String xml = "<?xml something='else' version='1' encoding='UTF-8'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test69() {
        String xml = "<?xml Version='1' EncodiNg='UTF-8' Something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml Version=\"1\" EncodiNg=\"UTF-8\" Something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test70() {
        String xml = "<?xml encoding='UTF-8'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test71() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test72() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<??\"; var b=\"???>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--??\"; var b=\"??-->\"; </script>", doc.html());
    }
}