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
    Rotation r = new Rotation(0, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test2() {
    Rotation r = new Rotation(0.001, -0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
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

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Regression test case 1
    r1 = new Rotation(new Vector3D(0, 1, 0), 0.0);
    Assert.assertEquals(Math.sqrt(2) / 2, r1.getQ0(), 1.0e-15);
    Assert.assertEquals(0.0, r1.getQ1(), 1.0e-15);
    Assert.assertEquals(Math.sqrt(2) / 2, r1.getQ2(), 1.0e-15);
    Assert.assertEquals(0.0, r1.getQ3(), 1.0e-15);

    // Regression test case 2
    r1 = new Rotation(new Vector3D(1, 0, 0), 2 * Math.PI);
    Assert.assertEquals(1.0, r1.getQ0(), 1.0e-15);
    Assert.assertEquals(0.0, r1.getQ1(), 1.0e-15);
    Assert.assertEquals(0.0, r1.getQ2(), 1.0e-15);
    Assert.assertEquals(0.0, r1.getQ3(), 1.0e-15);

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
    Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);

    // Regression test case 1
    u1 = new Vector3D(0.0, 0.0, 1.0);
    u2 = new Vector3D(1.0, 0.0, 0.0);
    rot = new Rotation(u1, u2, Vector3D.PLUS_I, Vector3D.PLUS_J);
    Assert.assertEquals(0.0, rot.getQ0(), 1.0e-15);
    Assert.assertEquals(0.0, rot.getQ1(), 1.0e-15);
    Assert.assertEquals(1.0, rot.getQ2(), 1.0e-15);
    Assert.assertEquals(0.0, rot.getQ3(), 1.0e-15);

    // Regression test case 2
    u1 = new Vector3D(1.0, 0.0, 0.0);
    u2 = new Vector3D(0.0, 1.0, 0.0);
    rot = new Rotation(u1, u2, Vector3D.PLUS_J, Vector3D.PLUS_K);
    Assert.assertEquals(0.0, rot.getQ0(), 1.0e-15);
    Assert.assertEquals(0.0, rot.getQ1(), 1.0e-15);
    Assert.assertEquals(0.0, rot.getQ2(), 1.0e-15);
    Assert.assertEquals(1.0, rot.getQ3(), 1.0e-15);

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
    
    // Additional test case #1
    r1 = new Rotation(new Vector3D(1, 1, 1), 0);
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
    
    // Additional test case #2
    r1 = new Rotation(new Vector3D(0, 0, 0), 1);
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
    
    // Additional test case #3
    r1 = new Rotation(new Vector3D(1, 0, 0), 0);
    n = 1;
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

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
  }
  @Test
  public void test6() {

    // Original Test Case
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

    // Regression Test Cases
    r1 = new Rotation(new Vector3D(0, 0, 0), 0);  // Test case with zero vector and zero angle
    n = -10;  // Change the value of n to a negative number
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -0.9; x < 0.9; x += 0.1) {  // Change the increment value of x
      for (double y = -0.9; y < 0.9; y += 0.1) {
        for (double z = -0.9; z < 0.9; z += 0.1) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Regression Test Case
    r1 = new Rotation(new Vector3D(1, 2, 3), 0);  // Test case with zero angle
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Regression Test Case
    r1 = new Rotation(new Vector3D(0, 0, 1), 0.5);  // Test case with non-zero angle
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test7() {

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
    
    // Additional test cases to kill more mutants
    r1 = new Rotation(new Vector3D(5, 0, 0), 1.7);
    n = 1.5;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      false);
    for (double x = -2; x < 2; x += 0.5) {
      for (double y = -2; y < 2; y += 0.5) {
        for (double z = -2; z < 2; z += 0.5) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation(1, 0, 0, 0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test8() {

    Rotation r = new Rotation(new Vector3D(5, 5, 5), Math.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), Math.PI / 3);

    try {
      new Rotation(new Vector3D(1, 2, 3), Math.PI / 2);
      Assert.fail("an exception should have been thrown");
    } catch (ArithmeticException e) {
    }

    r = new Rotation(Vector3D.PLUS_K, Math.PI);
    checkVector(r.getAxis(), new Vector3D(0, 0, -1));
    checkAngle(r.getAngle(), Math.PI);

    r = new Rotation(Vector3D.PLUS_J, Math.PI / 2);
    checkVector(r.getAxis(), Vector3D.PLUS_J);
    checkAngle(r.getAngle(), Math.PI / 2);

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

    Vector3D u1 = new Vector3D(1, 0, 0);
    Vector3D u2 = new Vector3D(0, 2, 0);
    Vector3D v1 = new Vector3D(0, 0, 1);
    Vector3D v2 = new Vector3D(-1, 0, 1);
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
    checkAngle(r.getAngle(), Math.PI);

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
    Rotation r = new Rotation(0.1, 0.2, 0.3, 0.4, true);
    double angle = r.getAngle();
    Assert.assertEquals(0.0, angle, 1.0e-15);
  }
  @Test
  public void test12() {
    Rotation r = new Rotation(-0.1, -0.2, -0.3, -0.4, true);
    double angle = r.getAngle();
    Assert.assertEquals(0.0, angle, 1.0e-15);
  }
  @Test
  public void test13() {
    Rotation r = new Rotation(0.05, 0.1, 0.15, 0.2, true);
    double angle = r.getAngle();
    Assert.assertEquals(0.0, angle, 1.0e-15);
  }
  @Test
  public void test14() {
    Rotation r = new Rotation(-0.05, -0.1, -0.15, -0.2, true);
    double angle = r.getAngle();
    Assert.assertEquals(0.0, angle, 1.0e-15);
  }
  @Test
  public void test15() {
    Rotation r = new Rotation(0.01, 0.02, 0.03, 0.04, true);
    double angle = r.getAngle();
    Assert.assertEquals(2.0, angle, 1.0e-15);
  }
  @Test
  public void test16() {
    Rotation r = new Rotation(-0.01, -0.02, -0.03, -0.04, true);
    double angle = r.getAngle();
    Assert.assertEquals(2.0, angle, 1.0e-15);
  }
  @Test
  public void test17() {
    Rotation r = new Rotation(0.0, 0.0, 0.0, 1.0, true);
    double angle = r.getAngle();
    Assert.assertEquals(0.0, angle, 1.0e-15);
  }
  @Test
  public void test18() {
    Rotation r = new Rotation(0.0, 1.0, 0.0, 0.0, true);
    double angle = r.getAngle();
    Assert.assertEquals(0.0, angle, 1.0e-15);
  }
@Test
public void test19() {

  RotationOrder[] CardanOrders = {
    RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
    RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
  };

  // Test with extreme values of v2.Z() for CardanOrders
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

  // Test with extreme values of v2.X() for EulerOrders
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

  // Test with extreme values of alpha1, alpha2, alpha3 for CardanOrders
  for (int i = 0; i < CardanOrders.length; ++i) {
    for (double alpha1 = -FastMath.PI; alpha1 <= FastMath.PI; alpha1 += FastMath.PI / 2) {
      for (double alpha2 = -FastMath.PI / 2; alpha2 <= FastMath.PI / 2; alpha2 += FastMath.PI / 2) {
        for (double alpha3 = -FastMath.PI; alpha3 <= FastMath.PI; alpha3 += FastMath.PI / 2) {
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

  // Test with extreme values of alpha1, alpha2, alpha3 for EulerOrders
  for (int i = 0; i < EulerOrders.length; ++i) {
    for (double alpha1 = -FastMath.PI; alpha1 <= FastMath.PI; alpha1 += FastMath.PI / 2) {
      for (double alpha2 = -FastMath.PI; alpha2 <= FastMath.PI; alpha2 += FastMath.PI / 2) {
        for (double alpha3 = -FastMath.PI; alpha3 <= FastMath.PI; alpha3 += FastMath.PI / 2) {
          Rotation r = new Rotation(EulerOrders[i], alpha1, alpha2, alpha3);
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
    // Change input values of method and generate new test cases

    try {
        new Rotation(new double[][] {
                       {  -0.4,  0.8, -0.4 },
                       {   0.4,  0.6,  0.7 },
                       {   0.8, -0.2,  0.5 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

    double[][] m6 = { { 1.0,  0.0,  0.0 },
                      { 0.0,  1.0,  0.0 },
                      { 0.0,  0.0,  1.0 } };
    r = new Rotation(m6, 1.0e-7);
    checkAngle(r.getAngle(), 0.0);

    double[][] m7 = { { 0.7071, -0.7071, 0.0 },
                      { 0.7071,  0.7071, 0.0 },
                      { 0.0,     0.0,   1.0 } };
    r = new Rotation(m7, 1.0e-7);
    checkAngle(r.getAngle(), 0.785398163);

    double[][] m8 = { { 0.0, -1.0, 0.0 },
                      { 1.0,  0.0, 0.0 },
                      { 0.0,  0.0, 1.0 } };
    r = new Rotation(m8, 1.0e-7);
    checkAngle(r.getAngle(), 1.57079633);

  }
  @Test
    throws NotARotationMatrixException {

    try {
      new Rotation(new double[][] {
                     { 0.0, 1.1, 0.0 },
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
                     { -0.354816,  0.574912,  0.837280 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
        new Rotation(new double[][] {
                       {  0.4,  0.8, -0.4 },
                       { -0.4,  0.6,  0.7 },
                       {  0.8, -0.2,  0.3 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.284320,  0.539200 }
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
  public void test20() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyTo(r1);

    for (double x = -1.1; x < 1.1; x += 0.2) {
      for (double y = -1.1; y < 1.1; y += 0.2) {
        for (double z = -1.1; z < 1.1; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

  }
  @Test
  public void test21() {

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
  public void test22() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1.1; x < 1.1; x += 0.2) {
      for (double y = -1.1; y < 1.1; y += 0.2) {
        for (double z = -1.1; z < 1.1; z += 0.2) {
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
  public void test25() {

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
  public void test26() {

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
  public void test27() {
    Rotation r = new Rotation(0.008, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test28() {

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
  public void test29() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    Rotation r3 = r2.applyInverseTo(r1);

    for (double x = -1.1; x < 1.1; x += 0.2) {
      for (double y = -1.1; y < 1.1; y += 0.2) {
        for (double z = -1.1; z < 1.1; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

  }
  @Test
  public void test30() {
    Rotation r = new Rotation(new double[][] {
                               {  0.445888,  0.797184, -0.407040 },
                               { -0.354816,  0.574912,  0.737280 },
                               {  0.821760, -0.184320,  0.539200 }
                             }, 1.0e-10);
    Vector3D u = new Vector3D(0, 0, 0);
    checkVector(r.applyInverseTo(u), u);
  }
  @Test
  public void test31() {
    Rotation r = new Rotation(new double[][] {
                               {  0.539200,  0.737280,  0.407040 },
                               {  0.184320, -0.574912,  0.797184 },
                               {  0.821760, -0.354816, -0.445888 }
                             }, 1.0e-10);
    Vector3D u = new Vector3D(1, 1, 1);
    checkVector(r.applyInverseTo(u), new Vector3D(3.162239, -0.931877, 0.039801));
  }
  @Test
  public void test32() {
    Rotation r = new Rotation(new double[][] {
                               { -0.445888,  0.797184, -0.407040 },
                               {  0.354816,  0.574912,  0.737280 },
                               {  0.821760,  0.184320, -0.539200 }
                             }, 1.0e-10);
    Vector3D u = new Vector3D(-2, -1, 4);
    checkVector(r.applyInverseTo(u), new Vector3D(1.222528, -2.201256, 1.123808));
  }
  @Test
  public void test33() {
    Rotation r = new Rotation(new double[][] {
                               { -0.539200,  0.737280,  0.407040 },
                               { -0.184320, -0.574912,  0.797184 },
                               {  0.821760,  0.354816,  0.445888 }
                             }, 1.0e-10);
    Vector3D u = new Vector3D(-3, 2, -5);
    checkVector(r.applyInverseTo(u), new Vector3D(-1.326070, 1.681914, -1.030475));
  }
  @Test
  public void test34() {
    Rotation r = new Rotation(new double[][] {
                               { 0.0, 1.0, 0.0 },
                               { 1.0, 0.0, 0.0 },
                               { 0.0, 0.0, 1.0 }
                             }, 1.0e-7);
    Vector3D u = new Vector3D(0, 0, -1);
    checkVector(r.applyInverseTo(u), new Vector3D(1, 0, 0));
  }
  @Test
  public void test35() {
    Rotation r = new Rotation(new double[][] {
                               { 0.832030, -0.550120, -0.071390 },
                               { 0.482930,  0.781640, -0.394740 },
                               { 0.272960,  0.293960,  0.916020 }
                             }, 1.0e-12);
    Vector3D u = new Vector3D(-1, 3, 2);
    checkVector(r.applyInverseTo(u), new Vector3D(2.508610, 2.931653, -1.249934));
  }
  @Test
  public void test36() {
    Rotation r = new Rotation(new double[][] {
                               { 1.0, 0.0, 0.0 },
                               { 0.0, -1.0, 0.0 },
                               { 0.0, 0.0, -1.0 }
                             }, 1.0e-7);
    Vector3D u = new Vector3D(0, 0, 0);
    checkVector(r.applyInverseTo(u), u);
  }
  @Test
  public void test37() {
    Rotation r = new Rotation(new double[][] {
                               { 0.0, 0.0, 1.0 },
                               { 0.0, 1.0, 0.0 },
                               { 1.0, 0.0, 0.0 }
                             }, 1.0e-7);
    Vector3D u = new Vector3D(1, -1, 2);
    checkVector(r.applyInverseTo(u), new Vector3D(2, -1, 1));
  }
  @Test
  public void test38() {
    Rotation r = new Rotation(new double[][] {
                               { 0.0, -1.0, 0.0 },
                               { 1.0,  0.0, 0.0 },
                               { 0.0,  0.0, 1.0 }
                             }, 1.0e-7);
    Vector3D u = new Vector3D(-3, 2, -1);
    checkVector(r.applyInverseTo(u), new Vector3D(2, 3, -1));
  }
  @Test
  public void test39() {
    Rotation r = new Rotation(new double[][] {
                               { 1.0, 0.0, 0.0 },
                               { 0.0, 0.0, 1.0 },
                               { 0.0, 1.0, 0.0 }
                             }, 1.0e-7);
    Vector3D u = new Vector3D(-2, -3, -4);
    checkVector(r.applyInverseTo(u), new Vector3D(-2, -4, -3));
  }
@Test
  throws NotARotationMatrixException {

// Regression test case 1
  new Rotation(new double[][] {
                     { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);

//  Regression test case 2
new Rotation(new double[][] {
                     {  0.445888,  0.797184, -0.407040 },
                     {  0.821760, -0.184320,  0.539200 },
                     { -0.354816,  0.574912,  0.737280 }
                   }, 1.0e-7);

//  Regression test case 3
new Rotation(new double[][] {
                       { 0.4,  0.8, -0.4 },
                       { -0.4,  0.6,  0.7 },
                       { 0.8, -0.2,  0.5 }
                     }, 1.0e-15);
  
...
}
  @Test
  public void test40() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(0.1, 0.2, 0.3);
    Vector3D expected = new Vector3D(-0.0017082097787766602, 0.15553006754514591, 0.08317405839235342);
    Assert.assertEquals(expected, r.applyInverseTo(u));

  }
  @Test
  public void test41() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(-0.3, 0.5, -0.7);
    Vector3D expected = new Vector3D(-0.3244976154616288, -0.21005187625978342, -0.21005187625978345);
    Assert.assertEquals(expected, r.applyInverseTo(u));

  }
  @Test
  public void test42() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(0.0, 0.0, 0.0);
    Vector3D expected = new Vector3D(0.0, 0.0, 0.0);
    Assert.assertEquals(expected, r.applyInverseTo(u));

  }
  @Test
  public void test43() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(1.0, 1.0, 1.0);
    Vector3D expected = new Vector3D(-0.23369923597027539, 0.037198315950170604, 0.6382616991187769);
    Assert.assertEquals(expected, r.applyInverseTo(u));

  }
  @Test
  public void test44() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(-1.0, -1.0, -1.0);
    Vector3D expected = new Vector3D(-0.23369923597027542, 0.37040244974947463, 0.20002719234455844);
    Assert.assertEquals(expected, r.applyInverseTo(u));

  }
@Test
  throws NotARotationMatrixException {

  try {
    new Rotation(new double[][] {
                   { 0.0, 1.0, 0.0, 0.0 },    // Extra element in the matrix
                   { 1.0, 0.0, 0.0 }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  // ...

}
@Test
  throws NotARotationMatrixException {

  // ...

  try {
    new Rotation(new double[][] {
                   { 0.539200,  0.737280,  0.407040 },
                   {  0.184320, -0.574912,  0.797184 },
                   {  0.821760, -0.354816, -0.445888 },
                   { 0.0, 0.0, 1.0 }             // Extra element in the matrix
                 }, 1.0e-10);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  // ...

}
@Test
  throws NotARotationMatrixException {

  try {
    new Rotation(new double[][] {
                   { 0.0, 1.0, 0.0, 0.0 },    // Extra element in the matrix
                   { 1.0, 0.0, 0.0 }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  // ...

}
@Test
  throws NotARotationMatrixException {

  // ...

  try {
    new Rotation(new double[][] {
                   { 0.539200,  0.737280,  0.407040 },
                   {  0.184320, -0.574912,  0.797184 },
                   {  0.821760, -0.354816, -0.445888 },
                   { 0.0, 0.0, 1.0 }             // Extra element in the matrix
                 }, 1.0e-10);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  // ...

}
    throws NotARotationMatrixException {
    
    // Modify the matrix to create a NotARotationMatrixException
    try {
      new Rotation(new double[][] {
                     { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    
    // Modify the matrix to create a NotARotationMatrixException
    try {
      new Rotation(new double[][] {
                     {  0.445888,  0.797184, -0.407040 },
                     {  0.821760, -0.184320,  0.539200 },
                     { -0.354816,  0.574912,  0.737280 }
                   }, 1.0e-7);
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    
    // Modify the matrix to create a NotARotationMatrixException
    try {
        new Rotation(new double[][] {
                       {  0.4,  0.8, -0.4 },
                       { -0.4,  0.6,  0.7 },
                       {  0.8, -0.2,  0.5 }
                     }, 1.0e-15);
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }
    
    // Modify the matrix to check the angle calculation
    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  0.9, 0.288, 0.384, 0.36);

    // Modify the matrix to check the angle calculation
    checkRotation(new Rotation(new double[][] {
                                 {  0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-10),
                  2.36, 0.8, 0.288, 0.384);

    // Modify the matrix to check the angle calculation
    checkRotation(new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10),
                  0.384, 1.36, 0.8, 0.288);

    // Modify the matrix to check the angle calculation
    checkRotation(new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 { -0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10),
                  0.288, 0.384, 4.36, 0.8);
  }
  testVectorTwoPairs() {
    
    // Modify u1 to change the rotation
    Vector3D u1 = new Vector3D(4, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    // Modify u1 to change the rotation
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

    // Modify u1 and u2 to change the rotation
    checkRotation(new Rotation(u1, u2, u1, u2), 1, 0, 0, 0);

    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }
  }
  testRevert() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    
    // Modify the input values of r.revert() to check the reversal
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  testQuaternion() {

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
}