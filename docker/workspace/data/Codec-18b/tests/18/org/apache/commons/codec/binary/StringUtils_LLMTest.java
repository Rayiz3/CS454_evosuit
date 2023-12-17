package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Original test case
    Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
    
    // Additional test cases
    Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("ABC")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc ")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ab c"));
}
@Test
public void test1() {
    // Original test cases
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
    Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
    
    // Additional test cases
    Assert.assertTrue(StringUtils.equals(null, new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("ab"), new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
}
@Test
public void test2() {
    // Original test cases
    Assert.assertTrue(StringUtils.equals(null, null));
    Assert.assertFalse(StringUtils.equals("abc", null));
    Assert.assertFalse(StringUtils.equals(null, "abc"));
    Assert.assertTrue(StringUtils.equals("abc", "abc"));
    Assert.assertFalse(StringUtils.equals("abc", "ABC"));
    
    // Additional test cases
    Assert.assertFalse(StringUtils.equals("abc", "abc "));
    Assert.assertFalse(StringUtils.equals("abc", "abcd"));
    Assert.assertTrue(StringUtils.equals("ABC", "ABC"));
}
@Test
public void test3() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked_modified(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE + " ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test4() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked_modified(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE + " ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test5() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked_modified(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE + " ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test6() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked_modified(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE + " ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test7() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked_modified(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE + " ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test8() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked_modified(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE + " ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test9() {
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertNull(actual);
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1("  ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final byte[] expected = {-61, -68, -65, -62, -68, -60, -62, -66, -66, -60};
        final byte[] actual = StringUtils.getBytesIso8859_1("äüößÄÜÖ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UNKNOWN";
        testGetBytesUnchecked(charsetName);
        try {
            StringUtils.getBytesUtf16Be(STRING_FIXTURE);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test14() {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUnchecked(null, charsetName);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test16() {
        final String charsetName = "NON_EXISTING";
        testGetBytesUnchecked(charsetName);
        try {
            StringUtils.getBytesUtf8(STRING_FIXTURE);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String input = "こんにちは";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String input = "こんにちは";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final String input = "こんにちは";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final String input = "こんにちは";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final String input = "こんにちは";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Test case with empty string
        final String emptyString = "";
        testGetBytesUnchecked(charsetName);
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Test case with a string containing special characters
        final String specialChars = "special!@#";
        testGetBytesUnchecked(charsetName);
        final byte[] expectedSpecial = specialChars.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUsAscii(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Change input to an empty string
        final byte[] emptyExpected = "".getBytes(charsetName);
        final byte[] emptyActual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));
        
        // Change input to a string with special characters
        final String specialCharacters = "¡Hola!";
        final byte[] specialCharactersExpected = specialCharacters.getBytes(charsetName);
        final byte[] specialCharactersActual = StringUtils.getBytesUtf16(specialCharacters);
        Assert.assertTrue(Arrays.equals(specialCharactersExpected, specialCharactersActual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() {
        // Test case 1: empty string
        String str1 = "";
        final byte[] expected1 = new byte[0];
        final byte[] actual1 = StringUtils.getBytesUtf16Le(str1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Test case 2: single character string
        String str2 = "a";
        final byte[] expected2 = new byte[]{0x61, 0x00};
        final byte[] actual2 = StringUtils.getBytesUtf16Le(str2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Test case 3: string with special characters
        String str3 = "Hello, world!\u2764";
        final byte[] expected3 = new byte[]{0x48, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F, 0x00, 0x2C, 0x00, 0x20, 0x00, 0x77, 0x00,
                0x6F, 0x00, 0x72, 0x00, 0x6C, 0x00, 0x64, 0x00, 0x21, 0x00, (byte) 0xE2, (byte) 0x9D, (byte) 0xA4, 0x00};
        final byte[] actual3 = StringUtils.getBytesUtf16Le(str3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
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
        
        // Regression test case 2: Non-English characters
        final String nonEnglishString = "こんにちは";
        final byte[] expected2 = nonEnglishString.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8(nonEnglishString);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: Single character
        final String singleChar = "a";
        final byte[] expected3 = singleChar.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8(singleChar);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test29() {
        try {
            StringUtils.getBytesUnchecked("Test String", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test30() {
        try {
            StringUtils.newString(new byte[]{ 72, 101, 108, 108, 111 }, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked("", "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {

        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {

        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(new byte[]{}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {

        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {

        }
    }
    @Test
    public void test35() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNotNull(StringUtils.newString(null, "UTF-8"));
        Assert.assertNotNull(StringUtils.newString(null, "ISO-8859-1"));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE_16BE, "UTF-8");
        final String actual2 = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "US-ASCII");
        final String actual2 = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "UTF-16LE");
        final String actual2 = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test39() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        Assert.assertNotNull(StringUtils.newStringUtf8(null));
        Assert.assertNotNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNotNull(StringUtils.newStringUsAscii(null));
        Assert.assertNotNull(StringUtils.newStringUtf16(null));
        Assert.assertNotNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNotNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "UTF-16BE");
        final String actual2 = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "US-ASCII");
        final String actual2 = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE_16LE, "UTF-8");
        final String actual2 = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test43() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[]{}, "UNKNOWN"));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final String expectedEmptyBytes = new String(new byte[]{}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmptyBytes = new String(new byte[]{}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUtf8(new byte[]{});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmptyBytes = new String(new byte[]{}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmptyBytes = new String(new byte[]{}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmptyBytes = new String(new byte[]{}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUtf16(new byte[]{});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final String expectedEmptyBytes = new String(new byte[]{}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUtf16Le(new byte[]{});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString(new byte[]{}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);

        // Regression test case 1: Change input bytes to null
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        // Regression test case 2: Change input bytes to empty array
        Assert.assertEquals("", StringUtils.newStringIso8859_1(new byte[] {}));
        // Regression test case 3: Change input bytes to a single byte array
        Assert.assertEquals("A", StringUtils.newStringIso8859_1(new byte[] { 65 }));
        // Regression test case 4: Change input bytes to a multi-byte array with special characters
        Assert.assertEquals("Hello, world!", StringUtils.newStringIso8859_1(new byte[] { 72, 101, 108, 108, 111, 44, 32, 119, 111, 114, 108, 100, 33 }));

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] reversedBytes = new byte[BYTES_FIXTURE.length];
        for (int i = 0; i < BYTES_FIXTURE.length; i++) {
            reversedBytes[i] = BYTES_FIXTURE[BYTES_FIXTURE.length - i - 1];
        }
        testNewString(charsetName);
        final String expected = new String(reversedBytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(reversedBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // new test case with empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // new test case with special characters
        final byte[] bytes2 = {-2, -1};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // new test case with empty byte array
        final byte[] bytes1 = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(bytes1));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes1));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes1));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes1));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes1));
        
        // new test case with special characters
        final byte[] bytes2 = {-2, -1};
        Assert.assertNull(StringUtils.newStringUtf8(bytes2));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes2));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes2));
        Assert.assertNull(StringUtils.newStringUtf16(bytes2));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes2));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes2));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // New test cases
        final byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
        
        final byte[] asciiBytes = "Hello".getBytes("US-ASCII");
        final String expectedAscii = new String(asciiBytes, charsetName);
        final String actualAscii = StringUtils.newStringUtf16Be(asciiBytes);
        Assert.assertEquals(expectedAscii, actualAscii);
        
        final byte[] isoBytes = "World".getBytes("ISO-8859-1");
        final String expectedIso = new String(isoBytes, charsetName);
        final String actualIso = StringUtils.newStringUtf16Be(isoBytes);
        Assert.assertEquals(expectedIso, actualIso);
        
        final byte[] utf8Bytes = "ABC".getBytes("UTF-8");
        final String expectedUtf8 = new String(utf8Bytes, charsetName);
        final String actualUtf8 = StringUtils.newStringUtf16Be(utf8Bytes);
        Assert.assertEquals(expectedUtf8, actualUtf8);
        
        final byte[] utf16leBytes = "Hello World".getBytes("UTF-16LE");
        final String expectedUtf16le = new String(utf16leBytes, charsetName);
        final String actualUtf16le = StringUtils.newStringUtf16Be(utf16leBytes);
        Assert.assertEquals(expectedUtf16le, actualUtf16le);
    }
    @Test
    public void test59() {
        // Original test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // New test cases
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[10]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{-1, -1, -1, -1}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{-128, -128, -128, -128}));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        byte[] bytes1 = { 97, 98, 99 };
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        byte[] bytes2 = {};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test61() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {}));
        
        // Regression test case 2
        byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final byte[] emptyBytes = {};
        final String charsetName = "UTF-8";
        final String actual = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals("", actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final byte[] nonUtf8Bytes = {(byte) 0xC3, (byte) 0x28};
        final String charsetName = "ISO-8859-1";
        final String expected = new String(nonUtf8Bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(nonUtf8Bytes);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final byte[] largeBytesArray = new byte[Integer.MAX_VALUE];
        Arrays.fill(largeBytesArray, (byte) 65);
        final String charsetName = "UTF-8";
        final String expected = new String(largeBytesArray, charsetName);
        final String actual = StringUtils.newStringUtf8(largeBytesArray);
        Assert.assertEquals(expected, actual);
    }
}