package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals("", ""));
        Assert.assertFalse(StringUtils.equals("", null));
        Assert.assertFalse(StringUtils.equals(null, ""));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abc"));
        Assert.assertFalse(StringUtils.equals("abc", "ABC"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder(""), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder(""), new StringBuilder("")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals("", new StringBuilder("")));
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder(""), "abc"));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "ABC"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with special characters
        final byte[] expected2 = "\n\r\t".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("\n\r\t");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        ...
        
        // Regression test case 1: string with uppercase letters
        final byte[] expected1 = "HELLO".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("HELLO");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with numbers
        final byte[] expected2 = "123".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("123");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        ...
        
        // Regression test case 1: string with lowercase letters
        final byte[] expected1 = "hello".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("hello");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with special characters and numbers
        final byte[] expected2 = "@$%567".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("@$%567");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        ...
        
        // Regression test case 1: string with whitespace
        final byte[] expected1 = "   ".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("   ");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with special characters, numbers, and symbols
        final byte[] expected2 = "!@#$%123".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("!@#$%123");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        ...
        
        // Regression test case 1: string with accented characters
        final byte[] expected1 = "éàè".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("éàè");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with special characters and numbers
        final byte[] expected2 = "%$%^789".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("%$%^789");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        ...
        
        // Regression test case 1: string with mixed case and numbers
        final byte[] expected1 = "hElLo12".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("hElLo12");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with special characters and whitespace
        final byte[] expected2 = "@#$     ".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("@#$     ");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("abc");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = (STRING_FIXTURE + " ").getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE + " ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = (STRING_FIXTURE + " ").getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE + " ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = (STRING_FIXTURE + " ").getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE + " ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = (STRING_FIXTURE + " ").getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE + " ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test16() {
        Assert.assertNotNull(StringUtils.getBytesUnchecked(null, "US-ASCII"));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = (STRING_FIXTURE + " ").getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE + " ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = (STRING_FIXTURE + " ").getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE + " ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "null".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello^".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello^");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Helloå".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Helloå");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello😊".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello😊");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "?".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("á");
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
        final byte[] expected = "@#$%^".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("@#$%^");
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
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "  hello world  ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("  hello world  ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedWithEmptyString(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedWithNullString(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedWithSpecialCharacters(charsetName);
        final byte[] expected = "§€#".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("§€#");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test34() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
    
    // Regression test cases
    final byte[] actual1 = StringUtils.getBytesUtf16Le("Hello world");
    Assert.assertFalse(Arrays.equals(expected, actual1));
    
    final byte[] actual2 = StringUtils.getBytesUtf16Le("");
    Assert.assertArrayEquals(new byte[]{}, actual2);
    
    final byte[] actual3 = StringUtils.getBytesUtf16Le("12345");
    Assert.assertFalse(Arrays.equals(expected, actual3));
    
    final byte[] actual4 = StringUtils.getBytesUtf16Le("abcdefghijklmnopqrstuvwxyz");
    Assert.assertFalse(Arrays.equals(expected, actual4));
    
    final byte[] actual5 = StringUtils.getBytesUtf16Le("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    Assert.assertFalse(Arrays.equals(expected, actual5));
}
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final String specialString = "abc123&$#@!";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final String accentedString = "áéíóú";
        final byte[] expected = accentedString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(accentedString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{65, 66, 67};
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() {
        final byte[] bytes = new byte[]{65, 66, 67};
        try {
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{65, 66, 67};
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{65, 66, 67};
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{65, 66, 67};
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() {
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newString(bytes, "UNKNOWN"));
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{65, 66, 67};
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[]{65, 66, 67};
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        final byte[] bytes = null;
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case: bytes is null
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Additional test case: bytes is null
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case: bytes is null
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case: bytes is null
        Assert.assertNull(StringUtils.newStringUtf16(null));
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case: bytes is null
        Assert.assertNull(StringUtils.newStringUsAscii(null));
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case: bytes is null
        Assert.assertNull(StringUtils.newStringUtf8(null));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case: bytes is null
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test58() {
        byte[] bytes = new byte[] {65, 66, 67};
        final String expected = new String(bytes, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        byte[] bytes = new byte[0];
        final String expected = new String(bytes, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        // Test with different byte array
        byte[] differentBytes = {65, 66, 67};
        final String expectedDifferentBytes = new String(differentBytes, charsetName);
        final String actualDifferentBytes = StringUtils.newStringUsAscii(differentBytes);
        Assert.assertEquals(expectedDifferentBytes, actualDifferentBytes);
        
        // Test with empty byte array
        byte[] emptyBytes = {};
        final String expectedEmptyBytes = new String(emptyBytes, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
    }
    @Test
    public void test61() {
        // Test with non-null byte array
        byte[] nonNullBytes = {65, 66, 67};
        Assert.assertNull(StringUtils.newStringUsAscii(nonNullBytes));
        
        // Test with all null byte array
        byte[] allNullBytes = {0, 0, 0};
        Assert.assertNull(StringUtils.newStringUsAscii(allNullBytes));
    }
@Test
public void test62() {
    // Original test case
    Assert.assertNull(StringUtils.newStringUtf8(null));
    // Regression test case 1
    Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
    // Regression test case 2
    Assert.assertNull(StringUtils.newStringUtf8(new byte[] {65, 66, 67}));
    // Insert more regression test cases here
}
@Test
public void test63() throws UnsupportedEncodingException {
    // Original test case
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    // Regression test case 1
    final String expected = new String(new byte[] {0, 0}, charsetName);
    final String actual = StringUtils.newStringUtf16(new byte[] {0, 0});
    Assert.assertEquals(expected, actual);
    // Regression test case 2
    final String expected = new String(new byte[] {-2, -1, 0, 66, 0, 67}, charsetName);
    final String actual = StringUtils.newStringUtf16(new byte[] {-2, -1, 0, 66, 0, 67});
    Assert.assertEquals(expected, actual);
    // Insert more regression test cases here
}
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        
        // Test with empty byte array
        byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with byte array containing only one byte
        byte[] singleByte = {65}; // ASCII value of 'A'
        final String expectedSingle = new String(singleByte, charsetName);
        final String actualSingle = StringUtils.newStringUtf16Be(singleByte);
        Assert.assertEquals(expectedSingle, actualSingle);
        
        // Test with byte array containing multiple bytes
        byte[] multipleBytes = {65, 66, 67}; // ASCII values of 'A', 'B', 'C'
        final String expectedMultiple = new String(multipleBytes, charsetName);
        final String actualMultiple = StringUtils.newStringUtf16Be(multipleBytes);
        Assert.assertEquals(expectedMultiple, actualMultiple);
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[0]));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[0]));
        
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {1}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {1}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {1}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {1}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {1}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {1}));
        
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {-1}));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {-1}));
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {-1}));
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {-1}));
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {-1}));
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {-1}));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected, actual);
        
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] {1});
        Assert.assertEquals(expected, actual);
        
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] {-1});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        byte[] bytes = "abc".getBytes(Charsets.UTF_8);
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        byte[] bytes = new byte[0];
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        byte[] bytes = null;
        final String charsetName = "UTF-8";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertNull(actual);
    }
}