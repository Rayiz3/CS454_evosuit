package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test public void test0() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span></p>"); // Missing the text node after the span
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertNull(tn); // Check if the text node is missing
        
        tn = new TextNode(" three &"); // Add the missing text node
        p.appendChild(tn);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test1() {
        TextNode one = new TextNode(""); // Test with an empty string
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
    @Test public void test2() {
        TextNode one = new TextNode(" "); // Test with a whitespace string
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test3() {
        Document doc = Jsoup.parse(""); // Test with an empty document
        TextNode t = doc.body().textNodes().get(0); // Check if body exists
        assertNull(t); // Check if the text node is missing
        
        t = new TextNode(new String(Character.toChars(135361)));
        doc.body().appendChild(t); // Add the missing text node
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test4() {
        Document doc = Jsoup.parse("<div></div>"); // Test with an empty div
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("<b></b>", TextUtil.stripNewlines(div.html())); // Check if div is still empty
    }
    @Test public void test5() {
        Document doc = Jsoup.parse("<div>Hello </div>"); // Missing the text node after the div
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertNull(tn); // Check if the text node is missing
        
        tn = new TextNode("there"); // Add the missing text node
        div.appendChild(tn);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // Check if the split and wrap operation is correct
    }
    @Test public void test6() {
        Document doc = Jsoup.parse("<div></div>"); // Test with an empty div
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertNull(tn); // Check if the text node is missing
        
        tn = new TextNode("Hello there"); // Add the missing text node
        div.appendChild(tn);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test7() {
    Attributes attributes = createEmptyAttributes();
    ensureAttributes(attributes);
    assertEquals(attributes, value);
}
@Test
public void test8() {
    Attributes attributes = createNonEmptyAttributes();
    ensureAttributes(attributes);
    assertEquals(attributes, value);
}
@Test
public void test9() {
    ensureAttributes(null);
    assertNull(value);
}
private Attributes createEmptyAttributes() {
    return new Attributes();
}
private Attributes createNonEmptyAttributes() {
    Attributes attributes = new Attributes();
    attributes.put("key1", "value1");
    attributes.put("key2", "value2");
    return attributes;
}
@Test
public void test10() {
    Object value = null;
    // Set value field
    Object setValue = value;
    
    // Call the method
    ensureAttributes();
    Object result = value;

    // Verify the new attributes object is created
    assertTrue(result instanceof Attributes);
    
    // Verify the attributes object is empty
    assertTrue(((Attributes) result).isEmpty());
    
    // Verify the original value is put into the attributes object (as null)
    assertEquals(setValue, ((Attributes) result).get(nodeName()));
}
@Test
public void test11() {
    Object value = "test";
    // Set value field
    Object setValue = value;
    
    // Call the method
    ensureAttributes();
    Object result = value;

    // Verify the new attributes object is created
    assertTrue(result instanceof Attributes);
    
    // Verify the attributes object is empty
    assertTrue(((Attributes) result).isEmpty());
    
    // Verify the original value is put into the attributes object
    assertEquals(setValue, ((Attributes) result).get(nodeName()));
}
@Test
public void test12() {
    Object value = "test";
    
    // Simulate existing attributes object
    Object existingAttributes = new Attributes();
    
    // Set the value field
    value = existingAttributes;
    Object setValue = value;
    
    // Call the method
    ensureAttributes();
    Object result = value;

    // Verify the existing attributes object is still used
    assertEquals(existingAttributes, result);
    
    // Verify the original value is put into the attributes object
    assertEquals(setValue, ((Attributes) result).get(nodeName()));
}
@Test
public void test13() {
    Object value = null;
    
    // Simulate existing attributes object
    Object existingAttributes = new Attributes();
    
    // Set the value field
    value = existingAttributes;
    Object setValue = value;
    
    // Call the method
    ensureAttributes();
    Object result = value;

    // Verify the existing attributes object is still used
    assertEquals(existingAttributes, result);
    
    // Verify the original value (null) is put into the attributes object
    assertEquals(setValue, ((Attributes) result).get(nodeName()));
}
@Test
public void test14() {
    Object value = "test";
    
    // Simulate non-Attributes object
    Object nonAttributes = new Object();
    
    // Set the value field
    value = nonAttributes;
    Object setValue = value;
    
    // Call the method
    ensureAttributes();
    Object result = value;

    // Verify a new attributes object is created
    assertTrue(result instanceof Attributes);
    
    // Verify the attributes object is empty
    assertTrue(((Attributes) result).isEmpty());
    
    // Verify the original value is put into the attributes object
    assertEquals(setValue, ((Attributes) result).get(nodeName()));
}
@Test
public void test15() {
    Object value = null;
    
    // Simulate non-Attributes object
    Object nonAttributes = new Object();
    
    // Set the value field
    value = nonAttributes;
    Object setValue = value;
    
    // Call the method
    ensureAttributes();
    Object result = value;

    // Verify a new attributes object is created
    assertTrue(result instanceof Attributes);
    
    // Verify the attributes object is empty
    assertTrue(((Attributes) result).isEmpty());
    
    // Verify the original value (null) is put into the attributes object
    assertEquals(setValue, ((Attributes) result).get(nodeName()));
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

    tn.text("");
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "");
    assertEquals("", tn.text());
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
}
@Test public void test17() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());

    tn.text("   ");
    assertEquals("One <span>two &amp;</span>    ", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "  ");
    assertEquals("  ", tn.text());
    assertEquals("One <span>two &amp;</span>  ", TextUtil.stripNewlines(p.html()));
}
@Test public void test18() {
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
@Test public void test19(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test20() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test public void test21() {
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
public void test22() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());

    tn.text(" PUM!");

    assertEquals("One <span>two &amp;</span> PUM!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test 
public void test23() {
    Document doc = Jsoup.parse("<p></p>");
    Element p = doc.select("p").first();

    TextNode tn = new TextNode("Hello");
    p.appendChild(tn);

    assertEquals("", TextUtil.stripNewlines(p.html()));
}
@Test 
public void test24() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());

    tn.text("");
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
}
@Test 
public void test25() {
    TextNode one = new TextNode("Hello");

    assertFalse(one.isBlank());
}
@Test 
public void test26() {
    TextNode one = new TextNode("");

    assertFalse(one.isBlank());
}
@Test 
public void test27() {
    TextNode one = new TextNode("     ");

    assertFalse(one.isBlank());
}
@Test 
public void test28() {
    TextNode one = new TextNode("  \n\n   ");

    assertTrue(one.isBlank());
}
@Test 
public void test29() {
    TextNode one = new TextNode("  \nHello ");

    assertFalse(one.isBlank());
}
@Test 
public void test30(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("", t.outerHtml().trim());
}
@Test 
public void test31(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)) + " mutation", t.outerHtml().trim());
}
@Test 
public void test32() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test 
public void test33() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(5);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
}
@Test 
public void test34() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("!");

    assertEquals("Hello there!", div.text());
    assertFalse(tn.parent() == tail.parent());
}
@Test 
public void test35() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(5);
    assertEquals("Hello", tn.getWholeText());
    assertEquals(" there", tail.getWholeText());
    tail.text("!");

    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
    @Test public void test36() {
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
    @Test public void test37() {
        Document doc = Jsoup.parse("<p>One two three</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One two POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One twokablam &", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test38() {
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
    @Test public void test39() {
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
    @Test public void test40() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test41(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test42(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.text());
    }
    @Test public void test43() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test44() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(11);
        tail.wrap("<b></b>");

        assertEquals("Hello there", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test45() {
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
    @Test public void test46() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(11);
        assertEquals("Hello there", tn.getWholeText());
        assertEquals("", tail.getWholeText());
        tail.text("!!!");
        assertEquals("Hello there!!!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test public void test47() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());

    tn.text("");
    assertEquals("One <span>two &amp;</span> ", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "");
    assertEquals("", tn.text());
    assertEquals("One <span>two &amp;</span> ", TextUtil.stripNewlines(p.html()));
}
@Test public void test48() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("");
    TextNode five = new TextNode("  \nHello ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertTrue(four.isBlank());
    assertFalse(five.isBlank());
}
@Test public void test49(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("", t.outerHtml().trim());
}
@Test public void test50() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test public void test51() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("!");
    assertEquals("Hello !", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test
public void test52() {
    boolean result = hasAttr(null);
    assertFalse(result);
}
@Test
public void test53() {
    boolean result = hasAttr("");
    assertFalse(result);
}
@Test
public void test54() {
    boolean result = hasAttr("nonExistingKey");
    assertFalse(result);
}
@Test
public void test55() {
    boolean result = hasAttr("existingKey");
    assertTrue(result);
}
@Test
public void test56() {
    // Create a new element with attributes
    Element element = new Element("div");
    element.attr("class", "container");
    element.attr("id", "main");

    // Remove an existing key
    Node removedNode = element.removeAttr("class");

    // Verify that the value of the removed attribute is the expected value
    assertNull(removedNode);
    assertEquals("", element.attr("class"));
}
@Test
public void test57() {
    // Create a new element with attributes
    Element element = new Element("div");
    element.attr("class", "container");
    element.attr("id", "main");

    // Remove a non-existing key
    Node removedNode = element.removeAttr("style");

    // Verify that the value of the removed attribute is the expected value
    assertNull(removedNode);
    assertEquals("", element.attr("style"));
}
@Test
public void test58() {
   String result = absUrl("href");
   // assert the logic of the method here
   // ...
}
@Test
public void test59() {
   String result = absUrl("invalidKey");
   // assert the logic of the method here
   // ...
}
@Test
public void test60() {
   String result = absUrl("");
   // assert the logic of the method here
   // ...
}
@Test
public void test61() {
   String result = absUrl(null);
   // assert the logic of the method here
   // ...
}
    @Test public void test62() {
        // Change the input text
        Document doc = Jsoup.parse("<div>Goodbye there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7);
        tail.wrap("<b></b>");

        assertEquals("Goodbye <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test63() {
        // Change the input text
        Document doc = Jsoup.parse("<div>Hello</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3);
        tail.wrap("<b></b>");

        assertEquals("Hel<b>lo</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test64() {
        // Change the input text
        Document doc = Jsoup.parse("<div>Hi there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3);
        tail.wrap("<b></b>");

        assertEquals("Hi <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
@Test
public void test65() {
    // Arrange
    String baseUri = null;

    // Act
    doSetBaseUri(baseUri);

    // Assert
    // No assertion because the method is a noop
}
@Test
public void test66() {
    // Arrange
    String baseUri = "";

    // Act
    doSetBaseUri(baseUri);

    // Assert
    // No assertion because the method is a noop
}
@Test
public void test67() {
    // Arrange
    String baseUri = "http://example.com/";

    // Act
    doSetBaseUri(baseUri);

    // Assert
    // No assertion because the method is a noop
}
@Test public void test68() {
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
@Test public void test69(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test70() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test public void test71() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(0, nodes.size());
}
@Test public void test72() {
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
@Test public void test73() {
    Document doc = Jsoup.parse("<p><span>hello</span> <span></span> world</p>");
    Element p = doc.select("p").first();
    TextNode tn = (TextNode) p.childNode(2);
    
    assertEquals("", tn.text());
    tn.text("!");
    
    assertEquals("hello <span></span> world!", p.html());
}
@Test public void test74() {
    Document doc = Jsoup.parse("<p><span>hello</span> <span>world</span></p>");
    Element p = doc.select("p").first();
    TextNode tn = (TextNode) p.childNode(0);
    
    tn.attr("class", "foo");
    
    assertEquals("hello <span>world</span>", p.html());
}
    @Test public void test75() {
        Document doc = Jsoup.parse("");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals("", tn.text());
        
        tn.text("");
        assertEquals("", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "");
        assertEquals("", tn.text());
        assertEquals("", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test76() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("hello!");
        assertEquals("One <span>two &amp;</span> hello!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "hello again!");
        assertEquals("hello again!", tn.text());
        assertEquals("One <span>two &amp;</span>hello again!", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test77() {
        Document doc = Jsoup.parse("");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test78() {
        Document doc = Jsoup.parse("");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b></b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test79() {
        Document doc = Jsoup.parse("");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test80() {
        Document doc = Jsoup.parse("");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
        tail.text("world!");
        assertEquals("world!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
}