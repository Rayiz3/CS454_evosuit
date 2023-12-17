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
        final byte[] actual = StringUtils.getBytesUtf16("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test6() {
    String input = "Hello, world!";
    Charset charset = StandardCharsets.UTF_8;
    
    ByteBuffer result = getByteBuffer(input, charset);
    
    assertNotNull(result);
    assertEquals(input.getBytes(charset), result.array());
}
@Test
public void test7() {
    String input = "Hello, world!";
    Charset charset = StandardCharsets.UTF_16;
    
    ByteBuffer result = getByteBuffer(input, charset);
    
    assertNotNull(result);
    assertEquals(input.getBytes(charset), result.array());
}
@Test
public void test8() {
    String input = "Hello, world!";
    Charset charset = StandardCharsets.ISO_8859_1;
    
    ByteBuffer result = getByteBuffer(input, charset);
    
    assertNotNull(result);
    assertEquals(input.getBytes(charset), result.array());
}
@Test
public void test9() {
    String input = null;
    Charset charset = StandardCharsets.UTF_8;
    
    ByteBuffer result = getByteBuffer(input, charset);
    
    assertNull(result);
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
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("RegressionTest1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("RegressionTest2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTest1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTest2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("RegressionTest1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("RegressionTest2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() {
        try {
            StringUtils.getBytesUnchecked("RegressionTest1", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test20() {
        try {
            StringUtils.getBytesUnchecked("RegressionTest2", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("RegressionTest1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("RegressionTest2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("RegressionTest1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("RegressionTest2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("RegressionTest1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("RegressionTest2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test28() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "US-ASCII"));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);

        // Regression test 1: Empty string
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2: String with special characters
        final String input2 = "Hello, $123! This is a test.";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3: String with line breaks
        final String input3 = "This is a multi-line\nstring";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test Case 1: input is an empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression Test Case 2: input contains special characters
        final String specialChars = "Hello @#$%^&*()";
        final byte[] expectedSpecialChars = specialChars.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf16(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
        
        // Regression Test Case 3: input is a long string
        final String longString = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed in massa a urna ultricies dapibus a sed orci.";
        final byte[] expectedLongString = longString.getBytes(charsetName);
        final byte[] actualLongString = StringUtils.getBytesUtf16(longString);
        Assert.assertTrue(Arrays.equals(expectedLongString, actualLongString));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Test with an empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test with a string containing only special characters
        final byte[] expected2 = "!!@#$%^&*()_+:;><<<>>>>".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("!!@#$%^&*()_+:;><<<>>>>");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test with a string containing alphanumeric characters
        final byte[] expected3 = "123abcDEF".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("123abcDEF");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Regression test 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2: string with special characters
        final byte[] expected2 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: string with leading spaces
        final byte[] expected3 = "  Hello, World!".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("  Hello, World!");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        // Regression test 4: string with trailing spaces
        final byte[] expected4 = "Hello, World!  ".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16Le("Hello, World!  ");
        Assert.assertTrue(Arrays.equals(expected4, actual4));

        testGetBytesUnchecked(charsetName);
        
        // Original test case
        final byte[] expected5 = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test36() {
    try {
        StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test37() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "INVALID");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        byte[] bytes = null;
        String charsetName = "UTF-16";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));
        bytes = null;
        charsetName = "UTF-16BE";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));
        bytes = null;
        charsetName = "UTF-16LE";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));
        bytes = null;
        charsetName = "ISO-8859-1";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));
        bytes = null;
        charsetName = "US-ASCII";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));
        bytes = null;
        charsetName = "UTF-8";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() {
        try {
            StringUtils.newString(BYTES_FIXTURE_REGRESSION, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_REGRESSION);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = {65, 66, 67, 68};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = {65, 66, 67, 68};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = {65, 66, 67, 68};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] bytes = {65, 66, 67, 68};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = {65, 66, 67, 68};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        byte[] bytes = {65, 66, 67, 68};
        try {
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        byte[] bytes = {65, 66, 67, 68};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final byte[] bytes = {72, 101, 108, 108, 111};
        final String expected = new String(bytes, "ISO-8859-1");
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final byte[] bytes = {97, 98, 99, 100, 101, 102};
        final String expected = new String(bytes, "ISO-8859-1");
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Regression test 1: Empty byte array
        final byte[] emptyBytes = {};
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Byte array with ASCII characters
        final byte[] asciiBytes = { 65, 66, 67, 97, 98, 99 }; // ASCII: ABCabc
        final String expected2 = new String(asciiBytes, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(asciiBytes);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Byte array with non-ASCII characters
        final byte[] nonAsciiBytes = { -1, -128, 127, 0 }; // Non-ASCII: ÿ€⌂
        final String expected3 = new String(nonAsciiBytes, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1: Empty byte array
        final String expectedRegression1 = new String(new byte[0], charsetName);
        final String actualRegression1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expectedRegression1, actualRegression1);
        
        // Regression Test 2: Byte array with one character
        final byte[] bytesRegression2 = new byte[] {0x00, 0x41}; // 'A' in UTF-16
        final String expectedRegression2 = new String(bytesRegression2, charsetName);
        final String actualRegression2 = StringUtils.newStringUtf16(bytesRegression2);
        Assert.assertEquals(expectedRegression2, actualRegression2);
        
        // Regression Test 3: Byte array with multiple characters
        final byte[] bytesRegression3 = new byte[] {0x00, 0x41, 0x00, 0x42, 0x00, 0x43}; // 'ABC' in UTF-16
        final String expectedRegression3 = new String(bytesRegression3, charsetName);
        final String actualRegression3 = StringUtils.newStringUtf16(bytesRegression3);
        Assert.assertEquals(expectedRegression3, actualRegression3);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        
        // Regression Test 1
        final byte[] input1 = {-70, -66, 20, 0};
        final String expected1 = new String(input1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(input1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2
        final byte[] input2 = {0, 0, 1, 0, 1, 0};
        final String expected2 = new String(input2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(input2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test 3
        final byte[] input3 = {0, 0, -1, -121, 0, 0};
        final String expected3 = new String(input3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(input3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        
        // Test with empty byte array
        byte[] emptyBytes = new byte[0];
        String expectedEmpty = new String(emptyBytes, charsetName);
        String actualEmpty = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with byte array containing null bytes
        byte[] nullBytes = {0x00, 0x00, 0x00, 0x00, 0x00};
        String expectedNull = new String(nullBytes, charsetName);
        String actualNull = StringUtils.newStringUtf16Le(nullBytes);
        Assert.assertEquals(expectedNull, actualNull);
        
        // Test with byte array containing special characters
        byte[] specialCharsBytes = {0x41, 0x00, 0x42, 0x00, 0x43, 0x00};
        String expectedSpecialChars = new String(specialCharsBytes, charsetName);
        String actualSpecialChars = StringUtils.newStringUtf16Le(specialCharsBytes);
        Assert.assertEquals(expectedSpecialChars, actualSpecialChars);
    }
@Test
public void test59() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));

    // Regression test with empty byte array
    byte[] emptyBytes = new byte[0];
    Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUsAscii(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
    Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));
}
@Test
public void test60() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);

    // Regression test with one-byte character
    byte[] oneByteCharBytes = {-61}; // Invalid UTF-8 byte for any character
    Assert.assertEquals("", StringUtils.newStringUtf8(oneByteCharBytes));
}
}