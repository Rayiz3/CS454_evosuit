package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final String specialString = "ãèìõù";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test9() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("new input");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test10() {
    Assert.assertNull(StringUtils.getBytesUnchecked("new input", "UNKNOWN"));
}
@Test
public void test11() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUsAscii("new input");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test12() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("new input");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test13() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("new input");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test14() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("new input");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test15() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("new input");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test16() {
    try {
        StringUtils.getBytesUnchecked("new input", "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: Changing input to an empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: Changing input to a string with special characters
        final String string2 = "Hello$World!";
        final byte[] expected2 = string2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii(string2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: Changing input to a string with uppercase letters
        final String string3 = "HELLO";
        final byte[] expected3 = string3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii(string3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String input = "Hello, 世界!";// Special characters: 世界
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        
        // Kill mutant by changing input value to an empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Kill mutant by changing input value to a string with one character
        final byte[] expected2 = "a".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("a");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Kill mutant by changing input value to a string with all uppercase characters
        final byte[] expected3 = "ABC".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("ABC");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String input = "Hello, 世界!";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String input = "Hello\u0000World";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "你好".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("你好");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "😊".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("😊");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }

        // Regression test - changing the input value
        try {
            StringUtils.getBytesUnchecked("", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }

        // Regression test - changing the input value
        try {
            StringUtils.newString(new byte[]{}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
@Test
public void test33() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(new byte[]{0, 1, 2, 3});
    Assert.assertEquals(expected, actual);
}
@Test
public void test34() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringIso8859_1(new byte[]{0, 1, 2, 3});
    Assert.assertEquals(expected, actual);
}
@Test
public void test35() {
    Assert.assertNull(StringUtils.newStringUtf8(new byte[]{0, 1, 2, 3}));
    Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{0, 1, 2, 3}));
    Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{0, 1, 2, 3}));
    Assert.assertNull(StringUtils.newStringUtf16(new byte[]{0, 1, 2, 3}));
    Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{0, 1, 2, 3}));
    Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{0, 1, 2, 3}));
}
@Test
public void test36() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUsAscii(new byte[]{0, 1, 2, 3});
    Assert.assertEquals(expected, actual);
}
@Test
public void test37() {
    Assert.assertNull(StringUtils.newString(new byte[]{0, 1, 2, 3}, "UNKNOWN"));
}
@Test
public void test38() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(new byte[]{0, 1, 2, 3});
    Assert.assertEquals(expected, actual);
}
@Test
public void test39() {
    try {
        StringUtils.newString(new byte[]{0, 1, 2, 3}, "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test40() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf8(new byte[]{0, 1, 2, 3});
    Assert.assertEquals(expected, actual);
}
@Test
public void test41() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(new byte[]{0, 1, 2, 3});
    Assert.assertEquals(expected, actual);
}
@Test
public void test42() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    final byte[] differentBytes = new byte[]{(byte) 0xFE, (byte) 0xFF};
    final String differentExpected = new String(differentBytes, charsetName);
    final String differentActual = StringUtils.newStringUtf16(differentBytes);
    Assert.assertEquals(differentExpected, differentActual);
}
@Test
public void test43() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    final byte[] differentBytes = new byte[]{(byte) 0xF1};
    final String differentExpected = new String(differentBytes, charsetName);
    final String differentActual = StringUtils.newStringIso8859_1(differentBytes);
    Assert.assertEquals(differentExpected, differentActual);
}
@Test
public void test44() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    final byte[] differentBytes = new byte[]{(byte) 0x80};
    final String differentExpected = new String(differentBytes, charsetName);
    final String differentActual = StringUtils.newStringUsAscii(differentBytes);
    Assert.assertEquals(differentExpected, differentActual);
}
@Test
public void test45() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    final byte[] differentBytes = new byte[]{(byte) 0xC0};
    final String differentExpected = new String(differentBytes, charsetName);
    final String differentActual = StringUtils.newStringUtf8(differentBytes);
    Assert.assertEquals(differentExpected, differentActual);
}
@Test
public void test46() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    Assert.assertEquals(expected, actual);
    final byte[] differentBytes = new byte[]{(byte) 0xAD};
    final String differentExpected = new String(differentBytes, charsetName);
    final String differentActual = StringUtils.newStringUtf16Be(differentBytes);
    Assert.assertEquals(differentExpected, differentActual);
}
@Test
public void test47() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
    Assert.assertEquals(expected, actual);
    final byte[] differentBytes = new byte[]{(byte) 0xAD};
    final String differentExpected = new String(differentBytes, charsetName);
    final String differentActual = StringUtils.newStringUtf16Le(differentBytes);
    Assert.assertEquals(differentExpected, differentActual);
}
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        // Regression test case 1: empty byte array
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        // Regression test case 2: invalid ISO-8859-1 byte sequence
        byte[] invalidBytes = { (byte) -1, (byte) -2, (byte) -3 };
        Assert.assertNull(StringUtils.newStringIso8859_1(invalidBytes));
    }
    @Test
    public void test50() {
        // Test with empty input
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, Charsets.US_ASCII);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // Test with non-ASCII characters
        final byte[] nonAsciiBytes = { 127, -128, -1 };
        final String expectedNonAscii = new String(nonAsciiBytes, Charsets.US_ASCII);
        final String actualNonAscii = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(expectedNonAscii, actualNonAscii);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String expected = null;
        final String actual = StringUtils.newStringUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = { 97 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = { 98, 99 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = { 100, 101, 102 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = { 103, 104, 105, 106 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = { 107, 108, 109, 110, 111 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = { 112, 113, 114, 115, 116, 117, 118, 119, 120, 121 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = new byte[100];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (i + 1);
        }
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[0];
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() {
        final byte[] bytes = null;
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = null;
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final byte[] bytes = "Hello World".getBytes(StandardCharsets.UTF_8);
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[0];
        final String charsetName = "UTF-16BE";
        testNewString(charsetName); 
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Test case with empty input
        final byte[] emptyBytes = {};

        try {
            final String emptyInputActual = StringUtils.newStringUtf16Le(emptyBytes);
            Assert.fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Test passed
        }

        // Test case with null input
        final byte[] nullBytes = null;

        try {
            final String nullInputActual = StringUtils.newStringUtf16Le(nullBytes);
            Assert.fail("Expected a NullPointerException to be thrown");
        } catch (NullPointerException e) {
            // Test passed
        }
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // New regression test cases
        final byte[] emptyBytes = new byte[] {};
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
        
        final byte[] singleByte = new byte[] {65};
        Assert.assertEquals("A", StringUtils.newStringUtf8(singleByte));
        
        final byte[] multiBytes = new byte[] {-30, -122, -84, -30, -122, -94, -30, -122, -101};
        Assert.assertEquals("😄😊😍", StringUtils.newStringUtf8(multiBytes));
    }
}