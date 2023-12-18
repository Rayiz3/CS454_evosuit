/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 19:48:59 GMT 2023
 */

package org.jsoup.nodes;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.util.List;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.Connection;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class FormElement_ESTest extends FormElement_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Tag tag0 = Tag.valueOf("sele\"Zc`");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "sele\"Zc`", attributes0);
      formElement0.attr("method", "POST");
      // Undeclared exception!
      try { 
        formElement0.submit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Malformed URL: sele\"Zc`
         //
         verifyException("org.jsoup.helper.HttpConnection", e);
      }
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      Tag tag0 = Tag.valueOf("select");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "select", attributes0);
      FormElement formElement1 = formElement0.addElement(formElement0);
      assertEquals(0, formElement1.siblingIndex());
      
      formElement1.childNodesAsArray();
      formElement0.attr("disabled", "select");
      List<Connection.KeyVal> list0 = formElement1.formData();
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      Tag tag0 = Tag.valueOf("select");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "select", attributes0);
      formElement0.addElement(formElement0);
      formElement0.attr("name", "select");
      List<Connection.KeyVal> list0 = formElement0.formData();
      assertEquals(0, list0.size());
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      Tag.valueOf("select");
      ParseSettings parseSettings0 = new ParseSettings(false, true);
      Tag tag0 = Tag.valueOf("select", parseSettings0);
      Attributes attributes0 = new Attributes();
      attributes0.clone();
      Attributes attributes1 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "on", attributes0);
      Tag.valueOf("on");
      Parser.parseBodyFragmentRelaxed("XfMt/%BX`\"<", "<img /rc=");
      formElement0.addElement(formElement0);
      formElement0.formData();
      formElement0.formData();
      List<Connection.KeyVal> list0 = formElement0.formData();
      List<Connection.KeyVal> list1 = formElement0.formData();
      assertNotSame(list1, list0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      Tag tag0 = Tag.valueOf("action");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "SECT", attributes0);
      formElement0.attr("action", "ST");
      // Undeclared exception!
      try { 
        formElement0.submit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Could not determine a form action URL for submit. Ensure you set a base URI when parsing.
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      Tag tag0 = Tag.valueOf("on");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "on", attributes0);
      formElement0.formData();
      formElement0.formData();
      formElement0.formData();
      Elements elements0 = formElement0.elements();
      assertEquals(0, elements0.size());
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      String string0 = "select";
      Tag tag0 = Tag.valueOf("select");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "select", attributes0);
      FormElement formElement1 = formElement0.addElement(formElement0);
      // Undeclared exception!
      try { 
        formElement0.removeChild(formElement1);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Must be true
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      Tag tag0 = Tag.valueOf("on");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "on", attributes0);
      // Undeclared exception!
      try { 
        formElement0.submit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Malformed URL: on
         //
         verifyException("org.jsoup.helper.HttpConnection", e);
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      Tag tag0 = Tag.valueOf("]i'ryR\"6vq|jw");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "]i'ryR\"6vq|jw", attributes0);
      FormElement formElement1 = formElement0.addElement(formElement0);
      List<Connection.KeyVal> list0 = formElement1.formData();
      assertEquals(0, list0.size());
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      Tag tag0 = Tag.valueOf("select");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "select", attributes0);
      FormElement formElement1 = formElement0.addElement(formElement0);
      List<Connection.KeyVal> list0 = formElement1.formData();
      assertTrue(list0.isEmpty());
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      Tag tag0 = Tag.valueOf("-\"C=jUA/^Dq3*");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "-\"C=jUA/^Dq3*", attributes0);
      assertEquals("-\"C=jUA/^Dq3*", formElement0.tagName());
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      Tag tag0 = Tag.valueOf("-\"C=jUA/Dq3*");
      Attributes attributes0 = new Attributes();
      FormElement formElement0 = new FormElement(tag0, "-\"C=jUA/Dq3*", attributes0);
      List<Connection.KeyVal> list0 = formElement0.formData();
      assertTrue(list0.isEmpty());
  }
}
