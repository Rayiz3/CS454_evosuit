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
        String xml = "<div><p>Hello</div></p>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertEquals("div", nodes.get(0).nodeName());
        assertEquals("p", nodes.get(1).nodeName());
    }
    @Test
    public void test1() {
        String xml = "<DIV><P>Hello</P></DIV>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertEquals("DIV", nodes.get(0).nodeName());
        assertEquals("P", nodes.get(1).nodeName());
    }
    @Test
    public void test2() {
        String xml = "";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertTrue(nodes.isEmpty());
    }
    @Test
    public void test3() {
        String xml = "<div><p>Good &amp; Bad</p></div>";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(2, nodes.size());

        assertEquals("div", nodes.get(0).nodeName());
        assertEquals("p", nodes.get(1).nodeName());
    }
    @Test
    public void test4() {
        String xml = "<img src='/img.jpg' data-url='http://example.com/' />";
        List<Node> nodes = Parser.parseXmlFragment(xml, "http://example.com/");
        assertEquals(1, nodes.size());

        assertEquals("/img.jpg", nodes.get(0).attr("src"));
        assertEquals("http://example.com/", nodes.get(0).absUrl("data-url"));
    }
    @Test
    public void test5() {
        String xml = "";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", doc.html());
    }
    @Test
    public void test6() {
        String xml = "<doc></doc>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<doc></doc>", doc.html());
    }
    @Test
    public void test7() {
        String xml = "<doc>One Two Three</doc>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<doc>One Two Three</doc>", doc.html());
    }
    @Test
    public void test8() {
        String xml = "<doc id='<![CDATA[123]]>'></doc>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<doc id=\"<![CDATA[123]]>\"></doc>", doc.html());
    }
    @Test
    public void test9() {
        String xml = "<?xml?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("", doc.html());
    }
@Test
public void test10() {
    String xml = "<!DOCTYPE HTML><!-- a comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML><!-- a comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test11() {
    String xml = "<div><!-- comment --></div>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<div><!-- comment --></div>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test12() {
    String xml = "<div></div>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<div></div>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test13() {
    String xml = "<html>One <qux />Two</html>";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<html>One <qux />Two</html>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test14() {
    String xml = "<!-- a comment -->";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!-- a comment -->", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test15() {
    String xml = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><!-- a comment -->One <qux />Two";
    XmlTreeBuilder tb = new XmlTreeBuilder();
    Document doc = tb.parse(xml, "http://foo.com/");
    assertEquals("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\"><!-- a comment -->One <qux />Two",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test16() {
    String html = "<img src=asdf onerror=\"alert(1)\" x= abc";
    Document xmlDoc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<img src=\"asdf\" onerror=\"alert(1)\" x=\"abc\" />", xmlDoc.html());
}
@Test
public void test17() {
    // html will force "<br>one</br>" to logically "<br />One<br />". XML should be stay "<br>one</br> -- don't recognise tag.
    Document htmlDoc = Jsoup.parse("<br attribute='value'>one</br>");
    assertEquals("<br attribute=\"value\">one\n<br>", htmlDoc.body().html());

    Document xmlDoc = Jsoup.parse("<br attribute='value'>one</br>", "", Parser.xmlParser());
    assertEquals("<br attribute=\"value\">one</br>", xmlDoc.html());
}
@Test
public void test18() {
    String xml = "<One attribute='value'>One</One>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    Elements one = doc.select("One");
    one.append("<Two ID=2 attribute='value'>Two</Two>");
    assertEquals("<One attribute=\"value\">One<Two ID=\"2\" attribute=\"value\">Two</Two></One>", TextUtil.stripNewlines(doc.html()));
}
@Test
public void test19() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-test.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.xmlParser());
    assertEquals("<doc attribute='value'><val attribute='value'>One<val attribute='value'>Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test20() {
    String xml = "<doc attribute='value'><val attribute='value'>One<val attribute='value'>Two</val></bar>Three</doc>";
    Document doc = Jsoup.parse(xml, "http://foo.com/", Parser.xmlParser());
    assertEquals("<doc attribute=\"value\"><val attribute=\"value\">One<val attribute=\"value\">Two</val>Three</val></doc>",
            TextUtil.stripNewlines(doc.html()));
}
@Test
public void test21() {
    // https://github.com/jhy/jsoup/issues/1139
    String html = "<script> var a=\"<??\"; var b=\"?>?\"; </script>";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment
}
@Test
public void test22() {
    String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("<?XML version=\"1\" encoding=\"utf-8\" something=\"else\"?>", doc.outerHtml());
}
@Test
public void test23() {
    String xml = "<?xml version='2.0'><val>One</val>";
    Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
    assertEquals("One", doc.select("val").text());
}
@Test
public void test24() {
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
public void test25() {
    String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
    Document doc = Jsoup.parse(html, "", Parser.xmlParser());
    assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
            StringUtil.normaliseWhitespace(doc.outerHtml()));
    assertEquals("#declaration", doc.childNode(0).nodeName());
    assertEquals("#comment", doc.childNode(2).nodeName());
}
@Test
public void test26() throws IOException, URISyntaxException {
    File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
    InputStream inStream = new FileInputStream(xmlFile);
    Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
    assertEquals("ISO-8858-1", doc.charset().name());
    assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>aeoaou</data>",
        TextUtil.stripNewlines(doc.html()));
}
    @Test
    public void test27() {
        // https://github.com/jhy/jsoup/issues/1139
        String html = "<script> var a=\"<?\"; var b=\"?>\"; </script>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<script> var a=\"\n <!--?\"; var b=\"?-->\"; </script>", doc.html()); // converted from pseudo xmldecl to comment

        // Additional test case - input without script tag
        String html2 = "var a=\"<?\"; var b=\"?>\";";
        Document doc2 = Jsoup.parse(html2, "", Parser.xmlParser());
        assertEquals("var a=\"<?\"; var b=\"?>\";", doc2.html());
    }
    @Test
    public void test28() {
        String xml = "<?XML version='1' encoding='UTF-8' something='else'?>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", doc.outerHtml());

        // Additional test case - different encoding attribute value
        String xml2 = "<?XML version='1' encoding='ISO-8859-1' something='else'?>";
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
        assertEquals("<?XML version=\"1\" encoding=\"ISO-8859-1\" something=\"else\"?>", doc2.outerHtml());
    }
    @Test
    public void test29() {
        String xml = "<?xml version='1.0'><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        assertEquals("One", doc.select("val").text());

        // Additional test case - different XML version
        String xml2 = "<?xml version='1.1'><val>One</val>";
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
        assertEquals("One", doc2.select("val").text());
    }
    @Test
    public void test30() {
        String xml = "<?xml version='1' encoding='UTF-8' something='else'?><val>One</val>";
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        XmlDeclaration decl = (XmlDeclaration) doc.childNode(0);
        assertEquals("1", decl.attr("version"));
        assertEquals("UTF-8", decl.attr("encoding"));
        assertEquals("else", decl.attr("something"));
        assertEquals("version=\"1\" encoding=\"UTF-8\" something=\"else\"", decl.getWholeDeclaration());
        assertEquals("<?xml version=\"1\" encoding=\"UTF-8\" something=\"else\"?>", decl.outerHtml());

        // Additional test case - different attribute value
        String xml2 = "<?xml version='2' encoding='ISO-8859-1' lang='en'?><val>One</val>";
        Document doc2 = Jsoup.parse(xml2, "", Parser.xmlParser());
        XmlDeclaration decl2 = (XmlDeclaration) doc2.childNode(0);
        assertEquals("2", decl2.attr("version"));
        assertEquals("ISO-8859-1", decl2.attr("encoding"));
        assertEquals("en", decl2.attr("lang"));
        assertEquals("version=\"2\" encoding=\"ISO-8859-1\" lang=\"en\"", decl2.getWholeDeclaration());
        assertEquals("<?xml version=\"2\" encoding=\"ISO-8859-1\" lang=\"en\"?>", decl2.outerHtml());
    }
    @Test
    public void test31() {
        String html = "<?xml encoding='UTF-8' ?><body>One</body><!-- comment -->";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> One </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc.outerHtml()));
        assertEquals("#declaration", doc.childNode(0).nodeName());
        assertEquals("#comment", doc.childNode(2).nodeName());

        // Additional test case - different body content
        String html2 = "<?xml encoding='UTF-8' ?><body>Two</body><!-- comment -->";
        Document doc2 = Jsoup.parse(html2, "", Parser.xmlParser());
        assertEquals("<?xml encoding=\"UTF-8\"?> <body> Two </body> <!-- comment -->",
                StringUtil.normaliseWhitespace(doc2.outerHtml()));
        assertEquals("#declaration", doc2.childNode(0).nodeName());
        assertEquals("#comment", doc2.childNode(2).nodeName());
    }
    @Test
    public void test32() throws IOException, URISyntaxException {
        File xmlFile = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset.xml").toURI());
        InputStream inStream = new FileInputStream(xmlFile);
        Document doc = Jsoup.parse(inStream, null, "http://example.com/", Parser.xmlParser());
        assertEquals("ISO-8859-1", doc.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <data>äöåéü</data>",
            TextUtil.stripNewlines(doc.html()));
        
        // Additional test case - different charset encoding
        File xmlFile2 = new File(XmlTreeBuilder.class.getResource("/htmltests/xml-charset2.xml").toURI());
        InputStream inStream2 = new FileInputStream(xmlFile2);
        Document doc2 = Jsoup.parse(inStream2, null, "http://example.com/", Parser.xmlParser());
        assertEquals("UTF-8", doc2.charset().name());
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><data>äöåéü</data>",
            TextUtil.stripNewlines(doc2.html()));
    }
}