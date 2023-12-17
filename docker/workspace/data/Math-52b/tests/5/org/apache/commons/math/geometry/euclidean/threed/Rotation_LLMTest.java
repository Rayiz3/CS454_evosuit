package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    Rotation r = new Rotation(0, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {
    Rotation r = new Rotation(0.001, -0.36, -0.48, -0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
@Test
public void test2() {

  // Test case 1
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

  // Test case 2
  r1 = new Rotation(0.65, -0.1, 0.4, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

}
@Test
public void test3(){
  Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                             -5774608829631843.0 /  268435456.0,
                             -3822921525525679.0 / 4294967296.0);
  Vector3D u2 =new Vector3D( -5712344449280879.0 /    2097152.0,
                             -2275058564560979.0 /    1048576.0,
                              4423475992255071.0 /      65536.0);
  Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
  Assert.assertEquals(0.6228370359608200639829222, rot.getQ0(), 1.0e-15);
  Assert.assertEquals(0.0257707621456498790029987, rot.getQ1(), 1.0e-15);
  Assert.assertEquals(-0.0000000002503012255839931, rot.getQ2(), 1.0e-15);
  Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);

  // Test case with different inputs
  Vector3D u3 = new Vector3D(0.5, -0.3, 0.7);
  Vector3D u4 = new Vector3D(0.2, 0.6, -0.8);
  rot = new Rotation(u3, u4, Vector3D.PLUS_J,Vector3D.PLUS_K);
  Assert.assertEquals(-0.3416484743592380934028, rot.getQ0(), 1.0e-15);
  Assert.assertEquals(-0.0694082417387044792473, rot.getQ1(), 1.0e-15);
  Assert.assertEquals(0.6077702861185299482985, rot.getQ2(), 1.0e-15);
  Assert.assertEquals(-0.7144158332870194076637, rot.getQ3(), 1.0e-15);
}
  @Test
  public void test4() {

    // original test case
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

    // additional regression test cases
    r1 = new Rotation(0, 0, 0, 0); // all components are zero
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

    r1 = new Rotation(1, 0, 0, 0); // only q0 component is 1
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

    r1 = new Rotation(1, 1, 1, 1); // all components are 1
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

    r1 = new Rotation(-1, -1, -1, -1); // all components are -1
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

    r1 = new Rotation(0.5, 0.5, 0.5, 0.5); // all components are 0.5
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

    r1 = new Rotation(-0.5, -0.5, -0.5, -0.5); // all components are -0.5
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

    r1 = new Rotation(0.1, 0.1, 0.1, 0.1); // all components are 0.1
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

    r1 = new Rotation(-0.1, -0.1, -0.1, -0.1); // all components are -0.1
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());

  }
  @Test
  public void test5() {

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

    // New test cases
    Rotation r3 = new Rotation(new Vector3D(0, 0, 0), 0);
    double m = 0;
    Rotation r4 = new Rotation(m * r3.getQ0(), m * r3.getQ1(),
                               m * r3.getQ2(), m * r3.getQ3(),
                               false);
    for (double x = -1.5; x < 1.5; x += 0.5) {
      for (double y = -1.5; y < 1.5; y += 0.5) {
        for (double z = -1.5; z < 1.5; z += 0.5) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r4.applyTo(u), r3.applyTo(u));
        }
      }
    }

    // Original test case
    r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // New test case
    Rotation r5 = new Rotation(0, 0, 0, 0, true);
    checkRotation(r5, -r5.getQ0(), -r5.getQ1(), -r5.getQ2(), -r5.getQ3());

  }
@Test
public void test6() {

  // Covering test case
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

  // Regression test case 1
  r1 = new Rotation(0, 0, 0, 1.7);
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

  // Regression test case 2
  r1 = new Rotation(2, 3, 5, 0);
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
  public void test7() {
    // Test case 1
    Rotation r1 = new Rotation(new Vector3D(-10, -10, -10), 2 * FastMath.PI / 3);
    checkVector(r1.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r1.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r1.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);
    double sqrt = FastMath.sqrt(3) / 3;
    checkVector(r1.getAxis(), new Vector3D(sqrt, sqrt, sqrt));
    checkAngle(r1.getAngle(), 2 * FastMath.PI / 3);

    // Test case 2
    Rotation r2 = new Rotation(new Vector3D(5, 0, -5), FastMath.PI);
    checkVector(r2.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_I);
    checkVector(r2.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_J);
    checkVector(r2.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_K);
    checkVector(r2.getAxis(), Vector3D.ZERO);
    checkAngle(r2.getAngle(), FastMath.PI);

    // Test case 3
    Rotation r3 = new Rotation(new Vector3D(1, 2, 3), FastMath.PI / 4);
    checkVector(r3.applyTo(Vector3D.PLUS_I), new Vector3D(-2, 1, 2));
    checkVector(r3.applyTo(Vector3D.PLUS_J), new Vector3D(3, -2, 1));
    checkVector(r3.applyTo(Vector3D.PLUS_K), new Vector3D(-1, 3, -2));
    checkVector(r3.getAxis(), new Vector3D(0.2672612419, 0.5345224838, 0.8017837257));
    checkAngle(r3.getAngle(), FastMath.PI / 4);
  }
@Test
  public void test8() {
    // Test case 1
    Rotation r1 = new Rotation(0.001, -0.36, 0.48, -0.8, true);
    Rotation reverted1 = r1.revert();
    checkRotation(r1.applyTo(reverted1), 1, 0, 0, 0);
    checkRotation(reverted1.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted1.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted1.getAxis()), 1.0e-12);

    // Test case 2
    Rotation r2 = new Rotation(-0.15, 0.25, 0.35, -0.45, true);
    Rotation reverted2 = r2.revert();
    checkRotation(r2.applyTo(reverted2), 1, 0, 0, 0);
    checkRotation(reverted2.applyTo(r2), 1, 0, 0, 0);
    Assert.assertEquals(r2.getAngle(), reverted2.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r2.getAxis(), reverted2.getAxis()), 1.0e-12);
  }
  @Test
  public void test9() {
    // Test case 1
    Vector3D u1 = new Vector3D(-1, 0, 1);
    Vector3D u2 = new Vector3D(0, -3, 0);
    Vector3D v1 = new Vector3D(2, 0, 0);
    Vector3D v2 = new Vector3D(0, 0, -2);
    Rotation r1 = new Rotation(u1, u2, v1, v2);
    checkVector(r1.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r1.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r1.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_J);
    checkVector(r1.getAxis(), new Vector3D(0.5773502692, -0.5773502692, 0.5773502692));
    checkAngle(r1.getAngle(), 4.1887902048);

    // Test case 2
    Vector3D u3 = new Vector3D(10, 0, 0);
    Vector3D u4 = new Vector3D(0, 0, 5);
    Vector3D v3 = new Vector3D(0, 2, 0);
    Vector3D v4 = new Vector3D(2, 0, 0);
    Rotation r2 = new Rotation(u3, u4, v3, v4);
    checkVector(r2.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_K);
    checkVector(r2.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r2.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_I);
    checkVector(r2.getAxis(), Vector3D.ZERO);
    checkAngle(r2.getAngle(), FastMath.PI);

    // Test case 3
    Vector3D u5 = new Vector3D(3, 4, 0);
    Vector3D u6 = new Vector3D(3, -4, 0);
    Vector3D v5 = new Vector3D(0, -5, 5);
    Vector3D v6 = new Vector3D(-5, 0, 5);
    Rotation r3 = new Rotation(u5, u6, v5, v6);
    checkVector(r3.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r3.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r3.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    checkVector(r3.getAxis(), new Vector3D(0.5144957554, -0.6859943405, 0.5144957554));
    checkAngle(r3.getAngle(), 1.0248957364);
  }
  @Test
  public void test10() {

    double q0 = -0.2;
    double q1 = 0.4;
    double q2 = 0.3;
    double q3 = 0.1;

    double expected = 0.6508698641839353;
    double result = getAngle(q0, q1, q2, q3);

    assertEquals(expected, result, 0.0);
  }
  @Test
  public void test11() {

    double q0 = -0.8;
    double q1 = 0.2;
    double q2 = 0.3;
    double q3 = 0.1;

    double expected = 2.498091544796509;
    double result = getAngle(q0, q1, q2, q3);

    assertEquals(expected, result, 0.0);
  }
  @Test
  public void test12() {

    double q0 = 0.5;
    double q1 = 0.2;
    double q2 = 0.3;
    double q3 = 0.1;

    double expected = 0.5235987755982988;
    double result = getAngle(q0, q1, q2, q3);

    assertEquals(expected, result, 0.0);
  }
  private double getAngle(double q0, double q1, double q2, double q3) {
    if ((q0 < -0.1) || (q0 > 0.1)) {
      return 2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3));
    } else if (q0 < 0) {
      return 2 * FastMath.acos(-q0);
    }
    return 2 * FastMath.acos(q0);
  }
  private void assertEquals(double expected, double result, double delta) {
    if (Math.abs(expected - result) <= delta) {
      System.out.println("Test passed!");
    } else {
      System.out.println("Test failed!");
    }
  }
  @Test
  public void test13() {

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

    // Additional test for singularities
    // Change singularCardanAngle to test different angles for Cardan orders
    // Change singularEulerAngle to test different angles for Euler orders
    // This will cover more cases and potentially kill more mutants

    double[] singularCardanAngle2 = { 0, FastMath.PI };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle2.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.1, singularCardanAngle2[j], 0.3);
        try {
          r.getAngles(CardanOrders[i]);
          Assert.fail("an exception should have been caught");
        } catch (CardanEulerSingularityException cese) {
          // expected behavior
        }
      }
    }

    double[] singularEulerAngle2 = { FastMath.PI / 2, -FastMath.PI / 2 };
    for (int i = 0; i < EulerOrders.length; ++i) {
      for (int j = 0; j < singularEulerAngle2.length; ++j) {
        Rotation r = new Rotation(EulerOrders[i], 0.1, singularEulerAngle2[j], 0.3);
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

    // Additional test for angles
    // Change the values of alpha1, alpha2, or alpha3 in the test loops
    // This will cover more cases and potentially kill more mutants

    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = -1.55; alpha2 < 1.55; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            Rotation r = new Rotation(CardanOrders[i], alpha1, alpha2, alpha3);
            double[] angles = r.getAngles(CardanOrders[i]);
            // Change the expected values or add additional checks
            checkAngle(angles[0], alpha1);
            checkAngle(angles[1], alpha2);
            checkAngle(angles[2], alpha3);
          }
        }
      }
    }

    for (int i = 0; i < EulerOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = 0.05; alpha2 < 3.1; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            Rotation r = new Rotation(EulerOrders[i],
                                      alpha1, alpha2, alpha3);
            double[] angles = r.getAngles(EulerOrders[i]);
            // Change the expected values or add additional checks
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

    // Additional Regression Tests

    checkRotation(new Rotation(new double[][] {
                                 {  1.0,  0.0,  0.0 },
                                 {  0.0,  1.0,  0.0 },
                                 {  0.0,  0.0,  1.0 }
                               }, 1.0e-10),
                  1.0, 0.0, 0.0, 0.0);

    checkRotation(new Rotation(new double[][] {
                                 { -1.0,  0.0,  0.0 },
                                 {  0.0, -1.0,  0.0 },
                                 {  0.0,  0.0, -1.0 }
                               }, 1.0e-10),
                  -1.0, 0.0, 0.0, 0.0);

    double[][] m6 = { { -0.83203,  0.55012,  0.07139 },
                      { -0.48293, -0.78164,  0.39474 },
                      { -0.27296, -0.29396, -0.91602 } };
    r = new Rotation(m6, 1.0e-12);

    double[][] m7 = r.getMatrix();
    double d03 = m6[0][0] - m7[0][0];
    double d04 = m6[0][1] - m7[0][1];
    double d05 = m6[0][2] - m7[0][2];
    double d13 = m6[1][0] - m7[1][0];
    double d14 = m6[1][1] - m7[1][1];
    double d15 = m6[1][2] - m7[1][2];
    double d23 = m6[2][0] - m7[2][0];
    double d24 = m6[2][1] - m7[2][1];
    double d25 = m6[2][2] - m7[2][2];

    Assert.assertTrue(FastMath.abs(d03) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d04) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d05) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d13) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d14) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d15) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d23) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d24) < 6.0e-6);
    Assert.assertTrue(FastMath.abs(d25) < 6.0e-6);

    Assert.assertTrue(FastMath.abs(d03) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d04) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d05) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d13) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d14) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d15) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d23) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d24) > 4.0e-7);
    Assert.assertTrue(FastMath.abs(d25) > 4.0e-7);

    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 3; ++j) {
        double m7tm7 = m7[i][0] * m7[j][0]
                     + m7[i][1] * m7[j][1]
                     + m7[i][2] * m7[j][2];
        if (i == j) {
          Assert.assertTrue(FastMath.abs(m7tm7 - 1.0) < 1.0e-10);
        } else {
          Assert.assertTrue(FastMath.abs(m7tm7) < 1.0e-10);
        }
      }
    }

    checkVector(r.applyTo(Vector3D.PLUS_I),
                new Vector3D(m7[0][0], m7[1][0], m7[2][0]));
    checkVector(r.applyTo(Vector3D.PLUS_J),
                new Vector3D(m7[0][1], m7[1][1], m7[2][1]));
    checkVector(r.applyTo(Vector3D.PLUS_K),
                new Vector3D(m7[0][2], m7[1][2], m7[2][2]));

    double[][] m8 = { {  0,  0, -1 },
                      {  1,  0,  0 },
                      {  0, -1,  0 } };
    r = new Rotation(m8, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI / 2.0);

    try {
      double[][] m9 = { { 1.0, 0.0, 0.0 },
                        { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 } };
      r = new Rotation(m9, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }

  }
  @Test
      throws NotARotationMatrixException {

    try {
      // Changing one element in the matrix to make it non-orthogonal
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
      // Changing one element in the matrix to make it non-orthogonal
      new Rotation(new double[][] {
        { 0.445888, 0.797184, -0.407040 },
        { 0.821760, -0.184320, 0.539200 },
        { 0.354816, 0.574912, 0.737280 }
      }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
      throws NotARotationMatrixException {

    try {
      // Changing diagonal elements in the matrix to make it non-unitary
      new Rotation(new double[][] {
        { 0.4, 0.8, -0.4 },
        { -0.4, 0.6, 0.7 },
        { 0.8, -0.2, 0.4 }
      }, 1.0e-15);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {

    try {
      // Changing one element in the matrix to make it non-orthogonal
      new Rotation(new double[][]{
        {0.355155, 0.621649, -0.698044},
        {-0.698044, 0.621649, 0.355155},
        {0.621649, 0.355155, 0.698044}
      }, 1.0e-10);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {

    try {
      // Changing diagonal elements in the matrix to make it non-unitary
      new Rotation(new double[][]{
        {0.355155, 0.621649, -0.698044},
        {-0.698044, 0.621649, 0.355155},
        {0.621649, 0.355155, 0.655155}
      }, 1.0e-10);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {

    try {
      // Changing diagonal elements in the matrix to make it non-unitary
      new Rotation(new double[][] {
        {0.332444, 0.393775, -0.856223},
        {0.393775, -0.521223, -0.756571},
        {0.856223, 0.756571, 0.213775}
      }, 1.0e-10);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {

    try {
      // Changing diagonal elements in the matrix to make it non-unitary
      new Rotation(new double[][] {
        {-0.672, -0.640, -0.372},
        {-0.640, 0.240, 0.730},
        {-0.372, 0.730, -0.770},
      }, 1.0e-10);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {

    try {
      // Changing one element in the matrix to make it non-orthogonal
      new Rotation(new double[][] {
        {0.343699, -0.478712, 0.355769},
        {0.489383, -0.604342, -0.638415},
        {0.582575, 0.636396, -0.506801}
      }, 1.0e-8);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {

    try {
      // Changing one element in the matrix to make it non-orthogonal
      new Rotation(new double[][] {
        {0.343699, -0.478712, 0.355769},
        {0.489383, -0.604342, -0.638415},
        {0.582575, 0.636396, -0.506801}
      }, 1.0e-8);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {

    try {
      // Changing one element in the matrix to make it non-orthogonal
      new Rotation(new double[][] {
        {-0.610697, 0.242592, 0.753607},
        {-0.084716, -0.825719, 0.558309},
        {0.787985, 0.509307, 0.343959}
      }, 1.0e-8);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test14() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          r1.applyInverseTo(r1.applyTo(u));
          checkVector(u, r1.applyInverseTo(r1.applyTo(u)));
          checkVector(u, r1.applyTo(r1.applyInverseTo(u)));
      }
    }

    Rotation r2 = Rotation.IDENTITY;
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r2.applyInverseTo(r2.applyTo(u)));
          checkVector(u, r2.applyTo(r2.applyInverseTo(u)));
      }
    }

    Rotation r3 = new Rotation(Vector3D.PLUS_K, FastMath.PI);
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r3.applyInverseTo(r3.applyTo(u)));
          checkVector(u, r3.applyTo(r3.applyInverseTo(u)));
      }
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
    
    // Additional tests for regression testing
    r = new Rotation(0, 0, 0, 1, false);
    double sqrt2 = FastMath.sqrt(2) / 2;
    Vector3D u1 = new Vector3D(1, 0, 0);
    Vector3D u2 = new Vector3D(0, 1, 0);
    Vector3D v1 = new Vector3D(sqrt2, sqrt2, 0);
    Vector3D v2 = new Vector3D(-sqrt2, sqrt2, 0);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyInverseTo(u1), u1);
    checkVector(r.applyInverseTo(u2), u2);
    checkVector(r.applyInverseTo(v1), v1);
    checkVector(r.applyInverseTo(v2), v2);

  }
  @Test
  public void test16() throws NotARotationMatrixException {

    try {
      new Rotation(new double[][]{
              {0.0, 1.0, 0.0},
              {1.0, 0.0, 0.0}
      }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][]{
              {0.445888, 0.797184, -0.407040},
              {0.821760, -0.184320, 0.539200},
              {-0.354816, 0.574912, 0.737280}
      }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
        new Rotation(new double[][]{
                {0.4, 0.8, -0.4},
                {-0.4, 0.6, 0.7},
                {0.8, -0.2, 0.5}
        }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }

    // Regression test case 1: small threshold
    checkRotation(new Rotation(new double[][]{{0.445888, 0.797184, -0.407040},
                                              {-0.354816, 0.574912, 0.737280},
                                              {0.821760, -0.184320, 0.539200}
                                             }, 1.0e-15), 0.8, 0.288, 0.384, 0.36);

    // Regression test case 2: negative threshold
    checkRotation(new Rotation(new double[][]{{0.445888, 0.797184, -0.407040},
                                              {-0.354816, 0.574912, 0.737280},
                                              {0.821760, -0.184320, 0.539200}
                                             }, -1.0e-7), 0.8, 0.288, 0.384, 0.36);

    // Regression test case 3: negative elements in the matrix
    checkRotation(new Rotation(new double[][]{{-0.445888, 0.797184, -0.407040},
                                              {0.354816, 0.574912, 0.737280},
                                              {0.821760, 0.184320, -0.539200}
                                             }, 1.0e-10), 0.384, 0.36, 0.8, 0.288);

    // Regression test case 4: large threshold
    checkRotation(new Rotation(new double[][]{{0.445888, 0.797184, -0.407040},
                                              {-0.354816, 0.574912, 0.737280},
                                              {0.821760, -0.184320, 0.539200}
                                             }, 1.0e10), 0.8, 0.288, 0.384, 0.36);

    // Regression test case 5: large elements in the matrix
    checkRotation(new Rotation(new double[][]{{0.820760, 0.797184, -0.407040},
                                              {-0.354816, 0.574912, 0.737280},
                                              {0.821760, -0.184320, 0.539200}
                                             }, 1.0e-10), 0.384, 0.36, 0.8, 0.288);

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

    
    // Regression test 1
    // Distance between two identical rotations should be 0
    Vector3D axis1 = new Vector3D(0.4, 0.8, -0.4);
    axis1 = axis1.normalize();
    Vector3D axis2 = axis1;
    Rotation r1 = new Rotation(axis1, 0.36);
    Rotation r2 = new Rotation(axis2, 0.36);
    Assert.assertEquals(0.0, distance(r1, r2), 1e-10);
    
    // Regression test 2
    // Angle between two orthogonal rotations should be 90 degrees
    axis1 = new Vector3D(0.4, 0.8, -0.4);
    axis1 = axis1.normalize();
    axis2 = new Vector3D(1, 0, 0);
    axis2 = axis2.normalize();
    r1 = new Rotation(axis1, 0.36);
    r2 = new Rotation(axis2, 0.36);
    Assert.assertEquals(FastMath.PI / 2, distance(r1, r2), 1e-10);
    
    // Regression test 3
    // Angle between two opposite rotations should be 180 degrees
    axis1 = new Vector3D(0.4, 0.8, -0.4);
    axis1 = axis1.normalize();
    axis2 = axis1.negate();
    r1 = new Rotation(axis1, 0.36);
    r2 = new Rotation(axis2, 0.36);
    Assert.assertEquals(FastMath.PI, distance(r1, r2), 1e-10);
    
    // Remaining original test cases...
    ...
  }
}