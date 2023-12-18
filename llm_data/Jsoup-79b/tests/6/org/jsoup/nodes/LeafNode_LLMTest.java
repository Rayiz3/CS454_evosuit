package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
@Test public void test0() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());
    
    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());
    
    tn.text(" POW!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test1() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test public void test2(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test3() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test public void test4() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test
public void test5() {
    Attributes attributes = new Attributes();
    // Add attributes using attributes.put(...)
    
    // Test the method with empty attributes
    assertEquals(attributes, attributes.attributes());
}
@Test
public void test6() {
    Attributes attributes = new Attributes();
    // Add attributes using attributes.put(...)
    
    // Test the method with non-empty attributes
    assertEquals(attributes, attributes.attributes());
}
    public void test7() {
        // Set the value to null
        value = null;
        // Call the method
        ensureAttributes();
        // Check that attributes have been created
        assertTrue(hasAttributes());
    }
    public void test8() {
        // Set the value to an empty string
        value = "";
        // Call the method
        ensureAttributes();
        // Check that attributes have been created
        assertTrue(hasAttributes());
        // Check that the empty value has been added as an attribute
        assertEquals(value, getAttributes().get(nodeName()));
    }
    public void test9() {
        // Set the value to a non-empty string
        value = "test";
        // Call the method
        ensureAttributes();
        // Check that attributes have been created
        assertTrue(hasAttributes());
        // Check that the non-empty value has been added as an attribute
        assertEquals(value, getAttributes().get(nodeName()));
    }
    @Test public void test10() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test11() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" BOOM!");
        assertEquals("One <span>two &amp;</span> BOOM!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test12() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test13(){
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test14(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test15(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test16() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test17() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>different</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test18() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("newtext", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test20() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
        
        // additional test case with value containing special characters
        tn.text("Special ?*&@!! Value");
        assertEquals("Special ?*&@!! Value", tn.text());
        assertEquals("One <span>two &amp;</span>Special ?*&@!! Value&amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test21() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");
        
        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
        
        // additional test case with empty value
        TextNode empty = new TextNode("");
        assertTrue(empty.isBlank());
    }
    @Test public void test22(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        // additional test case with supplementary character in value
        Document newDoc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode newText = newDoc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), newText.outerHtml().trim());
    }
    @Test public void test23() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        // additional test case with longer value to split
        Document newDoc = Jsoup.parse("<div>This is a long text</div>");
        Element newDiv = newDoc.select("div").first();
        TextNode newTn = (TextNode) newDiv.childNode(0);
        TextNode newTail = newTn.splitText(8);
        newTail.wrap("<b></b>");

        assertEquals("This is a<b> long text</b>", TextUtil.stripNewlines(newDiv.html()));
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
        
        // additional test case with different split index
        Document newDoc = Jsoup.parse("<div>Split at index 9</div>");
        Element newDiv = newDoc.select("div").first();
        TextNode newTn = (TextNode) newDiv.childNode(0);
        TextNode newTail = newTn.splitText(9);
        assertEquals("Split at i", newTn.getWholeText());
        assertEquals("ndex 9", newTail.getWholeText());
        newTail.text("ndex 10!");
        assertEquals("Split at index 10!", newDiv.text());
        assertTrue(newTn.parent() == newTail.parent());
    }
    @Test
    public void test25() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());

        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());

        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &amp;", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test
    public void test26() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test
    public void test27() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test
    public void test28() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test
    public void test29() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test
    public void test30() {
        Element element = new Element(Tag.valueOf("tag"), "");
        TextNode textNode = new TextNode("text", "");
        textNode.parent().appendChild(element);

        assertThrows(IllegalArgumentException.class, () -> textNode.attr(null));
    }
    @Test
    public void test31() {
        Element element = new Element(Tag.valueOf("tag"), "");
        TextNode textNode = new TextNode("text", "");
        textNode.parent().appendChild(element);

        assertEquals("text", textNode.attr("tag"));
    }
    @Test
    public void test32() {
        Element element = new Element(Tag.valueOf("tag"), "");
        TextNode textNode = new TextNode("text", "");
        textNode.parent().appendChild(element);

        assertEquals("", textNode.attr("attr"));
    }
    @Test
    public void test33() {
        TextNode textNode = new TextNode("text", "");
        textNode.attr("tag");

        assertEquals("text", textNode.attr("tag"));
    }
@Test
public void test34() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());

    tn.text(" POW!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test
public void test35() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test
public void test36() {
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test
public void test37() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test
public void test38() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test
public void test39() {
    Document doc = Jsoup.parse("<div>Hello</div>");
    Element div = doc.select("div").first();
    TextNode tn = new TextNode("Hello", "");
    div.appendChild(tn);

    tn.attr("Hello", "World");
    assertEquals("World", tn.text());
}
@Test
public void test40() {
    Document doc = Jsoup.parse("<div>Hello</div>");
    Element div = doc.select("div").first();
    TextNode tn = new TextNode("Hello", "");
    div.appendChild(tn);

    tn.attr("Hi", "World");
    assertEquals("Hello", tn.text());
}
@Test
public void test41() {
    Document doc = Jsoup.parse("<div><p>Hello</p></div>");
    Element div = doc.select("div").first();
    Element p = doc.select("p").first();
    TextNode tn = new TextNode("Hello", "");

    div.appendChild(p);
    p.appendChild(tn);

    tn.attr("Hi", "World");
    assertEquals("Hello", tn.text());
}
@Test
public void test42() {
    Node node = new Node();
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    Node result = node.removeAttr("key1");
    assertEquals("value2", result.attr("key2"));
    assertNull(result.attr("key1"));
}
@Test
public void test43() {
    Node node = new Node();
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    Node result = node.removeAttr("key2");
    assertEquals("value1", result.attr("key1"));
    assertNull(result.attr("key2"));
}
@Test
public void test44() {
    Node node = new Node();
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    node.attr("key3", "value3");
    Node result = node.removeAttr("key2");
    assertEquals("value1", result.attr("key1"));
    assertNull(result.attr("key2"));
    assertEquals("value3", result.attr("key3"));
}
@Test
public void test45() {
    Node node = new Node();
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    node.attr("key3", "value3");
    Node result = node.removeAttr("key3");
    assertEquals("value1", result.attr("key1"));
    assertEquals("value2", result.attr("key2"));
    assertNull(result.attr("key3"));
}
@Test
public void test46() {
    String url = "http://example.com";
    Document doc = Jsoup.parse(url);

    assertThrows(IllegalArgumentException.class, () -> {
        doc.absUrl(null);
    });
}
@Test
public void test47() {
    String url = "http://example.com";
    Document doc = Jsoup.parse(url);

    assertThrows(IllegalArgumentException.class, () -> {
        doc.absUrl("invalidKey");
    });
}
@Test
public void test48() {
    String url = "http://example.com";
    Document doc = Jsoup.parse(url);

    assertThrows(IllegalArgumentException.class, () -> {
        doc.absUrl("");
    });
}
@Test
public void test49() {
    String url = "http://example.com";
    Document doc = Jsoup.parse(url);

    String result = doc.absUrl("href");

    assertNull(result);
}
@Test
public void test50() {
    String url = "<a href='http://example.com'></a>";
    Document doc = Jsoup.parse(url);

    String result = doc.absUrl("href");

    assertEquals("http://example.com", result);
}
    @Test public void test51() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Section 1.1</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(8);
        tail.wrap("<b></b>");

        assertEquals("Section <b>1.1</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test
    public void test53() {
        String baseUri = "http://www.example.com";
        doSetBaseUri(baseUri);
    }
    @Test
    public void test54() {
        String baseUri = "https://www.example.com";
        doSetBaseUri(baseUri);
    }
    @Test
    public void test55() {
        String baseUri = "ftp://www.example.com";
        doSetBaseUri(baseUri);
    }
    @Test
    public void test56() {
        String baseUri = "http://example.com";
        doSetBaseUri(baseUri);
    }
    @Test
    public void test57() {
        String baseUri = "http://www.google.com";
        doSetBaseUri(baseUri);
    }
@Test public void test58() {
    Document doc = Jsoup.parse("<p></p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(0);
    assertEquals("", tn.text());
    
    tn.text("Hello");
    assertEquals("Hello", tn.text());
    
    tn.attr(tn.nodeName(), "World");
    assertEquals("World", tn.text());
}
@Test public void test59() {
    Document doc = Jsoup.parse("<p>&lt;span&gt;One &amp;amp; two &amp; three &amp;&lt;/span&gt;</p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(0);
    assertEquals("<span>One & two & three &</span>", tn.text());
}
@Test public void test60() {
    Document doc = Jsoup.parse("<p>   Leading whitespace</p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(0);
    assertEquals("   Leading whitespace", tn.text());
}
@Test public void test61() {
    Document doc = Jsoup.parse("<p>Trailing whitespace   </p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(0);
    assertEquals("Trailing whitespace   ", tn.text());
}
@Test public void test62() {
    Document doc = Jsoup.parse("<p>First line\nSecond line</p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(0);
    assertEquals("First line\nSecond line", tn.text());
}
@Test public void test63() {
    Document doc = Jsoup.parse("<p>&#x1F607; &#x1F609; &#x1F601;</p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(0);
    assertEquals("😇 😉 😁", tn.text());
}
    @Test public void test64() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test65(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test66() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test67() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test68() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test69() {
        Document doc = Jsoup.parse("<div>Hello world</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        
        List<Node> nodes = tn.ensureChildNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test70() {
        Document doc = Jsoup.parse("<div><p>Paragraph</p></div>");
        Element div = doc.select("div").first();
        Element p = div.select("p").first();
        
        List<Node> nodes = p.ensureChildNodes();
        assertEquals(1, nodes.size());
        assertTrue(nodes.get(0).nodeName().equals("#text"));
        assertEquals("Paragraph", ((TextNode)nodes.get(0)).text());
    }
    @Test public void test71() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        
        List<Node> nodes = div.ensureChildNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test72() {
        Document doc = Jsoup.parse("<div><p>Paragraph <span>with</span> <strong>bold</strong> and <em>italic</em> elements</p></div>");
        Element div = doc.select("div").first();
        Element p = div.select("p").first();
        
        List<Node> nodes = p.ensureChildNodes();

        assertEquals(6, nodes.size());
        assertTrue(nodes.get(0).nodeName().equals("#text"));
        assertEquals("Paragraph ", ((TextNode)nodes.get(0)).text());
        assertTrue(nodes.get(1).nodeName().equals("span"));
        assertTrue(nodes.get(2).nodeName().equals("#text"));
        assertEquals(" with", ((TextNode)nodes.get(2)).text());
        assertTrue(nodes.get(3).nodeName().equals("strong"));
        assertTrue(nodes.get(4).nodeName().equals("#text"));
        assertEquals(" and ", ((TextNode)nodes.get(4)).text());
        assertTrue(nodes.get(5).nodeName().equals("em"));
    }
}