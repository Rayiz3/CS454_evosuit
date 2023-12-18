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
        
        // Additional test case 1: Testing with empty string
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Additional test case 2: Testing with string containing special characters
        final String specialString = "Hello!@#$%^&*()";
        final byte[] specialExpected = specialString.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUtf16Be(specialString);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test case 1: Testing with empty string
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesIso8859_1(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Additional test case 2: Testing with string containing special characters
        final String specialString = "Hello!@#$%^&*()";
        final byte[] specialExpected = specialString.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesIso8859_1(specialString);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test case 1: Testing with empty string
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUtf16Le(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Additional test case 2: Testing with string containing special characters
        final String specialString = "Hello!@#$%^&*()";
        final byte[] specialExpected = specialString.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUtf16Le(specialString);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test case 1: Testing with empty string
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUtf8(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Additional test case 2: Testing with string containing special characters
        final String specialString = "Hello!@#$%^&*()";
        final byte[] specialExpected = specialString.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUtf8(specialString);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test case 1: Testing with empty string
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Additional test case 2: Testing with string containing special characters
        final String specialString = "Hello!@#$%^&*()";
        final byte[] specialExpected = specialString.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUsAscii(specialString);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Additional test case 1: Testing with empty string
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUtf16(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Additional test case 2: Testing with string containing special characters
        final String specialString = "Hello!@#$%^&*()";
        final byte[] specialExpected = specialString.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUtf16(specialString);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_oddLengthString(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_emptyString(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test11() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test12() {
        Assert.assertNull(StringUtils.getBytesUnchecked("", "UNKNOWN"));
    }
    @Test
    public void test13() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertArrayEquals(expected, actual);
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
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
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
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2: String with special characters
        final byte[] expected2 = "#@$!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("#@$!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with numerical values
        final byte[] expected3 = "1234567890".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("1234567890");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual1));
        
         // Regression test case 1
        String input2 = null;
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertNull(actual2);
        
         // Regression test case 2
        String input3 = "";
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertArrayEquals(new byte[]{0, 0}, actual3);
        
         // Regression test case 3
        String input4 = "test";
        final byte[] expected4 = input4.getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16(input4);
        Assert.assertArrayEquals(expected4, actual4);
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String input = "äöüß";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        // Test case 1: Empty string
        final String charsetName1 = "UTF-8";
        testGetBytesUnchecked(charsetName1);
        final byte[] expected1 = "".getBytes(charsetName1);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test case 2: String with special characters
        final String charsetName2 = "UTF-8";
        testGetBytesUnchecked(charsetName2);
        final byte[] expected2 = "!@#$%^&*()_+".getBytes(charsetName2);
        final byte[] actual2 = StringUtils.getBytesUtf8("!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test case 3: String with non-English characters
        final String charsetName3 = "UTF-8";
        testGetBytesUnchecked(charsetName3);
        final byte[] expected3 = "你好世界".getBytes(charsetName3);
        final byte[] actual3 = StringUtils.getBytesUtf8("你好世界");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "US-ASCII");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "US-ASCII");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(null); // Changed input to null
        // Assert that the returned value is null
        Assert.assertNull(actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(null, charsetName); // Changed input to null
        final String actual = StringUtils.newStringUtf8(null);
        // Assert that the returned value is null
        Assert.assertNull(actual);
    }
    @Test
    public void test47() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE)); // Added a test with non-null input
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii("abc"); // Changed input to "abc"
        // Assert that the returned value is equal to the expected value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(null);
        // Assert that the returned value is null
        Assert.assertNull(actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16("def"); // Changed input to "def"
        // Assert that the returned value is equal to the expected value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(null, null)); // Added a test with null charset
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString(BYTES_FIXTURE, null); // Changed charset to null
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(null); // Changed input to null
        // Assert that the returned value is null
        Assert.assertNull(actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[] {(byte)0x00, (byte)0x41, (byte)0x00, (byte)0x42});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] {(byte)0xC3, (byte)0xA9});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] {(byte)0x41, (byte)0x42});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] {(byte)0xE9});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[] {(byte)0x00, (byte)0x41, (byte)0x00, (byte)0x42});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] {(byte)0x41, (byte)0x00, (byte)0x42, (byte)0x00});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() {
        final byte[] bytes = { 72, 101, 108, 108, 111 };
        final String expected = "Hello";
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() {
        final byte[] bytes = { 65, 66, 67 };
        final String expected = "ABC";
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() {
        final byte[] bytes = { 37, 42, 35 };
        final String expected = "%*#";
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() {
        final byte[] bytes = new byte[0];
        final String expected = new String(bytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        final byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() {
        final byte[] bytes = {(byte) 200, (byte) 201, (byte) 202};
        final String expected = new String(bytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Test case 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Test case 2: Byte array with special characters
        final byte[] specialBytes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        final String expected2 = new String(specialBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16(specialBytes);
        Assert.assertEquals(expected2, actual2);
        
        // Test case 3: Byte array with non-ASCII characters
        final byte[] nonAsciiBytes = {(byte) 0xC4, (byte) 0xE4, (byte) 0xD6, (byte) 0xF6, (byte) 0xC5, (byte) 0xE5};
        final String expected3 = new String(nonAsciiBytes, charsetName);
        final String actual3 = StringUtils.newStringUtf16(nonAsciiBytes);
        Assert.assertEquals(expected3, actual3);
    }
   @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(null, charsetName);
        final String actual = StringUtils.newStringUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x10, 0x20, 0x30}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x10, 0x20, 0x30});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[0]); // Empty byte array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{1, 2, 3}); // Non-empty byte array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE); // Same input byte array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test cases
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertEquals("Hello world", StringUtils.newStringUtf8("Hello world".getBytes()));
        Assert.assertEquals("12345", StringUtils.newStringUtf8("12345".getBytes()));
        Assert.assertEquals("Special characters: ☺☃☻", StringUtils.newStringUtf8("Special characters: ☺☃☻".getBytes()));
    }
    @Test
    public void test74() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Additional test cases
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{1, 2, 3}));
        Assert.assertNull(StringUtils.newStringUtf8("".getBytes()));
        Assert.assertNull(StringUtils.newStringUtf8((byte[]) null));
    }
}