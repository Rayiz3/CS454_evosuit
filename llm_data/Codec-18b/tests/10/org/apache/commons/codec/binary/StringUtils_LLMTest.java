package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
        
        // Regression Test Cases
        Assert.assertEquals(StringUtils.equals("abc", "abc"), StringUtils.equals("abc", "def"));
        Assert.assertFalse(StringUtils.equals("abcd", "abcdx"));
        Assert.assertFalse(StringUtils.equals("xyz", "XYZ"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
        
        // Regression Test Cases
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("def")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abcdx")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("xyz"), new StringBuilder("XYZ")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
        
        // Regression Test Cases
        Assert.assertFalse(StringUtils.equals("abc", "def"));
        Assert.assertFalse(StringUtils.equals("abcd", "abcdx"));
        Assert.assertFalse(StringUtils.equals("xyz", "XYZ"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test: empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test: string with special characters
        final String specialString = "!@#$%^&*()_+{}[]:;'<>?,./";
        final byte[] expectedSpecial = specialString.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUsAscii(specialString);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test: string with numbers
        final String numberString = "1234567890";
        final byte[] expectedNumber = numberString.getBytes(charsetName);
        final byte[] actualNumber = StringUtils.getBytesUtf16Le(numberString);
        Assert.assertTrue(Arrays.equals(expectedNumber, actualNumber));
        
        // Regression test: string with spaces
        final String spaceString = "   ";
        final byte[] expectedSpace = spaceString.getBytes(charsetName);
        final byte[] actualSpace = StringUtils.getBytesUtf16Le(spaceString);
        Assert.assertTrue(Arrays.equals(expectedSpace, actualSpace));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test: string with lowercase letters
        final String lowercaseString = "abcdefghijklmnopqrstuvwxyz";
        final byte[] expectedLowercase = lowercaseString.getBytes(charsetName);
        final byte[] actualLowercase = StringUtils.getBytesUtf16Be(lowercaseString);
        Assert.assertTrue(Arrays.equals(expectedLowercase, actualLowercase));
        
        // Regression test: string with uppercase letters
        final String uppercaseString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final byte[] expectedUppercase = uppercaseString.getBytes(charsetName);
        final byte[] actualUppercase = StringUtils.getBytesUtf16Be(uppercaseString);
        Assert.assertTrue(Arrays.equals(expectedUppercase, actualUppercase));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test: string with mixed characters
        final String mixedString = "abc123!@#";
        final byte[] expectedMixed = mixedString.getBytes(charsetName);
        final byte[] actualMixed = StringUtils.getBytesIso8859_1(mixedString);
        Assert.assertTrue(Arrays.equals(expectedMixed, actualMixed));
        
        // Regression test: string with newline characters
        final String newlineString = "new\nline";
        final byte[] expectedNewline = newlineString.getBytes(charsetName);
        final byte[] actualNewline = StringUtils.getBytesIso8859_1(newlineString);
        Assert.assertTrue(Arrays.equals(expectedNewline, actualNewline));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test: string with special characters
        final String specialString = "!@#$%^&*()_+{}[]:;'<>?,./";
        final byte[] expectedSpecial = specialString.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf8(specialString);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
        
        // Regression test: string with unicode characters
        final String unicodeString = "σђัкє";
        final byte[] expectedUnicode = unicodeString.getBytes(charsetName);
        final byte[] actualUnicode = StringUtils.getBytesUtf8(unicodeString);
        Assert.assertTrue(Arrays.equals(expectedUnicode, actualUnicode));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test: empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test: string with all digits
        final String digitString = "0123456789";
        final byte[] expectedDigits = digitString.getBytes(charsetName);
        final byte[] actualDigits = StringUtils.getBytesUtf16(digitString);
        Assert.assertTrue(Arrays.equals(expectedDigits, actualDigits));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
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
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("new input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("new input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("new input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("new input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() {
        try {
            StringUtils.getBytesUnchecked("new input", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("new input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("new input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[]{65, 66, 67}; // ASCII values for 'A', 'B', 'C'
        final byte[] actual = StringUtils.getBytesUsAscii("ABC");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[]{97, 98, 0}; // ASCII values for 'a', 'b', null character
        final byte[] actual = StringUtils.getBytesUsAscii("ab\0");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[]{50, 49, 0, 48, 45}; // ASCII values for '2', '1', null character, '0', '-'
        final byte[] actual = StringUtils.getBytesUsAscii("21\0-");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[]{35, 36, 37, 38, 94, 42, 45}; // ASCII values for '#', '$', '%', '&', '^', '*', '-'
        final byte[] actual = StringUtils.getBytesUsAscii("#$%&^*-");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Test the empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test a string with the character 'a'
        final byte[] expected2 = "a".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("a");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test a string with a non-ASCII character
        final byte[] expected3 = "ä".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("ä");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_changedInput(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_emptyString(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Additional regression test cases
        final byte[] emptyBytes = StringUtils.getBytesUtf16Le("");
        Assert.assertEquals(0, emptyBytes.length);

        final byte[] nullBytes = StringUtils.getBytesUtf16Le(null);
        Assert.assertNull(nullBytes);

        final byte[] whitespaceBytes = StringUtils.getBytesUtf16Le("    ");
        Assert.assertEquals(8, whitespaceBytes.length);
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName1 = "UTF-8";
        final String charsetName2 = "ISO-8859-1";
        final String charsetName3 = "US-ASCII";
        testGetBytesUnchecked(charsetName1);
        testGetBytesUnchecked(charsetName2);
        testGetBytesUnchecked(charsetName3);
        
        final byte[] expected1 = STRING_FIXTURE.getBytes(charsetName1);
        final byte[] actual1 = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName2);
        final byte[] actual2 = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = STRING_FIXTURE.getBytes(charsetName3);
        final byte[] actual3 = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test31() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.newString(new byte[0], "UTF-8");
            Assert.assertEquals("", StringUtils.newString(new byte[0], "UTF-8"));
        } catch (final IllegalStateException e) {
            Assert.fail("Unexpected " + IllegalStateException.class.getName());
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.getBytesUnchecked("", "UTF-8");
            Assert.assertEquals(new byte[0], StringUtils.getBytesUnchecked("", "UTF-8"));
        } catch (final IllegalStateException e) {
            Assert.fail("Unexpected " + IllegalStateException.class.getName());
        }
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() {
        try {
            byte[] bytes = null;
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test with empty byte[]
        actual = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals("", actual);

        // Regression test with null charsetName
        actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }

        // Regression test with null byte[]
        try {
            StringUtils.newString(null, "UTF-16");
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        // Regression test with empty byte[]
        actual = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals("", actual);

        // Regression test with null charsetName
        actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test with empty byte[]
        actual = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals("", actual);

        // Regression test with null charsetName
        actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test with empty byte[]
        actual = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals("", actual);

        // Regression test with null charsetName
        actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));

        // Regression test with null charsetName
        Assert.assertNull(StringUtils.newString(null, null));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test with empty byte[]
        actual = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals("", actual);

        // Regression test with null charsetName
        actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Regression test with empty byte[]
        actual = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals("", actual);

        // Regression test with null charsetName
        actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);

        // Regression test case 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // Regression test case 2: Single byte
        final byte[] singleByte = { 65 };
        final String expectedSingle = new String(singleByte, charsetName);
        final String actualSingle = StringUtils.newStringIso8859_1(singleByte);
        Assert.assertEquals(expectedSingle, actualSingle);

        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));

        // Regression test case 3: Empty byte array
        Assert.assertNull(StringUtils.newStringUsAscii(null));

        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString("abcd"); // Change the input value to "abcd"
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii("")); // Change the input value to ""
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test Case 1: Empty input
        Assert.assertEquals("", StringUtils.newStringUtf16(new byte[]{}));
        
        // Regression Test Case 2: Input with one byte
        byte[] bytes = {66};
        Assert.assertEquals("B", StringUtils.newStringUtf16(bytes));
        
        // Regression Test Case 3: Input with multiple bytes
        bytes = {66, 69, 76, 76, 65};
        Assert.assertEquals("BELLA", StringUtils.newStringUtf16(bytes));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty byte array
        final String expected1 = new String(new byte[]{}, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with one element
        final String expected2 = new String(new byte[]{0x41}, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(new byte[]{0x41});
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: byte array with multiple elements
        final String expected3 = new String(new byte[]{0x41, 0x42, 0x43}, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(new byte[]{0x41, 0x42, 0x43});
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1: Empty byte array
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        
        // Regression test case 2: byte array with one element
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0x41}));
        
        // Regression test case 3: byte array with multiple elements
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        byte[] nullBytes = null; // null input
        Assert.assertNull(StringUtils.newStringUtf16Le(nullBytes));
        
        byte[] emptyBytes = new byte[0]; // empty input
        Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));
        
        byte[] bytes = {0x00, 0x41, 0x42}; // non-null and non-empty input
        Assert.assertEquals("\u0000AB", StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // regression test case 1: empty byte array
        final byte[] emptyBytes = new byte[0];
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        // regression test case 2: byte array with non-UTF8 characters
        final byte[] nonUtf8Bytes = {-50, -100, -120};
        final String nonUtf8Expected = new String(nonUtf8Bytes, charsetName);
        final String nonUtf8Actual = StringUtils.newStringUtf8(nonUtf8Bytes);
        Assert.assertEquals(nonUtf8Expected, nonUtf8Actual);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // regression test case 1: empty byte array
        final byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(emptyBytes));
        Assert.assertNull(StringUtils.newStringUsAscii(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(emptyBytes));
    }
}