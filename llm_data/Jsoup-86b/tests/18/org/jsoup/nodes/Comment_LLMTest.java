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
@Test
public void test0() {
    String xml = "";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("#comment", doc.nodeName());
}
@Test
public void test1() {
    String xml = "a";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("#comment", doc.nodeName());
}
@Test
public void test2() {
    String xml = "<one>Two</one>";
    List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
    assertEquals(1, nodes.size());

    assertEquals("#comment", nodes.get(0).nodeName());
}
@Test
public void test3() {
    String xml = "<one>Two";
    List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
    assertEquals(1, nodes.size());

    assertEquals("#comment", nodes.get(0).nodeName());
}
    @Test public void test4() {
        String xml = "<one src='/foo/test' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/test", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test5() {
        String xml = "<?xml version='2' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test6() {
        String xml = "<div id=2><![CDATA[\n<html>\n <foo><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("2");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"2\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test public void test7() {
        String xml = "<doc id=3 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"3\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://foo.com/bar");
    }
    @Test public void test8() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test9() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- reply -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- reply -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test public void test10() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test11() {
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test12() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-2", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-2\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test13() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test public void test14() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test public void test15() {
        String xml = "<CHECK>One</CHECK><TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test16() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test17() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test public void test18() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test19() {
        String xml = "<?xml version='1.1'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test public void test20() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test21() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test22() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test public void test23() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test24() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test25() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc><a></a>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc><a></a>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test26() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br><a></a>");
        assertEquals("<br>one\n<br><a></a>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br><a></a>", "", Parser.xmlParser());
        assertEquals("<br>one</br><a></a>", xmlDoc.html());
    }
    @Test
    public void test27() {
        Document document = Document.createShell("");
        document.outputSettings().syntax(Syntax.xml);
        document.charset(Charset.forName("utf-8"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<html>\n" +
            " <head></head>\n" +
            " <body></body>\n" +
            "</html>", document.outerHtml());
    }
    @Test public void test28() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test29() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc><a></a>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc><a></a>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test30() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?><a></a>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test31() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test32() {
        String xml = "<TEST ID=1>Check</TEST><a></a>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test><a></a>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test33() {
        String xml = "<One>One</One><a></a>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One><a></a>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test34() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val><a></a>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test35() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p><a></a>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p><a></a>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test public void test36() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div><a></a>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div><a></a>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test
    public void test37() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc><a></a>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc><a></a>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test38() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc><a></a>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test39() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script><a></a>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test40() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=><a></a>";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" /><a></a>", xmlDoc.html());
    }
    @Test
    public void test41() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two<a></a>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two<a></a>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test42() {
        Document doc = Jsoup.parse("");
        assertEquals("", doc.outerHtml());
    }
    @Test
    public void test43() {
        Document doc = Jsoup.parse("<div>Content</div>");
        assertEquals("<div>Content</div>", doc.outerHtml());
    }
    @Test
    public void test44() {
        Document doc = Jsoup.parse("<div><span>Content</span></div>");
        assertEquals("<div><span>Content</span></div>", doc.outerHtml());
    }
    @Test
    public void test45() {
        Document doc = Jsoup.parse("<img src=\"image.jpg\" />");
        assertEquals("<img src=\"image.jpg\" />", doc.outerHtml());
    }
    @Test
    public void test46() {
        Document doc = Jsoup.parse("<a href=\"https://www.example.com\">Link</a>");
        assertEquals("<a href=\"https://www.example.com\">Link</a>", doc.outerHtml());
    }
    @Test
    public void test47() {
        Document doc = Jsoup.parse("<div class=container>Content</div>");
        assertEquals("<div class=\"container\">Content</div>", doc.outerHtml());
    }
    @Test
    public void test48() {
        Document doc = Jsoup.parse("<p>This is a <b>bold</b> statement.</p>");
        assertEquals("<p>This is a <b>bold</b> statement.</p>", doc.outerHtml());
    }
    @Test
    public void test49() {
        Document doc = Jsoup.parse("<!DOCTYPE html><html><body><h1>Heading</h1></body></html>");
        assertEquals("<!DOCTYPE html><html><head></head><body><h1>Heading</h1></body></html>", doc.outerHtml());
    }
    @Test
    public void test50() {
        Document doc = Jsoup.parse("<!-- This is a comment --><div>Content</div>");
        assertEquals("<!-- This is a comment --><div>Content</div>", doc.outerHtml());
    }
    @Test
    public void test51() {
        Document doc = Jsoup.parse("<div><![CDATA[<p>This is a paragraph.</p>]]></div>");
        assertEquals("<div><![CDATA[<p>This is a paragraph.</p>]]></div>", doc.outerHtml());
    }
    @Test
    public void test52() {
        Document doc = Jsoup.parse("<div>&#128512;</div>");
        assertEquals("<div>&#128512;</div>", doc.outerHtml());
    }
    @Test
    public void test53() {
        Document doc = Jsoup.parse("<div><span>Missing closing tag</div>");
        assertEquals("<div><span>Missing closing tag</span></div>", doc.outerHtml());
    }
    @Test
    public void test54() {
        Document doc = Jsoup.parse("<pre>   \n   \n   Content   \n   \n   </pre>");
        assertEquals("<pre>   \n   \n   Content   \n   \n   </pre>", doc.outerHtml());
    }
@Test
public void test55() {
    String xml = "<?xml version='' encoding='UTF-8' something='else'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("", decl.attr("version"));
    assertEquals("UTF-8", decl.attr("encoding"));
    assertEquals("else", decl.attr("something"));
    assertEquals("version=\"\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
}
@Test
public void test56() {
    String xml = "<?xml version='1' encoding='' something='else'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("1", decl.attr("version"));
    assertEquals("", decl.attr("encoding"));
    assertEquals("else", decl.attr("something"));
    assertEquals("version=\"1\" encoding=\"\" something=\"else\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"1\" encoding=\"\" something=\"else\"?>", decl.outerHtml());
}
@Test
public void test57() {
    String xml = "<?xml version='' encoding='' something='else'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("", decl.attr("version"));
    assertEquals("", decl.attr("encoding"));
    assertEquals("else", decl.attr("something"));
    assertEquals("version=\"\" encoding=\"\" something=\"else\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"\" encoding=\"\" something=\"else\"?>", decl.outerHtml());
}
@Test
public void test58() {
    String xml = "<?xml?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertFalse(decl.hasAttributes());
    assertEquals("<?xml?>", decl.getWholeDeclaration());
    assertEquals("<?xml?>", decl.outerHtml());
}
    @Test
    public void test59() {
        String xml = "<?xml encoding='UTF-8' version='1' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test60() {
        String xml = "<?xmL version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xMl version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test61() {
        String xml = "<?xml?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test62() {
        String xml = "<?xml version='1.0' extra='info'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test63() {
        String xml = "<?xml version='1.0'><val>One";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test64() {
        String xml = "val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertTrue(doc.childNodes().isEmpty());
    }
    @Test
    public void test65() {
        String html = "<?xml encoding='UTF-8' ?><body/>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body></body>",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
    }
    @Test
    public void test66() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-invalid.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test67() {
        String html = "<script> var a=\"<<<\"; var b=\"<!\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--<<\"; var b=\"<!-\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
}