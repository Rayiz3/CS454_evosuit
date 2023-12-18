/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 10:44:58 GMT 2023
 */

package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class CSVRecord_ESTest extends CSVRecord_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer integer0 = new Integer((-1));
      hashMap0.put((String) null, integer0);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, (-1L));
      boolean boolean0 = cSVRecord0.isSet((String) null);
      assertTrue(boolean0);
      assertEquals((-1L), cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      String[] stringArray0 = new String[1];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, (String) null, 131072L);
      boolean boolean0 = cSVRecord0.isMapped((String) null);
      assertEquals(131072L, cSVRecord0.getRecordNumber());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord((String[]) null, (Map<String, Integer>) null, "p_gMylT}eD@qZ", 2658L);
      boolean boolean0 = cSVRecord0.isConsistent();
      assertTrue(boolean0);
      assertEquals(2658L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer integer0 = new Integer(1);
      hashMap0.putIfAbsent((String) null, integer0);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, 1L);
      assertEquals("[null, null]", cSVRecord0.toString());
      
      cSVRecord0.get((String) null);
      boolean boolean0 = cSVRecord0.isSet((String) null);
      assertTrue(boolean0);
      assertEquals(1L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      String[] stringArray0 = new String[0];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>", 0L);
      Integer integer0 = new Integer(4);
      hashMap0.put("<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>", integer0);
      cSVRecord0.getComment();
      cSVRecord0.isMapped((String) null);
      boolean boolean0 = cSVRecord0.isSet("<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer integer0 = new Integer(9);
      hashMap0.put((String) null, integer0);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, 131078L);
      boolean boolean0 = cSVRecord0.isSet((String) null);
      assertFalse(boolean0);
      assertEquals(131078L, cSVRecord0.getRecordNumber());
      assertEquals(2, cSVRecord0.size());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      String[] stringArray0 = new String[1];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      hashMap0.put((String) null, (Integer) null);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, 88L);
      // Undeclared exception!
      try { 
        cSVRecord0.isSet((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Object object0 = new Object();
      String[] stringArray0 = new String[4];
      stringArray0[0] = "A";
      stringArray0[1] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[2] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[3] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, "first", (-3180L));
      cSVRecord0.size();
      // Undeclared exception!
      try { 
        cSVRecord0.get("Hy2 tpiO5_Ygs$5");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // No header mapping was specified, the record values can't be accessed by name
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      String[] stringArray0 = new String[1];
      String string0 = "W:b1U";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, "W:b1U", 311L);
      // Undeclared exception!
      try { 
        cSVRecord0.get("W:b1U");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // No header mapping was specified, the record values can't be accessed by name
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, 131078L);
      cSVRecord0.getComment();
      boolean boolean0 = cSVRecord0.isSet("No header mapping was specified, the record values can't be accessed by name");
      assertFalse(boolean0);
      
      cSVRecord0.isMapped("No header mapping was specified, the record values can't be accessed by name");
      assertEquals(131078L, cSVRecord0.getRecordNumber());
      assertEquals(2, cSVRecord0.size());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord((String[]) null, hashMap0, "jCh", 2676L);
      assertEquals("[]", cSVRecord0.toString());
      assertEquals(2676L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Object object0 = new Object();
      String[] stringArray0 = new String[12];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>", 0L);
      cSVRecord0.isConsistent();
      HashMap<String, Integer> hashMap1 = new HashMap<String, Integer>();
      HashMap<String, Integer> hashMap2 = new HashMap<String, Integer>();
      CSVRecord cSVRecord1 = new CSVRecord(stringArray0, hashMap1, stringArray0[0], 234L);
      boolean boolean0 = cSVRecord1.isConsistent();
      assertFalse(boolean0);
      
      cSVRecord1.isMapped("<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>");
      assertEquals(234L, cSVRecord1.getRecordNumber());
      
      cSVRecord0.values();
      assertFalse(cSVRecord0.isConsistent());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, 131078L);
      boolean boolean0 = cSVRecord0.isSet((String) null);
      assertFalse(boolean0);
      assertEquals(131078L, cSVRecord0.getRecordNumber());
      assertEquals(2, cSVRecord0.size());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[3];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[2], 81L);
      assertEquals(81L, cSVRecord0.getRecordNumber());
      assertEquals("[null, null, null]", cSVRecord0.toString());
      
      cSVRecord0.isMapped(stringArray0[0]);
      assertEquals(81L, cSVRecord0.getRecordNumber());
      assertEquals(3, cSVRecord0.size());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      String[] stringArray0 = new String[7];
      stringArray0[0] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[1] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[2] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[3] = "0er;4.";
      stringArray0[4] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[5] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[6] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>", 0L);
      cSVRecord0.isConsistent();
      HashMap<String, Integer> hashMap1 = new HashMap<String, Integer>();
      CSVRecord cSVRecord1 = new CSVRecord(stringArray0, hashMap1, "new Map() /* ", 0L);
      cSVRecord0.isConsistent();
      cSVRecord1.isConsistent();
      // Undeclared exception!
      try { 
        cSVRecord0.get(479);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 479
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[3];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[2], 81L);
      cSVRecord0.spliterator();
      cSVRecord0.isMapped(stringArray0[0]);
      assertEquals(3, cSVRecord0.size());
      assertEquals(81L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, (-33L));
      cSVRecord0.get((String) null);
      boolean boolean0 = cSVRecord0.isSet((String) null);
      assertEquals((-33L), cSVRecord0.getRecordNumber());
      assertEquals(2, cSVRecord0.size());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      String[] stringArray0 = new String[7];
      stringArray0[0] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[1] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third<string>\n  </values>\n  <mapping>\n    <entry>\n      <string>third</string>\n      <int>2</int>\n    </entry>\n    <entry>\n      <string>first</string>\n      <int>0</int>\n    </entry>\n    <entry>\n      <string>second</string>\n      <int>1</int>\n    </entry>\n  </mapping>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[2] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[3] = "0er;4.";
      stringArray0[4] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[5] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[6] = "<org.apache.commons.csv.CSVRecord>\n  <values>\n    <string>first</string>\n    <string>second</string>\n    <string>third</string>\n  </values>\n  <recordNumber>0</recordNumber>\n</org.apache.commons.csv.CSVRecord>";
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "A", 1L);
      cSVRecord0.toString();
      String[] stringArray1 = new String[0];
      HashMap<String, Integer> hashMap1 = new HashMap<String, Integer>();
      CSVRecord cSVRecord1 = new CSVRecord(stringArray1, hashMap1, "g%cAj^avhe", 0L);
      assertTrue(cSVRecord1.isConsistent());
      
      cSVRecord0.isConsistent();
      boolean boolean0 = cSVRecord0.isConsistent();
      assertFalse(boolean0);
      
      long long0 = cSVRecord0.getRecordNumber();
      assertEquals(1L, long0);
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      String[] stringArray0 = new String[0];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "}Fpkx:/Li2d1!`XgG%a", 131071L);
      boolean boolean0 = cSVRecord0.isConsistent();
      assertEquals(131071L, cSVRecord0.getRecordNumber());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[3];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[2], 81L);
      boolean boolean0 = cSVRecord0.isConsistent();
      assertEquals(81L, cSVRecord0.getRecordNumber());
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "`^T4}*Ov55_(", (-4766L));
      cSVRecord0.toString();
      String string0 = cSVRecord0.toString();
      assertEquals((-4766L), cSVRecord0.getRecordNumber());
      assertEquals("[null, null]", string0);
  }
}
