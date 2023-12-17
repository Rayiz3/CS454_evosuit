package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    // Regression test 1: Negative q0
    Rotation r1 = new Rotation(-0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted1 = r1.revert();
    checkRotation(r1.applyTo(reverted1), 1, 0, 0, 0);
    checkRotation(reverted1.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted1.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted1.getAxis()), 1.0e-12);
    
    // Regression test 2: Different q1 value
    Rotation r2 = new Rotation(0.001, 0.5, 0.48, 0.8, true);
    Rotation reverted2 = r2.revert();
    checkRotation(r2.applyTo(reverted2), 1, 0, 0, 0);
    checkRotation(reverted2.applyTo(r2), 1, 0, 0, 0);
    Assert.assertEquals(r2.getAngle(), reverted2.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r2.getAxis(), reverted2.getAxis()), 1.0e-12);
    
    // Regression test 3: Different q2 value
    Rotation r3 = new Rotation(0.001, 0.36, 0.6, 0.8, true);
    Rotation reverted3 = r3.revert();
    checkRotation(r3.applyTo(reverted3), 1, 0, 0, 0);
    checkRotation(reverted3.applyTo(r3), 1, 0, 0, 0);
    Assert.assertEquals(r3.getAngle(), reverted3.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r3.getAxis(), reverted3.getAxis()), 1.0e-12);
    
    // Regression test 4: Different q3 value
    Rotation r4 = new Rotation(0.001, 0.36, 0.48, 0.9, true);
    Rotation reverted4 = r4.revert();
    checkRotation(r4.applyTo(reverted4), 1, 0, 0, 0);
    checkRotation(reverted4.applyTo(r4), 1, 0, 0, 0);
    Assert.assertEquals(r4.getAngle(), reverted4.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r4.getAxis(), reverted4.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test2(){
      Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                                 -5774608829631843.0 /  268435456.0,
                                 -3822921525525679.0 / 4294967296.0);
      Vector3D u2 =new Vector3D( -5712344449280879.0 /    2097152.0,
                                 -2275058564560979.0 /    1048576.0,
                                  4423475992255071.0 /      65536.0);
      Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
      Assert.assertEquals( 0.6228370359608200639829222, rot.getQ0(), 1.0e-15);
      Assert.assertEquals( 0.0257707621456498790029987, rot.getQ1(), 1.0e-15);
      Assert.assertEquals(-1.0, rot.getQ2(), 1.0e-15);
      Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);
  }
  @Test
  public void test3() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, true);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test4(){
      Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                                 -5774608829631843.0 /  268435456.0,
                                 -3822921525525679.0 / 4294967296.0);
      Vector3D u2 =new Vector3D( -5712344449280879.0 /    2097152.0,
                                 -2275058564560979.0 /    1048576.0,
                                  4423475992255071.0 /      65536.0);
      Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
      Assert.assertEquals( 0.6228370359608200639829222, rot.getQ0(), 1.0e-15);
      Assert.assertEquals( 0.0257707621456498790029987, rot.getQ1(), 1.0e-15);
      Assert.assertEquals(-0.0000000002503012255839931, rot.getQ2(), 1.0e-15);
      Assert.assertEquals( 1.0, rot.getQ3(), 1.0e-15);
  }
@Test
public void test5() {

  Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
  double n = 23.5;
  Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                             n * r1.getQ2(), n * r1.getQ3(),
                             true);
  for (double x = -0.9; x < 0.9; x += 0.2) {
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }
  
  // Additional test case with a larger value of n
  n = 100.0;
  r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                             n * r1.getQ2(), n * r1.getQ3(),
                             true);
  
  for (double x = -0.9; x < 0.9; x += 0.2) {
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }
  
}
@Test
public void test6() {

  Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
  double n = 23.5;
  Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                             n * r1.getQ2(), n * r1.getQ3(),
                             true);
                             
  // Test case with negative values of x, y, and z
  for (double x = -0.9; x > -2.0; x -= 0.2) {
    for (double y = -0.9; y > -2.0; y -= 0.2) {
      for (double z = -0.9; z > -2.0; z -= 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }
  
}
  @Test
  public void test7() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = -23.5; // changed input value to negative
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test8() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 0.5; // changed input value to a positive value
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test9() {

    // First test case 
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Second test case 
    r1 = new Rotation(new Vector3D(4, -7, 2), 0.5);
    n = 10.2;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -2.0; x < 2.0; x += 0.5) {
      for (double y = -2.0; y < 2.0; y += 0.5) {
        for (double z = -2.0; z < 2.0; z += 0.5) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Third test case 
    r1 = new Rotation(new Vector3D(1, 1, 1), 2.0);
    n = 5.0;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -1.0; x < 1.0; x += 0.2) {
      for (double y = -1.0; y < 1.0; y += 0.2) {
        for (double z = -1.0; z < 1.0; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    
    // Fourth test case
    checkRotation(r1, 0.5 * r1.getQ0(), 0.5 * r1.getQ1(), 0.5 * r1.getQ2(), 0.5 * r1.getQ3());
    
    // Fifth test case
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
@Test
public void test10() {
    Rotation r = new Rotation(new Vector3D(1, 1, 1), 0);
    checkVector(r.getAxis(), Vector3D.PLUS_I);
}
@Test
public void test11() {
    Rotation r = new Rotation(new Vector3D(-5, 5, -5), FastMath.PI);
    checkVector(r.getAxis(), Vector3D.MINUS_J);
}
@Test
public void test12() {
    Rotation r = new Rotation(new Vector3D(-2, -2, -2), -FastMath.PI);
    checkVector(r.getAxis(), Vector3D.MINUS_K);
}
@Test
public void test13() {
    Rotation r = new Rotation(new Vector3D(0, 0, 0), 0);
    checkVector(r.getAxis(), Vector3D.PLUS_I);
}
@Test
public void test14() {
    Rotation r = new Rotation(Vector3D.PLUS_K, 1);
    checkVector(r.getAxis(), Vector3D.PLUS_K);
}
@Test
public void test15() {
    Rotation r = new Rotation(Vector3D.MINUS_I, -1.5 * FastMath.PI);
    checkVector(r.getAxis(), Vector3D.MINUS_J);
}
@Test
public void test16() {
    Rotation r = new Rotation(Vector3D.MINUS_J, 0.5 * FastMath.PI);
    checkVector(r.getAxis(), Vector3D.PLUS_I);
}
@Test
public void test17() {
    Rotation r = new Rotation(Vector3D.PLUS_I, FastMath.PI);
    checkVector(r.getAxis(), Vector3D.MINUS_I);
}
@Test
public void test18() {
    Rotation r = new Rotation(0.5, 0.5, 0.5, 0, true);
    checkVector(r.getAxis(), new Vector3D(0.57735, 0.57735, 0.57735));
}
@Test
public void test19() {
    Rotation r = new Rotation(0, 0, 0, 1, true);
    checkVector(r.getAxis(), new Vector3D(-1, 0, 0));
}
  @Test
  public void test20() {
    // Test case 1
    Rotation r1 = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    double angle1 = r1.getAngle();
    Assert.assertEquals(1.51199681519787, angle1, 1.0e-10);
    
    // Test case 2
    Rotation r2 = new Rotation(0.5, 0.5, -0.5, 0.5, true);
    double angle2 = r2.getAngle();
    Assert.assertEquals(3.141592653589793, angle2, 1.0e-10);
    
    // Test case 3
    Rotation r3 = new Rotation(0.8, 0.288, 0.384, 0.36, false);
    double angle3 = r3.getAngle();
    Assert.assertEquals(0.6615941707986146, angle3, 1.0e-10);
  }
@Test
  public void test21() {

    // Regression test 1
    // Change the value of singularCardanAngle from -PI/2 to PI/2
    double[] singularCardanAngle = { FastMath.PI / 2, FastMath.PI / 2 };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.1, singularCardanAngle[j], 0.3);
        try {
          r.getAngles(CardanOrders[i]);
          Assert.fail("an exception should have been caught");
        } catch (CardanEulerSingularityException cese) {
          // expected behavior
        }
      }
    }

    // Regression test 2
    // Change the value of singularEulerAngle from PI to -PI
    double[] singularEulerAngle = { 0, -FastMath.PI };
    for (int i = 0; i < EulerOrders.length; ++i) {
      for (int j = 0; j < singularEulerAngle.length; ++j) {
        Rotation r = new Rotation(EulerOrders[i], 0.1, singularEulerAngle[j], 0.3);
        try {
          r.getAngles(EulerOrders[i]);
          Assert.fail("an exception should have been caught");
        } catch (CardanEulerSingularityException cese) {
          // expected behavior
        }
      }
    }
  }
  @Test
    throws CardanEulerSingularityException {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = -1.55; alpha2 < 1.55; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            // Regression test 3
            // Change the value of alpha1 from 0.1 to 0.5
            Rotation r = new Rotation(CardanOrders[i], 0.5, alpha2, alpha3);
            double[] angles = r.getAngles(CardanOrders[i]);
            checkAngle(angles[0], alpha1);
            checkAngle(angles[1], alpha2);
            checkAngle(angles[2], alpha3);
          }
        }
      }
    }

    RotationOrder[] EulerOrders = {
      RotationOrder.XYX, RotationOrder.XZX, RotationOrder.YXY,
      RotationOrder.YZY, RotationOrder.ZXZ, RotationOrder.ZYZ
    };

    for (int i = 0; i < EulerOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = 0.05; alpha2 < 3.1; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            // Regression test 4
            // Change the value of alpha2 from 0.05 to 0.15
            Rotation r = new Rotation(EulerOrders[i], alpha1, 0.15, alpha3);
            double[] angles = r.getAngles(EulerOrders[i]);
            checkAngle(angles[0], alpha1);
            checkAngle(angles[1], alpha2);
            checkAngle(angles[2], alpha3);
          }
        }
      }
    }
  }
  checkRotation(new Rotation(new double[][] {
                                {  0.0,  1.0,  0.0 },
                                {  1.0,  0.0,  0.0 },
                                {  0.0,  0.0,  1.0 }
                              }, 1.0e-7),
  double[][] m6 = { { -1.0,  0.0,  0.0 },
                    {  0.0, -1.0,  0.0 },
                    {  0.0,  0.0, -1.0 } };
  double[][] m7 = { {  0.7071,  0.7071,  0.0 },
                    { -0.7071,  0.7071,  0.0 },
                    {  0.0,     0.0,     1.0 } };
  double[][] m8 = { {  0.8660, -0.5,    0.0 },
                    {  0.5,     0.8660, 0.0 },
                    {  0.0,     0.0,     1.0 } };
  @Test
    throws NotARotationMatrixException {

    try {
      new Rotation(new double[][] {
                     { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("NotARotationMatrixException not thrown");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
                     {  0.445888,  0.797184, -0.407040 },
                     {  0.821760, -0.184320,  0.539200 },
                     { -0.354816,  0.574912,  0.737280 }
                   }, 1.0e-7);
      Assert.fail("NotARotationMatrixException not thrown");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
        new Rotation(new double[][] {
                       {  0.4,  0.8, -0.4 },
                       { -0.4,  0.6,  0.7 },
                       {  0.8, -0.2,  0.5 }
                     }, 1.0e-15);
        Assert.fail("NotARotationMatrixException not thrown");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);

    checkRotation(new Rotation(new double[][] {
                                 {  0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-10),
                  0.36, 0.8, 0.288, 0.384);

    checkRotation(new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10),
                  0.384, 0.36, 0.8, 0.288);

    checkRotation(new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 { -0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10),
                  0.288, 0.384, 0.36, 0.8);

    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 0.0, 0.0, 1.0 },
                      { 1.0, 0.0, 0.0 } };
    Rotation r = new Rotation(m1, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);

    double[][] m2 = { { 0.83203, -0.55012, -0.07139 },
                      { 0.48293,  0.78164, -0.39474 },
                      { 0.27296,  0.29396,  0.91602 } };
    r = new Rotation(m2, 1.0e-12);

    double[][] m3 = r.getMatrix();
    double d00 = m2[0][0] - m3[0][0];
    double d01 = m2[0][1] - m3[0][1];
    double d02 = m2[0][2] - m3[0][2];
    double d10 = m2[1][0] - m3[1][0];
    double d11 = m2[1][1] - m3[1][1];
    double d12 = m2[1][2] - m3[1][2];
    double d20 = m2[2][0] - m3[2][0];
    double d21 = m2[2][1] - m3[2][1];
    double d22 = m2[2][2] - m3[2][2];

    Assert.assertTrue(FastMath.abs(d00) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d01) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d02) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d10) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d11) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d12) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d20) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d21) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d22) < 6.0e-6);

    Assert.assertTrue(FastMath.abs(d00) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d01) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d02) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d10) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d11) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d12) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d20) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d21) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d22) > 4.0e-7);

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        double m3tm3 = m3[i][0] * m3[j][0]
                     + m3[i][1] * m3[j][1]
                     + m3[i][2] * m3[j][2];
        if (i == j) {
          Assert.assertTrue(FastMath.abs(m3tm3 - 1.0) < 1.0e-10);
        } else {
          Assert.assertTrue(FastMath.abs(m3tm3) < 1.0e-10);
        }
      }
    }

    checkVector(r.applyTo(Vector3D.PLUS_I),
                new Vector3D(m3[0][0], m3[1][0], m3[2][0]));
    checkVector(r.applyTo(Vector3D.PLUS_J),
                new Vector3D(m3[0][1], m3[1][1], m3[2][1]));
    checkVector(r.applyTo(Vector3D.PLUS_K),
                new Vector3D(m3[0][2], m3[1][2], m3[2][2]));

    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 } };
    r = new Rotation(m4, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI);

    try {
      double[][] m5 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m5, 1.0e-7);
      Assert.fail("IllegalArgumentException not thrown");
    } catch (NotARotationMatrixException e) {
      // expected behavior
    }

  }
  @Test
  public void test22() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyTo(r1);

    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

    double x = -0.83; // change the value of x
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(r1.applyTo(u)), r3.applyTo(u));
      }
    }

  }
  @Test
  public void test23() {

    Rotation r = Rotation.IDENTITY;
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), 0);

    r = new Rotation(-1, 0, 0, 0, false);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), 0);

    r = new Rotation(42, 0, 0, 0, true);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), 0);

  }
  @Test
  public void test24() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    double x = -0.83; // change the value of x
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test25() {

    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    r = new Rotation(u1, u2, u1.negate(), u2.negate());
    Vector3D axis = r.getAxis();
    if (Vector3D.dotProduct(axis, Vector3D.PLUS_K) > 0) {
      checkVector(axis, Vector3D.PLUS_K);
    } else {
      checkVector(axis, Vector3D.MINUS_K);
    }
    checkAngle(r.getAngle(), FastMath.PI);

    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }

  }
  @Test
  public void test26() {

    Vector3D u = new Vector3D(3, 2, 1);
    Vector3D v = new Vector3D(-4, 2, 2);
    Rotation r = new Rotation(u, v);
    checkVector(r.applyTo(u.scalarMultiply(v.getNorm())), v.scalarMultiply(u.getNorm()));

    checkAngle(new Rotation(u, u.negate()).getAngle(), FastMath.PI);

    try {
        new Rotation(u, Vector3D.ZERO);
        Assert.fail("IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }

  }
  @Test
  public void test27() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          r.applyInverseTo(r.applyTo(u));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }

    r = Rotation.IDENTITY;
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }

    r = new Rotation(Vector3D.PLUS_K, FastMath.PI);
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }

  }
  @Test
  public void test28() {

    Rotation r = new Rotation(new Vector3D(10, 10, 10), 2 * FastMath.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), 2 * FastMath.PI / 3);

    try {
      new Rotation(new Vector3D(0, 0, 0), 2 * FastMath.PI / 3);
      Assert.fail("IllegalArgumentException not thrown");
    } catch (ArithmeticException e) {
    }

    r = new Rotation(Vector3D.PLUS_K, 1.5 * FastMath.PI);
    checkVector(r.getAxis(), new Vector3D(0, 0, -1));
    checkAngle(r.getAngle(), 0.5 * FastMath.PI);

    r = new Rotation(Vector3D.PLUS_J, FastMath.PI);
    checkVector(r.getAxis(), Vector3D.PLUS_J);
    checkAngle(r.getAngle(), FastMath.PI);

    checkVector(Rotation.IDENTITY.getAxis(), Vector3D.PLUS_I);

  }
  @Test
  public void test29() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test30() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngle = { FastMath.PI / 2, -FastMath.PI / 2 };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.1, singularCardanAngle[j], 0.3);
        try {
          r.getAngles(CardanOrders[i]);
          Assert.fail("CardanEulerSingularityException not thrown");
        } catch (CardanEulerSingularityException cese) {
          // expected behavior
        }
      }
    }

    RotationOrder[] EulerOrders = {
            RotationOrder.XYX, RotationOrder.XZX, RotationOrder.YXY,
            RotationOrder.YZY, RotationOrder.ZXZ, RotationOrder.ZYZ
          };

    double[] singularEulerAngle = { 0, FastMath.PI };
    for (int i = 0; i < EulerOrders.length; ++i) {
      for (int j = 0; j < singularEulerAngle.length; ++j) {
        Rotation r = new Rotation(EulerOrders[i], 0.1, singularEulerAngle[j], 0.3);
        try {
          r.getAngles(EulerOrders[i]);
          Assert.fail("CardanEulerSingularityException not thrown");
        } catch (CardanEulerSingularityException cese) {
          // expected behavior
        }
      }
    }
  }
  @Test
    throws CardanEulerSingularityException {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = -1.55; alpha2 < 1.55; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            Rotation r = new Rotation(CardanOrders[i], alpha1, alpha2, alpha3);
            double[] angles = r.getAngles(CardanOrders[i]);
            checkAngle(angles[0], alpha1);
            checkAngle(angles[1], alpha2);
            checkAngle(angles[2], alpha3);
          }
        }
      }
    }

    RotationOrder[] EulerOrders = {
            RotationOrder.XYX, RotationOrder.XZX, RotationOrder.YXY,
            RotationOrder.YZY, RotationOrder.ZXZ, RotationOrder.ZYZ
          };

    for (int i = 0; i < EulerOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = 0.05; alpha2 < 3.1; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            Rotation r = new Rotation(EulerOrders[i],
                                      alpha1, alpha2, alpha3);
            double[] angles = r.getAngles(EulerOrders[i]);
            checkAngle(angles[0], alpha1);
            checkAngle(angles[1], alpha2);
            checkAngle(angles[2], alpha3);
          }
        }
      }
    }
  }
  @Test
  public void test31() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

    double x = -0.83; // change the value of x
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
      }
    }

  }
  @Test
    throws NotARotationMatrixException {
    
    ...
    
    // Regression test case 1
    checkRotation(new Rotation(new double[][] {
                                 {  0.2,  0.1, -0.5 },
                                 {  0.3, -0.2,  0.4 },
                                 { -0.1,  0.6,  0.7 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);
    
    // Regression test case 2
    checkRotation(new Rotation(new double[][] {
                                 {  0.591715,  0.795392, -0.13592 },
                                 { -0.098668,  0.384184,  0.918876 },
                                 {  0.79942,  -0.468072,  0.377157 }
                               }, 1.0e-10),
                  0.36, 0.8, 0.288, 0.384);

    ...
    
  }
  @Test
  public void test32() {
  
    ...
    
    // Regression test case 1
    r1 = new Rotation( 0.4,  0.8,  -0.4,  0.6, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    
    // Regression test case 2
    r1 = new Rotation(-0.6,  0.3,  -0.2,  0.7, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    
    ...
    
  }
  @Test
  public void test33() {
  
    ...
  
    // Regression test case 1
    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
    
    // Regression test case 2
    u1 = new Vector3D(1, 1, 0);
    u2 = new Vector3D(-1, 1, 0);
    v1 = new Vector3D(-2, -2, 0);
    v2 = new Vector3D(-2, -2, 0);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    
    ...
    
  }
  @Test
  public void test34() {
  
    ...
  
    // Regression test case 1
    r = new Rotation(Vector3D.PLUS_J, 1.5 * FastMath.PI);
    r.applyInverseTo(r.applyTo(u));
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
    
    // Regression test case 2
    r = new Rotation(Vector3D.PLUS_I, 0.5 * FastMath.PI);
    r.applyInverseTo(r.applyTo(u));
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
    
    ...
    
  }
  @Test
  public void test35() {
    ...
    
    // Regression test case 1
    r1 = new Rotation(-0.36, -0.48, -0.2, -0.8, true);
    reverted = r1.revert();
    checkRotation(r1.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted.getAxis()), 1.0e-12);
    
    // Regression test case 2
    r1 = new Rotation(0.6, -0.3, 0.2, -0.7, true);
    reverted = r1.revert();
    checkRotation(r1.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted.getAxis()), 1.0e-12);
    
    ...
    
  }
  @Test
    throws CardanEulerSingularityException {
    
    ...
    
    // Regression test case 1
    double alpha1 = 1.2;
    double alpha2 = -0.5;
    double alpha3 = 2.3;
    Rotation r = new Rotation(CardanOrders[i], alpha1, alpha2, alpha3);
    double[] angles = r.getAngles(CardanOrders[i]);
    checkAngle(angles[0], alpha1);
    checkAngle(angles[1], alpha2);
    checkAngle(angles[2], alpha3);
    
    // Regression test case 2
    alpha1 = 0.8;
    alpha2 = 0.5;
    alpha3 = 3.6;
    r = new Rotation(EulerOrders[i], alpha1, alpha2, alpha3);
    angles = r.getAngles(EulerOrders[i]);
    checkAngle(angles[0], alpha1);
    checkAngle(angles[1], alpha2);
    checkAngle(angles[2], alpha3);
    
    ...
    
  }
  @Test
  public void test36() {
  
    ...
  
    // Regression test case 1
    r1 = new Rotation(new Vector3D(0.4, 0.7, -0.1), 1.2);
    r2 = new Rotation(new Vector3D(-0.6, 0.2, -0.3), 0.5);
    r3 = r2.applyInverseTo(r1);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
    
    // Regression test case 2
    r1 = new Rotation(new Vector3D(2, 0.5, -1), 0.8);
    r2 = new Rotation(new Vector3D(-1, 3, 0.5), 0.3);
    r3 = r2.applyInverseTo(r1);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
    
    ...
    
  }
  @Test
  public void test37() {  
    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  0.5, 0.5, 0.5, 0.5);
  }
  @Test
  public void test38() {  
    checkRotation(new Rotation(new double[][] {
                                 {  0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-10),
                  0.6, 0.6, 0.6, 0.6);
  }
  @Test
  public void test39() {  
    checkRotation(new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10),
                  0.8, 0.8, 0.8, 0.8);
  }
  @Test
  public void test40() {  
    checkRotation(new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 { -0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10),
                  0.3, 0.3, 0.3, 0.3);
  }
  @Test
  public void test41() {  
    checkRotation(new Rotation(new double[][] {
                                 { 0.445888,  0.797184, -0.407040 },
                                 { 0.821760, -0.184320,  0.539200 },
                                 {-0.354816,  0.574912,  0.737280 }
                              }, 1.0e-10),
                  0.2, 0.2, 0.2, 0.2);
  }
  @Test
  public void test42() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test43() {
    Rotation r1 = new Rotation(new Vector3D(4, -2, 3), 2.2);
    Rotation r2 = new Rotation(new Vector3D(1, -2, -3), 0.7);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -1.5; x < 1.5; x += 0.2) {
      for (double y = -1.5; y < 1.5; y += 0.2) {
        for (double z = -1.5; z < 1.5; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test44() {
    Rotation r1 = new Rotation(new Vector3D(-1, 2, -3), 1.2);
    Rotation r2 = new Rotation(new Vector3D(-4, 2, -1), 1.3);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -1.5; x < 1.5; x += 0.2) {
      for (double y = -1.5; y < 1.5; y += 0.2) {
        for (double z = -1.5; z < 1.5; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test45() {
    Rotation r1 = new Rotation(new Vector3D(3, -1, 2), 1.8);
    Rotation r2 = new Rotation(new Vector3D(2, 1, -3), 0.9);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -1.5; x < 1.5; x += 0.2) {
      for (double y = -1.5; y < 1.5; y += 0.2) {
        for (double z = -1.5; z < 1.5; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test46() {
    Rotation r1 = new Rotation(new Vector3D(-1, -4, 2), 0.5);
    Rotation r2 = new Rotation(new Vector3D(-3, -2, 1), 2.1);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -1.5; x < 1.5; x += 0.2) {
      for (double y = -1.5; y < 1.5; y += 0.2) {
        for (double z = -1.5; z < 1.5; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
  }
@Test
public void test47() {
  Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
  Rotation r2 = new Rotation(-r1.q0 * 0 - (r1.q1 * r1.q1 + r1.q2 * r1.q2 + r1.q3 * r1.q3),
                             -r1.q1 * 0 + r1.q0 * 0 + (r1.q2 * r1.q3 - r1.q3 * r1.q2),
                             -r1.q2 * 0 + r1.q0 * 0 + (r1.q3 * r1.q1 - r1.q1 * r1.q3),
                             -r1.q3 * 0 + r1.q0 * 0 + (r1.q1 * r1.q2 - r1.q2 * r1.q1),
                             false);
  Assert.assertEquals(-r1.q0 * 0 - (r1.q1 * r1.q1 + r1.q2 * r1.q2 + r1.q3 * r1.q3), r2.q0, 0.0);
  Assert.assertEquals(-r1.q1 * 0 + r1.q0 * 0 + (r1.q2 * r1.q3 - r1.q3 * r1.q2), r2.q1, 0.0);
  Assert.assertEquals(-r1.q2 * 0 + r1.q0 * 0 + (r1.q3 * r1.q1 - r1.q1 * r1.q3), r2.q2, 0.0);
  Assert.assertEquals(-r1.q3 * 0 + r1.q0 * 0 + (r1.q1 * r1.q2 - r1.q2 * r1.q1), r2.q3, 0.0);
  Assert.assertFalse(r2.reversed);
}
@Test
public void test48() {
  Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
  Rotation r2 = new Rotation(-r1.q0 * r1.q0 - (0 * r1.q1 + r1.q2 * r1.q2 + r1.q3 * r1.q3),
                             -0 * r1.q0 + r1.q0 * r1.q1 + (r1.q2 * r1.q3 - r1.q3 * r1.q2),
                             -r1.q2 * r1.q0 + r1.q0 * 0 + (r1.q3 * r1.q1 - r1.q1 * r1.q3),
                             -r1.q3 * r1.q0 + r1.q0 * 0 + (r1.q1 * r1.q2 - r1.q2 * r1.q1),
                             false);
  Assert.assertEquals(-r1.q0 * r1.q0 - (0 * r1.q1 + r1.q2 * r1.q2 + r1.q3 * r1.q3), r2.q0, 0.0);
  Assert.assertEquals(-0 * r1.q0 + r1.q0 * r1.q1 + (r1.q2 * r1.q3 - r1.q3 * r1.q2), r2.q1, 0.0);
  Assert.assertEquals(-r1.q2 * r1.q0 + r1.q0 * 0 + (r1.q3 * r1.q1 - r1.q1 * r1.q3), r2.q2, 0.0);
  Assert.assertEquals(-r1.q3 * r1.q0 + r1.q0 * 0 + (r1.q1 * r1.q2 - r1.q2 * r1.q1), r2.q3, 0.0);
  Assert.assertFalse(r2.reversed);
}
@Test
public void test49() {
  Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
  Rotation r2 = new Rotation(-r1.q0 * r1.q0 - (r1.q1 * r1.q1 + 0 * r1.q2 + r1.q3 * r1.q3),
                             -r1.q1 * r1.q0 + r1.q0 * r1.q1 + (0 * r1.q3 - r1.q3 * 0),
                             -0 * r1.q0 + r1.q0 * r1.q2 + (r1.q3 * r1.q1 - r1.q1 * r1.q3),
                             -r1.q3 * r1.q0 + r1.q0 * 0 + (r1.q1 * 0 - 0 * r1.q2),
                             false);
  Assert.assertEquals(-r1.q0 * r1.q0 - (r1.q1 * r1.q1 + 0 * r1.q2 + r1.q3 * r1.q3), r2.q0, 0.0);
  Assert.assertEquals(-r1.q1 * r1.q0 + r1.q0 * r1.q1 + (0 * r1.q3 - r1.q3 * 0), r2.q1, 0.0);
  Assert.assertEquals(-0 * r1.q0 + r1.q0 * r1.q2 + (r1.q3 * r1.q1 - r1.q1 * r1.q3), r2.q2, 0.0);
  Assert.assertEquals(-r1.q3 * r1.q0 + r1.q0 * 0 + (r1.q1 * 0 - 0 * r1.q2), r2.q3, 0.0);
  Assert.assertFalse(r2.reversed);
}
@Test
public void test50() {
  Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
  Rotation r2 = new Rotation(-r1.q0 * r1.q0 - (r1.q1 * r1.q1 + r1.q2 * r1.q2 + 0 * r1.q3),
                             -r1.q1 * r1.q0 + r1.q0 * r1.q1 + (r1.q2 * 0 - 0 * r1.q2),
                             -r1.q2 * r1.q0 + r1.q0 * r1.q2 + (0 * r1.q1 - r1.q1 * 0),
                             -0 * r1.q0 + r1.q0 * r1.q3 + (r1.q1 * r1.q2 - r1.q2 * r1.q1),
                             false);
  Assert.assertEquals(-r1.q0 * r1.q0 - (r1.q1 * r1.q1 + r1.q2 * r1.q2 + 0 * r1.q3), r2.q0, 0.0);
  Assert.assertEquals(-r1.q1 * r1.q0 + r1.q0 * r1.q1 + (r1.q2 * 0 - 0 * r1.q2), r2.q1, 0.0);
  Assert.assertEquals(-r1.q2 * r1.q0 + r1.q0 * r1.q2 + (0 * r1.q1 - r1.q1 * 0), r2.q2, 0.0);
  Assert.assertEquals(-0 * r1.q0 + r1.q0 * r1.q3 + (r1.q1 * r1.q2 - r1.q2 * r1.q1), r2.q3, 0.0);
  Assert.assertFalse(r2.reversed);
}
@Test
public void test51() {
  Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
  Rotation r2 = new Rotation(-r1.q0 * r1.q0 - (r1.q1 * r1.q1 + r1.q2 * r1.q2 + r1.q3 * r1.q3),
                             -r1.q1 * r1.q0 + r1.q0 * r1.q1 + (r1.q2 * r1.q3 - r1.q3 * r1.q2),
                             -r1.q2 * r1.q0 + r1.q0 * r1.q2 + (r1.q3 * r1.q1 - r1.q1 * r1.q3),
                             -r1.q3 * r1.q0 + r1.q0 * r1.q3 + (r1.q1 * r1.q2 - r1.q2 * r1.q1),
                             false);
  Assert.assertEquals(-r1.q0 * r1.q0 - (r1.q1 * r1.q1 + r1.q2 * r1.q2 + r1.q3 * r1.q3), r2.q0, 0.0);
  Assert.assertEquals(-r1.q1 * r1.q0 + r1.q0 * r1.q1 + (r1.q2 * r1.q3 - r1.q3 * r1.q2), r2.q1, 0.0);
  Assert.assertEquals(-r1.q2 * r1.q0 + r1.q0 * r1.q2 + (r1.q3 * r1.q1 - r1.q1 * r1.q3), r2.q2, 0.0);
  Assert.assertEquals(-r1.q3 * r1.q0 + r1.q0 * r1.q3 + (r1.q1 * r1.q2 - r1.q2 * r1.q1), r2.q3, 0.0);
  Assert.assertFalse(r2.reversed);
}
  @Test
    throws NotARotationMatrixException {

    try {
      new Rotation(new double[][] {
                     { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
                     {  0.445888,  0.797184, -0.407040 },
                     {  0.821760, -0.184320,  0.539200 },
                     { -0.354816,  0.574912,  0.737280 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
        new Rotation(new double[][] {
                       {  0.4,  0.8, -0.4 },
                       { -0.4,  0.6,  0.7 },
                       {  0.8, -0.2,  0.5 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);

    checkRotation(new Rotation(new double[][] {
                                 {  0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-10),
                  0.36, 0.8, 0.288, 0.384);

    checkRotation(new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10),
                  0.384, 0.36, 0.8, 0.288);

    checkRotation(new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 { -0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10),
                  0.288, 0.384, 0.36, 0.8);

    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 0.0, 0.0, 1.0 },
                      { 1.0, 0.0, 0.0 } };
    Rotation r = new Rotation(m1, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);

    double[][] m2 = { { 0.83203, -0.55012, -0.07139 },
                      { 0.48293,  0.78164, -0.39474 },
                      { 0.27296,  0.29396,  0.91602 } };
    r = new Rotation(m2, 1.0e-12);

    double[][] m3 = r.getMatrix();
    double d00 = m2[0][0] - m3[0][0];
    double d01 = m2[0][1] - m3[0][1];
    double d02 = m2[0][2] - m3[0][2];
    double d10 = m2[1][0] - m3[1][0];
    double d11 = m2[1][1] - m3[1][1];
    double d12 = m2[1][2] - m3[1][2];
    double d20 = m2[2][0] - m3[2][0];
    double d21 = m2[2][1] - m3[2][1];
    double d22 = m2[2][2] - m3[2][2];

    Assert.assertTrue(FastMath.abs(d00) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d01) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d02) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d10) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d11) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d12) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d20) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d21) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d22) < 6.0e-6);

    Assert.assertTrue(FastMath.abs(d00) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d01) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d02) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d10) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d11) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d12) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d20) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d21) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d22) > 4.0e-7);

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        double m3tm3 = m3[i][0] * m3[j][0]
                     + m3[i][1] * m3[j][1]
                     + m3[i][2] * m3[j][2];
        if (i == j) {
          Assert.assertTrue(FastMath.abs(m3tm3 - 1.0) < 1.0e-10);
        } else {
          Assert.assertTrue(FastMath.abs(m3tm3) < 1.0e-10);
        }
      }
    }

    checkVector(r.applyTo(Vector3D.PLUS_I),
                new Vector3D(m3[0][0], m3[1][0], m3[2][0]));
    checkVector(r.applyTo(Vector3D.PLUS_J),
                new Vector3D(m3[0][1], m3[1][1], m3[2][1]));
    checkVector(r.applyTo(Vector3D.PLUS_K),
                new Vector3D(m3[0][2], m3[1][2], m3[2][2]));

    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 } };
    r = new Rotation(m4, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI);

    try {
      double[][] m5 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m5, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }

    // additional regression test cases
    double[][] m6 = { { 0.5, -0.5, -0.70710678118 },
                      { 0.5, -0.5, 0.70710678118 },
                      { -0.70710678118, -0.70710678118, 0 } };
    r = new Rotation(m6, 1.0e-12);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_I);
    
    double[][] m7 = { { -1, 0, 0 },
                      { 0, -1, 0 },
                      { 0, 0, -1 } };
    r = new Rotation(m7, 1.0e-12);
    checkAngle(r.getAngle(), FastMath.PI);
    
    double[][] m8 = { { 0, 0, 1 },
                      { 0, 1, 0 },
                      { 1, 0, 0 } };
    r = new Rotation(m8, 1.0e-12);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);
    
    double[][] m9 = { { 1, 0, 0 },
                      { 0, 0, -1 },
                      { 0, 1, 0 } };
    r = new Rotation(m9, 1.0e-12);
    checkAngle(r.getAngle(), FastMath.PI);
    
    double[][] m10 = { { 0.8660254, 0.5, 0 },
                      { -0.5, 0.8660254, 0 },
                      { 0, 0, 1 } };
    r = new Rotation(m10, 1.0e-12);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(0.866025404, -0.5, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0.5, 0.866025404, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);

  }
  @Test
  public void test52() {
      Rotation r1 = new Rotation(0, 0, 0, 1);
      Rotation r2 = new Rotation(0, 0, 0, 2);
      double expectedDistance = Math.PI;
      double actualDistance = distance(r1, r2);
      Assert.assertEquals(expectedDistance, actualDistance, 1e-10);
      
      r1 = new Rotation(1, 0, 0, 0);
      r2 = new Rotation(0, 1, 0, 0);
      expectedDistance = Math.PI / 2;
      actualDistance = distance(r1, r2);
      Assert.assertEquals(expectedDistance, actualDistance, 1e-10);
      
      r1 = new Rotation(1, 2, 3, 4);
      r2 = new Rotation(2, 3, 4, 5);
      expectedDistance = Math.acos(1 / Math.sqrt(58));
      actualDistance = distance(r1, r2);
      Assert.assertEquals(expectedDistance, actualDistance, 1e-10);
  }
}