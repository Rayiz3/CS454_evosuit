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
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
    @Test
    public void test1() {
        String xml = "<div>Text Node</div>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("div", nodes.get(0).nodeName());
    }
    @Test
    public void test2() {
        String xml = "<a href=http://example.com>Link</a>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("a", nodes.get(0).nodeName());
        assertEquals("http://example.com", nodes.get(0).attr("href"));
    }
    @Test
    public void test3() {
        String xml = "<p>Unclosed paragraph";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("p", nodes.get(0).nodeName());
        assertEquals("Unclosed paragraph", ((TextNode)nodes.get(0)).text());
    }
    @Test
    public void test4() {
        String xml = "<blockquote><p>Quoted text</p><cite>Author</cite></blockquote>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("blockquote", nodes.get(0).nodeName());
        assertEquals(2, nodes.get(0).childNodeSize());
        assertEquals("Quoted text", ((TextNode)nodes.get(0).childNode(0)).text());
        assertEquals("Author", ((TextNode)nodes.get(0).childNode(1)).text());
    }
    @Test
    public void test5() {
        String xml = "<div><![CDATA[This is a CDATA section with <tags> and &amp; entities]]></div>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("div", nodes.get(0).nodeName());
        assertEquals(1, nodes.get(0).childNodeSize());
        assertEquals("This is a CDATA section with <tags> and &amp; entities",
                ((TextNode)nodes.get(0).childNode(0)).text());
    }
    @Test
    public void test6() {
        String xml = "<div>\n    <p>Paragraph 1</p>\n    <p>Paragraph 2</p>\n</div>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("div", nodes.get(0).nodeName());
        assertEquals(3, nodes.get(0).childNodeSize());
        assertEquals("\n    ", ((TextNode)nodes.get(0).childNode(0)).text());
        assertEquals("Paragraph 1", ((TextNode)nodes.get(0).childNode(1)).text());
        assertEquals("\n    ", ((TextNode)nodes.get(0).childNode(2)).text());
    }
    @Test
    public void test7() {
        String xml = "<div>&lt; &gt; &amp;</div>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("div", nodes.get(0).nodeName());
        assertEquals("< >", ((TextNode)nodes.get(0).childNode(0)).text());
    }
    @Test
    public void test8() {
        String xml = "<a href=\"http://example.com\" invalid=\"attribute\">Link</a>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("a", nodes.get(0).nodeName());
        assertEquals("http://example.com", nodes.get(0).attr("href"));
        assertEquals("", nodes.get(0).attr("invalid"));
    }
    @Test
    public void test9() {
        String xml = "<img src=\"image.png\">";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());
        assertEquals("img", nodes.get(0).nodeName());
        assertEquals("image.png", nodes.get(0).attr("src"));
    }
@Test public void test10() {
    String xml = "<one src='/bar/' />Two<three><four /></three>";
    List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
    assertEquals(3, nodes.size());

    assertEquals("http://example.com/bar/", nodes.get(0).absUrl("src"));
    assertEquals("one", nodes.get(0).nodeName());
    assertEquals("Two", ((TextNode)nodes.get(1)).text());
}
@Test
public void test11() {
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
public void test12() {
    String xml = "<div id=1><![CDATA[\n<html>\n <foo>&</foo>]]></div>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

    Element div = doc.getElementById("1");
    assertEquals("\n<html>\n <foo>&</foo>", div.text());
    assertEquals(0, div.children().size());
    assertEquals(1, div.childNodeSize()); // no elements, one text node

    assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo>&amp;</foo>]]>\n</div>", div.outerHtml());

    CDataNode cdata = (CDataNode) div.textNodes().get(0);
    assertEquals("\n<html>\n <foo>&</foo>", cdata.text());
}
@Test
public void test13() {
    String xml = "<doc id=2 href='/foo'>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://bar.com/");
    assertEquals("<doc id=\"2\" href=\"/foo\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("href"), "http://bar.com/foo");
}
@Test
public void test14() {
    // test: </val> closes Two, </bar> ignored
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://bar.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test15() {
    String html = "<?xml encoding='UTF-16' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-16\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test16() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://bar.com/", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test17() {
    String xml = "<?XML version='1' encoding='UTF-16' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-16\" something=\"else\"?>", doc.outerHtml());
}
@Test
public void test18() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("UTF-16", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
        TextUtil.stripNewlines(doc.html()));
}
@Test
public void test19() {
    // https://github.com/jhy/jsoup/issues/1139
    String html = "<script> var a=\"<\"; var b=\">\"; </script>";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<script> var a=\"\n <!--<\"; var b=\">-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
}
@Test public void test20() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test
public void test21() {
    String xml = "<CHECK>Two</CHECK><TEST ID=2>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test22() {
    // html will force "<br>Two</br>" to logically "<br />Two<br />". XML should be stay "<br>Two</br> -- don't recognize tag.
    Document htmlDoc = Jsoup.parse("<br>Two</br>");
    assertEquals("<br>Two\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br>Two</br>", "", Parser.xmlParser());
    assertEquals("<br>Two</br>", xmlDoc.html());
}
@Test
public void test23() {
    Document doc = Jsoup.parse("x", "", Parser.xmlParser());
    assertEquals(Syntax.html, doc.outputSettings().syntax());
}
@Test
public void test24() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test25() {
    String xml = "<?xml version='2.0'><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("One", doc.select("val").text());
}
@Test
public void test26() {
    String xml = "<TEST ID=2>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test27() {
    String xml = "<One>One</One>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Elements one = doc.select("One");
    one.append("<Two ID=2>Two</Two>");
    assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
}
@Test public void test28() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
}
@Test
public void test29() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->Two <qux />Three";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://bar.com/");
    assertEquals("<!DOCTYPE HTML><!-- a comment -->Two <qux />Three",
            TextUtil.stripNewlines(doc.html()));
}
    @Test
    public void test30() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST><ANOTHER>Example</ANOTHER>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST><ANOTHER>Example</ANOTHER>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test31() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc><val>Four</val>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc><val>Four</val>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test32() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br><br>two</br>");
        assertEquals("<br>one\n<br>two\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br><br>two</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br><br>two</br>", xmlDoc.html());
    }
    @Test
    public void test33() {
        Document document = Document.createShell("");
        document.outputSettings().syntax(Syntax.xml);
        document.charset(Charset.forName("utf-8"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<html>\n" +
            " <head></head>\n" +
            " <body></body>\n" +
            "</html>", document.outerHtml());
    }
    @Test public void test34() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment --><body>Two</body>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment --> <body> Two </body>",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
        assertEquals("body", doc.childNode(4).nodeName());
    }
    @Test
    public void test35() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc><val>Four</val>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc><val>Four</val>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test36() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test37() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test38() {
        String xml = "<TEST ID=1>Check</TEST><ANOTHER ID=2>Example</ANOTHER>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test><another id=\"2\">Example</another>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test39() {
        String xml = "<One>One</One><Two ID=2>Two</Two>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Three ID=3>Three</Three>");
        assertEquals("<One>One<Two ID=\"2\">Two<Three ID=\"3\">Three</Three></Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test40() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val><val>Two</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test41() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p><span>example</span>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>\n<span>example</span>", document.html());
    }
    @Test public void test42() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div><div id=2><![CDATA[<html>\n <bar>multiline]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div1 = doc.getElementById("1");
        assertEquals("\n<html>\n <foo><&amp;", div1.text());
        assertEquals(0, div1.children().size());
        assertEquals(1, div1.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div1.outerHtml());

        CDataNode cdata1 = (CDataNode) div1.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata1.text());
        
        Element div2 = doc.getElementById("2");
        assertEquals("<html>\n <bar>multiline", div2.text());
        assertEquals(0, div2.children().size());
        assertEquals(1, div2.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"2\"><![CDATA[<html>\n <bar>multiline]]>\n</div>", div2.outerHtml());

        CDataNode cdata2 = (CDataNode) div2.textNodes().get(0);
        assertEquals("<html>\n <bar>multiline", cdata2.text());
    }
    @Test
    public void test43() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc><div id=1>Example</div>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc><div id=\"1\">Example</div>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals("http://foo.com/bar", doc.getElementById("2").absUrl("href"));
    }
    @Test
    public void test44() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc><val>Four</val>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test45() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script><div>Example</div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());
        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
        assertEquals("Example", doc.selectFirst("div").text());
    }
    @Test
    public void test46() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test47() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two<div>Example</div>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two<div>Example</div>",
                TextUtil.stripNewlines(doc.html()));
    }
@Test
public void test48() {
    Appendable accum = new StringBuilder();
    Document doc = new Document("");
    doc.outerHtmlTail(accum, 0, new Document.OutputSettings());
    assertEquals("", accum.toString());
}
@Test
public void test49() {
    Appendable accum = new StringBuilder();
    Document doc = new Document("");
    doc.outerHtmlTail(accum, -1, new Document.OutputSettings());
    assertEquals("", accum.toString());
}
@Test
public void test50() {
    Appendable accum = new StringBuilder();
    Document doc = new Document("");
    doc.outerHtmlTail(accum, 1, new Document.OutputSettings());
    assertEquals("", accum.toString());
}
@Test
public void test51() {
    Appendable accum = new StringBuilder();
    Document doc = new Document("");
    Document.OutputSettings outputSettings = new Document.OutputSettings();
    outputSettings.prettyPrint(true);
    doc.outerHtmlTail(accum, 0, outputSettings);
    assertEquals("\n", accum.toString());
}
@Test
public void test52() {
    Appendable accum = new StringBuilder();
    Document doc = new Document("");
    Document.OutputSettings outputSettings = new Document.OutputSettings();
    outputSettings.prettyPrint(true);
    doc.outerHtmlTail(accum, 1, outputSettings);
    assertEquals("\n", accum.toString());
}
    @Test
    public void test53() {
        String data = "";
        assertEquals(false, isXmlDeclaration(data));
    }
    @Test
    public void test54() {
        String data = "!";
        assertEquals(true, isXmlDeclaration(data));
    }
    @Test
    public void test55() {
        String data = "?xml";
        assertEquals(true, isXmlDeclaration(data));
    }
    @Test
    public void test56() {
        String data = "!DOCTYPE";
        assertEquals(true, isXmlDeclaration(data));
    }
    @Test
    public void test57() {
        String data = "This is a long data";
        assertEquals(false, isXmlDeclaration(data));
    }
    @Test
    public void test58() {
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
    public void test59() {
        String xml = "<?XML version='2' encoding='UTF-16' something='other'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"other\"?>", doc.outerHtml());
    }
    @Test
    public void test60() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test public void test61() {
        String html = "<?xml encoding='UTF-16' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test62() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-2", doc.charset().name());
        assertEquals("<?xml version=\"2.0\" encoding=\"ISO-8859-2\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test63() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
}