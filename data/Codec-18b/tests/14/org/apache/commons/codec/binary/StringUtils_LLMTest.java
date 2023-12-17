package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), ""));
        Assert.assertFalse(StringUtils.equals("", new StringBuilder("abc")));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(null, ""));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertTrue(StringUtils.equals("", ""));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), ""));
        Assert.assertFalse(StringUtils.equals("", new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertTrue(StringUtils.equals("", ""));
        Assert.assertFalse(StringUtils.equals("abc", ""));
        Assert.assertFalse(StringUtils.equals("", "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
    }
@Test
public void test3() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    final String emptyString = "";
    final byte[] expected = emptyString.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be(emptyString);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test4() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    final String nullString = null;
    final byte[] expected = null;
    final byte[] actual = StringUtils.getBytesIso8859_1(nullString);
    Assert.assertEquals(expected, actual);
}
@Test
public void test5() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    final String emptyString = "";
    final byte[] expected = emptyString.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUsAscii(emptyString);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test6() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    final String nullString = null;
    final byte[] expected = null;
    final byte[] actual = StringUtils.getBytesUtf8(nullString);
    Assert.assertEquals(expected, actual);
}
@Test
public void test7() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    final String emptyString = "";
    final byte[] expected = emptyString.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(emptyString);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test8() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    final String nullString = null;
    final byte[] expected = null;
    final byte[] actual = StringUtils.getBytesUtf16(nullString);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "iso-8859-1";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO_8859-1";
        testGetBytesUnchecked_regression3(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression4(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO8859-1";
        testGetBytesUnchecked_regression5(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        
        // Regression test 1: Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: Non-ASCII characters
        final String nonAsciiString = "안녕하세요";
        final byte[] expectedNonAscii = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesUtf16Be(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        
        // Regression test 1: Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: Non-ASCII characters
        final String nonAsciiString = "안녕하세요";
        final byte[] expectedNonAscii = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesUtf16Le(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        
        // Regression test 1: Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesIso8859_1(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: Non-ASCII characters
        final String nonAsciiString = "안녕하세요";
        final byte[] expectedNonAscii = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesIso8859_1(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        
        // Regression test 1: Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: Non-ASCII characters
        final String nonAsciiString = "안녕하세요";
        final byte[] expectedNonAscii = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesUsAscii(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        
        // Regression test 1: Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: Non-ASCII characters
        final String nonAsciiString = "안녕하세요";
        final byte[] expectedNonAscii = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesUtf16(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        
        // Regression test 1: Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf8(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: Non-ASCII characters
        final String nonAsciiString = "안녕하세요";
        final byte[] expectedNonAscii = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesUtf8(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final String input = "!@#$%^&*()_+-={}[]|\\:\";'<>?,./";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        
        // Testing with null input
        final byte[] expectedNullInput = null;
        final byte[] actualNullInput = StringUtils.getBytesUtf16(null);
        Assert.assertArrayEquals(expectedNullInput, actualNullInput);
        
        // Testing with empty string input
        final byte[] expectedEmptyInput = "".getBytes(charsetName);
        final byte[] actualEmptyInput = StringUtils.getBytesUtf16("");
        Assert.assertArrayEquals(expectedEmptyInput, actualEmptyInput);
        
        // Testing with whitespace input
        final byte[] expectedWhitespaceInput = " ".getBytes(charsetName);
        final byte[] actualWhitespaceInput = StringUtils.getBytesUtf16(" ");
        Assert.assertArrayEquals(expectedWhitespaceInput, actualWhitespaceInput);
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        
        // Testing with non-UTF-16 input
        final String nonUtf16Input = "Hello, world!";
        final byte[] expectedNonUtf16Input = nonUtf16Input.getBytes(charsetName);
        final byte[] actualNonUtf16Input = StringUtils.getBytesUtf16(nonUtf16Input);
        Assert.assertArrayEquals(expectedNonUtf16Input, actualNonUtf16Input);
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        // original test case
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // additional test cases
        final String charsetName2 = "UTF-16BE";
        final byte[] expected2 = null;
        final byte[] actual2 = StringUtils.getBytesUtf16Be(null);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final String charsetName3 = "UTF-16BE";
        final byte[] expected3 = new byte[]{};
        final byte[] actual3 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        final String charsetName4 = "UTF-16BE";
        final byte[] expected4 = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16Be(STRING_FIXTURE + "extra");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
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
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() {
        try {
            StringUtils.getBytesUnchecked("Hello World!", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() {
        try {
            StringUtils.newString(new byte[]{72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[]{}, "UNKNOWN")); // additional test case: empty byte array
        Assert.assertNull(StringUtils.newString(new byte[]{1,2,3}, "UNKNOWN")); // additional test case: non-empty byte array
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName); // additional test case: different byte array
        final String actual2 = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16LE);
        Assert.assertNotEquals(expected2, actual2);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName); // additional test case: different byte array
        final String actual2 = StringUtils.newStringUtf8(BYTES_FIXTURE_16LE);
        Assert.assertNotEquals(expected2, actual2);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName); // additional test case: different byte array
        final String actual2 = StringUtils.newStringUsAscii(BYTES_FIXTURE_16LE);
        Assert.assertNotEquals(expected2, actual2);
    }
    @Test
    public void test36() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newString(null, null)); // additional test case: null charset
        Assert.assertNull(StringUtils.newString(null, "UTF-8")); // additional test case: null bytes
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName); // additional test case: different byte array
        final String actual2 = StringUtils.newStringIso8859_1(BYTES_FIXTURE_16LE);
        Assert.assertNotEquals(expected2, actual2);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName); // additional test case: different byte array
        final String actual2 = StringUtils.newStringUtf16(BYTES_FIXTURE_16LE);
        Assert.assertNotEquals(expected2, actual2);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(BYTES_FIXTURE_16BE, charsetName); // additional test case: different byte array
        final String actual2 = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected2, actual2);
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString(BYTES_FIXTURE, null); // additional test case: null charset
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
        try {
            StringUtils.newString(null, "UTF-8"); // additional test case: null bytes
            Assert.fail("Expected " + NullPointerException.class.getName());
        } catch (final NullPointerException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        // null input
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        // empty byte array
        testNewString(new byte[0], charsetName);
        // byte array with one element
        testNewString(new byte[] { 65 }, charsetName);
        // byte array with multiple elements
        testNewString(new byte[] { 65, 66, 67 }, charsetName);
        
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        // empty byte array
        testNewString(new byte[0], charsetName);
        // byte array with one element
        testNewString(new byte[] { 65 }, charsetName);
        // byte array with multiple elements
        testNewString(new byte[] { 65, 66, 67 }, charsetName);
        
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        // empty byte array
        testNewString(new byte[0], charsetName);
        // byte array with one element
        testNewString(new byte[] { 65 }, charsetName);
        // byte array with multiple elements
        testNewString(new byte[] { 65, 66, 67 }, charsetName);
        
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        // null input
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
        // empty byte array
        testNewString(new byte[0], charsetName);
        // byte array with one element
        testNewString(new byte[] { 65 }, charsetName);
        // byte array with multiple elements
        testNewString(new byte[] { 65, 66, 67 }, charsetName);
        
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        // empty byte array
        testNewString(new byte[0], charsetName);
        // byte array with one element
        testNewString(new byte[] { 65 }, charsetName);
        // byte array with multiple elements
        testNewString(new byte[] { 65, 66, 67 }, charsetName);
        
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        // empty byte array
        testNewString(new byte[0], charsetName);
        // byte array with one element
        testNewString(new byte[] { 65 }, charsetName);
        // byte array with multiple elements
        testNewString(new byte[] { 65, 66, 67 }, charsetName);
        
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() {
        try {
            // empty byte array
            StringUtils.newString(new byte[0], "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    private void testNewString(byte[] bytes, String charsetName) {
        try {
            Assert.assertNotNull(StringUtils.newString(bytes, charsetName));
        } catch (final Exception e) {
            // Exception not expected
            Assert.fail("Unexpected exception: " + e.getMessage());
        }
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: Byte array with special characters
        byte[] specialBytes = {(byte) 0xE5, (byte) 0xE6, (byte) 0xE7};
        final String expected2 = new String(specialBytes, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(specialBytes);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 3: Null byte array
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] inputBytes = {};
        final String expected = new String(inputBytes, charsetName);
        final String actual = StringUtils.newStringUtf16(inputBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] inputBytes = {65};
        final String expected = new String(inputBytes, charsetName);
        final String actual = StringUtils.newStringUtf16(inputBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] inputBytes = {65, 66, 67, 68};
        final String expected = new String(inputBytes, charsetName);
        final String actual = StringUtils.newStringUtf16(inputBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() {
        final String actual = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals("", actual);
    }
    @Test
    public void test59() {
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x00});
        Assert.assertEquals("\u0000\u0000", actual);
    }
    @Test
    public void test60() {
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x48, 0x65, 0x6C, 0x6C, 0x6F, (byte) 0xD8, (byte) 0x41, (byte) 0xDC});
        Assert.assertEquals("Hello\uD841\uDC01", actual);
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
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[] {65}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] {65});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected2 = new String(new byte[0], charsetName);
        final String actual2 = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 2: byte array with one element
        final String expected3 = new String(new byte[]{65}, charsetName);
        final String actual3 = StringUtils.newStringUtf8(new byte[]{65});
        Assert.assertEquals(expected3, actual3);
        
        // Regression test case 3: byte array with special characters
        final String expected4 = new String(new byte[]{65, -30, -128, -1}, charsetName);
        final String actual4 = StringUtils.newStringUtf8(new byte[]{65, -30, -128, -1});
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test66() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 4: empty byte array
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Regression test case 5: byte array with one element
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{65}));
        
        // Regression test case 6: byte array with special characters
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{65, -30, -128, -1}));
    }
}