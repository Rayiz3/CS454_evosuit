/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Sat Dec 16 19:52:29 GMT 2023
 */

package org.jsoup.nodes;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class FormElement_ESTest_scaffolding {

  @org.junit.Rule
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "org.jsoup.nodes.FormElement"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
  } 

  @AfterClass
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("user.dir", "/home/coinse/Documents/jylee/defects4j/framework/temp"); 
    java.lang.System.setProperty("java.io.tmpdir", "/tmp"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(FormElement_ESTest_scaffolding.class.getClassLoader() ,
      "org.jsoup.select.NodeVisitor",
      "org.jsoup.internal.StringUtil",
      "org.jsoup.nodes.Document$QuirksMode",
      "org.jsoup.Connection$Response",
      "org.jsoup.select.Evaluator$AttributeWithValueStarting",
      "org.jsoup.parser.TreeBuilder",
      "org.jsoup.parser.Token$StartTag",
      "org.jsoup.nodes.Comment",
      "org.jsoup.Connection$Request",
      "org.jsoup.select.Evaluator$IndexGreaterThan",
      "org.jsoup.nodes.LeafNode",
      "org.jsoup.select.Evaluator$IndexEvaluator",
      "org.jsoup.HttpStatusException",
      "org.jsoup.parser.Parser",
      "org.jsoup.select.QueryParser",
      "org.jsoup.nodes.NodeUtils",
      "org.jsoup.nodes.Entities$CoreCharset",
      "org.jsoup.select.Evaluator$AttributeWithValueMatching",
      "org.jsoup.select.Evaluator$Matches",
      "org.jsoup.select.Selector",
      "org.jsoup.select.Evaluator$AttributeWithValueEnding",
      "org.jsoup.nodes.Element",
      "org.jsoup.helper.HttpConnection",
      "org.jsoup.select.Evaluator$Class",
      "org.jsoup.select.NodeTraversor",
      "org.jsoup.nodes.Entities$1",
      "org.jsoup.helper.HttpConnection$KeyVal",
      "org.jsoup.UncheckedIOException",
      "org.jsoup.Connection$Method",
      "org.jsoup.nodes.Node$OuterHtmlVisitor",
      "org.jsoup.helper.HttpConnection$Base",
      "org.jsoup.parser.Token",
      "org.jsoup.parser.TokenQueue",
      "org.jsoup.select.Evaluator$AttributeKeyPair",
      "org.jsoup.select.Evaluator$MatchesOwn",
      "org.jsoup.parser.ParseSettings",
      "org.jsoup.select.NodeFilter",
      "org.jsoup.parser.Tag",
      "org.jsoup.select.CombiningEvaluator$And",
      "org.jsoup.nodes.Node",
      "org.jsoup.select.Evaluator$Attribute",
      "org.jsoup.parser.Token$EndTag",
      "org.jsoup.helper.HttpConnection$Request",
      "org.jsoup.nodes.Document",
      "org.jsoup.Connection$KeyVal",
      "org.jsoup.select.Evaluator$AttributeStarting",
      "org.jsoup.select.Evaluator$ContainsOwnText",
      "org.jsoup.nodes.Entities",
      "org.jsoup.select.Evaluator$AttributeWithValueContaining",
      "org.jsoup.select.Elements",
      "org.jsoup.Jsoup",
      "org.jsoup.nodes.DataNode",
      "org.jsoup.parser.HtmlTreeBuilder",
      "org.jsoup.nodes.FormElement",
      "org.jsoup.select.Evaluator$AllElements",
      "org.jsoup.nodes.Element$1",
      "org.jsoup.select.Evaluator$IndexLessThan",
      "org.jsoup.nodes.Attributes",
      "org.jsoup.UnsupportedMimeTypeException",
      "org.jsoup.nodes.TextNode",
      "org.jsoup.select.Evaluator$AttributeWithValue",
      "org.jsoup.select.Evaluator$AttributeWithValueNot",
      "org.jsoup.nodes.Entities$EscapeMode",
      "org.jsoup.parser.ParseErrorList",
      "org.jsoup.nodes.BooleanAttribute",
      "org.jsoup.parser.XmlTreeBuilder",
      "org.jsoup.SerializationException",
      "org.jsoup.nodes.Document$OutputSettings",
      "org.jsoup.select.CombiningEvaluator",
      "org.jsoup.select.CombiningEvaluator$Or",
      "org.jsoup.select.Evaluator$ContainsText",
      "org.jsoup.nodes.EntitiesData",
      "org.jsoup.select.Evaluator",
      "org.jsoup.Connection$Base",
      "org.jsoup.Connection",
      "org.jsoup.select.Evaluator$Id",
      "org.jsoup.internal.Normalizer",
      "org.jsoup.nodes.CDataNode",
      "org.jsoup.select.Evaluator$IndexEquals",
      "org.jsoup.helper.Validate",
      "org.jsoup.select.Selector$SelectorParseException",
      "org.jsoup.select.Collector",
      "org.jsoup.select.Collector$Accumulator",
      "org.jsoup.parser.Token$Tag",
      "org.jsoup.nodes.XmlDeclaration",
      "org.jsoup.parser.Token$TokenType",
      "org.jsoup.internal.ConstrainableInputStream",
      "org.jsoup.nodes.Attribute",
      "org.jsoup.parser.CharacterReader",
      "org.jsoup.select.Evaluator$Tag",
      "org.jsoup.nodes.Document$OutputSettings$Syntax",
      "org.jsoup.helper.HttpConnection$Response"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(FormElement_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "org.jsoup.nodes.Node",
      "org.jsoup.nodes.Element",
      "org.jsoup.nodes.FormElement",
      "org.jsoup.Connection$Method",
      "org.jsoup.select.Elements",
      "org.jsoup.internal.StringUtil",
      "org.jsoup.nodes.Node$OuterHtmlVisitor",
      "org.jsoup.nodes.NodeUtils",
      "org.jsoup.nodes.Document$OutputSettings",
      "org.jsoup.nodes.EntitiesData",
      "org.jsoup.nodes.Document$OutputSettings$Syntax",
      "org.jsoup.nodes.Entities",
      "org.jsoup.parser.CharacterReader",
      "org.jsoup.nodes.Entities$EscapeMode",
      "org.jsoup.nodes.Document$QuirksMode",
      "org.jsoup.nodes.Entities$CoreCharset",
      "org.jsoup.select.NodeTraversor",
      "org.jsoup.nodes.Attribute",
      "org.jsoup.nodes.Entities$1",
      "org.jsoup.parser.Token",
      "org.jsoup.parser.Token$Tag",
      "org.jsoup.parser.Token$StartTag",
      "org.jsoup.parser.Token$TokenType",
      "org.jsoup.parser.Token$EndTag",
      "org.jsoup.parser.ParseErrorList",
      "org.jsoup.select.Selector",
      "org.jsoup.select.QueryParser",
      "org.jsoup.parser.TokenQueue",
      "org.jsoup.select.Evaluator",
      "org.jsoup.select.Evaluator$Tag",
      "org.jsoup.select.Collector",
      "org.jsoup.select.Collector$FirstFinder",
      "org.jsoup.select.NodeFilter$FilterResult",
      "org.jsoup.nodes.Attributes",
      "org.jsoup.internal.Normalizer",
      "org.jsoup.helper.Validate",
      "org.jsoup.parser.Tag",
      "org.jsoup.parser.ParseSettings",
      "org.jsoup.nodes.Document",
      "org.jsoup.Jsoup",
      "org.jsoup.helper.HttpConnection",
      "org.jsoup.helper.HttpConnection$Base",
      "org.jsoup.helper.HttpConnection$Request",
      "org.jsoup.parser.Parser",
      "org.jsoup.parser.TreeBuilder",
      "org.jsoup.parser.HtmlTreeBuilder",
      "org.jsoup.helper.HttpConnection$Response",
      "org.jsoup.nodes.Element$1",
      "org.jsoup.helper.HttpConnection$KeyVal",
      "org.jsoup.select.Evaluator$Attribute",
      "org.jsoup.select.CombiningEvaluator",
      "org.jsoup.select.CombiningEvaluator$And",
      "org.jsoup.select.Collector$Accumulator"
    );
  }
}
