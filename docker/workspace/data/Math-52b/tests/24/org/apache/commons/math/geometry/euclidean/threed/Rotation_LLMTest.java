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

    // Additional test cases
    r = new Rotation(0.0, 0.0, 0.0, 1.0, true);
    reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);

    r = new Rotation(0.5, -0.5, 0.5, -0.5, true);
    reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
@Test
public void test1() {

    // Test Case 1
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

    // Test Case 2
    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Test Case 3 (regression test)
    r1 = new Rotation(new Vector3D(1, 0, 0), 0);
    n = 5.0;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    Vector3D u = new Vector3D(1, 1, 1);
    checkVector(r2.applyTo(u), r1.applyTo(u));
}
@Test
public void test2(){
    // Test Case 1
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
    Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);

    // Test Case 2 (regression test)
    u1 = new Vector3D(0, 1, 0);
    u2 = new Vector3D(1, 0, 0);
    rot = new Rotation(u1, u2, Vector3D.PLUS_I, Vector3D.PLUS_K);
    Assert.assertEquals(0.0, rot.getQ0(), 1.0e-15);
    Assert.assertEquals(1.0, rot.getQ1(), 1.0e-15);
    Assert.assertEquals(0.0, rot.getQ2(), 1.0e-15);
    Assert.assertEquals(0.0, rot.getQ3(), 1.0e-15);
}
  @Test
  public void test3() {
    // Create two different rotations
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-2, 3, -5), -1.7);

    // Check that the q1 values are the same for both rotations
    assertEquals(r1.getQ1(), r2.getQ1(), 0.0);
  }
  @Test
  public void test4() {
    // Create two different rotations with different inputs
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(0, 0, 0), 0.0);

    // Check that the q1 values are the same for both rotations
    assertEquals(r1.getQ1(), r2.getQ1(), 0.0);
  }
  @Test
  public void test5() {
    // Create a rotation with a negative input value
    Rotation r = new Rotation(new Vector3D(2, -3, 5), -1.7);

    // Check that the q1 value is negative
    assertTrue(r.getQ1() < 0);
  }
  @Test
  public void test6() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               Math.abs(n * r1.getQ2()), n * r1.getQ3(),
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
  public void test7() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               -Math.abs(n * r1.getQ2()), n * r1.getQ3(),
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
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               0, n * r1.getQ3(),
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

  // Existing test cases
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

  // Additional test cases
  r1 = new Rotation(0, 0, 0, 0, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  r1 = new Rotation(0, 0, 0, 1, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  r1 = new Rotation(0, 0, 1, 0, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  r1 = new Rotation(0, 1, 0, 0, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  r1 = new Rotation(1, 0, 0, 0, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

}
  @Test
  public void test10() {

    Rotation r = new Rotation(new Vector3D(0, 0, 1), 0.5 * FastMath.PI);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    double s = 1 / FastMath.sqrt(2);
    checkVector(r.getAxis(), new Vector3D(s, s, 0));
    checkAngle(r.getAngle(), 0.5 * FastMath.PI);

    r = new Rotation(Vector3D.PLUS_K, 0);
    checkVector(r.getAxis(), new Vector3D(0, 0, 1));
    checkAngle(r.getAngle(), 0);

    r = new Rotation(new Vector3D(1, 1, 1), FastMath.PI);
    checkVector(r.getAxis(), new Vector3D(-1, -1, -1));
    checkAngle(r.getAngle(), FastMath.PI);

    checkVector(Rotation.IDENTITY.getAxis(), Vector3D.PLUS_I);

  }
  @Test
  public void test11() {
    Rotation r = new Rotation(0.1, 0.2, 0.3, 0.4, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test12() {

    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(2, 0, 0);
    Vector3D v2 = new Vector3D(0, -2, -2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_K);

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
                     new Vector3D(-0.5, -0.5, -sqrt));
    checkRotation(r, sqrt, -0.5, -0.5, 0);

    r = new Rotation(u1, u2, u1, Vector3D.crossProduct(u1, u2));
    checkRotation(r, sqrt, sqrt, 0, 0);

    checkRotation(new Rotation(u1, u2, u1, u2), 1, 0, 0, 0);

    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }

  }
  @Test
  public void test13() {
    // Test when q0 is less than -0.1
    Rotation r1 = new Rotation(0.2, 0.3, 0.4, -0.15, true);
    Assert.assertEquals(2 * FastMath.asin(FastMath.sqrt(0.2 * 0.2 + 0.3 * 0.3 + 0.4 * 0.4)), r1.getAngle(), 1.0e-12);

    // Test when q0 is greater than 0.1
    Rotation r2 = new Rotation(0.6, 0.7, 0.8, 0.9, true);
    Assert.assertEquals(2 * FastMath.asin(FastMath.sqrt(0.6 * 0.6 + 0.7 * 0.7 + 0.8 * 0.8)), r2.getAngle(), 1.0e-12);

    // Test when q0 is between -0.1 and 0
    Rotation r3 = new Rotation(-0.05, 0.2, 0.3, 0.4, true);
    Assert.assertEquals(2 * FastMath.acos(-0.05), r3.getAngle(), 1.0e-12);

    // Test when q0 is between 0 and 0.1
    Rotation r4 = new Rotation(0.05, 0.2, 0.3, 0.4, true);
    Assert.assertEquals(2 * FastMath.acos(0.05), r4.getAngle(), 1.0e-12);

    // Test when q0 is exactly -0.1
    Rotation r5 = new Rotation(-0.1, 0.2, 0.3, 0.4, true);
    Assert.assertEquals(2 * FastMath.acos(-0.1), r5.getAngle(), 1.0e-12);

    // Test when q0 is exactly 0.1
    Rotation r6 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
    Assert.assertEquals(2 * FastMath.acos(0.1), r6.getAngle(), 1.0e-12);
  }
  @Test
  public void test14() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngleRegressions = { -FastMath.PI / 2, FastMath.PI / 2, 0 };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngleRegressions.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.1, singularCardanAngleRegressions[j], 0.3);
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

    double[] singularEulerAngleRegressions = { FastMath.PI, 2 * FastMath.PI };
    for (int i = 0; i < EulerOrders.length; ++i) {
      for (int j = 0; j < singularEulerAngleRegressions.length; ++j) {
        Rotation r = new Rotation(EulerOrders[i], 0.1, singularEulerAngleRegressions[j], 0.3);
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
      for (double alpha1 = -5.0; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = -1.55; alpha2 < 1.55; alpha2 += 0.65) {
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
      for (double alpha1 = 0.1; alpha1 < 8.4; alpha1 += 0.7) {
        for (double alpha2 = 0.05; alpha2 < 3.1; alpha2 += 0.45) {
          for (double alpha3 = -4.1; alpha3 < 6.2; alpha3 += 1.3) {
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

    // Additional test cases
    checkRotation(new Rotation(new double[][] {
                                 {  -0.752,  -0.217, -0.622 },
                                 {  -0.464,   0.885,  0.040 },
                                 {   0.468,   0.410, -0.783 }
                               }, 1.0e-10),
                  0.840, 0.105, 0.110, 0.510);
    
    checkRotation(new Rotation(new double[][] {
                                 {  -0.772,  -0.629,  0.090 },
                                 {   0.614,  -0.401,  0.679 },
                                 {  -0.157,   0.665,  0.730 }
                               }, 1.0e-10),
                  0.894, 0.232, 0.245, 0.302);

    checkRotation(new Rotation(new double[][] {
                                 {  -0.983,   0.004, -0.175 },
                                 {  -0.046,  -0.754,  0.656 },
                                 {  -0.177,   0.657,  0.734 }
                               }, 1.0e-10),
                  0.818, 0.539, 0.598, 0.013);

  }
  @Test
  public void test15() {
    Vector3D u = new Vector3D(0, 0, 0);
    Vector3D result = method.applyTo(u);
    assertEquals(new Vector3D(0, 0, 0), result);
  }
  @Test
  public void test16() {
    Vector3D u = new Vector3D(1, 2, 3);
    Vector3D result = method.applyTo(u);
    assertEquals(new Vector3D(-10, 11, 9), result);
  }
  @Test
  public void test17() {
    Vector3D u = new Vector3D(-4, -5, -6);
    Vector3D result = method.applyTo(u);
    assertEquals(new Vector3D(-46, -38, -22), result);
  }
  @Test
  public void test18() {
    Vector3D u = new Vector3D(0, 5, 0);
    Vector3D result = method.applyTo(u);
    assertEquals(new Vector3D(-10, 0, 0), result);
  }
  @Test
  public void test19() {
    Vector3D u = new Vector3D(1, 1, 1);
    Vector3D result = method.applyTo(u);
    assertEquals(new Vector3D(-7, -7, -5), result);
  }
  @Test
  public void test20() {
    Vector3D u = new Vector3D(-1, -1, -1);
    Vector3D result = method.applyTo(u);
    assertEquals(new Vector3D(-15, -15, -13), result);
  }
  @Test
  public void test21() {
    Vector3D u = new Vector3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
    Vector3D result = method.applyTo(u);
    double expected = 2 * (method.getQ0() * (Double.MAX_VALUE * method.getQ0() - (method.getQ2() * Double.MAX_VALUE - method.getQ3() * Double.MAX_VALUE)) + ((method.getQ1() * Double.MAX_VALUE + method.getQ2() * Double.MAX_VALUE) + method.getQ3() * Double.MAX_VALUE) * method.getQ1()) - Double.MAX_VALUE;
    assertEquals(expected, result.getX(), 0);
    assertEquals(expected, result.getY(), 0);
    assertEquals(expected, result.getZ(), 0);
  }
  @Test
  public void test22() {
    Vector3D u = new Vector3D(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
    Vector3D result = method.applyTo(u);
    double expected = 2 * (method.getQ0() * (Double.MIN_VALUE * method.getQ0() - (method.getQ2() * Double.MIN_VALUE - method.getQ3() * Double.MIN_VALUE)) + ((method.getQ1() * Double.MIN_VALUE + method.getQ2() * Double.MIN_VALUE) + method.getQ3() * Double.MIN_VALUE) * method.getQ1()) - Double.MIN_VALUE;
    assertEquals(expected, result.getX(), 0);
    assertEquals(expected, result.getY(), 0);
    assertEquals(expected, result.getZ(), 0);
  }
 @Test
    throws NotARotationMatrixException {

    try {
      new Rotation(new double[][] {
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
                     {  0.445888,  0.797184, -0.407040 },
                     {  0.821760, 0.0,  0.539200 },
                     { -0.354816,  0.574912,  0.737280 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
                       {  0.4,  0.8, -0.4 },
                       {  0.0,  0.0,  0.0 },
                       {  0.8, -0.2,  0.5 }
                     }, 1.0e-15);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

  }
@Test
  public void test23() {

    Rotation r1 = new Rotation(new Vector3D(0, -3, 5), 0.0);
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

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test24() {

    Vector3D u1 = new Vector3D(0, 0, 0);
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
  public void test25() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 0.0);
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

    r = new Rotation(Vector3D.PLUS_K, 0.0);
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
  public void test26() {
    // Regression test case 1
    Rotation r = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    Rotation r2 = new Rotation(0.2, 0.4, 0.6, 0.8, false);
    Rotation result = r.applyTo(r2);
    checkRotation(result, -0.066, 0.418, 0.524, -0.738);

    // Regression test case 2
    Rotation r3 = new Rotation(-0.4, 0.8, -0.2, 0.5, true);
    Rotation r4 = new Rotation(0.5, 0.7, 0.4, -0.2, true);
    Rotation result2 = r3.applyTo(r4);
    checkRotation(result2, -0.526, -0.539, 0.588, 0.316);
  }
  @Test
  public void test27() throws NotARotationMatrixException {
    // Regression test case 1
    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 1.0, 0.0, 0.0 },
                      { 0.0, 0.0, 2.0 } };
    Rotation r = new Rotation(m1, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(0.0, 0.0, -1.0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(1.0, 0.0, 0.0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0.0, 2.0, 0.0));

    // Regression test case 2
    double[][] m2 = { { 1.0, 0.0, 0.0 },
                      { 0.0, -1.0, 0.0 },
                      { 0.0, 0.0, -1.0 } };
    r = new Rotation(m2, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(1.0, 0.0, 0.0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0.0, -1.0, 0.0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0.0, 0.0, 1.0));
  }
  @Test
  public void test28() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation inverted = r.revert();
    
    // test case where q0 of the rotation is 0
    checkVector(inverted.applyTo(new Vector3D(0.0, 0.0, 1.0)), new Vector3D(0.0, 0.0, -1.0));
    
    // test case where q1 and q2 of the rotation are 0
    checkVector(inverted.applyTo(new Vector3D(0.0, 1.0, 0.0)), new Vector3D(0.0, -1.0, 0.0));
    
    // test case where q3 of rotation is 0
    checkVector(inverted.applyTo(new Vector3D(1.0, 0.0, 0.0)), new Vector3D(-1.0, 0.0, 0.0));
  }
@Test
throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
            { 1.0, 0.0, 0.0 },
            { 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0 }
        }, 1.0e-7);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }
}
@Test
throws NotARotationMatrixException {
    try {
        new Rotation(new double[][] {
            { 1.0, 0.0, 0.0 },
            { 0.0, 1.0, 0.0 },
            { 0.0, 0.0, 1.0 }
        }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }
}
@Test
throws NotARotationMatrixException {
    try {
        new Rotation(new double[][] {
            { 0.0, 1.0, 0.0 },
            { 1.0, 0.0, 0.0 },
            { 0.0, 0.0, 1.0 }
        }, 1.0e-7);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }
}
@Test
throws NotARotationMatrixException {
    try {
        new Rotation(new double[][] {
            {  0.445888,  0.797184, -0.407040 },
            {  0.821760, -0.184320,  0.539200 },
            { -0.354816,  0.574912,  0.737280 }
        }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }
}
@Test
throws NotARotationMatrixException {
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
}
  @Test
  public void test29() {
    Rotation r1 = new Rotation(0.5, 0.2, 0.7, 0.4, false);
    Rotation r2 = new Rotation(-0.3, 0.8, -0.1, 0.5, false);
    double expectedDistance = 1.571;
    double actualDistance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, actualDistance, 1.0e-3);
    
    r1 = new Rotation(1.1, 0.3, -0.6, 0.8, false);
    r2 = new Rotation(0.6, 0.4, -0.8, 0.2, false);
    expectedDistance = 1.670;
    actualDistance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, actualDistance, 1.0e-3);
    
    r1 = new Rotation(-0.5, -0.6, 0.4, 0.7, false);
    r2 = new Rotation(-0.2, 0.1, -0.8, 0.5, false);
    expectedDistance = 1.217;
    actualDistance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, actualDistance, 1.0e-3);
    
    r1 = new Rotation(0.2, 0.8, -0.3, 0.5, false);
    r2 = new Rotation(-0.5, 0.7, -0.4, 0.2, false);
    expectedDistance = 1.066;
    actualDistance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, actualDistance, 1.0e-3);
  }
}