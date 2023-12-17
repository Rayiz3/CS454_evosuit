package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    CharSequence cs1 = null;
    CharSequence cs2 = null;
    boolean result = MyClass.equals(cs1, cs2);
    assertFalse(result);
}
@Test
public void test1() {
    CharSequence cs1 = null;
    CharSequence cs2 = "hello";
    boolean result = MyClass.equals(cs1, cs2);
    assertFalse(result);

    cs1 = "hello";
    cs2 = null;
    result = MyClass.equals(cs1, cs2);
    assertFalse(result);
}
@Test
public void test2() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "world";
    boolean result = MyClass.equals(cs1, cs2);
    assertFalse(result);
}
@Test
public void test3() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "hello";
    boolean result = MyClass.equals(cs1, cs2);
    assertTrue(result);
}
@Test
public void test4() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "Hello";
    boolean result = MyClass.equals(cs1, cs2);
    assertFalse(result);
}
    @Test
    public void test5() {
        final byte[] expected = null;
        final byte[] actual = getBytes(null, Charset.forName("UTF-8"));
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test6() {
        final byte[] expected = new byte[0];
        final byte[] actual = getBytes("", Charset.forName("UTF-8"));
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test7() {
        final String input = "你好世界";
        final byte[] expected = input.getBytes(Charset.forName("GBK"));
        final byte[] actual = getBytes(input, Charset.forName("GBK"));
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test8() {
        final String input = "hello";
        final byte[] expected = input.getBytes(Charset.forName("UTF-16"));
        final byte[] actual = getBytes(input, Charset.forName("UTF-16"));
        Assert.assertArrayEquals(expected, actual);
    }
@Test
public void test9() {
    String string = "Hello World";
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(string, new String(result.array(), charset));
}
@Test
public void test10() {
    String string = "Hello World";
    Charset charset = Charset.forName("ISO-8859-1");

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(string, new String(result.array(), charset));
}
@Test
public void test11() {
    String string = "";
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(string, new String(result.array(), charset));
}
@Test
public void test12() {
    String string = null;
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNull(result);
}
    @Test
    public void test13() {
        String input = "你好世界";
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

        ByteBuffer result = getByteBufferUtf8(input);

        assertByteBufferEquals(expected, result);
    }
    @Test
    public void test14() {
        String input = "";
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

        ByteBuffer result = getByteBufferUtf8(input);

        assertByteBufferEquals(expected, result);
    }
    @Test
    public void test15() {
        String input = "a";
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

        ByteBuffer result = getByteBufferUtf8(input);

        assertByteBufferEquals(expected, result);
    }
    @Test
    public void test16() {
        String input = "    ";
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));

        ByteBuffer result = getByteBufferUtf8(input);

        assertByteBufferEquals(expected, result);
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";

        // Original test cases
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test cases
        final byte[] expected2 = "abc".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("abc");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        final byte[] expected3 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        final byte[] expected4 = "中文测试".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesIso8859_1("中文测试");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Different Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Different Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Different Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() {
        try {
            StringUtils.getBytesUnchecked("Different Input", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Different Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Different Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Different Input");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() {
        Assert.assertNull(StringUtils.getBytesUnchecked("Different Input", "UNKNOWN"));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Regression test 1: Empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: String with special characters
        final String specialCharsString = "!@#$%^&*()_+-={}[]:\";',./<>?\\";
        final byte[] expectedSpecialChars = specialCharsString.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUsAscii(specialCharsString);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        final byte[] expectedRegression1 = "".getBytes(charsetName);
        final byte[] actualRegression1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expectedRegression1, actualRegression1));
        
        // Regression test 2: String with special characters
        final String specialString = "H\u00e9llo W\u00f3rld!";
        final byte[] expectedRegression2 = specialString.getBytes(charsetName);
        final byte[] actualRegression2 = StringUtils.getBytesUtf16(specialString);
        Assert.assertTrue(Arrays.equals(expectedRegression2, actualRegression2));
        
        // Regression test 3: String with numbers
        final String numberString = "12345";
        final byte[] expectedRegression3 = numberString.getBytes(charsetName);
        final byte[] actualRegression3 = StringUtils.getBytesUtf16(numberString);
        Assert.assertTrue(Arrays.equals(expectedRegression3, actualRegression3));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "RegressionTest1".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTest1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "RegressionTest2".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTest2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "RegressionTest3".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("RegressionTest3");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        // Regression test 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: string with special characters
        final byte[] expected2 = "abc123!@#".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("abc123!@#");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: changing the input to an empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression test 2: changing the input to a non-UTF-8 supported character set
        final String newCharsetName = "ISO-8859-1";
        final byte[] expectedNonUtf8 = STRING_FIXTURE.getBytes(newCharsetName);
        final byte[] actualNonUtf8 = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expectedNonUtf8, actualNonUtf8));
        
        // Regression test 3: changing the input to a string with special characters
        final String specialCharacters = "åß∂ƒ©˙∆˚¬…æœ∑´®†";
        final byte[] expectedSpecialChars = specialCharacters.getBytes(charsetName);
        final byte[] actualSpecialChars = StringUtils.getBytesUtf8(specialCharacters);
        Assert.assertTrue(Arrays.equals(expectedSpecialChars, actualSpecialChars));
    }
    @Test
    public void test33() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.getBytesUnchecked("", "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() {
        try {
            StringUtils.newString(new byte[]{}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test37() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test46() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString_regression(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    private void testNewString_regression(final String charsetName) throws UnsupportedEncodingException {
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newString(BYTES_FIXTURE, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        // Change the input to null
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        // Change the input to null
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        // Change the input to null
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test55() {
        Assert.assertNull(StringUtils.newString(null, null));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        // Change the input to null
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        // Change the input to null
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test60() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test61() {
        try {
            StringUtils.newString(BYTES_FIXTURE, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Change the input to null
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: test with empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: test with byte array containing one character
        final byte[] bytes2 = new byte[]{65};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: test with byte array containing special characters
        final byte[] bytes3 = new byte[]{64, 33, 36, 42};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringIso8859_1(bytes3);
        Assert.assertEquals(expected3, actual3);
        
        // Regression test case 4: test with large byte array
        final byte[] bytes4 = new byte[10000];
        for (int i = 0; i < bytes4.length; i++) {
            bytes4[i] = (byte) (i % 127);
        }
        final String expected4 = new String(bytes4, charsetName);
        final String actual4 = StringUtils.newStringIso8859_1(bytes4);
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1: test with null input
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Regression test case 2: test with empty byte array
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        
        // Regression test case 3: test with byte array containing one character
        final byte[] bytes3 = new byte[]{65};
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes3));
        
        // Regression test case 4: test with byte array containing special characters
        final byte[] bytes4 = new byte[]{64, 33, 36, 42};
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes4));
        
        // Regression test case 5: test with large byte array
        final byte[] bytes5 = new byte[10000];
        for (int i = 0; i < bytes5.length; i++) {
            bytes5[i] = (byte) (i % 127);
        }
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes5));
    }
    @Test
    public void test66() {
        final String expected = "";
        final byte[] bytes = new byte[0];
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() {
        final String expected = null;
        final byte[] bytes = null;
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() {
        final String expected = "æîøü";
        final byte[] bytes = { -61, -86, -29, -120, -112, -61, -68 };
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() {
        final String expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final byte[] bytes = expected.getBytes(Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        byte[] bytes = {0x00, 0x6C, (byte) 0xFF, (byte) 0xFE};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        byte[] bytes = {};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        byte[] bytes = null;
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = UnsupportedEncodingException.class)
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "InvalidCharset";
        testNewString(charsetName);
        byte[] bytes = {0x00, 0x61, 0x62, 0x63};
        StringUtils.newStringUtf16(bytes);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_reg1, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_reg1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression2(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_reg2, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_reg2);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression3(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_reg3, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_reg3);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test77() {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        
        // Regression Test Case 1
        final byte[] bytes1 = {(byte)0x00, (byte)0x41, (byte)0x00, (byte)0x42, (byte)0x00, (byte)0x43};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test Case 2
        final byte[] bytes2 = {(byte)0x30, (byte)0x41, (byte)0x30, (byte)0x42, (byte)0x30, (byte)0x43};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test Case 3
        final byte[] bytes3 = {(byte)0x41, (byte)0x00, (byte)0x42, (byte)0x00, (byte)0x43, (byte)0x00};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test78() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression Test 1: Empty byte array
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[0]));

        // Regression Test 2: Single-byte characters
        byte[] singleByteChars = {65, 66, 67, 68}; // A, B, C, D
        Assert.assertEquals("ABCD", StringUtils.newStringUtf8(singleByteChars));

        // Regression Test 3: Multi-byte characters
        byte[] multiByteChars = {(byte) 0xE6, (byte) 0x97, (byte) 0xA5, (byte) 0xE6, (byte) 0x9C, (byte) 0xAC}; // こんにちは
        Assert.assertEquals("こんにちは", StringUtils.newStringUtf8(multiByteChars));
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression Test 1: Empty byte array
        Assert.assertEquals("", StringUtils.newStringUtf8(new byte[0]));

        // Regression Test 2: Single-byte characters
        byte[] singleByteChars = {65, 66, 67, 68}; // A, B, C, D
        Assert.assertEquals("ABCD", StringUtils.newStringUtf8(singleByteChars));

        // Regression Test 3: Multi-byte characters
        byte[] multiByteChars = {(byte) 0xE6, (byte) 0x97, (byte) 0xA5, (byte) 0xE6, (byte) 0x9C, (byte) 0xAC}; // こんにちは
        Assert.assertEquals("こんにちは", StringUtils.newStringUtf8(multiByteChars));
    }
}