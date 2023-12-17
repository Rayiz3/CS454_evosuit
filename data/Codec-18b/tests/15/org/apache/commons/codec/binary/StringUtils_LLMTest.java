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

        // Regression test cases
        Assert.assertTrue(StringUtils.equals("", new StringBuilder("")));
        Assert.assertTrue(StringUtils.equals("abcd", new StringBuilder("abcd")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));

        // Regression test cases
        Assert.assertFalse(StringUtils.equals(new StringBuilder(""), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), null));
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

        // Regression test cases
        Assert.assertFalse(StringUtils.equals("", null));
        Assert.assertFalse(StringUtils.equals(null, ""));
        Assert.assertFalse(StringUtils.equals("abcd", null));
        Assert.assertTrue(StringUtils.equals("", ""));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = null;
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String input = "";
        final byte[] expected = new byte[]{};
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String input = "   ";
        final byte[] expected = new byte[]{32, 32, 32};
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final String input = "$#@!";
        final byte[] expected = new byte[]{36, 35, 64, 33};
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String input = "12345";
        final byte[] expected = new byte[]{49, 0, 50, 0, 51, 0, 52, 0, 53, 0};
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String input = "abc123";
        final byte[] expected = new byte[]{97, 0, 98, 0, 99, 0, 49, 0, 50, 0, 51, 0};
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "abc æøå";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "1234567890";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test12() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(null, charsetName);
}
@Test
public void test13() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked("", charsetName);
}
@Test
public void test14() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(null, charsetName);
}
@Test
public void test15() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked("", charsetName);
}
@Test
public void test16() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(null, charsetName);
}
@Test
public void test17() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked("", charsetName);
}
@Test
public void test18() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked(null, charsetName);
}
@Test
public void test19() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked("", charsetName);
}
@Test
public void test20() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(null, charsetName);
}
@Test
public void test21() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked("", charsetName);
}
@Test
public void test22() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(null, charsetName);
}
@Test
public void test23() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked("", charsetName);
}
@Test
public void test24() {
    Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
}
@Test
public void test25() {
    Assert.assertNull(StringUtils.getBytesUnchecked("", "UNKNOWN"));
}
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HeLLo".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("HeLLo");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "?!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("?!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "  \t\n".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("  \t\n");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "äàéêñ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("äàéêñ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@!#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("@!#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = " ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(" ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("HELLO");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName); // Test with different input
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "World".getBytes(charsetName); // Test with different input
        final byte[] actual = StringUtils.getBytesUtf16Be("World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final String string = "~!@#$%^&*()_-+={}[]\\|;:'\",.<>/?`";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test45() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test46() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final String input = "Hello World! 🌍";
    final byte[] expected = input.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8(input);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test47() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = null;
    final byte[] actual = StringUtils.getBytesUtf8(null);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test48() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8"); // Change the charsetName to a valid value
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test49() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8"); // Change the charsetName to a valid value
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1"); // Change the charsetName to a valid value
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1"); // Change the charsetName to a valid value
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() {
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
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
    public void test59() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[0], "UTF-16BE"));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String("Hello", charsetName);
        final String actual2 = StringUtils.newStringUtf16Be("Hello".getBytes(charsetName));
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String("Hello", charsetName);
        final String actual2 = StringUtils.newStringUtf8("Hello".getBytes(charsetName));
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String("Hello", charsetName);
        final String actual2 = StringUtils.newStringUsAscii("Hello".getBytes(charsetName));
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newString(null, "UTF-16BE"));
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String("Hello", charsetName);
        final String actual2 = StringUtils.newStringIso8859_1("Hello".getBytes(charsetName));
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String("Hello", charsetName);
        final String actual2 = StringUtils.newStringUtf16("Hello".getBytes(charsetName));
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String("Hello", charsetName);
        final String actual2 = StringUtils.newStringUtf16Le("Hello".getBytes(charsetName));
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test68() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString("Hello".getBytes("UTF-16BE"), "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test cases
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringIso8859_1(emptyBytes));

        byte[] additionalBytes = new byte[]{65, 66, 67};
        Assert.assertEquals("ABC", StringUtils.newStringIso8859_1(additionalBytes));

        byte[] specialBytes = new byte[]{32, 33, 34};
        Assert.assertEquals(" !\"", StringUtils.newStringIso8859_1(specialBytes));

        byte[] nullBytes = null;
        Assert.assertNull(StringUtils.newStringIso8859_1(nullBytes));
    }
    @Test
    public void test70() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test case
        byte[] nonNullBytes = new byte[]{68, 69, 70};
        Assert.assertNull(StringUtils.newStringIso8859_1(nonNullBytes));
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        final byte[] emptyBytes = {};
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);

        final byte[] nonAsciiBytes = "test".getBytes(StandardCharsets.UTF_8);
        final String nonAsciiExpected = new String(nonAsciiBytes, charsetName);
        final String nonAsciiActual = StringUtils.newStringUsAscii(nonAsciiBytes);
        Assert.assertEquals(nonAsciiExpected, nonAsciiActual);
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test
        final byte[] emptyBytes = {};
        Assert.assertNull(StringUtils.newStringUsAscii(emptyBytes));
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Extra input
        final String extraInput = "extra";
        final byte[] bytesWithExtra = new byte[BYTES_FIXTURE.length + extraInput.getBytes().length];
        System.arraycopy(BYTES_FIXTURE, 0, bytesWithExtra, 0, BYTES_FIXTURE.length);
        System.arraycopy(extraInput.getBytes(), 0, bytesWithExtra, BYTES_FIXTURE.length, extraInput.getBytes().length);
        final String expectedWithExtra = new String(bytesWithExtra, charsetName);
        final String actualWithExtra = StringUtils.newStringUtf16(bytesWithExtra);
        Assert.assertEquals(expectedWithExtra, actualWithExtra);
    }
    @Test
    public void test74() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Extra input
        final byte[] extraBytes = "extra".getBytes();
        final byte[] bytesWithExtra = new byte[extraBytes.length];
        System.arraycopy(extraBytes, 0, bytesWithExtra, 0, extraBytes.length);
        Assert.assertNull(StringUtils.newStringUtf16(bytesWithExtra));
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        
        // Regression test 1: empty input
        final String emptyInput = "";
        final String expected1 = new String(emptyInput.getBytes(), charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(emptyInput.getBytes());
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: input with special characters
        final String specialCharsInput = "Hello #$%^ World";
        final String expected2 = new String(specialCharsInput.getBytes(), charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(specialCharsInput.getBytes());
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test76() {
        Assert.assertNull(StringUtils.newStringUtf8ChangedInput(null));
        Assert.assertNull(StringUtils.newStringIso8859_1ChangedInput(null));
        Assert.assertNull(StringUtils.newStringUsAsciiChangedInput(null));
        Assert.assertNull(StringUtils.newStringUtf16ChangedInput(null));
        Assert.assertNull(StringUtils.newStringUtf16BeChangedInput(null));
        Assert.assertNull(StringUtils.newStringUtf16LeChangedInput(null));
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test with empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test with null byte array
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test with large byte array
        final byte[] largeBytes = new byte[10000];
        Arrays.fill(largeBytes, (byte) 7);
        final String expectedLarge = new String(largeBytes, charsetName);
        final String actualLarge = StringUtils.newStringUtf16Le(largeBytes);
        Assert.assertEquals(expectedLarge, actualLarge);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = "Hello, World!";
        final String actual = StringUtils.newStringUtf8(expected.getBytes(charsetName));
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test79() {
        Assert.assertNotNull(StringUtils.newStringUtf8("Hello".getBytes()));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}