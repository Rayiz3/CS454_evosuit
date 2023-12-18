package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    assertFalse(StringUtils.equals(null, "abc"));
    assertFalse(StringUtils.equals("abc", null));
    assertTrue(StringUtils.equals(null, null));
    
    assertTrue(StringUtils.equals("abc", "abc"));
    assertFalse(StringUtils.equals("abc", "ABC"));
    
    assertFalse(StringUtils.equals("abc", "abcd"));
    assertFalse(StringUtils.equals("abcd", "abc"));
    
    assertTrue(StringUtils.equals("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"));
    assertFalse(StringUtils.equals("abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    
    assertTrue(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("abc")));
    assertFalse(StringUtils.equals(new StringBuilder("abc"), new StringBuilder("ABC")));
    
    assertTrue(StringUtils.equals(new StringBuffer("abc"), new StringBuffer("abc")));
    assertFalse(StringUtils.equals(new StringBuffer("abc"), new StringBuffer("ABC")));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello, world!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello, world!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test7() {
    ByteBuffer buffer = getByteBuffer(null, StandardCharsets.UTF_8);
    assertNull(buffer);
}
@Test
public void test8() {
    ByteBuffer buffer = getByteBuffer("", StandardCharsets.UTF_8);
    assertEquals(0, buffer.limit());
}
@Test
public void test9() {
    ByteBuffer buffer = getByteBuffer("Hello World", StandardCharsets.UTF_8);
    assertEquals("Hello World", new String(buffer.array(), StandardCharsets.UTF_8));
}
@Test
public void test10() {
    ByteBuffer buffer = getByteBuffer("Hello World", StandardCharsets.US_ASCII);
    assertEquals("Hello World", new String(buffer.array(), StandardCharsets.US_ASCII));
}
@Test
public void test11() {
    String input = "";
    ByteBuffer expected = ByteBuffer.wrap(new byte[]{});
    ByteBuffer result = getByteBufferUtf8(input);
    assertEquals(expected, result);
}
@Test
public void test12() {
    String input = "Hello World";
    ByteBuffer expected = ByteBuffer.wrap(new byte[]{72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100});
    ByteBuffer result = getByteBufferUtf8(input);
    assertEquals(expected, result);
}
@Test
public void test13() {
    String input = "αβγδε";
    ByteBuffer expected = ByteBuffer.wrap(new byte[]{-59, -121, -59, -122, -59, -117, -59, -120, -59, -113});
    ByteBuffer result = getByteBufferUtf8(input);
    assertEquals(expected, result);
}
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test case 1: empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test case 2: string with special characters
        final byte[] expected2 = "Thïs ïs å strïng wïth spécïål chåråctèrs".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("Thïs ïs å strïng wïth spécïål chåråctèrs");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test case 3: string with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression test case 4: string with spaces
        final byte[] expected4 = "This is a string with spaces".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesIso8859_1("This is a string with spaces");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
        
        // Regression test case 5: string with uppercase letters
        final byte[] expected5 = "THIS IS A STRING WITH UPPERCASE LETTERS".getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesIso8859_1("THIS IS A STRING WITH UPPERCASE LETTERS");
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test25() {
        try {
            StringUtils.getBytesUnchecked("Regression Test 1", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test26() {
        try {
            StringUtils.getBytesUnchecked("Regression Test 2", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test 2");
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
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test37() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test38() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello world".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Hello world");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Regression test 1: empty string
        byte[] expected1 = "".getBytes(charsetName);
        byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: string with special characters
        byte[] expected2 = "Hello World!@#$%^&*()".getBytes(charsetName);
        byte[] actual2 = StringUtils.getBytesUtf16("Hello World!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: string with numbers
        byte[] expected3 = "12345".getBytes(charsetName);
        byte[] actual3 = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Original test
        testGetBytesUnchecked(charsetName);
        byte[] expectedOriginal = STRING_FIXTURE.getBytes(charsetName);
        byte[] actualOriginal = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expectedOriginal, actualOriginal));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test Case 1: Empty String
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression Test Case 2: Chinese characters
        final byte[] expected2 = "你好".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("你好");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression Test Case 3: Special characters
        final byte[] expected3 = "@#$%^&*".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("@#$%^&*");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression Test Case 4: Numbers
        final byte[] expected4 = "123456".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16Be("123456");
        Assert.assertTrue(Arrays.equals(expected4, actual4));
        
        // Regression Test Case 5: Mix of characters
        final byte[] expected5 = "abc123@#$".getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesUtf16Be("abc123@#$");
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test Case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression Test Case 2: String with special characters
        final String input2 = "This is a string with special characters: 🌟🌍🌈";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression Test Case 3: String with numbers
        final String input3 = "1234567890";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
        
        // Regression Test Case 4: String with spaces
        final String input4 = "   ";
        final byte[] expected4 = input4.getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16Le(input4);
        Assert.assertTrue(Arrays.equals(expected4, actual4));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression3(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test47() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final byte[] changedBytes = new byte[] {(byte) 0x48, (byte) 0x65, (byte) 0x6C, (byte) 0x6C, (byte) 0x6F};
        final String actual = StringUtils.newStringUtf16Be(changedBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final byte[] changedBytes = new byte[] {(byte) 0x48, (byte) 0x65, (byte) 0x6C, (byte) 0x6C, (byte) 0x6F};
        final String actual = StringUtils.newStringUtf16Be(changedBytes);
        Assert.assertNotEquals("Hello", actual);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final byte[] changedBytes = new byte[] {(byte) 0x48, (byte) 0x65, (byte) 0x6C, (byte) 0x6C, (byte) 0x6F};
        final String actual = StringUtils.newStringUsAscii(changedBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final byte[] changedBytes = new byte[] {(byte) 0x48, (byte) 0x65, (byte) 0x6C, (byte) 0x6C, (byte) 0x6F};
        final String actual = StringUtils.newStringUsAscii(changedBytes);
        Assert.assertNotEquals("Hello", actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(REVENUE_BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(REVENUE_BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(REVENUE_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(REVENUE_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(REVENUE_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(REVENUE_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(REVENUE_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(REVENUE_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(REVENUE_BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(REVENUE_BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test60() {
        try {
            StringUtils.newString(REVENUE_BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(REVENUE_BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(REVENUE_BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: byte array with special characters
        final byte[] bytes2 = {(byte) 201, (byte) 235, (byte) 168};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringIso8859_1(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: byte array with null characters
        final byte[] bytes3 = {0, 0, 0};
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringIso8859_1(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test63() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test 1: empty byte array
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Regression test 2: non-null byte array
        final byte[] bytes2 = {1, 2, 3};
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes2));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";

        // Original test case with BYTES_FIXTURE as input
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test case 1 with an empty byte array as input
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);

        // Regression test case 2 with a byte array containing only one byte as input
        final byte[] singleByte = {65};
        final String expectedSingleByte = new String(singleByte, charsetName);
        final String actualSingleByte = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expectedSingleByte, actualSingleByte);

        // Regression test case 3 with a byte array containing special characters as input
        final byte[] specialChars = {35, 36, 37, 38, 64, 91, 92, 93};
        final String expectedSpecialChars = new String(specialChars, charsetName);
        final String actualSpecialChars = StringUtils.newStringUsAscii(specialChars);
        Assert.assertEquals(expectedSpecialChars, actualSpecialChars);
    }
@Test
public void test65() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(new byte[] {5, 8, 3, 1});
    Assert.assertNotEquals(expected, actual);
}
@Test
public void test66() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(new byte[] {0, 0, 0, 0});
    Assert.assertNotEquals(expected, actual);
}
@Test
public void test67() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf16(new byte[] {-1, -1, -1, -1});
    Assert.assertNotEquals(expected, actual);
}
@Test
public void test68() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    testNewString(charsetName);

    // Regression test 1: Changing the input byte array to contain a single character
    final byte[] bytes1 = new byte[]{0x00, 0x41}; // Represents 'A' in UTF-16BE
    final String expected1 = new String(bytes1, charsetName);
    final String actual1 = StringUtils.newStringUtf16Be(bytes1);
    Assert.assertEquals(expected1, actual1);

    // Regression test 2: Changing the input byte array to contain multiple characters
    final byte[] bytes2 = new byte[]{0x00, 0x41, 0x00, 0x42}; // Represents 'AB' in UTF-16BE
    final String expected2 = new String(bytes2, charsetName);
    final String actual2 = StringUtils.newStringUtf16Be(bytes2);
    Assert.assertEquals(expected2, actual2);

    // Regression test 3: Changing the input byte array to be empty
    final byte[] bytes3 = new byte[0];
    final String expected3 = new String(bytes3, charsetName);
    final String actual3 = StringUtils.newStringUtf16Be(bytes3);
    Assert.assertEquals(expected3, actual3);

    // Regression test 4: Changing the input byte array to contain special characters
    final byte[] bytes4 = new byte[]{0x20, 0x16, 0x20, 0x14}; // Represents '‖—' in UTF-16BE
    final String expected4 = new String(bytes4, charsetName);
    final String actual4 = StringUtils.newStringUtf16Be(bytes4);
    Assert.assertEquals(expected4, actual4);

    // Regression test 5: Changing the input byte array to contain invalid byte sequence
    final byte[] bytes5 = new byte[]{0x41, 0x00}; // Represents invalid byte sequence in UTF-16BE
    final String expected5 = new String(bytes5, charsetName);
    final String actual5 = StringUtils.newStringUtf16Be(bytes5);
    Assert.assertEquals(expected5, actual5);
}
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {}));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[] {72, 101, 108, 108, 111}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] {72, 101, 108, 108, 111});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test74() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[] {119, 111, 114, 108, 100}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] {119, 111, 114, 108, 100});
        Assert.assertEquals(expected, actual);
    }
}