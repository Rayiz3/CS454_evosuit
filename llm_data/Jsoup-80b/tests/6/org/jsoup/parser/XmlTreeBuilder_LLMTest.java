package org.jsoup.parser;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
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

public class XmlTreeBuilder_LLMTest {
    @Test
    public void test0() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test1() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test2() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());
        // Changing the CDATA content
        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test3() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
        // Changing the xml declaration version
        xml = "<?xml version='2.0'><val>One</val>";
        doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test4() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
        // Removing the encoding attribute
        xml = "<?xml version='1' something='else'?><val>One</val>";
        doc = Jsoup.parse(xml, "", Parser.xmlParser());
        decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test5() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
        // Changing the attribute value
        html = "<img src=asdf onerror=\"alert(2)\" x=";
        xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(2)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test6() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
        // Changing the URL of the input stream
        inStream = new FileInputStream(xmlFile);
        doc = Jsoup.parse(inStream, null, "http://bar.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test7() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());
        // Changing the self-closing tag
        htmlDoc = Jsoup.parse("<br>two</br>");
        assertEquals("<br>two\n<br>", htmlDoc.body().html());
    }
    @Test public void test8() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
        // Changing the encoding attribute
        html = "<?xml encoding='ISO-8859-1' ?><body>One</body><!-- comment -->";
        doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test9() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
        // Changing the tag name to lower case
        xml = "<check>One</check><TEST ID=1>Check</TEST>";
        doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<check>One</check><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test10() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
        // Changing the CDATA content
        xml = "<div id=1><![CDATA[\n<html>\n <bar><&amp;]]></div>";
        doc = Jsoup.parse(xml, "", Parser.xmlParser());

        div = doc.getElementById("1");
        assertEquals("<html>\n <bar><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <bar><&amp;]]>\n</div>", div.outerHtml());

        cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <bar><&amp;", cdata.text());
    }
    @Test public void test11() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
        // Changing the xml fragment
        xml = "<one src='/bar/' />Two<three><four /></three>";
        nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test12() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
        // Changing the input
        doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test13() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
        // Changing the tag name to lower case
        xml = "<test ID=1>Check</TEST>";
        doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test14() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // Changing the closing tag name
        document = Jsoup.parse("<div>test</div><p></P>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    }
    @Test
    public void test15() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
        // Changing the encoding attribute
        inStream = new FileInputStream(xmlFile);
        doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test16() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
        // Changing the URL
        doc = Jsoup.parse(xml, "http://bar.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test17() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml.toLowerCase(), "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test18() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml.replaceFirst(">", "> "), "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test19() {
        String xml = "<!DOCTYPE HTML><!-- a comment --><!-- another comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment --><!-- another comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test20() {
        // test: </val> closes Two and One, <val>Three</val> outer
        String xml = "<doc><val>One<val>Two</val></val>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test21() {
        String xml = "<val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test22() {
        String xml = "<doc id=2 href='bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test23() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml.replace(" ", ""), "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test24() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test25() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test26() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should stay "<br>one</br>" -- don't recognize a tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test27() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?><body> One </body><!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#comment", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test28() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test29() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div>";
        Document doc = Jsoup.parse(xml.replaceFirst(">", "> "), "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test public void test30() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test31() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test32() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test33() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test34() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml.replaceFirst("<", "< "), "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test35() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test36() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test37() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());
        // Regression Test 2: Removing whitespace from CData
        assertEquals("// foo(); //", doc.selectFirst("script").text());
    }
    @Test public void test38() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[foo();//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());
        assertEquals("// foo();//", doc.selectFirst("script").text());
    }
    @Test
    public void test39() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test40() {
        String xml = "<!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test41() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test42() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test43() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test44() {
        String xml = "<val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test45() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test46() {
        String xml = "<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test47() {
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
    public void test48() {
        String xml = "<?xml version='2' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test49() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test50() {
        String html = "<IMG src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<IMG src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test51() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test52() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test-regression1.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test53() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test54() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br />one</br>", "", Parser.htmlParser());
        assertEquals("<br />one\n<br />", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test55() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test public void test56() {
        String html = "<?XML encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?XML encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test57() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test58() {
        String xml = "<check>One</check><test ID=1>Check</test>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<check>One</check><test ID=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test59() {
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
    @Test public void test60() {
        String xml = "<div id=1>\n<html>\n <foo><&amp;</div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("\n<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\">\n<html>\n <foo><&amp;</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("_", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test63() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test64() {
        String xml = "<test id=1>Check</test>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test65() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test public void test66() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</div><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test67() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test68() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-regression1.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test69() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test70() {
        String xml = "<doc><val>One<val>Two</val>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test71() {
        String xml = "<?xml version='2' encoding='UTF-16' something='new'?>"; // Change input values
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"new\"?>", doc.outerHtml());
    }
    @Test public void test72() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>"; // Change input values
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test73() {
        String xml = "<!DOCTYPE HTML><!-- another comment -->One <foo />Two"; // Change input values
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->One <foo />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test74() {
        // test: </qux> closes Two, </bar> ignored
        String xml = "<doc><qux>One<qux>Two</qux></bar>Three</doc>"; // Change input values
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc><qux>One<qux>Two</qux>Three</qux></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test75() {
        String xml = "<?xml version='2.0'><qux>One</qux>"; // Change input values
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("qux").text());
    }
    @Test
    public void test76() {
        String xml = "<doc id=3 href='/baz'>Foo <br /><link>One</link><link>Two</link></doc>"; // Change input values
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc id=\"3\" href=\"/baz\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://bar.com/baz");
    }
    @Test
    public void test77() {
        String xml = "<?xml version='2' encoding='UTF-16' something='new'?><qux>One</qux>"; // Change input values
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-16", decl.attr("encoding"));
        assertEquals("new", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-16\" something=\"new\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-16\" something=\"new\"?>", decl.outerHtml());
    }
    @Test
    public void test78() {
        String html = "<img src=qwer onerror=\"alert(2)\" y="; // Change input values
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"qwer\" onerror=\"alert(2)\" y=\"\" />", xmlDoc.html());
    }
    @Test
    public void test79() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://baz.com", Parser.xmlParser());
        assertEquals("<doc><qux>One<qux>Two</qux>Three</qux></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test80() {
        // html will force "<qux>one</qux>" to logically "<qux />One<qux />". XML should stay "<qux>one</qux> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<qux>one</qux>"); // Change input values
        assertEquals("<qux>one\n<qux></qux></qux>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<qux>one</qux>", "", Parser.xmlParser());
        assertEquals("<qux>one</qux>", xmlDoc.html());
    }
    @Test public void test81() {
        String html = "<?xml encoding='UTF-16' ?><body>One</body><!-- another comment -->"; // Change input values
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> One </body> <!-- another comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test82() {
        String xml = "<CHECK>One</CHECK><TEST ID=2>Check</TEST>"; // Change input values
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test83() {
        String xml = "<div id=2><![CDATA[\n<html>\n <bar><&amp;]]></div>"; // Change input values
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("2");
        assertEquals("<html>\n <bar><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"2\"><![CDATA[\n<html>\n <bar><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <bar><&amp;", cdata.text());
    }
    @Test public void test84() {
        String xml = "<two src='/bar/' />Three<four><qux /></four>"; // Change input values
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.org/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.org/bar/", nodes.get(0).absUrl("src"));
        assertEquals("two", nodes.get(0).nodeName());
        assertEquals("Three", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test85() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test86() {
        String xml = "<TEST ID=2>Check</TEST>"; // Change input values
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test87() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test88() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.org/", Parser.xmlParser());
        assertEquals("ISO-8859-2", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-2\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test89() {
        String xml = "<doc><qux>One<qux>Two</qux></bar>Three</doc>"; // Change input values
        Document doc = Jsoup.parse(xml, "http://baz.com/", Parser.xmlParser());
        assertEquals("<doc><qux>One<qux>Two</qux>Three</qux></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test90() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML VERSION=\"1\" ENCODING=\"UTF-8\" SOMETHING=\"ELSE\"?>", doc.outerHtml()); // Change the expected value
    }
    @Test public void test91() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());
    }
    @Test
    public void test92() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test93() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test94() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test95() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test96() {
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
    public void test97() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test98() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test99() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test100() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test101() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test102() {
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
    @Test public void test103() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test104() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test105() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test106() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test107() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test108() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test109() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", doc.outerHtml());
    }
    @Test
    public void test110() {
        String xml = "a";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("a", doc.outerHtml());
    }
    @Test
    public void test111() {
        String xml = "<doc>One<&><&gt;Two</doc>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<doc>One<&><&gt;Two</doc>", doc.outerHtml());
    }
    @Test
    public void test112() {
        String xml = "<123abc>One</123abc>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<123abc>One</123abc>", doc.outerHtml());
    }
    @Test
    public void test113() {
        String xml = "<doc>One";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<doc>One</doc>", doc.outerHtml());
    }
@Test
public void test114() {
    Node node = null;
    insertNode(node);
    // Assert that currentElement().appendChild(node) is not executed
    // Assert any other expected behavior
}
@Test
public void test115() {
    Node node = new Node(""); // Create an empty node
    insertNode(node);
    // Assert any expected behavior
}
@Test
public void test116() {
    Node node = new Node("test"); // Create a single node
    insertNode(node);
    // Assert any expected behavior
}
@Test
public void test117() {
    Node child1 = new Node("child1");
    Node child2 = new Node("child2");
    Node node = new Node("parent");
    node.appendChild(child1);
    node.appendChild(child2);
    insertNode(node);
    // Assert any expected behavior
}
@Test
public void test118() {
    Node node = new Node("test");
    node.attr("attribute", "value");
    insertNode(node);
    // Assert any expected behavior
}
    @Test
    public void test119() {
        String xml = "<?XML version='2' encoding='UTF-16' something='different'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"different\"?>", doc.outerHtml());
    }
    @Test public void test120() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test121() {
        String xml = "<!DOCTYPE HTML><!-- some comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<!DOCTYPE HTML><!-- some comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test122() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>Three<val>Four</val></bar>Five</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc><val>Three<val>Four</val>Five</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test123() {
        String xml = "<?xml version='2.1'><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("val").text());
    }
    @Test
    public void test124() {
        String xml = "<doc id=3 href='/baz'>Bar <br /><link>Two</link><link>Three</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc id=\"3\" href=\"/baz\">Bar <br /><link>Two</link><link>Three</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://bar.com/baz");
    }
    @Test
    public void test125() {
        String xml = "<?xml version='2' encoding='UTF-8' something='different'?><val>Three</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("different", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-8\" something=\"different\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-8\" something=\"different\"?>", decl.outerHtml());
    }
    @Test
    public void test126() {
        String html = "<img src=querty onerror=\"alert(2)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"querty\" onerror=\"alert(2)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test127() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://bar.com", Parser.xmlParser());
        assertEquals("<doc><val>Three<val>Four</val>Five</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test128() {
        // html will force "<br>two</br>" to logically "<br />Two<br />". XML should be stay "<br>two</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>two</br>");
        assertEquals("<br>two\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>two</br>", "", Parser.xmlParser());
        assertEquals("<br>two</br>", xmlDoc.html());
    }
    @Test public void test129() {
        String html = "<?xml encoding='UTF-8' ?><body>Two</body><!-- some comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> Two </body> <!-- some comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test130() {
        String xml = "<CHECK>Three</CHECK><TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Three</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test131() {
        String xml = "<div id=2><![CDATA[\n<html>\n <foo><&amp;Q>]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("2");
        assertEquals("\n<html>\n <foo><&amp;Q>", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"2\"><![CDATA[\n<html>\n <foo><&amp;Q>]]></div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;Q>", cdata.text());
    }
    @Test public void test132() {
        String xml = "<one src='/qux/' />Three<four><five /></four>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.org/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.org/qux/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Three", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test133() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test134() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test135() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test136() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.org/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test137() {
        String xml = "<doc><val>Three<val>Four</val></bar>Five</doc>";
        Document doc = Jsoup.parse(xml, "http://bar.com/", Parser.xmlParser());
        assertEquals("<doc><val>Three<val>Four</val>Five</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test138() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertNotEquals(xml, doc.outerHtml());
    }
    @Test public void test139() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertNotEquals(xml, doc.outerHtml());
    }
    @Test
    public void test140() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertNotEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test141() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertNotEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test142() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertNotEquals("One", doc.select("val").text());
    }
    @Test
    public void test143() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertNotEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertNotEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test144() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertNotEquals("1", decl.attr("version"));
        assertNotEquals("UTF-8", decl.attr("encoding"));
        assertNotEquals("else", decl.attr("something"));
        assertNotEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertNotEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test145() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertNotEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test146() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertNotEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test147() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertNotEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertNotEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test148() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertNotEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertNotEquals("#declaration", doc.childNode(0).nodeName());
        assertNotEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test149() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertNotEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test150() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertNotEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test151() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertNotEquals("ISO-8859-1", doc.charset().name());
        assertNotEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test152() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertNotEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test153() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test154() {
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test155() {
        String xml = "<?XML version='1' encoding='ASCII' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"ASCII\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test156() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test public void test157() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test public void test158() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  fooBar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  fooBar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test159() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test160() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->Three <qux />Four";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->Three <qux />Four",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test161() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->Five <qux />Six";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->Five <qux />Six",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test162() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test163() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Four</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Four</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test164() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Five</val></bar>Six</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Five</val>Six</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test165() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test166() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test167() {
        String xml = "<?xml version='1.0'><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("val").text());
    }
    @Test
    public void test168() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test169() {
        String xml = "<doc id=3 href='/bar'>Baz <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"3\" href=\"/bar\">Baz <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test170() {
        String xml = "<doc id=2 href='/baz'>FooBar <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/baz\">FooBar <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/baz");
    }
    @Test
    public void test171() {
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
    public void test172() {
        String xml = "<?xml version='2' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test173() {
        String xml = "<?xml version='1' encoding='ASCII' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("ASCII", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"ASCII\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"ASCII\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test174() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test175() {
        String html = "<img src=qwerty onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"qwerty\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test176() {
        String html = "<img src=zxcvb onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"zxcvb\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test177() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test178() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test179() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test3.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test180() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test181() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>test</br>");
        assertEquals("<br>test\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>test</br>", "", Parser.xmlParser());
        assertEquals("<br>test</br>", xmlDoc.html());
    }
    @Test
    public void test182() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>foo</br>");
        assertEquals("<br>foo\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>foo</br>", "", Parser.xmlParser());
        assertEquals("<br>foo</br>", xmlDoc.html());
    }
    @Test public void test183() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test public void test184() {
        String html = "<?xml encoding='ASCII' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"ASCII\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test public void test185() {
        String html = "<?xml encoding='UTF-16' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test186() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test187() {
        String xml = "<CHECK>Two</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test188() {
        String xml = "<CHECK>One</CHECK><TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test189() {
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
    @Test public void test190() {
        String xml = "<div id=1><![CDATA[\n<html>\n <bar><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <bar><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <bar><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <bar><&amp;", cdata.text());
    }
    @Test public void test191() {
        String xml = "<div id=1><![CDATA[\n<html>\n <fooBar><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <fooBar><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <fooBar><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <fooBar><&amp;", cdata.text());
    }
    @Test public void test192() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test193() {
        String xml = "<three src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("three", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test194() {
        String xml = "<one src='/bar/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test195() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test public void test196() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test public void test197() {
        Document doc = Jsoup.parse("z", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test198() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test199() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test200() {
        String xml = "<TEST ID=1>Verify</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Verify</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test201() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    }
    @Test public void test202() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>check</DIV><p></p>", "", parser);
        assertEquals("<div>\n check\n</div>\n<p></p>", document.html());
    }
    @Test public void test203() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>verify</DIV><p></p>", "", parser);
        assertEquals("<div>\n verify\n</div>\n<p></p>", document.html());
    }
    @Test
    public void test204() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test205() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-16", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test206() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset3.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test207() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test208() {
        String xml = "<doc><val>Four<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>Four<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test209() {
        String xml = "<doc><val>Five<val>Six</val></bar>Seven</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>Five<val>Six</val>Seven</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test210() {
        String xml = "<?XML version='2' encoding='UTF-16' something='different'?>"; // Changed version number and encoding
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"different\"?>", doc.outerHtml());
    }
    @Test public void test211() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>"; // Changed javascript code
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test212() {
        String xml = "<!DOCTYPE HTML><!-- a different comment -->One <baz />Two"; // Changed comment content and tag name
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<!DOCTYPE HTML><!-- a different comment -->One <baz />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test213() {
        // test: </quz> closes Two, </bar> ignored
        String xml = "<doc><quz>One<quz>Two</quz></bar>Three</doc>"; // Changed closing tag names
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<doc><quz>One<quz>Two</quz>Three</quz></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test214() {
        String xml = "<?xml version='2.0'><val>Two</val>"; // Changed version number
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("val").text());
    }
    @Test
    public void test215() {
        String xml = "<doc id=3 href='/hello'>Bar <br /><link>Three</link><link>Four</link></doc>"; // Changed id, href, and content
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<doc id=\"3\" href=\"/hello\">Bar <br /><link>Three</link><link>Four</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://example.com/hello");
    }
    @Test
    public void test216() {
        String xml = "<?xml version='2' encoding='UTF-16' something='different'?><val>Two</val>"; // Changed version number, encoding, and something attribute
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-16", decl.attr("encoding"));
        assertEquals("different", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-16\" something=\"different\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-16\" something=\"different\"?>", decl.outerHtml());
    }
    @Test
    public void test217() {
        String html = "<img src=abcd onerror=\"alert('Hello')\" y="; // Changed src and onerror attributes
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"abcd\" onerror=\"alert('Hello')\" y=\"\" />", xmlDoc.html());
    }
    @Test
    public void test218() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test219() {
        // html will force "<hr>Approx</hr>" to logically "<hr />Approx<hr />". XML should be stay "<hr>Approx</hr> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<hr>Approx</hr>"); // changed tag name and content
        assertEquals("<hr>Approx\n<hr>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<hr>Approx</hr>", "", Parser.xmlParser());
        assertEquals("<hr>Approx</hr>", xmlDoc.html());
    }
    @Test
    public void test220() {
        String html = "<?xml encoding='UTF-16' ?><body>Two</body><!-- different comment -->"; // Changed encoding and comment content
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> Two </body> <!-- different comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test221() {
        String xml = "<CHECK>Two</CHECK><TEST ID=2>Check</TEST>"; // Changed content, ID value, and tag name
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test222() {
        String xml = "<div id=2><![CDATA[\n<html>\n <bar><&amp;]]></div>"; // Changed content and tag name
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("2");
        assertEquals("<html>\n <bar><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"2\"><![CDATA[\n<html>\n <bar><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <bar><&amp;", cdata.text());
    }
    @Test public void test223() {
        String xml = "<one src='/bar/' />Two<three><four /></three>"; // Changed one attribute value, tag name, and three content
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test224() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test225() {
        String xml = "<TEST ID=2>Check</TEST>"; // Changed ID value and tag name
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test226() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>check</DIV><p></p>", "", parser);
        assertEquals("<div>\n check\n</div>\n<p></p>", document.html());
    }
    @Test
    public void test227() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-16", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test228() {
        String xml = "<doc><val>Two<val>Three</val></bar>Thirty</doc>"; // Changed content, tag name, and closing tag name
        Document doc = Jsoup.parse(xml, "http://example.com/", Parser.xmlParser());
        assertEquals("<doc><val>Two<val>Three</val>Thirty</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
@Test public void test229() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
}
@Test public void test230() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
}
@Test public void test231() {
    // test: </val> closes Two, </bar> ignored
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test public void test232() {
    // test: </val> closes Two, </baz> ignored
    String xml = "<doc><val>One<val>Two</val></baz>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test233() {
    String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
}
@Test
public void test234() {
    String xml = "<doc id=1 href='/foo'>Bar <br /><link>Two</link><link>Three</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"1\" href=\"/foo\">Bar <br /><link>Two</link><link>Three</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("1").absUrl("href"), "http://foo.com/foo");
}
@Test
public void test235() {
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
public void test236() {
    String xml = "<?xml version='2' encoding='UTF-16' something='other'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("2", decl.attr("version"));
    assertEquals("UTF-16", decl.attr("encoding"));
    assertEquals("other", decl.attr("something"));
    assertEquals("version=\"2\" encoding=\"UTF-16\" something=\"other\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"2\" encoding=\"UTF-16\" something=\"other\"?>", decl.outerHtml());
}
@Test
public void test237() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test238() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-data.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
    assertEquals("<doc><val>a<val>b</val>c</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test239() {
    // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<br>one</br>");
    assertEquals("<br>one\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
    assertEquals("<br>one</br>", xmlDoc.html());
}
@Test public void test240() {
    String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test public void test241() {
    String html = "<?xml version='1' encoding='UTF-16' ?><content>Two</content><!-- another comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml version=\"1\" encoding=\"UTF-16\"?> <content> Two </content> <!-- another comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test242() {
    String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test243() {
    String xml = "<check>Two</check><test id=2>Verify</test>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<check>Two</check><test id=\"2\">Verify</test>", TextUtil.stripNewlines(doc.html()));
}
@Test public void test244() {
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
@Test public void test245() {
    String xml = "<div id=2><![CDATA[\n<data>\n <bar><&amp;]]></div>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

    Element div = doc.getElementById("2");
    assertEquals("<data>\n <bar><&amp;", div.text());
    assertEquals(0, div.children().size());
    assertEquals(1, div.childNodeSize()); // no elements, one text node

    assertEquals("<div id=\"2\"><![CDATA[\n<data>\n <bar><&amp;]]>\n</div>", div.outerHtml());

    CDataNode cdata = (CDataNode) div.textNodes().get(0);
    assertEquals("\n<data>\n <bar><&amp;", cdata.text());
}
@Test public void test246() {
    String xml = "<one src='/foo/' />Two<three><four /></three>";
    List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
    assertEquals(3, nodes.size());

    assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
    assertEquals("one", nodes.get(0).nodeName());
    assertEquals("Two", ((TextNode)nodes.get(1)).text());
}
@Test public void test247() {
    String xml = "<three><five src='/foo/' /></three>Four<one><eight /></one>";
    List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
    assertEquals(4, nodes.size());

    assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
    assertEquals("five", nodes.get(0).nodeName());
    assertEquals("Four", ((TextNode)nodes.get(1)).text());
}
@Test
public void test248() {
    String xml = "<TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test249() {
    String xml = "<test id=2>Skip</test>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"2\">Skip</test>", TextUtil.stripNewlines(doc.html()));
}
@Test public void test250() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test public void test251() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<section>content</SECTION><header></header>", "", parser);
    assertEquals("<section>\n content\n</section>\n<header></header>", document.html());
    // was failing -> toString() = "<section>\n content\n <header>\n</header></section>"
}
@Test
public void test252() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("ISO-8859-1", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
        TextUtil.stripNewlines(doc.html()));
}
@Test
public void test253() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-encoding.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("UTF-16BE", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16BE\"?>\n<page>øααø</page>",
        TextUtil.stripNewlines(doc.html()));
}
@Test
public void test254() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test255() {
    String xml = "<doc><val>One<val>Two</val></baz>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
    @Test public void test256() {
        String xml = "";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
    @Test public void test257() {
        String xml = "<one src='/foo/' >Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
    @Test public void test258() {
        String xml = "<empty></empty>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
}