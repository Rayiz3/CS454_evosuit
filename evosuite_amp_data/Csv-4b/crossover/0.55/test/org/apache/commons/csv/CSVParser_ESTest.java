/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 11:45:50 GMT 2023
 */

package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.File;
import java.io.PipedReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.net.MockURI;
import org.evosuite.runtime.mock.java.net.MockURL;
import org.evosuite.runtime.testdata.EvoSuiteFile;
import org.evosuite.runtime.testdata.EvoSuiteURL;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.evosuite.runtime.testdata.NetworkHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class CSVParser_ESTest extends CSVParser_ESTest_scaffolding {

  @Test
  public void test00() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test00()  throws Throwable  {
//       CSVFormat cSVFormat0 = CSVFormat.RFC4180;
//       CSVFormat cSVFormat1 = CSVFormat.RFC4180;
//       CSVParser cSVParser0 = CSVParser.parse("8qX", cSVFormat1);
//       Character character0 = new Character(')');
//       CSVFormat cSVFormat2 = cSVFormat1.withEscape(character0);
//       Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
//       cSVParser0.forEach(consumer0);
//       CSVParser cSVParser1 = CSVParser.parse("34r", cSVFormat1);
//       cSVParser1.nextRecord();
//       cSVParser0.close();
//       URL uRL0 = MockURL.getHttpExample();
//       EvoSuiteURL evoSuiteURL0 = new EvoSuiteURL("http://www.someFakeButWellFormedURL.org/fooExample");
//       NetworkHandling.createRemoteTextFile(evoSuiteURL0, "8qX");
//       Charset charset0 = Charset.defaultCharset();
//       CSVParser cSVParser2 = CSVParser.parse("8qX", cSVFormat1);
//       charset0.encode("y_5=");
//       cSVParser2.close();
//       cSVParser2.close();
//       CSVParser cSVParser3 = CSVParser.parse(uRL0, charset0, cSVFormat2);
//       assertFalse(cSVParser3.equals((Object)cSVParser2));
//   }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.EXCEL;
      URL uRL0 = MockURL.getHttpExample();
      EvoSuiteURL evoSuiteURL0 = new EvoSuiteURL("http://www.someFakeButWellFormedURL.org/fooExample");
      NetworkHandling.createRemoteTextFile(evoSuiteURL0, "?lAAI!Z(l1^XwYr");
      Charset charset0 = Charset.defaultCharset();
      CSVParser cSVParser0 = CSVParser.parse(uRL0, charset0, cSVFormat0);
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test
  public void test02() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test02()  throws Throwable  {
//       CSVFormat cSVFormat0 = CSVFormat.EXCEL;
//       String[] stringArray0 = new String[1];
//       stringArray0[0] = "/qf";
//       CSVFormat cSVFormat1 = cSVFormat0.withNullString("/qf");
//       cSVFormat1.validate();
//       CSVParser.parse("^BldA4-", cSVFormat0);
//       CSVParser cSVParser0 = CSVParser.parse("/qf", cSVFormat1);
//       Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
//       cSVParser0.forEach(consumer0);
//       Charset.defaultCharset();
//       Charset.defaultCharset();
//       CSVParser cSVParser1 = CSVParser.parse(".combineClob\"", cSVFormat0);
//       assertFalse(cSVParser1.equals((Object)cSVParser0));
//   }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.EXCEL;
      CSVFormat cSVFormat1 = cSVFormat0.withNullString("!H.2HCRjmaNk");
      CSVParser cSVParser0 = CSVParser.parse("jdbc:h2:mem:my_test;", cSVFormat1);
      CSVRecord cSVRecord0 = cSVParser0.nextRecord();
      assertEquals("[jdbc:h2:mem:my_test;]", cSVRecord0.toString());
      
      CSVRecord cSVRecord1 = cSVParser0.nextRecord();
      assertNull(cSVRecord1);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVFormat cSVFormat1 = cSVFormat0.withNullString("url");
      CSVParser cSVParser0 = CSVParser.parse("url", cSVFormat1);
      CSVRecord cSVRecord0 = cSVParser0.nextRecord();
      assertEquals("[null]", cSVRecord0.toString());
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      String[] stringArray0 = new String[1];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      Character character0 = new Character('w');
      CSVFormat cSVFormat2 = cSVFormat1.withCommentStart(character0);
      CSVFormat cSVFormat3 = cSVFormat2.withRecordSeparator('w');
      CSVFormat cSVFormat4 = cSVFormat3.withSkipHeaderRecord(true);
      CSVParser cSVParser0 = CSVParser.parse("6S7]", cSVFormat4);
      cSVParser0.close();
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.DEFAULT;
      String[] stringArray0 = new String[0];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      CSVParser cSVParser0 = CSVParser.parse("", cSVFormat1);
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.EXCEL;
      String[] stringArray0 = new String[0];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      CSVParser cSVParser0 = CSVParser.parse("%_sLfZS%m", cSVFormat1);
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.EXCEL;
      String[] stringArray0 = new String[1];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      CSVParser cSVParser0 = CSVParser.parse("", cSVFormat1);
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test
  public void test09() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test09()  throws Throwable  {
//       CSVFormat cSVFormat0 = CSVFormat.EXCEL;
//       CSVParser cSVParser0 = CSVParser.parse("org.apache.commons.csv.CSVParser$2", cSVFormat0);
//       cSVParser0.close();
//       Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
//       cSVParser0.forEach(consumer0);
//       assertEquals(0L, cSVParser0.getRecordNumber());
//   }

  @Test
  public void test10() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test10()  throws Throwable  {
//       CSVFormat cSVFormat0 = CSVFormat.EXCEL;
//       CSVParser cSVParser0 = CSVParser.parse("WV@", cSVFormat0);
//       Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
//       cSVParser0.forEach(consumer0);
//       assertEquals(1L, cSVParser0.getRecordNumber());
//   }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.EXCEL;
      CSVParser cSVParser0 = CSVParser.parse(",\n(", cSVFormat0);
      CSVRecord cSVRecord0 = cSVParser0.nextRecord();
      assertEquals("[, ]", cSVRecord0.toString());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      File file0 = MockFile.createTempFile("CALL LOCK_MODE()", "uP&S");
      CSVFormat cSVFormat0 = CSVFormat.DEFAULT;
      CSVFormat cSVFormat1 = cSVFormat0.withSkipHeaderRecord(true);
      String string0 = "T/@9;?Pz4O=SGh!Ar";
      FileSystemHandling.appendLineToFile((EvoSuiteFile) null, "T/@9;?Pz4O=SGh!Ar");
      FileSystemHandling.appendStringToFile((EvoSuiteFile) null, (String) null);
      CSVFormat cSVFormat2 = cSVFormat1.withNullString("q>>)w0D");
      CSVFormat cSVFormat3 = cSVFormat2.withCommentStart('|');
      CSVParser cSVParser0 = CSVParser.parse(file0, cSVFormat3);
      cSVParser0.isClosed();
      cSVParser0.getRecordNumber();
      cSVParser0.iterator();
      cSVParser0.iterator();
      try { 
        MockURI.URI("T/@9;?Pz4O=SGh!Ar", "", "N");
        fail("Expecting exception: URISyntaxException");
      
      } catch(URISyntaxException e) {
         //
         // Illegal character in path at index 4: T/@9;?Pz4O=SGh!Ar:#N
         //
         verifyException("java.net.URI$Parser", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      PipedReader pipedReader0 = new PipedReader(1934);
      CSVFormat cSVFormat0 = CSVFormat.EXCEL;
      CSVParser cSVParser0 = new CSVParser(pipedReader0, cSVFormat0);
      boolean boolean0 = cSVParser0.isClosed();
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      PipedReader pipedReader0 = new PipedReader();
      CSVFormat cSVFormat0 = CSVFormat.DEFAULT;
      CSVParser cSVParser0 = new CSVParser(pipedReader0, cSVFormat0);
      assertEquals(0L, cSVParser0.getRecordNumber());
      
      cSVParser0.close();
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVParser cSVParser0 = CSVParser.parse(") invalid parse sequence", cSVFormat0);
      assertEquals(0L, cSVParser0.getRecordNumber());
      
      cSVParser0.iterator();
      List<CSVRecord> list0 = cSVParser0.getRecords();
      assertFalse(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.EXCEL;
      File file0 = MockFile.createTempFile("'W6l<RU>{", "'W6l<RU>{");
      CSVParser cSVParser0 = CSVParser.parse(file0, cSVFormat0);
      List<CSVRecord> list0 = cSVParser0.getRecords();
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.MYSQL;
      CSVParser cSVParser0 = CSVParser.parse("UT8", cSVFormat0);
      cSVParser0.getCurrentLineNumber();
      cSVParser0.nextRecord();
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVParser cSVParser0 = CSVParser.parse("F%aOmqB['^", cSVFormat0);
      cSVParser0.getRecords();
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      PipedReader pipedReader0 = new PipedReader();
      CSVFormat cSVFormat0 = CSVFormat.DEFAULT;
      CSVParser cSVParser0 = new CSVParser(pipedReader0, cSVFormat0);
      // Undeclared exception!
      try { 
        cSVParser0.getHeaderMap();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("java.util.HashMap", e);
      }
  }
}
