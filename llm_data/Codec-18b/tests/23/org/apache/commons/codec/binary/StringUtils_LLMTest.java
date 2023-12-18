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
        // Additional regression test cases
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abcd")));
        Assert.assertTrue(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("ABC")));
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
        // Additional regression test cases
        Assert.assertTrue(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcd"), null));
        Assert.assertTrue(StringUtils.equals("ABC", new StringBuilder("abc")));
    }
    @Test
    public void test2() {
        // Original test cases
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
        // Additional regression test cases
        Assert.assertFalse(StringUtils.equals(null, "abcd"));
        Assert.assertTrue(StringUtils.equals("ABC", "abc"));
        Assert.assertTrue(StringUtils.equals("abcd", "abc"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.getBytesUtf16Be(null));
        
        // Regression test case 2
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName); 
        expected2[0] = (byte)((expected2[0] + 1) % 256); // changing the first byte in the expected result
        final byte[] actual2 = StringUtils.getBytesUtf16Be(String.valueOf(expected2));
        Assert.assertFalse(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.getBytesIso8859_1(null));
        
        // Regression test case 2
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName); 
        expected2[0] = (byte)((expected2[0] + 1) % 256); // changing the first byte in the expected result
        final byte[] actual2 = StringUtils.getBytesIso8859_1(String.valueOf(expected2));
        Assert.assertFalse(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.getBytesUsAscii(null));
        
        // Regression test case 2
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName); 
        expected2[0] = (byte)((expected2[0] + 1) % 256); // changing the first byte in the expected result
        final byte[] actual2 = StringUtils.getBytesUsAscii(String.valueOf(expected2));
        Assert.assertFalse(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.getBytesUtf8(null));
        
        // Regression test case 2
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName); 
        expected2[0] = (byte)((expected2[0] + 1) % 256); // changing the first byte in the expected result
        final byte[] actual2 = StringUtils.getBytesUtf8(String.valueOf(expected2));
        Assert.assertFalse(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.getBytesUtf16Le(null));
        
        // Regression test case 2
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName); 
        expected2[0] = (byte)((expected2[0] + 1) % 256); // changing the first byte in the expected result
        final byte[] actual2 = StringUtils.getBytesUtf16Le(String.valueOf(expected2));
        Assert.assertFalse(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        Assert.assertNull(StringUtils.getBytesUtf16(null));
        
        // Regression test case 2
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName); 
        expected2[0] = (byte)((expected2[0] + 1) % 256); // changing the first byte in the expected result
        final byte[] actual2 = StringUtils.getBytesUtf16(String.valueOf(expected2));
        Assert.assertFalse(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello @world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello @world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() {
        Assert.assertNull(StringUtils.getBytesUtf16Be(null));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("!!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() {
        Assert.assertNull(StringUtils.getBytesUtf16Le(null));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("!!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() {
        Assert.assertNull(StringUtils.getBytesIso8859_1(null));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("!!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() {
        Assert.assertNull(StringUtils.getBytesUsAscii(null));
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
        final byte[] expected = "!!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("!!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() {
        Assert.assertNull(StringUtils.getBytesUtf16(null));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("!!@#$%^&*()_+");
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
    public void test33() {
        Assert.assertNull(StringUtils.getBytesUtf8(null));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("!!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test37() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UTF-8"));
    }
    @Test
    public void test38() {
        final String charsetName = "UTF-8";
        final byte[] expected = "".getBytes();
        final byte[] actual = StringUtils.getBytesUnchecked("", "UTF-8");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] expected = "!!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUnchecked("!!@#$%^&*()_+", "UTF-8");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Special Characters: !@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Special Characters: !@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "A".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("A");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello µ$".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello µ$");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Test with an empty string
        String emptyString = "";
        byte[] expectedEmpty = emptyString.getBytes(charsetName);
        byte[] actualEmpty = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Test with a string containing only digits
        String digitString = "1234567890";
        byte[] expectedDigit = digitString.getBytes(charsetName);
        byte[] actualDigit = StringUtils.getBytesUtf16Be(digitString);
        Assert.assertTrue(Arrays.equals(expectedDigit, actualDigit));
        
        // Test with a string containing special characters
        String specialString = "!@#$%^&*()";
        byte[] expectedSpecial = specialString.getBytes(charsetName);
        byte[] actualSpecial = StringUtils.getBytesUtf16Be(specialString);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Test with a string with a single character
        final String singleCharString = "A";
        final byte[] expectedSingleChar = singleCharString.getBytes(charsetName);
        final byte[] actualSingleChar = StringUtils.getBytesUtf16Le(singleCharString);
        Assert.assertTrue(Arrays.equals(expectedSingleChar, actualSingleChar));

        // Test with an empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Le(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Test with a string containing special characters
        final String specialCharsString = "!@#$%^&*()";
        final byte[] expectedSpecialChars = specialCharsString.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf16Le(specialCharsString);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked2(charsetName);
        final byte[] expected = STRING_FIXTURE2.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked2(charsetName);
        final byte[] expected = STRING_FIXTURE3.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE3);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked2(charsetName);
        final byte[] expected = STRING_FIXTURE4.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE4);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test53() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "BAD_NAME");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test54() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "BAD_ENC");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test55() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-99");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-99");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test57() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test58() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
        Assert.assertNull(StringUtils.newString(null, "US-ASCII"));
        Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16BE"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16LE"));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        // Test with different input
        final String differentCharsetName = "ISO-8859-1";
        final String expectedDifferent = new String(BYTES_FIXTURE_16BE, differentCharsetName);
        final String actualDifferent = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Test with different input
        final String differentCharsetName = "UTF-16";
        final String expectedDifferent = new String(BYTES_FIXTURE, differentCharsetName);
        final String actualDifferent = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Test with different input
        final String differentCharsetName = "UTF-8";
        final String expectedDifferent = new String(BYTES_FIXTURE, differentCharsetName);
        final String actualDifferent = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Test with different input
        final String differentCharsetName = "UTF-8";
        final String expectedDifferent = new String(BYTES_FIXTURE, differentCharsetName);
        final String actualDifferent = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Test with different input
        final String differentCharsetName = "US-ASCII";
        final String expectedDifferent = new String(BYTES_FIXTURE, differentCharsetName);
        final String actualDifferent = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Test with different input
        final String differentCharsetName = "UTF-16BE";
        final String expectedDifferent = new String(BYTES_FIXTURE_16LE, differentCharsetName);
        final String actualDifferent = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test67() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }

        // Test with different input
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test68() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        // Regression test to kill mutants using empty byte array
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN"));
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        // Regression test to kill mutants by changing bytes array length
        final String actual2 = StringUtils.newStringUtf16Be(new byte[] { 1, 2, 3 });
        Assert.assertNotEquals(expected, actual2);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test to kill mutants by changing bytes array length
        final String actual2 = StringUtils.newStringUtf8(new byte[] { 1, 2, 3 });
        Assert.assertNotEquals(expected, actual2);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test to kill mutants by changing bytes array length
        final String actual2 = StringUtils.newStringUsAscii(new byte[] { 1, 2, 3 });
        Assert.assertNotEquals(expected, actual2);
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        // Regression test to kill mutants using empty byte array
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test to kill mutants by changing bytes array length
        final String actual2 = StringUtils.newStringIso8859_1(new byte[] { 1, 2, 3 });
        Assert.assertNotEquals(expected, actual2);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test to kill mutants by changing bytes array length
        final String actual2 = StringUtils.newStringUtf16(new byte[] { 1, 2, 3 });
        Assert.assertNotEquals(expected, actual2);
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        // Regression test to kill mutants by changing bytes array length
        final String actual2 = StringUtils.newStringUtf16Le(new byte[] { 1, 2, 3 });
        Assert.assertNotEquals(expected, actual2);
    }
    @Test
    public void test76() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        // Regression test to kill mutants by changing charsetName
        try {
            StringUtils.newString(BYTES_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_charsetName(null, charsetName);
        testNewString_charsetName(new byte[]{}, charsetName);
        testNewString_charsetName(new byte[]{65, 66, 67}, charsetName);
        testNewString_charsetName(new byte[]{-1, -2, -3}, charsetName);
    }
    @Test(expected = UnsupportedEncodingException.class)
    public void test78() throws UnsupportedEncodingException {
        StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    }
    private void testNewString_charsetName(byte[] bytes, String charsetName) throws UnsupportedEncodingException {
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test79() {
        final byte[] bytes = new byte[0];
        final String expected = new String(bytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test80() {
        final byte[] bytes = { 33, 64, 35, 36, 37 };
        final String expected = new String(bytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test81() {
        final byte[] bytes = new byte[1000];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (i % 128);
        }
        final String expected = new String(bytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test82() {
        final byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test83() {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        
        // Change input to an empty byte array
        final byte[] emptyBytes = new byte[]{};
        final String actualEmptyBytes = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected, actualEmptyBytes);
        
        // Change input to a byte array with special characters
        final byte[] specialCharsBytes = new byte[]{0x00, 0x41, 0x00, 0x42, 0x00, 0x2C, 0x00, 0x45, 0x00, 0x3F};
        final String expectedSpecialChars = new String(specialCharsBytes, charsetName);
        final String actualSpecialChars = StringUtils.newStringUtf16(specialCharsBytes);
        Assert.assertEquals(expectedSpecialChars, actualSpecialChars);
    }
    @Test
    public void test84() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        
        // Change input to a null byte array
        Assert.assertNull(StringUtils.newStringUtf16(null));
        
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test85() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = "test string".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test86() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = new byte[0];
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test87() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = new byte[]{(byte) -1, (byte) -2, (byte) -3};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test88() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test89() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String("hello World!".getBytes(), charsetName);
        final String actual = StringUtils.newStringUtf16Le("hello World!".getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test90() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String("1234".getBytes(), charsetName);
        final String actual = StringUtils.newStringUtf16Le("1234".getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test91() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test92() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test93() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test94() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: Byte array with only one byte
        final String expected2 = new String(new byte[]{127}, charsetName);
        final String actual2 = StringUtils.newStringUtf8(new byte[]{127});
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: Byte array with multiple bytes
        final String expected3 = new String(new byte[]{65, 66, 67, 68}, charsetName);
        final String actual3 = StringUtils.newStringUtf8(new byte[]{65, 66, 67, 68});
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test95() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1: Empty byte array
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Regression test case 2: Byte array with only one byte
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{127}));
        
        // Regression test case 3: Byte array with multiple bytes
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{65, 66, 67, 68}));
    }
}