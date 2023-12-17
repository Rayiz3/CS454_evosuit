package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test public void test0() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("\tHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test1() {
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
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test3() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test4(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test5() {
        TextNode six = new TextNode("\t");
        assertTrue(six.isBlank());
    }
    @Test public void test6() {
        TextNode seven = new TextNode("\n\n\n");
        assertTrue(seven.isBlank());
    }
    @Test public void test7() {
        Document doc = Jsoup.parse("<div>Hello there!</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello", tn.getWholeText());
        assertEquals(" there!", tail.getWholeText());
        tail.text("where!");
        assertEquals("Hello where!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test8() {
        Document doc = Jsoup.parse("<div>Hellothere</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("where!");
        assertEquals("Hello where!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test9() {
        Document doc = Jsoup.parse("<p>One <span>two &amp; five</span> three &amp;</p>");
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
        Document doc = Jsoup.parse("<div>Hellothere</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello<b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test11() {
        Document doc = Jsoup.parse(new String(Character.toChars(135361))+ " This is another text.");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)) + " This is another text.", t.outerHtml().trim());
    }
@Test
public void test12() {
    // Initialize the test input
    value = null;
    
    // Call the method under test
    Attributes result = attributes();
    
    // Perform assertion
    assertNull(result);
}
@Test
public void test13() {
    // Initialize the test input
    value = "not_attributes";
    
    // Call the method under test
    Attributes result = attributes();
    
    // Perform assertion
    assertNull(result);
}
@Test
public void test14() {
    // Initialize the test input
    value = new Object();
    
    // Call the method under test
    Attributes result = attributes();
    
    // Perform assertion
    assertNull(result);
}
public class TestClass {

    @Test
    public void test15() {
        MyClass myClass = new MyClass();

        myClass.ensureAttributes();

        assertTrue(myClass.hasAttributes());
        assertNotNull(myClass.value);
        assertEquals(Attributes.class, myClass.value.getClass());
    }

    @Test
    public void test16() {
        MyClass myClass = new MyClass();
        myClass.value = new Attributes();

        myClass.ensureAttributes();

        assertTrue(myClass.hasAttributes());
        assertEquals(Attributes.class, myClass.value.getClass());
    }

    // Additional test case to kill more mutants

    @Test
    public void test17() {
        MyClass myClass = new MyClass();
        myClass.value = "coreValue";

        myClass.ensureAttributes();

        assertTrue(myClass.hasAttributes());
        assertNotNull(myClass.value);
        assertEquals(Attributes.class, myClass.value.getClass());

        Attributes attributes = (Attributes) myClass.value;
        assertEquals("coreValue", attributes.get(myClass.nodeName()));
    }
}
    @Test public void test18() {
        TextNode one = new TextNode(""); // original test case
        TextNode two = new TextNode("     "); // additional test case with trailing whitespace
        TextNode three = new TextNode("  \n\n   "); // additional test case with multiline whitespace
        TextNode four = new TextNode("Hello"); // original test case
        TextNode five = new TextNode("  \nHello "); // additional test case with leading and trailing whitespace

        assertTrue(one.isBlank()); // original test case
        // additional test case
        assertFalse(two.isBlank()); // expecting false because of trailing whitespace
        // additional test case
        assertTrue(three.isBlank()); // expecting true because it only contains whitespace characters
        assertFalse(four.isBlank()); // original test case
        // additional test case
        assertFalse(five.isBlank()); // expecting false because of leading and trailing whitespace
    }
    @Test public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        
        TextNode tn = (TextNode) div.childNode(0); // original test case
        TextNode tail = tn.splitText(6); // original test case
        assertEquals("Hello ", tn.getWholeText()); // original test case
        assertEquals("there", tail.getWholeText()); // original test case
        tail.text("there!"); // original test case
        assertEquals("Hello there!", div.text()); // original test case
        assertTrue(tn.parent() == tail.parent()); // original test case
      
        // additional test case
        tn = (TextNode) div.childNode(1);
        tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("there!", tail.getWholeText());
        tail.text("there again!");
        assertEquals("there again!", TextUtil.stripNewlines(div.text()));
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test20() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        
        Element span = doc.select("span").first();
        assertEquals("two &", span.text()); // original test case
        TextNode spanText = (TextNode) span.childNode(0); // original test case
        assertEquals("two &", spanText.text()); // original test case
        
        TextNode tn = (TextNode) p.childNode(2); // original test case
        assertEquals(" three &", tn.text()); // original test case
        
        tn.text(" POW!"); // original test case
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html())); // original test case

        tn.attr(tn.nodeName(), "kablam &"); // original test case
        assertEquals("kablam &", tn.text()); // original test case
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html())); // original test case
        
        // additional test case
        TextNode spanTextNode = new TextNode("new text");
        span.insertChildren(0, Collections.singletonList(spanTextNode));
        assertEquals("new texttwo &", span.text()); // expecting the span text to include the new text node
        
        tn.attr("data-attribute", "test"); // additional test case
        assertEquals("kablam &", tn.text()); // expecting the text node to not include the attribute
    }
    @Test public void test21() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0); // original test case
        TextNode tail = tn.splitText(6); // original test case
        tail.wrap("<b></b>"); // original test case

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // original test case
        
        // additional test case
        tn = (TextNode) div.childNode(1);
        tail = tn.splitText(3);
        tail.wrap("<i></i>");
        assertEquals("there <i>there</i>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test22(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361))); // original test case
        TextNode t = doc.body().textNodes().get(0); // original test case
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim()); // original test case
        
        // additional test case
        t.text("new text");
        assertEquals("new text", t.outerHtml().trim());
    }
    @Test public void test23() {
        TextNode empty = new TextNode(""); // expecting true because it is empty
        assertTrue(empty.isBlank());
    }
    @Test public void test24() {
        TextNode node = new TextNode("  \nHello \n "); // expecting false because of leading and trailing newlines
        assertFalse(node.isBlank());
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b>Hello there</b>", TextUtil.stripNewlines(div.html())); // expecting the entire text to be wrapped in <b> tags
    }
    @Test public void test26() {
        TextNode one = new TextNode("Hello");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test27() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello", tn.getWholeText());
        assertEquals(" there", tail.getWholeText());
        tail.text(" there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text("POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test29() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test30(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test31() {
        TextNode one = new TextNode(" ");
        TextNode two = new TextNode("\n");
        TextNode three = new TextNode("\r");
        TextNode four = new TextNode("\t");
        TextNode five = new TextNode(" \n\t ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertTrue(four.isBlank());
        assertTrue(five.isBlank());
    }
    @Test public void test32() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("Hello there", tail.getWholeText());
        tail.text("");
        assertEquals("Hello there", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text("");
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "");
        assertEquals("", tn.text());
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test34() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3);
        tail.wrap("<b></b>");

        assertEquals("Hel<b>lo there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test35(){
        Document doc = Jsoup.parse(new String(Character.toChars(159357)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(159357)), t.outerHtml().trim());
    }
    @Test public void test36() {
        TextNode one = new TextNode("Hello");  // Change the input to a non-blank text
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(one.isBlank());  // Change assertion to expect false
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test37() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);  // Change split index to 5
        assertEquals("Hello", tn.getWholeText());  // Change expected value to "Hello"
        assertEquals(" there", tail.getWholeText());  // Change expected value to " there"
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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
        
        tn.text("POW!");  // Remove space from the new text
        assertEquals("One <span>two &amp;</span>POW!", TextUtil.stripNewlines(p.html()));  // Remove space from expected HTML

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test39() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);  // Change split index to 5
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test40(){
        Document doc = Jsoup.parse(new String(Character.toChars(99734)));  // Change supplementary character to a different value
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(99734)), t.outerHtml().trim());  // Change expected value to the new supplementary character
    }
@Test
public void test41() {
    String key = "attribute";
    
    // Create a document with attribute "attribute"
    Document document = Jsoup.parse("<div attribute='value'></div>");
    
    boolean result = document.hasAttr(key);
    
    assertTrue(result);
}
@Test
public void test42() {
    String key = "attribute";
    
    // Create a document without any attributes
    Document document = Jsoup.parse("<div></div>");
    
    boolean result = document.hasAttr(key);
    
    assertFalse(result);
}
@Test
public void test43() {
    String key = null;
    
    Document document = Jsoup.parse("<div attribute='value'></div>");
    
    boolean result = document.hasAttr(key);
    
    assertFalse(result);
}
@Test
public void test44() {
    String key = "";
    
    Document document = Jsoup.parse("<div attribute='value'></div>");
    
    boolean result = document.hasAttr(key);
    
    assertFalse(result);
}
    @Test
    public void test45() {
        // Test when key exists in attributes map
        Element element = new Element("div");
        element.attr("id", "test");
        element.attr("class", "test-class");

        Node removedNode = element.removeAttr("id");
        assertNull(removedNode);
        assertFalse(element.hasAttr("id"));

        // Test when key does not exist in attributes map
        element = new Element("div");
        element.attr("class", "test-class");

        removedNode = element.removeAttr("id");
        assertNull(removedNode);
        assertFalse(element.hasAttr("id"));

        // Test when no attributes are present
        element = new Element("div");

        removedNode = element.removeAttr("id");
        assertNull(removedNode);
        assertFalse(element.hasAttr("id"));
    }
@Test
public void test46() {
    String key = "nonExistingAttribute";
    String absUrl = absUrl(key);
    assertEquals(null, absUrl);
}
@Test
public void test47() {
    String key = "";
    String absUrl = absUrl(key);
    assertEquals(null, absUrl);
}
@Test
public void test48() {
    String key = "href";
    String absUrl = absUrl(key);
    assertEquals(<expected result>, absUrl);
}
    @Test
    public void test49() {
        Document doc = Jsoup.parse("");
        assertEquals("", doc.baseUri());
    }
    @Test
    public void test50() {
        Document parentDoc = Jsoup.parse("<html></html>");
        Document doc = Jsoup.parse("<div>Hello there</div>", parentDoc.baseUri());
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("", tn.baseUri());
        assertEquals("http://example.com", tail.baseUri());

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test
    public void test51() {
        Document grandParentDoc = Jsoup.parse("<html></html>", "http://example.com");
        Document parentDoc = Jsoup.parse("", grandParentDoc.baseUri());
        Document doc = Jsoup.parse("<div>Hello there</div>", parentDoc.baseUri());
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("", tn.baseUri());
        assertEquals("http://example.com", tail.baseUri());

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
@Test
public void test52() {
    String validUri = "https://www.example.com";
    // call the method with a valid uri
    doSetBaseUri(validUri);
    // check if the baseUri is set correctly
    assertEquals(validUri, getBaseUri());
}
@Test
public void test53() {
    // call the method with a null uri
    doSetBaseUri(null);
    // check if the baseUri is null
    assertNull(getBaseUri());
}
@Test
public void test54() {
    String emptyUri = "";
    // call the method with an empty uri
    doSetBaseUri(emptyUri);
    // check if the baseUri is set correctly
    assertEquals(emptyUri, getBaseUri());
}
@Test
public void test55() {
    String invalidUri = "example.com";
    // call the method with an invalid uri
    doSetBaseUri(invalidUri);
    // check if the baseUri is set correctly
    assertEquals(invalidUri, getBaseUri());
}
    @Test public void test56() {
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
    @Test public void test57() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test58() {
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
    @Test public void test59() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test60(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("", tn.text());
    }
    @Test public void test62() {
        Document doc = Jsoup.parse("<div>Hello <b>there</b></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello", tn.text());
        assertEquals(" there", tail.text());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test63() {
        Document doc = Jsoup.parse("<div><p>Hello<span>there</span></p></div>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        tn.unwrap();
        assertEquals("Hello<span>there</span>", p.html());
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
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
    }
    @Test public void test66() {
        Document doc = Jsoup.parse("<div>Hi</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(1);
        assertEquals("H", tn.getWholeText());
        assertEquals("i", tail.getWholeText());
    }
    @Test public void test67() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test68() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test69() {
        Document doc = Jsoup.parse("<div>Hi</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test70() {
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
    @Test public void test71() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        List<Node> childNodes = p.childNodes();
        assertEquals(0, childNodes.size());
    }
    @Test public void test72() {
        Document doc = Jsoup.parse("<p>Hi</p>");
        Element p = doc.select("p").first();
        List<Node> childNodes = p.childNodes();
        assertEquals(0, childNodes.size());
    }
    @Test public void test73() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test74() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
    }
    @Test public void test75() {
        Document doc = Jsoup.parse("<div>Hi</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(1);
        assertEquals("H", tn.getWholeText());
        assertEquals("i", tail.getWholeText());
    }
    @Test public void test76(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test77(){
        Document doc = Jsoup.parse("");
        List<TextNode> textNodes = doc.body().textNodes();
        assertEquals(0, textNodes.size());
    }
    @Test public void test78(){
        Document doc = Jsoup.parse("A");
        List<TextNode> textNodes = doc.body().textNodes();
        assertEquals(0, textNodes.size());
    }
}