package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Comparing two different strings
    assertFalse(equals("hello", "world"));

    // Comparing a string with null
    assertFalse(equals("hello", null));

    // Comparing a null string with a string
    assertFalse(equals(null, "world"));

    // Comparing two null strings
    assertTrue(equals(null, null));

    // Comparing two equal strings
    assertTrue(equals("hello", "hello"));

    // Comparing two different CharSequence objects
    assertFalse(equals(new StringBuilder("hello"), new StringBuffer("hello")));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() {
        ByteBuffer result = getByteBuffer(null, StandardCharsets.UTF_8);
        assertNull(result);
    }
    @Test
    public void test8() {
        ByteBuffer result = getByteBuffer("", StandardCharsets.US_ASCII);
        assertNotNull(result);
        assertEquals(0, result.remaining());
    }
    @Test
    public void test9() {
        String input = "Hello World!";
        ByteBuffer result = getByteBuffer(input, StandardCharsets.UTF_16);
        assertNotNull(result);
        assertEquals(input.length() * 2, result.remaining());
    }
    @Test
    public void test10() {
        String input = "こんにちは";
        ByteBuffer result = getByteBuffer(input, StandardCharsets.ISO_8859_1);
        assertNotNull(result);
        assertEquals(input.getBytes(StandardCharsets.ISO_8859_1).length, result.remaining());
    }
@Test
public void test11() {
    String input = "Hello, world!";
    ByteBuffer expected = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(input);

    assertEquals(expected, result);
}
@Test
public void test12() {
    String input = "";
    ByteBuffer expected = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(input);

    assertEquals(expected, result);
}
@Test
public void test13() {
    String input = "特殊字符";
    ByteBuffer expected = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(input);

    assertEquals(expected, result);
}
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
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
        final byte[] expected = null.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
   @Test
    public void test18() {
        final String charsetName = "US-ASCII";
        String input = ""; // empty string
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() {
        final String charsetName = "UTF-16BE";
        String input = "test"; // non-empty string
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() {
        final String charsetName = "UTF-16LE";
        String input = "test"; // non-empty string
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() {
        final String charsetName = "ISO-8859-1";
        String input = "test"; // non-empty string
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() {
        final String charsetName = "UTF-16";
        String input = "test"; // non-empty string
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() {
        final String charsetName = "UTF-8";
        String input = "hello world"; // non-empty string
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: String with leading whitespace
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "  hello".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("  hello");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "@#%!#*".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("@#%!#*");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Testing, 1, 2, 3!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Testing, 1, 2, 3!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName); // Changing input to "Hello"
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello"); // Changing input to "Hello"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "World".getBytes(charsetName); // Changing input to "World"
        final byte[] actual = StringUtils.getBytesUtf16Le("World"); // Changing input to "World"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "123".getBytes(charsetName); // Changing input to "123"
        final byte[] actual = StringUtils.getBytesUtf16Le("123"); // Changing input to "123"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        // Regression test 1: Empty string
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2: String with one character
        final byte[] expected2 = "a".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("a");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3: String with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        // Regression test 4: String with special characters
        final byte[] expected4 = "!@#$%^&*()_+{}:\"|<>?".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf8("!@#$%^&*()_+{}:\"|<>?");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test35() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 1
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID_CHARSET");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 2
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 1
        try {
            StringUtils.newString(BYTES_FIXTURE, "INVALID_CHARSET");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 2
        try {
            StringUtils.newString(BYTES_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test50() {
        try {
            StringUtils.newString(BYTES_FIXTURE_regression1, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(ANOTHER_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(ANOTHER_BYTES_FIXTURE);
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
        final String charsetName = "US-ASCII";
        
        // Test with empty bytes array
        byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Test with single byte
        byte[] singleByte = {65}; // ASCII value of 'A'
        final String expected2 = new String(singleByte, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expected2, actual2);
        
        // Test with non-ASCII bytes
        byte[] nonAsciiBytes = {100, 200, -50};
        final String expected3 = new String(nonAsciiBytes, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Test case 1: Empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Test case 2: Byte array with special characters
        final byte[] bytes2 = {127, -128, 0, 1};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Test case 3: Byte array with high Unicode characters
        final byte[] bytes3 = {(byte) 0xD8, 0x00, (byte) 0xDC, 0x00};
        final String expected3 = new String(bytes2, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        // Regression test case 1
        final byte[] bytes1 = {0x00, 0x00, 0x00, 0x00};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test case 2
        final byte[] bytes2 = {0x41, 0x42, 0x43, 0x44};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        
        final byte[] bytes1 = "Hello".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        final byte[] bytes2 = "12345".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        final byte[] bytes3 = "abc123".getBytes(charsetName);
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
        
        final byte[] bytes4 = "".getBytes(charsetName);
        final String expected4 = new String(bytes4, charsetName);
        final String actual4 = StringUtils.newStringUtf16Le(bytes4);
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        //Regression test cases
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUTF8(new byte[] {0x41,0x42,0x43,0x44}));
        Assert.assertNull(StringUtils.newStringISO_8859_1(new byte[] {0x41,0x42,0x43,0x44}));
        Assert.assertNull(StringUtils.newStringUS_ASCII(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUTF16(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUTF16BE(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUTF16LE(new byte[] {}));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        //Regression test case
        final String expectedNull = new String(new byte[] {}, charsetName);
        final String actualNull = StringUtils.newStringUtf8(new byte[] {});
        Assert.assertEquals(expectedNull, actualNull);
    }
}