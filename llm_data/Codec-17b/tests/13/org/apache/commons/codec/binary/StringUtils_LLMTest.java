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
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test6() {
    ByteBuffer buffer = getByteBuffer(null, Charset.defaultCharset());
    assertNull(buffer);
}
@Test
public void test7() {
    ByteBuffer buffer = getByteBuffer("", Charset.defaultCharset());
    assertArrayEquals(new byte[0], buffer.array());
}
@Test
public void test8() {
    ByteBuffer buffer = getByteBuffer("Hello World", Charset.defaultCharset());
    assertArrayEquals("Hello World".getBytes(Charset.defaultCharset()), buffer.array());
}
@Test
public void test9() {
    ByteBuffer buffer = getByteBuffer("Hello World", Charset.forName("UTF-16"));
    assertArrayEquals("Hello World".getBytes(Charset.forName("UTF-16")), buffer.array());
}
@Test
public void test10() {
    // Test with empty string
    String emptyString = "";
    ByteBuffer result1 = getByteBufferUtf8(emptyString);
    assertEquals(0, result1.remaining());

    // Test with a single character string
    String singleChar = "a";
    ByteBuffer result2 = getByteBufferUtf8(singleChar);
    assertEquals(1, result2.remaining());
    assertEquals('a', result2.get());

    // Test with a string containing multiple characters
    String multipleChars = "Hello World!";
    ByteBuffer result3 = getByteBufferUtf8(multipleChars);
    assertEquals(12, result3.remaining());
    for (int i = 0; i < multipleChars.length(); i++) {
        assertEquals(multipleChars.charAt(i), result3.get());
    }

    // Test with a string containing non-ascii characters
    String nonAsciiChars = "你好，世界！";
    ByteBuffer result4 = getByteBufferUtf8(nonAsciiChars);
    assertEquals(18, result4.remaining());
    for (int i = 0; i < nonAsciiChars.length(); i++) {
        assertEquals(nonAsciiChars.charAt(i), result4.get());
    }
}
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";

        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test with empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Regression test with special characters
        final String specialCharacters = "!@#$%^&*()+=-{}[]|\\:;<>,.?/~";
        final byte[] expectedSpecial = specialCharacters.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesIso8859_1(specialCharacters);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));

        // Regression test with non ASCII characters
        final String nonAsciiCharacters = "ÄÖÜ";
        final byte[] expectedNonAscii = nonAsciiCharacters.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesIso8859_1(nonAsciiCharacters);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
@Test
public void test12() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUsAscii(OTHER_STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test13() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be(OTHER_STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test14() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(OTHER_STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test15() {
    try {
        StringUtils.getBytesUnchecked(OTHER_STRING_FIXTURE, "UNKNOWN");
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
    final byte[] actual = StringUtils.getBytesIso8859_1(OTHER_STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test17() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16(OTHER_STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test18() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8(OTHER_STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test19() {
    Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
}
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";

        // Regression test 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "abc!@#".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("abc!@#");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3: String with non-ASCII characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "æøå".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("æøå");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Test case 1: Empty string input
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test case 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "Hello, World! åäö".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("Hello, World! åäö");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test case 3: String with numbers
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        final byte[] expectedReg1 = "".getBytes(charsetName);
        final byte[] actualReg1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expectedReg1, actualReg1));
        
        // Regression test case 2
        final byte[] expectedReg2 = "test".getBytes(charsetName);
        final byte[] actualReg2 = StringUtils.getBytesUtf16Le("test");
        Assert.assertTrue(Arrays.equals(expectedReg2, actualReg2));
        
        // Regression test case 3
        final byte[] expectedReg3 = "123".getBytes(charsetName);
        final byte[] actualReg3 = StringUtils.getBytesUtf16Le("123");
        Assert.assertTrue(Arrays.equals(expectedReg3, actualReg3));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression3(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression4(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Testing 1,2,3");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ASCII");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ASCII");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test with different input
        final String differentInput = "Hello World!";
        final byte[] differentBytes = differentInput.getBytes(charsetName);
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringUtf16Be(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test with different input
        final String differentInput = "Hello World!";
        final byte[] differentBytes = differentInput.getBytes(charsetName);
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringUsAscii(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test with different input
        final String differentInput = "Hello World!";
        final byte[] differentBytes = differentInput.getBytes(charsetName);
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringIso8859_1(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test with different input
        final String differentInput = "Hello World!";
        final byte[] differentBytes = differentInput.getBytes(charsetName);
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringUtf16(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test with different input
        final String differentInput = "Hello World!";
        final byte[] differentBytes = differentInput.getBytes(charsetName);
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringUtf16Le(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test with different input
        final String differentInput = "Hello World!";
        final byte[] differentBytes = differentInput.getBytes(charsetName);
        final String expectedDifferent = new String(differentBytes, charsetName);
        final String actualDifferent = StringUtils.newStringUtf8(differentBytes);
        Assert.assertEquals(expectedDifferent, actualDifferent);
    }
@Test
public void test40() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString_regression(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE_REGRESSION, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_REGRESSION);
    Assert.assertEquals(expected, actual);
}
@Test
public void test41() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString_regression(charsetName);
    final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_REGRESSION);
    Assert.assertEquals(expected, actual);
}
@Test
public void test42() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString_regression(charsetName);
    final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_REGRESSION);
    Assert.assertEquals(expected, actual);
}
@Test
public void test43() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString_regression(charsetName);
    final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
    final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_REGRESSION);
    Assert.assertEquals(expected, actual);
}
@Test
public void test44() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString_regression(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE_REGRESSION, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_REGRESSION);
    Assert.assertEquals(expected, actual);
}
@Test
public void test45() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString_regression(charsetName);
    final String expected = new String(BYTES_FIXTURE_REGRESSION, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_REGRESSION);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytesInput = new byte[]{0x41, 0x42, 0x43};
        final String expected = new String(bytesInput, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytesInput);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test49() {
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{65, 66, 67, 68, 69});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{72, 101, 108, 108, 111});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Regression Test Case 1
        final String expected1 = new String(BYTES_FIXTURE, charsetName);
        final String actual1 = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected1, actual1);

        // Regression Test Case 2
        final byte[] bytes2 = new byte[]{};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test Case 3
        final byte[] bytes3 = new byte[]{65, 66, 67};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16(bytes3);
        Assert.assertEquals(expected3, actual3);
        
        // ...add more test cases as needed...
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final byte[] bytes = {0x00};
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final byte[] bytes = {0x42, 0x2A, (byte) 0x93};
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final byte[] bytes = {(byte) 0xFF, (byte) 0xFE};
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() {
        final byte[] bytes = {0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        final String expected = "abc";
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() {
        final byte[] bytes = {0x00, 0x48, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F};
        final String expected = "Hello";
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() {
        final byte[] bytes = {0x00, 0x54, 0x00, 0x68, 0x00, 0x69, 0x00, 0x73, 0x00, 0x20, 0x00, 0x69, 0x00, 0x73, 0x00, 0x20, 0x00, 0x61, 0x00, 0x20, 0x00, 0x74, 0x00, 0x65, 0x00, 0x73, 0x00, 0x74};
        final String expected = "This is a test";
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        // Regression test case 1: null input
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case 2: empty input
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[0]));
        Assert.assertEquals("", StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertEquals("", StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertEquals("", StringUtils.newStringUtf16(new byte[0]));
        Assert.assertEquals("", StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertEquals("", StringUtils.newStringUtf16Le(new byte[0]));

        // Regression test case 3: input with non-UTF-8 characters
        byte[] bytesFixture = new byte[] { -62, -1, -3, -23, -45, -95 };
        Assert.assertEquals("������", StringUtils.newStringUtf8(bytesFixture));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        // Regression test case 1: UTF-8 characters
        byte[] bytesFixture = new byte[] { 0x48, 0x65, 0x6C, 0x6C, 0x6F };
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(bytesFixture, charsetName);
        final String actual = StringUtils.newStringUtf8(bytesFixture);
        Assert.assertEquals(expected, actual);

        // Regression test case 2: input with non-UTF-8 characters
        byte[] bytesFixture2 = new byte[] { -62, -1, -3, -23, -45, -95 };
        final String expected2 = new String(bytesFixture2, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytesFixture2);
        Assert.assertEquals(expected2, actual2);
    }
}