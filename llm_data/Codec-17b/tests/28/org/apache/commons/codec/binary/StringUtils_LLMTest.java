package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(REGRESSION_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(REGRESSION_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(REGRESSION_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(REGRESSION_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(REGRESSION_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(REGRESSION_STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test6() {
    ByteBuffer result = getByteBuffer(null, Charset.forName("UTF-8"));
    assertNull(result);
}
@Test
public void test7() {
    ByteBuffer result = getByteBuffer("", Charset.forName("UTF-8"));
    assertEquals(ByteBuffer.wrap(new byte[0]), result);
}
@Test
public void test8() {
    String input = "Hello World!";
    ByteBuffer expected = ByteBuffer.wrap(input.getBytes(Charset.forName("UTF-8")));
    ByteBuffer result = getByteBuffer(input, Charset.forName("UTF-8"));
    assertEquals(expected, result);
}
@Test
public void test9() {
    String input = "Hello World!";
    ByteBuffer expected = ByteBuffer.wrap(input.getBytes(Charset.forName("ISO-8859-1")));
    ByteBuffer result = getByteBuffer(input, Charset.forName("ISO-8859-1"));
    assertEquals(expected, result);
}
@Test
public void test10() {
    String input = "Hello World!";
    ByteBuffer expected = ByteBuffer.wrap(input.getBytes(Charset.forName("US-ASCII")));
    ByteBuffer result = getByteBuffer(input, Charset.forName("US-ASCII"));
    assertEquals(expected, result);
}
@Test
public void test11() {
    // Test case 1
    String string1 = "Hello world";
    ByteBuffer expected1 = ByteBuffer.wrap(string1.getBytes(StandardCharsets.UTF_8));
    ByteBuffer actual1 = getByteBufferUtf8(string1);
    assertEquals(expected1, actual1);

    // Test case 2
    String string2 = "";
    ByteBuffer expected2 = ByteBuffer.wrap(string2.getBytes(StandardCharsets.UTF_8));
    ByteBuffer actual2 = getByteBufferUtf8(string2);
    assertEquals(expected2, actual2);

    // Test case 3
    String string3 = "12345";
    ByteBuffer expected3 = ByteBuffer.wrap(string3.getBytes(StandardCharsets.UTF_8));
    ByteBuffer actual3 = getByteBufferUtf8(string3);
    assertEquals(expected3, actual3);
}
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression3(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression4(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("ÄüÖ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression5(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("1234");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE1.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE1.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE1.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE1, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE1.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE1.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE1.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE2.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("1234");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("@#$%");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Tests
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        final String nullString = null;
        final byte[] expectedNull = new byte[0];
        final byte[] actualNull = StringUtils.getBytesUtf16(nullString);
        Assert.assertTrue(Arrays.equals(expectedNull, actualNull));
        
        final String specialChars = "!@#$%^&*()";
        final byte[] expectedSpecialChars = specialChars.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf16(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
    }
    @Test
    public void test31() {
        final String charsetName = "";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: empty string
        final byte[] expected2 = "".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 2: string with Unicode characters
        final String fixture3 = "Hello 世界";
        final byte[] expected3 = fixture3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le(fixture3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
@Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedWithEmptyString(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedWithSpecialCharacters(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedWithLongString(charsetName);
        final byte[] expected = "abcdefghijklmnopqrstuvwxyz".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("abcdefghijklmnopqrstuvwxyz");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression Test Case: Changing the input charset name
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression Test Case: Changing the input charset name
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(NEW_BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(NEW_BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(NEW_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(NEW_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(NEW_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(NEW_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(NEW_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(NEW_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(NEW_BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(NEW_BYTES_FIXTURE_16LE);
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
            StringUtils.newString(NEW_BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(NEW_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(NEW_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = new byte[]{0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] bytes = new byte[]{0x61, 0x62, 0x63};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] bytes = new byte[]{0x61, 0x62, 0x63};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = new byte[]{0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = new byte[]{0x61, 0x00, 0x62, 0x00, 0x63, 0x00};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() {
        try {
            final byte[] bytes = new byte[]{0x61, 0x62, 0x63};
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] bytes = new byte[]{0x61, 0x62, 0x63};
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
@Test
public void test56() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";

    // Test case 1: empty byte array
    byte[] emptyBytes = new byte[0];
    String expected1 = new String(emptyBytes, charsetName);
    String actual1 = StringUtils.newStringIso8859_1(emptyBytes);
    Assert.assertEquals(expected1, actual1);

    // Test case 2: byte array with single character
    byte[] singleCharBytes = {65};
    String expected2 = new String(singleCharBytes, charsetName);
    String actual2 = StringUtils.newStringIso8859_1(singleCharBytes);
    Assert.assertEquals(expected2, actual2);

    // Test case 3: byte array with multiple characters
    byte[] multipleCharBytes = {72, 101, 108, 108, 111};
    String expected3 = new String(multipleCharBytes, charsetName);
    String actual3 = StringUtils.newStringIso8859_1(multipleCharBytes);
    Assert.assertEquals(expected3, actual3);
}
@Test
public void test57() {
    // Test case 1: null byte array
    Assert.assertNull(StringUtils.newStringUtf8(null));
    
    // Test case 2: null byte array
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    
    // Test case 3: null byte array
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    
    // Test case 4: null byte array
    Assert.assertNull(StringUtils.newStringUtf16(null));
    
    // Test case 5: null byte array
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    
    // Test case 6: null byte array
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
}
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = "";
        final String actual = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = "Hello";
        final String actual = StringUtils.newStringUsAscii(new byte[]{72,101,108,108,111});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = "12345";
        final String actual = StringUtils.newStringUsAscii(new byte[]{49,50,51,52,53});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);

        // Regression test 1
        final byte[] bytes1 = {0x00, 0x00, 0x00, 0x41, 0x00, 0x00, 0x00, 0x42};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2
        final byte[] bytes2 = {0x00, 0x00, 0x33, 0x44, 0x00, 0x00, 0x34, 0x45};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression test 3
        final byte[] bytes3 = {0x00, 0x00, 0x53, 0x21, 0x00, 0x00, 0x54, 0x22};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes3);
        Assert.assertEquals(expected3, actual3);

        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1: Empty input
        byte[] emptyInput = new byte[0];
        final String expected1 = new String(emptyInput, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(emptyInput);
        Assert.assertEquals(expected1, actual1);

        // Regression Test 2: Input with 1 byte
        byte[] input1 = new byte[]{0x00};
        final String expected2 = new String(input1, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(input1);
        Assert.assertEquals(expected2, actual2);

        // Regression Test 3: Input with all 0 bytes
        byte[] allZeroBytesInput = new byte[10];
        final String expected3 = new String(allZeroBytesInput, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(allZeroBytesInput);
        Assert.assertEquals(expected3, actual3);

        // Regression Test 4: Input with a single character
        byte[] input2 = new byte[]{0x41, 0x00};
        final String expected4 = new String(input2, charsetName);
        final String actual4 = StringUtils.newStringUtf16Be(input2);
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);

        // Regression test case 1: Empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test case 2: Byte array with a single byte
        final byte[] bytes2 = {65};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);

        // Regression test case 3: Byte array with multiple bytes
        final byte[] bytes3 = {65, 66, 67};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test 1: Empty byte array
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[0]));

        // Regression test 2: Byte array with one byte
        byte[] bytes = {12};
        Assert.assertEquals("\f", StringUtils.newStringUtf8(bytes));

        // Regression test 3: Byte array with special characters
        bytes = {(byte)244, 32};
        Assert.assertEquals("ô ", StringUtils.newStringUtf8(bytes));
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test 1: Byte array with multiple bytes
        byte[] bytes = {97, 98, 99, 100};
        Assert.assertEquals("abcd", StringUtils.newStringUtf8(bytes));

        // Regression test 2: Byte array with special characters
        bytes = {(byte)195, (byte)177}; // "±" in UTF-8
        Assert.assertEquals("±", StringUtils.newStringUtf8(bytes));
    }
}