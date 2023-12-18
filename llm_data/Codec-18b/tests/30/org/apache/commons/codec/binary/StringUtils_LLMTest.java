package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals(new StringBuilder(), "abc"));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals("", null));
        Assert.assertFalse(StringUtils.equals(null, ""));
        Assert.assertTrue(StringUtils.equals(new StringBuilder(), new StringBuilder()));
        Assert.assertFalse(StringUtils.equals(new StringBuilder(), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder()));
        Assert.assertFalse(StringUtils.equals(new StringBuilder(), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals("", ""));
        Assert.assertFalse(StringUtils.equals("abc", ""));
        Assert.assertFalse(StringUtils.equals("", "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
    }
@Test
public void test3() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    
    // Change input value to null
    final byte[] actualNull = StringUtils.getBytesUtf16Be(null);
    Assert.assertNull(actualNull);
    
    // Change input value to empty string
    final byte[] actualEmpty = StringUtils.getBytesUtf16Be("");
    Assert.assertArrayEquals(expected, actualEmpty);
    
    // Change input value with special characters
    final byte[] actualSpecialChars = StringUtils.getBytesUtf16Be("Hello @~&^%$#");
    Assert.assertArrayEquals(expected, actualSpecialChars);
}
@Test
public void test4() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    
    // Change input value to null
    final byte[] actualNull = StringUtils.getBytesIso8859_1(null);
    Assert.assertNull(actualNull);
    
    // Change input value to empty string
    final byte[] actualEmpty = StringUtils.getBytesIso8859_1("");
    Assert.assertArrayEquals(expected, actualEmpty);
    
    // Change input value with special characters
    final byte[] actualSpecialChars = StringUtils.getBytesIso8859_1("Hello @~&^%$#");
    Assert.assertArrayEquals(expected, actualSpecialChars);
}
@Test
public void test5() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    
    // Change input value to null
    final byte[] actualNull = StringUtils.getBytesUsAscii(null);
    Assert.assertNull(actualNull);
    
    // Change input value to empty string
    final byte[] actualEmpty = StringUtils.getBytesUsAscii("");
    Assert.assertArrayEquals(expected, actualEmpty);
    
    // Change input value with special characters
    final byte[] actualSpecialChars = StringUtils.getBytesUsAscii("Hello @~&^%$#");
    Assert.assertArrayEquals(expected, actualSpecialChars);
}
@Test
public void test6() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    
    // Change input value to null
    final byte[] actualNull = StringUtils.getBytesUtf8(null);
    Assert.assertNull(actualNull);
    
    // Change input value to empty string
    final byte[] actualEmpty = StringUtils.getBytesUtf8("");
    Assert.assertArrayEquals(expected, actualEmpty);
    
    // Change input value with special characters
    final byte[] actualSpecialChars = StringUtils.getBytesUtf8("Hello @~&^%$#");
    Assert.assertArrayEquals(expected, actualSpecialChars);
}
@Test
public void test7() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    
    // Change input value to null
    final byte[] actualNull = StringUtils.getBytesUtf16Le(null);
    Assert.assertNull(actualNull);
    
    // Change input value to empty string
    final byte[] actualEmpty = StringUtils.getBytesUtf16Le("");
    Assert.assertArrayEquals(expected, actualEmpty);
    
    // Change input value with special characters
    final byte[] actualSpecialChars = StringUtils.getBytesUtf16Le("Hello @~&^%$#");
    Assert.assertArrayEquals(expected, actualSpecialChars);
}
@Test
public void test8() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    
    // Change input value to null
    final byte[] actualNull = StringUtils.getBytesUtf16(null);
    Assert.assertNull(actualNull);
    
    // Change input value to empty string
    final byte[] actualEmpty = StringUtils.getBytesUtf16("");
    Assert.assertArrayEquals(expected, actualEmpty);
    
    // Change input value with special characters
    final byte[] actualSpecialChars = StringUtils.getBytesUtf16("Hello @~&^%$#");
    Assert.assertArrayEquals(expected, actualSpecialChars);
}
    @Test
    public void test9() {
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test10() {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "!@#$%^&*()";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "12345";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String string = "abcdefghijklmnopqrstuvwxyz";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(string);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Special Characters: @#$%".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Special Characters: @#$%");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "abcdefghijklmnopqrstuvwxyz1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("abcdefghijklmnopqrstuvwxyz1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertFalse(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String string = "!@#$%^&*()_+=-{}[]|\\:\";'<>?,./~`";
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
    public void test23(){
        final String input = "";
        final byte[] expected = input.getBytes(Charsets.UTF_16);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test24(){
        final String input = "Hello!@#$%^&*()";
        final byte[] expected = input.getBytes(Charsets.UTF_16);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test25(){
        final String input = "\uD83D\uDE00\uD83D\uDE03\uD83D\uDE04";
        final byte[] expected = input.getBytes(Charsets.UTF_16);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%^&*".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("@#$%^&*");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "你好世界".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("你好世界");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "    ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("    ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(BYTES_FIXTURE, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(new byte[] {}, "UNKNOWN"));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[] {}, charsetName); // additional test case
        final String actualEmpty = StringUtils.newStringUtf16Be(new byte[] {}); // additional test case
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[] {}, charsetName); // additional test case
        final String actualEmpty = StringUtils.newStringUtf8(new byte[] {}); // additional test case
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[] {}, charsetName); // additional test case
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[] {}); // additional test case
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {})); // additional test case
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {})); // additional test case
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {})); // additional test case
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {})); // additional test case
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {})); // additional test case
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {})); // additional test case
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[] {}, charsetName); // additional test case
        final String actualEmpty = StringUtils.newStringIso8859_1(new byte[] {}); // additional test case
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[] {}, charsetName); // additional test case
        final String actualEmpty = StringUtils.newStringUtf16(new byte[] {}); // additional test case
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        final String expectedEmpty = new String(new byte[] {}, charsetName); // additional test case
        final String actualEmpty = StringUtils.newStringUtf16Le(new byte[] {}); // additional test case
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test49() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.newString(new byte[] {}, "UNKNOWN"); // additional test case
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() {
        // regression test: passing empty byte array
        Assert.assertNull(StringUtils.newString(new byte[0], "UNKNOWN"));
        // regression test: passing byte array with null element
        Assert.assertNull(StringUtils.newString(new byte[]{65, 66, 67, 0}, "UNKNOWN"));
        // regression test: passing byte array with negative elements
        Assert.assertNull(StringUtils.newString(new byte[]{65, -1, 67}, "UNKNOWN"));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        
        // regression test: passing additional byte array with a different encoding
        final String expected = new String(BYTES_FIXTURE_16BE_ISO, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_ISO);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        
        // regression test: passing additional byte array with a different encoding
        final String expected = new String(BYTES_FIXTURE_ISO, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_ISO);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        
        // regression test: passing additional byte array with a different encoding
        final String expected = new String(BYTES_FIXTURE_ISO, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_ISO);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        
        // regression test: passing additional byte array with a different encoding
        final String expected = new String(BYTES_FIXTURE_ISO, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_ISO);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        
        // regression test: passing additional byte array with a different encoding
        final String expected = new String(BYTES_FIXTURE_ISO, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_ISO);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        
        // regression test: passing additional byte array with a different encoding
        byte[] bytes = Arrays.copyOf(BYTES_FIXTURE_16LE_UTF8, BYTES_FIXTURE_16LE_UTF8.length);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
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
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(REVERSE_BYTES_FIXTURE); // Reverse the input bytes
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(REVERSE_BYTES_FIXTURE)); // Reverse the input bytes
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        
        // Existing test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        final byte[] bytesWithNull = new byte[]{65, 66, 67, 0};
        final String expectedNull = new String(bytesWithNull, charsetName);
        final String actualNull = StringUtils.newStringUsAscii(bytesWithNull);
        Assert.assertEquals(expectedNull, actualNull);
        
        final byte[] bytesWithNonAscii = new byte[]{65, 66, 67, -100};
        final String expectedNonAscii = new String(bytesWithNonAscii, charsetName);
        final String actualNonAscii = StringUtils.newStringUsAscii(bytesWithNonAscii);
        Assert.assertEquals(expectedNonAscii, actualNonAscii);
    }
    @Test
    public void test62() {
        // Existing test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test cases
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{65, 66, 67, 0}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{65, 66, 67, -100}));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // additional regression tests
        
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16(emptyBytes));
        
        byte[] singleByte = new byte[]{65, 0};
        Assert.assertEquals("A", StringUtils.newStringUtf16(singleByte));
        
        byte[] multipleBytes = new byte[]{65, 0, 66, 0, 67, 0};
        Assert.assertEquals("ABC", StringUtils.newStringUtf16(multipleBytes));
        
        byte[] invalidBytes = new byte[]{65};
        try {
            StringUtils.newStringUtf16(invalidBytes);
            Assert.fail("Expected UnsupportedEncodingException");
        } catch (UnsupportedEncodingException e){
            // expected
        }
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // additional regression tests
        
        byte[] emptyBytes = null;
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
        
        byte[] singleByte = null;
        Assert.assertNull(StringUtils.newStringUtf16(singleByte));
        
        byte[] multipleBytes = null;
        Assert.assertNull(StringUtils.newStringUtf16(multipleBytes));
        
        byte[] invalidBytes = null;
        Assert.assertNull(StringUtils.newStringUtf16(invalidBytes));
    }
    @Test
    public void test65() {
        byte[] bytes = {0x41, 0x00, 0x42, 0x00, 0x43, 0x00};
        String expected = "A�B�C�";
        String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() {
        byte[] bytes = {0x00, 0x61, 0x00, 0x62, 0x00, 0x63};
        String expected = "abc";
        String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() {
        byte[] bytes = {-1, -2, 0x00, 0x61, 0x00, 0x62};
        String expected = "ab";
        String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        // Test with empty input
        byte[] emptyBytes = new byte[0];
        expected = new String(emptyBytes, charsetName);
        actual = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expected, actual);
        
        // Test with one byte
        byte[] singleByte = new byte[]{ 65 };
        expected = new String(singleByte, charsetName);
        actual = StringUtils.newStringUtf16Le(singleByte);
        Assert.assertEquals(expected, actual);
        
        // Test with larger byte array
        byte[] largerBytes = new byte[] { 65, 66, 67 };
        expected = new String(largerBytes, charsetName);
        actual = StringUtils.newStringUtf16Le(largerBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}