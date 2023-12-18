/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Sat Dec 16 13:37:53 GMT 2023
 */

package org.apache.commons.csv;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class CSVPrinter_ESTest_scaffolding {

  @org.junit.Rule
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "org.apache.commons.csv.CSVPrinter"; 
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
    java.lang.System.setProperty("file.encoding", "UTF-8"); 
    java.lang.System.setProperty("user.home", "/home/coinse"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(CSVPrinter_ESTest_scaffolding.class.getClassLoader() ,
      "org.h2.value.ValueLong",
      "org.h2.constant.SysProperties",
      "org.h2.util.SortedProperties",
      "org.h2.util.Utils$1",
      "org.h2.util.MathUtils$1",
      "org.h2.value.ValueStringIgnoreCase",
      "org.apache.commons.csv.Constants",
      "org.h2.value.CompareMode",
      "org.h2.tools.SimpleResultSet",
      "org.h2.message.DbException",
      "org.apache.commons.csv.Assertions",
      "org.h2.util.Utils",
      "org.h2.tools.SimpleRowSource",
      "org.h2.value.ValueString",
      "org.h2.value.ValueJavaObject",
      "org.h2.value.ValueUuid",
      "org.apache.commons.csv.CSVFormat",
      "org.h2.store.DataHandler",
      "org.apache.commons.csv.CSVParser",
      "org.h2.value.ValueDate",
      "org.h2.util.IOUtils",
      "org.h2.value.Value",
      "org.h2.tools.SimpleResultSet$Column",
      "org.h2.value.ValueArray",
      "org.h2.value.ValueFloat",
      "org.h2.value.ValueBytes",
      "org.h2.value.ValueResultSet",
      "org.h2.value.ValueDouble",
      "org.h2.util.New",
      "org.h2.util.MathUtils",
      "org.h2.value.ValueInt",
      "org.h2.constant.ErrorCode",
      "org.h2.util.StringUtils",
      "org.h2.value.ValueByte",
      "org.h2.value.ValueStringFixed",
      "org.apache.commons.csv.Quote",
      "org.h2.value.DataType",
      "org.h2.jdbc.JdbcSQLException",
      "org.h2.value.ValueNull",
      "org.h2.value.ValueBoolean",
      "org.h2.value.ValueShort",
      "org.h2.value.ValueTimestamp",
      "org.h2.value.ValueTime",
      "org.apache.commons.csv.CSVPrinter$1",
      "org.apache.commons.csv.CSVPrinter",
      "org.h2.value.ValueDecimal"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(CSVPrinter_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "org.apache.commons.csv.CSVPrinter",
      "org.apache.commons.csv.Quote",
      "org.apache.commons.csv.CSVPrinter$1",
      "org.h2.tools.SimpleResultSet",
      "org.h2.value.ValueResultSet",
      "org.h2.tools.Csv",
      "org.h2.util.IOUtils",
      "org.h2.message.DbException",
      "org.h2.constant.ErrorCode",
      "org.h2.jdbc.JdbcSQLException",
      "org.h2.tools.SimpleResultSet$Column",
      "org.h2.api.IntervalQualifier",
      "org.h2.api.IntervalQualifier$1",
      "org.h2.value.ValueInterval",
      "org.h2.value.TypeInfo",
      "org.apache.commons.csv.Constants",
      "org.apache.commons.csv.CSVFormat",
      "org.apache.commons.csv.Assertions",
      "org.h2.util.New",
      "org.h2.value.Value",
      "org.h2.value.ValueLong",
      "org.h2.util.Utils",
      "org.h2.util.MathUtils",
      "org.h2.constant.SysProperties",
      "org.h2.value.DataType",
      "org.h2.util.StringUtils"
    );
  }
}
