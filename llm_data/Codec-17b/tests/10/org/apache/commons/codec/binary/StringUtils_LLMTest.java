package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // same char sequence
    assertTrue(StringUtils.equals("abc", "abc"));

    // different char sequences
    assertFalse(StringUtils.equals("abc", "def"));

    // one char sequence is null
    assertFalse(StringUtils.equals(null, "abc"));
    assertFalse(StringUtils.equals("abc", null));

    // both char sequences are null
    assertTrue(StringUtils.equals(null, null));

    // case sensitive matching
    assertFalse(StringUtils.equals("abc", "ABC"));

    // region matches
    assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuffer("abc")));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "changed input".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("changed input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "changed input".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("changed input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "changed input".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("changed input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "changed input".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("changed input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "changed input".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("changed input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "changed input".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("changed input");
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
    assertArrayEquals(new byte[0], byteBuffer.array());
}
@Test
public void test9() {
    ByteBuffer byteBuffer = getByteBuffer("Hello World", Charset.defaultCharset());
    assertArrayEquals("Hello World".getBytes(Charset.defaultCharset()), byteBuffer.array());
}
@Test
public void test10() {
    Charset charset = Charset.forName("UTF-16");
    ByteBuffer byteBuffer = getByteBuffer("Hello World", charset);
    assertArrayEquals("Hello World".getBytes(charset), byteBuffer.array());
}
@Test
public void test11() {
    // Test with empty string
    String input1 = "";
    ByteBuffer byteBuffer1 = getByteBufferUtf8(input1);
    assertEquals(0, byteBuffer1.remaining());

    // Test with string containing only ASCII characters
    String input2 = "Hello World";
    ByteBuffer byteBuffer2 = getByteBufferUtf8(input2);
    assertEquals(input2.getBytes(StandardCharsets.UTF_8).length, byteBuffer2.remaining());

    // Test with string containing special characters
    String input3 = "!@#$%^&*()";
    ByteBuffer byteBuffer3 = getByteBufferUtf8(input3);
    assertEquals(input3.getBytes(StandardCharsets.UTF_8).length, byteBuffer3.remaining());

    // Test with string containing non-ASCII characters
    String input4 = "你好世界";
    ByteBuffer byteBuffer4 = getByteBufferUtf8(input4);
    assertEquals(input4.getBytes(StandardCharsets.UTF_8).length, byteBuffer4.remaining());
}
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String input = "Hello";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String input = "12345";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String input = "#$%^&*";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test15() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUsAscii("New String");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test16() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("New String");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test17() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("New String");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test18() {
    try {
        StringUtils.getBytesUnchecked("New String", "UNKNOWN");
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
    final byte[] actual = StringUtils.getBytesIso8859_1("New String");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test20() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("New String");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test21() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("New String");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test22() {
    Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
}
    @Test
    public void test23() throws UnsupportedEncodingException {
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
        final byte[] expected2 = "hello@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("hello@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3: String with different character casing
        final byte[] expected3 = "HeLlO".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("HeLlO");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: empty string
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: string with special characters
        final String input2 = "Hello world! @#$%^&*";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: string with non-English characters
        final String input3 = "你好，世界！";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "This is a test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("This is a test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression tests
        
        // Empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // String with special characters
        final byte[] expectedSpecial = "Hello, 世界".getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf16Le("Hello, 世界");
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
        
        // String with numbers
        final byte[] expectedNumbers = "12345".getBytes(charsetName);
        final byte[] actualNumbers = StringUtils.getBytesUtf16Le("12345");
        Assert.assertTrue(Arrays.equals(expectedNumbers, actualNumbers));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: Non-ASCII characters
        final String nonAsciiString = "äöü";
        final byte[] expected2 = nonAsciiString.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: Null string
        final byte[] expected3 = null;
        final byte[] actual3 = StringUtils.getBytesUtf8(null);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test29() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN_ENCODING_NAME");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test30() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID_ENCODING_NAME");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN_ENCODING_NAME");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "INVALID_ENCODING_NAME");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = new byte[]{0x00, 0x41, 0x00, 0x42, 0x00, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] bytes = new byte[]{0x41, 0x42, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] bytes = new byte[]{0x41, 0x42, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = new byte[]{0x41, 0x00, 0x42, 0x00, 0x43, 0x00};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = new byte[]{0x41, 0x00, 0x42, 0x00, 0x43, 0x00};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] bytes = new byte[]{0x41, 0x42, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final byte[] bytes = { 65, 66, 67, 68 };
        
        final String charsetName = "UTF-16BE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final byte[] bytes = { -65, -66, -67, -68 };
        
        final String charsetName = "UTF-16BE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final byte[] bytes = { 65, 66, 67, 68 };
        
        final String charsetName = "US-ASCII";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final byte[] bytes = { -65, -66, -67, -68 };
        
        final String charsetName = "US-ASCII";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final byte[] bytes = { 65, 66, 67, 68 };
        
        final String charsetName = "ISO-8859-1";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final byte[] bytes = { -65, -66, -67, -68 };
        
        final String charsetName = "ISO-8859-1";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final byte[] bytes = { 65, 66, 67, 68 };
        
        final String charsetName = "UTF-16";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final byte[] bytes = { -65, -66, -67, -68 };
        
        final String charsetName = "UTF-16";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final byte[] bytes = { 65, 66, 67, 68 };
        
        final String charsetName = "UTF-16LE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final byte[] bytes = { -65, -66, -67, -68 };
        
        final String charsetName = "UTF-16LE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final byte[] bytes = { 65, 66, 67, 68 };
        
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final byte[] bytes = { -65, -66, -67, -68 };
        
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test 2: byte array with a single element
        final byte[] singleByte = {97};
        final String expectedSingle = new String(singleByte, charsetName);
        final String actualSingle = StringUtils.newStringIso8859_1(singleByte);
        Assert.assertEquals(expectedSingle, actualSingle);
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: null byte array
        byte[] nullBytes = null;
        Assert.assertNull(StringUtils.newStringIso8859_1(nullBytes));
        
        // Regression test 2: byte array with null element
        final byte[] bytesWithNull = {97, 0, 98};
        Assert.assertNull(StringUtils.newStringIso8859_1(bytesWithNull));
    }
    @Test
    public void test53() {
        final byte[] bytes = new byte[]{65, 66, 67, 68, 69}; // ASCII values for A, B, C, D, E
        final String expected = "ABCDE";
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() {
        final byte[] bytes = new byte[]{70, 71, 72, 73, 74}; // ASCII values for F, G, H, I, J
        final String expected = "FGHIJ";
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() {
        final byte[] bytes = new byte[]{49, 50, 51, 52, 53}; // ASCII values for 1, 2, 3, 4, 5
        final String expected = "12345";
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() {
        final byte[] bytes = new byte[]{97, 98, 99, 100, 101}; // ASCII values for a, b, c, d, e
        final String expected = "abcde";
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        
        // Regression test 1 - Empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2 - Byte array with only 1 byte
        final byte[] bytes2 = {110}; // ASCII value for 'n'
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3 - Byte array with special characters
        final byte[] bytes3 = {-61, -87, -61, -100, -61, -113, -61, -86}; // UTF-8 representation of "éèçà"
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x30, 0x31, 0x32, 0x33});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{(byte) 0xFE, (byte) 0xFF, 0x00, 0x48, 0x00, 0x65});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{(byte) 0x00, (byte) 0x41, (byte) 0x00, (byte) 0x42, (byte) 0x00, (byte) 0x43});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x41, 0x00, 0x42, 0x00, 0x43}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x00, 0x41, 0x00, 0x42, 0x00, 0x43});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{(byte)0xFF, (byte)0xFE, 0x41, 0x00, 0x42}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{(byte)0xFF, (byte)0xFE, 0x41, 0x00, 0x42});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x41, 0x42, 0x43}, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x41, 0x42, 0x43});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {}));
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {72, 101, 108, 108, 111}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {72, 101, 108, 108, 111}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {72, 101, 108, 108, 111}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {72, 101, 108, 108, 111}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {72, 101, 108, 108, 111}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {72, 101, 108, 108, 111}));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[] {65, 66, 67}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] {65, 66, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[] {(byte) 255, (byte) 192, (byte) 224}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] {(byte) 255, (byte) 192, (byte) 224});
        Assert.assertEquals(expected, actual);
    }
}