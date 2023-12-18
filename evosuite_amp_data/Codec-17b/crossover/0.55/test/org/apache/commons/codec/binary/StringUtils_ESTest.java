/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 15 23:20:20 GMT 2023
 */

package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.nio.CharBuffer;
import org.apache.commons.codec.binary.StringUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class StringUtils_ESTest extends StringUtils_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      StringUtils.newStringUsAscii((byte[]) null);
      StringUtils.getBytesUtf16Le((String) null);
      byte[] byteArray0 = StringUtils.getBytesUtf8("Auto");
      StringUtils stringUtils0 = new StringUtils();
      StringUtils.getBytesUtf8((String) null);
      char[] charArray0 = new char[8];
      charArray0[0] = 'o';
      charArray0[1] = '3';
      charArray0[2] = 'a';
      charArray0[3] = '.';
      charArray0[4] = 'R';
      charArray0[5] = 'Z';
      charArray0[6] = '4';
      charArray0[7] = '#';
      CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
      StringUtils.equals((CharSequence) "|I", (CharSequence) charBuffer0);
      StringUtils.newString((byte[]) null, "Auto");
      byte[] byteArray1 = StringUtils.getBytesIso8859_1("|I");
      StringUtils.newString((byte[]) null, "|I");
      StringUtils.newStringUsAscii(byteArray1);
      StringUtils.getByteBufferUtf8("org.apache.commons.codec.binary.StringUtils");
      StringUtils.getBytesUtf16("\"c9");
      StringUtils.newStringUtf16((byte[]) null);
      StringUtils.newStringUtf16Le(byteArray0);
      byte[] byteArray2 = StringUtils.getBytesUtf16("org.apache.commons.codec.binary.StringUtils");
      assertNotSame(byteArray2, byteArray0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      byte[] byteArray0 = new byte[2];
      byteArray0[0] = (byte) (-6);
      byteArray0[1] = (byte) (-1);
      StringUtils.newStringUsAscii(byteArray0);
      StringUtils stringUtils0 = new StringUtils();
      byte[] byteArray1 = new byte[5];
      byteArray1[1] = (byte) (-1);
      byteArray1[2] = (byte) (-6);
      byteArray1[3] = (byte) (-6);
      byteArray1[4] = (byte) (-6);
      StringUtils.newStringUtf16(byteArray1);
      StringUtils.getBytesUtf16Be(">n]IOj)=");
      boolean boolean0 = StringUtils.equals((CharSequence) ">n]IOj)=", (CharSequence) "\uFFFD\uFFFD");
      assertFalse(boolean0);
      
      char[] charArray0 = new char[4];
      charArray0[0] = ']';
      charArray0[1] = '[';
      charArray0[2] = '4';
      charArray0[3] = 's';
      CharBuffer charBuffer0 = CharBuffer.wrap(charArray0);
      boolean boolean1 = StringUtils.equals((CharSequence) charBuffer0, (CharSequence) ">n]IOj)=");
      assertFalse(boolean1);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      StringUtils.getByteBufferUtf8("");
      StringUtils.getBytesUtf16Be((String) null);
      StringUtils.equals((CharSequence) "", (CharSequence) null);
      byte[] byteArray0 = StringUtils.getBytesUtf16("");
      assertArrayEquals(new byte[] {}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      StringUtils.newStringUtf16Be((byte[]) null);
      StringUtils.getByteBufferUtf8((String) null);
      String string0 = "org.apache.commons.codec.binary.BaseNCodec$Context";
      byte[] byteArray0 = StringUtils.getBytesUsAscii("org.apache.commons.codec.binary.BaseNCodec$Context");
      byte[] byteArray1 = StringUtils.getBytesUtf8("org.apache.commons.codec.binary.BaseNCodec$Context");
      String string1 = "H`QAIW]|`q}=jk0";
      StringUtils.getBytesUtf16("H`QAIW]|`q}=jk0");
      String string2 = "";
      StringUtils.getBytesUtf16("");
      StringUtils.newStringIso8859_1(byteArray1);
      // Undeclared exception!
      try { 
        StringUtils.newString(byteArray0, "");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // : java.io.UnsupportedEncodingException: 
         //
         verifyException("org.apache.commons.codec.binary.StringUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      StringUtils stringUtils0 = new StringUtils();
      byte[] byteArray0 = StringUtils.getBytesUtf16Be("");
      assertEquals(0, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      StringUtils.getByteBufferUtf8("");
      StringUtils.getBytesUtf16Be((String) null);
      StringUtils.newStringUtf16Le((byte[]) null);
      StringUtils.newStringUtf16Be((byte[]) null);
      StringUtils.equals((CharSequence) null, (CharSequence) null);
      StringUtils.getBytesUtf16((String) null);
      StringUtils.getBytesUtf16("");
      StringUtils.newStringUtf16Le((byte[]) null);
      StringUtils.getBytesUtf16((String) null);
      StringUtils.newStringUtf16Be((byte[]) null);
      StringUtils.getBytesUnchecked((String) null, (String) null);
      StringUtils.newString((byte[]) null, "");
      String string0 = StringUtils.newString((byte[]) null, (String) null);
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      StringUtils.newStringUsAscii((byte[]) null);
      StringUtils.newString((byte[]) null, (String) null);
      // Undeclared exception!
      try { 
        CharBuffer.wrap((CharSequence) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.nio.CharBuffer", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      byte[] byteArray0 = new byte[3];
      byteArray0[2] = (byte)1;
      StringUtils.getBytesUtf16Be("");
      boolean boolean0 = StringUtils.equals((CharSequence) null, (CharSequence) "");
      assertFalse(boolean0);
      
      StringUtils.getBytesUtf16((String) null);
      StringUtils.newStringUsAscii((byte[]) null);
      boolean boolean1 = StringUtils.equals((CharSequence) null, (CharSequence) null);
      assertTrue(boolean1);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      byte[] byteArray0 = StringUtils.getBytesUtf16Le("94ed8d0319e4493399560fb67404d370");
      byte[] byteArray1 = StringUtils.getBytesUtf8("94ed8d0319e4493399560fb67404d370");
      StringUtils.newStringUtf16Le(byteArray0);
      String string0 = StringUtils.newStringUtf16Be(byteArray1);
      assertNotNull(string0);
      
      boolean boolean0 = StringUtils.equals((CharSequence) "\u3934\u6564\u3864\u3033\u3139\u6534\u3439\u3333\u3939\u3536\u3066\u6236\u3734\u3034\u6433\u3730", (CharSequence) "94ed8d0319e4493399560fb67404d370");
      boolean boolean1 = StringUtils.equals((CharSequence) "94ed8d0319e4493399560fb67404d370", (CharSequence) "94ed8d0319e4493399560fb67404d370");
      assertFalse(boolean1 == boolean0);
      assertTrue(boolean1);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      StringUtils stringUtils0 = new StringUtils();
      byte[] byteArray0 = StringUtils.getBytesIso8859_1("mGf");
      assertArrayEquals(new byte[] {(byte)109, (byte)71, (byte)102}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      StringUtils.getBytesUtf8("|I");
      byte[] byteArray0 = StringUtils.getBytesIso8859_1("+n@W6uD#i>R9aC");
      StringUtils.getBytesUtf16("zcT");
      StringUtils.newStringUtf16(byteArray0);
      String string0 = StringUtils.newStringUtf16Le(byteArray0);
      assertEquals("\u6E2B\u5740\u7536\u2344\u7F69\u523E\u6139\uFFFD", string0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      byte[] byteArray0 = StringUtils.getBytesUtf8(": ");
      boolean boolean0 = StringUtils.equals((CharSequence) ": ", (CharSequence) ": ");
      StringUtils.newStringIso8859_1(byteArray0);
      boolean boolean1 = StringUtils.equals((CharSequence) ": ", (CharSequence) ": ");
      assertTrue(boolean1 == boolean0);
      assertTrue(boolean1);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      String string0 = null;
      StringUtils.getBytesUtf16Be((String) null);
      String string1 = "";
      StringUtils.getBytesUtf16("");
      byte[] byteArray0 = new byte[0];
      // Undeclared exception!
      try { 
        StringUtils.newString(byteArray0, "antidisestablishmentarism");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // antidisestablishmentarism: java.io.UnsupportedEncodingException: antidisestablishmentarism
         //
         verifyException("org.apache.commons.codec.binary.StringUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      byte[] byteArray0 = StringUtils.getBytesUtf16Le("");
      assertEquals(0, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      boolean boolean0 = StringUtils.equals((CharSequence) ": ", (CharSequence) ": ");
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      StringUtils.getBytesUsAscii((String) null);
      StringUtils stringUtils0 = new StringUtils();
      byte[] byteArray0 = StringUtils.getBytesUsAscii((String) null);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      byte[] byteArray0 = new byte[6];
      byteArray0[0] = (byte)90;
      byteArray0[1] = (byte)1;
      byteArray0[2] = (byte) (-111);
      String string0 = StringUtils.newStringUtf8(byteArray0);
      assertNotNull(string0);
      
      boolean boolean0 = StringUtils.equals((CharSequence) "Z\u0001\uFFFD\u0000\u0000\u0000", (CharSequence) "Z\u0001\uFFFD\u0000\u0000\u0000");
      CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "Z\u0001\uFFFD\u0000\u0000\u0000");
      boolean boolean1 = StringUtils.equals((CharSequence) "Z\u0001\uFFFD\u0000\u0000\u0000", (CharSequence) charBuffer0);
      assertTrue(boolean1 == boolean0);
      assertTrue(boolean1);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      StringUtils.getByteBufferUtf8("org.apache.commons.codec.binary.StringUtils");
      StringUtils stringUtils0 = new StringUtils();
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      byte[] byteArray0 = StringUtils.getBytesUtf16("");
      String string0 = StringUtils.newStringUsAscii(byteArray0);
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      // Undeclared exception!
      try { 
        StringUtils.getBytesUnchecked("V@}rtw?v?,", "V@}rtw?v?,");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // V@}rtw?v?,: java.io.UnsupportedEncodingException: V@}rtw?v?,
         //
         verifyException("org.apache.commons.codec.binary.StringUtils", e);
      }
  }
}
