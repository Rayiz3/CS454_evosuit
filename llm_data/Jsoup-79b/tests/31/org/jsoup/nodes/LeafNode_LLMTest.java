package org.jsoup.nodes;
import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class LeafNode_LLMTest {
@Test
public void test0() {
    Document doc = Jsoup.parse(new String(Character.toChars(135361)));
    TextNode t = doc.body().textNodes().get(0);
    assertEquals("", t.outerHtml().trim());
}
@Test
public void test1() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    tail.wrap("<b></b>");

    assertEquals("Hello <b><b>there</b></b>", TextUtil.stripNewlines(div.html()));
}
@Test
public void test2() {
    Document doc = Jsoup.parse("<div>Hello there</div>");
    Element div = doc.select("div").first();
    TextNode tn = (TextNode) div.childNode(0);
    TextNode tail = tn.splitText(6);
    assertEquals("Hello ", tn.getWholeText());
    assertEquals("there", tail.getWholeText());
    tail.text("there!");
    assertEquals("Hello ", div.text());
    assertFalse(tn.parent() == tail.parent());
}
@Test
public void test3() {
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
@Test
public void test4() {
    Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
    Element p = doc.select("p").first();

    Element span = doc.select("span").first();
    assertEquals("two &", span.text());
    TextNode spanText = (TextNode) span.childNode(0);
    assertEquals("two &", spanText.text());

    TextNode tn = (TextNode) p.childNode(2);
    assertEquals(" ", tn.text());

    tn.text("POW!");
    assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

    tn.attr(tn.nodeName(), "kablam &");
    assertEquals("kablam &", tn.text());
    assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
}
    @Test public void test5(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)) + " ");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test6(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)) + "a");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test7(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)) + " ");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test8() {
        Document doc = Jsoup.parse("<div>Hello there </div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there </b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there  there... must correct
    }
    @Test public void test9() {
        Document doc = Jsoup.parse("<div>Hello there    </div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there   </b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there  there... must correct
    }
    @Test public void test10() {
        Document doc = Jsoup.parse("<div>Hello there </div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there ", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test11() {
        Document doc = Jsoup.parse("<div>Hello there    </div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there   ", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test12() {
        TextNode one = new TextNode("x");
        TextNode two = new TextNode("    x");
        TextNode three = new TextNode(" \n\n  x");
        TextNode four = new TextNode("Hellox");
        TextNode five = new TextNode("  \nHellox ");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test13() {
        Document doc = Jsoup.parse("<p>One <span>two &amp; </span> three &amp; </p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two & ", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two & ", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three & ", tn.text());
        
        tn.text(" POOOW!");
        assertEquals("One <span>two &amp; </span> POOOW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam & ");
        assertEquals("kablam & ", tn.text());
        assertEquals("One <span>two &amp; </span>kablam &amp; ", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test14(){
        // Change input value to a different supplementary character
        Document doc = Jsoup.parse(new String(Character.toChars(135362)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135362)), t.outerHtml().trim());
    }
    @Test public void test15() {
        // Change input value to a different string with the same length
        Document doc = Jsoup.parse("<div>Salutations</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Salutations <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test16() {
        // Change input value to a different string
        Document doc = Jsoup.parse("<div>Goodbye there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(7);
        assertEquals("Goodbye ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Goodbye there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test17() {
        // Change input to a completely empty string
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
    @Test public void test18() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        // Change input value to a different string
        tn.text(" BOOM!");
        assertEquals("One <span>two &amp;</span> BOOM!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test19(){
        Document doc = Jsoup.parse(new String(Character.toChars(135362))); // Change input value
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test20() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test21() {
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
    }
    @Test public void test23() {
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
    @Test public void test24(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test25(){
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        tn.attr("key", "");
        assertEquals("", tn.attr("key"));
        assertEquals("", p.html().trim());
    }
    @Test public void test26(){
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        tn.attr("", "");
        assertEquals("", tn.attr(""));
        assertEquals("", p.html().trim());
    }
    @Test public void test27(){
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        tn.attr("differentKey", "");
        assertEquals("", tn.attr("differentKey"));
        assertEquals("", p.html().trim());
    }
    @Test public void test28(){
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();
        TextNode tn = (TextNode) p.childNode(0);
        tn.attr("differentKey", "differentValue");
        assertEquals("differentValue", tn.attr("differentKey"));
        assertEquals("", p.html().trim());
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
        tn.setBaseUri("");

        assertEquals("", tn.baseUri());
    }
    @Test public void test31() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        tn.setBaseUri(null);

        assertEquals("", tn.baseUri());
    }
    @Test public void test32(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test33(){
        Document doc = Jsoup.parse(new String(Character.toChars(65536)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(65536)), t.outerHtml().trim());
    }
    @Test public void test34() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.selectFirst("div");
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test35() {
        Document doc = Jsoup.parse("<p>One two &amp; three &amp;</p>");
        Element p = doc.selectFirst("p");
        TextNode tn = (TextNode) p.childNode(3);
        TextNode tail = tn.splitText(4);
        tail.wrap("<b></b>");

        assertEquals("One two &amp; <b>three</b> &amp;", TextUtil.stripNewlines(p.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test36() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.selectFirst("div");
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test37() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.selectFirst("div");
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("they");
        assertEquals("Hello there", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
    @Test public void test38() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.selectFirst("div");
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test39() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.selectFirst("div");
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(1, nodes.size());
    }
    @Test public void test40() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.selectFirst("p");

        Element span = doc.selectFirst("span");
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
    @Test public void test41() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.selectFirst("p");

        Element span = doc.selectFirst("span");
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());
        
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());
        
        tn.text(" BAM!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kabl", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test42(){
        Document doc = Jsoup.parse("");
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test43(){
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        div.empty();
        TextNode tn = (TextNode) div.childNode(0);
        assertNull(tn);
    }
    @Test public void test44(){
        Document doc = Jsoup.parse("<div>&#10084;</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        assertEquals("&#10084;", tn.outerHtml().trim());
    }
    @Test public void test45(){
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5);
        assertEquals("Hello", tn.getWholeText());
        assertEquals(" there", tail.getWholeText());
    }
}