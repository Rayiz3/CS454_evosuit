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
        formEl.append("<input name=baz value=qux>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("baz=qux", data.get(0).toString());
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
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(9, form.elements().size());
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
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());
    }
    @Test public void test6() {
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("on", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test7() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test public void test8() {
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
    @Test public void test9() {
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
    @Test public void test10() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test public void test11() {
        String html = "<form><input name='one' value='two'><select name='three'><option value='not'>" +
                "option value='four' selected><option value='five' selected><textarea name=six>seven</textarea>" +
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
    @Test public void test12() {
        String html = "<form><input name=test value=foo name=test2 value=bar>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        assertEquals("test=foo", form.formData().get(0).toString());
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
    @Test public void test14() {
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test public void test15() {
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
    @Test public void test16() {
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
    @Test public void test17() {
        String html = "<form></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        Connection con = form.submit();
        assertNull(con.request().url());
    }
    @Test public void test18() {
        String html = "<form action='/search'></form><form action='/login'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = (FormElement) doc.selectFirst("form");
        Connection con = form.submit();
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        
        form = (FormElement) doc.select("form").get(1);
        con = form.submit();
        assertEquals("http://example.com/login", con.request().url().toExternalForm());
    }
    @Test public void test19() {
        String html = "<form action='/submit'><textarea name='message'></textarea></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        form.select("textarea").first().text("Hello world");
        
        Connection con = form.submit();
        
        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/submit?message=Hello%20world", con.request().url().toExternalForm());
    }
    @Test public void test20() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test public void test21() {
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
    @Test public void test22() {
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
    @Test public void test23() {
        String html = "<html>\n" +
                "  <body> \n" +
                "      <form action=\"/hello.php\" method=\"post\">\n" +
                "      User:<input type=\"text\" name=\"user\" />\n" +
                "      Password:<input type=\"password\" name=\"pass\" />\n" +
                "      <input type=\"checkbox\" name=\"rememberMe\" value=\"1\">\n" +
                "      <input type=\"submit\" name=\"login\" value=\"login\" />\n" +
                "   </form>\n" +
                "  </body>\n" +
                "</html>  ";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        Element pass = form.selectFirst("input[name=pass]");
        Element rememberMe = form.selectFirst("input[name=rememberMe]");
        pass.remove();
        rememberMe.remove();

        List<Connection.KeyVal> data = form.formData();
        assertEquals(2, data.size());
        assertEquals("user", data.get(0).key());
        assertEquals("login", data.get(1).key());
        assertEquals(null, doc.selectFirst("input[name=pass]"));
        assertEquals(null, doc.selectFirst("input[name=rememberMe]"));
    }
    @Test public void test24() {
        String html = "<html>\n" +
                "  <body> \n" +
                "      <form action=\"/hello.php\" method=\"post\">\n" +
                "   </form>\n" +
                "  </body>\n" +
                "</html>  ";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.selectFirst("form");
        form.remove();

        assertEquals(null, doc.selectFirst("form"));
    }
    @Test public void test25() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name='foo' value='bar'><input name='baz' value='qux'><input name='quux' value='corge'>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(3, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar", data.get(0).toString());
        assertEquals("baz=qux", data.get(1).toString());
        assertEquals("quux=corge", data.get(2).toString());
    }
    @Test public void test26() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test public void test27() {
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
    @Test public void test28() {
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
    @Test public void test29() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example1.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example1.com/", con.request().url().toExternalForm());
    }
    @Test public void test30() {
        String html = "<form action=''><input name='q'></form>";
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
    @Test public void test31() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, null);
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
    @Test public void test32() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "testing");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=testing", dataList.get(0).toString());

        doc.select("[name=q]").attr("value", "jsoup");
        Connection con2 = form.submit();
        assertEquals(Connection.Method.GET, con2.request().method());
        assertEquals("http://example.com/search", con2.request().url().toExternalForm());
        List<Connection.KeyVal> dataList2 = (List<Connection.KeyVal>) con2.request().data();
        assertEquals("q=jsoup", dataList2.get(0).toString());
    }
@Test
public void test33() {
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
@Test
public void test34() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<form />");

    Element formEl = doc.select("form").first();
    formEl.append("<input name=a>");
    formEl.append("<input name=b>");

    assertTrue(formEl instanceof FormElement);
    FormElement form = (FormElement) formEl;
    assertEquals(2, form.elements().size());

    List<Connection.KeyVal> data = form.formData();
    assertEquals("a=", data.get(0).toString());
    assertEquals("b=", data.get(1).toString());
}
@Test
public void test35() {
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
    assertEquals("user=", data.get(0).toString());
    assertEquals("pass=", data.get(1).toString());
    assertEquals("login=", data.get(2).toString());
}
@Test
public void test36() {
    Document doc = Jsoup.parse("<form><input type=checkbox name=foo></form>");
    FormElement form = (FormElement) doc.select("form").first();
    List<Connection.KeyVal> data = form.formData();
    assertEquals("on", data.get(0).value());
    assertEquals("foo=", data.get(0).toString());
}
@Test
public void test37() {
    String html = "<form><input name='q'></form>";
    Document doc = Jsoup.parse(html, "http://example.com/");
    FormElement form = ((FormElement) doc.select("form").first());
    Connection con = form.submit();

    assertEquals("http://example.com/", con.request().url().toExternalForm());
}
@Test
public void test38() {
    String html = "<form><input name=test value=foo name=test2 value=bar>";
    Document doc = Jsoup.parse(html);
    FormElement form = (FormElement) doc.selectFirst("form");
    assertEquals("test=foo", form.formData().get(0).toString());
}
@Test 
public void test39() {
    String html = "<form><input name='one' value='two'><select name='three'><option value='not'>" +
            "<option value='four' selected><option value='five' selected><textarea name=six>seven</textarea>" +
            "<input name='seven' type='radio' checked><input name='seven' type='radio'>" +
            "<input name='eight' type='checkbox' checked><input name='nine' type='checkbox' value='error'>" +
            "<input name='ten' value='text' disabled>" +
            "<input name='eleven' value='text' type='button'>" +
            "</form>";
    Document doc = Jsoup.parse(html);
    FormElement form = (FormElement) doc.select("form").first();
    List<Connection.KeyVal> data = form.formData();

    assertEquals(4, data.size());
    assertEquals("one=two", data.get(0).toString());
    assertEquals("three=four", data.get(1).toString());
    assertEquals("three=five", data.get(2).toString());
    assertEquals("six=seven", data.get(3).toString());
}
@Test
public void test40() {
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
    assertEquals("user=", data.get(0).toString());
    assertEquals("login=", data.get(1).toString());
    assertEquals(null, doc.selectFirst("input[name=pass]"));
}
}