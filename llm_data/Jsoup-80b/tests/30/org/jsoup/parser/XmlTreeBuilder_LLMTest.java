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
        String xml = "<?Xml version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test1() {
        String xml = "<?xml?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml?>", doc.outerHtml());
    }
    @Test
    public void test2() {
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
    public void test3() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><invalid>Invalid</invalid>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?><invalid>Invalid</invalid>", doc.outerHtml());
    }
@Test
public void test4() {
    String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
}
@Test
public void test5() {
    String xml = "<?XML version='1' encoding='ISO-8859-1' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"ISO-8859-1\" something=\"else\"?>", doc.outerHtml());
}
@Test
public void test6() {
    String xml = "<?XML version='1' encoding='UTF-8'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\"?>", doc.outerHtml());
}
@Test
public void test7() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else' another='attribute'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\" another=\"attribute\"?>", doc.outerHtml());
}
@Test
public void test8() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n  foo();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  bar();\n  foo();\n//", doc.selectFirst("script").text());
}
@Test
public void test9() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("", doc.selectFirst("script").text());
}
@Test
public void test10() {
    String xml = "<!DOCTYPE HTML><!-- a different comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- a different comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test11() {
    // test: </val> closes Three, </bar> ignored
    String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test12() {
    String xml = "<?xml version='2.0'><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("One", doc.select("val").text());
}
@Test
public void test13() {
    String xml = "<?xml version='1.0' encoding='ISO-8859-1'><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("One", doc.select("val").text());
}
@Test
public void test14() {
    String xml = "<doc id=3 href='/baz'>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"3\" href=\"/baz\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("3").absUrl("href"), "http://foo.com/baz");
}
@Test
public void test15() {
    String xml = "<doc id=2 href='/qux'>Foo <br /><link>One</link><link>Two</link></doc>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<doc id=\"2\" href=\"/qux\">Foo <br /><link>One</link><link>Two</link></doc>",
            TextUtil.stripNewlines(doc.html()));
    assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/qux");
}
@Test
public void test16() {
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
public void test17() {
    String html = "<img src=qwer onerror=\"alert(1)\" z=";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<img src=\"qwer\" onerror=\"alert(1)\" z=\"\" />", xmlDoc.html());
}
@Test
public void test18() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://bar.com", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test19() {
    // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br>" -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<br>one</br>");
    assertEquals("<br>one\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
    assertEquals("<br>one</br>", xmlDoc.html());
}
@Test
public void test20() {
    String html = "<?xml encoding='ISO-8859-1' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"ISO-8859-1\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test21() {
    String html = "<?xml metadata='header' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml metadata=\"header\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test22() {
    String xml = "<CHECK>One</CHECK><TEST ID=2>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<CHECK>One</CHECK><TEST ID=\"2\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test23() {
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
@Test
public void test24() {
    Document doc = Jsoup.parse("y", "", Parser.xmlParser());
    assertEquals(Syntax.xml, doc.outputSettings().syntax());
}
@Test
public void test25() {
    String xml = "<TEST ID=2>Check</TEST>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
    assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test26() {
    Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
    Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
    assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
}
@Test
public void test27() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("UTF-8", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <data>äöåéü</data>",
        TextUtil.stripNewlines(doc.html()));
}
@Test
public void test28() {
    String xml = "<doc><val>One<val>Three</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://bar.com/", Parser.xmlParser());
    assertEquals("<doc><val>One<val>Three</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test29() {
    String xml = "";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("", doc.html());
}
@Test
public void test30() {
    String xml = "   ";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("", doc.html());
}
@Test
public void test31() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->One </doc>Two";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<!DOCTYPE HTML><!-- a comment -->One </doc>Two", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test32() {
    String xml = "<?xml version=1 encoding='UTF-8'?><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<val>One</val>", doc.html());
}
@Test
public void test33() {
    String xml = "<html><head><title>Test</title></head><body><h1>Hello</h1><p>World</p></body></html>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<html><head><title>Test</title></head><body><h1>Hello</h1><p>World</p></body></html>", TextUtil.stripNewlines(doc.html()));
}
    @Test
    public void test34() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "");
        assertEquals("", doc.html());
    }
    @Test
    public void test35() {
        String xml = "   ";
        Document doc = Jsoup.parse(xml, "");
        assertEquals("", doc.html());
    }
    @Test
    public void test36() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One </doc>Two";
        Document doc = Jsoup.parse(xml, "");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One </doc>Two", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test37() {
        String xml = "<?xml version=1 encoding='UTF-8'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "");
        assertEquals("<val>One</val>", doc.html());
    }
    @Test
    public void test38() {
        String xml = "<html><head><title>Test</title></head><body><h1>Hello</h1><p>World</p></body></html>";
        Document doc = Jsoup.parse(xml, "");
        assertEquals("<html><head><title>Test</title></head><body><h1>Hello</h1><p>World</p></body></html>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test39() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>"; // change the value between '<?' and '?>'
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test
    public void test40() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test41() {
        String xml = "<?xml version='1.5'>"; // change the version value to a non-integer value
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(null, doc.selectFirst("val"));  // or any other assertion checking the effect of invalid XML declaration
    }
    @Test
    public void test42() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/"); // change the value of 'http://foo.com/' to an invalid URL
        assertEquals(null, doc.selectFirst("doc")); // or any other assertion checking the effect of invalid URL
    }
    @Test
    public void test43() {
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test44() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  bar();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test45() {
        String xml = "<!DOCTYPE HTML><!-- another comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test46() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Four<value /></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc><val>One<val>Two</val>Four<value /></val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test47() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("Two", doc.select("val").text());
    }
    @Test
    public void test48() {
        String xml = "<doc id=3 href='/baz'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://bar.com/");
        assertEquals("<doc id=\"3\" href=\"/baz\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("3").absUrl("href"), "http://bar.com/baz");
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
        String html = "<img src=qwerty onerror=\"alert(2)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"qwerty\" onerror=\"alert(2)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test51() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://baz.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Four<value /></val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test52() {
        // html will force "<br>four</br>" to logically "<br />Four<br />". XML should be stay "<br>four</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>four</br>");
        assertEquals("<br>four\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>four</br>", "", Parser.xmlParser());
        assertEquals("<br>four</br>", xmlDoc.html());
    }
    @Test public void test53() {
        String html = "<?xml encoding='UTF-8' ?><body>Two</body><!-- another comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> Two </body> <!-- another comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test54() {
        String xml = "<CHECK>Two</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>Two</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test55() {
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
    @Test public void test56() {
        String xml = "<one src='/baz/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.org/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.org/baz/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test58() {
        String xml = "<TEST ID=2>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"2\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test59() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>Four</DIV><p></p>", "", parser);
        assertEquals("<div>\n Four\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n Four\n <p></p>\n</div>"
    }
    @Test
    public void test60() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://baz.com/", Parser.xmlParser());
        assertEquals("ASCII", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ASCII\"?> <data>def</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test61() {
        String xml = "<doc><val>One<val>Two</val></bar>Four<value /></doc>";
        Document doc = Jsoup.parse(xml, "http://baz.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Four<value /></val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
@Test
public void test62() {
    Node node = null;
    // Call insertNode method with null Node
    XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
    xmlTreeBuilder.insertNode(node);
}
@Test
public void test63() {
    Node node = new Node("<element>");
    // Call insertNode method with valid Node
    XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
    xmlTreeBuilder.insertNode(node);
}
@Test
public void test64() {
    Node node = new Node("");
    // Call insertNode method with empty Node
    XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
    xmlTreeBuilder.insertNode(node);
}
@Test
public void test65() {
    Node node = new TextNode("text node");
    // Call insertNode method with TextNode
    XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
    xmlTreeBuilder.insertNode(node);
}
@Test
public void test66() {
    Node node = new Element("element");
    // Call insertNode method with ElementNode
    XmlTreeBuilder xmlTreeBuilder = new XmlTreeBuilder();
    xmlTreeBuilder.insertNode(node);
}
    @Test
    public void test67() {
        // Change the version number to 2
        String xml = "<?XML version='2' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"2\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test68() {
        // Remove the new lines in the CDATA section
        String xml = "<script type=\"text/javascript\">//<![CDATA[  foo();  //]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//  foo();  //", doc.selectFirst("script").text());
    }
    @Test
    public void test69() {
        // Add a space after the HTML DOCTYPE declaration
        String xml = "<!DOCTYPE HTML ><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML ><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test70() {
        // Remove the opening tag of </bar>
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test71() {
        // Add a space before closing the XML declaration
        String xml = "<?xml version='1.0' ><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test72() {
        // Add a space before the forward slash of <br />
        String xml = "<doc id=2 href='/bar'>Foo <br/ ><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test73() {
        // Remove the encoding attribute from the XML declaration
        String xml = "<?xml version='1' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test74() {
        // Remove the equals sign after x
        String html = "<img src=asdf onerror=\"alert(1)\" x";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test75() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        // Change the file extension to .html
        InputStream inStream = new FileInputStream(xmlFile.getAbsolutePath().replace(".xml", ".html"));
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test76() {
        // Remove the forward slash in the self-closing tag <br>
        Document htmlDoc = Jsoup.parse("<br>one<br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one<br>", "", Parser.xmlParser());
        assertEquals("<br>one<br>", xmlDoc.html());
    }
    @Test public void test77() {
        // Remove the space between <?xml and encoding
        String html = "<?xmlencoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xmlencoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test78() {
        // Add double quotes around the ID attribute value
        String xml = "<TEST ID=\"1\">Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test79() {
        // Add a closing div tag at the end
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p></div>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p></div>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test80() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        // Change the character encoding to UTF-16
        assertEquals("UTF-16", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-16\"?> <data>äöåéü</data>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test81() {
        // Remove the closing angle bracket at the end of <bar>
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test82() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    }
    @Test public void test83() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test84() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test85() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test86() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test87() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals(doc.getElementById("2").absUrl("href"), "http://foo.com/bar");
    }
    @Test
    public void test88() {
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
    public void test89() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test90() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test91() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test92() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test93() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test94() {
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
    @Test public void test95() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test public void test96() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test97() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test public void test98() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
        // was failing -> toString() = "<div>\n test\n <p></p>\n</div>"
    }
    @Test
    public void test99() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test100() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test101() {
        String xml = "<?xml version='0.9'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test102() {
        String xml = "<?xml version='2.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
    }
    @Test
    public void test103() {
        String xml = "<?xml version='2' encoding='UTF-16' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("2", decl.attr("version"));
        assertEquals("UTF-16", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"2\" encoding=\"UTF-16\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"UTF-16\" something=\"else\"?>", decl.outerHtml());
    }
    @Test
    public void test104() {
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
    public void test105() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test106() {
        String html = "<img src=asdf onerror=\"alert(1)\" x==/>";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test107() {
        String html = "<img src=asdf onerror=\"alert(1)\" x==='test==/>";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test108() {
        String html = "<img src=asdf onerror=\"alert(1)\" ";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" />", xmlDoc.html());
    }
    @Test
    public void test109() {
        String html = "<img src=asdf onerror='alert(1)";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals(html, xmlDoc.html());
    }
    @Test
    public void test110() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test111() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test-modified.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test112() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test-modified2.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test113() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());

        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test public void test114() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test public void test115() {
        String xml = "<div id=1><![CDATA[\n<html>\n <foo><&amp;]");
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Element div = doc.getElementById("1");
        assertEquals("<html>\n <foo><&amp;", div.text());
        assertEquals(0, div.children().size());
        assertEquals(1, div.childNodeSize()); // no elements, one text node

        assertEquals("<div id=\"1\"><![CDATA[\n<html>\n <foo><&amp;]]>\n</div>", div.outerHtml());

        CDataNode cdata = (CDataNode) div.textNodes().get(0);
        assertEquals("\n<html>\n <foo><&amp;", cdata.text());
    }
    @Test public void test116() {
        String xml = "<one src='/foo/' /><three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("<three>\n  <four></four>\n</three>", nodes.get(1).outerHtml());
    }
    @Test public void test117() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test118() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test119() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test120() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
        assertEquals("else", doc.select("XML").first().attr("something"));
    }
    @Test
    public void test121() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());
        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    }
    @Test
    public void test122() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
        assertEquals("HTML", doc.select("DOCTYPE").first().attr("HTML"));
    }
    @Test
    public void test123() {
        // test: </val> closes Two, </bar> ignored
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test124() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());
        assertEquals("1.0", doc.selectFirst("xml").attr("version"));
    }
    @Test
    public void test125() {
        String xml = "<doc id=2 href='/bar'>Foo <br /><link>One</link><link>Two</link></doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc id=\"2\" href=\"/bar\">Foo <br /><link>One</link><link>Two</link></doc>",
                TextUtil.stripNewlines(doc.html()));
        assertEquals("http://foo.com/bar", doc.getElementById("2").absUrl("href"));
    }
    @Test
    public void test126() {
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
    public void test127() {
        String html = "<img src=asdf onerror=\"alert(1)\" x=";
        Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"\" />", xmlDoc.html());
    }
    @Test
    public void test128() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test129() {
        // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
        Document htmlDoc = Jsoup.parse("<br>one</br>");
        assertEquals("<br>one\n<br>", htmlDoc.body().html());
    
        Document xmlDoc = Jsoup.parse("<br>one</br>", "", Parser.xmlParser());
        assertEquals("<br>one</br>", xmlDoc.html());
    }
    @Test
    public void test130() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());
    }
    @Test
    public void test131() {
        String xml = "<CHECK>One</CHECK><TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<CHECK>One</CHECK><TEST ID=\"1\">Check</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test132() {
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
    public void test133() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());
    
        assertEquals("http://example.com/foo/", nodes.get(0).absUrl("src"));
        assertEquals("one", nodes.get(0).nodeName());
        assertEquals("Two", ((TextNode)nodes.get(1)).text());
    }
    @Test
    public void test134() {
        Document doc = Jsoup.parse("x", "", Parser.xmlParser());
        assertEquals(Syntax.xml, doc.outputSettings().syntax());
    }
    @Test
    public void test135() {
        String xml = "<TEST ID=1>Check</TEST>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test id=\"1\">Check</test>", TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test136() {
        Parser parser = Parser.xmlParser().settings(ParseSettings.htmlDefault);
        Document document = Jsoup.parse("<div>test</DIV><p></p>", "", parser);
        assertEquals("<div>\n test\n</div>\n<p></p>", document.html());
    }
    @Test
    public void test137() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test138() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
        assertEquals("<doc><val>One<val>Two</val>Three</val></doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test139() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
        
        // Regression test with changed input
        String xml2 = "<?XML version='1' encoding='UTF-8' something='something else'?>";
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"something else\"?>", doc2.outerHtml());
    }
    @Test
    public void test140() {
        String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals(xml, doc.outerHtml());

        assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
        
        // Regression test with changed input
        String xml2 = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
        assertEquals("//\n\n  bar();\n//", doc2.selectFirst("script").text());
    }
    @Test
    public void test141() {
        String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
                TextUtil.stripNewlines(doc.html()));
        
        // Regression test with changed input
        String xml2 = "<!DOCTYPE HTML><!-- another comment -->Three <qux />Four";
        Document doc2 = tb.parse(xml2, "http://foo.com/");
        assertEquals("<!DOCTYPE HTML><!-- another comment -->Three <qux />Four",
                TextUtil.stripNewlines(doc2.html()));
    }
@Test
public void test142() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());
    
    // Regression test with changed input
    String xml2 = "<?XML version='1' encoding='UTF-8' something='something else'?>";
    Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"something else\"?>", doc2.outerHtml());
}
@Test
public void test143() {
    String xml = "<script type=\"text/javascript\">//<![CDATA[\n\n  foo();\n//]]></script>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals(xml, doc.outerHtml());

    assertEquals("//\n\n  foo();\n//", doc.selectFirst("script").text());
    
    // Regression test with changed input
    String xml2 = "<script type=\"text/javascript\">//<![CDATA[\n\n  bar();\n//]]></script>";
    Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
    assertEquals("//\n\n  bar();\n//", doc2.selectFirst("script").text());
}
@Test
public void test144() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
    
    // Regression test with changed input
    String xml2 = "<!DOCTYPE HTML><!-- another comment -->Three <qux />Four";
    Document doc2 = tb.parse(xml2, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- another comment -->Three <qux />Four",
            TextUtil.stripNewlines(doc2.html()));
}
    @Test
    public void test145() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val></bar>Three</doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test146() {
        String xml = "<doc><val>One<val>Two</val></bar>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val></bar>Three</doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test147() {
        String xml = "<doc><val>One<val>Two</val></val>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val></val>Three</doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test148() {
        String xml = "<doc><val>One<val>Two</val></val>Three</doc>";
        XmlTreeBuilder tb = new XmlTreeBuilder();
        Document doc = tb.parse(xml, "http://foo.com/");
        assertEquals("<doc><val>One<val>Two</val></val>Three</doc>",
                TextUtil.stripNewlines(doc.html()));
    }
    @Test
    public void test149() {
        String xml = "";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(0, nodes.size());
    }
    @Test
    public void test150() {
        String xml = "<one src='invalid-url' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());
        assertEquals("", nodes.get(0).absUrl("src"));
    }
    @Test
    public void test151() {
        String xml = "<one />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());
    }
    @Test
    public void test152() {
        String xml = "<one src='/foo/' />Two<three><four />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(3, nodes.size());
    }
    @Test
    public void test153() {
        String xml = "<one src='/foo/' />Two<three><four /></three>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "");
        assertEquals(3, nodes.size());
        assertEquals("/foo/", nodes.get(0).absUrl("src"));
    }
}