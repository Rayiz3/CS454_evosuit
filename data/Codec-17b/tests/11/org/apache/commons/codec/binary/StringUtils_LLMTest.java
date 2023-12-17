package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Test case 1
    CharSequence cs1 = "hello";
    CharSequence cs2 = "hello";
    assertTrue(equals(cs1, cs2));
    
    // Test case 2
    cs1 = "hello";
    cs2 = "world";
    assertFalse(equals(cs1, cs2));
    
    // Test case 3
    cs1 = null;
    cs2 = "hello";
    assertFalse(equals(cs1, cs2));
    
    // Test case 4
    cs1 = "hello";
    cs2 = null;
    assertFalse(equals(cs1, cs2));
    
    // Test case 5
    cs1 = "hello";
    cs2 = "HELLO";
    assertFalse(equals(cs1, cs2));
    
    // Test case 6
    cs1 = "hello";
    cs2 = "";
    assertFalse(equals(cs1, cs2));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() {
        String string = "Hello World";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer expectedResult = ByteBuffer.wrap(string.getBytes(charset));

        ByteBuffer result = getByteBuffer(string, charset);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test8() {
        String string = null;
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer expectedResult = null;

        ByteBuffer result = getByteBuffer(string, charset);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test9() {
        String string = "";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer expectedResult = ByteBuffer.wrap(string.getBytes(charset));

        ByteBuffer result = getByteBuffer(string, charset);

        assertEquals(expectedResult, result);
    }
    @Test
    public void test10() {
        String string = "Hello World";
        Charset charset = Charset.forName("ISO-8859-1");
        ByteBuffer expectedResult = ByteBuffer.wrap(string.getBytes(charset));

        ByteBuffer result = getByteBuffer(string, charset);

        assertEquals(expectedResult, result);
    }
public class TestClass {

    public static void main(String[] args) {
        // Test 1: Empty String
        String emptyString = "";
        ByteBuffer buffer1 = getByteBufferUtf8(emptyString);
        System.out.println(buffer1);
        
        // Test 2: String with special characters
        String specialString = "!@#$%^&*(){}[]";
        ByteBuffer buffer2 = getByteBufferUtf8(specialString);
        System.out.println(buffer2);
        
        // Test 3: String with numbers
        String numericString = "1234567890";
        ByteBuffer buffer3 = getByteBufferUtf8(numericString);
        System.out.println(buffer3);
        
        // Test 4: String with alphabets
        String alphabeticString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ByteBuffer buffer4 = getByteBufferUtf8(alphabeticString);
        System.out.println(buffer4);
        
        // Test 5: String with utf-8 characters
        String utf8String = "漢字";
        ByteBuffer buffer5 = getByteBufferUtf8(utf8String);
        System.out.println(buffer5);
    }

    public static ByteBuffer getByteBufferUtf8(final String string) {
        return getByteBuffer(string, Charsets.UTF_8);
    }

    public static ByteBuffer getByteBuffer(final String string, Charset charset) {
        return charset.encode(string);
    }
}
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        
        // Regression test case 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with special characters
        final byte[] expected2 = "Thís îs ä strïng wíth spéciâl chàráctérs".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("Thís îs ä strïng wíth spéciâl chàráctérs");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: string with numbers
        final byte[] expected3 = "1234567890".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("1234567890");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression_Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression_Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression_Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() {
        try {
            StringUtils.getBytesUnchecked("Regression_Test", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression_Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression_Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression_Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test cases
        final byte[] expected1 = "test".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("test");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        final byte[] expected2 = "".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        final byte[] expected2 = "Hello, World! ^_^".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("Hello, World! ^_^");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: String with numbers
        final byte[] expected3 = "1234567890".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("1234567890");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "áéíóú".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("áéíóú");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        // Regression test cases
        final String input1 = "";
        final byte[] expected1 = input1.getBytes("UTF-16LE");
        final byte[] actual1 = StringUtils.getBytesUtf16Le(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        final String input2 = "Hello, World!";
        final byte[] expected2 = input2.getBytes("UTF-16LE");
        final byte[] actual2 = StringUtils.getBytesUtf16Le(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Existing test case
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test27() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
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
    final byte[] expected = "áéíóú".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("áéíóú");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test30() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "This is a long string for testing purposes.".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("This is a long string for testing purposes.");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test31() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = null;
    final byte[] actual = StringUtils.getBytesUtf8(null);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked("", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(new byte[]{}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.getBytesUnchecked("Hello", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.newString(new byte[]{72, 101, 108, 108, 111}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.getBytesUnchecked("12345", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test37() {
        try {
            StringUtils.newString(new byte[]{49, 50, 51, 52, 53}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked("Test String", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(new byte[]{84, 101, 115, 116, 32, 83, 116, 114, 105, 110, 103}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.getBytesUnchecked("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.newString(new byte[]{65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = null; // expected result changed
        final String actual = StringUtils.newStringUtf16Be(null); // input changed to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = ""; // expected result changed
        final String actual = StringUtils.newStringUtf16Be(new byte[0]); // input changed to an empty array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = null; // expected result changed
        final String actual = StringUtils.newStringUsAscii(null); // input changed to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = ""; // expected result changed
        final String actual = StringUtils.newStringUsAscii(new byte[0]); // input changed to an empty array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = null; // expected result changed
        final String actual = StringUtils.newStringIso8859_1(null); // input changed to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = ""; // expected result changed
        final String actual = StringUtils.newStringIso8859_1(new byte[0]); // input changed to an empty array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newString(null, (Charset)null)); // input changed to null
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = null; // expected result changed
        final String actual = StringUtils.newStringUtf16(null); // input changed to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = ""; // expected result changed
        final String actual = StringUtils.newStringUtf16(new byte[0]); // input changed to an empty array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = null; // expected result changed
        final String actual = StringUtils.newStringUtf16Le(null); // input changed to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = ""; // expected result changed
        final String actual = StringUtils.newStringUtf16Le(new byte[0]); // input changed to an empty array
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null)); // input changed to null
        Assert.assertNull(StringUtils.newStringIso8859_1(null)); // input changed to null
        Assert.assertNull(StringUtils.newStringUsAscii(null)); // input changed to null
        Assert.assertNull(StringUtils.newStringUtf16(null)); // input changed to null
        Assert.assertNull(StringUtils.newStringUtf16Be(null)); // input changed to null
        Assert.assertNull(StringUtils.newStringUtf16Le(null)); // input changed to null
    }
    @Test
    public void test61() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test62() {
        try {
            StringUtils.newString(BYTES_FIXTURE, null); // input changed to null
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = null; // expected result changed
        final String actual = StringUtils.newStringUtf8(null); // Changed input to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = ""; // expected result changed
        final String actual = StringUtils.newStringUtf8(new byte[0]); // Changed input to an empty array
        Assert.assertEquals(expected, actual);
    }
@Test
public void test66() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[] {(byte) 0xFE, (byte) 0xFF};
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(bytes, charsetName);
    final String actual = StringUtils.newStringUtf16Be(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test67() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[] {(byte) 0x41, (byte) 0x42, (byte) 0x43};
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(bytes, charsetName);
    final String actual = StringUtils.newStringUsAscii(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test68() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[] {(byte) 0xE4, (byte) 0xF6, (byte) 0xFC};
    final String charsetName = "ISO-8859-1";
    testNewString(charsetName);
    final String expected = new String(bytes, charsetName);
    final String actual = StringUtils.newStringIso8859_1(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test69() {
    Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
}
@Test
public void test70() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[] {(byte) 0xFF, (byte) 0xFE};
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(bytes, charsetName);
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test71() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[] {(byte) 0xFF, (byte) 0xFE};
    final String charsetName = "UTF-16LE";
    testNewString(charsetName);
    final String expected = new String(bytes, charsetName);
    final String actual = StringUtils.newStringUtf16Le(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test72() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
}
@Test
public void test73() {
    try {
        StringUtils.newString(new byte[] {(byte) 0x61, (byte) 0x62, (byte) 0x63}, "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test74() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[] {(byte) 0xE4, (byte) 0xF6, (byte) 0xFC};
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(bytes, charsetName);
    final String actual = StringUtils.newStringUtf8(bytes);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression2(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression2, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression2);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression Test 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        // Regression Test 2: Byte array with a single character
        final byte[] singleByte = {100};
        final String singleCharExpected = new String(singleByte, charsetName);
        final String singleCharActual = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(singleCharExpected, singleCharActual);

        // Regression Test 3: Byte array with multiple characters
        final byte[] multiBytes = {65, 66, 67};
        final String multiCharExpected = new String(multiBytes, charsetName);
        final String multiCharActual = StringUtils.newStringUsAscii(multiBytes);
        Assert.assertEquals(multiCharExpected, multiCharActual);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        // Regression test case 1: Empty input
        final byte[] bytes1 = new byte[0];
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test case 2: Input with special characters
        final byte[] bytes2 = {-2, -1, 0, 64, 0, 65, 0, 66};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression test case 3: Input with invalid encoding
        final byte[] bytes3 = {-1, -1, -1, -1, -1};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test80() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "Hello";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0, 72, 0, 101, 0, 108, 0, 108, 0, 111});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test81() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = "12345";
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0, 49, 0, 50, 0, 51, 0, 52, 0, 53});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test82() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1: Empty byte array
        byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression Test 2: Byte array with multiple characters
        byte[] bytes2 = new byte[] { 0x48, 0x65, 0x6C, 0x6C, 0x6F };
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression Test 3: Byte array with special characters
        byte[] bytes3 = new byte[] { (byte) (0xd8), 0x40, (byte) (0xdc), 0x09 };
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test83() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case 1: empty input
        Assert.assertNotNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNotNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNotNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNotNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNotNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNotNull(StringUtils.newStringUtf16Le(new byte[0]));

        // Regression test case 2: non-null input
        byte[] bytes = {65, 66, 67};  // Arbitrary input
        Assert.assertNotNull(StringUtils.newStringUtf8(bytes));
        Assert.assertNotNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNotNull(StringUtils.newStringUsAscii(bytes));
        Assert.assertNotNull(StringUtils.newStringUtf16(bytes));
        Assert.assertNotNull(StringUtils.newStringUtf16Be(bytes));
        Assert.assertNotNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test84() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test case 3: byte array with special characters
        byte[] bytes = {100, 105, -59, -17};  // Contains special characters
        final String expected2 = new String(bytes, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected2, actual2);
    }
}