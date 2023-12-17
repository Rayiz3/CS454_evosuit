package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Test case 1: both cs1 and cs2 are null
    CharSequence cs1 = null;
    CharSequence cs2 = null;
    boolean expected1 = true;
    boolean actual1 = equals(cs1, cs2);
    assertEquals(expected1, actual1);

    // Test case 2: cs1 is null and cs2 is not null
    cs1 = null;
    cs2 = "hello";
    boolean expected2 = false;
    boolean actual2 = equals(cs1, cs2);
    assertEquals(expected2, actual2);

    // Test case 3: cs1 is not null and cs2 is null
    cs1 = "hello";
    cs2 = null;
    boolean expected3 = false;
    boolean actual3 = equals(cs1, cs2);
    assertEquals(expected3, actual3);

    // Test case 4: cs1 and cs2 are empty strings
    cs1 = "";
    cs2 = "";
    boolean expected4 = true;
    boolean actual4 = equals(cs1, cs2);
    assertEquals(expected4, actual4);

    // Test case 5: cs1 and cs2 are equal strings
    cs1 = "hello";
    cs2 = "hello";
    boolean expected5 = true;
    boolean actual5 = equals(cs1, cs2);
    assertEquals(expected5, actual5);

    // Test case 6: cs1 and cs2 are different strings
    cs1 = "hello";
    cs2 = "world";
    boolean expected6 = false;
    boolean actual6 = equals(cs1, cs2);
    assertEquals(expected6, actual6);
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = getStringFixture_regression(charsetName).getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(getStringFixture_regression(charsetName));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = getStringFixture_regression(charsetName).getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(getStringFixture_regression(charsetName));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = getStringFixture_regression(charsetName).getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(getStringFixture_regression(charsetName));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = getStringFixture_regression(charsetName).getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(getStringFixture_regression(charsetName));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = getStringFixture_regression(charsetName).getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(getStringFixture_regression(charsetName));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = getStringFixture_regression(charsetName).getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(getStringFixture_regression(charsetName));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    private void testGetBytesUnchecked_regression(final String charsetName) throws UnsupportedEncodingException {
        // Modify the test logic to accommodate the regression test
        Assert.assertEquals(null, StringUtils.getBytes(null, Charset.forName(charsetName)));
    }
    private String getStringFixture_regression(final String charsetName) {
        if (charsetName.equals("UTF-16")) {
            return "Hello, world!";
        } else if (charsetName.equals("UTF-16BE")) {
            return "Привет, мир!";
        } else if (charsetName.equals("UTF-16LE")) {
            return "Bonjour le monde!";
        } else if (charsetName.equals("UTF-8")) {
            return "안녕하세요, 세계!";
        } else if (charsetName.equals("US-ASCII")) {
            return "Hallo, Welt!";
        } else if (charsetName.equals("ISO-8859-1")) {
            return "Ciao, mondo!";
        } else {
            throw new IllegalArgumentException("Invalid charset name");
        }
    }
@Test
public void test7() {
    String string = "Hello World";
    Charset charset = StandardCharsets.UTF_8;
    
    ByteBuffer byteBuffer = getByteBuffer(string, charset);
    
    assertNotNull(byteBuffer);
    assertEquals(ByteBuffer.wrap(string.getBytes(charset)), byteBuffer);
}
@Test
public void test8() {
    String string = "Hello World";
    Charset charset = StandardCharsets.ISO_8859_1;
    
    ByteBuffer byteBuffer = getByteBuffer(string, charset);
    
    assertNotNull(byteBuffer);
    assertEquals(ByteBuffer.wrap(string.getBytes(charset)), byteBuffer);
}
@Test
public void test9() {
    String string = "";
    Charset charset = StandardCharsets.UTF_8;
    
    ByteBuffer byteBuffer = getByteBuffer(string, charset);
    
    assertNotNull(byteBuffer);
    assertEquals(ByteBuffer.wrap(string.getBytes(charset)), byteBuffer);
}
@Test
public void test10() {
    String string = null;
    Charset charset = StandardCharsets.UTF_8;
    
    ByteBuffer byteBuffer = getByteBuffer(string, charset);
    
    assertNull(byteBuffer);
}
@Test
public void test11() {
    // Test case 1: Empty string
    String input1 = "";
    ByteBuffer expectedOutput1 = ByteBuffer.allocate(0);
    ByteBuffer actualOutput1 = getByteBufferUtf8(input1);
    assertEquals(expectedOutput1, actualOutput1);

    // Test case 2: String with special characters
    String input2 = "@#$%^&*()_";
    byte[] bytes2 = input2.getBytes(Charsets.UTF_8);
    ByteBuffer expectedOutput2 = ByteBuffer.wrap(bytes2);
    ByteBuffer actualOutput2 = getByteBufferUtf8(input2);
    assertEquals(expectedOutput2, actualOutput2);

    // Test case 3: Non-English string
    String input3 = "こんにちは";
    byte[] bytes3 = input3.getBytes(Charsets.UTF_8);
    ByteBuffer expectedOutput3 = ByteBuffer.wrap(bytes3);
    ByteBuffer actualOutput3 = getByteBufferUtf8(input3);
    assertEquals(expectedOutput3, actualOutput3);
}
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Regression test 1: Empty string
        final byte[] expectedRegression1 = "".getBytes(charsetName);
        final byte[] actualRegression1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expectedRegression1, actualRegression1));
        // Regression test 2: String with special characters
        final String inputRegression2 = "Hello@World! #";
        final byte[] expectedRegression2 = inputRegression2.getBytes(charsetName);
        final byte[] actualRegression2 = StringUtils.getBytesIso8859_1(inputRegression2);
        Assert.assertTrue(Arrays.equals(expectedRegression2, actualRegression2));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() {
        try {
            StringUtils.getBytesUnchecked("Hello World!", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedRegression(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Testing 123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Testing 123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello, World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";

        // Regression test 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test 2: string with special characters
        final byte[] expected2 = "¡Hóla!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("¡Hóla!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test 3: string with numbers
        final byte[] expected3 = "123".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("123");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Regression test case 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "∆øœƒ©ß".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("∆øœƒ©ß");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with whitespace characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "   \t  ".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8("   \t  ");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression test case 4: String with numbers
        testGetBytesUnchecked(charsetName);
        final byte[] expected4 = "12345".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
        
        // Regression test case 5: String with special characters, whitespace and numbers
        testGetBytesUnchecked(charsetName);
        final byte[] expected5 = "∆ø œƒ 12345".getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesUtf8("∆ø œƒ 12345");
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test34() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8-INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with special characters
        final byte[] bytes2 = {(byte) 0x00, (byte) 0x41, (byte) 0x00, (byte) 0xE9, (byte) 0x00, (byte) 0x42};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with special characters
        final byte[] bytes2 = {0x41, 0x23, 0x5F, 0x2D};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with special characters
        final byte[] bytes2 = {(byte) 0xC4, (byte) 0xE4, (byte) 0xF6, (byte) 0xD6};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test38() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        
        // Regression test case 1: null charset
        Assert.assertNull(StringUtils.newString(BYTES_FIXTURE, null));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with special characters
        final byte[] bytes2 = {(byte) 0x41, (byte) 0x00, (byte) 0xE9, (byte) 0x00, (byte) 0x42, (byte) 0x00};
        final String expected2 = new String(bytes2, charsetName);
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
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with special characters
        final byte[] bytes2 = {(byte) 0x41, (byte) 0x00, (byte) 0xE9, (byte) 0x00, (byte) 0x42, (byte) 0x00};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test41() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1: null input for newStringUtf8() method
        Assert.assertNull(StringUtils.newStringUtf8(BYTES_FIXTURE));
        
        // Regression test case 2: null input for newStringIso8859_1() method
        Assert.assertNull(StringUtils.newStringIso8859_1(BYTES_FIXTURE));
        
        // Regression test case 3: null input for newStringUsAscii() method
        Assert.assertNull(StringUtils.newStringUsAscii(BYTES_FIXTURE));
        
        // Regression test case 4: null input for newStringUtf16() method
        Assert.assertNull(StringUtils.newStringUtf16(BYTES_FIXTURE));
        
        // Regression test case 5: null input for newStringUtf16Be() method
        Assert.assertNull(StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE));
        
        // Regression test case 6: null input for newStringUtf16Le() method
        Assert.assertNull(StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE));
    }
    @Test
    public void test42() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test case 1: unknown charset
        try {
            StringUtils.newString(BYTES_FIXTURE, "ABC");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with special characters
        final byte[] bytes2 = {(byte) 0xC5, (byte) 0x8D};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{0x00, 0x01, 0x02, 0x03});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{0x41, 0x42, 0x43});
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
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{0x61, 0x62, 0x63});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[]{0x00, 0x01, 0x02, 0x03});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[]{0x00, 0x01, 0x02, 0x03});
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
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{0x41, 0x42, 0x43}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{0x00, 0x01, 0x02, 0x03}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{0x61, 0x62, 0x63}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{0x00, 0x01, 0x02, 0x03}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0x00, 0x01, 0x02, 0x03}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{0x00, 0x01, 0x02, 0x03}));
    }
    @Test
    public void test58() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test59() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{0x41, 0x42, 0x43});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: input with empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: input with special characters
        final byte[] bytes2 = new byte[] { 0x48, 0x65, 0x6c, 0x6c, 0x6f, (byte)0xE7 };
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: input with non-null byte array
        final byte[] bytes1 = new byte[] { 0x41, 0x42, 0x43 };
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes1));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final byte[] newBytes = {65, 66, 67, 68, 69};
        final String actual = StringUtils.newStringUsAscii(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final byte[] newBytes = {97, 98, 99, 100, 101};
        final String actual = StringUtils.newStringUsAscii(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Test case with empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test case with special characters
        final byte[] specialBytes = {-2, -1, 0, 48, 0, 50};
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUtf16(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
        
        // Test case with multibyte characters
        final byte[] multibyteBytes = {0, 1, 0, 65, 0, 66};
        final String expectedMultibyte = new String(multibyteBytes, charsetName);
        final String actualMultibyte = StringUtils.newStringUtf16(multibyteBytes);
        Assert.assertEquals(expectedMultibyte, actualMultibyte);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        
        // Regression test 1: empty input
        final String expected1 = new String(new byte[]{}, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: input with a single byte
        final String expected2 = new String(new byte[]{12}, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(new byte[]{12});
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: input with multiple bytes
        final byte[] input3 = {127, -128, 0, 35};
        final String expected3 = new String(input3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(input3);
        Assert.assertEquals(expected3, actual3);
        
        // Regression test 4: input with null characters
        final byte[] input4 = {-1, -1, 0, 0};
        final String expected4 = new String(input4, charsetName);
        final String actual4 = StringUtils.newStringUtf16Be(input4);
        Assert.assertEquals(expected4, actual4);
        
        // Regression test 5: input with all possible byte values
        final byte[] input5 = new byte[256];
        for (int i = 0; i < 256; i++) {
            input5[i] = (byte) i;
        }
        final String expected5 = new String(input5, charsetName);
        final String actual5 = StringUtils.newStringUtf16Be(input5);
        Assert.assertEquals(expected5, actual5);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = new byte[0];
        String expected = "";
        String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = null;
        String expected = null;
        String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = "Hello World!".getBytes("UTF-8");
        String expected = new String(bytes, charsetName);
        String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes1 = "Hello World!".getBytes("UTF-16");
        final byte[] bytes2 = "Hello World!".getBytes("UTF-16BE");
        final byte[] bytes3 = "Hello World!".getBytes("UTF-16LE");
        String expected1 = new String(bytes1, charsetName);
        String actual1 = StringUtils.newStringUtf16Le(bytes1);
        String expected2 = new String(bytes2, charsetName);
        String actual2 = StringUtils.newStringUtf16Le(bytes2);
        String expected3 = new String(bytes3, charsetName);
        String actual3 = StringUtils.newStringUtf16Le(bytes3);
        Assert.assertEquals(expected1, actual1);
        Assert.assertEquals(expected2, actual2);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[] {}, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(new byte[] {});
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
}