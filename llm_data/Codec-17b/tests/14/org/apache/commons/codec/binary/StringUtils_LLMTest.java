package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Test equal CharSequence inputs
    assertTrue(StringUtils.equals("hello", "hello"));
    // Test unequal CharSequence inputs
    assertFalse(StringUtils.equals("hello", "world"));
    // Test null CharSequence inputs
    assertFalse(StringUtils.equals(null, "hello"));
    assertFalse(StringUtils.equals("hello", null));
    assertTrue(StringUtils.equals(null, null));
    // Test case with empty strings
    assertTrue(StringUtils.equals("", ""));
    assertFalse(StringUtils.equals("hello", ""));
    assertFalse(StringUtils.equals("", "hello"));
    // Test case with different implementations of CharSequence
    assertTrue(StringUtils.equals(new StringBuilder("hello"), new StringBuffer("hello")));
    assertFalse(StringUtils.equals(new StringBuilder("hello"), new StringBuffer("world")));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("This is a regression test case");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("This is a regression test case");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("This is a regression test case");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("This is a regression test case");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("This is a regression test case");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("This is a regression test case");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test7() {
    String string = "Hello World";
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer expectedResult = ByteBuffer.wrap(string.getBytes(charset));
    
    ByteBuffer result = getByteBuffer(string, charset);
    
    assertNotNull(result);
    assertEquals(expectedResult, result);
}
@Test
public void test8() {
    String string = "Hello World";
    Charset charset = StandardCharsets.ISO_8859_1;
    ByteBuffer expectedResult = ByteBuffer.wrap(string.getBytes(charset));
    
    ByteBuffer result = getByteBuffer(string, charset);
    
    assertNotNull(result);
    assertEquals(expectedResult, result);
}
@Test
public void test9() {
    String string = "";
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer expectedResult = ByteBuffer.wrap(string.getBytes(charset));
    
    ByteBuffer result = getByteBuffer(string, charset);
    
    assertNotNull(result);
    assertEquals(expectedResult, result);
}
@Test
public void test10() {
    String string = null;
    Charset charset = StandardCharsets.UTF_8;
    
    ByteBuffer result = getByteBuffer(string, charset);
    
    assertNull(result);
}
@Test
public void test11() {
    // Test with empty string
    String input1 = "";
    ByteBuffer expected1 = ByteBuffer.wrap(new byte[]{});
    ByteBuffer result1 = MyClass.getByteBufferUtf8(input1);
    assertEquals(expected1, result1);

    // Test with single character string
    String input2 = "A";
    ByteBuffer expected2 = ByteBuffer.wrap(new byte[]{65});
    ByteBuffer result2 = MyClass.getByteBufferUtf8(input2);
    assertEquals(expected2, result2);

    // Test with multi-character string
    String input3 = "Hello World!";
    ByteBuffer expected3 = ByteBuffer.wrap(new byte[]{72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33});
    ByteBuffer result3 = MyClass.getByteBufferUtf8(input3);
    assertEquals(expected3, result3);

    // Test with string containing special characters
    String input4 = "Thumbs Up! 👍🏼";
    ByteBuffer expected4 = ByteBuffer.wrap(new byte[]{84, 104, 117, 109, 98, 115, 32, 85, 112, 33, 32, -16, -97, -103, -105, -16, -97, -84, -94});
    ByteBuffer result4 = MyClass.getByteBufferUtf8(input4);
    assertEquals(expected4, result4);
}
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "Regression Test 1".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "Regression Test 2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "Regression Test 3".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test 3");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() {
        try {
            StringUtils.getBytesUnchecked("Regression Test 4", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "Regression Test 5".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 5");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "Regression Test 6".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test 6");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "Regression Test 7".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test 7");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() {
        Assert.assertNull(StringUtils.getBytesUnchecked("Regression Test 8", "UNKNOWN"));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        
        // Regression test case 1: Empty string
        final byte[] emptyExpected = "".getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Regression test case 2: String with special characters
        final byte[] specialExpected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUsAscii("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
        
        // Regression test case 3: String with non-ASCII characters
        final byte[] nonAsciiExpected = "Hello ñ!".getBytes(charsetName);
        final byte[] nonAsciiActual = StringUtils.getBytesUsAscii("Hello ñ!");
        Assert.assertTrue(Arrays.equals(nonAsciiExpected, nonAsciiActual));
        
        // Original test case
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null; // Change the input value to a different string that can be encoded in UTF-16
        final byte[] actual = StringUtils.getBytesUtf16("hello world"); // Change the input value to a different string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(""); // Change the input value to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(null); // Change the input value to null
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("@#$%^&*"); // Change the input value to a string with special characters
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
   @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression Test Case 1: Empty String
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression Test Case 2: String with special characters
        final String string2 = "!@#$%^&*()";
        final byte[] expected2 = string2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be(string2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression Test Case 3: String with multiple languages
        final String string3 = "Hello 你好 नमस्ते";
        final byte[] expected3 = string3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be(string3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: string with special characters
        final String specialChars = "!@#$%^&*()_+{}:\"<>?~`'";
        final byte[] expectedSpecialChars = specialChars.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf16Le(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
        
        // Regression test 3: string with non-ASCII characters
        final String nonASCII = "你好";
        final byte[] expectedNonASCII = nonASCII.getBytes(charsetName);
        final byte[] actualNonASCII = StringUtils.getBytesUtf16Le(nonASCII);
        Assert.assertTrue(Arrays.equals(expectedNonASCII, actualNonASCII));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Tests
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUtf8(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        final String specialChars = " Testing 123!@#$%^&*()_+";
        final byte[] specialExpected = specialChars.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUtf8(specialChars);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
    }
    @Test
    public void test30() {
        try {
            StringUtils.getBytesUnchecked("test string", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() {
        try {
            StringUtils.newString("test string".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
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
    public void test41() {
        final String charsetName = "UTF-16BE";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() {
        final String charsetName = "";
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newString(BYTES_FIXTURE_16BE, charsetName);
        Assert.assertEquals(expected, actual);
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
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
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
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);  // Changed input to empty byte array
        final String actual = StringUtils.newStringIso8859_1(new byte[0]);  // Changed input to empty byte array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final byte[] input = {65, 66, 67, -27, -128, -95, 68};  // Changed input to include a special character
        final String expected = new String(input, charsetName);  
        final String actual = StringUtils.newStringIso8859_1(input); 
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final byte[] input = {65, 66, 67, 127};  // Changed input to include a large value
        final String expected = new String(input, charsetName);
        final String actual = StringUtils.newStringIso8859_1(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        
        // Regression test 1: Empty byte array
        byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Byte array with one byte
        byte[] oneByte = { 65 }; // ASCII code for 'A'
        final String expected2 = new String(oneByte, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(oneByte);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Byte array with multiple bytes
        byte[] multipleBytes = { 72, 101, 108, 108, 111 }; // ASCII codes for 'Hello'
        final String expected3 = new String(multipleBytes, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(multipleBytes);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);

        // Modified input test case
        final String expected1 = new String(BYTES_FIXTURE, charsetName);
        final String actual1 = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected1, actual1);

        // Additional input test case
        final byte[] bytes2 = {0x00, 0x46, 0x00, 0x6F, 0x00, 0x6F, 0x00, 0x62, 0x00, 0x61, 0x00, 0x72};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{65, 66, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{70, 71, 72});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1
        final byte[] bytes1 = {-2, -1, 0, 97};  // EF, BB, 00, 61
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2
        final byte[] bytes2 = {0, 0, 0, 98};  // 00, 00, 00, 62
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3
        final byte[] bytes3 = {32, 0};  // 20, 00
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test59() {
        // Regression test case 1
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case 2
        byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(emptyBytes));
        Assert.assertNull(StringUtils.newStringUsAscii(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(emptyBytes));

        // Regression test case 3
        byte[] invalidBytes = {-1, -2};
        Assert.assertNull(StringUtils.newStringUtf8(invalidBytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(invalidBytes));
        Assert.assertNull(StringUtils.newStringUsAscii(invalidBytes));
        Assert.assertNull(StringUtils.newStringUtf16(invalidBytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(invalidBytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(invalidBytes));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
      
        // Regression test case 1
        byte[] singleByte = {65};
        Assert.assertEquals("A", StringUtils.newStringUtf8(singleByte));
        
        // Regression test case 2
        byte[] multiByte = {(byte) 0xE6, (byte) 0x97, (byte) 0xA5};
        Assert.assertEquals("日", StringUtils.newStringUtf8(multiByte));
    }
}