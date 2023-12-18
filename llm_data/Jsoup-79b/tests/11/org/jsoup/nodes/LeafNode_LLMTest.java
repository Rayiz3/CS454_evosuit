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
        
        tn.text("POW!");
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
        
        tn.text(" POWP");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
@Test public void test3() {
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
@Test public void test4() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("      ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
@Test public void test5(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
@Test public void test6(){
        Document doc = Jsoup.parse(new String(Character.toChars(135321)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135321)), t.outerHtml().trim());
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
        Document doc = Jsoup.parse("<div> Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
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
@Test public void test10() {
        Document doc = Jsoup.parse("<div>Hello! there</div>");
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
public void test11() {
    SampleClass sample = new SampleClass();
    sample.attributes();
    // Change input value of the method
    // Instead of ensuring attributes, set value to null
    sample.value = null;
    assertThrows(NullPointerException.class, () -> {
        sample.attributes();
    });
}
@Test
public void test12() {
    SampleClass sample = new SampleClass();
    sample.attributes();
    // Change input value of the method
    // Instead of ensuring attributes, set value to a different type
    sample.value = "invalid";
    assertThrows(ClassCastException.class, () -> {
        sample.attributes();
    });
}
@Test
public void test13() {
    // Test case with null value
    // This test case can kill the mutants that change the condition "coreValue != null" on line 8 to "coreValue == null"
    // The mutant will not enter the if statement and therefore the attributes object will not be updated with the core value
    Object value = null;
    YourClass instance = new YourClass(value);
    invokeEnsureAttributes(instance);
    Attributes attributes = instance.getAttributes();
    assertNull(attributes.get(instance.nodeName()));
}
@Test
public void test14() {
    // Test case with a non-null value
    // This test case can kill the mutants that change the condition "coreValue != null" on line 8 to "coreValue == null"
    // The mutant will enter the if statement and update the attributes object with the core value
    Object value = "value";
    YourClass instance = new YourClass(value);
    invokeEnsureAttributes(instance);
    Attributes attributes = instance.getAttributes();
    assertEquals(value, attributes.get(instance.nodeName()));
}
@Test
public void test15() {
    // Test case with non-null attributes
    // This test case can kill the mutants that change the condition "!hasAttributes()" on line 3 to "hasAttributes()"
    // The mutant will not enter the if statement and will not create a new attributes object
    Object value = "value";
    YourClass instance = new YourClass(value);
    instance.setAttributes(new Attributes());
    invokeEnsureAttributes(instance);
    Attributes attributes = instance.getAttributes();
    assertEquals(value, attributes.get(instance.nodeName()));
}
private void invokeEnsureAttributes(YourClass instance) {
    try {
        Method method = YourClass.class.getDeclaredMethod("ensureAttributes");
        method.setAccessible(true);
        method.invoke(instance);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
    }
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
        
        tn.text(""); // Change input to empty string
        assertEquals("", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), ""); // Change input to empty string
        assertEquals("", tn.text());
        assertEquals("One <span>two &amp;</span>", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test17() {
        TextNode one = new TextNode("Hello"); // Change input to non-blank text
        TextNode two = new TextNode("Hello  ");
        TextNode three = new TextNode("  Hello");
        TextNode four = new TextNode("H e l l o");
        TextNode five = new TextNode("Hello\n");

        assertFalse(one.isBlank());
        assertFalse(two.isBlank());
        assertFalse(three.isBlank());
        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test18(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361))); // Change input to different supplementary character
        TextNode t = doc.body().textNodes().get(0);
        assertEquals("", t.outerHtml().trim());
    }
    @Test public void test19() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(5); // Change split position
        tail.wrap("<b></b>");

        assertEquals("Hello<b> there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test20() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(4); // Change split position
        assertEquals("Hell", tn.getWholeText());
        assertEquals("o there", tail.getWholeText());
        tail.text(" there!"); // Change text of tail node
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
   @Test public void test21() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();
        //Changed input value from "POW!" to "POW\n!"
        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());

        tn.text("POW\n!");
        assertEquals("One <span>two &amp;</span> POW\n!", TextUtil.stripNewlines(p.html()));
   }
   @Test public void test22() {
        //Changed input value from "Hello" to "     Hello     "
        TextNode four = new TextNode("     Hello     ");
        assertTrue(four.isBlank());
   }
   @Test public void test23(){
        //Changed input value to have leading whitespace
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
   }
   @Test public void test24() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello \n<b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
   }
   @Test public void test25() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());

        //Changed input value from "there!" to "there!\n"
        tail.text("there!\n");
        assertEquals("Hello there!\n", div.text());
        assertTrue(tn.parent() == tail.parent());
   }
    @Test public void test26() {
        Document doc = Jsoup.parse("<p>One <span>two &amp;</span> three &amp;</p>");
        Element p = doc.select("p").first();

        Element span = doc.select("span").first();
        assertEquals("two &", span.text());
        TextNode spanText = (TextNode) span.childNode(0);
        assertEquals("two &", spanText.text());

        // Regression test 1
        // Change key to null
        span.attr(null);

        TextNode tn = (TextNode) p.childNode(2);
        assertEquals(" three &", tn.text());

        tn.text(" POW!");
        assertEquals("One <span>two &amp;</span> POW!", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test27() {
        TextNode one = new TextNode("");
        TextNode two = new TextNode("     ");
        TextNode three = new TextNode("  \n\n   ");
        TextNode four = new TextNode("Hello");
        TextNode five = new TextNode("  \nHello ");

        assertTrue(one.isBlank());
        assertTrue(two.isBlank());
        assertTrue(three.isBlank());

        // Regression test 2
        // Change text to null
        TextNode six = new TextNode(null);
        assertTrue(six.isBlank());

        assertFalse(four.isBlank());
        assertFalse(five.isBlank());
    }
    @Test public void test28(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());

        // Regression test 3
        // Change character to ascii character
        TextNode t2 = new TextNode("A");
        assertEquals("A", t2.outerHtml().trim());
    }
    @Test public void test29() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct

        // Regression test 4
        // Change split position
        TextNode tn2 = (TextNode) div.childNode(0);
        TextNode tail2 = tn2.splitText(2);
        tail2.wrap("<b></b>");

        assertEquals("He<b>llo there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test30() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());

        // Regression test 5
        // Change split position
        TextNode tn2 = (TextNode) div.childNode(0);
        TextNode tail2 = tn2.splitText(3);
        assertEquals("Hel", tn2.getWholeText());
        assertEquals("lo there", tail2.getWholeText());
        tail2.text("lo there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn2.parent() == tail2.parent());
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
        
        tn.text("");
        assertEquals("", TextUtil.stripNewlines(p.html()));

        tn.attr(tn.nodeName(), "kablam &");
        assertEquals("kablam &", tn.text());
        assertEquals("One <span>two &amp;</span>kablam &amp;", TextUtil.stripNewlines(p.html()));
    }
    @Test public void test32() {
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
    @Test public void test33(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
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
public void test36() {
    // Set up
    String key = null;
    
    // Assertion
    assertFalse(hasAttr(key));
}
@Test
public void test37() {
    // Set up
    String key = "";
    
    // Assertion
    assertFalse(hasAttr(key));
}
@Test
public void test38() {
    // Set up
    String key = "non_existing_key";
    
    // Assertion
    assertFalse(hasAttr(key));
}
@Test
public void test39() {
    // Set up
    String key = "existing_key";
    addAttr(key, "value");
    
    // Assertion
    assertTrue(hasAttr(key));
}
@Test
void removeAttr_shouldRemoveAttributeAndReturnNode() {
    // Test with a non-existing attribute key
    Node node = new Node("div");
    node.attr("id", "test");

    Node result = node.removeAttr("class");

    assertEquals(node, result);
    assertNull(node.attr("class"));
}
@Test
void removeAttr_shouldRemoveAttributeAndReturnNullIfAttributeDoesNotExist() {
    // Test with an existing attribute key
    Node node = new Node("span");
    node.attr("style", "color: red");

    Node result = node.removeAttr("class");

    assertNull(result);
    assertNull(node.attr("class"));
}
@Test
void removeAttr_shouldRemoveAttributeAndReturnNodeIfAttributesExist() {
    // Test with multiple existing attribute keys
    Node node = new Node("img");
    node.attr("src", "image.jpg");
    node.attr("alt", "image");

    Node result = node.removeAttr("src");

    assertEquals(node, result);
    assertNull(node.attr("src"));
    assertEquals("image", node.attr("alt"));
}
@Test
public void test40() {
    String key = "testKey";
    String expectedUrl = "https://www.example.com/";

    // Set up the attributes
    ...
    
    // Change the key to a different value
    String actualUrl = absUrl(key);

    assertEquals(expectedUrl, actualUrl);
}
@Test
public void test41() {
    String key = null;
    String expectedUrl = "https://www.example.com/";

    // Set up the attributes
    ...
    
    // Change the key to null
    String actualUrl = absUrl(key);

    assertEquals(expectedUrl, actualUrl);
}
    @Test public void test42() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test43() {
        Document doc = Jsoup.parse("<div>This is a longer sentence</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(9);
        tail.wrap("<b></b>");

        assertEquals("This is a <b>longer sentence</b>", TextUtil.stripNewlines(div.html()));
    }
@Test
public void test44() {
    doSetBaseUri("");
}
@Test
public void test45() {
    doSetBaseUri(null);
}
@Test
public void test46() {
    doSetBaseUri("a".repeat(100000));
}
    @Test public void test47() {
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
    @Test public void test48() {
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
    @Test public void test49() {
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
    @Test public void test50(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test51(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test52(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test53() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test54() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test55() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
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
        TextNode tail = tn.splitText(6);
        assertEquals("Hello ", tn.getWholeText());
        assertEquals("there", tail.getWholeText());
        tail.text("there!");
        assertEquals("Hello there!", div.text());
        assertTrue(tn.parent() == tail.parent());
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
    @Test public void test59(){
        Document doc = Jsoup.parse(new String(Character.toChars(135361)));
        TextNode t = doc.body().textNodes().get(0);
        assertEquals(new String(Character.toChars(135361)), t.outerHtml().trim());
    }
    @Test public void test60() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("Hello <b>there</b>", TextUtil.stripNewlines(div.html())); // not great that we get \n<b>there there... must correct
    }
    @Test public void test61() {
        Document doc = Jsoup.parse("<div>Hello there</div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.childNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test62() {
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
    @Test public void test63() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        List<Node> nodes = tn.ensureChildNodes();
        assertEquals(0, nodes.size());
    }
    @Test public void test64() {
        Document doc = Jsoup.parse("<p></p>");
        Element p = doc.select("p").first();

        TextNode tn = (TextNode) p.childNode(0);
        assertEquals("", tn.text());
        
        tn.text("Hello");
        assertEquals("Hello", tn.text());
        assertEquals("<p>Hello</p>", TextUtil.stripNewlines(p.outerHtml()));
    }
    @Test public void test65() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        tail.wrap("<b></b>");

        assertEquals("", TextUtil.stripNewlines(div.html()));
    }
    @Test public void test66() {
        Document doc = Jsoup.parse("<div></div>");
        Element div = doc.select("div").first();
        TextNode tn = (TextNode) div.childNode(0);
        TextNode tail = tn.splitText(6);
        assertEquals("", tn.getWholeText());
        assertEquals("", tail.getWholeText());
        tail.text("there!");
        assertEquals("", div.text());
        assertTrue(tn.parent() == tail.parent());
    }
}