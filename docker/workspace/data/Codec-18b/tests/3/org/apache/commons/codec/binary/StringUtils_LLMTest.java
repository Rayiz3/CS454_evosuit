package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));

        // Regression Test Cases
        Assert.assertFalse(StringUtils.equals(new StringBuilder("ab"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ab")));
        Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), "abc"));
    }
    @Test
    public void test1() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));

        // Regression Test Cases
        Assert.assertFalse(StringUtils.equals("ABC", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ab"));
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));

        // Regression Test Cases
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ab")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("ab"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("ab"), new StringBuilder("cd")));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUtf8(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUtf16(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
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
        final byte[] expected = "Hello, @#$%".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello, @#$%");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "aBcDeFgH".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("AbCdEfGh");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
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
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUnchecked("", charsetName);
        Assert.assertTrue(Arrays.equals(expected, actual));
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
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUnchecked(STRING_FIXTURE, null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE, null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE, null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE, null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE, null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE, null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE, null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String testString = "!@#$%^&*()";
        final byte[] expected = testString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(testString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String testString = "HelloWorld";
        final byte[] expected = testString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(testString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName); // Change input value to "Hello"
        final byte[] actual = StringUtils.getBytesUtf16("Hello"); // Change input value to "Hello"
        Assert.assertTrue(Arrays.equals(expected, actual)); // Assert that the expected and actual byte arrays are equal
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "World".getBytes(charsetName); // Change input value to "World"
        final byte[] actual = StringUtils.getBytesUtf16("World"); // Change input value to "World"
        Assert.assertTrue(Arrays.equals(expected, actual)); // Assert that the expected and actual byte arrays are equal
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Change input value to an empty string
        final byte[] actual = StringUtils.getBytesUtf16(""); // Change input value to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual)); // Assert that the expected and actual byte arrays are equal
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "123".getBytes(charsetName); // Change input value to "123"
        final byte[] actual = StringUtils.getBytesUtf16("123"); // Change input value to "123"
        Assert.assertTrue(Arrays.equals(expected, actual)); // Assert that the expected and actual byte arrays are equal
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abc".getBytes(charsetName); // Change input value to "abc"
        final byte[] actual = StringUtils.getBytesUtf16("abc"); // Change input value to "abc"
        Assert.assertTrue(Arrays.equals(expected, actual)); // Assert that the expected and actual byte arrays are equal
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = "Hello, world! \u2764";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = "";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = "éàü";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "\uD835\uDFE0\uD835\uDFE1\uD835\uDFE2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("\uD835\uDFE0\uD835\uDFE1\uD835\uDFE2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test1234".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Test1234");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test case 1
        final String charsetName2 = "ISO-8859-1";
        testGetBytesUnchecked(charsetName2);
        final byte[] expected2 = STRING_FIXTURE.getBytes(charsetName2);
        final byte[] actual2 = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected2, actual2));

        // Regression test case 2
        final String charsetName3 = "UTF-16";
        testGetBytesUnchecked(charsetName3);
        final byte[] expected3 = STRING_FIXTURE.getBytes(charsetName3);
        final byte[] actual3 = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected3, actual3));

        // Regression test case 3
        final String charsetName4 = "US-ASCII";
        testGetBytesUnchecked(charsetName4);
        final byte[] expected4 = STRING_FIXTURE.getBytes(charsetName4);
        final byte[] actual4 = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test51() {
        try {
            StringUtils.getBytesUnchecked("ABC", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString("ABC".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() {
        try {
            StringUtils.getBytesUnchecked("DEF", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test54() {
        try {
            StringUtils.newString("DEF".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test55() {
        try {
            StringUtils.getBytesUnchecked("XYZ", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() {
        try {
            StringUtils.newString("XYZ".getBytes(), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final byte[] bytes = {0,1,2,3}; // Test with different input
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final byte[] bytes = {10,20,30,40}; // Test with different input
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final byte[] bytes = {5,4,3,2}; // Test with different input
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final byte[] bytes = {15,25,35,45}; // Test with different input
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() {
        try {
            byte[] bytes = {1,2,3,4}; // Test with different input
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final byte[] bytes = {1,2,3,4}; // Test with different input
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final byte[] bytes = {3,4,5,6}; // Test with different input
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        byte[] bytes = {1,2,3,4}; // Test with different input
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test65() {
        byte[] bytes = {4,5,6,7}; // Test with different input
        Assert.assertNull(StringUtils.newString(bytes, "UNKNOWN"));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_wrongInput(charsetName); // Change the input value here
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_wrongInput(charsetName); // Change the input value here
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_wrongInput(charsetName); // Change the input value here
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_wrongInput(charsetName); // Change the input value here
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_wrongInput(charsetName); // Change the input value here
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_wrongInput(charsetName); // Change the input value here
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test74() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        // Test with empty input
        byte[] emptyInput = new byte[0];
        String expectedEmpty = new String(emptyInput, Charsets.ISO_8859_1);
        String actualEmpty = StringUtils.newStringIso8859_1(emptyInput);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // Test with a different set of bytes
        byte[] differentBytes = {65, 66, 67};
        String expectedDifferent = new String(differentBytes, Charsets.ISO_8859_1);
        String actualDifferent = StringUtils.newStringIso8859_1(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);

        // Test with null bytes
        byte[] nullBytes = null;
        String expectedNull = null;
        String actualNull = StringUtils.newStringIso8859_1(nullBytes);
        Assert.assertEquals(expectedNull, actualNull);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        //regression test case
        final String input = "hello";
        final byte[] bytes = input.getBytes();
        final String expected2 = new String(bytes, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test77() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        //regression test case
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
    }
    @Test
    public void test78() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case: empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test case: byte array with values outside the UTF-16 range
        byte[] invalidBytes = { 100, 200, 300, 400, 500 };
        Assert.assertEquals("d\u00C8\u012C\u01A0\u01F4", StringUtils.newStringUtf16(invalidBytes));
    }
    @Test
    public void test80() {
        byte[] invalidBytes = new byte[] { 0x41, 0x42, 0x43 };
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            StringUtils.newStringUtf16Be(invalidBytes);
        });
    }
    @Test
    public void test81() {
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
    }
    @Test
    public void test82() {
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
@Test
public void test83() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
    Assert.assertEquals(expected, actual);

    // Regression Test Case 1: Empty input
    final String actual1 = StringUtils.newStringUtf16Le(new byte[0]);
    Assert.assertEquals("", actual1);

    // Regression Test Case 2: Input with single byte
    final byte[] input2 = { 0x00 };
    final String expected2 = "\u0000";
    final String actual2 = StringUtils.newStringUtf16Le(input2);
    Assert.assertEquals(expected2, actual2);
}
    @Test
    public void test84() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        
        // Empty byte array
        final String expectedEmpty = "";
        final String actualEmpty = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Byte array with only one byte
        final String expectedSingleByte = "A";
        final String actualSingleByte = StringUtils.newStringUtf8(new byte[]{65});
        Assert.assertEquals(expectedSingleByte, actualSingleByte);
        
        // Byte array with invalid UTF-8 byte sequence
        final String expectedInvalid = "�";
        final byte[] invalidBytes = {(byte) 0b11111100, (byte) 0b10000000, (byte) 0b10000000}; // Invalid byte sequence: 11111100 10000000 10000000
        final String actualInvalid = StringUtils.newStringUtf8(invalidBytes);
        Assert.assertEquals(expectedInvalid, actualInvalid);
    }
    @Test
    public void test85() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Additional regression test case
        
        // Byte array with null value
        final byte[] nullBytes = null;
        final String expectedNull = null;
        final String actualNull = StringUtils.newStringUtf8(nullBytes);
        Assert.assertEquals(expectedNull, actualNull);
    }
}