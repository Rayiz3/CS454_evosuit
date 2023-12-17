package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
@Test
public void test0() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test1() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test2() {
    Rotation r = new Rotation(-0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test3() {
    Rotation r = new Rotation(0, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
  @Test
  public void test4() {

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
  }
  @Test
  public void test6(){
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.1; x < 0.9; x += 0.2) {
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
  public void test7(){
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

    r1 = new Rotation( 0.588,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
  }
  @Test
  public void test8() {

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

    // Regression test case 1 - changing input value of x and y
    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Regression test case 2 - changing input value of n
    n = 10.0;
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

    // Regression test case 3 - changing input value of z
    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 1.1; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Regression test case 4 - changing input value of n and z
    n = 15.0;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 1.1; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test9() {

    // Test case 1: q2 is set to 0.0
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               0.0, n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Test case 2: q2 is set to -1.0
    r1 = new Rotation(new Vector3D(2, -3, 5), -1.7);
    n = 23.5;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      -1.0, n * r1.getQ3(),
                      true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Test case 3: q2 is set to 1.0
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    n = 23.5;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      1.0, n * r1.getQ3(),
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
public void test10() {
  Rotation r = new Rotation(new Vector3D(1, 2, 3), 0.5);

  // Test case 1 - increased q3
  r.setQ3(1.5);
  assertEquals(1.5, r.getQ3(), 0.0001);

  // Test case 2 - decreased q3
  r.setQ3(-0.5);
  assertEquals(-0.5, r.getQ3(), 0.0001);

  // Test case 3 - zero q3
  r.setQ3(0);
  assertEquals(0, r.getQ3(), 0.0001);

  // Test case 4 - maximum allowed q3 value
  r.setQ3(Double.MAX_VALUE);
  assertEquals(Double.MAX_VALUE, r.getQ3(), 0.0001);

  // Test case 5 - minimum allowed q3 value
  r.setQ3(Double.MIN_VALUE);
  assertEquals(Double.MIN_VALUE, r.getQ3(), 0.0001);
}
@Test
public void test11() {

  // Existing test case
  Rotation r = new Rotation(new Vector3D(10, 10, 10), 2 * FastMath.PI / 3);
  checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
  checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
  checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
  double s = 1 / FastMath.sqrt(3);
  checkVector(r.getAxis(), new Vector3D(s, s, s));
  checkAngle(r.getAngle(), 2 * FastMath.PI / 3);

  // Test case with q0 = 0 and squaredSine = 0
  r = new Rotation(new Vector3D(0, 0, 0), 2 * FastMath.PI);
  checkVector(r.getAxis(), Vector3D.PLUS_I);
  checkAngle(r.getAngle(), 0);

  // Test case with squaredSine = 0 and q0 < 0
  r = new Rotation(new Vector3D(-5, -5, -5), FastMath.PI);
  checkVector(r.getAxis(), new Vector3D(-0.577, -0.577, -0.577));
  checkAngle(r.getAngle(), 3.141592653589793);

  // Test case with squaredSine != 0 and q0 >= 0
  r = new Rotation(new Vector3D(-5, -5, -5), FastMath.PI / 2);
  checkVector(r.getAxis(), new Vector3D(-0.577, -0.577, -0.577));
  checkAngle(r.getAngle(), 1.5707963267948966);

  // Test case with squaredSine != 0 and q0 < 0
  r = new Rotation(new Vector3D(-10, -10, -10), -FastMath.PI / 4);
  checkVector(r.getAxis(), new Vector3D(0.577, 0.577, 0.577));
  checkAngle(r.getAngle(), 0.7853981633974483);

  

}
@Test
public void test12() {
  // Existing test case
  Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);

  // Additional test case
  r = new Rotation(0.2, 0.4, -0.7, 0.5, true);
  reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test13() {
  
  // Existing test case
  Vector3D u1 = new Vector3D(3, 0, 0);
  Vector3D u2 = new Vector3D(0, 5, 0);
  Vector3D v1 = new Vector3D(0, 0, 2);
  Vector3D v2 = new Vector3D(-2, 0, 2);
  Rotation r = new Rotation(u1, u2, v1, v2);
  checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
  checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

  // Additional test case with v1 and v2 swapped
  r = new Rotation(u1, u2, v2, v1);
  checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_K);
  checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);

  // Test case with u1 and u2 swapped
  r = new Rotation(u2, u1, v1, v2);
  checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
  checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

  // Test case with u1, u2, v1, and v2 all negated
  r = new Rotation(u1.negate(), u2.negate(), v1.negate(), v2.negate());
  checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
  checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

  // Test case with u1 and u2 in the opposite direction to v1 and v2
  r = new Rotation(u1, u2, v1.negate(), v2.negate());
  checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
  checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

}
  @Test
  public void test14() {
    // Mutant that changes the comparison operator
    Assert.assertEquals(2 * FastMath.asin(FastMath.sqrt(0.16 + 0.09 + 0.04)), getAngle(), 1.0e-10);
    // Mutant that changes the order of the operands
    Assert.assertEquals(2 * FastMath.acos(-0.1), getAngle(), 1.0e-10);
  }
@Test
public void test15() {

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
public void test16() {

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
private void checkAngle(double angle, double expected) {
  double twoPi = 2 * FastMath.PI;
  double tol = 10 * FastMath.ulp(1d);
  while (angle < 0) {
    angle += twoPi;
  }
  while (angle > twoPi) {
    angle -= twoPi;
  }
  if (angle < expected - tol || angle > expected + tol) {
    Assert.fail("Angle " + angle + " is not within expected range " +
                (expected - tol) + " to " + (expected + tol));
  }
}
  @Test
    throws NotARotationMatrixException {

    try {
      new Rotation(new double[][] {
                     { 0.0, 1.0, 0.0 },  // Change to { 0.0, 0.0, 1.0 }
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    
    ...

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
  public void test17() {
    Vector3D u = new Vector3D(-2, 3, 4);
    checkVector(new Vector3D(0.798533, -0.239560, 0.552442), applyTo(u));
  }
  @Test
  public void test18() {
    Vector3D u = new Vector3D(1, -1, 1);
    checkVector(new Vector3D(-1.115365, 1.645919, -2.194559), applyTo(u));
  }
  @Test
  public void test19() {
    Vector3D u = new Vector3D(0.5, -0.5, 0.5);
    checkVector(new Vector3D(-0.646231, -0.923880, 0.169101), applyTo(u));
  }
  @Test
  public void test20() {
    Vector3D u = new Vector3D(0, 0, 1);
    checkVector(new Vector3D(0.707106, -0.292893, -0.646457), applyTo(u));
  }
  @Test
  public void test21() {
    Vector3D u = new Vector3D(2, 0, 0);
    checkVector(new Vector3D(-1.292893, -0.707106, 0), applyTo(u));
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
  public void test22() {

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
  public void test23() {

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
  public void test24() {

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
  public void test25() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test26() {

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
  public void test27() {

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
  public void test28() {
    Rotation r = new Rotation(0.0, 0.0, 0.0, 0.0, false);
    Rotation result = r.applyTo(r);
    Assert.assertEquals(0.0, result.getQ0(), 1e-7);
    Assert.assertEquals(0.0, result.getQ1(), 1e-7);
    Assert.assertEquals(0.0, result.getQ2(), 1e-7);
    Assert.assertEquals(0.0, result.getQ3(), 1e-7);
  }
  @Test
  public void test29() {
    Rotation r = new Rotation(-0.5, 0.3, -0.4, 0.2, true);
    Rotation result = r.applyTo(r);
    Assert.assertEquals(0.0, result.getQ0(), 1e-7);
    Assert.assertEquals(0.0, result.getQ1(), 1e-7);
    Assert.assertEquals(0.0, result.getQ2(), 1e-7);
    Assert.assertEquals(0.0, result.getQ3(), 1e-7);
  }
  @Test
  public void test30() {
    Rotation r = new Rotation(5.0, 10.0, -2.0, 0.6, false);
    Rotation result = r.applyTo(r);
    Assert.assertEquals(0.0, result.getQ0(), 1e-7);
    Assert.assertEquals(0.0, result.getQ1(), 1e-7);
    Assert.assertEquals(0.0, result.getQ2(), 1e-7);
    Assert.assertEquals(0.0, result.getQ3(), 1e-7);
  }
  @Test
  public void test31() {
    Rotation r = new Rotation(0, 0, 0, 0, false);
    Rotation r1 = new Rotation(1, 2, 3, 4, false);
    Rotation expected = new Rotation(0, 0, 0, 0, false);
    Rotation result = r.applyInverseTo(r1);
    Assert.assertEquals(expected.getQ0(), result.getQ0(), 1.0e-12);
    Assert.assertEquals(expected.getQ1(), result.getQ1(), 1.0e-12);
    Assert.assertEquals(expected.getQ2(), result.getQ2(), 1.0e-12);
    Assert.assertEquals(expected.getQ3(), result.getQ3(), 1.0e-12);
  }
  @Test
  public void test32() {
    Rotation r = new Rotation(0, 0, 0, 0, false);
    Rotation r1 = new Rotation(0, 0, 0, 0, false);
    Rotation expected = new Rotation(0, 0, 0, 0, false);
    Rotation result = r.applyInverseTo(r1);
    Assert.assertEquals(expected.getQ0(), result.getQ0(), 1.0e-12);
    Assert.assertEquals(expected.getQ1(), result.getQ1(), 1.0e-12);
    Assert.assertEquals(expected.getQ2(), result.getQ2(), 1.0e-12);
    Assert.assertEquals(expected.getQ3(), result.getQ3(), 1.0e-12);
  }
  @Test
  public void test33() {
    Rotation r = new Rotation(0.5, -0.3, 0.7, 0.2, false);
    Rotation r1 = new Rotation(1, 1, 1, 1, false);
    Rotation expected = new Rotation(-0.5, 0.3, -0.7, -0.2, false);
    Rotation result = r.applyInverseTo(r1);
    Assert.assertEquals(expected.getQ0(), result.getQ0(), 1.0e-12);
    Assert.assertEquals(expected.getQ1(), result.getQ1(), 1.0e-12);
    Assert.assertEquals(expected.getQ2(), result.getQ2(), 1.0e-12);
    Assert.assertEquals(expected.getQ3(), result.getQ3(), 1.0e-12);
  }
  @Test
  public void test34() {
    Rotation r = new Rotation(1, -0.5, 0.2, 0.7, true);
    Rotation r1 = new Rotation(-0.5, 0.7, -0.2, 1, true);
    Rotation expected = new Rotation(-0.91534, -0.396095, -0.04494, -0.0081, false);
    Rotation result = r.applyInverseTo(r1);
    Assert.assertEquals(expected.getQ0(), result.getQ0(), 1.0e-12);
    Assert.assertEquals(expected.getQ1(), result.getQ1(), 1.0e-12);
    Assert.assertEquals(expected.getQ2(), result.getQ2(), 1.0e-12);
    Assert.assertEquals(expected.getQ3(), result.getQ3(), 1.0e-12);
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

    // Regression test cases

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
                       {  0.4,  0.8, -0.4 },
                       { -0.4,  0.6,  0.7 },
                       {  0.8, -0.2,  0.5 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

    double[][] m6 = { { 0.5, 0.87, -0.87 },
                      { 0.87, -0.5, 0.5 },
                      { -0.87, 0.5, 0.5 } };
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

    try {
      double[][] m8 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m8, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }

  }
  @Test
  public void test35() {
    Rotation r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    Rotation r2 = new Rotation(0.288, 0.384, 0.36, -0.8, false);
    Assert.assertEquals(2 * FastMath.PI, distance(r1, r2), 1.0e-12);
    
    r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    r2 = new Rotation(-0.288, 0.384, 0.36, 0.8, false);
    Assert.assertEquals(FastMath.PI, distance(r1, r2), 1.0e-12);
    
    r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    r2 = new Rotation(0.288, 0.0, 0.0, 0.0, true);
    Assert.assertEquals(FastMath.PI, distance(r1, r2), 1.0e-12);
    
    r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    r2 = new Rotation(0.288, 0.384, 0.36, 0.8, true);
    Assert.assertEquals(0, distance(r1, r2), 1.0e-12);
  }
}