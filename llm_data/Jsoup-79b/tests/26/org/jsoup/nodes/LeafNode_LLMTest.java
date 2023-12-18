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
        TextNode six = new TextNode("&nbsp;");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
        assertFalse(six.isBlank());
    }
    @Test public void test2(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        Document doc2 = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t2 = doc2.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t2.outerHtml().trim());
    }
    @Test public void test3() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        TextNode tn2 = (TextNode) div.childNode(0);
        TextNode tail2 = tn2.splitText(5);
        tail2.wrap("<i></i>");
        
        assertEquals("Hello <i> there</i>", TextUtil.stripNewlines(div.html()));
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
        
        TextNode tn2 = (TextNode) div.childNode(0);
        TextNode tail2 = tn2.splitText(5);
        assertEquals("Hello", tn2.getWholeText());
        assertEquals(" there!", tail2.getWholeText());
        tail2.text("Nice one!");
        assertEquals("Hello Nice one!", div.text());
        assertTrue(tn2.parent() == tail2.parent());
    }
@Test
public void test5() {
    // Test with null value
    MyClass myObject = new MyClass();
    myObject.attributes(null);
    assertThrows(NullPointerException.class, () -> myObject.attributes());

    // Test with empty attributes
    myObject.attributes(new Attributes());
    Attributes result = myObject.attributes();
    assertEquals(new Attributes(), result);
    
    // Test with non-null attributes
    Attributes attributes = new Attributes();
    attributes.put("Name", "John");
    attributes.put("Age", "30");
    myObject.attributes(attributes);
    result = myObject.attributes();
    assertEquals(attributes, result);
}
@Test
public void test6() {
    Object value = null;
    // Call the method with null value
    // The method should create a new Attributes object and update the value field with it
    // The attributes object should be empty because the coreValue is null
    // Check that value is not null and has type Attributes
    // Check that the attributes object is empty
    // Check that the attributes object has the correct nodeName and coreValue
    // Add additional assertions here to kill more mutants

}
@Test
public void test7() {
    Object value = "";
    // Call the method with empty value
    // The method should create a new Attributes object and update the value field with it
    // The attributes object should be empty because the coreValue is empty
    // Check that value is not null and has type Attributes
    // Check that the attributes object is empty
    // Check that the attributes object has the correct nodeName and coreValue
    // Add additional assertions here to kill more mutants

}
@Test
public void test8() {
    Object value = "Test value";
    // Call the method with non-null and non-empty value
    // The method should create a new Attributes object and update the value field with it
    // The attributes object should contain the coreValue as a key-value pair with the nodeName as the key
    // Check that value is not null and has type Attributes
    // Check that the attributes object contains the correct key-value pair
    // Add additional assertions here to kill more mutants

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
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test10() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test11() {
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
    @Test public void test12() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test13() {
        TextNode one = new TextNode("");
        assertTrue(one.isBlank());
    }
    @Test public void test14() {
        TextNode two = new TextNode("     ");
        assertTrue(two.isBlank());
    }
    @Test public void test15() {
        TextNode three = new TextNode("  \n\n   ");
        assertTrue(three.isBlank());
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
        TextNode one = new TextNode("non-blank");
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
    @Test public void test23() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        t.attr("node_name", "kablam &");
        assertEquals("kablam &", t.text());
        assertEquals("kablam &", t.attr("node_name"));
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3);
        tail.wrap("<b></b>");

        assertEquals("Hel<b>lo there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(9);
        assertEquals("Hello the", tn.getWholeText());
        assertEquals("re", tail.getWholeText());
        tail.text("re!");
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
        
        tn.text("A NEW TEXT");
        assertEquals("One <span>two &amp;</span> A NEW TEXT", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "A NEW ATTRIBUTE");
        assertEquals("A NEW ATTRIBUTE", tn.text());
        assertEquals("One <span>two &amp;</span>A NEW ATTRIBUTE&", TextUtil.stripNewlines(p.html()));
    }
@Test public void test27() {
        TextNode one = new TextNode("A");
        TextNode two = new TextNode("B");
        TextNode three = new TextNode("C");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
}
@Test public void test28(){
        Document doc = Jsoup.parse(new String(Character.toChars(199)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(199)), t.outerHtml().trim());
}
@Test public void test29() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b> YAY", TextUtil.stripNewlines(div.html())); 
}
@Test public void test30() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(2);
        assertEquals("He", tn.getWholeText());
        assertEquals("llo there", tail.getWholeText());
        tail.text("THERE!");
        assertEquals("HeTHERE!", div.text());
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
        
        tn.text(" Hello &");
        assertEquals("One <span>two &amp;</span> Hello &", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test33() {
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
    @Test public void test34() {
        TextNode one = new TextNode("   ");
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
    @Test public void test35(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test36(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test37() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test38() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test39() {
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
    @Test public void test40() {
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
public void test41() {
    // Original test case
    String key = "attrKey";
    assertTrue(hasAttr(key));
}
@Test
public void test42() {
    // Original test case
    String key = "nonExistingAttr";
    assertFalse(hasAttr(key));
}
@Test
public void test43() {
    // Regression test case 1
    String key = null;
    assertFalse(hasAttr(key));
}
@Test
public void test44() {
    // Regression test case 2
    String key = "";
    assertFalse(hasAttr(key));
}
@Test
public void test45() {
    // Regression test case 3
    String key = "   ";
    assertFalse(hasAttr(key));
}
@Test
public void test46() {
   Node node = new Node("div");
   node.attr("id", "testId");
   
   Node result = node.removeAttr("id");
   
   assertNull(result.attr("id"));
}
@Test
public void test47() {
   Node node = new Node("div");
   
   Node result = node.removeAttr("id");
   
   assertNull(result.attr("id"));
}
@Test
public void test48() {
   Node node = new Node("div");
   node.attr("id", "testId");
   node.attr("class", "testClass");
   
   Node result = node.removeAttr("id");
   
   assertNull(result.attr("id"));
   assertNotNull(result.attr("class"));
}
    @Test
    public void test49() {
        // Original test case
        // return a block of contents with an absolute URL
        
        // Changed input: key = "link"
        // Modified test case to return a block of contents with a relative URL
    }
    @Test
    public void test50() {
        // Original test case
        // return a block of contents with an absolute URL
      
        // Changed input: key = ""
        // Modified test case to return a block of contents with an empty key
    }
    @Test
    public void test51() {
        // Original test case
        // return a block of contents with an absolute URL
        
        // Changed input: key = "invalid"
        // Modified test case to return a block of contents with an invalid key
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div>Hello there</div>", "https://example.com");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.splitText(6);
        TextNode tn2 = (TextNode) div.childNode(1);
        tn2.wrap("<b></b>");

        assertEquals("https://example.com", div.baseUri());
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();

        assertEquals("", div.baseUri());
    }
    @Test
    public void test55() {
        // Arrange
        String baseUri = null;
        
        // Act
        doSetBaseUri(baseUri);
        
        // Assert
        // Verify the behavior of the method with null base URI
        // For example: assert something
    }
    @Test
    public void test56() {
        // Arrange
        String baseUri = "";
        
        // Act
        doSetBaseUri(baseUri);
        
        // Assert
        // Verify the behavior of the method with empty base URI
        // For example: assert something
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test58(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test59() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test60() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test61() {
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
    @Test public void test62() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("Hello World!");
        assertEquals("One <span>two &amp;</span> Hello World!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test63() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)) + "!", t.outerHtml().trim());
    }
    @Test public void test64() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>!", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test65() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(1, nodes.size());
    }
    @Test public void test66() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there changed", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test67() {
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
    @Test public void test68(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        //Add another test case with @ in the textNode
        Document doc2 = Jsoup.parse("Hello&nbsp;&@hello");
        TextNode t2 = doc2.body().textNodes().get(0);
        assertEquals("Hello&amp;@hello", t2.outerHtml().trim());
        //Add another test case with < symbol in the textNode
        Document doc3 = Jsoup.parse("<");
        TextNode t3 = doc3.body().textNodes().get(0);
        assertEquals("&lt;", t3.outerHtml().trim());
    }
    @Test public void test69() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        //Add another test case with a different split index
        Document doc2 = Jsoup.parse("<div>Hello there</div>");
        Element div2 = doc2.select("div").first();
        TextNode tn2 = (TextNode) div2.childNode(0);
        TextNode tail2 = tn2.splitText(4);
        tail2.wrap("<b></b>");

        assertEquals("Hell<b>o there</b>", TextUtil.stripNewlines(div2.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test70() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
        
        //Add another test case with an empty div
        Document doc2 = Jsoup.parse("<div></div>");
        Element div2 = doc2.select("div").first();
        TextNode tn2 = (TextNode) div2.childNode(0);
        List<Node> nodes2 = tn2.childNodes();
        assertEquals(0, nodes2.size());
    }
    @Test public void test71() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
        
        //Add another test case with a different split index
        Document doc2 = Jsoup.parse("<div>Hello there</div>");
        Element div2 = doc2.select("div").first();
        TextNode tn2 = (TextNode) div2.childNode(0);
        TextNode tail2 = tn2.splitText(4);
        assertEquals("Hell", tn2.getWholeText());
        assertEquals("o there", tail2.getWholeText());
        tail2.text("o there!");
        assertEquals("Hello o there!", div2.text());
        assertTrue(tn2.parent() == tail2.parent());
    }
}