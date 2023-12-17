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
        // Regression test case
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "def"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
        // Regression test case
        Assert.assertFalse(StringUtils.equals(new StringBuilder("def"), new StringBuilder("abc")));
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
        // Regression test case
        Assert.assertFalse(StringUtils.equals("def", "abc"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_modified(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE + "extra"); // Change input by appending "extra"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_modified(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE + "extra"); // Change input by appending "extra"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_modified(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE + "extra"); // Change input by appending "extra"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_modified(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE + "extra"); // Change input by appending "extra"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_modified(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE + "extra"); // Change input by appending "extra"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_modified(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE + "extra"); // Change input by appending "extra"
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test9() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    final String input = "";
    final byte[] expected = input.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1(input);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test10() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    final String input = "Hello World";
    final byte[] expected = input.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1(input);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test11() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    final String input = "12345";
    final byte[] expected = input.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1(input);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test12() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test13() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    Assert.assertNull(StringUtils.getBytesUtf16Le(null));
}
@Test
public void test14() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "   ".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("   ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test15() {
    try {
        StringUtils.getBytesIso8859_1(STRING_FIXTURE, null);
        Assert.fail("Expected " + NullPointerException.class.getName());
    } catch (final NullPointerException e) {
        // Expected
    }
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
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    Assert.assertNull(StringUtils.getBytesUtf16(null));
}
@Test
public void test18() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "   ".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("   ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test19() {
    try {
        StringUtils.getBytesUtf8(STRING_FIXTURE, null);
        Assert.fail("Expected " + NullPointerException.class.getName());
    } catch (final NullPointerException e) {
        // Expected
    }
}
@Test
public void test20() {
    final String charsetName = "UTF-8";
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUnchecked("", charsetName);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test21() {
    try {
        StringUtils.getBytesUnchecked(STRING_FIXTURE, null);
        Assert.fail("Expected " + NullPointerException.class.getName());
    } catch (final NullPointerException e) {
        // Expected
    }
}
@Test
public void test22() {
    try {
        StringUtils.getBytesUnchecked(STRING_FIXTURE, "");
        Assert.fail("Expected " + UnsupportedEncodingException.class.getName());
    } catch (final UnsupportedEncodingException e) {
        // Expected
    }
}
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abc$%^".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("abc$%^");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("HELLO");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HeLlO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("HeLlO");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_withEmptyString(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_withSpecialCharacters(charsetName);
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_withMultibyteCharacters(charsetName);
        final byte[] expected = "日本".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("日本");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_emptyString(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_multibyteCharacters(charsetName);
        final byte[] expected = "你好".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("你好");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_specialCharacters(charsetName);
        final byte[] expected = "@#$%^&".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("@#$%^&");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_nullString(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    private void testGetBytesUnchecked_emptyString(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUnchecked("", charsetName);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    private void testGetBytesUnchecked_multibyteCharacters(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = "你好".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUnchecked("你好", charsetName);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    private void testGetBytesUnchecked_specialCharacters(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = "@#$%^&".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUnchecked("@#$%^&", charsetName);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    private void testGetBytesUnchecked_nullString(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUnchecked(null, charsetName);
        Assert.assertEquals(expected, actual);
    }
@Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcdefghijklmnopqrstuvwxyz".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("abcdefghijklmnopqrstuvwxyz");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "©§∞".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("©§∞");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "éüâ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("éüâ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() {
        try {
            StringUtils.getBytesUnchecked("", "UTF-8");
        } catch (final IllegalStateException e) {
            Assert.fail("Not expected " + IllegalStateException.class.getName());
        }
    }
    @Test
    public void test45() {
        try {
            StringUtils.getBytesUnchecked("Test", "UTF-8");
        } catch (final IllegalStateException e) {
            Assert.fail("Not expected " + IllegalStateException.class.getName());
        }
    }
    @Test
    public void test46() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
        } catch (final IllegalStateException e) {
            Assert.fail("Not expected " + IllegalStateException.class.getName());
        }
    }
    @Test
    public void test47() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() {
        try {
            StringUtils.newString(new byte[0], "UTF-8");
        } catch (final IllegalStateException e) {
            Assert.fail("Not expected " + IllegalStateException.class.getName());
        }
    }
    @Test
    public void test49() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
        } catch (final IllegalStateException e) {
            Assert.fail("Not expected " + IllegalStateException.class.getName());
        }
    }
    @Test
    public void test50() {
        try {
            StringUtils.newString(null, "UTF-8");
        } catch (final IllegalStateException e) {
            Assert.fail("Not expected " + IllegalStateException.class.getName());
        }
    }
@Test
public void test51() {
    Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN")); // regression test
}
@Test
public void test52() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";

    // testNewString(charsetName); - original test removed

    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    
    Assert.assertEquals(expected, actual);
    Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE_16BE, charsetName)); // regression test
}
@Test
public void test53() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";

    // testNewString(charsetName); - original test removed

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
    
    Assert.assertEquals(expected, actual);
    Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName)); // regression test
}
@Test
public void test54() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";

    // testNewString(charsetName); - original test removed

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
    
    Assert.assertEquals(expected, actual);
    Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName)); // regression test
}
@Test
public void test55() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    Assert.assertNull(StringUtils.newStringUtf8(new byte[0])); // regression test
    Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0])); // regression test
    Assert.assertNull(StringUtils.newStringUsAscii(new byte[0])); // regression test
    Assert.assertNull(StringUtils.newStringUtf16(new byte[0])); // regression test
    Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0])); // regression test
    Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0])); // regression test
}
@Test
public void test56() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";

    // testNewString(charsetName); - original test removed

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
    
    Assert.assertEquals(expected, actual);
    Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName)); // regression test
}
@Test
public void test57() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";

    // testNewString(charsetName); - original test removed

    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
    
    Assert.assertEquals(expected, actual);
    Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName)); // regression test
}
@Test
public void test58() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";

    // testNewString(charsetName); - original test removed

    final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
    final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
    
    Assert.assertEquals(expected, actual);
    Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE_16LE, charsetName)); // regression test
}
@Test
public void test59() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
    try {
        StringUtils.newString(new byte[0], "UNKNOWN"); // regression test
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN"));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(new byte[] {0, 0, 65, 66}, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(new byte[] {0, 0, 65, 66});
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(new byte[] {65, 66}, charsetName);
        final String actual2 = StringUtils.newStringUtf8(new byte[] {65, 66});
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(new byte[] {65, 66}, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(new byte[] {65, 66});
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
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(new byte[] {65, 66}, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(new byte[] {65, 66});
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(new byte[] {0, 65, 0, 66}, charsetName);
        final String actual2 = StringUtils.newStringUtf16(new byte[] {0, 65, 0, 66});
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final String expected2 = new String(new byte[] {65, 0, 66, 0}, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(new byte[] {65, 0, 66, 0});
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
            StringUtils.newString(new byte[] {65, 66}, "UNKNOWN");
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
        
        // Regression Test Case 1: Passing an empty byte array as input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression Test Case 2: Passing a byte array with special characters as input
        final byte[] specialBytes = {(byte) 0xEA, (byte) 0x80, (byte) 0xB0}; // Represents the rupee sign symbol
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringIso8859_1(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test70() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression Test Case: Passing a null byte array as input
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        // Test with empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with byte array containing special characters
        final byte[] specialCharsBytes = new byte[] { 65, -30, 127, -10 };
        final String expectedSpecialChars = new String(specialCharsBytes, charsetName);
        final String actualSpecialChars = StringUtils.newStringUsAscii(specialCharsBytes);
        Assert.assertEquals(expectedSpecialChars, actualSpecialChars);
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression tests
        // Test with byte array containing null values
        final byte[] nullBytes = new byte[] { 65, 0, 127, 0 };
        final String actualNullBytes = StringUtils.newStringUsAscii(nullBytes);
        Assert.assertNull(actualNullBytes);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with only negative values
        final byte[] bytes2 = new byte[] {-1, -2, -3};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test74() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case: null byte array
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {0, 1, 2}));
    }
@Test
public void test75() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString_regression1(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE_regression1, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression1);
    Assert.assertEquals(expected, actual);
}
@Test
public void test76() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString_regression2(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE_regression2, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression2);
    Assert.assertEquals(expected, actual);
}
@Test
public void test77() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression1));
}
@Test
public void test78() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regression2));
}
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // New regression test cases
        byte[] emptyBytes = {};
        Assert.assertEquals("", StringUtils.newStringUtf16Le(emptyBytes));
        
        byte[] bytes = {0, 0, 0, 65};
        Assert.assertEquals("\u0000A", StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test80() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // New regression test cases
        byte[] bytes = {65, 66, 67};
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test81() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty input
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: Non-UTF-8 charset
        final String charsetName2 = "ISO-8859-1";
        final String expected2 = new String(BYTES_FIXTURE, charsetName2);
        final String actual2 = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertNotEquals(expected2, actual2);
    }
    @Test
    public void test82() {
        // Original test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case: Empty input
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
    }
}