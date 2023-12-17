package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test public void test0() {
        Attributes attributes = new Attributes();
        TextNode tn = new TextNode("example text", attributes);
        assertTrue(tn.hasAttributes());
        tn.removeAttributes();
        assertFalse(tn.hasAttributes());
    }
    @Test public void test1() {
        TextNode tn = new TextNode("example text");
        assertFalse(tn.hasAttributes());
    }
    @Test public void test2() {
        Attributes attributes1 = new Attributes();
        tn1 = new TextNode("example text", attributes1);

        Attributes attributes2 = new Attributes();
        attributes2.put("class", "exampleClass");
        tn2 = new TextNode("example text", attributes2);

        assertFalse(tn1.hasAttributes());
        assertTrue(tn2.hasAttributes());
    }
@Test
public void test3() {
    Object value = null;

    // Insert test logic here

    fail("Test case not implemented");
}
@Test
public void test4() {
    Object value = "test";

    // Insert test logic here

    fail("Test case not implemented");
}
@Test
public void test5() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" three &", tn.text());

    tn.text("");
    assertTrue(tn.isBlank());
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
}
@Test
public void test6() {
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("", t.outerHtml().trim());
}
@Test
public void test7() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(0);
    tail.wrap("<b></b>");

    assertEquals("<b>Hello there</b>", TextUtil.stripNewlines(div.html()));
}
@Test
public void test8() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(0);
    assertEquals("Hello there", tn.getWholeText());
    assertEquals("", tail.getWholeText());
    tail.text("Hello");
    assertEquals("Hello Hello", div.text());
    assertTrue(tn.parent() == tail.parent());
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
        
        tn.text(""); // Regression test 1: empty string
        assertEquals("", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "new value"); // Regression test 2: different attribute value
        assertEquals("new value", tn.text());
        assertEquals("One <span>two &amp;</span>new value;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test10() {
        TextNode one = new TextNode("non-empty string"); // Regression test 3: non-empty string
        TextNode two = new TextNode(" non-empty string "); // Regression test 4: non-empty string with leading and trailing whitespace
        TextNode three = new TextNode("  \nHello "); // Regression test 5: non-empty string with leading whitespace

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
    }
    @Test public void test11(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        t.text(""); // Regression test 6: empty string
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test12() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        tn.text(""); // Regression test 7: empty string
        div.text("Hello there"); // correct the expected value
        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test13() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
        
        tail.text(""); // Regression test 8: empty string
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("", tail.getWholeText());
        assertEquals("Hello ", div.text());
    }
    @Test public void test14() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(""); // change input value to empty string
        assertEquals("<span>two &amp;</span>", TextUtil.stripNewlines(p.html())); // mutant: return EmptyString instead of the value
        
        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test15() {
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

        one.text("not blank"); // change input value to a non-empty string
        assertFalse(one.isBlank()); // mutant: return false instead of true
    }
    @Test public void test16(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
        
        String supChar = new String(Character.toChars(135361));
        t.text(""); // change input value to empty string
        assertEquals("", t.outerHtml().trim()); // mutant: return EmptyString instead of the value

        t.text(supChar); // change input value to supplementary character
        assertEquals(supChar, t.outerHtml().trim()); // mutant: return EmptyString instead of the value
    }
    @Test public void test17() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
        
        // change the text value to be splitted
        tn.text("Hello"); // change input value to "Hello"
        tail = tn.splitText(3); // mutant: incorrect splitting position
        tail.wrap("<b></b>");

        assertEquals("Hel<b>lo there</b>", TextUtil.stripNewlines(div.html())); // mutant: return "H<b>ello there</b>" instead
    }
    @Test public void test18() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());

        tn.text("Hello"); // change input value to "Hello"
        tail = tn.splitText(2); // mutant: incorrect splitting position
        tail.text("!");

        assertEquals("H!lo there!", div.text()); // mutant: return "H!llo there!" instead
        
        tail.text(" there!"); // change input value to " there!"
        assertEquals("H there!", div.text()); // mutant: return "H!llo there!" instead
    }
    @Test public void test19() {
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
    @Test public void test20() {
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
    @Test public void test21(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test22() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test23() {
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
    @Test public void test24(){
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
    @Test public void test25() {
        TextNode one = new TextNode("Hello");
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
    @Test public void test26(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test27() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(1);
        tail.wrap("<b></b>");

        assertEquals("H<b>ello there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test28() {
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
public void test29() {
    Element element = new Element("div");
    element.attr("id", "myDiv");
    boolean hasAttr = element.hasAttr("id");
    assertTrue(hasAttr);
}
@Test
public void test30() {
    Element element = new Element("div");
    boolean hasAttr = element.hasAttr("class");
    assertFalse(hasAttr);
}
@Test
public void test31() {
    Element element = new Element("div");
    String key = null;
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        boolean hasAttr = element.hasAttr(key);
    });
    assertEquals("key must not be null", exception.getMessage());
}
@Test
public void test32() {
    String result = absUrl(null);
    // Assert statements
}
@Test
public void test33() {
    String result = absUrl("");
    // Assert statements
}
@Test
public void test34() {
    String result = absUrl("key");
    // Assert statements
}
@Test
public void test35() {
    ensureAttributes();
    String result = absUrl("key");
    // Assert statements
}
@Test
public void test36() {
    ensureAttributes();
    String result = absUrl("invalidKey");
    // Assert statements
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
        Document doc = Jsoup.parse("");
        
        assertEquals("", doc.baseUri());
    }
    @Test public void test39() {
        Document doc = Jsoup.parse("<div>Hello</div>");
        
        assertEquals("", doc.baseUri());
    }
    @Test public void test40() {
        Document parentDoc = Jsoup.parse("<html><head></head><body></body></html>");
        Document doc = Jsoup.parse("<div>Hello</div>", "", parentDoc.baseUri());
        
        assertEquals(parentDoc.baseUri(), doc.baseUri());
    }
    @Test
    public void test41() {
        // Original test case
        doSetBaseUri("http://example.com");
        
        // Additional test cases
        doSetBaseUri("http://127.0.0.1");
        doSetBaseUri("http://localhost");
        doSetBaseUri("https://www.google.com");
    }
    @Test public void test42() {
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
    @Test public void test43(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test44() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.text("");
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test45() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.text("Hellothere");
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test46() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.text("");
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test47() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.text("Hellothere");
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test48() {
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
    @Test public void test49() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text("POW! POW!");
        assertEquals("One <span>two &amp;</span> POW! POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test50(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361))+" ", t.outerHtml().trim());
    }
    @Test public void test51() {
        Document doc = Jsoup.parse("<div>Hello there test1234567(*&#$%!</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there test1234567(*&#$%!</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test52() {
        Document doc = Jsoup.parse("<div>Hello there test1234567(*&#$%!</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div>Hello there test1234567(*&#$%!</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there test1234567(*&#$%!", tail.getWholeText());
        tail.text("there! test1234567(*&#$%!");
        assertEquals("Hello there! test1234567(*&#$%!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
}