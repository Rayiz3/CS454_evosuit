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
        String xml = "<fragment />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());

        // Change the xml to not contain a <fragment> tag
        xml = "<div />";
        nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
    @Test
    public void test1() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
        
        // Change the xml to not contain uppercase letters
        xml = "<test id=1>Check</test>";
        doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test2() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
        
        // Change the XML file to have a different structure
        xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test2.xml").toURI());
        inStream = new FileInputStream(xmlFile);
        doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>Four<val>Five</val>Six</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
@Test
public void test3() {
    String xml = "<TEST ID=1>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));

    // Change the xml to not have an ID attribute
    xml = "<TEST>Check</TEST>";
    doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test>Check</test>", TextUtil.stripNewlines(doc.html()));
}
    @Test public void test4() {
        String xml = "<one src='/bar/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertNull(nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test5() {
        Document doc = Jsoup.parse("y", "", Parser.xmlParser());
        assertEquals(Syntax.html, doc.outputSettings().syntax());
    }
    @Test
    public void test6() {
        String xml = "<TEST ID=2>Check2</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check2</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test7() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test8() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test9() {
        String xml = "<doc id=3 href='/baz'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://baz.com/");
        assertEquals("<doc id=\"3\" href=\"/baz\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://baz.com/baz");
    }
    @Test
    public void test10() {
        String xml = "<?xml version='2' encoding='UTF-16' something='different'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-16", decl.attr("encoding"));
        assertEquals("different", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-16\" something=\"different\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-16\" something=\"different\"?>", decl.outerHtml());
    }
    @Test
    public void test11() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-16", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test12() {
        String xml = "<One>Two</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=3>Four</Two>");
        assertEquals("<One>Two<Two ID=\"3\">Four</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test13() {
        String xml = "<CHECK>Two</CHECK><TEST ID=2>Check2</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"2\">Check2</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test14() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test15() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test16() {
        String xml = "<!DOCTYPE HTML><!-- another comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test17() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test18() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test19() {
        String xml = "<?XML version='2' encoding='UTF-16' something='different'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-16\" something=\"different\"?>", doc.outerHtml());
    }
    @Test public void test20() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test21() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test22() {
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
    @Test public void test23() {
        String html = "<?xml encoding='UTF-16' ?><body>Two</body><!-- another comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-16\"?> <body> Two </body> <!-- another comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
@Test
public void test24() {
    String xml = "<!DOCTYPE HTML><!-- -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test25() {
    String html = "<img src= onerror=\"alert(1)\" x=";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<img src=\"\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
}
@Test
public void test26() {
    // html will force "<br id=1>one</br id=1>" to logically "<br id=1 />One<br id=1 />". XML should be stay "<br id=1>one</br id=1>" -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<br id=1>one</br id=1>");
    assertEquals("<br id=\"1\">one\n<br id=\"1\">", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br id=1>one</br id=1>", "", Parser.xmlParser());
    assertEquals("<br id=\"1\">one</br>", xmlDoc.html());
}
@Test
public void test27() {
    String xml = "<One ID=1>One</One>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Elements one = doc.select("One");
    one.append("<Two ID=2>Two</Two>");
    assertEquals("<One ID=\"1\">One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test28() {
    String xml = "<doc id=2 href='/bar'>Foo <br id=1 /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br id=\"1\" /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
}
@Test
public void test29() {
    String xml = "<CHECK ID=1>One</CHECK><TEST ID=2>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK ID=\"1\">One</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test30() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else' key=1?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\" key=\"1\"?><doc>One</doc>", doc.outerHtml());
}
@Test
    public void test31() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test32() {
        String xml = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test33() {
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test34() {
        String xml = "<One>One</One>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements one = doc.select("One");
        one.append("<Two ID=2>Two</Two>");
        assertEquals("<One>One<Two ID=\"2\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test35() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test36() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test37() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test38() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    }
    @Test
    public void test39() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test40() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test41() {
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
    public void test42() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test43() {
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
    public void test44() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]]></div>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize());

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test
    public void test45() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test46() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test47() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test48() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test49() {
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
    public void test50() {
        String xml = "<newDoc><val>One</val></newDoc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<newDoc><val>One</val></newDoc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test51() {
        String xml = "<doc id=3 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"3\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test52() {
        MyObject obj = new MyObject("<");
        assertFalse(obj.isXmlDeclaration());
    }
    @Test
    public void test53() {
        MyObject obj = new MyObject("!");
        assertTrue(obj.isXmlDeclaration());
    }
    @Test
    public void test54() {
        MyObject obj = new MyObject("?");
        assertTrue(obj.isXmlDeclaration());
    }
    @Test
    public void test55() {
        MyObject obj = new MyObject("Hello");
        assertFalse(obj.isXmlDeclaration());
    }
    @Test
    public void test56() {
        // https://github.com/jhy/jsoup/issues/1139
        String xml = "<?xml version='1.0' encoding='UTF-8'?><script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
    }
    @Test
    public void test57() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test58() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test59() {
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
    public void test60() {
        String xml = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test61() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
}