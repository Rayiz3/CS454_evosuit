package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
@Test
public void test0() {
    // test case 1: cs1 and cs2 are null
    CharSequence cs1 = null;
    CharSequence cs2 = null;
    boolean result = StringUtils.equals(cs1, cs2);
    assertTrue(result);
    
    // test case 2: cs1 is null and cs2 is empty string
    cs1 = null;
    cs2 = "";
    result = StringUtils.equals(cs1, cs2);
    assertFalse(result);
    
    // test case 3: cs1 is empty string and cs2 is null
    cs1 = "";
    cs2 = null;
    result = StringUtils.equals(cs1, cs2);
    assertFalse(result);
    
    // test case 4: cs1 and cs2 are empty strings
    cs1 = "";
    cs2 = "";
    result = StringUtils.equals(cs1, cs2);
    assertTrue(result);
    
    // test case 5: cs1 and cs2 are different non-null strings
    cs1 = "abc";
    cs2 = "def";
    result = StringUtils.equals(cs1, cs2);
    assertFalse(result);
    
    // test case 6: cs1 and cs2 are different non-null strings with different cases
    cs1 = "AbC";
    cs2 = "aBc";
    result = StringUtils.equals(cs1, cs2);
    assertFalse(result);
    
    // test case 7: cs1 and cs2 are equal non-null strings
    cs1 = "abc";
    cs2 = "abc";
    result = StringUtils.equals(cs1, cs2);
    assertTrue(result);
    
    // test case 8: cs1 and cs2 are equal non-null strings with different cases
    cs1 = "AbC";
    cs2 = "AbC";
    result = StringUtils.equals(cs1, cs2);
    assertTrue(result);
}
    @Test
    public void test1() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test2() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
@Test
public void test7() {
    ByteBuffer byteBuffer = getByteBuffer("", StandardCharsets.UTF_8);
    Assert.assertNotNull(byteBuffer);
    Assert.assertEquals(0, byteBuffer.limit());
}
@Test
public void test8() {
    ByteBuffer byteBuffer = getByteBuffer(null, StandardCharsets.UTF_8);
    Assert.assertNull(byteBuffer);
}
@Test
public void test9() {
    ByteBuffer byteBuffer = getByteBuffer("Hello World!", StandardCharsets.UTF_8);
    Assert.assertNotNull(byteBuffer);
    Assert.assertEquals(12, byteBuffer.limit());
    byte[] byteArray = new byte[byteBuffer.remaining()];
    byteBuffer.get(byteArray);
    Assert.assertEquals("Hello World!", new String(byteArray, StandardCharsets.UTF_8));
}
    @Test
    public void test10() {
        ByteBuffer byteBuffer = getByteBufferUtf8("");
        assertEquals(0, byteBuffer.remaining());
    }
    @Test
    public void test11() {
        String input = "Hello World";
        ByteBuffer byteBuffer = getByteBufferUtf8(input);
        assertEquals(byteBuffer.remaining(), input.getBytes(StandardCharsets.UTF_8).length);
    }
    @Test
    public void test12() {
        String input = "€$§@";
        ByteBuffer byteBuffer = getByteBufferUtf8(input);
        assertEquals(byteBuffer.remaining(), input.getBytes(StandardCharsets.UTF_8).length);
    }
    @Test
    public void test13() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append("text");
        }
        String input = sb.toString();
        ByteBuffer byteBuffer = getByteBufferUtf8(input);
        assertEquals(byteBuffer.remaining(), input.getBytes(StandardCharsets.UTF_8).length);
    }
    @Test
    public void test14() {
        assertThrows(NullPointerException.class, () -> {
            getByteBufferUtf8(null);
        });
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 1");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 2");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test17() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("Regression Test 3");
        Assert.assertTrue(Arrays.equals(expected, actual));
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
        final byte[] expected = "differentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("differentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertEquals(expected, actual);
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
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "differentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be("differentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Be(null);
        Assert.assertEquals(expected, actual);
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
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "differentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("differentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16Le(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "differentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("differentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
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
        final byte[] expected = "differentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("differentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test37() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test38() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test39() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "differentString".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("differentString");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test41() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf8(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test42() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test43() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test44() {
        final String charsetName = "UTF-8";
        try {
            StringUtils.getBytesUnchecked(null, charsetName);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test45() {
        final String string = "string";
        try {
            StringUtils.getBytesUnchecked(string, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test46() {
        final String string = "";
        try {
            StringUtils.getBytesUnchecked(string, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression test 1: Test with an empty string
        final String emptyString = "";
        testGetBytesUnchecked(charsetName);
        final byte[] expected1 = emptyString.getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUsAscii(emptyString);
        Assert.assertTrue(Arrays.equals(expected1, actual1));
        
        // Regression test 2: Test with a string without any ASCII characters
        final String nonAsciiString = "안녕하세요";
        testGetBytesUnchecked(charsetName);
        final byte[] expected2 = nonAsciiString.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUsAscii(nonAsciiString);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
        
        // Regression test 3: Test with a string that contains special ASCII characters
        final String specialAsciiString = "!@#$%^&*()";
        testGetBytesUnchecked(charsetName);
        final byte[] expected3 = specialAsciiString.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUsAscii(specialAsciiString);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);

        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2: String with special characters
        final String input2 = "Hello, 世界!";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test case 3: String with all lowercase characters
        final String input3 = "abcdef";
        final byte[] expected3 = input3.getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16(input3);
        Assert.assertTrue(Arrays.equals(expected3, actual3));
    }
    @Test
    public void test49() throws UnsupportedEncodingException {
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
        final String input2 = "特殊字符";
        final byte[] expected2 = input2.getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Be(input2);
        Assert.assertTrue(Arrays.equals(expected2, actual2));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";

        // Regression test case 1: Empty string
        final byte[] expected1 = "".getBytes(charsetName);
        final byte[] actual1 = StringUtils.getBytesUtf16Le("");
        Assert.assertTrue(Arrays.equals(expected1, actual1));

        // Regression test case 2: String with special characters
        final byte[] expected2 = "特殊字符".getBytes(charsetName);
        final byte[] actual2 = StringUtils.getBytesUtf16Le("特殊字符");
        Assert.assertTrue(Arrays.equals(expected2, actual2));

        // Regression test case 3: String with spaces
        final byte[] expected3 = "  String with spaces  ".getBytes(charsetName);
        final byte[] actual3 = StringUtils.getBytesUtf16Le("  String with spaces  ");
        Assert.assertTrue(Arrays.equals(expected3, actual3));

        // Regression test case 4: String with digits
        final byte[] expected4 = "123456".getBytes(charsetName);
        final byte[] actual4 = StringUtils.getBytesUtf16Le("123456");
        Assert.assertTrue(Arrays.equals(expected4, actual4));

        // Regression test case 5: String with punctuation
        final byte[] expected5 = "!@#$%^&*()".getBytes(charsetName);
        final byte[] actual5 = StringUtils.getBytesUtf16Le("!@#$%^&*()");
        Assert.assertTrue(Arrays.equals(expected5, actual5));
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));
        
        // Regression Test Case 1 - Empty string
        final byte[] expectedEmpty = "".getBytes(charsetName);
        final byte[] actualEmpty = StringUtils.getBytesUtf8("");
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));
        
        // Regression Test Case 2 - String with special characters
        final byte[] expectedSpecial = "Ab2!@#$%^&*()_+?><\":}{|][\\';/.,`~".getBytes(charsetName);
        final byte[] actualSpecial = StringUtils.getBytesUtf8("Ab2!@#$%^&*()_+?><\":}{|][\\';/.,`~");
        Assert.assertTrue(Arrays.equals(expectedSpecial, actualSpecial));
        
        // Regression Test Case 3 - String with unicode characters
        final byte[] expectedUnicode = "\u00EE\u571F\uD83D\uDE0A\uD83D\uDCA9".getBytes(charsetName);
        final byte[] actualUnicode = StringUtils.getBytesUtf8("\u00EE\u571F\uD83D\uDE0A\uD83D\uDCA9");
        Assert.assertTrue(Arrays.equals(expectedUnicode, actualUnicode));
    }
    @Test
    public void test52() {
        try {
            // Regression test 1: Changing the charset name from "UNKNOWN" to "UTF-8"
            StringUtils.getBytesUnchecked(STRING_FIXTURE, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test53() {
        try {
            // Regression test 2: Changing the charset name from "UNKNOWN" to "ISO-8859-1"
            StringUtils.newString(BYTES_FIXTURE, "ISO-8859-1");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE_regress, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE_regress);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regress, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE_regress);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test56() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regress, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE_regress);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test57() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN_regress"));
    }
    @Test
    public void test58() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regress, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE_regress);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE_regress, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE_regress);
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
    public void test61() {
        try {
            StringUtils.newString(BYTES_FIXTURE_regress, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test62() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_regress, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE_regress);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(new byte[] {65, 66, 67}); // changed input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[] {68, 69, 70}); // changed input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test65() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[] {71, 72, 73}); // changed input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test66() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(new byte[] {74, 75, 76}); // changed input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(new byte[] {77, 78, 79}); // changed input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test70() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test71() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[] {80, 81, 82}); // changed input
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test72() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        // Change the input value of the method to null
        final String actual = StringUtils.newStringIso8859_1(null);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test73() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        // Change the input value of the method to an empty byte array
        final String actual = StringUtils.newStringIso8859_1(new byte[0]);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test74() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        // Change the input value of the method to a byte array with invalid characters
        final String actual = StringUtils.newStringIso8859_1(new byte[]{127, -1, 0});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test75() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{65, 66, 67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test77() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(new byte[]{97, 98, 99});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test78() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        
        // Regression Test 1: Changing the input value to empty byte array
        byte[] emptyBytes = new byte[0];
        final String expected1 = new String(emptyBytes, charsetName);
        final String actual1 = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expected1, actual1);
        
        // Regression Test 2: Changing the input value to a byte array with a single null byte
        byte[] singleNullByte = new byte[]{0};
        final String expected2 = new String(singleNullByte, charsetName);
        final String actual2 = StringUtils.newStringUtf16(singleNullByte);
        Assert.assertEquals(expected2, actual2);
        
        // Regression Test 3: Changing the input value to a byte array with a single non-null byte
        byte[] singleNonNullByte = new byte[]{65};
        final String expected3 = new String(singleNonNullByte, charsetName);
        final String actual3 = StringUtils.newStringUtf16(singleNonNullByte);
        Assert.assertEquals(expected3, actual3);
        
        // ... add more regression tests here ...
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1: Empty input
        final String expected1 = new String(new byte[0], charsetName);
        final String actual1 = StringUtils.newStringUtf16Be(new byte[0]);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test case 2: Input with a single character
        final byte[] bytes2 = new byte[] { 0x00, 0x41 }; // 'A' in UTF-16BE
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Be(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Regression test case 3: Input with special characters
        final byte[] bytes3 = new byte[] { 0x00, 0x5A, 0x00, 0x61, 0x00, 0x6F, 0x00, 0x21 }; // 'Zao!' in UTF-16BE
        final String expected3 = new String(bytes3, charsetName);
        final String actual3 = StringUtils.newStringUtf16Be(bytes3);
        Assert.assertEquals(expected3, actual3);
    }
    @Test
    public void test80() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        
        // Regression test 1: empty input
        final byte[] bytes1 = new byte[0];
        final String expected1 = new String(bytes1, charsetName);
        final String actual1 = StringUtils.newStringUtf16Le(bytes1);
        Assert.assertEquals(expected1, actual1);
        
        // Regression test 2: input with special characters
        final byte[] bytes2 = new byte[] {-2, -1, 0, 97, -5, -17};
        final String expected2 = new String(bytes2, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(bytes2);
        Assert.assertEquals(expected2, actual2);
        
        // Existing test case
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test81() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[]{0x00, 0x00, 0x00}));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test82() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x7F, 0x2A, 0x5D}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{0x7F, 0x2A, 0x5D});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test83() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(new byte[]{0x54}, charsetName);
        final String actual = StringUtils.newStringUtf8(new byte[]{0x54});
        Assert.assertEquals(expected, actual);
    }
}