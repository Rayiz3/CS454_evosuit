package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // Test when both cs1 and cs2 are null
    assertFalse(StringUtils.equals(null, null));

    // Test when cs1 is null and cs2 is not null
    assertFalse(StringUtils.equals(null, "abc"));

    // Test when cs1 is not null and cs2 is null
    assertFalse(StringUtils.equals("abc", null));

    // Test when cs1 and cs2 are empty strings
    assertTrue(StringUtils.equals("", ""));

    // Test when cs1 and cs2 are the same string
    assertTrue(StringUtils.equals("abc", "abc"));
    assertTrue(StringUtils.equals(new StringBuffer("abc"), new StringBuffer("abc")));
    
    // Test when cs1 and cs2 are different strings
    assertFalse(StringUtils.equals("abc", "def"));
    assertFalse(StringUtils.equals(new StringBuffer("abc"), new StringBuffer("def")));
    assertFalse(StringUtils.equals("abc", "abcd"));
    assertFalse(StringUtils.equals("abcd", "abc"));
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUncheckedWithNull(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUncheckedWithEmptyString(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedWithWhiteSpaceString(charsetName);
        final byte[] expected = {32};
        final byte[] actual = StringUtils.getBytesUtf8(" ");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUncheckedWithSpecialCharacters(charsetName);
        final byte[] expected = {104, 0, 101, 0, 108, 0, 108, 0, 111, 0, 33, 0};
        final byte[] actual = StringUtils.getBytesUtf16Le("hello!");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUncheckedWithNumericString(charsetName);
        final byte[] expected = {49, 50, 51};
        final byte[] actual = StringUtils.getBytesUsAscii("123");
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUncheckedWithUpperCaseString(charsetName);
        final byte[] expected = {76, 69, 84, 83, 32, 73, 78, 32, 84, 72, 69, 32, 83, 84, 82, 73, 78, 71};
        final byte[] actual = StringUtils.getBytesIso8859_1("LETS IN STRING");
        Assert.assertEquals(expected, actual);
    }
    private static void testGetBytesUncheckedWithNull(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytes(charsetName, null);
        Assert.assertEquals(expected, actual);
    }
    private static void testGetBytesUncheckedWithEmptyString(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytes(charsetName, "");
        Assert.assertEquals(expected, actual);
    }
    private static void testGetBytesUncheckedWithWhiteSpaceString(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = {32};
        final byte[] actual = StringUtils.getBytes(charsetName, " ");
        Assert.assertEquals(expected, actual);
    }
    private static void testGetBytesUncheckedWithSpecialCharacters(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = {104, 0, 101, 0, 108, 0, 108, 0, 111, 0, 33, 0};
        final byte[] actual = StringUtils.getBytes(charsetName, "hello!");
        Assert.assertEquals(expected, actual);
    }
    private static void testGetBytesUncheckedWithNumericString(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = {49, 50, 51};
        final byte[] actual = StringUtils.getBytes(charsetName, "123");
        Assert.assertEquals(expected, actual);
    }
    private static void testGetBytesUncheckedWithUpperCaseString(final String charsetName) throws UnsupportedEncodingException {
        final byte[] expected = {76, 69, 84, 83, 32, 73, 78, 32, 84, 72, 69, 32, 83, 84, 82, 73, 78, 71};
        final byte[] actual = StringUtils.getBytes(charsetName, "LETS IN STRING");
        Assert.assertEquals(expected, actual);
    }
@Test
public void test7() {
    String string = null;
    ByteBuffer byteBuffer = getByteBuffer(string, Charset.defaultCharset());
    assertNull(byteBuffer);
}
@Test
public void test8() {
    String string = "";
    ByteBuffer byteBuffer = getByteBuffer(string, Charset.defaultCharset());
    assertEquals(0, byteBuffer.remaining());
}
@Test
public void test9() {
    String string = "Hello World!";
    ByteBuffer byteBuffer = getByteBuffer(string, Charset.defaultCharset());
    assertEquals(string.getBytes().length, byteBuffer.remaining());
}
@Test
public void test10() {
    String string = "Hello World!";
    Charset charset = Charset.forName("UTF-16");
    ByteBuffer byteBuffer = getByteBuffer(string, charset);
    assertEquals(string.getBytes(charset).length, byteBuffer.remaining());
}
@Test
public void test11() {
    // Test with empty string
    String input1 = "";
    ByteBuffer expected1 = ByteBuffer.wrap("".getBytes(StandardCharsets.UTF_8));
    assertEquals(expected1, MyClass.getByteBufferUtf8(input1));

    // Test with string containing only ASCII characters
    String input2 = "Hello World";
    ByteBuffer expected2 = ByteBuffer.wrap("Hello World".getBytes(StandardCharsets.UTF_8));
    assertEquals(expected2, MyClass.getByteBufferUtf8(input2));

    // Test with string containing Unicode characters
    String input3 = "こんにちは";
    ByteBuffer expected3 = ByteBuffer.wrap("こんにちは".getBytes(StandardCharsets.UTF_8));
    assertEquals(expected3, MyClass.getByteBufferUtf8(input3));
}
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        
        // Regression Test Cases
        final byte[] expected1 = "Hello".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesIso8859_1("Hello");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        final byte[] expected2 = "".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "!@#".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("!@#");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //Regression Test Cases
        final byte[] expected2 = "regression test".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii("regression test");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "123".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii("123");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //Regression Test Cases
        final byte[] expected2 = "regression test".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("regression test");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "123".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("123");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //Regression Test Cases
        final byte[] expected2 = "regression test".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("regression test");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "123".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("123");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test16() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        //Regression Test Cases
        try {
            StringUtils.getBytesUnchecked("regression test", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
        try {
            StringUtils.getBytesUnchecked("123", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //Regression Test Cases
        final byte[] expected2 = "regression test".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesIso8859_1("regression test");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "123".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesIso8859_1("123");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //Regression Test Cases
        final byte[] expected2 = "regression test".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("regression test");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "123".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("123");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        //Regression Test Cases
        final byte[] expected2 = "regression test".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf8("regression test");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        final byte[] expected3 = "123".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf8("123");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test20() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
        
        //Regression Test Cases
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
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
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "   ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("   ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupporteEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = " ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(" ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "test".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "Hello World!".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Hello World!");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Test with empty string
        final String input1 = "";
        final byte[] expected1 = input1.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le(input1);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Test with special characters
        final String input2 = "Special_@&*^%$#^*()_Characters";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Test with long string
        final String input3 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
                + "Sed tincidunt magna sed ipsum ullamcorper, non gravida mauris eleifend. "
                + "Nunc vitae dolor at elit semper sagittis.";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedRegression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE_REPLACEMENT);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUncheckedRegression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE_REPLACEMENT);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() {
        try {
            StringUtils.getBytesUnchecked("Test String", "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test35() {
        try {
            StringUtils.newString(new byte[] { 65, 66, 67 }, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        // Different input
        final byte[] bytes = {65, 66, 67};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        // Different input
        final byte[] bytes = {97, 98, 99};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        // Different input
        final byte[] bytes = {104, 101, 108, 108, 111};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test43() {
        Assert.assertNull(StringUtils.newString(null, "UTF-8"));
    }
    @Test
    public void test44() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test45() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        // Different input
        final byte[] bytes = {72, 101, 108, 108, 111};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test46() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        // Different input
        final byte[] bytes = {105, 116};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test48() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test49() {
        byte[] bytes = {97, 98, 99};
        Assert.assertNull(StringUtils.newStringUtf8(bytes));
        bytes = new byte[] {65, 66, 67};
        Assert.assertNull(StringUtils.newStringIso8859_1(bytes));
        bytes = new byte[] {104, 101, 108, 108};
        Assert.assertNull(StringUtils.newStringUsAscii(bytes));
        bytes = new byte[] {72, 101, 108, 108};
        Assert.assertNull(StringUtils.newStringUtf16(bytes));
        bytes = new byte[] {105, 116};
        Assert.assertNull(StringUtils.newStringUtf16Be(bytes));
        bytes = new byte[] {104, 105};
        Assert.assertNull(StringUtils.newStringUtf16Le(bytes));
    }
    @Test
    public void test50() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "INVALID_ENCODING");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        // Different input
        final byte[] bytes = {49, 50, 51};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
      
        // Test with null input
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        
        // Test with empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with special characters
        final byte[] specialBytes = {
            (byte) 0x00, (byte) 0x20, (byte) 0xD8, (byte) 0x05, (byte) 0xDC, (byte) 0x00, (byte) 0x20,
            (byte) 0x4D, (byte) 0x65, (byte) 0x72, (byte) 0x63, (byte) 0x65, (byte) 0x64, (byte) 0x65,
            (byte) 0x73, (byte) 0x2E
        };
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUtf16Be(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
      
        // Test with null input
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        
        // Test with empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with special characters
        final byte[] specialBytes = {
            (byte) 0x48, (byte) 0x65, (byte) 0x6C, (byte) 0x6C, (byte) 0x6F, (byte) 0x2C, (byte) 0x20,
            (byte) 0x57, (byte) 0x6F, (byte) 0x72, (byte) 0x6C, (byte) 0x64
        };
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUsAscii(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
      
        // Test with null input
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Test with empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with special characters
        final byte[] specialBytes = {
            (byte) 0x40, (byte) 0x61, (byte) 0x72, (byte) 0xE1, (byte) 0x74, (byte) 0x72, (byte) 0xF3,
            (byte) 0x6E, (byte) 0x40
        };
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringIso8859_1(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
      
        // Test with null input
        Assert.assertNull(StringUtils.newStringUtf16(null));
        
        // Test with empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with special characters
        final byte[] specialBytes = {
            (byte) 0x00, (byte) 0x48, (byte) 0x00, (byte) 0x65, (byte) 0x00, (byte) 0x6C, (byte) 0x00,
            (byte) 0x6C, (byte) 0x00, (byte) 0x6F
        };
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUtf16(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
      
        // Test with null input
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Test with empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with special characters
        final byte[] specialBytes = {
            (byte) 0x48, (byte) 0x00, (byte) 0x65, (byte) 0x00, (byte) 0x6C, (byte) 0x00, (byte) 0x6C,
            (byte) 0x00, (byte) 0x6F, (byte) 0x00
        };
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUtf16Le(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
      
        // Test with null input
        Assert.assertNull(StringUtils.newStringUtf8(null));
        
        // Test with empty input
        final String expectedEmpty = new String(new byte[0], charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with special characters
        final byte[] specialBytes = {
            (byte) 0x48, (byte) 0xC3, (byte) 0xA9, (byte) 0x6C, (byte) 0x6C,
            (byte) 0xC3, (byte) 0xB4
        };
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUtf8(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
    }
    @Test
    public void test60() {
        final String expected = new String(new byte[]{97, 98, 99}, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{97, 98, 99});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test61() {
        final String expected = new String(new byte[]{65, 66, 67}, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{65, 66, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() {
        final String expected = new String(new byte[]{}, Charsets.ISO_8859_1);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        // original test case
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // additional test case 1
        final byte[] bytes1 = {72, 101, 108, 108, 111}; // "Hello"
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // additional test case 2
        final byte[] bytes2 = {}; // empty byte array
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // additional test case 3
        final byte[] bytes3 = {33, 64, 35, 36, 37}; // "!@#$%"
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression Test Case 1: Empty byte array
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16(new byte[0]);
        Assert.assertEquals(expected1, actual1);

        // Regression Test Case 2: Byte array with null value
        final String expected2 = new String(new byte[] {0x00, 0x00, 0x00, 0x00}, charsetName);
        final String actual2 = StringUtils.newStringUtf16(new byte[] {0x00, 0x00, 0x00, 0x00});
        Assert.assertEquals(expected2, actual2);

        // Regression Test Case 3: Byte array with non-UTF-16 characters
        final String expected3 = new String(new byte[] {0x41, 0x42, 0x43, 0x44}, charsetName);
        final String actual3 = StringUtils.newStringUtf16(new byte[] {0x41, 0x42, 0x43, 0x44});
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION_1, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_REGRESSION_1);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_REGRESSION_2, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_REGRESSION_2);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final String expected = new String(new byte[0], charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = { 97 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = { 102, 111, 111, 32, 98, 97, 114, 32, 98, 97 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
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
    public void test71() {
        final byte[] bytes = {72, 101, 108, 108, 111}; // Hello
        final String expected = "Hello";
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
}