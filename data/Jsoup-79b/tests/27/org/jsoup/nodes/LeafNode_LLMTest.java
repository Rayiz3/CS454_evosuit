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
        
        tn.text(" Changed!");
        assertEquals("One <span>two &amp;</span> Changed!", TextUtil.stripNewlines(p.html()));

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
    @Test public void test5() {
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

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test6() {
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
    @Test public void test7(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test8() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
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
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test10() {
    // Test input value: null
    // The method will throw a NullPointerException when trying to cast the null value to type Attributes
    // This test will kill the mutant that changes the return type of the method to null
    Attributes result = object.attributes();
    
    // Assertion to check the result
    assertNull(result);
}
@Test
public void test11() {
    // Test input value: new Attributes()
    // The method will return an empty Attributes object
    // This test will kill the mutant that changes the method to not return an empty Attributes object
    Attributes result = object.attributes();
    
    // Assertion to check the result
    assertTrue(result.isEmpty());
}
@Test
public void test12() {
    // Test input value: new Attributes(Map.of("key1", "value1", "key2", "value2"))
    // The method will return an Attributes object with two key-value pairs
    // This test will kill the mutant that changes the method to not return the correct Attributes object
    Attributes result = object.attributes();
    
    // Assertion to check the result
    assertEquals(2, result.size());
    assertEquals("value1", result.get("key1"));
    assertEquals("value2", result.get("key2"));
}
    @Test
    public void test13() {
        // Test case 1: When hasAttributes() is true
        // In this test case, the value of hasAttributes() method will be set to true.
        // This will cause the if statement to be false, and the method will do nothing.
        // This test case can help kill mutants that depend on the condition in the if statement.
        // Input: hasAttributes() = true
        // Expected output: No changes in the method behavior
        hasAttributes = true;
        ensureAttributes();
        // No assertions needed, method behavior remains the same
        
        // Test case 2: When value is null
        // In this test case, the value of the node will be null.
        // This will cause the method to create a new Attributes object and store it in the value variable.
        // This test case can help kill mutants that assume the value variable is not null.
        // Input: value = null
        // Expected output: value is set to a new Attributes object
        value = null;
        ensureAttributes();
        assertNotNull(value);
        assertTrue(value instanceof Attributes);
        
        // Test case 3: When coreValue is not null
        // In this test case, the coreValue will not be null.
        // This will cause the method to add the coreValue as an attribute to the Attributes object.
        // This test case can help kill mutants that assume the coreValue is always null.
        // Input: coreValue = "test"
        // Expected output: "test" is added as an attribute to the Attributes object
        String coreValue = "test";
        value = coreValue;
        ensureAttributes();
        assertTrue(value instanceof Attributes);
        assertEquals(coreValue, ((Attributes) value).get(nodeName()));
    }
    @Test public void test14() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(""); // change input value to empty string
        assertEquals("", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), ""); // change input value to empty string
        assertEquals("", tn.text());
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test15() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode(""); // change input value to empty string
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank()); // mutated line
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test16(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        // add another supplementary character
        Document doc2 = Jsoup.parse(new String(Character.toChars(119990)));
        TextNode t2 = doc2.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(119990)), t2.outerHtml().trim());
    }
    @Test public void test17() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

        // create new TagNode and append as child to the divided tag
        TextNode tn2 = new TextNode("world");
        div.appendChild(tn2);
        
        assertEquals("Hello <b>there</b>world", TextUtil.stripNewlines(div.html())); // mutated line
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
        
        // create new TextNode and set it as the tail
        TextNode t2 = new TextNode("!!!");
        tail.replaceWith(t2);
        
        assertEquals("Hello !!!", div.text()); // mutated line
        assertTrue(tn.parent() == t2.parent());
    }
@Test public void test19() {
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
@Test public void test20() {
        TextNode one = new TextNode("");     // Changed "" to "a"
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
@Test public void test21(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361))); // Changed 135361 to 0
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(0)), t.outerHtml().trim());
    }
@Test public void test22() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(8);     // Changed 6 to 8
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
@Test public void test23() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(8);     // Changed 6 to 8
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));

        // Test with null key
        assertNull(tn.attr(null));
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
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));

        // Test with empty string key
        assertEquals(" three &", tn.attr(""));
    }
    @Test
    public void test26() {
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

        // Test with non-matching key
        assertNull(tn.attr("key"));
    }
    @Test public void test27() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" NEW TEXT"); // Change input value here
        assertEquals("One <span>two &amp;</span> NEW TEXT", TextUtil.stripNewlines(p.html())); // Change expected output here

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test28() {
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
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test30() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test31() {
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
    @Test public void test32() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("ANOTHER NEW TEXT"); // Change input value here
        assertEquals("One <span>two &amp;</span> ANOTHER NEW TEXT", TextUtil.stripNewlines(p.html())); // Change expected output here

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test33() {
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
    @Test public void test34(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test35() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test36() {
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
public void test37() {
    // Original test case
    // The provided key is valid and exists in the attributes, so the method should return true
    assertTrue(obj.hasAttr("key1"));

    // Regression test case 1
    // Change the key to a different valid key that exists in the attributes
    // The method should still return true
    assertTrue(obj.hasAttr("key2"));
}
@Test
public void test38() {
    // Original test case
    // The provided key is invalid and does not exist in the attributes, so the method should return false
    assertFalse(obj.hasAttr("key3"));

    // Regression test case 1
    // Change the key to a different invalid key that does not exist in the attributes
    // The method should still return false
    assertFalse(obj.hasAttr("key4"));
}
    @Test
    public void test39() {
        // Test case 1: Removing attribute from an empty element
        Element element1 = new Element("div");
        element1.removeAttr("class");

        // Test case 2: Removing attribute that doesn't exist
        Element element2 = new Element("a");
        element2.attr("href", "https://www.example.com");
        element2.removeAttr("target");

        // Test case 3: Removing attribute from an element with multiple attributes
        Element element3 = new Element("img");
        element3.attr("src", "image.jpg");
        element3.attr("width", "100");
        element3.attr("height", "200");
        element3.removeAttr("width");

        // Regression test case 1: Removing attribute from an element with attributes of different types
        Element element4 = new Element("input");
        element4.attr("type", "text");
        element4.attr("value", "example");
        element4.attr("disabled");
        element4.removeAttr("value");

        // Regression test case 2: Removing attribute from an element with duplicate attributes
        Element element5 = new Element("p");
        element5.attr("class", "bold");
        element5.attr("class", "italic");
        element5.attr("class", "underline");
        element5.removeAttr("class");

        // Regression test case 3: Removing attribute from an element with special characters in attribute value
        Element element6 = new Element("span");
        element6.attr("content", "<span>&amp;</span>");
        element6.removeAttr("content");
        
        // Regression test case 4: Removing attribute from an element with attribute value containing whitespace
        Element element7 = new Element("div");
        element7.attr("class", "   container  ");
        element7.removeAttr("class");
    }
@Test
public void test40() {
   String result = myObject.absUrl(null);
   // Assert the result
   // This test case can kill a mutant that returns null even when the key is null
}
@Test
public void test41() {
   String result = myObject.absUrl("");
   // Assert the result
   // This test case can kill a mutant that returns null or an empty string when the key is empty
}
@Test
public void test42() {
   String result = myObject.absUrl("nonExistingKey");
   // Assert the result
   // This test case can kill a mutant that returns null when the key does not exist in the attributes
}
    @Test public void test43() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        
        // Modified input: changing the text content of the TextNode
        tn.text("Hello");

        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello<b> there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test44() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        
        // Modified input: changing the tag name of the wrapping element
        TextNode tail = tn.splitText(6);
        tail.wrap("<strong></strong>");

        assertEquals("Hello <strong>there</strong>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
@Test
public void test45() {
    String baseUri = null;
    doSetBaseUri(baseUri);
}
@Test
public void test46() {
    String baseUri = "";
    doSetBaseUri(baseUri);
}
@Test
public void test47() {
    String baseUri = "http://example.com";
    doSetBaseUri(baseUri);
}
    @Test public void test48() {
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
    @Test public void test49(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
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
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test52() {
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
    @Test public void test53() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("POW!"); // changed input value
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test54(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test55() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello<b> there</b>", TextUtil.stripNewlines(div.html())); // changed expected value
    }
    @Test public void test56() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(1, nodes.size()); // changed expected value
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello", tn.getWholeText()); // changed expected value
        assertEquals("there!", tail.getWholeText()); // changed expected value
        tail.text("there!");
        assertEquals("Hello there", div.text()); // changed expected value
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test58(){
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        
        // Change the HTML tag from <p> to <div>
        // This mutation can be killed by checking for the correct exception message
        assertThrows(UnsupportedOperationException.class, () -> {
            Document doc = Jsoup.parse("<div>One <span>two &amp;</span> three &amp;</div>");
            Element div = doc.select("div").first();
            div.ensureChildNodes();
        }, "Leaf Nodes do not have child nodes.");
    }
    @Test public void test59(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);

        // Change the supplementary character to a regular character
        // This mutation can be killed by checking if the text is not the same as before
        assertNotEquals("A", t.text());
    }
    @Test public void test60() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        // Change the HTML tag from <b> to <i>
        // This mutation can be killed by checking for the correct output HTML
        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
        tail.unwrap();

        // Change the HTML tag from wrap <b> to wrap <i>
        // This mutation can be killed by checking for the correct output HTML
        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();

        // Change the HTML tag from <div> to <p>
        // This mutation can be killed by checking for the correct output size
        assertEquals(0, nodes.size());
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");

        // Change the HTML tag from <div> to <p>
        // This mutation can be killed by checking for the correct output text
        assertEquals("Hello there!", div.text());
    }
}