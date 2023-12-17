package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), "abc"));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABCD"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
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
        final byte[] expected = null; // Changed input to null
        final byte[] actual = StringUtils.getBytesUtf16Be(null); // Changed input to null
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Changed input to empty string
        final byte[] actual = StringUtils.getBytesIso8859_1(""); // Changed input to empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "ABC".getBytes(charsetName); // Changed input to a string with non-ASCII characters
        final byte[] actual = StringUtils.getBytesUsAscii("ABCí"); // Changed input to a string with non-ASCII characters
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "\uFFFD\uFFFD".getBytes(charsetName); // Changed input to a string with invalid UTF-8 characters
        final byte[] actual = StringUtils.getBytesUtf8("\uFFFD\uFFFD"); // Changed input to a string with invalid UTF-8 characters
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0]; // Changed input to an empty byte array
        final byte[] actual = StringUtils.getBytesUtf16Le(""); // Changed input to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Changed input to an empty string
        final byte[] actual = StringUtils.getBytesUtf16(""); // Changed input to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test15() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    
    testGetBytesUnchecked(charsetName);
    
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("");
    
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test16() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    
    testGetBytesUnchecked(charsetName);
    
    final byte[] expected = "¡Hello World!".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("¡Hello World!");
    
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test17() {
    try {
        StringUtils.getBytesIso8859_1(null);
        Assert.fail("Expected NullPointerException to be thrown");
    } catch (NullPointerException e) {
        Assert.assertTrue(true);
    }
}
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() {
        Assert.assertNull(StringUtils.getBytesUnchecked("Hello World", "UNKNOWN"));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() {
        try {
            StringUtils.getBytesUnchecked("Hello World", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test26() {
        final String emptyString = "";
        final byte[] expectedEmpty = new byte[0];
        final byte[] actualEmpty = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertArrayEquals(expectedEmpty, actualEmpty);
        
        final String nonAsciiString = "Ж";
        final byte[] expectedNonAscii = new byte[0];
        final byte[] actualNonAscii = StringUtils.getBytesUsAscii(nonAsciiString);
        Assert.assertArrayEquals(expectedNonAscii, actualNonAscii);
        
        final String specialChars = "B@1!a%z";
        final byte[] expectedSpecialChars = new byte[]{66, 64, 49, 33, 97, 37, 122};
        final byte[] actualSpecialChars = StringUtils.getBytesUsAscii(specialChars);
        Assert.assertArrayEquals(expectedSpecialChars, actualSpecialChars);
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test 1: Change input to an empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression Test 2: Change input to a string with a single character
        final String singleCharString = "A";
        final byte[] expectedSingleChar = singleCharString.getBytes(charsetName);
        final byte[] actualSingleChar = StringUtils.getBytesUtf16(singleCharString);
        Assert.assertTrue(Arrays.equals(expectedSingleChar, actualSingleChar));
        
        // Regression Test 3: Change input to a string with multiple characters
        final String multiCharString = "Hello";
        final byte[] expectedMultiChar = multiCharString.getBytes(charsetName);
        final byte[] actualMultiChar = StringUtils.getBytesUtf16(multiCharString);
        Assert.assertTrue(Arrays.equals(expectedMultiChar, actualMultiChar));
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
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertSame(expected, actual);
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "こんにちは".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("こんにちは");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Test with empty string
        byte[] expected = "".getBytes(charsetName);
        byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Test with string containing only one character
        expected = "a".getBytes(charsetName);
        actual = StringUtils.getBytesUtf16Le("a");
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Test with string containing multiple characters
        expected = "Hello, world!".getBytes(charsetName);
        actual = StringUtils.getBytesUtf16Le("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String input = "Hello, World! 日本語";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String input = "Hello, World! 😊❤️";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String input = "Hello, World!\t\n";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() {
        try {
            StringUtils.getBytesUnchecked("Hello World", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test37() {
        try {
            StringUtils.newString("Hello World".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked("", "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(new byte[]{}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(null, "UTF-8")); // additional test case
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final String otherCharsetName = "UTF-16"; // additional test case
        final String otherExpected = new String(BYTES_FIXTURE_16BE, otherCharsetName);
        final String otherActual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(otherExpected, otherActual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String otherCharsetName = "US-ASCII"; // additional test case
        final String otherExpected = new String(BYTES_FIXTURE, otherCharsetName);
        final String otherActual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(otherExpected, otherActual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String otherCharsetName = "ISO-8859-1"; // additional test case
        final String otherExpected = new String(BYTES_FIXTURE, otherCharsetName);
        final String otherActual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(otherExpected, otherActual);
    }
    @Test
    public void test46() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newString("UNKNOWN".getBytes(), "UNKNOWN")); // additional test case
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String otherCharsetName = "UTF-16LE"; // additional test case
        final String otherExpected = new String(BYTES_FIXTURE, otherCharsetName);
        final String otherActual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(otherExpected, otherActual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String otherCharsetName = "UTF-8"; // additional test case
        final String otherExpected = new String(BYTES_FIXTURE, otherCharsetName);
        final String otherActual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(otherExpected, otherActual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final String otherCharsetName = "UTF-16"; // additional test case
        final String otherExpected = new String(BYTES_FIXTURE_16LE, otherCharsetName);
        final String otherActual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(otherExpected, otherActual);
    }
    @Test
    public void test50() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString(BYTES_FIXTURE, "US-ASCII"); // additional test case
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[]{}, "UNKNOWN"));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test55() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test59() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString(new byte[]{}, "UNKNOWN");
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
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    
    // Regression Test Case 1
    final String expected2 = new String(new byte[]{}, charsetName);
    final String actual2 = StringUtils.newStringIso8859_1(new byte[]{});
    Assert.assertEquals(expected2, actual2);
    
    // Regression Test Case 2
    final String expected3 = new String(new byte[]{65, 66, 67}, charsetName);
    final String actual3 = StringUtils.newStringIso8859_1(new byte[]{65, 66, 67});
    Assert.assertEquals(expected3, actual3);
    
    // Regression Test Case 3
    final String expected4 = new String(new byte[]{-1, -1, -1}, charsetName);
    final String actual4 = StringUtils.newStringIso8859_1(new byte[]{-1, -1, -1});
    Assert.assertEquals(expected4, actual4);
}
@Test
public void test61() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    
    // Regression Test Case 4
    Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
    
    // Regression Test Case 5
    Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{65, 66, 67}));
    
    // Regression Test Case 6
    Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{-1, -1, -1}));
}
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty input
        final String expected2 = new String(new byte[0], charsetName);
        final String actual2 = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 2: Input with special characters
        final byte[] bytes3 = {65, 66, 67, 32, 126, 33, 63}; // ABC ~!?
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 3: Null input with non-null default
        final String charsetName = "US-ASCII";
        final String defaultString = "Default string";
        final String expected2 = defaultString;
        final String actual2 = StringUtils.newStringUsAscii(null, defaultString);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        
        // Additional test case 1: Changing the input to an empty byte array
        final String actual1 = StringUtils.newStringUtf16(new byte[]{});
        Assert.assertEquals(expected, actual1);
        
        // Additional test case 2: Changing the input to a byte array containing special characters
        final String actual2 = StringUtils.newStringUtf16(new byte[]{65, 66, 67, -1, -2, -3});
        Assert.assertEquals(expected, actual2);
        
        // Additional test case 3: Changing the input to a byte array containing null bytes
        final String actual3 = StringUtils.newStringUtf16(new byte[]{0, 0, 0, 0, 0, 0});
        Assert.assertEquals("", actual3);
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Additional test case 4: Changing the input to a non-null byte array
        final String actual4 = StringUtils.newStringUtf16(new byte[]{65, 66, 67});
        Assert.assertEquals("ABC", actual4);
        
        // Additional test case 5: Changing the input to a byte array containing only null bytes
        final String actual5 = StringUtils.newStringUtf16(new byte[]{0, 0, 0, 0});
        Assert.assertEquals("", actual5);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case - Mutant 1
        byte[] bytesFixture = {(byte) 0x00, (byte) 0x61, (byte) 0x00, (byte) 0x62, (byte) 0x00, (byte) 0x63};
        final String expected1 = new String(bytesFixture, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytesFixture);
        Assert.assertEquals(expected1, actual1);

        // Regression test case - Mutant 2
        byte[] bytesFixture2 = {(byte) 0x00, (byte) 0x61, (byte) 0x00, (byte) 0x62, (byte) 0x00, (byte) 0x63, (byte) 0x00};
        final String expected2 = new String(bytesFixture2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytesFixture2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test67() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case - Mutant 3
        byte[] nullBytes = null;
        Assert.assertNull(StringUtils.newStringUtf16Be(nullBytes));
    }
@Test
public void test68() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString(charsetName);

    // New regression test cases
    final byte[] emptyBytes = {};
    Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));

    final byte[] bytesWithNull = {97, 0, 98, 0, 0};
    Assert.assertEquals("a", StringUtils.newStringUtf16Le(bytesWithNull));

    final byte[] bytesWithWhiteSpace = {97, 0, 32, 0, 98, 0};
    Assert.assertEquals("a b", StringUtils.newStringUtf16Le(bytesWithWhiteSpace));
}
@Test
public void test69() {
    // Unchanged test cases
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));

    // Additional test cases
    final byte[] emptyBytes = {};
    Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
    Assert.assertNull(StringUtils.newStringIso8859_1(emptyBytes));
    Assert.assertNull(StringUtils.newStringUsAscii(emptyBytes));
    Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
    Assert.assertNull(StringUtils.newStringUtf16Be(emptyBytes));
    Assert.assertNull(StringUtils.newStringUtf16Le(emptyBytes));
}
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test case 1: Null input
        Assert.assertNull(StringUtils.newStringUtf8(null));

        // Regression test case 2: Empty input
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[0]));

        // Regression test case 3: Input with single byte
        final byte[] singleByteBytes = {65}; // 'A' in ASCII
        final String expected2 = new String(singleByteBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf8(singleByteBytes);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test71() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case 1: Empty input
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[0]));

        // Regression test case 2: Input with single byte
        final byte[] singleByteBytes = {65}; // 'A' in ASCII
        final String expected2 = new String(singleByteBytes, Charset.forName("ISO-8859-1"));
        final String actual2 = StringUtils.newStringIso8859_1(singleByteBytes);
        Assert.assertEquals(expected2, actual2);
    }
}