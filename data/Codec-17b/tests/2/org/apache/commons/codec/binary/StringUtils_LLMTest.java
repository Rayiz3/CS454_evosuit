package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Positive test cases
    assertTrue(StringUtils.equals(null, null));
    assertTrue(StringUtils.equals("", ""));
    assertTrue(StringUtils.equals("abc", "abc"));

    // Negative test cases
    assertFalse(StringUtils.equals(null, ""));
    assertFalse(StringUtils.equals("", null));
    assertFalse(StringUtils.equals("abc", "def"));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("ABC123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("こんにちは");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("你好");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("नमस्ते");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Привет");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test7() {
    String input = "Hello, World!";
    Charset charset = Charset.defaultCharset();

    ByteBuffer result = getByteBuffer(input, charset);

    assertNotNull(result);
}
@Test
public void test8() {
    String input = null;
    Charset charset = Charset.defaultCharset();

    ByteBuffer result = getByteBuffer(input, charset);

    assertNull(result);
}
@Test
public void test9() {
    String input = "";
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(input, charset);

    assertNotNull(result);
    assertEquals(0, result.limit());
}
@Test
public void test10() {
    String input = "Hello, World!";
    Charset charset = StandardCharsets.UTF_16;

    ByteBuffer result = getByteBuffer(input, charset);

    assertNotNull(result);
    assertNotEquals(ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8)), result);
}
    @Test
    public void test11() {
        ByteBuffer result = getByteBufferUtf8(null);
        assertEquals("Test failed for null input", null, result);
    }
    @Test
    public void test12() {
        ByteBuffer result = getByteBufferUtf8("");
        assertEquals("Test failed for empty string input", ByteBuffer.allocate(0), result);
    }
    @Test
    public void test13() {
        ByteBuffer result = getByteBufferUtf8("Hello World");
        assertEquals("Test failed for valid input", ByteBuffer.wrap("Hello World".getBytes(StandardCharsets.UTF_8)), result);
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "regression test 1".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("regression test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "regression test 2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("regression test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(null, charsetName);
        final byte[] expected = NULL_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(null, charsetName);
        final byte[] expected = NULL_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(null, charsetName);
        final byte[] expected = NULL_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() {
        Assert.assertNull(StringUtils.getBytesUnchecked("input string", "UNKNOWN"));
    }
    @Test
    public void test20() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(null, charsetName);
        final byte[] expected = NULL_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(null, charsetName);
        final byte[] expected = NULL_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(null, charsetName);
        final byte[] expected = NULL_STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()_-+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("!@#$%^&*()_-+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test30() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));

    // Regression Test 1: Empty string
    final byte[] expected1 = "".getBytes(charsetName);
    final byte[] actual1 = StringUtils.getBytesUtf16Be("");
    Assert.assertTrue(Arrays.equals(expected1, actual1));

    // Regression Test 2: String with special characters
    final byte[] expected2 = "@#$%^&*()".getBytes(charsetName);
    final byte[] actual2 = StringUtils.getBytesUtf16Be("@#$%^&*()");
    Assert.assertTrue(Arrays.equals(expected2, actual2));

    // Regression Test 3: String with whitespace
    final byte[] expected3 = "  This is a test  ".getBytes(charsetName);
    final byte[] actual3 = StringUtils.getBytesUtf16Be("  This is a test  ");
    Assert.assertTrue(Arrays.equals(expected3, actual3));

}
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression tests
        final String input1 = "Hello, World!";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        final String input2 = "Testing utf16le";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final String input3 = "12345";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression3(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test37() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "charsetName");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "charsetName");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
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
    }
    @Test
    public void test43() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String("Hello World!".getBytes(), charsetName);
        final String actual = StringUtils.newStringUtf16("Hello World!".getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String("Hello World!".getBytes(), charsetName);
        final String actual = StringUtils.newStringUtf8("Hello World!".getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String("Hello World!".getBytes(), charsetName);
        final String actual = StringUtils.newStringUtf16Be("Hello World!".getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        try {
            StringUtils.newString("Hello World!".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newStringUtf8("Hello World!".getBytes()));
        Assert.assertNull(StringUtils.newStringIso8859_1("Hello World!".getBytes()));
        Assert.assertNull(StringUtils.newStringUsAscii("Hello World!".getBytes()));
        Assert.assertNull(StringUtils.newStringUtf16("Hello World!".getBytes()));
        Assert.assertNull(StringUtils.newStringUtf16Be("Hello World!".getBytes()));
        Assert.assertNull(StringUtils.newStringUtf16Le("Hello World!".getBytes()));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String("Hello World!".getBytes(), charsetName);
        final String actual = StringUtils.newStringUtf16Le("Hello World!".getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String("Hello World!".getBytes(), charsetName);
        final String actual = StringUtils.newStringUsAscii("Hello World!".getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String("Hello World!".getBytes(), charsetName);
        final String actual = StringUtils.newStringIso8859_1("Hello World!".getBytes());
        Assert.assertEquals(expected, actual);
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
    public void test58() {
        final byte[] bytes = new byte[0];
        final String expected = new String(bytes, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        final byte[] bytes = {65};
        final String expected = new String(bytes, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() {
        final byte[] bytes = {65, 66, 67};
        final String expected = new String(bytes, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Regression test with empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test with byte array containing only one byte
        final byte[] singleByte = new byte[] { 65 };
        final String expectedSingleByte = new String(singleByte, charsetName);
        final String actualSingleByte = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expectedSingleByte, actualSingleByte);
        
        // Regression test with byte array containing multiple bytes
        final byte[] multipleBytes = new byte[] { 65, 66, 67};
        final String expectedMultipleBytes = new String(multipleBytes, charsetName);
        final String actualMultipleBytes = StringUtils.newStringUsAscii(multipleBytes);
        Assert.assertEquals(expectedMultipleBytes, actualMultipleBytes);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression Test Case 1: Empty byte array
        byte[] emptyBytes = {};
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        // Regression Test Case 2: Valid input with a single character
        byte[] singleCharBytes = {-2, -1, 0, 65}; // 0xFEFF is the byte-order marker for UTF-16
        final String singleCharExpected = new String(singleCharBytes, charsetName);
        final String singleCharActual = StringUtils.newStringUtf16(singleCharBytes);
        Assert.assertEquals(singleCharExpected, singleCharActual);

        // Regression Test Case 3: Valid input with multiple characters
        byte[] multiCharBytes = {-2, -1, 0, 65, 0, 66}; // 0xFEFF is the byte-order marker for UTF-16
        final String multiCharExpected = new String(multiCharBytes, charsetName);
        final String multiCharActual = StringUtils.newStringUtf16(multiCharBytes);
        Assert.assertEquals(multiCharExpected, multiCharActual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1
        final byte[] bytes1 = {0x00, 0x41, 0x00, 0x42, 0x00, 0x43};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2
        final byte[] bytes2 = {0x30, 0x00, 0x31, 0x00, 0x32, 0x00};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        
        // Test with an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with a byte array containing one character
        final byte[] singleByte = {0x01};
        final String expectedSingleByte = new String(singleByte, charsetName);
        final String actualSingleByte = StringUtils.newStringUtf16Le(singleByte);
        Assert.assertEquals(expectedSingleByte, actualSingleByte);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66()  {
        byte[] bytes = {65, 66, 67, 68, 69}; // ABCDE in bytes
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        byte[] bytes = {65, 66, 67, 68, 69}; // ABCDE in bytes
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}