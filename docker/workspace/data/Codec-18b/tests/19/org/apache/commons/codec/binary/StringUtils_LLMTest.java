package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "abc"));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), "abc"));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
    Assert.assertFalse(StringUtils.equals(null, "abc"));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), ""));
}
@Test
public void test1() {
    Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
    Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), null));
    Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), null));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
    Assert.assertFalse(StringUtils.equals("", new StringBuilder("abc")));
}
@Test
public void test2() {
    Assert.assertFalse(StringUtils.equals("abc", null));
    Assert.assertTrue(StringUtils.equals(null, null));
    Assert.assertTrue(StringUtils.equals(null, "abc"));
    Assert.assertTrue(StringUtils.equals("abc", "abc"));
    Assert.assertFalse(StringUtils.equals("abcd", null));
    Assert.assertFalse(StringUtils.equals("abc", "abcd"));
    Assert.assertFalse(StringUtils.equals("", "abc"));
}
    @Test
    public void test3() {
        final String charsetName = "UTF-8";
        final String string = null;
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytes(string, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test4() {
        final String charsetName = "UTF-8";
        final String string = "";
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytes(string, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = UnsupportedCharsetException.class)
    public void test5() {
        final String charsetName = "UNSUPPORTED_CHARSET";
        final String string = "Hello, world!";
        StringUtils.getBytes(string, Charset.forName(charsetName));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "regression1".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("regression1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "regression2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("regression2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0]; // Empty string
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = {102, 111, 111, -61, -87}; // String with non US-ASCII character
        final byte[] actual = StringUtils.getBytesUsAscii("fooç");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = {32, 32, 32, 32, 32}; // String with only whitespace characters
        final byte[] actual = StringUtils.getBytesUsAscii("     ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test 1: Testing an empty string
        final byte[] expectedRegression1 = "".getBytes(charsetName);
        final byte[] actualRegression1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expectedRegression1, actualRegression1));

        // Regression test 2: Testing a string with special characters
        final String stringRegression2 = "\u00A9 All rights reserved.";
        final byte[] expectedRegression2 = stringRegression2.getBytes(charsetName);
        final byte[] actualRegression2 = StringUtils.getBytesUtf16(stringRegression2);
        Assert.assertTrue(Arrays.equals(expectedRegression2, actualRegression2));

        // Regression test 3: Testing a string with multibyte characters
        final String stringRegression3 = "漢字";
        final byte[] expectedRegression3 = stringRegression3.getBytes(charsetName);
        final byte[] actualRegression3 = StringUtils.getBytesUtf16(stringRegression3);
        Assert.assertTrue(Arrays.equals(expectedRegression3, actualRegression3));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Ω♫∑".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Ω♫∑");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "LE-UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "讲龙诗".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("讲龙诗");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("HELLO");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    private void testGetBytesUnchecked(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = "testString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUnchecked("testString", charsetName);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() {
        final String emptyString = "";
        final byte[] expected = emptyString.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(emptyString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() {
        final String nullString = null;
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf8(nullString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() {
        final String specialCharacters = "!@#$%^&*()";
        final byte[] expected = specialCharacters.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(specialCharacters);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() {
        final String longString = "This is a long string that will be encoded in UTF-8 format";
        final byte[] expected = longString.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(longString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() {
        final String nonAsciiString = "日本語";
        final byte[] expected = nonAsciiString.getBytes(Charsets.UTF_8);
        final byte[] actual = StringUtils.getBytesUtf8(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test28() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test29() {
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    }
    @Test
    public void test30() {
        Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test34() {
        Assert.assertNull(StringUtils.newString(BYTES_FIXTURE, "US"));
    }
    @Test
    public void test35() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "TEST");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        Assert.assertNull(StringUtils.newString(new byte[]{1, 2, 3}, "UNKNOWN"));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{1, 2, 3}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{1, 2, 3});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[]{1, 2, 3}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{1, 2, 3});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[]{1, 2, 3}, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{1, 2, 3});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{1, 2, 3}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{1, 2, 3}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{1, 2, 3}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{1, 2, 3}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{1, 2, 3}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{1, 2, 3}));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{1, 2, 3}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{1, 2, 3});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(new byte[]{1, 2, 3}, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[]{1, 2, 3});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{1, 2, 3}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{1, 2, 3});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        try {
            StringUtils.newString(new byte[]{1, 2, 3}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        // Change the input byte array to a different value
        byte[] bytes = { 65, 66, 67 };
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        // Change the input value to a non-null byte array
        byte[] bytes = { 65, 66, 67 };
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: passing an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test case 2: passing a byte array with only one element
        final byte[] singleByte = {65};
        final String expectedSingle = new String(singleByte, charsetName);
        final String actualSingle = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expectedSingle, actualSingle);
        
        // Regression test case 3: passing a byte array with multiple elements
        final byte[] multipleBytes = {65, 66, 67};
        final String expectedMultiple = new String(multipleBytes, charsetName);
        final String actualMultiple = StringUtils.newStringUsAscii(multipleBytes);
        Assert.assertEquals(expectedMultiple, actualMultiple);
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1: passing null as the byte array
        final String actualNull = StringUtils.newStringUsAscii(null);
        Assert.assertNull(actualNull);
        
        // Regression test case 2: passing a byte array with null element
        final byte[] bytesWithNull = {65, 0, 66, 67};
        final String actualWithNull = StringUtils.newStringUsAscii(bytesWithNull);
        Assert.assertNull(actualWithNull);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test cases
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        final byte[] nonAsciiBytes = new byte[] { (byte) 0xC4, (byte) 0xD6, (byte) 0xE4, (byte) 0xF6 };
        final String expectedNonAscii = new String(nonAsciiBytes, charsetName);
        final String actualNonAscii = StringUtils.newStringUtf16(nonAsciiBytes);
        Assert.assertEquals(expectedNonAscii, actualNonAscii);
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Additional test case
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        byte[] bytes1 = new byte[]{0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        String expected1 = "abc";
        String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        byte[] bytes2 = new byte[]{0x00, 0x48, 0x00, 0x65, 0x00, 0x6c, 0x00, 0x6c, 0x00, 0x6f};
        String expected2 = "Hello";
        String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1: Empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression Test 2: Large input
        final byte[] largeBytes = new byte[1000];
        for(int i = 0; i < 1000; i++) {
            largeBytes[i] = (byte) i;
        }
        final String expectedLarge = new String(largeBytes, charsetName);
        final String actualLarge = StringUtils.newStringUtf16Le(largeBytes);
        Assert.assertEquals(expectedLarge, actualLarge);
        
        // Regression Test 3: Invalid input (array containing non-UTF-16LE characters)
        final byte[] invalidBytes = new byte[] {0x41, 0x42, 0x43, 0x44}; // Characters 'A', 'B', 'C', 'D'
        final String expectedInvalid = new String(invalidBytes, charsetName);
        final String actualInvalid = StringUtils.newStringUtf16Le(invalidBytes);
        Assert.assertEquals(expectedInvalid, actualInvalid);
        
        
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression Test 4: Non-null input
        Assert.assertNotNull(StringUtils.newStringUtf16Le(new byte[1]));
    }
@Test
public void test58() {
    final byte[] emptyArray = new byte[0];
    final String expected = new String(emptyArray, Charsets.UTF_8);
    final String actual = StringUtils.newStringUtf8(emptyArray);
    Assert.assertEquals(expected, actual);
}
@Test
public void test59() {
    final byte[] bytes = "Hello, World!".getBytes(Charsets.UTF_8);
    final String expected = new String(bytes, null);
    final String actual = StringUtils.newStringUtf8(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test60() {
    final byte[] bytes = "Hello, World!".getBytes(Charsets.UTF_8);
    final String expected = new String(bytes, Charsets.ISO_8859_1);
    final String actual = StringUtils.newStringUtf8(bytes);
    Assert.assertEquals(expected, actual);
}
}