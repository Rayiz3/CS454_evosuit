/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 05:37:03 GMT 2023
 */

package org.apache.commons.compress.archivers.zip;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.compress.archivers.zip.X7875_NewUnix;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class X7875_NewUnix_ESTest extends X7875_NewUnix_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      long long0 = x7875_NewUnix0.getGID();
      assertEquals(1000L, long0);
      
      FileSystemHandling.shouldAllThrowIOExceptions();
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.getHeaderId();
      x7875_NewUnix0.getLocalFileDataLength();
      byte[] byteArray0 = new byte[8];
      FileSystemHandling.shouldThrowIOException((EvoSuiteFile) null);
      byteArray0[3] = (byte)2;
      byteArray0[2] = (byte)2;
      byteArray0[3] = (byte)2;
      x7875_NewUnix0.getUID();
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, (byte)0, 1);
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getLocalFileDataLength();
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix1.clone();
      long long1 = x7875_NewUnix0.getGID();
      assertEquals(2L, long1);
      
      x7875_NewUnix0.getCentralDirectoryLength();
      X7875_NewUnix x7875_NewUnix2 = new X7875_NewUnix();
      String string0 = x7875_NewUnix2.toString();
      assertEquals("0x7875 Zip Extra Field: UID=1000 GID=1000", string0);
      
      x7875_NewUnix2.setUID(0L);
      X7875_NewUnix x7875_NewUnix3 = new X7875_NewUnix();
      X7875_NewUnix x7875_NewUnix4 = new X7875_NewUnix();
      boolean boolean0 = x7875_NewUnix1.equals("0x7875 Zip Extra Field: UID=1000 GID=1000");
      assertFalse(boolean0);
      
      ZipShort zipShort0 = x7875_NewUnix2.getCentralDirectoryLength();
      assertEquals(6, zipShort0.getValue());
      
      Object object0 = new Object();
      x7875_NewUnix4.getCentralDirectoryLength();
      x7875_NewUnix4.parseFromLocalFileData(byteArray0, (byte)2, 176);
      x7875_NewUnix4.parseFromLocalFileData(byteArray0, 1, (-2856));
      byte[] byteArray1 = X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      assertEquals(6, byteArray1.length);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      byte[] byteArray0 = x7875_NewUnix0.getLocalFileDataData();
      assertArrayEquals(new byte[] {(byte)1, (byte)2, (byte) (-24), (byte)3, (byte)2, (byte) (-24), (byte)3}, byteArray0);
      
      x7875_NewUnix0.equals(x7875_NewUnix0);
      boolean boolean0 = x7875_NewUnix0.equals(x7875_NewUnix0);
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.getHeaderId();
      x7875_NewUnix0.getLocalFileDataLength();
      byte[] byteArray1 = new byte[8];
      byteArray1[2] = (byte)124;
      byteArray1[3] = (byte)2;
      byteArray1[5] = (byte)2;
      byteArray1[3] = (byte)2;
      x7875_NewUnix0.getUID();
      x7875_NewUnix0.parseFromLocalFileData(byteArray1, (byte)2, 1);
      x7875_NewUnix0.getLocalFileDataLength();
      X7875_NewUnix x7875_NewUnix1 = (X7875_NewUnix)x7875_NewUnix0.clone();
      x7875_NewUnix1.getGID();
      x7875_NewUnix1.getCentralDirectoryLength();
      assertTrue(x7875_NewUnix1.equals((Object)x7875_NewUnix0));
      
      x7875_NewUnix1.setUID(10L);
      boolean boolean1 = x7875_NewUnix0.equals(x7875_NewUnix1);
      assertFalse(boolean1);
      
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix1.getUID();
      byte[] byteArray2 = new byte[1];
      byteArray2[0] = (byte)124;
      Object object0 = new Object();
      x7875_NewUnix1.equals(object0);
      X7875_NewUnix x7875_NewUnix2 = new X7875_NewUnix();
      boolean boolean2 = x7875_NewUnix1.equals(x7875_NewUnix2);
      assertFalse(boolean2 == boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix1.equals(x7875_NewUnix0);
      boolean boolean0 = x7875_NewUnix0.equals(x7875_NewUnix1);
      assertTrue(boolean0);
      
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix1.getCentralDirectoryLength();
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getCentralDirectoryLength();
      byte[] byteArray0 = new byte[8];
      byteArray0[0] = (byte)46;
      byteArray0[1] = (byte)23;
      byteArray0[2] = (byte)124;
      byteArray0[3] = (byte)2;
      byteArray0[4] = (byte)83;
      byteArray0[5] = (byte)2;
      byteArray0[7] = (byte)3;
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, (byte)2, 1);
      x7875_NewUnix1.equals(x7875_NewUnix0);
      x7875_NewUnix1.getCentralDirectoryLength();
      assertFalse(x7875_NewUnix1.equals((Object)x7875_NewUnix0));
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      byte[] byteArray0 = new byte[4];
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.getUID();
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, 1, 1);
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.clone();
      x7875_NewUnix0.getGID();
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.toString();
      x7875_NewUnix0.setUID(1);
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix0.equals(x7875_NewUnix1);
      x7875_NewUnix0.getCentralDirectoryLength();
      Object object0 = new Object();
      x7875_NewUnix1.equals(object0);
      X7875_NewUnix x7875_NewUnix2 = new X7875_NewUnix();
      x7875_NewUnix2.parseFromLocalFileData(byteArray0, 1, 3);
      // Undeclared exception!
      try { 
        x7875_NewUnix2.parseFromLocalFileData(byteArray0, (-2155), 1);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // -2155
         //
         verifyException("org.apache.commons.compress.archivers.zip.X7875_NewUnix", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      byte[] byteArray0 = X7875_NewUnix.trimLeadingZeroesForceMinLength((byte[]) null);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setGID(425L);
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix0.equals(x7875_NewUnix1);
      x7875_NewUnix0.getCentralDirectoryLength();
      X7875_NewUnix x7875_NewUnix2 = (X7875_NewUnix)x7875_NewUnix0.clone();
      x7875_NewUnix2.setUID(425L);
      x7875_NewUnix0.equals(x7875_NewUnix2);
      x7875_NewUnix0.getCentralDirectoryLength();
      byte[] byteArray0 = new byte[0];
      // Undeclared exception!
      try { 
        x7875_NewUnix1.parseFromLocalFileData(byteArray0, 60012, (-1749));
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 60012
         //
         verifyException("org.apache.commons.compress.archivers.zip.X7875_NewUnix", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix1.equals(x7875_NewUnix0);
      x7875_NewUnix0.equals(x7875_NewUnix1);
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getCentralDirectoryLength();
      byte[] byteArray0 = new byte[8];
      byteArray0[0] = (byte)46;
      byteArray0[1] = (byte)23;
      byteArray0[2] = (byte)124;
      byteArray0[3] = (byte)2;
      byteArray0[4] = (byte)83;
      byteArray0[5] = (byte)2;
      byteArray0[7] = (byte)3;
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, (byte)2, 1);
      assertArrayEquals(new byte[] {(byte)46, (byte)23, (byte)124, (byte)2, (byte)83, (byte)2, (byte)0, (byte)3}, byteArray0);
      
      x7875_NewUnix1.equals(x7875_NewUnix0);
      // Undeclared exception!
      try { 
        x7875_NewUnix1.parseFromLocalFileData(byteArray0, 40, 40);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 40
         //
         verifyException("org.apache.commons.compress.archivers.zip.X7875_NewUnix", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setGID(0L);
      x7875_NewUnix0.clone();
      ZipShort zipShort0 = x7875_NewUnix0.getHeaderId();
      x7875_NewUnix0.getLocalFileDataLength();
      boolean boolean0 = x7875_NewUnix0.equals(zipShort0);
      assertFalse(boolean0);
      
      x7875_NewUnix0.hashCode();
      byte[] byteArray0 = new byte[6];
      byteArray0[0] = (byte)17;
      byteArray0[3] = (byte)16;
      byteArray0[4] = (byte) (-64);
      byteArray0[5] = (byte)122;
      x7875_NewUnix0.parseFromCentralDirectoryData(byteArray0, (byte)122, (byte)122);
      x7875_NewUnix0.getHeaderId();
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, (byte)0, 992);
      x7875_NewUnix0.setUID((-1L));
      ZipShort zipShort1 = x7875_NewUnix0.getLocalFileDataLength();
      assertEquals(8, zipShort1.getValue());
      
      x7875_NewUnix0.hashCode();
      x7875_NewUnix0.setGID(16711680L);
      ZipShort zipShort2 = x7875_NewUnix0.getLocalFileDataLength();
      assertEquals(10, zipShort2.getValue());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      byte[] byteArray0 = new byte[0];
      byte[] byteArray1 = X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      assertEquals(1, byteArray1.length);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      ZipShort zipShort0 = x7875_NewUnix0.getCentralDirectoryLength();
      assertEquals(7, zipShort0.getValue());
      
      x7875_NewUnix0.getLocalFileDataData();
      byte[] byteArray0 = x7875_NewUnix0.getCentralDirectoryData();
      x7875_NewUnix0.getGID();
      x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.equals((Object) null);
      x7875_NewUnix0.setUID(1024L);
      x7875_NewUnix0.setUID(1024L);
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.toString();
      x7875_NewUnix0.clone();
      int int0 = 87;
      x7875_NewUnix0.getCentralDirectoryLength();
      // Undeclared exception!
      try { 
        x7875_NewUnix0.parseFromLocalFileData(byteArray0, 87, 9558);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 87
         //
         verifyException("org.apache.commons.compress.archivers.zip.X7875_NewUnix", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      byte[] byteArray0 = x7875_NewUnix0.getCentralDirectoryData();
      assertEquals(0, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.getGID();
      Object object0 = x7875_NewUnix0.clone();
      Object object1 = x7875_NewUnix0.clone();
      assertTrue(object1.equals((Object)x7875_NewUnix0));
      
      x7875_NewUnix0.setGID(1325L);
      x7875_NewUnix0.getGID();
      x7875_NewUnix0.getUID();
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.setUID(0L);
      x7875_NewUnix0.getGID();
      x7875_NewUnix0.equals(object0);
      x7875_NewUnix0.equals(object1);
      x7875_NewUnix0.hashCode();
      x7875_NewUnix0.equals(object0);
      x7875_NewUnix0.setUID(1325L);
      x7875_NewUnix0.setUID((-1068L));
      x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.setGID(2151L);
      x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.getCentralDirectoryLength();
      byte[] byteArray0 = x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.hashCode();
      x7875_NewUnix0.toString();
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix1.equals(object1);
      boolean boolean0 = x7875_NewUnix0.equals(x7875_NewUnix1);
      assertFalse(boolean0);
      
      X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      x7875_NewUnix0.getGID();
      x7875_NewUnix1.getUID();
      x7875_NewUnix1.equals((Object) null);
      assertFalse(x7875_NewUnix1.equals((Object)x7875_NewUnix0));
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      long long0 = x7875_NewUnix0.getUID();
      assertEquals(1000L, long0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix1.equals(x7875_NewUnix0);
      x7875_NewUnix0.equals(x7875_NewUnix1);
      x7875_NewUnix0.toString();
      assertTrue(x7875_NewUnix0.equals((Object)x7875_NewUnix1));
      
      Object object0 = new Object();
      X7875_NewUnix x7875_NewUnix2 = new X7875_NewUnix();
      x7875_NewUnix2.equals("0x7875 Zip Extra Field: UID=1000 GID=1000");
      x7875_NewUnix0.setGID(0L);
      x7875_NewUnix0.getLocalFileDataLength();
      Object object1 = x7875_NewUnix1.clone();
      boolean boolean0 = x7875_NewUnix0.equals(object1);
      assertFalse(boolean0);
      
      x7875_NewUnix1.getCentralDirectoryLength();
      ZipShort zipShort0 = x7875_NewUnix2.getCentralDirectoryLength();
      x7875_NewUnix0.getGID();
      x7875_NewUnix1.clone();
      x7875_NewUnix1.equals(zipShort0);
      x7875_NewUnix1.setUID(0L);
      x7875_NewUnix1.getUID();
      Object object2 = new Object();
      x7875_NewUnix2.equals(object2);
      x7875_NewUnix1.getHeaderId();
      assertFalse(x7875_NewUnix1.equals((Object)x7875_NewUnix2));
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setUID(44L);
      Object object0 = x7875_NewUnix0.clone();
      boolean boolean0 = x7875_NewUnix0.equals(object0);
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      boolean boolean1 = x7875_NewUnix0.equals(x7875_NewUnix1);
      assertFalse(boolean1 == boolean0);
      assertFalse(boolean1);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.hashCode();
      ZipShort zipShort0 = x7875_NewUnix0.getCentralDirectoryLength();
      assertEquals(7, zipShort0.getValue());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      byte[] byteArray0 = new byte[1];
      byte[] byteArray1 = X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      assertNotSame(byteArray1, byteArray0);
      assertEquals(1, byteArray1.length);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setUID((-43L));
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      ZipShort zipShort0 = x7875_NewUnix0.getHeaderId();
      assertEquals(30837, zipShort0.getValue());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      x7875_NewUnix1.equals(x7875_NewUnix0);
      x7875_NewUnix0.equals(x7875_NewUnix1);
      Object object0 = x7875_NewUnix0.clone();
      x7875_NewUnix0.getLocalFileDataLength();
      byte[] byteArray0 = new byte[0];
      X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      boolean boolean0 = x7875_NewUnix1.equals(object0);
      assertTrue(boolean0);
      
      object0.hashCode();
      x7875_NewUnix1.parseFromCentralDirectoryData(byteArray0, (byte) (-54), (byte) (-54));
      x7875_NewUnix0.setUID(738L);
      x7875_NewUnix0.getCentralDirectoryLength();
      assertFalse(x7875_NewUnix0.equals((Object)x7875_NewUnix1));
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      boolean boolean0 = x7875_NewUnix0.equals(x7875_NewUnix0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.hashCode();
      x7875_NewUnix0.toString();
      x7875_NewUnix0.getGID();
      x7875_NewUnix0.getHeaderId();
      x7875_NewUnix0.getGID();
      Object object0 = x7875_NewUnix0.clone();
      x7875_NewUnix0.toString();
      byte[] byteArray0 = new byte[6];
      x7875_NewUnix0.hashCode();
      assertTrue(x7875_NewUnix0.equals((Object)object0));
      
      byteArray0[0] = (byte)70;
      x7875_NewUnix0.setUID((-334L));
      byteArray0[1] = (byte)3;
      x7875_NewUnix0.setGID((byte)3);
      byteArray0[2] = (byte)0;
      x7875_NewUnix0.setGID((byte)70);
      byteArray0[3] = (byte) (-99);
      byteArray0[4] = (byte)0;
      byteArray0[5] = (byte) (-23);
      x7875_NewUnix0.clone();
      x7875_NewUnix0.parseFromCentralDirectoryData(byteArray0, 1451, (byte)0);
      boolean boolean0 = x7875_NewUnix0.equals(object0);
      assertFalse(boolean0);
      
      x7875_NewUnix0.clone();
      x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.getLocalFileDataData();
      ZipShort zipShort0 = x7875_NewUnix0.getCentralDirectoryLength();
      assertEquals(8, zipShort0.getValue());
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      byte[] byteArray0 = new byte[14];
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      // Undeclared exception!
      try { 
        x7875_NewUnix0.parseFromLocalFileData(byteArray0, (-1234567), (-1234567));
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // -1234567
         //
         verifyException("org.apache.commons.compress.archivers.zip.X7875_NewUnix", e);
      }
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      ZipShort zipShort0 = x7875_NewUnix0.getCentralDirectoryLength();
      boolean boolean0 = x7875_NewUnix0.equals(zipShort0);
      assertFalse(boolean0);
      assertEquals(7, zipShort0.getValue());
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setGID(425L);
      ZipShort zipShort0 = x7875_NewUnix0.getCentralDirectoryLength();
      assertEquals(7, zipShort0.getValue());
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.getCentralDirectoryLength();
      Object object0 = x7875_NewUnix0.clone();
      assertNotSame(x7875_NewUnix0, object0);
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      String string0 = x7875_NewUnix0.toString();
      assertEquals("0x7875 Zip Extra Field: UID=1000 GID=1000", string0);
      
      ZipShort zipShort0 = x7875_NewUnix0.getCentralDirectoryLength();
      boolean boolean0 = x7875_NewUnix0.equals(zipShort0);
      assertEquals(7, zipShort0.getValue());
      assertFalse(boolean0);
  }
}
