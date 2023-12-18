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
    TextNode one = new TextNode(" Hello");
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
    // Test case 1: Test with empty attributes
    assertEquals(attributes, attributes());
    
    // Test case 2: Test with non-empty attributes
    attributes.put("key1", "value1");
    attributes.put("key2", "value2");
    assertEquals(attributes, attributes());
    
    // Test case 3: Test with null attributes
    attributes.put("key3", null);
    assertEquals(attributes, attributes());

    // Test case 4: Test with different attributes
    attributes.put("key4", "value4");
    assertFalse(attributes.equals(attributes()));
}
private void ensureAttributes() {
    // Setup - Assuming value already has attributes
    Object coreValue = "testCoreValue";
    Attributes attributes = new Attributes();
    value = attributes;
    attributes.put("existingAttribute", "existingValue");

    // Method being tested
    if (!hasAttributes()) {
        attributes.put(nodeName(), (String) coreValue);
    }

    // Assertion
    // Assert that no new attribute is added since value already has attributes
    assertEquals(1, attributes.size());
    assertEquals("existingValue", attributes.get("existingAttribute"));
}
private void ensureAttributes() {
    // Setup - Assuming value does not have attributes
    value = null; // Assuming value is null
    Object coreValue = "testCoreValue";

    // Method being tested
    if (!hasAttributes()) {
        Object originalValue = value;
        Attributes attributes = new Attributes();
        value = attributes;
        attributes.put(nodeName(), (String) coreValue);
    }

    // Assertion
    // Assert that new attributes are added to value
    assertNotNull(value);
    assertTrue(value instanceof Attributes);
    Attributes attributes = (Attributes) value;
    assertEquals(1, attributes.size());
    assertEquals("testCoreValue", attributes.get(nodeName()));
}
private void ensureAttributes() {
    // Setup - Assuming value does not have attributes
    value = null; // Assuming value is null
    Object coreValue = null;

    // Method being tested
    if (!hasAttributes()) {
        Object originalValue = value;
        Attributes attributes = new Attributes();
        value = attributes;
        // No new attribute is added since coreValue is null
    }

    // Assertion
    // Assert that no new attribute is added to value
    assertNull(value);
}
    @Test public void test6() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(2);
        
        // Change the input value of the method
        tn.text(" four &");

        // Verify the result
        assertEquals(" four &", tn.text());
        assertEquals("One <span>two &amp;</span> four &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test7() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        // Change the input value of the method
        assertFalse(one.isBlank());

        // Verify the result
        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test8() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);

        // Change the input value of the method
        assertEquals("", t.outerHtml().trim());

        // Verify the result
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test9() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        // Change the input value of the method
        assertEquals("Hello there", TextUtil.stripNewlines(div.html()));

        // Verify the result
        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test10() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);

        // Change the input value of the method
        tail.text("!");

        // Verify the result
        assertEquals("Hello !", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text("REGRESSION_TEST_CASE_1");
        assertEquals("One <span>two &amp;</span> REGRESSION_TEST_CASE_1", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test12() {
        TextNode one = new TextNode("REGRESSION_TEST_CASE_2");
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
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test14() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test15() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("REGRESSION_TEST_CASE_3");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test16() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        // Change the input value of tn.text() to test different scenarios
        TextNode tn = (TextNode) p.childNode(2);
        tn.text(""); // Empty string

        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test17() {
        // Change the input value to test different scenarios
        TextNode one = new TextNode("Hello"); // Not blank
        
        assertFalse(one.isBlank());
    }
    @Test public void test18() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        // Change the input value of div.html() to test different scenarios
        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);

        // Change the input value of tail.text() to test different scenarios
        tail.text("there!!!");

        assertEquals("Hello there!!!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test 
public void test20() {
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
public void test21() {
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
public void test22(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test 
public void test23() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test 
public void test24() {
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
public void test25() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    TextNode spanText = (TextNode) span.childNode(0);
    
    spanText.attr("", "newValue");
    assertEquals("newValue", spanText.text());
}
@Test 
public void test26() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    TextNode spanText = (TextNode) span.childNode(0);
    
    spanText.attr("attrKey", "attrValue");
    assertEquals("two &", spanText.text());
    assertEquals("attrValue", spanText.attr("attrKey"));
}
@Test 
public void test27() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    TextNode spanText = (TextNode) span.childNode(0);
    
    spanText.attr("span", "newValue");
    assertEquals("two &", spanText.text());
    assertEquals("newValue", spanText.attr("span"));
}
@Test 
public void test28() {
    Document doc = Jsoup.parse("<div></div>");
    Element div = doc.select("div").first();

    TextNode textNode = new TextNode("example");
    
    div.appendChild(textNode);
    textNode.attr("div", "attrValue");
    assertEquals("example", textNode.text());
    assertEquals("attrValue", textNode.attr("div"));
}
@Test
public void test29() {
    // existing key "name"
    assertTrue(hasAttr("name"));
}
@Test
public void test30() {
    // non-existing key "age"
    assertFalse(hasAttr("age"));
}
@Test
public void test31() {
    // null key
    assertFalse(hasAttr(null));
}
@Test
public void test32() {
    // empty key
    assertFalse(hasAttr(""));
}
    @Test
    public void test33() {
        // create a sample node
        Node node = new Node("sample");
        node.attr("key1", "value1");
        node.attr("key2", "value2");
        
        // remove an existing attribute
        Node result = node.removeAttr("key1");
        
        // ensure that the attribute has been removed
        assertNull(result.attr("key1"));
        assertEquals("value2", result.attr("key2"));
    }
    @Test
    public void test34() {
        // create a sample node
        Node node = new Node("sample");
        node.attr("key1", "value1");
        node.attr("key2", "value2");
        
        // remove a non-existing attribute
        Node result = node.removeAttr("key3");
        
        // ensure that the original node is not modified
        assertEquals("value1", node.attr("key1"));
        assertEquals("value2", node.attr("key2"));
        
        // ensure that the returned node is the same as the original node
        assertSame(node, result);
    }
    @Test
    public void test35() {
        // create a sample node without any attributes
        Node node = new Node("sample");
        
        // remove an attribute
        Node result = node.removeAttr("key1");
        
        // ensure that the original node is not modified
        assertTrue(node.attributes().isEmpty());
        
        // ensure that the returned node is the same as the original node
        assertSame(node, result);
    }
@Test
public void test36() {
    // Change input from key = "valid_key" to key = null
    String result = absUrl(null);
    // Verify the result
    assertNull(result);
}
@Test
public void test37() {
    // Change input from key = "valid_key" to key = ""
    String result = absUrl("");
    // Verify the result
    assertEquals("", result);
}
@Test
public void test38() {
    // Change input from key = "valid_key" to key = "invalid_key"
    String result = absUrl("invalid_key");
    // Verify the result
    assertNull(result);
}
    @Test public void test39() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test40() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);

        assertEquals("", tn.baseUri());
    }
    @Test public void test41() {
        Document doc = Jsoup.parse("<div>Hello</div><div>there</div>");
        Element div1 = doc.select("div").first();
        TextNode tn = (TextNode) div1.childNode(0);

        assertEquals("", tn.baseUri());
    }
    @Test
    public void test42() {
        doSetBaseUri(null);
        // assert something
    }
    @Test
    public void test43() {
        doSetBaseUri("");
        // assert something
    }
    @Test
    public void test44() {
        doSetBaseUri("https://example.com");
        // assert something
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
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        
        assertEquals(0, tn.childNodeSize());
    }
    @Test public void test51() {
        Document doc = Jsoup.parse(new String(Character.toChars(135360)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135360)), t.outerHtml().trim());
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello, how are you?</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7);
        tail.wrap("<b></b>");

        assertEquals("Hello, <b>how are you?</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div>Hi there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3);
        assertEquals("Hi ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hi there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
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
    @Test public void test56() {
        // change the text value to ""
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(""); // changed input
        
        // expect UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> { p.ensureChildNodes(); });
    }
    @Test public void test57() {
        // change the text value to "null"
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("null"); // changed input
        
        // expect UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> { p.ensureChildNodes(); });
    }
    @Test public void test58() {
        // change the index value to 0
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals(0, tn.siblingIndex()); // changed input
        
        // expect UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> { p.ensureChildNodes(); });
    }
    @Test public void test59() {
        // change the index value to 5
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals(5, tn.siblingIndex()); // changed input
        
        // expect UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> { p.ensureChildNodes(); });
    }
    @Test public void test60() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test61() {
        // change the supplementary character to a regular character
        Document doc = Jsoup.parse("<div>Hello there</div>"); // changed input
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test63() {
        // remove the text value from the div tag
        Document doc = Jsoup.parse("<div></div>"); // changed input
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test64() {
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
    @Test public void test65() {
        // change the index value to 0
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0); // changed input
        assertEquals("", tn.getWholeText());
        assertEquals("Hello there", tail.getWholeText());
        tail.text("there!");
        assertEquals(" there!", div.text()); // changed expectation
        assertFalse(tn.parent() == tail.parent()); // changed expectation
    }
    @Test public void test66() {
        // change the index value to 12
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(12); // changed input
        assertEquals("Hello there", tn.getWholeText()); // changed expectation
        assertEquals("", tail.getWholeText()); // changed expectation
        tail.text("there!");
        assertEquals("Hello ", div.text()); // changed expectation
        assertFalse(tn.parent() == tail.parent()); // changed expectation
    }
}