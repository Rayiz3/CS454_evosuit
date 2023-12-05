package org.apache.commons.lang3.math;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class NumberUtils_LLMTest {

@Test
public void test0() {
    // Test positive integer string
    assertEquals("12345 converted to int", 12345, NumberUtils.toInt("12345"));
    
    // Test non-numeric string
    assertEquals("Invalid string converted to int", 0, NumberUtils.toInt("abc"));
    
    // Test empty string
    assertEquals("Empty string converted to int", 0, NumberUtils.toInt(""));
    
    // Test null string
    assertEquals("Null string converted to int", 0, NumberUtils.toInt(null));
}

@Test
public void test1() {
    int result = NumberUtils.toInt("12345", 0);
    assertEquals(12345, result);
}

@Test
public void test2() {
    int result = NumberUtils.toInt("abc", 0);
    assertEquals(0, result);
}

@Test
public void test3() {
    int result = NumberUtils.toInt("", 0);
    assertEquals(0, result);
}

@Test
public void test4() {
    // Positive test cases
    assertEquals(12345L, NumberUtils.toLong("12345"));
    
    // Negative test cases
    assertEquals(0L, NumberUtils.toLong("abc"));
    assertEquals(0L, NumberUtils.toLong("1L"));
    assertEquals(0L, NumberUtils.toLong("1l"));
    assertEquals(0L, NumberUtils.toLong(""));
    assertEquals(0L, NumberUtils.toLong(null));
}
   
@Test
public void test5() {
    // Positive test cases
    assertEquals(12345L, NumberUtils.toLong("12345", 5L));
    
    // Negative test cases
    assertEquals(5L, NumberUtils.toLong("1234.5", 5L));
}

@Test
public void test6() {
    assertEquals(12345l, NumberUtils.toLong("12345"));
    assertEquals(0l, NumberUtils.toLong("abc"));
    assertEquals(0l, NumberUtils.toLong("1L"));
    assertEquals(0l, NumberUtils.toLong("1l"));
    assertEquals(Long.MAX_VALUE, NumberUtils.toLong(Long.MAX_VALUE + ""));
    assertEquals(Long.MIN_VALUE, NumberUtils.toLong(Long.MIN_VALUE + ""));
    assertEquals(0l, NumberUtils.toLong(""));
    assertEquals(0l, NumberUtils.toLong(null));
}

@Test
public void test7() {
    assertEquals(12345l, NumberUtils.toLong("12345", 5l));
    assertEquals(5l, NumberUtils.toLong("1234.5", 5l));
}

@Test
public void test8() {
    // Test when the input string is a negative number
    assertEquals(-12345l, NumberUtils.toLong("-12345", 0l));
}

@Test
public void test9() {
    // Test when the input string contains leading and trailing whitespaces
    assertEquals(12345l, NumberUtils.toLong("   12345   ", 0l));
}

@Test
public void test10() {
    // Test when the input string is a large number represented in scientific notation
    assertEquals(1000000000000000l, NumberUtils.toLong("1E15", 0l));
}



@Test
public void test11() {
    float result = NumberUtils.toFloat("-1.2345", 0.0f);
    assertEquals(-1.2345f, result, 0.0001f);
}

@Test
public void test12() {
    float result = NumberUtils.toFloat("10.567", 0.0f);
    assertEquals(10.567f, result, 0.0001f);
}

@Test
public void test13() {
    float result = NumberUtils.toFloat("abc", -5.0f);
    assertEquals(-5.0f, result, 0.0001f);
}

@Test
public void test14() {
    // Test valid negative double value
    String str1 = "-1.2345";
    double expected1 = -1.2345d;
    double actual1 = NumberUtils.toDouble(str1);
    assertEquals("toDouble(String) test failed for valid negative double value", expected1, actual1, 0.0001);
    
    // Test valid positive double value
    String str2 = "1.2345";
    double expected2 = 1.2345d;
    double actual2 = NumberUtils.toDouble(str2);
    assertEquals("toDouble(String) test failed for valid positive double value", expected2, actual2, 0.0001);
    
    // Test invalid double value
    String str3 = "abc";
    double expected3 = 0.0d;
    double actual3 = NumberUtils.toDouble(str3);
    assertEquals("toDouble(String) test failed for invalid double value", expected3, actual3, 0.0001);
}

@Test
public void test15() {
    // Test valid double value
    String str1 = "1.2345";
    double defaultValue1 = 5.1d;
    double expected1 = 1.2345d;
    double actual1 = NumberUtils.toDouble(str1, defaultValue1);
    assertEquals("toDouble(String, double) test failed for valid double value", expected1, actual1, 0.0001);
    
    // Test invalid double value
    String str2 = "a";
    double defaultValue2 = 5.0d;
    double expected2 = 5.0d;
    double actual2 = NumberUtils.toDouble(str2, defaultValue2);
    assertEquals("toDouble(String, double) test failed for invalid double value", expected2, actual2, 0.0001);
}

@Test
public void test16() {
    // Test empty input string
    String str1 = "";
    double defaultValue1 = 0.0d;
    double expected1 = 0.0d;
    double actual1 = NumberUtils.toDouble(str1, defaultValue1);
    assertEquals("toDouble(String, double) test failed for empty input string", expected1, actual1, 0.0001);
    
    // Test null input string
    String str2 = null;
    double defaultValue2 = 0.0d;
    double expected2 = 0.0d;
    double actual2 = NumberUtils.toDouble(str2, defaultValue2);
    assertEquals("toDouble(String, double) test failed for null input string", expected2, actual2, 0.0001);
}

@Test
public void test17() {
    // Test when the input string is a valid decimal number
    assertEquals("toDouble(String) - Valid string test 1 failed", 
                 12.34d, NumberUtils.toDouble("12.34"), 0.00001d);
    assertEquals("toDouble(String) - Valid string test 2 failed", 
                 -9.876d, NumberUtils.toDouble("-9.876"), 0.00001d);
    assertEquals("toDouble(String) - Valid string test 3 failed", 
                 3.0d, NumberUtils.toDouble("3.0"), 0.00001d);
}

@Test
public void test18() {
    // Test when the input string is not a valid decimal number
    assertEquals("toDouble(String) - Invalid string test 1 failed", 
                 0.0d, NumberUtils.toDouble("abc"), 0.00001d);
    assertEquals("toDouble(String) - Invalid string test 2 failed", 
                 0.0d, NumberUtils.toDouble("123abc"), 0.00001d);
    assertEquals("toDouble(String) - Invalid string test 3 failed", 
                 0.0d, NumberUtils.toDouble("12.34.56"), 0.00001d);
}

@Test
public void test19() {
    // Test when the input string is null
    assertEquals("toDouble(String) - Null string test failed", 
                 0.0d, NumberUtils.toDouble(null), 0.00001d);
}

@Test
public void test20() {
    // Test case 1: positive integer string
    byte result1 = NumberUtils.toByte("123");
    assertEquals(123, result1);

    // Test case 2: non-numeric string
    byte result2 = NumberUtils.toByte("abc");
    assertEquals(0, result2);

    // Test case 3: empty string
    byte result3 = NumberUtils.toByte("");
    assertEquals(0, result3);

    // Test case 4: null string
    byte result4 = NumberUtils.toByte(null);
    assertEquals(0, result4);
}

@Test
public void test21() {
    byte result = NumberUtils.toByte("123", (byte) 0);
    assertEquals(123, result);
}

@Test
public void test22() {
    byte result = NumberUtils.toByte("abc", (byte) 5);
    assertEquals(5, result);
}

@Test
public void test23() {
    byte result = NumberUtils.toByte("", (byte) 10);
    assertEquals(10, result);
}

@Test
public void test24() {
    assertEquals((short) 12345, NumberUtils.toShort("12345"));
}

@Test
public void test25() {
    assertEquals((short) 0, NumberUtils.toShort("abc"));
}

@Test
public void test26() {
    assertEquals((short) 0, NumberUtils.toShort(""));
}

@Test
public void test27() {
    assertTrue(NumberUtils.toShort("12345", (short) 0) == 12345);
}

@Test
public void test28() {
    assertTrue(NumberUtils.toShort("abc", (short) 0) == 0);
}

@Test
public void test29() {
    assertTrue(NumberUtils.toShort("", (short) 0) == 0);
}

@Test
public void test30() {
    // Test hexadecimal numbers
    assertEquals(Byte.valueOf((byte) 0x12), NumberUtils.createNumber("0x12"));
    assertEquals(Short.valueOf((short) 0x1234), NumberUtils.createNumber("0x1234"));
    assertEquals(Integer.valueOf(0x12345678), NumberUtils.createNumber("0x12345678"));
    assertEquals(Long.valueOf(0x1234567890abcdefL), NumberUtils.createNumber("0x1234567890abcdef"));
}

@Test
public void test31() {
    // Test decimal numbers without other modifiers
    assertEquals(Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5"));
    assertEquals(Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5"));
    assertEquals(BigDecimal.valueOf(1234.5), NumberUtils.createNumber("1234.5"));
}

@Test
public void test32() {
    // Test numbers with exponent notation
    assertEquals(Float.valueOf("1.1E200"), NumberUtils.createNumber("1.1E200"));
    assertEquals(Double.valueOf("1.1E200"), NumberUtils.createNumber("1.1E200"));
    assertEquals(Double.valueOf("-1.1E-200"), NumberUtils.createNumber("-1.1E-200"));
}



@Test
public void test33() {
    // Regression test 1: Testing valid input
    assertEquals("createFloat(String) failed for valid input", Float.valueOf("1234.5"), NumberUtils.createFloat("1234.5"));
    
    // Regression test 2: Testing null input
    assertEquals("createFloat(String) failed for null input", null, NumberUtils.createFloat(null));
    
    // Regression test 3: Testing empty string input
    try {
        final Float value = NumberUtils.createFloat("");
        fail("createFloat(String) should have failed for empty string input, but it returned: " + value);
    } catch (final NumberFormatException ex) {
        // Exception thrown as expected
        assertNotNull("Exception message should not be null", ex.getMessage());
    }
}

@Test
public void test34() {
    // Positive test case
    assertEquals(Double.valueOf("1234.5"), NumberUtils.createDouble("1234.5"));

    // Test case when input string is null
    assertEquals(null, NumberUtils.createDouble(null));

    // Negative test cases
    // Test case when input string is empty
    testCreateDoubleFailure(""); 
    // Test case when input string has only spaces
    testCreateDoubleFailure(" ");
    // Test case when input string has control characters
    testCreateDoubleFailure("\b\t\n\f\r");
    // Test case when input string has funky whitespaces
    testCreateDoubleFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
}

protected void testCreateDoubleFailure(final String str) {
    try {
        // Expected NumberFormatException to be thrown
        final Double value = NumberUtils.createDouble(str);
        fail("createDouble(\"" + str + "\") should have failed: " + value);
    } catch (final NumberFormatException ex) {
        // Exception caught as expected
    }
}


@Test
public void test36() {
    // Positive integer input
    assertEquals("createInteger(\"12345\") should return Integer value of 12345",
            Integer.valueOf("12345"), NumberUtils.createInteger("12345"));
}

@Test
public void test37() {
    // Negative integer input
    assertEquals("createInteger(\"-54321\") should return Integer value of -54321",
            Integer.valueOf("-54321"), NumberUtils.createInteger("-54321"));
}

@Test
public void test38() {
    // Positive test case: Valid input string, should return the expected Long value
    assertEquals("createLong(String) failed", Long.valueOf("12345"), NumberUtils.createLong("12345"));
    
    // Positive test case: Input string is null, should return null
    assertEquals("createLong(null) failed", null, NumberUtils.createLong(null));
    
    // Negative test case: Input string is empty, should throw NumberFormatException
    try {
        NumberUtils.createLong("");
        fail("createLong(\"\") should have thrown NumberFormatException");
    } catch (NumberFormatException ex) {
        // Exception expected, do nothing
    }
    
    // Negative test case: Input string contains whitespace only, should throw NumberFormatException
    try {
        NumberUtils.createLong(" ");
        fail("createLong(\" \") should have thrown NumberFormatException");
    } catch (NumberFormatException ex) {
        // Exception expected, do nothing
    }
    
    // Negative test case: Input string contains non-numeric characters, should throw NumberFormatException
    try {
        NumberUtils.createLong("12a34");
        fail("createLong(\"12a34\") should have thrown NumberFormatException");
    } catch (NumberFormatException ex) {
        // Exception expected, do nothing
    }
}

@Test
public void test39() {
    // Test case 1: Valid positive decimal number
    assertEquals(new BigInteger("12345"), NumberUtils.createBigInteger("12345"));

    // Test case 2: Valid negative decimal number
    assertEquals(new BigInteger("-98765"), NumberUtils.createBigInteger("-98765"));

    // Test case 3: Valid hexadecimal number
    assertEquals(new BigInteger("255"), NumberUtils.createBigInteger("0xff"));
}

@Test
public void test40() {
    assertEquals("createBigDecimal(String) failed", new BigDecimal("1234.5"), NumberUtils.createBigDecimal("1234.5"));
    assertEquals("createBigDecimal(null) failed", null, NumberUtils.createBigDecimal(null));
    assertEquals("createBigDecimal(empty string) failed", new BigDecimal("0"), NumberUtils.createBigDecimal(""));
}

@Test
public void test41() {
    // Test the minimum value for array length 1
    assertEquals(5, NumberUtils.min(new long[] { 5 }));

    // Test the minimum value for array length 2
    assertEquals(6, NumberUtils.min(new long[] { 6, 9 }));

    // Test the minimum value for a non-sequential array
    assertEquals(-10, NumberUtils.min(new long[] { -10, -5, 0, 5, 10 }));

    // Test the minimum value for a reversed array
    assertEquals(-10, NumberUtils.min(new long[] { -5, 0, -10, 5, 10 }));
}

@Test
public void test42() {
    assertEquals(
        "min(int[]) failed for array length 1",
        5,
        NumberUtils.min(new int[] { 5 }));

    assertEquals(
        "min(int[]) failed for array length 2",
        6,
        NumberUtils.min(new int[] { 6, 9 }));

    assertEquals(-10, NumberUtils.min(new int[] { -10, -5, 0, 5, 10 }));
    assertEquals(-10, NumberUtils.min(new int[] { -5, 0, -10, 5, 10 }));
}

@Test
public void test43() {
    assertEquals(
        "min(long[]) failed for array length 1",
        5,
        NumberUtils.min(new long[] { 5 }));

    assertEquals(
        "min(long[]) failed for array length 2",
        6,
        NumberUtils.min(new long[] { 6, 9 }));

    assertEquals(-10, NumberUtils.min(new long[] { -10, -5, 0, 5, 10 }));
    assertEquals(-10, NumberUtils.min(new long[] { -5, 0, -10, 5, 10 }));
}

@Test
public void test44() {
    assertEquals(
        "min(short[]) failed for array length 1",
        5,
        NumberUtils.min(new short[] { 5 }));

    assertEquals(
        "min(short[]) failed for array length 2",
        6,
        NumberUtils.min(new short[] { 6, 9 }));

    assertEquals(-10, NumberUtils.min(new short[] { -10, -5, 0, 5, 10 }));
    assertEquals(-10, NumberUtils.min(new short[] { -5, 0, -10, 5, 10 }));
}

@Test(expected = IllegalArgumentException.class)
public void test45() {
    NumberUtils.min((short[]) null);
}

@Test(expected = IllegalArgumentException.class)
public void test46() {
    NumberUtils.min(new short[0]);
}

@Test
public void test47() {
    assertEquals(
        "min(short[]) failed for array length 1",
        -32768,
        NumberUtils.min(new short[] { -32768 }));

    assertEquals(
        "min(short[]) failed for array length 2",
        -32767,
        NumberUtils.min(new short[] { -32767, -32766 }));

    assertEquals(0, NumberUtils.min(new short[] { 0, 1, 2, 3, 4 }));
    assertEquals(-10, NumberUtils.min(new short[] { -5, 0, -10, 5, 10 }));
}

    @Test
    public void test48() {
        // Regression test case 1
        // Test with an array of length 1
        byte[] array = {5};
        byte result = NumberUtils.min(array);
        assertEquals("min(byte[]) failed for array length 1", 5, result);

        // Regression test case 2
        // Test with an array of length 2
        array = new byte[]{6, 9};
        result = NumberUtils.min(array);
        assertEquals("min(byte[]) failed for array length 2", 6, result);

        // Regression test case 3
        // Test with an array containing negative numbers
        array = new byte[]{-10, -5, 0, 5, 10};
        result = NumberUtils.min(array);
        assertEquals("min(byte[]) failed for array with negative numbers", -10, result);
    }

@Test
public void test49() {
    // Test array with negative values
    double[] array = {-5.2, -3.1, -7.5, -10.9, -2.0};
    double expected = -10.9;
    double actual = NumberUtils.min(array);
    assertEquals(expected, actual, 0.0001);
}

@Test
public void test50() {
    // Test array with positive values
    double[] array = {3.8, 2.1, 7.6, 4.2, 6.9};
    double expected = 2.1;
    double actual = NumberUtils.min(array);
    assertEquals(expected, actual, 0.0001);
}

@Test
public void test51() {
    // Test array with both negative and positive values
    double[] array = {-5.2, 3.1, -7.5, 8.4, -2.0};
    double expected = -7.5;
    double actual = NumberUtils.min(array);
    assertEquals(expected, actual, 0.0001);
}

    @Test
    public void test52() {
        // Test with an array of size 0
        assertEquals(Float.POSITIVE_INFINITY, NumberUtils.min(new float[0]), 0.0001f);
    }

    @Test
    public void test53() {
        // Test with an array of all NaN values
        assertEquals(Float.NaN, NumberUtils.min(new float[] { Float.NaN, Float.NaN, Float.NaN }), 0.0001f);
    }

    @Test
    public void test54() {
        // Test with an array of all positive values
        assertEquals(0, NumberUtils.min(new float[] { 2, 4, 6, 8, 10 }), 0.0001f);
    }

@Test(expected = IllegalArgumentException.class)
public void test55() {
    NumberUtils.max((long[]) null);
}

@Test(expected = IllegalArgumentException.class)
public void test56() {
    NumberUtils.max(new long[0]);
}

@Test
public void test57() {
    assertEquals(
        "max(long[]) failed for array length 1",
        5,
        NumberUtils.max(new long[] { 5 }));

    assertEquals(
        "max(long[]) failed for array length 2",
        9,
        NumberUtils.max(new long[] { 6, 9 }));

    assertEquals(
        "max(long[]) failed for array length 5",
        10,
        NumberUtils.max(new long[] { -10, -5, 0, 5, 10 }));
}

// Regression test for int[] input with single element
@Test
public void test58() {
    int[] array = new int[]{5};
    int expected = 5;
    int actual = NumberUtils.max(array);
    assertEquals(expected, actual);
}

// Regression test for int[] input with multiple elements in ascending order
@Test
public void test59() {
    int[] array = new int[]{1, 2, 3, 4, 5};
    int expected = 5;
    int actual = NumberUtils.max(array);
    assertEquals(expected, actual);
}

// Regression test for int[] input with multiple elements in descending order
@Test
public void test60() {
    int[] array = new int[]{5, 4, 3, 2, 1};
    int expected = 5;
    int actual = NumberUtils.max(array);
    assertEquals(expected, actual);
}

@Test
public void test61() {
    // Test case with array length 1
    assertEquals("max(short[]) failed for array length 1", 5, NumberUtils.max(new short[] { 5 }));

    // Test case with array length 2
    assertEquals("max(short[]) failed for array length 2", 9, NumberUtils.max(new short[] { 6, 9 }));

    // Test case with array length 5
    assertEquals("max(short[]) failed for array length 5", 10, NumberUtils.max(new short[] { -10, -5, 0, 5, 10 }));
}

@Test
public void test62() {
    assertEquals(
        "max(byte[]) failed for array length 1",
        5,
        NumberUtils.max(new byte[] { 5 }));

    assertEquals(
        "max(byte[]) failed for array length 2",
        9,
        NumberUtils.max(new byte[] { 6, 9 }));

    assertEquals(
        "max(byte[]) failed for array length 5",
        10,
        NumberUtils.max(new byte[] { -10, -5, 0, 5, 10 }));

    assertEquals(
        "max(byte[]) failed for array length 6",
        127,
        NumberUtils.max(new byte[] { -10, -5, 0, 5, 10, 127 }));

    assertEquals(
        "max(byte[]) failed for array length 7",
        -1,
        NumberUtils.max(new byte[] { -10, -5, 0, 5, 10, 127, -1 }));
}

    @Test
    public void test63() {
        assertEquals(
            "max(double[]) failed for array length 3",
            10.4,
            NumberUtils.max(new double[] { 5.7, 10.4, 7.2 }),
            0);
    }

    @Test
    public void test64() {
        assertEquals(
            "max(double[]) failed for array with negative values",
            -2.5,
            NumberUtils.max(new double[] { -10.5, -5.6, -2.5, -7.8 }),
            0);
    }

    @Test
    public void test65() {
        assertEquals(
            "max(double[]) failed for array with all equal values",
            5.2,
            NumberUtils.max(new double[] { 5.2, 5.2, 5.2, 5.2 }),
            0);
    }

    // Regression test case 1
    @Test
    public void test66() {
        assertEquals(
            "max(float[]) failed for array length 1",
            5.1f,
            NumberUtils.max(new float[] { 5.1f }),
            0);
    }

    // Regression test case 2
    @Test
    public void test67() {
        assertEquals(
            "max(float[]) failed for array length 2",
            9.2f,
            NumberUtils.max(new float[] { 6.3f, 9.2f }),
            0);
    }

    // Regression test case 3
    @Test
    public void test68() {
        assertEquals(
            "max(float[]) failed for float length 5",
            10.4f,
            NumberUtils.max(new float[] { -10.5f, -5.6f, 0, 5.7f, 10.4f }),
            0);
    }


@Test
public void test72() {
    assertEquals(5, NumberUtils.min(5, 10, 7));
    assertEquals(0, NumberUtils.min(0, 0, 0));
    assertEquals(-20, NumberUtils.min(-20, 10, 50));
}

@Test
public void test73() {
    assertEquals("min(int) failed for positive numbers", 5, NumberUtils.min(5, 10, 15));
    assertEquals("min(int) failed for negative numbers", -15, NumberUtils.min(-5, -10, -15));
    assertEquals("min(int) failed for mixed numbers", -10, NumberUtils.min(-10, 0, 10));
}

@Test
public void test74() {
    // Test the minimum value
    assertEquals(
        "min(short[]) failed for array length 1",
        Short.MIN_VALUE,
        NumberUtils.min(new short[] { Short.MIN_VALUE }));

    // Test positive values
    assertEquals(
        "min(short[]) failed for array length 2",
        6,
        NumberUtils.min(new short[] { 6, 9 }));

    // Test negative values
    assertEquals(
        "min(short[]) failed for array length 5",
        Short.MIN_VALUE,
        NumberUtils.min(new short[] { -10, -5, 0, 5, Short.MIN_VALUE }));

}

@Test
public void test75() {
    // Test case 1: all positive numbers
    assertEquals(1, NumberUtils.min((byte) 5, (byte) 3, (byte) 1));

    // Test case 2: all negative numbers
    assertEquals(-10, NumberUtils.min((byte) -5, (byte) -10, (byte) -3));

    // Test case 3: positive, negative, and zero
    assertEquals(-5, NumberUtils.min((byte) -5, (byte) 0, (byte) 5));
}

@Test
public void test76() {
    // Test case with all positive numbers
    double result = NumberUtils.min(5.7, 8.9, 1.2);
    assertEquals("Minimum value is not correct", 1.2, result, 0);
}

@Test
public void test77() {
    // Test case with all negative numbers
    double result = NumberUtils.min(-7.6, -4.3, -9.1);
    assertEquals("Minimum value is not correct", -9.1, result, 0);
}

@Test
public void test78() {
    // Test case with a mix of positive and negative numbers
    double result = NumberUtils.min(3.4, -2.1, 6.8);
    assertEquals("Minimum value is not correct", -2.1, result, 0);
}

    @Test
    public void test79() {
        // Test for positive floats
        assertEquals(1.5f, NumberUtils.min(1.5f, 2.2f, 3.7f), 0.0001f);
    }

    @Test
    public void test80() {
        // Test for negative floats
        assertEquals(-6.4f, NumberUtils.min(-8.9f, -6.4f, -5.3f), 0.0001f);
    }
    
    @Test
    public void test81() {
        // Test for zero and positive floats
        assertEquals(0.0f, NumberUtils.min(0.0f, 1.2f, 5.7f), 0.0001f);
    }

@Test
public void test82() {
    assertEquals(Long.MAX_VALUE, NumberUtils.max(Long.MAX_VALUE, Long.MAX_VALUE - 1, Long.MAX_VALUE - 2));
    assertEquals(Long.MAX_VALUE, NumberUtils.max(Long.MAX_VALUE - 1, Long.MAX_VALUE, Long.MAX_VALUE - 2));
    assertEquals(Long.MAX_VALUE, NumberUtils.max(Long.MAX_VALUE - 1, Long.MAX_VALUE - 2, Long.MAX_VALUE));
}

@Test
public void test83() {
    assertEquals(Integer.MAX_VALUE, NumberUtils.max(Integer.MAX_VALUE, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2));
    assertEquals(Integer.MAX_VALUE, NumberUtils.max(Integer.MAX_VALUE - 1, Integer.MAX_VALUE, Integer.MAX_VALUE - 2));
    assertEquals(Integer.MAX_VALUE, NumberUtils.max(Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2, Integer.MAX_VALUE));
}

@Test
public void test84() {
    assertEquals(Short.MAX_VALUE, NumberUtils.max(Short.MAX_VALUE, Short.MAX_VALUE - 1, Short.MAX_VALUE - 2));
    assertEquals(Short.MAX_VALUE, NumberUtils.max(Short.MAX_VALUE - 1, Short.MAX_VALUE, Short.MAX_VALUE - 2));
    assertEquals(Short.MAX_VALUE, NumberUtils.max(Short.MAX_VALUE - 1, Short.MAX_VALUE - 2, Short.MAX_VALUE));
}

@Test
public void test85() {
    // Test when the first argument is the greatest
    assertEquals(10, NumberUtils.max(10, 5, 7));

    // Test when the second argument is the greatest
    assertEquals(9, NumberUtils.max(6, 9, 3));
    
    // Test when the third argument is the greatest
    assertEquals(8, NumberUtils.max(4, 2, 8));
}

    @Test
    public void test86() {
        assertEquals(
            "max(short) failed for positive numbers",
            100,
            NumberUtils.max((short) 10, (short) 50, (short) 100)
        );

        assertEquals(
            "max(short) failed for negative numbers",
            -5,
            NumberUtils.max((short) -20, (short) -5, (short) -10)
        );

        assertEquals(
            "max(short) failed for equal numbers",
            20,
            NumberUtils.max((short) 20, (short) 20, (short) 20)
        );
    }

@Test
public void test87() {
    // Regression test 1
    assertEquals("Regression test 1 failed", (byte) 9, NumberUtils.max((byte) 9, (byte) 6, (byte) 3));

    // Regression test 2
    assertEquals("Regression test 2 failed", (byte) 11, NumberUtils.max((byte) 5, (byte) 11, (byte) 7));

    // Regression test 3
    assertEquals("Regression test 3 failed", (byte) 100, NumberUtils.max((byte) 95, (byte) 100, (byte) 99));
}

}