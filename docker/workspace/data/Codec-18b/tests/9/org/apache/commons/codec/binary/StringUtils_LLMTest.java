package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertFalse(StringUtils.equals(null, null)); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals("abc", null));  // mutant: replacing "true" with "false"
        Assert.assertTrue(StringUtils.equals(null, "abc"));  // mutant: replacing "true" with "false"
        Assert.assertFalse(StringUtils.equals("abc", "abc")); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals("abc", "abcd")); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals("abcd", "abc")); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals("abc", "ABC"));  // mutant: replacing "false" with "true"
    }
    @Test
    public void test1() {
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), null)); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals(null, new StringBuilder("abc"))); // mutant: replacing "true" with "false"
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc"))); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd"))); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc"))); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC"))); // mutant: replacing "false" with "true"
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("abc"))); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "abcd")); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals("abcd", new StringBuilder("abc"))); // mutant: replacing "false" with "true"
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "ABC")); // mutant: replacing "false" with "true"
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression test 1
        final byte[] actual2 = StringUtils.getBytesUsAscii(null);
        Assert.assertNull(actual2);
        // Regression test 2
        final byte[] actual3 = StringUtils.getBytesUsAscii("");
        Assert.assertEquals(0, actual3.length);
        // Regression test 3
        final byte[] actual4 = StringUtils.getBytesUsAscii("ABC123");
        final byte[] expected4 = "ABC123".getBytes(charsetName);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression test 1
        final byte[] actual2 = StringUtils.getBytesUtf16Le(null);
        Assert.assertNull(actual2);
        // Regression test 2
        final byte[] actual3 = StringUtils.getBytesUtf16Le("");
        Assert.assertEquals(0, actual3.length);
        // Regression test 3
        final byte[] actual4 = StringUtils.getBytesUtf16Le("ABC123");
        final byte[] expected4 = "ABC123".getBytes(charsetName);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression test 1
        final byte[] actual2 = StringUtils.getBytesUtf16Be(null);
        Assert.assertNull(actual2);
        // Regression test 2
        final byte[] actual3 = StringUtils.getBytesUtf16Be("");
        Assert.assertEquals(0, actual3.length);
        // Regression test 3
        final byte[] actual4 = StringUtils.getBytesUtf16Be("ABC123");
        final byte[] expected4 = "ABC123".getBytes(charsetName);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression test 1
        final byte[] actual2 = StringUtils.getBytesIso8859_1(null);
        Assert.assertNull(actual2);
        // Regression test 2
        final byte[] actual3 = StringUtils.getBytesIso8859_1("");
        Assert.assertEquals(0, actual3.length);
        // Regression test 3
        final byte[] actual4 = StringUtils.getBytesIso8859_1("ABC123");
        final byte[] expected4 = "ABC123".getBytes(charsetName);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression test 1
        final byte[] actual2 = StringUtils.getBytesUtf8(null);
        Assert.assertNull(actual2);
        // Regression test 2
        final byte[] actual3 = StringUtils.getBytesUtf8("");
        Assert.assertEquals(0, actual3.length);
        // Regression test 3
        final byte[] actual4 = StringUtils.getBytesUtf8("ABC123");
        final byte[] expected4 = "ABC123".getBytes(charsetName);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression test 1
        final byte[] actual2 = StringUtils.getBytesUtf16(null);
        Assert.assertNull(actual2);
        // Regression test 2
        final byte[] actual3 = StringUtils.getBytesUtf16("");
        Assert.assertEquals(0, actual3.length);
        // Regression test 3
        final byte[] actual4 = StringUtils.getBytesUtf16("ABC123");
        final byte[] expected4 = "ABC123".getBytes(charsetName);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World!");
        Assert.assertFalse(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesIso8859_1(StringUtils.EMPTY);
        Assert.assertFalse(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World");
        Assert.assertFalse(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regress1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_regress1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_regress1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regress1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regress1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regress1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    public void testGetBytesUnchecked_regress1(final String charsetName) {        
        try {
            StringUtils.getBytesUnchecked("test", charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("HELLO");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "123@#$".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("123@#$");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "null".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";

        // Test with an empty string
        String emptyString = "";
        byte[] emptyExpected = emptyString.getBytes(charsetName);
        byte[] emptyActual = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(emptyExpected, emptyActual));

        // Test with a string containing only whitespace characters
        String whitespaceString = "    ";
        byte[] whitespaceExpected = whitespaceString.getBytes(charsetName);
        byte[] whitespaceActual = StringUtils.getBytesUtf16Be(whitespaceString);
        Assert.assertTrue(Arrays.equals(whitespaceExpected, whitespaceActual));

        // Test with a string containing special characters
        String specialCharsString = "abc@#$%def";
        byte[] specialCharsExpected = specialCharsString.getBytes(charsetName);
        byte[] specialCharsActual = StringUtils.getBytesUtf16Be(specialCharsString);
        Assert.assertTrue(Arrays.equals(specialCharsExpected, specialCharsActual));

        // Test with a string containing non-ASCII characters
        String nonAsciiString = "こんにちは";
        byte[] nonAsciiExpected = nonAsciiString.getBytes(charsetName);
        byte[] nonAsciiActual = StringUtils.getBytesUtf16Be(nonAsciiString);
        Assert.assertTrue(Arrays.equals(nonAsciiExpected, nonAsciiActual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with only one character
        final byte[] expected2 = "a".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("a");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: string with multiple characters
        final byte[] expected3 = "abc".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("abc");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "こんにちは".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("こんにちは");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("!@#$%^&*");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(new byte[0], "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.getBytesUnchecked("", "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.newString(new byte[]{-1, -2, -3}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.getBytesUnchecked("!@#$", "UTF-8");
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
        
        // Regression tests
        final byte[] bytes = null;
        final String result = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(null, result);

        final Charset charset = null;
        final String result2 = StringUtils.newString(BYTES_FIXTURE, charset);
        Assert.assertEquals(expected, result2);
    }
    @Test
    public void test38() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression tests
        final String charsetName = "KOI8-R";
        final String result = StringUtils.newString(BYTES_FIXTURE, charsetName);
        Assert.assertEquals(expected, result);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final byte[] bytes = new byte[0];
        final String result = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals("", result);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final Charset charset = Charset.forName(charsetName);
        final String result = StringUtils.newString(BYTES_FIXTURE, charset);
        Assert.assertEquals(expected, result);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final byte[] bytes = new byte[]{45, 68, 33, 85, 105};
        final String result = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals("-D!Ui", result);
    }
    @Test
    public void test42() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        
        // Regression tests
        final byte[] bytes = new byte[0];
        final String result = StringUtils.newString(bytes, Charset.forName("ISO-8859-1"));
        Assert.assertEquals("", result);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final Charset charset = Charset.forName(charsetName);
        final String result = StringUtils.newString(BYTES_FIXTURE, charset);
        Assert.assertEquals(expected, result);
        
        final byte[] bytes = new byte[]{45, 68, 33, 85, 105};
        final String result2 = StringUtils.newString(bytes, charset);
        Assert.assertEquals("-D!Ui", result2);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final byte[] bytes = new byte[]{0, 45, 0, 68, 0, 33, 0, 85, 0, 105};
        final String result = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals("-D!Ui", result);
        
        final Charset charset = Charset.forName(charsetName);
        final String result2 = StringUtils.newString(BYTES_FIXTURE_16LE, charset);
        Assert.assertEquals(expected, result2);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression tests
        final String result = StringUtils.newString(null, Charset.forName("UTF-8"));
        Assert.assertEquals(null, result);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        final String differentExpected = new String(differentBytes, differentCharset);
        final String differentActual = StringUtils.newStringIso8859_1(differentBytes);
        Assert.assertNotEquals(differentExpected, differentActual);
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
            final String differentCharset = "UTF-8";
            try {
                StringUtils.newString(BYTES_FIXTURE, differentCharset);
                Assert.fail("Expected " + IllegalStateException.class.getName());
            } catch (final IllegalStateException ex) {
                // Expected
            }
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        final String differentExpected = new String(differentBytes, differentCharset);
        final String differentActual = StringUtils.newStringUtf16Be(differentBytes);
        Assert.assertNotEquals(differentExpected, differentActual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        final String differentExpected = new String(differentBytes, differentCharset);
        final String differentActual = StringUtils.newStringUtf16(differentBytes);
        Assert.assertNotEquals(differentExpected, differentActual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        final String differentExpected = new String(differentBytes, differentCharset);
        final String differentActual = StringUtils.newStringUsAscii(differentBytes);
        Assert.assertNotEquals(differentExpected, differentActual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        Assert.assertNull(StringUtils.newString(differentBytes, differentCharset));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        final String differentExpected = new String(differentBytes, differentCharset);
        final String differentActual = StringUtils.newStringUtf8(differentBytes);
        Assert.assertNotEquals(differentExpected, differentActual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        final String differentExpected = new String(differentBytes, differentCharset);
        final String differentActual = StringUtils.newStringUtf16Le(differentBytes);
        Assert.assertNotEquals(differentExpected, differentActual);
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        final byte[] differentBytes = { 10, 11, 12, 13, 14, 15 };
        final String differentCharset = "UTF-8";
        Assert.assertNull(StringUtils.newStringUtf8(differentBytes));
    }
@Test
public void test55() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    
    // Regression Tests
    final byte[] emptyBytes = new byte[0];
    Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));
    
    final byte[] nonLatinBytes = { 72, 101, 108, 108, 111, (byte) 195, (byte) 169, 33 }; // "HelloÃ©!" in ISO-8859-1
    Assert.assertEquals("HelloÃ©!", StringUtils.newStringIso8859_1(nonLatinBytes));
}
@Test
public void test56() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));

    // Regression Tests
    // Test with null input for each method
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    
    // Test with non-null but empty input for each method
    final byte[] emptyBytes = new byte[0];
    Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUsAscii(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));
    
    // Test with non-null but non-empty input for each method
    final byte[] nonEmptyBytes = { 72, 101, 108, 108, 111, 33 }; // "Hello!" in ASCII
    Assert.assertEquals("Hello!", StringUtils.newStringUtf8(nonEmptyBytes));
    Assert.assertEquals("Hello!", StringUtils.newStringIso8859_1(nonEmptyBytes));
    Assert.assertEquals("Hello!", StringUtils.newStringUsAscii(nonEmptyBytes));
    Assert.assertEquals("Hello!", StringUtils.newStringUtf16(nonEmptyBytes));
    Assert.assertEquals("Hello!", StringUtils.newStringUtf16Be(nonEmptyBytes));
    Assert.assertEquals("Hello!", StringUtils.newStringUtf16Le(nonEmptyBytes));
}
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { 65, 98, 99, 100 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { 81, 119, 101, 114, 116, 121 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { 112, 113, 114, 64, 115, 116, 117 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { 120, 121, 122, 49, 50, 51, 52, 53 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { 97, 98, 99, 100, 101, 102, 103 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test: empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test: empty byte array
        byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test: null byte array
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
@Test
public void test64() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    Assert.assertEquals(expected, actual);

    // Regression test cases
    // Test with empty byte array
    final byte[] emptyBytes = new byte[0];
    final String expectedEmpty = new String(emptyBytes, charsetName);
    final String actualEmpty = StringUtils.newStringUtf16Be(emptyBytes);
    Assert.assertEquals(expectedEmpty, actualEmpty);

    // Test with byte array that contains only one byte
    final byte[] singleByte = new byte[]{10};
    final String expectedSingleByte = new String(singleByte, charsetName);
    final String actualSingleByte = StringUtils.newStringUtf16Be(singleByte);
    Assert.assertEquals(expectedSingleByte, actualSingleByte);
}
@Test
public void test65() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));

    // Regression test case
    // Test with an array of null bytes
    final byte[] nullBytes = new byte[]{0, 0, 0};
    final String expectedNullBytes = new String(nullBytes, Charsets.UTF_16BE);
    final String actualNullBytes = StringUtils.newStringUtf16Be(nullBytes);
    Assert.assertEquals(expectedNullBytes, actualNullBytes);
}
    @Test
    public void test66() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression tests
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{})); // empty array
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{1, 2, 3, 127})); // different byte values
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{-1, -2, -3, -127})); // negative byte values
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{0})); // single byte
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{0, 0, 0})); // multiple zero bytes
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final String expectedEmptyBytes = new String(new byte[]{}, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUtf16Le(new byte[]{});
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
        
        final String expectedDifferentBytes = new String(new byte[]{1, 2, 3, 127}, charsetName);
        final String actualDifferentBytes = StringUtils.newStringUtf16Le(new byte[]{1, 2, 3, 127});
        Assert.assertEquals(expectedDifferentBytes, actualDifferentBytes);
        
        final String expectedNegativeBytes = new String(new byte[]{-1, -2, -3, -127}, charsetName);
        final String actualNegativeBytes = StringUtils.newStringUtf16Le(new byte[]{-1, -2, -3, -127});
        Assert.assertEquals(expectedNegativeBytes, actualNegativeBytes);
        
        final String expectedSingleByte = new String(new byte[]{0}, charsetName);
        final String actualSingleByte = StringUtils.newStringUtf16Le(new byte[]{0});
        Assert.assertEquals(expectedSingleByte, actualSingleByte);
        
        final String expectedMultipleZeroBytes = new String(new byte[]{0, 0, 0}, charsetName);
        final String actualMultipleZeroBytes = StringUtils.newStringUtf16Le(new byte[]{0, 0, 0});
        Assert.assertEquals(expectedMultipleZeroBytes, actualMultipleZeroBytes);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
        
        // Regression test 2: Byte array with one null character
        final byte[] nullBytes = {0};
        Assert.assertEquals("\u0000", StringUtils.newStringUtf8(nullBytes));
        
        // Regression test 3: Byte array with special characters
        final byte[] specialCharsBytes = {-27, -83, -118, -28, -67, -96, -26, -100, -84, -27, -76, -108};
        Assert.assertEquals("日本語", StringUtils.newStringUtf8(specialCharsBytes));
        
        // Regression test 4: Byte array with invalid encoding
        final byte[] invalidBytes = {65, 66, (byte) 0xFF, 67};
        Assert.assertThrows(UnsupportedEncodingException.class, () -> StringUtils.newStringUtf8(invalidBytes));
    }
    @Test
    public void test69() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: Null byte array
        final byte[] nullBytes = null;
        Assert.assertNull(StringUtils.newStringUtf8(nullBytes));
    }
}