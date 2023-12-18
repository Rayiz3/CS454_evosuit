package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
    public void test0() {
        Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("abcd"))); // changing cs2 value
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "abc"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), null)); // changing cs1 value
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc"))); // changing cs1 value
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd"))); // changing cs1 value
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals("abc", null)); // changing cs1 value
        Assert.assertFalse(StringUtils.equals(null, "abc")); // changing cs2 value
        Assert.assertTrue(StringUtils.equals("abc", "abcd")); // changing cs1 value
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "H€llo!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("H€llo!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "H€llo!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("H€llo!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "H€llo!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("H€llo!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "H€llo!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("H€llo!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "H€llo!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("H€llo!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "H€llo!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("H€llo!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_InvalidInput(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_EmptyString(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_Whitespace(charsetName);
        final byte[] expected = " ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(" ");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes();
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test(expected = UnsupportedEncodingException.class)
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "INVALID";
        StringUtils.getBytesUsAscii(STRING_FIXTURE);
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "??".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("日本");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case: Empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test case: String with one character
        final byte[] expectedOneChar = "a".getBytes(charsetName);
        final byte[] actualOneChar = StringUtils.getBytesUtf16("a");
        Assert.assertTrue(Arrays.equals(expectedOneChar, actualOneChar));
        
        // Regression test case: String with special characters
        final byte[] expectedSpecialChars = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf16("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
        
        // Regression test case: String with Unicode characters
        final byte[] expectedUnicode = "ÄÖÜ".getBytes(charsetName);
        final byte[] actualUnicode = StringUtils.getBytesUtf16("ÄÖÜ");
        Assert.assertTrue(Arrays.equals(expectedUnicode, actualUnicode));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Tests
        final byte[] emptyArray = new byte[0];
        final byte[] actualEmptyString = StringUtils.getBytesUtf16Be("");
        Assert.assertArrayEquals(emptyArray, actualEmptyString);
        
        final byte[] asciiArray = { 0, 65, 0, 66, 0, 67 };
        final byte[] actualAsciiString = StringUtils.getBytesUtf16Be("ABC");
        Assert.assertArrayEquals(asciiArray, actualAsciiString);

        final byte[] unicodeArray = { -1, -2, 0, 65, 0, 66, 0, 67 };
        final byte[] actualUnicodeString = StringUtils.getBytesUtf16Be("\uFEFFABC");
        Assert.assertArrayEquals(unicodeArray, actualUnicodeString);
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Special_@#$%_Characters".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Special_@#$%_Characters");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test30() {
        try {
            StringUtils.getBytesUnchecked("", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
     
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        try {
            StringUtils.getBytesUnchecked("test", "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }

    }
    @Test
    public void test31() {
        try {
            StringUtils.newString(new byte[0], "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        try {
            StringUtils.newString(new byte[0], "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
    }
    @Test
    public void test32() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test36() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newStringIso8859_1(BYTES_FIXTURE));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        //Mutant-killing test case
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        //Mutant-killing test case
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test cases
        
        // Test with empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with bytes that represent a single character 'A'
        final byte[] singleCharBytes = {65};
        final String expectedSingleChar = new String(singleCharBytes, charsetName);
        final String actualSingleChar = StringUtils.newStringIso8859_1(singleCharBytes);
        Assert.assertEquals(expectedSingleChar, actualSingleChar);
        
        // Test with bytes that represent a string "Hello"
        final byte[] stringBytes = {72, 101, 108, 108, 111};
        final String expectedString = new String(stringBytes, charsetName);
        final String actualString = StringUtils.newStringIso8859_1(stringBytes);
        Assert.assertEquals(expectedString, actualString);
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Additional test cases
        
        // Test with null input for newStringUtf8()
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Test with null input for newStringUsAscii()
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        
        // Test with null input for newStringUtf16()
        Assert.assertNull(StringUtils.newStringUtf16(null));
        
        // Test with null input for newStringUtf16Be()
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        
        // Test with null input for newStringUtf16Le()
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression tests
        final byte[] bytes1 = {65, 66, 67, 68};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(bytes1);
        Assert.assertEquals(expected1, actual1);

        final byte[] bytes2 = {97, 98, 99, 100};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression tests
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {}));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test Case 1: Null input
        Assert.assertNull(StringUtils.newStringUtf16(null));
        
        // Regression Test Case 2: Empty input
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression Test Case 3: Input with a different charset
        final String differentCharsetName = "UTF-8";
        final String expectedDifferent = new String(BYTES_FIXTURE, differentCharsetName);
        final String actualDifferent = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expectedDifferent, actualDifferent);
        
        // Regression Test Case 4: Input with invalid characters
        final byte[] invalidBytes = {(byte) 0xFF, (byte) 0xFE};
        final String expectedInvalid = new String(invalidBytes, charsetName);
        final String actualInvalid = StringUtils.newStringUtf16(invalidBytes);
        Assert.assertEquals(expectedInvalid, actualInvalid);
    }
    @Test
    public void test61() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression Test Case: Empty input
        final byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_v2(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_v2, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_v2);
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
        Assert.assertNull(StringUtils.newStringUtf16Be(BYTES_FIXTURE_NULL));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x00, 0x00, 0x00, 0x00});
        Assert.assertEquals(expected, actual);
        
        final String actual2 = StringUtils.newStringUtf16Le(new byte[]{0x00});
        Assert.assertEquals("", actual2);
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{0x00}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{0x00}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{0x00}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{0x00}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0x00}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{0x00}));
        
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        
        // Change the input byte array to empty byte array
        final byte[] bytes = new byte[]{};
        final String expected = "";
        final String actual = StringUtils.newStringUtf8(bytes);
        
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        
        // Change the input byte array to a non-UTF-8 encoded byte array
        final byte[] bytes = "Hello".getBytes(StandardCharsets.ISO_8859_1);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test68() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Change the input byte array to non-empty byte array
        final byte[] bytes = new byte[]{1, 2, 3};
        Assert.assertNotNull(StringUtils.newStringUtf8(bytes));
        
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}