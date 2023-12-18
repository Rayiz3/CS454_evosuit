package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        String input = "hello";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        String input = "hello";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        String input = "hello";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        String input = "hello";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        String input = "hello";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        String input = "hello";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test6() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test7() throws UnsupportedEncodingException {
    final byte[] expected = "".getBytes("ISO-8859-1");
    final byte[] actual = StringUtils.getBytesIso8859_1(null);
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test8() throws UnsupportedEncodingException {
    final byte[] expected = "".getBytes("ISO-8859-1");
    final byte[] actual = StringUtils.getBytesIso8859_1("");
    Assert.assertArrayEquals(expected, actual);
}
@Test
public void test9() throws UnsupportedEncodingException {
    final byte[] expected = "éàπòñÇф ".getBytes("ISO-8859-1");
    final byte[] actual = StringUtils.getBytesIso8859_1("éàπòñÇф ");
    Assert.assertArrayEquals(expected, actual);
}
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        Assert.assertNull(StringUtils.getBytesUnchecked(null, charsetName));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        Assert.assertNull(StringUtils.getBytesUnchecked(null, charsetName));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        Assert.assertNull(StringUtils.getBytesUnchecked(null, charsetName));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        Assert.assertNull(StringUtils.getBytesUnchecked(null, charsetName));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        Assert.assertNull(StringUtils.getBytesUnchecked(null, charsetName));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        Assert.assertNull(StringUtils.getBytesUnchecked(null, charsetName));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, charsetName);
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
        final byte[] expected = "@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "  \t   \n   \r  ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("  \t   \n   \r  ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case to kill more mutants
        final String input = "Hello, World!"; // Different input string
        final byte[] expected2 = input.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
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
        final String input = "Test123!@#$%^&*()";
        final byte[] expected = input.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(input);
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
        final byte[] expected = StringUtils.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello World");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = StringUtils.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
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
        final byte[] expected = "Special Characters: #@!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Special Characters: #@!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "   ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("   ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Unicode Characters: \u00A9\u00AE".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Unicode Characters: \u00A9\u00AE");
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
        StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-16");
        Assert.fail("Expected " + IllegalStateException.class.getName());
    } catch (final IllegalStateException e) {
        // Expected
    }
}
@Test
public void test40() {
    try {
        StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
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
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(null, charsetName); // change input to null
        final String actual = StringUtils.newStringUtf16Be(null); // change input to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(null); // change input to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test44() {
        Assert.assertNull(StringUtils.newStringUtf8(BYTES_FIXTURE)); // change input to BYTES_FIXTURE
        Assert.assertNull(StringUtils.newStringIso8859_1(BYTES_FIXTURE)); // change input to BYTES_FIXTURE
        Assert.assertNull(StringUtils.newStringUsAscii(BYTES_FIXTURE)); // change input to BYTES_FIXTURE
        Assert.assertNull(StringUtils.newStringUtf16(BYTES_FIXTURE)); // change input to BYTES_FIXTURE
        Assert.assertNull(StringUtils.newStringUtf16Be(BYTES_FIXTURE)); // change input to BYTES_FIXTURE
        Assert.assertNull(StringUtils.newStringUtf16Le(BYTES_FIXTURE)); // change input to BYTES_FIXTURE
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(null); // change input to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(null); // change input to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(null); // change input to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        Assert.assertNull(StringUtils.newString(null, null)); // change input to null
    }
    @Test
    public void test49() {
        try {
            StringUtils.newString(null, "UNKNOWN"); // change input to null
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(null); // change input to null
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewStringRevised(charsetName);
        final String expected = "Hello, World!";
        final String actual = StringUtils.newStringUtf16Be(new byte[] {(byte)72, (byte)0, (byte)101, (byte)0, (byte)108, (byte)0, (byte)108, (byte)0, (byte)111, (byte)0, (byte)44, (byte)0, (byte)32, (byte)0, (byte)87, (byte)0, (byte)111, (byte)0, (byte)114, (byte)0, (byte)108, (byte)0, (byte)100, (byte)0, (byte)33, (byte)0});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewStringRevised(charsetName);
        final String expected = "Hello, World!";
        final String actual = StringUtils.newStringUtf8(new byte[] {(byte)72, (byte)101, (byte)108, (byte)108, (byte)111, (byte)44, (byte)32, (byte)87, (byte)111, (byte)114, (byte)108, (byte)100, (byte)33});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewStringRevised(charsetName);
        final String expected = "Hello, World!";
        final String actual = StringUtils.newStringUsAscii(new byte[] {(byte)72, (byte)101, (byte)108, (byte)108, (byte)111, (byte)44, (byte)32, (byte)87, (byte)111, (byte)114, (byte)108, (byte)100, (byte)33});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewStringRevised(charsetName);
        final String expected = "Hello, World!";
        final String actual = StringUtils.newStringIso8859_1(new byte[] {(byte)72, (byte)101, (byte)108, (byte)108, (byte)111, (byte)44, (byte)32, (byte)87, (byte)111, (byte)114, (byte)108, (byte)100, (byte)33});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewStringRevised(charsetName);
        final String expected = "Hello, World!";
        final String actual = StringUtils.newStringUtf16(new byte[] {(byte)0, (byte)72, (byte)0, (byte)101, (byte)0, (byte)108, (byte)0, (byte)108, (byte)0, (byte)111, (byte)0, (byte)44, (byte)0, (byte)32, (byte)0, (byte)87, (byte)0, (byte)111, (byte)0, (byte)114, (byte)0, (byte)108, (byte)0, (byte)100, (byte)0, (byte)33});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewStringRevised(charsetName);
        final String expected = "Hello, World!";
        final String actual = StringUtils.newStringUtf16Le(new byte[] {(byte)72, (byte)0, (byte)101, (byte)0, (byte)108, (byte)0, (byte)108, (byte)0, (byte)111, (byte)0, (byte)44, (byte)0, (byte)32, (byte)0, (byte)87, (byte)0, (byte)111, (byte)0, (byte)114, (byte)0, (byte)108, (byte)0, (byte)100, (byte)0, (byte)33});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() {
        final String expected = null;
        final String actual = StringUtils.newString(null, "UNKNOWN");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() {
        try {
            StringUtils.newString(new byte[] {(byte)72, (byte)101, (byte)108, (byte)108, (byte)111, (byte)44, (byte)32, (byte)87, (byte)111, (byte)114, (byte)108, (byte)100, (byte)33}, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    public void testNewStringRevised(final String charsetName) throws UnsupportedEncodingException {
        final String expected = "Hello, World!";
        final String actual = StringUtils.newString(new byte[] {(byte)72, (byte)101, (byte)108, (byte)108, (byte)111, (byte)44, (byte)32, (byte)87, (byte)111, (byte)114, (byte)108, (byte)100, (byte)33}, charsetName);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1
        final byte[] bytes1 = {72, 101, 108, 108, 111};
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2
        final byte[] bytes2 = {-1, -3, -1, -3, -1};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
    }
    @Test
    public void test60() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 3
        final String actual3 = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals("", actual3);
    }
    @Test
    public void test61() {
        final byte[] bytes = {};
        final String expected = new String(bytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        assertEquals(expected, actual);
    }
    @Test
    public void test62() {
        final byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newStringUsAscii(bytes);
        assertEquals(expected, actual);
    }
    @Test
    public void test63() {
        final byte[] bytes = {(byte) 0x21, (byte) 0x22, (byte) 0x23, (byte) 0x24, (byte) 0x25};
        final String expected = new String(bytes, Charsets.US_ASCII);
        final String actual = StringUtils.newStringUsAscii(bytes);
        assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(null, charsetName);
        final String actual = StringUtils.newStringUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final byte[] bytes = { 65, 66, 67 }; // Invalid UTF-16 bytes
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() {
        final byte[] bytes = new byte[0];
        final String expected = new String(bytes, Charsets.UTF_16BE);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() {
        final byte[] bytes = null;
        final String expected = null;
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() {
        final byte[] bytes = { -127 };
        final String expected = new String(bytes, Charsets.UTF_16BE);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() {
        final byte[] bytes = { 65, 66, 67, 68 };
        final String expected = new String(bytes, Charsets.UTF_16BE);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() {
        final byte[] bytes = null;
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertNull(actual);
    }
    @Test
    public void test73() {
        final byte[] bytes = new byte[0];
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals("", actual);
    }
    @Test
    public void test74() {
        final byte[] bytes = { 0x61 };
        final String expected = "\u0061";
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test75() {
        final byte[] bytes = { (byte) 0xFF, (byte) 0xFE, 0x61, 0x00 };
        final String expected = "\uFEFF\u0061";
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        
        // Input with all bytes set to zero
        byte[] zeroBytes = new byte[BYTES_FIXTURE.length];
        Arrays.fill(zeroBytes, (byte) 0);
        final String expectedZero = new String(zeroBytes, charsetName);
        final String actualZero = StringUtils.newStringUtf8(zeroBytes);
        Assert.assertEquals(expectedZero, actualZero);
        
        // Input with all bytes set to maximum value
        byte[] maxBytes = new byte[BYTES_FIXTURE.length];
        Arrays.fill(maxBytes, (byte) 255);
        final String expectedMax = new String(maxBytes, charsetName);
        final String actualMax = StringUtils.newStringUtf8(maxBytes);
        Assert.assertEquals(expectedMax, actualMax);
        
        // Input with mixed bytes
        byte[] mixedBytes = new byte[]{(byte) 72, (byte) 101, (byte) 108, (byte) 108, (byte) 111};
        final String expectedMixed = new String(mixedBytes, charsetName);
        final String actualMixed = StringUtils.newStringUtf8(mixedBytes);
        Assert.assertEquals(expectedMixed, actualMixed);
    }
    @Test
    public void test77() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case
        
        // Empty byte array
        final byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
    }
}