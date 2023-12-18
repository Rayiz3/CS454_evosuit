package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals("def", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("def"), "abcd"));
        Assert.assertFalse(StringUtils.equals("defg", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("def"), "ABC"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("def"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("def")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("def"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("def"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("defg"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("def"), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("def", null));
        Assert.assertFalse(StringUtils.equals(null, "def"));
        Assert.assertTrue(StringUtils.equals("def", "abc"));
        Assert.assertFalse(StringUtils.equals("def", "abcd"));
        Assert.assertFalse(StringUtils.equals("defg", "abc"));
        Assert.assertFalse(StringUtils.equals("def", "ABC"));
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
        final byte[] expected = LONG_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(LONG_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = SPECIAL_CHARS_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(SPECIAL_CHARS_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = LONG_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(LONG_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = SPECIAL_CHARS_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(SPECIAL_CHARS_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "a".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("a");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello 123!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello 123!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String emptyString = "";
        final byte[] expected = emptyString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String nullString = null;
        Assert.assertNull(StringUtils.getBytesUtf16Le(nullString));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final String specialCharacters = "å©©";
        final byte[] expected = specialCharacters.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(specialCharacters);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final String numbers = "12345";
        final byte[] expected = numbers.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(numbers);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final String emptyString = "";
        final byte[] expected = emptyString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(emptyString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String whitespace = "    ";
        final byte[] expected = whitespace.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(whitespace);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test23() {
        final String emptyString = "";
        try {
            StringUtils.getBytesUnchecked(emptyString, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@!#%&".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("@!#%&");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test27() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test28() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    try {
        StringUtils.getBytesUtf16(null);
    } catch (IllegalArgumentException e) {
        Assert.assertEquals("Input string should not be null", e.getMessage());
    }
}
@Test
public void test29() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "特殊字符".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("特殊字符");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Special Characters: !@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Special Characters: !@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "UPPERCASE".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("UPPERCASE");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "lowercase".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("lowercase");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "space separated string".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("space separated string");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "tab\tseparated\tstring".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("tab\tseparated\tstring");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "newline\nseparated\nstring".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("newline\nseparated\nstring");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "\u6771\u4eac".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("\u6771\u4eac");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String emptyString = "";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = emptyString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(emptyString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String specialChars = "!@#$%^&*()-=_+";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = specialChars.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(specialChars);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String whiteSpace = "       ";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = whiteSpace.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(whiteSpace);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String digits = "12345";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = digits.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(digits);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String alphaNumeric = "abc123";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = alphaNumeric.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(alphaNumeric);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "^&*%$#@".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("^&*%$#@");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test48() {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        Assert.assertNull(StringUtils.getBytesUtf8(null));
    }
    @Test
    public void test49() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() {
        try {
            StringUtils.getBytesUnchecked("", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() {
        try {
            StringUtils.getBytesUnchecked("!@#$%^", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() {
        try {
            StringUtils.newString(new byte[0], "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test54() {
        try {
            StringUtils.newString(null, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test55() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        // Additional regression test cases
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[]{'A'}, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[]{-1,0,1}, "UNKNOWN"));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        // Additional regression test cases
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        final String expectedAscii = new String(new byte[]{'A'}, charsetName);
        final String actualAscii = StringUtils.newStringUtf16Be(new byte[]{'A'});
        Assert.assertEquals(expectedAscii, actualAscii);
        final String expectedMixed = new String(new byte[]{-1,0,1}, charsetName);
        final String actualMixed = StringUtils.newStringUtf16Be(new byte[]{-1,0,1});
        Assert.assertEquals(expectedMixed, actualMixed);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Additional regression test cases
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        final String expectedAscii = new String(new byte[]{'A'}, charsetName);
        final String actualAscii = StringUtils.newStringUtf8(new byte[]{'A'});
        Assert.assertEquals(expectedAscii, actualAscii);
        final String expectedMixed = new String(new byte[]{-1,0,1}, charsetName);
        final String actualMixed = StringUtils.newStringUtf8(new byte[]{-1,0,1});
        Assert.assertEquals(expectedMixed, actualMixed);
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newString(null, "ANOTHER_UNKNOWN"));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() {
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ANOTHER_UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_INVALID, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_INVALID);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_INVALID, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_INVALID);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{1, 2, 3});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{127, -128, -1});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        // Test case 1: empty byte array
        // This test case can kill mutants that assume the byte array is not empty
        final byte[] emptyBytes = {};
        final String emptyExpected = new String(emptyBytes, "US-ASCII");
        final String emptyActual = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        // Test case 2: byte array with only one element
        // This test case can kill mutants that do not properly handle byte arrays with length of 1
        final byte[] singleByte = {97};
        final String singleByteExpected = new String(singleByte, "US-ASCII");
        final String singleByteActual = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(singleByteExpected, singleByteActual);

        // Test case 3: byte array with non-ASCII values
        // This test case can kill mutants that do not handle non-ASCII values correctly
        final byte[] nonAsciiBytes = {(byte) 128, (byte) 255};
        final String nonAsciiExpected = new String(nonAsciiBytes, "US-ASCII");
        final String nonAsciiActual = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(nonAsciiExpected, nonAsciiActual);
    }
    @Test
    public void test73() {
        // Test case 1: null byte array
        // This test case can kill mutants that assume the byte array is not null
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
      
        // Test with input value containing non-ASCII characters
        final byte[] input1 = { 0x00, 0x61, 0x00, 0x72, 0x00, 0x79 }; // "ary" in UTF-16
        final String expected1 = "ary";
        final String actual1 = StringUtils.newStringUtf16(input1);
        Assert.assertEquals(expected1, actual1);
        
        // Test with input value containing ASCII characters
        final byte[] input2 = { 0x00, 0x68, 0x00, 0x65, 0x00, 0x6c, 0x00, 0x6c, 0x00, 0x6f }; // "hello" in UTF-16
        final String expected2 = "hello";
        final String actual2 = StringUtils.newStringUtf16(input2);
        Assert.assertEquals(expected2, actual2);
        
        // Test with empty input value
        final byte[] input3 = {};
        final String expected3 = "";
        final String actual3 = StringUtils.newStringUtf16(input3);
        Assert.assertEquals(expected3, actual3);
        
        // Test with input value containing invalid characters
        final byte[] input4 = { 0x00, (byte) 0xD8, (byte) 0x00, (byte) 0x00 }; // Invalid character in UTF-16
        final String expected4 = "\uD800\u0000";
        final String actual4 = StringUtils.newStringUtf16(input4);
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final byte[] bytes = {65, 66, 67};
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test76() {
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = new byte[] { 0x00, 0x61 }; // Change input
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = new byte[] { 0x63, 0x00 }; // Change input
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test case 2
        byte[] specialCharBytes = new byte[] {(byte) 0xC3, (byte) 0x28};
        final String expectedSpecialChar = new String(specialCharBytes, charsetName);
        final String actualSpecialChar = StringUtils.newStringUtf8(specialCharBytes);
        Assert.assertEquals(expectedSpecialChar, actualSpecialChar);
    }
    @Test
    public void test80() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        
        // Regression test case 2
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {(byte) 0xC3, (byte) 0x28}));
    }
}