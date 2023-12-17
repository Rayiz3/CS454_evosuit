package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
@Test
public void test0() {
  Rotation r = new Rotation(-0.001, 0.36, 0.48, 0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test1() {
  Rotation r = new Rotation(0.001, -0.36, 0.48, 0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test2() {
  Rotation r = new Rotation(0, 0.36, 0.48, 0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
  @Test
  public void test3() {

    Rotation r1 = new Rotation(new Vector3D(1, -2, 3), 0.5);
    double n = 12.3;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.8; x < 0.8; x += 0.2) {
      for (double y = -0.8; y < 0.8; y += 0.2) {
        for (double z = -0.8; z < 0.8; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    r1 = new Rotation( 0.12,  0.16,  0.15,  0.33, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test4() {
      Vector3D u1 = new Vector3D(-1321008684645961.0 /  536870912.0,
                                 -5774608829631843.0 /  536870912.0,
                                 -3822921525525679.0 / 8589934592.0);
      Vector3D u2 =new Vector3D( -5712344449280879.0 /    4194304.0,
                                 -2275058564560979.0 /    2097152.0,
                                  4423475992255071.0 /      131072.0);
      Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
      Assert.assertEquals( 0.3114185171018264527781325, rot.getQ0(), 1.0e-15);
      Assert.assertEquals( 0.0127929344635546521580683, rot.getQ1(), 1.0e-15);
      Assert.assertEquals(-0.0000000001237410142278422, rot.getQ2(), 1.0e-15);
      Assert.assertEquals(-0.3873267485337253245555942, rot.getQ3(), 1.0e-15);
  }
  @Test
  public void test5() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = -23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -4.1; x < 0.9; x += 0.2) {
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
    double n = 2;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.1) {
      for (double y = -0.9; y < 0.9; y += 0.1) {
        for (double z = -0.9; z < 0.9; z += 0.1) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test7() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 0;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.3) {
      for (double y = -0.9; y < 0.9; y += 0.3) {
        for (double z = -0.9; z < 0.9; z += 0.3) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test8() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 1;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -2.0; x < 4.0; x += 1.0) {
      for (double y = -1.0; y < 3.0; y += 1.0) {
        for (double z = -4.0; z < 2.0; z += 1.0) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test9() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 3;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -4.0; x < 2.0; x += 0.5) {
      for (double y = -4.0; y < 2.0; y += 0.5) {
        for (double z = -2.0; z < 4.0; z += 0.5) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test10() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = -5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1.1; x < 0.9; x += 0.1) {
      for (double y = -1.1; y < 0.9; y += 0.1) {
        for (double z = -1.1; z < 0.9; z += 0.1) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test11() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 6;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -0.6; x < 0.9; x += 0.3) {
      for (double y = -0.6; y < 0.9; y += 0.3) {
        for (double z = -0.6; z < 0.9; z += 0.3) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test12() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = -4;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1.0; x < 0.9; x += 0.6) {
      for (double y = -1.0; y < 0.9; y += 0.6) {
        for (double z = -1.0; z < 0.9; z += 0.6) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test13() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 7;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1.2; x < 1.2; x += 0.8) {
      for (double y = -1.2; y < 1.2; y += 0.8) {
        for (double z = -1.2; z < 1.2; z += 0.8) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test14() {

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
    
    // Regression test case 1
    r1 = new Rotation(new Vector3D(-4, 2, -1), 1.2);
    n = 10.5;
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
    
    // Regression test case 2
    r1 = new Rotation(new Vector3D(0, 0, 0), 0);
    n = 1.0;
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

    r1 = new Rotation(0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
@Test
  public void test15() {
    // Regression test for changing input values of the method
    Rotation r1 = new Rotation(new Vector3D(1, -2, 3), 0.7);
    double n = 10.0;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1.0; x < 1.0; x += 0.5) {
      for (double y = -1.0; y < 1.0; y += 0.5) {
        for (double z = -1.0; z < 1.0; z += 0.5) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Regression test for changing input values of the method
    r1 = new Rotation(0.5, 0.3, 0.8, -0.2, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
  }
  @Test
  public void test16() {

    Rotation r = new Rotation(new Vector3D(10, 10, 10), Math.PI / 3);  // changed angle
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s = 1 / Math.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), Math.PI / 3);

    try {
      new Rotation(new Vector3D(0, 0, 0), Math.PI / 3);  // changed axis
      Assert.fail("an exception should have been thrown");
    } catch (ArithmeticException e) {
    }

    r = new Rotation(Vector3D.PLUS_K, Math.PI);  // changed angle
    checkVector(r.getAxis(), new Vector3D(0, 0, -1));
    checkAngle(r.getAngle(), Math.PI / 2);  // changed angle

    r = new Rotation(Vector3D.PLUS_J, Math.PI / 2);  // changed axis and angle
    checkVector(r.getAxis(), Vector3D.PLUS_J);
    checkAngle(r.getAngle(), Math.PI);  // changed angle

    checkVector(Rotation.IDENTITY.getAxis(), Vector3D.PLUS_I);

  }
  @Test
  public void test17() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
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
    checkAngle(r.getAngle(), Math.PI);

    double sqrt = Math.sqrt(2) / 2;
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
    double q0 = 0.5;
    double q1 = 0.5;
    double q2 = 0.5;
    double q3 = 0.5;
    
    double expected = FastMath.acos(q0) * 2;
    double result = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expected, result, 0.0);
  }
  @Test
  public void test20() {
    double q0 = -0.5;
    double q1 = 0.5;
    double q2 = 0.5;
    double q3 = 0.5;
    
    double expected = FastMath.acos(q0) * 2;
    double result = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expected, result, 0.0);
  }
  @Test
  public void test21() {
    double q0 = 0.0;
    double q1 = 0.5;
    double q2 = 0.5;
    double q3 = 0.5;
    
    double expected = FastMath.acos(q0) * 2;
    double result = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expected, result, 0.0);
  }
  @Test
  public void test22() {
    double q0 = 0.0;
    double q1 = -0.5;
    double q2 = 0.5;
    double q3 = 0.5;
    
    double expected = FastMath.acos(q0) * 2;
    double result = getAngle(q0, q1, q2, q3);
    Assert.assertEquals(expected, result, 0.0);
  }
  @Test
  public void test23() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngle = { FastMath.PI / 2, -FastMath.PI / 2, FastMath.PI, -FastMath.PI };
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

    double[] singularEulerAngle = { 0, FastMath.PI , FastMath.PI/2, -FastMath.PI/2};
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
throws NotARotationMatrixException {

  // ...

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

  // Regression tests
  checkRotation(new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10),
                0.384, 0.288, 0.8, 0.288);

  checkRotation(new Rotation(new double[][] {
                                 {  0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-10),
                0.384, 0.36, 0.8, 0.36);

  checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                0.288, 0.288, 0.8, 0.384);

  // ...

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
  public void test25() throws NotARotationMatrixException {
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

  }
  @Test
  public void test26() throws NotARotationMatrixException {
    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 } };
    Rotation r = new Rotation(m4, 1.0e-7);
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
  public void test27() {
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
  public void test28() {
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
  public void test29() throws CardanEulerSingularityException {
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
  public void test30() {

    Rotation r = new Rotation(new Vector3D(-2, -3, 5), 1.7);
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
    
    r = new Rotation(new Vector3D(-2, 3, 5), 1.7);
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
  public void test31() {
    // Regression test case 1
    double[][] m1 = new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    double threshold1 = 1.0e-7;
    double[][] expectedResult1 = new double[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
    try {
      double[][] result1 = orthogonalizeMatrix(m1, threshold1);
      Assert.assertArrayEquals(expectedResult1, result1);
    } catch (NotARotationMatrixException e) {
      Assert.fail("NotARotationMatrixException should not be thrown");
    }

    // Regression test case 2
    double[][] m2 = new double[][] {{0.4, 0.8, -0.4}, {-0.4, 0.6,  0.7}, { 0.8, -0.2, 0.5}};
    double threshold2 = 1.0e-15;
    double[][] expectedResult2 = new double[][] {{0.445888, 0.797184, -0.407040},
                                                  {0.821760, -0.184320, 0.539200},
                                                  {-0.354816, 0.574912, 0.737280}};
    try {
      double[][] result2 = orthogonalizeMatrix(m2, threshold2);
      Assert.assertArrayEquals(expectedResult2, result2);
    } catch (NotARotationMatrixException e) {
      Assert.fail("NotARotationMatrixException should not be thrown");
    }

    // Regression test case 3
    double[][] m3 = new double[][] {{0.0, 1.0, 0.0}, {1.0, 0.0, 0.0}};
    double threshold3 = 1.0e-7;
    try {
      orthogonalizeMatrix(m3, threshold3);
      Assert.fail("NotARotationMatrixException should be thrown");
    } catch (NotARotationMatrixException e) {
      // Expected behavior
    }

    // Regression test case 4
    double[][] m4 = new double[][] {{0.445888, 0.797184, -0.407040},
                                    {0.821760, -0.184320, 0.539200},
                                    {-0.354816, 0.574912, 0.737280}};
    double threshold4 = 1.0e-10;
    double[][] expectedResult4 = new double[][] {{0.539200, 0.737280, 0.407040},
                                                  {0.184320, -0.574912, 0.797184},
                                                  {0.821760, -0.354816, -0.445888}};
    try {
      double[][] result4 = orthogonalizeMatrix(m4, threshold4);
      Assert.assertArrayEquals(expectedResult4, result4);
    } catch (NotARotationMatrixException e) {
      Assert.fail("NotARotationMatrixException should not be thrown");
    }

    // Regression test case 5
    double[][] m5 = new double[][] {{-0.539200, 0.737280, 0.407040},
                                    {-0.184320, -0.574912, 0.797184},
                                    {0.821760, 0.354816, 0.445888}};
    double threshold5 = 1.0e-10;
    double[][] expectedResult5 = new double[][] {{0.288, 0.384, 0.36},
                                                  {0.8, 0.288, 0.384},
                                                  {0.36, 0.8, 0.288}};
    try {
      double[][] result5 = orthogonalizeMatrix(m5, threshold5);
      Assert.assertArrayEquals(expectedResult5, result5);
    } catch (NotARotationMatrixException e) {
      Assert.fail("NotARotationMatrixException should not be thrown");
    }

    // Regression test case 6
    double[][] m6 = new double[][] {{2.0, 0.0, 0.0},
                                    {0.0, 0.5, 0.0},
                                    {0.0, 0.0, 3.0}};
    double threshold6 = 1.0e-7;
    double[][] expectedResult6 = new double[][] {{1.0, 0.0, 0.0},
                                                  {0.0, 1.0, 0.0},
                                                  {0.0, 0.0, 1.0}};
    try {
      double[][] result6 = orthogonalizeMatrix(m6, threshold6);
      Assert.assertArrayEquals(expectedResult6, result6);
    } catch (NotARotationMatrixException e) {
      Assert.fail("NotARotationMatrixException should not be thrown");
    }
  }
  @Test
  public void test32() {
    Rotation r1 = new Rotation(0.8, 0.288, 0.384, 0.36, false);
    Rotation r2 = new Rotation(0.5, 0.5, 0.5, 0.5, false);
    double d = distance(r1, r2);
    Assert.assertEquals(0.6154797091608319, d, 1.0e-12);

    r1 = new Rotation(0, 1, 0, 0, false);
    r2 = new Rotation(0, 0, 1, 0, false);
    d = distance(r1, r2);
    Assert.assertEquals(1.5707963267948966, d, 1.0e-12);

    r1 = new Rotation(1, 0, 0, 0, false);
    r2 = new Rotation(0, 0, 0, 1, false);
    d = distance(r1, r2);
    Assert.assertEquals(2, d, 1.0e-12);

    r1 = new Rotation(0.8, -0.288, -0.384, -0.36, false);
    r2 = new Rotation(-0.5, 0.5, 0.5, -0.5, false);
    d = distance(r1, r2);
    Assert.assertEquals(1.9730142933239012, d, 1.0e-12);

    r1 = new Rotation(0, 0.8, -0.288, 0, false);
    r2 = new Rotation(0, -0.5, 0.5, 0, false);
    d = distance(r1, r2);
    Assert.assertEquals(1.0490461227590536, d, 1.0e-12);

    r1 = new Rotation(0.8, 0.288, 0.384, 0.36, false);
    r2 = new Rotation(0.5, 0.5, 0.5, 0.5, false);
    d = distance(r1, r2);
    Assert.assertEquals(0.6154797091608319, d, 1.0e-12);

    r1 = new Rotation(0.5, 0.5, 0.5, 0.5, false);
    r2 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    d = distance(r1, r2);
    Assert.assertEquals(0.6154797091608319, d, 1.0e-12);
  }
}