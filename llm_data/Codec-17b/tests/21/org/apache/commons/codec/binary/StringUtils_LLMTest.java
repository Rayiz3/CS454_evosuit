package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test6() {
   ByteBuffer result = getByteBuffer(null, Charset.defaultCharset());
   assertNull(result);
}
@Test
public void test7() {
   ByteBuffer result = getByteBuffer("", Charset.defaultCharset());
   assertNotNull(result);
   assertEquals(0, result.capacity());
}
@Test
public void test8() {
   String input = "Hello, World!";
   Charset charset = StandardCharsets.UTF_8;
   
   ByteBuffer result = getByteBuffer(input, charset);
   
   assertNotNull(result);
   assertEquals(input.length(), result.capacity());
   assertEquals(charset, result.charset());
}
@Test
public void test9() {
   String input = "Hello, World!";
   Charset charset = StandardCharsets.ISO_8859_1;
   
   ByteBuffer result = getByteBuffer(input, charset);
   
   assertNotNull(result);
   assertEquals(input.length(), result.capacity());
   assertEquals(charset, result.charset());
}
@Test
public void test10() {
    // regression test 1
    String input1 = ""; // empty string
    ByteBuffer expectedOutput1 = ByteBuffer.allocate(0);
    ByteBuffer actualOutput1 = SomeClass.getByteBufferUtf8(input1);
    assertEquals(expectedOutput1, actualOutput1);

    // regression test 2
    String input2 = "Hello World!"; // string with special characters
    ByteBuffer expectedOutput2 = ByteBuffer.wrap("Hello World!".getBytes(StandardCharsets.UTF_8));
    ByteBuffer actualOutput2 = SomeClass.getByteBufferUtf8(input2);
    assertEquals(expectedOutput2, actualOutput2);

    // regression test 3
    String input3 = "12345"; // string with only digits
    ByteBuffer expectedOutput3 = ByteBuffer.wrap("12345".getBytes(StandardCharsets.UTF_8));
    ByteBuffer actualOutput3 = SomeClass.getByteBufferUtf8(input3);
    assertEquals(expectedOutput3, actualOutput3);
}
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression Test Case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        // Regression Test Case 2: String with special characters
        final byte[] expected2 = "Testing $pecial Characters".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("Testing $pecial Characters");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        // Regression Test Case 3: String with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
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
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
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
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 1".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Tests
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        final byte[] expected2 = "Hello, World!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression test case 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression test case 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression test case 3");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, 世界!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello, 世界!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "  Hello, World!  ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("  Hello, World!  ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        // Test with an empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Test with a string containing special characters
        final byte[] expectedSpecial = "A string with special characters: @#$%^&*".getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf8("A string with special characters: @#$%^&*");
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
        
        // Test with a string containing non-ASCII characters
        final byte[] expectedNonASCII = "Α string with non-ASCII characters: ΩΨΦΞ".getBytes(charsetName);
        final byte[] actualNonASCII = StringUtils.getBytesUtf8("Α string with non-ASCII characters: ΩΨΦΞ");
        Assert.assertTrue(Arrays.equals(expectedNonASCII, actualNonASCII));
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked("abc", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.newString("abc".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test36() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    private void testNewString_regression(String charsetName) throws UnsupportedEncodingException {
        final byte[] bytesFixture = "Regtest".getBytes(charsetName);
        final String expected = new String(bytesFixture, charsetName);
        final String actual = StringUtils.newString(bytesFixture, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
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
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] bytes = new byte[0];
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] bytes = {65}; // 'A'
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] bytes = {65, 66, 67}; // 'A', 'B', 'C'
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
@Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        
        // Test with empty byte array
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with byte array containing special characters
        final byte[] specialBytes = {65, 66, 67, -128, -127, -126, -1};
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUsAscii(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
        
        // Test with byte array containing null values
        final byte[] nullBytes = {65, 66, 0, 67, 0, 68};
        final String expectedNull = new String(nullBytes, charsetName);
        final String actualNull = StringUtils.newStringUsAscii(nullBytes);
        Assert.assertEquals(expectedNull, actualNull);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Regression Test Case 1: Empty input
        final byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test Case 2: Input with null bytes
        final byte[] nullBytes = {0, 0, 0, 0, 0};
        final String expected2 = new String(nullBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16(nullBytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test Case 3: Input with all same byte values
        final byte[] sameBytes = {127, 127, 127, 127, 127};
        final String expected3 = new String(sameBytes, charsetName);
        final String actual3 = StringUtils.newStringUtf16(sameBytes);
        Assert.assertEquals(expected3, actual3);
        
        // Existing test case unchanged
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Existing test case
        testNewString(charsetName);
        
        // Regression test 1: Empty byte array
        final byte[] emptyBytes = {};
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Multiple bytes
        final byte[] bytes = { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43 };
        final String expected2 = new String(bytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Byte array with negative values
        final byte[] negativeBytes = { (byte) 0xFF, (byte) 0xFE };
        final String expected3 = new String(negativeBytes, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(negativeBytes);
        Assert.assertEquals(expected3, actual3);

        // Regression test 4: Byte array with special characters
        final byte[] specialCharsBytes = { 0x00, 0x00, 0x24, 0x20, 0x00, (byte) 0xA9, 0x20, 0x00, (byte) 0xA2, 0x00 };
        final String expected4 = new String(specialCharsBytes, charsetName);
        final String actual4 = StringUtils.newStringUtf16Be(specialCharsBytes);
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = "";
        final String actual = StringUtils.newStringUtf16Le(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{1, 2, 3, 4, 5});
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test59() {
        // Regression test 1: empty array
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        
        // Regression test 2: null array
        final String actual = StringUtils.newStringUtf8(null);
        Assert.assertEquals(expected, actual);
        
        // Regression test 3: array with one byte
        final byte[] bytes = new byte[]{87};
        final String expected2 = new String(bytes, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 4: array with multiple bytes
        final byte[] bytes2 = new byte[]{87, 79, 82, 68};
        final String expected3 = new String(bytes2, charsetName);
        final String actual3 = StringUtils.newStringUtf8(bytes2);
        Assert.assertEquals(expected3, actual3);
    }
}