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
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());
    
    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());
    
    tn.text(" PRIME!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test2() {
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
    assertEquals("BOOM!", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test3() {
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
    assertEquals("One <span>two &amp;</span>kablam &amp;", "BOOM!");
}
@Test public void test4() {
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
    assertEquals("kablam &", "BOOM!");
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
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
    
    tn.text(" POW!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("ALRIGHT!", TextUtil.stripNewlines(p.html()));
}
@Test public void test6() {
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
    assertEquals("BOOM!", tn.text());
    assertEquals("ALRIGHT!", TextUtil.stripNewlines(p.html()));
}
@Test public void test7() {
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
    assertEquals("BOOM!", "ALRIGHT!");
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
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
    assertEquals("BOOM!", TextUtil.stripNewlines(p.html()));
}
@Test public void test9() {
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
    assertEquals("BOOM!", tn.text());
    assertEquals("BOOM!", TextUtil.stripNewlines(p.html()));
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
    
    tn.text(" POW!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", "BOOM!");
    assertEquals("kablam &", TextUtil.stripNewlines(p.html()));
}
@Test public void test11() {
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
@Test public void test12() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertFalse(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test public void test13() {
    TextNode one = new TextNode("");
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
@Test public void test14() {
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
@Test public void test15() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    assertTrue(one.isBlank());
    assertFalse(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
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
    assertTrue(five.isBlank());
}
@Test public void test17(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test18(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
}
@Test public void test19(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), "");
}
@Test public void test20(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("", t.outerHtml().trim());
}
@Test public void test21(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135362)), "");
}
@Test public void test22(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("", t.outerHtml().trim());
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
    tail.wrap("<b></b>");

    assertEquals("Hello there", TextUtil.stripNewlines(div.html())); 
}
@Test public void test25() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>ther</b>e", TextUtil.stripNewlines(div.html())); 
}
@Test public void test26() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello t<b>her</b>e", TextUtil.stripNewlines(div.html())); 
}
@Test public void test27() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello t<b>here</b>", TextUtil.stripNewlines(div.html())); 
}
@Test public void test28() {
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
@Test public void test29() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertFalse("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test30() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertFalse("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test31() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertFalse("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test32() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    assertFalse("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test33() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("BOOM!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test34() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertFalse("Hello there!", div.text());
    assertTrue(tn.parent() != tail.parent());
}
@Test public void test35() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertFalse("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello there!", div.text());
    assertTrue(tn.parent() != tail.parent());
}
@Test public void test36() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertFalse("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertFalse("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test37() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertFalse("there", tail.getWholeText());
    tail.text("there!");
    assertFalse("Hello there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test38() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertFalse("there", tail.getWholeText());
    tail.text("there!");
    assertFalse("Hello there!", div.text());
    assertTrue(tn.parent() != tail.parent());
}
    @Test
    public void test39() {
        // Create a mock object for the value
        Value valueMock = mock(Value.class);
        
        // Set the mock object as the value of the method
        instance.value = valueMock;
        
        // Call the method under test
        Attributes result = instance.attributes();
        
        // Verify that the ensureAttributes() method is called
        verify(instance).ensureAttributes();
        
        // Verify that the method returns the value casted to Attributes
        assertEquals((Attributes) valueMock, result);
    }
    @Test
    public void test40() {
        // Set the value of the method to null
        instance.value = null;
        
        // Call the method under test
        Attributes result = instance.attributes();
        
        // Verify that the ensureAttributes() method is called
        verify(instance).ensureAttributes();
        
        // Verify that the method returns null
        assertNull(result);
    }
    @Test
    public void test41() {
        // Create a mock object for the value
        Value valueMock = mock(Value.class);
        
        // Set the mock object as the value of the method
        instance.value = valueMock;
        
        // Call the method under test
        instance.ensureAttributes();
        
        // Verify that the method calls the ensureAttributes() method of the value object
        verify(valueMock).ensureAttributes();
    }
    @Test
    public void test42() {
        // Set the value of the method to null
        instance.value = null;
        
        // Call the method under test
        instance.ensureAttributes();
        
        // Verify that the method does not call the ensureAttributes() method of any object
        verifyZeroInteractions(valueMock);
    }
@Test
void testEnsureAttributesWithNullValue() {
    XMLNode xmlNode = new XMLNode(null); // Change input to null
    xmlNode.ensureAttributes();
    assertTrue(xmlNode.hasAttributes());
    assertNotNull(xmlNode.value);
    assertTrue(xmlNode.value instanceof Attributes);
    Attributes attributes = (Attributes) xmlNode.value;
    assertNull(attributes.get(xmlNode.nodeName()));
}
@Test
void testEnsureAttributesWithNonNullValue() {
    XMLNode xmlNode = new XMLNode("value"); // Change input to a non-null value
    xmlNode.ensureAttributes();
    assertTrue(xmlNode.hasAttributes());
    assertNotNull(xmlNode.value);
    assertTrue(xmlNode.value instanceof Attributes);
    Attributes attributes = (Attributes) xmlNode.value;
    assertEquals("value", attributes.get(xmlNode.nodeName()));
}
@Test
void testEnsureAttributesWithEmptyStringValue() {
    XMLNode xmlNode = new XMLNode(""); // Change input to an empty string
    xmlNode.ensureAttributes();
    assertTrue(xmlNode.hasAttributes());
    assertNotNull(xmlNode.value);
    assertTrue(xmlNode.value instanceof Attributes);
    Attributes attributes = (Attributes) xmlNode.value;
    assertEquals("", attributes.get(xmlNode.nodeName()));
}
@Test
void testEnsureAttributesWithNonEmptyStringValue() {
    XMLNode xmlNode = new XMLNode("value"); // Change input to a non-empty string
    xmlNode.ensureAttributes();
    assertTrue(xmlNode.hasAttributes());
    assertNotNull(xmlNode.value);
    assertTrue(xmlNode.value instanceof Attributes);
    Attributes attributes = (Attributes) xmlNode.value;
    assertEquals("value", attributes.get(xmlNode.nodeName()));
}
@Test
void testEnsureAttributesWithNonStringValue() {
    XMLNode xmlNode = new XMLNode(123); // Change input to a non-string value
    xmlNode.ensureAttributes();
    assertTrue(xmlNode.hasAttributes());
    assertNotNull(xmlNode.value);
    assertTrue(xmlNode.value instanceof Attributes);
    Attributes attributes = (Attributes) xmlNode.value;
    assertNotEquals(123, attributes.get(xmlNode.nodeName()));
    assertNull(attributes.get(xmlNode.nodeName()));
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test44() {
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
    @Test public void test45(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test46() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test47() {
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
    @Test public void test48() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("", tn.text());
    }
    @Test public void test49() {
        Document doc = Jsoup.parse("<p>This is a test: &lt; &gt; &amp;</p>");
        Element p = doc.select("p").first();
        
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("This is a test: < > &", tn.text());
    }
    @Test public void test50() {
        Document doc = Jsoup.parse("<p>  This is a test   </p>");
        Element p = doc.select("p").first();
        
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("  This is a test   ", tn.text());
    }
    @Test public void test51() {
        Document doc = Jsoup.parse("<p>12345</p>");
        Element p = doc.select("p").first();
        
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("12345", tn.text());
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<p> This is a test: &lt; &gt; &amp; </p>");
        Element p = doc.select("p").first();
        
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals(" This is a test: < > & ", tn.text());
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

    // Regression test 1: Changing the value of tn.text() to an empty string
    tn.text("");

    assertEquals("", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
@Test public void test54() {
    TextNode one = new TextNode("");
    TextNode two = new TextNode("     ");
    TextNode three = new TextNode("  \n\n   ");
    TextNode four = new TextNode("Hello");
    TextNode five = new TextNode("  \nHello ");

    // Regression test 2: Changing the value of one to a non-empty string
    one.text("Hello");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test public void test55(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);

    // Regression test 3: Changing the value of t to a different supplementary character
    t.text(new String(Character.toChars(135362)));

    assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
}
@Test public void test56() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

    // Regression test 4: Changing the value of tn.splitText(6) to a different value
    TextNode tailNew = tn.splitText(5);
    tailNew.wrap("<strong></strong>");

    assertEquals("Hell<strong>o there</strong>", TextUtil.stripNewlines(div.html()));
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

    // Regression test 5: Changing the value of tn.splitText(6) to a different value
    TextNode tailNew = tn.splitText(4);
    tailNew.text("lo");

    assertEquals("Helllo there!", div.text());
}
    @Test 
    public void test58() {
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

        // regression test to kill mutants
        // when key is null
        String nullKey = null;
        assertThrows(NullPointerException.class, () -> tn.attr(nullKey));
        
        // when key is empty
        String emptyKey = "";
        assertEquals("", tn.attr(emptyKey));
        
        // when key is same as node name
        assertEquals("three &", tn.attr(tn.nodeName()));
        
        // when key is different from node name and element does not have attributes
        assertEquals("four", tn.attr("four"));
        
        // when key is different from node name and element has attributes
        tn.attr("five", "six");
        assertEquals("six", tn.attr("five"));
    }
    @Test 
    public void test59() {
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
        
        // regression test to kill mutants
        // when text node is empty
        assertTrue(one.isBlank());
        
        // when text node contains only whitespace characters
        assertTrue(two.isBlank());
        
        // when text node contains newlines, spaces, and tabs
        assertTrue(three.isBlank());
        
        // when text node contains non-blank characters
        assertFalse(four.isBlank());
        
        // when text node contains leading whitespace characters
        assertFalse(five.isBlank());
    }
    @Test 
    public void test60(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        // regression test to kill mutants
        // when text node contains a supplementary character
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
        
        // regression test to kill mutants
        // when split position is at beginning
        TextNode splitAtBeginning = (TextNode) div.childNode(0);
        TextNode tailAtBeginning = splitAtBeginning.splitText(0);
        tailAtBeginning.wrap("<b></b>");
        assertEquals("<b>Hello</b> there", TextUtil.stripNewlines(div.html()));
        
        // when split position is at end
        TextNode splitAtEnd = (TextNode) div.childNode(0);
        TextNode tailAtEnd = splitAtEnd.splitText(5);
        tailAtEnd.wrap("<b></b>");
        assertEquals("Hello <b> there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test 
    public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
        
        // regression test to kill mutants
        // when split position is at beginning
        TextNode splitAtBeginning = (TextNode) div.childNode(0);
        TextNode tailAtBeginning = splitAtBeginning.splitText(0);
        assertEquals("", splitAtBeginning.getWholeText());
        assertEquals("Hello there!", div.text());
        
        // when split position is at end
        TextNode splitAtEnd = (TextNode) div.childNode(0);
        TextNode tailAtEnd = splitAtEnd.splitText(11);
        assertEquals("Hello there", splitAtEnd.getWholeText());
        assertEquals("", tailAtEnd.getWholeText());
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
    @Test public void test64() {
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
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test
    public void test68() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("One ", tn.getWholeText());
        tn.attr("", "new attribute");
        assertEquals("One ", tn.getWholeText());
    }
    @Test
    public void test69() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("One ", tn.getWholeText());
        tn.attr("differentKey", "new attribute");
        assertEquals("One ", tn.getWholeText());
    }
    @Test
    public void test70() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("One ", tn.getWholeText());
        tn.attr("span", "new attribute");
        assertEquals("One ", tn.getWholeText());
    }
    @Test 
    public void test71() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("One ", tn.getWholeText());
        tn.attr("p", "new attribute");
        assertEquals("new attribute", tn.getWholeText());
    }
    @Test 
    public void test72() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("One ", tn.getWholeText());
        tn.attr("differentKey", "new attribute");
        assertEquals("One ", tn.getWholeText());
    }
@Test
public void test73() {
    // Original test case
    String key = "name";
    boolean expected = true;

    // New test cases
    String key1 = "id";
    String key2 = "age";
    String key3 = "address";
    String key4 = "email";

    // Ensure attributes before calling hasAttr
    // Ensure key is present in attributes map
    // Verify that hasAttr returns the expected result
    assertTrue(hasAttr(key));
    assertTrue(hasAttr(key1));
    assertTrue(hasAttr(key2));
    assertTrue(hasAttr(key3));
    assertTrue(hasAttr(key4));
}
@Test
public void test74() {
    // Original test case
    String key = "weight";
    boolean expected = false;

    // New test cases
    String key1 = "grade";
    String key2 = "height";
    String key3 = "city";
    String key4 = "phone";

    // Ensure attributes before calling hasAttr
    // Ensure key is not present in attributes map
    // Verify that hasAttr returns the expected result
    assertFalse(hasAttr(key));
    assertFalse(hasAttr(key1));
    assertFalse(hasAttr(key2));
    assertFalse(hasAttr(key3));
    assertFalse(hasAttr(key4));
}
    @Test
    public void test75() {
        // Create a node with an existing attribute key
        Node node = new Node("div");
        node.attr("class", "container");

        // Remove the attribute using the removeAttr method
        node.removeAttr("class");

        // Check if the attribute was successfully removed
        assertNull(node.attr("class"));
    }
    @Test
    public void test76() {
        // Create a node without any attributes
        Node node = new Node("div");

        // Remove a non-existing attribute using the removeAttr method
        node.removeAttr("class");

        // Check if no attribute was added
        assertNull(node.attr("class"));
    }
    @Test
    public void test77() {
        // Create a node with multiple attributes
        Node node = new Node("div");
        node.attr("class", "container");
        node.attr("id", "main");

        // Remove one of the attributes using the removeAttr method
        node.removeAttr("class");

        // Check if the specified attribute was successfully removed
        assertNull(node.attr("class"));

        // Check if the other attribute still exists
        assertEquals("main", node.attr("id"));
    }
    @Test
    public void test78() {
        // Create a node with a non-empty attribute key
        Node node = new Node("div");
        node.attr("", "container");

        // Remove the attribute using the removeAttr method
        node.removeAttr("");

        // Check if the attribute was successfully removed
        assertNull(node.attr(""));
    }
    @Test
    public void test79() {
        // Create a node with a non-empty attribute key
        Node node = new Node("div");
        node.attr("  ", "container");

        // Remove the attribute using the removeAttr method
        node.removeAttr("  ");

        // Check if the attribute was successfully removed
        assertNull(node.attr("  "));
    }
@Test
public void test80() {
    // Change input to null key
    String result = absUrl(null);

    // Check the result
    assertNull(result);
}
@Test
public void test81() {
    // Change input to empty key
    String result = absUrl("");

    // Check the result
    assertNull(result);
}
@Test
public void test82() {
    // Change input to a key that does not exist
    String result = absUrl("nonexistent");

    // Check the result
    assertNull(result);
}
@Test
public void test83() {
    // Change input to a valid key
    String result = absUrl("validKey");

    // Check the result
    assertNotNull(result);
    assertEquals("http://www.example.com", result);
}
    @Test public void test84() {
        // Original test case
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

        // Test case with empty string as input
        doc = Jsoup.parse("<div></div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b></b>", TextUtil.stripNewlines(div.html()));
    }
@Test
void testDoSetBaseUriNull() {
    // Test with null baseUri
    doSetBaseUri(null);
}
@Test
void testDoSetBaseUriEmptyString() {
    // Test with empty baseUri
    doSetBaseUri("");
}
@Test
void testDoSetBaseUriValidUri() {
    // Test with valid baseUri
    doSetBaseUri("http://example.com");
}
@Test
void testDoSetBaseUriInvalidUri() {
    // Test with invalid baseUri
    doSetBaseUri("example.com");
}
@Test 
public void test85() {
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
public void test86() {
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
public void test87() {
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
public void test88() {
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
public void test89() {
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test 
public void test90() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
}
@Test 
public void test91() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(0, nodes.size());
}
@Test 
public void test92() {
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
    @Test public void test93() {
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
    @Test(expected = UnsupportedOperationException.class) 
    public void test94() {
        TextNode tn = new TextNode("Leaf Node", "");
        tn.ensureChildNodes();
    }
    @Test(expected = Exception.class) 
    public void test95() {
        TextNode tn = Mockito.mock(TextNode.class);
        List<Node> childNodes = new ArrayList<>();
        childNodes.add(new CommentNode("Child Comment Node"));
        Mockito.when(tn.childNodes()).thenReturn(childNodes);
        
        tn.ensureChildNodes();
    }
    @Test public void test96(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test97() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test98() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test99() {
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
    @Test public void test100() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("Hello there", tail.getWholeText());
    }
    @Test public void test101() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(tn.text().length());
        assertEquals("Hello there", tn.getWholeText());
        assertEquals("", tail.getWholeText());
    }
    @Test public void test102() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(tn.text().length() + 5);
        assertEquals("Hello there", tn.getWholeText());
        assertEquals("", tail.getWholeText());
    }
}