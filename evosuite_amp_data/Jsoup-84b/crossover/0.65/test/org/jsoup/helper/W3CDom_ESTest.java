/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 18:37:49 GMT 2023
 */

package org.jsoup.helper;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import java.nio.charset.Charset;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.HtmlTreeBuilder;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.jsoup.parser.Tag;
import org.junit.runner.RunWith;
import org.w3c.dom.DOMException;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class W3CDom_ESTest extends W3CDom_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = new Document("?(<6KW#$9B4I;1V*ld&");
      HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
      ParseSettings parseSettings0 = ParseSettings.htmlDefault;
      Tag tag0 = Tag.valueOf("i2", parseSettings0);
      Attributes attributes0 = document0.attributes();
      Attributes attributes1 = attributes0.put("xmlns", "?(<6KW#$9B4I;1V*ld&");
      Element element0 = new Element(tag0, "\"jV93Y}{rC]Ho", attributes1);
      attributes0.put("TgoSl", "yDBePku{|sOUO]L?y%");
      document0.appendChild(element0);
      org.w3c.dom.Document document1 = w3CDom0.fromJsoup(document0);
      assertNotNull(document1);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = Parser.parseBodyFragmentRelaxed("U</_.u!@Er<n", ">\"eW(Go");
      document0.attributes();
      String string0 = "$Eod?";
      org.w3c.dom.Document document1 = w3CDom0.fromJsoup(document0);
      // Undeclared exception!
      try { 
        w3CDom0.convert(document0, document1);
        fail("Expecting exception: DOMException");
      
      } catch(DOMException e) {
      }
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Attributes attributes0 = new Attributes();
      W3CDom w3CDom1 = new W3CDom();
      W3CDom.W3CBuilder w3CDom_W3CBuilder0 = new W3CDom.W3CBuilder((org.w3c.dom.Document) null);
      DataNode dataNode0 = new DataNode("pZ");
      dataNode0.shallowClone();
      // Undeclared exception!
      try { 
        w3CDom_W3CBuilder0.head(dataNode0, 56);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.jsoup.helper.W3CDom$W3CBuilder", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = new Document("xmlns:xmlns:?(<6KW#$9B4I;1V*ld&");
      document0.appendElement("xmlns:xmlns:?(<6KW#$9B4I;1V*ld&");
      // Undeclared exception!
      try { 
        w3CDom0.fromJsoup(document0);
        fail("Expecting exception: DOMException");
      
      } catch(DOMException e) {
      }
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = new Document("");
      HtmlTreeBuilder htmlTreeBuilder0 = new HtmlTreeBuilder();
      Parser parser0 = new Parser(htmlTreeBuilder0);
      Document document1 = document0.parser(parser0);
      ParseSettings parseSettings0 = ParseSettings.htmlDefault;
      Tag tag0 = Tag.valueOf("I", parseSettings0);
      Attributes attributes0 = new Attributes();
      attributes0.normalize();
      Element element0 = new Element(tag0, "id", attributes0);
      attributes0.put("xmlns:", "id");
      document1.appendChild(element0);
      document1.select("id");
      Charset.defaultCharset();
      Document.OutputSettings document_OutputSettings0 = new Document.OutputSettings();
      W3CDom w3CDom1 = new W3CDom();
      org.w3c.dom.Document document2 = w3CDom0.fromJsoup(document0);
      w3CDom0.fromJsoup(document0);
      w3CDom1.fromJsoup(document1);
      W3CDom w3CDom2 = new W3CDom();
      org.w3c.dom.Document document3 = w3CDom2.fromJsoup(document0);
      assertFalse(document3.equals((Object)document2));
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = new Document("?6$rB4FI;1V*ld");
      Attributes attributes0 = document0.attributes();
      Element element0 = document0.tagName("code");
      attributes0.put("?6$rB4FI;1V*ld", "?6$rB4FI;1V*ld");
      document0.appendChild(element0);
      // Undeclared exception!
      w3CDom0.fromJsoup(document0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = Parser.parseBodyFragment("xmlns::maytchText", "xmlns::maytchText");
      Attributes attributes0 = document0.attributes();
      attributes0.put("xmlns", "xmlns::maytchText");
      org.w3c.dom.Document document1 = w3CDom0.fromJsoup(document0);
      W3CDom.W3CBuilder w3CDom_W3CBuilder0 = new W3CDom.W3CBuilder(document1);
      // Undeclared exception!
      try { 
        w3CDom_W3CBuilder0.head(document0, (-1));
        fail("Expecting exception: DOMException");
      
      } catch(DOMException e) {
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = new Document(";Q2?rh@Wz6D%n!Mo");
      Attributes attributes0 = document0.attributes();
      attributes0.put("xmlns:K;z^IBC~", ";Q2?rh@Wz6D%n!Mo");
      document0.appendChild(document0);
      // Undeclared exception!
      try { 
        w3CDom0.fromJsoup(document0);
        fail("Expecting exception: DOMException");
      
      } catch(DOMException e) {
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = Parser.parse("tyle", "tyle");
      org.w3c.dom.Document document1 = w3CDom0.fromJsoup(document0);
      W3CDom.W3CBuilder w3CDom_W3CBuilder0 = new W3CDom.W3CBuilder(document1);
      w3CDom_W3CBuilder0.head((Node) null, 356);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = new Document("s");
      Tag tag0 = Tag.valueOf("s");
      Attributes attributes0 = new Attributes();
      Element element0 = new Element(tag0, "s", attributes0);
      attributes0.put("s", "s");
      document0.appendChild(element0);
      org.w3c.dom.Document document1 = w3CDom0.fromJsoup(document0);
      assertNotNull(document1);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = new Document("E)4c=S1h95$V$X4 n2");
      Attributes attributes0 = document0.attributes();
      attributes0.put("E)4c=S1h95$V$X4 n2", "E)4c=S1h95$V$X4 n2");
      Document document1 = (Document)document0.appendChild(document0);
      // Undeclared exception!
      try { 
        w3CDom0.fromJsoup(document1);
        fail("Expecting exception: DOMException");
      
      } catch(DOMException e) {
      }
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      String string0 = w3CDom0.asString((org.w3c.dom.Document) null);
      assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", string0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      // Undeclared exception!
      try { 
        w3CDom0.fromJsoup((Document) null);
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // Object must not be null
         //
         verifyException("org.jsoup.helper.Validate", e);
      }
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      W3CDom w3CDom0 = new W3CDom();
      Document document0 = Parser.parse("", "");
      org.w3c.dom.Document document1 = w3CDom0.fromJsoup(document0);
      assertNotNull(document1);
  }
}
