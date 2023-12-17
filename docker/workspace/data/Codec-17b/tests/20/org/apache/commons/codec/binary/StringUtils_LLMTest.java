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
        final byte[] actual = StringUtils.getBytesUtf16("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
public class ByteBufferTest {

    @Test
    public void test12() {
        String input = "Hello";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(charset));

        ByteBuffer result = getByteBuffer(input, charset);

        assertEquals(expected, result);
    }

    @Test
    public void test13() {
        String input = null;
        Charset charset = Charset.forName("UTF-8");

        ByteBuffer result = getByteBuffer(input, charset);

        assertNull(result);
    }

    // Regression test cases

    @Test
    public void test14() {
        String input = "";
        Charset charset = Charset.forName("ISO-8859-1");
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(charset));

        ByteBuffer result = getByteBuffer(input, charset);

        assertEquals(expected, result);
    }

    @Test
    public void test15() {
        String input = "Hello";
        Charset charset = Charset.forName("ISO-8859-1");
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(charset));

        ByteBuffer result = getByteBuffer(input, charset);

        assertEquals(expected, result);
    }

    @Test
    public void test16() {
        String input = "Bonjour";
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(charset));

        ByteBuffer result = getByteBuffer(input, charset);

        assertEquals(expected, result);
    }

    @Test
    public void test17() {
        String input = "Hello";
        Charset charset = Charset.forName("UTF-16");
        ByteBuffer expected = ByteBuffer.wrap(input.getBytes(charset));

        ByteBuffer result = getByteBuffer(input, charset);

        assertEquals(expected, result);
    }
}
@Test
public void test18() {
    String input = "Hello World!";
    ByteBuffer expected = ByteBuffer.wrap("Hello World!".getBytes(StandardCharsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(input);

    assertEquals(expected, result);
}
@Test
public void test19() {
    String input = "";
    ByteBuffer expected = ByteBuffer.wrap("".getBytes(StandardCharsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(input);

    assertEquals(expected, result);
}
@Test
public void test20() {
    String input = "!@#$%^&*()-_=+[]{};':,.<>/?`~";
    ByteBuffer expected = ByteBuffer.wrap("!@#$%^&*()-_=+[]{};':,.<>/?`~".getBytes(StandardCharsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(input);

    assertEquals(expected, result);
}
@Test
public void test21() {
    String input = "こんにちは";
    ByteBuffer expected = ByteBuffer.wrap("こんにちは".getBytes(StandardCharsets.UTF_8));

    ByteBuffer result = getByteBufferUtf8(input);

    assertEquals(expected, result);
}
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Regression test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcABC123".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("abcABC123");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Lorem ipsum".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Lorem ipsum");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() {
        try {
            StringUtils.getBytesUnchecked("Hello, world!", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcde".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("abcde");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression Test Cases
        final byte[] expected1 = "Hello World".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        final byte[] expected2 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        final byte[] expected4 = "abcdefghijklmnopqrstuvwxyz".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUsAscii("abcdefghijklmnopqrstuvwxyz");
        Assert.assertTrue(Arrays.equals(expected4, actual4));

        final byte[] expected5 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesUsAscii("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
@Test
public void test35() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test36() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test37() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "$#@!".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("$#@!");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        // Regression test 2: Non-ASCII characters
        final String nonAsciiString = "你好";
        final byte[] expectedNonAscii = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesUtf16Be(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));

        // Regression test 3: Null string
        final byte[] expectedNull = null;
        final byte[] actualNull = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expectedNull, actualNull);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // changing the input value to empty string
        final byte[] actual = StringUtils.getBytesUtf16Le(""); // changing the input value to empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello world!".getBytes(charsetName); // changing the input value to "Hello world!"
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello world!"); // changing the input value to "Hello world!"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test46() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN2");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1
        final byte[] bytes1 = "regression test".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2
        final byte[] bytes2 = "".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1
        final byte[] bytes1 = "regression test".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2
        final byte[] bytes2 = "".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1
        final byte[] bytes1 = "regression test".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2
        final byte[] bytes2 = "".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1
        final byte[] bytes1 = "regression test".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2
        final byte[] bytes2 = "".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1
        final byte[] bytes1 = "regression test".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2
        final byte[] bytes2 = "".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1
        final byte[] bytes1 = "regression test".getBytes(charsetName);
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf8(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2
        final byte[] bytes2 = "".getBytes(charsetName);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
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
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
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
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        
        final byte[] bytes = new byte[] { 65, 66, 67 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        
        final byte[] bytes = new byte[] { 97, 98, 99 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{0x41, 0x42, 0x43});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{0x30, 0x31, 0x32, 0x33});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{0x23, 0x24, 0x25, 0x26});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        // Test with empty input
        final byte[] emptyBytes = new byte[0];
        final String emptyExpected = new String(emptyBytes, "UTF-16");
        final String emptyActual = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        // Test with non-empty input
        final byte[] nonEmptyBytes = {72, 101, 108, 108, 111}; // "Hello" in ASCII
        final String nonEmptyExpected = new String(nonEmptyBytes, "UTF-16");
        final String nonEmptyActual = StringUtils.newStringUtf16(nonEmptyBytes);
        Assert.assertEquals(nonEmptyExpected, nonEmptyActual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test Case 1
        final byte[] bytes1 = { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43 }; // "ABC" in UTF-16BE
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test Case 2
        final byte[] bytes2 = { 0x1F, (byte)0xD8, 0x00, 0x1F, (byte)0xD9, 0x00 }; // Surrogate pair for a "smiling face" emoji in UTF-16BE
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = {(byte) 0xFF, (byte) 0xFE, 0x00, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = {(byte) 0xFF, (byte) 0xFE, 0x00, 0x34, (byte) 0xA1, (byte) 0x1E};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = {(byte) 0xA1, (byte) 0x1E, 0x00, 0x34};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() {
        // Regression test case 1: passing empty byte array
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
        
        // Regression test case 2: passing byte array with 1 element
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {0x00}));

        // Regression test case 3: passing byte array with null element
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {0x00, 0x00, (byte)0x80}));

        // Existing test case from the original code
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Regression test case 1: passing byte array with 1 element
        byte[] singleByte = {-1};
        String expected = new String(singleByte, charsetName);
        String actual = StringUtils.newStringUtf8(singleByte);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 2: passing byte array with 2 elements
        byte[] twoBytes = {-1, 0};
        expected = new String(twoBytes, charsetName);
        actual = StringUtils.newStringUtf8(twoBytes);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 3: passing byte array with all characters of 1 byte long
        byte[] bytes = {97, 98, 99};
        expected = new String(bytes, charsetName);
        actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
        
        // Existing test case from the original code
        testNewString(charsetName);
        expected = new String(BYTES_FIXTURE, charsetName);
        actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
}