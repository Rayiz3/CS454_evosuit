package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Regression test case 1
    CharSequence cs1 = "hello";
    CharSequence cs2 = "hello";
    boolean expected1 = true;
    boolean result1 = YourClassName.equals(cs1, cs2);
    assertEquals(expected1, result1);

    // Regression test case 2
    cs1 = "hello";
    cs2 = "HELLO";
    boolean expected2 = false;
    boolean result2 = YourClassName.equals(cs1, cs2);
    assertEquals(expected2, result2);
  
    // Regression test case 3
    cs1 = "hello";
    cs2 = null;
    boolean expected3 = false;
    boolean result3 = YourClassName.equals(cs1, cs2);
    assertEquals(expected3, result3);
  
    // Regression test case 4
    cs1 = null;
    cs2 = "hello";
    boolean expected4 = false;
    boolean result4 = YourClassName.equals(cs1, cs2);
    assertEquals(expected4, result4);
  
    // Regression test case 5
    cs1 = null;
    cs2 = null;
    boolean expected5 = false;
    boolean result5 = YourClassName.equals(cs1, cs2);
    assertEquals(expected5, result5);
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 1".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 3".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test 3");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 4".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test 4");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 5".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test 5");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 6".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test 6");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 7".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test 7");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 8".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test 8");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 9".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 9");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 10".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 10");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 11".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 11");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression Test 12".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 12");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test13() {
    // Regression test case 1: string is null
    String string1 = null;
    Charset charset1 = Charset.defaultCharset();
    assertNull(getByteBuffer(string1,charset1));

    // Regression test case 2: string is empty
    String string2 = "";
    Charset charset2 = Charset.defaultCharset();
    assertEquals(ByteBuffer.wrap("".getBytes(charset2)), getByteBuffer(string2,charset2));

    // Regression test case 3: string contains special characters
    String string3 = "@#*$!";
    Charset charset3 = Charset.defaultCharset();
    assertEquals(ByteBuffer.wrap("@#*$!".getBytes(charset3)), getByteBuffer(string3,charset3));

    // Regression test case 4: string contains non-ASCII characters
    String string4 = "ŁЇШµ€";
    Charset charset4 = Charset.forName("UTF-8");
    assertEquals(ByteBuffer.wrap("ŁЇШµ€".getBytes(charset4)), getByteBuffer(string4,charset4));
}
@Test
public void test14() {
    String string1 = "Hello";
    ByteBuffer buffer1 = ByteBuffer.wrap(string1.getBytes(Charsets.UTF_8));
    assertEquals(buffer1, getByteBufferUtf8(string1));

    String string2 = "12345";
    ByteBuffer buffer2 = ByteBuffer.wrap(string2.getBytes(Charsets.UTF_8));
    assertEquals(buffer2, getByteBufferUtf8(string2));

    String string3 = "中文";
    ByteBuffer buffer3 = ByteBuffer.wrap(string3.getBytes(Charsets.UTF_8));
    assertEquals(buffer3, getByteBufferUtf8(string3));

    String string4 = "";
    ByteBuffer buffer4 = ByteBuffer.wrap(string4.getBytes(Charsets.UTF_8));
    assertEquals(buffer4, getByteBufferUtf8(string4));
}
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test case 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2: string with special characters
        final byte[] expected2 = "Hello, こんにちは".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("Hello, こんにちは");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test case 3: string with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName); // change input value
        final byte[] actual = StringUtils.getBytesUsAscii("Hello, World!"); // change input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // change input value
        final byte[] actual = StringUtils.getBytesUsAscii(""); // change input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: empty string
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: string with special characters
        final String input2 = "æøå";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: string with numbers and symbols
        final String input3 = "123!@#";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = "Hello @#$%^&*()";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = "1234567890";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = "Hello World";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello, world!");
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
        final byte[] expected = "日本語".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("日本語");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        
        // Regression test case 1: Empty string
        String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: String with special characters
        String input2 = "&^%$#@!";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with numbers
        String input3 = "0123456789";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test34() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-5");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test37() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ASCII");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16LE");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    private void testNewString_regression1(final String charsetName) throws UnsupportedEncodingException {
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newString(BYTES_FIXTURE, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() {
        final byte[] bytes = { 72, 101, 108, 108, 111 };
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, Charset.forName(charsetName));
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        final byte[] bytes = {};
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, Charset.forName(charsetName));
        final String actual = StringUtils.newString(bytes, Charset.forName(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewStringRegression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewStringRegression(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewStringRegression(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewStringRegression(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewStringRegression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewStringRegression(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_REGRESSION);
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
        final String charsetName = "ISO-8859-1";
        final byte[] input = new byte[0];
        final String expected = new String(input, charsetName);
        final String actual = StringUtils.newStringIso8859_1(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        final String charsetName = "ISO-8859-1";
        final byte[] input = new byte[]{65};
        final String expected = new String(input, charsetName);
        final String actual = StringUtils.newStringIso8859_1(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() {
        final String charsetName = "ISO-8859-1";
        final byte[] input = new byte[]{65, 66, 67, 49, 50, 51};
        final String expected = new String(input, charsetName);
        final String actual = StringUtils.newStringIso8859_1(input);
        Assert.assertEquals(expected, actual);
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
        final String charsetName = "US-ASCII";
        
        // Test case 1: Changing the input value from BYTES_FIXTURE to an empty byte array
        final byte[] input1 = new byte[] {};
        final String expected1 = new String(input1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(input1);
        Assert.assertEquals(expected1, actual1);

        // Test case 2: Changing the input value from BYTES_FIXTURE to a byte array with a single element
        final byte[] input2 = new byte[] { 65 };
        final String expected2 = new String(input2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(input2);
        Assert.assertEquals(expected2, actual2);

        // Test case 3: Changing the input value from BYTES_FIXTURE to a byte array with multiple elements
        final byte[] input3 = new byte[] { 65, 66, 67, 68 };
        final String expected3 = new String(input3, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(input3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[]{65, 66, 67, 68});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[]{97, 98, 99, 100});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        
        // Regression test 1: Empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Byte array with single byte
        final byte[] bytes2 = {65};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Byte array with multiple bytes
        final byte[] bytes3 = {65, 66, 67};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
@Test
public void test66() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString_regression1(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test67() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString_regression2(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test68() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString_regression3(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test69() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() {
        byte[] emptyArray = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyArray));
        Assert.assertNull(StringUtils.newStringIso8859_1(emptyArray));
        Assert.assertNull(StringUtils.newStringUsAscii(emptyArray));
        Assert.assertNull(StringUtils.newStringUtf16(emptyArray));
        Assert.assertNull(StringUtils.newStringUtf16Be(emptyArray));
        Assert.assertNull(StringUtils.newStringUtf16Le(emptyArray));
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        
        byte[] singleByte = {65}; // ASCII value for 'A'
        final String expected = new String(singleByte, charsetName);
        final String actual = StringUtils.newStringUtf8(singleByte);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        
        byte[] invalidBytes = {(byte) 0xF0, (byte) 0x9F, (byte) 0x91, (byte) 0x8E, (byte) 0x41}; // Invalid UTF-8 sequence
        final String expected = new String(invalidBytes, charsetName);
        final String actual = StringUtils.newStringUtf8(invalidBytes);
        Assert.assertEquals(expected, actual);
    }
}