package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test6() {
    // Arrange
    String string = null;
    Charset charset = StandardCharsets.UTF_8;

    // Act
    ByteBuffer result = getByteBuffer(string, charset);

    // Assert
    assertNull(result);
}
@Test
public void test7() {
    // Arrange
    String string = "";
    Charset charset = StandardCharsets.UTF_8;

    // Act
    ByteBuffer result = getByteBuffer(string, charset);

    // Assert
    assertEquals(0, result.capacity());
}
@Test
public void test8() {
    // Arrange
    String string = "Hello, World!";
    Charset charset = StandardCharsets.US_ASCII;

    // Act
    ByteBuffer result = getByteBuffer(string, charset);

    // Assert
    assertEquals(charset, result.charset());
}
@Test
public void test9() {
    // Arrange
    String string = "Hello, \u039A\u03B1\u03BB\u03B7\u03BC\u03AD\u03C1\u03B1!";
    Charset charset = StandardCharsets.UTF_8;

    // Act
    ByteBuffer result = getByteBuffer(string, charset);

    // Assert
    assertEquals(string, new String(result.array(), charset));
}
@Test
public void test10() {
    String string = "Hello World!";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer actual = MyClass.getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
@Test
public void test11() {
    String string = "";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer actual = MyClass.getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
@Test
public void test12() {
    String string = "Hello $@World!";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer actual = MyClass.getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
@Test
public void test13() {
    String string = "This is a long string that contains many characters. This is a long string that contains many characters. This is a long string that contains many characters.";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer actual = MyClass.getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = "Regression Test 1".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = "Regression Test 2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression3(charsetName);
        final byte[] expected = "Regression Test 3".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 3");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("New Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("New Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("New Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() {
        try {
            StringUtils.getBytesUnchecked("New Input", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("New Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("New Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("New Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
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
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final byte[] regTest1 = new byte[] {97, 98, 99, 100};
        final String input1 = new String(regTest1, charsetName);
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        final byte[] regTest2 = new byte[] {65, 66, 67, 68};
        final String input2 = new String(regTest2, charsetName);
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] regTest3 = new byte[] {33, 64, 35, 36};
        final String input3 = new String(regTest3, charsetName);
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] expected = "This is a very long string that contains multiple characters.".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("This is a very long string that contains multiple characters.");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] expected = "\u0391\u0392\u0393".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("\u0391\u0392\u0393");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("~!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        // Test with an empty string
        final byte[] expectedEmptyString = "".getBytes(charsetName);
        final byte[] actualEmptyString = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expectedEmptyString, actualEmptyString));
        
        // Test with a string containing special characters
        final String specialString = "!@#$%^&*()";
        final byte[] expectedSpecialString = specialString.getBytes(charsetName);
        final byte[] actualSpecialString = StringUtils.getBytesUtf8(specialString);
        Assert.assertTrue(Arrays.equals(expectedSpecialString, actualSpecialString));
        
        // Test with a string containing non-English characters
        final String nonEnglishString = "こんにちは";
        final byte[] expectedNonEnglishString = nonEnglishString.getBytes(charsetName);
        final byte[] actualNonEnglishString = StringUtils.getBytesUtf8(nonEnglishString);
        Assert.assertTrue(Arrays.equals(expectedNonEnglishString, actualNonEnglishString));
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
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
        try {
            StringUtils.newString(BYTES_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
   @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newString(null, null));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        try {
            StringUtils.newString(null, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(null);
        Assert.assertEquals(expected, actual);
    }
    private static void testNewString_regression(String charsetName) throws UnsupportedEncodingException {
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        // Regression test 1: Changing the input to null
        Assert.assertNull(StringUtils.newStringUtf16Be(null));

        // Regression test 2: Changing the input to empty array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test 1: Changing the input to null
        Assert.assertNull(StringUtils.newStringUsAscii(null));

        // Regression test 2: Changing the input to empty array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test 1: Changing the input to null
        Assert.assertNull(StringUtils.newStringIso8859_1(null));

        // Regression test 2: Changing the input to empty array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
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
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test Case 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test Case 2: Byte array with only one character
        final String expected2 = new String(new byte[]{65}, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(new byte[]{65});
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test Case 3: Byte array with invalid characters
        final String expected3 = new String(new byte[]{120, -30}, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(new byte[]{120, -30});
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);

        // Regression test 1
        final byte[] input1 = {(byte) 0x00, (byte) 0x41};
        final String expected1 = new String(input1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(input1);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2
        final byte[] input2 = {(byte) 0xFF, (byte) 0xFE, (byte) 0xFF, (byte) 0xFD, (byte) 0x00, (byte) 0x00};
        final String expected2 = new String(input2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(input2);
        Assert.assertEquals(expected2, actual2);

        // Regression test 3
        final byte[] input3 = {(byte) 0x00, (byte) 0x00, (byte) 0x41, (byte) 0x41};
        final String expected3 = new String(input3, charsetName);
        final String actual3 = StringUtils.newStringUtf16(input3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        
        // Regression Test Case 1
        final String expected1 = new String(new byte[]{0x00, 0x48, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F}, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x48, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F});
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test Case 2
        final String expected2 = new String(new byte[]{0x00, 0x41, 0x00, 0x42, 0x00, 0x43, 0x00, 0x44}, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x41, 0x00, 0x42, 0x00, 0x43, 0x00, 0x44});
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test Case 3
        final String expected3 = new String(new byte[]{0x7F, (byte) 0xFF, (byte) 0x80, 0x00, (byte) 0x80, 0x01, (byte) 0xFF, (byte) 0xFE}, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(new byte[]{0x7F, (byte) 0xFF, (byte) 0x80, 0x00, (byte) 0x80, 0x01, (byte) 0xFF, (byte) 0xFE});
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Regression test 1
        final byte[] bytes1 = new byte[]{0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2
        final byte[] bytes2 = new byte[]{0x00, 0x73, 0x00, 0x74, 0x00, 0x72, 0x00, 0x69, 0x00, 0x6e, 0x00, 0x67};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression test 3
        final byte[] bytes3 = new byte[]{0x00, 0x55, 0x00, 0x54, 0x00, 0x46, 0x00, 0x2d, 0x00, 0x31, 0x00, 0x36};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test65() {
        // Regression test 1: Null input for UTF-8
        Assert.assertNull(StringUtils.newStringUtf8(null));
        // Regression test 2: Null input for ISO-8859-1
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        // Regression test 3: Null input for US-ASCII
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        // Regression test 4: Null input for UTF-16
        Assert.assertNull(StringUtils.newStringUtf16(null));
        // Regression test 5: Null input for UTF-16BE
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        // Regression test 6: Null input for UTF-16LE
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Regression test 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test 2: Byte array with valid UTF-8 characters
        final byte[] validBytes = {(byte) 0xE2, (byte) 0x82, (byte) 0xAC}; // Euro sign: €
        final String expectedValid = new String(validBytes, charsetName);
        final String actualValid = StringUtils.newStringUtf8(validBytes);
        Assert.assertEquals(expectedValid, actualValid);
        
        // Regression test 3: Byte array with invalid UTF-8 characters
        final byte[] invalidBytes = {(byte) 0xC0, (byte) 0xC1, (byte) 0xFE}; // Invalid UTF-8 characters
        final String expectedInvalid = new String(invalidBytes, charsetName);
        final String actualInvalid = StringUtils.newStringUtf8(invalidBytes);
        Assert.assertEquals(expectedInvalid, actualInvalid);
        
        // Regression test 4: Byte array with a mix of valid and invalid UTF-8 characters
        final byte[] mixedBytes = {(byte) 0xE2, (byte) 0x82, (byte) 0xAC, (byte) 0xC0, (byte) 0xC1, (byte) 0xFE};
        final String expectedMixed = new String(mixedBytes, charsetName);
        final String actualMixed = StringUtils.newStringUtf8(mixedBytes);
        Assert.assertEquals(expectedMixed, actualMixed);
    }
}