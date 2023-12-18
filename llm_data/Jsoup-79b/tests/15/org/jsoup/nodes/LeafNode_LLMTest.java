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

    tn.attr(tn.nodeName(), null);
    assertEquals("", tn.text());
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
}
@Test public void test2() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    assertTrue(one.isBlank());
    assertFalse(two.isBlank());
    assertFalse(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test public void test3(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test4() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test public void test5() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertFalse(tn.parent() == tail.parent());
}
@Test
public void test6() {
    Attributes attributes = new Attributes();
    attributes.put("key1", "value1");
    attributes.put("key2", "value2");
    attributes.put("key3", "value3");

    // The input value is changed to a non-null value
    Attributes result = methodUnderTest.attributes();
    
    // Assert that the returned attributes are equal to the original attributes
    assertEquals(attributes, result);
}
@Test
public void test7() {
    Attributes attributes = null;

    // The input value is changed to null
    Attributes result = methodUnderTest.attributes();

    // Assert that the returned attributes are null
    assertNull(result);
}
@Test
public void test8() {
    Attributes attributes = new Attributes();

    // The input value is changed to an empty Attributes object
    Attributes result = methodUnderTest.attributes();

    // Assert that the returned attributes are equal to the original empty attributes
    assertEquals(attributes, result);
}
@Test
public void test9() {
    // Create an object with attributes
    Object value = new Object();
    // Set the value to have attributes
    setAttributes(value, true);

    // Call the ensureAttributes() method
    ensureAttributesReflection(value);

    // Assert that the attributes are not modified
    assertTrue(hasAttributes(value));
}
@Test
public void test10() {
    // Set the value to null
    Object value = null;

    // Call the ensureAttributes() method
    ensureAttributesReflection(value);

    // Assert that attributes are created and nodeName is null
    assertTrue(hasAttributes(value));
    assertNull(getAttributes(value).get(nodeName()));
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test12() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("foobar");
        assertEquals("One <span>two &amp;</span> foobar", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test13() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("");
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test14() {
        //...
    }
    @Test public void test15(){
        //...
    }
    @Test public void test16() {
        //...
    }
    @Test public void test17() {
        //...
    }
    @Test public void test18() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" NEW VALUE");
        assertEquals("One <span>two &amp;</span> NEW VALUE", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test19() {
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
    @Test public void test20(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test21() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test22() {
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
public void test23() {
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

    tn.attr(tn.nodeName(), "");
    assertEquals("", tn.text());
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
}
@Test
public void test24() {
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

    tn.attr(null, "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test
public void test25() {
    TextNode one = new TextNode(" ");
    TextNode two = new TextNode("      ");
    TextNode three = new TextNode("  \n  \n \n  ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test
public void test26() {
    Document doc = Jsoup.parse(new String(Character.toChars(128512)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(128512)), t.outerHtml().trim());
}
@Test
public void test27() {
    Document doc = Jsoup.parse("<div>Hello, there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello, <b>there</b>", TextUtil.stripNewlines(div.html()));
}
@Test
public void test28() {
    Document doc = Jsoup.parse("<div>Hellothere</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(5);
    assertEquals("Hello", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
    @Test public void test29() {
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
    @Test public void test30() {
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
    @Test public void test31(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test32() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test33() {
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
    @Test public void test34() {
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
        
        tn.attr(tn.nodeName(), "test");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test35() {
        TextNode one = new TextNode("\t");
        TextNode two = new TextNode("\t\t\t");
        TextNode three = new TextNode("  \t\n\t   ");
        TextNode four = new TextNode("\tHello");
        TextNode five = new TextNode("  \nHello \t");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test36(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)) + " ☺️");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)) + " ☺️", t.outerHtml().trim());
    }
    @Test public void test37(){
        Document doc = Jsoup.parse("<div>Hëllö thèrë</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hëllö <b>thèrë</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>thèrë thèrë... must correct
    }
    @Test public void test38() {
        Document doc = Jsoup.parse("<div>Hëllö thèrë</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hëllö ", tn.getWholeText());
        assertEquals("thèrë", tail.getWholeText());
        tail.text("thèrë!");
        assertEquals("Hëllö thèrë!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test39() {
    // Create a new node
    Node node = new Node();

    // Add some attributes
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    node.attr("key3", "value3");

    // Remove attribute with key "key2"
    node.removeAttr("key2");

    // Assert that the attribute with key "key2" is removed
    assertNull(node.attr("key2"));
}
@Test
public void test40() {
    String expected = "";
    String result = absUrl(null);
    assertEquals(expected, result);
}
@Test
public void test41() {
    String expected = "";
    String result = absUrl("");
    assertEquals(expected, result);
}
@Test
public void test42() {
    String expected = "http://example.com";
    String result = absUrl("href");
    assertEquals(expected, result);
}
@Test
public void test43() {
    String expected = "";
    String result = absUrl("invalidKey");
    assertEquals(expected, result);
}
@Test
public void test44() {
    String expected = "";
    String result = absUrl("   href");
    assertEquals(expected, result);
}
    @Test public void test45() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test46() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b></b>", TextUtil.stripNewlines(div.html())); 
    }
    @Test public void test47() {
        Document doc = Jsoup.parse("<div>A</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b>A</b>", TextUtil.stripNewlines(div.html())); 
    }
    @Test public void test48() {
        Document doc = Jsoup.parse("<div>!!!</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(2);
        tail.wrap("<b></b>");

        assertEquals("!!<b>!</b>", TextUtil.stripNewlines(div.html())); 
    }
    @Test public void test49() {
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
    @Test public void test50(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test51() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test53() {
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
    @Test public void test54(){
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(0); // Change childNode index from 2 to 0
        assertEquals("One ", tn.text()); // Change expected value from " three &" to "One "
        
        tn.text(" POW!");
        assertEquals(" POW!<span>two &amp;</span> three &amp;", TextUtil.stripNewlines(p.html())); // Change expected value from "One <span>two &amp;</span> POW!" to " POW!<span>two &amp;</span> three &amp;"
    }
    @Test public void test55(){
        Document doc = Jsoup.parse("<div></div>"); // change input to an empty element
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim()); // Assert the text node is empty
    }
    @Test public void test56() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5); // Change split index from 6 to 5
        tail.wrap("<b></b>");

        assertEquals("Hello<there</b>", TextUtil.stripNewlines(div.html())); // Change expected value from "Hello <b>there</b>" to "Hello<there</b>"
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(1); // Change childNode index from 0 to 1
        List<Node> nodes = tn.childNodes();
        assertEquals(1, nodes.size()); // Change expected value from 0 to 1
    }
    @Test public void test58() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals(" ", tail.getWholeText()); // Change expected value from "there" to " "
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test59() {
        Document doc = Jsoup.parse("<p>One <span></span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("", span.text());
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
    }
    @Test public void test60() {
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
    @Test public void test61(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test63() {
        Document doc = Jsoup.parse("<div>Hello there! How are you?</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test64() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
}