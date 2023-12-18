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
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test
    public void test1() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test2() {
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
    public void test3() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test4() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test5() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test6() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test7() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test8() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test9() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test public void test10() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test11() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test12() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test13() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test14() {
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
    public void test15() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test16() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test17() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test18() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test19() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test20() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test21() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc><p></p>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(4, nodes.size());

        assertEquals("http://example.com/bar", nodes.get(0).absUrl("href"));
        assertEquals("doc", nodes.get(0).nodeName());
        assertEquals("p", nodes.get(3).nodeName());
    }
    @Test
    public void test22() {
        String xml = "<?xml version='2' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test23() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;>Test</foo><bar /></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;>Test", div.text());
        assertEquals(1, div.children().size());
        assertEquals(2, div.childNodeSize()); // one element, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;>Test]]></div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;>Test", cdata.text());
    }
    @Test
    public void test24() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://example.com/bar");
    }
    @Test
    public void test25() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</p></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://example.com/");
        assertEquals("<doc><val>One<val>Two<p></p></val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
@Test 
public void test26() {
    String xml = "</one src='/foo/>' ?>Two<three><four /></three>";
    List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
    assertNull(nodes.get(0));
}
@Test 
public void test27() {
    String xml = "<?xml version='2' encoding='UTF-8' something='else'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("2", decl.attr("version"));
    assertNull(decl.attr("encoding"));
    assertNull(decl.attr("something"));
}
@Test 
public void test28() {
    String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

    Element div = doc.getElementById("1");
    assertNull(div.text());
    assertNull(div.children());
    assertNull(div.childNodeSize());

    assertNull(doc.textNodes().get(0));
}
@Test 
public void test29() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertNull(doc.html());
    assertEquals("http://foo.com/bar", doc.getElementById("2").absUrl("href"));
}
@Test 
public void test30() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test 
public void test31() {
    String html = "<html encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-8\"?> <body>One</body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
}
@Test 
public void test32() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertNull(doc.html());
}
@Test 
public void test33() {
    String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
}
@Test 
public void test34() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertNull(doc.charset().name());
    assertEquals("<?xml version=\"2.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
}
@Test 
public void test35() {
    String html = "<script> var a=\"<\"; var b=\">\"; </script>";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<script> var a=\"\n <!--<\"; var b=\">-->\"; </script>", doc.html()); 
}
@Test 
public void test36() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</Div><p></p>", "", parser);
    assertNull(document.html());
}
@Test 
public void test37() {
    String xml = "<CHECK>One</CHECK><TEST ID=2>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertNull(doc.html());
}
@Test 
public void test38() {
    Document htmlDoc = Jsoup.parse("<br>one</br>");
    assertEquals("<br>one\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
    assertNull(xmlDoc.html());
}
@Test public void test39() {
    Document doc = Jsoup.parse("x", "", Parser.xmlParser());
    assertEquals(Syntax.html, doc.outputSettings().syntax());
}
@Test
public void test40() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
    assertNull(doc.html());
}
@Test
public void test41() {
    String xml = "<?xml version='2.0'><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertNull(doc.select("val").text());
}
@Test
public void test42() {
    String xml = "<TEST ID=2>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertNull(TextUtil.stripNewlines(doc.html()));
}
@Test
public void test43() {
    String xml = "<One>One</One>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Elements one = doc.select("One");
    assertNull(one.html());
}
@Test public void test44() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertNull(doc.outerHtml());

    assertNull(doc.selectFirst("script").text());
}
@Test
public void test45() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertNull(doc.html());
}
@Test
public void test46() {
    String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<check>One</check><test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test47() {
    String xml = "<check>One</check><TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<check>One</check><test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test48() {
    String xml = "<check>One</check><test id=1>Check</test>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<check>One</check><test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test49() {
    // test: </val> closes Two, </bar> ignored
    String xml = "<doc><val>One<val>Two<val>Three</val></val><bar></bar>Four</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two<val>Three</val></val></val><bar></bar>Four</doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test50() {
    // html will force "<BR>one</BR>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<BR>one</BR>", "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<br>one\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<BR>one</BR>", "", Parser.xmlParser());
    assertEquals("<br>one</br>", xmlDoc.html());
}
@Test
public void test51() {
    Document document = Document.createShell("");
    document.outputSettings().syntax(Syntax.xml);
    document.charset(Charset.forName(""));
    assertEquals("<?xml version=\"1.0\" encoding=\"\"?>\n" +
        "<html>\n" +
        " <head></head>\n" +
        " <body></body>\n" +
        "</html>", document.outerHtml());
}
@Test
public void test52() {
    Document document = Document.createShell("");
    document.outputSettings().syntax(Syntax.xml);
    document.charset(Charset.forName("UTF-8"));
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<html>\n" +
        " <head></head>\n" +
        " <body></body>\n" +
        "</html>", document.outerHtml());
}
@Test
public void test53() {
    Document document = Document.createShell("");
    document.outputSettings().syntax(Syntax.xml);
    document.charset(Charset.forName("utf-8"));
    assertEquals("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
        "<html>\n" +
        " <head></head>\n" +
        " <body></body>\n" +
        "</html>", document.outerHtml());
}
@Test public void test54() {
    String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test public void test55() {
    String html = "<?XML encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test public void test56() {
    String html = "<?XmL encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test public void test57() {
    String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test58() {
    Document document = Document.createShell("");
    document.outputSettings().syntax(Syntax.xml);
    assertEquals("<?xml version=\"1.0\"?>\n" +
        "<html>\n" +
        " <head></head>\n" +
        " <body></body>\n" +
        "</html>", document.outerHtml());
}
@Test
public void test59() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<doc><val>One<val>Two</val></val>Three</doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test60() {
    Document document = Document.createShell("");
    document.outputSettings().syntax(Syntax.xml);
    document.outputSettings().syntax(Syntax.xml).syntaxDeclaration("version=\"1.1\" encoding=\"UTF-8\" standalone=\"yes\"");
    assertEquals("<?xml version=\"1.1\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
        "<html>\n" +
        " <head></head>\n" +
        " <body></body>\n" +
        "</html>", document.outerHtml());
}
@Test public void test61() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test public void test62() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</div><P></P>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test public void test63() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<DIV>test</DIV><P></P>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test public void test64() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</div><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test public void test65() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test<P></P></div>", "", parser);
    assertEquals("<div>\n test\n <p></p>\n</div>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test public void test66() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<DIV>test<p></P></DIV>", "", parser);
    assertEquals("<div>\n test\n <p></p>\n</div>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test public void test67() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test<div><p></p><div><h1></h1></div></div></div>", "", parser);
    assertEquals("<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>", document.html());
    // was failing -> toString() = "<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>"
}
@Test public void test68() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<DIV>test<Div><P></P><div><H1></H1></div></dIv></div>", "", parser);
    assertEquals("<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>", document.html());
    // was failing -> toString() = "<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>"
}
@Test public void test69() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<DIV>test<DIV><P></P><DIV><H1></H1></DIV></DIV></DIV>", "", parser);
    assertEquals("<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>", document.html());
    // was failing -> toString() = "<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>"
}
@Test public void test70() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test<div><p></p><div><h1></h1></div></div></div>", "", parser);
    assertEquals("<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>", document.html());
    // was failing -> toString() = "<div>\n test\n <div>\n  <p></p>\n  <div>\n   <h1></h1>\n  </div>\n </div>\n</div>"
}
@Test public void test71() {
    String xml = "<div id=1><![CDATA[]]></div>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

    Element div = doc.getElementById("1");
    assertEquals("", div.text());
    assertEquals(0, div.children().size());
    assertEquals(1, div.childNodeSize()); // no elements, one text node

    assertEquals("<div id=\"1\"><![CDATA[]]></div>", div.outerHtml());

    CDataNode cdata = (CDataNode) div.textNodes().get(0);
    assertEquals("", cdata.text());
}
@Test public void test72() {
    String xml = "<div id=1><![CDATA[[CDATA]]]></div>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

    Element div = doc.getElementById("1");
    assertEquals("[CDATA]", div.text());
    assertEquals(0, div.children().size());
    assertEquals(1, div.childNodeSize()); // no elements, one text node

    assertEquals("<div id=\"1\"><![CDATA[[CDATA]]]></div>", div.outerHtml());

    CDataNode cdata = (CDataNode) div.textNodes().get(0);
    assertEquals("[CDATA]", cdata.text());
}
@Test
public void test73() {
    String xml = "<doc ID=2 HREF='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc ID=\"2\" HREF=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("HREF"), "http://foo.com/bar");
}
@Test
public void test74() {
    String xml = "<dOc ID=2 HREF='/bar'>Foo <bR /><lInk>One</lInk><LinK>Two</LinK></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc ID=\"2\" HREF=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("HREF"), "http://foo.com/bar");
}
@Test
public void test75() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test76() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo(); \n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  foo(); \n//", doc.selectFirst("script").text());
}
@Test
public void test77() {
    String xml = "<script type=\"text/javascript\">\n\t//<![CDATA[\n\n\t\tfoo(); \t\n\t//]]>\n</script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n\t\tfoo(); \t\n\t//", doc.selectFirst("script").text());
}
@Test
public void test78() {
    String xml = "<script type=\"text/javascript\">\n   //<![CDATA[\n\n\t\tfoo(); \n   //]]>\n</script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("\n\n\t\tfoo(); \n   ", doc.selectFirst("script").text());
}
@Test
public void test79() {
    String xml = "<script type=\"text/javascript\">\n\t //<![CDATA[ \n\n\t\tfoo(); \n\t //]]>\n</script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals(" \n\n\t\tfoo(); \n ", doc.selectFirst("script").text());
}
@Test
public void test80() {
    String html = "<img src= asdf onerror=\"alert(1)\" x=";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
}
@Test
public void test81() {
    String html = "<img src=asdf onerror=\"alert(1)\" x= y= z= /><p></p>";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" y=\"\" z=\"\" /><p></p>", xmlDoc.html());
}
@Test
public void test82() {
    String xml = "<!DOCTYPE HTML>\n<!-- a comment -->\nOne\n<qux />\nTwo";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML>\n<!-- a comment -->\nOne\n<qux />\nTwo",
            TextUtil.stripNewlines(doc.html()));
}
    @Test
    public void test83() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test84() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test85() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test86() {
        Document document = Document.createShell("");
        document.outputSettings().syntax(Syntax.xml);
        document.charset(Charset.forName("utf-8"));
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<html>\n" +
            " <head></head>\n" +
            " <body></body>\n" +
            "</html>", document.outerHtml());
    }
    @Test public void test87() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test88() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test89() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test90() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test91() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test92() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test93() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test94() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test public void test95() {
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
    public void test96() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test97() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test98() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test99() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
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
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("", decl.attr("version"));
        assertEquals("", decl.attr("encoding"));
        assertEquals("", decl.attr("something"));
        assertEquals("version=\"\" encoding=\"\" something=\"\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"\" encoding=\"\" something=\"\"?>", decl.outerHtml());
    }
    @Test
    public void test102() {
        String xml = "<?xml version='1.0' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test103() {
        String xml = "<?xml version='1' encoding='UTF-8'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test104() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test105() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test106() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<\"; var b=\">\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--<\"; var b=\">-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test107() {
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
    public void test108() {
        String xml = "<?xml version='' encoding='' something=''>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"\" encoding=\"\" something=\"\"?>", doc.outerHtml());
    }
    @Test
    public void test109() {
        String xml = "<?xml version='1.0' encoding='UTF-8'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test110() {
        String html = "<?xml encoding='UTF-8' ?><body></body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test111() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-16", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test112() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=<< ; var b= <href= >hello ; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--<<\" ; var b=\"<href= >hello\" ; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test113() {
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
    public void test114() {
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
    public void test115() {
        String xml = "<?xml encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test116() {
        String xml = "<?xml version='1' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test117() {
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test118() {
        String xml = "<?XML version='1' encoding='ISO-8859-1' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"ISO-8859-1\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test119() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test120() {
        String xml = "<?xml encoding='ISO-8859-1'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test121() {
        String xml = "<?xml><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test122() {
        String xml = "<?xml version='1'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test123() {
        String html = "<?xml version='2' encoding='UTF-8'?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test124() {
        String html = "<?xml version='1' encoding='ISO-8859-1'?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test125() {
        String html = "<?xml encoding='UTF-8'?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test126() {
        String html = "<?xml version='1'?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test127() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test128() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test129() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test130() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test131() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test132() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
}