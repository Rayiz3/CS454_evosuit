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
        assertEquals("", doc.html());
    }
    @Test
    public void test1() {
        String xml = "<!-- This is a comment -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("#comment", doc.childNode(0).nodeName());
        assertEquals("<!-- This is a comment -->", doc.html());
    }
    @Test
    public void test2() {
        String xml = "<![CDATA[<tag>value</tag>]]>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("#cdata", doc.childNode(0).nodeName());
        assertEquals("<tag>value</tag>", ((CDataNode)doc.childNode(0)).text());
        assertEquals("<![CDATA[<tag>value</tag>]]>", doc.html());
    }
    @Test
    public void test3() {
        String xml = "<tag>&amp;value;</tag>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("&value;", doc.select("tag").text());
        assertEquals("<tag>&amp;value;</tag>", doc.html());
    }
@Test
public void test4() {
    String expectedResult = "";
    String actualResult = getData("");
    assertEquals(expectedResult, actualResult);
}
@Test
public void test5() {
    String actualResult = getData(null);
    assertNull(actualResult);
}
@Test
public void test6() {
    String expectedResult = "coreValue";
    String actualResult = getData("coreValue");
    assertEquals(expectedResult, actualResult);
}
@Test
public void test7() {
    String expectedResult = "!@#$%^&*()";
    String actualResult = getData("!@#$%^&*()");
    assertEquals(expectedResult, actualResult);
}
    @Test
    public void test8() {
        String xml = "<!DOCTYPE HTML><!-- another comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test9() {
        String html = "<img src=asdf onerror=\"alert(1)\" y=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" y=\"\" />", xmlDoc.html());
    }
    @Test
    public void test10() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>two</br>");
        assertEquals("<br>two\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>three</br>", "", Parser.xmlParser());
        assertEquals("<br>three</br>", xmlDoc.html());
    }
    @Test
    public void test11() {
        String xml = "<Two>Two</Two>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("Two");
        one.append("<Three ID=3>Three</Three>");
        assertEquals("<Two>Two<Three ID=\"3\">Three</Three></Two>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test12() {
        String xml = "<doc><val>Two<val>Three</val></bar>Four</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>Two<val>Three</val>Four</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
@Test
public void test13() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
    // Test with different URL
    doc = tb.parse(xml, "http://example.com/");
    assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test14() {
    String html = "<img src=asdf onerror=\"alert(1)\" x=";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    // Test with different tag attributes
    html = "<img src=abc onerror=\"alert(1)\" x=";
    xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<img src=\"abc\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
}
@Test
public void test15() {
    // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<br>one</br>");
    assertEquals("<br>one\n<br>", htmlDoc.body().html());
    // Test with different tag name
    htmlDoc = Jsoup.parse("<hr>one</hr>");
    assertEquals("<hr>one\n</hr>", htmlDoc.body().html());
}
@Test
public void test16() {
    String xml = "<One>One</One>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Elements one = doc.select("One");
    one.append("<Two ID=2>Two</Two>");
    assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    // Test with different tag name and attribute
    xml = "<Test ID=1>Check</Test>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Elements test = doc.select("Test");
    test.append("<Two ID=2>Another Two</Two>");
    assertEquals("<Test ID=\"1\">Check<Two ID=\"2\">Another Two</Two></Test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test17() {
    String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    // Test with different URL and tag values
    doc = tb.parse(xml, "http://example.com/");
    assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("href"), "http://example.com/bar");
}
@Test
public void test18() {
    String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    // Test with different tag names and attribute values
    xml = "<verify>The test is successful</verify><Control ID=1>Monitor</Control>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<verify>The test is successful</verify><Control ID=\"1\">Monitor</Control>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test19() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    // Test with different versions and encodings
    xml = "<?XML version='2' encoding='UTF-16' something='new'?>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"new\"?>", doc.outerHtml());
}
@Test public void test20() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // Test with different outer tags
    document = Jsoup.parse("<myDiv>test</myDiv><p></p>", "", parser);
    assertEquals("<myDiv>\n test\n</myDiv>\n<p></p>", document.html());
}
@Test
public void test21() {
    String xml = "<TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    // Test with different tag name and attribute value
    xml = "<Validation ID=1>Error</Validation>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<validation id=\"1\">Error</validation>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test22() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
    // Provide invalid URL
    doc = Jsoup.parse(inStream, null, "http://example.com", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test23() {
    Document document = Document.createShell("");
    document.outputSettings().syntax(Syntax.xml);
    document.charset(Charset.forName("utf-8"));
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<html>\n" +
        " <head></head>\n" +
        " <body></body>\n" +
        "</html>", document.outerHtml());
    // Test with different charset
    document.charset(Charset.forName("ISO-8859-1"));
    assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
        "<html>\n" +
        " <head></head>\n" +
        " <body></body>\n" +
        "</html>", document.outerHtml());
}
@Test
public void test24() {
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
    // Test with different URL and tag name
    doc = Jsoup.parse(xml, "http://example.com/", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test public void test25() {
    String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

    Element div = doc.getElementById("1");
    assertEquals("<html>\n <foo><&amp;", div.text());
    assertEquals(0, div.children().size());
    assertEquals(1, div.childNodeSize()); // no elements, one text node

    assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

    CDataNode cdata = (CDataNode) div.textNodes().get(0);
    assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    // Test with different CDATA content
    xml = "<div id=1><![CDATA[\n<hr>\n <bar><&amp;]]></div>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser());

    div = doc.getElementById("1");
    assertEquals("<hr>\n <bar><&amp;", div.text());
    assertEquals(0, div.children().size());
    assertEquals(1, div.childNodeSize()); // no elements, one text node

    assertEquals("<div id=\"1\"><![CDATA[\n<hr>\n <bar><&amp;]]>\n</div>", div.outerHtml());

    cdata = (CDataNode) div.textNodes().get(0);
    assertEquals("\n<hr>\n <bar><&amp;", cdata.text());
}
@Test
public void test26() {
    String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("1", decl.attr("version"));
    assertEquals("UTF-8", decl.attr("encoding"));
    assertEquals("else", decl.attr("something"));
    assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    // Test with different declaration attributes
    xml = "<?xml version='1.1' encoding='UTF-8' something='new'?><val>One</val>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser());
    decl = (XmlDeclaration) doc.childNode(0);
    assertEquals("1.1", decl.attr("version"));
    assertEquals("UTF-8", decl.attr("encoding"));
    assertEquals("new", decl.attr("something"));
    assertEquals("version=\"1.1\" encoding=\"UTF-8\" something=\"new\"", decl.getWholeDeclaration());
    assertEquals("<?xml version=\"1.1\" encoding=\"UTF-8\" something=\"new\"?>", decl.outerHtml());
}
@Test public void test27() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    // Test with different CDATA content
    xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
}
@Test public void test28() {
    String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
    // Test with different XML declaration and comment
    html = "<?xml version='1.1' encoding='UTF-16' ?><body>Two</body><!-- new -->";
    doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml version=\"1.1\" encoding=\"UTF-16\"?> <body> Two </body> <!-- new -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test29() {
    // test: </val> closes Two, </bar> ignored
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
    // Test with different tag names and self closing tag
    xml = "<doc><first>One</secnd>Proxy<val /></bar>Oxygen</doc>";
    tb = new XmlTreeBuilder();
    doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><first>One</secnd>Proxy<val />Oxygen</doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test30() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("ISO-8859-1", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
        TextUtil.stripNewlines(doc.html()));
    // Test with different encoding
    doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals(doc.charset().name(), "UTF-8");
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
        TextUtil.stripNewlines(doc.html()));
}
    @Test
    public void test31() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<\"; var b=\">\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test32() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test33() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test34() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());
    }
    @Test public void test35() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test36() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test37() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<!DOCTYPE html> <html><head><title>Test</title></head><body><script> var a=\"<?\"; var b=\"?>\"; </script></body></html>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<!DOCTYPE html>\n <html>\n  <head>\n   <title>\n    Test\n   </title>\n  </head>\n  <body>\n   <script>\n    var a=\"\n <!--?\";\n    var b=\"?-->\n    \", \u2028;\n   </script>\n  </body>\n </html>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test38() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>\n <val> One </val>", doc.outerHtml());
    }
    @Test
    public void test39() {
        String xml = "<requestSDetailedData xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.datacontract.org/2004/07/DAL.DTO.Request_Bittrex.Indexes\"><CyptoCurrencyName>BTC-CRW</CyptoCurrencyName></requestSDetailedData>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("BTC-CRW", doc.select("CyptoCurrencyName").text());
    }
    @Test
    public void test40() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?><val>One</val>", decl.outerHtml());
    }
    @Test public void test41() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?>\n <body> One </body>\n <!-- comment -->", doc.outerHtml());
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test42() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><data>äöåéü</data>", TextUtil.stripNewlines(doc.html()));
    }
}