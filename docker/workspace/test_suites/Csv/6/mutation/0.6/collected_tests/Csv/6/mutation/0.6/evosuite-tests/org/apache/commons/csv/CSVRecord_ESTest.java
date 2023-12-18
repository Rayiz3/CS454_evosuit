/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 12:17:48 GMT 2023
 */

package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import com.thoughtworks.xstream.io.StreamException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import org.apache.commons.csv.CSVRecord;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.testcarver.testcase.EvoSuiteXStream;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class CSVRecord_ESTest extends CSVRecord_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      String[] stringArray0 = new String[5];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer integer0 = new Integer(90140);
      hashMap0.put("Index for header '%s' is %d but CSVRecord only has %d values!", integer0);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "Index for header '%s' is %d but CSVRecord only has %d values!", 0L);
      boolean boolean0 = cSVRecord0.isSet("Index for header '%s' is %d but CSVRecord only has %d values!");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      String[] stringArray0 = new String[8];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer integer0 = new Integer(891);
      Integer integer1 = new Integer(2099);
      hashMap0.put("m6@=XYiEc\"", integer0);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "m6@=XYiEc\"", (-1L));
      Consumer<String> consumer0 = (Consumer<String>) mock(Consumer.class, new ViolatedAssumptionAnswer());
      cSVRecord0.forEach(consumer0);
      String[] stringArray1 = new String[4];
      stringArray1[0] = "m6@=XYiEc\"";
      stringArray1[1] = "escape";
      stringArray1[2] = "m6@=XYiEc\"";
      stringArray1[3] = "m6@=XYiEc\"";
      CSVRecord cSVRecord1 = new CSVRecord(stringArray1, hashMap0, (String) null, 78L);
      cSVRecord1.isConsistent();
      CSVRecord cSVRecord2 = new CSVRecord(stringArray1, hashMap0, "m6@=XYiEc\"", (-4186L));
      cSVRecord2.isSet("m6@=XYiEc\"");
      // Undeclared exception!
      try { 
        cSVRecord1.get("e8`YCi7?dwS`l<?XP'");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Mapping for e8`YCi7?dwS`l<?XP' not found, expected one of [m6@=XYiEc\"]
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      String[] stringArray0 = new String[5];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String string0 = "Qu)$D\"n3";
      Integer integer0 = new Integer((-2285));
      hashMap0.put("Qu)$D\"n3", integer0);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "Qu)$D\"n3", (-1L));
      // Undeclared exception!
      try { 
        cSVRecord0.get("Qu)$D\"n3");
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Index for header 'Qu)$D\"n3' is -2285 but CSVRecord only has 5 values!
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      String[] stringArray0 = new String[8];
      String string0 = "|N|";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, "|N|", (-11L));
      // Undeclared exception!
      try { 
        cSVRecord0.get("|N|");
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // No header mapping was specified, the record values can't be accessed by name
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      hashMap0.put("c4N%-9c8`", (Integer) null);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "c4N%-9c8`", 1048566L);
      // Undeclared exception!
      try { 
        cSVRecord0.isSet("c4N%-9c8`");
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      String[] stringArray0 = new String[7];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer integer0 = new Integer(0);
      hashMap0.put("Index for header '%s' is %d but CSVRecord only has %d values!", integer0);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "Index for header '%s' is %d but CSVRecord only has %d values!", (-2272L));
      boolean boolean0 = cSVRecord0.isSet("Index for header '%s' is %d but CSVRecord only has %d values!");
      assertEquals((-2272L), cSVRecord0.getRecordNumber());
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      String[] stringArray0 = new String[8];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, (String) null, 3531L);
      // Undeclared exception!
      try { 
        cSVRecord0.get((String) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Mapping for null not found, expected one of []
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Object object0 = new Object();
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer integer0 = new Integer((-1));
      hashMap0.put("l'T`)Z^c:QA", integer0);
      CSVRecord cSVRecord0 = new CSVRecord((String[]) null, hashMap0, "", 0L);
      cSVRecord0.getRecordNumber();
      CSVRecord cSVRecord1 = new CSVRecord((String[]) null, hashMap0, (String) null, 0L);
      cSVRecord1.isSet((String) null);
      // Undeclared exception!
      try { 
        EvoSuiteXStream.fromString("org.apache.commons.csv.CSVRecord");
        fail("Expecting exception: StreamException");
      
      } catch(StreamException e) {
         //
         // 
         //
         verifyException("com.thoughtworks.xstream.io.xml.XppReader", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Object object0 = new Object();
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[1];
      stringArray0[0] = "9";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "ISO_YEAR", 2281L);
      cSVRecord0.spliterator();
      CSVRecord cSVRecord1 = new CSVRecord(stringArray0, hashMap0, "ISO_YEAR", 2281L);
      cSVRecord1.spliterator();
      cSVRecord0.isConsistent();
      cSVRecord0.isConsistent();
      cSVRecord1.toMap();
      cSVRecord0.isSet("9");
      // Undeclared exception!
      try { 
        cSVRecord1.get(90136);
        fail("Expecting exception: ArrayIndexOutOfBoundsException");
      
      } catch(ArrayIndexOutOfBoundsException e) {
         //
         // 90136
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Object object0 = new Object();
      String[] stringArray0 = new String[6];
      stringArray0[0] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>", 0L);
      cSVRecord0.isMapped((String) null);
      HashMap<String, String> hashMap1 = new HashMap<String, String>();
      cSVRecord0.isConsistent();
      boolean boolean0 = cSVRecord0.isConsistent();
      assertFalse(boolean0);
      
      cSVRecord0.size();
      assertFalse(cSVRecord0.isConsistent());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      String[] stringArray0 = new String[6];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      Integer.getInteger("WK");
      hashMap0.put("WK", (Integer) null);
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "WK", 0L);
      boolean boolean0 = cSVRecord0.isMapped("WK");
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String string0 = "Mapping or %s not found, expected one of %s";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "Mapping or %s not found, expected one of %s", 999985L);
      hashMap0.put("Mapping or %s not found, expected one of %s", (Integer) null);
      // Undeclared exception!
      try { 
        cSVRecord0.toMap();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.csv.CSVRecord", e);
      }
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      Object object0 = new Object();
      String[] stringArray0 = new String[6];
      stringArray0[0] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[1] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[2] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[3] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[4] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[5] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>", 2856L);
      boolean boolean0 = cSVRecord0.isConsistent();
      cSVRecord0.isSet("58IA._pm7x+:A]M*u");
      boolean boolean1 = cSVRecord0.isSet("58IA._pm7x+:A]M*u");
      assertFalse(boolean1 == boolean0);
      
      cSVRecord0.toString();
      assertEquals(2856L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      Object object0 = new Object();
      String[] stringArray0 = new String[6];
      stringArray0[0] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[0] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[2] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[3] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>", 2869L);
      cSVRecord0.isConsistent();
      cSVRecord0.spliterator();
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord1 = new CSVRecord(stringArray0, hashMap0, "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>", 0L);
      cSVRecord1.spliterator();
      cSVRecord0.isConsistent();
      boolean boolean0 = cSVRecord0.isConsistent();
      assertTrue(boolean0);
      
      cSVRecord0.values();
      assertEquals(2869L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      Object object0 = new Object();
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord((String[]) null, hashMap0, "9", 0L);
      boolean boolean0 = cSVRecord0.isConsistent();
      assertTrue(boolean0);
      
      cSVRecord0.getComment();
      Integer integer0 = new Integer(6);
      hashMap0.put("%FF Zv1i/r5fUN", integer0);
      cSVRecord0.isConsistent();
      boolean boolean1 = cSVRecord0.isConsistent();
      assertFalse(boolean1);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      Object object0 = new Object();
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[8];
      stringArray0[0] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[1] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[2] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      stringArray0[3] = "9";
      stringArray0[4] = "9";
      stringArray0[5] = "Ggpw42ro\u0004JNg2.f[";
      stringArray0[6] = "9";
      stringArray0[7] = "<org.apache.commons.csv.CSVRecord>\n  <recordNumber>0</recordNumber>\n  <values>\n    <string>A</string>\n    <string>B</string>\n    <string>C</string>\n  </values>\n</org.apache.commons.csv.CSVRecord>";
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "_G#8S<Wsv1B", 0L);
      cSVRecord0.isConsistent();
      // Undeclared exception!
      try { 
        EvoSuiteXStream.fromString("c");
        fail("Expecting exception: StreamException");
      
      } catch(StreamException e) {
         //
         // 
         //
         verifyException("com.thoughtworks.xstream.io.xml.XppReader", e);
      }
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[18];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[4], 81L);
      cSVRecord0.putIn((Map<String, Integer>) hashMap0);
      assertEquals(81L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[6];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[0], 1324L);
      cSVRecord0.toMap();
      assertEquals(1324L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      String[] stringArray0 = new String[19];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, "jg=J|E~eLv=d4aJ00", 0L);
      boolean boolean0 = cSVRecord0.isMapped("jg=J|E~eLv=d4aJ00");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      String[] stringArray0 = new String[2];
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, "t1p,u.5U$ Mg\";|", 0L);
      boolean boolean0 = cSVRecord0.isSet("t1p,u.5U$ Mg\";|");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[5];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[4], 100L);
      boolean boolean0 = cSVRecord0.isMapped(stringArray0[2]);
      assertFalse(boolean0);
      assertEquals(100L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[5];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[4], 124L);
      boolean boolean0 = cSVRecord0.isConsistent();
      assertFalse(boolean0);
      assertEquals(124L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test22()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord((String[]) null, hashMap0, "", 0L);
      assertEquals(0L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test23()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      CSVRecord cSVRecord0 = new CSVRecord((String[]) null, hashMap0, "PERSISTENT", 2132L);
      assertEquals("[]", cSVRecord0.toString());
      
      boolean boolean0 = cSVRecord0.isConsistent();
      assertTrue(boolean0);
      assertEquals(2132L, cSVRecord0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test24()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[4];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[0], (-1L));
      assertEquals((-1L), cSVRecord0.getRecordNumber());
      assertFalse(cSVRecord0.isConsistent());
  }

  @Test(timeout = 4000)
  public void test25()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[8];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, (Map<String, Integer>) null, "3}c2mgUa6R'0yv9IoU\"", 0L);
      boolean boolean0 = cSVRecord0.isConsistent();
      assertTrue(boolean0);
      assertEquals("[null, null, null, null, null, null, null, null]", cSVRecord0.toString());
  }

  @Test(timeout = 4000)
  public void test26()  throws Throwable  {
      HashMap<String, Integer> hashMap0 = new HashMap<String, Integer>();
      String[] stringArray0 = new String[5];
      CSVRecord cSVRecord0 = new CSVRecord(stringArray0, hashMap0, stringArray0[4], 100L);
      cSVRecord0.iterator();
      boolean boolean0 = cSVRecord0.isMapped(stringArray0[2]);
      assertFalse(boolean0);
      assertEquals(100L, cSVRecord0.getRecordNumber());
      assertEquals(5, cSVRecord0.size());
  }
}
