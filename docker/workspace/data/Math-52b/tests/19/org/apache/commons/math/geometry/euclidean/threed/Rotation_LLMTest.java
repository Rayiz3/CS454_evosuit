package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    // Test case 1
    Rotation r1 = new Rotation(0, 0, 0, 1, true);
    Rotation reverted1 = r1.revert();
    checkRotation(r1.applyTo(reverted1), 1, 0, 0, 0);
    checkRotation(reverted1.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted1.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted1.getAxis()), 1.0e-12);
    
    // Test case 2
    Rotation r2 = new Rotation(0, 0, 1, 1, true);
    Rotation reverted2 = r2.revert();
    checkRotation(r2.applyTo(reverted2), 1, 0, 0, 0);
    checkRotation(reverted2.applyTo(r2), 1, 0, 0, 0);
    Assert.assertEquals(r2.getAngle(), reverted2.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r2.getAxis(), reverted2.getAxis()), 1.0e-12);
    
    // Test case 3
    Rotation r3 = new Rotation(0.01, 0.6, 0.4, 0.8, true);
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
      Assert.assertEquals(-0.0000000002503012255839931, rot.getQ2(), 1.0e-15);
      Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);
  }
  @Test
  public void test3() {

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
  public void test4(){
      Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                                 -5774608829631843.0 /  268435456.0,
                                 -3822921525525679.0 / 4294967296.0);
      Vector3D u2 =new Vector3D( -5712344449280879.0 /    2097152.0,
                                 -2275058564560979.0 /    1048576.0,
                                  4423475992255071.0 /      65536.0);
      Rotation rot = new Rotation(u2, u1, Vector3D.PLUS_I,Vector3D.PLUS_K);
      Assert.assertEquals( 0.6228370359608200639829222, rot.getQ0(), 1.0e-15);
      Assert.assertEquals( 0.0257707621456498790029987, rot.getQ1(), 1.0e-15);
      Assert.assertEquals(-0.0000000002503012255839931, rot.getQ2(), 1.0e-15);
      Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);
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
    
    // Regression test case to kill more mutants
    r1 = new Rotation(new Vector3D(0, 0, 0), 0);
    n = 10;
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
    
    // Regression test case to kill more mutants
    r1 = new Rotation(new Vector3D(1, 2, 3), 0);
    n = 5;
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

    // Original test cases
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
    // Changing the input value of q2
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double newQ2 = 0.5;
    r2 = new Rotation(r1.getQ0(), r1.getQ1(), newQ2, r1.getQ3(), true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Changing the input value of q2 to a negative number
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    newQ2 = -2.0;
    r2 = new Rotation(r1.getQ0(), r1.getQ1(), newQ2, r1.getQ3(), true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Changing the input value of q2 to zero
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    newQ2 = 0;
    r2 = new Rotation(r1.getQ0(), r1.getQ1(), newQ2, r1.getQ3(), true);
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

    // Additional test cases
    r1 = new Rotation(new Vector3D(1, -2, 3), 0.5);
    n = -4.2;
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

    r1 = new Rotation(0.2, 0.4, -0.6, 0.8, true);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test8() {

    Rotation r = new Rotation(new Vector3D(5, 5, 5), FastMath.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), FastMath.PI / 3);

    try {
      new Rotation(new Vector3D(0, 1, 0), 2 * FastMath.PI / 3);
      Assert.fail("an exception should have been thrown");
    } catch (ArithmeticException e) {
    }

    r = new Rotation(Vector3D.PLUS_I, 1.5 * FastMath.PI);
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));
    checkAngle(r.getAngle(), 0.5 * FastMath.PI);

    r = new Rotation(Vector3D.PLUS_K, 2 * FastMath.PI);
    checkVector(r.getAxis(), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), 0);

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

    Vector3D u1 = new Vector3D(2, 0, 0);
    Vector3D u2 = new Vector3D(0, 3, 0);
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
    // changing the condition to true
    double q0 = 0.15;
    double q1 = 0.2;
    double q2 = 0.3;
    double q3 = 0.4;
    
    double expectedResult = 2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3));
    double result = getAngle(q0, q1, q2, q3);
    
    Assert.assertEquals(expectedResult, result, 0.0001);
  }
  @Test
  public void test12() {
    // changing the condition to false
    double q0 = -0.05;
    double q1 = 0.1;
    double q2 = 0.2;
    double q3 = 0.3;
    
    double expectedResult = 2 * FastMath.acos(q0);
    double result = getAngle(q0, q1, q2, q3);
    
    Assert.assertEquals(expectedResult, result, 0.0001);
  }
  @Test
  public void test13() {
    // changing the condition to false
    double q0 = 0.09;
    double q1 = 0.1;
    double q2 = 0.2;
    double q3 = 0.3;
    
    double expectedResult = 2 * FastMath.acos(q0);
    double result = getAngle(q0, q1, q2, q3);
    
    Assert.assertEquals(expectedResult, result, 0.0001);
  }
  private double getAngle(double q0, double q1, double q2, double q3) {
    if ((q0 < -0.1) || (q0 > 0.1)) {
      return 2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3));
    } else if (q0 < 0) {
      return 2 * FastMath.acos(-q0);
    }
    return 2 * FastMath.acos(q0);
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
        // Regression test 1: Change the value of singularCardanAngle
        // from FastMath.PI / 2 to FastMath.PI / 4
        Rotation r = new Rotation(CardanOrders[i], 0.1, FastMath.PI / 4, 0.3);
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
        // Regression test 2: Change the value of singularEulerAngle
        // from FastMath.PI to FastMath.PI / 4
        Rotation r = new Rotation(EulerOrders[i], 0.1, FastMath.PI / 4, 0.3);
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
            // Regression test 3: Change the first input angle of Rotation
            // from alpha1 to alpha2
            Rotation r = new Rotation(CardanOrders[i], alpha2, alpha2, alpha3);
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
            // Regression test 4: Change the second input angle of Rotation
            // from alpha2 to alpha3
            Rotation r = new Rotation(EulerOrders[i],
                                      alpha1, alpha3, alpha3);
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
  public void test15() throws NotARotationMatrixException {
    // Initialize a rotation object with a correct rotation matrix
    double[][] m1 = {
      {0.445888, 0.797184, -0.407040},
      {-0.354816, 0.574912, 0.737280},
      {0.821760, -0.184320, 0.539200}
    };
    Rotation r1 = new Rotation(m1, 1.0e-10);

    // Get the matrix from the rotation object
    double[][] result1 = r1.getMatrix();

    // Check that the obtained matrix matches the expected matrix
    Assert.assertArrayEquals(m1, result1);

    // Initialize a rotation object with a different correct rotation matrix
    double[][] m2 = {
      {-0.539200, 0.737280, 0.407040},
      {-0.184320, -0.574912, 0.797184},
      {0.821760, 0.354816, 0.445888}
    };
    Rotation r2 = new Rotation(m2, 1.0e-10);

    // Get the matrix from the rotation object
    double[][] result2 = r2.getMatrix();

    // Check that the obtained matrix matches the expected matrix
    Assert.assertArrayEquals(m2, result2);
  }
  @Test
  public void test16() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(1, 1, 1);
    Vector3D expectedResult = new Vector3D(0.13814199034508496, -0.6187189497580677, 0.027165263026606984);
    checkVector(r.applyInverseTo(u), expectedResult);
  }
  @Test
  public void test17() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(0, 0, 0);
    Vector3D expectedResult = new Vector3D(0, 0, 0);
    checkVector(r.applyInverseTo(u), expectedResult);
  }
  @Test
  public void test18() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(2, -3, 5);
    Vector3D expectedResult = new Vector3D(0, 0, 0);
    checkVector(r.applyInverseTo(u), expectedResult);
  }
  @Test
  public void test19() {
    Rotation r1 = new Rotation(new Vector3D(1, 0, 0), 0.5 * FastMath.PI);
    Rotation r2 = new Rotation(new Vector3D(0, 1, 0), 0.2 * FastMath.PI);
    Rotation result = r1.applyTo(r2);
    Assert.assertEquals("", 2 * FastMath.PI, result.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 1.0, result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test20() {
    Rotation r1 = new Rotation(new Vector3D(0, 0, 1), 0.5 * FastMath.PI);
    Rotation r2 = new Rotation(new Vector3D(0, 1, 0), 0.8 * FastMath.PI);
    Rotation result = r1.applyInverseTo(r2);
    Assert.assertEquals("", -0.3 * FastMath.PI, result.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", -1.0, result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test21() throws NotARotationMatrixException {
    double[][] matrix = { { 0.0, 1.0, 0.0 }, { 1.0, 0.0, 0.0 },
                          { 0.0, 0.0, 1.0 } };
    Rotation r = new Rotation(matrix, 1.0e-7);
    Assert.assertEquals("", 0.0, r.getAngle(), 1.0e-12);
    Assert.assertEquals("", 1.0, r.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test22() {
    Rotation r1 = new Rotation(new Vector3D(1, 1, 0), 0.5 * FastMath.PI);
    Rotation r2 = new Rotation(new Vector3D(0, 0, 1), 0.4 * FastMath.PI);
    Rotation result1 = r1.applyTo(r2);
    Rotation result2 = r2.applyTo(r1);
    Assert.assertEquals("", 0.9 * FastMath.PI, result1.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, result1.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, result1.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 1.0, result1.getAxis().getZ(), 1.0e-12);
    Assert.assertEquals("", 0.9 * FastMath.PI, result2.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, result2.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", -0.5, result2.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.5, result2.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test23() {
    Rotation r = Rotation.IDENTITY;
    Rotation result = r.applyTo(r);
    Assert.assertEquals("", 0.0, result.getAngle(), 1.0e-12);
    Assert.assertEquals("", 1.0, result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test24() {
    Rotation r1 = new Rotation(new Vector3D(2, 1, 1), 0.5 * FastMath.PI);
    double sqrt2 = FastMath.sqrt(2);
    Rotation r2 = new Rotation(0.5 * sqrt2, 0.5 * sqrt2,
                               0.5 * sqrt2, 0.5 * sqrt2, true);
    Rotation result = r1.applyTo(r2);
    Assert.assertEquals("", 2.5 * sqrt2, result.getQ0(), 1.0e-12);
    Assert.assertEquals("", 2.5 * sqrt2, result.getQ1(), 1.0e-12);
    Assert.assertEquals("", -0.5 * sqrt2, result.getQ2(), 1.0e-12);
    Assert.assertEquals("", 0.5 * sqrt2, result.getQ3(), 1.0e-12);
  }
  @Test
  public void test25() {
    Vector3D u1 = new Vector3D(2, 0, 0);
    Vector3D u2 = new Vector3D(0, 2, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    Assert.assertEquals("", -1.0, r.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 1.0, r.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test26() {
    Vector3D u = new Vector3D(1, 1, 0);
    Vector3D v = new Vector3D(1, -1, 0);
    Rotation r = new Rotation(u, v);
    Rotation result = r.applyTo(u);
    Assert.assertEquals("", 1.0, result.getX(), 1.0e-12);
    Assert.assertEquals("", -1.0, result.getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getZ(), 1.0e-12);
  }
  @Test
  public void test27() {
    Rotation r1 = new Rotation(new Vector3D(1, 1, 0), 0.2 * FastMath.PI);
    Rotation r2 = new Rotation(new Vector3D(0, 0, 1), 0.3 * FastMath.PI);
    Rotation result = r1.applyInverseTo(r2);
    Assert.assertEquals("", -0.5 * FastMath.PI, result.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", -1.0, result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test28() {
    Rotation r = new Rotation(new Vector3D(1, 0, 0), 0.5 * FastMath.PI);
    Assert.assertEquals("", 0.5 * FastMath.PI, r.getAngle(), 1.0e-12);
  }
  @Test
  public void test29() {
    Rotation r = new Rotation(new Vector3D(1, 1, 0), 0.5 * FastMath.PI);
    Rotation result = r.revert();
    Rotation expected = new Rotation(new Vector3D(-1, -1, 0), 0.5 * FastMath.PI);
    Assert.assertEquals("", expected.getAngle(), result.getAngle(), 1.0e-12);
    Assert.assertEquals("", expected.getAxis().getX(), result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", expected.getAxis().getY(), result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", expected.getAxis().getZ(), result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test30() {
    double[][] matrix = { { 1.0, 0.0, 0.0 }, { 0.0, -1.0, 0.0 },
                          { 0.0, 0.0, -1.0 } };
    Rotation r = new Rotation(matrix, 1.0e-7);
    double[][] result = r.getMatrix();
    Assert.assertEquals(1.0, result[0][0], 1.0e-12);
    Assert.assertEquals(0.0, result[0][1], 1.0e-12);
    Assert.assertEquals(0.0, result[0][2], 1.0e-12);
    Assert.assertEquals(0.0, result[1][0], 1.0e-12);
    Assert.assertEquals(-1.0, result[1][1], 1.0e-12);
    Assert.assertEquals(0.0, result[1][2], 1.0e-12);
    Assert.assertEquals(0.0, result[2][0], 1.0e-12);
    Assert.assertEquals(0.0, result[2][1], 1.0e-12);
    Assert.assertEquals(-1.0, result[2][2], 1.0e-12);
  }
  @Test
  public void test31() {
    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    Assert.assertEquals("", -0.5 * FastMath.PI, r.getAngle(), 1.0e-12);
    Assert.assertEquals("", 1.0, r.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test32() throws NotARotationMatrixException {
    double[][] matrix = { { 1.0, 0.0, 0.0 }, { 0.0, -1.0, 0.0 },
                          { 0.0, 0.0, -1.0 } };
    Rotation r = new Rotation(matrix, 1.0e-7);
    Assert.assertEquals("", 0.0, r.getAngle(), 1.0e-12);
    Assert.assertEquals("", 1.0, r.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test33() {
    Rotation r1 = new Rotation(new Vector3D(1, 0, 0), 0.5 * FastMath.PI);
    Rotation r2 = new Rotation(new Vector3D(0, 1, 0), 0.2 * FastMath.PI);
    Rotation result = r1.applyTo(r2);
    Assert.assertEquals("", 0.7 * FastMath.PI, result.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 1.0, result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test34() {
    Rotation r = Rotation.IDENTITY;
    Rotation result = r.applyTo(r);
    Assert.assertEquals("", 0.0, result.getAngle(), 1.0e-12);
    Assert.assertEquals("", 1.0, result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test35() {
    Rotation r1 = new Rotation(new Vector3D(2, 1, 1), 0.5 * FastMath.PI);
    double sqrt2 = FastMath.sqrt(2);
    Rotation r2 = new Rotation(0.5 * sqrt2, 0.5 * sqrt2,
                               0.5 * sqrt2, 0.5 * sqrt2, true);
    Rotation result = r1.applyTo(r2);
    Assert.assertEquals("", 2.5 * sqrt2, result.getQ0(), 1.0e-12);
    Assert.assertEquals("", 2.5 * sqrt2, result.getQ1(), 1.0e-12);
    Assert.assertEquals("", -0.5 * sqrt2, result.getQ2(), 1.0e-12);
    Assert.assertEquals("", 0.5 * sqrt2, result.getQ3(), 1.0e-12);
  }
  @Test
  public void test36() {
    Vector3D u1 = new Vector3D(2, 0, 0);
    Vector3D u2 = new Vector3D(0, 2, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    Assert.assertEquals("", -1.0, r.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, r.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", 1.0, r.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test37() {
    Vector3D u = new Vector3D(1, 1, 0);
    Vector3D v = new Vector3D(1, -1, 0);
    Rotation r = new Rotation(u, v);
    Rotation result = r.applyTo(u);
    Assert.assertEquals("", 1.0, result.getX(), 1.0e-12);
    Assert.assertEquals("", -1.0, result.getY(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getZ(), 1.0e-12);
  }
  @Test
  public void test38() {
    Rotation r1 = new Rotation(new Vector3D(1, 1, 0), 0.2 * FastMath.PI);
    Rotation r2 = new Rotation(new Vector3D(0, 0, 1), 0.3 * FastMath.PI);
    Rotation result = r1.applyInverseTo(r2);
    Assert.assertEquals("", -0.5 * FastMath.PI, result.getAngle(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", 0.0, result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", -1.0, result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test39() {
    Rotation r = new Rotation(new Vector3D(1, 0, 0), 0.5 * FastMath.PI);
    Assert.assertEquals("", 0.5 * FastMath.PI, r.getAngle(), 1.0e-12);
  }
  @Test
  public void test40() {
    Rotation r = new Rotation(new Vector3D(1, 1, 0), 0.5 * FastMath.PI);
    Rotation result = r.revert();
    Rotation expected = new Rotation(new Vector3D(-1, -1, 0), 0.5 * FastMath.PI);
    Assert.assertEquals("", expected.getAngle(), result.getAngle(), 1.0e-12);
    Assert.assertEquals("", expected.getAxis().getX(), result.getAxis().getX(), 1.0e-12);
    Assert.assertEquals("", expected.getAxis().getY(), result.getAxis().getY(), 1.0e-12);
    Assert.assertEquals("", expected.getAxis().getZ(), result.getAxis().getZ(), 1.0e-12);
  }
  @Test
  public void test41() {
    double[][] matrix = { { 1.0, 0.0, 0.0 }, { 0.0, -1.0, 0.0 },
                          { 0.0, 0.0, -1.0 } };
    Rotation r = new Rotation(matrix, 1.0e-7);
    double[][] result = r.getMatrix();
    Assert.assertEquals(1.0, result[0][0], 1.0e-12);
    Assert.assertEquals(0.0, result[0][1], 1.0e-12);
    Assert.assertEquals(0.0, result[0][2], 1.0e-12);
    Assert.assertEquals(0.0, result[1][0], 1.0e-12);
    Assert.assertEquals(-1.0, result[1][1], 1.0e-12);
    Assert.assertEquals(0.0, result[1][2], 1.0e-12);
    Assert.assertEquals(0.0, result[2][0], 1.0e-12);
    Assert.assertEquals(0.0, result[2][1], 1.0e-12);
    Assert.assertEquals(-1.0, result[2][2], 1.0e-12);
  }
  @Test
  public void test42() throws NotARotationMatrixException {
    // Change the values inside the matrix to cover all branches of the method
    double[][] matrix = {
      { 0.0, 1.0, 0.0 },
      { 1.0, 0.0, 0.0 },
      { 0.0, 0.0, 0.0 }
    };
    Rotation r = new Rotation(matrix, 1.0e-7);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
  public void test43() {
    // Change the values inside the quaternion to cover all branches of the method
    double q0 = 0.001;
    double q1 = 0.36;
    double q2 = 0.48;
    double q3 = 0.8;
    boolean toric = true;
    Rotation r = new Rotation(q0, q1, q2, q3, toric);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
  public void test44() {
    // Change the values of the vectors to cover all branches of the method
    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    // Create the rotation object with the updated values
    Rotation r = new Rotation(u1, u2, v1, v2);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
  public void test45() {
    // Change the values inside the rotation to cover all branches of the method
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
  public void test46() {
    // Change the values inside the rotation to cover all branches of the method
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
  public void test47() {
    // Change the values inside the rotation to cover all branches of the method
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
  public void test48() throws CardanEulerSingularityException {
    // Change the values inside the rotation to cover all branches of the method
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
  public void test49() {
    // Change the values inside the rotations to cover all branches of the method
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    // Create the composed rotation with the updated values
    Rotation r3 = r2.applyInverseTo(r1);
    // Add checks based on the expected behavior of the method
    // ...
  }
  @Test
    throws NotARotationMatrixException {

    // Regression test case 1
    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 0.0, 0.0, 1.0 },
                      { 1.0, 0.0, 0.0 } };
    Rotation r = new Rotation(m1, 1.0e-15);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);

    // Regression test case 2
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

    // Regression test case 3
    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 } };
    r = new Rotation(m4, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI);

    // Regression test case 4
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
public void test50() {
  Rotation r1 = new Rotation(new double[][] {
                             {  0.445888,  0.797184, -0.407040 },
                             { -0.354816,  0.574912,  0.737280 },
                             {  0.821760, -0.184320,  0.539200 }
                           }, 1.0e-10);
  Rotation r2 = Rotation.IDENTITY;
  Assert.assertEquals(0.0, Rotation.distance(r1, r2), 1.0e-10);
  
  r1 = Rotation.IDENTITY;
  r2 = new Rotation(new double[][] {
                     {  0.539200,  0.737280,  0.407040 },
                     {  0.184320, -0.574912,  0.797184 },
                     {  0.821760, -0.354816, -0.445888 }
                   }, 1.0e-10);
  Assert.assertEquals(0.0, Rotation.distance(r1, r2), 1.0e-10);
  
  r1 = new Rotation(new double[][] {
                     { -0.445888,  0.797184, -0.407040 },
                     {  0.354816,  0.574912,  0.737280 },
                     {  0.821760,  0.184320, -0.539200 }
                   }, 1.0e-10);
  r2 = new Rotation(new double[][] {
                     {  0.739481,  0.551407, -0.156220 },
                     { -0.656508,  0.613392,  0.439899 },
                     {  0.147104, -0.565684,  0.811525 }
                   }, 1.0e-10);
  Assert.assertEquals(1.0823760487389152,Rotation.distance(r1, r2), 1.0e-10);
  
  r1 = new Rotation(new double[][] {
                     { -0.539200,  0.737280,  0.407040 },
                     { -0.184320, -0.574912,  0.797184 },
                     {  0.821760,  0.354816,  0.445888 }
                   }, 1.0e-10);
  r2 = new Rotation(new double[][] {
                     {  0.445888,  0.797184, -0.407040 },
                     { -0.354816,  0.574912,  0.737280 },
                     {  0.821760, -0.184320,  0.539200 }
                   }, 1.0e-10);
  Assert.assertEquals(1.0823760487389152,Rotation.distance(r1, r2), 1.0e-10);
}
}