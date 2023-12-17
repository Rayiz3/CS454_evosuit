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

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar", data.get(0).toString());
    }
    @Test 
    public void test1() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar><input name=baz value=qux>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar", data.get(0).toString());
        assertEquals("baz=qux", data.get(1).toString());
    }
    @Test 
    public void test2() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=''><input name=baz value=qux>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=", data.get(0).toString());
        assertEquals("baz=qux", data.get(1).toString());
    }
    @Test 
    public void test3() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test 
    public void test4() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/submit'><input name='q' value='search'><input name='foo' value='bar'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());
    }
    @Test 
    public void test5() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/login'><input name='username' value='user'><input name='password' value='pass'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(2, form.elements().size());
        
        doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/signup'><input name='email' value='test@test.com'><input name='password' value='pass'>");
        formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        form = (FormElement) formEl;
        assertEquals(2, form.elements().size());
    }
    @Test 
    public void test6() {
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
            "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test 
    public void test7() {
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
            "<select id=7><option></select><textarea id=8><p id=9>" +
            "<input id=10><textarea id=11><option></option></select>";

        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(11, form.elements().size());
    }
    @Test 
    public void test8() {
        // Test case 1: add an input element to the form
        String html1 = "<form><input name='email' value=''></form>";
        Document doc1 = Jsoup.parse(html1);
        FormElement form1 = (FormElement) doc1.select("form").first();
        Element input1 = doc1.selectFirst("input[name=email]");
        form1.addElement(input1);

        assertTrue(form1.elements().contains(input1));

        // Test case 2: add a textarea element to the form
        String html2 = "<form><textarea name='comment'></textarea></form>";
        Document doc2 = Jsoup.parse(html2);
        FormElement form2 = (FormElement) doc2.select("form").first();
        Element textarea2 = doc2.selectFirst("textarea[name=comment]");
        form2.addElement(textarea2);

        assertTrue(form2.elements().contains(textarea2));

        // Test case 3: add a select element to the form
        String html3 = "<form><select name='country'></select></form>";
        Document doc3 = Jsoup.parse(html3);
        FormElement form3 = (FormElement) doc3.select("form").first();
        Element select3 = doc3.selectFirst("select[name=country]");
        form3.addElement(select3);

        assertTrue(form3.elements().contains(select3));
    }
    @Test public void test9() {
        // Remove one form element
        String html1 = "<html>\n" +
                "  <body> \n" +
                "      <form action=\"/hello.php\" method=\"post\">\n" +
                "      User:<input type=\"text\" name=\"user\" />\n" +
                "      Password:<input type=\"password\" name=\"pass\" />\n" +
                "      <input type=\"submit\" name=\"login\" value=\"login\" />\n" +
                "   </form>\n" +
                "  </body>\n" +
                "</html>  ";
        Document doc1 = Jsoup.parse(html1);
        FormElement form1 = (FormElement) doc1.selectFirst("form");
        Element pass1 = form1.selectFirst("input[name=pass]");
        pass1.remove();

        List<Connection.KeyVal> data1 = form1.formData();
        assertEquals(2, data1.size());
        assertEquals("user", data1.get(0).key());
        assertEquals("login", data1.get(1).key());
        assertEquals(null, doc1.selectFirst("input[name=pass]"));


        // Remove multiple form elements
        String html2 = "<html>\n" +
                "  <body> \n" +
                "      <form action=\"/hello.php\" method=\"post\">\n" +
                "      User:<input type=\"text\" name=\"user\" />\n" +
                "      Password:<input type=\"password\" name=\"pass\" />\n" +
                "      Email:<input type=\"email\" name=\"email\" />\n" +
                "      <input type=\"submit\" name=\"login\" value=\"login\" />\n" +
                "   </form>\n" +
                "  </body>\n" +
                "</html>  ";
        Document doc2 = Jsoup.parse(html2);
        FormElement form2 = (FormElement) doc2.selectFirst("form");
        Element pass2 = form2.selectFirst("input[name=pass]");
        Element email2 = form2.selectFirst("input[name=email]");
        pass2.remove();
        email2.remove();

        List<Connection.KeyVal> data2 = form2.formData();
        assertEquals(2, data2.size());
        assertEquals("user", data2.get(0).key());
        assertEquals("login", data2.get(1).key());
        assertEquals(null, doc2.selectFirst("input[name=pass]"));
        assertEquals(null, doc2.selectFirst("input[name=email]"));
    }
    @Test public void test10() {
        // Add a form element
        Document doc1 = Jsoup.parse("<body />");
        doc1.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl1 = doc1.select("form").first();
        assertTrue(formEl1 instanceof FormElement);

        FormElement form1 = (FormElement) formEl1;
        assertEquals(1, form1.elements().size());


        // Add multiple form elements
        Document doc2 = Jsoup.parse("<body />");
        doc2.body().html("<form action='http://example.com/login'><input name='user'><input name='pass'><input name='login'>");
        Element formEl2 = doc2.select("form").first();
        assertTrue(formEl2 instanceof FormElement);

        FormElement form2 = (FormElement) formEl2;
        assertEquals(3, form2.elements().size());
    }
    @Test public void test11() {
        // Add a form control
        Document doc1 = Jsoup.parse("<body />");
        doc1.body().html("<form />");

        Element formEl1 = doc1.select("form").first();
        formEl1.append("<input name=foo value=bar>");

        assertTrue(formEl1 instanceof FormElement);
        FormElement form1 = (FormElement) formEl1;
        assertEquals(1, form1.elements().size());

        List<Connection.KeyVal> data1 = form1.formData();
        assertEquals("foo=bar", data1.get(0).toString());


        // Add multiple form controls
        Document doc2 = Jsoup.parse("<body />");
        doc2.body().html("<form />");

        Element formEl2 = doc2.select("form").first();
        formEl2.append("<input name=email value='test@example.com'><input name=password value='password'>");

        assertTrue(formEl2 instanceof FormElement);
        FormElement form2 = (FormElement) formEl2;
        assertEquals(2, form2.elements().size());

        List<Connection.KeyVal> data2 = form2.formData();
        assertEquals("email=test@example.com", data2.get(0).toString());
        assertEquals("password=password", data2.get(1).toString());
    }
    @Test public void test12() {
        String html = "<form><input name='q' value='testing'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test public void test13() {
        String html = "<form><input name='q' value='testing'></form>";
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
    @Test public void test14() {
        String html = "<form action='/search'><input name='q' value='jsoup'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "testing");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=testing", dataList.get(0).toString());

        doc.select("form").attr("method", "post");
        Connection con2 = form.submit();
        assertEquals(Connection.Method.POST, con2.request().method());
    }
    @Test public void test15() { // original test case, do not change
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("on", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test16() { 
        Document doc = Jsoup.parse("<form><input type=checkbox name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("off", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test17() { 
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo value=></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test18() { 
        Document doc = Jsoup.parse("<form><input type=checkbox name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals(0, data.size());
    }
    @Test public void test19() { 
        Document doc = Jsoup.parse("<form><select name=foo><option value=1><option value=2></select></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("1", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test20() { 
        Document doc = Jsoup.parse("<form><select name=foo><option value=1><option value=2 selected></select></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("2", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
}