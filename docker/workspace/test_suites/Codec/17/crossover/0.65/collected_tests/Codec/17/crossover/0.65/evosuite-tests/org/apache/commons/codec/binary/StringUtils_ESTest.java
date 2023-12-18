/*
 * This file was automatically generated by EvoSuite
 * Fri Dec 15 22:55:22 GMT 2023
 */

package org.apache.commons.codec.binary;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import org.apache.commons.codec.binary.StringUtils;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class StringUtils_ESTest extends StringUtils_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      byte[] byteArray0 = StringUtils.getBytesUtf16Le("&!MhH/");
      byte[] byteArray1 = StringUtils.getBytesIso8859_1("&!MhH/");
      StringUtils.newStringIso8859_1(byteArray0);
      StringUtils.newStringUtf16(byteArray1);
      StringUtils.equals((CharSequence) "\u2621\u4D68\u482F", (CharSequence) "&\u0000!\u0000M\u0000h\u0000H\u0000/\u0000");
      byte[] byteArray2 = StringUtils.getBytesUtf16Le("OK");
      StringUtils.newStringUtf16Be((byte[]) null);
      StringUtils.newStringUsAscii(byteArray2);
      StringUtils.getByteBufferUtf8("&!MhH/");
      byte[] byteArray3 = StringUtils.getBytesUtf8("\u2621\u4D68\u482F");
      String string0 = "";
      StringUtils.getBytesUtf16Le("&\u0000!\u0000M\u0000h\u0000H\u0000/\u0000");
      StringUtils.newStringUtf16Be(byteArray3);
      CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "O\u0000K\u0000", 1, 1);
      // Undeclared exception!
      try { 
        StringUtils.equals((CharSequence) "&!MhH/", (CharSequence) charBuffer0);
        fail("Expecting exception: IndexOutOfBoundsException");
      
      } catch(IndexOutOfBoundsException e) {
         //
         // 1
         //
         verifyException("java.nio.StringCharBuffer", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      StringUtils.getByteBufferUtf8("");
      StringUtils stringUtils0 = new StringUtils();
      byte[] byteArray0 = new byte[0];
      StringUtils.newStringUtf16Be(byteArray0);
      StringUtils.equals((CharSequence) "", (CharSequence) "");
      StringUtils.getByteBufferUtf8("");
      StringUtils.getByteBufferUtf8((String) null);
      byte[] byteArray1 = StringUtils.getBytesUsAscii("E5'EA`;(TZp");
      StringUtils.newStringUtf16Le(byteArray1);
      StringUtils.getBytesUtf16((String) null);
      StringUtils.newStringUtf8(byteArray0);
      byte[] byteArray2 = new byte[3];
      byteArray2[1] = (byte)1;
      byteArray2[2] = (byte) (-62);
      StringUtils.newStringUtf16(byteArray2);
      StringUtils.newStringUtf16(byteArray1);
      StringUtils stringUtils1 = new StringUtils();
      StringUtils.newStringIso8859_1(byteArray2);
      StringUtils.getBytesIso8859_1("");
      CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "\u0000\u0001\u00C2");
      StringUtils.newStringUtf16(byteArray1);
      boolean boolean0 = StringUtils.equals((CharSequence) charBuffer0, (CharSequence) "\u4535\u2745\u4160\u3B28\u545A\uFFFD");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      StringUtils.getByteBufferUtf8("");
      StringUtils stringUtils0 = new StringUtils();
      byte[] byteArray0 = new byte[0];
      StringUtils.getByteBufferUtf8((String) null);
      byte[] byteArray1 = StringUtils.getBytesUtf16("sX^qL?$pe:bz!");
      StringUtils.newStringUtf16(byteArray0);
      StringUtils.newStringUtf8(byteArray1);
      StringUtils.getByteBufferUtf8("");
      StringUtils.newStringUtf16Le(byteArray1);
      StringUtils.newStringIso8859_1(byteArray1);
      StringUtils.newStringUtf8(byteArray1);
      boolean boolean0 = StringUtils.equals((CharSequence) "\uFFFD\uFFFD\u0000s\u0000X\u0000^\u0000q\u0000L\u0000?\u0000$\u0000p\u0000e\u0000:\u0000b\u0000z\u0000!", (CharSequence) "");
      char[] charArray0 = new char[2];
      charArray0[0] = ',';
      charArray0[1] = 't';
      StringUtils.getBytesIso8859_1("");
      CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "\uFFFD\uFFFD\u0000s\u0000X\u0000^\u0000q\u0000L\u0000?\u0000$\u0000p\u0000e\u0000:\u0000b\u0000z\u0000!");
      StringUtils.newStringUtf16(byteArray0);
      boolean boolean1 = StringUtils.equals((CharSequence) charBuffer0, (CharSequence) "\uFFFD\uFFFD\u0000s\u0000X\u0000^\u0000q\u0000L\u0000?\u0000$\u0000p\u0000e\u0000:\u0000b\u0000z\u0000!");
      assertFalse(boolean1 == boolean0);
      assertTrue(boolean1);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      StringUtils.getBytesUtf16Le("&!MhH/");
      StringUtils.getBytesUtf16Be("&!MhH/");
      StringUtils.getBytesIso8859_1("&!MhH/");
      StringUtils.getBytesUtf8("&!MhH/");
      StringUtils.getBytesUtf16("&!MhH/");
      ByteBuffer byteBuffer0 = StringUtils.getByteBufferUtf8((String) null);
      assertNull(byteBuffer0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      boolean boolean0 = StringUtils.equals((CharSequence) "", (CharSequence) "");
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      StringUtils stringUtils0 = new StringUtils();
      StringUtils.getBytesUnchecked((String) null, "h%#.m;oaleZC");
      StringUtils.newString((byte[]) null, "h%#.m;oaleZC");
      StringUtils.newStringUtf16Le((byte[]) null);
      StringUtils.getBytesUtf16("");
      byte[] byteArray0 = StringUtils.getBytesUtf16Le("PV0h");
      assertEquals(8, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      StringUtils.getByteBufferUtf8("");
      StringUtils stringUtils0 = new StringUtils();
      byte[] byteArray0 = new byte[0];
      StringUtils.newStringUtf16Be(byteArray0);
      boolean boolean0 = StringUtils.equals((CharSequence) "", (CharSequence) "");
      assertTrue(boolean0);
      
      ByteBuffer byteBuffer0 = StringUtils.getByteBufferUtf8("");
      assertNotNull(byteBuffer0);
      
      StringUtils.getByteBufferUtf8((String) null);
      byte[] byteArray1 = StringUtils.getBytesUsAscii("E5'EA`;(TZp");
      StringUtils.newStringUtf16Le(byteArray1);
      StringUtils.getBytesUtf16((String) null);
      StringUtils.newStringUtf8(byteArray0);
      byte[] byteArray2 = new byte[3];
      byteArray2[1] = (byte)1;
      StringUtils.newStringUtf16Le(byteArray2);
      boolean boolean1 = StringUtils.equals((CharSequence) "", (CharSequence) null);
      assertFalse(boolean1);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      StringUtils.getByteBufferUtf8("w1)R,],48'p&6");
      StringUtils.getBytesUtf8("w1)R,],48'p&6");
      StringUtils.getBytesUtf16("w1)R,],48'p&6");
      ByteBuffer byteBuffer0 = StringUtils.getByteBufferUtf8("j[Lb32x0ec0'6,ES");
      assertNotNull(byteBuffer0);
      
      StringUtils.equals((CharSequence) "j[Lb32x0ec0'6,ES", (CharSequence) "w1)R,],48'p&6");
      boolean boolean0 = StringUtils.equals((CharSequence) "w1)R,],48'p&6", (CharSequence) "j[Lb32x0ec0'6,ES");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      StringUtils.getBytesIso8859_1("");
      StringUtils.getByteBufferUtf8("7");
      StringUtils.getBytesUsAscii((String) null);
      byte[] byteArray0 = StringUtils.getBytesUtf16("wait");
      StringUtils.newStringUtf16(byteArray0);
      StringUtils stringUtils0 = new StringUtils();
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      StringUtils.getByteBufferUtf8("");
      StringUtils.getByteBufferUtf8("");
      StringUtils.getBytesUtf16Le("");
      StringUtils.getByteBufferUtf8("");
      byte[] byteArray0 = StringUtils.getBytesUtf16Le("crusifed");
      StringUtils.newStringUtf8(byteArray0);
      String string0 = StringUtils.newStringUtf16(byteArray0);
      assertEquals("\u6300\u7200\u7500\u7300\u6900\u6600\u6500\u6400", string0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      byte[] byteArray0 = new byte[2];
      byteArray0[0] = (byte) (-1);
      byteArray0[1] = (byte) (-63);
      String string0 = StringUtils.newStringUsAscii(byteArray0);
      assertEquals("\uFFFD\uFFFD", string0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      StringUtils.getBytesUtf8((String) null);
      StringUtils.newStringUtf16Le((byte[]) null);
      boolean boolean0 = StringUtils.equals((CharSequence) null, (CharSequence) "j[Lb32x0ec0'6,ES");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      byte[] byteArray0 = new byte[0];
      StringUtils.newStringUtf16Be(byteArray0);
      StringUtils.getByteBufferUtf8("");
      // Undeclared exception!
      try { 
        StringUtils.getBytesUnchecked("", "yLWCg&!It3wlLOj'>");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // yLWCg&!It3wlLOj'>: java.io.UnsupportedEncodingException: yLWCg&!It3wlLOj'>
         //
         verifyException("org.apache.commons.codec.binary.StringUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      byte[] byteArray0 = StringUtils.getBytesUtf8("*36KLw[Y");
      StringUtils stringUtils0 = new StringUtils();
      StringUtils.newStringUsAscii(byteArray0);
      StringUtils.getBytesUtf16Le("*36KLw[Y");
      String string0 = StringUtils.newStringUtf16Le(byteArray0);
      assertEquals("\u332A\u4B36\u774C\u595B", string0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      StringUtils.getBytesIso8859_1("");
      StringUtils.equals((CharSequence) "", (CharSequence) "");
      byte[] byteArray0 = StringUtils.getBytesUtf16("");
      StringUtils.newStringUtf16Le(byteArray0);
      CharBuffer charBuffer0 = CharBuffer.wrap((CharSequence) "");
      assertEquals(0, charBuffer0.position());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      StringUtils.getBytesUtf16("j[Lb32x0ec0'6,ES");
      ByteBuffer byteBuffer0 = StringUtils.getByteBufferUtf8("j[Lb32x0ec0'6,ES");
      assertFalse(byteBuffer0.isDirect());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      StringUtils.getBytesUtf8((String) null);
      StringUtils.getBytesUtf16("j[Lb32x0ec0'6,ES");
      ByteBuffer byteBuffer0 = StringUtils.getByteBufferUtf8("j[Lb32x0ec0'6,ES");
      assertTrue(byteBuffer0.hasRemaining());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      byte[] byteArray0 = new byte[1];
      byteArray0[0] = (byte) (-102);
      StringUtils.newStringUtf16Le(byteArray0);
      StringUtils stringUtils0 = new StringUtils();
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
  public void test18()  throws Throwable  {
      byte[] byteArray0 = StringUtils.getBytesUtf16Be("nI-3lnQH@8dc2AA\bA_");
      assertEquals(36, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      ByteBuffer byteBuffer0 = StringUtils.getByteBufferUtf8("C>VQf.6'");
      assertEquals(8, byteBuffer0.remaining());
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      // Undeclared exception!
      try { 
        StringUtils.getBytesUnchecked("", "A]");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // A]: java.io.UnsupportedEncodingException: A]
         //
         verifyException("org.apache.commons.codec.binary.StringUtils", e);
      }
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      byte[] byteArray0 = new byte[1];
      byteArray0[0] = (byte) (-52);
      String string0 = StringUtils.newStringUtf16(byteArray0);
      String string1 = StringUtils.newStringIso8859_1(byteArray0);
      assertFalse(string1.equals((Object)string0));
  }
}
