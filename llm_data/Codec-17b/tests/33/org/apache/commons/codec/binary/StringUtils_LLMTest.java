package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_regress(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regress(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regress(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regress(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_regress(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regress(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("!@#$%");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "ÅÍÎÏÌ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("ÅÍÎÏÌ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test11() {
        Assert.assertNull(StringUtils.getBytesUnchecked(STRING_FIXTURE, "INVALID"));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "こんにちは".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("こんにちは");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "こんにちは".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("こんにちは");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Îñţérñåţîöñåļîžåţîờñ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Îñţérñåţîöñåļîžåţîờñ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "   ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("   ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);

        // Change input value for killing more mutants
        final String string1 = "";
        final byte[] expected1 = string1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be(string1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Change input value for killing more mutants
        final String string2 = "abc";
        final byte[] expected2 = string2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be(string2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        // Test with an empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test with a string containing only one character
        final byte[] expected2 = "A".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("A");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test with a string containing special characters 
        final byte[] expected3 = "&^*()+/{}|:".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("&^*()+/{}|:");
        Assert.assertTrue(Arrays.equals(expected3, actual3));        
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String input = "ãëü";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final String input = "HELLO";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16");
            // The above line should throw an IllegalStateException
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test30() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            // The above line should throw an IllegalStateException
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
            // The above line should throw an IllegalStateException
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1");
            // The above line should throw an IllegalStateException
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
@Test
public void test33() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
    
    byte[] mutatedBytes = new byte[BYTES_FIXTURE_16BE.length];
    for (int i = 0; i < BYTES_FIXTURE_16BE.length; i++) {
        mutatedBytes[i] = (byte) (BYTES_FIXTURE_16BE[i] + 1);
    }
    
    final String actual = StringUtils.newStringUtf16Be(mutatedBytes);
    Assert.assertNotEquals(expected, actual);
    Assert.assertNull(actual);
}
@Test
public void test34() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    
    byte[] mutatedBytes = new byte[BYTES_FIXTURE.length];
    for (int i = 0; i < BYTES_FIXTURE.length; i++) {
        mutatedBytes[i] = (byte) (BYTES_FIXTURE[i] + 1);
    }
    
    final String actual = StringUtils.newStringUtf8(mutatedBytes);
    Assert.assertNotEquals(expected, actual);
    Assert.assertNull(actual);
}
@Test
public void test35() {
    Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
    Assert.assertNull(StringUtils.newString(null, "US-ASCII"));
    Assert.assertNull(StringUtils.newString(null, "UTF-16"));
    Assert.assertNull(StringUtils.newString(null, "UTF-16BE"));
    Assert.assertNull(StringUtils.newString(null, "UTF-16LE"));
}
@Test
public void test36() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[] { 0x41, 0x42, 0x43 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] { 0x41, 0x42, 0x43 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] { 0x41, 0x42, 0x43 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] { 0x41, 0x42, 0x43 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[] { 0x41, 0x42, 0x43 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test44() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] { 0x41, 0x42, 0x43 });
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression1, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString_regression2(charsetName);
        final String expected = new String(BYTES_FIXTURE_regression2, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regression2);
        Assert.assertEquals(expected, actual);
    }
    public void testNewString_regression1(String charsetName) throws UnsupportedEncodingException {
        // Test with empty byte array
        final String expected = new String(new byte[]{}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    public void testNewString_regression2(String charsetName) throws UnsupportedEncodingException {
        // Test with byte array containing special characters
        final byte[] bytes = {-27, -128, 70, 84, 2, 92};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1
        byte[] bytes1 = {65, 66, 67, 68, 69};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2
        byte[] bytes2 = {72, 73, 74, 75, 76};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3
        byte[] bytes3 = {85, 86, 87, 88, 89};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(bytes3);
        Assert.assertEquals(expected3, actual3);
        
        // Regression test 4
        byte[] bytes4 = {100, 101, 102, 103, 104};
        final String expected4 = new String(bytes4, charsetName);
        final String actual4 = StringUtils.newStringUsAscii(bytes4);
        Assert.assertEquals(expected4, actual4);
    }
@Test
public void test49() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(new byte[]{});
    Assert.assertEquals(expected, actual);
}
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final String expected = new String(null, charsetName);
        final String actual = StringUtils.newStringUtf16Be(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] differentBytes = { 1, 2, 3, 4, 5 };
        final String expected = new String(differentBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(differentBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Original test cases
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Additional test cases
        final byte[] emptyBytes = {};
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        final byte[] nonLatinBytes = {-2, -1, 0, 0, 0, 97, 0, 50};
        final String expectedNonLatin = new String(nonLatinBytes, charsetName);
        final String actualNonLatin = StringUtils.newStringUtf16Le(nonLatinBytes);
        Assert.assertEquals(expectedNonLatin, actualNonLatin);

    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);

        // Regression test 1: passing an empty byte array
        final byte[] emptyBytes = {};
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(expected1, actual1);

        // Regression test 2: passing a null byte array
        final byte[] nullBytes = null;
        Assert.assertNull(StringUtils.newStringUtf8(nullBytes));

        // Regression test 3: passing a byte array of length 1
        final byte[] singleByte = {127};
        final String expected3 = new String(singleByte, charsetName);
        final String actual3 = StringUtils.newStringUtf8(singleByte);
        Assert.assertEquals(expected3, actual3);

        Assert.assertEquals(expected, actual);
    }
}