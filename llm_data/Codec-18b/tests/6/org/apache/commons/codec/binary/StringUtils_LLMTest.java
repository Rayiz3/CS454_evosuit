package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertFalse(StringUtils.equals(null, "abc")); // Change the first parameter to null
        Assert.assertFalse(StringUtils.equals("abc", null)); // Change the second parameter to null
        Assert.assertFalse(StringUtils.equals("abc", "ABC")); // Change the second parameter to uppercase
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc"))); // Change the first parameter to null
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd"))); // Change the second parameter to "abcd"
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc"))); // Change the first parameter to "abcd"
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC"))); // Change the second parameter to uppercase
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd")); // Change the second parameter to "abcd"
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc"))); // Change the first parameter to "abcd"
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC")); // Change the second parameter to uppercase
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "RegressionTest".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "RegressionTest".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "RegressionTest".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "RegressionTest".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "RegressionTest".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "RegressionTest".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("RegressionTest");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "null".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "†•π£←↓→øþ€¤".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("†•π£←↓→øþ€¤");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Test String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Another Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Another Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Test123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Testing".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Testing");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "String".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("String");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] expected = "?".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("?");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] expected = "$#".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("$#");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello$#@!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello$#@!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Test empty string
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test string with special characters
        final String input2 = "Hello, World! \u2665";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test string with numbers
        final String input3 = "1234567890";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "A".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("A");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "你好".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("你好");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = @"!@#$%^&*()_+".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(@"!@#$%^&*()_+");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() {
        final byte[] expected = new byte[]{};
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() {
        final byte[] expected = new byte[]{72, 101, 108, 108, 111};
        final byte[] actual = StringUtils.getBytesUtf8("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() {
        final byte[] expected = new byte[]{-26, -109, -120, -28, -72, -101, -24, -73, -105, -26, -111, -69};
        final byte[] actual = StringUtils.getBytesUtf8("你好世界");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8"); // change "UNKNOWN" to "UTF-8"
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8"); // change "UNKNOWN" to "UTF-8"
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
@Test
public void test42() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    // Original test case
    testNewString(charsetName);

    // Regression test case: change the input bytes to null
    Assert.assertNull(StringUtils.newStringIso8859_1(null));

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test43() {
    try {
        // Original test case
        StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }

    // Regression test case: change the input charset to null
    try {
        StringUtils.newString(BYTES_FIXTURE, null);
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test44() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    // Original test case
    testNewString(charsetName);

    // Regression test case: change the input bytes to null
    Assert.assertNull(StringUtils.newStringUtf16Be(null));

    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test45() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    // Original test case
    testNewString(charsetName);

    // Regression test case: change the input bytes to null
    Assert.assertNull(StringUtils.newStringUtf16(null));

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test46() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    // Original test case
    testNewString(charsetName);

    // Regression test case: change the input bytes to null
    Assert.assertNull(StringUtils.newStringUsAscii(null));

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test47() {
    // Original test case
    Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));

    // Regression test case: change the input charset to null
    Assert.assertNull(StringUtils.newString(null, null));
}
@Test
public void test48() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    // Original test case
    testNewString(charsetName);

    // Regression test case: change the input bytes to null
    Assert.assertNull(StringUtils.newStringUtf8(null));

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test49() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    // Original test case
    testNewString(charsetName);

    // Regression test case: change the input bytes to null
    Assert.assertNull(StringUtils.newStringUtf16Le(null));

    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test50() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    // Regression test case: empty input
    final String actualEmptyInput = StringUtils.newStringIso8859_1(new byte[0]);
    Assert.assertEquals("", actualEmptyInput);
    // Regression test case: input with special characters
    final byte[] specialBytes = {-27, -122, -82, -24, -115, -111, -27, -120, -122, -27, -66, -119, -27, -67, -90};
    final String expectedSpecial = new String(specialBytes, charsetName);
    final String actualSpecial = StringUtils.newStringIso8859_1(specialBytes);
    Assert.assertEquals(expectedSpecial, actualSpecial);
}
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: empty byte array
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test 2: byte array with one element
        final byte[] bytesSingle = {65};
        final String expectedSingle = new String(bytesSingle, charsetName);
        final String actualSingle = StringUtils.newStringUsAscii(bytesSingle);
        Assert.assertEquals(expectedSingle, actualSingle);
        
        // Regression test 3: byte array with all bytes set to 127
        final byte[] bytesMax = new byte[3];
        Arrays.fill(bytesMax, (byte) 127);
        final String expectedMax = new String(bytesMax, charsetName);
        final String actualMax = StringUtils.newStringUsAscii(bytesMax);
        Assert.assertEquals(expectedMax, actualMax);
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: empty byte array
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        
        // Regression test 2: byte array with one element
        final byte[] bytesSingle = {65};
        final String expectedSingle = new String(bytesSingle, Charsets.US_ASCII);
        final String actualSingle = StringUtils.newStringUsAscii(bytesSingle);
        Assert.assertEquals(expectedSingle, actualSingle);
        
        // Regression test 3: byte array with all bytes set to 127
        final byte[] bytesMax = new byte[3];
        Arrays.fill(bytesMax, (byte) 127);
        final String expectedMax = new String(bytesMax, Charsets.US_ASCII);
        final String actualMax = StringUtils.newStringUsAscii(bytesMax);
        Assert.assertEquals(expectedMax, actualMax);
    }
    @Test
    public void test53() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{})); // regression test 1
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{0})); // regression test 2
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{1,2,3})); // regression test 3
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{127})); // regression test 4
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{-128})); // regression test 5
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{-1})); // regression test 6
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{1,2,-3,-4})); // regression test 7
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{1,2,-3,4})); // regression test 8
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{0,0,1,2})); // regression test 9
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test58() {
        final byte[] bytes = new byte[0];
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals("", actual);
    }
    @Test
    public void test59() {
        final byte[] bytes = { 65 };
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals("A", actual);
    }
    @Test
    public void test60() {
        final byte[] bytes = { 65, 66, 67, 68 };
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals("ABCD", actual);
    }
    @Test
    public void test61() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] altBytes = "Hello".getBytes("UTF-8");
        final String expected = new String(altBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(altBytes);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] longBytes = new byte[1000000];
        Arrays.fill(longBytes, (byte) 'a');
        final String expected = new String(longBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(longBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
        
        byte[] specialCharsBytes = {(byte) 0xC2, (byte) 0xA3}; // £ character
        Assert.assertEquals("£", StringUtils.newStringUtf8(specialCharsBytes));
        
        byte[] invalidBytes = {(byte) 0xC3, (byte) 0xFF}; // Invalid UTF-8 byte
        try {
            StringUtils.newStringUtf8(invalidBytes);
            Assert.fail("Expected exception not thrown");
        } catch (UTFDataFormatException e) {
            // Expected exception thrown
        }
    }
    @Test
    public void test67() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test cases
        byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
    }
}