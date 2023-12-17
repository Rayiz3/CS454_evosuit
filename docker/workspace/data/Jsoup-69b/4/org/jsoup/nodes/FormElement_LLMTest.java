package org.jsoup.nodes;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class FormElement_LLMTest {
    @Test public void test0() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test public void test1() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test public void test2() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar", data.get(0).toString());
    }
    @Test public void test3() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertFalse(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());
    }
    @Test public void test4() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(4, form.elements().size());
    }
    @Test public void test5() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar", data.get(1).toString());
    }

    @Test public void test6() {
        // Regression test for actionWithNoValue, changing the input value
        String html1 = "<form><input name='q'></form>";
        Document doc1 = Jsoup.parse(html1, "http://example.com/");
        FormElement form1 = ((FormElement) doc1.select("form").first());
        Connection con1 = form1.submit();

        assertEquals("http://example.com/", con1.request().url().toExternalForm());

        // Regression test for createsSubmitableConnection, changing the input value
        String html2 = "<form action='/search'><input name='q'></form>";
        Document doc2 = Jsoup.parse(html2, "http://example.com/");
        doc2.select("[name=q]").attr("value", "jsoup-regression");

        FormElement form2 = ((FormElement) doc2.select("form").first());
        Connection con2 = form2.submit();

        assertEquals(Connection.Method.GET, con2.request().method());
        assertEquals("http://example.com/search", con2.request().url().toExternalForm());
        List<Connection.KeyVal> dataList2 = (List<Connection.KeyVal>) con2.request().data();
        assertEquals("q=jsoup-regression", dataList2.get(0).toString());

        doc2.select("form").attr("method", "post");
        Connection con3 = form2.submit();
        assertEquals(Connection.Method.POST, con3.request().method());

        // Regression test for actionWithNoBaseUri, changing the input value
        String html3 = "<form><input name='q'></form>";
        Document doc3 = Jsoup.parse(html3);
        FormElement form3 = ((FormElement) doc3.select("form").first());


        boolean threw = false;
        try {
            Connection con4 = form3.submit();
        } catch (IllegalArgumentException e) {
            threw = true;
            assertEquals("Could not determine a form action URL for submit. Ensure you set a base URI when parsing.",
                    e.getMessage());
        }
        assertTrue(threw);
    }

@Test public void test7() {
    String html = "<html>\n" +
            "  <body> \n" +
            "      <form action=\"/hello.php\" method=\"post\">\n" +
            "      User:<input type=\"text\" name=\"user\" />\n" +
            "      Password:<input type=\"password\" name=\"pass\" />\n" +
            "      <input type=\"submit\" name=\"login\" value=\"login\" />\n" +
            "   </form>\n" +
            "  </body>\n" +
            "</html>  ";
    Document doc = Jsoup.parse(html);
    FormElement form = (FormElement) doc.selectFirst("form");
    Element pass = form.selectFirst("input[name=pass]");
    pass.remove();

    List<Connection.KeyVal> data = form.formData();
    assertEquals(2, data.size());
    assertEquals("user", data.get(0).key());
    assertEquals("login", data.get(1).key());
    assertEquals(null, doc.selectFirst("input[name=pass]"));
}

@Test public void test8() {
    String html = "<form action='/search'><input name='q'></form>";
    Document doc = Jsoup.parse(html, "http://example.com/");
    doc.select("[name=q]").attr("value", "jsoup");

    FormElement form = ((FormElement) doc.select("form").first());
    Connection con = form.submit();

    assertEquals(Connection.Method.GET, con.request().method());
    assertEquals("http://example.com/search", con.request().url().toExternalForm());
    List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
    assertEquals("q=jsoup", dataList.get(0).toString());

    doc.select("form").attr("method", "post");
    Connection con2 = form.submit();
    assertEquals(Connection.Method.POST, con2.request().method());
}

@Test public void test9() {
    String html = "<form><input name='q'></form>";
    Document doc = Jsoup.parse(html, "http://example.com/");
    FormElement form = ((FormElement) doc.select("form").first());
    Connection con = form.submit();

    assertEquals("http://example.com/", con.request().url().toExternalForm());
}

@Test public void test10() {
    String html = "<form><input name='one' value='two'><select name='three'><option value='not'>" +
            "<option value='four' selected><option value='five' selected><textarea name=six>seven</textarea>" +
            "<input name='seven' type='radio' value='on' checked><input name='seven' type='radio' value='off'>" +
            "<input name='eight' type='checkbox' checked><input name='nine' type='checkbox' value='unset'>" +
            "<input name='ten' value='text' disabled>" +
            "</form>";
    Document doc = Jsoup.parse(html);
    FormElement form = (FormElement) doc.select("form").first();
    List<Connection.KeyVal> data = form.formData();

    assertEquals(6, data.size());
    assertEquals("one=two", data.get(0).toString());
    assertEquals("three=four", data.get(1).toString());
    assertEquals("three=five", data.get(2).toString());
    assertEquals("six=seven", data.get(3).toString());
    assertEquals("seven=on", data.get(4).toString()); // set
    assertEquals("eight=on", data.get(5).toString()); // default
    // nine should not appear, not checked checkbox
    // ten should not appear, disabled
}

@Test public void test11() {
    // test for https://github.com/jhy/jsoup/issues/249
    String html = "<html>\n" +
            "<body>  \n" +
            "  <table>\n" +
            "      <form action=\"/hello.php\" method=\"post\">\n" +
            "      <tr><td>User:</td><td> <input type=\"text\" name=\"user\" /></td></tr>\n" +
            "      <tr><td>Password:</td><td> <input type=\"password\" name=\"pass\" /></td></tr>\n" +
            "      <tr><td><input type=\"submit\" name=\"login\" value=\"login\" /></td></tr>\n" +
            "   </form>\n" +
            "  </table>\n" +
            "</body>\n" +
            "</html>";
    Document doc = Jsoup.parse(html);
    FormElement form = (FormElement) doc.select("form").first();
    List<Connection.KeyVal> data = form.formData();
    assertEquals(3, data.size());
    assertEquals("user", data.get(0).key());
    assertEquals("pass", data.get(1).key());
    assertEquals("login", data.get(2).key());
}

@Test public void test12() {
    Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
    FormElement form = (FormElement) doc.select("form").first();
    List<Connection.KeyVal> data = form.formData();
    assertEquals("on", data.get(0).value());
    assertEquals("foo", data.get(0).key());
}

@Test public void test13() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<form />");

    Element formEl = doc.select("form").first();
    formEl.append("<input name=foo value=bar>");

    assertTrue(formEl instanceof FormElement);
    FormElement form = (FormElement) formEl;
    assertEquals(1, form.elements().size());

    List<Connection.KeyVal> data = form.formData();
    assertEquals("foo=bar", data.get(0).toString());
}

}