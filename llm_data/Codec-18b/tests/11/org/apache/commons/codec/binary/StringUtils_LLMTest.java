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
        
        // Regression test cases
        Assert.assertFalse(StringUtils.equals("", null));
        Assert.assertFalse(StringUtils.equals("123", null));
        Assert.assertFalse(StringUtils.equals("abc", ""));
        Assert.assertFalse(StringUtils.equals("abc", "def"));
        Assert.assertTrue(StringUtils.equals("abcdef", "abcdef"));
        Assert.assertTrue(StringUtils.equals("abcdefg", "abcdefg"));
        Assert.assertTrue(StringUtils.equals("abcde", "abcde"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
        
        // Regression test cases
        Assert.assertFalse(StringUtils.equals(new StringBuilder(""), null));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("123"), null));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("def")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcdef"), new StringBuilder("abcdef")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcdefg"), new StringBuilder("abcdefg")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcde"), new StringBuilder("abcde")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
        
        // Regression test cases
        Assert.assertTrue(StringUtils.equals("", new StringBuilder("")));
        Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("def")));
        Assert.assertTrue(StringUtils.equals("abcdef", new StringBuilder("abcdef")));
        Assert.assertTrue(StringUtils.equals("abcdefg", new StringBuilder("abcdefg")));
        Assert.assertTrue(StringUtils.equals("abcde", new StringBuilder("abcde")));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final byte[] actualNullString = StringUtils.getBytesUsAscii(null);
        Assert.assertNull(actualNullString);
        final byte[] actualEmptyString = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(new byte[0], actualEmptyString));
        final byte[] actualNonAsciiString = StringUtils.getBytesUsAscii("日本語");
        Assert.assertTrue(Arrays.equals(new byte[0], actualNonAsciiString));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final byte[] actualNullString = StringUtils.getBytesUtf16Le(null);
        Assert.assertNull(actualNullString);
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final byte[] actualNullString = StringUtils.getBytesUtf16Be(null);
        Assert.assertNull(actualNullString);
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final byte[] actualNullString = StringUtils.getBytesIso8859_1(null);
        Assert.assertNull(actualNullString);
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final byte[] actualNullString = StringUtils.getBytesUtf8(null);
        Assert.assertNull(actualNullString);
        final byte[] actualEmptyString = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(new byte[0], actualEmptyString));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final byte[] actualNullString = StringUtils.getBytesUtf16(null);
        Assert.assertNull(actualNullString);
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_withNullString(charsetName);
        final String string = null;
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_withEmptyString(charsetName);
        final String string = "";
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_withSpecialCharacters(charsetName);
        final String string = " !@#$%^&*()";
        final byte[] expected = new byte[] {32, 33, 64, 35, 36, 37, 94, 38, 42, 40, 41};
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "another string".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("another string");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "another string".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("another string");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "another string".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("another string");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "another string".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("another string");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() {
        try {
            StringUtils.getBytesUnchecked("another string", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() {
        Assert.assertNull(StringUtils.getBytesUnchecked("another string", "UNKNOWN"));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "another string".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("another string");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "another string".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("another string");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);

        // New test case: empty string
        String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // New test case: string with special characters
        String specialCharacters = " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
        final byte[] expectedSpecial = specialCharacters.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUsAscii(specialCharacters);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));

        // New test case: string with lowercase letters
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        final byte[] expectedLowercase = lowercaseLetters.getBytes(charsetName);
        final byte[] actualLowercase = StringUtils.getBytesUsAscii(lowercaseLetters);
        Assert.assertTrue(Arrays.equals(expectedLowercase, actualLowercase));
    }
    @Test
    public void test21() {
        final String input = "This is a test string";
        final byte[] expected = StringUtils.getBytes("This is a test string", Charsets.UTF_16);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() {
        final String input = "";
        final byte[] expected = StringUtils.getBytes("", Charsets.UTF_16);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() {
        final String input = "abcdefghijklmnopqrstuvwxyz".repeat(100);
        final byte[] expected = StringUtils.getBytes("abcdefghijklmnopqrstuvwxyz".repeat(100), Charsets.UTF_16);
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
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test26() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "漢字".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("漢字");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";

        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Test case with empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Test case with string containing only spaces
        final byte[] expectedSpaces = "    ".getBytes(charsetName);
        final byte[] actualSpaces = StringUtils.getBytesUtf16Le("    ");
        Assert.assertTrue(Arrays.equals(expectedSpaces, actualSpaces));

        // Test case with string containing special characters
        final byte[] expectedSpecialChars = "¡^«§¯±".getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf16Le("¡^«§¯±");
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf8("  ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() {
        try {
            StringUtils.newString("".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.getBytesUnchecked("", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // regression test case - empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected1, actual1);
    }
    @Test
    public void test38() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // regression test case - empty byte array
        try {
            StringUtils.newString(new byte[0], "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // regression test case - empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected1, actual1);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // regression test case - empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected1, actual1);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // regression test case - empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected1, actual1);
    }
    @Test
    public void test42() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        
        // regression test case - empty byte array
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // regression test case - empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected1, actual1);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // regression test case - empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected1, actual1);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // regression test case - empty byte array
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] emptyBytes = new byte[]{};
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newString(emptyBytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final byte[] emptyBytes = new byte[]{};
        try {
            StringUtils.newString(emptyBytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] emptyBytes = new byte[]{};
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newString(emptyBytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] emptyBytes = new byte[]{};
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newString(emptyBytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] emptyBytes = new byte[]{};
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newString(emptyBytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] emptyBytes = new byte[]{};
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newString(emptyBytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] emptyBytes = new byte[]{};
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newString(emptyBytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{(byte) 0x61, (byte) 0x62, (byte) 0x63};
        final String charsetName = null;

        try {
            StringUtils.newString(bytes, charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final byte[] bytes = null;
        final String charsetName = "ISO-8859-1";

        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final byte[] bytes = null;
        final String charsetName = "UNKNOWN";

        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final byte[] bytes = null;
        final String charsetName = "UTF-16BE";

        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final byte[] bytes = null;
        final String charsetName = "UTF-16";

        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final byte[] bytes = null;
        final String charsetName = "US-ASCII";

        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final byte[] bytes = null;
        final String charsetName = "UTF-8";

        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final byte[] bytes = null;
        final String charsetName = "UTF-16LE";

        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: empty input
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test 2: input with special characters
        final byte[] specialBytes = { (byte) 97, (byte) 98, (byte) 99, (byte) 128, (byte) 161, (byte) 254 };
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringIso8859_1(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test62() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: empty input
        final byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(emptyBytes));
        Assert.assertNull(StringUtils.newStringUsAscii(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(emptyBytes));
        
        // Regression test 2: array with one null element
        final byte[] bytesWithNull = { (byte) 97, (byte) 98, (byte) 99, (byte) 0, (byte) 100 };
        Assert.assertNull(StringUtils.newStringUtf8(bytesWithNull));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUsAscii(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUtf16(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytesWithNull));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] {105, 110, 118, 97, 108, 105, 100});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {105, 110, 118, 97, 108, 105, 100}));
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test 1 - empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));

        // Regression test 2 - byte array with one null byte
        byte[] nullByte = {0};
        Assert.assertEquals("", StringUtils.newStringUtf16(nullByte));

        // Regression test 3 - byte array with special characters
        byte[] specialChars = {-15, -103, -111, -111};
        Assert.assertEquals("इल्ल", StringUtils.newStringUtf16(specialChars));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test 1 - empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));

        // Regression test 2 - byte array with one null byte
        byte[] nullByte = {0};
        Assert.assertEquals("", StringUtils.newStringUtf16(nullByte));

        // Regression test 3 - byte array with special characters
        byte[] specialChars = {-15, -103, -111, -111};
        Assert.assertEquals("इल्ल", StringUtils.newStringUtf16(specialChars));
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Change input to empty byte array
        final String expected2 = new String(new byte[0], charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected2, actual2);
        
        // Change input to byte array with non-UTF-16BE characters
        final byte[] bytes3 = new byte[]{0x41, 0x42, 0x43, 0x44};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test68() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Change input to empty byte array
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        
        // Change input to byte array with non-UTF-16BE characters
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0x41, 0x42, 0x43, 0x44}));
    }
    @Test
    public void test69() {
        // Covering test
        Assert.assertNull(StringUtils.newStringUtf8(null));
        // Regression test
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));

        // Covering test
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        // Regression test
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));

        // Covering test
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        // Regression test
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));

        // Covering test
        Assert.assertNull(StringUtils.newStringUtf16(null));
        // Regression test
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));

        // Covering test
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        // Regression test
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));

        // Covering test
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        // Regression test
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);

        // Regression test
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        final byte[] emptyBytes = {}; 
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
        
        final byte[] accentBytes = { 0x61, (byte)0xC3, (byte)0xA9, 0x62 }; 
        Assert.assertEquals("aÃ©b", StringUtils.newStringUtf8(accentBytes));
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test cases
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
    }
}