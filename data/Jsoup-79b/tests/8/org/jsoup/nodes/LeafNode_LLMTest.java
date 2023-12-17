package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test 
    public void test0() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        assertFalse(div.hasAttr("value"));

        // change input value to div
        TextNode tn = new TextNode("", div);
        assertEquals("", tn.text());
        assertTrue(tn.isBlank());
        assertFalse(tn.hasAttributes());

        tn.attr("value", "test");
        assertTrue(tn.hasAttributes());
        assertEquals("test", tn.attr("value"));
    }
    @Test 
    public void test1() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        assertFalse(div.hasAttr("value"));

        // change input value to div
        TextNode tn = new TextNode("Hello", div);
        assertEquals("Hello", tn.text());
        assertFalse(tn.isBlank());
        assertFalse(tn.hasAttributes());

        tn.attr("value", "test");
        assertTrue(tn.hasAttributes());
        assertEquals("test", tn.attr("value"));
    }
    @Test 
    public void test2() {
        TextNode one = new TextNode("    ");
        assertTrue(one.isBlank());

        // change input value to ""
        TextNode two = new TextNode("", one.parent());
        assertTrue(two.isBlank());
    }
    @Test 
    public void test3(){
        Document doc = Jsoup.parse("<body></body>");
        Element body = doc.select("body").first();

        // change input value to body
        TextNode t = new TextNode("", body);
        assertTrue(t.isBlank());

        // add attribute to body
        body.attr("value", "test");

        t.attr(t.nodeName(), "kablam &");
        assertEquals("kablam &", t.text());
        assertEquals("kablam &", t.attr(t.nodeName()));
        assertEquals("kablam &", t.outerHtml().trim());
    }
    @Test 
    public void test4() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();

        // change input value to div
        TextNode tn = new TextNode("Hello there", div);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", div.html());
    }
    @Test 
    public void test5() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();

        // change input value to div
        TextNode tn = new TextNode("", div);
        TextNode tail = tn.splitText(6);
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
    }
    @Test
    public void test6() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();

        // change input value to div
        TextNode tn = new TextNode("Hello there", div);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
    }
@Test
public void test7() {
    // Ensure attributes have been initialized
    assertNotNull(attributes());
}
@Test
public void test8() {
    // Ensure attributes are empty
    assertTrue(attributes().isEmpty());
}
@Test
public void test9() {
    // Ensure attributes contain a specific key
    String key = "color";
    attributes().put(key, "red");
    assertTrue(attributes().containsKey(key));
}
@Test
public void test10() {
    // Ensure attributes contain a specific value
    String value = "red";
    attributes().put("color", value);
    assertTrue(attributes().containsValue(value));
}
@Test
public void test11() {
    // Ensure attributes have the correct size
    int size = 3;
    attributes().put("color", "red");
    attributes().put("size", "small");
    attributes().put("shape", "circle");
    assertEquals(size, attributes().size());
}
@Test
public void test12() {
    // Ensure an attribute can be successfully removed
    String key = "color";
    String value = "red";
    attributes().put(key, value);
    attributes().remove(key);
    assertFalse(attributes().containsKey(key));
    assertFalse(attributes().containsValue(value));
}
@Test
public void test13() {
    // Ensure all attributes can be successfully cleared
    attributes().put("color", "red");
    attributes().put("size", "small");
    attributes().put("shape", "circle");
    attributes().clear();
    assertTrue(attributes().isEmpty());
}
@Test
public void test14() {
    // Set up
    Object value = null;
    Attributes attributes = new Attributes();

    // Test
    attributes.ensureAttributes();

    // Verify
    assertNotEquals(value, attributes.getValue());
    assertTrue(attributes.hasAttributes());
    assertNull(attributes.getValue());
}
@Test
public void test15() {
    // Set up
    Object value = "test";
    Attributes attributes = new Attributes();

    // Test
    attributes.ensureAttributes();

    // Verify
    assertNotEquals(value, attributes.getValue());
    assertTrue(attributes.hasAttributes());
    assertEquals(value, attributes.getValue());
}
    @Test public void test16() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        // Covering the case when nodeName() returns an empty string
        // This will kill the mutation that changes attr() to return a different value
        assertEquals("", p.nodeName());

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
        // Covering the case when the text is not blank
        // This will kill the mutation that changes isBlank() to return a different value
        TextNode two = new TextNode("not blank");
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
        // Covering the case when the text contains multiple supplementary characters
        // This will kill the mutation that changes the handling of supplementary characters
        Document doc = Jsoup.parse(new String(Character.toChars(135361)) + new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)) + new String(Character.toChars(135361)), t.outerHtml().trim());
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
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());
    
    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());
    
    tn.text("regression test"); // changed input value
    assertEquals("One <span>two &amp;</span> regression test", TextUtil.stripNewlines(p.html()));
}
@Test public void test22() {
    TextNode one = new TextNode("regression test"); // changed input value
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    assertFalse(one.isBlank()); // changed assert condition
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test public void test23(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("regression test", t.outerHtml().trim()); // changed expected value
}
@Test public void test24() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>regression test</b>", TextUtil.stripNewlines(div.html())); // changed expected value
}
@Test public void test25() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("regression test"); // changed input value
    assertEquals("Hello regression test", div.text()); // changed expected value
    assertTrue(tn.parent() == tail.parent());
}
    @Test public void test26() {
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

        tn.attr(null, "kablam &");
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
    @Test public void test29() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("");
        TextNode five = new TextNode("  \n ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
        assertTrue(five.isBlank());
    }
    @Test public void test30(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test31(){
        Document doc = Jsoup.parse("");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
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
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(-1);
        assertEquals("Hello", tn.getWholeText());
        assertEquals(" there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test35() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(""); // Change the input value to an empty string
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html())); // Mutant: value = ""
    }
    @Test public void test36() {
        TextNode one = new TextNode(""); // Mutant: value = ""
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
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim()); // Mutant: value = ""
    }
    @Test public void test38() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test39() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("", tn.getWholeText()); // Mutant: value = ""
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("there!", div.text()); // Mutant: value = "there!"
        assertTrue(tn.parent() == tail.parent());
    }
    @Test
    public void test40() {
        // Existing attribute key
        assertTrue(objectUnderTest.hasAttr("key1")); // existing attribute key
    }
    @Test
    public void test41() {
        // Non-existing attribute key
        assertFalse(objectUnderTest.hasAttr("key2")); // non-existing attribute key
    }
    @Test
    public void test42() {
        // Empty key
        assertFalse(objectUnderTest.hasAttr(""));
    }
    @Test
    public void test43() {
        // Null key
        assertFalse(objectUnderTest.hasAttr(null));
    }
    @Test
    public void test44() {
        // Key with spaces
        assertFalse(objectUnderTest.hasAttr("key 3"));
    }
    @Test
    public void test45() {
        // Key with special characters
        assertFalse(objectUnderTest.hasAttr("key_4"));
    }
@Test
public void test46() {
    // Test with a key that exists in the attributes
    Node node1 = new Node();
    node1.attr("key1", "value1");
    node1.attr("key2", "value2");

    Node result1 = node1.removeAttr("key1");
    assertNull(result1.attr("key1"));

    // Test with a key that does not exist in the attributes
    Node node2 = new Node();
    node2.attr("key1", "value1");

    Node result2 = node2.removeAttr("key2");
    assertEquals(node2, result2);

    // Test with a null key
    Node node3 = new Node();

    Node result3 = node3.removeAttr(null);
    assertEquals(node3, result3);
}
    @Test
    public void test47() {
        String result = obj.absUrl(null);
        
        // Assertion
        assertNotNull(result);
    }
    @Test
    public void test48() {
        String result = obj.absUrl("");
        
        // Assertion
        assertNotNull(result);
    }
    @Test
    public void test49() {
        String result = obj.absUrl("missingKey");
        
        // Assertion
        assertNotNull(result);
    }
    @Test
    public void test50() {
        String result = obj.absUrl(123);
        
        // Assertion
        assertNotNull(result);
    }
    @Test
    public void test51() {
        String result = obj.absUrl("validKey");
        
        // Assertion
        assertNotNull(result);
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5); // Change the input value to 5 instead of 6
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7); // Change the input value to 7 instead of 6
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(10); // Change the input value to 10 instead of 6
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
@Test
public void test55() {
    doSetBaseUri(null);
    // add assertions
}
@Test
public void test56() {
    doSetBaseUri("");
    // add assertions
}
@Test
public void test57() {
    doSetBaseUri("htp://example.com");
    // add assertions
}
@Test
public void test58() {
    doSetBaseUri("http://example.com");
    // add assertions
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
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
        
        tn.text("HI!");
        assertEquals("One <span>two &amp;</span> HI!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("Hello");
        assertEquals("One <span>two &amp;</span> Hello", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("!!");
        assertEquals("One <span>two &amp;</span> !!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test63() {
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
    @Test public void test64(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test65() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test66() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test67() {
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
    @Test public void test68() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        TextNode tn = (TextNode) p.childNode(4); // Change the index to access the empty element
        assertEquals("", tn.text());
    }
    @Test public void test69() {
        Document doc = Jsoup.parse("");
        TextNode t = doc.body().textNodes().get(0); // Change the index to access a non-existing text node
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test70() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.splitText(100); // Change the split point to a value larger than the text length
        assertEquals("Hello there", tn.getWholeText());
    }
    @Test public void test71() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.childNode(0).text("child"); // Add a child node to the lead text node
        List<Node> nodes = tn.childNodes();
        assertEquals(1, nodes.size());
    }
}