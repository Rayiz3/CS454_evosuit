    @Test
    public void testEmptyLineBehaviourCSVWithExtraEmptyLines() throws Exception {
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
    public void testEmptyLineBehaviourCSVWithoutEmptyLines() throws Exception {
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
    public void testEmptyLineBehaviourCSVWithOneEmptyLine() throws Exception {
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
    public void testEmptyFileWithNewLine() throws Exception {
        String code = "\n";
        CSVParser parser = new CSVParser(code, CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }

    @Test
    public void testEmptyFileWithoutNewLine() throws Exception {
        String code = "";
        CSVParser parser = new CSVParser(code, CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }

    @Test
    public void testCarriageReturnEndingsWithExtraCarriageReturn() throws IOException {
        String code = "foo\rbaar,\rhello,world\r,kanu\r";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }

    @Test
    public void testCarriageReturnEndingsWithoutCarriageReturn() throws IOException {
        String code = "foo\rbaar,\nhello,world\r,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }

    @Test
    public void testIgnoreEmptyLinesWithExtraEmptyLines() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n\n\n\n\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }

    @Test
    public void testIgnoreEmptyLinesWithoutEmptyLines() throws IOException {
        String code = "\nfoo,baar\n,world\r\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(2, records.size());
    }

    @Test
    public void testGetRecordsWithEmptyQuotes() throws IOException {
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
    public void testHeaderAsNumbers() throws Exception {
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
    public void testHeaderWithEmptyValues() throws Exception {
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
    public void testEmptyLineBehaviourCSVWithAdditionalEmptyLine() throws Exception {
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
    public void testEmptyLineBehaviourCSVWithInvalidEmptyLine() throws Exception {
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
    public void testEmptyFileWithNewLine() throws Exception {
        CSVParser parser = new CSVParser("\n", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }

    @Test
    public void testEmptyFileWithCarriageReturn() throws Exception {
        CSVParser parser = new CSVParser("\r", CSVFormat.DEFAULT);
        assertNull(parser.getRecord());
    }

    @Test
    public void testCarriageReturnEndingsWithExtraCarriageReturn() throws IOException {
        String code = "foo\rbaar,\rhello,world\r\r,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }

    @Test
    public void testIgnoreEmptyLinesWithExtraLine() throws IOException {
        String code = "\nfoo,baar\n\r\n,\n\n,world\r\n\n";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(3, records.size());
    }

    @Test
    public void testGetRecordsWithIncorrectFormat() throws IOException {
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
    public void testProvidedHeaderWithIncorrectHeader() throws Exception {
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
    public void testExcelFormat2WithIncorrectFormat() throws Exception {
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
    public void testBackslashEscaping2WithIncorrectEscapeCharacter() throws IOException {

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
    public void testEmptyLineBehaviourExcelWithAdditionalEmptyLine() throws Exception {
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
    public void testEmptyLineBehaviourExcelWithInvalidEmptyLine() throws Exception {
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
    public void testGetLineNumberWithCRWithExtraCarriageReturn() throws Exception {
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
    public void testGetLineNumberWithCRLFWithExtraCarriageReturn() throws Exception {
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
    public void testForEachWithEmptyFile() throws Exception {
        List<CSVRecord> records = new ArrayList<CSVRecord>();

        Reader in = new StringReader("");

        for (CSVRecord record : CSVFormat.DEFAULT.parse(in)) {
            records.add(record);
        }

        assertEquals(0, records.size());
    }

    @Test
    public void testEndOfFileBehaviourExcelWithAdditionalLine() throws Exception {
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
    public void testGetLineNumberWithLFWithExtraLine() throws Exception {
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
    public void testCarriageReturnLineFeedEndingsWithExtraCarriageReturn() throws IOException {
        String code = "foo\r\nbaar,\r\nhello,world\r\n\r\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }

    @Test
    public void testExcelFormat1WithIncorrectDelimiter() throws IOException {
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
    public void testIteratorWithEmptyFile() throws Exception {
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
    public void testDefaultFormatWithEmptyFile() throws IOException {
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
    public void testHeaderWithInvalidHeader() throws Exception {
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
    public void testEndOfFileBehaviorCSVWithAdditionalLine() throws Exception {
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
    public void testBackslashEscapingWithIncorrectEscapeCharacter() throws IOException {

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
    public void testGetLineWithExtraLine() throws IOException {
        CSVParser parser = new CSVParser(new StringReader(code), CSVFormat.DEFAULT.withSurroundingSpacesIgnored(true));
        for (String[] re : res) {
            assertArrayEquals(re, parser.getRecord().values());
        }

        assertNull(parser.getRecord());
    }

    @Test
    public void testLineFeedEndingsWithExtraLine() throws IOException {
        String code = "foo\nbaar,\nhello,world\n\n,kanu";
        CSVParser parser = new CSVParser(new StringReader(code));
        List<CSVRecord> records = parser.getRecords();
        assertEquals(4, records.size());
    }

