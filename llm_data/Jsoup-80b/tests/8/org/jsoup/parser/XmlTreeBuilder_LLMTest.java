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
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test1() {
        String xml = "<?XML version='1' encoding='ISO-8859-1' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"ISO-8859-1\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test2() {
        String xml = "<?XML version='1' encoding='UTF-8' something='new'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"new\"?>", doc.outerHtml());
    }
    @Test
    public void test3() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test4() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test5() {
        String xml = "<?xml version='1.0'><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("val").text());
    }
    @Test
    public void test6() {
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
    public void test7() {
        String xml = "<?xml version='1' encoding='ISO-8859-1' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("ISO-8859-1", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"ISO-8859-1\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"ISO-8859-1\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test8() {
        String html = "<?xml encoding='ISO-8859-1' ?><body>Two</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> Two </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test9() {
        String xml = "<Check>One</Check><Test ID=1>Check</Test>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<Check>One</Check><Test ID=\"1\">Check</Test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test10() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test11() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<DIV>test</DIV><P></P>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    }
    @Test
    public void test12() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test13() {
        String xml = "<doc><val>One<val>Three</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Three</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test14() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test15() {
        String xml = "<?xml?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml?>", doc.outerHtml());
    }
    @Test
    public void test16() {
        String xml = "<root>One</root>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<root>One</root>", doc.outerHtml());
    }
    @Test
    public void test17() {
        String xml = "<?xml version='1'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\"?>", doc.outerHtml());
    }
    @Test
    public void test18() {
        String xml = "<?xml version='1' encoding='ISO-8859-1'?><root>One</root>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"ISO-8859-1\"?><root>One</root>", doc.outerHtml());
    }
    @Test
    public void test19() {
        String xml = "<?xml version='1.1' encoding='UTF-8'?><root>One</root>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1.1\" encoding=\"UTF-8\"?><root>One</root>", doc.outerHtml());
    }
    @Test
    public void test20() {
        String xml = "<?xml something='ok'?><root>One</root>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml something=\"ok\"?><root>One</root>", doc.outerHtml());
    }
    @Test
    public void test21() {
        String xml = "<?xml version='2' encoding='UTF-16' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test22() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test23() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->Two <baz />Three";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->Two <baz />Three",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test24() {
        // test: </val> closes Three, </baz> ignored
        String xml = "<doc><val>One<val>Two</val></baz>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val></baz>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test25() {
        String xml = "<?xml version='2.0'><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("val").text());
    }
    @Test
    public void test26() {
        String xml = "<doc id=3 href='/baz'>Bar <br /><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"3\" href=\"/baz\">Bar <br /><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://foo.com/baz");
    }
    @Test
    public void test27() {
        String xml = "<?xml version='2' encoding='UTF-16' something='else'?><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-16", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-16\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-16\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test28() {
        String html = "<img src=asdf onerror=\"alert(2)\" y=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(2)\" y=\"\" />", xmlDoc.html());
    }
    @Test
    public void test29() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>Two<val>Three</val></val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test30() {
        // html will force "<br>two</br>" to logically "<br />Two<br />". XML should be stay "<br>two</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>two</br>");
        assertEquals("<br>two\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>two</br>", "", Parser.xmlParser());
        assertEquals("<br>two</br>", xmlDoc.html());
    }
    @Test public void test31() {
        String html = "<?xml encoding='UTF-16' ?><body>Two</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> Two </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test32() {
        String xml = "<CHECK>Two</CHECK><TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test33() {
        String xml = "<div id=2><![CDATA[\n<html>\n <bar><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("2");
        assertEquals("<html>\n <bar><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"2\"><![CDATA[\n<html>\n <bar><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <bar><&amp;", cdata.text());
    }
    @Test public void test34() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test35() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test36() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test37() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-16", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test38() {
        String xml = "<doc><val>Two<val>Three</val></qui>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>Two<val>Three</val></qui>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test39() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test40() {
        String xml = "<?XML version='1' encoding='UTF-16' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-16\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test41() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test public void test42() {
        String xml = "<script type=\"text/javascript\">/*<![CDATA[*/ testing /**/ foo(); \n/*]]>*/</script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());
        assertEquals("/* testing */ foo(); \n", doc.selectFirst("script").text());
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
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test45() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test46() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test47() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test48() {
        String xml = "<?xml version='2.0'><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("val").text());
    }
    @Test
    public void test49() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test50() {
        String xml = "<doc id=1 href='/foo'>Bar <div /><link>Three</link><link>Four</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<doc id=\"1\" href=\"/foo\">Bar <div></div><link>Three</link><link>Four</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("1").absUrl("href"), "http://example.com/foo");
    }
    @Test
    public void test51() {
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
    public void test52() {
        String xml = "<?xml version='2' encoding='UTF-16' something='new'?><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-16", decl.attr("encoding"));
        assertEquals("new", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-16\" something=\"new\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-16\" something=\"new\"?>", decl.outerHtml());
    }
    @Test
    public void test53() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test54() {
        String html = "<img src=asdf onerror=\"alert(2)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(2)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test55() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test56() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test-changed.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com", Parser.xmlParser());
        assertEquals("<doc><val>Three<val>Four</val>Five</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test57() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test58() {
        // html will force "<br>five</br>" to logically "<br />Five<br />". XML should be stay "<br>five</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>five</br>");
        assertEquals("<br>five\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>five</br>", "", Parser.xmlParser());
        assertEquals("<br>five</br>", xmlDoc.html());
    }
    @Test public void test59() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test public void test60() {
        String html = "<?xml encoding='UTF-16' ?><body>Two</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> Two </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test61() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test62() {
        String xml = "<CHECK>Two</CHECK><TEST ID=2>Verify</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Verify</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test63() {
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
    @Test public void test64() {
        String xml = "<div id=3><![CDATA[\n<html>\n <bar>< &amp; ]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("3");
        assertEquals("<html>\n <bar>< &amp; ", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"3\"><![CDATA[\n<html>\n <bar>< &amp; ]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <bar>< &amp; ", cdata.text());
    }
    @Test public void test65() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test66() {
        String xml = "<six src='/bar/' />Five<six><seven /></six>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("six", nodes.get(0).nodeName());
        assertEquals("Five", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test67() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test public void test68() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test69() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test70() {
        String xml = "<TWO id=2>Verify</TWO>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<two id=\"2\">Verify</two>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test71() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test public void test72() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<p>check</p><div></div>", "", parser);
        assertEquals("<p>\n check\n</p>\n<div></div>", document.html());
        // was failing -> toString() = "<p>\n test\n <div></div>\n</p>"
    }
    @Test
    public void test73() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test74() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-changed.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"2.0\" encoding=\"UTF-8\"?> <data>ñÿû¢</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test75() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test76() {
        String xml = "<doc><val>Three<val>Four</val></bar>Five</doc>";
        Document doc = Jsoup.parse(xml, "http://example.com/", Parser.xmlParser());
        assertEquals("<doc><val>Three<val>Four</val>Five</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test77() {
        Token token = new Token(Token.TokenType.StartTag);
        boolean result = process(token);
        assertTrue(result);
        // Assert that the start tag is inserted correctly
        // ...
    }
    @Test
    public void test78() {
        Token token = new Token(Token.TokenType.EndTag);
        boolean result = process(token);
        assertTrue(result);
        // Assert that the end tag is popped correctly
        // ...
    }
    @Test
    public void test79() {
        Token token = new Token(Token.TokenType.Comment);
        boolean result = process(token);
        assertTrue(result);
        // Assert that the comment is inserted correctly
        // ...
    }
    @Test
    public void test80() {
        Token token = new Token(Token.TokenType.Character);
        boolean result = process(token);
        assertTrue(result);
        // Assert that the character is inserted correctly
        // ...
    }
    @Test
    public void test81() {
        Token token = new Token(Token.TokenType.Doctype);
        boolean result = process(token);
        assertTrue(result);
        // Assert that the doctype is inserted correctly
        // ...
    }
    @Test
    public void test82() {
        Token token = new Token(Token.TokenType.EOF);
        boolean result = process(token);
        assertTrue(result);
        // Assert that the method returns true for EOF token
        // ...
    }
    @Test
    public void test83() {
        Token token = new Token(Token.TokenType.Unknown);
        try {
            process(token);
            fail("Should throw an exception for unexpected token type");
        } catch (IllegalArgumentException e) {
            // Expected exception
            // Assert the error message or perform other assertions
            // ...
        }
    }
    @Test
    public void test84() {
        // Test inserting a null node into the current element
        Node node = null;
        Node originalNode = currentElement().clone();
        insertNode(node);

        // Check that the current element is not modified
        assertEquals(originalNode, currentElement());
    }
    @Test
    public void test85() {
        // Test inserting an element node into the current element
        Element elementNode = new Element("div");
        Node originalNode = currentElement().clone();
        insertNode(elementNode);

        // Check that the element node is inserted as a child of the current element
        assertEquals(elementNode, originalNode.child(0));
    }
    @Test
    public void test86() {
        // Test inserting a text node into the current element
        TextNode textNode = new TextNode("Hello World");
        Node originalNode = currentElement().clone();
        insertNode(textNode);

        // Check that the text node is inserted as a child of the current element
        assertEquals(textNode, originalNode.child(0));
    }
    @Test
    public void test87() {
        // Test inserting a comment node into the current element
        Comment commentNode = new Comment("This is a comment");
        Node originalNode = currentElement().clone();
        insertNode(commentNode);

        // Check that the comment node is inserted as a child of the current element
        assertEquals(commentNode, originalNode.child(0));
    }
    @Test
    public void test88() {
        // Test inserting an XML declaration node into the current element
        XmlDeclaration declarationNode = new XmlDeclaration("xml", "version='1.0' encoding='UTF-8'");
        Node originalNode = currentElement().clone();
        insertNode(declarationNode);

        // Check that the XML declaration node is inserted as a child of the current element
        assertEquals(declarationNode, originalNode.child(0));
    }
    @Test
    public void test89() {
        String xml = "<?xml VERSION='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml VERSION=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test90() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  BAR();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  BAR();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test91() {
        String xml = "<!DOCTYPE HTML><!-- a comment --><val>One</bar>Two</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment --><val>One</bar>Two</doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test92() {
        // test: </foo> closes Two, </bar> ignored
        String xml = "<doc><foo>One<foo>Two</bar>Three</foo></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><foo>One<foo>Two</bar>Three</foo></bar></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test93() {
        String xml = "<?xml version='1.0'><foo>One</foo>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("foo").text());
    }
    @Test
    public void test94() {
        String xml = "<doc id=2 href='/foo/'>Bar <hr /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/foo/\">Bar <hr /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test95() {
        String xml = "<?xml version='2' encoding='UTF-8' something='else'?><foo>One</foo>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"99\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test96() {
        String html = "<img src=temp onerror=\"alert(1)\" x="; // extra = sign
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test97() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://bar.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</foo>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test98() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</bar>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</bar>", "", Parser.xmlParser());
        assertEquals("<br>one\n</bar>", xmlDoc.html());
    }
    @Test public void test99() {
        String html = "<?xml version='UTF-8' encoding='UTF-8' ?><body>Two</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> Two </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test100() {
        String xml = "<CHECK>Two</bar><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</bar><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test101() {
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
    @Test public void test102() {
        String xml = "<one src='/bar/' />Two<three><bar /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test103() {
        Document doc = Jsoup.parse("z", "", Parser.xmlParser());
        assertEquals(Syntax.html, doc.outputSettings().syntax());
    }
    @Test
    public void test104() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test105() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><bar></bar>", "", parser);
        assertEquals("<div>\n test\n</div>\n<bar></bar>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test106() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"2.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test107() {
        String xml = "<doc><val>Two<val>Three</bar>Four</val></doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>Two<val>Three</bar>Four</val></bar></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test108() {
        Token.Comment commentToken = new Token.Comment("Test comment");
        commentToken.bogus = false;
        
        Document doc = new Document("http://example.com/");
        doc.insert(commentToken);
        
        // Check that the comment is properly inserted as a Comment node
        assertEquals(1, doc.childNodes().size());
        assertTrue(doc.childNode(0) instanceof Comment);
        assertEquals("Test comment", doc.childNode(0).outerHtml());
    }
    @Test
    public void test109() {
        Token.Comment commentToken = new Token.Comment("!DOCTYPE HTML");
        commentToken.bogus = true;
        
        Document doc = new Document("http://example.com/");
        doc.insert(commentToken);
        
        // Check that the comment is properly parsed as an XmlDeclaration and inserted
        assertEquals(1, doc.childNodes().size());
        assertTrue(doc.childNode(0) instanceof XmlDeclaration);
        XmlDeclaration declaration = (XmlDeclaration) doc.childNode(0);
        assertEquals("DOCTYPE", declaration.tagName());
        assertEquals("HTML", declaration.attr("HTML"));
    }
    @Test
    public void test110() {
        Token.Comment commentToken = new Token.Comment("");
        commentToken.bogus = true;
        
        Document doc = new Document("http://example.com/");
        doc.insert(commentToken);
        
        // Check that an empty comment is not inserted
        assertEquals(0, doc.childNodes().size());
    }
    @Test
    public void test111() {
        Token.Comment commentToken = new Token.Comment("Invalid declaration");
        commentToken.bogus = true;
        
        Document doc = new Document("http://example.com/");
        doc.insert(commentToken);
        
        // Check that an invalid declaration is not inserted as an XmlDeclaration
        assertEquals(1, doc.childNodes().size());
        assertTrue(doc.childNode(0) instanceof Comment);
        assertEquals("Invalid declaration", doc.childNode(0).outerHtml());
    }
    @Test
    public void test112() {
        Token.Comment commentToken = new Token.Comment("Test comment");
        
        Document doc = new Document("http://example.com/");
        doc.insert(commentToken);
        
        // Check that the comment is properly inserted as a Comment node
        assertEquals(1, doc.childNodes().size());
        assertTrue(doc.childNode(0) instanceof Comment);
        assertEquals("Test comment", doc.childNode(0).outerHtml());
    }
    @Test
    public void test113() {
        Token.Comment commentToken = new Token.Comment("");
        
        Document doc = new Document("http://example.com/");
        doc.insert(commentToken);
        
        // Check that an empty comment is not inserted
        assertEquals(0, doc.childNodes().size());
    }
    @Test
    public void test114() {
        Token.Comment commentToken = new Token.Comment("<!-- Invalid comment -->");
        
        Document doc = new Document("http://example.com/");
        doc.insert(commentToken);
        
        // Check that an invalid comment is not inserted
        assertEquals(1, doc.childNodes().size());
        assertTrue(doc.childNode(0) instanceof Comment);
        assertEquals("<!-- Invalid comment -->", doc.childNode(0).outerHtml());
    }
    @Test
    public void test115() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test116() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test117() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test118() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test119() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test120() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test121() {
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
    public void test122() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test123() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test124() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test125() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test126() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test127() {
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
    @Test public void test128() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test129() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test130() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test131() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test132() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test133() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test134() {
        Token.Character token = new Token.Character("");
        final String data = token.getData();
        XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
        xmlTreeBuilder.insert(token);
        assertEquals("", data);
    }
    @Test
    public void test135() {
        Token.Character token = new Token.Character(null);
        final String data = token.getData();
        XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
        xmlTreeBuilder.insert(token);
        assertEquals(null, data);
    }
    @Test
    public void test136() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test137() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test138() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test139() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test140() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test141() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test142() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test143() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test144() {
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
    public void test145() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test146() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test147() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test148() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test149() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test150() {
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
    @Test public void test151() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test152() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test153() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test154() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test155() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test156() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test157() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
        
        // Additional test: Change the end tag name in popStackToClose method and make sure it still works correctly
        Element script = doc.selectFirst("script");
        Token.EndTag endTag = new Token.EndTag(script.tagName() + "Different");
        popStackToClose(endTag);
        assertTrue(stack.isEmpty());
    }
    @Test
    public void test158() {
        // test: </bar> closes Two, </val> ignored
        String xml = "<doc><val>One<val>Two</bar></val>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</bar></val>Three</doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test159() {
        String xml = "<xml id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></xml>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<xml id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></xml>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test160() {
        String xml = "<?xml version='2' encoding='ISO-8859-1' something='different'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("ISO-8859-1", decl.attr("encoding"));
        assertEquals("different", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"ISO-8859-1\" something=\"different\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"ISO-8859-1\" something=\"different\"?>", decl.outerHtml());
    }
    @Test
    public void test161() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test-encoding.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test162() {
        // xml will force "<br/>one</br>" to logically "<br />One<br />". XML should be stay "<br/>one</br> -- don't recognise tag.
        Document xmlDoc = Jsoup.parse("<br/>one</br>", "", Parser.xmlParser());
        assertEquals("<br/>one</br>", xmlDoc.html());
    }
    @Test public void test163() {
        String html = "<?xml encoding='ISO-8859-1' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test164() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test165() {
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
    @Test public void test166() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test
    public void test167() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test168() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test169() throws IOException {
        String html = "<?xml encoding='ISO-8859-1' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
    }
    @Test
    public void test170() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val></bar>Three</doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test171() {
        String xml = "";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
    @Test public void test172() {
        String xml = "<one src=/foo/ />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());

        assertFalse(nodes.get(0).hasAttr("src"));
        assertEquals("one", nodes.get(0).nodeName());
    }
    @Test public void test173() {
        String xml = "<one src='/foo/' >Two<three><four />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
}