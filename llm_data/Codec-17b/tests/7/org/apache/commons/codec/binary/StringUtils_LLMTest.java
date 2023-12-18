package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    assertTrue(StringUtil.equals(null, null));
    assertFalse(StringUtil.equals(null, "abc"));
    assertFalse(StringUtil.equals("abc", null));
    assertTrue(StringUtil.equals("abc", "abc"));
    assertFalse(StringUtil.equals("abc", "def"));
    assertTrue(StringUtil.equals("ABC", "abc"));
    assertFalse(StringUtil.equals("abc", "ABC"));
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
        String string = null;
        Charset charset = StandardCharsets.UTF_8;

        ByteBuffer result = getByteBuffer(string, charset);

        Assertions.assertNull(result);
    }
    @Test
    public void test8() {
        String string = "";
        Charset charset = StandardCharsets.UTF_8;

        ByteBuffer result = getByteBuffer(string, charset);

        Assertions.assertEquals(0, result.remaining());
    }
    @Test
    public void test9() {
        String string = "Hello World";
        Charset charset = StandardCharsets.UTF_8;

        ByteBuffer result = getByteBuffer(string, charset);

        String expectedResult = "Hello World";
        Assertions.assertEquals(expectedResult, new String(result.array()));
    }
    @Test
    public void test10() {
        String string = "Hello World";
        Charset charset = StandardCharsets.US_ASCII;

        ByteBuffer result = getByteBuffer(string, charset);

        String expectedResult = "Hello World";
        Assertions.assertEquals(expectedResult, new String(result.array()));
    }
    @Test
    public void test11() {
        String string = "Hello World";
        Charset charset = StandardCharsets.ISO_8859_1;

        ByteBuffer result = getByteBuffer(string, charset);

        String expectedResult = "Hello World";
        Assertions.assertEquals(expectedResult, new String(result.array()));
    }
@Test
public void test12() {
    // Test case 1
    String input1 = "Hello, world!";
    ByteBuffer expectedOutput1 = ByteBuffer.wrap(input1.getBytes(StandardCharsets.UTF_8));
    ByteBuffer actualOutput1 = getByteBufferUtf8(input1);
    assertEquals(expectedOutput1, actualOutput1);

    // Test case 2
    String input2 = "12345";
    ByteBuffer expectedOutput2 = ByteBuffer.wrap(input2.getBytes(StandardCharsets.UTF_8));
    ByteBuffer actualOutput2 = getByteBufferUtf8(input2);
    assertEquals(expectedOutput2, actualOutput2);

    // Test case 3
    String input3 = "";
    ByteBuffer expectedOutput3 = ByteBuffer.wrap(input3.getBytes(StandardCharsets.UTF_8));
    ByteBuffer actualOutput3 = getByteBufferUtf8(input3);
    assertEquals(expectedOutput3, actualOutput3);
}
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test case 1
        final byte[] expected1 = "Regression test case1".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("Regression test case1");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2
        final byte[] expected2 = "Regression test case2".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("Regression test case2");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression test".getBytes(charsetName); // change the input value
        final byte[] actual = StringUtils.getBytesUsAscii("Regression test"); // change the input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression test".getBytes(charsetName); // change the input value
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression test"); // change the input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression test".getBytes(charsetName); // change the input value
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression test"); // change the input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() {
        try {
            StringUtils.getBytesUnchecked("Regression test", "UNKNOWN"); // change the input value
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression test".getBytes(charsetName); // change the input value
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression test"); // change the input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression test".getBytes(charsetName); // change the input value
        final byte[] actual = StringUtils.getBytesUtf16("Regression test"); // change the input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression test".getBytes(charsetName); // change the input value
        final byte[] actual = StringUtils.getBytesUtf8("Regression test"); // change the input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN")); // change the input value
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);

        // Regression test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2: String with special characters
        final byte[] expected2 = "Hello!@#$%^&*()".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("Hello!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3: String with non-ASCII characters
        final byte[] expected3 = "你好".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("你好");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regress1(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regress2(charsetName);
        final byte[] expected = "abc".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("abc");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regress3(charsetName);
        final byte[] expected = "123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regress4(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Test123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Test123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";

        // Regression Test Case 1: Empty String
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression Test Case 2: String with special characters
        final byte[] expected2 = "Hello, 世界!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("Hello, 世界!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression Test Case 3: String with emoji
        final byte[] expected3 = "I love 😊".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("I love 😊");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcdefghijklmnopqrstuvwxyz".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("abcdefghijklmnopqrstuvwxyz");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
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
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        final String expected1 = new String(BYTES_FIXTURE, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(BYTES_FIXTURE);
        Assert.assertNotEquals(expected1, actual1);

        final String expected2 = "";
        final byte[] bytes2 = null;
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        final String expected1 = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected1, actual1);

        final String expected2 = "";
        final byte[] bytes2 = null;
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        final String expected1 = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected1, actual1);

        final String expected2 = "";
        final byte[] bytes2 = null;
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        final String expected1 = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual1 = StringUtils.newStringUtf16(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected1, actual1);

        final String expected2 = "";
        final byte[] bytes2 = null;
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        final String expected1 = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected1, actual1);

        final String expected2 = "";
        final byte[] bytes2 = null;
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        final String expected1 = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual1 = StringUtils.newStringUtf8(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected1, actual1);

        final String expected2 = "";
        final byte[] bytes2 = null;
        final String actual2 = StringUtils.newStringUtf8(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        // Regression test case 1
        final byte[] bytes1 = {0x00, 0x41}; // Latin capital letter A
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = {0x00, 0x61}; // Latin small letter a
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        // Regression test case 1
        final byte[] bytes1 = {0x41, 0x42, 0x43}; // ABC
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = {0x61, 0x62, 0x63}; // abc
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        // Regression test case 1
        final byte[] bytes1 = {0x41, 0x42, 0x43}; // ABC
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = {0x61, 0x62, 0x63}; // abc
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test45() {
        // Regression test case 1
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        
        // Regression test case 2
        Assert.assertNull(StringUtils.newString(null, "US-ASCII"));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        // Regression test case 1
        final byte[] bytes1 = {0x00, 0x41}; // Latin capital letter A
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = {0x00, 0x61}; // Latin small letter a
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        // Regression test case 1
        final byte[] bytes1 = {0x00, 0x41}; // Latin capital letter A
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = {0x00, 0x61}; // Latin small letter a
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test48() {
        // Regression test case 1
        try {
            StringUtils.newString(new byte[] {0x41}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 2
        try {
            StringUtils.newString(new byte[] {0x61}, "US-ASCII");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        // Regression test case 1
        final byte[] bytes1 = {0x41, 0x42, 0x43}; // ABC
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf8(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2
        final byte[] bytes2 = {0x61, 0x62, 0x63}; // abc
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1
        final byte[] bytes1 = {(byte) 65, (byte) 66, (byte) 67, (byte) 68};  // ABCD
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2
        final byte[] bytes2 = {(byte) 32, (byte) 33, (byte) 34, (byte) 35};  // !"#
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1
        final byte[] bytes1 = null;
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes1));
        
        // Regression test 2
        final byte[] bytes2 = {(byte) 65, (byte) 66, (byte) 67, (byte) 68};  // ABCD
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes2));
    }
@Test
public void test52() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final byte[] bytes = {65, 66, 67}; // change input bytes
    final String actual = StringUtils.newStringUsAscii(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test53() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = "";
    final byte[] bytes = {}; // change input bytes
    final String actual = StringUtils.newStringUsAscii(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test54() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final byte[] bytes = {}; // change input bytes
    final String actual = StringUtils.newStringUsAscii(bytes);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: Multiple bytes
        final String expected2 = new String(new byte[] {72, 101, 108, 108, 111}, charsetName);
        final String actual2 = StringUtils.newStringUtf16(new byte[] {72, 101, 108, 108, 111});
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: Single byte
        final String expected3 = new String(new byte[] {65}, charsetName);
        final String actual3 = StringUtils.newStringUtf16(new byte[] {65});
        Assert.assertEquals(expected3, actual3);
    }
@Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
@Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String((byte[]) null, charsetName);
        final String actual = StringUtils.newStringUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
@Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final byte[] bytes = {0, 0, 0, 0, 97, 0, 98, 0, 99};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
@Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final byte[] bytes = {65, 0, 0, 0};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
@Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final byte[] bytes = {0, 65, 0, 66, 0, 67};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";

        // Test case 1: Empty byte array
        final byte[] emptyBytes = {};
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        // Test case 2: Single byte
        final byte[] singleByte = {65};
        final String singleByteExpected = new String(singleByte, charsetName);
        final String singleByteActual = StringUtils.newStringUtf16Le(singleByte);
        Assert.assertEquals(singleByteExpected, singleByteActual);

        // Test case 3: Multiple bytes
        final byte[] multipleBytes = {65, 66, 67};
        final String multipleBytesExpected = new String(multipleBytes, charsetName);
        final String multipleBytesActual = StringUtils.newStringUtf16Le(multipleBytes);
        Assert.assertEquals(multipleBytesExpected, multipleBytesActual);

        // Test case 4: Null byte array
        final byte[] nullBytes = null;
        Assert.assertThrows(NullPointerException.class, () -> StringUtils.newStringUtf16Le(nullBytes));
    }
    @Test
    public void test62() {
        // Regression test 1: Changing input to empty byte array
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));

        // Regression test 2: Changing input to non-empty byte array
        byte[] bytes = {65, 66, 67}; // ASCII values for 'A', 'B', 'C'
        Assert.assertNull(StringUtils.newStringUtf8(bytes));

        // Existing test case
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Regression test 1: Changing input to empty byte array
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test 2: Changing input to non-empty byte array
        byte[] bytes = {65, 66, 67}; // ASCII values for 'A', 'B', 'C'
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);

        // Existing test case
        final String expectedFixture = new String(BYTES_FIXTURE, charsetName);
        final String actualFixture = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expectedFixture, actualFixture);
    }
}