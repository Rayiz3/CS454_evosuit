package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Original test cases
    Assert.assertTrue(StringUtils.equals(null, null));
    Assert.assertFalse(StringUtils.equals("abc", null));
    Assert.assertFalse(StringUtils.equals(null, "abc"));
    Assert.assertTrue(StringUtils.equals("abc", "abc"));
    Assert.assertFalse(StringUtils.equals("abc", "abcd"));
    Assert.assertFalse(StringUtils.equals("abcd", "abc"));
    Assert.assertFalse(StringUtils.equals("abc", "ABC"));
    
    // Additional test cases
    Assert.assertFalse(StringUtils.equals("", null)); // cs1 is empty
    Assert.assertFalse(StringUtils.equals("abc", "")); // cs2 is empty
    Assert.assertTrue(StringUtils.equals("", "")); // both cs1 and cs2 are empty
    Assert.assertFalse(StringUtils.equals("abc", "def")); // both cs1 and cs2 have different values
}
@Test
public void test1() {
    // Original test cases
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
    Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
    Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
    
    // Additional test cases
    Assert.assertFalse(StringUtils.equals(new StringBuilder(""), null)); // cs1 is empty
    Assert.assertFalse(StringUtils.equals(null, new StringBuilder(""))); // cs2 is empty
    Assert.assertTrue(StringUtils.equals(new StringBuilder(""), new StringBuilder(""))); // both cs1 and cs2 are empty
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("def"))); // both cs1 and cs2 have different values
}
@Test
public void test2() {
    // Original test cases
    Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
    Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
    
    // Additional test cases
    Assert.assertFalse(StringUtils.equals("", new StringBuilder("abc"))); // cs1 is empty
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "")); // cs2 is empty
    Assert.assertTrue(StringUtils.equals("", new StringBuilder(""))); // both cs1 and cs2 are empty
    Assert.assertFalse(StringUtils.equals("abc", "def")); // both cs1 and cs2 have different values
}
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.toUpperCase().getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE.toLowerCase());
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.toLowerCase().getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE.toUpperCase());
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello world! 喂".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello world! 喂");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = " ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(" ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test10() {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test11() {
        final String string = "  ";
        final byte[] expected = new byte[] {32, 32};
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test12() {
        final String string = "&$#@!";
        final byte[] expected = new byte[] {38, 36, 35, 64, 33};
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test13() {
        final String string = "äöü";
        final byte[] expected = new byte[] {-61, -74, -61, -92, -61, -68};
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Special characters: üøþ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Special characters: üøþ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // New test cases
        // Test for an empty string
        final String emptyString = "";
        final byte[] emptyExpected = emptyString.getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));

        // Test for a string with one character
        final String oneCharString = "a";
        final byte[] oneCharExpected = oneCharString.getBytes(charsetName);
        final byte[] oneCharActual = StringUtils.getBytesUsAscii(oneCharString);
        Assert.assertTrue(Arrays.equals(oneCharExpected, oneCharActual));

        // Test for a string with numbers and special characters
        final String specialString = "@1#+%^&*()";
        final byte[] specialExpected = specialString.getBytes(charsetName);
        final byte[] specialActual = StringUtils.getBytesUsAscii(specialString);
        Assert.assertTrue(Arrays.equals(specialExpected, specialActual));

        // Test for a string with lower case and upper case letters
        final String mixedString = "aBcDeFgHiJkLmNoPqRsTuVwXyZ";
        final byte[] mixedExpected = mixedString.getBytes(charsetName);
        final byte[] mixedActual = StringUtils.getBytesUsAscii(mixedString);
        Assert.assertTrue(Arrays.equals(mixedExpected, mixedActual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes("ISO-8859-1");
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Ψ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Ψ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: string with only special characters
        final String specialChars = "!@#$%^&*()_+";
        final byte[] expectedSpecialChars = specialChars.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf16Be(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
        
        // Regression test 3: string with mix of uppercase and lowercase letters
        final String mixedCase = "HeLLoWoRLd";
        final byte[] expectedMixedCase = mixedCase.getBytes(charsetName);
        final byte[] actualMixedCase = StringUtils.getBytesUtf16Be(mixedCase);
        Assert.assertTrue(Arrays.equals(expectedMixedCase, actualMixedCase));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUtf16Le("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%^&*".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("@#$%^&*");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("HELLO");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test33() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "US-ASCII");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test34() {
    try {
        StringUtils.getBytesUnchecked(STRING_FIXTURE, "US-ASCII");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test35() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "UTF-16");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test36() {
    try {
        StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test Case 1: Null bytes input
        final String actualNullInput = StringUtils.newStringIso8859_1(null);
        Assert.assertNull(actualNullInput);
        
        // Regression Test Case 2: Empty bytes input
        final byte[] emptyBytes = {};
        final String expectedEmptyInput = new String(emptyBytes, charsetName);
        final String actualEmptyInput = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmptyInput, actualEmptyInput);
        
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = null; // input changed
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() {
        byte[] bytes = null; // input changed
        try {
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = null; // input changed
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] bytes = null; // input changed
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = null; // input changed
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() {
        byte[] bytes = null; // input changed
        Assert.assertNull(StringUtils.newString(bytes, "UNKNOWN"));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        byte[] bytes = null; // input changed
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = null; // input changed
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() {
        byte[] bytes = null; // input changed
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        // Test with a different byte array
        byte[] differentBytes = { 72, 101, 108, 108, 111 };
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringIso8859_1(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);

        // Test with an empty byte array
        byte[] emptyBytes = {};
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test48() {
        // Test with a null byte array
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Test with a byte array containing null bytes
        byte[] bytesWithNull = { 72, 101, 108, 108, 111, 0, 87, 111, 114, 108, 100 };
        Assert.assertNull(StringUtils.newStringUtf8(bytesWithNull));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUsAscii(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUtf16(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytesWithNull));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytesWithNull));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final byte[] differentBytesFixture = {97, 98, 99, 100}; // different bytes from BYTES_FIXTURE
        final String expected = new String(differentBytesFixture, charsetName);
        final String actual = StringUtils.newStringUsAscii(differentBytesFixture);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        final byte[] differentBytesFixture = {97, 98, 99, 100}; // different bytes from BYTES_FIXTURE
        Assert.assertNull(StringUtils.newStringUtf8(differentBytesFixture));
        Assert.assertNull(StringUtils.newStringIso8859_1(differentBytesFixture));
        Assert.assertNull(StringUtils.newStringUsAscii(differentBytesFixture));
        Assert.assertNull(StringUtils.newStringUtf16(differentBytesFixture));
        Assert.assertNull(StringUtils.newStringUtf16Be(differentBytesFixture));
        Assert.assertNull(StringUtils.newStringUtf16Le(differentBytesFixture));
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Additional test case with empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Additional test case with empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final byte[] differentBytes = new byte[]{65, 66, 67};
        // different input value
        final String expected = new String(differentBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(differentBytes);
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
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test for newStringUtf16Le
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {0}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {0, 0}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {0, 1}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {1, 0}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {127, 1}));

        byte[] bytesWithNullByte = new byte[5];
        Assert.assertNull(StringUtils.newStringUtf16Le(bytesWithNullByte));
        bytesWithNullByte[2] = 1;
        Assert.assertNull(StringUtils.newStringUtf16Le(bytesWithNullByte));
        bytesWithNullByte[2] = 0;
        Assert.assertNull(StringUtils.newStringUtf16Le(bytesWithNullByte));
        bytesWithNullByte[0] = 1;
        bytesWithNullByte[1] = 1;
        bytesWithNullByte[2] = 1;
        bytesWithNullByte[3] = 1;
        bytesWithNullByte[4] = 1;
        Assert.assertNull(StringUtils.newStringUtf16Le(bytesWithNullByte));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test for newStringUtf16Le
        byte[] modifiedBytes = new byte[] {-128, 1, 127};
        Assert.assertNotEquals(expected, StringUtils.newStringUtf16Le(modifiedBytes));
        modifiedBytes = new byte[] {-128, 1, 0, 1, 127};
        Assert.assertNotEquals(expected, StringUtils.newStringUtf16Le(modifiedBytes));
        modifiedBytes = new byte[] {-128, 1, 0, -1, 127};
        Assert.assertNotEquals(expected, StringUtils.newStringUtf16Le(modifiedBytes));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // New test case - null input
        final byte[] bytesNull = null;
        Assert.assertNull(StringUtils.newStringUtf8(bytesNull));
        
        // New test case - empty input
        final byte[] bytesEmpty = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf8(bytesEmpty));

        // New test case - input with special characters
        final byte[] bytesSpecial = { 71, 114, 101, 101, 116, 105, 110, 103, 115, 33 };
        Assert.assertEquals("Greetings!", StringUtils.newStringUtf8(bytesSpecial));
    }
}