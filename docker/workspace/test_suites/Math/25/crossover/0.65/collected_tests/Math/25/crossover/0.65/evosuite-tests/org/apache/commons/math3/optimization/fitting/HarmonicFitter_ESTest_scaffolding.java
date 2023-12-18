/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Sat Dec 16 22:36:12 GMT 2023
 */

package org.apache.commons.math3.optimization.fitting;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

@EvoSuiteClassExclude
public class HarmonicFitter_ESTest_scaffolding {

  @org.junit.Rule
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "org.apache.commons.math3.optimization.fitting.HarmonicFitter"; 
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
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(HarmonicFitter_ESTest_scaffolding.class.getClassLoader() ,
      "org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer",
      "org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction",
      "org.apache.commons.math3.util.Precision",
      "org.apache.commons.math3.optimization.PointVectorValuePair",
      "org.apache.commons.math3.exception.util.ExceptionContextProvider",
      "org.apache.commons.math3.optimization.BaseOptimizer",
      "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer",
      "org.apache.commons.math3.util.Incrementor$1",
      "org.apache.commons.math3.optimization.fitting.WeightedObservedPoint",
      "org.apache.commons.math3.optimization.BaseMultivariateVectorOptimizer",
      "org.apache.commons.math3.exception.util.ArgUtils",
      "org.apache.commons.math3.exception.MathArithmeticException",
      "org.apache.commons.math3.exception.NumberIsTooSmallException",
      "org.apache.commons.math3.exception.MathIllegalStateException",
      "org.apache.commons.math3.optimization.fitting.CurveFitter$TheoreticalValuesFunction$1",
      "org.apache.commons.math3.optimization.fitting.CurveFitter$TheoreticalValuesFunction",
      "org.apache.commons.math3.exception.MathIllegalArgumentException",
      "org.apache.commons.math3.util.Incrementor",
      "org.apache.commons.math3.util.FastMath$CodyWaite",
      "org.apache.commons.math3.exception.MathIllegalNumberException",
      "org.apache.commons.math3.util.Pair",
      "org.apache.commons.math3.exception.util.LocalizedFormats",
      "org.apache.commons.math3.exception.ZeroException",
      "org.apache.commons.math3.util.Incrementor$MaxCountExceededCallback",
      "org.apache.commons.math3.exception.ConvergenceException",
      "org.apache.commons.math3.analysis.MultivariateVectorFunction",
      "org.apache.commons.math3.analysis.ParametricUnivariateFunction",
      "org.apache.commons.math3.util.FastMath",
      "org.apache.commons.math3.exception.DimensionMismatchException",
      "org.apache.commons.math3.exception.MaxCountExceededException",
      "org.apache.commons.math3.optimization.ConvergenceChecker",
      "org.apache.commons.math3.exception.util.Localizable",
      "org.apache.commons.math3.optimization.fitting.CurveFitter",
      "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer",
      "org.apache.commons.math3.optimization.fitting.HarmonicFitter",
      "org.apache.commons.math3.exception.util.ExceptionContext",
      "org.apache.commons.math3.exception.NullArgumentException",
      "org.apache.commons.math3.exception.TooManyEvaluationsException",
      "org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser",
      "org.apache.commons.math3.analysis.function.HarmonicOscillator$Parametric",
      "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer",
      "org.apache.commons.math3.analysis.MultivariateMatrixFunction"
    );
  } 

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(HarmonicFitter_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "org.apache.commons.math3.optimization.fitting.CurveFitter",
      "org.apache.commons.math3.optimization.fitting.HarmonicFitter",
      "org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser",
      "org.apache.commons.math3.util.Precision",
      "org.apache.commons.math3.util.Incrementor",
      "org.apache.commons.math3.util.Incrementor$1",
      "org.apache.commons.math3.optimization.fitting.WeightedObservedPoint",
      "org.apache.commons.math3.exception.MathIllegalArgumentException",
      "org.apache.commons.math3.exception.MathIllegalNumberException",
      "org.apache.commons.math3.exception.NumberIsTooSmallException",
      "org.apache.commons.math3.exception.util.LocalizedFormats",
      "org.apache.commons.math3.exception.util.ExceptionContext",
      "org.apache.commons.math3.exception.util.ArgUtils",
      "org.apache.commons.math3.random.BitsStreamGenerator",
      "org.apache.commons.math3.random.AbstractWell",
      "org.apache.commons.math3.random.Well44497a",
      "org.apache.commons.math3.random.GaussianRandomGenerator",
      "org.apache.commons.math3.random.UncorrelatedRandomVectorGenerator",
      "org.apache.commons.math3.optimization.BaseMultivariateVectorMultiStartOptimizer",
      "org.apache.commons.math3.optimization.DifferentiableMultivariateVectorMultiStartOptimizer",
      "org.apache.commons.math3.exception.MathIllegalStateException",
      "org.apache.commons.math3.exception.MaxCountExceededException",
      "org.apache.commons.math3.exception.TooManyEvaluationsException",
      "org.apache.commons.math3.random.Well512a",
      "org.apache.commons.math3.random.RandomAdaptor",
      "org.apache.commons.math3.random.StableRandomGenerator",
      "org.apache.commons.math3.exception.OutOfRangeException",
      "org.apache.commons.math3.exception.NotStrictlyPositiveException",
      "org.apache.commons.math3.linear.RealLinearOperator",
      "org.apache.commons.math3.linear.RealMatrixFormat",
      "org.apache.commons.math3.util.CompositeFormat",
      "org.apache.commons.math3.linear.AbstractRealMatrix",
      "org.apache.commons.math3.linear.Array2DRowRealMatrix",
      "org.apache.commons.math3.random.Well19937a",
      "org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer",
      "org.apache.commons.math3.optimization.general.AbstractLeastSquaresOptimizer",
      "org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer",
      "org.apache.commons.math3.analysis.function.HarmonicOscillator$Parametric",
      "org.apache.commons.math3.optimization.fitting.CurveFitter$TheoreticalValuesFunction",
      "org.apache.commons.math3.optimization.fitting.CurveFitter$TheoreticalValuesFunction$1",
      "org.apache.commons.math3.util.FastMath",
      "org.apache.commons.math3.util.Pair",
      "org.apache.commons.math3.optimization.PointVectorValuePair",
      "org.apache.commons.math3.util.FastMath$CodyWaite"
    );
  }
}
