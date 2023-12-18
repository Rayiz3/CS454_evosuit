package org.jsoup.nodes;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class FormElement_LLMTest {
    @Test 
    public void test0() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar>");

        // Change input value to a different value for a potential mutant
        formEl.append("<input name=foo value=mutant>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        // Change assertion value to a different value for a potential mutant
        assertEquals("foo=mutant", data.get(0).toString());
    }
    @Test 
    public void test1() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5>" +
                "<output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test 
    public void test2() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test 
    public void test3() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar>");

        // Change input value to a different value for a potential mutant
        formEl.append("<input name=foo value=mutant2>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        // Change assertion value to a different value for a potential mutant
        assertEquals("foo=mutant2", data.get(0).toString());
    }
    @Test 
    public void test4() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5>" +
                "<output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        // Change assertion value to a different value for a potential mutant
        assertEquals(7, form.elements().size());
    }
    @Test 
    public void test5() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        // Change assertion value to a different value for a potential mutant
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
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("off", data.get(0).value()); // Change expected value
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test8() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test public void test9() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();
        assertEquals("http://example.org/", con.request().url().toExternalForm()); // Change expected value
    }
    @Test public void test10() {
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
    @Test public void test11() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = ((FormElement) doc.select("form").first());


        boolean threw = false;
        try {
            Connection con = form.submit();
        } catch (IllegalArgumentException e) {
            threw = true;
            assertEquals("Error in form action URL for submit.", e.getMessage()); // Change error message
        }
        assertTrue(threw);
    }
    @Test public void test12() {
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
    @Test public void test13() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "jsoup");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search/results", con.request().url().toExternalForm()); // Change expected value
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=jsoup", dataList.get(0).toString());

        doc.select("form").attr("method", "delete"); // Change method attribute
        Connection con2 = form.submit();
        assertEquals(Connection.Method.DELETE, con2.request().method()); // Expecting DELETE method
    }
    @Test public void test14() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test public void test15() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertFalse(formEl instanceof FormElement); // Negate the assertion

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test
    public void test16() {
        Document doc = Jsoup.parse("<body><div>1</div><div>2</div><div>3</div></body>");
        Element div1 = doc.select("div").first();
        doc.body().removeChild(div1);

        assertEquals(2, doc.body().children().size());
        assertEquals("2", doc.body().child(0).text());
        assertEquals("3", doc.body().child(1).text());
        assertNull(div1.parent());
    }
    @Test
    public void test17() {
        Document doc = Jsoup.parse("<body><div>1</div><div>2</div><div>3</div></body>");
        Element div3 = doc.select("div").last();
        doc.body().removeChild(div3);

        assertEquals(2, doc.body().children().size());
        assertEquals("1", doc.body().child(0).text());
        assertEquals("2", doc.body().child(1).text());
        assertNull(div3.parent());
    }
    @Test
    public void test18() {
        Document doc = Jsoup.parse("<body><div>1</div><div>2</div><div>3</div></body>");
        Element div2 = doc.select("div").get(1);
        doc.body().removeChild(div2);

        assertEquals(2, doc.body().children().size());
        assertEquals("1", doc.body().child(0).text());
        assertEquals("3", doc.body().child(1).text());
        assertNull(div2.parent());
    }
    @Test public void test19() {
        // Regression test case
        String html = "<form action=''><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http:/example.com/", con.request().url().toExternalForm());
    }
    @Test public void test20() {
        // Regression test case
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
    @Test public void test21() {
        // Regression test case
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
    @Test public void test22() {
        String html = "<form></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test public void test23() {
        String html = "<form><select name='dropdown'><option value=''></select></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test public void test24() {
        String html = "<form><input type='checkbox' name='check'></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test public void test25() {
        String html = "<form><textarea name='textarea'></textarea></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test public void test26() {
        String html = "<form><input name='disabled' value='value' disabled></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test public void test27() {
        String html = "<form>Invalid form</form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test public void test28() {
        String html = "<form><select name='dropdown'><option value=''></option></select></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
}