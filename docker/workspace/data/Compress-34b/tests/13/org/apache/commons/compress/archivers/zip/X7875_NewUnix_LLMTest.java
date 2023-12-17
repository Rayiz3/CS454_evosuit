package org.apache.commons.compress.archivers.zip;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.ZipException;
import static org.apache.commons.compress.AbstractTestCase.getFile;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class X7875_NewUnix_LLMTest {
    @Test public void test0() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=baz>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=baz", data.get(0).toString());
    }
    @Test public void test1() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=qux>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=qux", data.get(0).toString());
    }
    @Test public void test2() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='query'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test public void test3() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
@Test
public void test4() {
    String html = "<form id=1></form>";
    Document doc = Jsoup.parse(html);

    FormElement form = (FormElement) doc.select("form").first();
    assertEquals(0, form.elements().size());
}
@Test
public void test5() {
    String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
            "<select id=7><option></select><textarea id=8><p id=9></form>";
    Document doc = Jsoup.parse(html);

    FormElement form = (FormElement) doc.select("form").first();
    form.remove();
    assertEquals(0, form.elements().size());
}
@Test
public void test6() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<form />");

    Element formEl = doc.select("form").first();
    formEl.append("<button name=submit value=Submit>Submit</button>");

    assertTrue(formEl instanceof FormElement);
    FormElement form = (FormElement) formEl;
    assertEquals(1, form.elements().size());

    List<Connection.KeyVal> data = form.formData();
    assertEquals("submit=Submit", data.get(0).toString());
}
@Test
public void test7() {
    String html = "<form><input name='q'></form>";
    Document doc = Jsoup.parse(html, "http://example.com/");
    FormElement form = ((FormElement) doc.select("form").first());
    
    form.attr("action", "/search");
    Connection con = form.submit();

    assertEquals("http://example.com/search", con.request().url().toExternalForm());
}
@Test
public void test8() {
    Document doc = Jsoup.parse("<form><input type=checkbox name=foo></form>");
    FormElement form = (FormElement) doc.select("form").first();
    List<Connection.KeyVal> data = form.formData();
    assertEquals("", data.get(0).value());
    assertEquals("foo", data.get(0).key());
}
@Test
public void test9() {
    Document doc = Jsoup.parse("<body />");
    doc.body().html("<div>Some content</div><input name='test' value='123' /><p>More content</p>");
    Element formEl = doc.append("<form />").select("form").first();
    assertTrue(formEl instanceof FormElement);

    FormElement form = (FormElement) formEl;
    assertEquals(0, form.elements().size());
}
@Test
public void test10() {
    String html = "<form></form>";
    Document doc = Jsoup.parse(html);
    FormElement form = (FormElement) doc.select("form").first();
    List<Connection.KeyVal> data = form.formData();

    assertEquals(0, data.size());
}
@Test
public void test11() {
    String html = "<form><input name='q'></form>";
    Document doc = Jsoup.parse(html);
    FormElement form = ((FormElement) doc.select("form").first());

    doc.setBaseUri("http://example.com/");
    Connection con = form.submit();

    assertEquals("http://example.com/", con.request().url().toExternalForm());
}
@Test
public void test12() {
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
    form.append("<input name='test' value='123' />");

    List<Connection.KeyVal> data = form.formData();
    assertEquals(4, data.size());
    assertEquals("user", data.get(0).key());
    assertEquals("pass", data.get(1).key());
    assertEquals("login", data.get(2).key());
    assertEquals("test=123", data.get(3).toString());
}
@Test
public void test13() {
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
    @Test
    public void test14() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test
    public void test15() {
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
    @Test
    public void test16() {
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
    @Test
    public void test17() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("form").attr("action", null);
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test
    public void test18() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/search", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertNotEquals("q=jsoup", dataList.get(0).toString());

        doc.select("form").attr("method", "post");
        Connection con2 = form.submit();
        assertEquals(Connection.Method.POST, con2.request().method());
    }
    @Test
    public void test19() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "");
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
    @Test public void test20() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=baz>"); // Change: Updated the value from "bar" to "baz"

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=baz", data.get(0).toString()); // Change: Updated expected value from "foo=bar" to "foo=baz"
    }
    @Test public void test21() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/search", con.request().url().toExternalForm()); // Change: Updated the expected value from "http://example.com/" to "http://example.com/search"
    }
    @Test public void test22() {
        String html = "<form><input name='one' value='three'><select name='three'><option value='not'>" +
                "<option value='four' selected><option value='five' selected><textarea name=six>eight</textarea>" +
                "<input name='seven' type='radio' value='on' checked><input name='seven' type='radio' value='off'>" +
                "<input name='eight' type='checkbox' checked><input name='nine' type='checkbox' value='unset'>" +
                "<input name='ten' value='new text'>" +
                "</form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(6, data.size());
        assertEquals("one=three", data.get(0).toString());
        assertEquals("three=four", data.get(1).toString());
        assertEquals("three=five", data.get(2).toString());
        assertEquals("six=eight", data.get(3).toString());
        assertEquals("seven=on", data.get(4).toString()); // set
        assertEquals("eight=on", data.get(5).toString()); // default
        // nine should not appear, not checked checkbox
        assertEquals("ten=new text", data.get(6).toString()); // Change: Added a new assertion for the new input field
    }
    @Test public void test23() {
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("on", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test24() {
        String html = "<form action='/search'><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "jsoup");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/", con.request().url().toExternalForm()); // Change: Updated the expected value from "http://example.com/search" to "http://example.com/"
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=jsoup", dataList.get(0).toString());
    }
    @Test public void test25() {
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
    @Test public void test26() {
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
    public void test27() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected - 1, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test28() throws ZipException {

        // Version=1, Len=0, Len=0.
        final byte[] ZERO_LEN = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero.
        final byte[] ZERO_UID_GID = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one
        final byte[] ONE_UID_GID = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand
        final byte[] ONE_THOUSAND_UID_GID = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning.
        final byte[] UNIX_MAX_UID_GID = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5 = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8 = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID, 0, 0);
        parseReparse(1, 1, ONE_UID_GID, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID - 1, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));
    }
@Test
public void test29() {
    byte[] data = new byte[10];
    byte[] result = getCentralDirectoryData(data);
    assertEquals(0, result.length);
}
@Test
public void test30() {
    byte[] data = null;
    byte[] result = getCentralDirectoryData(data);
    assertEquals(0, result.length);
}
@Test
public void test31() {
    byte[] data = new byte[5];
    byte[] result = getCentralDirectoryData(data);
    assertEquals(0, result.length);
}
    @Test
    public void test32() throws ZipException {
        byte[] data = {1, 1, 2, 1, 2};
        parseFromLocalFileData(data, 0, data.length);
        assertEquals(2, xf.getUID());
        assertEquals(2, xf.getGID());
    }
    @Test
    public void test33() throws ZipException {
        byte[] data = {1, 1, 0, 1, 0};
        parseFromLocalFileData(data, 0, data.length);
        assertEquals(0, xf.getUID());
        assertEquals(0, xf.getGID());
    }
    @Test
    public void test34() throws ZipException {
        byte[] data = {1, 2, -24, 3, 2, -24, 3};
        parseFromLocalFileData(data, 0, data.length);
        assertEquals(1000, xf.getUID());
        assertEquals(1000, xf.getGID());
    }
    @Test
    public void test35() throws ZipException {
        byte[] data = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};
        parseFromLocalFileData(data, 0, data.length);
        assertEquals(4294967294L, xf.getUID());
        assertEquals(4294967294L, xf.getGID());
    }
@Test
public void test36() throws Exception {
    byte[] buffer = new byte[0];
    int offset = 0;
    int length = 0;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test37() throws Exception {
    byte[] buffer = new byte[10];
    int offset = -1;
    int length = 10;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test38() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = 0;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test39() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 0;
    int length = 20;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test40() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 15;
    int length = 5;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) {
        // Expected exception
    }
}
@Test
public void test41() throws Exception {
    byte[] buffer = new byte[0];
    int offset = 3;
    int length = 5;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test42() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 3;
    int length = 0;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test43() throws Exception {
    byte[] buffer = new byte[10];
    int offset = 3;
    int length = 15;

    try {
        parseFromCentralDirectoryData(buffer, offset, length);
        fail("Expected ZipException");
    } catch (ZipException e) {
        // Expected exception
    }
}
@Test
public void test44() {
    uid = 555;
    gid = 555;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
@Test
public void test45() {
    uid = -1000;
    gid = -1000;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
@Test
public void test46() {
    uid = 1000000;
    gid = 1000000;
    reset();
    assertEquals(1000, uid);
    assertEquals(1000, gid);
}
    @Test
    public void test47() throws Exception {
        xf.setUID(0);
        xf.setGID(0);
        assertEquals("0x7875 Zip Extra Field: UID=0 GID=0", xf.toString());

        xf.setUID(123);
        xf.setGID(456);
        assertEquals("0x7875 Zip Extra Field: UID=123 GID=456", xf.toString());
    }
    @Test
    public void test48() throws Exception {
        ZipExtraField other = new ZipExtraField();
        assertFalse(xf.equals(other));

        xf.setUID(0);
        xf.setGID(0);
        other.setUID(0);
        other.setGID(0);
        assertTrue(xf.equals(other));

        other.setUID(123);
        assertFalse(xf.equals(other));

        other.setUID(0);
        other.setGID(456);
        assertFalse(xf.equals(other));
    }
    @Test
    public void test49() throws Exception {
        ZipExtraField clone = (ZipExtraField) xf.clone();
        assertEquals(clone.hashCode(), xf.hashCode());
        assertTrue(xf.equals(clone));

        xf.setUID(0);
        xf.setGID(0);
        clone = (ZipExtraField) xf.clone();
        assertEquals(clone.hashCode(), xf.hashCode());
        assertTrue(xf.equals(clone));

        xf.setUID(123);
        clone.setUID(456);
        assertFalse(xf.equals(clone));
    }
    @Test
    public void test50() throws Exception {
        assertFalse(xf.clone().equals(new Object())); // Mutant: changing clone() to new Object()
        assertTrue(xf.clone().toString().startsWith("0x7875 Zip Extra Field")); // Mutant: changing toString() to new Object()
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.clone().hashCode()); // Mutant: changing o.hashCode() to xf.clone().hashCode()
        assertTrue(xf.clone().equals(o)); // Mutant: changing xf.clone().equals(o) to true
        xf.setUID(12345);
        assertFalse(xf.clone().equals(o)); // Mutant: changing xf.clone().equals(o) to true
    }
    @Test
    public void test51() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test52() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = 1000;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                } else if (name.contains("invalid_uidgid")) {
                    expected = 123;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test53() {
        assertEquals(X7875, xf.getHeaderId());
    }
    @Test
    public void test54() {
        assertEquals(X7875, xf.getHeaderId());
        assertEquals(123, xf.getUID());
    }
    @Test
    public void test55() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test56() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        
        xf.setUID(123);
        assertTrue(xf.equals(o));
    }
    @Test
    public void test57() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = -1234567 * version;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(expected, xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test58() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(-1234567 * version, xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test59() throws Exception {
        File archive = getFile("COMPRESS-211_uid_gid_zip_test.zip");
        ZipFile zf = null;

        try {
            zf = new ZipFile(archive);
            Enumeration<ZipArchiveEntry> en = zf.getEntries();

            // We expect EVERY entry of this zip file (dir & file) to
            // contain extra field 0x7875.
            while (en.hasMoreElements()) {

                ZipArchiveEntry zae = en.nextElement();
                String name = zae.getName();
                X7875_NewUnix xf = (X7875_NewUnix) zae.getExtraField(X7875);

                // The directory entry in the test zip file is uid/gid 1000.
                long expected = -1234567 * version;
                if (name.contains("uid555_gid555")) {
                    expected = 555;
                } else if (name.contains("uid5555_gid5555")) {
                    expected = 5555;
                } else if (name.contains("uid55555_gid55555")) {
                    expected = 55555;
                } else if (name.contains("uid555555_gid555555")) {
                    expected = 555555;
                } else if (name.contains("min_unix")) {
                    expected = 0;
                } else if (name.contains("max_unix")) {
                    // 2^32-2 was the biggest UID/GID I could create on my linux!
                    // (December 2012, linux kernel 3.4)
                    expected = 0x100000000L - 2;
                }
                assertEquals(Integer.rotateLeft(uid.hashCode(), 16), xf.getUID());
                assertEquals(expected, xf.getGID());
            }
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test60() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(-1234567 * version, xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test61() throws ZipException {

        // Version=1, Len=0, Len=0 (unchanged)
        final byte[] ZERO_LEN_REGRESSION = {1, 0, 0};

        // Version=1, Len=1, zero, Len=1, zero (unchanged)
        final byte[] ZERO_UID_GID_REGRESSION = {1, 1, 0, 1, 0};

        // Version=1, Len=1, one, Len=1, one (unchanged)
        final byte[] ONE_UID_GID_REGRESSION = {1, 1, 1, 1, 1};

        // Version=1, Len=2, one thousand, Len=2, one thousand (unchanged)
        final byte[] ONE_THOUSAND_UID_GID_REGRESSION = {1, 2, -24, 3, 2, -24, 3};

        // (2^32 - 2).   I guess they avoid (2^32 - 1) since it's identical to -1 in
        // two's complement, and -1 often has a special meaning (unchanged)
        final byte[] UNIX_MAX_UID_GID_REGRESSION = {1, 4, -2, -1, -1, -1, 4, -2, -1, -1, -1};

        // Version=1, Len=5, 2^32, Len=5, 2^32 + 1 (unchanged)
        // Esoteric test:  can we handle 40 bit numbers?
        final byte[] LENGTH_5_REGRESSION = {1, 5, 0, 0, 0, 0, 1, 5, 1, 0, 0, 0, 1};

        // Version=1, Len=8, 2^63 - 2, Len=8, 2^63 - 1 (unchanged)
        // Esoteric test:  can we handle 64 bit numbers?
        final byte[] LENGTH_8_REGRESSION = {1, 8, -2, -1, -1, -1, -1, -1, -1, 127, 8, -1, -1, -1, -1, -1, -1, -1, 127};

        final long TWO_TO_32 = 0x100000000L;
        final long MAX = TWO_TO_32 - 2;

        parseReparse(0, 0, ZERO_LEN_REGRESSION, 0, 0);
        parseReparse(0, 0, ZERO_UID_GID_REGRESSION, 0, 0);
        parseReparse(1, 1, ONE_UID_GID_REGRESSION, 1, 1);
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID_REGRESSION, 1000, 1000);
        parseReparse(MAX, MAX, UNIX_MAX_UID_GID_REGRESSION, MAX, MAX);
        parseReparse(-2, -2, UNIX_MAX_UID_GID_REGRESSION, MAX, MAX);
        parseReparse(TWO_TO_32, TWO_TO_32 + 1, LENGTH_5_REGRESSION, TWO_TO_32, TWO_TO_32 + 1);
        parseReparse(Long.MAX_VALUE - 1, Long.MAX_VALUE, LENGTH_8_REGRESSION, Long.MAX_VALUE - 1, Long.MAX_VALUE);

        // We never emit this, but we should be able to parse it:
        final byte[] SPURIOUS_ZEROES_1 = {1, 4, -1, 0, 0, 0, 4, -128, 0, 0, 0};
        final byte[] EXPECTED_1 = {1, 1, -1, 1, -128};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_1, 0, SPURIOUS_ZEROES_1.length);

        assertEquals(255, xf.getUID());
        assertEquals(128, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_1, xf.getLocalFileDataData()));

        final byte[] SPURIOUS_ZEROES_2 = {1, 4, -1, -1, 0, 0, 4, 1, 2, 0, 0};
        final byte[] EXPECTED_2 = {1, 2, -1, -1, 2, 1, 2};
        xf.parseFromLocalFileData(SPURIOUS_ZEROES_2, 0, SPURIOUS_ZEROES_2.length);

        assertEquals(65535, xf.getUID());
        assertEquals(513, xf.getGID());
        assertTrue(Arrays.equals(EXPECTED_2, xf.getLocalFileDataData()));

        // New regression test cases
        final byte[] NEGATIVE_ONE_BYTE = {-1};
        parseReparse(255, 127, NEGATIVE_ONE_BYTE, -1, -1);

        final byte[] NEGATIVE_TWO_BYTES = {-1, -1};
        parseReparse(32767, 16383, NEGATIVE_TWO_BYTES, -1, -1);

        final byte[] NEGATIVE_THREE_BYTES = {-1, -1, -1};
        parseReparse(8388607, 4194303, NEGATIVE_THREE_BYTES, -1, -1);

        final byte[] NEGATIVE_FOUR_BYTES = {-1, -1, -1, -1};
        parseReparse(2147483647, 1073741823, NEGATIVE_FOUR_BYTES, -1, -1);

        final byte[] NEGATIVE_EIGHT_BYTES = {-1, -1, -1, -1, -1, -1, -1, -1};
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, NEGATIVE_EIGHT_BYTES, -1, -1);

        final byte[] MAX_UID_GID = {7, -1, -1, -1, -1, -1, -1, -1};
        parseReparse(Long.MAX_VALUE, Long.MAX_VALUE, MAX_UID_GID, Long.MAX_VALUE, Long.MAX_VALUE);

        final byte[] RANDOM_UID_GID = {-2, -1, -1, -1, -1, -1, -1, -1};
        parseReparse(-2, -2, RANDOM_UID_GID, -2, -2);
    }
}