package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        CharSequence cs1 = "abc";
        CharSequence cs2 = "abc";
        assertTrue(StringUtils.equals(cs1, cs2));
    }
    @Test
    public void test1() {
        CharSequence cs1 = "abc";
        CharSequence cs2 = null;
        assertFalse(StringUtils.equals(cs1, cs2));
    }
    @Test
    public void test2() {
        CharSequence cs1 = "abc";
        CharSequence cs2 = "def";
        assertFalse(StringUtils.equals(cs1, cs2));
    }
    @Test
    public void test3() {
        CharSequence cs1 = "";
        CharSequence cs2 = "abc";
        assertFalse(StringUtils.equals(cs1, cs2));
    }
    @Test
    public void test4() {
        CharSequence cs1 = "abc";
        CharSequence cs2 = new StringBuilder("abc");
        assertTrue(StringUtils.equals(cs1, cs2));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "A".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("A");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, © World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, © World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "  \t  \n".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("  \t  \n");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, ä World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello, ä World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test11() {
    String string = "Hello, World!";
    Charset charset = StandardCharsets.UTF_8;
    
    ByteBuffer byteBuffer = getByteBuffer(string, charset);
    
    assertNotNull(byteBuffer);
    assertEquals(string, new String(byteBuffer.array(), charset));
}
@Test
public void test12() {
    String string = null;
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer byteBuffer = getByteBuffer(string, charset);

    assertNull(byteBuffer);
}
@Test
public void test13() {
    String string = "Hello World";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test14() {
    String string = "";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test15() {
    String string = "çãÇÃ";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression Test Case 1: Changing input to null
        try {
            StringUtils.getBytesIso8859_1(null);
            Assert.fail("Expected NullPointerException, but no exception was thrown");
        } catch (NullPointerException e) {
            // Exception was expected
        }

        // Regression Test Case 2: Changing input to empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Regression Test Case 3: Changing input to string with non-ISO-8859-1 characters
        final String nonIsoString = "©";
        final byte[] expectedNonIso = nonIsoString.getBytes(charsetName);
        final byte[] actualNonIso = StringUtils.getBytesIso8859_1(nonIsoString);
        Assert.assertTrue(Arrays.equals(expectedNonIso, actualNonIso));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        
        // Regression Test
        final byte[] expected = "regression".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("regression");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        
        // Regression Test
        final byte[] expected = "regression".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("regression");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        
        // Regression Test
        final byte[] expected = "regression".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("regression");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        
        // Regression Test
        final byte[] expected = "regression".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("regression");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        
        // Regression Test
        final byte[] expected = "regression".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("regression");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        
        // Regression Test
        final byte[] expected = "regression".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("regression");
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
        final byte[] expected = "Hello".getBytes(charsetName); // changing input value to "Hello"
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // changing input value to empty string
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName); // changing input value to "12345"
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression Test Case 1
        final byte[] expected1 = "Hello".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("Hello");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression Test Case 2
        final byte[] expected2 = "".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression Test Case 3
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        // Regression Test Case 4
        final byte[] expected4 = "Test123!@#".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16("Test123!@#");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final String emptyString = "";
        final String longString = "This is a long string that exceeds the limit of UTF-16BE encoding";
        final String specialCharacters = "❤️🌎🔥";  

        // Test with an empty string
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Test with a long string
        final byte[] expectedLong = longString.getBytes(charsetName);
        final byte[] actualLong = StringUtils.getBytesUtf16Be(longString);
        Assert.assertTrue(Arrays.equals(expectedLong, actualLong));

        // Test with special characters
        final byte[] expectedSpecial = specialCharacters.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf16Be(specialCharacters);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
    }
@Test
public void test30() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "Hello, World!".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("Hello, World!");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test31() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test32() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "Hello, 世界!".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("Hello, 世界!");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final String newString = "New String";
        final byte[] expectedNew = newString.getBytes(charsetName);
        final byte[] actualNew = StringUtils.getBytesUtf8(newString);
        Assert.assertTrue(Arrays.equals(expectedNew, actualNew));
        
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf8(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
    }
    @Test
    public void test34() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test 1
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16LE");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test 2
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-13");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test 1
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16LE");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test 2
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-13");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
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
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test Case 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression Test Case 2: Byte array with special characters
        final byte[] specialBytes = { 72, 101, 108, 108, 111, (byte) 165, 33 }; // "Hello¥!"
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringIso8859_1(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test55() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression Test Case 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringIso8859_1(emptyBytes));
        
        // Regression Test Case 2: Byte array with special characters
        final byte[] specialBytes = { 72, 101, 108, 108, 111, (byte) 165, 33 }; // "Hello¥!"
        Assert.assertNull(StringUtils.newStringIso8859_1(specialBytes));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression Test Case 1: Empty Input
        final String expectedEmptyInput = new String(new byte[0], charsetName);
        final String actualEmptyInput = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expectedEmptyInput, actualEmptyInput);

        // Regression Test Case 2: Input with special characters
        final byte[] specialChars = { 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96, 123, 124, 125, 126 };
        final String expectedSpecialChars = new String(specialChars, charsetName);
        final String actualSpecialChars = StringUtils.newStringUsAscii(specialChars);
        Assert.assertEquals(expectedSpecialChars, actualSpecialChars);

        // Regression Test Case 3: Input with non-ASCII characters
        final byte[] nonAsciiChars = { (byte) 128, (byte) 129, (byte) 130, (byte) 131, (byte) 132, (byte) 133, (byte) 134, (byte) 135, (byte) 136, (byte) 137, (byte) 138, (byte) 139, (byte) 140, (byte) 141, (byte) 142, (byte) 143, (byte) 144, (byte) 145, (byte) 146, (byte) 147, (byte) 148, (byte) 149, (byte) 150, (byte) 151, (byte) 152, (byte) 153, (byte) 154, (byte) 155, (byte) 156, (byte) 157, (byte) 158, (byte) 159 };
        final String expectedNonAsciiChars = new String(nonAsciiChars, charsetName);
        final String actualNonAsciiChars = StringUtils.newStringUsAscii(nonAsciiChars);
        Assert.assertEquals(expectedNonAsciiChars, actualNonAsciiChars);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);  
 
        // Regression test 1: Empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2: Single byte array
        final byte[] bytes2 = new byte[] { 65 };
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression test 3: Large byte array
        final byte[] bytes3 = new byte[] { 97, 98, 99, 100, 101 };
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
@Test
public void test58() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test59() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test60() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[] { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43 }, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(new byte[] { 0x00, 0x61, 0x00, 0x62, 0x00, 0x63 }, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] { 0x00, 0x61, 0x00, 0x62, 0x00, 0x63 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case 1: Empty byte array
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[0]));

        // Regression test case 2: Byte array with only 1 byte
        byte[] singleByte = new byte[]{65}; // ASCII value for 'A'
        Assert.assertEquals("A", StringUtils.newStringUtf8(singleByte));

        // Regression test case 3: Byte array with special characters
        byte[] specialChars = new byte[]{35, 36, 37, 64}; // ASCII values for '#', '$', '%' and '@'
        Assert.assertEquals("#$%@",
                StringUtils.newStringUtf8(specialChars));
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test case 4: Byte array with multi-byte characters
        byte[] multiBytes = new byte[]{-61, -74, -61, -87, -61, -97}; // UTF-8 bytes for 'ê', 'û' and 'ı'
        Assert.assertEquals("êûı",
                StringUtils.newStringUtf8(multiBytes));
    }
}