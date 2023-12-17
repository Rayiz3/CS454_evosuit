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
        
        // Regression test case 1
        final byte[] actual1 = StringUtils.getBytesUtf16("Hello");
        Assert.assertEquals('H', actual1[0]);
        Assert.assertEquals(0, actual1[1]);
        Assert.assertEquals('e', actual1[2]);
        Assert.assertEquals(0, actual1[3]);
        Assert.assertEquals('l', actual1[4]);
        Assert.assertEquals(0, actual1[5]);
        Assert.assertEquals('l', actual1[6]);
        Assert.assertEquals(0, actual1[7]);
        Assert.assertEquals('o', actual1[8]);
        Assert.assertEquals(0, actual1[9]);
        
        // Regression test case 2
        final byte[] actual2 = StringUtils.getBytesUtf16("World");
        Assert.assertEquals('W', actual2[0]);
        Assert.assertEquals(0, actual2[1]);
        Assert.assertEquals('o', actual2[2]);
        Assert.assertEquals(0, actual2[3]);
        Assert.assertEquals('r', actual2[4]);
        Assert.assertEquals(0, actual2[5]);
        Assert.assertEquals('l', actual2[6]);
        Assert.assertEquals(0, actual2[7]);
        Assert.assertEquals('d', actual2[8]);
        Assert.assertEquals(0, actual2[9]);
    }
@Test
public void test1() {
    String string = null;
    Charset charset = Charset.defaultCharset();

    ByteBuffer result = getByteBuffer(string, charset);

    assertNull(result);
}
@Test
public void test2() {
    String string = "";
    Charset charset = Charset.defaultCharset();

    ByteBuffer result = getByteBuffer(string, charset);

    assertEquals(0, result.capacity());
}
@Test
public void test3() {
    String string = "Hello, World!";
    Charset charset = Charset.defaultCharset();

    ByteBuffer result = getByteBuffer(string, charset);

    assertEquals(string.length(), result.capacity());
}
@Test
public void test4() {
    String string = "Hello, World!";
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertEquals(string.length(), result.capacity());
}
@Test
public void test5() {
    String string = "Hello, World!";
    Charset charset = StandardCharsets.ISO_8859_1;

    ByteBuffer result = getByteBuffer(string, charset);

    assertEquals(string.length(), result.capacity());
}
@Test
public void test6() {
    String string = "Hello, World!@#$%^&*()";
    Charset charset = Charset.defaultCharset();

    ByteBuffer result = getByteBuffer(string, charset);

    assertEquals(string.length(), result.capacity());
}
@Test
public void test7() {
    String input = "Hello World!";
    ByteBuffer expected = // the expected result of converting the input string to UTF-8 ByteBuffer
    ByteBuffer result = MyClass.getByteBufferUtf8(input);
    assertEquals(expected, result);
}
@Test
public void test8() {
    String input = "";
    ByteBuffer expected = // the expected result of converting the input string to UTF-8 ByteBuffer
    ByteBuffer result = MyClass.getByteBufferUtf8(input);
    assertEquals(expected, result);
}
@Test
public void test9() {
    String input = "äöüß";
    ByteBuffer expected = // the expected result of converting the input string to UTF-8 ByteBuffer
    ByteBuffer result = MyClass.getByteBufferUtf8(input);
    assertEquals(expected, result);
}
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Changing input to null
        Assert.assertNull(StringUtils.getBytesUsAscii(null));
        
        // Regression test 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        final byte[] expectedMultiByte = multiByteString.getBytes(charsetName);
        final byte[] actualMultiByte = StringUtils.getBytesUsAscii(multiByteString);
        Assert.assertTrue(Arrays.equals(expectedMultiByte, actualMultiByte));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Changing input to null
        Assert.assertNull(StringUtils.getBytesUtf16Be(null));
        
        // Regression test 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        final byte[] expectedMultiByte = multiByteString.getBytes(charsetName);
        final byte[] actualMultiByte = StringUtils.getBytesUtf16Be(multiByteString);
        Assert.assertTrue(Arrays.equals(expectedMultiByte, actualMultiByte));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Changing input to null
        Assert.assertNull(StringUtils.getBytesUtf16Le(null));
        
        // Regression test 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        final byte[] expectedMultiByte = multiByteString.getBytes(charsetName);
        final byte[] actualMultiByte = StringUtils.getBytesUtf16Le(multiByteString);
        Assert.assertTrue(Arrays.equals(expectedMultiByte, actualMultiByte));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Changing input to null
        Assert.assertNull(StringUtils.getBytesIso8859_1(null));
        
        // Regression test 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        final byte[] expectedMultiByte = multiByteString.getBytes(charsetName);
        final byte[] actualMultiByte = StringUtils.getBytesIso8859_1(multiByteString);
        Assert.assertTrue(Arrays.equals(expectedMultiByte, actualMultiByte));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Changing input to null
        Assert.assertNull(StringUtils.getBytesUtf16(null));
        
        // Regression test 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        final byte[] expectedMultiByte = multiByteString.getBytes(charsetName);
        final byte[] actualMultiByte = StringUtils.getBytesUtf16(multiByteString);
        Assert.assertTrue(Arrays.equals(expectedMultiByte, actualMultiByte));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Changing input to null
        Assert.assertNull(StringUtils.getBytesUtf8(null));
        
        // Regression test 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        final byte[] expectedMultiByte = multiByteString.getBytes(charsetName);
        final byte[] actualMultiByte = StringUtils.getBytesUtf8(multiByteString);
        Assert.assertTrue(Arrays.equals(expectedMultiByte, actualMultiByte));
    }
    @Test
    public void test18() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test 1: Changing input to null
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
        
        // Regression test 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes("UNKNOWN");
        final byte[] actualEmpty = StringUtils.getBytesUnchecked("", "UNKNOWN");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        try {
            StringUtils.getBytesUnchecked(multiByteString, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test19() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
        
        // Regression test 1: Changing charsetName to null
        Assert.assertNull(StringUtils.getBytesUnchecked("", null));
        
        // Regression test 2: Changing charsetName to empty string
        final byte[] expectedEmpty = "".getBytes("");
        final byte[] actualEmpty = StringUtils.getBytesUnchecked("", "");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 3: Changing input to a multi-byte string
        final String multiByteString = "Hello World";
        Assert.assertNull(StringUtils.getBytesUnchecked(multiByteString, "UNKNOWN"));
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
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] {63, 63, 63, 63}; // Since non-ASCII characters are replaced with '?'
        final byte[] actual = StringUtils.getBytesUsAscii("漢字");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        
        // Regression test cases
        // Change input value of the method
        
        // Test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test case 2: String with special characters
        final byte[] expected2 = "abc@#$%^&*()".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("abc@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test case 3: String with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
@Test
public void test25() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "Hello".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("Hello");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test26() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "Regression1".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("Regression1");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test27() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "12345".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("12345");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        final String input2 = "H\u00E9llo W\u00F6rld";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3: String with emojis
        final String input3 = "Hello World 😀🌍";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test29() {
        try {
            StringUtils.getBytesUnchecked("Hello", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test30() {
        try {
            StringUtils.newString(new byte[]{65, 66, 67}, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = null;
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] bytes = new byte[] { -123, 55, 32, 89 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = null;
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] bytes = new byte[] { -123, 55, 32, 89 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = null;
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] bytes = new byte[] { -123, 55, 32, 89 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{0x00, 0x68, 0x00, 0x69, 0x00, 0x20, 0x00, 0x74, 0x00, 0x68, 0x00, 0x65, 0x00, 0x72, 0x00, 0x65};
        final String charsetName = "UTF-16BE";

        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{0x68, 0x65, 0x6c, 0x6c, 0x6f};
        final String charsetName = "US-ASCII";

        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final byte[] bytes= new byte[]{0x68, 0x65, 0x6c, 0x6c, 0x6f};
        final String charsetName = "ISO-8859-1";

        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{0x00, 0x68, 0x00, 0x69, 0x00, 0x20, 0x00, 0x74, 0x00, 0x68, 0x00, 0x65, 0x00, 0x72, 0x00, 0x65};
        final String charsetName = "UTF-16";

        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{0x68, 0x00, 0x69, 0x00, 0x20, 0x00, 0x74, 0x00, 0x68, 0x00, 0x65, 0x00, 0x72, 0x00, 0x65, 0x00};
        final String charsetName = "UTF-16LE";

        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{0x68, 0x65, 0x6c, 0x6c, 0x6f};
        final String charsetName = "UTF-8";

        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test for empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // Regression test for input with only special characters
        final byte[] specialCharactersBytes = {65, 66, 67, -1, -2};
        final String expectedSpecial = new String(specialCharactersBytes, charsetName);
        final String actualSpecial = StringUtils.newStringIso8859_1(specialCharactersBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);

        // Regression test for longer input
        final byte[] longerBytes = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80};
        final String expectedLonger = new String(longerBytes, charsetName);
        final String actualLonger = StringUtils.newStringIso8859_1(longerBytes);
        Assert.assertEquals(expectedLonger, actualLonger);
    }
    @Test
    public void test47() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test for null input with different character sets
        final String validCharsetName1 = "ISO-8859-1";
        final String validCharsetName2 = "UTF-8";
        final String validCharsetName3 = "US-ASCII";

        Assert.assertNull(StringUtils.newStringIso8859_1(null));

        Assert.assertNull(StringUtils.newString(validCharsetName1, null));
        Assert.assertNull(StringUtils.newString(validCharsetName2, null));
        Assert.assertNull(StringUtils.newString(validCharsetName3, null));
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Changing the input bytes to an empty array
        final byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Changing the input bytes to contain non-ASCII characters
        final byte[] nonAsciiBytes = {65, 66, 67, (byte) 195, (byte) 169};
        final String expected2 = new String(nonAsciiBytes, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Changing the charset to a non-existent one
        final String nonExistentCharsetName = "NonExistentCharset";
        final String expected3 = new String(BYTES_FIXTURE, nonExistentCharsetName);
        final String actual3 = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty input
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: Input with some special characters
        final byte[] bytes2 = new byte[] {(byte)0x41, (byte)0x00, (byte)0x42, (byte)0x00, (byte)0x43, 
                                         (byte)0x00, (byte)0x7F, (byte)0x00, (byte)0x80, (byte)0x00};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: Input with non-ASCII characters
        final byte[] bytes3 = new byte[] {(byte)0x04, (byte)0x30, (byte)0x04, (byte)0x31, (byte)0x04, 
                                         (byte)0x32, (byte)0x04, (byte)0x33, (byte)0x04, (byte)0x34};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x00, 0x43, 0x00, 0x61, 0x00, 0x74, 0x00, 0x73}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x43, 0x00, 0x61, 0x00, 0x74, 0x00, 0x73});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x06, 0x21, 0x01, 0x75, 0x03}, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x06, 0x21, 0x01, 0x75, 0x03});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty input
        final byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Input with multiple characters
        final byte[] multipleCharsBytes = {0x00, 0x41, 0x00, 0x42, 0x00, 0x43};
        final String expected2 = new String(multipleCharsBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(multipleCharsBytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Input with a null character
        final byte[] nullCharBytes = {0x00, 0x41, 0x00};
        final String expected3 = new String(nullCharBytes, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(nullCharBytes);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test53() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test55() {
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test56() {
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test59() {
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, Charsets.UTF_8);
        final String actual = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() {
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() {
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() {
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, Charsets.UTF_16);
        final String actual = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() {
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, Charsets.UTF_16BE);
        final String actual = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, Charsets.UTF_16LE);
        final String actual = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
}