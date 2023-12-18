package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    assertTrue(MyClass.equals("Hello", "Hello"));
}
@Test
public void test1() {
    assertFalse(MyClass.equals("Hello", "World"));
}
@Test
public void test2() {
    assertFalse(MyClass.equals(null, "Hello"));
}
@Test
public void test3() {
    assertTrue(MyClass.equals(null, null));
}
@Test
public void test4() {
    StringBuilder sb = new StringBuilder("Hello");
    assertFalse(MyClass.equals("Hello", sb));
}
@Test
public void test5() {
    StringBuffer sb = new StringBuffer("Hello");
    assertFalse(MyClass.equals("Hello", sb));
}
@Test
public void test6() {
    CharSequence cs = "Hello";
    assertFalse(MyClass.equals("Hello", cs));
}
@Test
public void test7() {
    CharSequence cs1 = "Hello";
    CharSequence cs2 = new StringBuilder("Hello");
    assertTrue(MyClass.equals(cs1, cs2));
}
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test9() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test10() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String string = "HELLO WORLD";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String string = "Hello, World!";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String string = "Hello\0World";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final String string = "Hello\nWorld";
        final byte[] expected = string.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(string);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test14() {
    String string = null;
    Charset charset = Charset.forName("UTF-8");

    ByteBuffer result = getByteBuffer(string, charset);

    assertNull(result);
}
@Test
public void test15() {
    String string = "";
    Charset charset = Charset.forName("UTF-8");

    ByteBuffer result = getByteBuffer(string, charset);
    byte[] expectedBytes = new byte[0];
    ByteBuffer expected = ByteBuffer.wrap(expectedBytes);

    assertEquals(expected, result);
}
@Test
public void test16() {
    String string = "Hello World";
    Charset charset = Charset.forName("UTF-8");

    ByteBuffer result = getByteBuffer(string, charset);
    byte[] expectedBytes = string.getBytes(charset);
    ByteBuffer expected = ByteBuffer.wrap(expectedBytes);

    assertEquals(expected, result);
}
@Test
public void test17() {
    String string = "Hello World";
    Charset charset = Charset.forName("US-ASCII");

    ByteBuffer result = getByteBuffer(string, charset);
    byte[] expectedBytes = string.getBytes(charset);
    ByteBuffer expected = ByteBuffer.wrap(expectedBytes);

    assertEquals(expected, result);
}
@Test
public void test18() {
    String string = "";
    ByteBuffer expected = ByteBuffer.wrap(new byte[0]);

    ByteBuffer actual = getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
@Test
public void test19() {
    String string = "a";
    ByteBuffer expected = ByteBuffer.wrap(new byte[]{ 0x61 });

    ByteBuffer actual = getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
@Test
public void test20() {
    String string = "!@#$%^&*()";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(StandardCharsets.UTF_8));

    ByteBuffer actual = getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
@Test
public void test21() {
    String string = "こんにちは";
    ByteBuffer expected = ByteBuffer.wrap(string.getBytes(StandardCharsets.UTF_8));

    ByteBuffer actual = getByteBufferUtf8(string);

    assertEquals(expected, actual);
}
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "Hello".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Hello");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test25() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test32() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test36() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test40() {
        Assert.assertNull(StringUtils.getBytesUnchecked("", "UNKNOWN"));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        // Existing test case
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Regression test case 1: Empty string
        final String emptyString = "";
        final byte[] expected1 = emptyString.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2: String with special characters
        final String specialCharacters = "!@#$%^&*()_+-=[]{}/?.,<>~`";
        final byte[] expected2 = specialCharacters.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii(specialCharacters);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test case 3: String with unicode characters
        final String unicodeString = "Hello 你好 Привет 안녕하세요";
        final byte[] expected3 = unicodeString.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii(unicodeString);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        final byte[] expected2 = "Th‌i‍s‒i s".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16("Th‌i‍s‒i s");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: String with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test43() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Be("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: String with special characters
        final byte[] expected2 = "!@#$%^".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be("!@#$%^");
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: String with numbers
        final byte[] expected3 = "12345".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Be("12345");
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
@Test
public void test44() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test45() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test46() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    final String str = "Hello, 世界";
    final byte[] expected = str.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(str);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test47() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16LE";
    final String str = "1234567890";
    final byte[] expected = str.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Le(str);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test cases
        // Test with empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Test with special characters
        final String specialCharacters = "!@#$%^&*()_+\"\'\\/";
        final byte[] expectedSpecial = specialCharacters.getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf8(specialCharacters);
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
        
        // Test with non-ASCII characters
        final String nonAsciiCharacters = "ąćęłńóśźż";
        final byte[] expectedNonAscii = nonAsciiCharacters.getBytes(charsetName);
        final byte[] actualNonAscii = StringUtils.getBytesUtf8(nonAsciiCharacters);
        Assert.assertTrue(Arrays.equals(expectedNonAscii, actualNonAscii));
    }
    @Test
    public void test49() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN_ENCODING");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test50() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN_ENCODING");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test51() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test52() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UTF-9");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        final byte[] newBytes = {65, 66, 67, 68};
        final String expected = new String(newBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        final byte[] newBytes = {65, 66, 67, 68};
        final String expected = new String(newBytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        final byte[] newBytes = {65, 66, 67, 68};
        final String expected = new String(newBytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test60() {
        final byte[] newBytes = null;
        Assert.assertNull(StringUtils.newString(newBytes, "UNKNOWN"));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        final byte[] newBytes = {65, 66, 67, 68};
        final String expected = new String(newBytes, charsetName);
        final String actual = StringUtils.newStringUtf16(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        final byte[] newBytes = {65, 66, 67, 68};
        final String expected = new String(newBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        final byte[] newBytes = {65, 66, 67, 68};
        final String expected = new String(newBytes, charsetName);
        final String actual = StringUtils.newStringUtf8(newBytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        byte[] bytes = new byte[] { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Be(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        byte[] bytes = new byte[] { 0x41, 0x42, 0x43 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUsAscii(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        byte[] bytes = new byte[] { 0x41, 0x42, 0x43 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringIso8859_1(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        byte[] bytes = new byte[] { 0x00, 0x41, 0x00, 0x42, 0x00, 0x43};
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] bytes = new byte[] { 0x41, 0x00, 0x42, 0x00, 0x43, 0x00 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        byte[] bytes = new byte[] { 0x41, 0x42, 0x43 };
        final String expected = new String(bytes, charsetName);
        final String actual = StringUtils.newStringUtf8(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: empty input
        final String emptyInput = new String(new byte[0]);
        final String expectedEmpty = new String(emptyInput, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyInput.getBytes());
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Regression test case 2: input with special characters
        final byte[] specialCharsInput = {65, 66, -27, -2, -3};
        final String expectedSpecialChars = new String(specialCharsInput, charsetName);
        final String actualSpecialChars = StringUtils.newStringIso8859_1(specialCharsInput);
        Assert.assertEquals(expectedSpecialChars, actualSpecialChars);
    }
    @Test
    public void test75() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 3: input with one null byte
        final byte[] nullByteInput = {0};
        final String expectedNullByte = new String(nullByteInput, Charsets.ISO_8859_1);
        final String actualNullByte = StringUtils.newStringIso8859_1(nullByteInput);
        Assert.assertEquals(expectedNullByte, actualNullByte);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test 1: Empty byte array
        byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: Byte array with a single element
        byte[] singleByte = new byte[]{65};
        final String expected2 = new String(singleByte, charsetName);
        final String actual2 = StringUtils.newStringUsAscii(singleByte);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test 3: Byte array with special characters
        byte[] specialBytes = new byte[]{33, 64};
        final String expected3 = new String(specialBytes, charsetName);
        final String actual3 = StringUtils.newStringUsAscii(specialBytes);
        Assert.assertEquals(expected3, actual3);
        
        // Regression test 4: Byte array with null value
        byte[] nullBytes = null;
        final String expected4 = null;
        final String actual4 = StringUtils.newStringUsAscii(nullBytes);
        Assert.assertEquals(expected4, actual4);
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final byte[] bytes = "Hello".getBytes("UTF-8");
        final String expected = new String(bytes, "UTF-16");
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final byte[] bytes = "".getBytes("UTF-8");
        final String expected = new String(bytes, "UTF-16");
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final byte[] bytes = "12345".getBytes("UTF-8");
        final String expected = new String(bytes, "UTF-16");
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test80() throws UnsupportedEncodingException {
        final byte[] bytes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes("UTF-8");
        final String expected = new String(bytes, "UTF-16");
        final String actual = StringUtils.newStringUtf16(bytes);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test81() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression Test 1: Empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmptyBytes = new String(emptyBytes, charsetName);
        final String actualEmptyBytes = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expectedEmptyBytes, actualEmptyBytes);
        
        // Regression Test 2: Byte array with one element
        final byte[] singleByte = {98};
        final String expectedSingleByte = new String(singleByte, charsetName);
        final String actualSingleByte = StringUtils.newStringUtf16Be(singleByte);
        Assert.assertEquals(expectedSingleByte, actualSingleByte);
        
        // Regression Test 3: Byte array with even number of elements
        final byte[] evenBytes = {97, 98, 99, 100};
        final String expectedEvenBytes = new String(evenBytes, charsetName);
        final String actualEvenBytes = StringUtils.newStringUtf16Be(evenBytes);
        Assert.assertEquals(expectedEvenBytes, actualEvenBytes);
        
        // Regression Test 4: Byte array with odd number of elements
        final byte[] oddBytes = {97, 98, 99, 100, 101};
        final String expectedOddBytes = new String(oddBytes, charsetName);
        final String actualOddBytes = StringUtils.newStringUtf16Be(oddBytes);
        Assert.assertEquals(expectedOddBytes, actualOddBytes);
        
        // Regression Test 5: Byte array with all zeros
        final byte[] zeroBytes = {0, 0, 0, 0, 0, 0};
        final String expectedZeroBytes = new String(zeroBytes, charsetName);
        final String actualZeroBytes = StringUtils.newStringUtf16Be(zeroBytes);
        Assert.assertEquals(expectedZeroBytes, actualZeroBytes);
    }
    @Test
    public void test82() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test cases
        
        // Test with empty byte array
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
        
        // Test with special characters
        final byte[] specialBytes = {0x61, 0x00, (byte) 0xFF, 0x00};
        final String expectedSpecial = new String(specialBytes, charsetName);
        final String actualSpecial = StringUtils.newStringUtf16Le(specialBytes);
        Assert.assertEquals(expectedSpecial, actualSpecial);
        
        // Test with UTF-16LE encoding of a single character
        final byte[] singleCharBytes = {(byte) 0xAC, (byte) 0x20};
        final String expectedSingleChar = new String(singleCharBytes, charsetName);
        final String actualSingleChar = StringUtils.newStringUtf16Le(singleCharBytes);
        Assert.assertEquals(expectedSingleChar, actualSingleChar);
    }
    @Test
    public void test83() {
        // Test case for null input
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test84() throws UnsupportedEncodingException {
        // Test case for valid input
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test85() throws UnsupportedEncodingException {
        // Test case for empty input
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = "";
        final String actual = StringUtils.newStringUtf8(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test86() throws UnsupportedEncodingException {
        // Test case for input with special characters
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = "abc123!@#$";
        final String actual = StringUtils.newStringUtf8("abc123!@#$".getBytes(charsetName));
        Assert.assertEquals(expected, actual);
    }
}