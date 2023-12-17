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
        
        tn.text(""); // change input to empty string
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html())); // mutated line
        assertTrue(tn.toString().isEmpty()); // mutated line

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test2() {
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
    @Test public void test3() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");
        TextNode six = new TextNode(""); // change input to empty string

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
        assertTrue(six.isBlank()); // mutated line
    }
    @Test public void test4(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test5(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)) + "Test"); // change input to include "Test"
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)) + "Test", t.outerHtml().trim()); // mutated line
    }
    @Test public void test6() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test7() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // mutated line
    }
    @Test public void test8() {
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
    @Test public void test9() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertFalse(tn.parent() == tail.parent()); // mutated line
    }
    @Test
    public void test10() {
        // Test case with null value
        assertNull(attributes());

        // Test case with empty attributes
        value = new Attributes();
        assertSame(value, attributes());

        // Test case with non-empty attributes
        value.put("key1", "value1");
        value.put("key2", "value2");
        assertSame(value, attributes());
    }
    @Test
    public void test11() {
        // Test case with null value
        assertNull(attributes());

        // Test case with empty attributes
        value = new Attributes();
        assertSame(value, attributes());

        // Test case with non-empty attributes
        value.put("key1", "value1");
        value.put("key2", "value2");
        assertSame(value, attributes());
    }
    @Test
    public void test12() {
        // Test case with null value
        assertNull(attributes());

        // Test case with empty attributes
        value = new Attributes();
        assertSame(value, attributes());

        // Test case with non-empty attributes
        value.put("key1", "value1");
        value.put("key2", "value2");
        assertSame(value, attributes());

        // Test case with different non-empty attributes
        value.put("key3", "value3");
        value.put("key4", "value4");
        assertSame(value, attributes());
    }
@Test
public void test13() {
    // Arrange
    Object value = null;
    // Act
    ensureAttributes(value);
    // Assert
    assertNotNull(value);
    assertTrue(value instanceof Attributes);
}
@Test
public void test14() {
    // Arrange
    Object value = "test";
    // Act
    ensureAttributes(value);
    // Assert
    assertNotNull(value);
    assertTrue(value instanceof Attributes);
}
@Test
public void test15() {
    // Arrange
    Object value = new Attributes();
    // Act
    ensureAttributes(value);
    // Assert
    assertNotNull(value);
    assertTrue(value instanceof Attributes);
}
    @Test public void test16() {
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
    @Test public void test17() {
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
    @Test public void test18(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test20() {
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
    @Test public void test21() {
        TextNode tn = new TextNode("Hello");
        assertNull(tn.parent());
        assertEquals("Hello", tn.toString());
        assertEquals("Hello", tn.getWholeText());
        assertFalse(tn.isBlank());
        assertEquals("Hello", tn.coreValue());
    }
    @Test public void test22() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = new TextNode("Hello");
        div.appendChild(tn);
        
        assertEquals("Hello", tn.toString());
        assertEquals("Hello", tn.getWholeText());
        assertFalse(tn.isBlank());
        assertEquals("Hello", tn.coreValue());
    }
    @Test public void test23() {
        Document doc = Jsoup.parse("<div>Test <span>TextNode</span></div>");
        Element div = doc.select("div").first();
        TextNode tn = new TextNode("Hello");
        div.appendChild(tn);
        
        assertEquals("Hello", tn.toString());
        assertEquals("Hello", tn.getWholeText());
        assertFalse(tn.isBlank());
        assertEquals("Hello", tn.coreValue());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = new TextNode("   ");
        div.appendChild(tn);
        
        assertEquals("   ", tn.toString());
        assertEquals("   ", tn.getWholeText());
        assertTrue(tn.isBlank());
        assertEquals("   ", tn.coreValue());
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = new TextNode(new String(Character.toChars(135361)));
        div.appendChild(tn);
        
        assertEquals(new String(Character.toChars(135361)), tn.toString());
        assertEquals(new String(Character.toChars(135361)), tn.getWholeText());
        assertFalse(tn.isBlank());
        assertEquals(new String(Character.toChars(135361)), tn.coreValue());
    }
    @Test public void test26() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = new TextNode("Hello there");
        
        TextNode tail = tn.splitText(5);
        
        assertEquals("Hello", tn.toString());
        assertEquals("there", tail.toString());
        assertEquals("Hello", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        assertFalse(tn.isBlank());
        assertFalse(tail.isBlank());
        
        assertEquals("Hello", tn.coreValue());
        assertEquals("there", tail.coreValue());
    }
    @Test public void test27() {
        // Test with special characters
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
    @Test public void test28() {
        // Test with blank value
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
    @Test public void test29(){
        // Test with supplementary character
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test30() {
        // Test with a different tag
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<i></i>");

        assertEquals("Hello <i>there</i>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test31() {
        // Test with a longer input string
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(4);
        assertEquals("Hell", tn.getWholeText());
        assertEquals("o there", tail.getWholeText());
        tail.text("o there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test32() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(2);
    tn.text(null); // change: pass null as the input value

    assertEquals("", EmptyString);
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
}
@Test
public void test33() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(2);
    tn.text(" three &&"); // change: add an extra '&' in the input value

    assertEquals(" three &&", tn.text());
    assertEquals("One <span>two &amp;</span> three &amp;", TextUtil.stripNewlines(p.html()));
}
@Test
public void test34() {
    TextNode one = new TextNode(""); 
    assertTrue(one.isBlank());

    TextNode two = new TextNode("   "); // change: add whitespace in the input value
    assertTrue(two.isBlank());
}
@Test
public void test35() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello there", TextUtil.stripNewlines(div.html())); // change: remove the extra space at the end
}
@Test
public void test36() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello", tn.getWholeText()); // change: change the split index to 5
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
    @Test public void test37() {
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

        // Regression tests
        tn.attr("test-key", "test-value"); // Change the attribute name and value
        assertEquals("test-value", tn.text());
        assertEquals("One <span>two &amp;</span>test-value;", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), ""); // Empty attribute value
        assertEquals("", tn.text());
        assertEquals("One <span>two &amp;</span>;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test38() {
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

        // Regression tests
        TextNode blankTextNode = new TextNode("     "); // Non-empty but blank text
        assertTrue(blankTextNode.isBlank());

        TextNode nonBlankTextNode = new TextNode("Hello"); // Non-blank text
        assertFalse(nonBlankTextNode.isBlank());
    }
    @Test public void test39(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());

        // Regression test
        t.attr("test-key", "test-value"); // Add an attribute
        assertEquals(new String(Character.toChars(135361)), t.text());
    }
    @Test public void test40() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

        // Regression test
        tn.attr("test-key", "test-value"); // Add an attribute
        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test41() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());

        // Regression test
        tn.attr("test-key", "test-value"); // Add an attribute
        assertEquals("Hello there!", div.text());
    }
@Test
public void test42() {
   Element element = new Element();
   element.attr("key", "value");

   assertTrue(element.hasAttr("key"));
}
@Test
public void test43() {
   Element element = new Element();

   assertFalse(element.hasAttr("key"));
}
@Test
public void test44() {
   Element element = new Element();

   assertFalse(element.hasAttr(null));
}
@Test
public void test45() {
   Element element = new Element();
   element.attr("", "value");

   assertTrue(element.hasAttr(""));
}
@Test
public void test46() {
   Element element = new Element();
   element.attr("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", "value");

   assertTrue(element.hasAttr("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));
}
@Test
public void test47() {
   Element element = new Element();
   element.attr("!@#$%^&*()", "value");

   assertTrue(element.hasAttr("!@#$%^&*()"));
}
@Test
public void test48() {
    // Test with empty key
    String result = absUrl("");
    assertEquals("", result);
}
@Test
public void test49() {
    // Test with null key
    String result = absUrl(null);
    assertNull(result);
}
@Test
public void test50() {
    // Test with non-existing key
    String result = absUrl("nonExistingKey");
    assertNull(result);
}
@Test
public void test51() {
    // Test with existing key
    String result = absUrl("existingKey");
    assertEquals("https://example.com/somePath", result);
}
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0); // get a NullPointerException here
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); 
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<div><br></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0); // get a NullPointerException here
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); 
    }
    protected void doSetBaseUri(String baseUri) {
        // noop
    }
    @Test public void test55() {
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
    @Test public void test56(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test58() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test59() {
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
    @Test public void test60() {
        Document doc = Jsoup.parse("<div>No children</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        int size = tn.childNodeSize();
        assertEquals(0, size);
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("<div>Two children</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode child1 = new TextNode("child 1");
        TextNode child2 = new TextNode("child 2");
        tn.appendChild(child1);
        tn.appendChild(child2);
        int size = tn.childNodeSize();
        assertEquals(2, size);
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Ten children</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        for (int i = 0; i < 10; i++) {
            TextNode child = new TextNode("child " + (i + 1));
            tn.appendChild(child);
        }
        int size = tn.childNodeSize();
        assertEquals(10, size);
    }
    @Test public void test63() {
        Document doc = Jsoup.parse("<div>Hundred children</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        for (int i = 0; i < 100; i++) {
            TextNode child = new TextNode("child " + (i + 1));
            tn.appendChild(child);
        }
        int size = tn.childNodeSize();
        assertEquals(100, size);
    }
    @Test public void test64() {
        Document doc = Jsoup.parse("<div>Thousand children</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        for (int i = 0; i < 1000; i++) {
            TextNode child = new TextNode("child " + (i + 1));
            tn.appendChild(child);
        }
        int size = tn.childNodeSize();
        assertEquals(1000, size);
    }
  @Test
  public void test65() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());

    tn.text(" Alternate Text");
    assertEquals("One <span>two &amp;</span> Alternate Text", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
  }
  @Test
  public void test66() {
    Document doc = Jsoup.parse("<p></p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(0);
    assertEquals("", tn.text());
  }
  @Test
  public void test67() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there ExtraText</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
  }
  @Test
  public void test68() {
    Document doc = Jsoup.parse("<div></div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(0, nodes.size());
  }
  @Test
  public void test69() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there were additional characters!");
    assertEquals("Hello there were additional characters!", div.text());
    assertTrue(tn.parent() == tail.parent());
  }
}