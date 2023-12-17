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
        TextNode three = new TextNode("  \t\t   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \tHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test2() {
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
        // any non-null value
        Object value = new Object();
        
        // Set value to null
        value = null;
        
        // Create instance of the class under test
        // and set the value
        MyClass myClass = new MyClass();
        myClass.setValue(value);
        
        // Call the method to be tested
        Attributes attributes = myClass.attributes();
        
        // Assert that the returned object is null
        assertNull(attributes);
    }
    @Test
    public void test6() {
        // any value other than Attributes
        Object value = "string";
        
        // Create instance of the class under test
        // and set the value
        MyClass myClass = new MyClass();
        myClass.setValue(value);
        
        // Call the method to be tested
        Attributes attributes = myClass.attributes();
        
        // Assert that the returned object is null
        assertNull(attributes);
    }
    @Test
    public void test7() {
        // any object other than Attributes
        Object value = new Object();
        
        // Create instance of the class under test
        // and set the value
        MyClass myClass = new MyClass();
        myClass.setValue(value);
        
        // Call the method to be tested
        Attributes attributes = myClass.attributes();
        
        // Assert that the returned object is null
        assertNull(attributes);
    }
@Test
public void test8() {
    // Ensure that when the value is null, the attributes are created with no key-value pairs
    Object initialCoreValue = null;
    Object expectedValue = new Attributes();

    // Check that the method creates the attributes with no key-value pairs
    ensureAttributes();
    assertEquals(expectedValue, value);

    // Check that the method does not modify the coreValue
    assertEquals(initialCoreValue, value);
}
@Test
public void test9() {
    // Ensure that when the value is non-null, the attributes are created with the key-value pair using the nodeName as key
    Object initialCoreValue = "TestValue";
    String expectedKey = nodeName();
    String expectedValue = (String) initialCoreValue;

    // Check that the method creates the attributes with the expected key-value pair
    ensureAttributes();
    assertEquals(1, ((Attributes) value).size());
    assertEquals(expectedValue, ((Attributes) value).get(expectedKey));

    // Check that the method does not modify the coreValue
    assertEquals(initialCoreValue, value);
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

    tn.text(" POWER!");
    assertEquals("One <span>two &amp;</span> POWER!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test11() {
    TextNode one = new TextNode("Hello");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("");
    TextNode five = new TextNode("  \nHello ");

    assertFalse(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertTrue(four.isBlank());
    assertFalse(five.isBlank());
}
@Test public void test12(){
    Document doc = Jsoup.parse(new String(Character.toChars(135362)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
}
@Test public void test13() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(5);
    tail.wrap("<b></b>");

    assertEquals("Hello<there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<there there... must correct
}
@Test public void test14() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(5);
    assertEquals("Hello", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!!");
    assertEquals("Hello there!!", div.text());
    assertTrue(tn.parent() == tail.parent());
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

    tn.text(" POW!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test16() {
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
@Test public void test17(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test18() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
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

    tn.text(" BAM!");
    assertEquals("One <span>two &amp;</span> BAM!", TextUtil.stripNewlines(p.html()));

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
}
@Test public void test22(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test23() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
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
}
    @Test public void test25() {
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
    @Test public void test26() {
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
    @Test public void test27(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test28() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test29() {
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
    @Test public void test30() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        TextNode spanText = (TextNode) span.childNode(0);
        
        String result = spanText.attr(null); // change input value to null
        
        assertEquals("", result);
    }
    @Test public void test31() {
        TextNode tn = new TextNode("");
        
        String result = tn.attr("key"); // change input value to "key"
        
        assertEquals("", result);
    }
    @Test public void test32() {
        TextNode tn = new TextNode("     ");
        
        String result = tn.attr("key"); // change input value to "key"
        
        assertEquals("", result);
    }
    @Test public void test33() {
        TextNode tn = new TextNode(new String(Character.toChars(135361)));
        
        String result = tn.attr("key"); // change input value to "key"
        
        assertEquals("", result);
    }
    @Test public void test34() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        
        tn.splitText(6);
        String result = tn.attr("key"); // change input value to "key"
        
        assertEquals("", result);
    }
@Test public void test35() {
        // Original Test Case
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
    @Test public void test36() {
        // Change the value of tn to blank
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
    @Test public void test37(){
        // Change the supplementary character to be parsed
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test38() {
        // Change the value of splitText() method parameter
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test39() {
        // Change the value of splitText() method parameter
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test40() {
  // Existing key
  String key = "testKey";
  Element element = new Element();
  element.attr(key, "testValue");

  boolean result = element.hasAttr(key);

  assertTrue(result);
}
@Test
public void test41() {
  // Non-existing key
  String key = "nonExistingKey";
  Element element = new Element();

  boolean result = element.hasAttr(key);

  assertFalse(result);
}
@Test
public void test42() {
  // Null key
  String key = null;
  Element element = new Element();

  boolean result = element.hasAttr(key);

  assertFalse(result);
}
@Test
public void test43() {
  // Empty key
  String key = "";
  Element element = new Element();

  boolean result = element.hasAttr(key);

  assertFalse(result);
}
@Test
public void test44() {
  // Whitespace key
  String key = " ";
  Element element = new Element();

  boolean result = element.hasAttr(key);

  assertFalse(result);
}
@Test
public void test45() {
    // Test case 1: Remove an existing attribute
    Node node = new Node();
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    Node result = node.removeAttr("key1");
    assertNull(result.attr("key1")); // The key should be removed
    assertEquals("value2", result.attr("key2")); // The other key should still exist

    // Test case 2: Remove a non-existing attribute
    Node node2 = new Node();
    node2.attr("key1", "value1");
    node2.attr("key2", "value2");
    Node result2 = node2.removeAttr("key3");
    assertEquals("value1", result2.attr("key1")); // The existing keys should still exist
    assertEquals("value2", result2.attr("key2"));

    // Test case 3: Remove attribute on a node with no attributes
    Node node3 = new Node();
    Node result3 = node3.removeAttr("key1");
    assertNull(result3.attr("key1")); // The attribute should still be null

    // Test case 4: Remove attribute on a node with empty attribute map
    Node node4 = new Node();
    node4.ensureAttributes();
    Node result4 = node4.removeAttr("key1");
    assertNull(result4.attr("key1")); // The attribute should still be null
}
@Test
public void test46() {
    String result = myClass.absUrl(null);
    // assert result
}
@Test
public void test47() {
    String result = myClass.absUrl("");
    // assert result
}
@Test
public void test48() {
    String result1 = myClass.absUrl("key1");
    // assert result1

    String result2 = myClass.absUrl("key2");
    // assert result2

    String result3 = myClass.absUrl("key3");
    // assert result3
}
    @Test public void test49() {
        // Test case 1: Empty base uri
        Document doc1 = Jsoup.parse("<div>Hello there</div>");
        Element div1 = doc1.select("div").first();
        TextNode tn1 = (TextNode) div1.childNode(0);
        TextNode tail1 = tn1.splitText(6);
        tail1.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div1.html())); // not great that we get \n<b>there there... must correct

        // Test case 2: Non-empty base uri
        Document doc2 = Jsoup.parse("<div>Hello there</div>");
        Element div2 = doc2.select("div").first();
        TextNode tn2 = (TextNode) div2.childNode(0);
        tn2.baseUri("http://www.example.com/");
        TextNode tail2 = tn2.splitText(6);
        tail2.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div2.html())); // not great that we get \n<b>there there... must correct
    }
@Test
public void test50() {
    // Setting an empty base URI
    doSetBaseUri("");
    String actualBaseUri = getBaseUri();
    assertEquals("", actualBaseUri);
}
@Test
public void test51() {
    // Setting an invalid base URI
    doSetBaseUri("htp://www.example.com");
    String actualBaseUri = getBaseUri();
    assertEquals("htp://www.example.com", actualBaseUri);
}
@Test 
public void test52() {
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
public void test53(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test 
public void test54() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test 
public void test55() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(0, nodes.size());
}
@Test 
public void test56() {
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
public void test57() {
    Document doc = Jsoup.parse("<div>No child nodes</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    int childNodeSize = tn.childNodeSize();
    assertEquals(0, childNodeSize);
}
@Test 
public void test58() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    int childNodeSize = tn.childNodeSize();
    assertEquals(2, childNodeSize);
}
    @Test
    public void test59() {
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
    public void test60(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test
    public void test61() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
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
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
}