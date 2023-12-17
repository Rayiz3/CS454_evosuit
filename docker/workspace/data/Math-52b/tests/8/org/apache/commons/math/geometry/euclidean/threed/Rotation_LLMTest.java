package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
@Test
public void test0() {
  // Original test case
  Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  
  // New test cases
  Rotation r1 = new Rotation(0, 0, 0, 0, true);
  Rotation reverted1 = r1.revert();
  checkRotation(r1.applyTo(reverted1), 1, 0, 0, 0);
  checkRotation(reverted1.applyTo(r1), 1, 0, 0, 0);
  Assert.assertEquals(r1.getAngle(), reverted1.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted1.getAxis()), 1.0e-12);

  Rotation r2 = new Rotation(0.005, 0.1, 0.2, 0.3, true);
  Rotation reverted2 = r2.revert();
  checkRotation(r2.applyTo(reverted2), 1, 0, 0, 0);
  checkRotation(reverted2.applyTo(r2), 1, 0, 0, 0);
  Assert.assertEquals(r2.getAngle(), reverted2.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r2.getAxis(), reverted2.getAxis()), 1.0e-12);

  Rotation r3 = new Rotation(0.9, 0.3, 0.6, 0.2, true);
  Rotation reverted3 = r3.revert();
  checkRotation(r3.applyTo(reverted3), 1, 0, 0, 0);
  checkRotation(reverted3.applyTo(r3), 1, 0, 0, 0);
  Assert.assertEquals(r3.getAngle(), reverted3.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r3.getAxis(), reverted3.getAxis()), 1.0e-12);
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
    
                // Change the input value of u here
                Vector3D uNew = new Vector3D(0.5 * x, 0.5 * y, 0.5 * z);
    
                checkVector(r2.applyTo(uNew), r1.applyTo(u));
            }
        }
    }
}
@Test
public void test2() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);

    for (double x = -0.9; x < 0.9; x += 0.2) {
        for (double y = -0.9; y < 0.9; y += 0.2) {
            for (double z = -0.9; z < 0.9; z += 0.2) {
                Vector3D u = new Vector3D(x, y, z);
    
                // Change the input value of u here
                Vector3D uNew = new Vector3D(-x, -y, -z);
    
                checkVector(r2.applyTo(uNew), r1.applyTo(u));
            }
        }
    }
}
@Test
public void test3() {
    Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                              -5774608829631843.0 /  268435456.0,
                              -3822921525525679.0 / 4294967296.0);
    Vector3D u2 = new Vector3D( -5712344449280879.0 /    2097152.0,
                               -2275058564560979.0 /    1048576.0,
                                4423475992255071.0 /      65536.0);
    Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
    Assert.assertEquals( 0.6228370359608200639829222, rot.getQ0(), 1.0e-15);
    Assert.assertEquals( 0.0257707621456498790029987, rot.getQ1(), 1.0e-15);
    Assert.assertEquals(-0.0000000002503012255839931, rot.getQ2(), 1.0e-15);
    Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);
}
@Test
public void test4() {
    Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                              -5774608829631843.0 /  268435456.0,
                              -3822921525525679.0 / 4294967296.0);
    Vector3D u2 = new Vector3D( -5712344449280879.0 /    2097152.0,
                               -2275058564560979.0 /    1048576.0,
                                4423475992255071.0 /      65536.0);
    Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);

    // Change the expected output value here
    Assert.assertEquals( 0.6, rot.getQ0(), 1.0e-15);
    Assert.assertEquals( 0.03, rot.getQ1(), 1.0e-15);
    Assert.assertEquals(-0.0000000002, rot.getQ2(), 1.0e-15);
    Assert.assertEquals(-0.8, rot.getQ3(), 1.0e-15);
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

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Additional test cases with different inputs

    r1 = new Rotation(new Vector3D(0, 0, 0), 0);
    n = 0;
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

    r1 = new Rotation(new Vector3D(1, 1, 1), 2.5);
    n = -8.9;
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

  // Original test case
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

  // New regression test cases
  r1 = new Rotation(new Vector3D(5, -8, 3), 2.3);
  n = 12.5;
  r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                     n * r1.getQ2(), n * r1.getQ3(),
                     true);
  for (double x = -1.5; x < 1.5; x += 0.3) {
    for (double y = -1.5; y < 1.5; y += 0.3) {
      for (double z = -1.5; z < 1.5; z += 0.3) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }

  r1 = new Rotation(new Vector3D(1, 1, 1), 0.9);
  n = 10;
  r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                     n * r1.getQ2(), n * r1.getQ3(),
                     true);
  for (double x = -1; x < 1; x += 0.1) {
    for (double y = -1; y < 1; y += 0.1) {
      for (double z = -1; z < 1; z += 0.1) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }

}
  @Test
  public void test7() {
    // Test case 1: q3 = 0
    Rotation r1 = new Rotation(0, 0, 0, 0, true);
    double expectedQ3 = 0;
    double actualQ3 = r1.getQ3();
    assertEquals(expectedQ3, actualQ3, 0.0001);

    // Test case 2: q3 = 1.5
    r1 = new Rotation(1, 2, 3, 1.5, false);
    expectedQ3 = 1.5;
    actualQ3 = r1.getQ3();
    assertEquals(expectedQ3, actualQ3, 0.0001);

    // Test case 3: q3 = -2.7
    r1 = new Rotation(2.7, -5.4, 3, -2.7, true);
    expectedQ3 = -2.7;
    actualQ3 = r1.getQ3();
    assertEquals(expectedQ3, actualQ3, 0.0001);
  }
  @Test
  public void test8() {

    Rotation r = new Rotation(new Vector3D(10, 10, 10), FastMath.PI / 3); // Changed angle value
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), FastMath.PI / 3);

    try {
      new Rotation(new Vector3D(0, 0, 0), FastMath.PI / 3);
      Assert.fail("an exception should have been thrown");
    } catch (ArithmeticException e) {
    }

    r = new Rotation(Vector3D.PLUS_K, 0.75 * FastMath.PI); // Changed angle value
    checkVector(r.getAxis(), new Vector3D(0, 0, -1));
    checkAngle(r.getAngle(), 0.25 * FastMath.PI);

    r = new Rotation(Vector3D.PLUS_J, 0.5 * FastMath.PI); // Changed angle value
    checkVector(r.getAxis(), Vector3D.PLUS_J);
    checkAngle(r.getAngle(), 0.5 * FastMath.PI);

    checkVector(Rotation.IDENTITY.getAxis(), Vector3D.PLUS_I);

  }
  @Test
  public void test9() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test10() {

    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);

    r = new Rotation(u1, u2, u1.negate(), u2.negate());
    Vector3D axis = r.getAxis();
    if (Vector3D.dotProduct(axis, Vector3D.PLUS_K) > 0) {
      checkVector(axis, Vector3D.PLUS_K);
    } else {
      checkVector(axis, Vector3D.MINUS_K);
    }
    checkAngle(r.getAngle(), FastMath.PI);

    double sqrt = FastMath.sqrt(2) / 2;
    r = new Rotation(Vector3D.PLUS_I,  Vector3D.PLUS_J,
                     new Vector3D(0.5, 0.5,  sqrt),
                     new Vector3D(0.5, 0.5, -sqrt));
    checkRotation(r, sqrt, 0.5, 0.5, 0);

    r = new Rotation(u1, u2, u1, Vector3D.crossProduct(u1, u2));
    checkRotation(r, sqrt, -sqrt, 0, 0);

    checkRotation(new Rotation(u1, u2, u1, u2), 1, 0, 0, 0);

    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }

  }
@Test
public void test11() {
    // Initial test case
    double q0 = 0.5;
    double q1 = -0.7;
    double q2 = 0.3;
    double q3 = 0.1;
    double expected = 0.5995364144954259;
    double result = getAngle(q0, q1, q2, q3);
    assertEquals(expected, result, 0.00001);

    // Test case with q0 < -0.1
    q0 = -0.2;
    q1 = 0.4;
    q2 = 0.6;
    q3 = 0.8;
    expected = 3.141592653589793;
    result = getAngle(q0, q1, q2, q3);
    assertEquals(expected, result, 0.00001);

    // Test case with q0 > 0.1
    q0 = 0.3;
    q1 = 0.2;
    q2 = 0.1;
    q3 = 0.5;
    expected = 0.39951946875508614;
    result = getAngle(q0, q1, q2, q3);
    assertEquals(expected, result, 0.00001);

    // Test case with q0 < 0
    q0 = -0.5;
    q1 = 0.4;
    q2 = 0.3;
    q3 = 0.2;
    expected = 3.141592653589793;
    result = getAngle(q0, q1, q2, q3);
    assertEquals(expected, result, 0.00001);
}
  @Test
  public void test12() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngle = { FastMath.PI / 2, -FastMath.PI / 2, 0, FastMath.PI };
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

    RotationOrder[] EulerOrders = {
            RotationOrder.XYX, RotationOrder.XZX, RotationOrder.YXY,
            RotationOrder.YZY, RotationOrder.ZXZ, RotationOrder.ZYZ
          };

    double[] singularEulerAngle = { 0.1, 0.5, 1.1, 1.5 };
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

 
    //------------- Regression Tests ------------------

    // New test case with q0q0, q0q1, q0q2, q0q3 being 0
    // Therefore, all values of matrix m should be 0
    checkRotation(new Rotation(new double[][] {
                                 {  0.0,  0.0, 0.0 },
                                 {  0.0,  0.0, 0.0 },
                                 {  0.0, 0.0, 0.0 }
                               }, 1.0e-10),
                  0.0, 0.0, 0.0, 0.0);
    
    // New test case with q1q1, q1q2, q1q3 being negative numbers
    // Therefore, the corresponding values in matrix m should be negative
    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 {  0.821760, -0.184320,  -0.539200 },
                                 { -0.354816,  -0.574912,  0.737280 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);
    
    // New test case with q2q2, q2q3, q3q3 being 0
    // Therefore, the corresponding values in matrix m should be same as those in matrix m1
    checkRotation(new Rotation(new double[][] {
                                 {  0.4,  0.8, -0.4 },
                                 { -0.4,  0.6,  0.0 },
                                 { -0.8, 0.0,  0.0 }
                               }, 1.0e-15),
                                 0.4, 0.8, -0.4, -0.4);
    
    // New test case with all values of q0q0, q0q1, q0q2, q0q3 being 1.0
    // Therefore all values of m should be same as their respective m1 values multiplied by 2.0
    checkRotation(new Rotation(new double[][] {
                                 {  1.79155e-7,  3.19589e-7, -1.62953e-7 },
                                 {  3.2656e-7, -7.34065e-8,  3.02566e-7 },
                                 { -1.40919e-7,  2.28745e-7,  2.94202e-7 }
                               }, 1.0e-10),
                  1.6, 0.576, 0.768, 0.72);
    
  }
  @Test
    throws NotARotationMatrixException {

    // Original test cases
    
    // Change the input value of the method
    try {
      new Rotation(new double[][] {
                     {  0.0,  1.0,  0.0 },
                     {  1.0,  0.0,  0.0 },
                     {  0.0,  0.0,  1.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    // Change the input value of the method
    try {
      new Rotation(new double[][] {
                     {  0.590208,  0.807168,  0.010496 },
                     {  0.098624,  0.071808, -0.992192 },
                     { -0.801792,  0.053376,  0.594432 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    // Change the input value of the method
    try {
        new Rotation(new double[][] {
                       { -0.6, -0.8,  0.0 },
                       {  0.8, -0.6,  0.0 },
                       {  0.0,  0.0, -1.0 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

    // Original test cases
    
    // Change the input value of the method
    checkRotation(new Rotation(new double[][] {
                                 {  0.590208,  0.807168,  0.010496 },
                                 { -0.801792,  0.053376,  0.594432 },
                                 {  0.098624,  0.071808, -0.992192 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);

    // Change the input value of the method
    checkRotation(new Rotation(new double[][] {
                                 {  0.010496,  0.807168, -0.590208 },
                                 {  0.594432,  0.053376,  0.801792 },
                                 {  0.071808, -0.992192, -0.098624 }
                              }, 1.0e-10),
                  0.36, 0.8, 0.288, 0.384);

    // Change the input value of the method
    checkRotation(new Rotation(new double[][] {
                                 { -0.590208,  0.807168, -0.010496 },
                                 {  0.801792,  0.053376,  0.594432 },
                                 {  0.098624,  0.071808,  0.992192 }
                               }, 1.0e-10),
                  0.384, 0.36, 0.8, 0.288);

    // Change the input value of the method
    checkRotation(new Rotation(new double[][] {
                                 { -0.010496,  0.807168,  0.590208 },
                                 {  0.594432,  0.053376, -0.801792 },
                                 { -0.071808, -0.992192,  0.098624 }
                               }, 1.0e-10),
                  0.288, 0.384, 0.36, 0.8);

    double[][] m1 = { { 0.0, 0.0, 1.0 },
                      { 0.0, 1.0, 0.0 },
                      { 1.0, 0.0, 0.0 } };
    Rotation r = new Rotation(m1, 1.0e-7);
    // Change the input value of the method
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    // Change the input value of the method
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    // Change the input value of the method
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

    // Change the input value of the method
    checkVector(r.applyTo(Vector3D.PLUS_I),
                new Vector3D(m3[0][0], m3[1][0], m3[2][0]));
    // Change the input value of the method
    checkVector(r.applyTo(Vector3D.PLUS_J),
                new Vector3D(m3[0][1], m3[1][1], m3[2][1]));
    // Change the input value of the method
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

  }
  @Test
  public void test13() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          // Change the input value of the method
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    // Change the input value of the method
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test14() {

    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    // Change the input value of the method
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    // Change the input value of the method
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    r = new Rotation(u1, u2, u1.negate(), u2.negate());
    Vector3D axis = r.getAxis();
    if (Vector3D.dotProduct(axis, Vector3D.PLUS_K) > 0) {
      // Change the input value of the method
      checkVector(axis, Vector3D.PLUS_K);
    } else {
      // Change the input value of the method
      checkVector(axis, Vector3D.MINUS_K);
    }
    checkAngle(r.getAngle(), FastMath.PI);

    double sqrt = FastMath.sqrt(2) / 2;
    r = new Rotation(Vector3D.PLUS_I,  Vector3D.PLUS_J,
                     new Vector3D(0.5, 0.5,  sqrt),
                     new Vector3D(0.5, 0.5, -sqrt));
    // Change the input value of the method
    checkRotation(r, sqrt, 0.5, 0.5, 0);

    r = new Rotation(u1, u2, u1, Vector3D.crossProduct(u1, u2));
    // Change the input value of the method
    checkRotation(r, sqrt, -sqrt, 0, 0);

    // Change the input value of the method
    checkRotation(new Rotation(u1, u2, u1, u2), 1, 0, 0, 0);

    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }

  }
  @Test
  public void test15() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          r.applyInverseTo(r.applyTo(u));
          // Change the input value of the method
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          // Change the input value of the method
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }

    r = Rotation.IDENTITY;
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          // Change the input value of the method
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          // Change the input value of the method
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }

    r = new Rotation(Vector3D.PLUS_K, FastMath.PI);
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          // Change the input value of the method
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          // Change the input value of the method
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }

  }
  @Test
  public void test16() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    // Change the input value of the method
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    // Change the input value of the method
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test17() {

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
          Assert.fail("an exception should have been caught");
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
  public void test18() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          // Change the input value of the method
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

  }
  @Test
  public void test19() {
    // Regression test: q0 = 0, q1 = 0, q2 = 0, q3 = 0
    Rotation r = new Rotation(0, 0, 0, 0, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, 1, 0, 0, 0);
  }
  @Test
  public void test20() {
    // Regression test: q0 = -1, q1 = 0, q2 = 0, q3 = 0
    Rotation r = new Rotation(-1, 0, 0, 0, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, 0, 0, 0, -1);
  }
  @Test
  public void test21() {
    // Regression test: q0 = 0.288, q1 = 0.384, q2 = 0.36, q3 = 0.8
    Rotation r = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, -0.3856, 0.6936, -0.128, 0.5936);
  }
  @Test
  public void test22() {
    // Regression test: q0 = 0.36, q1 = 0.8, q2 = 0.288, q3 = 0.384
    Rotation r = new Rotation(0.36, 0.8, 0.288, 0.384, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, 0.3872, 0.6928, 0.1936, 0.6176);
  }
  @Test
  public void test23() {
    // Regression test: q0 = 0.384, q1 = 0.36, q2 = 0.8, q3 = 0.288
    Rotation r = new Rotation(0.384, 0.36, 0.8, 0.288, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, -0.1, 0.104, 0.216, -0.952);
  }
  @Test
  public void test24() {
    // Regression test: q0 = 0.8, q1 = 0.288, q2 = 0.384, q3 = 0.36
    Rotation r = new Rotation(0.8, 0.288, 0.384, 0.36, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, -0.2648, 0.2528, -0.1456, 0.8984);
  }
  @Test
  public void test25() {
    // Regression test: q0 = 0.8, q1 = 0.384, q2 = 0.36, q3 = 0.288
    Rotation r = new Rotation(0.8, 0.384, 0.36, 0.288, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, -0.2216, 0.2276, -0.1324, 0.9232);
  }
  @Test
  public void test26() {
    // Regression test: q0 = 0.8, q1 = 0.36, q2 = 0.384, q3 = 0.288
    Rotation r = new Rotation(0.8, 0.36, 0.384, 0.288, false);
    Rotation result = r.applyTo(r);
    checkRotation(result, -0.148, 0.156, -0.0912, 0.9656);
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

    // Regression test: Test case with specific values.
    r = new Rotation(new double[][] {
                                 {  0,  -1,   0 },
                                 {  1,   0,   0 },
                                 {  0,   0,   1 }
                               }, 1.0e-10);
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

    // Additional test cases for regression testing
    double[][] m6 = { { 0.445888, -0.797184, 0.407040 },
                      { 0.354816,  0.574912, 0.737280 },
                      { 0.821760, -0.184320, 0.539200 } };
    checkRotation(new Rotation(m6, 1.0e-10),
                  0.8, -0.288, 0.384, -0.36);

    double[][] m7 = { { 0.539200, -0.737280, -0.407040 },
                      { 0.184320,  0.574912, -0.797184 },
                      { 0.821760, -0.354816,  0.445888 } };
    checkRotation(new Rotation(m7, 1.0e-10),
                  -0.36, 0.8, -0.288, 0.384);

    double[][] m8 = { { -0.445888, -0.797184, 0.407040 },
                      {  0.354816, -0.574912, -0.737280 },
                      {  0.821760,  0.184320,  0.539200 } };
    checkRotation(new Rotation(m8, 1.0e-10),
                  -0.384, -0.36, 0.8, -0.288);

    double[][] m9 = { { -0.539200, -0.737280, -0.407040 },
                      { -0.184320,  0.574912, -0.797184 },
                      {  0.821760,  0.354816, -0.445888 } };
    checkRotation(new Rotation(m9, 1.0e-10),
                  -0.288, -0.384, -0.36, 0.8);
  }
@Test
public void test28() {
    Rotation r1 = new Rotation(new double[][] {
                    { 0.445888,  0.797184, -0.407040 },
                    { -0.354816,  0.574912,  0.737280 },
                    {  0.821760, -0.184320,  0.539200 }
                }, 1.0e-10);
    Rotation r2 = new Rotation(new double[][] {
                    {  0.539200,  0.737280,  0.407040 },
                    {  0.184320, -0.574912,  0.797184 },
                    {  0.821760, -0.354816, -0.445888 }
                }, 1.0e-10);
    double expectedDistance = r1.applyInverseTo(r2).getAngle();
    double actualDistance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, actualDistance, 1e-10);

    r1 = new Rotation(new double[][] {
                    { -0.445888,  0.797184, -0.407040 },
                    { 0.354816,  0.574912,  0.737280 },
                    {  0.821760,  0.184320, -0.539200 }
                }, 1.0e-10);
    r2 = new Rotation(new double[][] {
                    { -0.539200,  0.737280,  0.407040 },
                    { -0.184320, -0.574912,  0.797184 },
                    {  0.821760,  0.354816,  0.445888 }
                }, 1.0e-10);
    expectedDistance = r1.applyInverseTo(r2).getAngle();
    actualDistance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, actualDistance, 1e-10);

    r1 = new Rotation(new double[][] {
                    {  0.539200,  0.737280,  0.407040 },
                    {  0.184320, -0.574912,  0.797184 },
                    {  0.821760, -0.354816, -0.445888 }
                }, 1.0e-10);
    r2 = new Rotation(new double[][] {
                    { -0.539200,  0.737280,  0.407040 },
                    { -0.184320, -0.574912,  0.797184 },
                    {  0.821760,  0.354816,  0.445888 }
                }, 1.0e-10);
    expectedDistance = r1.applyInverseTo(r2).getAngle();
    actualDistance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, actualDistance, 1e-10);
}
}