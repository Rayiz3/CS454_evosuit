package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
@Test
public void test0() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;&nbsp;</span> three &nbsp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two & ", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two & ", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &nbsp;", tn.text());
    
    tn.text(" POW!");
    assertEquals("One <span>two &amp;&nbsp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &nbsp;");
    assertEquals("kablam &nbsp;", tn.text());
    assertEquals("One <span>two &amp;&nbsp;</span>kablam &amp;nbsp;", TextUtil.stripNewlines(p.html()));
}
@Test
public void test1() {
    TextNode one = new TextNode("&nbsp;");
    TextNode two = new TextNode("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    TextNode three = new TextNode("&nbsp;\n\n&nbsp;");
    TextNode four = new TextNode("&nbsp;Hello");
    TextNode five = new TextNode("&nbsp;\nHello ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test
public void test2(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)) + "&nbsp;");
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)) + "&nbsp;", t.outerHtml().trim());
}
@Test
public void test3() {
    Document doc = Jsoup.parse("<div>Hello &nbsp; there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello &nbsp; <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test
public void test4() {
    Document doc = Jsoup.parse("<div>Hello &nbsp; there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("&nbsp;there", tail.getWholeText());
    tail.text("&nbsp;there!");
    assertEquals("Hello &nbsp;there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test
public void test5() {
    // Test case to cover the branch where the coreValue is null
    Object value = null;
    // Insert the given method invocation here with the modified input
    Attributes attributes = new Attributes();
    attributes.put("nodeName", (String) value);
    assertTrue(attributes.hasAttributes());
}
@Test
public void test6() {
    // Test case to cover the branch where the coreValue is a string
    Object value = "testValue";
    // Insert the given method invocation here with the modified input
    Attributes attributes = new Attributes();
    attributes.put("nodeName", (String) value);
    assertTrue(attributes.hasAttributes());
}
@Test
public void test7() {
    // Test case to cover the branch where the coreValue is an integer
    Object value = 123;
    // Insert the given method invocation here with the modified input
    Attributes attributes = new Attributes();
    attributes.put("nodeName", String.valueOf(value));
    assertTrue(attributes.hasAttributes());
}
@Test
public void test8() {
    // Test case to cover the branch where the coreValue is a double
    Object value = 1.23;
    // Insert the given method invocation here with the modified input
    Attributes attributes = new Attributes();
    attributes.put("nodeName", String.valueOf(value));
    assertTrue(attributes.hasAttributes());
}
    @Test public void test9() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;&amp;</span> three &amp; &amp;</p>"); // Change input value here
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &&", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &&", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &&", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;&amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;&amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test10() {
        TextNode one = new TextNode(""); // Change input value here
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
    @Test public void test11(){
        Document doc = Jsoup.parse(new String(Character.toChars(135360))); // Change input value here
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135360)), t.outerHtml().trim());
    }
    @Test public void test12() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test13() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6); // Change input value here
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test15() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertNull(tn.text());
        
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
    @Test public void test17() {
        TextNode one = new TextNode(" ");
        TextNode two = new TextNode("   ");
        TextNode three = new TextNode("\n\n     ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test18(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test19(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertNull(t.outerHtml().trim());
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
        TextNode tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b>Hello there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
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
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("Hello there", tail.getWholeText());
        tail.text("there!");
        assertEquals("there!Hello there", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("");
        assertEquals("", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "&");
        assertEquals("&", tn.text());
        assertEquals("One <span>two &amp;</span>&amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test25() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("");
        TextNode five = new TextNode("  \n ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test26(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test27() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b></b> there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test28() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("");
        assertEquals("Hello ", div.text());
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
    @Test public void test34(){
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(2);
        
        tn.attr("attr1", "value1");
        assertEquals("value1", tn.attr("attr1"));
    }
    @Test public void test35(){
        TextNode tn = new TextNode("Hello");
        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("Hello", tn.text());
        assertEquals("", tn.attr(tn.nodeName()));
    }
    @Test public void test36(){
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(2);
        
        tn.attr("attr1", "value1");
        assertEquals("", tn.attr(tn.nodeName()));
    }
    @Test public void test37(){
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = new TextNode("");
        
        tn.attr("attr1", "value1");
        assertEquals("value1", tn.attr("attr1"));
    }
    @Test public void test38(){
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = new TextNode("Hello");
        
        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("Hello", tn.text());
        assertEquals("", tn.attr(tn.nodeName()));
    }
    @Test public void test39(){
        TextNode tn = new TextNode("");
        tn.value("Hello");
        assertEquals("Hello", tn.text());
    }
    @Test public void test40(){
        TextNode tn = new TextNode("");
        tn.value("");
        assertEquals("", tn.text());
    }
@Test
public void test41() {
    // Change the input to have a different key
    String key = "attributeKey";
    
    // Rest of the test remains the same
    Element element = // create an instance of the Element class
    element.attr(key, "attributeValue");
    
    boolean result = element.hasAttr(key);
    
    // Assert the result
    assertTrue(result);
}
@Test
public void test42() {
    // Change the input to have a different key
    String key = "attributeKey";
    
    // Rest of the test remains the same
    Element element = // create an instance of the Element class
    
    boolean result = element.hasAttr(key);
    
    // Assert the result
    assertFalse(result);
}
@Test
public void test43() {
    // Change the input to have a null key
    String key = null;
    
    // Rest of the test remains the same
    Element element = // create an instance of the Element class
    
    boolean result = element.hasAttr(key);
    
    // Assert the result
    assertFalse(result);
}
    @Test
    public void test44() {
        // Original test case
        Node node1 = new Node();
        node1.putAttr("color", "red");
        Node returnedNode1 = node1.removeAttr("color");
        assertTrue(returnedNode1.getAttributes().size() == 0);

        // Test case with key not present in attributes
        Node node2 = new Node();
        Node returnedNode2 = node2.removeAttr("size");
        assertTrue(returnedNode2.getAttributes().size() == 0);

        // Test case with multiple attributes
        Node node3 = new Node();
        node3.putAttr("color", "red");
        node3.putAttr("size", "small");
        node3.putAttr("weight", "light");
        Node returnedNode3 = node3.removeAttr("color");
        assertTrue(returnedNode3.getAttributes().size() == 2);
    }
@Test
public void test45() {
    // Arrange
    String key = "link";
    
    // Act
    String result = absUrl(key);
    
    // Assert
    // Insert assertions here
    
    // Additional test cases
    // Test with different valid key
    key = "img";
    result = absUrl(key);
    // Insert assertions here
    
    // Test with empty string as key
    key = "";
    result = absUrl(key);
    // Insert assertions here
    
    // Test with null as key
    key = null;
    result = absUrl(key);
    // Insert assertions here
    
    // Test with invalid key
    key = "invalidKey";
    result = absUrl(key);
    // Insert assertions here
}
    @Test public void test46() {
        Document doc = Jsoup.parse("<p>This is a paragraph.</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        TextNode tail = tn.splitText(2);
        tail.wrap("<b></b>");

        assertEquals("Th<b>is is a paragraph.</b>", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test47() {
        Document doc = Jsoup.parse("<div><span>Testing</span>123</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(1);
        TextNode tail = tn.splitText(4);
        tail.wrap("<b></b>");

        assertEquals("<span>Testing</span><b>123</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test48() {
        Document doc = Jsoup.parse("<p>Hello, world!</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        TextNode tail = tn.splitText(8);
        tail.wrap("<b></b>");

        assertEquals("Hello, <b>world!</b>", TextUtil.stripNewlines(p.html()));
    }
    @Test
    public void test49() {
        String baseUri = "https://example.com";
        
        // Call the method under test
        doSetBaseUri(baseUri);
        
        // Perform assertions or verifications
        // ...
    }
    @Test
    public void test50() {
        String baseUri = "";
        
        // Call the method under test
        doSetBaseUri(baseUri);
        
        // Perform assertions or verifications
        // ...
    }
    @Test
    public void test51() {
        String baseUri = null;
        
        // Call the method under test
        doSetBaseUri(baseUri);
        
        // Perform assertions or verifications
        // ...
    }
    @Test
    public void test52() {
        String baseUri = "https://example.com/#/page?param=value";
        
        // Call the method under test
        doSetBaseUri(baseUri);
        
        // Perform assertions or verifications
        // ...
    }
    @Test
    public void test53() {
        String baseUri = "https://" + "a".repeat(1000);
        
        // Call the method under test
        doSetBaseUri(baseUri);
        
        // Perform assertions or verifications
        // ...
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" ANOTHER TEXT");
        assertEquals("One <span>two &amp;</span> ANOTHER TEXT", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test55(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test56() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test58() {
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
    @Test public void test59() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" YET ANOTHER TEXT");
        assertEquals("One <span>two &amp;</span> YET ANOTHER TEXT", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &amp;");
        assertEquals("kablam &amp;", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test60(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertNotEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertNotEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); 
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertNotEquals(2, nodes.size());
    }
    @Test public void test63() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there!", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertFalse(tn.parent() == tail.parent());
    }
@Test
public void test64() {
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
public void test65() {
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test
public void test66() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test
public void test67() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(0, nodes.size());
}
@Test
public void test68() {
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
public void test69() {
    Document doc = Jsoup.parse("<div></div>");
    Element div = doc.select("div").first();
    TextNode tn = new TextNode("Hello", "");
    div.appendChild(tn);

    assertThrows(UnsupportedOperationException.class, () -> tn.ensureChildNodes());
}
}