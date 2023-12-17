package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    assertTrue(StringUtils.equals("abc", "abc"));
}
@Test
public void test1() {
    assertFalse(StringUtils.equals("abc", "def"));
}
@Test
public void test2() {
    assertFalse(StringUtils.equals(null, "abc"));
    assertFalse(StringUtils.equals("abc", null));
}
@Test
public void test3() {
    assertTrue(StringUtils.equals("", ""));
}
@Test
public void test4() {
    assertTrue(StringUtils.equals(new String("abc"), "abc"));
    assertTrue(StringUtils.equals("abc", new String("abc")));
}
@Test
public void test5() {
    assertFalse(StringUtils.equals("abc", "abcd"));
    assertFalse(StringUtils.equals("abcd", "abc"));
}
@Test
public void test6() {
    assertTrue(StringUtils.equals("abc", "ABC"));
}
@Test
public void test7() {
    assertFalse(StringUtils.equals("abc", "DEF"));
}
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test14() {
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer result = getByteBuffer(null, charset);
    assertNull(result);
}
@Test
public void test15() {
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer result = getByteBuffer("", charset);
    assertEquals(0, result.capacity());
}
@Test
public void test16() {
    Charset charset = StandardCharsets.UTF_8;
    String string = "Hello 你好";
    ByteBuffer result = getByteBuffer(string, charset);
    assertEquals(string.getBytes(charset), result.array());
}
@Test
public void test17() {
    Charset charset = StandardCharsets.UTF_16;
    String string = "Hello";
    ByteBuffer result = getByteBuffer(string, charset);
    assertEquals(string.getBytes(charset), result.array());
}
@Test
public void test18() {
    // Test when the string is null
    assertNull(getByteBufferUtf8(null));

    // Test when the string is empty
    assertEquals(ByteBuffer.wrap(new byte[0]), getByteBufferUtf8(""));

    // Test when the string contains ASCII characters
    assertEquals(ByteBuffer.wrap("Hello".getBytes(Charsets.UTF_8)), getByteBufferUtf8("Hello"));

    // Test when the string contains special characters
    assertEquals(ByteBuffer.wrap("😊".getBytes(Charsets.UTF_8)), getByteBufferUtf8("😊"));

    // Test when the string contains multibyte characters
    assertEquals(ByteBuffer.wrap("안녕하세요".getBytes(Charsets.UTF_8)), getByteBufferUtf8("안녕하세요"));
}
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression Test 2: String with special characters
        final byte[] expected2 = "Hello, world!@#$%^&*()".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("Hello, world!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression Test 3: String with multibyte characters
        final byte[] expected3 = "你好，世界".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("你好，世界");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
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
        // Change the input value of the method
        final byte[] expected = "Regression Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        // Change the input value of the method
        final byte[] expected = "Regression Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        // Change the input value of the method
        final byte[] expected = "Regression Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test27() {
        try {
            // Change the input value of the method
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        // Change the input value of the method
        final byte[] expected = "Regression Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        // Change the input value of the method
        final byte[] expected = "Regression Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        // Change the input value of the method
        final byte[] expected = "Regression Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test35() {
        // Change the input value of the method
        Assert.assertNull(StringUtils.getBytesUnchecked("Regression Test", "UNKNOWN"));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        final byte[] expected2 = "hello~!@#$%^&*()".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("hello~!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: String with uppercase and lowercase letters
        final byte[] expected3 = "Hello World".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "#%*!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("#%*!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2: String with only one character
        final byte[] expected2 = "A".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("A");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with special characters
        final byte[] expected3 = "ä#$%".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("ä#$%");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression Test 2: String with special characters
        final byte[] expected2 = "äçéìñ".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("äçéìñ");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression Test 3: String with digits
        final byte[] expected3 = "1234567890".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8("1234567890");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression Test 4: String with whitespace
        final byte[] expected4 = "    ".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf8("    ");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test46() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_regression, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_regression);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_regression);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regression, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regression);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test55() {
        try {
            StringUtils.newString(BYTES_FIXTURE_regression, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_regression);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{(byte)0xFE, (byte)0xFF}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{(byte)0xFE, (byte)0xFF});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[]{65, 66, 67}, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{65, 66, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{48, 49, 50}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{48, 49, 50});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{(byte)0xFF, (byte)0xFE}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{(byte)0xFF, (byte)0xFE});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[]{97, 98, 99}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{97, 98, 99});
        Assert.assertEquals(expected, actual);
    }
@Test
public void test62() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    
    // Regression test case 1: Test with empty input
    final String expected1 = new String(new byte[]{}, charsetName);
    final String actual1 = StringUtils.newStringIso8859_1(new byte[]{});
    Assert.assertEquals(expected1, actual1);
    
    // Regression test case 2: Test with non-English characters
    final String expected2 = new String(new byte[]{65, 66, 67, -61, -95, -48, -128}, charsetName);
    final String actual2 = StringUtils.newStringIso8859_1(new byte[]{65, 66, 67, -61, -95, -48, -128});
    Assert.assertEquals(expected2, actual2);
}
@Test
public void test63() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    
    // Regression test case 1: Test with null input containing non-English characters
    final byte[] bytes1 = null;
    final String charsetName = "ISO-8859-1";
    final String expected1 = new String(bytes1, charsetName);
    final String actual1 = StringUtils.newStringIso8859_1(bytes1);
    Assert.assertEquals(expected1, actual1);
    
    // Regression test case 2: Test with null input containing empty array
    final byte[] bytes2 = null;
    final String expected2 = new String(new byte[]{}, charsetName);
    final String actual2 = StringUtils.newStringIso8859_1(bytes2);
    Assert.assertEquals(expected2, actual2);
}
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(EMPTY_BYTE_ARRAY);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(SINGLE_BYTE_ARRAY);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(MULTI_BYTE_ARRAY);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(NULL_BYTE_ARRAY);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() {
        final byte[] bytes = new byte[0]; // empty byte array
        final String expected = new String(bytes, Charsets.UTF_16);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() {
        final byte[] bytes = null; // null byte array
        final String expected = new String(bytes, Charsets.UTF_16);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() {
        final byte[] bytes = { 97 }; // byte array with single byte
        final String expected = new String(bytes, Charsets.UTF_16);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() {
        final byte[] bytes = { 104, 101, 108, 108, 111 }; // byte array with multiple bytes
        final String expected = new String(bytes, Charsets.UTF_16);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() {
        final byte[] bytes = { -17, -69, -65 }; // byte array containing special characters
        final String expected = new String(bytes, Charsets.UTF_16);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);

        // Regression Test 1: Empty input
        final String actual1 = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected, actual1);

        // Regression Test 2: Input with single byte
        final byte[] bytes2 = new byte[]{0x00};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression Test 3: Input with multiple bytes
        final byte[] bytes3 = new byte[]{0x00, 0x61, 0x00, 0x62};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        
        // Regression Test 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2: Byte array with special characters
        final byte[] specialBytes = {(byte) 0x00, (byte) 0xd8, (byte) 0x09};
        final String expected2 = new String(specialBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(specialBytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test 3: Byte array with extended ASCII characters
        final byte[] extendedBytes = {(byte) 0x00, (byte) 0xC4, (byte) 0x00, (byte) 0xC5};
        final String expected3 = new String(extendedBytes, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(extendedBytes);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test75() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        // Regression test case: empty input
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case: invalid input
        byte[] invalidBytes = new byte[] { -1, -2, -3 };
        Assert.assertEquals("���", StringUtils.newStringUtf8(invalidBytes));
    }
}