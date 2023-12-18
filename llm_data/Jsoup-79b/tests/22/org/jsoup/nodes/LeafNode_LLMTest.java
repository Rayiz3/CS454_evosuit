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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test2() {
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
    @Test public void test3() {
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
    @Test public void test4(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test5(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test6() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test7() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test8() {
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
    @Test public void test9() {
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
public void test10() {
    // Create a non-null value for 'value'
    Attributes attributes = mock(Attributes.class);
    when(value).thenReturn(attributes);

    // Call the method
    Attributes result = attributes();

    // Verify that the 'ensureAttributes' method is called
    verify(this).ensureAttributes();

    // Verify the result
    assertEquals(attributes, result);
}
@Test
public void test11() {
    // Create a null value for 'value'
    when(value).thenReturn(null);

    // Call the method
    Attributes result = attributes();

    // Verify that the 'ensureAttributes' method is called
    verify(this).ensureAttributes();

    // Verify the result
    assertNull(result);
}
    @Test
    public void test12() {
        // Arrange
        MyClass myClass = mock(MyClass.class);
        when(myClass.nodeName()).thenReturn("nodeName");
        
        // Act
        myClass.ensureAttributes();
        
        // Assert
        assertNull(myClass.getValue());
    }
    @Test
    public void test13() {
        // Arrange
        MyClass myClass = mock(MyClass.class);
        when(myClass.nodeName()).thenReturn("nodeName");
        myClass.setValue("value");
        
        // Act
        myClass.ensureAttributes();
        
        // Assert
        assertNotNull(myClass.getValue());
        assertTrue(myClass.getValue() instanceof Attributes);
        assertEquals("value", ((Attributes) myClass.getValue()).get("nodeName"));
    }
    @Test
    public void test14() {
        // Arrange
        MyClass myClass = mock(MyClass.class);
        when(myClass.hasAttributes()).thenReturn(true);
        myClass.setValue("value");
        
        // Act
        myClass.ensureAttributes();
        
        // Assert
        assertNotNull(myClass.getValue());
        assertEquals("value", (String) myClass.getValue());
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
        
        tn.text("");
        assertEquals("", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test16() {
        TextNode one = new TextNode("Hello");
        TextNode two = new TextNode("Hello");
        TextNode three = new TextNode("Hello");
        TextNode four = new TextNode("");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
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

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test19() {
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
    @Test public void test20() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" New Value");
        assertEquals("One <span>two &amp;</span> New Value", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "new attribute");
        assertEquals("new attribute", tn.text());
        assertEquals("One <span>two &amp;</span>new attribute;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test21() {
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
        tail.text("new value");
        assertEquals("Hello new value", div.text());
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

        tn.text(null); //Regression test case. Passing null as input to the method.
        assertEquals("", TextUtil.stripNewlines(p.html())); //Expected: empty string

        tn.text("&amp;"); //Regression test case. Passing "&amp;" as input to the method.
        assertEquals("One <span>two &amp;</span>&amp;", TextUtil.stripNewlines(p.html())); //Expected: "One <span>two &amp;</span>&amp;"

        tn.text("&<>"); //Regression test case. Passing "&<>" as input to the method.
        assertEquals("One <span>two &amp;</span>&<>", TextUtil.stripNewlines(p.html())); //Expected: "One <span>two &amp;</span>&<>"
    }
    @Test public void test26() {
        TextNode one = new TextNode(""); //Regression test case. Passing empty string as input to the constructor.
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
        Document doc = Jsoup.parse(new String(Character.toChars(135361))); //Regression test case. Passing a supplementary character as input to the method.
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

        tn.text(""); //Regression test case. Passing empty string as input to the method.
        assertEquals("<b>there</b>", TextUtil.stripNewlines(div.html())); //Expected: "<b>there</b>

        tn.text("Hello"); //Regression test case. Passing "Hello" as input to the method.
        assertEquals("Hello<b>there</b>", TextUtil.stripNewlines(div.html())); //Expected: "Hello<b>there</b>"
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

        tn.text(null); //Regression test case. Passing null as input to the method.
        assertEquals("", div.text()); //Expected: ""

        tn.text("hello there"); //Regression test case. Passing "hello there" as input to the method.
        assertEquals("hello there there!", div.text()); //Expected: "hello there there!"
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
@Test
public void test35() {
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

    tn.attr(tn.nodeName(), tn.nodeName());
    assertEquals(tn.nodeName(), tn.text());
    assertEquals("One <span>two &amp;</span><three &amp;></three &amp;>", TextUtil.stripNewlines(p.html()));
}
@Test
public void test36() {
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

    tn.attr("key", tn.nodeName());
    assertEquals(tn.nodeName(), tn.text());
    assertEquals("One <span>two &amp;</span><key>three &amp;</key>", TextUtil.stripNewlines(p.html()));
}
@Test public void test37() {
    TextNode one = new TextNode("     ");
    TextNode two = new TextNode("  \n\n   ");
    TextNode three = new TextNode("           ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
}
@Test public void test38() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(2);
    assertEquals("He", tn.getWholeText());
    assertEquals("llo there", tail.getWholeText());
    tail.text("llo");
    assertEquals("Hello there", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test
public void test39() {
    // Create a mock Node object
    Node node = new Node();
    
    // Set an attribute
    node.attr("attribute", "value");
    
    // Remove the attribute
    node.removeAttr("attribute");
    
    // Try to get the attribute again
    assertNull(node.attr("attribute"));
}
@Test
public void test40() {
    // Create a mock Node object
    Node node = new Node();
    
    // Try to remove a non-existing attribute
    node.removeAttr("non_existing_attribute");
    
    // No exception should be thrown
    // The method should return null or an empty string
    assertNull(node.attr("non_existing_attribute"));
}
@Test
public void test41() {
    String absUrl = absUrl(null);
    assertNull(absUrl);
}
@Test
public void test42() {
    String absUrl = absUrl("");
    assertEquals("", absUrl);
}
@Test
public void test43() {
    String absUrl = absUrl("invalidKey");
    assertNull(absUrl);
}
@Test
public void test44() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    tn.setBaseUri("http://www.example.com/");
    TextNode tail = tn.splitText(6);

    assertEquals("http://www.example.com/", tail.baseUri());
}
@Test
public void test45() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);

    assertEquals("", tn.baseUri());
}
@Test
public void test46() {
    String baseUri = "";
    doSetBaseUri(baseUri);
    // Assertion or verification code
}
@Test
public void test47() {
    String baseUri = null;
    doSetBaseUri(baseUri);
    // Assertion or verification code
}
@Test
public void test48() {
    String baseUri = "://example.com";
    doSetBaseUri(baseUri);
    // Assertion or verification code
}
@Test public void test49() {
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
@Test public void test50(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
@Test public void test51() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b>Hello there</b>", TextUtil.stripNewlines(div.html()));
    }
@Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
@Test public void test53() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("Hello there", tail.getWholeText());
        tail.text("there!");
        assertEquals("there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

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
    @Test
    public void test59() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").last();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("One ", tn.text());
        
        tn.text("Hello!");
        assertEquals("Hello! <span>two &amp;</span> three &amp;", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "world &");
        assertEquals("world &", tn.text());
        assertEquals("world &<span>two &amp;</span> three &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test 
    public void test60(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test 
    public void test61() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello<b> there</b>", TextUtil.stripNewlines(div.html())); 
    }
    @Test 
    public void test62() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(1, nodes.size());
    }
    @Test 
    public void test63() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(4);
        assertEquals("Hell", tn.getWholeText());
        assertEquals("o there", tail.getWholeText());
        tail.text("move!");
        assertEquals("Hellmove! there", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
}