/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 11:30:27 GMT 2023
 */

package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.shaded.org.mockito.Mockito.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.io.File;
import java.io.IOException;
import java.io.PipedReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.function.Consumer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.Quote;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.ViolatedAssumptionAnswer;
import org.evosuite.runtime.mock.java.io.MockFile;
import org.evosuite.runtime.mock.java.net.MockURL;
import org.evosuite.runtime.testdata.FileSystemHandling;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class CSVParser_ESTest extends CSVParser_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.DEFAULT;
      String[] stringArray0 = new String[1];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      CSVFormat.newFormat('/');
      cSVFormat1.format(stringArray0);
      CSVFormat cSVFormat2 = cSVFormat1.withSkipHeaderRecord(true);
      CSVParser cSVParser0 = CSVParser.parse("\"\"", cSVFormat2);
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test
  public void test01() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test01()  throws Throwable  {
//       MockURL.getFtpExample();
//       Charset.defaultCharset();
//       CSVFormat cSVFormat0 = CSVFormat.RFC4180;
//       CSVFormat cSVFormat1 = cSVFormat0.withNullString("qJu$TrcAXm");
//       cSVFormat1.withEscape('?');
//       String[] stringArray0 = new String[0];
//       CSVFormat cSVFormat2 = cSVFormat1.withHeader(stringArray0);
//       CSVFormat cSVFormat3 = cSVFormat2.withCommentStart('A');
//       CSVParser cSVParser0 = CSVParser.parse("2BB]xagwBI1Q>G~;zH5", cSVFormat3);
//       assertEquals(1L, cSVParser0.getRecordNumber());
//       
//       Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
//       cSVParser0.forEach(consumer0);
//       cSVParser0.nextRecord();
//       MockURL.getHttpExample();
//       cSVFormat1.withHeader(stringArray0);
//       CSVParser cSVParser1 = CSVParser.parse("qJu$TrcAXm", cSVFormat3);
//       assertEquals(1L, cSVParser1.getRecordNumber());
//   }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVFormat cSVFormat1 = cSVFormat0.withNullString("UTF-8");
      FileSystemHandling fileSystemHandling0 = new FileSystemHandling();
      String[] stringArray0 = new String[0];
      CSVFormat cSVFormat2 = cSVFormat1.withHeader(stringArray0);
      CSVParser cSVParser0 = CSVParser.parse("UTF-8", cSVFormat2);
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      MockURL.getFtpExample();
      Charset.defaultCharset();
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVFormat cSVFormat1 = cSVFormat0.withNullString("qJu$TrcAXm");
      String[] stringArray0 = new String[1];
      stringArray0[0] = "qJu$TrcAXm";
      CSVParser cSVParser0 = CSVParser.parse("TQs%_", cSVFormat1);
      cSVParser0.nextRecord();
      cSVParser0.close();
      try { 
        cSVParser0.nextRecord();
        fail("Expecting exception: IOException");
      
      } catch(IOException e) {
         //
         // Stream closed
         //
         verifyException("java.io.BufferedReader", e);
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVFormat cSVFormat1 = cSVFormat0.withNullString("UTF-8");
      CSVParser cSVParser0 = CSVParser.parse("sxn9vr>i^[[!:yn", cSVFormat1);
      cSVParser0.nextRecord();
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test
  public void test05() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test05()  throws Throwable  {
//       CSVFormat cSVFormat0 = CSVFormat.RFC4180;
//       CSVParser cSVParser0 = CSVParser.parse(".TT", cSVFormat0);
//       cSVParser0.close();
//       Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
//       cSVParser0.forEach(consumer0);
//       assertEquals(0L, cSVParser0.getRecordNumber());
//   }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.TDF;
      String[] stringArray0 = new String[0];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      cSVFormat0.format(stringArray0);
      CSVParser cSVParser0 = CSVParser.parse("", cSVFormat1);
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.TDF;
      String[] stringArray0 = new String[0];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      cSVFormat0.format(stringArray0);
      CSVParser cSVParser0 = CSVParser.parse("O17xjGyyEh5k", cSVFormat1);
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test
  public void test08() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test08()  throws Throwable  {
//       CSVFormat cSVFormat0 = CSVFormat.MYSQL;
//       CSVParser cSVParser0 = CSVParser.parse("|", cSVFormat0);
//       Consumer<Object> consumer0 = (Consumer<Object>) mock(Consumer.class, new ViolatedAssumptionAnswer());
//       cSVParser0.forEach(consumer0);
//       assertEquals(1L, cSVParser0.getRecordNumber());
//   }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.DEFAULT;
      cSVFormat0.withCommentStart('@');
      CSVParser cSVParser0 = CSVParser.parse("foo\nbaar,\nhello,world\n,kanu", cSVFormat0);
      CSVRecord cSVRecord0 = cSVParser0.nextRecord();
      assertEquals("[foo]", cSVRecord0.toString());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVParser cSVParser0 = CSVParser.parse("bD cxu[,xyR%", cSVFormat0);
      CSVRecord cSVRecord0 = cSVParser0.nextRecord();
      assertEquals("[bD cxu[, xyR%]", cSVRecord0.toString());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.MYSQL;
      CSVParser cSVParser0 = CSVParser.parse("sxn9vr>i^[[!:yn", cSVFormat0);
      cSVParser0.getCurrentLineNumber();
      cSVParser0.nextRecord();
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.newFormat('b');
      Quote quote0 = Quote.MINIMAL;
      CSVFormat cSVFormat1 = cSVFormat0.withQuotePolicy(quote0);
      cSVFormat1.withNullString("B<p");
      CSVFormat cSVFormat2 = cSVFormat1.withNullString("B<p");
      cSVFormat2.withQuoteChar('b');
      CSVFormat cSVFormat3 = cSVFormat2.withCommentStart('j');
      cSVFormat3.withIgnoreEmptyLines(false);
      CSVFormat cSVFormat4 = cSVFormat3.withIgnoreSurroundingSpaces(false);
      PipedReader pipedReader0 = new PipedReader();
      cSVFormat2.withEscape('_');
      CSVParser cSVParser0 = cSVFormat4.parse(pipedReader0);
      CSVParser.parse("B<p", cSVFormat0);
      cSVParser0.close();
      cSVParser0.close();
      cSVParser0.close();
      cSVFormat0.withDelimiter('@');
      cSVParser0.getRecordNumber();
      URL uRL0 = null;
      cSVFormat4.toString();
      // Undeclared exception!
      try { 
        Charset.forName("PDoDYH<V^YQy?m");
        fail("Expecting exception: IllegalCharsetNameException");
      
      } catch(IllegalCharsetNameException e) {
         //
         // PDoDYH<V^YQy?m
         //
         verifyException("java.nio.charset.Charset", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.MYSQL;
      CSVParser cSVParser0 = CSVParser.parse(".TT", cSVFormat0);
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

  @Test
  public void test14() {}
// Defects4J: flaky method
//   @Test(timeout = 4000)
//   public void test14()  throws Throwable  {
//       CSVFormat cSVFormat0 = CSVFormat.MYSQL;
//       CSVParser cSVParser0 = CSVParser.parse("org.h2.engine.Session", cSVFormat0);
//       cSVParser0.spliterator();
//       cSVParser0.nextRecord();
//       assertEquals(1L, cSVParser0.getRecordNumber());
//   }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      CSVParser cSVParser0 = CSVParser.parse("f9S5n31z@)1 Im", cSVFormat0);
      cSVParser0.getRecords();
      cSVParser0.close();
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.MYSQL;
      CSVParser cSVParser0 = CSVParser.parse("", cSVFormat0);
      assertEquals(0L, cSVParser0.getRecordNumber());
      
      CSVRecord cSVRecord0 = cSVParser0.nextRecord();
      assertNull(cSVRecord0);
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test17()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.MYSQL;
      CSVParser cSVParser0 = CSVParser.parse("[&O`", cSVFormat0);
      cSVParser0.isClosed();
      cSVParser0.close();
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test18()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.MYSQL;
      CSVParser cSVParser0 = CSVParser.parse("2bb]xagwbi1q>g~;zh5", cSVFormat0);
      cSVParser0.close();
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test19()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.DEFAULT;
      String[] stringArray0 = new String[1];
      CSVFormat cSVFormat1 = cSVFormat0.withHeader(stringArray0);
      cSVFormat1.format(stringArray0);
      CSVParser cSVParser0 = CSVParser.parse("\"\"", cSVFormat1);
      assertEquals(0L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test20()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.MYSQL;
      CSVParser cSVParser0 = CSVParser.parse("org.h2.engine.Session", cSVFormat0);
      cSVParser0.nextRecord();
      assertEquals(1L, cSVParser0.getRecordNumber());
  }

  @Test(timeout = 4000)
  public void test21()  throws Throwable  {
      CSVFormat cSVFormat0 = CSVFormat.RFC4180;
      MockURL.getFtpExample();
      // Undeclared exception!
      try { 
        CSVParser.parse((File) null, cSVFormat0);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Parameter 'file' must not be null!
         //
         verifyException("org.apache.commons.csv.Assertions", e);
      }
  }
}
