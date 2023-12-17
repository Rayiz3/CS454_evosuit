package org.apache.commons.codec.binary;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class StringUtils_LLMTest {
    @Test
    public void test0() {
        // Regression test cases for different inputs

        // Changing null, null to null, "abc"
        Assert.assertTrue(StringUtils.equals(null, "abc"));

        // Changing "abc", "abc" to "abc", null
        Assert.assertFalse(StringUtils.equals("abc", null));

        // Changing "abc", null to null, null
        Assert.assertTrue(StringUtils.equals(null, null));

        // Changing "abc", "abc" to "abc", "abcd"
        Assert.assertFalse(StringUtils.equals("abc", "abcd"));

        // Changing "abc", "ABC" to "abc", "abc"
        Assert.assertTrue(StringUtils.equals("abc", "abc"));
    }
    @Test
    public void test1() {
        // Regression test cases for different inputs

        // Changing new StringBuilder("abc"), null to new StringBuilder("abc"), "abc"
        Assert.assertFalse(StringUtils.equals(new StringBuilder("abc"), "abc"));

        // Changing null, new StringBuilder("abc") to "abc", new StringBuilder("abc")
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
    }
    @Test
    public void test2() {
        // Regression test case for different inputs

        // Changing new StringBuilder("abc"), "abcd" to null, "abcd"
        Assert.assertFalse(StringUtils.equals(null, "abcd"));

        // Changing "abc", new StringBuilder("abc") to "abc", new StringBuilder("abc")
        Assert.assertTrue(StringUtils.equals("abc", new StringBuilder("abc")));
    }
    @Test
    public void test3() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUsAscii(null);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Le("");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test5() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[0];
        final byte[] actual = StringUtils.getBytesUtf16Be(" ");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test6() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] {-24, -22, -20, -18, -16, -14, -12, -10, -8, -6, -4, -2};
        final byte[] actual = StringUtils.getBytesIso8859_1("������������");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test7() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] {104, 101, 108, 108, 111, 10, 116, 104, 101, 114, 101, 10};
        final byte[] actual = StringUtils.getBytesUtf8("hello\nthere\n");
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test8() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = new byte[] {0, 49, 0, 50, 0, 51, 0, 52, 0, 53, 0, 54, 0, 55, 0, 56, 0, 57};
        final byte[] actual = StringUtils.getBytesUtf16("123456789");
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
        final byte[] expected = "éüñç".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("éüñç");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test11() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "12345".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1("12345");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test12() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE_regression1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test13() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE_regression1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test14() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE_regression1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test15() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE_regression1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test16() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE_regression1, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test17() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test18() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE_regression1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test19() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression1(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE_regression1);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test20() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16(STRING_FIXTURE_regression2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test21() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii(STRING_FIXTURE_regression2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test22() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Be(STRING_FIXTURE_regression2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test23() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesIso8859_1(STRING_FIXTURE_regression2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test24() {
        try {
            StringUtils.getBytesUnchecked(STRING_FIXTURE_regression2, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test25() {
        Assert.assertNull(StringUtils.getBytesUnchecked(null, "UNKNOWN"));
    }
    @Test
    public void test26() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf8(STRING_FIXTURE_regression2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test27() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked_regression2(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE_regression2);
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test28() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test29() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "特殊çĥαŗαçŧëŗƽ".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("特殊çĥαŗαçŧëŗƽ");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test30() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "UPPERCASE".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("UPPERCASE");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test31() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "lowercase".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUsAscii("lowercase");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test32() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test33() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "特殊字符".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("特殊字符");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test34() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = "1234567890".getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16("1234567890");
        Assert.assertTrue(Arrays.equals(expected, actual));
    }
    @Test
    public void test35() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = null;
        final byte[] actual = StringUtils.getBytesUtf16(null);
        Assert.assertEquals(expected, actual);
    }
@Test
public void test36() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test37() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    final byte[] expected = null;
    final byte[] actual = StringUtils.getBytesUtf16Be(null);
    Assert.assertEquals(expected, actual);
}
@Test
public void test38() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    final String input = "Special Characters: !@#$%^&*()_+=-{}[];':,.<>?";
    final byte[] expected = input.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be(input);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test39() throws UnsupportedEncodingException {
    final String charsetName = "UTF-16BE";
    final String input = "   ";
    final byte[] expected = input.getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf16Be(input);
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test40() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testGetBytesUnchecked(charsetName);
        final byte[] expected = STRING_FIXTURE.getBytes(charsetName);
        final byte[] actual = StringUtils.getBytesUtf16Le(STRING_FIXTURE);
        Assert.assertTrue(Arrays.equals(expected, actual));

        // Additional test cases
        String emptyString = "";
        byte[] expectedEmpty = emptyString.getBytes(charsetName);
        byte[] actualEmpty = StringUtils.getBytesUtf16Le(emptyString);
        Assert.assertTrue(Arrays.equals(expectedEmpty, actualEmpty));

        String multiByteString = "🌍";
        byte[] expectedMultiByte = multiByteString.getBytes(charsetName);
        byte[] actualMultiByte = StringUtils.getBytesUtf16Le(multiByteString);
        Assert.assertTrue(Arrays.equals(expectedMultiByte, actualMultiByte));
    }
@Test
public void test41() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test42() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "特殊字符".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("特殊字符");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test43() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "hello".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("hello");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test44() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "WORLD".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("WORLD");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
@Test
public void test45() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testGetBytesUnchecked(charsetName);
    final byte[] expected = "1234567890".getBytes(charsetName);
    final byte[] actual = StringUtils.getBytesUtf8("1234567890");
    Assert.assertTrue(Arrays.equals(expected, actual));
}
    @Test
    public void test46() {
        try {
            StringUtils.newString(new byte[]{(byte) 0xE6}, "UTF-8");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test47() {
        try {
            StringUtils.getBytesUnchecked("这是一个测试", "GBK");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test48() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        
        // Regression test case 2
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringIso8859_1(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test49() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
        
         // Regression test case 
        final byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newString(emptyBytes, "UNKNOWN"));
    }
    @Test
    public void test50() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Be(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test51() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test52() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUsAscii(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test53() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));
        
        // Regression test case 
        Assert.assertNull(StringUtils.newString(null, "ISO-8859-1"));
    }
    @Test
    public void test54() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf8(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test55() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);
        
        // Regression test case 1
        final byte[] emptyBytes = new byte[0];
        final String expectedEmpty = new String(emptyBytes, charsetName);
        final String actualEmpty = StringUtils.newStringUtf16Le(emptyBytes);
        Assert.assertEquals(expectedEmpty, actualEmpty);
    }
    @Test
    public void test56() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Regression test case 
        final byte[] emptyBytes = new byte[0];
        Assert.assertNull(StringUtils.newStringUtf8(emptyBytes));
        Assert.assertNull(StringUtils.newStringIso8859_1(emptyBytes));
        Assert.assertNull(StringUtils.newStringUsAscii(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Be(emptyBytes));
        Assert.assertNull(StringUtils.newStringUtf16Le(emptyBytes));
    }
    @Test
    public void test57() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringIso8859_1(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test: change input to null
        byte[] bytesFixture = null;
        Assert.assertNull(StringUtils.newStringIso8859_1(bytesFixture));
    }
    @Test
    public void test58() {
        try {
            StringUtils.newString(BYTES_FIXTURE, "UNKNOWN");
            Assert.fail("Expected " + IllegalStateException.class.getName());

            // Regression test: change charsetName to null
            StringUtils.newString(BYTES_FIXTURE, null);
            Assert.fail("Expected " + IllegalStateException.class.getName());
        } catch (final IllegalStateException e) {
            // Expected
        }
    }
    @Test
    public void test59() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);

        // Regression test: change input to null
        byte[] bytesFixture = null;
        Assert.assertNull(StringUtils.newStringUtf16Be(bytesFixture));
    }
    @Test
    public void test60() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test: change input to null
        byte[] bytesFixture = null;
        Assert.assertNull(StringUtils.newStringUtf16(bytesFixture));
    }
    @Test
    public void test61() throws UnsupportedEncodingException {
        final String charsetName = "US-ASCII";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test: change input to null
        byte[] bytesFixture = null;
        Assert.assertNull(StringUtils.newStringUsAscii(bytesFixture));
    }
    @Test
    public void test62() {
        Assert.assertNull(StringUtils.newString(null, "UNKNOWN"));

        // Regression test: change charsetName to null
        byte[] bytesFixture = "UNKNOWN".getBytes();
        Assert.assertNull(StringUtils.newString(bytesFixture, null));
    }
    @Test
    public void test63() throws UnsupportedEncodingException {
        final String charsetName = "UTF-8";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);

        // Regression test: change input to null
        byte[] bytesFixture = null;
        Assert.assertNull(StringUtils.newStringUtf8(bytesFixture));
    }
    @Test
    public void test64() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16LE, charsetName);
        final String actual = StringUtils.newStringUtf16Le(BYTES_FIXTURE_16LE);
        Assert.assertEquals(expected, actual);

        // Regression test: change input to null
        byte[] bytesFixture = null;
        Assert.assertNull(StringUtils.newStringUtf16Le(bytesFixture));
    }
    @Test
    public void test65() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));

        // Regression test: change each input to null
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
    }
    @Test
    public void test66() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{50,65,70,67}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{50,65,70,67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test67() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{92,74,65,67}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{92,74,65,67});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test68() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{48,67,77,76,80}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{48,67,77,76,80});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test69() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{112,98,101,102,103}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{112,98,101,102,103});
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test70() throws UnsupportedEncodingException {
        final String charsetName = "ISO-8859-1";
        testNewString(charsetName);
        final String expected = new String(new byte[]{83,84,82,73,78,71}, charsetName);
        final String actual = StringUtils.newStringIso8859_1(new byte[]{83,84,82,73,78,71});
        Assert.assertEquals(expected, actual);
    }
@Test
public void test71() throws UnsupportedEncodingException {
    final String charsetName = "ISO-8859-1"; // change charset to ISO-8859-1
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUsAscii(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
}
@Test
public void test72() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final String expected = new String(new byte[0], charsetName); // change byte array to empty byte array
    final String actual = StringUtils.newStringUsAscii(new byte[0]);
    Assert.assertEquals(expected, actual);
}
@Test
public void test73() throws UnsupportedEncodingException {
    final String charsetName = "US-ASCII";
    testNewString(charsetName);
    final byte[] bytes = null; // change byte array to null
    final String expected = null;
    final String actual = StringUtils.newStringUsAscii(bytes);
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
        final String charsetName = "UTF-16";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE, charsetName);
        final String actual = StringUtils.newStringUtf16(BYTES_FIXTURE);
        Assert.assertEquals(expected, actual);
        
        // Additional test case with empty input
        Assert.assertEquals("", StringUtils.newStringUtf16(new byte[0]));
    }
    @Test
    public void test76() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16BE";
        testNewString(charsetName);
        final String expected = new String(BYTES_FIXTURE_16BE, charsetName);
        final String actual = StringUtils.newStringUtf16Be(BYTES_FIXTURE_16BE);
        Assert.assertEquals(expected, actual);
        
        // Additional regression tests
        byte[] emptyBytes = new byte[0];
        Assert.assertEquals("", StringUtils.newStringUtf16Be(emptyBytes));
        
        byte[] bytes1 = {0x00, 0x48, 0x00, 0x65, 0x00, 0x6C, 0x00, 0x6C, 0x00, 0x6F};
        Assert.assertEquals("Hello", StringUtils.newStringUtf16Be(bytes1));
    }
    @Test
    public void test77() {
        Assert.assertNull(StringUtils.newStringUtf8(null));
        Assert.assertNull(StringUtils.newStringIso8859_1(null));
        Assert.assertNull(StringUtils.newStringUsAscii(null));
        Assert.assertNull(StringUtils.newStringUtf16(null));
        Assert.assertNull(StringUtils.newStringUtf16Be(null));
        Assert.assertNull(StringUtils.newStringUtf16Le(null));
        
        // Additional regression tests
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[0]));
    }
    @Test
    public void test78() {
        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {})); // empty input
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {})); // empty input
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {})); // empty input
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {})); // empty input
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {})); // empty input
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {})); // empty input

        Assert.assertNull(StringUtils.newStringUtf8(new byte[] {1, 2, 3})); // non-null empty string
        Assert.assertNull(StringUtils.newStringIso8859_1(new byte[] {1, 2, 3})); // non-null empty string
        Assert.assertNull(StringUtils.newStringUsAscii(new byte[] {1, 2, 3})); // non-null empty string
        Assert.assertNull(StringUtils.newStringUtf16(new byte[] {1, 2, 3})); // non-null empty string
        Assert.assertNull(StringUtils.newStringUtf16Be(new byte[] {1, 2, 3})); // non-null empty string
        Assert.assertNull(StringUtils.newStringUtf16Le(new byte[] {1, 2, 3})); // non-null empty string
    }
    @Test
    public void test79() throws UnsupportedEncodingException {
        final String charsetName = "UTF-16LE";
        byte[] inputBytes = new byte[] {1, 2, 3}; // non-null input
        testNewString(charsetName);
        final String expected = new String(inputBytes, charsetName);
        final String actual = StringUtils.newStringUtf16Le(inputBytes);
        Assert.assertEquals(expected, actual);

        inputBytes = new byte[] {-1, -2, -3}; // negative input
        final String expected2 = new String(inputBytes, charsetName);
        final String actual2 = StringUtils.newStringUtf16Le(inputBytes);
        Assert.assertEquals(expected2, actual2);
    }
@Test
public void test80() throws UnsupportedEncodingException {
    final String charsetName = "UTF-8";
    testNewString(charsetName);
    final String expected = new String(BYTES_FIXTURE, charsetName);
    final String actual = StringUtils.newStringUtf8(BYTES_FIXTURE);
    Assert.assertEquals(expected, actual);
    
    // New regression test cases
    Assert.assertEquals("", StringUtils.newStringUtf8(new byte[] {})); // Empty byte array
    Assert.assertEquals("abc", StringUtils.newStringUtf8(new byte[] {97, 98, 99})); // Byte array with lowercase letters
    Assert.assertEquals("ABC", StringUtils.newStringUtf8(new byte[] {65, 66, 67})); // Byte array with uppercase letters
    Assert.assertEquals("123", StringUtils.newStringUtf8(new byte[] {49, 50, 51})); // Byte array with numbers
    Assert.assertEquals(" ", StringUtils.newStringUtf8(new byte[] {32})); // Byte array with space
    Assert.assertEquals("€", StringUtils.newStringUtf8(new byte[] {-30, -126, -84})); // Byte array with Euro sign (2-byte character)
    Assert.assertEquals("£", StringUtils.newStringUtf8(new byte[] {-61, -87})); // Byte array with Pound sign (1-byte character)
}
@Test
public void test81() {
    Assert.assertNull(StringUtils.newStringUtf8(null));
    Assert.assertNull(StringUtils.newStringIso8859_1(null));
    Assert.assertNull(StringUtils.newStringUsAscii(null));
    Assert.assertNull(StringUtils.newStringUtf16(null));
    Assert.assertNull(StringUtils.newStringUtf16Be(null));
    Assert.assertNull(StringUtils.newStringUtf16Le(null));
    
    // New regression test cases
    Assert.assertNull(StringUtils.newStringUtf8(new byte[] {72, 101, 108, 108, 111, 0, 119, 111, 114, 108, 100})); // Byte array with null character
    Assert.assertNull(StringUtils.newStringUtf8(new byte[] {-30, -126, -84, 111, 114, 108, 100})); // Byte array with incomplete Euro sign
    Assert.assertNull(StringUtils.newStringUtf8(new byte[] {-61})); // Byte array with incomplete Pound sign
}
}