package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test 
    public void test0() {
        // Mutant: Change the tag name from "p" to "div"
        Document doc = Jsoup.parse("<div>One <span>two &amp;</span> three &amp;</div>");
        Element p = doc.select("div").first();

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
    public void test1() {
        // Mutant: Change the content of two to a non-blank string
        TextNode one = new TextNode("");
        TextNode two = new TextNode("NON-BLANK");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertFalse(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test 
    public void test2(){
        // Mutant: Change the supplementary character to a non-supplementary character
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test 
    public void test3() {
        // Mutant: Change the tag name from "div" to "span"
        Document doc = Jsoup.parse("<span>Hello there</span>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test 
    public void test4() {
        // Mutant: Remove the text " there" from the input HTML
        Document doc = Jsoup.parse("<div>Hello</div>");
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
        // Test case 1: Testing with null value
        // The method should throw a NullPointerException
        try {
          attributes();
          fail("Should have thrown a NullPointerException");
        } catch (NullPointerException e) {
          // Exception thrown as expected
        }
        
        // Test case 2: Testing with empty attributes
        // The method should return an empty attributes object
        ensureAttributes();
        Attributes attributes = attributes();
        assert(attributes.size() == 0);
        
        // Test case 3: Testing with non-empty attributes
        // The method should return a attributes object containing all the attributes
        ensureAttributes();
        attributes = attributes();
        assert(attributes.size() == 5);
        assert(attributes.get("attribute1").equals("value1"));
        assert(attributes.get("attribute2").equals("value2"));
        assert(attributes.get("attribute3").equals("value3"));
        assert(attributes.get("attribute4").equals("value4"));
        assert(attributes.get("attribute5").equals("value5"));
    }
@Test
public void test6() {
  MyClass obj = new MyClass();
  obj.ensureAttributes();
  Assert.assertNotNull(obj.getValue());
}
@Test
public void test7() {
  MyClass obj = new MyClass();
  obj.setValue("testValue");
  obj.ensureAttributes();
  Assert.assertNotNull(obj.getValue());
  Assert.assertTrue(obj.getValue() instanceof Attributes);
  Assert.assertEquals("testValue", ((Attributes)obj.getValue()).get(obj.getNodeName()));
}
    @Test public void test8() {
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
    @Test public void test9() {
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
        
        // Additional test cases
        TextNode six = new TextNode("\n");
        TextNode seven = new TextNode("  \n  ");
        TextNode eight = new TextNode("   \n   ");
        assertTrue(six.isBlank());
        assertTrue(seven.isBlank());
        assertTrue(eight.isBlank());
    }
    @Test public void test10(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test11() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        // Additional test case
        tail.text(" World!");
        assertEquals("Hello <b>World!</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test12() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
        
        // Additional test case
        TextNode newTail = tail.splitText(3);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("the", tail.getWholeText());
        assertEquals("re", newTail.getWholeText());
        newTail.text("final");
        assertEquals("Hello thefinal!", div.text());
        assertTrue(tn.parent() == newTail.parent());
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test14() {
        TextNode one = new TextNode("TEST"); // change the input from "" to "TEST"
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
    @Test public void test15(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361))); // change the input from 135361 to 0
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(0)), t.outerHtml().trim());
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
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

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
    @Test public void test23() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("POW!"); // Changed input value
        assertNotEquals(" One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test24() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertFalse(three.isBlank()); // Changed input value
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertNotEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // Changed expected value
    }
    @Test public void test26() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("THERE!"); // Changed input value
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        // Additional test case with a different key-value
        tn.attr(tn.nodeName(), "kablam &amp;");
        assertEquals("kablam &amp;", tn.text());
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

        // Additional test cases with non-blank textNodes
        TextNode six = new TextNode("A");
        TextNode seven = new TextNode("Long text with spaces");
        assertFalse(six.isBlank());
        assertFalse(seven.isBlank());
    }
    @Test public void test29(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());

        // Additional test case with different supplementary character
        doc = Jsoup.parse(new String(Character.toChars(135362)));
        t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test30() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

        // Additional test case with different split index
        tail = tn.splitText(2);
        tail.wrap("<i></i>");
        assertEquals("He<i>llo</i> <b>there</b>", TextUtil.stripNewlines(div.html()));
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

        // Additional test case with different text
        TextNode tn2 = (TextNode) doc.select("p").first().childNode(0);
        TextNode tail2 = tn2.splitText(3);
        assertEquals("One", tn2.getWholeText());
        assertEquals(" <span>two &amp;</span> three &amp;", tail2.getWholeText());
        tail2.text("Split text");
        assertEquals("OneSplit text", doc.select("p").first().text());
        assertTrue(tn2.parent() == tail2.parent());
    }
@Test
public void test32() {
    Element element = new Element("div");
    element.attr("id", "testId");

    boolean result = element.hasAttr("id");

    assertTrue(result);
}
@Test
public void test33() {
    Element element = new Element("div");

    boolean result = element.hasAttr("class");

    assertFalse(result);
}
@Test
public void test34() {
    // Create a new Element with attributes
    Element element = new Element("div");
    element.attr("class", "container");
    element.attr("id", "myDiv");
    element.attr("data-name", "John Doe");
    
    // Remove a non-existing attribute
    Node removedAttribute = element.removeAttr("name");
    
    // Verify that the removedAttribute is null
    assertNull(removedAttribute);
    
    // Verify that the attributes are unchanged
    assertEquals("container", element.attr("class"));
    assertEquals("myDiv", element.attr("id"));
    assertEquals("John Doe", element.attr("data-name"));
}
@Test
public void test35() {
    // Create a new Element with attributes
    Element element = new Element("div");
    element.attr("class", "container");
    element.attr("id", "myDiv");
    element.attr("data-name", "John Doe");
    
    // Remove an existing attribute
    Node removedAttribute = element.removeAttr("id");
    
    // Verify that the removedAttribute is not null
    assertNotNull(removedAttribute);
    
    // Verify that the removedAttribute has the correct key and value
    assertEquals("id", removedAttribute.key());
    assertEquals("myDiv", removedAttribute.val());
    
    // Verify that the removed attribute is no longer in the element
    assertNull(element.attr("id"));
    
    // Verify that the other attributes are unchanged
    assertEquals("container", element.attr("class"));
    assertEquals("John Doe", element.attr("data-name"));
}
@Test
public void test36() {
    // Test case with empty key
    String absUrl = absUrl("");
    assertTrue(absUrl.isEmpty());
}
@Test
public void test37() {
    // Test case with valid key
    String absUrl = absUrl("href");
    assertEquals("http://www.example.com", absUrl);
}
@Test
public void test38() {
    // Test case with invalid key
    String absUrl = absUrl("invalidKey");
    assertNull(absUrl);
}
    @Test public void test39() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.setBaseUri("https://example.com/");
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test40() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.setParentNode(div);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test41() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        div.setBaseUri("https://example.com/");
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
@Test
public void test42() {
    doSetBaseUri("");
    // Add assertions here
}
@Test
public void test43() {
    doSetBaseUri(null);
    // Add assertions here
}
@Test
public void test44() {
    doSetBaseUri("This is a very long base URI string that may cause issues.");
    // Add assertions here
}
    @Test public void test45() {
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
    @Test public void test46(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test47() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test48() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test49() {
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
    @Test public void test50() {
        Document doc = Jsoup.parse("<div>  Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("  Hello there", tn.getWholeText());
        tn.text("Hello there");
        assertEquals("Hello there", div.text());
    }
    @Test public void test51() {
        Document doc = Jsoup.parse("<div>Hello there  </div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("Hello there  ", tn.getWholeText());
        tn.text("Hello there");
        assertEquals("Hello there", div.text());
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello     there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("Hello     there", tn.getWholeText());
        tn.text("Hello there");
        assertEquals("Hello there", div.text());
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div>Hello\tthere</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("Hello\tthere", tn.getWholeText());
        tn.text("Hello there");
        assertEquals("Hello there", div.text());
    }
    @Test public void test54() {
        // original input: "<p>One <span>two &amp;</span> three &amp;</p>"
        Document doc = Jsoup.parse("<p><span>Two &amp;</span> One &amp;</p>"); // changed input
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("Two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("Two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(1); // changed index
        assertEquals(" One &", tn.text());
        
        tn.text(" POW!");
        assertEquals("<span>Two &amp;</span> POW!", TextUtil.stripNewlines(p.html())); // changed expected result

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("<span>Two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html())); // changed expected result
    }
    @Test public void test55(){
        // original input: new String(Character.toChars(135361))
        Document doc = Jsoup.parse(new String(Character.toChars(135362))); // changed input
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test56() {
        // original input: "<div>Hello there</div>"
        Document doc = Jsoup.parse("<div>Hi there</div>"); // changed input
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3); // changed index
        tail.wrap("<b></b>");

        assertEquals("Hi <b>there</b>", TextUtil.stripNewlines(div.html())); // changed expected result
    }
    @Test public void test57() {
        // original input: "<div>Hello there</div>"
        Document doc = Jsoup.parse("<div>Hello</div>"); // changed input
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test58() {
        // original input: "<div>Hello there</div>"
        Document doc = Jsoup.parse("<div>Goodbye there</div>"); // changed input
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(8); // changed index
        assertEquals("Goodbye ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Goodbye there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
}