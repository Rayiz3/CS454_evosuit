package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("This is a regression test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: string with special characters
        final byte[] expected2 = "áéíóú".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("áéíóú");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: string with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello, World!"); // changed input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, World!"); // changed input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello, World!"); // changed input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() {
        try {
            StringUtils.getBytesUnchecked("Hello, World!", "UNKNOWN"); // changed input here
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello, World!"); // changed input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello, World!"); // changed input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, World!"); // changed input here
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Additional test cases
        // Test case with an empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Test case with a string containing special characters
        final String specialCharacters = "!@#$%^&*()";
        final byte[] expectedSpecial = specialCharacters.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUsAscii(specialCharacters);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));

        // Test case with a string containing uppercase and lowercase letters
        final String mixedCase = "AbCdEfGhIjKlMnOp";
        final byte[] expectedMixed = mixedCase.getBytes(charsetName);
        final byte[] actualMixed = StringUtils.getBytesUsAscii(mixedCase);
        Assert.assertTrue(Arrays.equals(expectedMixed, actualMixed));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        final String input2 = "Hello, World!";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final String input3 = "12345";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String input = "Hello, 世界!";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        // Existing test case
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1 - Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test case 2 - String with special characters
        final String specialString = "Hello, world! \u00A9";
        final byte[] expectedSpecial = specialString.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf16Le(specialString);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
        
        // Regression test case 3 - String with non-Latin characters
        final String nonLatinString = "你好，世界！";
        final byte[] expectedNonLatin = nonLatinString.getBytes(charsetName);
        final byte[] actualNonLatin = StringUtils.getBytesUtf16Le(nonLatinString);
        Assert.assertTrue(Arrays.equals(expectedNonLatin, actualNonLatin));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ENCODING");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test21() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ENCODING");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_regression, charsetName); // modified input
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression); // modified input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName); // modified input
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_regression); // modified input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName); // modified input
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression); // modified input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName); // modified input
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_regression); // modified input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regression, charsetName); // modified input
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regression); // modified input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression, charsetName); // modified input
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_regression); // modified input
        Assert.assertEquals(expected, actual);
    }
@Test
public void test28() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE_regress, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regress);
    Assert.assertEquals(expected, actual);
}
@Test
public void test29() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_regress, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_regress);
    Assert.assertEquals(expected, actual);
}
@Test
public void test30() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_regress, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regress);
    Assert.assertEquals(expected, actual);
}
@Test
public void test31() {
    Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
}
@Test
public void test32() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_regress, charsetName);
    final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_regress);
    Assert.assertEquals(expected, actual);
}
@Test
public void test33() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE_regress, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regress);
    Assert.assertEquals(expected, actual);
}
@Test
public void test34() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
}
@Test
public void test35() {
    try {
        StringUtils.newString(BYTES_FIXTURE_regress, "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test36() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_regress, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_regress);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        
        // Regression test 1
        byte[] bytes1 = {65, 66, 67, 68};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2
        byte[] bytes2 = {};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test38() {
        // Regression test 1
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Regression test 2
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Regression test 3
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        
        // Regression test 4
        Assert.assertNull(StringUtils.newStringUtf16(null));
        
        // Regression test 5
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        
        // Regression test 6
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[]{65, 66, 67}, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{65, 66, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Regression test 1: Empty byte array
        byte[] bytes = new byte[0];
        final String expected1 = new String(bytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Byte array with single byte
        bytes = new byte[]{0x41};
        final String expected2 = new String(bytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Byte array with multiple bytes
        bytes = new byte[]{0x41, 0x42, 0x43, 0x44};
        final String expected3 = new String(bytes, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected3, actual3);
        
        // Regression test 4: Byte array with maximum length
        bytes = new byte[65535];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (i % 256);
        }
        final String expected4 = new String(bytes, charsetName);
        final String actual4 = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2: Byte array with one character
        final byte[] bytes2 = {72, 0}; // Letter 'H' in UTF-16BE encoding
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression test 3: Byte array with multiple characters
        final byte[] bytes3 = {72, 0, 101, 0, 108, 0, 108, 0, 111, 0}; // String "Hello" in UTF-16BE encoding
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Existing test cases
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        byte[] bytes1 = { 0x00, 0x68, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F };
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        byte[] bytes2 = { 0x05, 0x00, 0x68, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F };
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        byte[] bytes3 = { 0x00, 0x68, 0x00, 0x65, 0x01, 0x6C, 0x00, 0x6C, 0x00, 0x6F };
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newStringUtf8(null));              // Regression Test Case 1
        Assert.assertNull(StringUtils.newStringIso8859_1(null));         // Regression Test Case 2
        Assert.assertNull(StringUtils.newStringUsAscii(null));           // Regression Test Case 3
        Assert.assertNull(StringUtils.newStringUtf16(null));             // Regression Test Case 4
        Assert.assertNull(StringUtils.newStringUtf16Be(null));           // Regression Test Case 5
        Assert.assertNull(StringUtils.newStringUtf16Le(null));           // Regression Test Case 6
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));     // Regression Test Case 7
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {}));// Regression Test Case 8
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {}));  // Regression Test Case 9
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {}));    // Regression Test Case 10
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {}));  // Regression Test Case 11
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {}));  // Regression Test Case 12
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);   // Regression Test Case 13
        Assert.assertEquals(expected, actual);
        
        final byte[] bytes = new byte[] {-50, -70, -55, -20};
        final String actual2 = StringUtils.newStringUtf8(bytes);          // Regression Test Case 14
        Assert.assertEquals("私の", actual2);
    }
}