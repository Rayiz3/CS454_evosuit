package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
        // Regression test to kill mutants for null check
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertTrue(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(null, null));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
        // Regression test to kill mutants for null check
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, null));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
        // Regression test to kill mutants for null check
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", null));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
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
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String differentCharset = "ISO-8859-1";
        final byte[] expected = STRING_FIXTURE.getBytes(differentCharset);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final String differentCharset = "UTF-8";
        final byte[] expected = STRING_FIXTURE.getBytes(differentCharset);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final String differentCharset = "UTF-8";
        final byte[] expected = STRING_FIXTURE.getBytes(differentCharset);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String differentCharset = "ISO-8859-1";
        final byte[] expected = STRING_FIXTURE.getBytes(differentCharset);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String differentCharset = "US-ASCII";
        final byte[] expected = STRING_FIXTURE.getBytes(differentCharset);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final String differentCharset = "ISO-8859-1";
        final byte[] expected = STRING_FIXTURE.getBytes(differentCharset);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test30() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(null, charsetName);
    final byte[] expected = null;
    final byte[] actual = StringUtils.getBytesUtf16Be(null);
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test31() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked("", charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("");
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test32() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked("   ", charsetName);
    final byte[] expected = "   ".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("   ");
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test33() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked("12345", charsetName);
    final byte[] expected = "12345".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUsAscii("12345");
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test34() {
    Assert.assertNull(StringUtils.getBytesUnchecked(null, "UTF-16BE"));
}
@Test
public void test35() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked("special!@#$", charsetName);
    final byte[] expected = "special!@#$".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16("special!@#$");
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test36() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked("nullCharset", charsetName);
    final byte[] expected = "nullCharset".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("nullCharset");
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test37() {
    try {
        StringUtils.getBytesUnchecked("", "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "A".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("A");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello! @#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello! @#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "  Hello World  ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("  Hello World  ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Original test case
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        final String emptyString = "";
        final byte[] emptyStringBytes = emptyString.getBytes(charsetName);
        final byte[] actualEmptyStringBytes = StringUtils.getBytesUtf8(emptyString);
        Assert.assertTrue(Arrays.equals(emptyStringBytes, actualEmptyStringBytes));
        
        final String nonAsciiString = "안녕하세요";
        final byte[] nonAsciiStringBytes = nonAsciiString.getBytes(charsetName);
        final byte[] actualNonAsciiStringBytes = StringUtils.getBytesUtf8(nonAsciiString);
        Assert.assertTrue(Arrays.equals(nonAsciiStringBytes, actualNonAsciiStringBytes));
    }
    @Test
    public void test55() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test57() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
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
            StringUtils.newString(BYTES_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test60() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test61() {
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test62() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
    @Test
    public void test66() {
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test67() {
        final String charsetName = "ISO-8859-1";
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test68() {
        final String charsetName = "ISO-8859-1";
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test69() {
        final String charsetName = "ISO-8859-1";
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test70() {
        final String charsetName = "UTF-8";
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test71() {
        final String charsetName = "UTF-8";
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test72() {
        final String charsetName = "ISO-8859-1";
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test73() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN"));
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        final String charsetName2 = "UTF-8";
        final String expected2 = new String(BYTES_FIXTURE_16BE, charsetName2);
        final String actual2 = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        final String charsetName2 = "UTF-16BE";
        final String expected2 = new String(BYTES_FIXTURE, charsetName2);
        final String actual2 = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        final String charsetName2 = "ISO-8859-1";
        final String expected2 = new String(BYTES_FIXTURE, charsetName2);
        final String actual2 = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test77() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        final String charsetName2 = "UTF-8";
        final String expected2 = new String(BYTES_FIXTURE, charsetName2);
        final String actual2 = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        final String charsetName2 = "UTF-8";
        final String expected2 = new String(BYTES_FIXTURE, charsetName2);
        final String actual2 = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test80() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        final String charsetName2 = "UTF-8";
        final String expected2 = new String(BYTES_FIXTURE_16LE, charsetName2);
        final String actual2 = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test81() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }

        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test82() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        // Test with empty byte array
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));
        
        // Test with one byte
        byte[] singleByte = {65};
        Assert.assertEquals("A", StringUtils.newStringIso8859_1(singleByte));
        
        // Test with special characters
        byte[] specialBytes = {(byte) 136, (byte) 209};
        Assert.assertEquals("Ñ", StringUtils.newStringIso8859_1(specialBytes));
    }
    @Test
    public void test83() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case with empty byte array
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Additional test case with non-ascii characters
        final byte[] nonAsciiBytes = { -64, -88, -61, -79 }; // "āžčū" in UTF-8
        final String expectedNonAscii = new String(nonAsciiBytes, charsetName);
        final String actualNonAscii = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(expectedNonAscii, actualNonAscii);
    }
    @Test
    public void test84() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Additional test cases with empty byte arrays
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[]{}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[]{}));
    }
    @Test
    public void test85() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = new byte[0];
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test86() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = {65, 66, 67, -61, -87, 67}; // ABCÿC
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test87() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] bytes = {0, 1, 2, 3, 4, 5};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test88() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        
        // Change input value to new byte array that has different length than BYTES_FIXTURE_16BE
        final byte[] differentInput = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05 };
        final String expected1 = new String(differentInput, charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(differentInput);
        Assert.assertEquals(expected1, actual1);
        
        // Change input value to new byte array that has the same length as BYTES_FIXTURE_16BE but different values
        final byte[] differentInput2 = new byte[] { 0x06, 0x07, 0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D };
        final String expected2 = new String(differentInput2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(differentInput2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test89() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
 
        // Pass a non-null byte array as input
        final byte[] nonNullInput = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf16Le(nonNullInput));
    }
    @Test
    public void test90() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty input
        final byte[] emptyInput = new byte[]{};
        final String expectedEmpty = new String(emptyInput, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(emptyInput);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test 2: Input with one byte
        final byte[] oneByteInput = new byte[]{ 0x41 };
        final String expectedOneByte = new String(oneByteInput, charsetName);
        final String actualOneByte = StringUtils.newStringUtf16Le(oneByteInput);
        Assert.assertEquals(expectedOneByte, actualOneByte);
        
        // Regression test 3: Input with all zeroes
        final byte[] zeroInput = new byte[]{ 0x00, 0x00, 0x00, 0x00 };
        final String expectedZero = new String(zeroInput, charsetName);
        final String actualZero = StringUtils.newStringUtf16Le(zeroInput);
        Assert.assertEquals(expectedZero, actualZero);
    }
    @Test
    public void test91() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: Null input with one byte
        final byte[] oneByteInput = new byte[]{ 0x41 };
        final String actualOneByte = StringUtils.newStringUtf16Le(oneByteInput);
        Assert.assertNotNull(actualOneByte);
        
        // Regression test 2: Null input with multiple bytes
        final byte[] multiByteInput = new byte[]{ 0x41, 0x42, 0x43 };
        final String actualMultiByte = StringUtils.newStringUtf16Le(multiByteInput);
        Assert.assertNotNull(actualMultiByte);
    }
    @Test
    public void test92() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final byte[] bytes = "test".getBytes(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test93() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        final byte[] bytes = null;
        final String expected = new String(bytes);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertNull(actual);
        Assert.assertEquals(expected, actual);
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}