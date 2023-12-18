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
        String xml = "<one src=\"/foo/\"></one>Two<three><four></four></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test
    public void test1() {
        String xml = "<?xml version=\"2\" encoding=\"UTF-8\" something=\"else\"?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test2() {
        String xml = "<div id=1><![CDATA[\n<differentHtml>\n <foo><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<differentHtml>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<differentHtml>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<differentHtml>\n <foo><&amp;", cdata.text());
    }
    @Test
    public void test3() {
        String xml = "<html><something id=2 href=\"/bar\">Foo <br /><link>One</link><link>Two</link></something></html>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<html><something id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></something></html>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test4() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Different</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Different</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test5() {
        String html = "<?xml encoding=\"ISO-8859-1\" ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test6() {
        String xml = "<doc><val>One<val>Different</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Different</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test7() {
        String xml = "<?xml VERSION='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test8() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/different-xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>differentContent</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test9() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var 1=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var 1=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test public void test10() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>differentContent</DIV><p></p>", "", parser);
        assertEquals("<div>\n differentContent\n</div>\n<p></p>", document.html());
    }
    @Test
    public void test11() {
        String xml = "<CHECK>Different</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Different</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test12() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>differentContent</br>");
        assertEquals("<br>differentContent\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>differentContent</br>", "", Parser.xmlParser());
        assertEquals("<br>differentContent</br>", xmlDoc.html());
    }
    @Test
    public void test13() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/different-xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>differentOne<val>differentTwo</val>differentThree</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test14() {
        Document document = Document.createShell("");
        document.outputSettings().syntax(Syntax.xml);
        document.charset(Charset.forName("ISO-8859-1"));
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
            "<html>\n" +
            " <head></head>\n" +
            " <body></body>\n" +
            "</html>", document.outerHtml());
    }
    @Test
    public void test15() {
        String xml = "<?xml version='2.0'><val>Different</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Different", doc.select("val").text());
    }
    @Test
    public void test16() {
        String xml = "<TEST id=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test17() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Different ID=2>Different</Different>");
        assertEquals("<One>One<Different ID=\"2\">Different</Different></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test18() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  differentFoo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  differentFoo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test19() {
        String html = "<img src=differentAttribute onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"differentAttribute\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test20() {
        String xml = "<!DOCTYPE HTML><!-- a different comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a different comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test21() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test
    public void test22() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test23() {
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
    public void test24() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test25() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test26() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test27() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test28() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test29() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test30() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test public void test31() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test32() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test33() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test34() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test35() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test36() {
        String xml = "<?xml version='1.0'><val>One</val>";
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
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test39() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test40() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test41() {
        String xml = "<one src='/foo/'>Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());

        // additional test case where invalid tag is inserted
        Node invalidTag = nodes.get(2);
        assertEquals("four", invalidTag.nodeName());
        assertEquals(0, invalidTag.childNodeSize());
        assertEquals("<four />", invalidTag.outerHtml());
    }
    @Test
    public void test42() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else' unknowAttribute=''/?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("", decl.attr("unknowAttribute"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\" unknowAttribute=\"\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\" unknowAttribute=\"\"?>", decl.outerHtml());
    }
    @Test
    public void test43() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());

        // additional test case where invalid text is inserted
        div.text("<<invalid-text>>");
        assertEquals("<<invalid-text>>", div.text());
        assertEquals(1, div.childNodeSize());
        assertEquals("#text", div.childNode(0).nodeName());
        assertEquals("<<invalid-text>>", ((TextNode) div.childNode(0)).text());
    }
    @Test
    public void test44() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link><invalidTag></invalidTag></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link><invalidTag></invalidTag></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test45() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three<invalidTag></invalidTag></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val><invalidTag></invalidTag></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test46() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\" <invalid>; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\" <invalid>; </script>", doc.html()); // converted from pseudo xmldecl to comment

        // additional test case where invalid characters are inserted
        Element script = doc.selectFirst("script");
        script.text("link<tag>something</tag> </ <?php ?>");
        assertEquals("link<tag>something</tag> </ <?php ?>", script.text());
    }
@Test
public void test47() {
    String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test48() {
    // test: </val> closes Two, </bar> ignored
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>", TextUtil.stripNewlines(doc.html()));
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
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test51() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
}
@Test
public void test52() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("ISO-8859-1", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test53() {
    String xml = "<TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test54() {
    String xml = "<One>One</One>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Elements one = doc.select("One");
    one.append("<Two ID=2>Two</Two>");
    assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
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
public void test56() {
    String html = "<img src=asdf onerror=\"alert(1)\" x=";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
}
@Test
public void test57() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
}
   @Test
    public void test58() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", doc.outerHtml());
    }
    @Test
    public void test59() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>", doc.outerHtml());
    }
    @Test
    public void test60() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.outerHtml());
    }
    @Test
    public void test61() {
        Document document = Document.createShell("");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        document.charset(Charset.forName("utf-8"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<html>\n <head></head>\n <body></body>\n</html>", document.outerHtml());
    }
    @Test
    public void test62() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test63() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc1 = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        Document doc2 = Jsoup.parse(xml, "http://google.com/", Parser.xmlParser());
        assertEquals(doc1.html(), doc2.html());
    }
    @Test
    public void test64() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test65() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.outerHtml()));
    }
    @Test
    public void test66() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", doc.outerHtml());
    }
    @Test
    public void test67() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", doc.outerHtml());
    }
    @Test
    public void test68() {
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
    public void test69() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.outerHtml());
    }
    @Test
    public void test70() {
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
    public void test71() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>", doc.outerHtml());
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test72() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc1 = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        Document doc2 = Jsoup.parse(inStream, null, "http://bar.com", Parser.xmlParser());
        assertEquals(doc1.html(), doc2.html());
    }
    @Test
    public void test73() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test74() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test75() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two", doc.outerHtml());
    }
    @Test
    public void test76() {
        // Change the XML declaration version and encoding
        String xml = "<?xml version='2' encoding='ISO-8859-1' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("ISO-8859-1", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"ISO-8859-1\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"ISO-8859-1\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test77() {
        // Change the XML declaration to be all lower case
        String xml = "<?xml version='1' encoding='utf-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"utf-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test78() {
        // Remove the XML declaration
        String xml = "<val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test 
    public void test79() {
        // Remove the XML declaration
        String html = "<body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(1).nodeName());
    }
    @Test
    public void test80() throws IOException, URISyntaxException {
        // Change the encoding in the XML declaration
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test81() {
        // Remove the XML declaration
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test82() {
        // Change the XML declaration to be empty
        String xml = "<?xml?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("", decl.attr("version"));
        assertEquals("", decl.attr("encoding"));
        assertEquals("", decl.attr("something"));
        assertEquals("version=\"\" encoding=\"\" something=\"\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"\" encoding=\"\" something=\"\"?>", decl.outerHtml());
    }
@Test
public void test83() {
    String xml = "<?xml version='1' encoding='UTF-8'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("1", decl.attr("version"));
    assertEquals("UTF-8", decl.attr("encoding"));
    assertEquals("", decl.attr("something"));
    assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"\"?>", decl.outerHtml());
}
@Test
public void test84() {
    String xml = "<?xml version='1' encoding='UTF-8' something='else' another='attribute'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("1", decl.attr("version"));
    assertEquals("UTF-8", decl.attr("encoding"));
    assertEquals("else", decl.attr("something"));
    assertEquals("attribute", decl.attr("another"));
    assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\" another=\"attribute\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\" another=\"attribute\"?>", decl.outerHtml());
}
@Test
public void test85() {
    String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
}
@Test
public void test86() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
}
@Test
public void test87() {
    String xml = "<?xml version='1.0'><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("One", doc.select("val").text());
}
@Test
public void test88() {
    String xml = "<?xml version=\"1.0\"><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("One", doc.select("val").text());
}
@Test
public void test89() {
    String html = "<?xmlencoding='UTF-8'?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xmlencoding=\"UTF-8\"?> <body> One </body> <!-- comment -->", StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test90() {
    String html = "<?xml encoding='UTF-8' ?> <body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->", StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test91() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("ISO-8859-1", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test92() {
    // https://github.com/jhy/jsoup/issues/1139
    String html = "<script> var a='<?'; var b='?>'; </script>";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<script> var a='\n <!--?'; var b='?-->'; </script>", doc.html()); // converted from pseudo xmldecl to comment
}
@Test
public void test93() {
    // https://github.com/jhy/jsoup/issues/1139
    String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
}
}