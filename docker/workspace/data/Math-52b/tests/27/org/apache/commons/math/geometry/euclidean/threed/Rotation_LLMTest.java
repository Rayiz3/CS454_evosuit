package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    Rotation r = new Rotation(0, 0, 0, 1, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {
    Rotation r = new Rotation(0.001, 0, 0, 1, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test2() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test3() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test4() {

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
    r1 = new Rotation(new Vector3D(-2, 3, -5), 1.7);  // Changing the vector values
    n = 10.1;  // Changing the value of n
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

    r1 = new Rotation(new Vector3D(1, 0, 0), 0);  // Changing the angle to zero
    n = 15.3;  // Changing the value of n
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
  public void test5(){
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

      // New regression test case
      u1 = new Vector3D(-100.0, 200.0, 300.0);  // Changing the vector values
      u2 = new Vector3D(-500.0, -600.0, 700.0);  // Changing the vector values
      rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
      Assert.assertEquals(-0.6166891672621228, rot.getQ0(), 1.0e-15);
      Assert.assertEquals(-0.4677071737535398, rot.getQ1(), 1.0e-15);
      Assert.assertEquals(-0.04613794064754907, rot.getQ2(), 1.0e-15);
      Assert.assertEquals(-0.3934386139194564, rot.getQ3(), 1.0e-15);
  }
  public void test6() {
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 1.0);
    Rotation r2 = new Rotation(new Vector3D(1, 0, 0), 1.0);
    Rotation r3 = new Rotation(new Vector3D(0, 1, 0), 1.0);
    Rotation r4 = new Rotation(new Vector3D(0, 0, 1), 1.0);

    assertEquals(0.0, r1.getQ1(), 0.0); // Test with zero vector
    assertEquals(0.707, r2.getQ1(), 0.001); // Test with x-axis unit vector
    assertEquals(0.0, r3.getQ1(), 0.0); // Test with y-axis unit vector
    assertEquals(0.0, r4.getQ1(), 0.0); // Test with z-axis unit vector
  }
  @Test
  public void test7() {

    Rotation r1 = new Rotation(new Vector3D(-5, -3, 2), 1.7); // changed input value
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
  public void test8() {
    Rotation r1 = new Rotation(new Vector3D(5, 2, -3), 1.7);
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
  public void test9() {
    Rotation r1 = new Rotation(new Vector3D(-2, 3, -5), 1.7);
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
  public void test10() {
    // Regression test 1: squaredSine == 0

    // Updated test case
    Rotation r = new Rotation(new Vector3D(0, 0, 0), 2 * FastMath.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    checkVector(r.getAxis(), Vector3D.PLUS_I);
    checkAngle(r.getAngle(), 0.0);

    // Rest of the original test cases...
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), 2 * FastMath.PI / 3);

    try {
      new Rotation(new Vector3D(0, 0, 0), 2 * FastMath.PI / 3);
      Assert.fail("an exception should have been thrown");
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
  public void test11() {
    // Regression test 1: applyTo method

    // Updated test case
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);

    // Rest of the original test cases...
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test12() {

    // Regression test 1: u1 & u2 with negative values
    Vector3D u1 = new Vector3D(-3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    // Rest of the original test cases...
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
  public void test13() {
    // Test case 1: q0 < -0.1
    q0 = -0.2;
    q1 = 0.4;
    q2 = 0.6;
    q3 = 0.8;
    double angle = getAngle();
    Assert.assertEquals(2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3)), angle, 1e-10);
    
    // Test case 2: q0 > 0.1
    q0 = 0.2;
    q1 = 0.4;
    q2 = 0.6;
    q3 = 0.8;
    angle = getAngle();
    Assert.assertEquals(2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3)), angle, 1e-10);
    
    // Test case 3: q0 < 0 and -1 < q0 < 0.1
    q0 = -0.05;
    q1 = 0.4;
    q2 = 0.6;
    q3 = 0.8;
    angle = getAngle();
    Assert.assertEquals(2 * FastMath.acos(-q0), angle, 1e-10);
    
    // Test case 4: q0 > 0 and 0 < q0 < 0.1
    q0 = 0.05;
    q1 = 0.4;
    q2 = 0.6;
    q3 = 0.8;
    angle = getAngle();
    Assert.assertEquals(2 * FastMath.acos(q0), angle, 1e-10);
  }
  @Test
  public void test14() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngle = { FastMath.PI / 2, -FastMath.PI / 2 };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.2, singularCardanAngle[j], 0.3);
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
        Rotation r = new Rotation(EulerOrders[i], 0.2, singularEulerAngle[j], 0.3);
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

    // New regression test cases

    try {
      new Rotation(new double[][] {
                     { 0.1, 0.1, 0.1 },
                     { 0.1, 0.1, 0.1 },
                     { 0.1, 0.1, 0.1 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
                     {  0.5, -0.5, -0.5 },
                     { -0.5,  0.5,  0.5 },
                     { -0.5,  0.5,  0.5 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
        new Rotation(new double[][] {
                       {  0.2,  0.2, -0.2 },
                       { -0.2,  0.3,  0.4 },
                       {  0.3, -0.1,  0.5 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

    checkRotation(new Rotation(new double[][] {
                                 {  0.5,  0.8, -0.2 },
                                 { -0.3,  0.7,  0.6 },
                                 {  0.1, -0.4,  0.9 }
                               }, 1.0e-10),
                  0.2, 0.5, 0.4, 0.3);

    checkRotation(new Rotation(new double[][] {
                                 {  0.5,  0.6,  0.8 },
                                 {  0.1, -0.9,  0.4 },
                                 { -0.8, -0.3,  0.5 }
                               }, 1.0e-10),
                  0.3, 0.2, 0.5, 0.4);

    checkRotation(new Rotation(new double[][] {
                                 { -0.3,  0.5, -0.8 },
                                 {  0.8,  0.2,  0.5 },
                                 {  0.4, -0.8, -0.4 }
                               }, 1.0e-10),
                  0.4, 0.3, 0.2, 0.5);

    checkRotation(new Rotation(new double[][] {
                                 { -0.4,  0.5,  0.6 },
                                 {  0.9, -0.2, -0.3 },
                                 {  0.4,  0.8, -0.5 }
                               }, 1.0e-10),
                  0.5, 0.4, 0.3, 0.2);

    double[][] m6 = { { 0.0, 1.0, 0.0 },
                      { 0.0, 0.0, 1.0 },
                      { 1.0, 0.0, 0.0 } };
    r = new Rotation(m6, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);

    double[][] m7 = { { 0.4,  0.1,  0.6 },
                      { 0.2, -0.8,  -0.5 },
                      { 0.5,  0.4,   0.7 } };
    r = new Rotation(m7, 1.0e-12);

    double[][] m8 = r.getMatrix();
    double d30 = m7[0][0] - m8[0][0];
    double d31 = m7[0][1] - m8[0][1];
    double d32 = m7[0][2] - m8[0][2];
    double d40 = m7[1][0] - m8[1][0];
    double d41 = m7[1][1] - m8[1][1];
    double d42 = m7[1][2] - m8[1][2];
    double d50 = m7[2][0] - m8[2][0];
    double d51 = m7[2][1] - m8[2][1];
    double d52 = m7[2][2] - m8[2][2];

    Assert.assertTrue(FastMath.abs(d30) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d31) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d32) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d40) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d41) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d42) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d50) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d51) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d52) < 6.0e-6);

    Assert.assertTrue(FastMath.abs(d30) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d31) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d32) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d40) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d41) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d42) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d50) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d51) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d52) > 4.0e-7);

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        double m8tm8 = m8[i][0] * m8[j][0]
                     + m8[i][1] * m8[j][1]
                     + m8[i][2] * m8[j][2];
        if (i == j) {
          Assert.assertTrue(FastMath.abs(m8tm8 - 1.0) < 1.0e-10);
        } else {
          Assert.assertTrue(FastMath.abs(m8tm8) < 1.0e-10);
        }
      }
    }

    checkVector(r.applyTo(Vector3D.PLUS_K),
                new Vector3D(m8[0][0], m8[1][0], m8[2][0]));
    checkVector(r.applyTo(Vector3D.PLUS_K),
                new Vector3D(m8[0][1], m8[1][1], m8[2][1]));
    checkVector(r.applyTo(Vector3D.PLUS_K),
                new Vector3D(m8[0][2], m8[1][2], m8[2][2]));

    double[][] m9 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 } };
    r = new Rotation(m9, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI);

    try {
      double[][] m0 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m0, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }

  }
@Test
    throws NotARotationMatrixException {

    // Regression test case 1
    try {
      new Rotation(new double[][] {
                     {  0.445888,  0.797184, -0.407040 },
                     { -0.354816,  0.574912,  0.737280 },
                     {  0.821760,  0.184320, -0.539200 }
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

  }
  @Test
  public void test15() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyTo(r1);

    // Regression test case 1
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

  }
  @Test
  public void test16() {

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
  public void test17() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);

    // Regression test case 1
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
  public void test18() {

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
  public void test19() {

    Vector3D u = new Vector3D(3, 2, 1);
    Vector3D v = new Vector3D(-4, 2, 2);
    Rotation r = new Rotation(u, v);
    checkVector(r.applyTo(u.scalarMultiply(v.getNorm())), v.scalarMultiply(u.getNorm()));

    checkAngle(new Rotation(u, u.negate()).getAngle(), FastMath.PI);

    try {
        new Rotation(u, Vector3D.ZERO);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }

  }
  @Test
  public void test20() {

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
  public void test21() {

    Rotation r = new Rotation(new Vector3D(10, 10, 10), 2 * FastMath.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), 2 * FastMath.PI / 3);

    try {
      new Rotation(new Vector3D(0, 0, 0), 2 * FastMath.PI / 3);
      Assert.fail("an exception should have been thrown");
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
  public void test22() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test23() {

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
  public void test24() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyInverseTo(r1);

    // Regression test case 1
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
  public void test25() {
    Rotation r = new Rotation(new double[][] {
                             {  0.445888,  0.797184, -0.407040 },
                             { -0.354816,  0.574912,  0.737280 },
                             {  0.821760, -0.184320,  0.539200 }
                           }, 1.0e-10);

    Vector3D u = new Vector3D(0.5, 0.5, 0.5);
    Vector3D expected = new Vector3D(0.500038351725, 0.007149695519, 0.992483267505);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test26() {
    Rotation r = new Rotation(new double[][] {
                             { -0.539200,  0.737280,  0.407040 },
                             { -0.184320, -0.574912,  0.797184 },
                             {  0.821760,  0.354816,  0.445888 }
                           }, 1.0e-10);

    Vector3D u = new Vector3D(-1.0, 2.0, -3.0);
    Vector3D expected = new Vector3D(-8.92778907057, -5.73024201763, 1.93233659122);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test27() {
    Rotation r = new Rotation(new double[][] {
                             {  -0.445888,  0.797184, -0.407040 },
                             {   0.354816,  0.574912,  0.737280 },
                             {  -0.821760, -0.184320,  0.539200 }
                           }, 1.0e-10);

    Vector3D u = new Vector3D(-2.0, 1.0, 0.5);
    Vector3D expected = new Vector3D(-0.566901543029,-1.201900232651,-0.559787044049);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test28() {
    Rotation r = new Rotation(new double[][] {
                             { 1.0,  0.0,   0.0 },
                             { 0.0, -1.0,  0.0 },
                             { 0.0,  0.0, -1.0 }
                           }, 1.0e-7);

    Vector3D u = new Vector3D(3.0, 5.0, -2.0);
    Vector3D expected = new Vector3D(3.0, -5.0, 2.0);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test29() {
    Rotation r = new Rotation(new double[][] {
                             { 0.539200,  0.737280,  0.407040 },
                             { 0.184320, -0.574912,  0.797184 },
                             { 0.821760, -0.354816, -0.445888 }
                           }, 1.0e-10);

    Vector3D u = new Vector3D(2.0, 4.0, -5.0);
    Vector3D expected = new Vector3D(11.31387602597, 2.44950056285, 2.66133531772);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test30() {
    Rotation r = new Rotation(new double[][] {
                             { -0.539200,  0.737280,  0.407040 },
                             { -0.184320, -0.574912,  0.797184 },
                             {  0.821760,  0.354816,  0.445888 }
                           }, 1.0e-10);

    Vector3D u = new Vector3D(-1.0, -3.0, 2.0);
    Vector3D expected = new Vector3D(1.472655675736, -3.050268802333, -0.155256628694);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test31() {
    Rotation r = new Rotation(new double[][] {
                             {  0.539200,  0.737280,  0.407040 },
                             {  0.184320, -0.574912,  0.797184 },
                             { -0.821760,  0.354816,  0.445888 }
                           }, 1.0e-10);

    Vector3D u = new Vector3D(6.0, -2.0, -3.0);
    Vector3D expected = new Vector3D(5.570359183327, -9.979677973104, -3.091378388541);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test32() {
    Rotation r = new Rotation(new double[][] {
                             { -0.445888,  0.797184, -0.407040 },
                             {  0.354816,  0.574912,  0.737280 },
                             { -0.821760, -0.184320,  0.539200 }
                           }, 1.0e-10);

    Vector3D u = new Vector3D(-7.0, 4.0, -1.0);
    Vector3D expected = new Vector3D(-4.437157697329, 6.554139496289, -5.766457145525);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test33() {
    Rotation r = new Rotation(new double[][] {
                             {  0.288,  0.384,  0.36 },
                             { -0.8,    0.0,    0.0  },
                             {  0.0,    0.0,   -0.8  }
                           }, 1.0e-7);

    Vector3D u = new Vector3D(-5.0, 3.0, 2.0);
    Vector3D expected = new Vector3D(-2.000000015729, -1.192092813395, 4.0);
    Vector3D result = r.applyInverseTo(u);
    Assert.assertEquals(expected.getX(), result.getX(), 1.0e-12);
    Assert.assertEquals(expected.getY(), result.getY(), 1.0e-12);
    Assert.assertEquals(expected.getZ(), result.getZ(), 1.0e-12);
  }
  @Test
  public void test34() {
    Rotation r = new Rotation(0.2, 0.3, 0.4, 0.5, false);
    Rotation result = r.applyTo(new Rotation(0.1, 0.2, 0.3, 0.4, true));
    Assert.assertEquals(0.06, result.getQ0(), 1.0e-8);
    Assert.assertEquals(-0.14, result.getQ1(), 1.0e-8);
    Assert.assertEquals(-0.10, result.getQ2(), 1.0e-8);
    Assert.assertEquals(-0.18, result.getQ3(), 1.0e-8);
  }
  @Test
  public void test35() {
    Rotation r = new Rotation(0.5, -0.8, 0.3, 0.2, true);
    Rotation result = r.applyTo(new Rotation(-0.3, 0.4, 0.1, 0.5, true));
    Assert.assertEquals(0.6, result.getQ0(), 1.0e-8);
    Assert.assertEquals(-0.22, result.getQ1(), 1.0e-8);
    Assert.assertEquals(0.46, result.getQ2(), 1.0e-8);
    Assert.assertEquals(-0.32, result.getQ3(), 1.0e-8);
  }
  @Test
  public void test36() {
    Rotation r = new Rotation(-0.1, 0.6, -0.7, 0.3, false);
    Rotation result = r.applyTo(new Rotation(-0.2, 0.1, 0.6, 0.4, true));
    Assert.assertEquals(0.12, result.getQ0(), 1.0e-8);
    Assert.assertEquals(0.0, result.getQ1(), 1.0e-8);
    Assert.assertEquals(-0.4, result.getQ2(), 1.0e-8);
    Assert.assertEquals(-0.18, result.getQ3(), 1.0e-8);
  }
  @Test
  public void test37() {
    // Regression test case for mutant M1
    Rotation r = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    Rotation result = r.applyInverseTo(r);
    checkRotation(result, -0.734, 0.709, 0.403, -0.373);
  }
  @Test
  public void test38() {
    // Regression test case for mutant M2
    Rotation r = new Rotation(0.7, 0.2, 0.3, 0.4, false);
    Rotation result = r.applyInverseTo(r);
    checkRotation(result, -0.190, -0.744, -0.492, -0.431);
  }
  @Test
  public void test39() {
    // Regression test case for mutant M3
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(1, 3, 2), 0.3);
    Rotation r3 = r2.applyInverseTo(r1);
    checkRotation(r3, -0.055, -0.592, 0.800, 0.079);
  }
  @Test
  public void test40() {
    // Regression test case for mutant M4
    Rotation r1 = new Rotation(new Vector3D(-5, 6, 8), 1.2);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.6);
    Rotation r3 = r2.applyInverseTo(r1);
    checkRotation(r3,  0.284, -0.839, -0.380, -0.160);
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
    
    try {
      double[][] m6 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m6, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }
    
    try {
      double[][] m7 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m7, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
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

}
@Test
public void test41() {

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
public void test42() {
  Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test43() {

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
public void test44() {
  Rotation r1 = new Rotation(0, 0, 0, 1);
  Rotation r2 = new Rotation(0, 0, 0, -1);
  Assert.assertEquals(FastMath.PI, distance(r1, r2), 1.0e-12);

  r1 = new Rotation(0, 0, 0, 1);
  r2 = new Rotation(0, 0, 1, 0);
  Assert.assertEquals(FastMath.PI / 2, distance(r1, r2), 1.0e-12);

  r1 = new Rotation(1, 0, 0, 0);
  r2 = new Rotation(0, 1, 0, 0);
  Assert.assertEquals(FastMath.PI / 2, distance(r1, r2), 1.0e-12);

  r1 = new Rotation(1, 1, 0, 0);
  r2 = new Rotation(1, -1, 0, 0);
  Assert.assertEquals(FastMath.PI, distance(r1, r2), 1.0e-12);
}
}