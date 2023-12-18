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
        four.attr("attribute", "value");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test2(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        t.attr(t.nodeName(), "value");
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test3() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.attr(tn.nodeName(), "value");
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there value</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test4() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.attr(tn.nodeName(), "value");
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    public final Attributes attributes() {
        ensureAttributes();
        return (Attributes) value;
    }
@Test
public void test5() {
    // create a new instance of the class
    MyClass myClass = new MyClass();
    // set a non-null value for value attribute
    myClass.value = new Attributes();
    
    // call the attributes() method
    Attributes result = myClass.attributes();
    
    // assert that the result is not null
    assertNotNull(result);
}
@Test
public void test6() {
    // create a new instance of the class
    MyClass myClass = new MyClass();
    // set a null value for value attribute
    myClass.value = null;
    
    // call the attributes() method
    Attributes result = myClass.attributes();
    
    // assert that the result is null
    assertNull(result);
}
@Test
public void test7() {
    // Set value to null
    Object value = null;
    
    // Execute the method
    ensureAttributes();
    
    // Verify that attributes are created and value is added
    Attributes attributes = (Attributes) value;
    assertNotNull(attributes);
    assertNotNull(attributes.get(nodeName()));
}
@Test
public void test8() {
    // Set value to a non-null object
    Object value = "test";
    
    // Execute the method
    ensureAttributes();
    
    // Verify that attributes are created and value is added
    Attributes attributes = (Attributes) value;
    assertNotNull(attributes);
    assertNotNull(attributes.get(nodeName()));
}
@Test
public void test9() {
    // Set value to an empty string
    Object value = "";
    
    // Execute the method
    ensureAttributes();
    
    // Verify that attributes are created and value is added
    Attributes attributes = (Attributes) value;
    assertNotNull(attributes);
    assertNotNull(attributes.get(nodeName()));
}
@Test
public void test10() {
    // Set value to a non-string object
    Object value = 123;
    
    // Execute the method
    ensureAttributes();
    
    // Verify that attributes are created and value is added
    Attributes attributes = (Attributes) value;
    assertNotNull(attributes);
    assertNull(attributes.get(nodeName()));
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
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test12() {
        TextNode one = new TextNode("Hello");
        TextNode two = new TextNode("World");
        TextNode three = new TextNode("Hello\nWorld");
        TextNode four = new TextNode("Hello World!");
        TextNode five = new TextNode("Hello World?");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test13(){
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test14() {
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
    public void test15() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());

        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());

        tn.text(" <test>");
        assertEquals("One <span>two &amp;</span> <test>", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test
    public void test16() {
        TextNode one = new TextNode("Hello");
        TextNode two = new TextNode(" Hello ");
        TextNode three = new TextNode("Hello there");
        TextNode four = new TextNode("Hello!");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
    }
    @Test
    public void test17() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test
    public void test18() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<strong></strong>");

        assertEquals("Hello <strong>there</strong>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test
    public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(2);
        assertEquals("Hel", tn.getWholeText());
        assertEquals("lo there", tail.getWholeText());
        tail.text("lo there!");
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

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
        TextNode tn = (TextNode) p.childNode(2);
        tn.attr(null);
    }
    @Test public void test26() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(2);
        tn.attr(tn.nodeName(), "different value");
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

        tn.attr("", "kablam &");
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

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test29() {
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
    @Test public void test30(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test31() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test32() {
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
    public void test33() {
        Node node = new Node();
        assertNull(node.removeAttr(null));
    }
    @Test
    public void test34() {
        Node node = new Node();
        node.attr("name", "John");
        assertNull(node.removeAttr("age"));
    }
    @Test
    public void test35() {
        Node node = new Node();
        node.attr("name", "John");
        assertNotNull(node.removeAttr("name"));
    }
    @Test
    public void test36() {
        Node node = new Node();
        node.attr("name", "John");
        node.attr("age", "25");
        assertEquals("25", node.removeAttr("age"));
    }
@Test
public void test37() {
    String key = "url";
    String expectedResult = "https://www.example.com/url";
    
    String result = absUrl(key);
    
    assertEquals(expectedResult, result);
}
@Test
public void test38() {
    String key = null;
    String expectedResult = "https://www.example.com";
    
    String result = absUrl(key);
    
    assertEquals(expectedResult, result);
}
@Test
public void test39() {
    String key = "";
    String expectedResult = "https://www.example.com";
    
    String result = absUrl(key);
    
    assertEquals(expectedResult, result);
}
@Test
public void test40() {
    Document doc = Jsoup.parse("");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("", TextUtil.stripNewlines(div.html()));
}
@Test
public void test41() {
    Document doc = Jsoup.parse("<div></div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("", TextUtil.stripNewlines(div.html()));
}
@Test
public void test42() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(1);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello ", TextUtil.stripNewlines(div.html()));
}
@Test
public void test43() {
    String baseUri = "https://www.example.com";
    doSetBaseUri(baseUri);
    // Add assertions here
}
@Test
public void test44() {
    String baseUri = "";
    doSetBaseUri(baseUri);
    // Add assertions here
}
@Test
public void test45() {
    String baseUri = null;
    doSetBaseUri(baseUri);
    // Add assertions here
}
@Test
public void test46() {
    String baseUri = "https://www.example.com/long_string";
    doSetBaseUri(baseUri);
    // Add assertions here
}
    @Test public void test47() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;&amp;</span> three &&</p>");
        Element p = doc.select("p").first();
        ...
    }
    @Test public void test48(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)) + "Hello there");
        TextNode t = doc.body().textNodes().get(0);
        ...
    }
    @Test public void test49() {
        Document doc = Jsoup.parse("<div>Hello there!</div>");
        Element div = doc.select("div").first();
        ...
    }
    @Test public void test50() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        ...
    }
    @Test public void test51() {
        Document doc = Jsoup.parse("<div>Hello!</div>");
        Element div = doc.select("div").first();
        ...
    }
    @Test public void test52() {
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
    @Test public void test53() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals(0, p.childNodeSize());
        
        try {
            TextNode tn = (TextNode) p.childNode(1);
            assertFalse(true);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
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
        
        try {
            tn.text(" POW!");
            assertFalse(true);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
    @Test public void test55(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        try {
            t.text("changed");
            assertFalse(true);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
    @Test public void test56() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        try {
            tail.wrap("<u></u>");
            assertFalse(true);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
    @Test public void test57() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
        
        try {
            List<Node> subnodes = tn.ensureChildNodes();
            assertFalse(true);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
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
        
        try {
            tail.splitText(3);
            assertFalse(true);
        } catch(UnsupportedOperationException e) {
            assertTrue(true);
        }
    }
}