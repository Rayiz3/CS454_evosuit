package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "hello";
    boolean result = YourClass.equals(cs1, cs2);
    assertTrue(result);
}
@Test
public void test1() {
    CharSequence cs1 = "hello";
    CharSequence cs2 = "world";
    boolean result = YourClass.equals(cs1, cs2);
    assertFalse(result);
}
@Test
public void test2() {
    CharSequence cs1 = null;
    CharSequence cs2 = "hello";
    boolean result = YourClass.equals(cs1, cs2);
    assertFalse(result);
}
@Test
public void test3() {
    CharSequence cs1 = "";
    CharSequence cs2 = "";
    boolean result = YourClass.equals(cs1, cs2);
    assertTrue(result);
}
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test16() {
    String string = null;
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNull(result);
}
@Test
public void test17() {
    String string = "";
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(0, result.capacity());
}
@Test
public void test18() {
    String string = "Hello World";
    Charset charset = StandardCharsets.UTF_8;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(string.getBytes(charset).length, result.capacity());
    assertEquals(string, new String(result.array(), charset));
}
@Test
public void test19() {
    String string = "Hello World";
    Charset charset = null;

    ByteBuffer result = getByteBuffer(string, charset);

    assertNotNull(result);
    assertEquals(string.getBytes().length, result.capacity());
    assertEquals(string, new String(result.array()));
}
@Test
public void test20() {
    String string = "hello";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test21() {
    String string = "";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
@Test
public void test22() {
    String string = "こんにちは";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(Charsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(string);

    assertEquals(expected, result);
}
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test Case 1: Empty String
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression Test Case 2: String with special characters
        final byte[] expected2 = "¡ÈÆÀßệЖ".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("¡ÈÆÀßệЖ");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test24() {
        try {
            StringUtils.getBytesUnchecked("HELLO WORLD", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO WORLD".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("HELLO WORLD");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO WORLD".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("HELLO WORLD");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO WORLD".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("HELLO WORLD");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO WORLD".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("HELLO WORLD");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO WORLD".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("HELLO WORLD");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO WORLD".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("HELLO WORLD");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: String with all ASCII characters
        final byte[] expected2 = "HelloWorld".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("HelloWorld");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with non-ASCII characters
        final byte[] expected3 = "你好世界".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("你好世界");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //Regression test 1: empty string
        final String emptyString = "";
        final byte[] expectedEmpty = emptyString.getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        //Regression test 2: string with special characters
        final String specialChars = "èø¬#";
        final byte[] expectedSpecial = specialChars.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf16Be(specialChars);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
        
        //Regression test 3: string with numbers
        final String numbers = "12345";
        final byte[] expectedNumbers = numbers.getBytes(charsetName);
        final byte[] actualNumbers = StringUtils.getBytesUtf16Be(numbers);
        Assert.assertTrue(Arrays.equals(expectedNumbers, actualNumbers));
        
        //Regression test 4: string with emoji
        final String emoji = "😀😂😃😄😊";
        final byte[] expectedEmoji = emoji.getBytes(charsetName);
        final byte[] actualEmoji = StringUtils.getBytesUtf16Be(emoji);
        Assert.assertTrue(Arrays.equals(expectedEmoji, actualEmoji));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Special Characters: @#$%^&*".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Special Characters: @#$%^&*");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test46() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
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
        Assert.assertNotNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test64() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test65() {
        Assert.assertNotNull(StringUtils.newStringUtf8(null));
        Assert.assertNotNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNotNull(StringUtils.newStringUsAscii(null));
        Assert.assertNotNull(StringUtils.newStringUtf16(null));
        Assert.assertNotNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNotNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test66() {
        Assert.assertEquals(StringUtils.newString(new byte[0], "UTF-8"), "");
        Assert.assertEquals(StringUtils.newString(new byte[0], "ISO-8859-1"), "");
        Assert.assertEquals(StringUtils.newString(new byte[0], "US-ASCII"), "");
        Assert.assertEquals(StringUtils.newString(new byte[0], "UTF-16"), "");
        Assert.assertEquals(StringUtils.newString(new byte[0], "UTF-16BE"), "");
        Assert.assertEquals(StringUtils.newString(new byte[0], "UTF-16LE"), "");
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = "Hello, World!".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newString(bytes, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        // Add more regression tests here
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("", StringUtils.newStringIso8859_1(new byte[]{}));
        // Add more regression tests here
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1: Empty byte array
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2: Non-ASCII byte array
        final byte[] bytes2 = {97, 98, 99, 100, 101};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test 3: Byte array with special characters
        final byte[] bytes3 = {33, 64, 35, 36, 37};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Regression test 1: empty input
        testNewString(charsetName);
        final byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: input with special characters
        testNewString(charsetName);
        final byte[] specialBytes = new byte[] {(byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x00, (byte) 0xFF, (byte) 0x00};
        final String expected2 = new String(specialBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16(specialBytes);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        // Regression test 1 with different input
        byte[] bytes1 = "Hello".getBytes();
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2 with different input
        byte[] bytes2 = {65, 66, 67, 68, 69};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regression1, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString_regression2(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regression2, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regression2);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test80() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty input
        final String expected1 = new String(new byte[]{}, charsetName);
        final String actual1 = StringUtils.newStringUtf8(new byte[]{});
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Input with special characters
        final String expected2 = new String(new byte[]{65, 66, 67, (byte) 195, (byte) 169}, charsetName);
        final String actual2 = StringUtils.newStringUtf8(new byte[]{65, 66, 67, (byte) 195, (byte) 169});
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test81() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: Null input with supported charset (UTF-8)
        final String charsetName = "UTF-8";
        final String expected1 = new String(new byte[]{}, charsetName);
        final String actual1 = StringUtils.newStringUtf8(null);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Null input with unsupported charset
        final String unsupportedCharsetName = "unsupportedCharset";
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
}