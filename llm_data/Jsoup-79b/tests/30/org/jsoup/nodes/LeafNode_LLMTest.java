package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test public void test0() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3); // Change input from 6 to 3
        assertEquals("Hel", tn.getWholeText()); // Change expected result from "Hello " to "Hel"
        assertEquals("lo there", tail.getWholeText()); // Change expected result from "there" to "lo there"
        tail.text("lo there!"); // Change input from "there!" to "lo there!"
        assertEquals("Hel lo there!", div.text()); // Change expected result from "Hello there!" to "Hel lo there!"
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test1() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(3); // Change input from 6 to 3
        tail.wrap("<b></b>");

        assertEquals("Hel<b>lo there</b>", TextUtil.stripNewlines(div.html())); // Change expected result from "Hello <b>there</b>" to "Hel<b>lo there</b>"
    }
    @Test public void test2(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test3() {
        TextNode one = new TextNode("Hello"); // Change input from "" to "Hello"
        TextNode two = new TextNode(" World"); // Change input from "     " to " World"
        TextNode three = new TextNode("  \n\n   "); // No change in input
        TextNode four = new TextNode("Hello"); // No change in input
        TextNode five = new TextNode("  \nHello "); // No change in input

        assertFalse(one.isBlank()); // Change expected result from true to false
        assertFalse(two.isBlank()); // Change expected result from true to false
        assertTrue(three.isBlank()); // No change in expected result
        assertFalse(four.isBlank()); // No change in expected result
        assertFalse(five.isBlank()); // No change in expected result
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

        tn.text(" YAY!"); // Change input from " POW!" to " YAY!"
        assertEquals("One <span>two &amp;</span> YAY!", TextUtil.stripNewlines(p.html())); // Change expected result from "One <span>two &amp;</span> POW!" to "One <span>two &amp;</span> YAY!"

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test5() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0); // change input value to 0
        assertEquals("", tn.getWholeText()); // expect empty string
        assertEquals("Hello there", tail.getWholeText()); // expect "Hello there"
        tail.text("there!");
        assertEquals("there!", div.text()); // expect "there!"
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test6() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(11); // change input value to 11
        assertEquals("Hello there", tn.getWholeText()); // expect "Hello there"
        assertEquals("", tail.getWholeText()); // expect empty string
        tail.text("!");
        assertEquals("Hello there!", div.text()); // expect "Hello there!"
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test7() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(20); // change input value to 20
        assertEquals("Hello there", tn.getWholeText()); // expect "Hello there"
        assertEquals("", tail.getWholeText()); // expect empty string
        tail.text("!");
        assertEquals("Hello there!", div.text()); // expect "Hello there!"
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test8() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(-1); // change input value to -1
        assertEquals("Hello there", tn.getWholeText()); // expect "Hello there"
        assertEquals("", tail.getWholeText()); // expect empty string
        tail.text("!");
        assertEquals("Hello there!", div.text()); // expect "Hello there!"
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test9() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(100); // change input value to 100
        assertEquals("Hello there", tn.getWholeText()); // expect "Hello there"
        assertEquals("", tail.getWholeText()); // expect empty string
        tail.text("!");
        assertEquals("Hello there!", div.text()); // expect "Hello there!"
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test10() {
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
    @Test public void test11() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test12(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test13() {
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
    @Test public void test14() {
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
    @Test public void test15() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test16() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test17(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test18() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("hello");
        TextNode five = new TextNode("  \nhello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
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
        
        tn.text(" pow!");
        assertEquals("one <span>two &amp;</span> pow!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("one <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test20() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7); // changed from 6 to 7
        assertEquals("Hello t", tn.getWholeText());
        assertEquals("here", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test21() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7); // changed from 6 to 7
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test22(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test23() {
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
    @Test public void test24() {
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
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test27(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test28() {
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
        Document doc = Jsoup.parse("<div id=\"1\"></div>");
        Element div = doc.select("div").first();

        Node returnedNode = div.attr("class", "abc");
        assertEquals(div, returnedNode);
        assertEquals("abc", div.attr("class"));

        returnedNode = div.attr("id", "2");
        assertEquals(div, returnedNode);
        assertEquals("2", div.attr("id"));
    }
    @Test public void test31() {
        Document doc = Jsoup.parse("<div>Hello</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.text("Goodbye");
        assertEquals("Goodbye", div.text());
    }
    @Test public void test32() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("over there");
        assertEquals("Hello over there", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test33() {
        Document doc = Jsoup.parse("<div>Hello world</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        Attributes attributes = div.attributes();
        attributes.put("class", "sample");
        assertEquals("sample", div.attr("class"));
        assertEquals("div class=\"sample\"", div.toString());
    }
    @Test public void test34() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test35() {
        Document doc = Jsoup.parse("");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("", tn.baseUri());
    }
    @Test public void test36() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(12);
        tail.wrap("<b></b>");

        assertEquals("<div>Hello there<b></b></div>", tn.baseUri());
    }
@Test public void test37() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(0); // Change input value from 6 to 0
    assertEquals("", tn.getWholeText()); // Mutant: Change return value from 0 to 5
    assertEquals("Hello there", tail.getWholeText()); // Mutant: Change return value from "there" to "there "
    tail.text("there!");
    assertEquals("there Hello there!", div.text()); // Mutant: Add a space before "Hello there!"
    assertTrue(tn.parent() == tail.parent());
}
@Test public void test38() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(0); // Change input value from 6 to 0
    tail.wrap("<b></b>");

    assertEquals("<b>Hello there</b>", TextUtil.stripNewlines(div.html())); // Mutant: Remove space before "Hello"
}
@Test public void test39(){
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("", t.outerHtml().trim()); // Mutant: Change return value from "135361" to ""
}
@Test public void test40() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("&", span.text()); // Mutant: Change return value from "two &" to "&"
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("&", spanText.text()); // Mutant: Change return value from "two &" to "&"

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" ", tn.text()); // Mutant: Change return value from " three &" to " "
    
    tn.text(""); // Change input value from " POW!" to ""
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html())); // Mutant: Remove " POW!" from target string

    tn.attr(tn.nodeName(), ""); // Change input value from "kablam &" to ""
    assertEquals("", tn.text()); // Mutant: Change return value from "kablam &" to ""
    assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html())); // Mutant: Remove "kablam &amp;" from target string
}
@Test public void test41() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    List<Node> nodes = tn.childNodes();
    assertEquals(1, nodes.size()); // Mutant: Change expected value from 0 to 1
}
    @Test public void test42() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7); // Change input value from 6 to 7
        assertEquals("Hello t", tn.getWholeText()); // Mutant: Change tn.getWholeText() to tn.text()
        assertEquals("here", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test43() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7); // Change input value from 6 to 7
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test44(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test45() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test46() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
}