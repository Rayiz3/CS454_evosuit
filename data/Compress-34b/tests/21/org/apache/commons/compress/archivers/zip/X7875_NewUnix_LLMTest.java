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
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
    }
    @Test public void test1() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
        assertEquals("search", form.elements().get(0).val());
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
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();
        formEl.append("<input name=foo value=bar>");

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals("foo=bar", data.get(0).toString());
        assertEquals("bar", form.elements().get(0).val());
    }
    @Test public void test4() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test public void test5() {
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
        assertEquals("button", form.elements().get(0).tagName());
        assertEquals("fieldset", form.elements().get(1).tagName());
        assertEquals("input", form.elements().get(2).tagName());
        assertEquals("keygen", form.elements().get(3).tagName());
        assertEquals("object", form.elements().get(4).tagName());
        assertEquals("output", form.elements().get(5).tagName());
        assertEquals("select", form.elements().get(6).tagName());
        assertEquals("textarea", form.elements().get(7).tagName());
    }
    @Test public void test6() {
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
    @Test public void test7() {
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
    @Test public void test8() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'><input name='q' value='search'>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(1, form.elements().size());
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
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals("http://example.com/", con.request().url().toExternalForm());
    }
    @Test public void test11() {
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
    @Test public void test12() {
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
        //"button", "fieldset", "input", "keygen", "object", "output", "select", "textarea"
        String html = "<form id=1><button id=1><fieldset id=2 /><input id=3><keygen id=4><object id=5><output id=6>" +
                "<select id=7><option></select><textarea id=8><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(8, form.elements().size());
    }
    @Test public void test15() {
        Document doc = Jsoup.parse("<form><input type=checkbox checked name=foo></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("on", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    @Test public void test16() {
        String html = "<html>\n" +
                "<body>  \n" +
                "  <table>\n" +
                "      <form action=\"/hello.php\" method=\"post\">\n" +
                "      </form>\n" +
                "  </table>\n" +
                "</body>\n" +
                "</html>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals(0, data.size());
    }
    @Test public void test17() {
        String html = "<form></form>";
        Document doc = Jsoup.parse(html);
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();

        assertEquals(0, data.size());
    }
    @Test public void test18() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form action='http://example.com/search'></form>");
        Element formEl = doc.select("form").first();
        assertTrue(formEl instanceof FormElement);

        FormElement form = (FormElement) formEl;
        assertEquals(0, form.elements().size());
    }
    @Test public void test19() {
        String html = "<form><input name='q'></form>";
        Document doc = Jsoup.parse(html, "http://example.com/");
        doc.select("[name=q]").attr("value", "jsoup");

        FormElement form = ((FormElement) doc.select("form").first());
        Connection con = form.submit();

        assertEquals(Connection.Method.GET, con.request().method());
        assertEquals("http://example.com/http://example.com/", con.request().url().toExternalForm());
        List<Connection.KeyVal> dataList = (List<Connection.KeyVal>) con.request().data();
        assertEquals("q=jsoup", dataList.get(0).toString());

        doc.select("form").attr("method", "post");
        Connection con2 = form.submit();
        assertEquals(Connection.Method.POST, con2.request().method());
    }
    @Test public void test20() {
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
        Element pass = form.selectFirst("input[name=password]");
        pass.remove();

        List<Connection.KeyVal> data = form.formData();
        assertEquals(3, data.size());
        assertEquals("user", data.get(0).key());
        assertEquals("pass", data.get(1).key());
        assertEquals("login", data.get(2).key());
        assertEquals(null, doc.selectFirst("input[name=password]"));
    }
    @Test public void test21() {
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
    @Test public void test22() {
        Document doc = Jsoup.parse("<body />");
        doc.body().html("<form />");

        Element formEl = doc.select("form").first();

        assertTrue(formEl instanceof FormElement);
        FormElement form = (FormElement) formEl;
        assertEquals(0, form.elements().size());

        List<Connection.KeyVal> data = form.formData();
        assertEquals(0, data.size());
    }
    @Test public void test23() {
        String html = "<form id=1><option></select><p id=9>";
        Document doc = Jsoup.parse(html);

        FormElement form = (FormElement) doc.select("form").first();
        assertEquals(2, form.elements().size());
    }
    @Test public void test24() {
        Document doc = Jsoup.parse("<form><input type=checkbox name=foo checked></form>");
        FormElement form = (FormElement) doc.select("form").first();
        List<Connection.KeyVal> data = form.formData();
        assertEquals("on", data.get(0).value());
        assertEquals("foo", data.get(0).key());
    }
    public void test25() throws Exception {
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
    public void test26() throws ZipException {

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
        parseReparse(1000, 1000, ONE_THOUSAND_UID_GID, 1000, 1000);
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
    public void test27() {
        xf = new X7875_NewUnix();
        xf.setUID(0);
        xf.setGID(0);

        byte[] expectedResult = {1,1, 0,0, 0,0};

        assertTrue(Arrays.equals(expectedResult, xf.getLocalFileDataData()));
    }
    @Test
    public void test28() {
        xf = new X7875_NewUnix();
        xf.setUID(100);
        xf.setGID(100);

        byte[] expectedResult = {1,1, 100, 1, 100};

        assertTrue(Arrays.equals(expectedResult, xf.getLocalFileDataData()));
    }
    @Test
    public void test29() {
        xf = new X7875_NewUnix();
        xf.setUID(1000);
        xf.setGID(1000);

        byte[] expectedResult = {1,2, -24, 3, -24, 3};

        assertTrue(Arrays.equals(expectedResult, xf.getLocalFileDataData()));
    }
    @Test
    public void test30() {
        xf = new X7875_NewUnix();
        xf.setUID(Long.MAX_VALUE);
        xf.setGID(Long.MAX_VALUE);

        byte[] expectedResult = {1,8, -1,-1,-1,-1,-1,-1,-1,-1, -1,-1,-1,-1,-1,-1,-1,-1};

        assertTrue(Arrays.equals(expectedResult, xf.getLocalFileDataData()));
    }
public void test31() {
    // Create a non-empty byte array
    byte[] expected = {1, 2, 3, 4, 5};
    
    // Set the byte array as the central directory data
    YourClass yourClass = new YourClass();
    yourClass.setCentralDirectoryData(expected);
    
    // Call the method and check if the returned byte array is the same as the expected byte array
    byte[] actual = yourClass.getCentralDirectoryData();
    assertArrayEquals(expected, actual);
}
public void test32() {
    // Create an empty byte array
    byte[] expected = new byte[0];
    
    // Set the byte array as the central directory data
    YourClass yourClass = new YourClass();
    yourClass.setCentralDirectoryData(expected);
    
    // Call the method and check if the returned byte array is the same as the expected byte array
    byte[] actual = yourClass.getCentralDirectoryData();
    assertArrayEquals(expected, actual);
}
public void test33() {
    // Set the central directory data as null
    YourClass yourClass = new YourClass();
    yourClass.setCentralDirectoryData(null);
    
    // Call the method and check if the returned byte array is null
    byte[] actual = yourClass.getCentralDirectoryData();
    assertNull(actual);
}
    @Test
    public void test34() throws ZipException {
        byte[] data = {0, 2, -24, 3, 2, -24, 3};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test35() throws ZipException {
        byte[] data = {1, 0, 1, 1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test36() throws ZipException {
        byte[] data = {1, -3, 1, 1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test37() throws ZipException {
        byte[] data = {1, 1, 0, 0, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test38() throws ZipException {
        byte[] data = {1, 1, -1, -2, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test39() throws ZipException {
        byte[] data = {1, 1, -3, -4, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test40() throws ZipException {
        byte[] data = {1, 1, -1, 1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test41() throws ZipException {
        byte[] data = {1, 1, 1, -1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test42() throws ZipException {
        byte[] data = {1, 1, -1, -1, -1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test43() throws ZipException {
        byte[] data = {-1, 1, 1, 1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test44() throws ZipException {
        byte[] data = {1, 65, 1, 1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test45() throws ZipException {
        byte[] data = {1, 1, 1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test
    public void test46() throws ZipException {
        byte[] data = {1, 1, 1};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test(expected = ZipException.class)
    public void test47() throws ZipException {
        byte[] data = {};
        zipClass.parseFromLocalFileData(data,0,data.length);
    }
    @Test(expected = ZipException.class)
    public void test48() throws ZipException {
        byte[] data = null;
        zipClass.parseFromLocalFileData(data,0,0);
    }
    @Test
    public void test49() throws Exception {
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
                if (expected != xf.getUID()) {
                    expected = xf.getUID() - 1;
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
    public void test50() throws Exception {
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
                if (expected != xf.getUID()) {
                    expected = xf.getUID() - 1;
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
                if (expected != xf.getUID()) {
                    expected = xf.getUID() - 1;
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
    public void test52() {
        uid = 555;
        gid = 555;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test53() {
        uid = 5555;
        gid = 5555;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test54() {
        uid = 55555;
        gid = 55555;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test55() {
        uid = 555555;
        gid = 555555;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test56() {
        uid = 0;
        gid = 0;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test57() {
        uid = 4294967294L;
        gid = 4294967294L;
        reset();
        assertEquals(1000, uid);
        assertEquals(1000, gid);
    }
    @Test
    public void test58() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test59() throws Exception {
        xf.setUID(56789);
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test60() throws Exception {
        xf.setGID(9876);
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test61() throws Exception {
        xf.setUID(56789);
        xf.setGID(9876);
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test62() {
        assertThrows(CloneNotSupportedException.class, () -> {
            Object o = xf.clone();
        });
    }
    @Test
    public void test63() {
        assertFalse(xf.equals(null));
    }
    @Test
    public void test64() {
        Object o = xf.clone();
        assertEquals(0, o.hashCode());
    }
    @Test
    public void test65() {
        Object o = xf.clone();
        ((ExtraField) o).setUID(54321);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test66() {
        Object o = xf.clone();
        ((ExtraField) o).setUID(null);
        assertFalse(xf.equals(o));
    }
    @Test
    public void test67() throws Exception {
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
            
            // Regression test case 1: Change uid and gid to null
            long expected = -1;
            X7875_NewUnix xf1 = new X7875_NewUnix(2, null, null);
            assertEquals(expected, xf1.getUID());
            assertEquals(expected, xf1.getGID());
            
            // Regression test case 2: Change version to 0
            boolean result = xf.equals(new X7875_NewUnix(0, xf.getUID(), xf.getGID()));
            assertFalse(result);
            
            // Regression test case 3: Change version to -1
            result = xf.equals(new X7875_NewUnix(-1, xf.getUID(), xf.getGID()));
            assertFalse(result);
            
            // Regression test case 4: Change uid to a negative number
            result = xf.equals(new X7875_NewUnix(xf.getVersion(), -1, xf.getGID()));
            assertFalse(result);
            
            // Regression test case 5: Change gid to a negative number
            result = xf.equals(new X7875_NewUnix(xf.getVersion(), xf.getUID(), -1));
            assertFalse(result);
        } finally {
            if (zf != null) {
                zf.close();
            }
        }
    }
    @Test
    public void test68() {
        assertEquals(X7875, xf.getHeaderId());
        
        // Regression test case 6: Change headerId to a different value
        int expectedHeaderId = 12345;
        X7875_NewUnix xf1 = new X7875_NewUnix(expectedHeaderId, xf.getUID(), xf.getGID());
        assertEquals(expectedHeaderId, xf1.getHeaderId());
    }
    @Test
    public void test69() throws Exception {
        assertFalse(xf.equals(new Object()));
        assertTrue(xf.toString().startsWith("0x7875 Zip Extra Field"));
        Object o = xf.clone();
        assertEquals(o.hashCode(), xf.hashCode());
        assertTrue(xf.equals(o));
        xf.setUID(12345);
        assertFalse(xf.equals(o));
        
        // Regression test case 7: Change the cloned object
        o = xf.clone();
        X7875_NewUnix xf1 = (X7875_NewUnix) o;
        xf1.setGID(54321);
        assertFalse(xf.equals(xf1));
    }
}

    @Test
    public void test70() throws Exception {
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
    public void test71() throws Exception {
    }
    
    // Additional regression tests
    
    @Test
    public void test72() throws Exception {
    }
    
    @Test
    public void test73() throws Exception {
    }
    
    @Test
    public void test74() throws Exception {
    }
    
    @Test
    public void test75() throws Exception {
    }

    @Test
    public void test76() {
    }

}