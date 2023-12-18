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
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test1() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test2() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else' additional='attribute'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\" additional=\"attribute\"?>", doc.outerHtml());
    }
    @Test
    public void test3() {
        String xml = "<?XML version='5' encoding='UTF-16' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"5\" encoding=\"UTF-16\" something=\"else\"?>", doc.outerHtml());
    }
@Test
public void test4() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
}
@Test public void test5() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
}
@Test
public void test6() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test7() {
    // test: </val> closes Two, </bar> ignored
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test8() {
    String xml = "<?xml version='1.0'><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("One", doc.select("val").text());
}
@Test
public void test9() {
    String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
}
@Test
public void test10() {
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
public void test11() {
    String html = "<img src=asdf onerror=\"alert(1)\" x=";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
}
@Test
public void test12() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test13() {
    // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<br>one</br>");
    assertEquals("<br>one\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
    assertEquals("<br>one</br>", xmlDoc.html());
}
@Test
public void test14() {
    String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test15() {
    String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test16() {
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
@Test public void test17() {
    Document doc = Jsoup.parse("x", "", Parser.xmlParser());
    assertEquals(Syntax.xml, doc.outputSettings().syntax());
}
@Test
public void test18() {
    String xml = "<TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test public void test19() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test
public void test20() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("ISO-8859-1", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
        TextUtil.stripNewlines(doc.html()));
}
@Test
public void test21() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test22() {
    String xml = "<a href='hello' onclick='alert(&apos;Hello&apos;)' id='link'>Link</a>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Element element = doc.getElementById("link");
    assertEquals("alert('Hello')", element.attr("onclick"));
}
@Test
public void test23() {
    String xml = "<a href='hello' onclick=\"alert(&quot;Hello&quot;)\" id='link'>Link</a>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Element element = doc.getElementById("link");
    assertEquals("alert(\"Hello\")", element.attr("onclick"));
}
    @Test
    public void test24() {
        String xml = "<?XML Version='1' encoding='UTF-8' something='else'?>"; // Changed "version" to "Version"
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML Version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test25() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  Foo();\n//]]></script>"; // Changed "foo" to "Foo"
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  Foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test26() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two"; // Changed "val" to "qux"
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test27() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test28() {
        String xml = "<?xml version='1.0'><val>One</val>"; // Added " encoding='UTF-8' something='else'"
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test29() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><i>One</i><link>Two</link></doc>"; // Changed "link" to "i"
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><i>One</i><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test30() {
        String xml = "<?xml Version='1' encoding='UTF-8' something='else'?><val>One</val>"; // Changed "version" to "Version"
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("Version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml Version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test31() {
        String html = "<img src=asdf onerror=\"alert(1)\" x="; // Changed "' asdf" to "=asdf'"
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test32() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://bar.com", Parser.xmlParser()); // Changed "http://foo.com" to "http://bar.com"
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test33() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>"); // Changed "<br />one</br>" to "<br>one</br>"
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test34() {
        String html = "<?xml encoding='UTF-8' ?><body>Two</body><!-- comment -->"; // Changed "One" to "Two"
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> Two </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test35() {
        String xml = "<CHECK>Two</CHECK><TEST ID=1>Check</TEST>"; // Changed "One" to "Two"
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test36() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div>"; // Changed "Foo()" to "<html>"
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test public void test37() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test38() {
        String xml = "<TEST ID=1>Check</TEST>"; // Changed "val" to "TEST"
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test39() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test40() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://bar.com/", Parser.xmlParser()); // Changed "http://example.com" to "http://bar.com/"
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test41() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://bar.com/", Parser.xmlParser()); // Changed "http://foo.com" to "http://bar.com"
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test42() {
        String xml = "<?xml version='2' encoding='UTF-16' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test43() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test44() {
        String xml = "<!DOCTYPE HTML><!-- another comment -->One <baz />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->One <baz />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test45() {
        // test: </val> closes One, </foo> ignored
        String xml = "<doc><val>Two<val>One</val></foo>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>Two<val>One</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test46() {
        String xml = "<?xml version='2.0'><val>Three</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Three", doc.select("val").text());
    }
    @Test
    public void test47() {
        String xml = "<doc id=3 href='/baz'>Bar <br /><link>Three</link><link>Four</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"3\" href=\"/baz\">Bar <br /><link>Three</link><link>Four</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://foo.com/baz");
    }
    @Test
    public void test48() {
        String xml = "<?xml version='2' encoding='UTF-32' something='else'?><val>Three</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-32", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-32\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-32\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test49() {
        String html = "<span src=asdf onerror=\"alert(2)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<span src=\"asdf\" onerror=\"alert(2)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test50() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test-2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test51() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<span>one</span>");
        assertEquals("<span>one\n</span>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<span>one</span>", "", Parser.xmlParser());
        assertEquals("<span>one</span>", xmlDoc.html());
    }
    @Test public void test52() {
        String html = "<?xml encoding='UTF-16' ?><body>Two</body><!-- another comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> Two </body> <!-- another comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test53() {
        String xml = "<CHECK>Two</CHECK><TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test54() {
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
    @Test public void test55() {
        String xml = "<three src='/bar/' />Four<five><six /></five>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("three", nodes.get(0).nodeName());
        assertEquals("Four", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test56() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test57() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test58() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<li>test</LI><p></p>", "", parser);
        assertEquals("<li>\n test\n</li>\n<p></p>", document.html());
        // was failing -> toString() = "<li>\n test\n <p></p>\n</li>"
    }
    @Test
    public void test59() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>ëêÎÎÇÉÝ</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test60() {
        String xml = "<doc><val>One<val>Two</val></foo>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test61() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test62() {
        String xml = "<?xml version='1' encoding='ISO-8859-1' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"ISO-8859-1\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test63() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test public void test64() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test65() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test66() {
        String xml = "<!DOCTYPE HTML><!-- another comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test67() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test68() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Three</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Three</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test69() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test70() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test71() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test72() {
        String xml = "<doc id=2 href='/baz'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/baz\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/baz");
    }
    @Test
    public void test73() {
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
    public void test74() {
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
    public void test75() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test76() {
        String html = "<img src=baz onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"baz\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test77() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test78() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test-different-data.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test79() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test80() {
        // html will force "<hr>one</hr>" to logically "<hr />One<hr />". XML should be stay "<hr>one</hr> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<hr>one</hr>");
        assertEquals("<hr>one\n<hr>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<hr>one</hr>", "", Parser.xmlParser());
        assertEquals("<hr>one</hr>", xmlDoc.html());
    }
    @Test public void test81() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test public void test82() {
        String html = "<?xml encoding='ISO-8859-1' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test83() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test84() {
        String xml = "<CHECK>Two</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test85() {
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
    @Test public void test86() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&gt;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&gt;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&gt;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&gt;", cdata.text());
    }
    @Test public void test87() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test88() {
        String xml = "<two src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("two", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test89() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test90() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test91() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test92() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test public void test93() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test1</DIV><p></p>", "", parser);
        assertEquals("<div>\n test1\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test94() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test95() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset-utf16.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-16", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test96() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test97() {
        String xml = "<doc><val>One<val>Four</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Four</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test98() {
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test99() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test100() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test101() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Three</val></bar>Four</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Three</val>Four</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test102() {
        String xml = "<doc id=2 href='/baz'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/baz\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/baz");
    }
    @Test
    public void test103() {
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
    public void test104() {
        String html = "<img src=qwerty onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"qwerty\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test105() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Three</val>Four</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test106() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test107() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test108() {
        String xml = "<CHECK>One</CHECK><TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test109() {
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
    @Test public void test110() {
        String xml = "<two src='/bar/' />Three<four><five /></four>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());
        assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
        assertEquals("two", nodes.get(0).nodeName());
        assertEquals("Three", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test111() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test112() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test113() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test114() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test115() {
        String xml = "<doc><val>One<val>Three</val></bar>Four</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Three</val>Four</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test116() {
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test117() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test118() {
        String xml = "<!DOCTYPE HTML><!-- another comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test119() {
        // test: </val> closes Three, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></val>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test120() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test121() {
        String xml = "<doc id=3 href='/baz'>Bar <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc id=\"3\" href=\"/baz\">Bar <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://bar.com/baz");
    }
    @Test
    public void test122() {
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
    public void test123() {
        String html = "<img src=asdf onerror=\"alert(2)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(2)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test124() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://bar.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test125() {
        // html will force "<br>One</br>" to logically "<br />One<br />". XML should be stay "<br>One</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>One</br>");
        assertEquals("<br>One\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>One</br>", "", Parser.xmlParser());
        assertEquals("<br>One</br>", xmlDoc.html());
    }
    @Test
    public void test126() {
        String html = "<?xml encoding='UTF-8' ?><body>Two</body><!-- new comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> Two </body> <!-- new comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test127() {
        String xml = "<CHECK>Two</CHECK><TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test128() {
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
    @Test
    public void test129() {
        String xml = "<one src='/baz/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example2.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example2.com/baz/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode) nodes.get(1)).text());
    }
    @Test
    public void test130() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test131() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test132() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test133() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example2.com/", Parser.xmlParser());
        assertEquals("ISO-8859-2", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-2\"?> <data>äöåéü</data>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test134() {
        String xml = "<doc><val>One<val>Two</val></val>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://baz.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test135() { // Change the version to a different value
        String xml = "<?xml version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test136() { // Remove the leading newline
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test137() { // Remove the HTML comment
        String xml = "<!DOCTYPE HTML>One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML>One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test138() { // Remove </bar> tag
        String xml = "<doc><val>One<val>Two</val>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test139() { // Add space after version value
        String xml = "<?xml version='1.0' ?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test140() { // Remove space after '=' in attributes
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test141() { // Remove the attribute 'something'
        String xml = "<?xml version='1' encoding='UTF-8'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("version=\"1\" encoding=\"UTF-8\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\"?>", decl.outerHtml());
    }
    @Test
    public void test142() { // Remove space after '=' in attribute
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test143() throws IOException, URISyntaxException { // Remove the last closing ">" in xml-test.xml
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test144() { // Remove the closing ">" after "br" tag
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one<br>", "", Parser.xmlParser());
        assertEquals("<br>one<br>", xmlDoc.html());
    }
    @Test
    public void test145() { // Remove the XML declaration
        String html = "<body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#comment", doc.childNode(1).nodeName());
    }
    @Test
    public void test146() { // Change 'val' tag to uppercase
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test147() { // Remove the empty <p> tag
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV>", "", parser);
        assertEquals("<div>\n test\n</div>\n", document.html());
    }
    @Test
    public void test148() throws IOException, URISyntaxException { // Change the xml-charset.xml document encoding to UTF-8
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test149() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        // Change input by removing the version attribute
        xml = "<?XML encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test150() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        // Change input by removing the CDATA wrappers
        xml = "<script type=\"text/javascript\">foo();</script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("foo();", doc.selectFirst("script").text());
    }
    @Test
    public void test151() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        // Change input by removing the comment
        xml = "<!DOCTYPE HTML>One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML>One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test152() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        // Change input by adding closing tag for the 'bar' element
        xml = "<doc><val>One<val>Two</val></bar>Three</bar></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test153() {
        String xml = "<?xml version='1.0'><val>One</val>";
        // Change input by removing the version attribute
        xml = "<?xml><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test154() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        // Change input by removing the href attribute
        xml = "<doc id=2>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test155() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        // Change input by removing the version attribute
        xml = "<?xml encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test156() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        // Change input by adding closing quote for 'onerror' attribute
        html = "<img src=asdf onerror=\"alert(1)\" x=\"\"";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test157() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test158() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test159() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        // Change input by removing the xml declaration
        html = "<body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test160() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        // Change input by removing the ID attribute
        xml = "<CHECK>One</CHECK><TEST>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST>Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test161() {
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
    @Test public void test162() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        // Change input by removing the src attribute
        xml = "<one />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test163() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test164() {
        String xml = "<TEST ID=1>Check</TEST>";
        // Change input by removing the ID attribute
        xml = "<TEST>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test165() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test166() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        // Change input by changing the encoding declaration
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test167() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        // Change input by adding closing tag for the 'bar' element
        xml = "<doc><val>One<val>Two</val></bar>Three</bar></doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test168() {
        // Mutant killed by changing the input value of xml
        String xml = "<?xml version='1.0' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test169() {
        // Mutant killed by changing the input value of xml
        String xml = "<?xml version='0.5'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test170() {
        // Mutant killed by changing the input value of html
        String xml = "<?xml version='0.5'?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
@Test public void test171() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    assertEquals(xml, doc.outerHtml());
}
@Test
public void test172() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val></bar>Three</doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test173() {
    String xml = "<doc><val>One<val>Two</val></val>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test174() {
    String xml = "<doc id=2 href=''>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"2\" href=\"\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals("", doc.getElementById("2").absUrl("href"));
}
@Test
public void test175() {
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
public void test176() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/empty.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
    assertEquals("", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test177() {
    // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<br>one</br>");
    assertEquals("<br>one\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
    assertEquals("<br>one</br>", xmlDoc.html());
}
@Test public void test178() {
    String html = "<?xml encoding='UTF-16' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-16\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test179() {
    String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
}
@Test public void test180() {
    String xml = "<div id=1><![CDATA[]]></div>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

    Element div = doc.getElementById("1");
    assertEquals("", div.text());
    assertEquals(0, div.children().size());
    assertEquals(1, div.childNodeSize()); // no elements, one empty text node

    assertEquals("<div id=\"1\"><![CDATA[]]></div>", div.outerHtml());

    CDataNode cdata = (CDataNode) div.textNodes().get(0);
    assertEquals("", cdata.text());
}
@Test public void test181() {
    String xml = "";
    List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
    assertEquals(0, nodes.size());
}
@Test
public void test182() {
    String xml = "<TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test public void test183() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test
public void test184() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("UTF-16", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
        TextUtil.stripNewlines(doc.html()));
}
@Test
public void test185() {
    String xml = "";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertEquals("", TextUtil.stripNewlines(doc.html()));
}
    @Test 
    public void test186() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, null);
        assertEquals(3, nodes.size());

        assertEquals("/foo/", nodes.get(0).absUrl("src")); // Mutant: changed "http://example.com/foo/" to "/foo/"
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test 
    public void test187() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "");
        assertEquals(3, nodes.size());

        assertEquals("/foo/", nodes.get(0).absUrl("src")); // Mutant: changed "http://example.com/foo/" to "/foo/"
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test 
    public void test188() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "example.com");
        assertEquals(3, nodes.size());

        assertEquals("/foo/", nodes.get(0).absUrl("src")); // Mutant: changed "http://example.com/foo/" to "/foo/"
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test 
    public void test189() {
        String xml = "Invalid input";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size()); // Mutant: changed 3 to 0
    }
    @Test 
    public void test190() {
        String xml = "<one src='/foo/' /><two src='/bar/' />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size()); // Mutant: changed 3 to 2

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("http://example.com/bar/", nodes.get(1).absUrl("src")); // Mutant: changed "/bar/" to "http://example.com/bar/"
        assertEquals("two", nodes.get(1).nodeName()); // Mutant: changed "one" to "two"
    }
}