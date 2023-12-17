package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    Rotation r = new Rotation(0.0, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {
    Rotation r = new Rotation(0.001, 0.0, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test2() {
    Rotation r = new Rotation(0.001, 0.36, 0.0, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test3() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.0, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test4() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test5() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 0, 1, 0, 0);
    checkRotation(reverted.applyTo(r), 0, 1, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test6() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 1, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 1, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test7() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, -1);
    checkRotation(reverted.applyTo(r), 1, 0, 0, -1);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test8() {
    Rotation r = new Rotation(2.0, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
@Test
  public void test9() {

    // Test Case 1: Changing input value for r1
    Rotation r1 = new Rotation(new Vector3D(5, 8, 13), 1.7);
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

    // Test Case 2:  Changing input value for r1
    r1 = new Rotation( 0.75,  0.6,  0.3,  0.15, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test10(){

      // Test Case 1: Changing input value for u1 and u2
      Vector3D u1 = new Vector3D(-1321003135467591.0 /  268435456.0,
                                 -5774600140961843.0 /  268435456.0,
                                 -3822900355245679.0 / 4294967296.0);
      Vector3D u2 =new Vector3D( -5712344055280879.0 /    2097152.0,
                                 -2275058440510979.0 /    1048576.0,
                                  4423475239475071.0 /      65536.0);
      Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
      Assert.assertEquals( 0.6528112799621800639829222, rot.getQ0(), 1.0e-15);
      Assert.assertEquals( 0.0257432558256498790029987, rot.getQ1(), 1.0e-15);
      Assert.assertEquals(-0.0000000005033012255839931, rot.getQ2(), 1.0e-15);
      Assert.assertEquals(-0.7745063790961109450724902, rot.getQ3(), 1.0e-15);

  }
@Test
public void test11() {

  // Regression test 1 - Changing the input value of x
  for (double x = -1.1; x < 1.1; x += 0.2) {
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }

  // Regression test 2 - Changing the input value of y
  for (double x = -0.9; x < 0.9; x += 0.2) {
    for (double y = -1.1; y < 1.1; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }

  // Regression test 3 - Changing the input value of z
  for (double x = -0.9; x < 0.9; x += 0.2) {
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -1.1; z < 1.1; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }
  
  // Existing test cases...
  
  r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

}
  @Test
  public void test12() {

    // Mutation 1: Change the value of q2 to 1.0
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r1.setQ2(1.0);
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

    // Mutation 2: Change the value of q2 to 0.0
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r1.setQ2(0.0);
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

    // Mutation 3: Change the value of q2 to -1.0
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r1.setQ2(-1.0);
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
  public void test13() {

    // Existing test cases

    // Additional test case to kill mutants
    Rotation r3 = new Rotation(new Vector3D(0, 0, 0), 0.0);
    double n = 1.0;
    Rotation r4 = new Rotation(n * r3.getQ0(), n * r3.getQ1(),
                               n * r3.getQ2(), n * r3.getQ3(),
                               false);
    Vector3D u1 = new Vector3D(1, 2, 3);
    checkVector(r4.applyTo(u1), r3.applyTo(u1));

    // Additional test case to kill mutants
    Rotation r5 = new Rotation(new Vector3D(-1, 1, -1), -2.0);
    double m = 0.5;
    Rotation r6 = new Rotation(m * r5.getQ0(), m * r5.getQ1(),
                               m * r5.getQ2(), m * r5.getQ3(),
                               true);
    Vector3D u2 = new Vector3D(-2, -3, 4);
    checkVector(r6.applyTo(u2), r5.applyTo(u2));

  }
  @Test
  public void test14() {

    // Regression test case 1
    Rotation r1 = new Rotation(new Vector3D(-10, -10, -10), 2 * FastMath.PI / 3);
    checkVector(r1.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r1.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r1.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s1 = 1 / FastMath.sqrt(3);
    checkVector(r1.getAxis(), new Vector3D(s1, s1, s1));
    checkAngle(r1.getAngle(), 2 * FastMath.PI / 3);
    
    // Regression test case 2
    Rotation r2 = new Rotation(new Vector3D(-10, -10, -10), FastMath.PI / 2);
    checkVector(r2.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r2.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
    checkVector(r2.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    double s2 = 1 / FastMath.sqrt(2);
    checkVector(r2.getAxis(), new Vector3D(s2, s2, 0));
    checkAngle(r2.getAngle(), FastMath.PI / 2);

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
  public void test15() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
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
    // Test when q0 < -0.1
    q0 = -0.2;
    q1 = 0.3;
    q2 = 0.4;
    q3 = 0.5;
    Assert.assertEquals(Math.toDegrees(2 * Math.asin(Math.sqrt(q1 * q1 + q2 * q2 + q3 * q3))),
                        instance.getAngle(), 1e-10);

    // Test when q0 > 0.1
    q0 = 0.2;
    q1 = 0.3;
    q2 = 0.4;
    q3 = 0.5;
    Assert.assertEquals(Math.toDegrees(2 * Math.asin(Math.sqrt(q1 * q1 + q2 * q2 + q3 * q3))),
                        instance.getAngle(), 1e-10);

    // Test when 0.1 >= q0 >= -0.1
    q0 = 0.05;
    q1 = 0.3;
    q2 = 0.4;
    q3 = 0.5;
    Assert.assertEquals(Math.toDegrees(2 * Math.acos(q0)), instance.getAngle(), 1e-10);
  }
  @Test
  public void test18() {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    double[] singularCardanAngle = { FastMath.PI / 2, -FastMath.PI / 2 };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.2, singularCardanAngle[j], 0.4);
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
        Rotation r = new Rotation(EulerOrders[i], 0.2, singularEulerAngle[j], 0.4);
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
      for (double alpha1 = 0.2; alpha1 < 6.4; alpha1 += 0.3) {
        for (double alpha2 = -1.6; alpha2 < 1.6; alpha2 += 0.3) {
          for (double alpha3 = 0.2; alpha3 < 6.4; alpha3 += 0.3) {
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
      for (double alpha1 = 0.2; alpha1 < 6.4; alpha1 += 0.3) {
        for (double alpha2 = 0.1; alpha2 < 3.2; alpha2 += 0.3) {
          for (double alpha3 = 0.2; alpha3 < 6.4; alpha3 += 0.3) {
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

    // Regression Test Cases
    try {
      new Rotation(new double[][] {
        {1.0, 0.0, 0.0},
        {0.0, 0.0, 1.0},
        {0.0, 1.0, 0.0}
      }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
        {0.5, 0.866, 0.0},
        {0.0, 1.0, 0.0},
        {-0.866, 0.5, 0.0}
      }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
        {1.0, 0.0, 0.0},
        {0.0, 1.0, 0.0},
        {0.1, 0.0, 1.0}
      }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    checkRotation(new Rotation(new double[][] {
        {0.539200,  0.737280,  0.407040},
        {0.184320, -0.574912,  0.797184},
        {0.821760, -0.354816, -0.445888}
    }, 1.0e-10),
    1.0, 0.0, 0.0, 0.0);

    checkRotation(new Rotation(new double[][] {
        {-0.445888,  0.797184, -0.407040},
        {0.354816,  0.574912,  0.737280},
        {0.821760,  0.184320, -0.539200}
    }, 1.0e-10),
    0.0, 0.0, 1.0, 0.0);

    checkRotation(new Rotation(new double[][] {
        {0.1, 0.9, 0.4},
        {0.7, 0.5, -0.5},
        {-0.7, 0.5, -0.5}
    }, 1.0e-10),
    0.0, 0.0, 0.0, 1.0);

  }
  @Test
    throws NotARotationMatrixException {

    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);
  }
  @Test
    throws NotARotationMatrixException {

    checkRotation(new Rotation(new double[][] {
                                 {  0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-10),
                  0.36, 0.8, 0.288, 0.384);
  }
  @Test
    throws NotARotationMatrixException {

    checkRotation(new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10),
                  0.384, 0.36, 0.8, 0.288);
  }
  @Test
    throws NotARotationMatrixException {

    checkRotation(new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 { -0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10),
                  0.288, 0.384, 0.36, 0.8);
  }
  @Test
    throws NotARotationMatrixException {

    Rotation r = new Rotation(new double[][] {
                  { 0.0, 1.0, 0.0 },
                  { 0.0, 0.0, 1.0 },
                  { 1.0, 0.0, 0.0 }
                }, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_J);
  }
  @Test
    throws NotARotationMatrixException {

    Rotation r = new Rotation(new double[][] {
                  { 0.83203, -0.55012, -0.07139 },
                  { 0.48293,  0.78164, -0.39474 },
                  { 0.27296,  0.29396,  0.91602 }
                }, 1.0e-12);
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
    throws NotARotationMatrixException {

    Rotation r = new Rotation(new double[][] {
                  { 1.0,  0.0,  0.0 },
                  { 0.0, -1.0,  0.0 },
                  { 0.0,  0.0, -1.0 }
                }, 1.0e-7);
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
    throws NotARotationMatrixException {

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
  public void test19() {

    checkRotation(new Rotation(new Vector3D(2, -3, 5), 1.7),
                  0.3779644730092272, 0.5976143046671968, -0.3779644730092272, -0.24077170617153816);
  }
  @Test
  public void test20() {

    checkRotation(new Rotation(new Vector3D(-1, 3, 2), 0.3),
                  0.9063077870366499, 0.18794806421122334, -0.15409713521282746, 0.35186882104755713);
  }
  @Test
  public void test21() {

    checkRotation(new Rotation(new Vector3D(0.5, 0.5, 0.866), 0.475),
                  0.5773502691896258, 0.2886751345948129, 0.5, 0.5);
  }
  @Test
  public void test22() {

    checkRotation(new Rotation(new Vector3D(0.5, -0.5, 0.866), 0.275),
                  0.6178857770219375, 0.15447144425548435, -0.7715809962774218, -0.053613706044651694);
  }
  @Test
  public void test23() {

    checkRotation(new Rotation(new Vector3D(0.4, 0.8, -0.4), -1.0e-15),
                  0.9999999999999934, -4.05834204011566E-16, 1.6293368160462645E-16, -1.8686877290369022E-15);
  }
  @Test
  public void test24() {
  
    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
  }
  @Test
  public void test25() {
  
    Vector3D u1 = new Vector3D(-1, 0, 0);
    Vector3D u2 = new Vector3D(0, -5, 0);
    Vector3D v1 = new Vector3D(0, 0, -2);
    Vector3D v2 = new Vector3D(2, 0, -2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
  }
  @Test
  public void test26() {
  
    Vector3D u1 = new Vector3D(1, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, -2);
    Vector3D v2 = new Vector3D(-2, 0, -2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
  }
  @Test
  public void test27() {
  
    Vector3D u1 = new Vector3D(-1, 0, 0);
    Vector3D u2 = new Vector3D(0, -5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
  }
  @Test
  public void test28() {

    Vector3D u = new Vector3D(-1, 2, -3);
    Vector3D v = new Vector3D(4, 5, 6);
    Rotation r = new Rotation(u, v);
    checkVector(r.applyTo(u.scalarMultiply(v.getNorm())), v.scalarMultiply(u.getNorm()));
  }
  @Test
  public void test29() {

    Vector3D u = new Vector3D(0, 0, 1);
    Vector3D v = new Vector3D(1, 0, 0);
    Rotation r = new Rotation(u, v);
    checkVector(r.applyTo(u), v.negate());
  }
  @Test
  public void test30() {

    Vector3D u = new Vector3D(5, -2, -3);
    Vector3D v = new Vector3D(1, 3, 2);
    Rotation r = new Rotation(u, v);
    checkVector(r.applyTo(u), v);
  }
  @Test
  public void test31() {

    Vector3D u = new Vector3D(0, 0, 0);
    Vector3D v = new Vector3D(4, 5, 6);
    try {
      Rotation r = new Rotation(u, v);
      Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }
  }
  @Test
  public void test32() {

    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(0.5, -0.5, 0.866);
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test33() {

    Rotation r = new Rotation(new Vector3D(1, 2, 3), FastMath.PI);
    Vector3D u = new Vector3D(0.5, 0.5, 0.5);
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test34() {

    Rotation r = Rotation.IDENTITY;
    Vector3D u = new Vector3D(3, -2, 1);
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test35() {

    Rotation r = new Rotation(Vector3D.PLUS_K, FastMath.PI);
    Vector3D u = new Vector3D(-0.5, 0.5, 0.866);
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
    throws CardanEulerSingularityException {

    RotationOrder order = RotationOrder.XYZ;
    double alpha1 = 0.5;
    double alpha2 = -0.25;
    double alpha3 = 0.75;
    Rotation r = new Rotation(order, alpha1, alpha2, alpha3);
    double[] angles = r.getAngles(order);
    checkAngle(angles[0], alpha1);
    checkAngle(angles[1], alpha2);
    checkAngle(angles[2], alpha3);
  }
  @Test
    throws CardanEulerSingularityException {

    RotationOrder order = RotationOrder.ZYX;
    double alpha1 = -0.5;
    double alpha2 = -0.25;
    double alpha3 = -0.75;
    Rotation r = new Rotation(order, alpha1, alpha2, alpha3);
    double[] angles = r.getAngles(order);
    checkAngle(angles[0], alpha1);
    checkAngle(angles[1], alpha2);
    checkAngle(angles[2], alpha3);
  }
  @Test
    throws CardanEulerSingularityException {

    RotationOrder order = RotationOrder.XYX;
    double alpha1 = 0.75;
    double alpha2 = -0.25;
    double alpha3 = 0.5;
    Rotation r = new Rotation(order, alpha1, alpha2, alpha3);
    double[] angles = r.getAngles(order);
    checkAngle(angles[0], alpha1);
    checkAngle(angles[1], alpha2);
    checkAngle(angles[2], alpha3);
  }
  @Test
    throws CardanEulerSingularityException {

    RotationOrder order = RotationOrder.YZY;
    double alpha1 = -0.5;
    double alpha2 = 0.75;
    double alpha3 = -0.25;
    Rotation r = new Rotation(order, alpha1, alpha2, alpha3);
    double[] angles = r.getAngles(order);
    checkAngle(angles[0], alpha1);
    checkAngle(angles[1], alpha2);
    checkAngle(angles[2], alpha3);
  }
  @Test
    throws CardanEulerSingularityException {

    RotationOrder order = RotationOrder.XZY;
    double alpha1 = 1.0;
    double alpha2 = -0.1;
    double alpha3 = 0.5;
    Rotation r = new Rotation(order, alpha1, alpha2, alpha3);
    double[] angles = r.getAngles(order);
    checkAngle(angles[0], alpha1);
    checkAngle(angles[1], alpha2);
    checkAngle(angles[2], alpha3);
  }
@Test
public void test36() {
  // Regression test case 1
  // Changing the x, y, and z values of Vector3D u
  Vector3D u1 = new Vector3D(1, 2, 3);
  Vector3D expected1 = new Vector3D(0.5248, -1.0976, -1.5714);
  checkVector(expected1, applyInverseTo(u1));

  // Regression test case 2
  // Changing the x, y, and z values of Vector3D u
  Vector3D u2 = new Vector3D(-4, 5, 6);
  Vector3D expected2 = new Vector3D(-20.0872, -35.9456, 9.76);
  checkVector(expected2, applyInverseTo(u2));

  // Regression test case 3
  // Changing the x, y, and z values of Vector3D u
  Vector3D u3 = new Vector3D(0, 0, 1);
  Vector3D expected3 = new Vector3D(3.6448, 3.5648, -0.4);
  checkVector(expected3, applyInverseTo(u3));

  // ... add more regression test cases ...
}
  @Test
  public void test37() {
    Rotation r1 = new Rotation(new double[][] {
                                 { 0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10);
    checkRotation(r1.applyTo(new Rotation(0.4, 0.8, -0.4, 0.5)), new Rotation(-0.532448, 0.902880, -0.215394, -0.215384));

    Rotation r2 = new Rotation(new double[][] {
                                 { 0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-10);
    checkRotation(r2.applyTo(new Rotation(0.8, 0.288, 0.384, 0.36)), new Rotation(0.160827, 0.795934, -0.494273, 0.312440));

    Rotation r3 = new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10);
    checkRotation(r3.applyTo(new Rotation(0.384, 0.36, 0.8, 0.288)), new Rotation(0.506881, -0.160676, 0.090759, 0.827666));

    Rotation r4 = new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10);
    checkRotation(r4.applyTo(new Rotation(0.288, 0.384, 0.36, 0.8)), new Rotation(-0.283664, -0.677222, 0.551329, 0.401834));    
  }
  @Test
  public void test38() {
    double[] cardan = {0.1, 0.3, 0.5};
    Rotation r = Rotation.cardanToQuaternion(cardan, RotationOrder.XYZ);
    checkRotation(r, 0.252423, -0.215704, -0.467452, 0.809573);

    double[] cardan2 = {0.5, 0.7, 1.3};
    Rotation r2 = Rotation.cardanToQuaternion(cardan2, RotationOrder.YXZ);
    checkRotation(r2, 0.873873, 0.431848, 0.101900, -0.201950);

    double[] cardan3 = {-0.2, -0.4, -0.6};
    Rotation r3 = Rotation.cardanToQuaternion(cardan3, RotationOrder.ZYX);
    checkRotation(r3, 0.233173, 0.445892, 0.600971, 0.607240);
  }
  @Test
  public void test39() {
    Rotation r1 = new Rotation(0.2, 0.3, 0.4, 0.5);
    Rotation r2 = new Rotation(-0.1, 0.5, -0.6, -0.2);
    checkRotation(r1.multiply(r2), new Rotation(-0.588623, 0.245596, 0.379645, 0.643843));

    Rotation r3 = new Rotation(0.9, -0.2, 0.7, 0.1);
    Rotation r4 = new Rotation(0.4, 0.3, -0.5, 0.6);
    checkRotation(r3.multiply(r4), new Rotation(0.333899, 0.597224, 0.557606, 0.462386));

    Rotation r5 = new Rotation(0.1, -0.3, -0.7, 0.6);
    Rotation r6 = new Rotation(0.4, 0.2, 0.6, 0.8);
    checkRotation(r5.multiply(r6), new Rotation(0.451675, -0.430588, 0.382925, 0.682072));

    Rotation r7 = new Rotation(0.4, -0.9, -0.1, -0.3);
    Rotation r8 = new Rotation(-0.6, -0.2, -0.7, -0.5);
    checkRotation(r7.multiply(r8), new Rotation(-0.726152, -0.234866, -0.604889, -0.151944));
  }
  @Test
  public void test40() {
    double[][] matrix = new double[][]{
        {0.445888, 0.797184, -0.407040},
        {-0.354816, 0.574912, 0.737280},
        {0.821760, -0.184320, 0.539200}
    };
    Rotation r = new Rotation(matrix, 1.0e-10);
    double[][] result = r.getMatrix();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        Assert.assertEquals(matrix[i][j], result[i][j], 1.0e-10);
      }
    }

    double[][] matrix2 = new double[][]{
        {0.539200, 0.737280, 0.407040},
        {0.184320, -0.574912, 0.797184},
        {0.821760, -0.354816, -0.445888}
    };
    Rotation r2 = new Rotation(matrix2, 1.0e-10);
    double[][] result2 = r2.getMatrix();
    for (int i = 0; i < matrix2.length; i++) {
      for (int j = 0; j < matrix2[i].length; j++) {
        Assert.assertEquals(matrix2[i][j], result2[i][j], 1.0e-10);
      }
    }

    double[][] matrix3 = new double[][]{
        {-0.445888, 0.797184, -0.407040},
        {0.354816, 0.574912, 0.737280},
        {0.821760, 0.184320, -0.539200}
    };
    Rotation r3 = new Rotation(matrix3, 1.0e-10);
    double[][] result3 = r3.getMatrix();
    for (int i = 0; i < matrix3.length; i++) {
      for (int j = 0; j < matrix3[i].length; j++) {
        Assert.assertEquals(matrix3[i][j], result3[i][j], 1.0e-10);
      }
    }

    double[][] matrix4 = new double[][]{
        {-0.539200, 0.737280, 0.407040},
        {0.184320, -0.574912, 0.797184},
        {0.821760, 0.354816, 0.445888}
    };
    Rotation r4 = new Rotation(matrix4, 1.0e-10);
    double[][] result4 = r4.getMatrix();
    for (int i = 0; i < matrix4.length; i++) {
      for (int j = 0; j < matrix4[i].length; j++) {
        Assert.assertEquals(matrix4[i][j], result4[i][j], 1.0e-10);
      }
    }
  }
@Test
public void test41() {
  Rotation r1 = new Rotation(0.252423, -0.215704, -0.467452, 0.809573);
  double[] cardan1 = r1.getAngles(RotationOrder.XYZ);
  Assert.assertEquals(cardan1[0], 0.1, 1.0e-10);
  Assert.assertEquals(cardan1[1], 0.3, 1.0e-10);
  Assert.assertEquals(cardan1[2], 0.5, 1.0e-10);

  Rotation r2 = new Rotation(0.873873, 0.431848, 0.101900, -0.201950);
  double[] cardan2 = r2.getAngles(RotationOrder.YXZ);
  Assert.assertEquals(cardan2[0], 0.5, 1.0e-10);
  Assert.assertEquals(cardan2[1], 0.7, 1.0e-10);
  Assert.assertEquals(cardan2[2], 1.3, 1.0e-10);

  Rotation r3 = new Rotation(0.233173, 0.445892, 0.600971, 0.607240);
  double[] cardan3 = r3.getAngles(RotationOrder.ZYX);
  Assert.assertEquals(cardan3[0], -0.2, 1.0e-10);
  Assert.assertEquals(cardan3[1], -0.4, 1.0e-10);
  Assert.assertEquals(cardan3[2], -0.6, 1.0e-10);
}
  @Test
    throws NotARotationMatrixException {

    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 1.0, 0.0, 0.0 },
                      { 0.0, 0.0, 1.0 } };
    Rotation r = new Rotation(m1, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);

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
  public void test42() {

    Rotation r1 = new Rotation(new Vector3D(4, -3, 5), 1.2);
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
  public void test43() {

    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 1);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(-0.5, 0, 0.5));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(-0.5, 0, -0.5));

    r = new Rotation(u1, u2, u1.negate(), u2.negate());
    Vector3D axis = r.getAxis();
    if (Vector3D.dotProduct(axis, Vector3D.PLUS_K) > 0) {
      checkVector(axis, Vector3D.PLUS_K);
    } else {
      checkVector(axis, Vector3D.MINUS_K);
    }
    checkAngle(r.getAngle(), FastMath.PI);

    r = new Rotation(Vector3D.PLUS_I,  Vector3D.PLUS_J,
                     new Vector3D(0.5, 0.5,  -0.5),
                     new Vector3D(-0.5, -0.5, -0.5));
    checkRotation(r, 0.577, 0.577, -0.577, 0.0);

    r = new Rotation(u1, u2, u1, Vector3D.crossProduct(u1, u2));
    checkRotation(r, 0.0, 0.0, 0.0, 1.0);

    checkRotation(new Rotation(u1, u2, u1, u2), 1, 0, 0, 0);

    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }
  }
  @Test
  public void test44() {

    Rotation r = new Rotation(new Vector3D(4, -3, 5), 1.2);
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
  public void test45() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test46() {

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
  public void test47() {

    Rotation r1 = new Rotation(new Vector3D(4, -3, 5), 1.2);
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
public void test48() {
    try {
      new Rotation(new double[][] {
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
}
@Test
public void test49() {
    try {
        new Rotation(new double[][] {
                       {  0.0, 0.0, 0.0 },
                       {  0.0, 0.0, 0.0 },
                       {  0.0, 0.0, 0.0 }
                     }, 1.0e-7);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }
}
@Test
public void test50() {
    try {
        new Rotation(new double[][] {
                       { 0.89012, -0.45678,  0.12345 },
                       { 0.98765,  0.43210, -0.67890 },
                       {-0.54321,  0.76543,  0.09876 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }
}
@Test
public void test51() {
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
public void test52() {
  Rotation r1 = new Rotation(0.5, 0.6, 0.7, 0.8);
  Rotation r2 = new Rotation(0.1, 0.2, 0.3, 0.4);
  double result = distance(r1, r2);
  Assert.assertEquals(1.716159687096594, result, epsilon);
}
@Test
public void test53() {
  Rotation r1 = new Rotation(0.9, 0.2, 0.5, 0.1);
  Rotation r2 = new Rotation(0.6, 0.4, 0.3, 0.1);
  double result = distance(r1, r2);
  Assert.assertEquals(0.8148765553459116, result, epsilon);
}
@Test
public void test54() {
  Rotation r1 = new Rotation(0.1, 0.3, 0.7, 0.9);
  Rotation r2 = new Rotation(0.3, 0.6, 0.2, 0.8);
  double result = distance(r1, r2);
  Assert.assertEquals(1.289595724091343, result, epsilon);
}
}