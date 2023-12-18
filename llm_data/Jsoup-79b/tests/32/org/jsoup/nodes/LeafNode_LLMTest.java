package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
    @Test public void test0(){
        Document doc = Jsoup.parse("<p class=\"test\">Hello</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        
        assertEquals(false, tn.hasAttributes());
    }
    @Test public void test1(){
        Document doc = Jsoup.parse("<p>Hello</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        
        assertEquals(false, tn.hasAttributes());
    }
    @Test public void test2(){
        Document doc = Jsoup.parse("<p Hello>Goodbye</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        
        assertEquals(true, tn.hasAttributes());
    }
    @Test public void test3(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362))); // Change input value
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test4() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7); // Change input value
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test5() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5); // Change input value
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test6() {
        TextNode one = new TextNode("   "); // Change input value
        TextNode two = new TextNode(""); // Change input value
        TextNode three = new TextNode("  \n"); // Change input value
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
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
        
        tn.text(" TEST!"); // Change input value
        assertEquals("One <span>two &amp;</span> TEST!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "test &"); // Change input value
        assertEquals("test &", tn.text());
        assertEquals("One <span>two &amp;</span>test &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test8(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362))); //change the input value here
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test9() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7); //change the input value here
        tail.wrap("<b></b>");

        assertEquals("Hello t<b>here</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test10() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5); //change the input value here
        assertEquals("Hello", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!!");
        assertEquals("Hello there!!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test11() {
        TextNode one = new TextNode("a"); //change the input value here
        TextNode two = new TextNode("a   "); //change the input value here
        TextNode three = new TextNode(" a\na a  "); //change the input value here
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test12() {
        Document doc = Jsoup.parse("<p>One <span>tw &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("tw &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("tw &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" POW!");
        assertEquals("One <span>tw &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>tw &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test13(){
        Document doc = Jsoup.parse(new String(Character.toChars(135360))); // Change the input value
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135360)), t.outerHtml().trim());
    }
    @Test public void test14() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(1); // Change the index of childNode
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test15() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5); // Change the split position
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test16() {
        TextNode one = new TextNode(" "); // Change the input value to a non-blank space
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
    @Test public void test17() {
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
    @Test public void test18(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test19(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test20(){
        Document doc = Jsoup.parse("");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test21(){
        Document doc = Jsoup.parse(null);
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test22(){
        Document doc = Jsoup.parse("     ");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("     ", t.outerHtml().trim());
    }
    @Test public void test23(){
        Document doc = Jsoup.parse("     " + new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("     " + new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<html><body><div>Hello there</div></body></html>", "http://www.example.com");
        Element div = doc.select("div").first();

        assertEquals("http://www.example.com", div.baseUri());
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<div>Hello there</div>");

        assertEquals("", doc.baseUri());
    }
    @Test public void test26() {
        Document doc = Jsoup.parse("<html><body><div><p>Hello there</p></div></body></html>", "http://www.example.com");
        Element p = doc.select("p").first();

        assertEquals("http://www.example.com", p.baseUri());
    }
    @Test public void test27(){
        Document doc = Jsoup.parse("");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test28(){
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("", tn.getWholeText());
    }
    @Test public void test29(){
        Document doc = Jsoup.parse("<div>   </div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("   ", tn.getWholeText());
    }
    @Test public void test30(){
        Document doc = Jsoup.parse(new String(new int[]{135361}, 0, 1));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(new int[]{135361}, 0, 1), t.outerHtml().trim());
    }
    @Test public void test31() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        tail.wrap("<b></b>");

        assertEquals("<b></b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test32() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(0);
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
        tail.text("there!");
        assertEquals("there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test33() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test34() {
        Document doc = Jsoup.parse("<p><span></span></p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("", tn.text());
        
        tn.text("POW!");
        assertEquals("POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("<span></span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test35(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test36() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test37() {
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
    @Test public void test38() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test39() {
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
    @Test public void test40(){
        Document doc = Jsoup.parse("");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test41(){
        Document doc = Jsoup.parse("\t  \n");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("\t  \n", t.outerHtml().trim());
    }
    @Test public void test42(){
        Document doc = Jsoup.parse("<div>Hello there!</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.text("Hello <> there!");
        assertEquals("Hello &lt;&gt; there!", div.text());
    }
    @Test public void test43(){
        Document doc = Jsoup.parse("<div>Hello there!</div>\n<div>How are you?</div>");
        Element div1 = doc.select("div").first();
        Element div2 = doc.select("div").last();
        TextNode tn1 = (TextNode) div1.childNode(0);
        TextNode tn2 = (TextNode) div2.childNode(0);
        assertEquals("Hello there!", tn1.text());
        assertEquals("How are you?", tn2.text());
    }
    @Test public void test44(){
        Document doc = Jsoup.parse("<p>&amp; &lt; &gt; &quot; &apos;</p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("& < > \" '", tn.text());
    }
}