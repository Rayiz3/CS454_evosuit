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
        String xml = "<three><four /><one src='/foo/' />Two</three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(1).absUrl("src"));
        assertEquals("one", nodes.get(1).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(2)).text());
    }
    @Test
    public void test1() {
        String xml = "<one src='/foo/' /><one src='/bar/' /><one src='/baz/' />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("http://example.com/bar/", nodes.get(1).absUrl("src"));
        assertEquals("http://example.com/baz/", nodes.get(2).absUrl("src"));
    }
    @Test
    public void test2() {
        String xml = "";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
    @Test
    public void test3() {
        String xml = "<test id=1>Check</test>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test4() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test5() {
        String xml = "<tEst iD=1>Check</Test>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test6() throws IOException, URISyntaxException {
        String xml = "<doc><val>One<val>Two</val>Three</val></doc>";
        InputStream inStream = new ByteArrayInputStream(xml.getBytes());
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test7() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", doc.html());
    }
    @Test
    public void test8() {
        String xml = "<?xml version='1.0'; charset='UTF-8'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test9() {
        String xml = "";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("", TextUtil.stripNewlines(doc.html()));
        assertEquals(0, doc.getAllElements().size());
    }
    @Test
    public void test10() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test11() throws IOException, URISyntaxException {
        String xml = "<data>äöåéü</data>";
        InputStream inStream = new ByteArrayInputStream(xml.getBytes());
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test12() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>\n <Two ID=\"2\">Two</Two>\n</One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test13() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test14() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", doc.html());

        assertNull(doc.selectFirst("script"));
    }
    @Test
    public void test15() {
        String xml = "";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test16() {
        String xml = "";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test17() {
        String html = "";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("", xmlDoc.html());
    }
    @Test
    public void test18() {
        String xml = "";
        Document xmlDoc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", xmlDoc.html());
    }
    @Test
    public void test19() {
        String html = "";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("", doc.html());
    }
    @Test
    public void test20() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", doc.outerHtml());
    }
    @Test public void test21() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("", "", parser);
        assertEquals("", document.html());
    }
    @Test
    public void test22() {
        Document document = Document.createShell("");
        document.outputSettings().syntax(Syntax.xml);
        document.charset(Charset.forName("utf-8"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<html>\n" +
            " <head></head>\n" +
            " <body></body>\n" +
            "</html>", document.outerHtml());
    }
    @Test
    public void test23() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("", TextUtil.stripNewlines(doc.html()));
        assertEquals(0, doc.getAllElements().size());
    }
    @Test public void test24() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertNull(div);
        assertFalse(doc.textNodes().isEmpty());
        CDataNode cdata = (CDataNode) doc.textNodes().get(0);
        assertEquals("", cdata.text());
    }
    @Test public void test25() {
        String html = "";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("", StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals(0, doc.childNodeSize());
    }
    @Test public void test26() {
        String xml = "<one />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertEquals("", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test27() {
        String xml = "<one src='' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertEquals("", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test28() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser().settings(ParseSettings.htmlSyntax()));
        assertEquals(Syntax.html, doc.outputSettings().syntax());
    }
    @Test
    public void test29() {
        String xml = "<TEST><</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test><</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test30() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", null);
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test31() {
        String xml = "<?xml ?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test32() {
        String xml = "<doc id=2>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "");
    }
    @Test
    public void test33() {
        String xml = "<?xml version='1' encoding='UTF-8' something='über'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("über", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"über\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"über\"?>", decl.outerHtml());
    }
    @Test
    public void test34() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test35() {
        String xml = "<one>One</one>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("one");
        one.append("<two ID=2>Two</two>");
        assertEquals("<one>One<two ID=\"2\">Two</two></one>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test36() {
        String xml = "<check>One</check><test ID=1>Check</test>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<check>One</check><test ID=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test37() {
        String xml = "<script type=\"text/javascript\"><![CDATA[\n\n  foo();\n\n]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("\n\n  foo();\n\n", doc.selectFirst("script").text());
    }
    @Test
    public void test38() {
        String xml = "<doc><val>One<val>Two</val>Three</val></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test39() {
        String xml = "<!DOCTYPE HTML>One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML>One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test40() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test41() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test42() {
        String xml = "<?XML version='2' encoding='UTF-16' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test43() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div >test</DIV > < p ></p > ", "", parser);
        assertEquals("<div >\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test44() {
        String xml = "<doc><val>One<val>Two</val>Three</val></doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", null);
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test45() {
        String xml = "<div id=1><![CDATA[\n<html> <foo><&amp;]]><!-- comment --></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html> <foo><&amp;", div.text());
        assertEquals(1, div.children().size());
        assertEquals(2, div.childNodeSize()); // one element, one comment

        assertEquals("<div id=\"1\"><![CDATA[\n<html> <foo><&amp;]]><!-- comment --></div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html> <foo><&amp;", cdata.text());

        Comment comment = (Comment) div.childNode(1);
        assertEquals(" comment ", comment.getData());
    }
    @Test public void test46() {
        String html = "<?xmlencoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xmlencoding='UTF-8'?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test47() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test48() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test49() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test50() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test51() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test52() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test53() {
        String xml = "<?XML version='1' encoding='UTF-8' something=\'else\'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test54() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test55() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test56() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test57() {
        Document document = Document.createShell("");
        document.outputSettings().syntax(Syntax.xml);
        document.charset(Charset.forName("utf-8"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<html>\n" +
            " <head></head>\n" +
            " <body></body>\n" +
            "</html>", document.outerHtml());
    }
    @Test
    public void test58() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
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
    @Test
    public void test60() {
        String xml = "<?xml version='1' encoding=\"UTF-8\" something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test61() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test public void test62() {
        String html = "<?xml encoding=\"UTF-8\" ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test63() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test64() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test65() {
        String xml = "<One>  One  </One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two>  Two  </Two>");
        assertEquals("<One>  One  <Two>  Two  </Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test66() {
        String xml = "<One><!-- --></One>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<One><!-- --></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test67() {
        String xml = "<tag></tag>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<tag></tag>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test68() {
        String xml1 = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        String xml2 = "<!DOCTYPE HTML><!-- a comment -->One Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc1 = tb.parse(xml1, "http://foo.com/");
        Document doc2 = tb.parse(xml2, "http://foo.com/");
        
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc1.html()));
        
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One Two",
                TextUtil.stripNewlines(doc2.html()));
    }
    @Test
    public void test69() {
        String html1 = "<img src=asdf onerror=\"alert(1)\" x=";
        String html2 = "<img src=bdef onerror=\"alert(1)\" x=";
        Document xmlDoc1 = Jsoup.parse(html1, "", Parser.xmlParser());
        Document xmlDoc2 = Jsoup.parse(html2, "", Parser.xmlParser());

        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc1.html());
        assertEquals("<img src=\"bdef\" onerror=\"alert(1)\" x=\"\" />", xmlDoc2.html());
    }
    @Test
    public void test70() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc1 = Jsoup.parse("<br>one</br>");
        Document htmlDoc2 = Jsoup.parse("<br>two</br>");

        assertEquals("<br>one\n<br>", htmlDoc1.body().html());
        assertEquals("<br>two\n<br>", htmlDoc2.body().html());

        Document xmlDoc1 = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        Document xmlDoc2 = Jsoup.parse("<br>two</br>", "", Parser.xmlParser());

        assertEquals("<br>one</br>", xmlDoc1.html());
        assertEquals("<br>two</br>", xmlDoc2.html());
    }
    @Test
    public void test71() {
        String xml1 = "<One>One</One>";
        String xml2 = "<Two>Two</Two>";
        Document doc1 = Jsoup.parse(xml1, "", Parser.xmlParser());
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());

        Elements one1 = doc1.select("One");
        one1.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc1.html()));

        Elements one2 = doc2.select("Two");
        one2.append("<Three ID=3>Three</Three>");
        assertEquals("<Two>Two<Three ID=\"3\">Three</Three></Two>", TextUtil.stripNewlines(doc2.html()));
    }
    @Test
    public void test72() {
        String xml1 = "<doc id=1 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        String xml2 = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb1 = new XmlTreeBuilder();
        XmlTreeBuilder tb2 = new XmlTreeBuilder();
        Document doc1 = tb1.parse(xml1, "http://foo.com/");
        Document doc2 = tb2.parse(xml2, "http://baz.com/");

        assertEquals("<doc id=\"1\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc1.html()));
        assertEquals(doc1.getElementById("1").absUrl("href"), "http://foo.com/bar");

        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc2.html()));
        assertEquals(doc2.getElementById("2").absUrl("href"), "http://baz.com/bar");
    }
    @Test
    public void test73() {
        String xml1 = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        String xml2 = "<CHECK>Two</CHECK><TEST ID=2>Check</TEST>";
        Document doc1 = Jsoup.parse(xml1, "", Parser.xmlParser());
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());

        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc1.html()));
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc2.html()));
    }
    @Test
    public void test74() {
        String xml1 = "<?XML version='1' encoding='UTF-8' something='else'?>";
        String xml2 = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc1 = Jsoup.parse(xml1, "", Parser.xmlParser());
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());

        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc1.outerHtml());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc2.outerHtml());
    }
    @Test
    public void test75() {
        // test: </val> closes Two, </bar> ignored
        String xml1 = "<doc><val>One<val>Two</val></bar>Three</doc>";
        String xml2 = "<doc><tag>One<tag>Two</tag></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc1 = tb.parse(xml1, "http://foo.com/");
        Document doc2 = tb.parse(xml2, "http://foo.com/");
        
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc1.html()));
        
        assertEquals("<doc><tag>One<tag>Two</tag></bar>Three</tag></doc>",
                TextUtil.stripNewlines(doc2.html()));
    }
    @Test
    public void test76() throws IOException, URISyntaxException {
        File xmlFile1 = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        File xmlFile2 = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-utf16.xml").toURI());
        InputStream inStream1 = new FileInputStream(xmlFile1);
        InputStream inStream2 = new FileInputStream(xmlFile2);
        
        Document doc1 = Jsoup.parse(inStream1, null, "http://example.com/", Parser.xmlParser());
        Document doc2 = Jsoup.parse(inStream2, null, "http://example.com/", Parser.xmlParser());

        assertEquals("ISO-8859-1", doc1.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc1.html()));
        
        assertEquals("UTF-16", doc2.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc2.html()));
    }
    @Test
    public void test77() {
        String html = "";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("", doc.html()); 
    }
    @Test
    public void test78() {
        String html = "?";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<body>? </body>", doc.outerHtml()); 
    }
    @Test
    public void test79() {
        String html = "<?html version='1' encoding='UTF-8'?><val>One</val>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<body><?html version=\"1\" encoding=\"UTF-8\"?> <val> One </val></body>",
                doc.outerHtml()); 
    }
    @Test
    public void test80() {
        String html = "???";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<body>??? </body>", doc.outerHtml()); 
    }
    @Test
    public void test81() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?=\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?=\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test82() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test83() {
        String xml = "<?xml version='1.0'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test84() {
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
    public void test85() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test86() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test87() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = doc.asXmlDeclaration();
        assertNull(decl);
    }
    @Test
    public void test88() {
        String xml = "<?xml version='2' encoding='UTF-8' something='else'?><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = doc.asXmlDeclaration();
        assertNull(decl);
    }
}