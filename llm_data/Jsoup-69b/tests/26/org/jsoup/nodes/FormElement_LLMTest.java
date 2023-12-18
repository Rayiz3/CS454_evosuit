package org.jsoup.nodes;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class FormElement_LLMTest {
    @Test
    public void test0() {
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(7, form.elements().size());
    }
    @Test
    public void test1() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());
    }
    @Test
    public void test2() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar", data.get(0).toString());
    }
@Test
public void test3() {
    Element emptyElement = new Element("");
    FormElement formElement = new FormElement();
    formElement.addElement(emptyElement);

    List<Element> elements = formElement.getElements();
    assertFalse(elements.isEmpty());
    assertEquals(emptyElement, elements.get(0));
}
@Test
public void test4() {
    Element nonEmptyElement = new Element("input");
    FormElement formElement = new FormElement();
    formElement.addElement(nonEmptyElement);

    List<Element> elements = formElement.getElements();
    assertFalse(elements.isEmpty());
    assertEquals(nonEmptyElement, elements.get(0));
}
@Test
public void test5() {
    Element element1 = new Element("input");
    Element element2 = new Element("textarea");
    Element element3 = new Element("select");

    FormElement formElement = new FormElement();
    formElement.addElement(element1);
    formElement.addElement(element2);
    formElement.addElement(element3);

    List<Element> elements = formElement.getElements();
    assertEquals(3, elements.size());
    assertEquals(element1, elements.get(0));
    assertEquals(element2, elements.get(1));
    assertEquals(element3, elements.get(2));
}
@Test
public void test6() {
    Element nullElement = null;
    FormElement formElement = new FormElement();
    formElement.addElement(nullElement);

    List<Element> elements = formElement.getElements();
    assertTrue(elements.isEmpty());
}
    @Test public void test7() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "jsoup");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=jsoup", dataList.get(0).toString());

        // Regression test case: empty value of input name
        html = "<form action='/search'><input name=''></form>";
        doc = Jsoup.parse(html, "http://example.com/");
        form = (FormElement) doc.select("form").first();
        Connection con2 = form.submit();
        assertEquals(Connection.Method.GET, con2.request().method());
        assertEquals("http://example.com/search", con2.request().url().toExternalForm());
        dataList = (List<Connection.KeyVal>) con2.request().data();
        assertEquals("", dataList.get(0).toString());

        // Regression test case: different method
        doc.select("form").attr("method", "post");
        Connection con3 = form.submit();
        assertEquals(Connection.Method.POST, con3.request().method());
    }
    @Test public void test8() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = ((FormElement) doc.select("form").first());

        boolean threw = false;
        try {
            Connection con = form.submit();

            // Regression test case: empty form action without base URI
            doc = Jsoup.parse(html, "http://example.com/");
            form = ((FormElement) doc.select("form").first());
            con = form.submit();
        } catch (IllegalArgumentException e) {
            threw = true;
            assertEquals("Could not determine a form action URL for submit. Ensure you set a base URI when parsing.",
                    e.getMessage());
        }
        assertTrue(threw);
    }
    @Test public void test9() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());

        // Regression test case: empty action value
        html = "<form action=''><input name='q'></form>";
        doc = Jsoup.parse(html, "http://example.com/");
        form = ((FormElement) doc.select("form").first());
        Connection con2 = form.submit();
        assertEquals("http://example.com/", con2.request().url().toExternalForm());
    }
    @Test
    public void test10() {
        String html = "<form action='/search'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals(0, dataList.size());
    }
    @Test
    public void test11() {
        String html = "<html>\n" +
                "  <body> \n" +
                "      <form action=\"/hello.php\" method=\"post\">\n" +
                "      User:<input type=\"text\" name=\"user\" />\n" +
                "      Password:<input type=\"password\" name=\"pass\" disabled />\n" +
                "      <input type=\"submit\" name=\"login\" value=\"login\" />\n" +
                "   </form>\n" +
                "  </body>\n" +
                "</html>  ";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        Element pass = form.selectFirst("input[name=pass]");
        pass.remove();

        List<Connection.KeyVal> data = form.formData();
        assertEquals(1, data.size());
        assertEquals("user", data.get(0).key());
        assertEquals(null, doc.selectFirst("input[name=pass]"));
    }
    @Test
    public void test12() {
        String html = "<html>\n" +
                "<body>  \n" +
                "  <table>\n" +
                "      <form action=\"/hello.php\" method=\"post\">\n" +
                "      <tr><td>User:</td><td> <input type=\"text\" name=\"user\" /></td></tr>\n" +
                "      <tr><td><select name=\"gender\">\n" +
                "              <option value=\"Male\">Male</option>\n" +
                "              <option value=\"Female\">Female</option>\n" +
                "           </select></td></tr>" +
                "      <tr><td><input type=\"submit\" name=\"login\" value=\"login\" /></td></tr>\n" +
                "   </form>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals(2, data.size());
        assertEquals("user", data.get(0).key());
        assertEquals("gender", data.get(1).key());
        assertEquals("Male", data.get(1).value());
    }
    @Test
    public void test13() {
        String html = "<form><input name='one' type='checkbox' value='on'><input name='two' type='checkbox' value='off'></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(2, data.size());
        assertEquals("one=on", data.get(0).toString());
        assertEquals("two=off", data.get(1).toString());
    }
    @Test
    public void test14() {
        String html = "<form><input name='one' type='checkbox' value='on' checked disabled><input name='two' type='checkbox' value='off' checked disabled></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test
    public void test15() {
        String html = "<form><input name='one' type='radio' value='yes' checked disabled><input name='one' type='radio' value='no' disabled></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
}