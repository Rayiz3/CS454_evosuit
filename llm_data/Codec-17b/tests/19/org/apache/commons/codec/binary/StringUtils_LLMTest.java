package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Test when both cs1 and cs2 are null
    CharSequence cs1 = null;
    CharSequence cs2 = null;
    assertFalse(StringUtils.equals(cs1, cs2));
  
    // Test when cs1 is null but cs2 is not null
    cs1 = null;
    cs2 = "abc";
    assertFalse(StringUtils.equals(cs1, cs2));
    
    // Test when cs1 is not null but cs2 is null
    cs1 = "abc";
    cs2 = null;
    assertFalse(StringUtils.equals(cs1, cs2));
    
    // Test when cs1 and cs2 are empty strings
    cs1 = "";
    cs2 = "";
    assertTrue(StringUtils.equals(cs1, cs2));
    
    // Test when cs1 and cs2 are equal non-empty strings
    cs1 = "abc";
    cs2 = "abc";
    assertTrue(StringUtils.equals(cs1, cs2));
    
    // Test when cs1 and cs2 are different non-empty strings
    cs1 = "abc";
    cs2 = "def";
    assertFalse(StringUtils.equals(cs1, cs2));
    
    // Test when cs1 is a different type of CharSequence than cs2
    cs1 = new StringBuffer("abc");
    cs2 = "abc";
    assertFalse(StringUtils.equals(cs1, cs2));
    
    // Test when cs1 and cs2 are equal CharSequences of different types
    cs1 = new StringBuffer("abc");
    cs2 = new StringBuilder("abc");
    assertFalse(StringUtils.equals(cs1, cs2));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_Regression(charsetName); // regression test
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_Regression(charsetName); // regression test
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_Regression(charsetName); // regression test
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_Regression(charsetName); // regression test
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_Regression(charsetName); // regression test
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_Regression(charsetName); // regression test
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test7() {
    ByteBuffer byteBuffer = getByteBuffer(null, Charset.defaultCharset());
    assertNull(byteBuffer);
}
@Test
public void test8() {
    ByteBuffer byteBuffer = getByteBuffer("", Charset.defaultCharset());
    assertEquals(0, byteBuffer.remaining());
}
@Test
public void test9() {
    String string = "Hello World";
    Charset charset = Charset.forName("UTF-8");

    ByteBuffer byteBuffer = getByteBuffer(string, charset);
    byte[] bytes = string.getBytes(charset);

    assertEquals(bytes.length, byteBuffer.remaining());
    byte[] result = new byte[byteBuffer.remaining()];
    byteBuffer.get(result);

    assertArrayEquals(bytes, result);
}
@Test
public void test10() {
    String string = "Hello World";
    Charset charset1 = Charset.forName("UTF-8");
    Charset charset2 = Charset.forName("UTF-16");

    ByteBuffer byteBuffer1 = getByteBuffer(string, charset1);
    ByteBuffer byteBuffer2 = getByteBuffer(string, charset2);

    assertNotEquals(byteBuffer1, byteBuffer2);
}
@Test
public void test11() {
    // Test with empty string
    String input = "";
    ByteBuffer expected = ByteBuffer.allocate(0);
    ByteBuffer result = getByteBufferUtf8(input);
    assertEquals(expected, result);
}
@Test
public void test12() {
    // Test with string containing only Unicode characters
    String input = "Hello, 世界!";
    ByteBuffer expected = ByteBuffer.allocate(18).put("Hello, 世界!".getBytes(Charsets.UTF_8)).flip();
    ByteBuffer result = getByteBufferUtf8(input);
    assertEquals(expected, result);
}
@Test
public void test13() {
    // Test with string containing special characters
    String input = "$%^&*@!";
    ByteBuffer expected = ByteBuffer.allocate(7).put("$%^&*@!".getBytes(Charsets.UTF_8)).flip();
    ByteBuffer result = getByteBufferUtf8(input);
    assertEquals(expected, result);
}
@Test
public void test14() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    
    // Regression test case 1: empty string
    final byte[] expected1 = "".getBytes(charsetName);
    final byte[] actual1 = StringUtils.getBytesIso8859_1("");
    Assert.assertTrue(Arrays.equals(expected1, actual1));
    
    // Regression test case 2: string with special characters
    final byte[] expected2 = "Héllo".getBytes(charsetName);
    final byte[] actual2 = StringUtils.getBytesIso8859_1("Héllo");
    Assert.assertTrue(Arrays.equals(expected2, actual2));
    
    // Regression test case 3: string with numbers and symbols
    final byte[] expected3 = "123!@#".getBytes(charsetName);
    final byte[] actual3 = StringUtils.getBytesIso8859_1("123!@#");
    Assert.assertTrue(Arrays.equals(expected3, actual3));
    
    // Original test case
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
   @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("New Input Value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("New Input Value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("New Input Value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() {
        try {
            StringUtils.getBytesUnchecked("New Input Value", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("New Input Value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("New Input Value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("New Input Value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Regression test 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: string with only uppercase letters
        final byte[] expected2 = "HELLO".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("HELLO");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: string with only lowercase letters
        final byte[] expected3 = "hello".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("hello");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression test 4: string with numbers
        final byte[] expected4 = "12345".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
        
        // Regression test 5: string with special characters
        final byte[] expected5 = "@!#$%^&*()_-+=<>,.;:\"'[]{}/?\\|~`".getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesUsAscii("@!#$%^&*()_-+=<>,.;:\"'[]{}/?\\|~`");
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression Test Case 1: Empty String
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression Test Case 2: String with emoji
        final byte[] expected2 = "".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression Test Case 3: String with special characters
        final byte[] expected3 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        // Regression Test Case 4: String with non-English characters
        final byte[] expected4 = "こんにちは".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16("こんにちは");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
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
        final byte[] expected2 = "特殊字符".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("特殊字符");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression Test 3: String with numbers
        final byte[] expected3 = "1234567890".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("1234567890");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Additional test case: empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Additional test case: string with special characters
        final String specialCharacters = "ABC!@#$";
        final byte[] expectedSpecial = specialCharacters.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf16Le(specialCharacters);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));

        // Additional test case: string with non-Latin characters
        final String nonLatinCharacters = "你好世界";
        final byte[] expectedNonLatin = nonLatinCharacters.getBytes(charsetName);
        final byte[] actualNonLatin = StringUtils.getBytesUtf16Le(nonLatinCharacters);
        Assert.assertTrue(Arrays.equals(expectedNonLatin, actualNonLatin));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Tests
        // Test with empty string
        final byte[] emptyExpected = "".getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Test with string containing special characters
        final String specialCharacters = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/’";
        final byte[] specialExpected = specialCharacters.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUtf8(specialCharacters);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));
        
        // Test with string containing unicode characters
        final String unicodeString = "\u00A9 This is a unicode string";
        final byte[] unicodeExpected = unicodeString.getBytes(charsetName);
        final byte[] unicodeActual = StringUtils.getBytesUtf8(unicodeString);
        Assert.assertTrue(Arrays.equals(unicodeExpected, unicodeActual));
    }
    @Test
    public void test28() {
        // Regression test case 1: String with special characters
        String input = "String with special characters: #$@!";
        try {
            StringUtils.getBytesUnchecked(input, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test29() {
        // Regression test case 1: Bytes with negative values
        byte[] input = {-1, -2, -3, -4, -5};
        try {
            StringUtils.newString(input, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Epected
        }
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(REPRODUCED_BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(REPRODUCED_BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(REPRODUCED_BYTES_FIXTURE, charsetName); // changed the bytes here
        final String actual = StringUtils.newStringUsAscii(REPRODUCED_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(REPRODUCED_BYTES_FIXTURE, charsetName); // changed the bytes here
        final String actual = StringUtils.newStringIso8859_1(REPRODUCED_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test36() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test37() {
        Assert.assertNull(StringUtils.newString(REPRODUCED_NULL_BYTES, "UNKNOWN")); // changed the null bytes here
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(REPRODUCED_BYTES_FIXTURE, charsetName); // changed the bytes here
        final String actual = StringUtils.newStringUtf16(REPRODUCED_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(REPRODUCED_BYTES_FIXTURE_16LE, charsetName); // changed the bytes here
        final String actual = StringUtils.newStringUtf16Le(REPRODUCED_BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test43() {
        Assert.assertNull(StringUtils.newStringUtf8(REPRODUCED_NULL_BYTES)); // changed the null bytes here
        Assert.assertNull(StringUtils.newStringIso8859_1(REPRODUCED_NULL_BYTES)); // changed the null bytes here
        Assert.assertNull(StringUtils.newStringUsAscii(REPRODUCED_NULL_BYTES)); // changed the null bytes here
        Assert.assertNull(StringUtils.newStringUtf16(REPRODUCED_NULL_BYTES)); // changed the null bytes here
        Assert.assertNull(StringUtils.newStringUtf16Be(REPRODUCED_NULL_BYTES)); // changed the null bytes here
        Assert.assertNull(StringUtils.newStringUtf16Le(REPRODUCED_NULL_BYTES)); // changed the null bytes here
    }
    @Test
    public void test44() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() {
        try {
            StringUtils.newString(REPRODUCED_BYTES_FIXTURE, "UNKNOWN"); // changed the bytes here
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(REPRODUCED_BYTES_FIXTURE, charsetName); // changed the bytes here
        final String actual = StringUtils.newStringUtf8(REPRODUCED_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final byte[] bytes = {};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final byte[] bytes = {};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final byte[] bytes = {};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final byte[] bytes = {};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final byte[] bytes = {};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() {
        try {
            StringUtils.newString(new byte[0], "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final byte[] bytes = {};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        
        // Regression test case 1: empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with special characters
        final byte[] bytes2 = {65, 66, 67, -27, -20, -32, -20, 69, 70};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test56() {
        // Regression test case 1: null byte array
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 2: empty byte array
        final byte[] bytes2 = new byte[0];
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes2));
        
        // Regression test case 3: byte array with special characters
        final byte[] bytes3 = {65, 66, 67, -27, -20, -32, -20, 69, 70};
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes3));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Change the input bytes to an empty array
        final byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Change the input bytes to contain only a single ASCII character
        final byte[] singleByte = { 65 }; // letter 'A'
        final String expected2 = new String(singleByte, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Change the input bytes to contain non-ASCII characters
        final byte[] nonAsciiBytes = "€".getBytes(charsetName); // Euro symbol
        final String expected3 = new String(nonAsciiBytes, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        final String expected1 = new String(new byte[] { 0x00, 0x61, 0x00, 0x62, 0x00, 0x63 }, charsetName);
        final String actual1 = StringUtils.newStringUtf16(new byte[] { 0x00, 0x61, 0x00, 0x62, 0x00, 0x63 });
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final String expected2 = new String(new byte[] { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43 }, charsetName);
        final String actual2 = StringUtils.newStringUtf16(new byte[] { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43 });
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression2(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_regression2, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression2);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Regression Test Case 1
        final byte[] bytes1 = "Hello World".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test Case 2
        final byte[] bytes2 = "123".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
@Test
public void test62() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    
    // Added regression test with empty byte array
    byte[] emptyBytes = new byte[0];
    Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUsAscii(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));
}
@Test
public void test63() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    
    // Added regression test with special characters
    byte[] specialBytes = new byte[] { -30, -128, -102 };
    String expectedSpecial = new String(specialBytes, charsetName);
    String actualSpecial = StringUtils.newStringUtf8(specialBytes);
    Assert.assertEquals(expectedSpecial, actualSpecial);
}
}