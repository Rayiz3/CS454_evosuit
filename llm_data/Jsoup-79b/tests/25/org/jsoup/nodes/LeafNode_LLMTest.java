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
    @Test public void test5() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(null); // Change the input value to null
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test6() {
        TextNode one = new TextNode(""); // Change the input value to empty string
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
        TextNode t = doc.body().textNodes().get(""); // Change the input value to empty string
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
        TextNode tail = tn.splitText(null); // Change the input value to null
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test10() {
    // Create a new instance of the class
    MyClass obj = new MyClass();

    // Test case 1: Empty attributes
    Attributes emptyAttributes = new Attributes();
    obj.setValue(emptyAttributes);
    assertEquals(emptyAttributes, obj.attributes());

    // Test case 2: Non-empty attributes
    Attributes nonEmptyAttributes = new Attributes();
    nonEmptyAttributes.put("key1", "value1");
    nonEmptyAttributes.put("key2", "value2");
    nonEmptyAttributes.put("key3", "value3");
    obj.setValue(nonEmptyAttributes);
    assertEquals(nonEmptyAttributes, obj.attributes());
}
@Test
public void test11() {
    // Create a new instance of the class
    MyClass obj = new MyClass();

    // Test case 1: When attributes are already ensured
    Attributes attributes1 = new Attributes();
    obj.setValue(attributes1);
    obj.ensureAttributes();
    assertEquals(attributes1, obj.attributes());

    // Test case 2: When attributes are not yet ensured
    obj.setValue(null);
    obj.ensureAttributes();
    assertNotNull(obj.attributes());
    assertTrue(obj.attributes().isEmpty());
}
@Test
public void test12() {
    // Create test object
    TestObject testObject = new TestObject(null);

    // Call the method under test
    testObject.ensureAttributes();

    // Assert that the value is an instance of Attributes
    assertTrue(testObject.value instanceof Attributes);
}
@Test
public void test13() {
    // Create test object
    TestObject testObject = new TestObject(123);

    // Call the method under test
    testObject.ensureAttributes();

    // Assert that the value is an instance of Attributes
    assertTrue(testObject.value instanceof Attributes);
    // Assert that the core value was added to the attributes
    assertEquals("123", testObject.attributes.get(testObject.nodeName()));
}
@Test
public void test14() {
    // Create test object with existing attributes
    Attributes attributes = new Attributes();
    attributes.put("name", "value");
    TestObject testObject = new TestObject(attributes);

    // Call the method under test
    testObject.ensureAttributes();

    // Assert that the value remains unchanged
    assertEquals(attributes, testObject.value);
}
    @Test public void test15() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());

        tn.attr(tn.nodeName(), "");
        assertEquals("", tn.text());
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test16() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test17(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test18() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());

        tail.text("");
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("", tail.getWholeText());
        assertEquals("Hello ", div.text());
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
        
        // Regression test 1
        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertTrue(five.isBlank());
        
        // Regression test 2
        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test22(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        // Regression test 1
        TextNode t2 = doc.body().textNodes().get(1);
        assertEquals("", t2.outerHtml().trim());
    }
    @Test public void test23() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        // Regression test 1
        TextNode tn2 = (TextNode) div.childNode(1);
        assertEquals("there", tn2.getWholeText());
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
        
        // Regression test 1
        tn.text("Hello!");
        tail.text(" there");
        assertEquals("Hello! there", div.text());
        
        // Regression test 2
        tn.text("  ");
        tail.text("Hello there");
        assertEquals("   Hello there", div.text());
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;&amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &&", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &&", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &&", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;&amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &&");
        assertEquals("kablam &&", tn.text());
        assertEquals("One <span>two &amp;&amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test26() {
        TextNode one = new TextNode("    ");
        TextNode two = new TextNode("");
        TextNode three = new TextNode(" \n\n  ");
        TextNode four = new TextNode("  Hello  ");
        TextNode five = new TextNode("  Hello  ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test27(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test28() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test29() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello", tn.getWholeText());
        assertEquals(" there", tail.getWholeText());
        tail.text(" there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test30() {
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
    @Test public void test31() {
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
    @Test public void test32(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test33() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test34() {
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
    @Test public void test35() {
        Document doc = Jsoup.parse("<p>Hello there</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        
        tn.attr("key", "value");
        assertEquals("", tn.text());
        assertEquals("value", tn.attr("key"));
        assertEquals("Hello there", p.html());
    }
    @Test public void test36() {
        Document doc = Jsoup.parse("<p>Hello there</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        
        tn.attr("text", "Hello");
        assertEquals("Hello", tn.text());
        assertEquals("", tn.attr("text"));
        assertEquals("Hello there", p.html());
    }
    @Test public void test37() {
        Document doc = Jsoup.parse("<p>p</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        
        tn.attr("p", "p");
        assertEquals("p", tn.text());
        assertEquals("", tn.attr("p"));
        assertEquals("p", p.html());
    }
    @Test public void test38() {
        Document doc = Jsoup.parse("<p>p</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        
        tn.attr("p", "value");
        assertEquals("", tn.text());
        assertEquals("value", tn.attr("p"));
        assertEquals("p", p.html());
    }
@Test
public void test39() {
    // Create a node with attributes
    Node node = new Node("div");
    node.attr("class", "container");
    node.attr("id", "myId");
    
    // Remove an attribute with specific value
    node.removeAttr("class");
    
    // Check that the attribute is removed
    assertNull(node.attr("class"));
    
    // Check that other attributes are not affected
    assertEquals("myId", node.attr("id"));
}
@Test
public void test40() {
    // Create a node with attributes
    Node node = new Node("div");
    node.attr("class", "container");
    node.attr("id", "myId");
    
    // Remove an attribute that doesn't exist
    node.removeAttr("style");
    
    // Check that attributes are not affected
    assertEquals("container", node.attr("class"));
    assertEquals("myId", node.attr("id"));
}
@Test
public void test41() {
    // Create a node with an attribute with empty value
    Node node = new Node("div");
    node.attr("class", "");
    
    // Remove the attribute with empty value
    node.removeAttr("class");
    
    // Check that the attribute is removed
    assertNull(node.attr("class"));
}
@Test
public void test42() {
    // Create a node with attributes
    Node node = new Node("div");
    node.attr("class", "container");
    node.attr("id", "myId");
    
    // Remove an attribute with null key
    node.removeAttr(null);
    
    // Check that attributes are not affected
    assertEquals("container", node.attr("class"));
    assertEquals("myId", node.attr("id"));
}
@Test
public void test43() {
    // Create a node with attributes
    Node node = new Node("div");
    node.attr("class", "container");
    node.attr("id", "myId");
    
    // Remove an attribute with a long key
    node.removeAttr("thisKeyIsVeryLongAndShouldNotExistButWeAreTestingLongKeys");
    
    // Check that attributes are not affected
    assertEquals("container", node.attr("class"));
    assertEquals("myId", node.attr("id"));
}
@Test
public void test44() {
    // Null key
    String result1 = absUrl(null);
    assertNull(result1);

    // Blank key
    String result2 = absUrl("");
    assertNull(result2);
}
@Test
public void test45() {
    // Valid key
    String result = absUrl("href");
    assertEquals("https://www.example.com", result);
}
@Test
public void test46() {
    Document doc = Jsoup.parse("<div>Hello</div>");
    Element div = doc.select("div").first();
    Element parent = doc.select("body").first();
    parent.appendChild(div);

    assertEquals("", div.baseUri());
}
@Test
public void test47() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();

    assertEquals("", div.baseUri());
}
    @Test
    public void test48() {
        // Change the baseUri to null
        doSetBaseUri(null);
        
        // Assertion statement
        // ...
    }
    @Test
    public void test49() {
        // Change the baseUri to an empty string
        doSetBaseUri("");
        
        // Assertion statement
        // ...
    }
    @Test
    public void test50() {
        // Change the baseUri to a different URI
        doSetBaseUri("http://www.example.com");
        
        // Assertion statement
        // ...
    }
    @Test public void test51() {
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
    @Test public void test52(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test55() {
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
    @Test public void test56() {
        Document doc = Jsoup.parse("");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals(0, tn.childNodeSize());
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals(0, tn.childNodeSize());
    }
    @Test public void test58() {
        Document doc = Jsoup.parse("<p>This is a long text node.</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals(0, tn.childNodeSize());
    }
@Test
public void test59() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> &nbsp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" ", tn.text()); // Change input value to empty space

    tn.text("POW!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test
public void test60() {
    Document doc = Jsoup.parse(new String(Character.toChars(135361)) + " ");
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test
public void test61() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(5); // Change split index to 5
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
}
@Test
public void test62() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(0, nodes.size());
}
@Test
public void test63() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(5); // Change split index to 5
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
}