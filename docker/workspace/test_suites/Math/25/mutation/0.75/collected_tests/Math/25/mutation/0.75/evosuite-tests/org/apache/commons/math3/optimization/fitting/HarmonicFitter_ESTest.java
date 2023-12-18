/*
 * This file was automatically generated by EvoSuite
 * Sat Dec 16 22:29:37 GMT 2023
 */

package org.apache.commons.math3.optimization.fitting;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.apache.commons.math3.optimization.fitting.HarmonicFitter;
import org.apache.commons.math3.optimization.fitting.WeightedObservedPoint;
import org.apache.commons.math3.optimization.general.LevenbergMarquardtOptimizer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class HarmonicFitter_ESTest extends HarmonicFitter_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      double[] doubleArray0 = new double[4];
      harmonicFitter0.fit(doubleArray0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(0.0, (-371.576237), 678.5);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-1648.8), 0.0, 85.0);
      weightedObservedPointArray0[1] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint((-371.576237), (-1648.8), 1.2500000000000009);
      weightedObservedPointArray0[2] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint((-0.002467834632425111), 849.4756791222, 1851.71891);
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      WeightedObservedPoint weightedObservedPoint4 = new WeightedObservedPoint(849.4756791222, 85.0, 1.2500000000000009);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser2.equals((Object)harmonicFitter_ParameterGuesser0));
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-367.839864437), (-371.576237), 678.5);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-1648.8), (-367.839864437), 84.54802531314516);
      weightedObservedPointArray0[1] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint((-371.576237), (-1648.8), 1.2500000000000009);
      weightedObservedPointArray0[2] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(1.2500000000000009, 849.4756791222, 1851.71891);
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
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
  public void test02()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      double[] doubleArray0 = new double[4];
      doubleArray0[0] = (-367.839864437);
      doubleArray0[1] = 85.0;
      doubleArray0[2] = (-367.839864437);
      doubleArray0[3] = (-367.839864437);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-367.839864437), (-371.576237), 678.5);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-1648.8), 85.0, Double.NEGATIVE_INFINITY);
      weightedObservedPointArray0[1] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint((-371.576237), (-1648.8), 1.2500000000000009);
      weightedObservedPointArray0[2] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint((-0.002467834632425111), 849.4756791222, 1851.71891);
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser2.equals((Object)harmonicFitter_ParameterGuesser0));
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      double[] doubleArray0 = new double[8];
      doubleArray0[0] = 1.0;
      doubleArray0[1] = 2159.373073;
      doubleArray0[2] = 0.757156603336549;
      doubleArray0[3] = (-332.0);
      doubleArray0[4] = 1.270000000000001;
      doubleArray0[5] = (-2958.05772597031);
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(337.0, 0.7571887825122858, (-0.49999999999999994));
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[9];
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      weightedObservedPointArray0[2] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-332.0), 3093.1501, (-594.66));
      weightedObservedPointArray0[3] = weightedObservedPoint1;
      weightedObservedPointArray0[4] = weightedObservedPoint0;
      weightedObservedPointArray0[5] = weightedObservedPoint0;
      weightedObservedPointArray0[6] = weightedObservedPoint0;
      weightedObservedPointArray0[7] = weightedObservedPoint0;
      weightedObservedPointArray0[8] = weightedObservedPoint0;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser1.equals((Object)harmonicFitter_ParameterGuesser0));
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[7];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(6325.8675, 849.4756791222, 1.0);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(849.4756791222, 0.29, (-0.103635));
      weightedObservedPointArray0[1] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(0.9400000000000006, 1.0, 0.9400000000000006);
      weightedObservedPointArray0[2] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(0.9400000000000006, 0.9400000000000006, 0.9000000000000006);
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      WeightedObservedPoint weightedObservedPoint4 = new WeightedObservedPoint(849.4756791222, 1.0, 0.9000000000000006);
      weightedObservedPointArray0[4] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint5 = new WeightedObservedPoint(2398.445454, 0.9000000000000006, 0.8400000000000005);
      weightedObservedPointArray0[5] = weightedObservedPoint5;
      WeightedObservedPoint weightedObservedPoint6 = new WeightedObservedPoint(2398.445454, 4041.4, 0.9000000000000006);
      weightedObservedPointArray0[6] = weightedObservedPoint6;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      double[] doubleArray0 = harmonicFitter_ParameterGuesser0.guess();
      assertArrayEquals(new double[] {Double.NaN, Double.NaN, Double.NaN}, doubleArray0, 0.01);
      
      double[] doubleArray1 = harmonicFitter_ParameterGuesser0.guess();
      assertArrayEquals(new double[] {Double.NaN, Double.NaN, Double.NaN}, doubleArray1, 0.01);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[5];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(6325.8675, 849.4756791222, 1.0);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(0.9400000000000006, 1.0, 0.9400000000000006);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      // Undeclared exception!
      try { 
        harmonicFitter_ParameterGuesser0.guess();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      double[] doubleArray0 = new double[4];
      doubleArray0[0] = (-367.839864437);
      doubleArray0[1] = 85.0;
      doubleArray0[2] = (-367.839864437);
      doubleArray0[3] = (-367.839864437);
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(85.0, (-1256.5131611830416), (-367.839864437));
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[8];
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      weightedObservedPointArray0[2] = weightedObservedPoint0;
      weightedObservedPointArray0[3] = weightedObservedPoint0;
      weightedObservedPointArray0[4] = weightedObservedPoint0;
      weightedObservedPointArray0[5] = weightedObservedPoint0;
      weightedObservedPointArray0[6] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(0.5934166937264557, (-6.166460555548122E-5), 85.0);
      weightedObservedPointArray0[7] = weightedObservedPoint1;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser3 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser3.equals((Object)harmonicFitter_ParameterGuesser0));
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-2167.29714313509), (-2167.29714313509), (-2167.29714313509));
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-2167.29714313509), (-1682.66238), 1851.71891);
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(4197.414988387875, (-3.910930846659224E-6), 1150.5);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[4];
      weightedObservedPointArray0[0] = weightedObservedPoint2;
      weightedObservedPointArray0[1] = weightedObservedPoint2;
      weightedObservedPointArray0[2] = weightedObservedPoint0;
      weightedObservedPointArray0[3] = weightedObservedPoint1;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser2.equals((Object)harmonicFitter_ParameterGuesser1));
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[5];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(6325.8675, 849.4756791222, 6325.8675);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(0.9400000000000006, 6325.8675, 0.9400000000000006);
      weightedObservedPointArray0[2] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(0.9400000000000006, 0.9400000000000006, 6.2723202949866295);
      weightedObservedPointArray0[3] = weightedObservedPoint2;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      // Undeclared exception!
      try { 
        harmonicFitter_ParameterGuesser0.guess();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[5];
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      // Undeclared exception!
      try { 
        harmonicFitter_ParameterGuesser0.guess();
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint((-3905.339509), (-2167.29714313509), (-3905.339509));
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint((-3905.339509), (-2167.29714313509), 1.5830993332061267E-10);
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(1.5830993332061267E-10, 3.0, (-966.6406));
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[5];
      weightedObservedPointArray0[0] = weightedObservedPoint1;
      weightedObservedPointArray0[1] = weightedObservedPoint0;
      weightedObservedPointArray0[2] = weightedObservedPoint1;
      weightedObservedPointArray0[3] = weightedObservedPoint0;
      weightedObservedPointArray0[4] = weightedObservedPoint1;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      harmonicFitter_ParameterGuesser0.guess();
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser1 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser2 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      assertFalse(harmonicFitter_ParameterGuesser2.equals((Object)harmonicFitter_ParameterGuesser1));
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      WeightedObservedPoint[] weightedObservedPointArray0 = new WeightedObservedPoint[7];
      WeightedObservedPoint weightedObservedPoint0 = new WeightedObservedPoint(6325.8675, 849.4756791222, 1.0);
      weightedObservedPointArray0[0] = weightedObservedPoint0;
      WeightedObservedPoint weightedObservedPoint1 = new WeightedObservedPoint(849.4756791222, 0.29, (-0.103635));
      weightedObservedPointArray0[1] = weightedObservedPoint1;
      WeightedObservedPoint weightedObservedPoint2 = new WeightedObservedPoint(0.9400000000000006, 1.0, 0.9400000000000006);
      weightedObservedPointArray0[2] = weightedObservedPoint2;
      WeightedObservedPoint weightedObservedPoint3 = new WeightedObservedPoint(0.9400000000000006, 0.9400000000000006, 0.9000000000000006);
      weightedObservedPointArray0[3] = weightedObservedPoint3;
      WeightedObservedPoint weightedObservedPoint4 = new WeightedObservedPoint(849.4756791222, 1.0, 0.9000000000000006);
      weightedObservedPointArray0[4] = weightedObservedPoint4;
      WeightedObservedPoint weightedObservedPoint5 = new WeightedObservedPoint(2398.445454, 0.9000000000000006, 0.8400000000000005);
      weightedObservedPointArray0[5] = weightedObservedPoint5;
      WeightedObservedPoint weightedObservedPoint6 = new WeightedObservedPoint(2398.445454, 337.0, 0.9000000000000006);
      weightedObservedPointArray0[6] = weightedObservedPoint6;
      HarmonicFitter.ParameterGuesser harmonicFitter_ParameterGuesser0 = new HarmonicFitter.ParameterGuesser(weightedObservedPointArray0);
      double[] doubleArray0 = harmonicFitter_ParameterGuesser0.guess();
      assertArrayEquals(new double[] {Double.NaN, Double.NaN, Double.NaN}, doubleArray0, 0.01);
  }

  @Test(timeout = 4000)
  public void testPreconditions1testPreconditions1_12()  throws Throwable  {
      LevenbergMarquardtOptimizer levenbergMarquardtOptimizer0 = new LevenbergMarquardtOptimizer();
      HarmonicFitter harmonicFitter0 = new HarmonicFitter(levenbergMarquardtOptimizer0);
      double[] doubleArray0 = new double[4];
      doubleArray0[0] = (-367.839864437);
      doubleArray0[1] = 85.0;
      doubleArray0[2] = (-367.839864437);
      doubleArray0[3] = (-367.839864437);
      harmonicFitter0.fit(doubleArray0);
      harmonicFitter0.addObservedPoint((-367.839864437), (-367.839864437));
      // Undeclared exception!
      try { 
        harmonicFitter0.fit();
        fail("Expecting exception: IllegalArgumentException");
      
      } catch(IllegalArgumentException e) {
         //
         // sample contains 1 observed points, at least 4 are required
         //
         verifyException("org.apache.commons.math3.optimization.fitting.HarmonicFitter$ParameterGuesser", e);
      }
  }

  @Test(timeout = 4000)
  public void testPreconditions1_13()  throws Throwable  {
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
