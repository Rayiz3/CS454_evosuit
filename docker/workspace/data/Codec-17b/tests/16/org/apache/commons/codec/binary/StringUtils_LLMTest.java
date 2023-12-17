package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        // Test if both input values are null
        assertFalse(MyClass.equals(null, null));

        // Test if one input value is null and one is not null
        assertFalse(MyClass.equals(null, "abc"));
        assertFalse(MyClass.equals("abc", null));

        // Test if both input values are empty strings
        assertTrue(MyClass.equals("", ""));

        // Test if one input value is an empty string and the other is not
        assertFalse(MyClass.equals("", "abc"));
        assertFalse(MyClass.equals("abc", ""));

        // Test if both input values are the same string
        assertTrue(MyClass.equals("abc", "abc"));

        // Test if the input values are different strings
        assertFalse(MyClass.equals("abc", "def"));

        // Test if the input values are strings with different cases
        assertFalse(MyClass.equals("abc", "ABC"));

        // Test if the input values are different types
        assertFalse(MyClass.equals("abc", 123));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test7() {
    ByteBuffer result = getByteBuffer(null, Charset.defaultCharset());
    assertNull(result);
}
@Test
public void test8() {
    String str = "";
    ByteBuffer result = getByteBuffer(str, Charset.defaultCharset());
    assertNotNull(result);
    assertEquals(0, result.capacity());
}
@Test
public void test9() {
    String str = "Hello, World!";
    Charset charset = Charset.forName("UTF-8");
    ByteBuffer result = getByteBuffer(str, charset);
    assertNotNull(result);
    assertEquals(str.getBytes(charset).length, result.capacity());
}
@Test
public void test10() {
    String str = "Hello, World!";
    Charset charset = Charset.forName("UTF-16");
    ByteBuffer result = getByteBuffer(str, charset);
    assertNotNull(result);
    assertEquals(str.getBytes(charset).length, result.capacity());
}
@Test
public void test11() {
    String string = null;
    ByteBuffer expected = ByteBuffer.wrap(new byte[0]);

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test12() {
    String string = "";
    ByteBuffer expected = ByteBuffer.wrap(new byte[0]);

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test13() {
    String string = "Hello, World!";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        final byte[] expected1 = "Hello".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("Hello");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2
        final byte[] expected2 = "12345".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3
        final byte[] expected3 = "".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUsAscii("");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf16Be("");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "World".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf16Be("World");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf16Le("");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "RegTest".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf16Le("RegTest");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesIso8859_1("");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Testing".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesIso8859_1("Testing");  // Changed input
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
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf16("");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "RegTest".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf16("RegTest");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf8("");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);  // Changed input
        final byte[] actual = StringUtils.getBytesUtf8("Hello World");  // Changed input
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Regression test 1
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2
        final byte[] expected2 = "Hello World".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test cases
        final String input1 = ""; // Empty string
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        final String input2 = "Hello, world!"; // String with special characters
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        final String input3 = "12345"; // String with numbers
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        final String input4 = "  "; // String with spaces
        final byte[] expected4 = input4.getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16(input4);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: String with numbers 
        final byte[] expected2 = "123".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("123");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked("");
        testGetBytesUnchecked("abc");
        testGetBytesUnchecked("12345");
        testGetBytesUnchecked("!@#$%");
        testGetBytesUnchecked("   ");
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);

        // Regression Test Case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression Test Case 2: String with special characters
        final byte[] expected2 = "åß∂ƒ©˙∆˚¬".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("åß∂ƒ©˙∆˚¬");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression Test Case 3: String with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
@Test
public void test40() {
    try {
        StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test41() {
    try {
        StringUtils.getBytesUnchecked(STRING_FIXTURE, "US-ASCII");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test42() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "UTF-8");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test43() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "US-ASCII");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void testNewString_regression(String charsetName) {
        // Regression test with null input
        Assert.assertNull(StringUtils.newString(null, charsetName));
        
        // Regression test with empty input
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newString(emptyBytes, charsetName));
        
        // Regression test with valid input
        byte[] bytes = {-27, -94, -83, -26, -106, -121, -25, -112, -83};
        String expected = new String(bytes, charsetName);
        String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Changing the input bytes to null
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        
        // Regression test case 2: Changing the input bytes to an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Changing the input bytes to null
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        
        // Regression test case 2: Changing the input bytes to an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Changing the input bytes to null
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Regression test case 2: Changing the input bytes to an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test53() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        
        // Regression test case 1: Changing the charsetName to null
        Assert.assertNull(StringUtils.newString(BYTES_FIXTURE, null));
        
        // Regression test case 2: Changing both the bytes and charsetName to null
        Assert.assertNull(StringUtils.newString(null, null));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Changing the input bytes to null
        Assert.assertNull(StringUtils.newStringUtf16(null));
        
        // Regression test case 2: Changing the input bytes to an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Changing the input bytes to null
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 2: Changing the input bytes to an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
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
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 1: Changing the charsetName to null
        try {
            StringUtils.newString(BYTES_FIXTURE, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 2: Changing both the bytes and charsetName to null
        try {
            StringUtils.newString(null, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Changing the input bytes to null
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Regression test case 2: Changing the input bytes to an empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{ (byte) 0xC3, (byte) 0x28 }, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{ (byte) 0xC3, (byte) 0x28 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{ (byte) 0x61, (byte) 0xC3 }, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{ (byte) 0x61, (byte) 0xC3 });
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
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
@Test
public void test64() throws UnsupportedEncodingException {
    // Original test case
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);

    // Regression test 1: Empty byte array
    byte[] emptyBytes = new byte[0];
    String expected1 = new String(emptyBytes, charsetName);
    String actual1 = StringUtils.newStringUsAscii(emptyBytes);
    Assert.assertEquals(expected1, actual1);

    // Regression test 2: Byte array with special characters
    byte[] specialBytes = {-38, -127, -38, -112, -38, -125, -38, -110, -38, -116};
    String expected2 = new String(specialBytes, charsetName);
    String actual2 = StringUtils.newStringUsAscii(specialBytes);
    Assert.assertEquals(expected2, actual2);

    // Regression test 3: Byte array with null character in between
    byte[] nullBytes = {72, 101, 108, 108, 111, 0, 87, 111, 114, 108, 100};
    String expected3 = new String(nullBytes, charsetName);
    String actual3 = StringUtils.newStringUsAscii(nullBytes);
    Assert.assertEquals(expected3, actual3);
}
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        byte[] emptyBytes = new byte[0];
        String emptyExpected = new String(emptyBytes, charsetName);
        String emptyActual = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        byte[] singleByte = {65}; // 'A' in ASCII
        String singleExpected = new String(singleByte, charsetName);
        String singleActual = StringUtils.newStringUtf16(singleByte);
        Assert.assertEquals(singleExpected, singleActual);

        byte[] multiBytes = {97, 32, 98, 32, 99}; // 'a b c' in ASCII
        String multiExpected = new String(multiBytes, charsetName);
        String multiActual = StringUtils.newStringUtf16(multiBytes);
        Assert.assertEquals(multiExpected, multiActual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "Hello";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0, 72, 0, 101, 0, 108, 0, 108, 0, 111});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "12345";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0, 49, 0, 50, 0, 51, 0, 52, 0, 53});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "ABC";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0, 65, 0, 66, 0, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "こんにちは";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{94, 123, -121, -62, -127, -5});
        Assert.assertEquals(expected, actual);
    }
@Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x00, 0x61, 0x00, 0x62, 0x00, 0x63, 0x00, 0x64});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{97, 98, 99}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{97, 98, 99}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{97, 98, 99}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{97, 98, 99}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{97, 98, 99}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{97, 98, 99}));
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[]{-62, -127, -62, -126, -62, -125}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{-62, -127, -62, -126, -62, -125});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0, 0, 0, 0, 0, 0}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{0, 0, 0, 0, 0, 0});
        Assert.assertEquals(expected, actual);
    }
}