package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "hello";
    assertTrue(equals(cs1, cs2));
}
@Test
public void test1() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "world";
    assertFalse(equals(cs1, cs2));
}
@Test
public void test2() {
    CharSequence cs1 = null;
    CharSequence cs2 = "hello";
    assertFalse(equals(cs1, cs2));
}
@Test
public void test3() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = null;
    assertFalse(equals(cs1, cs2));
}
@Test
public void test4() {
    CharSequence cs1 = null;
    CharSequence cs2 = null;
    assertTrue(equals(cs1, cs2));
}
@Test
public void test5() {
    CharSequence cs1 = "";
    CharSequence cs2 = "hello";
    assertFalse(equals(cs1, cs2));
}
@Test
public void test6() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "";
    assertFalse(equals(cs1, cs2));
}
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "This is a test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("This is a test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Bonjour!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Bonjour!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcde".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("abcde");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test13() {
    ByteBuffer result = getByteBuffer(null, StandardCharsets.UTF_8);
    assertNull(result);
}
@Test
public void test14() {
    ByteBuffer result = getByteBuffer("", StandardCharsets.UTF_8);
    assertEquals(0, result.remaining());
}
@Test
public void test15() {
    String input = "Hello, World!";
    ByteBuffer result = getByteBuffer(input, StandardCharsets.UTF_8);
    assertNotNull(result);
    assertEquals(input.getBytes(StandardCharsets.UTF_8).length, result.remaining());
}
@Test
public void test16() {
    String input = "Hello, World!";
    ByteBuffer result = getByteBuffer(input, Charset.forName("US-ASCII"));
    assertNotNull(result);
    assertEquals(input.getBytes(Charset.forName("US-ASCII")).length, result.remaining());
}
@Test
public void test17() {
    // Test empty string
    ByteBuffer buffer = MyClass.getByteBufferUtf8("");
    assertEquals(0, buffer.position());
    assertEquals(0, buffer.limit());

    // Test string with ASCII characters
    buffer = MyClass.getByteBufferUtf8("Hello");
    assertEquals(0, buffer.position());
    assertEquals(5, buffer.limit());
    assertEquals("Hello", new String(buffer.array(), Charsets.UTF_8));

    // Test string with non-ASCII characters
    buffer = MyClass.getByteBufferUtf8("こんにちは");
    assertEquals(0, buffer.position());
    assertEquals(15, buffer.limit());
    assertEquals("こんにちは", new String(buffer.array(), Charsets.UTF_8));
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
        final byte[] expected = "special ? characters !@#$%^&*".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("special ? characters !@#$%^&*");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "\u0394\u03B5\u03B9\u03C3\u03B2\u03AC".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("\u0394\u03B5\u03B9\u03C3\u03B2\u03AC");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() {
        try {
            StringUtils.getBytesUnchecked("Regression Test", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "     ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("     ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abc".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("abc😀");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test 1: Empty string
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2: String with special characters
        final String input2 = "&^%$#@!~";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "This is a test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("This is a test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: string with special characters
        final byte[] expected2 = "Hello @#$%".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("Hello @#$%");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: string with numbers
        final byte[] expected3 = "123456".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("123456");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
@Test
public void test39() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "Hello".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("Hello");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test40() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "World".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("World");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test41() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "12345".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("12345");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test42() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
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
    public void test45() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test46() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
        Assert.assertNull(StringUtils.newString(null, "US-ASCII"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16BE"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16LE"));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
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
        Assert.assertNull(StringUtils.newString(null, null));
    }
    @Test
    public void test55() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            StringUtils.newString(BYTES_FIXTURE, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        // change the byte array
        final byte[] bytes = {0x00, 0x7A, 0x00, 0x7A}; 
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        // change the byte array
        final byte[] bytes = {0x41, 0x42, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        // change the byte array
        final byte[] bytes = {0x41, 0x42, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        // change the byte array
        final byte[] bytes = {0x00, 0x7A, 0x00, 0x7A};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        // change the byte array
        final byte[] bytes = {0x7A, 0x00, 0x7A, 0x00};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        // change the byte array
        final byte[] bytes = {(byte)0xE6, (byte)0x97, (byte)0xA5, (byte)0xE6, (byte)0x9C, (byte)0xAC};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{65, 66, 67}, charsetName); // change the bytes to any other value
        final String actual = StringUtils.newStringIso8859_1(new byte[]{65, 66, 67}); // change the input bytes
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[] {}, charsetName); // change the bytes to an empty array
        final String actual = StringUtils.newStringIso8859_1(new byte[] {}); // change the input bytes to an empty array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{97}, charsetName); // change the bytes to a single byte value
        final String actual = StringUtils.newStringIso8859_1(new byte[]{97}); // change the input bytes to a single byte value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{104, 101, 108, 108, 111}, charsetName); // change the bytes to a string value
        final String actual = StringUtils.newStringIso8859_1(new byte[]{104, 101, 108, 108, 111}); // change the input bytes to a string value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{127}, charsetName); // change the bytes to a value outside the ISO-8859-1 range
        final String actual = StringUtils.newStringIso8859_1(new byte[]{127}); // change the input bytes to a value outside the ISO-8859-1 range
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedRegression1 = new String(emptyBytes, charsetName);
        final String actualRegression1 = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedRegression1, actualRegression1);

        // Regression test 2: Single byte array
        final byte[] singleByte = {65};
        final String expectedRegression2 = new String(singleByte, charsetName);
        final String actualRegression2 = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expectedRegression2, actualRegression2);

        // Regression test 3: Byte array with negative values
        final byte[] bytesWithNegative = {-65, -66, -67};
        final String expectedRegression3 = new String(bytesWithNegative, charsetName);
        final String actualRegression3 = StringUtils.newStringUsAscii(bytesWithNegative);
        Assert.assertEquals(expectedRegression3, actualRegression3);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        // Regression tests
        final String expected1 = "";
        final String actual1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        final String expected2 = "hello";
        final String actual2 = StringUtils.newStringUtf16(new byte[] {0, 104, 0, 101, 0, 108, 0, 108, 0, 111});
        Assert.assertEquals(expected2, actual2);
        
        final String expected3 = "123";
        final String actual3 = StringUtils.newStringUtf16(new byte[] {0, 49, 0, 50, 0, 51});
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(regressionInput1(charsetName));
        final String expected = new String(BYTES_FIXTURE_16BE_regression1(), charsetName);
        final String actual = StringUtils.newStringUtf16Be(regressionInput1());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(regressionInput2(charsetName));
        final String expected = new String(BYTES_FIXTURE_16BE_regression2(), charsetName);
        final String actual = StringUtils.newStringUtf16Be(regressionInput2());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        
        // Regression test 1
        final String regressionInput1 = "Test Input";
        final byte[] regressionBytes1 = regressionInput1.getBytes(charsetName);
        final String expected1 = new String(regressionBytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(regressionBytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2
        final String regressionInput2 = "Input #2";
        final byte[] regressionBytes2 = regressionInput2.getBytes(charsetName);
        final String expected2 = new String(regressionBytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(regressionBytes2);
        Assert.assertEquals(expected2, actual2);
    }
@Test
public void test73() {
    // Regression test for null input
    byte[] input = null;
    Assert.assertNull(StringUtils.newStringUtf8(input));
    Assert.assertNull(StringUtils.newStringIso8859_1(input));
    Assert.assertNull(StringUtils.newStringUsAscii(input));
    Assert.assertNull(StringUtils.newStringUtf16(input));
    Assert.assertNull(StringUtils.newStringUtf16Be(input));
    Assert.assertNull(StringUtils.newStringUtf16Le(input));
}
@Test
public void test74() throws UnsupportedEncodingException {
    // Regression test for valid input
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);

    // Test with empty byte array
    byte[] input = new byte[0];
    Assert.assertEquals("", StringUtils.newStringUtf8(input));

    // Test with byte array of length 1
    input = new byte[] {65};
    Assert.assertEquals("A", StringUtils.newStringUtf8(input));

    // Test with different byte array
    input = new byte[] {104, 101, 108, 108, 111};
    Assert.assertEquals("hello", StringUtils.newStringUtf8(input));

    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
}
}