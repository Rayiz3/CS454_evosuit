package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //New regression test cases
        final byte[] expectedNull = null;
        final byte[] actualNull = StringUtils.getBytesUtf16Be(null);
        Assert.assertArrayEquals(expectedNull, actualNull);
        
        final byte[] expectedEmpty = new byte[0];
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be("");
        Assert.assertArrayEquals(expectedEmpty, actualEmpty);
        
        final byte[] expectedSpecialChar = "ABC\u0026ABC".getBytes(charsetName);
        final byte[] actualSpecialChar = StringUtils.getBytesUtf16Be("ABC&ABC");
        Assert.assertArrayEquals(expectedSpecialChar, actualSpecialChar);
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //New regression test cases
        final byte[] expectedNull = null;
        final byte[] actualNull = StringUtils.getBytesUtf16Le(null);
        Assert.assertArrayEquals(expectedNull, actualNull);
        
        final byte[] expectedEmpty = new byte[0];
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertArrayEquals(expectedEmpty, actualEmpty);
        
        final byte[] expectedSpecialChar = "ABC\u02BAABC".getBytes(charsetName);
        final byte[] actualSpecialChar = StringUtils.getBytesUtf16Le("ABCʺABC");
        Assert.assertArrayEquals(expectedSpecialChar, actualSpecialChar);
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //New regression test cases
        final byte[] expectedNull = null;
        final byte[] actualNull = StringUtils.getBytesUsAscii(null);
        Assert.assertArrayEquals(expectedNull, actualNull);
        
        final byte[] expectedEmpty = new byte[0];
        final byte[] actualEmpty = StringUtils.getBytesUsAscii("");
        Assert.assertArrayEquals(expectedEmpty, actualEmpty);
        
        final byte[] expectedSpecialChar = "ABCABC".getBytes(charsetName);
        final byte[] actualSpecialChar = StringUtils.getBytesUsAscii("ABC\u20ACABC");
        Assert.assertArrayEquals(expectedSpecialChar, actualSpecialChar);
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //New regression test cases
        final byte[] expectedNull = null;
        final byte[] actualNull = StringUtils.getBytesUtf16(null);
        Assert.assertArrayEquals(expectedNull, actualNull);
        
        final byte[] expectedEmpty = new byte[0];
        final byte[] actualEmpty = StringUtils.getBytesUtf16("");
        Assert.assertArrayEquals(expectedEmpty, actualEmpty);
        
        final byte[] expectedSpecialChar = "ABC\uFFFFABC".getBytes(charsetName);
        final byte[] actualSpecialChar = StringUtils.getBytesUtf16("ABC��ABC");
        Assert.assertArrayEquals(expectedSpecialChar, actualSpecialChar);
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //New regression test cases
        final byte[] expectedNull = null;
        final byte[] actualNull = StringUtils.getBytesIso8859_1(null);
        Assert.assertArrayEquals(expectedNull, actualNull);
        
        final byte[] expectedEmpty = new byte[0];
        final byte[] actualEmpty = StringUtils.getBytesIso8859_1("");
        Assert.assertArrayEquals(expectedEmpty, actualEmpty);
        
        final byte[] expectedSpecialChar = "ABCABC".getBytes(charsetName);
        final byte[] actualSpecialChar = StringUtils.getBytesIso8859_1("ABC\u20ACABC");
        Assert.assertArrayEquals(expectedSpecialChar, actualSpecialChar);
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //New regression test cases
        final byte[] expectedNull = null;
        final byte[] actualNull = StringUtils.getBytesUtf8(null);
        Assert.assertArrayEquals(expectedNull, actualNull);
        
        final byte[] expectedEmpty = new byte[0];
        final byte[] actualEmpty = StringUtils.getBytesUtf8("");
        Assert.assertArrayEquals(expectedEmpty, actualEmpty);
        
        final byte[] expectedSpecialChar = "ABC\uFFFDABC".getBytes(charsetName);
        final byte[] actualSpecialChar = StringUtils.getBytesUtf8("ABC�ABC");
        Assert.assertArrayEquals(expectedSpecialChar, actualSpecialChar);
    }
    @Test(expected = NullPointerException.class)
    public void test6() throws UnsupportedEncodingException {
        StringUtils.getBytesIso8859_1(null);
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String input = "éåß";
        final byte[] expected = input.getBytes(Charsets.ISO_8859_1);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String input = "こんにちは";
        final byte[] expected = input.getBytes(Charsets.ISO_8859_1);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("NewInputValue1");  // Change the input value here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() {
        Assert.assertNull(StringUtils.getBytesUnchecked("null", "UNKNOWN"));  // Change the input value here
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("NewInputValue2");  // Change the input value here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("NewInputValue3");  // Change the input value here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("NewInputValue4");  // Change the input value here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("NewInputValue5");  // Change the input value here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("NewInputValue6");  // Change the input value here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() {
        try {
            StringUtils.getBytesUnchecked("InputValue", "UNKNOWN");  // Change the input value here
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#*&".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("!@#*&");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Changed input
        final byte[] actual = StringUtils.getBytesUtf16(""); // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName); // Changed input
        final byte[] actual = StringUtils.getBytesUtf16("Hello, World!"); // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "\uD83D\uDE00\uD83D\uDE03\u263A\uD83D\uDE04".getBytes(charsetName); // Changed input
        final byte[] actual = StringUtils.getBytesUtf16("\uD83D\uDE00\uD83D\uDE03\u263A\uD83D\uDE04"); // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName); // Changed input
        final byte[] actual = StringUtils.getBytesUtf16("12345"); // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        //Original test case
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        //Tests using different input values
        final String charsetName2 = "ISO-8859-1";
        testGetBytesUnchecked(charsetName2);
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName2);
        final byte[] actual2 = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected2, actual2));

        final String charsetName3 = "UTF-8";
        testGetBytesUnchecked(charsetName3);
        final byte[] expected3 = STRING_FIXTURE.getBytes(charsetName3);
        final byte[] actual3 = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected3, actual3));

        final String charsetName4 = "UTF-16";
        testGetBytesUnchecked(charsetName4);
        final byte[] expected4 = STRING_FIXTURE.getBytes(charsetName4);
        final byte[] actual4 = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String nonAsciiString = "中文测试";
        final byte[] expected = nonAsciiString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String specialChars = "!@#$%^&*()";
        final byte[] expected = specialChars.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(specialChars);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() {
        final String input = "";
        final byte[] expected = new byte[] {};
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test31() {
        final String input = null;
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test32() {
        final String input = "こんにちは";
        final byte[] expected = new byte[] {-29, -127, -109, -29, -126, -126, -29, -127, -109, -29, -128, -128};
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test33() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-U");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-U");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] bytes = new byte[] { 0, 0, 0, 0, 0, 0 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = new byte[] { -128, -127, -126, -125, -124 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = new byte[] { 65, 66, 67, 68, 69, 70 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        byte[] bytes = new byte[] { -27, -101, -67 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = new byte[] { 0, 1, 2, 3, 4, 5 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = new byte[] { 0, 1, 2, 3, 4, 5 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] bytes = {97, 98, 99};
        testNewString(charsetName, bytes);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = {97, 98, 99};
        testNewString(charsetName, bytes);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() {
        byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = {97, 98, 99};
        testNewString(charsetName, bytes);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        byte[] bytes = null;
        Assert.assertNull(StringUtils.newString(bytes, "UNKNOWN"));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = {97, 98, 99};
        testNewString(charsetName, bytes);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() {
        byte[] bytes = {97, 98, 99};
        try {
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        byte[] bytes = {97, 98, 99};
        testNewString(charsetName, bytes);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = {97, 98, 99};
        testNewString(charsetName, bytes);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // regression test to kill more mutants
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {}));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // regression test to kill more mutants
        final String expectedEmptyBytes = new String(new byte[] {}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringIso8859_1(new byte[] {});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // regression test to kill more mutants
        final String expectedNullBytes = new String((byte[]) null, charsetName);
        final String actualNullBytes = StringUtils.newStringIso8859_1((byte[]) null);
        Assert.assertEquals(expectedNullBytes, actualNullBytes);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] specialBytes = {65, 66, 67, 126};
        final String expected = new String(specialBytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(specialBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] nullBytes = null;
        final String expected = new String(nullBytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(nullBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = new byte[0];
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = {-84, -19, -69, -41, 0, 114, 0, 111, 0, 98, 0, 111, 0, 116};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = {0, 72, 0, 101, 0, 108, 0, 108, 0, 111};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final byte[] bytesEmpty = new byte[0];
        final String expectedEmpty = new String(bytesEmpty, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(bytesEmpty);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        final byte[] bytesNull = null;
        final String expectedNull = null;
        final String actualNull = StringUtils.newStringUtf16Be(bytesNull);
        Assert.assertEquals(expectedNull, actualNull);
    }
   @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";

        // regression test case 1: empty bytes
        byte[] bytes1 = {};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);

        // regression test case 2: bytes contains special characters
        byte[] bytes2 = {(byte) 0xFF, (byte) 0xFE, (byte) 0x41, (byte) 0x00, (byte) 0x42, (byte) 0x00};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);

        // regression test case 3: bytes contains null character
        byte[] bytes3 = {(byte) 0x41, (byte) 0x00, (byte) 0x42, (byte) 0x00, (byte) 0x00, (byte) 0x00};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);

        // existing test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case to kill more mutants
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case to kill more mutants
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] { (byte) 0xFF }));
    }
}