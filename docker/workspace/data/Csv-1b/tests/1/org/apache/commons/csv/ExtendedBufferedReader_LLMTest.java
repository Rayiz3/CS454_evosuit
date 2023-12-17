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
        String[] codes = {
                "hello,\r\n\r\n\r\n",
                "hello,\n\n\n",
                "hello,\"\"\r\n\r\n\r\n",
                "hello,\"\"\n\n\n"
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
    public void test1() throws Exception {
        CSVParser parser = new CSVParser("", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test2() throws IOException {
        String code = "foo\rbaar,\rhello,world\r,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test3() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n";
        //String code = "world\r\n\n";
        //String code = "foo;baar\r\n\r\nhello;\r\n\r\nworld;\r\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test4() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test5() throws Exception {
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
    public void test6() throws Exception {
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
    public void test7() throws IOException {
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
    public void test8() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n",
                "hello,\n\n\n",
                "hello,\"\"\r\n\r\n\r\n",
                "hello,\"\"\n\n\n"
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
    public void test9() throws Exception {
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
    public void test10() throws Exception {
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
    public void test11() throws Exception {
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
    public void test12() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {"world", ""}
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
    public void test13() throws Exception {
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
    public void test14() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test15() throws IOException {
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
    public void test16() throws Exception {
        String code = "hello,\r\n\r\n\r\n\r\n";
        String[][] res = { {"hello", ""} };
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test17() throws Exception {
        CSVParser parser = new CSVParser("\r\n", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test18() throws IOException {
        String code = "foo\rbaar,\rhello,world\r,kanu\r";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(5, records.size());
    }
    @Test
    public void test19() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n\r\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test20() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code+"\n"), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length+1, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test21() throws Exception {
        Reader in = new StringReader("a,b,c\n1,2,3\nx,y,z\n");

        Iterator<CSVRecord> records = CSVFormat.DEFAULT.withHeader("A", "B", "C").parse(in).iterator();

        for (int i = 0; i < 4; i++) {
            assertTrue(records.hasNext());
            CSVRecord record = records.next();
            assertEquals(record.get(0), record.get("A"));
            assertEquals(record.get(1), record.get("B"));
            assertEquals(record.get(2), record.get("C"));
        }

        assertFalse(records.hasNext());
    }
    @Test
    public void test22() throws Exception {
        String code = "foo,baar\r\nhello,\r\nworld,\r\n\r\n";
        String[][] res = {
                {"foo", "baar"},
                {""},
                {"hello", ""},
                {""},
                {"world", ""},
                {""}
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
    public void test23() throws IOException {
        String code = ""
                + " , , \r\n"           // 1)
                + " \t ,  , \r\n"       // 2)
                + " // , /, , /,\r\n"   // 3)
                + "\r\n";
        String[][] res = {
                {" ", " ", " "},         // 1
                {" \t ", "  ", " "},     // 2
                {" / ", " , ", " ,"},    // 3
                {""}
        };


        CSVFormat format = new CSVFormat(',',  CSVFormat.DISABLED,  CSVFormat.DISABLED, '/', false, true, "\r\n", null);

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));
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
        assertEquals(3, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(3, parser.getLineNumber());
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
        assertEquals(3, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(3, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test26() throws Exception {
        List<CSVRecord> records = new ArrayList<CSVRecord>();
        
        Reader in = new StringReader("a,b,c\r\n1,2,3\r\nx,y,z\r\n");
        
        for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
            records.add(record);
        }
        
        assertEquals(4, records.size());
        assertArrayEquals(new String[]{"a", "b", "c"}, records.get(0).values());
        assertArrayEquals(new String[]{"1", "2", "3"}, records.get(1).values());
        assertArrayEquals(new String[]{"x", "y", "z"}, records.get(2).values());
        assertArrayEquals(new String[]{"", "", ""}, records.get(3).values());
    }
    @Test
    public void test27() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n\r\n",
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,\"\"\r\n\r\n",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\n\r\n",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,\"\"\n\r\n",
                "hello,\r\n\r\nworld,\"\"\n"
        };
        String[][] res = {
                {"hello", ""},
                {""},
                {"world", ""},
                {""}
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
    public void test28() throws IOException {
        String code = "foo\nbaar,\nhello,world\n,kanu\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(5, records.size());
    }
@Test
public void test29() throws Exception {
    String code = "var,\n\n\n";
    String[][] res = {
            {"var", ""},
            {""},  // CSV format ignores empty lines
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
public void test30() throws IOException {
    String code = "var,\n\n\n\n";
    CSVParser parser = new CSVParser(new StringReader(code));
    List<CSVRecord> records = parser.getRecords();
    assertEquals(1, records.size());
}
    @Test
    public void test31() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n",
                "hello,\n\n\n",
                "hello,\"\"\r\n\r\n\r\n",
                "hello,\"\"\n\n\n",
                "hello,\r\n\r\nworld,\r\n",  // additional test case
                "hello,\r\n\r\nworld,"      // additional test case
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
    public void test32() throws Exception {
        CSVParser parser = new CSVParser("", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }
    @Test
    public void test33() throws IOException {
        String code = "foo\rbaar,\rhello,world\r,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test34() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n";
        //String code = "world\r\n\n";
        //String code = "foo;baar\r\n\r\nhello;\r\n\r\nworld;\r\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test35() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test36() throws Exception {
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
    public void test37() throws Exception {
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
    public void test38() throws IOException {

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
    public void test39() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n",
                "hello,\n\n\n",
                "hello,\"\"\r\n\r\n\r\n",
                "hello,\"\"\n\n\n",
                "hello,\r\n\r\nworld,\r\n",  // additional test case
                "hello,\r\n\r\nworld,"      // additional test case
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
    public void test40() throws Exception {
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
    public void test41() throws Exception {
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
    public void test42() throws Exception {
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
    public void test43() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {"world", ""}
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
    public void test44() throws Exception {
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
    public void test45() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test46() throws IOException {
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
    public void test47() throws Exception {
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
    public void test48() throws IOException {
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

        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test49() throws Exception {
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
    public void test50() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},  // CSV format ignores empty lines
                {"world", ""}
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
    public void test51() throws IOException {

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
    public void test52() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n",
                "hello,\n\n\n",
                "hello,\"\"\r\n\r\n\r\n",
                "hello,\"\"\n\n\n",
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,"
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
    public void test53() throws Exception {
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
    public void test54() throws Exception {
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
    public void test55() throws Exception {
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
    public void test56() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {"world", ""}
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
    public void test58() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test59() throws IOException {
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
    public void test60() throws Exception {
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
    public void test61() throws IOException {
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

        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test62() throws Exception {
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
    public void test63() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},  // CSV format ignores empty lines
                {"world", ""}
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
    public void test64() throws IOException {

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
    public void test65() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n",
                "hello,\n\n\n",
                "hello,\"\"\r\n\r\n\r\n",
                "hello,\"\"\n\n\n",
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,"
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
    public void test66() throws Exception {
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
    public void test67() throws Exception {
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
    public void test68() throws Exception {
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
    public void test69() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {"world", ""}
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
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test72() throws IOException {
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
    public void test73() throws Exception {
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
    public void test74() throws IOException {
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

        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test75() throws Exception {
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
    public void test76() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},  // CSV format ignores empty lines
                {"world", ""}
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
    public void test77() throws IOException {

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
    public void test78() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n",
                "hello,\n\n\n",
                "hello,\"\"\r\n\r\n\r\n",
                "hello,\"\"\n\n\n",
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,"
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
    public void test79() throws Exception {
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
    public void test80() throws Exception {
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
    public void test81() throws Exception {
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
    public void test82() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\r\n",
                "hello,\r\n\r\nworld,\"\"",
                "hello,\r\n\r\nworld,\n",
                "hello,\r\n\r\nworld,",
                "hello,\r\n\r\nworld,\"\"\n",
                "hello,\r\n\r\nworld,\"\""
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {"world", ""}
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
    public void test83() throws Exception {
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
    public void test84() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test85() throws IOException {
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
    public void test86() throws IOException {
        String code = ""
                + "a,b\n"            // 1)
                + "\"\n\",\" \"\n"   // 2)
                + "\n"               // 3) Added empty line
                ;
        String[][] res = {
                {"a", "b"},
                {"\n", " "},
                {}
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
                {},
        };

        format = CSVFormat.DEFAULT.withCommentStart('#');
        parser = new CSVParser(code, format);
        records = parser.getRecords();
        
        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test87() throws IOException {
        String code = ""
                + "x,y\n"            // 1)
                + "\"\n\",\" \"\n"   // 2)
                + "\"\",#\n"   // 2)
                ;
        String[][] res = {
                {"x", "y"},
                {"\n", " "},
                {"", "#"},
        };

        CSVFormat format = CSVFormat.ALTERNATE;
        assertEquals('\'', format.getQuoteCharacter());

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));

        String[][] res_comments = {
                {"x", "y"},
                {"\n", " "},
                {""},
        };

        format = CSVFormat.ALTERNATE.withCommentStart('#');
        parser = new CSVParser(code, format);
        records = parser.getRecords();
        
        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test88() throws IOException {
        String code = ""
                + "a\tb\n"            // 1) Changed delimiter to tab
                + "\"\t\"\t\" \"\n"   // 2)
                + "\"\"\t#\n"   // 2)
                ;
        String[][] res = {
                {"a", "b"},
                {"\t", " "},
                {"", "#"},
        };

        CSVFormat format = CSVFormat.ALTERNATE.withDelimiter('\t');
        assertEquals('\t', format.getDelimiter());

        CSVParser parser = new CSVParser(code, format);
        List<CSVRecord> records = parser.getRecords();
        assertTrue(records.size() > 0);

        assertTrue(CSVPrinterTest.equals(res, records));

        String[][] res_comments = {
                {"a", "b"},
                {"\t", " "},
                {""},
        };

        format = CSVFormat.ALTERNATE.withCommentStart('#').withDelimiter('\t');
        parser = new CSVParser(code, format);
        records = parser.getRecords();
        
        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test89() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n\r\n",  // additional empty line
                "hello,\n\n\n\n",  // additional empty line
                "hello,\"\"\r\n\r\n\r\n\r\n",  // additional empty line
                "hello,\"\"\n\n\n\n"  // additional empty line
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
    public void test90() throws IOException {
        String code = "foo\rbaar,\rhello,world\r,kanu\r";  // additional carriage return at the end
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test91() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n\r";  // additional empty lines
        //String code = "world\r\n\n";
        //String code = "foo;baar\r\n\r\nhello;\r\n\r\nworld;\r\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }
    @Test
    public void test92() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(res.length, records.size());
        assertTrue(records.size() > 0);
        for (int i = 0; i < res.length; i++) {
            assertArrayEquals(res[i], records.get(i).values());
        }
    }
    @Test
    public void test93() throws Exception {
        String code = "foo,baar\r\n\r\nhello,\r\n\r\nworld,\r\n\r";  // additional carriage return at the end
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
    public void test94() throws Exception {
        String[] codes = {
                "hello,\r\n\r\n\r\n\r\n",  // additional empty line
                "hello,\n\n\n\n",  // additional empty line
                "hello,\"\"\r\n\r\n\r\n\r\n",  // additional empty line
                "hello,\"\"\n\n\n\n"  // additional empty line
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
    public void test95() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n\r",  // additional carriage return at the end
                "hello,\r\n\r\nworld,\r\n",  // additional comma
                "hello,\r\n\r\nworld,\"\"\r\n\r",  // additional carriage return at the end
                "hello,\r\n\r\nworld,\"\"",  // additional double quote
                "hello,\r\n\r\nworld,\n\r",  // additional new line character
                "hello,\r\n\r\nworld,",  // additional comma
                "hello,\r\n\r\nworld,\"\"\n\r",  // additional new line character
                "hello,\r\n\r\nworld,\"\""  // additional double quote
        };
        String[][] res = {
                {"hello", ""},
                {""},  // Excel format does not ignore empty lines
                {"world", ""}
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
    public void test96() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n,kanu\r";  // additional carriage return at the end
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }
    @Test
    public void test97() throws IOException {
        String code =
                "value1,value2,value3,value4\r\na,b,c,d\r\n  x,,,"
                        + "\r\n\r\n\"\"\"hello\"\"\",\"  \"\"world\"\"\",\"abc\ndef\",\r\n\r";  // additional carriage return at the end
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
    public void test98() throws IOException {
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
        
        assertTrue(CSVPrinterTest.equals(res_comments, records));
    }
    @Test
    public void test99() throws Exception {
        String[] codes = {
                "hello,\r\n\r\nworld,\r\n\r",  // additional carriage return at the end
                "hello,\r\n\r\nworld,\r\n",  // additional comma
                "hello,\r\n\r\nworld,\"\"\r\n\r",  // additional carriage return at the end
                "hello,\r\n\r\nworld,\"\"",  // additional double quote
                "hello,\r\n\r\nworld,\n\r",  // additional new line character
                "hello,\r\n\r\nworld,",  // additional comma
                "hello,\r\n\r\nworld,\"\"\n\r",  // additional new line character
                "hello,\r\n\r\nworld,\"\""  // additional double quote
        };
        String[][] res = {
                {"hello", ""},  // CSV format ignores empty lines
                {"world", ""}
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
    public void test100() throws IOException {

        // To avoid confusion over the need for escaping chars in java code,
        // We will test with a forward slash as the escape char, and a single
        // quote as the encapsulator.

        String code =
                "one,two,three\n" // 0
                + "'',''\n"       // 1) empty encapsulators
                + "/',/'\n"       // 2) single encapsulators
                + "'/'','/''\n"   // 3) single encapsulators encapsulated via escape
                + "'''',''''\n"   // 4) single encapsulators encapsulated via doubling
                + "/,,/,\n"       // 5) separator escaped
                + "//,//\n"       // 6) escape escaped
                + "'//','//'\n"   // 7) escape escaped in encapsulation
                + "   8   ,   \"quoted \"\" /\" // string\"   \n"     // don't eat spaces
                + "9,   /\n   \n"  // escaped newline
                + "";
        String[][] res = {
                {"one", "two", "three"}, // 0
                {"", ""},                // 1
                {"'", "'"},              // 2
                {"'", "'"},              // 3
                {"'", "'"},              // 4
                {",", ","},              // 5
                {"/", "/"},              // 6
                {"/", "/"},              // 7
                {"   8   ", "   \"quoted \"\" \" / string\"   "},
                {"9", "   \n   "},
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
    public void test101() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        for (String[] re : res) {
            assertArrayEquals(re, parser.getRecord().values());
        }
        
        assertNull(parser.getRecord());
    }
    @Test
    public void test102() throws Exception {
        CSVParser parser = new CSVParser("", CSVFormat.DEFAULT);
        
        assertEquals(0, parser.getLineNumber());
    }
    @Test
    public void test103() throws Exception {
        CSVParser parser = new CSVParser("hello,world", CSVFormat.DEFAULT);
        
        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
    @Test
    public void test104() throws Exception {
        CSVParser parser = new CSVParser("hello,world\nfoo,bar\nbaz,qux", CSVFormat.DEFAULT);
        
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
    public void test105() throws Exception {
        CSVParser parser = new CSVParser("hello\tworld\nfoo\tbar\tbaz\tqux", CSVFormat.DEFAULT.withDelimiter('\t'));
        
        assertEquals(0, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(1, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNotNull(parser.getRecord());
        assertEquals(2, parser.getLineNumber());
        assertNull(parser.getRecord());
    }
}