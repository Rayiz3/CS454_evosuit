package org.jsoup.nodes;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class FormElement_LLMTest {
    @Test public void test0() {
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
    @Test public void test1() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");
        
        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=newValue>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=newValue", data.get(0).toString());
    }
    @Test public void test2() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test public void test3() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=10>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test public void test4() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test public void test5() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='newValue'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test
    public void test6() {
        Document doc = Jsoup.parse("<form></form>");
        FormElement form = (FormElement) doc.select("form").first();
        Element input = doc.createElement("input");
        FormElement returnedForm = form.addElement(input);
        assertEquals(form, returnedForm);
        assertEquals(1, form.elements().size());
    }
    @Test
    public void test7() {
        Document doc = Jsoup.parse("<form></form>");
        FormElement form = (FormElement) doc.select("form").first();
        FormElement returnedForm = form.addElement(null);
        assertEquals(form, returnedForm);
        assertEquals(0, form.elements().size());
    }
    @Test
    public void test8() {
        Document doc = Jsoup.parse("<form></form>");
        FormElement form = (FormElement) doc.select("form").first();
        Element input1 = doc.createElement("input");
        Element input2 = doc.createElement("input");
        FormElement returnedForm1 = form.addElement(input1);
        FormElement returnedForm2 = form.addElement(input2);
        assertEquals(form, returnedForm2);
        assertEquals(2, form.elements().size());
        assertEquals(input1, form.elements().get(0));
        assertEquals(input2, form.elements().get(1));
    }
    @Test
    public void test9() {
        Document doc = Jsoup.parse("<form></form>");
        FormElement form = (FormElement) doc.select("form").first();
        Element input = doc.createElement("input");
        Element textarea = doc.createElement("textarea");
        FormElement returnedForm1 = form.addElement(input);
        FormElement returnedForm2 = form.addElement(textarea);
        assertEquals(form, returnedForm2);
        assertEquals(2, form.elements().size());
        assertEquals(input, form.elements().get(0));
        assertEquals(textarea, form.elements().get(1));
    }
@Test
public void test10() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
    Element formEl = doc.select("form").first();
    
    assertTrue(formEl instanceof FormElement);

    FormElement form = (FormElement) formEl;
    assertEquals(1, form.elements().size());
    
    doc.removeChild(formEl);
    assertEquals(0, form.elements().size());
}
@Test
public void test11() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<div><input name='q' value='search'></div>");
    Element divEl = doc.select("div").first();
    
    assertFalse(divEl instanceof FormElement);

    FormElement form = (FormElement) divEl;
    assertEquals(0, form.elements().size());
    
    doc.removeChild(divEl);
    assertEquals(0, form.elements().size());
}
@Test
public void test12() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<form action='http://example.com/search'><input name='q' value='search'><div><input name='q' value='search'></div>");
    Element formEl = doc.select("form").first();

    assertTrue(formEl instanceof FormElement);

    FormElement form = (FormElement) formEl;
    assertEquals(2, form.elements().size());

    doc.removeChild(formEl);
    assertEquals(1, form.elements().size());
}
@Test
public void test13() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
    Element formEl = doc.select("form").first();

    assertTrue(formEl instanceof FormElement);

    FormElement form = (FormElement) formEl;
    assertEquals(1, form.elements().size());

    Element anotherEl = new Element(Tag.valueOf("div"), "");
    doc.body().appendChild(anotherEl);

    doc.removeChild(anotherEl);
    assertEquals(1, form.elements().size());    
}
    @Test public void test14() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test public void test15() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = ((FormElement) doc.select("form").first());


        boolean threw = false;
        try {
            Connection con = form.submit();
        } catch (IllegalArgumentException e) {
            threw = true;
            assertEquals("Could not determine a form action URL for submit. Ensure you set a base URI when parsing.",
                    e.getMessage());
        }
        assertTrue(threw);
    }
    @Test public void test16() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=", dataList.get(0).toString());

        doc.select("form").attr("method", "post");
        Connection con2 = form.submit();
        assertEquals(Connection.Method.POST, con2.request().method());
    }
    @Test public void test17() {
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
    @Test public void test18() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "test");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=test", dataList.get(0).toString());

        doc.select("form").attr("method", "post");
        Connection con2 = form.submit();
        assertEquals(Connection.Method.POST, con2.request().method());
    }
    @Test public void test19() {
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
    @Test public void test20() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar2>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar2", data.get(0).toString());
    }
    @Test public void test21() {
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
    @Test public void test22() {
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
        form.selectFirst("input[name=user]").attr("value", "test");
        form.selectFirst("input[name=pass]").attr("value", "password");
        List<Connection.KeyVal> data = form.formData();
        assertEquals(3, data.size());
        assertEquals("user=test", data.get(0).toString());
        assertEquals("pass=password", data.get(1).toString());
        assertEquals("login", data.get(2).key());
    }
    @Test public void test23() {
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("on", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        form.selectFirst("input[name=foo]").attr("value", "test");
        List<Connection.KeyVal> data = form.formData();
        assertEquals("on", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test25() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test public void test26() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/search", con.request().url().toExternalForm());
    }
    @Test public void test27() {
        String html = "<form><input name=test value=foo name=test2 value=bar>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        assertEquals("test=foo", form.formData().get(0).toString());
    }
    @Test public void test28() {
        String html = "<form><input name=test value=foo name=test2 value=bar2>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        assertEquals("test=foo", form.formData().get(0).toString());
    }
    @Test public void test29() {
        String html = "<form><input name='one' value='two'><select name='three'><option value='not'>" +
                "<option value='four' selected><option value='five' selected><textarea name=six>seven</textarea>" +
                "<input name='seven' type='radio' value='on' checked><input name='seven' type='radio' value='off'>" +
                "<input name='eight' type='checkbox' checked><input name='nine' type='checkbox' value='unset'>" +
                "<input name='ten' value='text' disabled>" +
                "<input name='eleven' value='text' type='button'>" +
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
        // eleven should not appear, button
    }
    @Test public void test30() {
        String html = "<form><input name='one' value='two'><select name='three'><option value='not'>" +
                "<option value='four2' selected><option value='five2' selected><textarea name=six>seven8</textarea>" +
                "<input name='seven' type='radio' value='on2' checked><input name='seven' type='radio' value='off2'>" +
                "<input name='eight' type='checkbox' checked><input name='nine' type='checkbox' value='unset2'>" +
                "<input name='ten' value='text2' disabled>" +
                "<input name='eleven' value='text2' type='button'>" +
                "</form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(6, data.size());
        assertEquals("one=two", data.get(0).toString());
        assertEquals("three=four2", data.get(1).toString());
        assertEquals("three=five2", data.get(2).toString());
        assertEquals("six=seven8", data.get(3).toString());
        assertEquals("seven=on2", data.get(4).toString()); // set
        assertEquals("eight=on", data.get(5).toString()); // default
        // nine should not appear, not checked checkbox
        // ten should not appear, disabled
        // eleven should not appear, button
    }
    @Test public void test31() {
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
    @Test public void test32() {
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
        form.selectFirst("input[name=user]").attr("value", "test");
        Element pass = form.selectFirst("input[name=pass]");
        pass.remove();

        List<Connection.KeyVal> data = form.formData();
        assertEquals(2, data.size());
        assertEquals("user=test", data.get(0).toString());
        assertEquals("login", data.get(1).key());
        assertEquals(null, doc.selectFirst("input[name=pass]"));
    }
}