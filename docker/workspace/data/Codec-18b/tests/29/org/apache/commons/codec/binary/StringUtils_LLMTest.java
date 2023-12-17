package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertFalse(StringUtils.equals("def", new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertTrue(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "ABC"));
    }
    @Test
    public void test1() {
        Assert.assertTrue(StringUtils.equals(new StringBuilder("def"), null));
        Assert.assertTrue(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("def"), new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("def"), new StringBuilder("abcd")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("def"), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals("def", null));
        Assert.assertTrue(StringUtils.equals(null, "def"));
        Assert.assertFalse(StringUtils.equals("def", "def"));
        Assert.assertTrue(StringUtils.equals("def", "abcd"));
        Assert.assertTrue(StringUtils.equals("abcd", "def"));
        Assert.assertTrue(StringUtils.equals("def", "DEF"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("abc"); // Changing input to "abc"
        Assert.assertFalse(Arrays.equals(expected, actual)); // Expected and actual are not equal
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(""); // Changing input to an empty string
        Assert.assertFalse(Arrays.equals(expected, actual)); // Expected and actual are not equal
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(null); // test with null input
        testGetBytesUnchecked(""); // test with empty string input
        testGetBytesUnchecked("hello"); // test with different string input
        testGetBytesUnchecked("12345"); // test with different string input
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(null); // test with null input
        testGetBytesUnchecked(""); // test with empty string input
        testGetBytesUnchecked("hello"); // test with different string input
        testGetBytesUnchecked("12345"); // test with different string input
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(null); // test with null input
        testGetBytesUnchecked(""); // test with empty string input
        testGetBytesUnchecked("hello"); // test with different string input
        testGetBytesUnchecked("12345"); // test with different string input
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(null); // test with null input
        testGetBytesUnchecked(""); // test with empty string input
        testGetBytesUnchecked("hello"); // test with different string input
        testGetBytesUnchecked("12345"); // test with different string input
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(null); // test with null input
        testGetBytesUnchecked(""); // test with empty string input
        testGetBytesUnchecked("hello"); // test with different string input
        testGetBytesUnchecked("12345"); // test with different string input
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(null); // test with null input
        testGetBytesUnchecked(""); // test with empty string input
        testGetBytesUnchecked("hello"); // test with different string input
        testGetBytesUnchecked("12345"); // test with different string input
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() {
        try {
            StringUtils.getBytesUnchecked(null, "UNKNOWN"); // test with null input
            StringUtils.getBytesUnchecked("", "UNKNOWN"); // test with empty string input
            StringUtils.getBytesUnchecked("hello", "UNKNOWN"); // test with different string input
            StringUtils.getBytesUnchecked("12345", "UNKNOWN"); // test with different string input
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final String input = "漢字";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("a");
        }
        final String input = sb.toString();
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression1(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression2(charsetName);
        final byte[] expected = "A".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("A");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression3(charsetName);
        final byte[] expected = "Test$#@".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Test$#@");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression4(charsetName);
        final byte[] expected = " Hello World ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(" Hello World ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        testGetBytesUnchecked("UTF-16LE");
        final byte[] expected = "abc".getBytes("UTF-16LE");
        final byte[] actual = StringUtils.getBytesUtf16Le("abc");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        testGetBytesUnchecked("UTF-16LE");
        final byte[] expected = "".getBytes("UTF-16LE");
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        testGetBytesUnchecked("UTF-16LE");
        final byte[] expected = "ÆæØøÅå".getBytes("UTF-16LE");
        final byte[] actual = StringUtils.getBytesUtf16Le("ÆæØøÅå");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test(expected = UnsupportedEncodingException.class)
    public void test32() throws UnsupportedEncodingException {
        StringUtils.getBytesUtf16Le("abc");
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String input = "";
        final byte[] expected = input.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String input = null;
        final byte[] expected = {};
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String input = "!@#$%^&*()";
        final byte[] expected = input.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String input = "   ";
        final byte[] expected = input.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String input = "Åäö";
        final byte[] expected = input.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() {
        // Regression test case 1
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
       // Regression test case 2
        Assert.assertNull(StringUtils.newString(new byte[]{}, "UTF-8"));
       // Regression test case 3
        Assert.assertNull(StringUtils.newString(new byte[]{1, 2, 3}, "UTF-16"));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        // Regression test case 4
        final String expected2 = new String(BYTES_FIXTURE, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case 5
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual2 = StringUtils.newStringUtf8(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case 6
        final String expected2 = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test46() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{1, 2, 3}));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case 7
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case 8
        final String expected2 = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual2 = StringUtils.newStringUtf16(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        // Regression test case 9
        final String expected2 = new String(new byte[0], charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test50() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        // Regression test case 10
        try {
            StringUtils.newString(BYTES_FIXTURE, "US-ASCII");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        // Regression test case: empty bytes array
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN"));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        // Regression test case: empty bytes array
        Assert.assertEquals(new String(new byte[0], charsetName), StringUtils.newStringUtf16Be(new byte[0]));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case: empty bytes array
        Assert.assertEquals(new String(new byte[0], charsetName), StringUtils.newStringUtf8(new byte[0]));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case: empty bytes array
        Assert.assertEquals(new String(new byte[0], charsetName), StringUtils.newStringUsAscii(new byte[0]));
    }
    @Test
    public void test55() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        // Regression test case: empty bytes array
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case: empty bytes array
        Assert.assertEquals(new String(new byte[0], charsetName), StringUtils.newStringIso8859_1(new byte[0]));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case: empty bytes array
        Assert.assertEquals(new String(new byte[0], charsetName), StringUtils.newStringUtf16(new byte[0]));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        // Regression test case: empty bytes array
        Assert.assertEquals(new String(new byte[0], charsetName), StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test59() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(UPDATED_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(EMPTY_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        final byte[] emptyBytes = new byte[0];
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);
        
        final byte[] singleByte = new byte[]{65};
        final String singleByteExpected = new String(singleByte, charsetName);
        final String singleByteActual = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(singleByteExpected, singleByteActual);
        
        final byte[] multiBytes = new byte[]{65, 66, 67};
        final String multiBytesExpected = new String(multiBytes, charsetName);
        final String multiBytesActual = StringUtils.newStringUsAscii(multiBytes);
        Assert.assertEquals(multiBytesExpected, multiBytesActual);
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test cases
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{65}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{65, 66, 67}));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Test with empty byte array
        final String expectedEmptyByteArray = new String(new byte[]{}, charsetName);
        final String actualEmptyByteArray = StringUtils.newStringUtf16(new byte[]{});
        Assert.assertEquals(expectedEmptyByteArray, actualEmptyByteArray);
        
        // Test with byte array of length 1
        final byte[] byteArrLength1 = {65};
        final String expectedByteArrLength1 = new String(byteArrLength1, charsetName);
        final String actualByteArrLength1 = StringUtils.newStringUtf16(byteArrLength1);
        Assert.assertEquals(expectedByteArrLength1, actualByteArrLength1);
        
        // Test with byte array of length 100
        final byte[] byteArrLength100 = new byte[100];
        Arrays.fill(byteArrLength100, (byte) 65);
        final String expectedByteArrLength100 = new String(byteArrLength100, charsetName);
        final String actualByteArrLength100 = StringUtils.newStringUtf16(byteArrLength100);
        Assert.assertEquals(expectedByteArrLength100, actualByteArrLength100);
    }
    @Test
    public void test67() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Test with empty byte array
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
        
        // Test with byte array of length 1
        final byte[] byteArrLength1 = {65};
        Assert.assertNull(StringUtils.newStringUtf8(byteArrLength1));
        Assert.assertNull(StringUtils.newStringIso8859_1(byteArrLength1));
        Assert.assertNull(StringUtils.newStringUsAscii(byteArrLength1));
        Assert.assertNull(StringUtils.newStringUtf16Be(byteArrLength1));
        Assert.assertNull(StringUtils.newStringUtf16Le(byteArrLength1));
        
        // Test with byte array of length 100
        final byte[] byteArrLength100 = new byte[100];
        Arrays.fill(byteArrLength100, (byte) 65);
        Assert.assertNull(StringUtils.newStringUtf8(byteArrLength100));
        Assert.assertNull(StringUtils.newStringIso8859_1(byteArrLength100));
        Assert.assertNull(StringUtils.newStringUsAscii(byteArrLength100));
        Assert.assertNull(StringUtils.newStringUtf16Be(byteArrLength100));
        Assert.assertNull(StringUtils.newStringUtf16Le(byteArrLength100));
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final byte[] bytes = {0x00, 0x41}; // input that is different from BYTES_FIXTURE_16BE
        final String charsetName = "UTF-16BE";
        testNewString_regression1(charsetName);
        final String expected = new String(bytes, charsetName); // expected value should be based on the new input
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final byte[] bytes = {}; // empty input
        final String charsetName = "UTF-16BE";
        testNewString_regression2(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final byte[] bytes = new byte[] { -2, -1, 0, 63 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        String testStr = "This is a long string.";
        byte[] bytes = testStr.getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = null;
        final String actual = StringUtils.newStringUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test75() {
        byte[] bytes = {104, 101, 108, 108, 111}; // ASCII encoding of "hello"
        final String expected = "hello";
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test76() {
        byte[] bytes = {};
        final String expected = "";
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test77() {
        byte[] bytes = {72, 101, 108, 108, -1, 111}; // Invalid UTF-8 character
        final String expected = "Hell�o"; // ASCII encoding of "H" "e" "l" "l" "o" + invalid character
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
}