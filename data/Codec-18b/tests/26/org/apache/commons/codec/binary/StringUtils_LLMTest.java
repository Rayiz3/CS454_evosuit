package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        // Test case 1: Changing the input CharSequence cs2 to an empty String
        Assert.assertTrue(StringUtils.equals("abc", ""));
        // Test case 2: Changing the input CharSequence cs2 to a different String
        Assert.assertFalse(StringUtils.equals("abc", "def"));
    }
    @Test
    public void test1() {
        // Test case 1: Changing the input CharSequence cs1 to an empty String
        Assert.assertFalse(StringUtils.equals("", new StringBuilder("abc")));
        // Test case 2: Changing the input CharSequence cs1 to a different String
        Assert.assertFalse(StringUtils.equals("def", new StringBuilder("abc")));
    }
    @Test
    public void test2() {
        // Test case 1: Changing the input Strings to null
        Assert.assertFalse(StringUtils.equals(null, null));
        Assert.assertFalse(StringUtils.equals("abc", null));
        Assert.assertFalse(StringUtils.equals(null, "abc"));
        // Test case 2: Changing the input Strings to empty Strings
        Assert.assertEquals(false, StringUtils.equals("", ""));
        Assert.assertFalse(StringUtils.equals("abc", ""));
        Assert.assertFalse(StringUtils.equals("", "abc"));
        // Test case 3: Changing the input Strings to different Strings
        Assert.assertFalse(StringUtils.equals("abc", "def"));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName); // Change input value
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World"); // Change input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName); // Change input value
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello World"); // Change input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName); // Change input value
        final byte[] actual = StringUtils.getBytesUsAscii("Hello World"); // Change input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName); // Change input value
        final byte[] actual = StringUtils.getBytesUtf8("Hello World"); // Change input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName); // Change input value
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello World"); // Change input value
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World".getBytes(charsetName); // Change input value
        final byte[] actual = StringUtils.getBytesUtf16("Hello World"); // Change input value
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
    final byte[] expected = "Special Characters: !@#$%^&*()_+-=".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("Special Characters: !@#$%^&*()_+-=");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test12() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "   ".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("   ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test13() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "áéíóú".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1("áéíóú");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        String input = "abc";  // Change the input value
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        String input = "xyz";  // Change the input value
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        String input = "pqr";  // Change the input value
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        String input = "def";  // Change the input value
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() {
        String input = null;  // Change the input value
        Assert.assertNull(StringUtils.getBytesUnchecked(input, "UNKNOWN"));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        String input = "mno";  // Change the input value
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        String input = "ghi";  // Change the input value
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() {
        String input = "jkl";  // Change the input value
        try {
            StringUtils.getBytesUnchecked(input, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("你好");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Testing with an empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: Testing with a string that contains only whitespaces
        final byte[] expected2 = "   ".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("   ");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: Testing with a string that contains special characters
        final byte[] expected3 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression test 4: Testing with a string that contains non-ASCII characters
        final byte[] expected4 = "こんにちは".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16("こんにちは");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
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
        final byte[] expected = "ãµ¨z!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("ãµ¨z!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "  \t\n".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("  \t\n");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "A".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("A");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "åßÇ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("åßÇ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%^&*".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("@#$%^&*");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() {
        try {
            StringUtils.getBytesUnchecked("", "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            StringUtils.getBytesUnchecked(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() {
        try {
            StringUtils.newString(new byte[] {}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test40() {
        try {
            StringUtils.newString(null, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test41() {
        Assert.assertNull(StringUtils.newString(null, Charset.forName("UTF-8")));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, Charset.forName("UTF-16BE"));
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, Charset.forName("UTF-8"));
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, Charset.forName("US-ASCII"));
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
        final String expected = new String(BYTES_FIXTURE, Charset.forName("ISO-8859-1"));
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, Charset.forName("UTF-16"));
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, Charset.forName("UTF-16LE"));
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() {
        try {
            StringUtils.newString(BYTES_FIXTURE, Charset.forName("ISO-8859-1"));
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() {
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
        Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
        Assert.assertNull(StringUtils.newString(null, "US-ASCII"));
        Assert.assertNull(StringUtils.newString(null, "UTF-16"));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE_16BE, "UTF-8");
        final String actual2 = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "ISO-8859-1");
        final String actual2 = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "UTF-16BE");
        final String actual2 = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "US-ASCII");
        final String actual2 = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE, "UTF-8");
        final String actual2 = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        final String expected2 = new String(BYTES_FIXTURE_16LE, "ISO-8859-1");
        final String actual2 = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Changing input value to empty array
        final byte[] emptyBytes = new byte[0];
        final String emptyExpected = new String(emptyBytes, charsetName);
        final String emptyActual = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(emptyExpected, emptyActual);
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Changing input value to non-empty array
        final byte[] nonEmptyBytes = "Test".getBytes(Charsets.ISO_8859_1);
        Assert.assertNull(StringUtils.newStringIso8859_1(nonEmptyBytes));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        //Regression test cases:
        //1. Test with empty byte array
        final String expectedEmpty = new String(new byte[]{}, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        //2. Test with a byte array containing special characters
        final String inputSpecial = new String(new byte[] {0x41, (byte) 0xFF, 0x42}, "ISO-8859-1");
        final String expectedSpecial = new String(inputSpecial.getBytes(charsetName), charsetName);
        final String actualSpecial = StringUtils.newStringUsAscii(inputSpecial.getBytes());
        Assert.assertEquals(expectedSpecial, actualSpecial);
        
        //3. Test with a byte array containing non-ASCII characters
        final String inputNonASCII = "Test 编程";
        final String expectedNonASCII = new String(inputNonASCII.getBytes(charsetName), charsetName);
        final String actualNonASCII = StringUtils.newStringUsAscii(inputNonASCII.getBytes());
        Assert.assertEquals(expectedNonASCII, actualNonASCII);
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        //Regression test cases:
        //4. Test with non-null byte array
        Assert.assertNotNull(StringUtils.newStringUsAscii(BYTES_FIXTURE));
        
        //5. Test with an empty byte array
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[]{}));
        
        //6. Test with a byte array containing special characters
        final String inputSpecial = new String(new byte[] {0x41, (byte) 0xFF, 0x42}, "ISO-8859-1");
        Assert.assertNotNull(StringUtils.newStringUsAscii(inputSpecial.getBytes()));
        
        //7. Test with a byte array containing non-ASCII characters
        final String inputNonASCII = "Test 编程";
        Assert.assertNotNull(StringUtils.newStringUsAscii(inputNonASCII.getBytes()));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        // Test with empty byte array
        byte[] emptyBytes = new byte[0];
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected, actual);
        
        // Test with non-empty byte array
        byte[] nonEmptyBytes = new byte[]{1, 2, 3};
        final String expected2 = new String(nonEmptyBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16(nonEmptyBytes);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test62() {
        // Test with null input
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Test with empty byte array
        byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes);
        Assert.assertEquals(expected, StringUtils.newStringUtf8(emptyBytes));
        Assert.assertEquals(expected, StringUtils.newStringIso8859_1(emptyBytes));
        Assert.assertEquals(expected, StringUtils.newStringUsAscii(emptyBytes));
        Assert.assertEquals(expected, StringUtils.newStringUtf16(emptyBytes));
        Assert.assertEquals(expected, StringUtils.newStringUtf16Be(emptyBytes));
        Assert.assertEquals(expected, StringUtils.newStringUtf16Le(emptyBytes));
        
        // Test with non-empty byte array
        byte[] nonEmptyBytes = new byte[]{1, 2, 3};
        final String expected2 = new String(nonEmptyBytes);
        Assert.assertEquals(expected2, StringUtils.newStringUtf8(nonEmptyBytes));
        Assert.assertEquals(expected2, StringUtils.newStringIso8859_1(nonEmptyBytes));
        Assert.assertEquals(expected2, StringUtils.newStringUsAscii(nonEmptyBytes));
        Assert.assertEquals(expected2, StringUtils.newStringUtf16(nonEmptyBytes));
        Assert.assertEquals(expected2, StringUtils.newStringUtf16Be(nonEmptyBytes));
        Assert.assertEquals(expected2, StringUtils.newStringUtf16Le(nonEmptyBytes));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);

        // New test case that covers an empty byte array input
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // New test case that covers a byte array input with all zeroes
        final byte[] zeroBytes = new byte[] {0, 0, 0, 0};
        final String expectedZero = new String(zeroBytes, charsetName);
        final String actualZero = StringUtils.newStringUtf16Be(zeroBytes);
        Assert.assertEquals(expectedZero, actualZero);

        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));

        // New test case that covers a null input for UTF-16BE
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        // New test case that covers a null input for UTF-16LE
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final byte[] bytes = new byte[] {97, 98, 99, 100, 101, 102};
        final String charsetName = "UTF-16LE";
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() {
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        
        // Regression Test Case 1: change input to empty byte array
        final byte[] emptyBytesFixture = new byte[0];
        final String expectedEmpty = new String(emptyBytesFixture, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(emptyBytesFixture);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression Test Case 2: change input to byte array with non UTF-8 characters
        final byte[] nonUtf8BytesFixture = { (byte) 0xC0, (byte) 0xFD, (byte) 0x80 };
        final String expectedNonUtf8 = new String(nonUtf8BytesFixture, charsetName);
        final String actualNonUtf8 = StringUtils.newStringUtf8(nonUtf8BytesFixture);
        Assert.assertEquals(expectedNonUtf8, actualNonUtf8);
    }
    @Test
    public void test68() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression Test Case: change input to constant non-NULL byte array
        final byte[] constantBytesFixture = {1, 1, 1};
        final String expectedConstant = new String(constantBytesFixture, Charsets.UTF_8);
        Assert.assertEquals(expectedConstant, StringUtils.newStringUtf8(constantBytesFixture));
    }
}