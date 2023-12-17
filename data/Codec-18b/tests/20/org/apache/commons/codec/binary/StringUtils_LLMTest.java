package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abcde")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), "ABC"));
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), null));
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abcd")));
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abcd")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abcde")));
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("ABC")));
    }
    @Test
    public void test2() {
        Assert.assertTrue(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abcd", null));
        Assert.assertFalse(StringUtils.equals(null, "abcd"));
        Assert.assertTrue(StringUtils.equals("abcd", "abcd"));
        Assert.assertFalse(StringUtils.equals("abcd", "abcde"));
        Assert.assertFalse(StringUtils.equals("abcd", "ABC"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test9() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));

    // Regression test 1: empty string
    final byte[] expected1 = "".getBytes(charsetName);
    final byte[] actual1 = StringUtils.getBytesIso8859_1("");
    Assert.assertTrue(Arrays.equals(expected1, actual1));

    // Regression test 2: string with special characters
    final String specialCharacters = "çñ@#$";
    final byte[] expected2 = specialCharacters.getBytes(charsetName);
    final byte[] actual2 = StringUtils.getBytesIso8859_1(specialCharacters);
    Assert.assertTrue(Arrays.equals(expected2, actual2));
}
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Change the input value to an empty string
        final byte[] actual = StringUtils.getBytesUtf16Be(""); // Change the input value to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Change the input value to an empty string
        final byte[] actual = StringUtils.getBytesUtf16Le(""); // Change the input value to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Change the input value to an empty string
        final byte[] actual = StringUtils.getBytesIso8859_1(""); // Change the input value to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Change the input value to an empty string
        final byte[] actual = StringUtils.getBytesUsAscii(""); // Change the input value to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() {
        Assert.assertNull(StringUtils.getBytesUnchecked("", "UNKNOWN")); // Change the input value to an empty string
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16"; // Change the input value to an unsupported charsetName
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName); // Change the input value to an empty string
        final byte[] actual = StringUtils.getBytesUtf8(""); // Change the input value to an empty string
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() {
        try {
            StringUtils.getBytesUnchecked("", "UNKNOWN"); // Change the input value to an empty string
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "+-*/\\%$#@!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("+-*/\\%$#@!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Test with an empty string
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertArrayEquals(expected1, actual1);
        
        // Test with a string with only one character
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = "a".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("a");
        Assert.assertArrayEquals(expected2, actual2);
        
        // Test with a string with special characters
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("!@#$%^&*()");
        Assert.assertArrayEquals(expected3, actual3);
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "\uD83D\uDE03\uD83D\uDE04\uD83D\uDE0A\uD83D\uDE0B".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("\uD83D\uDE03\uD83D\uDE04\uD83D\uDE0A\uD83D\uDE0B");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final String longString = "This is a long string that exceeds the maximum length of a character set, which is why it is going to be splitted into multiple bytes.";
        final byte[] expected = longString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(longString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
@Test
public void test28() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
    
    // Regression test case 1: Changing the input string
    final String input1 = "Hello world!";
    final byte[] expected1 = input1.getBytes(charsetName);
    final byte[] actual1 = StringUtils.getBytesUtf16Le(input1);
    Assert.assertTrue(Arrays.equals(expected1, actual1));
    
    // Regression test case 2: Changing the input string to an empty string
    final String input2 = "";
    final byte[] expected2 = input2.getBytes(charsetName);
    final byte[] actual2 = StringUtils.getBytesUtf16Le(input2);
    Assert.assertTrue(Arrays.equals(expected2, actual2));
    
    // Regression test case 3: Changing the input string to contain special characters
    final String input3 = "abc@#$%^&";
    final byte[] expected3 = input3.getBytes(charsetName);
    final byte[] actual3 = StringUtils.getBytesUtf16Le(input3);
    Assert.assertTrue(Arrays.equals(expected3, actual3));
    
    // Regression test case 4: Changing the input string to a longer string
    final String input4 = "This is a longer string that contains more characters.";
    final byte[] expected4 = input4.getBytes(charsetName);
    final byte[] actual4 = StringUtils.getBytesUtf16Le(input4);
    Assert.assertTrue(Arrays.equals(expected4, actual4));
}
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: String with special characters
        final byte[] expected2 = "hello!@#$%^&*()".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("hello!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: String with non-ASCII characters
        final byte[] expected3 = "你好".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8("你好");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
@Test
public void test30() {
    try {
        StringUtils.getBytesUnchecked("", "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test31() {
    try {
        StringUtils.newString(new byte[0], "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
 @Test
public void test32() {
   Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
   Assert.assertNull(StringUtils.newString(null, "UTF-8"));
   Assert.assertNull(StringUtils.newString(null, "UTF-16BE"));
   Assert.assertNull(StringUtils.newString(null, "US-ASCII"));
   Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
   Assert.assertNull(StringUtils.newString(null, "UTF-16"));
   Assert.assertNull(StringUtils.newString(null, "UTF-16LE"));
}
@Test
public void test33() throws UnsupportedEncodingException {
   final String charsetName = "UTF-16BE";
   testNewString(charsetName);
   final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
   final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
   Assert.assertEquals(expected, actual);
   Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE_16BE, charsetName));
}
@Test
public void test34() throws UnsupportedEncodingException {
   final String charsetName = "UTF-8";
   testNewString(charsetName);
   final String expected = new String(BYTES_FIXTURE, charsetName);
   final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
   Assert.assertEquals(expected, actual);
   Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName));
}
@Test
public void test35() throws UnsupportedEncodingException {
   final String charsetName = "US-ASCII";
   testNewString(charsetName);
   final String expected = new String(BYTES_FIXTURE, charsetName);
   final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
   Assert.assertEquals(expected, actual);
   Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName));
}
@Test
public void test36() {
   Assert.assertNull(StringUtils.newStringUtf8(null));
   Assert.assertNull(StringUtils.newStringIso8859_1(null));
   Assert.assertNull(StringUtils.newStringUsAscii(null));
   Assert.assertNull(StringUtils.newStringUtf16(null));
   Assert.assertNull(StringUtils.newStringUtf16Be(null));
   Assert.assertNull(StringUtils.newStringUtf16Le(null));
   Assert.assertNull(StringUtils.newString(null, null));
}
@Test
public void test37() throws UnsupportedEncodingException {
   final String charsetName = "ISO-8859-1";
   testNewString(charsetName);
   final String expected = new String(BYTES_FIXTURE, charsetName);
   final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
   Assert.assertEquals(expected, actual);
   Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName));
}
@Test
public void test38() throws UnsupportedEncodingException {
   final String charsetName = "UTF-16";
   testNewString(charsetName);
   final String expected = new String(BYTES_FIXTURE, charsetName);
   final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
   Assert.assertEquals(expected, actual);
   Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE, charsetName));
}
@Test
public void test39() throws UnsupportedEncodingException {
   final String charsetName = "UTF-16LE";
   testNewString(charsetName);
   final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
   final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
   Assert.assertEquals(expected, actual);
   Assert.assertEquals(expected, StringUtils.newString(BYTES_FIXTURE_16LE, charsetName));
}
@Test
public void test40() {
   try {
       StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
       Assert.fail("Expected " + IllegalStateException.class.getName());
   } catch (final IllegalStateException e) {
       // Expected
   }
   try {
       StringUtils.newString(BYTES_FIXTURE, "UTF-8");
       Assert.fail("Expected " + IllegalStateException.class.getName());
   } catch (final IllegalStateException e) {
       // Expected
   }
   try {
       StringUtils.newString(BYTES_FIXTURE, "UTF-16BE");
       Assert.fail("Expected " + IllegalStateException.class.getName());
   } catch (final IllegalStateException e) {
       // Expected
   }
   try {
       StringUtils.newString(BYTES_FIXTURE, "US-ASCII");
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
   try {
       StringUtils.newString(BYTES_FIXTURE, "UTF-16");
       Assert.fail("Expected " + IllegalStateException.class.getName());
   } catch (final IllegalStateException e) {
       // Expected
   }
   try {
       StringUtils.newString(BYTES_FIXTURE, "UTF-16LE");
       Assert.fail("Expected " + IllegalStateException.class.getName());
   } catch (final IllegalStateException e) {
       // Expected
   }
}
    @Test
    public void test41() {
        Assert.assertNull(StringUtils.newString(null, "UTF-16LE"));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] {65, 66, 67, -1, -2});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {1, 2, 3}));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test with empty byte array
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test with byte array containing null bytes
        final byte[] bytesWithNulls = {65, 0, 66, 0, 0};
        final String expectedNulls = new String(bytesWithNulls, charsetName);
        final String actualNulls = StringUtils.newStringUsAscii(bytesWithNulls);
        Assert.assertEquals(expectedNulls, actualNulls);
        
        // Regression test with byte array containing non-ASCII characters
        final byte[] bytesNonAscii = {65, 66, -1, -2};
        final String expectedNonAscii = new String(bytesNonAscii, charsetName);
        final String actualNonAscii = StringUtils.newStringUsAscii(bytesNonAscii);
        Assert.assertEquals(expectedNonAscii, actualNonAscii);
    }
    @Test
    public void test53() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test with empty byte array
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[0]));
        
        // Regression test with byte array containing null bytes
        final byte[] bytesWithNulls = {65, 0, 66, 0, 0};
        Assert.assertNull(StringUtils.newStringUsAscii(bytesWithNulls));
        
        // Regression test with byte array containing non-ASCII characters
        final byte[] bytesNonAscii = {65, 66, -1, -2};
        Assert.assertNull(StringUtils.newStringUsAscii(bytesNonAscii));
    }
@Test
public void test54() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[]{65, 66, 67, 68, 69};
    final String expected = "ABC";
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test55() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[]{};
    final String expected = "";
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertEquals(expected, actual);
}
@Test
public void test56() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[]{0, -16, 0, -103}; // UTF-16 encoding for 'έ'
    final String expected = "έ";
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        // Regression test case with empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        // Regression test case with different character encoding
        final String charsetName2 = "UTF-8";
        final String expected2 = new String(BYTES_FIXTURE_16BE, charsetName2);
        final String actual2 = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected2, actual2);
        // Regression test case with longer input
        final byte[] additionalBytes = { 0x78, 0x79, 0x7A };  // "xyz"
        final byte[] longerBytes = new byte[BYTES_FIXTURE_16BE.length + additionalBytes.length];
        System.arraycopy(BYTES_FIXTURE_16BE, 0, longerBytes, 0, BYTES_FIXTURE_16BE.length);
        System.arraycopy(additionalBytes, 0, longerBytes, BYTES_FIXTURE_16BE.length, additionalBytes.length);
        final String expectedLonger = new String(longerBytes, charsetName);
        final String actualLonger = StringUtils.newStringUtf16Be(longerBytes);
        Assert.assertEquals(expectedLonger, actualLonger);
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        // Regression test case with null input
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = {-1, -2, -3}; // Invalid UTF-16LE bytes
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] bytes = {0x61, 0x00, 0x62, 0x00, 0x63, 0x00}; // UTF-16LE bytes representing "abc"
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf8(emptyBytes));
        
        byte[] nonUtf8Bytes = { 0x80, 0x81, 0x82 };
        Assert.assertThrows(MalformedInputException.class, () -> StringUtils.newStringUtf8(nonUtf8Bytes));
        
        byte[] validBytes = { 0x41, 0x42, 0x43, 0x44 };
        Assert.assertEquals("ABCD", StringUtils.newStringUtf8(validBytes));
    }
    @Test
    public void test65() {
        
        // Original test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case
        byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
    }
}