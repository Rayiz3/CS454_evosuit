/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 22:31:49 GMT 2023
 */

package org.apache.commons.math3.optimization.fitting;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math3.analysis.function.HarmonicOscillator;
import org.apache.commons.math3.optimization.fitting.HarmonicFitter;
import org.apache.commons.math3.optimization.fitting.WeightedObservedPoint;
import org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.System;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class HarmonicFitter_ESTest extends HarmonicFitter_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-2307.0), (-2307.0), (-2307.0));
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(0.9600000000000006, 505.9385733121, 1088.0);
      weightedObservedPointArray0[2] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(1088.0, 0.9600000000000006, 1088.0);
      weightedObservedPointArray0[3] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(545.03714976953, 0.9600000000000006, (-2018.687));
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      double[] doubleArray0 = harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter harmonicFitter1 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      harmonicFitter0.fit(doubleArray0);
      HarmonicFitter harmonicFitter2 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      harmonicFitter2.fit(doubleArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser3 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser4 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser5 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      System.setCurrentTimeMillis(0L);
      System.setCurrentTimeMillis(0L);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-4.068233003401932E-9), 0.9600000000000006, 2759.4522261928423);
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-712.92), (-416.14), 0.9600000000000006);
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint((-1388.31977170703), 2003.1, 267.5955995);
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint((-1388.31977170703), (-712.92), 545.03714976953);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[6];
      weightedObservedPointArray0[0] = weightedObservedPoint3;
      weightedObservedPointArray0[1] = weightedObservedPoint2;
      weightedObservedPointArray0[2] = weightedObservedPoint0;
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      weightedObservedPointArray0[4] = weightedObservedPoint0;
      weightedObservedPointArray0[5] = weightedObservedPoint1;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser1.guess();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser2.equals((Object)harmonicFitter_ParameterGuesser0));
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(0.9600000000000006, 1.2000000000000008, 1656.357563563703);
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(0.672, 0.9600000000000006, 97.0);
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(0.9600000000000006, (-1698.7644590054426), (-372.9886415));
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint((-593.08852028), (-169297.1247824827), 2192.1033834);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[5];
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint4 = new WeightedObservedPoint((-2.6033824355191673E-8), (-2397.189012), 97.0);
      weightedObservedPointArray0[1] = weightedObservedPoint4;
      weightedObservedPointArray0[2] = weightedObservedPoint3;
      weightedObservedPointArray0[3] = weightedObservedPoint2;
      weightedObservedPointArray0[4] = weightedObservedPoint1;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      // Undeclared exception!
      try { 
        harmonicFitter0.fit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // sample contains 0 observed points, at least 4 are required
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      double[] doubleArray0 = new double[4];
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[5];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-2307.0), (-2307.0), (-2307.0));
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-2307.0), (-2307.0), 0.9600000000000006);
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(0.9600000000000006, 505.9385733121, 1088.0);
      weightedObservedPointArray0[2] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(1088.0, 0.9600000000000006, 1088.0);
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      WeightedObservedPoint weightedObservedPoint4 = new WeightedObservedPoint(545.0371498, 1.2500000000000009, (-2018.687));
      weightedObservedPointArray0[4] = weightedObservedPoint4;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      double[] doubleArray1 = harmonicFitter_ParameterGuesser0.guess();
      doubleArray0[0] = 1.0300000000000007;
      HarmonicOscillator.Parametric harmonicOscillator_Parametric0 = new HarmonicOscillator.Parametric();
      harmonicFitter_ParameterGuesser0.guess();
      harmonicFitter0.fit(1800, harmonicOscillator_Parametric0, doubleArray1);
      HarmonicOscillator.Parametric harmonicOscillator_Parametric1 = new HarmonicOscillator.Parametric();
      HarmonicFitter harmonicFitter1 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      harmonicFitter1.fit(1800, harmonicOscillator_Parametric1, doubleArray0);
      harmonicFitter1.fit(doubleArray1);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser2.equals((Object)harmonicFitter_ParameterGuesser1));
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-4.068233003401932E-9), 0.9600000000000006, 2759.4522261928423);
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-712.92), (-416.14), 0.9600000000000006);
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint((-1388.31977170703), 2003.1, 267.5955995);
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint((-1388.31977170703), (-712.92), 545.03714976953);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[6];
      weightedObservedPointArray0[0] = weightedObservedPoint3;
      weightedObservedPointArray0[1] = weightedObservedPoint2;
      weightedObservedPointArray0[2] = weightedObservedPoint0;
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      weightedObservedPointArray0[4] = weightedObservedPoint0;
      weightedObservedPointArray0[5] = weightedObservedPoint1;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      double[] doubleArray0 = harmonicFitter_ParameterGuesser1.guess();
      HarmonicOscillator.Parametric harmonicOscillator_Parametric0 = new HarmonicOscillator.Parametric();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      // Undeclared exception!
      try { 
        harmonicFitter0.fit((-788), harmonicOscillator_Parametric0, doubleArray0);
        fail("Expecting exception: IllegalStateException");
      
      } catch(IllegalStateException e) {
         //
         // illegal state: maximal count (-788) exceeded: evaluations
         //
         verifyException("org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer", e);
      }
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-2304.7266822986535), (-2304.7266822986535), (-2304.7266822986535));
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(13.047361859345823, 505.9385733121, 1088.0);
      weightedObservedPointArray0[2] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(1088.0, 13.047361859345823, 1088.0);
      weightedObservedPointArray0[3] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(1088.0, 13.047361859345823, (-2018.687));
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser1.guess();
      // Undeclared exception!
      try { 
        harmonicFitter0.fit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // sample contains 0 observed points, at least 4 are required
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void testPreconditions1testPreconditions1_06()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      double[] doubleArray0 = new double[4];
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[5];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-2307.0), (-2307.0), (-2307.0));
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-2307.0), (-2307.0), 0.9600000000000006);
      weightedObservedPointArray0[1] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(0.9600000000000006, 505.9385733121, 1088.0);
      weightedObservedPointArray0[2] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(1088.0, 0.9600000000000006, 1088.0);
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      WeightedObservedPoint weightedObservedPoint4 = new WeightedObservedPoint(545.03714976953, 1.2500000000000009, (-2018.687));
      weightedObservedPointArray0[4] = weightedObservedPoint4;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      double[] doubleArray1 = harmonicFitter_ParameterGuesser0.guess();
      doubleArray0[0] = 1.0300000000000007;
      HarmonicOscillator.Parametric harmonicOscillator_Parametric0 = new HarmonicOscillator.Parametric();
      harmonicFitter0.fit(1800, harmonicOscillator_Parametric0, doubleArray1);
      doubleArray0[1] = 442.61798565;
      doubleArray0[2] = (-1221.61308002);
      doubleArray0[3] = (-1.0);
      harmonicFitter0.fit(doubleArray0);
      // Undeclared exception!
      try { 
        harmonicFitter0.fit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // sample contains 0 observed points, at least 4 are required
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(1539.63597, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(2714.492029226, 2714.492029226, 2714.492029226);
      weightedObservedPointArray0[1] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(1539.63597, 1539.63597, 1539.63597);
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(2714.492029226, 2714.492029226, 1539.63597);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      // Undeclared exception!
      try { 
        harmonicFitter_ParameterGuesser0.guess();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(1539.63597, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(2714.492029226, 3.58378458873621, 3.58378458873621);
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(1539.63597, 1539.63597, 1539.63597);
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(2714.492029226, 2714.492029226, 1539.63597);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      // Undeclared exception!
      try { 
        harmonicFitter_ParameterGuesser0.guess();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(1.280000000000001, 1.280000000000001, 1.280000000000001);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[6];
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      weightedObservedPointArray0[2] = weightedObservedPoint0;
      weightedObservedPointArray0[3] = weightedObservedPoint0;
      weightedObservedPointArray0[4] = weightedObservedPoint0;
      weightedObservedPointArray0[5] = weightedObservedPoint0;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-2307.0), (-2307.0), (-2307.0));
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      weightedObservedPointArray0[2] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(1088.0, (-2307.0), 1088.0);
      weightedObservedPointArray0[3] = weightedObservedPoint1;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      double[] doubleArray0 = harmonicFitter_ParameterGuesser0.guess();
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter harmonicFitter1 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      harmonicFitter1.fit(doubleArray0);
      // Undeclared exception!
      try { 
        harmonicFitter1.fit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // sample contains 0 observed points, at least 4 are required
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void testPreconditions1_11()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      // Undeclared exception!
      try { 
        harmonicFitter0.fit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // sample contains 0 observed points, at least 4 are required
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }
}
