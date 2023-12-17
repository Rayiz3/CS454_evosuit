package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
void testEquals() {
    // Same strings
    assertTrue(equals("hello", "hello"));

    // Different strings
    assertFalse(equals("hello", "world"));

    // One string is null
    assertFalse(equals("hello", null));

    // Both strings are null
    assertFalse(equals(null, null));

    // Non-String CharSequence objects
    assertFalse(equals(new StringBuilder("hello"), new StringBuffer("hello")));

    // Empty strings
    assertTrue(equals("", ""));

    // One empty string
    assertFalse(equals("hello", ""));

    // One string is a substring of the other
    assertFalse(equals("hello world", "hello"));

    // Case insensitive comparison
    assertFalse(equals("HELLO", "hello"));
}
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test6() {
    String input = "test";
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer result = getByteBuffer(input, charset);
    assertNotNull(result);
}
@Test
public void test7() {
    String input = null;
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer result = getByteBuffer(input, charset);
    assertNull(result);
}
@Test
public void test8() {
    String input = "";
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer result = getByteBuffer(input, charset);
    assertEquals(0, result.limit());
}
@Test
public void test9() {
    // Regression test 1
    String string1 = "Hello World";
    ByteBuffer byteBuffer1 = ByteBuffer.wrap(string1.getBytes(Charsets.UTF_8));
    assertEquals(byteBuffer1, getByteBufferUtf8(string1));
    
    // Regression test 2
    String string2 = "12345";
    ByteBuffer byteBuffer2 = ByteBuffer.wrap(string2.getBytes(Charsets.UTF_8));
    assertEquals(byteBuffer2, getByteBufferUtf8(string2));
    
    // Regression test 3
    String string3 = "Testing 123";
    ByteBuffer byteBuffer3 = ByteBuffer.wrap(string3.getBytes(Charsets.UTF_8));
    assertEquals(byteBuffer3, getByteBufferUtf8(string3));
}
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUncheckedRegression(charsetName); // regression test using modified method
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression(charsetName); // regression test using modified method
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUncheckedRegression(charsetName); // regression test using modified method
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() {
        try {
            StringUtils.getBytesUnchecked(null, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUncheckedRegression(charsetName); // regression test using modified method
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUncheckedRegression(charsetName); // regression test using modified method
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedRegression(charsetName); // regression test ausing modified method
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "ISO-8859-1")); // regression test using a different encoding
    }
    private void testGetBytesUncheckedRegression(final String charsetName) throws UnsupportedEncodingException {
        try {
            StringUtils.getBytesUnchecked(null, charsetName); 
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test Case 1: Empty string
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression Test Case 2: String with special characters
        final String input2 = "Hello @#$% World!";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression Test Case 3: String with only numbers
        final String input3 = "1234567890";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression Test Case 4: String with uppercase and lowercase letters
        final String input4 = "AbcDefGhiJklMnoPqrStuVwxYz";
        final byte[] expected4 = input4.getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUsAscii(input4);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2
        final String input2 = "Hello, World!";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3
        final String input3 = "12345";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "   ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("   ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()-_=+{}[]|\\;:'\",.<>/?`~".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("!@#$%^&*()-_=+{}[]|\\;:'\",.<>/?`~");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test 1: Empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Regression test 2: String with special characters
        final String specialChars = "!@#$%^&*()-=_+[]{};':\",./<>?\\|`~";
        final byte[] expectedSpecial = specialChars.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf16Le(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));

        // Regression test 3: String with leading and trailing whitespace
        final String whitespace = "  abc  ";
        final byte[] expectedWhitespace = whitespace.getBytes(charsetName);
        final byte[] actualWhitespace = StringUtils.getBytesUtf16Le(whitespace);
        Assert.assertTrue(Arrays.equals(expectedWhitespace, actualWhitespace));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName); // change input here
        final byte[] actual = StringUtils.getBytesUtf8("hello"); // change input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234".getBytes(charsetName); // change input here
        final byte[] actual = StringUtils.getBytesUtf8("1234"); // change input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcde".getBytes(charsetName); // change input here
        final byte[] actual = StringUtils.getBytesUtf8("abcde"); // change input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-55");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-55");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test42() {
        try {
            StringUtils.newString(new byte[0], "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Test with empty byte array
        final byte[] emptyBytes = {};
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);
        
        // Regression test case 2: Test with special characters in byte array
        final byte[] specialBytes = {65, 66, -32, -80, -36, -42, -52, -62, -68, -73, -86, -94, -102, -110, -118};
        final String specialExpected = new String(specialBytes, charsetName);
        final String specialActual = StringUtils.newStringIso8859_1(specialBytes);
        Assert.assertEquals(specialExpected, specialActual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 3: Test with null byte array
        final byte[] nullBytes = null;
        Assert.assertNull(StringUtils.newStringIso8859_1(nullBytes));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        
        // Regression Test Case 1
        final byte[] bytes1 = {65, 66, 67, 68, 69}; // ABCDE
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test Case 2
        final byte[] bytes2 = {97, 98, 99, 100, 101}; // abcde
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // ...
        
        // Regression Test Case N
        final byte[] bytesN = {49, 50, 51, 52, 53}; // 12345
        final String expectedN = new String(bytesN, charsetName);
        final String actualN = StringUtils.newStringUsAscii(bytesN);
        Assert.assertEquals(expectedN, actualN);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        final String expectedRegression1 = new String(BYTES_FIXTURE, charsetName);
        final String actualRegression1 = StringUtils.newStringUtf16(new byte[]{});
        Assert.assertEquals(expectedRegression1, actualRegression1);
        
        final String expectedRegression2 = new String(BYTES_FIXTURE, charsetName);
        final String actualRegression2 = StringUtils.newStringUtf16(new byte[]{-1, 0, 1});
        Assert.assertEquals(expectedRegression2, actualRegression2);
        
        final String expectedRegression3 = new String(BYTES_FIXTURE, charsetName);
        final String actualRegression3 = StringUtils.newStringUtf16(new byte[]{127, -128, 0});
        Assert.assertEquals(expectedRegression3, actualRegression3);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Byte array with special characters
        final byte[] bytes2 = {0x00, 0x41, 0x00, 0x42, 0x00, 0x43};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Byte array with null character
        final byte[] bytes3 = {0x00, 0x41, 0x00, 0x00, 0x00, 0x43};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{(byte) 0xFF, (byte) 0xFE, 0x41, 0x00, 0x42, 0x00};
        final String expected = new String(bytes, "UTF-16LE");
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{(byte) 0xFF, (byte) 0xFE, 0x43, 0x00, 0x44, 0x00};
        final String expected = new String(bytes, "UTF-16LE");
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{80, 97, 114, 116, 105, 99, 105, 112, 97, 110, 116, 115});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{76, 105, 98, 114, 97, 114, 121, 95, 101, 120, 97, 109, 112, 108, 101});
        Assert.assertEquals(expected, actual);
    }
}