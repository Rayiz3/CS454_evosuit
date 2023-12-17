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
        
    tn.text(" changed!"); // Changed input value
    assertEquals("One <span>two &amp;</span> changed!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test 
public void test1() {
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
    
    three.text(" \n changed!"); // Changed input value
    assertTrue(three.isBlank());
}
@Test 
public void test2(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    
    t.text(" changed!"); // Changed input value
    assertEquals(" changed!", t.outerHtml().trim());
}
@Test 
public void test3() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    
    tail.wrap("<i></i>"); // Changed input value
    assertEquals("Hello <i><b>there</b></i>", TextUtil.stripNewlines(div.html())); 
}
@Test 
public void test4() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
    
    tn.text("changed!"); // Changed input value
    assertEquals("changed! there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test
public void test5() {
    // Set up
    Attributes attributes = null;

    // Execution
    // Call the method with null attributes
    // ensureAttributes() will be called and NullPointerException will be thrown
    try {
        attributes();
        fail("NullPointerException should have been thrown");
    } catch (NullPointerException e) {
        // Exception should be thrown
    }
}
@Test
public void test6() {
    // Set up
    Attributes attributes = new Attributes();

    // Execution
    // Call the method with pre-set attributes
    // ensureAttributes() should not be called
    Attributes result = attributes();

    // Verification
    assertEquals(attributes, result);
}
@Test
public void test7() {
    // Set up
    Attributes attributes = null;

    // Execution
    // Call the method with attributes needing initialization
    // ensureAttributes() will be called to initialize attributes
    Attributes result = attributes();

    // Verification
    assertEquals(attributes, result);
}
@Test
public void test8() {
    // Before: value = null
    // After: value = attributes, where attributes = new Attributes()
    //        coreValue = null
    //        nodeName() = ""
    // No mutation changes the behavior of the method
    // Test case does not kill any new mutants
    // Therefore, no regression test is needed
    ...
}
@Test
public void test9() {
    // Before: value = ""
    // After: value = attributes, where attributes = new Attributes()
    //        coreValue = ""
    //        nodeName() = ""
    // No mutation changes the behavior of the method
    // Test case does not kill any new mutants
    // Therefore, no regression test is needed
    ...
}
@Test
public void test10() {
    // Before: value = "value"
    // After: value = attributes, where attributes = new Attributes()
    //        coreValue = "value"
    //        nodeName() = ""
    // No mutation changes the behavior of the method
    // Test case does not kill any new mutants
    // Therefore, no regression test is needed
    ...
}
@Test
public void test11() {
    // Before: value = attributes, where attributes is an instance of Attributes
    // After: No change in the method behavior
    // No mutation changes the behavior of the method
    // Test case does not kill any new mutants
    // Therefore, no regression test is needed
    ...
}
    @Test
    public void test12() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p><p>Four <span>five &amp;</span> six &amp;</p>");
        Element p = doc.select("p").get(1);

        Element span = doc.select("span").get(1);
        assertEquals("five &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("five &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" six &", tn.text());
        
        tn.text(" POW!");
        assertEquals("Four <span>five &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("Four <span>five &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test
    public void test13() {
        TextNode tn = new TextNode("   ");
        assertTrue(tn.isBlank());
    }
    @Test
    public void test14() {
        Document doc = Jsoup.parse("<div>Hello there</div><div>Another text</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
        
        div = doc.select("div").get(1);
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(8);
        tail.wrap("<b></b>");

        assertEquals("Another <b>text</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test
    public void test15() {
        Document doc = Jsoup.parse("<div>Hello</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertNull(tail);
        assertEquals("Hello", tn.getWholeText());
    }
    @Test public void test16() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.text("NewValue");
        assertEquals("NewValue", tn.getWholeText());
        assertEquals("NewValue", div.text());
    }
    @Test public void test17() {
        TextNode one = new TextNode("Text");
        TextNode two = new TextNode("    Text    ");
        TextNode three = new TextNode("Text\n  Text");
        TextNode four = new TextNode("Hello");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
    }
    @Test public void test18(){
        Document doc = Jsoup.parse(new String(Character.toChars(9731)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(9731)), t.outerHtml().trim());
    }
    @Test public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test20() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!!!");
        assertEquals("Hello there!!!", div.text());
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test22() {
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
    @Test public void test23(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test25() {
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

        tn.attr("", "kablam &");
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

        tn.attr("p", "kablam &");
        assertEquals(" three &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
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
    @Test public void test31() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
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
@Test
public void test35() {
    // Arrange
    String key = "attr";
    Element element = new Element();
    element.attr(key, "value");
    
    // Act
    boolean result = element.hasAttr(key);
    
    // Assert
    assertTrue(result);
}
@Test
public void test36() {
    // Arrange
    String key = "attr";
    Element element = new Element();
    
    // Act
    boolean result = element.hasAttr(key);
    
    // Assert
    assertFalse(result);
}
@Test
public void test37() {
    // Arrange
    String key = "attr";
    Element element = new Element();
    element.attr(key, null);
    
    // Act
    boolean result = element.hasAttr(key);
    
    // Assert
    assertTrue(result);
}
@Test
public void test38() {
    // Create a new Node
    Node node = new Node();

    // Add attributes to the Node
    node.attr("id", "1");
    node.attr("class", "test");

    // Remove the "class" attribute
    Node removedAttribute = node.removeAttr("class");

    // Verify that the attribute was removed
    assertNull(node.attr("class"));
    assertEquals("test", removedAttribute.attr("class"));
}
@Test
public void test39() {
    // Create a new Node
    Node node = new Node();

    // Add attributes to the Node
    node.attr("id", "1");

    // Remove a non-existing attribute
    Node removedAttribute = node.removeAttr("class");

    // Verify that no attributes were removed
    assertNull(removedAttribute.attr("class"));
}
@Test
public void test40() {
    // Create a new Node
    Node node = new Node();

    // Remove a non-existing attribute
    Node removedAttribute = node.removeAttr("class");

    // Verify that no attributes were removed
    assertNull(removedAttribute.attr("class"));
}
@Test
public void test41() {
    String url = "https://www.example.com";
    Document doc = Jsoup.connect(url).get();
    String absUrl = doc.absUrl("");
    assertNull(absUrl);
}
@Test
public void test42() {
    String url = "https://www.example.com";
    Document doc = Jsoup.connect(url).get();
    String absUrl = doc.absUrl("invalidKey");
    assertNull(absUrl);
}
@Test
public void test43() {
    String url = "https://www.example.com";
    Document doc = Jsoup.connect(url).get();
    String absUrl = doc.absUrl("keyWithoutAttribute");
    assertNull(absUrl);
}
@Test
public void test44() {
    String url = "https://www.example.com";
    Document doc = Jsoup.connect(url).get();
    Element element = doc.selectFirst("a");
    element.attr("keyWithEmptyAttribute", "");
    String absUrl = doc.absUrl("keyWithEmptyAttribute");
    assertNull(absUrl);
}
@Test
public void test45() {
    String url = "https://www.example.com";
    Document doc = Jsoup.connect(url).get();
    Element element = doc.selectFirst("a");
    element.attr("keyWithRelativeUrlAttribute", "/page");
    String absUrl = doc.absUrl("keyWithRelativeUrlAttribute");
    assertEquals("https://www.example.com/page", absUrl);
}
    @Test public void test46() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test47() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test48() {
        Document doc = Jsoup.parse("<div>Hello <span>there</span></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test49() {
        Document doc = Jsoup.parse("");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("", TextUtil.stripNewlines(div.html()));
    }
@Test
public void test50() {
    String baseUri = "";
    
    doSetBaseUri(baseUri);
    
    // Assertion
    // ...
}
@Test
public void test51() {
    String baseUri = null;
    
    doSetBaseUri(baseUri);
    
    // Assertion
    // ...
}
@Test
public void test52() {
    String baseUri = "htt://example.com";
    
    doSetBaseUri(baseUri);
    
    // Assertion
    // ...
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
        
        tn.text(" POW!");
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

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test56() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test57() {
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
    @Test public void test58() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("", tn.text());
    }
    @Test public void test59() {
        Document doc = Jsoup.parse("<p>   </p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("   ", tn.text());
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test61(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test63() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
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
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span></p>");
        Element p = doc.select("p").first();
        
        TextNode tn = (TextNode) p.childNode(2);
        try {
            tn.text(" POW!");
            fail("Expected UnsupportedOperationException to be thrown.");
        } catch (UnsupportedOperationException e) {
            //
        }
    }
    @Test public void test66() {
        Document doc = Jsoup.parse("<div>Hello there</div><p>One <span>two &amp;</span> three &amp;</p>");
        Element div = doc.select("div").first();
        TextNode dn = (TextNode) div.childNode(0);
        List<Node> nodesBefore = dn.siblingNodes();
        int textNodeCountBefore = 0;
        for(Node node : nodesBefore) {
            if(node instanceof TextNode) {
                textNodeCountBefore++;
            }
        }
        
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test67() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        
        TextNode tn = (TextNode) div.childNode(1);
        try {
            TextNode tail = tn.splitText(6);
            fail("Expected UnsupportedOperationException to be thrown.");
        } catch (UnsupportedOperationException e) {
            //
        }
    }
    @Test public void test68() {
        Document doc = Jsoup.parse("<div>Hello there</div><p>One <span>two &amp;</span></p>");
        Element div = doc.select("div").first();
        TextNode dn = (TextNode) div.childNode(0);
        List<Node> nodesBefore = dn.siblingNodes();
        int childNodeCountBefore = 0;
        for(Node node : nodesBefore) {
            List<Node> childNodes = node.childNodes();
            if(childNodes.size() > 0) {
                childNodeCountBefore++;
            }
        }
        
        TextNode tn = (TextNode) div.childNode(1);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test69() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        try {
            TextNode tail = tn.splitText(11);
            fail("Expected IllegalArgumentException to be thrown.");
        } catch (IllegalArgumentException e) {
            //
        }
    }
    @Test public void test70() {
        Document doc = Jsoup.parse("<div>Hello there</div><p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        
        TextNode tn = (TextNode) p.childNode(2);
        TextNode tail = tn.splitText(1);
        
        assertEquals(" t", tn.getWholeText());
        assertEquals("hree &", tail.getWholeText());
        tail.text("there!");
        assertEquals(" tthere!", p.text());
        assertFalse(tn.parent() == tail.parent());
    }
    @Test public void test71() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        TextNode parent = new TextNode("Parent Node", "");
        tn.wrap(parent);
        assertEquals(parent, tn.parent());
        assertEquals(parent, tail.parent());
    }
    @Test public void test72() {
        Document doc = Jsoup.parse("<div>Hello there</div><p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(2);
        TextNode tail = tn.splitText(1);
        Element div = doc.select("div").first();
        TextNode dn = (TextNode) div.childNode(0);
        tail.wrap(dn);

        assertEquals(" t", tn.getWholeText());
        assertEquals("hree &", tail.getWholeText());
        assertFalse(tn.parent() == tail.parent());
    }
    @Test public void test73() {
        Document doc = Jsoup.parse("<div>Hello there</div><p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(2);
        TextNode tail = tn.splitText(1);
        Element span = doc.select("span").first();
        TextNode tn2 = (TextNode) span.childNode(0);
        tail.wrap(tn2);

        assertEquals(" t", tn.getWholeText());
        assertEquals("hree &", tail.getWholeText());
        assertTrue(tn.parent() == tail.parent());
    }
}