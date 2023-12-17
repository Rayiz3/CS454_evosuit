package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        // Original test cases
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
        
        // New test cases
        Assert.assertFalse(StringUtils.equals("", null));
        Assert.assertFalse(StringUtils.equals(null, ""));
        Assert.assertTrue(StringUtils.equals("", ""));
        Assert.assertFalse(StringUtils.equals("ABC", ""));
        Assert.assertFalse(StringUtils.equals("", "ABC"));
        Assert.assertTrue(StringUtils.equals("ABC", "ABC"));
        Assert.assertFalse(StringUtils.equals("ABC", "ABCDE"));
        Assert.assertFalse(StringUtils.equals("ABCDE", "ABC"));
    }
    @Test
    public void test1() {
        // Original test cases
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
        
        // New test cases
        Assert.assertFalse(StringUtils.equals(new StringBuilder(""), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder(""), new StringBuilder("")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("ABC"), new StringBuilder("")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder(""), new StringBuilder("ABC")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("ABC"), new StringBuilder("ABC")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("ABC"), new StringBuilder("ABCDE")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("ABCDE"), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        // Original test cases
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
        
        // New test cases
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", ""));
        Assert.assertFalse(StringUtils.equals("", "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abcabc"));
        Assert.assertTrue(StringUtils.equals("abcabc", "abc"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test 1: Empty string as input
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesIso8859_1(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Regression Test 2: String containing special characters
        final String specialCharacters = "r\u00E9sum\u00E9";
        final byte[] specialExpected = specialCharacters.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesIso8859_1(specialCharacters);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
        
        // Regression Test 3: String containing only whitespace characters
        final String whitespace = "        ";
        final byte[] whitespaceExpected = whitespace.getBytes(charsetName);
        final byte[] whitespaceActual = StringUtils.getBytesIso8859_1(whitespace);
        Assert.assertTrue(Arrays.equals(whitespaceExpected, whitespaceActual));
    }
    @Test
    public void test16() {
        Assert.assertArrayEquals(null, StringUtils.getBytesUnchecked("", "UTF-8"));
    }
    @Test
    public void test17() {
        Assert.assertNotNull(StringUtils.getBytesUnchecked("你好", "UTF-8"));
    }
    @Test
    public void test18() {
        Assert.assertNotNull(StringUtils.getBytesUnchecked("Hello", "UNKNOWN"));
    }
    @Test
    public void test19() {
        Assert.assertArrayEquals(null, StringUtils.getBytesUnchecked("Hello", null));
    }
    @Test
    public void test20() {
        Assert.assertArrayEquals(null, StringUtils.getBytesUnchecked(null, null));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Non-Ascii Characters".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Non-Ascii Characters");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
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
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String input = "Hello World! \u00A9";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String input = "";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%^".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("@#$%^");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.repeat("a", 10000).getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(StringUtils.repeat("a", 10000));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[] {}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] {});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[] {100, -50, 122, 23, 9, -10, 34, 70}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[] {100, -50, 122, 23, 9, -10, 34, 70});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(new byte[] {}, charsetName);
        final String actual = StringUtils.newStringUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[] {}, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] {});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[] {23, 76, -13, -47}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] {23, 76, -13, -47});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[] {}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Passing null bytes
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Regression test 2: Passing empty bytes array
        final String emptyExpected = new String(new byte[]{}, charsetName);
        final String emptyActual = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(emptyExpected, emptyActual);
    }
    @Test
    public void test50() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test: Passing null charsetName
        try {
            StringUtils.newString(BYTES_FIXTURE, null);
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test: Passing null bytes
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test: Passing null bytes
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test: Passing null bytes
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test: Passing null bytes
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test: Passing null bytes
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewStringWithSpecialCharacters(charsetName);
        final String expected = new String(SPECIAL_CHARACTERS_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(SPECIAL_CHARACTERS_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewStringIsEmptyArray(charsetName);
        final String expected = new String(EMPTY_ARRAY_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(EMPTY_ARRAY_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        Assert.assertEquals("", StringUtils.newStringUsAscii(new byte[] {}));
        Assert.assertEquals("@", StringUtils.newStringUsAscii(new byte[] { 64 }));
        Assert.assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789",
                StringUtils.newStringUsAscii("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".getBytes()));
        Assert.assertEquals("\0\u001F\u007F", StringUtils.newStringUsAscii(new byte[] { 0, 31, 127 }));
    }
    @Test
    public void test59() {
        // Existing test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test cases
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] { 1, 2, 3 }));
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1: empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
        
        // Regression test case 2: byte array with unexpected character
        byte[] invalidBytes = {(byte) 0xF0, (byte) 0x9F, (byte) 0x92, (byte) 0xA9};
        Assert.assertNull(StringUtils.newStringUtf16(invalidBytes));
        
        // Regression test case 3: byte array with incomplete character
        byte[] incompleteBytes = {(byte) 0xC3};
        Assert.assertNull(StringUtils.newStringUtf16(incompleteBytes));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 4: byte array with null
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{0, 0}));
        
        // Regression test case 5: byte array with unexpected character
        byte[] invalidBytes = {(byte) 0x1F, (byte) 0x4A, (byte) 0x09, (byte) 0x41};
        Assert.assertNull(StringUtils.newStringUtf16(invalidBytes));
        
        // Regression test case 6: byte array with incomplete character
        byte[] incompleteBytes = {(byte) 0xAA};
        Assert.assertNull(StringUtils.newStringUtf16(incompleteBytes));
    }
    @Test
    public void test62() {
        final String charsetName = "UTF-16BE";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = { 0x00 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = { 0x00, 0x61, (byte)0xFF, (byte)0xFE };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression Test Case 1: empty byte array
        final byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));
        
        // Regression Test Case 2: byte array with special characters
        final byte[] specialBytes = { 66, 97, (byte) 195, (byte) 169, 99, 117, 108, 97 };
        Assert.assertEquals("Baécula", StringUtils.newStringUtf16Le(specialBytes));
        
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test Case 3: byte array with multiple characters
        final byte[] multiBytes = { 97, 0, 98, 0, 99, 0, 100, 0 };
        Assert.assertEquals("a b c d", StringUtils.newStringUtf16Le(multiBytes));
    }
    @Test
    public void test67() {
        final String charsetName = "UTF-8";
        final byte[] bytes = { 65, 66, 67, 72, -61, -98 };
        final String expected = new String(bytes, Charset.forName(charsetName));
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() {
        final String charsetName = "UTF-8";
        final byte[] bytes = {};
        final String expected = new String(bytes, Charset.forName(charsetName));
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test70() {
        final String charsetName = "UTF-8";
        final byte[] bytes = { 65, -61, 98, 67 };
        final String expected = new String(bytes, Charset.forName(charsetName));
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() {
        final String charsetName = "UTF-8";
        final byte[] bytes = { 65, -61, 66, -61, -98, 67, 68 };
        final String expected = new String(bytes, Charset.forName(charsetName));
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
}