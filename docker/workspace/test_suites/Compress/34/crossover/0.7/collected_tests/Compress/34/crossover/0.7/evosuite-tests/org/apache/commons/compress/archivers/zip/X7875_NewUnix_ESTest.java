/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 05:27:45 GMT 2023
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
      byte[] byteArray0 = x7875_NewUnix0.getCentralDirectoryData();
      assertEquals(0, byteArray0.length);
      
      byte[] byteArray1 = new byte[9];
      byteArray1[3] = (byte)1;
      X7875_NewUnix x7875_NewUnix1 = (X7875_NewUnix)x7875_NewUnix0.clone();
      x7875_NewUnix0.toString();
      x7875_NewUnix1.getUID();
      x7875_NewUnix0.getGID();
      x7875_NewUnix0.parseFromLocalFileData(byteArray1, 3, (byte)0);
      x7875_NewUnix0.setUID((-1L));
      x7875_NewUnix0.parseFromLocalFileData(byteArray1, (byte)1, (byte) (-32));
      x7875_NewUnix1.toString();
      X7875_NewUnix x7875_NewUnix2 = (X7875_NewUnix)x7875_NewUnix1.clone();
      x7875_NewUnix1.hashCode();
      X7875_NewUnix x7875_NewUnix3 = new X7875_NewUnix();
      x7875_NewUnix3.getLocalFileDataData();
      x7875_NewUnix3.setGID((-1L));
      x7875_NewUnix3.equals(x7875_NewUnix2);
      x7875_NewUnix1.getGID();
      X7875_NewUnix x7875_NewUnix4 = (X7875_NewUnix)x7875_NewUnix3.clone();
      x7875_NewUnix0.getGID();
      x7875_NewUnix4.toString();
      x7875_NewUnix1.getUID();
      x7875_NewUnix2.getCentralDirectoryData();
      x7875_NewUnix0.getUID();
      FileSystemHandling fileSystemHandling0 = new FileSystemHandling();
      x7875_NewUnix2.getLocalFileDataData();
      x7875_NewUnix3.getCentralDirectoryData();
      x7875_NewUnix4.getCentralDirectoryLength();
      x7875_NewUnix2.getHeaderId();
      x7875_NewUnix1.getUID();
      x7875_NewUnix3.getGID();
      assertFalse(x7875_NewUnix3.equals((Object)x7875_NewUnix1));
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      ZipShort zipShort0 = x7875_NewUnix0.getLocalFileDataLength();
      assertEquals(7, zipShort0.getValue());
      
      x7875_NewUnix0.hashCode();
      FileSystemHandling.appendStringToFile((EvoSuiteFile) null, "iXD/|yUY.2\"HC");
      byte[] byteArray0 = new byte[5];
      byteArray0[0] = (byte)117;
      byteArray0[1] = (byte)0;
      byteArray0[2] = (byte)0;
      byteArray0[3] = (byte)0;
      byteArray0[4] = (byte)0;
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, (byte)0, (-2527));
      x7875_NewUnix0.hashCode();
      byte[] byteArray1 = x7875_NewUnix0.getLocalFileDataData();
      assertArrayEquals(new byte[] {(byte)117, (byte)1, (byte)0, (byte)1, (byte)0}, byteArray1);
      
      ZipShort zipShort1 = x7875_NewUnix0.getCentralDirectoryLength();
      assertEquals(5, zipShort1.getValue());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.getUID();
      X7875_NewUnix x7875_NewUnix1 = (X7875_NewUnix)x7875_NewUnix0.clone();
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getUID();
      X7875_NewUnix x7875_NewUnix2 = (X7875_NewUnix)x7875_NewUnix0.clone();
      x7875_NewUnix2.getGID();
      byte[] byteArray0 = new byte[6];
      x7875_NewUnix1.getLocalFileDataLength();
      byteArray0[1] = (byte)0;
      x7875_NewUnix1.hashCode();
      assertTrue(x7875_NewUnix1.equals((Object)x7875_NewUnix0));
      assertTrue(x7875_NewUnix0.equals((Object)x7875_NewUnix1));
      
      byteArray0[2] = (byte)0;
      byteArray0[3] = (byte) (-18);
      byteArray0[4] = (byte)0;
      byteArray0[5] = (byte)117;
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, (byte)0, (byte)0);
      x7875_NewUnix0.hashCode();
      x7875_NewUnix1.getLocalFileDataData();
      x7875_NewUnix1.equals(x7875_NewUnix0);
      assertTrue(x7875_NewUnix1.equals((Object)x7875_NewUnix2));
      
      x7875_NewUnix0.equals(x7875_NewUnix2);
      assertTrue(x7875_NewUnix2.equals((Object)x7875_NewUnix1));
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.getUID();
      X7875_NewUnix x7875_NewUnix1 = (X7875_NewUnix)x7875_NewUnix0.clone();
      x7875_NewUnix0.getLocalFileDataLength();
      x7875_NewUnix0.getUID();
      X7875_NewUnix x7875_NewUnix2 = (X7875_NewUnix)x7875_NewUnix0.clone();
      byte[] byteArray0 = new byte[6];
      x7875_NewUnix1.getLocalFileDataLength();
      byteArray0[1] = (byte)0;
      x7875_NewUnix1.hashCode();
      assertTrue(x7875_NewUnix1.equals((Object)x7875_NewUnix0));
      
      byteArray0[2] = (byte)0;
      byteArray0[3] = (byte) (-18);
      byteArray0[4] = (byte)0;
      byteArray0[5] = (byte)117;
      x7875_NewUnix0.parseFromLocalFileData(byteArray0, (byte)0, (byte)0);
      x7875_NewUnix0.hashCode();
      x7875_NewUnix1.getLocalFileDataData();
      x7875_NewUnix1.equals(x7875_NewUnix0);
      assertTrue(x7875_NewUnix1.equals((Object)x7875_NewUnix2));
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      byte[] byteArray0 = x7875_NewUnix0.getCentralDirectoryData();
      X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      byte[] byteArray1 = new byte[8];
      byteArray1[0] = (byte) (-32);
      byteArray1[1] = (byte)121;
      byteArray1[2] = (byte)101;
      byteArray1[3] = (byte) (-111);
      byteArray1[4] = (byte)1;
      byteArray1[5] = (byte)125;
      byteArray1[6] = (byte)0;
      byteArray1[7] = (byte)98;
      x7875_NewUnix0.parseFromLocalFileData(byteArray1, 3, (-1343));
      assertArrayEquals(new byte[] {(byte) (-32), (byte)121, (byte)101, (byte) (-111), (byte)1, (byte)125, (byte)0, (byte)98}, byteArray1);
      
      byte[] byteArray2 = X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      assertEquals(1, byteArray2.length);
      assertEquals(0, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      byte[] byteArray0 = X7875_NewUnix.trimLeadingZeroesForceMinLength((byte[]) null);
      assertNull(byteArray0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      FileSystemHandling fileSystemHandling0 = new FileSystemHandling();
      x7875_NewUnix0.setUID(0L);
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      boolean boolean0 = x7875_NewUnix0.equals(x7875_NewUnix1);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setGID(0L);
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      boolean boolean0 = x7875_NewUnix0.equals(x7875_NewUnix1);
      assertFalse(boolean0);
      
      byte[] byteArray0 = x7875_NewUnix0.getLocalFileDataData();
      assertArrayEquals(new byte[] {(byte)1, (byte)2, (byte) (-24), (byte)3, (byte)1, (byte)0}, byteArray0);
      
      x7875_NewUnix0.setUID(1L);
      X7875_NewUnix x7875_NewUnix2 = new X7875_NewUnix();
      x7875_NewUnix2.getGID();
      assertTrue(x7875_NewUnix2.equals((Object)x7875_NewUnix1));
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      ZipShort zipShort0 = x7875_NewUnix0.getHeaderId();
      assertEquals(30837, zipShort0.getValue());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      Object object0 = new Object();
      FileSystemHandling fileSystemHandling0 = new FileSystemHandling();
      FileSystemHandling.createFolder((EvoSuiteFile) null);
      x7875_NewUnix0.equals(object0);
      FileSystemHandling.createFolder((EvoSuiteFile) null);
      x7875_NewUnix0.setGID(0L);
      byte[] byteArray0 = new byte[1];
      x7875_NewUnix0.setUID(1L);
      ZipShort zipShort0 = x7875_NewUnix0.getLocalFileDataLength();
      byteArray0[0] = (byte)87;
      x7875_NewUnix0.parseFromCentralDirectoryData(byteArray0, (byte)87, 2516);
      x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.getCentralDirectoryLength();
      x7875_NewUnix0.getLocalFileDataData();
      x7875_NewUnix0.getUID();
      x7875_NewUnix0.getUID();
      Object object1 = x7875_NewUnix0.clone();
      x7875_NewUnix0.equals(object1);
      assertTrue(x7875_NewUnix0.equals((Object)object1));
      
      x7875_NewUnix0.setUID(1000L);
      FileSystemHandling.shouldThrowIOException((EvoSuiteFile) null);
      x7875_NewUnix0.setGID(1000L);
      ZipShort zipShort1 = x7875_NewUnix0.getCentralDirectoryLength();
      assertFalse(zipShort1.equals((Object)zipShort0));
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      boolean boolean0 = x7875_NewUnix0.equals((Object) null);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      boolean boolean0 = x7875_NewUnix0.equals(x7875_NewUnix0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setUID((-101L));
      x7875_NewUnix0.toString();
      x7875_NewUnix0.getLocalFileDataLength();
      byte[] byteArray0 = new byte[5];
      byteArray0[0] = (byte)0;
      byteArray0[1] = (byte) (-80);
      byteArray0[2] = (byte)16;
      byteArray0[3] = (byte) (-17);
      byteArray0[4] = (byte)4;
      x7875_NewUnix0.parseFromCentralDirectoryData(byteArray0, (byte) (-80), (byte)0);
      assertEquals(5, byteArray0.length);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      long long0 = x7875_NewUnix0.getGID();
      assertEquals(1000L, long0);
      
      byte[] byteArray0 = new byte[3];
      byteArray0[0] = (byte)0;
      byteArray0[1] = (byte) (-99);
      byteArray0[2] = (byte)1;
      x7875_NewUnix0.parseFromCentralDirectoryData(byteArray0, (byte) (-99), 49);
      assertArrayEquals(new byte[] {(byte)0, (byte) (-99), (byte)1}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      byte[] byteArray0 = new byte[1];
      byte[] byteArray1 = X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray0);
      assertEquals(1, byteArray1.length);
      assertNotSame(byteArray1, byteArray0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      FileSystemHandling.setPermissions((EvoSuiteFile) null, false, false, true);
      byte[] byteArray0 = new byte[1];
      byteArray0[0] = (byte)10;
      FileSystemHandling.appendDataToFile((EvoSuiteFile) null, byteArray0);
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.clone();
      x7875_NewUnix0.hashCode();
      x7875_NewUnix0.setGID(0L);
      x7875_NewUnix0.toString();
      x7875_NewUnix0.toString();
      x7875_NewUnix0.getCentralDirectoryLength();
      byte[] byteArray1 = x7875_NewUnix0.getCentralDirectoryData();
      X7875_NewUnix.trimLeadingZeroesForceMinLength(byteArray1);
      x7875_NewUnix0.getGID();
      // Undeclared exception!
      try { 
        x7875_NewUnix0.parseFromLocalFileData(byteArray1, 3, (byte)10);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 3
         //
         verifyException("org.apache.commons.compress.archivers.zip.X7875_NewUnix", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.getUID();
      x7875_NewUnix0.hashCode();
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      String string0 = x7875_NewUnix0.toString();
      assertEquals("0x7875 Zip Extra Field: UID=1000 GID=1000", string0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.setUID(0L);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      byte[] byteArray0 = new byte[2];
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      // Undeclared exception!
      try { 
        x7875_NewUnix0.parseFromLocalFileData(byteArray0, 3, 3);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 3
         //
         verifyException("org.apache.commons.compress.archivers.zip.X7875_NewUnix", e);
      }
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      X7875_NewUnix x7875_NewUnix1 = new X7875_NewUnix();
      byte[] byteArray0 = x7875_NewUnix1.getLocalFileDataData();
      assertArrayEquals(new byte[] {(byte)1, (byte)2, (byte) (-24), (byte)3, (byte)2, (byte) (-24), (byte)3}, byteArray0);
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      Object object0 = x7875_NewUnix0.clone();
      assertNotSame(object0, x7875_NewUnix0);
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      ZipShort zipShort0 = x7875_NewUnix0.getCentralDirectoryLength();
      assertEquals(7, zipShort0.getValue());
      
      x7875_NewUnix0.setGID(0L);
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      X7875_NewUnix x7875_NewUnix0 = new X7875_NewUnix();
      x7875_NewUnix0.hashCode();
  }
}
