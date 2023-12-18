package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test public void test0() {
        TextNode one = new TextNode("   ");
        TextNode two = new TextNode("\t\t\n");
        TextNode three = new TextNode("\n \n");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("\tHello\t");

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
        TextNode tail = tn.splitText(5);
        assertEquals("Hello", tn.getWholeText());
        assertEquals(" there", tail.getWholeText());
        tail.text(" there!");
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
        
        tn.text(" BOOM!");
        assertEquals("One <span>two &amp;</span> BOOM!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test3() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        tail.wrap("<b></b>");

        assertEquals("Hello <b> there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test4(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
@Test
public void test5() {
    // Arrange
    Object value = null;

    // Act
    ensureAttributes();

    // Assert
    assertFalse(hasAttributes());
    assertEquals(new Attributes(), value);
}
@Test
public void test6() {
    // Arrange
    Object value = "test";

    // Act
    ensureAttributes();

    // Assert
    assertTrue(hasAttributes());
    assertEquals(new Attributes().put(nodeName(), "test"), value);
}
@Test
public void test7() {
    TextNode one = new TextNode(null);
    TextNode two = new TextNode("          ");
    TextNode three = new TextNode("  \t \n\n   ");
    TextNode four = new TextNode("Hi");
    TextNode five = new TextNode("\t \nHi ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
    assertFalse(four.isBlank());
    assertFalse(five.isBlank());
}
@Test
public void test8() {
    Document doc = Jsoup.parse("<div>Greetings there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(10);
    assertEquals("Greetings ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Greetings there!", div.text());
    assertTrue(tn.parent() == tail.parent());
}
@Test
public void test9() {
    Document doc = Jsoup.parse("<p>One <span>two &lt;&gt;</span> three &lt;&gt;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two <", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two <", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three <", tn.text());

    tn.text(" KAPOW!");
    assertEquals("One <span>two &lt;&gt;</span> KAPOW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam <");
    assertEquals("kablam <", tn.text());
    assertEquals("One <span>two &lt;&gt;</span>kablam &lt;&gt;", TextUtil.stripNewlines(p.html()));
}
@Test
public void test10() {
    Document doc = Jsoup.parse("<div>Greetings there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(10);
    tail.wrap("<b></b>");

    assertEquals("Greetings <b>there</b>", TextUtil.stripNewlines(div.html()));
}
@Test
public void test11(){
    Document doc = Jsoup.parse(new String(Character.toChars(123456)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(123456)), t.outerHtml().trim());
}
@Test
public void test12() {
    TextNode one = new TextNode("     ");
    TextNode two = new TextNode("  \n\n   ");
    TextNode three = new TextNode("  \t\t   ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
}
@Test
public void test13() {
    TextNode one = new TextNode("     ");
    TextNode two = new TextNode("\n\n   ");
    TextNode three = new TextNode("\n  ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
}
@Test
public void test14() {
    TextNode one = new TextNode("     ");
    TextNode two = new TextNode("  \n\n   ");
    TextNode three = new TextNode("\n  ");

    assertTrue(one.isBlank());
    assertTrue(two.isBlank());
    assertTrue(three.isBlank());
}
@Test
public void test15() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(3);
    tail.wrap("<em></em>");

    assertEquals("Hel<em>lo there</em>", TextUtil.stripNewlines(div.html()));
}
@Test
public void test16() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(4);
    tail.wrap("<b></b>");

    assertEquals("Hell<b>o there</b>", TextUtil.stripNewlines(div.html()));
}
@Test
public void test17() {
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
public void test18() {
    Document doc = Jsoup.parse("  " + new String(Character.toChars(135361)) + "  ");
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("  " + new String(Character.toChars(135361)) + "  ", t.outerHtml().trim());
}
    @Test public void test19() {
        TextNode one = new TextNode(""); // change input: one = new TextNode("Hello");
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
        
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test22() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test23(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test24() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("   ");
        TextNode three = new TextNode("  \n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");
        TextNode six = new TextNode("     "); // Change: Add a new TextNode with multiple spaces

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
        assertFalse(six.isBlank()); // Change: Verify that the method correctly detects multiple spaces as not blank
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!!");
        assertEquals("Hello there!!", div.text());
        assertTrue(tn.parent() == tail.parent());

        TextNode tn2 = (TextNode) div.childNode(0); // Change: Add a new TextNode with different index
        TextNode tail2 = tn2.splitText(5);
        assertEquals("Hello", tn2.getWholeText());
        assertEquals(" there!!", tail2.getWholeText());
        tail2.text(" there??");
        assertEquals("Hello there??", div.text());
        assertTrue(tn2.parent() == tail2.parent());
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

        tn.text(" POW!!");
        assertEquals("One <span>two &amp;</span> POW!!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam!!");
        assertEquals("kablam!!", tn.text());
        assertEquals("One <span>two &amp;</span>kablam!!amp;", TextUtil.stripNewlines(p.html()));

        TextNode tn2 = (TextNode) p.childNode(0); // Change: Add a new TextNode with different index
        tn2.text("Uno");
        assertEquals("Uno<span>two &amp;</span>kablam!!amp;", TextUtil.stripNewlines(p.html()));
        tn2.attr(tn2.nodeName(), "x");
        assertEquals("x", tn2.text());
        assertEquals("Uno<span>two &amp;</span>kablam!!amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test27() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));

        TextNode tn2 = (TextNode) div.childNode(0); // Change: Add a new TextNode with different index
        TextNode tail2 = tn2.splitText(3);
        tail2.wrap("<i></i>");

        assertEquals("Hel<i>lo </i><b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test28(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());

        Document doc2 = Jsoup.parse(new String(Character.toChars(9728))); // Change: Add a new supplementary character
        TextNode t2 = doc2.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(9728)), t2.outerHtml().trim());
    }
@Test
public void test29() {
    Element element = new Element("div");
    assertTrue(element.hasAttr("key"));
}
@Test
public void test30() {
    Element element = new Element("span");
    assertFalse(element.hasAttr("key"));
}
@Test
public void test31() {
    Element element = new Element("a");
    element.attr("href", "https://example.com");
    assertTrue(element.hasAttr("href"));
}
@Test
public void test32() {
    Element element = new Element("img");
    element.attr("src", "image.jpg");
    assertTrue(element.hasAttr("src"));
}
@Test
public void test33() {
    Element element = new Element("p");
    assertFalse(element.hasAttr("id"));
}
@Test
public void test34() {
    // Create a new Node with attributes
    Node node = new Node();
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    
    // Call removeAttr method with key not present in the attributes
    Node result = node.removeAttr("key3");
    
    // Verify that the attributes are not changed
    Assert.assertEquals("value1", node.attr("key1"));
    Assert.assertEquals("value2", node.attr("key2"));
    
    // Verify that the result is the same as the original node
    Assert.assertSame(node, result);
}
@Test
public void test35() {
    // Create a new Node with attributes
    Node node = new Node();
    node.attr("key1", "value1");
    node.attr("key2", "value2");
    
    // Call removeAttr method with key present in the attributes
    Node result = node.removeAttr("key1");
    
    // Verify that the key is removed from the attributes
    Assert.assertNull(node.attr("key1"));
    
    // Verify that the other key-value pairs are not changed
    Assert.assertEquals("value2", node.attr("key2"));
    
    // Verify that the result is the same as the original node
    Assert.assertSame(node, result);
}
@Test
public void test36() {
    String result = absUrl(null);
    // assert result
    assertNotNull(result);
    assertEquals("", result);
}
@Test
public void test37() {
    String result = absUrl("");
    // assert result
    assertNotNull(result);
    assertEquals("", result);
}
@Test
public void test38() {
    String result = absUrl("invalidKey");
    // assert result
    assertNotNull(result);
    assertEquals("", result);
}
@Test
public void test39() {
    String result = absUrl("validKey");
    // assert result
    assertNotNull(result);
    assertEquals("", result);
}
@Test
public void test40() {
    // set up attributes
    // ...
    
    String result = absUrl("validKey");
    // assert result
    assertNotNull(result);
    // assert other conditions based on the specific attributes
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
        Document doc = Jsoup.parse("<div>Hello there</div>", "https://www.example.com");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.setBaseUri("https://www.google.com");

        assertEquals("https://www.google.com", tn.baseUri());
    }
    @Test public void test43() {
        Document doc = Jsoup.parse("<div>Hello there</div>", "https://www.example.com");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);

        assertEquals("", tn.baseUri());
    }
@Test
public void test44() {
    String baseUri = "https://www.example.com";
    doSetBaseUri(baseUri);
    // assert something
}
@Test
public void test45() {
    String baseUri = null;
    doSetBaseUri(baseUri);
    // assert something
}
@Test
public void test46() {
    String baseUri = "";
    doSetBaseUri(baseUri);
    // assert something
}
@Test
public void test47() {
    String baseUri = " ";
    doSetBaseUri(baseUri);
    // assert something
}
@Test
public void test48() {
    String baseUri = "\t";
    doSetBaseUri(baseUri);
    // assert something
}
@Test
public void test49() {
    String baseUri = "\n";
    doSetBaseUri(baseUri);
    // assert something
}
@Test
public void test50() {
    String baseUri = "invalidurl";
    doSetBaseUri(baseUri);
    // assert something
}
@Test public void test51() {
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
@Test public void test52() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(0, nodes.size());
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
@Test public void test54() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); 
}
@Test public void test55(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
}
@Test public void test56(){
    Document doc = Jsoup.parse("<div></div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    assertEquals(0, tn.childNodeSize());
}
@Test public void test57(){
    Document doc = Jsoup.parse("<div>Hello <span>there</span></div>");
    Element div = doc.select("div").first();
    TextNode tn1 = (TextNode) div.childNode(0);
    TextNode tn2 = (TextNode) div.childNode(2);
    tn1.text("Hi");
    tn2.text("bye");
    assertEquals("Hi <span>bye</span>", div.html());
}
    @Test public void test58() {
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

        // Variation 1: Empty text
        doc = Jsoup.parse("<div></div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
        tail.text("new text");
        assertEquals("new text", div.text());
        assertTrue(tn.parent() == tail.parent());
        
        // Variation 2: Text with white space
        doc = Jsoup.parse("<div>Hello \n World</div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("\n World", tail.getWholeText());
        tail.text("World!");
        assertEquals("Hello World!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test59() {
        // Original test case
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());

        // Variation 1: Empty text
        doc = Jsoup.parse("<div></div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        nodes = tn.childNodes();
        assertEquals(0, nodes.size());
        
        // Variation 2: Text with white space
        doc = Jsoup.parse("<div>Hello \n World</div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test60() {
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

        // Variation 1: Empty text
        doc = Jsoup.parse("<p></p>");
        p = doc.select("p").first();

        tn = (TextNode) p.childNode(0);
        tn.text("new text");
        assertEquals("new text", p.text());

        tn.attr(tn.nodeName(), "attr value");
        assertEquals("attr value", tn.text());
        assertEquals("<p>attr value</p>", TextUtil.stripNewlines(p.html()));
        
        // Variation 2: Text with white space
        doc = Jsoup.parse("<p>Hello \n World</p>");
        p = doc.select("p").first();

        tn = (TextNode) p.childNode(0);
        tn.text("Hello World!");
        assertEquals("Hello World!", p.text());

        tn.attr(tn.nodeName(), "attr value");
        assertEquals("attr value", tn.text());
        assertEquals("<p>attr value</p>", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test61() {
        // Original test case
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

        // Variation 1: Empty text
        doc = Jsoup.parse("<div></div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b></b>", TextUtil.stripNewlines(div.html()));

        // Variation 2: Text with white space
        doc = Jsoup.parse("<div>Hello \n World</div>");
        div = doc.select("div").first();
        tn = (TextNode) div.childNode(0);
        tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello \n <b>World</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test62(){
        // Original test case
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());

        // Variation 1: Empty text
        doc = Jsoup.parse("");
        t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());

        // Variation 2: Text with white space
        doc = Jsoup.parse(" ");
        t = doc.body().textNodes().get(0);
        assertEquals(" ", t.outerHtml().trim());
    }
}