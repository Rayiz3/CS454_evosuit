package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        Assert.assertFalse(StringUtils.equals("abc", new StringBuilder("abcd"))); //mutant: append a character to cs2
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abcd"), "abcd")); //mutant: remove a character from cs1
        Assert.assertFalse(StringUtils.equals("abcd", new StringBuilder("abc"))); //mutant: append a character to cs1
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), "ABC")); //mutant: change the case of cs2
    }
    @Test
    public void test1() {
        Assert.assertFalse(StringUtils.equals("abc", null)); //mutant: change null to a non-null String
        Assert.assertFalse(StringUtils.equals(null, "abc")); //mutant: change null to a non-null String
        Assert.assertTrue(StringUtils.equals("abc", "abcd")); //mutant: append a character to cs2
        Assert.assertFalse(StringUtils.equals("abcd", "abc")); //mutant: append a character to cs1
        Assert.assertTrue(StringUtils.equals("abc", "ABC")); //mutant: change the case of cs2
    }
    @Test
    public void test2() {
        Assert.assertFalse(StringUtils.equals(null, new StringBuilder("abc"))); //mutant: change null to a non-null StringBuilder
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abcd"))); //mutant: append a character to cs2
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abcd"), new StringBuilder("abc"))); //mutant: append a character to cs1
        Assert.assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC"))); //mutant: change the case of cs2
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = " ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(" ");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("HELLO");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "@#$%".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("@#$%");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final String specialString = "¡¿Çáëñ";
        final byte[] expected = specialString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(specialString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final String longString = "This is a very long string that is more than 100 characters long...";
        final byte[] expected = longString.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(longString);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Additional test case
        final String differentString = "Different String";
        final byte[] expectedDifferent = differentString.getBytes(charsetName);
        final byte[] actualDifferent = StringUtils.getBytesIso8859_1(differentString);
        Assert.assertTrue(Arrays.equals(expectedDifferent, actualDifferent));
    }
    @Test
    public void test15() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
        // Additional test case
        final String nonNullString = "Non-null String";
        Assert.assertNotNull(StringUtils.getBytesUnchecked(nonNullString, "UNKNOWN"));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Additional test case
        final String differentString = "Different String";
        final byte[] expectedDifferent = differentString.getBytes(charsetName);
        final byte[] actualDifferent = StringUtils.getBytesUtf16Le(differentString);
        Assert.assertTrue(Arrays.equals(expectedDifferent, actualDifferent));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Additional test case
        final String differentString = "Different String";
        final byte[] expectedDifferent = differentString.getBytes(charsetName);
        final byte[] actualDifferent = StringUtils.getBytesUtf8(differentString);
        Assert.assertTrue(Arrays.equals(expectedDifferent, actualDifferent));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Additional test case
        final String differentString = "Different String";
        final byte[] expectedDifferent = differentString.getBytes(charsetName);
        final byte[] actualDifferent = StringUtils.getBytesUsAscii(differentString);
        Assert.assertTrue(Arrays.equals(expectedDifferent, actualDifferent));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Additional test case
        final String differentString = "Different String";
        final byte[] expectedDifferent = differentString.getBytes(charsetName);
        final byte[] actualDifferent = StringUtils.getBytesUtf16(differentString);
        Assert.assertTrue(Arrays.equals(expectedDifferent, actualDifferent));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        // Additional test case
        final String differentString = "Different String";
        final byte[] expectedDifferent = differentString.getBytes(charsetName);
        final byte[] actualDifferent = StringUtils.getBytesUtf16Be(differentString);
        Assert.assertTrue(Arrays.equals(expectedDifferent, actualDifferent));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String input = "AAAAAA";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String input = "TeStInG";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final String input = "!@#$%^&*()";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        // Regression test 1
        final String input1 = "Hello world!";
        final byte[] expected1 = input1.getBytes("UTF-16");
        final byte[] actual1 = StringUtils.getBytesUtf16(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2
        final String input2 = "12345";
        final byte[] expected2 = input2.getBytes("UTF-16");
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Original test cases
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
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
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "∞€£¥§π".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("∞€£¥§π");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "你好世界".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("你好世界");
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
    final byte[] expected = "     ".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("     ");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test33() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "!@#$%^&*()_+{}:\"<>?[];',./\\|-=".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("!@#$%^&*()_+{}:\"<>?[];',./\\|-=");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test34() throws UnsupportedEncodingException {
        String string = "";
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        String string = "!@#$%^&*()";
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        String string = "";
        for (int i = 0; i < 1000; i++) {
            string += "a";
        }
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() {
        try {
            StringUtils.getBytesUnchecked("Hello", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test38() {
        try {
            byte[] bytes = {65, 66, 67};
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[] { 'a', 'b', 'c' });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] { 'a', 'b', 'c' });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { 'a', 'b', 'c' });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] { 'a', 'b', 'c' });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[] { 'a', 'b', 'c' });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] { 'a', 'b', 'c' });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        // Regression test case: change bytes to null
        final byte[] bytes = null;
        final String charsetName = "UTF-16";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        // Regression test case: change bytes to null
        final byte[] bytes = null;
        final String charsetName = "UTF-8";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        // Regression test case: change bytes to null
        final byte[] bytes = null;
        final String charsetName = "US-ASCII";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        // Regression test case: change bytes to null
        final byte[] bytes = null;
        final String charsetName = "ISO-8859-1";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() {
        // Regression test case: change charsetName to null
        final byte[] bytes = BYTES_FIXTURE;
        final String charsetName = null;
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        try {
            StringUtils.newString(bytes, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        // Regression test case: change bytes to null
        final byte[] bytes = null;
        final String charsetName = "UTF-16BE";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        // Regression test case: change bytes to null
        final byte[] bytes = null;
        final String charsetName = "UTF-16LE";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() {
        // Regression test case: change charsetName to null
        final byte[] bytes = BYTES_FIXTURE;
        Assert.assertNull(StringUtils.newString(bytes, null));

        // Original test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test53() {
        // Regression test case: change bytes to null
        final byte[] bytes = null;
        final String charsetName = "UNKNOWN";
        Assert.assertNull(StringUtils.newString(bytes, charsetName));

        // Original test case
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String specialChars = "±§¿";
        final String expected = new String(specialChars.getBytes(), charsetName);
        final String actual = StringUtils.newStringIso8859_1(specialChars.getBytes());
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);

        // Test with a single byte of value 100
        byte[] bytes = {100};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);

        // Test with a single byte of value 0
        bytes = {0};
        final String expected2 = new String(bytes, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test58() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test59() throws UnsupportedEncodingException {

        // Original test case
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test cases
        final byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUsAscii(emptyBytes));

        final byte[] bytes1 = {65, 66, 67, 68, 69};
        Assert.assertEquals("ABCDE", StringUtils.newStringUsAscii(bytes1));

        final byte[] bytes2 = {97, 98, 99, 100, 101};
        Assert.assertEquals("abcde", StringUtils.newStringUsAscii(bytes2));

        final byte[] bytes3 = {48, 49, 50, 51, 52};
        Assert.assertEquals("01234", StringUtils.newStringUsAscii(bytes3));

        final byte[] bytes4 = {32, 33, 34, 35, 36};
        Assert.assertEquals(" !\"#$", StringUtils.newStringUsAscii(bytes4));
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));  //no change in input value
        Assert.assertNull(StringUtils.newStringIso8859_1(null));  //no change in input value
        Assert.assertNull(StringUtils.newStringUsAscii(null));  //no change in input value
        Assert.assertNull(StringUtils.newStringUtf16(null));  //no change in input value
        Assert.assertNull(StringUtils.newStringUtf16Be(null));  //no change in input value
        Assert.assertNull(StringUtils.newStringUtf16Le(null));  //no change in input value
        
        // additional regression test cases
        Assert.assertNull(StringUtils.newStringUtf8(new byte[0]));  //empty byte array
        Assert.assertNull(StringUtils.newStringUTF16(new byte[4]));  //byte array with length not divisible by 2
        Assert.assertNull(StringUtils.newStringUTF16(new byte[] {1, 3, 5}));  //byte array with odd length
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // additional regression test cases
        Assert.assertNull(StringUtils.newStringUtf16(new byte[0]));  //empty byte array
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {0, 0}));  //byte array with only two null bytes
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {1, 3, 5, 7}));  //byte array with length not divisible by 2
    }
@Test
public void test62() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
    Assert.assertEquals(expected, actual);
    
    // Regression tests
    byte[] emptyBytes = new byte[0];
    Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
    
    byte[] bytesWithSpecialCharacters = {0, 3, 2, 115, -57, 104, 3, 8, 9};
    Assert.assertEquals("\u0000\u0003\u0002s\uFFC9h\u0003\b\t", StringUtils.newStringUtf16Be(bytesWithSpecialCharacters));
    
    byte[] bytesWithHighSurrogate = {68, 34, 2, -50, 0, -34, 0, 0, -16, 0, -1, 0};
    Assert.assertEquals("D\"\u0002\uD800\uDC00\uFFC0\uFFFF", StringUtils.newStringUtf16Be(bytesWithHighSurrogate));
}
@Test
public void test63() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    
    // Regression test
    Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
}
    @Test
    public void test64() {
        byte[] bytes = { 0x00, 0x00, 0x00, 0x00 };
        String expected = "";
        String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() {
        byte[] bytes = { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43, 0x00, 0x44 };
        String expected = "ABCD";
        String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() {
        byte[] bytes = { 0x41, 0x41, 0x41, 0x41 };
        String expected = "AAAA";
        String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        // Original test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: byte array with non-UTF-8 characters
        final String input2 = "abc\xA9";
        final byte[] bytes2 = input2.getBytes(Charsets.ISO_8859_1);
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf8(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: byte array with length greater than 100
        final byte[] bytes3 = new byte[101];
        Arrays.fill(bytes3, (byte) 'a');
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf8(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test68() {
        // Original test case
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 1: null byte array
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Regression test case 2: null byte array for different charsets
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
}