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
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertTrue(actual);
}
@Test
public void test1() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "world";
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertFalse(actual);
}
@Test
public void test2() {
    CharSequence cs1 = null;
    CharSequence cs2 = "hello";
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertFalse(actual);
}
@Test
public void test3() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = null;
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertFalse(actual);
}
@Test
public void test4() {
    CharSequence cs1 = "";
    CharSequence cs2 = "";
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertTrue(actual);
}
@Test
public void test5() {
    CharSequence cs1 = "abcdef";
    CharSequence cs2 = "abcd";
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertTrue(actual);
}
@Test
public void test6() {
    CharSequence cs1 = "abcdef";
    CharSequence cs2 = "abce";
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertFalse(actual);
}
@Test
public void test7() {
    CharSequence cs1 = "HELLO";
    CharSequence cs2 = "hello";
    boolean actual = ClassUnderTest.equals(cs1, cs2);
    assertFalse(actual);
}
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        
        // Change input value of the method
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        
        // Change input value of the method
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        
        // Change input value of the method
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        
        // Change input value of the method
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        
        // Change input value of the method
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        
        // Change input value of the method
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test14() {
    String string = "Hello, World!";
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(string.getBytes(charset), result.array());
}
@Test
public void test15() {
    String string = "Hello, World!";
    Charset charset = StandardCharsets.ISO_8859_1;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(string.getBytes(charset), result.array());
}
@Test
public void test16() {
    String string = null;
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNull(result);
}
@Test
public void test17() {
    String string = "Hello World";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test18() {
    String string = "";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test19() {
    String string = "Hello!@#$%^&*() World";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression tests
        final byte[] actual2 = StringUtils.getBytesIso8859_1("");
        Assert.assertEquals(0, actual2.length);

        final byte[] actual3 = StringUtils.getBytesIso8859_1("Hello\nWorld");
        Assert.assertEquals(12, actual3.length);
        Assert.assertEquals(72, actual3[0]);
        Assert.assertEquals(101, actual3[1]);
        Assert.assertEquals(108, actual3[2]);
        Assert.assertEquals(108, actual3[3]);
        Assert.assertEquals(111, actual3[4]);
        Assert.assertEquals(10, actual3[5]);
        Assert.assertEquals(87, actual3[6]);
        Assert.assertEquals(111, actual3[7]);
        Assert.assertEquals(114, actual3[8]);
        Assert.assertEquals(108, actual3[9]);
        Assert.assertEquals(100, actual3[10]);

        final byte[] actual4 = StringUtils.getBytesIso8859_1("12345");
        Assert.assertEquals(5, actual4.length);
        Assert.assertEquals(49, actual4[0]);
        Assert.assertEquals(50, actual4[1]);
        Assert.assertEquals(51, actual4[2]);
        Assert.assertEquals(52, actual4[3]);
        Assert.assertEquals(53, actual4[4]);
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("RegressionTestInput");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTestInput");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("RegressionTestInput");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() {
        try {
            StringUtils.getBytesUnchecked("RegressionTestInput", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("RegressionTestInput");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("RegressionTestInput");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("RegressionTestInput");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%^&*".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("@#$%^&*");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#&*!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("@#&*!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "你好，世界".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("你好，世界");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World"); // Regression test 1
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        final byte[] actual2 = StringUtils.getBytesUtf8(""); // Regression test 2
        Assert.assertTrue(actual2.length == 0);
        
        final byte[] actual3 = StringUtils.getBytesUtf8("12345"); // Regression test 3
        byte[] expected3 = "12345".getBytes(charsetName);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test44() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test46() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final byte[] bytes = { 80, 76, 65, 78, 84, 72, 79, 78, 89, 79, 70, 70 };
        final String charsetName = "UTF-16BE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final byte[] bytes = { 84, 69, 83, 84, 73, 78, 71 };
        final String charsetName = "US-ASCII";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final byte[] bytes = { 72, 69, 76, 76, 79 };
        final String charsetName = "ISO-8859-1";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final byte[] bytes = { 87, 79, 82, 76, 68 };
        final String charsetName = "UTF-16";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final byte[] bytes = { 83, 84, 82, 73, 78, 71 };
        final String charsetName = "UTF-16LE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final byte[] bytes = { 72, 69, 76, 76, 79 };
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
@Test
public void test54() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString_reg(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE_reg, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_reg);
    Assert.assertEquals(expected, actual);
}
@Test
public void test55() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString_reg(charsetName);
    final String expected = new String(BYTES_FIXTURE_reg, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_reg);
    Assert.assertEquals(expected, actual);
}
@Test
public void test56() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString_reg(charsetName);
    final String expected = new String(BYTES_FIXTURE_reg, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_reg);
    Assert.assertEquals(expected, actual);
}
@Test
public void test57() {
    Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
}
@Test
public void test58() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString_reg(charsetName);
    final String expected = new String(BYTES_FIXTURE_reg, charsetName);
    final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_reg);
    Assert.assertEquals(expected, actual);
}
@Test
public void test59() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString_reg(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE_reg, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_reg);
    Assert.assertEquals(expected, actual);
}
@Test
public void test60() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
}
@Test
public void test61() {
    try {
        StringUtils.newString(BYTES_FIXTURE_reg, "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test62() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString_reg(charsetName);
    final String expected = new String(BYTES_FIXTURE_reg, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_reg);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test65() {
        byte[] bytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringIso8859_1(bytes));
    }
    @Test
    public void test66() {
        byte[] bytes = new byte[] {65, 66, 67, 195, 169};
        Assert.assertEquals("ABCé", StringUtils.newStringIso8859_1(bytes));
    }
    @Test
    public void test67() {
        byte[] bytes = new byte[] {65, 66, 67, -1, -2};
        Assert.assertEquals("ABC�?", StringUtils.newStringIso8859_1(bytes));
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Regression test case 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // Regression test case 2: Single byte
        final byte[] singleByte = {65};
        final String expectedSingle = new String(singleByte, charsetName);
        final String actualSingle = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expectedSingle, actualSingle);
        
        // Regression test case 3: Multiple bytes
        final byte[] multipleBytes = {65, 66, 67};
        final String expectedMultiple = new String(multipleBytes, charsetName);
        final String actualMultiple = StringUtils.newStringUsAscii(multipleBytes);
        Assert.assertEquals(expectedMultiple, actualMultiple);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";

        // Test with empty byte array
        byte[] bytesEmpty = {};
        final String expectedEmpty = new String(bytesEmpty, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(bytesEmpty);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // Test with byte array containing a single character
        byte[] bytesSingleChar = {0, 65};
        final String expectedSingleChar = new String(bytesSingleChar, charsetName);
        final String actualSingleChar = StringUtils.newStringUtf16(bytesSingleChar);
        Assert.assertEquals(expectedSingleChar, actualSingleChar);

        // Test with byte array containing multiple characters
        byte[] bytesMultipleChars = {0, 65, 0, 66, 0, 67};
        final String expectedMultipleChars = new String(bytesMultipleChars, charsetName);
        final String actualMultipleChars = StringUtils.newStringUtf16(bytesMultipleChars);
        Assert.assertEquals(expectedMultipleChars, actualMultipleChars);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x00, 0x00, 0x48, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x48, 0x65, 0x6C, 0x6C, 0x6F});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x00, 0x4D, 0x61, 0x72, 0x6B}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x4D, 0x61, 0x72, 0x6B});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x68, 0x65, 0x6C, 0x6C, 0x6F}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x68, 0x65, 0x6C, 0x6C, 0x6F});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Regression Test Case 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected1, actual1);

        // Regression Test Case 2: Byte array with one byte
        final byte[] bytes2 = { (byte) 0xFF };
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression Test Case 3: Byte array with multiple bytes
        final byte[] bytes3 = { 0x00, (byte) 0xF0, (byte) 0xFF, 0x7F };
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test74() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case with empty input
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
        Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));
        Assert.assertEquals("", StringUtils.newStringUsAscii(emptyBytes));
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
        Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
        Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case with special characters input
        byte[] specialCharsBytes = {-27, -117, -80, -27, -100, -91, -27, -101, -67};
        final String expectedSpecialChars = new String(specialCharsBytes, charsetName);
        final String actualSpecialChars = StringUtils.newStringUtf8(specialCharsBytes);
        Assert.assertEquals(expectedSpecialChars, actualSpecialChars);
    }
}