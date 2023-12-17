package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test
    public void test0() {
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
    @Test
    public void test1() {
        TextNode one = new TextNode("Blank");
        TextNode two = new TextNode(" ");
        TextNode three = new TextNode("\n ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");
        
        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test
    public void test2() {
        Document doc = Jsoup.parse(new String(Character.toChars(135360)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135360)), t.outerHtml().trim());
    }
    @Test
    public void test3() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test
    public void test4() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there test!");
        assertEquals("Hello there test!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test5() {
    // Create a non-null value of type Attributes
    Attributes attributes = new Attributes();
    
    // Set the value of the method to the non-null value
    methodUnderTest.setValue(attributes);
    
    // Call the method and assert that the returned value is the same as the non-null value
    assertEquals(attributes, methodUnderTest.attributes());
}
@Test
public void test6() {
    // Set the value of the method to null
    methodUnderTest.setValue(null);
    
    // Call the method and assert that the returned value is null
    assertNull(methodUnderTest.attributes());
}
    @Test
    public void test7() {
        // Set up
        MyObject obj = new MyObject();
        obj.setValue("value");

        // Action
        obj.ensureAttributes();

        // Assertion
        assertTrue(obj.hasAttributes());
    }
    @Test
    public void test8() {
        // Set up
        MyObject obj = new MyObject();
        obj.setValue("value");

        // Action
        obj.ensureAttributes();

        // Assertion
        assertNotNull(obj.getAttributes().get(obj.getNodeName()));
        assertEquals(obj.getValue(), obj.getAttributes().get(obj.getNodeName()));
    }
    @Test
    public void test9() {
        // Set up
        MyObject obj = new MyObject();
        obj.setValue(null);

        // Action
        obj.ensureAttributes();

        // Assertion
        assertNull(obj.getAttributes().get(obj.getNodeName()));
    }
    @Test
    public void test10() {
        // Set up
        MyObject obj = new MyObject();
        obj.setValue(new Attributes());

        // Action
        obj.ensureAttributes();

        // Assertion
        assertEquals(obj.getValue(), obj.getAttributes());
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
    // Change the expected value to a different core value
    // The original expected value: "One <span>two &amp;</span>kablam &amp;"
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test12() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    // Change the expected values to cover different scenarios
    // The original expected values: assertTrue, assertTrue, assertTrue, assertFalse, assertFalse
    assertFalse(one.isBlank());
    assertFalse(two.isBlank());
    assertFalse(three.isBlank());
    assertTrue(four.isBlank());
    assertTrue(five.isBlank());
}
@Test public void test13(){
    // Change the input to test different supplementary character
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    // Change the expected value to match the modified input
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test14() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    // Change the expected value to a different stripped HTML
    // The original expected value: "Hello <b>there</b>"
    assertEquals("Hello there", TextUtil.stripNewlines(div.html()));
}
@Test public void test15() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    // Change the text value of tail
    // The original expected value: "Hello there!"
    tail.text("there");
    assertEquals("Hello there", div.text());
    assertTrue(tn.parent() == tail.parent());
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
    @Test public void test22() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("\t\t");
        TextNode three = new TextNode("\n\t\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test23() {
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3);
        tail.wrap("<b></b>");

        assertEquals("Hel<b>lo there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3);
        assertEquals("Hel", tn.getWholeText());
        assertEquals("lo there", tail.getWholeText());
        tail.text("lo there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test26() {
        // Original test case
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
        
        // Regression test case 1: Null key
        doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        p = doc.select("p").first();

        span = doc.select("span").first();
        assertEquals("two &", span.text());
        spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(null, "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
        
        // Regression test case 2: Empty key
        doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        p = doc.select("p").first();

        span = doc.select("span").first();
        assertEquals("two &", span.text());
        spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr("", "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test27() {
        // Original test case
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
        
        // Regression test case 3: TextNode with only whitespace
        TextNode six = new TextNode("    ");
        assertTrue(six.isBlank());
    }
    @Test public void test28(){
        // Original test case
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        // Regression test case 4: Empty String
        doc = Jsoup.parse("");
        t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test29() {
        // Original test case
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        // Regression test case 5: Empty String
        doc = Jsoup.parse("<div></div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test30() {
        // Original test case
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
        
        // Regression test case 6: TextNode with only whitespace
        doc = Jsoup.parse("<div>   </div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(1);
        assertEquals(" ", tn.getWholeText());
        assertEquals("  ", tail.getWholeText());
        tail.text("  there");
        assertEquals("  there", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test31() {
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
public void test32() {
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
public void test33() {
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test
public void test34() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test
public void test35() {
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
public void test36() {
    Document doc = Jsoup.parse("<div>test</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    tn.attr("", "value");

    assertEquals("", tn.text());
    assertEquals("value", div.attr(""));
}
@Test
public void test37() {
    Document doc = Jsoup.parse("<div>test</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    tn.attr("key", "");

    assertEquals("", tn.text());
    assertEquals("", div.attr("key"));
}
@Test
public void test38() {
    Document doc = Jsoup.parse("<div>test</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    tn.attr("key", "value");

    assertEquals("value", tn.text());
    assertEquals("value", div.attr("key"));
}
@Test
public void test39() {
    assertFalse(hasAttr(""));
}
@Test
public void test40() {
    assertFalse(hasAttr(null));
}
@Test
public void test41() {
    assertTrue(hasAttr("key"));
}
@Test
public void test42() {
    assertTrue(hasAttr("this_is_a_very_long_key_that_exceeds_the_maximum_length"));
}
@Test
public void test43() {
    // Create a new element
    Element element = new Element("div");

    // Add an attribute to the element
    element.attr("class", "container");
  
    // Call the removeAttr method with a valid key
    Node removedNode = element.removeAttr("class");

    // Assert that the removed node is not null
    assertNotNull(removedNode);
    // Assert that the key of the removed node is "class"
    assertEquals("class", removedNode.getKey());
    // Assert that the value of the removed node is "container"
    assertEquals("container", removedNode.getValue());
}
@Test
public void test44() {
    // Create a new element
    Element element = new Element("div");

    // Add an attribute to the element
    element.attr("class", "container");

    // Call the removeAttr method with an invalid key
    Node removedNode = element.removeAttr("id");

    // Assert that the removed node is null
    assertNull(removedNode);
}
@Test
public void test45() {
    // Create a new element
    Element element = new Element("div");

    // Add an attribute to the element
    element.attr("class", "container");
  
    // Call the removeAttr method with a null key
    Node removedNode = element.removeAttr(null);

    // Assert that the removed node is null
    assertNull(removedNode);
}
    @Test public void test46() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
        assertEquals("Hello <b>there</b>", tail.parent().baseUri()); // mutant: returns wrong base URI when parent exists
    }
    @Test public void test47() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.unwrap(); // removes parent of the text node
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
        assertEquals("", tail.parent().baseUri()); // mutant: returns wrong base URI when parent does not exist
    }
@Test
public void test48() {
    String baseUri = "www.example.com";
    doSetBaseUri(baseUri);

    // Verify that the baseUri is set correctly
    assertEquals(baseUri, getBaseUri());
}
@Test
public void test49() {
    String baseUri = null;
    doSetBaseUri(baseUri);

    // Verify that the baseUri is set correctly
    assertEquals(baseUri, getBaseUri());
}
@Test
public void test50() {
    String baseUri = "";
    doSetBaseUri(baseUri);

    // Verify that the baseUri is set correctly
    assertEquals(baseUri, getBaseUri());
}
    @Test public void test51() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam");
        assertEquals("kablam", tn.text());
        assertEquals("One <span>two &amp;</span>kablam", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test52(){
        Document doc = Jsoup.parse(new String(Character.toChars(65535)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("", div.html().trim());
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<div>Hello</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(1, nodes.size());
    }
    @Test public void test55() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(20);
        assertNull(tail);
        assertEquals("Hello there", tn.getWholeText());
    }
    @Test public void test56() {
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
    @Test public void test57(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test58() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test59() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test60() {
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
    @Test public void test61() {
        try {
            Document doc = Jsoup.parse("<p>Hello</p>");
            Element p = doc.select("p").first();
            p.ensureChildNodes();
            
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // pass
        }
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        List<Node> childNodes = p.ensureChildNodes();
        
        assertTrue(childNodes.isEmpty());
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
        try {
            Document doc = Jsoup.parse("<p>Hello</p>");
            Element p = doc.select("p").first();
            p.ensureChildNodes();
            
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // pass
        }
    }
    @Test public void test69() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        List<Node> childNodes = p.ensureChildNodes();
        
        assertTrue(childNodes.isEmpty());
    }
    @Test public void test70() {
        Document doc = Jsoup.parse("<span>Hello</span>");
        Element span = doc.select("span").first();
        
        List<Node> childNodes = span.ensureChildNodes();
        
        assertTrue(childNodes.isEmpty());
    }
    @Test public void test71() {
        Document doc = Jsoup.parse("<div>Hello<span>world</span>!</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        
        List<Node> childNodes = tn.ensureChildNodes();
        
        assertTrue(childNodes.size() > 0);
    }
}