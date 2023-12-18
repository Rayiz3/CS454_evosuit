package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        // Additional test cases for cs2
        Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcde"));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "abc"));
    }
    @Test
    public void test1() {
        // Additional test cases for cs1
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcde"), null));
        Assert.assertTrue(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("ab"), new StringBuilder("abc")));
    }
    @Test
    public void test2() {
        // Additional test cases for string
        Assert.assertFalse(StringUtils.equals(null, "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", null));
        Assert.assertTrue(StringUtils.equals("abcd", "abcd"));
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
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String specialString = "ČūņԁęƦ¡";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final String specialString = "ČūņԁęƦ¡";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final String specialString = "ČūņԁęƦ¡";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String specialString = "ČūņԁęƦ¡";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String specialString = "ČūņԁęƦ¡";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final String specialString = "ČūņԁęƦ¡";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "áéíóú".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("áéíóú");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Modified input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Modified input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Modified input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Modified input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Modified input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Modified input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        
        //Original test case
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        //Test case with empty string
        final String charsetName1 = "US-ASCII";
        testGetBytesUnchecked(charsetName1);
        final byte[] expected1 = "".getBytes(charsetName1);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        //Test case with non-ASCII characters
        final String charsetName2 = "US-ASCII";
        testGetBytesUnchecked(charsetName2);
        final byte[] expected2 = "é".getBytes(charsetName2);
        final byte[] actual2 = StringUtils.getBytesUsAscii("é");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        //Test case with long string
        final String charsetName3 = "US-ASCII";
        testGetBytesUnchecked(charsetName3);
        final byte[] expected3 = STRING_FIXTURE.substring(0, 5).getBytes(charsetName3);
        final byte[] actual3 = StringUtils.getBytesUsAscii(STRING_FIXTURE.substring(0, 5));
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test37() {
        final String string = null;
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test38() {
        final String string = "";
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test39() {
        final String string = "!@#$%^&*()_+-={}[]:\";'<>?,./`~";
        final byte[] expected = new byte[]{-1, -2, 33, 0, 64, 0, 35, 0, 36, 0, 37, 0, 94, 0, 38, 0, 42, 0,
                40, 0, 41, 0, 95, 0, 43, 0, 45, 0, 61, 0, 123, 0, 125, 0, 91, 0, 93, 0, 58, 0, 34, 0, 59, 0,
                39, 0, 60, 0, 62, 0, 63, 0, 44, 0, 46, 0, 47, 0, 96, 0, 126, 0};
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test40() {
        final String string = "Hello World";
        final byte[] expected = new byte[]{72, 0, 101, 0, 108, 0, 108, 0, 111, 0, 32, 0, 87, 0, 111, 0, 114, 0,
                108, 0, 100, 0};
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "😊".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("😊");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("!@#$");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "#@$%^&".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("#@$%^&");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] { 97, 98, 99 };
        final byte[] actual = StringUtils.getBytesUtf8("abc");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] { -61, -91 };
        final byte[] actual = StringUtils.getBytesUtf8("é");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] { 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100 };
        final byte[] actual = StringUtils.getBytesUtf8("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test50() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "!@#$%");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test54() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "!@#$%");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test55() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() {
        // Changing the input to an empty byte array
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN"));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Changing the input to an empty byte array
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Changing the input to a different byte array
        final byte[] differentBytes = {0x64, 0x65, 0x66}; // ASCII values for 'd', 'e', 'f'
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringUtf16Be(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Changing the input to an empty byte array
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Changing the input to a different byte array
        final byte[] differentBytes = {0x67, 0x68, 0x69}; // ASCII values for 'g', 'h', 'i'
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringUtf8(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        // Regression test: changing null input value
        byte[] bytes = new byte[]{};
        Assert.assertNull(StringUtils.newString(bytes, "UNKNOWN"));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        // Regression test: changing input value
        byte[] bytes = new byte[]{0, 1, 2, 3};
        expected = new String(bytes, charsetName);
        actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test: changing input value
        byte[] bytes = new byte[]{99, 100, 101, 102};
        expected = new String(bytes, charsetName);
        actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test: changing input value
        byte[] bytes = new byte[]{97, 98, 99, 100};
        expected = new String(bytes, charsetName);
        actual = StringUtils.newStringUsAscii(bytes);
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
        // Regression test: changing null input value
        byte[] bytes = new byte[]{};
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test: changing input value
        byte[] bytes = new byte[]{120, 121, 122, 123};
        expected = new String(bytes, charsetName);
        actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test: changing input value
        byte[] bytes = new byte[]{36, 37, 38, 39};
        expected = new String(bytes, charsetName);
        actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        // Regression test: changing input value
        byte[] bytes = new byte[]{48, 49, 50, 51};
        expected = new String(bytes, charsetName);
        actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        // Regression test: changing input value
        try {
            byte[] bytes = new byte[]{65, 66, 67, 68};
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));
        
        byte[] specialCharactersBytes = {(byte) 65, (byte) 66, (byte) 67};
        Assert.assertEquals("ABC", StringUtils.newStringIso8859_1(specialCharactersBytes));
    }
    @Test
    public void test69() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case
        byte[] nullBytes = null;
        Assert.assertNull(StringUtils.newStringIso8859_1(nullBytes));
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(NEW_BYTES_FIXTURE, charsetName); // Change input bytes
        final String actual = StringUtils.newStringUsAscii(NEW_BYTES_FIXTURE); // Change input bytes
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = "Hello";
        final String actual = StringUtils.newStringUtf16(new byte[]{0, 'H', 0, 'e', 0, 'l', 0, 'l', 0, 'o'});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        final String expected = "";
        final String actual = StringUtils.newStringUtf16(null);
        Assert.assertEquals(expected, actual);
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1 
        final byte[] bytes1 = {0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = {0x00, 0x31, 0x00, 0x32, 0x00, 0x33};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test75() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        
        // Regression test case 2
        final byte[] bytes = {0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        
        // Regression test case 3
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0x00, 0x61}));
        
        // Regression test case 4
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0x00}));
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{127, -128});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{65, 66, 0, 67, 68});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{98, 97, 115, 100, 80, 97, 115, 115, 119, 111, 114, 100});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test80() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{-1, 126, -127, 0});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test81() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{-128, -127, -126, -125, -124});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test82() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        
        // Regression test case 1
        final byte[] bytes1 = { 49, 50, 51 };
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf8(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = { -1, -2, -3 };
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test83() {
        // Regression test case 1
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Regression test case 2
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Regression test case 3
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        
        // Regression test case 4
        Assert.assertNull(StringUtils.newStringUtf16(null));
        
        // Regression test case 5
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        
        // Regression test case 6
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}