package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertFalse(StringUtils.equals(null, ""));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder(""), null));
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals("", new StringBuilder("")));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "¡Hola, mundo!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("¡Hola, mundo!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "   ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("   ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Test String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Test String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Test String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Test String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Test String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Test String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("!@#$%");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "你好".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("你好");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO world".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("HELLO world");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
    public void test36() throws UnsupportedEncodingException {
        // Test with empty string
        final String charsetName1 = "UTF-16";
        testGetBytesUnchecked(charsetName1);
        final byte[] expected1 = "".getBytes(charsetName1);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test with string consisting of only one character
        final String charsetName2 = "UTF-16";
        testGetBytesUnchecked(charsetName2);
        final byte[] expected2 = "a".getBytes(charsetName2);
        final byte[] actual2 = StringUtils.getBytesUtf16("a");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test with string having multiple characters
        final String charsetName3 = "UTF-16";
        testGetBytesUnchecked(charsetName3);
        final byte[] expected3 = "Hello World!".getBytes(charsetName3);
        final byte[] actual3 = StringUtils.getBytesUtf16("Hello World!");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // New test case: empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // New test case: single character string
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "A".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("A");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // New test case: string with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected4 = "Hello, 世界".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16Be("Hello, 世界");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: changing the input to an empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test case 2: changing the input to a string with all Unicode characters
        final String unicodeString = "\u05D0\u05D1\u05D2\u05D3";
        final byte[] expectedUnicode = unicodeString.getBytes(charsetName);
        final byte[] actualUnicode = StringUtils.getBytesUtf16Le(unicodeString);
        Assert.assertTrue(Arrays.equals(expectedUnicode, actualUnicode));
        
        // Regression test case 3: changing the input to a string with only numbers
        final String numberString = "1234567890";
        final byte[] expectedNumbers = numberString.getBytes(charsetName);
        final byte[] actualNumbers = StringUtils.getBytesUtf16Le(numberString);
        Assert.assertTrue(Arrays.equals(expectedNumbers, actualNumbers));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        // Existing test case 1
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // New test case 1 - Empty string input
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // New test case 2 - Special characters input
        final byte[] expected2 = "Hello$#@!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("Hello$#@!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // New test case 3 - Null input
        final byte[] expected3 = null;
        final byte[] actual3 = StringUtils.getBytesUtf8(null);
        Assert.assertNull(actual3);
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(new byte[]{}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.newString(new byte[]{(byte) 128}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() {
        try {
            StringUtils.getBytesUnchecked("", "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() {
        try {
            StringUtils.getBytesUnchecked("Hello", "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test to kill mutants: providing null bytes
        final String actual2 = StringUtils.newStringIso8859_1(null);
        Assert.assertNull(actual2);
        
        // Regression test to kill mutants: providing non-null bytes with different charset
        final String actual3 = StringUtils.newStringIso8859_1(BYTES_FIXTURE, Charset.forName("UTF-8"));
        Assert.assertEquals(expected, actual3);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[] { 49, 50, 51 }, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] { 49, 50, 51 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() {
        try {
            StringUtils.newString(null, "UNKNOWN");
            Assert.fail("Expected " + UnsupportedOperationException.class.getName());
        } catch (final UnsupportedOperationException e) {
            // Expected
        }
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[] {}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[] {});
        Assert.assertEquals(expected, actual);
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
        testNewString(charsetName);
        final String expected = new String(new byte[] { 0, 0 }, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[] { 0, 0 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[] { -49, -50, -51 }, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { -49, -50, -51 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        try {
            final String charsetName = null;
            testNewString(charsetName);
            final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
            final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
            Assert.assertEquals(expected, actual);
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final byte[] bytes = { 97, -124, 98, 99, 100 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final byte[] bytes = { 49, 50, 51, 63, 64, 91, 92, 93, 94, 95, 96, 123, 124, 125, 126 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        // Test with empty byte array
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with single byte
        byte[] singleByteFixture = { 65 };
        final String expectedSingleByte = new String(singleByteFixture, charsetName);
        final String actualSingleByte = StringUtils.newStringUsAscii(singleByteFixture);
        Assert.assertEquals(expectedSingleByte, actualSingleByte);
        
        // Test with byte array containing negative values
        byte[] negativeByteFixture = { -65, -97, -128 };
        final String expectedNegativeByte = new String(negativeByteFixture, charsetName);
        final String actualNegativeByte = StringUtils.newStringUsAscii(negativeByteFixture);
        Assert.assertEquals(expectedNegativeByte, actualNegativeByte);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test cases
        // Test with empty byte array
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        
        // Test with single byte
        Assert.assertEquals("A", StringUtils.newStringUsAscii(new byte[] { 65 }));
        
        // Test with byte array containing negative values
        Assert.assertEquals("��?", StringUtils.newStringUsAscii(new byte[] { -65, -97, -128 }));
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        // Regression tests
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {}));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test
        final String expectedEmptyBytes = new String(new byte[] {}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUtf16(new byte[] {});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x41, 0x00, 0x42}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x41, 0x00, 0x42});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x41, 0x00, 0x43}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x41, 0x00, 0x43});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x44, 0x00, 0x45, 0x00, 0x46}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x44, 0x00, 0x45, 0x00, 0x46});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {}));
        
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {65, 66, 67}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {65, 66, 67}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {65, 66, 67}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {65, 66, 67}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {65, 66, 67}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {65, 66, 67}));
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        final String expected1 = new String(new byte[] {}, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(new byte[] {});
        Assert.assertEquals(expected1, actual1);
        
        final String expected2 = new String(new byte[] {65, 66, 67}, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(new byte[] {65, 66, 67});
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);

        // New test case to kill more mutants
        final byte[] emptyBytes = {};
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() {
        Assert.assertNull(StringUtils.newStringUtf8(null));

        // New test cases to kill more mutants
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{0x00}));
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{(byte) 0xFF, (byte) 0xFE}));

        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}