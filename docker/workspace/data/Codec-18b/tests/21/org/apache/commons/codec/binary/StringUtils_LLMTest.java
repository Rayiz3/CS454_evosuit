package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));

        // Regression Test Case 1
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("ABC")));
        // Regression Test Case 2
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abc"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));

        // Regression Test Case 1
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("ABC")));
        // Regression Test Case 2
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), null));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));

        // Regression Test Case 1
        Assert.assertTrue(StringUtils.equals("ABC", "abc"));
        // Regression Test Case 2
        Assert.assertFalse(StringUtils.equals("abc", "abcde"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Change input to null
        final byte[] actualNull = StringUtils.getBytesUtf16Be(null);
        Assert.assertNull(actualNull);
        
        // Change input to empty string
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals("".getBytes(charsetName), actualEmpty));
        
        // Change input to a string with special characters
        final String specialString = "üÜÖß";
        final byte[] expectedSpecial = specialString.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf16Be(specialString);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Change input to null
        final byte[] actualNull = StringUtils.getBytesIso8859_1(null);
        Assert.assertNull(actualNull);
        
        // Change input to empty string
        final byte[] actualEmpty = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals("".getBytes(charsetName), actualEmpty));
        
        // Change input to a string with special characters
        final String specialString = "üÜÖß";
        final byte[] expectedSpecial = specialString.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesIso8859_1(specialString);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "abc$!@";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "你好";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test with null input
        Assert.assertNull(StringUtils.getBytesUtf16Be(null));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test with empty string input
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test with non-latin string input
        final String nonLatinString = "你好";
        final byte[] expectedNonLatin = nonLatinString.getBytes(charsetName);
        final byte[] actualNonLatin = StringUtils.getBytesIso8859_1(nonLatinString);
        Assert.assertTrue(Arrays.equals(expectedNonLatin, actualNonLatin));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test with special characters input
        final String specialChars = "!@#$%^&*()";
        final byte[] expectedSpecialChars = specialChars.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUsAscii(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
    }
    @Test
    public void test12() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
        
        // Additional test with empty charsetName
        Assert.assertNull(StringUtils.getBytesUnchecked(STRING_FIXTURE, ""));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test with empty string input
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test with non-utf8 input
        final String nonUtf8String = "Привет";
        final byte[] expectedNonUtf8 = nonUtf8String.getBytes(charsetName);
        final byte[] actualNonUtf8 = StringUtils.getBytesUtf8(nonUtf8String);
        Assert.assertTrue(Arrays.equals(expectedNonUtf8, actualNonUtf8));
    }
    @Test
    public void test15() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Additional test with null input
        try {
            StringUtils.getBytesUnchecked(null, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression tests
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "test".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("test");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
@Test
public void test17() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test 
public void test18() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test19() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "123@#$%^&*ü".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("123@#$%^&*ü");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test20() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "a".repeat(10000).getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("a".repeat(10000));
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName); // existing test case, do not modify
        
        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: String with only one character
        final byte[] expected2 = "A".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("A");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with special characters
        final byte[] expected3 = "äöü".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("äöü");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String nonEnglishString = "你好世界";
        final byte[] expected = nonEnglishString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(nonEnglishString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String specialCharacters = "!@#$%^&*()";
        final byte[] expected = specialCharacters.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(specialCharacters);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_RegTest1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_RegTest2(charsetName);
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() {
        try {
            StringUtils.getBytesUnchecked("Hello World!", "UNKNOWN"); // change input value to "Hello World!"
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test29() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN"); // change input value to BYTES_FIXTURE
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test30() {
        try {
            StringUtils.getBytesUnchecked(null, "UNKNOWN"); // when input value is null
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, null); // when charsetName is null
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.newString(null, "UTF-8"); // when input value is null
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(BYTES_FIXTURE, null); // when charsetName is null
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16LE"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16BE"));
        Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
        Assert.assertNull(StringUtils.newString(null, "US-ASCII"));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        // Change input
        final String expected = new String(BYTES_FIXTURE_16BE, "ISO-8859-1");
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Change input
        final String expected = new String(BYTES_FIXTURE, "ISO-8859-1");
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        // Change input
        final String expected = new String(BYTES_FIXTURE, "ISO-8859-1");
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        // Change input
        final String expected = new String(BYTES_FIXTURE, "UTF-8");
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        // Change input
        final String expected = new String(BYTES_FIXTURE, "UTF-8");
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        // Change input
        final String expected = new String(BYTES_FIXTURE_16LE, "UTF-8");
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        // Add test case with different unknown encoding
        try {
            StringUtils.newString(BYTES_FIXTURE, "ANOTHER_UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[0];
        final String charsetName = "UTF-16BE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[0];
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        final byte[] bytes = { 97, 98, 99 };
        final String expected = new String(bytes);
        final String actual = StringUtils.newString(bytes, null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() {
        final byte[] bytes = { 100, 101, 102 };
        final String charsetName = "UTF-*%";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
   @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);

        // Regression test case 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected1, actual1);

        // Regression test case 2: Byte array with all zeroes
        final byte[] bytes2 = new byte[]{0, 0, 0, 0};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: Byte array with negative values
        final byte[] bytes3 = new byte[]{-1, -2, -3, -4};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case: Null byte array with different length
        final byte[] bytes = null;

        final String expected1 = new String(new byte[0], Charsets.US_ASCII);
        final String actual1 = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected1, actual1);

        final byte[] bytes2 = new byte[]{0, 0, 0, 0};
        final String expected2 = new String(bytes2, Charsets.US_ASCII);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);

        final byte[] bytes3 = new byte[]{127, 126, 125, 124};
        final String expected3 = new String(bytes3, Charsets.US_ASCII);
        final String actual3 = StringUtils.newStringUsAscii(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // New regression tests
        byte[] emptyBytes = new byte[]{};
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
        
        byte[] bytes = new byte[]{104, 101, 108, 108, 111};
        Assert.assertEquals("hello", StringUtils.newStringUtf16(bytes));
        
        byte[] bytesWithOneNullByte = new byte[]{104, 0, 101, 108, 108, 111};
        Assert.assertEquals("h\u0000ello", StringUtils.newStringUtf16(bytesWithOneNullByte));
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // New regression tests
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{104, 101, 108, 108, 111}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{104, 0, 101, 108, 108, 111}));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // New regression test case
        final byte[] bytes = {'H', 'e', 'l', 'l', 'o'};
        final String expected2 = new String(bytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test56() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // New regression test case
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x68, 0x00, 0x65, 0x00, 0x6c, 0x00, 0x6c, 0x00, 0x6f}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x00, 0x68, 0x00, 0x65, 0x00, 0x6c, 0x00, 0x6c, 0x00, 0x6f});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x68, 0x00, 0x65, 0x00, 0x6c, 0x00, 0x6c, 0x00, 0x6f}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x00, 0x68, 0x00, 0x65, 0x00, 0x6c, 0x00, 0x6c, 0x00, 0x6f});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final byte[] bytes = new byte[]{ (byte)243 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
}