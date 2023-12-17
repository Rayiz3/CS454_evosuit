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
        TextNode empty = new TextNode("");
        assertFalse(empty.hasAttributes());
    }
    @Test public void test6() {
        Attributes attr = new Attributes();
        attr.put("attr1", "value1");
        attr.put("attr2", "value2");
        
        TextNode tn = new TextNode("text", attr);
        assertTrue(tn.hasAttributes());
    }
    @Test public void test7() {
        TextNode tn = new TextNode("text", null);
        assertFalse(tn.hasAttributes());
    }
    @Test public void test8() {
        TextNode spaces = new TextNode("     ");
        assertFalse(spaces.hasAttributes());
    }
    @Test public void test9() {
        TextNode supplementary = new TextNode(new String(Character.toChars(135361)));
        assertFalse(supplementary.hasAttributes());
    }
    @Test public void test10() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        
        // Split at index 3
        TextNode tail1 = tn.splitText(3);
        assertEquals("Hel", tn.getWholeText());
        assertEquals("lo there", tail1.getWholeText());
        
        tail1.text("New Text");
        assertEquals("HelNew Text", div.text());
        
        // Split at index 5
        TextNode tail2 = tn.splitText(5);
        assertEquals("HelN", tn.getWholeText());
        assertEquals("ew Text", tail2.getWholeText());
        
        tail2.text("Another Text");
        assertEquals("HelNAnother Text", div.text());
    }
@Test
public void test11() {
    // Original test case
    Foo foo = new Foo();
    foo.attributes(); // Covering the method under test
    
    // Updated test cases 
    // Test case 1: Pass a null value
    Foo foo1 = new Foo();
    foo1.setValue(null);
    foo1.attributes();
    
    // Test case 2: Pass a different type of object
    Foo foo2 = new Foo();
    foo2.setValue("string");
    foo2.attributes();
    
    // Test case 3: Pass a different instance of Attributes
    Foo foo3 = new Foo();
    foo3.setValue(new Attributes());
    foo3.attributes();
    
    // Test case 4: Pass a null value for a different attribute
    Foo foo4 = new Foo();
    foo4.setAnotherAttribute(null);
    foo4.attributes();
}
@Test
public void test12() {
    // Arrange
    Object value = null;
    
    // Act
    YourClass yourObject = new YourClass(value);
    yourObject.ensureAttributes();
    
    // Assert
    assertNotNull(yourObject.getValue());
    assertTrue(yourObject.getAttributes().containsKey(yourObject.getNodeName()));
    assertEquals(value, yourObject.getAttributes().get(yourObject.getNodeName()));
}
@Test
public void test13() {
    // Arrange
    Object value = "value";
    
    // Act
    YourClass yourObject = new YourClass(value);
    yourObject.ensureAttributes();
    
    // Assert
    assertNotNull(yourObject.getValue());
    assertFalse(yourObject.getAttributes().containsKey(yourObject.getNodeName()));
}
@Test
public void test14() {
    // Arrange
    Object value = "value";
    YourClass yourObject = new YourClass(value);
    yourObject.getAttributes().put(yourObject.getNodeName(), "oldValue");
    
    // Act
    yourObject.ensureAttributes();
    
    // Assert
    assertNotNull(yourObject.getValue());
    assertTrue(yourObject.getAttributes().containsKey(yourObject.getNodeName()));
    assertEquals(value, yourObject.getAttributes().get(yourObject.getNodeName()));
}
@Test
public void test15() {
    // Arrange
    Object value = "value";
    YourClass yourObject = new YourClass(value);
    yourObject.getAttributes().put(yourObject.getNodeName(), value);
    
    // Act
    yourObject.ensureAttributes();
    
    // Assert
    assertNotNull(yourObject.getValue());
    assertTrue(yourObject.getAttributes().containsKey(yourObject.getNodeName()));
    assertEquals(value, yourObject.getAttributes().get(yourObject.getNodeName()));
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
        
        tn.text(" NEW_TEXT");
        assertEquals("One <span>two &amp;</span> NEW_TEXT", TextUtil.stripNewlines(p.html()));

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
        
        TextNode six = new TextNode("    ");
        assertFalse(six.isBlank());
        
        TextNode seven = new TextNode(" \n ");
        assertFalse(seven.isBlank());
    }
    @Test public void test23(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        Document doc1 = Jsoup.parse(new String(Character.toChars(135358)));
        TextNode t1 = doc1.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135358)), t1.outerHtml().trim());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        TextNode tn1 = (TextNode) div.childNode(0);
        TextNode tail1 = tn1.splitText(2);
        tail1.wrap("<b></b>");

        assertEquals("He<b>ll</b>o <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
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
        
        TextNode tn1 = (TextNode) div.childNode(0);
        TextNode tail1 = tn1.splitText(2);
        assertEquals("He", tn1.getWholeText());
        assertEquals("llo ", tail1.getWholeText());
        tail.text("llo!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn1.parent() == tail1.parent());
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
        TextNode one = new TextNode("");
        // Change the input value to a non-blank value
        TextNode two = new TextNode("newText");
        // Change the input value to a blank value with additional spaces
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertFalse(two.isBlank()); // The input value has changed, the result is different
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test28(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test29() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test30() {
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
@Test public void test31() {
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
@Test public void test32() {
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
    
    tn.text("three");
    assertEquals("One <span>two &amp;</span> three", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test33() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());
    
    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());
    
    tn.text("Pow pow");
    assertEquals("One <span>two &amp;</span> Pow pow", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test34() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());
    
    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());
    
    tn.text("hello xyz");
    assertEquals("One <span>two &amp;</span> hello xyz", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
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
    
    tn.text("    ");
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
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
    
    tn.text("abc123 & $%^&*");
    assertEquals("One <span>two &amp;</span> abc123 & $%^&amp;", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test37() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());
    
    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());
    
    tn.text("!@#$%^&*()_+");
    assertEquals("One <span>two &amp;</span> !@#$%^&amp;*()_+", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
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
    @Test public void test40(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test41() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test42() {
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
    @Test public void test43() {
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
    @Test public void test44() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("");
        assertEquals("Hello", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
@Test
public void test45() {
    boolean result = myObject.hasAttr(null);
    assertFalse(result);
}
@Test
public void test46() {
    boolean result = myObject.hasAttr("");
    assertFalse(result);
}
@Test
public void test47() {
    boolean result = myObject.hasAttr("nonExisting");
    assertFalse(result);
}
@Test
public void test48() {
    // Set up test data with an existing attribute
    myObject.setAttr("existing", "value");
    
    boolean result = myObject.hasAttr("existing");
    assertTrue(result);
}
@Test
public void test49() {
    // Test removing an existing attribute
    Element element = new Element("div");
    element.attr("id", "myDiv");
    element.attr("class", "myClass");
    
    // Change the input: remove an existing attribute
    element.removeAttr("class");
    
    assertNull(element.attr("class"));
    assertEquals(1, element.attributes().size());
    assertEquals("myDiv", element.attr("id"));
    
    // Test removing a non-existing attribute
    Element element1 = new Element("p");
    element1.attr("align", "left");
    
    // Change the input: remove a non-existing attribute
    element1.removeAttr("class");
    
    assertEquals(1, element1.attributes().size());
    assertEquals("left", element1.attr("align"));
}
@Test
public void test50() {
    // Test removing an attribute when attributes map is empty
    Element element2 = new Element("span");
    
    // Change the input: remove an attribute when attributes map is empty
    element2.removeAttr("class");
    
    assertEquals(0, element2.attributes().size());
    assertNull(element2.attr("class"));
    
    // Test removing a non-existing attribute when attributes map is empty
    Element element3 = new Element("h1");
    
    // Change the input: remove a non-existing attribute when attributes map is empty
    element3.removeAttr("id");
    
    assertEquals(0, element3.attributes().size());
    assertNull(element3.attr("id"));
}
    @Test
    public void test51() {
        String absUrl = absUrl(null);
        assertNull(absUrl);
    }
    @Test
    public void test52() {
        String absUrl = absUrl("");
        assertNull(absUrl);
    }
    @Test
    public void test53() {
        String absUrl = absUrl("href");
        assertNotNull(absUrl);
        // Add additional assertions if necessary to validate the behavior of the method with a valid key
    }
@Test
public void test54() {
    Document doc = Jsoup.parse("<div>Hello there</div>", "https://example.com/");
    Element div = doc.select("div").first();

    assertEquals("https://example.com/", div.baseUri());
}
@Test
public void test55() {
    Document doc = Jsoup.parse("<div>Hello there</div>");

    assertEquals("", doc.baseUri());
}
    @Test
    public void test56() {
        doSetBaseUri(null);
        // assert some behavior
    }
    @Test
    public void test57() {
        doSetBaseUri("");
        // assert some behavior
    }
    @Test
    public void test58() {
        doSetBaseUri("https://www.example.com");
        // assert some behavior
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
        
        // Regression test case where all text nodes are empty
        tn.text("");
        assertEquals("", TextUtils.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test60(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());

        // Regression test case where supplementary character is replaced by a normal character
        assertEquals("abcd", t.text());
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

        // Regression test case where splitText is called with an index that is greater than the length of the text node
        tail = tn.splitText(100);
        tail.wrap("<b></b>");
        assertEquals("Hello there<b></b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());

        // Regression test case where childNodeSize() is called after inserting a new child node
        tn.splitText(6);
        nodes = tn.childNodes();
        assertEquals(2, nodes.size());
    }
    @Test public void test63() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());

        // Regression test case where splitText is called with an index that is less than 0
        tail = tn.splitText(-2);
        tail.wrap("<b></b>");
        assertEquals("<b></b>Hello there!", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test64() {
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
    @Test public void test65(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test66() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test67() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test68() {
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
    @Test public void test69() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = new TextNode(div.ownText(), div.baseUri());
        div.appendChild(tn);
        List<Node> childNodes = tn.childNodes();
        assertEquals(0, childNodes.size());
    }
    @Test public void test70() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> childNodes = tn.childNodes();
        assertEquals(0, childNodes.size());
    }
    @Test(expected = UnsupportedOperationException.class)
    public void test71() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = new TextNode(div.ownText(), div.baseUri());
        div.appendChild(tn);
        tn.childNodes();
    }
    @Test(expected = UnsupportedOperationException.class)
    public void test72() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.childNodes();
    }
    @Test(expected = UnsupportedOperationException.class)
    public void test73() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");
        tn.childNodes();
    }
}