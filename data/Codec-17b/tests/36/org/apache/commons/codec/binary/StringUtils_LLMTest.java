package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "   ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("   ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello! @#$%^&".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello! @#$%^&");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "HELLO".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("HELLO");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
       
        // Regression test case 1: Empty string 
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2: String with special characters
        final byte[] expected2 = "äöü!".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("äöü!");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test case 3: String with numbers 
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        // Regression test case 4: String with whitespace
        final byte[] expected4 = "   ".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesIso8859_1("   ");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "DifferentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("DifferentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "DifferentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("DifferentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "DifferentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("DifferentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "DifferentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("DifferentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "DifferentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("DifferentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "DifferentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("DifferentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "GBK";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);

        // Test case 1: Empty string input
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Test case 2: String input containing only whitespace characters
        final byte[] expected2 = "   ".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("   ");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Test case 3: String input containing special characters
        final byte[] expected3 = "∑∆≈".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("∑∆≈");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        // Test case 4: String input containing numbers
        final byte[] expected4 = "123456".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16("123456");
        Assert.assertTrue(Arrays.equals(expected4, actual4));

        // Test case 5: String input containing special characters and numbers
        final byte[] expected5 = "∑∆≈123456".getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesUtf16("∑∆≈123456");
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
    @Test
    public void test35() {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test36() {
        final byte[] expected = new byte[] { 0x06, 0xE4, 0x06, 0x8E, 0x06, 0xA2, 0x06, 0xA6 };
        final byte[] actual = StringUtils.getBytesUtf16Be("äŽ¢Ŧ");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test37() {
        final byte[] expected = new byte[] { 0x00, 0x21, 0x00, 0x23, 0x00, 0x24, 0x00, 0x25, 0x00, 0x26 };
        final byte[] actual = StringUtils.getBytesUtf16Be("!#$%&");
        Assert.assertArrayEquals(expected, actual);
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
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "tEsT".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("tEsT");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] {-30, -128, -109, -28, -72, -118, -27, -101, -77, -28, -67, -96};
        final byte[] actual = StringUtils.getBytesUtf8("测试");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] {63};
        final byte[] actual = StringUtils.getBytesUtf8("©");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[Integer.MAX_VALUE];
        Arrays.fill(expected, (byte) 0);
        final byte[] actual = StringUtils.getBytesUtf8(new String(new char[Integer.MAX_VALUE]));
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
   @Test
    public void test46() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
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
    }
    @Test
    public void test47() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName); // Mutated input
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, "UTF-16BE"); // Mutated input
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() {
        Assert.assertNull(StringUtils.newStringUtf8(BYTES_FIXTURE)); // Mutated input
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName); // Mutated input
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName); // Mutated input
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName); // Mutated input
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() {
        Assert.assertNull(StringUtils.newString(null, "UTF-16BE")); // Mutated input
    }
    @Test
    public void test55() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-16BE"); // Mutated input
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, "UTF-16BE"); // Mutated input
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test - Changing input value
        final byte[] changedInput = {112, 114, 111, 103, 114, 97, 109};
        final String expectedChanged = new String(changedInput, charsetName);
        final String actualChanged = StringUtils.newStringUtf16Be(changedInput);
        Assert.assertEquals(expectedChanged, actualChanged);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test - Changing input value
        final byte[] changedInput = {97, 98, 99, 100, 101};
        final String expectedChanged = new String(changedInput, charsetName);
        final String actualChanged = StringUtils.newStringUtf8(changedInput);
        Assert.assertEquals(expectedChanged, actualChanged);
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test - Changing input value
        final byte[] changedInput = null;
        Assert.assertNull(StringUtils.newStringUtf8(changedInput));
        Assert.assertNull(StringUtils.newStringIso8859_1(changedInput));
        Assert.assertNull(StringUtils.newStringUsAscii(changedInput));
        Assert.assertNull(StringUtils.newStringUtf16(changedInput));
        Assert.assertNull(StringUtils.newStringUtf16Be(changedInput));
        Assert.assertNull(StringUtils.newStringUtf16Le(changedInput));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test - Changing input value
        final byte[] changedInput = {65, 66, 67};
        final String expectedChanged = new String(changedInput, charsetName);
        final String actualChanged = StringUtils.newStringUsAscii(changedInput);
        Assert.assertEquals(expectedChanged, actualChanged);
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test - Changing input value
        final byte[] changedInput = {84, 101, 115, 116};
        final String expectedChanged = new String(changedInput, charsetName);
        final String actualChanged = StringUtils.newStringIso8859_1(changedInput);
        Assert.assertEquals(expectedChanged, actualChanged);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test - Changing input value
        final byte[] changedInput = {65, 66, 67, 68};
        final String expectedChanged = new String(changedInput, charsetName);
        final String actualChanged = StringUtils.newStringUtf16(changedInput);
        Assert.assertEquals(expectedChanged, actualChanged);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        
        // Regression test - Changing input value
        final byte[] changedInput = null;
        Assert.assertNull(StringUtils.newString(changedInput, "UNKNOWN"));
    }
    @Test
    public void test64() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        // Regression test - Changing input value
        final byte[] changedInput = {112, 114, 111, 103, 114, 97, 109};
        try {
            StringUtils.newString(changedInput, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test - Changing input value
        final byte[] changedInput = {80, 114, 111, 103, 114, 97, 109};
        final String expectedChanged = new String(changedInput, charsetName);
        final String actualChanged = StringUtils.newStringUtf16Le(changedInput);
        Assert.assertEquals(expectedChanged, actualChanged);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] emptyBytes = new byte[0];
        final String expected = new String(emptyBytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] nonAsciiBytes = {-128, -1, 0, 1};
        final String expected = new String(nonAsciiBytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(nonAsciiBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() {
        final byte[] reversedBytes = new byte[BYTES_FIXTURE.length];
        for (int i = 0; i < BYTES_FIXTURE.length; i++) {
            reversedBytes[i] = BYTES_FIXTURE[BYTES_FIXTURE.length - 1 - i];
        }
        final String charsetName = "US-ASCII";
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(reversedBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() {
        final byte[] emptyBytes = new byte[0];
        final String charsetName = "US-ASCII";
        final String expected = new String(emptyBytes, charsetName); // Should be an empty string
        final String actual = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected, actual);
    }
@Test
public void test70() throws UnsupportedEncodingException {
    final byte[] bytes = "Hello World!".getBytes("UTF-16");
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertNotEquals("Hello World!", actual);
}
@Test
public void test71() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[]{};
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertNotNull(actual);
    Assert.assertEquals("", actual);
}
@Test
public void test72() throws UnsupportedEncodingException {
    final byte[] bytes = "Hello World!".getBytes("UTF-8");
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertNotNull(actual);
    Assert.assertNotEquals("Hello World!", actual);
}
@Test
public void test73() throws UnsupportedEncodingException {
    final byte[] bytes = new byte[]{1, 2, 3, 4, 5, 6};
    final String actual = StringUtils.newStringUtf16(bytes);
    Assert.assertNotNull(actual);
    Assert.assertNotEquals("", actual);
}
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression1(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression2(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString_regression3(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] {0, 1, 2, 3});
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] {-1, -1, -1, -1});
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Changing the byte value at index 0
        final byte[] bytes = Arrays.copyOf(BYTES_FIXTURE, BYTES_FIXTURE.length);
        bytes[0] = (byte) (bytes[0] + 1);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test80() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Changing the byte value at index 1
        final byte[] bytes = Arrays.copyOf(BYTES_FIXTURE, BYTES_FIXTURE.length);
        bytes[1] = (byte) (bytes[1] + 1);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test81() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Changing the byte value at index 2
        final byte[] bytes = Arrays.copyOf(BYTES_FIXTURE, BYTES_FIXTURE.length);
        bytes[2] = (byte) (bytes[2] + 1);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test82() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Changing the byte value at index 3
        final byte[] bytes = Arrays.copyOf(BYTES_FIXTURE, BYTES_FIXTURE.length);
        bytes[3] = (byte) (bytes[3] + 1);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test83() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Changing the byte value at index 4
        final byte[] bytes = Arrays.copyOf(BYTES_FIXTURE, BYTES_FIXTURE.length);
        bytes[4] = (byte) (bytes[4] + 1);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test84() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Changing the byte value at index 5
        final byte[] bytes = Arrays.copyOf(BYTES_FIXTURE, BYTES_FIXTURE.length);
        bytes[5] = (byte) (bytes[5] + 1);
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
}