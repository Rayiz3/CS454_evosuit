package org.apache.commons.csv;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExtendedBufferedReader_LLMTest {
    @Test
    public void test0() throws Exception {
        String code = "hello,\r\n\r\n\r\n\r\n\r\n";
        String[][] res = {
            {"hello", ""}  // CSV format ignores empty lines
        };

        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test1() throws Exception {
        String code = "hello,\r\n";
        String[][] res = {
            {"hello", ""}
        };

        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test2() throws Exception {
        String code = "hello,\r\n\r\n";
        String[][] res = {
            {"hello", ""}
        };

        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test3() throws Exception {
        String code = "\n";
        CSVParser parser = new CSVParser(code, CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test4() throws Exception {
        String code = "";
        CSVParser parser = new CSVParser(code, CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test5() throws IOException {
        String code = "foo\rbaar,\rhello,world\r,kanu\r";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test6() throws IOException {
        String code = "foo\rbaar,\nhello,world\r,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test7() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n\n\n\n\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test8() throws IOException {
        String code = "\nfoo,baar\n,world\r\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(2, records.size());
    }
    @Test
    public void test9() throws IOException {
        String code = "foo;baar\r\nhello;\r\n;\r\nworld;\r\n";
        String[][] res = {
            {"foo;baar"},
            {"hello", ""},
            {""},
            {"world", ""}
        };

        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true).withDelimiter(';'));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test10() throws Exception {
        Reader in = new StringReader("1,2,3\n4,5,6\n7,8,9");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader(1, 2, 3).parse(in).iterator();

        for (int i = 1; i <= 3; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get(String.valueOf(i)));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test11() throws Exception {
        Reader in = new StringReader("a,b,c\n,,");
        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in).iterator();

        assertTrue(records.hasNext());
        CSVRecord record = records.next();
        assertTrue(record.isConsistent());
        assertEquals("", record.get("a"));
        assertEquals("", record.get("b"));
        assertEquals("", record.get("c"));

        assertFalse(records.hasNext());
    }
    @Test
    public void test12() throws Exception {
        String code = "hello,\r\n\n\r\n\r\n";
        String[][] res = {
                {"hello", ""}  // CSV format ignores empty lines
        };
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test13() throws Exception {
        String code = ",\r\n\r\n\r\n";
        String[][] res = {
                {"", ""}  // CSV format ignores empty lines
        };
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test14() throws Exception {
        CSVParser parser = new CSVParser("\n", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test15() throws Exception {
        CSVParser parser = new CSVParser("\r", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test16() throws IOException {
        String code = "foo\rbaar,\rhello,world\r\r,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test17() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test18() throws IOException {
        String code = "hello;world,abc\n123;456,def";
        String[][] res = {
                {"hello", "world", "abc"},
                {"123", "456", "def"}
        };
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withDelimiter(';'));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test19() throws Exception {
        Reader in = new StringReader("a,b,c\n1,2\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader("A", "B", "C").parse(in).iterator();

        for (int i = 0; i < 2; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertNull(record.get(2));
            assertEquals(record.get(0), record.get("A"));
            assertEquals(record.get(1), record.get("B"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test20() throws Exception {
        String code = "foo,baar\r\n\r\nhello,\r\n\r\nworld,\r\n";
        String[][] res = {
                {"foo", "baar"},
                {""},
                {"hello", ""},
                {""},
                {"world", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.DEFAULT.withDelimiter(';'));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test21() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code = ""
                + " , , \n"           // 1)
                + " \t ,  , \n"       // 2)
                + " // , /, , /,\n"   // 3)
                + "";
        String[][] res = {
                {" ", " ", " "},         // 1
                {" \t ", "  ", " "},     // 2
                {" / ", " , ", " ,"},    // 3
        };


        CSVFormat format = new CSVFormat(',',  CSVFormat.DISABLED,  CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));
    }
    @Test
    public void test22() throws Exception {
        String code = "hello,\r\n\n\r\n\r\n";
        String[][] res = {
                {"hello", ""},
                {""}  // Excel format does not ignore empty lines
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test23() throws Exception {
        String code = ",\r\n\r\n\r\n";
        String[][] res = {
                {"", ""},
                {""}  // Excel format does not ignore empty lines
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test24() throws Exception {
        CSVParser parser = new CSVParser("a\rb\rc\r", CSVFormat.DEFAULT.withLineSeparator("\r"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test25() throws Exception {
        CSVParser parser = new CSVParser("a\r\nb\r\nc\r\n", CSVFormat.DEFAULT.withLineSeparator("\r\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test26() throws Exception {
        List<CSVRecord> records = new ArrayList<CSVRecord>();

        Reader in = new StringReader("");

        for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
            records.add(record);
        }

        assertEquals(0, records.size());
    }
    @Test
    public void test27() throws Exception {
        String code = "hello,\r\n\r\nworld,\r\n\n";
        String[][] res = {
                {"hello", ""},
                {""},
                {"world", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test28() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc\n", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test29() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test30() throws IOException {
        String code =
                "value1,value2,value3,value4\r\na,b,c,d\r\n  x,,,"
                        + "\r\n\r\n\"\"\"hello\"\"\",\"  \"\"world\"\"\",\"abc\ndef\",\r\n";
        String[][] res = {
                {"value1", "value2", "value3", "value4"},
                {"a", "b", "c", "d"},
                {"  x", "", "", ""},
                {""},
                {"\"hello\"", "  \"world\"", "abc\ndef", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL.withDelimiter(';'));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test31() throws Exception {
        Reader in = new StringReader("");

        Iterator<CSVRecord> iterator = CSVFormat.DEFAULT.parse(in).iterator();

        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());

        try {
            iterator.remove();
            fail("expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
        }

        try {
            iterator.next();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            // expected
        }
    }
    @Test
    public void test32() throws IOException {
        String code = "";

        CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(CSVFormat.DISABLED, format.getCommentStart());

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(0, records.size());

        format = CSVFormat.DEFAULT.withCommentStart('#');
        parser = new CSVParser(code, format);
        records = parser.getRecords();
        assertEquals(0, records.size());
    }
    @Test
    public void test33() throws Exception {
        Reader in = new StringReader("a,b,c\n1\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in).iterator();

        assertTrue(records.hasNext());
        CSVRecord record = records.next();
        assertEquals(1, record.size());
        assertEquals(record.get(0), record.get("a"));

        assertTrue(records.hasNext());
        record = records.next();
        assertEquals(3, record.size());
        assertEquals(record.get(0), "x");

        assertFalse(records.hasNext());
    }
    @Test
    public void test34() throws Exception {
        String code = "hello,\r\n\r\nworld,\r\n\n";
        String[][] res = {
                {"hello", ""},  // CSV format ignores empty lines
                {""}
        };
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test35() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code = ""
                + " , , \n"           // 1)
                + " \t ,  , \n"       // 2)
                + " // , /, , /,\n"   // 3)
                + "";
        String[][] res = {
                {" ", " ", " "},         // 1
                {" \t ", "  ", " "},     // 2
                {" / ", " , ", " ,"},    // 3
        };


        CSVFormat format = new CSVFormat(',',  CSVFormat.DISABLED,  CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));
    }
    @Test
    public void test36() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        for (String[] re : res) {
            assertArrayEquals(re, parser.getRecord().values());
        }

        assertNull(parser.getRecord());
    }
    @Test
    public void test37() throws IOException {
        String code = "foo\nbaar,\nhello,world\n\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test38() throws Exception {
        String[] codes = {
                "hello,\n\n\r\n",
                "hello,\n\n",
                "hello,\n\n\"\r\n",
                "hello,\n\n\"\"\n"
        };
        String[][] res = {
                {"hello", ""}  // CSV format ignores empty lines
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code));
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test39() throws Exception {
        String[] codes = {
                "hello,\"\"\n",
                "hello,\"\",\"\r\n",
                "hello,\"\",\"\n\"\n",
                "hello,\"\",\",\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""}  // CSV format ignores empty lines
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code));
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test40() throws Exception {
        String[] codes = {
                "hello,\"\n\"\n",
                "hello,\",\n\"\n",
                "hello,\",\r\n\"\n",
                "hello,\"\r\n",
                "hello,\",\r\n\",\r\n"
        };
        String[][] res = {
                {"hello", ""}  // CSV format ignores empty lines
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code));
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test41() throws Exception {
        String[] codes = {
                "hello",
                "hello\n\n\n",
                "hello,\n\n\n\n\n",
                "hello,\n\n\n\n\"\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code));
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test42() throws Exception {
        CSVParser parser = new CSVParser("", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test43() throws Exception {
        CSVParser parser = new CSVParser("\n", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test44() throws IOException {
        String code = "foo\bbaar,\rhello,world\r,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test45() throws IOException {
        String code = "foo\r\b\bbaar,\rhello,\bworld\r,\22kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test46() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test47() throws IOException {
        String code = "foo;baar\r\n\r\nhello;\r\n\r\nworld;\r\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test48() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test49() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.TDF);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test50() throws Exception {
        Reader in = new StringReader("a,b,c\n1,2,3\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader("A", "B", "C").parse(in).iterator();

        for (int i = 0; i < 3; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get("A"));
            assertEquals(record.get(1), record.get("B"));
            assertEquals(record.get(2), record.get("C"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test51() throws Exception {
        Reader in = new StringReader("a,b,c\r\n1,2,3\r\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader("A", "B", "C").parse(in).iterator();

        for (int i = 0; i < 3; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get("A"));
            assertEquals(record.get(1), record.get("B"));
            assertEquals(record.get(2), record.get("C"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test52() throws Exception {
        String code = "foo,baar\r\n\r\nhello,\r\n\r\nworld,\r\n";
        String[][] res = {
                {"foo", "baar"},
                {""},
                {"hello", ""},
                {""},
                {"world", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test53() throws Exception {
        String code = "foo,baar\r\nhello,world\r\n";
        String[][] res = {
                {"foo", "baar"},
                {"hello", "world"}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test54() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code = ""
                + " , , \n"               // 1)
                + " \t ,  , \n"           // 2)
                + " // , /, , /,\n"       // 3)
                + "";
        String[][] res = {
                {" ", " ", " "},         // 1
                {" \t ", "  ", " "},     // 2
                {" / ", " , ", " ,"}     // 3
        };


        CSVFormat format = new CSVFormat(',', CSVFormat.EXCEL, CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));
    }
    @Test
    public void test55() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code = ""
                + " , , \n"           // 1)
                + " \t ,  , \n"       // 2)
                + " // , ¤, , //,ß, \n"   // 3)
                + "";
        String[][] res = {
                {" ", " ", " "},         // 1
                {" \t ", "  ", " "},     // 2
                {" // ", " ¤ ", " ", " //", "ß", " "}    // 3
        };


        CSVFormat format = new CSVFormat(',', CSVFormat.EXCEL, CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));
    }
    @Test
    public void test56() throws Exception {
        String[] codes = {
                "hello,\n\n\r\n",
                "hello,\n\n",
                "hello,\n\n\"\r\n",
                "hello,\n\n\"\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test57() throws Exception {
        String[] codes = {
                "hello,\"\"\n",
                "hello,\"\",\"\r\n",
                "hello,\"\",\"\n\"\n",
                "hello,\"\",\",\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test58() throws Exception {
        String[] codes = {
                "hello,\"\n\"\n",
                "hello,\",\n\"\n",
                "hello,\",\r\n\"\n",
                "hello,\"\r\n",
                "hello,\",\r\n\",\r\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test59() throws Exception {
        String[] codes = {
                "hello",
                "hello\n\n\n",
                "hello,\n\n\n\n\n",
                "hello,\n\n\n\n\"\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test60() throws Exception {
        CSVParser parser = new CSVParser("a\rb\rc", CSVFormat.DEFAULT.withLineSeparator("\r"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test61() throws Exception {
        CSVParser parser = new CSVParser("a\rb\rc", CSVFormat.DEFAULT.withLineSeparator("\r"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test62() throws Exception {
        CSVParser parser = new CSVParser("a\r\nb\r\nc", CSVFormat.DEFAULT.withLineSeparator("\r\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test63() throws Exception {
        CSVParser parser = new CSVParser("a\r\nb\r\nc", CSVFormat.DEFAULT.withLineSeparator("\r\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test64() throws Exception {
        List<CSVRecord> records = new ArrayList<CSVRecord>();

        Reader in = new StringReader("a,b,c\n1,2,3\nx,y,z");

        for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
            records.add(record);
        }

        assertEquals(3, records.size());
        assertArrayEquals(new String[]{"a", "b", "c"}, records.get(0).values());
        assertArrayEquals(new String[]{"1", "2", "3"}, records.get(1).values());
        assertArrayEquals(new String[]{"x", "y", "z"}, records.get(2).values());
    }
    @Test
    public void test65() throws Exception {
        List<CSVRecord> records = new ArrayList<CSVRecord>();

        Reader in = new StringReader("a,b,c\r\n1,2,3\r\nx,y,z");

        for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
            records.add(record);
        }

        assertEquals(3, records.size());
        assertArrayEquals(new String[]{"a", "b", "c"}, records.get(0).values());
        assertArrayEquals(new String[]{"1", "2", "3"}, records.get(1).values());
        assertArrayEquals(new String[]{"x", "y", "z"}, records.get(2).values());
    }
    @Test
    public void test66() throws Exception {
        String[] codes = {
                "hello,\n\n\r\n",
                "hello,\n\n",
                "hello,\n\n\"\r\n",
                "hello,\n\n\"\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };

        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test67() throws Exception {
        String[] codes = {
                "hello,\"\"\n",
                "hello,\"\",\"\r\n",
                "hello,\"\",\"\n\"\n",
                "hello,\"\",\",\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test68() throws Exception {
        String[] codes = {
                "hello,\"\n\"\n",
                "hello,\",\n\"\n",
                "hello,\",\r\n\"\n",
                "hello,\"\r\n",
                "hello,\",\r\n\",\r\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test69() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test70() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test71() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu\r";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test72() throws IOException {
        String code = "foo\r\b\bbaar,\rhello,\b\bworld\r,\22kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test73() throws IOException {
        String code =
                "value1,value2,value3,value4\r\na,b,c,d\r\n  x,,,"
                        + "\r\n\r\n\"\"\"hello\"\"\",\"  \"\"world\"\"\",\"abc\ndef\",\r\n";
        String[][] res = {
                {"value1", "value2", "value3", "value4"},
                {"a", "b", "c", "d"},
                {"  x", "", "", ""},
                {""},
                {"\"hello\"", "  \"world\"", "abc\ndef", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test74() throws IOException {
        String code =
                "value1,value2,value3,value4\r\na,b,c,d\r\n  x,,,"
                        + "\r\n\r\n\"\"\"hello\"\"\",\"  \"\"world\"\"\",\"abc\ndef\",\n";
        String[][] res = {
                {"value1", "value2", "value3", "value4"},
                {"a", "b", "c", "d"},
                {"  x", "", "", ""},
                {""},
                {"\"hello\"", "  \"world\"", "abc\ndef", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test75() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code =
                " , , \n"                               // 1)
                        + " \t ,  , \n"                           // 2)
                        + " // , /, , /,\n"                       // 3)
                        + "   \\\\ , \"hello \"\\\" \\
                        + "''\",\" abc \"\\de\" ,\\\"\n"           // 4)
                        + "\"\\\" \\\", \"\\\"\'\n"                 // 5)
                        + "\",\"\" \r\n\"\"\", \"\"\"\n"            // 6)
                        + "\"\\\" \\, \\,\"\\\", \"\\\", \"\\\"\n"; // 7)
        String[][] res = {
                {" ", " ", " "},                           // 1
                {" \t ", "  ", " "},                       // 2
                {" / ", " , ", " ,"},                       // 3
                {" \\\\ ", "\"hello \"\" \"\n''", " abc \"\\de\" ,\""},  // 4
                {"\"\" \r\n\"\"", "\""},                    // 5
                {"\" \\, \\,", "\"", "\""},                  // 6
                {"\" \\, ", " , \"\"", " "},                // 7
        };

        CSVFormat format = new CSVFormat(',', '\'', CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test76() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code =
                " , , \n"                               // 1)
                        + " \t ,  , \n"                           // 2)
                        + " // , /, , /,\n"                       // 3)
                        + "   \\\\ , \"hello \"\\\" \\
                        + "''\",\" abc \"\\de\" ,\\\"\n"           // 4)
                        + "\"\\\" \\\", \"\\\"\'\n"                 // 5)
                        + "\",\"\" \r\n\"\"\", \"\"\"\n"            // 6)
                        + "\"\\\" \\, \\,\"\\\", \"\\\", \"\\\"\n"; // 7)
        String[][] res = {
                {"  ", " ", " "},                           // 1
                {" \t ", "  ", " "},                       // 2
                {" // ", " / ", " /", " "},                       // 3
                {"   \\\\ ", "\"hello\"\" ", "''\"", " \" abc \"de\" ,"},
                {"\"", "\" \", \"'"},
                {"\", \"" + "\""},                            // 6
                {"\" \\, \\,", "\", \"", "\""},              // 7
        };

        CSVFormat format = new CSVFormat(',', '\'', CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test77() throws IOException {
        String code = ""
                + "a,b\n"            // 1)
                + "\"\n\",\" \"\n"   // 2)
                + "\"\",#\n"   // 2)
                ;
        String[][] res = {
                {"a", "b"},
                {"\n", " "},
                {"", "#"},
        };

        CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(CSVFormat.DISABLED, format.getCommentStart());

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));

        String[][] res_comments = {
                {"a", "b"},
                {"\n", " "},
                {""},
        };

        format = CSVFormat.DEFAULT.withCommentStart('#');
        parser = new CSVParser(code, format);
        records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test78() throws IOException {
        String code = ""
                + "a,b\n"            // 1)
                + "\"\n\",\" \"\n"   // 2)
                + "\"\",#\n"   // 2)
                + "\n,\n\n\n"   // 3)
                + ", ,\r\n"   // 4)
                + ", ,\n\n\n";  // 5)
        String[][] res = {
                {"a", "b"},
                {"\n", " "},
                {"", "#"},
                {""},
                {","},
                {","},
        };

        CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(CSVFormat.DISABLED, format.getCommentStart());

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));

        String[][] res_comments = {
                {"a", "b"},
                {"\n", " "},
                {""},
                {""},
                {""},
                {""},
        };

        format = CSVFormat.DEFAULT.withCommentStart('#');
        parser = new CSVParser(code, format);
        records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test79() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        for (String[] re : res) {
            assertArrayEquals(re, parser.getRecord().values());
        }

        assertNull(parser.getRecord());
    }
    @Test
    public void test80() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.TDF.withSurroundingSpacesIgnored(true));
        for (String[] re : res) {
            assertArrayEquals(re, parser.getRecord().values());
        }

        assertNull(parser.getRecord());
    }
    @Test
    public void test81() throws Exception {
        Reader in = new StringReader("a,b,c\n1,2,3\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in).iterator();

        for (int i = 0; i < 2; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get("a"));
            assertEquals(record.get(1), record.get("b"));
            assertEquals(record.get(2), record.get("c"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test82() throws Exception {
        Reader in = new StringReader("a,b,c\r\n1,2,3\r\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(in).iterator();

        for (int i = 0; i < 2; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get("a"));
            assertEquals(record.get(1), record.get("b"));
            assertEquals(record.get(2), record.get("c"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test83() throws Exception {
        Reader in = new StringReader("a,b,c\n1,2,3\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.parse(in).iterator();

        assertTrue(records.hasNext());
        try {
            records.remove();
            fail("expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
        }
        assertArrayEquals(new String[]{"a", "b", "c"}, records.next().values());
        assertArrayEquals(new String[]{"1", "2", "3"}, records.next().values());
        assertTrue(records.hasNext());
        assertTrue(records.hasNext());
        assertTrue(records.hasNext());
        assertArrayEquals(new String[]{"x", "y", "z"}, records.next().values());
        assertFalse(records.hasNext());

        try {
            records.next();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            // expected
        }
    }
    @Test
    public void test84() throws Exception {
        Reader in = new StringReader("a,b,c\r\n1,2,3\r\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.parse(in).iterator();

        assertTrue(records.hasNext());
        try {
            records.remove();
            fail("expected UnsupportedOperationException");
        } catch (UnsupportedOperationException expected) {
        }
        assertArrayEquals(new String[]{"a", "b", "c"}, records.next().values());
        assertArrayEquals(new String[]{"1", "2", "3"}, records.next().values());
        assertTrue(records.hasNext());
        assertTrue(records.hasNext());
        assertTrue(records.hasNext());
        assertArrayEquals(new String[]{"x", "y", "z"}, records.next().values());
        assertFalse(records.hasNext());

        try {
            records.next();
            fail("NoSuchElementException expected");
        } catch (NoSuchElementException e) {
            // expected
        }
    }
    @Test
    public void test85() throws Exception {
        Reader in = new StringReader("a,b,c\n1,2,3\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader("A", "B", "C").parse(in).iterator();

        for (int i = 0; i < 3; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get("A"));
            assertEquals(record.get(1), record.get("B"));
            assertEquals(record.get(2), record.get("C"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test86() throws Exception {
        Reader in = new StringReader("a,b,c\r\n1,2,3\r\nx,y,z");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader("A", "B", "C").parse(in).iterator();

        for (int i = 0; i < 3; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get("A"));
            assertEquals(record.get(1), record.get("B"));
            assertEquals(record.get(2), record.get("C"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test87() throws Exception {
        String code = "foo,baar\r\n\r\nhello,\r\n\r\nworld,\r\n";
        String[][] res = {
                {"foo", "baar"},
                {""},
                {"hello", ""},
                {""},
                {"world", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test88() throws Exception {
        String code = "foo\r\nbaar,\r\nhello,\bworld\r\n,\22kanu";
        String[][] res = {
                {"foo\nbaar", ""},
                {"hello", "world\r"},
                {"", "kanu"}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test89() throws IOException {
        String code = "\nfoo,baar\n,\n\n,world\n\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test90() throws IOException {
        String code = "foo;baar\n\nhello;\nworld;\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test91() throws Exception {
        CSVParser parser = new CSVParser("a\rb\rc", CSVFormat.DEFAULT.withLineSeparator("\r"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(3, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(3, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test92() throws Exception {
        CSVParser parser = new CSVParser("\r\na;","a;");

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test93() throws Exception {
        CSVParser parser = new CSVParser("a\r\nb\r\nc", CSVFormat.DEFAULT.withLineSeparator("\r\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(3, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(3, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test94() throws Exception {
        CSVParser parser = new CSVParser("\r\na;\";b\r\na;\",\";\n;\"\"\",\"\"\"\n", CSVFormat.DEFAULT.withLineSeparator("\r\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(4, parser.getLineNumber());
        assertFalse(parser.getRecord().isConsistent());
        assertEquals(4, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test95() throws Exception {
        List<CSVRecord> records = new ArrayList<CSVRecord>();

        Reader in = new StringReader("a,b,c\n1,2,3\nx,y,z");

        for (CSVRecord record : CSVFormat.EXCEL.parse(in)) {
            records.add(record);
        }

        assertEquals(3, records.size());
        assertArrayEquals(new String[]{"a", "b", "c"}, records.get(0).values());
        assertArrayEquals(new String[]{"1", "2", "3"}, records.get(1).values());
        assertArrayEquals(new String[]{"x", "y", "z"}, records.get(2).values());
    }
    @Test
    public void test96() throws Exception {
        List<CSVRecord> records = new ArrayList<CSVRecord>();

        Reader in = new StringReader("a,b,c\r\n1,2,3\r\nx,y,z");

        for (CSVRecord record : CSVFormat.EXCEL.parse(in)) {
            records.add(record);
        }

        assertEquals(3, records.size());
        assertArrayEquals(new String[]{"a", "b", "c"}, records.get(0).values());
        assertArrayEquals(new String[]{"1", "2", "3"}, records.get(1).values());
        assertArrayEquals(new String[]{"x", "y", "z"}, records.get(2).values());
    }
    @Test
    public void test97() throws Exception {
        String[] codes = {
                "hello,\n\n\r\n",
                "hello,\n\n",
                "hello,\n\n\"\r\n",
                "hello,\n\n\"\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };

        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test98() throws Exception {
        String[] codes = {
                "hello,\"\"\n",
                "hello,\"\",\"\r\n",
                "hello,\"\",\"\n\"\n",
                "hello,\"\",\",\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test99() throws Exception {
        String[] codes = {
                "hello,\"\n\"\n",
                "hello,\",\n\"\n",
                "hello,\",\r\n\"\n",
                "hello,\"\r\n",
                "hello,\",\r\n\",\r\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test100() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test101() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test102() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu\r";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test103() throws IOException {
        String code = "foo\r\b\bbaar,\rhello,\bworld\r,\22kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test104() throws IOException {
        String code =
                "value1,value2,value3,value4\r\na,b,c,d\r\n  x,,,"
                        + "\r\n\r\n\"\"\"hello\"\"\",\"  \"\"world\"\"\",\"abc\ndef\",\r\n";
        String[][] res = {
                {"value1", "value2", "value3", "value4"},
                {"a", "b", "c", "d"},
                {"  x", "", "", ""},
                {""},
                {"\"hello\"", "  \"world\"", "abc\ndef", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test105() throws IOException {
        String code =
                "value1,value2,value3,value4\r\na,b,c,d\r\n  x,,,"
                        + "\r\n\r\n\"\"\"hello\"\"\",\"  \"\"world\"\"\",\"abc\ndef\",\n";
        String[][] res = {
                {"value1", "value2", "value3", "value4"},
                {"a", "b", "c", "d"},
                {"  x", "", "", ""},
                {""},
                {"\"hello\"", "  \"world\"", "abc\ndef", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test106() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code =
                " , , \n"           // 1)
                        + " \t ,  , \n"       // 2)
                        + " // , /, , /,\n"   // 3)
                        + "";
        String[][] res = {
                {" ", " ", " "},         // 1
                {" \t ", "  ", " "},     // 2
                {" / ", " , ", " ,"}     // 3
        };


        CSVFormat format = new CSVFormat(',', CSVFormat.DEFAULT, CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));
    }
    @Test
    public void test107() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code =
                " , , \n"           // 1)
                        + " \t ,  , \n"       // 2)
                        + " // , ¤, , //,ß, \n"   // 3)
                        + "";
        String[][] res = {
                {" ", " ", " "},         // 1
                {" \t ", "  ", " "},     // 2
                {" // ", " ¤ ", " ", " //", "ß", " "}    // 3
        };


        CSVFormat format = new CSVFormat(',', CSVFormat.DEFAULT, CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));
    }
    @Test
    public void test108() throws Exception {
        String[] codes = {
                "hello,\n\n\r\n",
                "hello,\n\n",
                "hello,\n\n\"\r\n",
                "hello,\n\n\"\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };

        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test109() throws Exception {
        String[] codes = {
                "hello,\"\"\n",
                "hello,\"\",\"\r\n",
                "hello,\"\",\"\n\"\n",
                "hello,\"\",\",\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test110() throws Exception {
        String[] codes = {
                "hello,\"\n\"\n",
                "hello,\",\n\"\n",
                "hello,\",\r\n\"\n",
                "hello,\"\r\n",
                "hello,\",\r\n\",\r\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test111() throws Exception {
        String[] codes = {
                "hello",
                "hello\n\n\n",
                "hello,\n\n\n\n\n",
                "hello,\n\n\n\n\"\r\n\"\n"
        };
        String[][] res = {
                {"hello", ""}
        };
        for (String code : codes) {
            CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
            List<CSVRecord> records = parser.getRecords();
            assertEquals(res.length, records.size());
            assertTrue(records.size() > 0);
            for (int i = 0; i < res.length; i++) {
                assertArrayEquals(res[i], records.get(i).values());
            }
        }
    }
    @Test
    public void test112() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test113() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test114() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test115() throws IOException {
        String code = "foo\r\b\rbbaar,\rhello,\bworld\r,\22kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test116() throws IOException {
        String code =
                "value1,value2,value3,value4\r\na,b,c,d\r\n  x,,,"
                        + "\r\n\r\n\"\"\"hello\"\"\",\"  \"\"world\"\"\",\"abc\ndef\",\r\n";
        String[][] res = {
                {"value1", "value2", "value3", "value4"},
                {"a", "b", "c", "d"},
                {"  x", "", "", ""},
                {""},
                {"\"hello\"", "  \"world\"", "abc\ndef", ""}
        };
        CSVParser parser = new CSVParser(code, CSVFormat.EXCEL);
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test117() throws IOException {
        String code = ""
                + "a,b\n"            // 1)
                + "\"\n\",\" \"\n"   // 2)
                + "\"\",#\n"   // 2)
                + "c,d\n"            // regression test 1
                ;
        String[][] res = {
                {"a", "b"},
                {"\n", " "},
                {"", "#"},
                {"c", "d"}           // Expected result for regression test 1
        };

        CSVFormat format = CSVFormat.DEFAULT;
        assertEquals(CSVFormat.DISABLED, format.getCommentStart());

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));

        String[][] res_comments = {
                {"a", "b"},
                {"\n", " "},
                {""},
        };

        format = CSVFormat.DEFAULT.withCommentStart('#');
        parser = new CSVParser(code, format);
        records = parser.getRecords();
        
        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test118() throws IOException {
        // Change the input value to an empty file
        CSVParser parser = new CSVParser(new StringReader(""), CSVFormat.DEFAULT);
        assertEquals(-1, parser.lookAhead());
    }
    @Test
    public void test119() throws IOException {
        // Change the input value to a non-empty file
        CSVParser parser = new CSVParser(new StringReader("hello,world"), CSVFormat.DEFAULT);
        assertEquals('h', parser.lookAhead());
    }
    @Test
    public void test120() throws IOException {
        // Change the input value to include a special character
        CSVParser parser = new CSVParser(new StringReader("hello,%world"), CSVFormat.DEFAULT);
        assertEquals('%', parser.lookAhead());
    }
    @Test
    public void test121() throws IOException {
        // Change the input value to include whitespace
        CSVParser parser = new CSVParser(new StringReader("hello, world"), CSVFormat.DEFAULT);
        assertEquals(' ', parser.lookAhead());
    }
    @Test
    public void test122() throws IOException {
        // Change the input value to include a new line character
        CSVParser parser = new CSVParser(new StringReader("hello,\nworld"), CSVFormat.DEFAULT);
        assertEquals('\n', parser.lookAhead());
    }
    @Test
    public void test123() throws Exception {
        CSVParser parser = new CSVParser("a\rb\rc", CSVFormat.DEFAULT.withLineSeparator("\r"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test124() throws Exception {
        CSVParser parser = new CSVParser("a\nb\nc", CSVFormat.DEFAULT.withLineSeparator("\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test125() throws Exception {
        CSVParser parser = new CSVParser("a\r\nb\r\nc", CSVFormat.DEFAULT.withLineSeparator("\r\n"));

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test126() throws Exception {
        CSVParser parser = new CSVParser("a,,b,c", CSVFormat.DEFAULT);

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test127() throws Exception {
        CSVParser parser = new CSVParser("abc", CSVFormat.DEFAULT);

        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertNull(parser.getRecord());
    }
}