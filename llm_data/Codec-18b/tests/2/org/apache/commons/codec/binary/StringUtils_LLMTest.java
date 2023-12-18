package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("abcd")));
    Assert.assertTrue(StringUtils.equals(new StringBuilder("abcd"), "abcd"));
    Assert.assertFalse(StringUtils.equals("abcde", new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcde"));
}
@Test
public void test1() {
    Assert.assertFalse(StringUtils.equals("abc", "abcd"));
    Assert.assertTrue(StringUtils.equals("abcd", "abcd"));
    Assert.assertFalse(StringUtils.equals("abcde", "abc"));
    Assert.assertFalse(StringUtils.equals("abc", "abcde"));
}
@Test
public void test2() {
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
    Assert.assertTrue(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abcd")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abcde"), new StringBuilder("abc")));
    Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcde")));
}
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String input = null;
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String input = "Hello world!";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final String input = "";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final String input = "Lorem ipsum";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String input = "12345";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String input = "abcdefghijklmnopqrstuvwxyz";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "é€%$".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("é€%$");
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
    public void test13() {
        try {
            StringUtils.getBytesUnchecked("hello", "UNKNOWN");
            StringUtils.getBytesUnchecked(null, "UNKNOWN");  // Regression test, passing null as string
            StringUtils.getBytesUnchecked("", "UNKNOWN");    // Regression test, passing empty string
            StringUtils.getBytesUnchecked("hello", "");      // Regression test, passing empty charsetName
            StringUtils.getBytesUnchecked("hello", null);    // Regression test, passing null as charsetName
            
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);     // Regression test, changing the string value
        final byte[] actual = StringUtils.getBytesIso8859_1("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() {
        Assert.assertNull(StringUtils.getBytesUnchecked("hello", "UNKNOWN"));      // Regression test, passing non-null string and charsetName
        Assert.assertNull(StringUtils.getBytesUnchecked(null, null));    // Regression test, passing null as string and charsetName
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);     // Regression test, changing the string value
        final byte[] actual = StringUtils.getBytesUtf16Le("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);     // Regression test, changing the string value
        final byte[] actual = StringUtils.getBytesUtf8("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);     // Regression test, changing the string value
        final byte[] actual = StringUtils.getBytesUsAscii("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);     // Regression test, changing the string value
        final byte[] actual = StringUtils.getBytesUtf16("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);     // Regression test, changing the string value
        final byte[] actual = StringUtils.getBytesUtf16Be("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
     
        // Regression test case 1: Empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: Single character string
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "a".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("a");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String string = "Hello, 你好, مرحبا";
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String nullString = null;
        final byte[] expected = nullString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(nullString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String emptyString = "";
        final byte[] expected = emptyString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(emptyString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String specialCharacters = "!@#$%^&*()";
        final byte[] expected = specialCharacters.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(specialCharacters);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String unicodeString = "\u0048\u0065\u006C\u006C\u006F";
        final byte[] expected = unicodeString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(unicodeString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String multilineString = "This is a\nmultiline\nstring";
        final byte[] expected = multilineString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(multilineString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String whitespaceString = "   \t";
        final byte[] expected = whitespaceString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(whitespaceString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "   ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("   ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()-_=+[]{};:'\"<>?/,.|\\`~".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("!@#$%^&*()-_=+[]{};:'\"<>?/,.|\\`~");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1
        final byte[] actual2 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals("".getBytes(charsetName), actual2));
        
        // Regression test case 2
        final byte[] actual3 = StringUtils.getBytesUtf8("Hello World");
        Assert.assertTrue(Arrays.equals("Hello World".getBytes(charsetName), actual3));
    }
    @Test
    public void test37() {
        try {
            StringUtils.getBytesUnchecked("test string", "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            byte[] bytes = { 65 };
            StringUtils.newString(bytes, "INVALID");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39(){ 
        // Change the input bytes to a different value 
        final byte[] bytes = new byte[]{0x00, 0x01, 0x02, 0x03};
    	final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40(){ 
        // Change the input bytes to a different value 
        final byte[] bytes = new byte[]{0x04, 0x05, 0x06, 0x07};
    	final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41(){ 
        // Change the input bytes to a different value 
        final byte[] bytes = new byte[]{0x08, 0x09, 0x0A, 0x0B};
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42(){ 
        // Change the input bytes to a different value 
        final byte[] bytes = new byte[]{0x0C, 0x0D, 0x0E, 0x0F};
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43(){ 
        // Change the input bytes to a different value 
        final byte[] bytes = new byte[]{0x10, 0x11, 0x12, 0x13};
        try {
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44(){ 
        // Change the input bytes to a different value 
        final byte[] bytes = new byte[]{0x14, 0x15, 0x16, 0x17};
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45(){ 
        // Change the input bytes to a different value 
        final byte[] bytes = new byte[]{0x18, 0x19, 0x1A, 0x1B};
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46(){ 
        // Change the input to null 
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
    }
    @Test
    public void test47(){ 
        // Change the input to null 
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
    }
    @Test
    public void test48(){ 
        // Change the input to null 
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
    }
    @Test
    public void test49(){ 
        // Change the input to null 
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
    }
    @Test
    public void test50(){ 
        // Change the input to null 
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
    }
    @Test
    public void test51(){ 
        // Change the input to null 
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] bytes = null; // change input value
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName); // change input value
        final String actual = StringUtils.newStringUtf16(bytes); // change input value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        byte[] bytes = new byte[] {(byte) 0xC4, (byte) 0xC5}; // change input value
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName); // change input value
        final String actual = StringUtils.newStringUtf8(bytes); // change input value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = new byte[] {(byte) 70, (byte) 71, (byte) 72}; // change input value
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName); // change input value
        final String actual = StringUtils.newStringUsAscii(bytes); // change input value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = new byte[] {(byte) 100, (byte) 101, (byte) 102}; // change input value
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName); // change input value
        final String actual = StringUtils.newStringIso8859_1(bytes); // change input value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() {
        byte[] bytes = new byte[] {(byte) 100, (byte) 101, (byte) 102}; // change input value
        try {
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = new byte[] {(byte) 65, (byte) 66, (byte) 67}; // change input value
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName); // change input value
        final String actual = StringUtils.newStringUtf16Be(bytes); // change input value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = new byte[] {(byte) 65, (byte) 66, (byte) 67}; // change input value
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName); // change input value
        final String actual = StringUtils.newStringUtf16Le(bytes); // change input value
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        byte[] bytes = new byte[] {(byte) 65, (byte) 66, (byte) 67}; // change input value
        Assert.assertNull(StringUtils.newStringUtf8(bytes)); // change input value
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes)); // change input value
        Assert.assertNull(StringUtils.newStringUsAscii(bytes)); // change input value
        Assert.assertNull(StringUtils.newStringUtf16(bytes)); // change input value
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes)); // change input value
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes)); // change input value
    }
    @Test
    public void test60() {
        byte[] bytes = null; // change input value
        Assert.assertNull(StringUtils.newString(bytes, "UNKNOWN")); // change input value
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] {});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] { 65, 66, 67 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] { -1, -2, -3 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty byte array input
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: Byte array with special characters
        final byte[] bytes2 = {-128, -127, 0, 127};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
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
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {})); // Empty byte array
        
        // Regression test case 2
        Assert.assertEquals("", StringUtils.newStringUtf16(new byte[] {0, 0})); // Byte array with two null bytes
        
        // Regression test case 3
        Assert.assertEquals("A", StringUtils.newStringUtf16(new byte[] {0, 65})); // Byte array with one null byte and "A" character
        
        // Regression test case 4
        Assert.assertEquals("AB", StringUtils.newStringUtf16(new byte[] {0, 65, 0, 66})); // Byte array with null bytes and "A", "B" characters
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression tests
        
        // Test with empty byte array
        final String emptyBytesExpected = new String(new byte[0], charsetName);
        final String emptyBytesActual = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(emptyBytesExpected, emptyBytesActual);
        
        // Test with byte array containing 3 bytes
        final byte[] threeBytes = {(byte) 0x41, (byte) 0x42, (byte) 0x43};
        final String threeBytesExpected = new String(threeBytes, charsetName);
        final String threeBytesActual = StringUtils.newStringUtf16Be(threeBytes);
        Assert.assertEquals(threeBytesExpected, threeBytesActual);
    }
    @Test
    public void test68() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression tests
        
        // Test with null input
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        
        // Test with blank input
        final byte[] blankInput = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf16Be(blankInput));
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final byte[] mutatedBytes = {65, 66, 67};
        final String expected = new String(mutatedBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(mutatedBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        final byte[] mutatedBytes = {65, 66, 67};
        Assert.assertNull(StringUtils.newStringUtf16Le(mutatedBytes));
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);

        // Regression test cases
        final byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));

        final byte[] specialCharBytes = {(byte) 0xC3, (byte) 0xA9}; // é in UTF-8 encoding
        Assert.assertEquals("é", StringUtils.newStringUtf8(specialCharBytes));

        final byte[] invalidBytes = {(byte) 0xC3, (byte) 0x28}; // Invalid UTF-8 encoding
        try {
            StringUtils.newStringUtf8(invalidBytes);
            Assert.fail("Exception should be thrown for invalid input");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(null));

        // Regression test cases
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}