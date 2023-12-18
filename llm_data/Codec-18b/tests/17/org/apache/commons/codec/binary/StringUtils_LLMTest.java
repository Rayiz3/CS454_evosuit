package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        
        // Regression test: Changing the input from "abcd" to "abcd "
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd "));
        
        Assert.assertFalse(StringUtils.equals("abcd ", new StringBuilder("abc")));
        
        // Regression test: Changing the input from "ABC" to "ABC "
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC "));
    }
    @Test
    public void test1() {
        // Regression test: Changing the input from null to new StringBuilder("abc ")
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc "), null));
        
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        
        // Regression test: Changing the input from new StringBuilder("abc") to new StringBuilder("abc ")
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc "), new StringBuilder("abc")));
        
        // Regression test: Changing the input from new StringBuilder("abc") to new StringBuilder("abcd ")
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd ")));
        
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd "), new StringBuilder("abc")));
        
        // Regression test: Changing the input from new StringBuilder("abc") to new StringBuilder("ABC ")
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC ")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals(null, null));
        
        // Regression test: Changing the input from "abc" to "abc "
        Assert.assertFalse(StringUtils.equals("abc ", null));
        
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        
        // Regression test: Changing the input from "abc" to "abc "
        Assert.assertTrue(StringUtils.equals("abc ", "abc"));
        
        // Regression test: Changing the input from "abc" to "abcd "
        Assert.assertFalse(StringUtils.equals("abc", "abcd "));
        
        Assert.assertFalse(StringUtils.equals("abcd ", "abc"));
        
        // Regression test: Changing the input from "abc" to "ABC "
        Assert.assertFalse(StringUtils.equals("abc", "ABC "));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("new input value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("new input value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("new input value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("new input value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("new input value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("new input value");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        testGetBytesUnchecked("UTF-8");
        final byte[] expected = "Hello".getBytes("UTF-8");
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        testGetBytesUnchecked("UTF-16");
        final byte[] expected = "World".getBytes("UTF-16");
        final byte[] actual = StringUtils.getBytesIso8859_1("World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Variant".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Variant");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Variant".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Variant");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Variant".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Variant");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Variant".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Variant");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Variant".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Variant");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Variant".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Variant");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE + "extra");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "A".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("A");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "你好".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("你好");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("hello");
        Assert.assertFalse(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertFalse(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Test with an empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test with a string containing special characters
        final byte[] expected2 = "!@#$%^&*()_+{}:\"<>?".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("!@#$%^&*()_+{}:\"<>?");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test with a string containing non-English characters
        final byte[] expected3 = "こんにちは".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("こんにちは");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "漢字".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("漢字");
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
    }
    @Test
    public void test39() {
        try {
            StringUtils.getBytesUnchecked("", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.getBytesUnchecked(null, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() {
        try {
            StringUtils.newString(new byte[0], "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test43() {
        try {
            StringUtils.newString(null, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNotNull(StringUtils.newString(new byte[]{0x41, 0x42, 0x43}, "UNKNOWN"));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(StringUtils.newStringUtf16Be(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(StringUtils.newStringUtf8(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(StringUtils.newStringUsAscii(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNotNull(StringUtils.newStringUtf8(new byte[]{0x41, 0x42, 0x43}));
        Assert.assertNotNull(StringUtils.newStringIso8859_1(new byte[]{0x41, 0x42, 0x43}));
        Assert.assertNotNull(StringUtils.newStringUsAscii(new byte[]{0x41, 0x42, 0x43}));
        Assert.assertNotNull(StringUtils.newStringUtf16(new byte[]{0x41, 0x42, 0x43}));
        Assert.assertNotNull(StringUtils.newStringUtf16Be(new byte[]{0x41, 0x42, 0x43}));
        Assert.assertNotNull(StringUtils.newStringUtf16Le(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(StringUtils.newStringIso8859_1(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(StringUtils.newStringUtf16(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        Assert.assertNotNull(StringUtils.newStringUtf16Le(new byte[]{0x41, 0x42, 0x43}));
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString(new byte[]{0x41, 0x42, 0x43}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN")); // regression test with empty byte array
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        Assert.assertThrows(CharacterCodingException.class, () -> StringUtils.newString(BYTES_FIXTURE_16BE, "UTF-8")); // regression test with wrong charset
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertThrows(CharacterCodingException.class, () -> StringUtils.newString(BYTES_FIXTURE, "US-ASCII")); // regression test with wrong charset
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertThrows(CharacterCodingException.class, () -> StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1")); // regression test with wrong charset
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertThrows(IllegalArgumentException.class, () -> StringUtils.newString(BYTES_FIXTURE, null)); // regression test with null charset
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertThrows(CharacterCodingException.class, () -> StringUtils.newString(BYTES_FIXTURE, "UTF-16")); // regression test with wrong charset
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        Assert.assertThrows(CharacterCodingException.class, () -> StringUtils.newString(BYTES_FIXTURE, "UTF-16LE")); // regression test with wrong charset
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        Assert.assertThrows(CharacterCodingException.class, () -> StringUtils.newString(BYTES_FIXTURE_16LE, "UTF-16BE")); // regression test with wrong charset
    }
    @Test
    public void test61() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString(BYTES_FIXTURE, "ASCII"); // regression test with different wrong charset
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_DIFFERENT_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(BYTES_FIXTURE));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{(byte) 0x80, (byte) 0xFF});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: passing an empty byte array
        final String emptyBytesExpected = new String(new byte[0], charsetName);
        final String emptyBytesActual = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(emptyBytesExpected, emptyBytesActual);

        // Regression test 2: passing a byte array with a single byte
        final String singleByteExpected = new String(new byte[] { (byte) 65 }, charsetName);
        final String singleByteActual = StringUtils.newStringUtf16(new byte[] { (byte) 65 });
        Assert.assertEquals(singleByteExpected, singleByteActual);
    }
    @Test
    public void test67() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: passing a non-null byte array
        final byte[] nonNullBytes = new byte[] { (byte) 65, (byte) 66, (byte) 67 };
        Assert.assertNull(StringUtils.newStringUtf16(nonNullBytes));
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        // Original test case
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        final String charsetName2 = "ISO-8859-1";
        testNewString(charsetName2);
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName2);
        final String actual2 = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected2, actual2);

        // Regression test case 2
        final String charsetName3 = "UTF-8";
        testNewString(charsetName3);
        final String expected3 = new String(BYTES_FIXTURE_16LE, charsetName3);
        final String actual3 = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test71() {
        // Original test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case 1
        Assert.assertNull(StringUtils.newStringUtf8(BYTES_FIXTURE_16LE));
        Assert.assertNull(StringUtils.newStringIso8859_1(BYTES_FIXTURE_16LE));
        Assert.assertNull(StringUtils.newStringUsAscii(BYTES_FIXTURE_16LE));
        Assert.assertNull(StringUtils.newStringUtf16(BYTES_FIXTURE_16LE));
        Assert.assertNull(StringUtils.newStringUtf16Be(BYTES_FIXTURE_16LE));
        Assert.assertNull(StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE));
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{65, 66, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test75() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test76() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{65, 66, 67}));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}