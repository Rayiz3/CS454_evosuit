/
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package org.apache.commons.lang3.math;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;

/
 
 
 
 
 
public class NumberUtils {
    
    /
    public static final Long LONG_ZERO = Long.valueOf(0L);
    /
    public static final Long LONG_ONE = Long.valueOf(1L);
    /
    public static final Long LONG_MINUS_ONE = Long.valueOf(-1L);
    /
    public static final Integer INTEGER_ZERO = Integer.valueOf(0);
    /
    public static final Integer INTEGER_ONE = Integer.valueOf(1);
    /
    public static final Integer INTEGER_MINUS_ONE = Integer.valueOf(-1);
    /
    public static final Short SHORT_ZERO = Short.valueOf((short) 0);
    /
    public static final Short SHORT_ONE = Short.valueOf((short) 1);
    /
    public static final Short SHORT_MINUS_ONE = Short.valueOf((short) -1);
    /
    public static final Byte BYTE_ZERO = Byte.valueOf((byte) 0);
    /
    public static final Byte BYTE_ONE = Byte.valueOf((byte) 1);
    /
    public static final Byte BYTE_MINUS_ONE = Byte.valueOf((byte) -1);
    /
    public static final Double DOUBLE_ZERO = Double.valueOf(0.0d);
    /
    public static final Double DOUBLE_ONE = Double.valueOf(1.0d);
    /
    public static final Double DOUBLE_MINUS_ONE = Double.valueOf(-1.0d);
    /
    public static final Float FLOAT_ZERO = Float.valueOf(0.0f);
    /
    public static final Float FLOAT_ONE = Float.valueOf(1.0f);
    /
    public static final Float FLOAT_MINUS_ONE = Float.valueOf(-1.0f);

    /
     
     
     
     
     
     
    public NumberUtils() {
        super();
    }

    
    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static int toInt(final String str) {
        return toInt(str, 0);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static int toInt(final String str, final int defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static long toLong(final String str) {
        return toLong(str, 0L);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static long toLong(final String str, final long defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static float toFloat(final String str) {
        return toFloat(str, 0.0f);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static float toFloat(final String str, final float defaultValue) {
      if (str == null) {
          return defaultValue;
      }     
      try {
          return Float.parseFloat(str);
      } catch (final NumberFormatException nfe) {
          return defaultValue;
      }
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static double toDouble(final String str) {
        return toDouble(str, 0.0d);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static double toDouble(final String str, final double defaultValue) {
      if (str == null) {
          return defaultValue;
      }
      try {
          return Double.parseDouble(str);
      } catch (final NumberFormatException nfe) {
          return defaultValue;
      }
    }

     
     /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static byte toByte(final String str) {
        return toByte(str, (byte) 0);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static byte toByte(final String str, final byte defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Byte.parseByte(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static short toShort(final String str) {
        return toShort(str, (short) 0);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static short toShort(final String str, final short defaultValue) {
        if(str == null) {
            return defaultValue;
        }
        try {
            return Short.parseShort(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    public static Number createNumber(final String str) throws NumberFormatException {
        if (str == null) {
            return null;
        }
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        
        final String[] hex_prefixes = {"0x", "0X", "-0x", "-0X", "#", "-#"};
        int pfxLen = 0;
        for(final String pfx : hex_prefixes) {
            if (str.startsWith(pfx)) {
                pfxLen += pfx.length();
                break;
            }
        }
        if (pfxLen > 0) { 
            final int hexDigits = str.length() - pfxLen;
            if (hexDigits > 16) { 
                return createBigInteger(str);
            }
            if (hexDigits > 8) { 
                return createLong(str);
            }
            return createInteger(str);
        }
        final char lastChar = str.charAt(str.length() - 1);
        String mant;
        String dec;
        String exp;
        final int decPos = str.indexOf('.');
        final int expPos = str.indexOf('e') + str.indexOf('E') + 1; 
        
        

        int numDecimals = 0; 
        if (decPos > -1) { 

            if (expPos > -1) { 
                if (expPos < decPos || expPos > str.length()) { 
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                dec = str.substring(decPos + 1, expPos);
            } else {
                dec = str.substring(decPos + 1);
            }
            mant = str.substring(0, decPos);
            numDecimals = dec.length(); 
        } else {
            if (expPos > -1) {
                if (expPos > str.length()) { 
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                mant = str.substring(0, expPos);
            } else {
                mant = str;
            }
            dec = null;
        }
        if (!Character.isDigit(lastChar) && lastChar != '.') {
            if (expPos > -1 && expPos < str.length() - 1) {
                exp = str.substring(expPos + 1, str.length() - 1);
            } else {
                exp = null;
            }
            
            final String numeric = str.substring(0, str.length() - 1);
            final boolean allZeros = isAllZeros(mant) && isAllZeros(exp);
            switch (lastChar) {
                case 'l' :
                case 'L' :
                    if (dec == null
                        && exp == null
                        && (numeric.charAt(0) == '-' && isDigits(numeric.substring(1)) || isDigits(numeric))) {
                        try {
                            return createLong(numeric);
                        } catch (final NumberFormatException nfe) { 
                            
                        }
                        return createBigInteger(numeric);

                    }
                    throw new NumberFormatException(str + " is not a valid number.");
                case 'f' :
                case 'F' :
                    try {
                        final Float f = NumberUtils.createFloat(numeric);
                        if (!(f.isInfinite() || (f.floatValue() == 0.0F && !allZeros))) {
                            
                            
                            return f;
                        }

                    } catch (final NumberFormatException nfe) { 
                        
                    }
                    
                case 'd' :
                case 'D' :
                    try {
                        final Double d = NumberUtils.createDouble(numeric);
                        if (!(d.isInfinite() || (d.floatValue() == 0.0D && !allZeros))) {
                            return d;
                        }
                    } catch (final NumberFormatException nfe) { 
                        
                    }
                    try {
                        return createBigDecimal(numeric);
                    } catch (final NumberFormatException e) { 
                        
                    }
                    
                default :
                    throw new NumberFormatException(str + " is not a valid number.");

            }
        }
        
        
        if (expPos > -1 && expPos < str.length() - 1) {
            exp = str.substring(expPos + 1, str.length());
        } else {
            exp = null;
        }
        if (dec == null && exp == null) { 
            
            try {
                return createInteger(str);
            } catch (final NumberFormatException nfe) { 
                
            }
            try {
                return createLong(str);
            } catch (final NumberFormatException nfe) { 
                
            }
            return createBigInteger(str);
        }

        
        final boolean allZeros = isAllZeros(mant) && isAllZeros(exp);
        try {
            if(numDecimals <= 7){
                final Float f = createFloat(str);
                if (!(f.isInfinite() || (f.floatValue() == 0.0F && !allZeros))) {
                    return f;
                }
            }
        } catch (final NumberFormatException nfe) { 
            
        }
        try {
            if(numDecimals <= 16){
                final Double d = createDouble(str);
                if (!(d.isInfinite() || (d.doubleValue() == 0.0D && !allZeros))) {
                    return d;
                }
            }
        } catch (final NumberFormatException nfe) { 
            
        }

        return createBigDecimal(str);
    }

    /
     
     
     
     
     
     
     
    private static boolean isAllZeros(final String str) {
        if (str == null) {
            return true;
        }
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return str.length() > 0;
    }

    
    /
     
     
     
     
     
     
     
     
    public static Float createFloat(final String str) {
        if (str == null) {
            return null;
        }
        return Float.valueOf(str);
    }

    /
     
     
     
     
     
     
     
     
    public static Double createDouble(final String str) {
        if (str == null) {
            return null;
        }
        return Double.valueOf(str);
    }

    /
     
     
     
     
     
     
     
     
     
    public static Integer createInteger(final String str) {
        if (str == null) {
            return null;
        }
        
        return Integer.decode(str);
    }

    /
     
     
     
     
     
     
     
     
     
    public static Long createLong(final String str) {
        if (str == null) {
            return null;
        }
        return Long.decode(str);
    }

    /
     
     
     
     
     
     
     
     
     
    public static BigInteger createBigInteger(final String str) {
        if (str == null) {
            return null;
        }
        int pos = 0; 
        int radix = 10;
        boolean negate = false; 
        if (str.startsWith("-")) {
            negate = true;
            pos = 1;
        }
        if (str.startsWith("0x", pos) || str.startsWith("0x", pos)) { 
            radix = 16;
            pos += 2;
        } else if (str.startsWith("#", pos)) { 
            radix = 16;
            pos ++;
        } else if (str.startsWith("0", pos) && str.length() > pos + 1) { 
            radix = 8;
            pos ++;
        } 

        final BigInteger value = new BigInteger(str.substring(pos), radix);
        return negate ? value.negate() : value;
    }

    /
     
     
     
     
     
     
     
     
    public static BigDecimal createBigDecimal(final String str) {
        if (str == null) {
            return null;
        }
        
        if (StringUtils.isBlank(str)) {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (str.trim().startsWith("--")) {
            
            
            
            
            throw new NumberFormatException(str + " is not a valid number.");
        }
        return new BigDecimal(str);
    }

    
    
    /
     
     
     
     
     
     
     
    public static long min(final long[] array) {
        
        validateArray(array);
    
        
        long min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
    
        return min;
    }

    /
     
     
     
     
     
     
     
    public static int min(final int[] array) {
        
        validateArray(array);
    
        
        int min = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] < min) {
                min = array[j];
            }
        }
    
        return min;
    }

    /
     
     
     
     
     
     
     
    public static short min(final short[] array) {
        
        validateArray(array);
    
        
        short min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
    
        return min;
    }

    /
     
     
     
     
     
     
     
    public static byte min(final byte[] array) {
        
        validateArray(array);
    
        
        byte min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
    
        return min;
    }

     /
     
     
     
     
     
     
     
     
    public static double min(final double[] array) {
        
        validateArray(array);
    
        
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Double.isNaN(array[i])) {
                return Double.NaN;
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
    
        return min;
    }

    /
     
     
     
     
     
     
     
     
    public static float min(final float[] array) {
        
        validateArray(array);
    
        
        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (Float.isNaN(array[i])) {
                return Float.NaN;
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
    
        return min;
    }

    
    
    /
     
     
     
     
     
     
     
    public static long max(final long[] array) {
        
        validateArray(array);

        
        long max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }

    /
     
     
     
     
     
     
     
    public static int max(final int[] array) {
        
        validateArray(array);
    
        
        int max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
    
        return max;
    }

    /
     
     
     
     
     
     
     
    public static short max(final short[] array) {
        
        validateArray(array);
    
        
        short max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
    
        return max;
    }

    /
     
     
     
     
     
     
     
    public static byte max(final byte[] array) {
        
        validateArray(array);
    
        
        byte max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
    
        return max;
    }

    /
     
     
     
     
     
     
     
     
    public static double max(final double[] array) {
        
        validateArray(array);

        
        double max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (Double.isNaN(array[j])) {
                return Double.NaN;
            }
            if (array[j] > max) {
                max = array[j];
            }
        }
    
        return max;
    }

    /
     
     
     
     
     
     
     
     
    public static float max(final float[] array) {
        
        validateArray(array);

        
        float max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (Float.isNaN(array[j])) {
                return Float.NaN;
            }
            if (array[j] > max) {
                max = array[j];
            }
        }

        return max;
    }

    /
     
     
     
     
     
    private static void validateArray(final Object array) {
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (Array.getLength(array) == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
    }
     
    
    
    /
     
     
     
     
     
     
     
    public static long min(long a, final long b, final long c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
    public static int min(int a, final int b, final int c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
    public static short min(short a, final short b, final short c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
    public static byte min(byte a, final byte b, final byte c) {
        if (b < a) {
            a = b;
        }
        if (c < a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
     
     
     
     
    public static double min(final double a, final double b, final double c) {
        return Math.min(Math.min(a, b), c);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
    public static float min(final float a, final float b, final float c) {
        return Math.min(Math.min(a, b), c);
    }

    
    
    /
     
     
     
     
     
     
     
    public static long max(long a, final long b, final long c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
    public static int max(int a, final int b, final int c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
    public static short max(short a, final short b, final short c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
    public static byte max(byte a, final byte b, final byte c) {
        if (b > a) {
            a = b;
        }
        if (c > a) {
            a = c;
        }
        return a;
    }

    /
     
     
     
     
     
     
     
     
     
     
     
    public static double max(final double a, final double b, final double c) {
        return Math.max(Math.max(a, b), c);
    }

    /
     
     
     
     
     
     
     
     
     
     
     
    public static float max(final float a, final float b, final float c) {
        return Math.max(Math.max(a, b), c);
    }

    
    /
     
     
     
     
     
     
     
     
     
    public static boolean isDigits(final String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /
     
     
     
     
     
     
     
     
     
     
     
     
    public static boolean isNumber(final String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        final char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        
        final int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
            int i = start + 2;
            if (i == sz) {
                return false; 
            }
            
            for (; i < chars.length; i++) {
                if ((chars[i] < '0' || chars[i] > '9')
                    && (chars[i] < 'a' || chars[i] > 'f')
                    && (chars[i] < 'A' || chars[i] > 'F')) {
                    return false;
                }
            }
            return true;
        }
        sz--; 
              
        int i = start;
        
        
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit)) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                foundDigit = true;
                allowSigns = false;

            } else if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    
                    return false;
                }
                hasDecPoint = true;
            } else if (chars[i] == 'e' || chars[i] == 'E') {
                
                if (hasExp) {
                    
                    return false;
                }
                if (!foundDigit) {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            } else if (chars[i] == '+' || chars[i] == '-') {
                if (!allowSigns) {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; 
            } else {
                return false;
            }
            i++;
        }
        if (i < chars.length) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E') {
                
                return false;
            }
            if (chars[i] == '.') {
                if (hasDecPoint || hasExp) {
                    
                    return false;
                }
                
                return foundDigit;
            }
            if (!allowSigns
                && (chars[i] == 'd'
                    || chars[i] == 'D'
                    || chars[i] == 'f'
                    || chars[i] == 'F')) {
                return foundDigit;
            }
            if (chars[i] == 'l'
                || chars[i] == 'L') {
                
                return foundDigit && !hasExp && !hasDecPoint;
            }
            
            return false;
        }
        
        
        return !allowSigns && foundDigit;
    }

}
/
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
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

/
 
 
 
 
public class NumberUtilsTest {

    
    @Test
    public void testConstructor() {
        assertNotNull(new NumberUtils());
        final Constructor<?>[] cons = NumberUtils.class.getDeclaredConstructors();
        assertEquals(1, cons.length);
        assertTrue(Modifier.isPublic(cons[0].getModifiers()));
        assertTrue(Modifier.isPublic(NumberUtils.class.getModifiers()));
        assertFalse(Modifier.isFinal(NumberUtils.class.getModifiers()));
    }

    

    /
     
     
    @Test
    public void testToIntString() {
        assertTrue("toInt(String) 1 failed", NumberUtils.toInt("12345") == 12345);
        assertTrue("toInt(String) 2 failed", NumberUtils.toInt("abc") == 0);
        assertTrue("toInt(empty) failed", NumberUtils.toInt("") == 0);
        assertTrue("toInt(null) failed", NumberUtils.toInt(null) == 0);
    }

    /
     
     
    @Test
    public void testToIntStringI() {
        assertTrue("toInt(String,int) 1 failed", NumberUtils.toInt("12345", 5) == 12345);
        assertTrue("toInt(String,int) 2 failed", NumberUtils.toInt("1234.5", 5) == 5);
    }

    /
     
     
    @Test
    public void testToLongString() {
        assertTrue("toLong(String) 1 failed", NumberUtils.toLong("12345") == 12345l);
        assertTrue("toLong(String) 2 failed", NumberUtils.toLong("abc") == 0l);
        assertTrue("toLong(String) 3 failed", NumberUtils.toLong("1L") == 0l);
        assertTrue("toLong(String) 4 failed", NumberUtils.toLong("1l") == 0l);
        assertTrue("toLong(Long.MAX_VALUE) failed", NumberUtils.toLong(Long.MAX_VALUE+"") == Long.MAX_VALUE);
        assertTrue("toLong(Long.MIN_VALUE) failed", NumberUtils.toLong(Long.MIN_VALUE+"") == Long.MIN_VALUE);
        assertTrue("toLong(empty) failed", NumberUtils.toLong("") == 0l);
        assertTrue("toLong(null) failed", NumberUtils.toLong(null) == 0l);
    }

    /
     
     
    @Test
    public void testToLongStringL() {
        assertTrue("toLong(String,long) 1 failed", NumberUtils.toLong("12345", 5l) == 12345l);
        assertTrue("toLong(String,long) 2 failed", NumberUtils.toLong("1234.5", 5l) == 5l);
    }

    /
     
     
    @Test
    public void testToFloatString() {
        assertTrue("toFloat(String) 1 failed", NumberUtils.toFloat("-1.2345") == -1.2345f);
        assertTrue("toFloat(String) 2 failed", NumberUtils.toFloat("1.2345") == 1.2345f);
        assertTrue("toFloat(String) 3 failed", NumberUtils.toFloat("abc") == 0.0f);
        assertTrue("toFloat(Float.MAX_VALUE) failed", NumberUtils.toFloat(Float.MAX_VALUE+"") ==  Float.MAX_VALUE);
        assertTrue("toFloat(Float.MIN_VALUE) failed", NumberUtils.toFloat(Float.MIN_VALUE+"") == Float.MIN_VALUE);
        assertTrue("toFloat(empty) failed", NumberUtils.toFloat("") == 0.0f);
        assertTrue("toFloat(null) failed", NumberUtils.toFloat(null) == 0.0f);
    }

    /
     
     
    @Test
    public void testToFloatStringF() {
        assertTrue("toFloat(String,int) 1 failed", NumberUtils.toFloat("1.2345", 5.1f) == 1.2345f);
        assertTrue("toFloat(String,int) 2 failed", NumberUtils.toFloat("a", 5.0f) == 5.0f);
    }
    
    /
     
     
    @Test
    public void testStringCreateNumberEnsureNoPrecisionLoss(){
        String shouldBeFloat = "1.23";
        String shouldBeDouble = "3.40282354e+38";
        String shouldBeBigDecimal = "1.797693134862315759e+308";
        
        assertTrue(NumberUtils.createNumber(shouldBeFloat) instanceof Float);
        assertTrue(NumberUtils.createNumber(shouldBeDouble) instanceof Double);
        assertTrue(NumberUtils.createNumber(shouldBeBigDecimal) instanceof BigDecimal);
    }
    /
     
     
    @Test
    public void testStringToDoubleString() {
        assertTrue("toDouble(String) 1 failed", NumberUtils.toDouble("-1.2345") == -1.2345d);
        assertTrue("toDouble(String) 2 failed", NumberUtils.toDouble("1.2345") == 1.2345d);
        assertTrue("toDouble(String) 3 failed", NumberUtils.toDouble("abc") == 0.0d);
        assertTrue("toDouble(Double.MAX_VALUE) failed", NumberUtils.toDouble(Double.MAX_VALUE+"") == Double.MAX_VALUE);
        assertTrue("toDouble(Double.MIN_VALUE) failed", NumberUtils.toDouble(Double.MIN_VALUE+"") == Double.MIN_VALUE);
        assertTrue("toDouble(empty) failed", NumberUtils.toDouble("") == 0.0d);
        assertTrue("toDouble(null) failed", NumberUtils.toDouble(null) == 0.0d);
    }

    /
     
     
    @Test
    public void testStringToDoubleStringD() {
        assertTrue("toDouble(String,int) 1 failed", NumberUtils.toDouble("1.2345", 5.1d) == 1.2345d);
        assertTrue("toDouble(String,int) 2 failed", NumberUtils.toDouble("a", 5.0d) == 5.0d);
    }

     /
     
     
    @Test
    public void testToByteString() {
        assertTrue("toByte(String) 1 failed", NumberUtils.toByte("123") == 123);
        assertTrue("toByte(String) 2 failed", NumberUtils.toByte("abc") == 0);
        assertTrue("toByte(empty) failed", NumberUtils.toByte("") == 0);
        assertTrue("toByte(null) failed", NumberUtils.toByte(null) == 0);
    }

    /
     
     
    @Test
    public void testToByteStringI() {
        assertTrue("toByte(String,byte) 1 failed", NumberUtils.toByte("123", (byte) 5) == 123);
        assertTrue("toByte(String,byte) 2 failed", NumberUtils.toByte("12.3", (byte) 5) == 5);
    }

    /
     
     
    @Test
    public void testToShortString() {
        assertTrue("toShort(String) 1 failed", NumberUtils.toShort("12345") == 12345);
        assertTrue("toShort(String) 2 failed", NumberUtils.toShort("abc") == 0);
        assertTrue("toShort(empty) failed", NumberUtils.toShort("") == 0);
        assertTrue("toShort(null) failed", NumberUtils.toShort(null) == 0);
    }

    /
     
     
    @Test
    public void testToShortStringI() {
        assertTrue("toShort(String,short) 1 failed", NumberUtils.toShort("12345", (short) 5) == 12345);
        assertTrue("toShort(String,short) 2 failed", NumberUtils.toShort("1234.5", (short) 5) == 5);
    }

    @Test
    public void testCreateNumber() {
        
        assertEquals("createNumber(String) 1 failed", Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5"));
        assertEquals("createNumber(String) 2 failed", Integer.valueOf("12345"), NumberUtils.createNumber("12345"));
        assertEquals("createNumber(String) 3 failed", Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5D"));
        assertEquals("createNumber(String) 3 failed", Double.valueOf("1234.5"), NumberUtils.createNumber("1234.5d"));
        assertEquals("createNumber(String) 4 failed", Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5F"));
        assertEquals("createNumber(String) 4 failed", Float.valueOf("1234.5"), NumberUtils.createNumber("1234.5f"));
        assertEquals("createNumber(String) 5 failed", Long.valueOf(Integer.MAX_VALUE + 1L), NumberUtils.createNumber(""
            + (Integer.MAX_VALUE + 1L)));
        assertEquals("createNumber(String) 6 failed", Long.valueOf(12345), NumberUtils.createNumber("12345L"));
        assertEquals("createNumber(String) 6 failed", Long.valueOf(12345), NumberUtils.createNumber("12345l"));
        assertEquals("createNumber(String) 7 failed", Float.valueOf("-1234.5"), NumberUtils.createNumber("-1234.5"));
        assertEquals("createNumber(String) 8 failed", Integer.valueOf("-12345"), NumberUtils.createNumber("-12345"));
        assertTrue("createNumber(String) 9a failed", 0xFADE == NumberUtils.createNumber("0xFADE").intValue());
        assertTrue("createNumber(String) 9b failed", 0xFADE == NumberUtils.createNumber("0Xfade").intValue());
        assertTrue("createNumber(String) 10a failed", -0xFADE == NumberUtils.createNumber("-0xFADE").intValue());
        assertTrue("createNumber(String) 10b failed", -0xFADE == NumberUtils.createNumber("-0Xfade").intValue());
        assertEquals("createNumber(String) 11 failed", Double.valueOf("1.1E200"), NumberUtils.createNumber("1.1E200"));
        assertEquals("createNumber(String) 12 failed", Float.valueOf("1.1E20"), NumberUtils.createNumber("1.1E20"));
        assertEquals("createNumber(String) 13 failed", Double.valueOf("-1.1E200"), NumberUtils.createNumber("-1.1E200"));
        assertEquals("createNumber(String) 14 failed", Double.valueOf("1.1E-200"), NumberUtils.createNumber("1.1E-200"));
        assertEquals("createNumber(null) failed", null, NumberUtils.createNumber(null));
        assertEquals("createNumber(String) failed", new BigInteger("12345678901234567890"), NumberUtils
                .createNumber("12345678901234567890L"));

        assertEquals("createNumber(String) 15 failed", new BigDecimal("1.1E-700"), NumberUtils
                    .createNumber("1.1E-700F"));

        assertEquals("createNumber(String) 16 failed", Long.valueOf("10" + Integer.MAX_VALUE), NumberUtils
                .createNumber("10" + Integer.MAX_VALUE + "L"));
        assertEquals("createNumber(String) 17 failed", Long.valueOf("10" + Integer.MAX_VALUE), NumberUtils
                .createNumber("10" + Integer.MAX_VALUE));
        assertEquals("createNumber(String) 18 failed", new BigInteger("10" + Long.MAX_VALUE), NumberUtils
                .createNumber("10" + Long.MAX_VALUE));

        
        assertEquals("createNumber(String) LANG-521 failed", Float.valueOf("2."), NumberUtils.createNumber("2."));

        
        assertFalse("createNumber(String) succeeded", checkCreateNumber("1eE"));

        
        assertEquals("createNumber(String) LANG-693 failed", Double.valueOf(Double.MAX_VALUE), NumberUtils
                    .createNumber("" + Double.MAX_VALUE));

        
        
        final Number bigNum = NumberUtils.createNumber("-1.1E-700F");
        assertNotNull(bigNum);
        assertEquals(BigDecimal.class, bigNum.getClass());
    }

    @Test
    public void TestLang747() {
        assertEquals(Integer.valueOf(0x8000),      NumberUtils.createNumber("0x8000"));
        assertEquals(Integer.valueOf(0x80000),     NumberUtils.createNumber("0x80000"));
        assertEquals(Integer.valueOf(0x800000),    NumberUtils.createNumber("0x800000"));
        assertEquals(Integer.valueOf(0x8000000),   NumberUtils.createNumber("0x8000000"));
        assertEquals(Integer.valueOf(0x7FFFFFFF),  NumberUtils.createNumber("0x7FFFFFFF"));
        assertEquals(Long.valueOf(0x80000000L),    NumberUtils.createNumber("0x80000000"));
        assertEquals(Long.valueOf(0xFFFFFFFFL),    NumberUtils.createNumber("0xFFFFFFFF"));

        
        assertEquals(Integer.valueOf(0x8000000),   NumberUtils.createNumber("0x08000000"));
        assertEquals(Integer.valueOf(0x7FFFFFFF),  NumberUtils.createNumber("0x007FFFFFFF"));
        assertEquals(Long.valueOf(0x80000000L),    NumberUtils.createNumber("0x080000000"));
        assertEquals(Long.valueOf(0xFFFFFFFFL),    NumberUtils.createNumber("0x00FFFFFFFF"));

        assertEquals(Long.valueOf(0x800000000L),        NumberUtils.createNumber("0x800000000"));
        assertEquals(Long.valueOf(0x8000000000L),       NumberUtils.createNumber("0x8000000000"));
        assertEquals(Long.valueOf(0x80000000000L),      NumberUtils.createNumber("0x80000000000"));
        assertEquals(Long.valueOf(0x800000000000L),     NumberUtils.createNumber("0x800000000000"));
        assertEquals(Long.valueOf(0x8000000000000L),    NumberUtils.createNumber("0x8000000000000"));
        assertEquals(Long.valueOf(0x80000000000000L),   NumberUtils.createNumber("0x80000000000000"));
        assertEquals(Long.valueOf(0x800000000000000L),  NumberUtils.createNumber("0x800000000000000"));
        assertEquals(Long.valueOf(0x7FFFFFFFFFFFFFFFL), NumberUtils.createNumber("0x7FFFFFFFFFFFFFFF"));
        
        assertEquals(new BigInteger("8000000000000000", 16), NumberUtils.createNumber("0x8000000000000000"));
        assertEquals(new BigInteger("FFFFFFFFFFFFFFFF", 16), NumberUtils.createNumber("0xFFFFFFFFFFFFFFFF"));

        
        assertEquals(Long.valueOf(0x80000000000000L),   NumberUtils.createNumber("0x00080000000000000"));
        assertEquals(Long.valueOf(0x800000000000000L),  NumberUtils.createNumber("0x0800000000000000"));
        assertEquals(Long.valueOf(0x7FFFFFFFFFFFFFFFL), NumberUtils.createNumber("0x07FFFFFFFFFFFFFFF"));
        
        assertEquals(new BigInteger("8000000000000000", 16), NumberUtils.createNumber("0x00008000000000000000"));
        assertEquals(new BigInteger("FFFFFFFFFFFFFFFF", 16), NumberUtils.createNumber("0x0FFFFFFFFFFFFFFFF"));
    }

    @Test(expected=NumberFormatException.class)
    
    public void testCreateNumberFailure_1() {
        NumberUtils.createNumber("--1.1E-700F");
    }

    @Test(expected=NumberFormatException.class)
    
    public void testCreateNumberFailure_2() {
        NumberUtils.createNumber("-1.1E+0-7e00");
    }

    @Test(expected=NumberFormatException.class)
    
    public void testCreateNumberFailure_3() {
        NumberUtils.createNumber("-11E+0-7e00");
    }

    @Test(expected=NumberFormatException.class)
    
    public void testCreateNumberFailure_4() {
        NumberUtils.createNumber("1eE+00001");
    }

    
    
    @Test
    public void testCreateNumberMagnitude() {
        
        assertEquals(Float.valueOf(Float.MAX_VALUE),  NumberUtils.createNumber("3.4028235e+38"));
        assertEquals(Double.valueOf(3.4028236e+38),   NumberUtils.createNumber("3.4028236e+38"));

        
        assertEquals(Double.valueOf(Double.MAX_VALUE),          NumberUtils.createNumber("1.7976931348623157e+308"));
        
        assertEquals(new BigDecimal("1.7976931348623159e+308"), NumberUtils.createNumber("1.7976931348623159e+308"));

        assertEquals(Integer.valueOf(0x12345678), NumberUtils.createNumber("0x12345678"));
        assertEquals(Long.valueOf(0x123456789L),  NumberUtils.createNumber("0x123456789"));

        assertEquals(Long.valueOf(0x7fffffffffffffffL),      NumberUtils.createNumber("0x7fffffffffffffff"));
        
        assertEquals(new BigInteger("7fffffffffffffff0",16), NumberUtils.createNumber("0x7fffffffffffffff0"));

        assertEquals(Long.valueOf(0x7fffffffffffffffL),      NumberUtils.createNumber("#7fffffffffffffff"));
        assertEquals(new BigInteger("7fffffffffffffff0",16), NumberUtils.createNumber("#7fffffffffffffff0"));

        assertEquals(Integer.valueOf(017777777777), NumberUtils.createNumber("017777777777")); 
        assertEquals(Long.valueOf(037777777777L),   NumberUtils.createNumber("037777777777")); 

        assertEquals(Long.valueOf(0777777777777777777777L),      NumberUtils.createNumber("0777777777777777777777")); 
        assertEquals(new BigInteger("1777777777777777777777",8), NumberUtils.createNumber("01777777777777777777777"));
    }

    @Test
    public void testCreateFloat() {
        assertEquals("createFloat(String) failed", Float.valueOf("1234.5"), NumberUtils.createFloat("1234.5"));
        assertEquals("createFloat(null) failed", null, NumberUtils.createFloat(null));
        this.testCreateFloatFailure("");
        this.testCreateFloatFailure(" ");
        this.testCreateFloatFailure("\b\t\n\f\r");
        
        this.testCreateFloatFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
    }

    protected void testCreateFloatFailure(final String str) {
        try {
            final Float value = NumberUtils.createFloat(str);
            fail("createFloat(\"" + str + "\") should have failed: " + value);
        } catch (final NumberFormatException ex) {
            
        }
    }

    @Test
    public void testCreateDouble() {
        assertEquals("createDouble(String) failed", Double.valueOf("1234.5"), NumberUtils.createDouble("1234.5"));
        assertEquals("createDouble(null) failed", null, NumberUtils.createDouble(null));
        this.testCreateDoubleFailure("");
        this.testCreateDoubleFailure(" ");
        this.testCreateDoubleFailure("\b\t\n\f\r");
        
        this.testCreateDoubleFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
    }

    protected void testCreateDoubleFailure(final String str) {
        try {
            final Double value = NumberUtils.createDouble(str);
            fail("createDouble(\"" + str + "\") should have failed: " + value);
        } catch (final NumberFormatException ex) {
            
        }
    }

    @Test
    public void testCreateInteger() {
        assertEquals("createInteger(String) failed", Integer.valueOf("12345"), NumberUtils.createInteger("12345"));
        assertEquals("createInteger(null) failed", null, NumberUtils.createInteger(null));
        this.testCreateIntegerFailure("");
        this.testCreateIntegerFailure(" ");
        this.testCreateIntegerFailure("\b\t\n\f\r");
        
        this.testCreateIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
    }

    protected void testCreateIntegerFailure(final String str) {
        try {
            final Integer value = NumberUtils.createInteger(str);
            fail("createInteger(\"" + str + "\") should have failed: " + value);
        } catch (final NumberFormatException ex) {
            
        }
    }

    @Test
    public void testCreateLong() {
        assertEquals("createLong(String) failed", Long.valueOf("12345"), NumberUtils.createLong("12345"));
        assertEquals("createLong(null) failed", null, NumberUtils.createLong(null));
        this.testCreateLongFailure("");
        this.testCreateLongFailure(" ");
        this.testCreateLongFailure("\b\t\n\f\r");
        
        this.testCreateLongFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
    }

    protected void testCreateLongFailure(final String str) {
        try {
            final Long value = NumberUtils.createLong(str);
            fail("createLong(\"" + str + "\") should have failed: " + value);
        } catch (final NumberFormatException ex) {
            
        }
    }

    @Test
    public void testCreateBigInteger() {
        assertEquals("createBigInteger(String) failed", new BigInteger("12345"), NumberUtils.createBigInteger("12345"));
        assertEquals("createBigInteger(null) failed", null, NumberUtils.createBigInteger(null));
        this.testCreateBigIntegerFailure("");
        this.testCreateBigIntegerFailure(" ");
        this.testCreateBigIntegerFailure("\b\t\n\f\r");
        
        this.testCreateBigIntegerFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        assertEquals("createBigInteger(String) failed", new BigInteger("255"), NumberUtils.createBigInteger("0xff"));
        assertEquals("createBigInteger(String) failed", new BigInteger("255"), NumberUtils.createBigInteger("#ff"));
        assertEquals("createBigInteger(String) failed", new BigInteger("-255"), NumberUtils.createBigInteger("-0xff"));
        assertEquals("createBigInteger(String) failed", new BigInteger("255"), NumberUtils.createBigInteger("0377"));
        assertEquals("createBigInteger(String) failed", new BigInteger("-255"), NumberUtils.createBigInteger("-0377"));
        assertEquals("createBigInteger(String) failed", new BigInteger("-255"), NumberUtils.createBigInteger("-0377"));
        assertEquals("createBigInteger(String) failed", new BigInteger("-0"), NumberUtils.createBigInteger("-0"));
        assertEquals("createBigInteger(String) failed", new BigInteger("0"), NumberUtils.createBigInteger("0"));
        testCreateBigIntegerFailure("#");
        testCreateBigIntegerFailure("-#");
        testCreateBigIntegerFailure("0x");
        testCreateBigIntegerFailure("-0x");
    }

    protected void testCreateBigIntegerFailure(final String str) {
        try {
            final BigInteger value = NumberUtils.createBigInteger(str);
            fail("createBigInteger(\"" + str + "\") should have failed: " + value);
        } catch (final NumberFormatException ex) {
            
        }
    }

    @Test
    public void testCreateBigDecimal() {
        assertEquals("createBigDecimal(String) failed", new BigDecimal("1234.5"), NumberUtils.createBigDecimal("1234.5"));
        assertEquals("createBigDecimal(null) failed", null, NumberUtils.createBigDecimal(null));
        this.testCreateBigDecimalFailure("");
        this.testCreateBigDecimalFailure(" ");
        this.testCreateBigDecimalFailure("\b\t\n\f\r");
        
        this.testCreateBigDecimalFailure("\u00A0\uFEFF\u000B\u000C\u001C\u001D\u001E\u001F");
        this.testCreateBigDecimalFailure("-"); 
        this.testCreateBigDecimalFailure("--"); 
        this.testCreateBigDecimalFailure("--0");
        this.testCreateBigDecimalFailure("+"); 
        this.testCreateBigDecimalFailure("++"); 
        this.testCreateBigDecimalFailure("++0");
    }

    protected void testCreateBigDecimalFailure(final String str) {
        try {
            final BigDecimal value = NumberUtils.createBigDecimal(str);
            fail("createBigDecimal(\"" + str + "\") should have failed: " + value);
        } catch (final NumberFormatException ex) {
            
        }
    }

    
    
    @Test(expected = IllegalArgumentException.class)
    public void testMinLong_nullArray() {
        NumberUtils.min((long[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinLong_emptyArray() {
        NumberUtils.min(new long[0]);
    }

    @Test
    public void testMinLong() {
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

    @Test(expected = IllegalArgumentException.class)
    public void testMinInt_nullArray() {
        NumberUtils.min((int[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinInt_emptyArray() {
        NumberUtils.min(new int[0]);
    }

    @Test
    public void testMinInt() {
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

    @Test(expected = IllegalArgumentException.class)
    public void testMinShort_nullArray() {
        NumberUtils.min((short[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinShort_emptyArray() {
        NumberUtils.min(new short[0]);
    }

    @Test
    public void testMinShort() {
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
    public void testMinByte_nullArray() {
        NumberUtils.min((byte[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinByte_emptyArray() {
        NumberUtils.min(new byte[0]);
    }

    @Test
    public void testMinByte() {
        assertEquals(
            "min(byte[]) failed for array length 1",
            5,
            NumberUtils.min(new byte[] { 5 }));

        assertEquals(
            "min(byte[]) failed for array length 2",
            6,
            NumberUtils.min(new byte[] { 6, 9 }));

        assertEquals(-10, NumberUtils.min(new byte[] { -10, -5, 0, 5, 10 }));
        assertEquals(-10, NumberUtils.min(new byte[] { -5, 0, -10, 5, 10 }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinDouble_nullArray() {
        NumberUtils.min((double[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinDouble_emptyArray() {
        NumberUtils.min(new double[0]);
    }

    @Test
    public void testMinDouble() {
        assertEquals(
            "min(double[]) failed for array length 1",
            5.12,
            NumberUtils.min(new double[] { 5.12 }),
            0);

        assertEquals(
            "min(double[]) failed for array length 2",
            6.23,
            NumberUtils.min(new double[] { 6.23, 9.34 }),
            0);

        assertEquals(
            "min(double[]) failed for array length 5",
            -10.45,
            NumberUtils.min(new double[] { -10.45, -5.56, 0, 5.67, 10.78 }),
            0);
        assertEquals(-10, NumberUtils.min(new double[] { -10, -5, 0, 5, 10 }), 0.0001);
        assertEquals(-10, NumberUtils.min(new double[] { -5, 0, -10, 5, 10 }), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinFloat_nullArray() {
        NumberUtils.min((float[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinFloat_emptyArray() {
        NumberUtils.min(new float[0]);
    }

    @Test
    public void testMinFloat() {
        assertEquals(
            "min(float[]) failed for array length 1",
            5.9f,
            NumberUtils.min(new float[] { 5.9f }),
            0);

        assertEquals(
            "min(float[]) failed for array length 2",
            6.8f,
            NumberUtils.min(new float[] { 6.8f, 9.7f }),
            0);

        assertEquals(
            "min(float[]) failed for array length 5",
            -10.6f,
            NumberUtils.min(new float[] { -10.6f, -5.5f, 0, 5.4f, 10.3f }),
            0);
        assertEquals(-10, NumberUtils.min(new float[] { -10, -5, 0, 5, 10 }), 0.0001f);
        assertEquals(-10, NumberUtils.min(new float[] { -5, 0, -10, 5, 10 }), 0.0001f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxLong_nullArray() {
        NumberUtils.max((long[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxLong_emptyArray() {
        NumberUtils.max(new long[0]);
    }

    @Test
    public void testMaxLong() {
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
        assertEquals(10, NumberUtils.max(new long[] { -10, -5, 0, 5, 10 }));
        assertEquals(10, NumberUtils.max(new long[] { -5, 0, 10, 5, -10 }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxInt_nullArray() {
        NumberUtils.max((int[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxInt_emptyArray() {
        NumberUtils.max(new int[0]);
    }

    @Test
    public void testMaxInt() {
        assertEquals(
            "max(int[]) failed for array length 1",
            5,
            NumberUtils.max(new int[] { 5 }));

        assertEquals(
            "max(int[]) failed for array length 2",
            9,
            NumberUtils.max(new int[] { 6, 9 }));

        assertEquals(
            "max(int[]) failed for array length 5",
            10,
            NumberUtils.max(new int[] { -10, -5, 0, 5, 10 }));
        assertEquals(10, NumberUtils.max(new int[] { -10, -5, 0, 5, 10 }));
        assertEquals(10, NumberUtils.max(new int[] { -5, 0, 10, 5, -10 }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxShort_nullArray() {
        NumberUtils.max((short[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxShort_emptyArray() {
        NumberUtils.max(new short[0]);
    }

    @Test
    public void testMaxShort() {
        assertEquals(
            "max(short[]) failed for array length 1",
            5,
            NumberUtils.max(new short[] { 5 }));

        assertEquals(
            "max(short[]) failed for array length 2",
            9,
            NumberUtils.max(new short[] { 6, 9 }));

        assertEquals(
            "max(short[]) failed for array length 5",
            10,
            NumberUtils.max(new short[] { -10, -5, 0, 5, 10 }));
        assertEquals(10, NumberUtils.max(new short[] { -10, -5, 0, 5, 10 }));
        assertEquals(10, NumberUtils.max(new short[] { -5, 0, 10, 5, -10 }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxByte_nullArray() {
        NumberUtils.max((byte[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxByte_emptyArray() {
        NumberUtils.max(new byte[0]);
    }

    @Test
    public void testMaxByte() {
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
        assertEquals(10, NumberUtils.max(new byte[] { -10, -5, 0, 5, 10 }));
        assertEquals(10, NumberUtils.max(new byte[] { -5, 0, 10, 5, -10 }));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxDouble_nullArray() {
        NumberUtils.max((double[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxDouble_emptyArray() {
        NumberUtils.max(new double[0]);
    }

    @Test
    public void testMaxDouble() {
        final double[] d = null;
        try {
            NumberUtils.max(d);
            fail("No exception was thrown for null input.");
        } catch (final IllegalArgumentException ex) {}

        try {
            NumberUtils.max(new double[0]);
            fail("No exception was thrown for empty input.");
        } catch (final IllegalArgumentException ex) {}

        assertEquals(
            "max(double[]) failed for array length 1",
            5.1f,
            NumberUtils.max(new double[] { 5.1f }),
            0);

        assertEquals(
            "max(double[]) failed for array length 2",
            9.2f,
            NumberUtils.max(new double[] { 6.3f, 9.2f }),
            0);

        assertEquals(
            "max(double[]) failed for float length 5",
            10.4f,
            NumberUtils.max(new double[] { -10.5f, -5.6f, 0, 5.7f, 10.4f }),
            0);
        assertEquals(10, NumberUtils.max(new double[] { -10, -5, 0, 5, 10 }), 0.0001);
        assertEquals(10, NumberUtils.max(new double[] { -5, 0, 10, 5, -10 }), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxFloat_nullArray() {
        NumberUtils.max((float[]) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxFloat_emptyArray() {
        NumberUtils.max(new float[0]);
    }

    @Test
    public void testMaxFloat() {
        assertEquals(
            "max(float[]) failed for array length 1",
            5.1f,
            NumberUtils.max(new float[] { 5.1f }),
            0);

        assertEquals(
            "max(float[]) failed for array length 2",
            9.2f,
            NumberUtils.max(new float[] { 6.3f, 9.2f }),
            0);

        assertEquals(
            "max(float[]) failed for float length 5",
            10.4f,
            NumberUtils.max(new float[] { -10.5f, -5.6f, 0, 5.7f, 10.4f }),
            0);
        assertEquals(10, NumberUtils.max(new float[] { -10, -5, 0, 5, 10 }), 0.0001f);
        assertEquals(10, NumberUtils.max(new float[] { -5, 0, 10, 5, -10 }), 0.0001f);
    }

    @Test
    public void testMinimumLong() {
        assertEquals("minimum(long,long,long) 1 failed", 12345L, NumberUtils.min(12345L, 12345L + 1L, 12345L + 2L));
        assertEquals("minimum(long,long,long) 2 failed", 12345L, NumberUtils.min(12345L + 1L, 12345L, 12345 + 2L));
        assertEquals("minimum(long,long,long) 3 failed", 12345L, NumberUtils.min(12345L + 1L, 12345L + 2L, 12345L));
        assertEquals("minimum(long,long,long) 4 failed", 12345L, NumberUtils.min(12345L + 1L, 12345L, 12345L));
        assertEquals("minimum(long,long,long) 5 failed", 12345L, NumberUtils.min(12345L, 12345L, 12345L));
    }

    @Test
    public void testMinimumInt() {
        assertEquals("minimum(int,int,int) 1 failed", 12345, NumberUtils.min(12345, 12345 + 1, 12345 + 2));
        assertEquals("minimum(int,int,int) 2 failed", 12345, NumberUtils.min(12345 + 1, 12345, 12345 + 2));
        assertEquals("minimum(int,int,int) 3 failed", 12345, NumberUtils.min(12345 + 1, 12345 + 2, 12345));
        assertEquals("minimum(int,int,int) 4 failed", 12345, NumberUtils.min(12345 + 1, 12345, 12345));
        assertEquals("minimum(int,int,int) 5 failed", 12345, NumberUtils.min(12345, 12345, 12345));
    }

    @Test
    public void testMinimumShort() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        assertEquals("minimum(short,short,short) 1 failed", low, NumberUtils.min(low, mid, high));
        assertEquals("minimum(short,short,short) 1 failed", low, NumberUtils.min(mid, low, high));
        assertEquals("minimum(short,short,short) 1 failed", low, NumberUtils.min(mid, high, low));
        assertEquals("minimum(short,short,short) 1 failed", low, NumberUtils.min(low, mid, low));
    }

    @Test
    public void testMinimumByte() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        assertEquals("minimum(byte,byte,byte) 1 failed", low, NumberUtils.min(low, mid, high));
        assertEquals("minimum(byte,byte,byte) 1 failed", low, NumberUtils.min(mid, low, high));
        assertEquals("minimum(byte,byte,byte) 1 failed", low, NumberUtils.min(mid, high, low));
        assertEquals("minimum(byte,byte,byte) 1 failed", low, NumberUtils.min(low, mid, low));
    }

    @Test
    public void testMinimumDouble() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001);
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001);
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001);
        assertEquals(low, NumberUtils.min(low, mid, low), 0.0001);
        assertEquals(mid, NumberUtils.min(high, mid, high), 0.0001);
    }

    @Test
    public void testMinimumFloat() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        assertEquals(low, NumberUtils.min(low, mid, high), 0.0001f);
        assertEquals(low, NumberUtils.min(mid, low, high), 0.0001f);
        assertEquals(low, NumberUtils.min(mid, high, low), 0.0001f);
        assertEquals(low, NumberUtils.min(low, mid, low), 0.0001f);
        assertEquals(mid, NumberUtils.min(high, mid, high), 0.0001f);
    }

    @Test
    public void testMaximumLong() {
        assertEquals("maximum(long,long,long) 1 failed", 12345L, NumberUtils.max(12345L, 12345L - 1L, 12345L - 2L));
        assertEquals("maximum(long,long,long) 2 failed", 12345L, NumberUtils.max(12345L - 1L, 12345L, 12345L - 2L));
        assertEquals("maximum(long,long,long) 3 failed", 12345L, NumberUtils.max(12345L - 1L, 12345L - 2L, 12345L));
        assertEquals("maximum(long,long,long) 4 failed", 12345L, NumberUtils.max(12345L - 1L, 12345L, 12345L));
        assertEquals("maximum(long,long,long) 5 failed", 12345L, NumberUtils.max(12345L, 12345L, 12345L));
    }

    @Test
    public void testMaximumInt() {
        assertEquals("maximum(int,int,int) 1 failed", 12345, NumberUtils.max(12345, 12345 - 1, 12345 - 2));
        assertEquals("maximum(int,int,int) 2 failed", 12345, NumberUtils.max(12345 - 1, 12345, 12345 - 2));
        assertEquals("maximum(int,int,int) 3 failed", 12345, NumberUtils.max(12345 - 1, 12345 - 2, 12345));
        assertEquals("maximum(int,int,int) 4 failed", 12345, NumberUtils.max(12345 - 1, 12345, 12345));
        assertEquals("maximum(int,int,int) 5 failed", 12345, NumberUtils.max(12345, 12345, 12345));
    }

    @Test
    public void testMaximumShort() {
        final short low = 1234;
        final short mid = 1234 + 1;
        final short high = 1234 + 2;
        assertEquals("maximum(short,short,short) 1 failed", high, NumberUtils.max(low, mid, high));
        assertEquals("maximum(short,short,short) 1 failed", high, NumberUtils.max(mid, low, high));
        assertEquals("maximum(short,short,short) 1 failed", high, NumberUtils.max(mid, high, low));
        assertEquals("maximum(short,short,short) 1 failed", high, NumberUtils.max(high, mid, high));
    }

    @Test
    public void testMaximumByte() {
        final byte low = 123;
        final byte mid = 123 + 1;
        final byte high = 123 + 2;
        assertEquals("maximum(byte,byte,byte) 1 failed", high, NumberUtils.max(low, mid, high));
        assertEquals("maximum(byte,byte,byte) 1 failed", high, NumberUtils.max(mid, low, high));
        assertEquals("maximum(byte,byte,byte) 1 failed", high, NumberUtils.max(mid, high, low));
        assertEquals("maximum(byte,byte,byte) 1 failed", high, NumberUtils.max(high, mid, high));
    }

    @Test
    public void testMaximumDouble() {
        final double low = 12.3;
        final double mid = 12.3 + 1;
        final double high = 12.3 + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001);
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001);
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001);
        assertEquals(mid, NumberUtils.max(low, mid, low), 0.0001);
        assertEquals(high, NumberUtils.max(high, mid, high), 0.0001);
    }

    @Test
    public void testMaximumFloat() {
        final float low = 12.3f;
        final float mid = 12.3f + 1;
        final float high = 12.3f + 2;
        assertEquals(high, NumberUtils.max(low, mid, high), 0.0001f);
        assertEquals(high, NumberUtils.max(mid, low, high), 0.0001f);
        assertEquals(high, NumberUtils.max(mid, high, low), 0.0001f);
        assertEquals(mid, NumberUtils.max(low, mid, low), 0.0001f);
        assertEquals(high, NumberUtils.max(high, mid, high), 0.0001f);
    }

    
    @Test
    public void testCompareDouble() {
        assertTrue(Double.compare(Double.NaN, Double.NaN) == 0);
        assertTrue(Double.compare(Double.NaN, Double.POSITIVE_INFINITY) == +1);
        assertTrue(Double.compare(Double.NaN, Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(Double.NaN, 1.2d) == +1);
        assertTrue(Double.compare(Double.NaN, 0.0d) == +1);
        assertTrue(Double.compare(Double.NaN, -0.0d) == +1);
        assertTrue(Double.compare(Double.NaN, -1.2d) == +1);
        assertTrue(Double.compare(Double.NaN, -Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(Double.NaN, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(Double.POSITIVE_INFINITY, Double.NaN) == -1);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY) == 0);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, 1.2d) == +1);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, 0.0d) == +1);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, -0.0d) == +1);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, -1.2d) == +1);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, -Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(Double.MAX_VALUE, Double.NaN) == -1);
        assertTrue(Double.compare(Double.MAX_VALUE, Double.POSITIVE_INFINITY) == -1);
        assertTrue(Double.compare(Double.MAX_VALUE, Double.MAX_VALUE) == 0);
        assertTrue(Double.compare(Double.MAX_VALUE, 1.2d) == +1);
        assertTrue(Double.compare(Double.MAX_VALUE, 0.0d) == +1);
        assertTrue(Double.compare(Double.MAX_VALUE, -0.0d) == +1);
        assertTrue(Double.compare(Double.MAX_VALUE, -1.2d) == +1);
        assertTrue(Double.compare(Double.MAX_VALUE, -Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(Double.MAX_VALUE, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(1.2d, Double.NaN) == -1);
        assertTrue(Double.compare(1.2d, Double.POSITIVE_INFINITY) == -1);
        assertTrue(Double.compare(1.2d, Double.MAX_VALUE) == -1);
        assertTrue(Double.compare(1.2d, 1.2d) == 0);
        assertTrue(Double.compare(1.2d, 0.0d) == +1);
        assertTrue(Double.compare(1.2d, -0.0d) == +1);
        assertTrue(Double.compare(1.2d, -1.2d) == +1);
        assertTrue(Double.compare(1.2d, -Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(1.2d, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(0.0d, Double.NaN) == -1);
        assertTrue(Double.compare(0.0d, Double.POSITIVE_INFINITY) == -1);
        assertTrue(Double.compare(0.0d, Double.MAX_VALUE) == -1);
        assertTrue(Double.compare(0.0d, 1.2d) == -1);
        assertTrue(Double.compare(0.0d, 0.0d) == 0);
        assertTrue(Double.compare(0.0d, -0.0d) == +1);
        assertTrue(Double.compare(0.0d, -1.2d) == +1);
        assertTrue(Double.compare(0.0d, -Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(0.0d, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(-0.0d, Double.NaN) == -1);
        assertTrue(Double.compare(-0.0d, Double.POSITIVE_INFINITY) == -1);
        assertTrue(Double.compare(-0.0d, Double.MAX_VALUE) == -1);
        assertTrue(Double.compare(-0.0d, 1.2d) == -1);
        assertTrue(Double.compare(-0.0d, 0.0d) == -1);
        assertTrue(Double.compare(-0.0d, -0.0d) == 0);
        assertTrue(Double.compare(-0.0d, -1.2d) == +1);
        assertTrue(Double.compare(-0.0d, -Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(-0.0d, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(-1.2d, Double.NaN) == -1);
        assertTrue(Double.compare(-1.2d, Double.POSITIVE_INFINITY) == -1);
        assertTrue(Double.compare(-1.2d, Double.MAX_VALUE) == -1);
        assertTrue(Double.compare(-1.2d, 1.2d) == -1);
        assertTrue(Double.compare(-1.2d, 0.0d) == -1);
        assertTrue(Double.compare(-1.2d, -0.0d) == -1);
        assertTrue(Double.compare(-1.2d, -1.2d) == 0);
        assertTrue(Double.compare(-1.2d, -Double.MAX_VALUE) == +1);
        assertTrue(Double.compare(-1.2d, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(-Double.MAX_VALUE, Double.NaN) == -1);
        assertTrue(Double.compare(-Double.MAX_VALUE, Double.POSITIVE_INFINITY) == -1);
        assertTrue(Double.compare(-Double.MAX_VALUE, Double.MAX_VALUE) == -1);
        assertTrue(Double.compare(-Double.MAX_VALUE, 1.2d) == -1);
        assertTrue(Double.compare(-Double.MAX_VALUE, 0.0d) == -1);
        assertTrue(Double.compare(-Double.MAX_VALUE, -0.0d) == -1);
        assertTrue(Double.compare(-Double.MAX_VALUE, -1.2d) == -1);
        assertTrue(Double.compare(-Double.MAX_VALUE, -Double.MAX_VALUE) == 0);
        assertTrue(Double.compare(-Double.MAX_VALUE, Double.NEGATIVE_INFINITY) == +1);

        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, Double.NaN) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, Double.MAX_VALUE) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, 1.2d) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, 0.0d) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, -0.0d) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, -1.2d) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, -Double.MAX_VALUE) == -1);
        assertTrue(Double.compare(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY) == 0);
    }

    @Test
    public void testCompareFloat() {
        assertTrue(Float.compare(Float.NaN, Float.NaN) == 0);
        assertTrue(Float.compare(Float.NaN, Float.POSITIVE_INFINITY) == +1);
        assertTrue(Float.compare(Float.NaN, Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(Float.NaN, 1.2f) == +1);
        assertTrue(Float.compare(Float.NaN, 0.0f) == +1);
        assertTrue(Float.compare(Float.NaN, -0.0f) == +1);
        assertTrue(Float.compare(Float.NaN, -1.2f) == +1);
        assertTrue(Float.compare(Float.NaN, -Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(Float.NaN, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(Float.POSITIVE_INFINITY, Float.NaN) == -1);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY) == 0);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, 1.2f) == +1);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, 0.0f) == +1);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, -0.0f) == +1);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, -1.2f) == +1);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, -Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(Float.MAX_VALUE, Float.NaN) == -1);
        assertTrue(Float.compare(Float.MAX_VALUE, Float.POSITIVE_INFINITY) == -1);
        assertTrue(Float.compare(Float.MAX_VALUE, Float.MAX_VALUE) == 0);
        assertTrue(Float.compare(Float.MAX_VALUE, 1.2f) == +1);
        assertTrue(Float.compare(Float.MAX_VALUE, 0.0f) == +1);
        assertTrue(Float.compare(Float.MAX_VALUE, -0.0f) == +1);
        assertTrue(Float.compare(Float.MAX_VALUE, -1.2f) == +1);
        assertTrue(Float.compare(Float.MAX_VALUE, -Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(Float.MAX_VALUE, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(1.2f, Float.NaN) == -1);
        assertTrue(Float.compare(1.2f, Float.POSITIVE_INFINITY) == -1);
        assertTrue(Float.compare(1.2f, Float.MAX_VALUE) == -1);
        assertTrue(Float.compare(1.2f, 1.2f) == 0);
        assertTrue(Float.compare(1.2f, 0.0f) == +1);
        assertTrue(Float.compare(1.2f, -0.0f) == +1);
        assertTrue(Float.compare(1.2f, -1.2f) == +1);
        assertTrue(Float.compare(1.2f, -Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(1.2f, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(0.0f, Float.NaN) == -1);
        assertTrue(Float.compare(0.0f, Float.POSITIVE_INFINITY) == -1);
        assertTrue(Float.compare(0.0f, Float.MAX_VALUE) == -1);
        assertTrue(Float.compare(0.0f, 1.2f) == -1);
        assertTrue(Float.compare(0.0f, 0.0f) == 0);
        assertTrue(Float.compare(0.0f, -0.0f) == +1);
        assertTrue(Float.compare(0.0f, -1.2f) == +1);
        assertTrue(Float.compare(0.0f, -Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(0.0f, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(-0.0f, Float.NaN) == -1);
        assertTrue(Float.compare(-0.0f, Float.POSITIVE_INFINITY) == -1);
        assertTrue(Float.compare(-0.0f, Float.MAX_VALUE) == -1);
        assertTrue(Float.compare(-0.0f, 1.2f) == -1);
        assertTrue(Float.compare(-0.0f, 0.0f) == -1);
        assertTrue(Float.compare(-0.0f, -0.0f) == 0);
        assertTrue(Float.compare(-0.0f, -1.2f) == +1);
        assertTrue(Float.compare(-0.0f, -Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(-0.0f, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(-1.2f, Float.NaN) == -1);
        assertTrue(Float.compare(-1.2f, Float.POSITIVE_INFINITY) == -1);
        assertTrue(Float.compare(-1.2f, Float.MAX_VALUE) == -1);
        assertTrue(Float.compare(-1.2f, 1.2f) == -1);
        assertTrue(Float.compare(-1.2f, 0.0f) == -1);
        assertTrue(Float.compare(-1.2f, -0.0f) == -1);
        assertTrue(Float.compare(-1.2f, -1.2f) == 0);
        assertTrue(Float.compare(-1.2f, -Float.MAX_VALUE) == +1);
        assertTrue(Float.compare(-1.2f, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(-Float.MAX_VALUE, Float.NaN) == -1);
        assertTrue(Float.compare(-Float.MAX_VALUE, Float.POSITIVE_INFINITY) == -1);
        assertTrue(Float.compare(-Float.MAX_VALUE, Float.MAX_VALUE) == -1);
        assertTrue(Float.compare(-Float.MAX_VALUE, 1.2f) == -1);
        assertTrue(Float.compare(-Float.MAX_VALUE, 0.0f) == -1);
        assertTrue(Float.compare(-Float.MAX_VALUE, -0.0f) == -1);
        assertTrue(Float.compare(-Float.MAX_VALUE, -1.2f) == -1);
        assertTrue(Float.compare(-Float.MAX_VALUE, -Float.MAX_VALUE) == 0);
        assertTrue(Float.compare(-Float.MAX_VALUE, Float.NEGATIVE_INFINITY) == +1);

        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, Float.NaN) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, Float.MAX_VALUE) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, 1.2f) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, 0.0f) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, -0.0f) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, -1.2f) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, -Float.MAX_VALUE) == -1);
        assertTrue(Float.compare(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY) == 0);
    }

    @Test
    public void testIsDigits() {
        assertFalse("isDigits(null) failed", NumberUtils.isDigits(null));
        assertFalse("isDigits('') failed", NumberUtils.isDigits(""));
        assertTrue("isDigits(String) failed", NumberUtils.isDigits("12345"));
        assertFalse("isDigits(String) neg 1 failed", NumberUtils.isDigits("1234.5"));
        assertFalse("isDigits(String) neg 3 failed", NumberUtils.isDigits("1ab"));
        assertFalse("isDigits(String) neg 4 failed", NumberUtils.isDigits("abc"));
    }

    /
     
     
     
    @Test
    public void testIsNumber() {
        String val = "12345";
        assertTrue("isNumber(String) 1 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 1 failed", checkCreateNumber(val));
        val = "1234.5";
        assertTrue("isNumber(String) 2 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 2 failed", checkCreateNumber(val));
        val = ".12345";
        assertTrue("isNumber(String) 3 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 3 failed", checkCreateNumber(val));
        val = "1234E5";
        assertTrue("isNumber(String) 4 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 4 failed", checkCreateNumber(val));
        val = "1234E+5";
        assertTrue("isNumber(String) 5 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 5 failed", checkCreateNumber(val));
        val = "1234E-5";
        assertTrue("isNumber(String) 6 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 6 failed", checkCreateNumber(val));
        val = "123.4E5";
        assertTrue("isNumber(String) 7 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 7 failed", checkCreateNumber(val));
        val = "-1234";
        assertTrue("isNumber(String) 8 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 8 failed", checkCreateNumber(val));
        val = "-1234.5";
        assertTrue("isNumber(String) 9 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 9 failed", checkCreateNumber(val));
        val = "-.12345";
        assertTrue("isNumber(String) 10 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 10 failed", checkCreateNumber(val));
        val = "-1234E5";
        assertTrue("isNumber(String) 11 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 11 failed", checkCreateNumber(val));
        val = "0";
        assertTrue("isNumber(String) 12 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 12 failed", checkCreateNumber(val));
        val = "-0";
        assertTrue("isNumber(String) 13 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 13 failed", checkCreateNumber(val));
        val = "01234";
        assertTrue("isNumber(String) 14 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 14 failed", checkCreateNumber(val));
        val = "-01234";
        assertTrue("isNumber(String) 15 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 15 failed", checkCreateNumber(val));
        val = "0xABC123";
        assertTrue("isNumber(String) 16 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 16 failed", checkCreateNumber(val));
        val = "0x0";
        assertTrue("isNumber(String) 17 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 17 failed", checkCreateNumber(val));
        val = "123.4E21D";
        assertTrue("isNumber(String) 19 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 19 failed", checkCreateNumber(val));
        val = "-221.23F";
        assertTrue("isNumber(String) 20 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 20 failed", checkCreateNumber(val));
        val = "22338L";
        assertTrue("isNumber(String) 21 failed", NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 21 failed", checkCreateNumber(val));
        val = null;
        assertTrue("isNumber(String) 1 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 1 Neg failed", !checkCreateNumber(val));
        val = "";
        assertTrue("isNumber(String) 2 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 2 Neg failed", !checkCreateNumber(val));
        val = "--2.3";
        assertTrue("isNumber(String) 3 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 3 Neg failed", !checkCreateNumber(val));
        val = ".12.3";
        assertTrue("isNumber(String) 4 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 4 Neg failed", !checkCreateNumber(val));
        val = "-123E";
        assertTrue("isNumber(String) 5 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 5 Neg failed", !checkCreateNumber(val));
        val = "-123E+-212";
        assertTrue("isNumber(String) 6 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 6 Neg failed", !checkCreateNumber(val));
        val = "-123E2.12";
        assertTrue("isNumber(String) 7 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 7 Neg failed", !checkCreateNumber(val));
        val = "0xGF";
        assertTrue("isNumber(String) 8 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 8 Neg failed", !checkCreateNumber(val));
        val = "0xFAE-1";
        assertTrue("isNumber(String) 9 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 9 Neg failed", !checkCreateNumber(val));
        val = ".";
        assertTrue("isNumber(String) 10 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 10 Neg failed", !checkCreateNumber(val));
        val = "-0ABC123";
        assertTrue("isNumber(String) 11 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 11 Neg failed", !checkCreateNumber(val));
        val = "123.4E-D";
        assertTrue("isNumber(String) 12 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 12 Neg failed", !checkCreateNumber(val));
        val = "123.4ED";
        assertTrue("isNumber(String) 13 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 13 Neg failed", !checkCreateNumber(val));
        val = "1234E5l";
        assertTrue("isNumber(String) 14 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 14 Neg failed", !checkCreateNumber(val));
        val = "11a";
        assertTrue("isNumber(String) 15 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 15 Neg failed", !checkCreateNumber(val));
        val = "1a";
        assertTrue("isNumber(String) 16 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 16 Neg failed", !checkCreateNumber(val));
        val = "a";
        assertTrue("isNumber(String) 17 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 17 Neg failed", !checkCreateNumber(val));
        val = "11g";
        assertTrue("isNumber(String) 18 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 18 Neg failed", !checkCreateNumber(val));
        val = "11z";
        assertTrue("isNumber(String) 19 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 19 Neg failed", !checkCreateNumber(val));
        val = "11def";
        assertTrue("isNumber(String) 20 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 20 Neg failed", !checkCreateNumber(val));
        val = "11d11";
        assertTrue("isNumber(String) 21 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 21 Neg failed", !checkCreateNumber(val));
        val = "11 11";
        assertTrue("isNumber(String) 22 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 22 Neg failed", !checkCreateNumber(val));
        val = " 1111";
        assertTrue("isNumber(String) 23 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 23 Neg failed", !checkCreateNumber(val));
        val = "1111 ";
        assertTrue("isNumber(String) 24 Neg failed", !NumberUtils.isNumber(val));
        assertTrue("isNumber(String)/createNumber(String) 24 Neg failed", !checkCreateNumber(val));

        
        val = "2.";
        assertTrue("isNumber(String) LANG-521 failed", NumberUtils.isNumber(val));

        
        val = "1.1L";
        assertFalse("isNumber(String) LANG-664 failed", NumberUtils.isNumber(val));
    }

    private boolean checkCreateNumber(final String val) {
        try {
            final Object obj = NumberUtils.createNumber(val);
            if (obj == null) {
                return false;
            }
            return true;
        } catch (final NumberFormatException e) {
            return false;
       }
    }

    @SuppressWarnings("cast") 
    @Test
    public void testConstants() {
        assertTrue(NumberUtils.LONG_ZERO instanceof Long);
        assertTrue(NumberUtils.LONG_ONE instanceof Long);
        assertTrue(NumberUtils.LONG_MINUS_ONE instanceof Long);
        assertTrue(NumberUtils.INTEGER_ZERO instanceof Integer);
        assertTrue(NumberUtils.INTEGER_ONE instanceof Integer);
        assertTrue(NumberUtils.INTEGER_MINUS_ONE instanceof Integer);
        assertTrue(NumberUtils.SHORT_ZERO instanceof Short);
        assertTrue(NumberUtils.SHORT_ONE instanceof Short);
        assertTrue(NumberUtils.SHORT_MINUS_ONE instanceof Short);
        assertTrue(NumberUtils.BYTE_ZERO instanceof Byte);
        assertTrue(NumberUtils.BYTE_ONE instanceof Byte);
        assertTrue(NumberUtils.BYTE_MINUS_ONE instanceof Byte);
        assertTrue(NumberUtils.DOUBLE_ZERO instanceof Double);
        assertTrue(NumberUtils.DOUBLE_ONE instanceof Double);
        assertTrue(NumberUtils.DOUBLE_MINUS_ONE instanceof Double);
        assertTrue(NumberUtils.FLOAT_ZERO instanceof Float);
        assertTrue(NumberUtils.FLOAT_ONE instanceof Float);
        assertTrue(NumberUtils.FLOAT_MINUS_ONE instanceof Float);

        assertTrue(NumberUtils.LONG_ZERO.longValue() == 0);
        assertTrue(NumberUtils.LONG_ONE.longValue() == 1);
        assertTrue(NumberUtils.LONG_MINUS_ONE.longValue() == -1);
        assertTrue(NumberUtils.INTEGER_ZERO.intValue() == 0);
        assertTrue(NumberUtils.INTEGER_ONE.intValue() == 1);
        assertTrue(NumberUtils.INTEGER_MINUS_ONE.intValue() == -1);
        assertTrue(NumberUtils.SHORT_ZERO.shortValue() == 0);
        assertTrue(NumberUtils.SHORT_ONE.shortValue() == 1);
        assertTrue(NumberUtils.SHORT_MINUS_ONE.shortValue() == -1);
        assertTrue(NumberUtils.BYTE_ZERO.byteValue() == 0);
        assertTrue(NumberUtils.BYTE_ONE.byteValue() == 1);
        assertTrue(NumberUtils.BYTE_MINUS_ONE.byteValue() == -1);
        assertTrue(NumberUtils.DOUBLE_ZERO.doubleValue() == 0.0d);
        assertTrue(NumberUtils.DOUBLE_ONE.doubleValue() == 1.0d);
        assertTrue(NumberUtils.DOUBLE_MINUS_ONE.doubleValue() == -1.0d);
        assertTrue(NumberUtils.FLOAT_ZERO.floatValue() == 0.0f);
        assertTrue(NumberUtils.FLOAT_ONE.floatValue() == 1.0f);
        assertTrue(NumberUtils.FLOAT_MINUS_ONE.floatValue() == -1.0f);
    }

    @Test
    public void testLang300() {
        NumberUtils.createNumber("-1l");
        NumberUtils.createNumber("01l");
        NumberUtils.createNumber("1l");
    }

    @Test
    public void testLang381() {
        assertTrue(Double.isNaN(NumberUtils.min(1.2, 2.5, Double.NaN)));
        assertTrue(Double.isNaN(NumberUtils.max(1.2, 2.5, Double.NaN)));
        assertTrue(Float.isNaN(NumberUtils.min(1.2f, 2.5f, Float.NaN)));
        assertTrue(Float.isNaN(NumberUtils.max(1.2f, 2.5f, Float.NaN)));

        final double[] a = new double[] { 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertTrue(Double.isNaN(NumberUtils.max(a)));
        assertTrue(Double.isNaN(NumberUtils.min(a)));

        final double[] b = new double[] { Double.NaN, 1.2, Double.NaN, 3.7, 27.0, 42.0, Double.NaN };
        assertTrue(Double.isNaN(NumberUtils.max(b)));
        assertTrue(Double.isNaN(NumberUtils.min(b)));

        final float[] aF = new float[] { 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertTrue(Float.isNaN(NumberUtils.max(aF)));

        final float[] bF = new float[] { Float.NaN, 1.2f, Float.NaN, 3.7f, 27.0f, 42.0f, Float.NaN };
        assertTrue(Float.isNaN(NumberUtils.max(bF)));
    }

}

29218
