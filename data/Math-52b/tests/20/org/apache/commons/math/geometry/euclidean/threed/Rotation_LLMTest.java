package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    // Test case 1: Reverting a rotation with zero angle
    Rotation r1 = new Rotation(0, 0.5, 0.5, 0.5, true);
    Rotation reverted1 = r1.revert();
    checkRotation(r1.applyTo(reverted1), 1, 0, 0, 0);
    checkRotation(reverted1.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted1.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted1.getAxis()), 1.0e-12);

    // Test case 2: Reverting a rotation with negative angle
    Rotation r2 = new Rotation(-0.5, 0.2, -0.6, 0.7, true);
    Rotation reverted2 = r2.revert();
    checkRotation(r2.applyTo(reverted2), 1, 0, 0, 0);
    checkRotation(reverted2.applyTo(r2), 1, 0, 0, 0);
    Assert.assertEquals(r2.getAngle(), reverted2.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r2.getAxis(), reverted2.getAxis()), 1.0e-12);

    // Test case 3: Reverting a rotation with larger angle
    Rotation r3 = new Rotation(2.5, 0, 0, 1, true);
    Rotation reverted3 = r3.revert();
    checkRotation(r3.applyTo(reverted3), 1, 0, 0, 0);
    checkRotation(reverted3.applyTo(r3), 1, 0, 0, 0);
    Assert.assertEquals(r3.getAngle(), reverted3.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r3.getAxis(), reverted3.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {
    // Test case 1
    Rotation r1 = new Rotation(new Vector3D(5, 6, 7), 2.3);
    double expectedResult1 = 0.8177678347705193;
    assertEquals(expectedResult1, r1.getQ0(), 1e-15);

    // Test case 2
    Rotation r2 = new Rotation(new Vector3D(-1, 0, 0), FastMath.PI);
    double expectedResult2 = -1.0;
    assertEquals(expectedResult2, r2.getQ0(), 1e-15);
  }
  @Test
  public void test2() {

    // Additional test case 1
    Rotation r1 = new Rotation(new Vector3D(-2, 3, -5), -1.7);
    double n = -23.5;
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

    // Additional test case 2
    r1 = new Rotation( 0.288,  0.384,  -0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Original test case
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    n = 23.5;
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
  public void test3() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.5; x < 0.5; x += 0.2) {
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
  public void test4() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.5; y < 0.5; y += 0.2) {
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
  public void test5() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.5; z < 0.5; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test6() {

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

    // Additional regression tests
    r1 = new Rotation(0, 0, 0, 0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    r1 = new Rotation(1, 0, 0, 0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    r1 = new Rotation(0, 1, 0, 0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    r1 = new Rotation(0, 0, 1, 0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    r1 = new Rotation(0, 0, 0, 1, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test7() {

    Rotation r = new Rotation(new Vector3D(-2, -2, -2), 2 * FastMath.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(-s, -s, -s));
    checkAngle(r.getAngle(), 2 * FastMath.PI / 3);

    try {
      new Rotation(new Vector3D(0, 0, 0), 2 * FastMath.PI / 3);
      Assert.fail("an exception should have been thrown");
    } catch (ArithmeticException e) {
    }

    r = new Rotation(Vector3D.MINUS_K, 1.5 * FastMath.PI);
    checkVector(r.getAxis(), new Vector3D(0, 0, 1));
    checkAngle(r.getAngle(), 0.5 * FastMath.PI);

    r = new Rotation(Vector3D.PLUS_J, FastMath.PI);
    checkVector(r.getAxis(), Vector3D.MINUS_J);
    checkAngle(r.getAngle(), FastMath.PI);

    checkVector(Rotation.IDENTITY.getAxis(), Vector3D.PLUS_I);

  }
  @Test
  public void test8() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test9() {

    Vector3D u1 = new Vector3D(-3, 0, 0);
    Vector3D u2 = new Vector3D(0, -5, 0);
    Vector3D v1 = new Vector3D(0, 0, -2);
    Vector3D v2 = new Vector3D(2, 0, -2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_K);
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
                     new Vector3D(-0.5, -0.5,  sqrt),
                     new Vector3D(-0.5, -0.5, -sqrt));
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
  public void test10() {
    // Test case 1: q0 < -0.1
    double q0 = -0.2;
    double q1 = 0.4;
    double q2 = 0.6;
    double q3 = 0.8;
    double expectedAngle1 = 2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3));
    double actualAngle1 = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expectedAngle1, actualAngle1, 1e-15);

    // Test case 2: q0 > 0.1
    q0 = 0.3;
    q1 = 0.6;
    q2 = 0.9;
    q3 = 1.2;
    double expectedAngle2 = 2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3));
    double actualAngle2 = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expectedAngle2, actualAngle2, 1e-15);

    // Test case 3: q0 < 0 (else if condition)
    q0 = -0.4;
    q1 = 0.7;
    q2 = 1.0;
    q3 = 1.3;
    double expectedAngle3 = 2 * FastMath.acos(-q0);
    double actualAngle3 = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expectedAngle3, actualAngle3, 1e-15);

    // Test case 4: 0 <= q0 <= 0.1
    q0 = 0.05;
    q1 = 0.1;
    q2 = 0.15;
    q3 = 0.2;
    double expectedAngle4 = 2 * FastMath.acos(q0);
    double actualAngle4 = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expectedAngle4, actualAngle4, 1e-15);
  }
  @Test
  public void test11() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngle = { FastMath.PI / 2, -FastMath.PI / 2 };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 1.0, singularCardanAngle[j], 0.3);
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
        Rotation r = new Rotation(EulerOrders[i], 1.0, singularEulerAngle[j], 0.3);
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
  public void test12() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngle = { FastMath.PI / 2, -FastMath.PI / 2 };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.1, singularCardanAngle[j], 1.0);
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
        Rotation r = new Rotation(EulerOrders[i], 0.1, singularEulerAngle[j], 1.0);
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
      for (double alpha1 = 1.1; alpha1 < 6.2; alpha1 += 0.3) {
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
      for (double alpha1 = 1.1; alpha1 < 6.2; alpha1 += 0.3) {
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
    throws CardanEulerSingularityException {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = 1.55; alpha2 < 1.58; alpha2 += 0.01) {
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
          for (double alpha3 = 1.1; alpha3 < 4.2; alpha3 += 0.3) {
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
    
    // Additional regression tests
    
    double[][] m6 = { { -0.049, 0.786, -0.617 },
                      { 0.819, -0.577, -0.045 },
                      { 0.572, 0.228, 0.789 } };
    r = new Rotation(m6, 1.0e-10);
    
    checkRotation(r, 0.459, 0.785, 0.412, 0.256);
    
    double[][] m7 = { { 0.509838, 0.859536, -0.032052 },
                      { -0.012713, 0.070347, 0.997437 },
                      { 0.860260, -0.506822, 0.067406 } };
    r = new Rotation(m7, 1.0e-10);
    
    checkRotation(r, 0.670, 0.033, 0.720, 0.168);
    
    double[][] m8 = { { 0.858234, 0.486011, -0.165718 },
                      { 0.507357, -0.836351, -0.204140 },
                      { -0.075443, 0.251149, -0.965380 } };
    r = new Rotation(m8, 1.0e-10);
    
    checkRotation(r, 0.314, 0.399, 0.747, 0.416);
    
    double[][] m9 = { { 0.603210, -0.680934, 0.415157 },
                      { 0.717951, 0.704252, 0.002206 },
                      { -0.346012, 0.205419, 0.915194 } };
    r = new Rotation(m9, 1.0e-10);
    
    checkRotation(r, 0.049, 0.607, 0.791, 0.057);
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
  public void test13() {

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

  }
  @Test
  public void test14() {

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
  public void test15() {

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
  public void test16() {

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
  public void test17() {

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
  public void test18() {

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
  public void test19() {

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
  public void test20() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test21() {

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
  public void test22() {

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
  public void test23() {
    try {
      new Rotation(new double[][] {
                     {  1.0,  2.0,  3.0 },
                     {  4.0,  5.0,  6.0 },
                     {  7.0,  8.0,  9.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test24() {
    double[][] m = { { 1.0, 0.0, 0.0 },
                     { 0.0, 1.0, 0.0 },
                     { 0.0, 0.0, 1.0 } };
    Rotation r = new Rotation(m, 1.0e-7);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(u, r.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test25() {
    double[][] m = { { 1.0, 0.0, 0.0 },
                     { 0.0, 1.0, 0.0 },
                     { 0.0, 0.0, 1.0 } };
    Rotation r = new Rotation(m, 1.0e-7);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(u, r.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test26() {
    Vector3D u = new Vector3D(0, 0, 1);
    Vector3D v = new Vector3D(3, -2, 1);
    checkRotation(new Rotation(u, v), 1, 0, 0, 0);
  }
  @Test
  public void test27() {
    Vector3D u = new Vector3D(0, 0, 2);
    Vector3D v = new Vector3D(-2, 0, 2);
    checkRotation(new Rotation(u, v), -1, 0, 0, 0);
  }
  @Test
  public void test28() {
    Vector3D u = new Vector3D(3, -2, 1);
    checkRotation(new Rotation(u, u), 1, 0, 0, 0);
  }
  @Test
  public void test29() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(r1.applyTo(u)),
                      r1.applyTo(r2.applyTo(u)));
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
    
    try {
      double[][] m5 = { { 1.0, 0.0, 0.0 },
                        { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 } };
      r = new Rotation(m5, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }

  }
  @Test
  public void test30() {

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
  public void test31() {

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
  public void test32() {

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
  public void test33() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test34() {

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
  public void test35() {

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
  public void test36() {
  
    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 2.0, 0.0, 0.0 },
                      { 0.0, 0.0, 1.0 } };
    Rotation r = new Rotation(m1, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_I);
  
    double[][] m2 = { { -0.5, -0.866, 0.0 },
                      { -0.866, 0.5, 0.0 },
                      { 0.0, 0.0, 1.0 } };
    r = new Rotation(m2, 1.0e-12);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-0.866, 0.5, 0.0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(-0.5, -0.866, 0.0));
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
  
    double[][] m3 = { { 0.5, -0.866, 0.0 },
                      { 0.866, 0.5, 0.0 },
                      { 0.0, 0.0, 1.0 } };
    r = new Rotation(m3, 1.0e-10);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(0.866, 0.5, 0.0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(-0.5, 0.866, 0.0));
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
  
    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0,  0.0, -1.0 },
                      { 0.0,  1.0,  0.0 } };
    r = new Rotation(m4, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI / 2);
  
    double[][] m5 = { { 0.0, 0.0, -1.0 },
                      { 0.0, 1.0,  0.0 },
                      { 1.0, 0.0,  0.0 } };
    r = new Rotation(m5, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI / 2);
  
  }
checkRotation(new Rotation(new double[][] {
  {  0.445888,  0.797184, -0.407040 },
  { -0.354816,  0.574912,  0.737280 },
  {  0.821760, -0.184320,  0.539200 }
}, 1.0e-10),
checkRotation(new Rotation(new double[][] {
  {  0.539200,  0.737280,  0.407040 },
  {  0.184320, -0.574912,  0.797184 },
  {  0.821760, -0.354816, -0.445888 }
}, 1.0e-10),
checkRotation(new Rotation(new double[][] {
  { -0.445888,  0.797184, -0.407040 },
  {  0.354816,  0.574912,  0.737280 },
  {  0.821760,  0.184320, -0.539200 }
}, 1.0e-10),
checkRotation(new Rotation(new double[][] {
  { -0.539200,  0.737280,  0.407040 },
  { -0.184320, -0.574912,  0.797184 },
  {  0.821760,  0.354816,  0.445888 }
}, 1.0e-10),
double[][] m3 = {
  { -0.426401, -0.657023, -0.621532 },
  { -0.423055,  0.676714, -0.602204 },
  {  0.801944, -0.33260,  0.49800 }
};
  @Test
  public void test37() {

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
  public void test38() {

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

    r1 = new Rotation(new Vector3D(1, -2, 3), 1.2);
    r2 = new Rotation(new Vector3D(-3, 2, -1), 0.1);
    r3 = r2.applyInverseTo(r1);

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

    // additional tests
    double[][] m6 = { { 0.0, -1.0, 0.0 },
                      { 1.0, 0.0, 0.0 },
                      { 0.0, 0.0, 1.0 } };
    r = new Rotation(m6, 1.0e-7);
    checkRotation(r, 0.5, -0.5, 0.5, 0.5);

    double[][] m7 = { { 0.0, 0.0, -1.0 },
                      { 0.0, 1.0, 0.0 },
                      { 1.0, 0.0, 0.0 } };
    r = new Rotation(m7, 1.0e-7);
    checkRotation(r, 0.5, 0.5, -0.5, 0.5);

    double[][] m8 = { { 1.0,  0.0, 0.0 },
                      { 0.0,  0.0, 1.0 },
                      { 0.0, -1.0, 0.0 } };
    r = new Rotation(m8, 1.0e-7);
    checkRotation(r, 0.5, 0.5, 0.5, -0.5);

    double[][] m9 = { { 0.0, 0.0, 1.0 },
                      { 0.0, 1.0, 0.0 },
                      { -1.0, 0.0, 0.0 } };
    r = new Rotation(m9, 1.0e-7);
    checkRotation(r, 0.5, 0.5, 0.5, 0.5);

  }
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
checkRotation(new Rotation(new double[][] {
                             {  0.539200,  0.737280,  0.407040 },
                             {  0.184320, -0.574912,  0.797184 },
                             {  0.821760, -0.354816, -0.445888 }
                          }, 1.0e-10),
checkRotation(new Rotation(new double[][] {
                             { -0.445888,  0.797184, -0.407040 },
                             {  0.354816,  0.574912,  0.737280 },
                             {  0.821760,  0.184320, -0.539200 }
                           }, 1.0e-10),
checkRotation(new Rotation(new double[][] {
                             { -0.539200,  0.737280,  0.407040 },
                             { -0.184320, -0.574912,  0.797184 },
                             {  0.821760,  0.354816,  0.445888 }
                           }, 1.0e-10),
double[][] m1 = { { 0.0, 1.0, 0.0 },
                  { 0.0, 0.0, 1.0 },
                  { 1.0, 0.0, 0.0 } };
double[][] m2 = { { 0.83203, -0.55012, -0.07139 },
                  { 0.48293,  0.78164, -0.39474 },
                  { 0.27296,  0.29396,  0.91602 } };
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
  @Test
  public void test39() {
    Rotation r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    Rotation r2 = new Rotation(0.36, 0.8, 0.288, 0.384, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(1.848, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test40() {
    Rotation r1 = new Rotation(0.445888, 0.797184, -0.407040, 0.8, false);
    Rotation r2 = new Rotation(0.36, 0.8, 0.288, 0.384, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(1.810703538370176, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test41() {
    Rotation r1 = new Rotation(0.445888, 0.797184, -0.407040, 0.8, false);
    Rotation r2 = new Rotation(0.445888, 0.797184, -0.407040, 0.8, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(0.0, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test42() {
    Rotation r1 = new Rotation(0.36, 0.8, 0.288, 0.384, false);
    Rotation r2 = new Rotation(0.384, 0.36, 0.8, 0.288, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(0.1753843229791934, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test43() {
    Rotation r1 = new Rotation(-0.445888, 0.797184, -0.407040, 0.8, false);
    Rotation r2 = new Rotation(0.36, 0.8, 0.288, 0.384, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(1.1286459394169914, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test44() {
    Rotation r1 = new Rotation(-0.539200, 0.737280, 0.407040, 0.8, false);
    Rotation r2 = new Rotation(0.36, 0.8, 0.288, 0.384, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(1.2990369473374927, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test45() {
    Rotation r1 = new Rotation(-0.539200, 0.737280, 0.407040, 0.8, false);
    Rotation r2 = new Rotation(-0.445888, 0.797184, -0.407040, 0.8, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(0.6971320607178187, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test46() {
    Rotation r1 = new Rotation(0.797184, -0.407040, 0.445888, 0.8, false);
    Rotation r2 = new Rotation(-0.539200, 0.737280, 0.407040, 0.8, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(1.3803936377852702, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test47() {
    Rotation r1 = new Rotation(0.821760, -0.184320, 0.539200, 0.975766);
    Rotation r2 = new Rotation(0.36, 0.8, 0.288, 0.384, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(1.5234331405410876, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test48() {
    Rotation r1 = new Rotation(0.821760, -0.184320, 0.539200, 0.975766);
    Rotation r2 = new Rotation(0.445888, 0.797184, -0.407040, 0.8, false);
    Assert.assertEquals(0.0, distance(r1, r1), 1.0e-10);
    Assert.assertEquals(0.0, distance(r2, r2), 1.0e-10);
    Assert.assertEquals(0.9471725594082667, distance(r1, r2), 1.0e-10);
  }
}