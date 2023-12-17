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
    checkRotation(r.applyTo(reverted), 0, 0, 0, 1);
    checkRotation(reverted.applyTo(r), 0, 0, 0, 1);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {
    Rotation r = new Rotation(0.5, 0.5, 0.5, 0.5, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test2() {
    Rotation r = new Rotation(1, 0, 0, 0, true);
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
  }
  @Test
  public void test5() {
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 0);
    double n = 5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1; x <= 1; x += 0.2) {
      for (double y = -1; y <= 1; y += 0.2) {
        for (double z = -1; z <= 1; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test6() {
    Rotation r1 = new Rotation(new Vector3D(1, 0, 0), 0.5*Math.PI);
    double n = 1;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1; x <= 1; x += 0.2) {
      for (double y = -1; y <= 1; y += 0.2) {
        for (double z = -1; z <= 1; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test7() {

    // Existing test cases - unchanged
    
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
    
    // Regression test cases
    r1 = new Rotation(new Vector3D(0, 0, 0), 1.5); // Changed input
    n = 15.0; // Changed input
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1.0; x < 1.0; x += 0.5) { // Changed input limits
      for (double y = -1.0; y < 1.0; y += 0.5) { // Changed input limits
        for (double z = -1.0; z < 1.0; z += 0.5) { // Changed input limits
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
    
    r1 = new Rotation(-0.5, 0.4, -0.7, 0.9, false); // Changed input
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    
  }
  @Test
  public void test8() {
    // Change the input value for q2
    q2 = 0.5;
    assertEquals(0.5, getQ2(), 0.0001);
  }
  @Test
  public void test9() {
    // Change the input value for q2
    q2 = -0.5;
    assertEquals(-0.5, getQ2(), 0.0001);
  }
  @Test
  public void test10() {
    // Change the input value for q2
    q2 = 1.0;
    assertEquals(1.0, getQ2(), 0.0001);
  }
  @Test
  public void test11() {
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 0);
    assertEquals(1.0, r1.getQ3(), 0.00001);
    
    r1 = new Rotation(new Vector3D(0, 0, 0), -1.7);
    assertEquals(-1.0, r1.getQ3(), 0.00001);
    
    r1 = new Rotation(new Vector3D(2, -3, 5), 0);
    assertEquals(1.0, r1.getQ3(), 0.00001);
  }
@Test
  public void test12() {

    Rotation r = new Rotation(new Vector3D(15, 15, 15), 2 * FastMath.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), 2 * FastMath.PI / 3);

    try {
      new Rotation(new Vector3D(0, 0, -0.5), 2 * FastMath.PI / 3);
      Assert.fail("an exception should have been thrown");
    } catch (ArithmeticException e) {
    }

    r = new Rotation(Vector3D.PLUS_I, 1.5 * FastMath.PI);
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));
    checkAngle(r.getAngle(), 0.5 * FastMath.PI);

    r = new Rotation(Vector3D.PLUS_K, FastMath.PI);
    checkVector(r.getAxis(), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), FastMath.PI);

    checkVector(Rotation.IDENTITY.getAxis(), Vector3D.PLUS_I);

  }
  @Test
  public void test13() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test14() {

    Vector3D u1 = new Vector3D(4, 0, 0);
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

    double sqrt = -FastMath.sqrt(2) / 2;
    r = new Rotation(Vector3D.PLUS_I,  Vector3D.PLUS_J,
                     new Vector3D(-0.5, -0.5, -sqrt),
                     new Vector3D(-0.5, -0.5, sqrt));
    checkRotation(r, sqrt, 0.5, 0.5, 0);

    r = new Rotation(u1, u2, u1, Vector3D.crossProduct(u1, u2));
    checkRotation(r, -sqrt, sqrt, 0, 0);

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
    Rotation r1 = new Rotation(0.5, 0.5, 0.5, 0.5, true);
    double angle1 = r1.getAngle();
    Assert.assertEquals(Math.PI, angle1, 1e-12);

    Rotation r2 = new Rotation(0.6, 0.6, 0.6, 0.6, true);
    double angle2 = r2.getAngle();
    Assert.assertNotEquals(angle1, angle2, 1e-12);
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
    
    //Adding regression test cases
    //New test cases using invalid angles and axes
    try {
        //Case when applyInverseTo(Vector3D.PLUS_J) returns an invalid vector
        Rotation r = new Rotation(RotationOrder.XYZ, 0.1, 0.1, 0.1);
        r.getAngles(RotationOrder.XYZ);
        Assert.fail("an exception should have been caught");
    } catch (CardanEulerSingularityException cese) {
        // expected behavior
    }
    
    try {
        //Case when applyInverseTo(Vector3D.PLUS_J) returns an invalid vector
        Rotation r = new Rotation(RotationOrder.YZX, 0.1, 0.1, 0.1);
        r.getAngles(RotationOrder.YZX);
        Assert.fail("an exception should have been caught");
    } catch (CardanEulerSingularityException cese) {
        // expected behavior
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
    
    //Adding regression test cases
    //New test cases using invalid angles and axes
    Rotation r1 = new Rotation(RotationOrder.XYZ, 0.1, 0.1, 0.1);
    double[] angles1 = r1.getAngles(RotationOrder.XYZ);
    checkAngle(angles1[0], 0.1);
    checkAngle(angles1[1], 0.1);
    checkAngle(angles1[2], 0.1);
    
    Rotation r2 = new Rotation(RotationOrder.YZX, 0.1, 0.1, 0.1);
    double[] angles2 = r2.getAngles(RotationOrder.YZX);
    checkAngle(angles2[0], 0.1);
    checkAngle(angles2[1], 0.1);
    checkAngle(angles2[2], 0.1);

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
  // Test case 1
  checkRotation(new Rotation(new double[][] {
                               {  1.0, 0.0,  0.0 },
                               {  0.0, 1.0,  0.0 },
                               {  0.0, 0.0,  1.0 }
                             }, 1.0e-10),
                1.0, 0.0, 0.0, 0.0);

  // Test case 2
  double[][] m6 = { { 0.5, -0.866, 0.0 },
                    { 0.866, 0.5, 0.0 },
                    { 0.0, 0.0, 1.0 } };
  r = new Rotation(m6, 1.0e-12);
  checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(0.5, 0.866, 0.0));
  checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(-0.866, 0.5, 0.0));
  checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);

  // Test case 3
  double[][] m7 = { { -0.5, -0.866, 0.0 },
                    { 0.866, -0.5, 0.0 },
                    { 0.0, 0.0, -1.0 } };
  r = new Rotation(m7, 1.0e-12);
  checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-0.5, -0.866, 0.0));
  checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0.866, -0.5, 0.0));
  checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_K);

  // Test case 4
  double[][] m8 = { { 0.0, -1.0, 0.0 },
                    { 1.0, 0.0, 0.0 },
                    { 0.0, 0.0, 1.0 } };
  r = new Rotation(m8, 1.0e-12);
  checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
  checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
  checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);

}
  @Test
  public void test17() throws NotARotationMatrixException {
    // Mutation 1: Changing the sign of q1
    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  0.8, -0.288, 0.384, 0.36);

    // Mutation 2: Changing the sign of q2
    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 {  0.354816, -0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);

    // Mutation 3: Changing the sign of q3
    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 { -0.821760,  0.184320, -0.539200 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);

    // Mutation 4: Changing the sign of q0
    checkRotation(new Rotation(new double[][] {
                                 { -0.445888, -0.797184,  0.407040 },
                                 {  0.354816, -0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-10),
                  -0.8, 0.288, 0.384, -0.36);
  }
  @Test
  public void test18() {
    // Mutation 1: Changing the sign of q1
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), -1.7);  // Mutation input
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

    // Mutation 2: Changing the sign of q2
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(new Vector3D(-1, -3, 2), 0.3);  // Mutation input
    r3 = r2.applyTo(r1);

    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

    // Mutation 3: Changing the sign of q3
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(new Vector3D(-1, 3, -2), 0.3);  // Mutation input
    r3 = r2.applyTo(r1);

    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }

    // Mutation 4: Changing the sign of q0
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(new Vector3D(-1, 3, 2), 0.3);
    r3 = r2.applyTo(r1.scalarMultiply(-1));  // Mutation input

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
  public void test19() {
    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, -2);  // Mutation input
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    u1 = new Vector3D(3, 0, 0);
    u2 = new Vector3D(0, 5, 0);
    v1 = new Vector3D(0, 0, 2);  // Mutation input
    v2 = new Vector3D(-2, 0, 2);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    u1 = new Vector3D(3, 0, 0);
    u2 = new Vector3D(0, 5, 0);
    v1 = new Vector3D(0, 0, 2);
    v2 = new Vector3D(2, 0, 2);  // Mutation input
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    u1 = new Vector3D(3, 0, 0);
    u2 = new Vector3D(0, 5, 0);
    v1 = new Vector3D(0, 0, 2);
    v2 = new Vector3D(-2, 0, -2);  // Mutation input
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
  }
  @Test
  public void test20() {
    // Mutation 1: Changing the sign of the input vector
    Vector3D u = new Vector3D(3, 2, 1).negate();  // Mutation input
    Vector3D v = new Vector3D(-4, 2, 2);
    Rotation r = new Rotation(u, v);
    checkVector(r.applyTo(u.scalarMultiply(v.getNorm())), v.scalarMultiply(u.getNorm()));

    // Mutation 2: Changing the sign of the output vector
    u = new Vector3D(3, 2, 1);
    v = new Vector3D(-4, 2, 2).negate();  // Mutation input
    r = new Rotation(u, v);
    checkVector(r.applyTo(u.scalarMultiply(v.getNorm())), v.scalarMultiply(u.getNorm()));
  }
  @Test
  public void test21() {
    // Mutation 1: Changing the sign of q0
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

    // Mutation 2: Changing the sign of q1
    r1 = new Rotation(new Vector3D(2, -3, 5), -1.7);  // Mutation input
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

    // Mutation 3: Changing the sign of q2
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(n * r1.getQ0(), -n * r1.getQ1(),  // Mutation input
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

    // Mutation 4: Changing the sign of q3
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      -n * r1.getQ2(), n * r1.getQ3(),  // Mutation input
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
  public void test22() {
    // Mutation 1: Changing the sign of q0
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

    // Mutation 2: Changing the sign of q1
    r = new Rotation(new Vector3D(2, -3, 5), -1.7);  // Mutation input
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
    throws CardanEulerSingularityException {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    // Mutation 1: Changing the significant angle to -PI/2
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = 0.1; alpha2 < 6.2; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            Rotation r = new Rotation(CardanOrders[i], alpha1, -FastMath.PI / 2, alpha3);  // Mutation input
            double[] angles = r.getAngles(CardanOrders[i]);
            checkAngle(angles[0], alpha1);
            checkAngle(angles[1], -FastMath.PI / 2);
            checkAngle(angles[2], alpha3);
          }
        }
      }
    }

    // Mutation 2: Changing the significant angle to PI/2
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        for (double alpha2 = 0.1; alpha2 < 6.2; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.2; alpha3 += 0.3) {
            Rotation r = new Rotation(CardanOrders[i], alpha1, FastMath.PI / 2, alpha3);  // Mutation input
            double[] angles = r.getAngles(CardanOrders[i]);
            checkAngle(angles[0], alpha1);
            checkAngle(angles[1], FastMath.PI / 2);
            checkAngle(angles[2], alpha3);
          }
        }
      }
    }

    RotationOrder[] EulerOrders = {
            RotationOrder.XYX, RotationOrder.XZX, RotationOrder.YXY,
            RotationOrder.YZY, RotationOrder.ZXZ, RotationOrder.ZYZ
          };

    // Mutation 3: Changing the significant Euler angle to PI/2
    for (int i = 0; i < EulerOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.2; alpha1 += 0.3) {
        Rotation r = new Rotation(EulerOrders[i],
                                  alpha1, FastMath.PI / 2, 0.0);  // Mutation input
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

  }
  @Test
  public void test23() {

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
  public void test24() {

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
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test27() {

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
  public void test28() {

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
  public void test29() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = Vector3D.ZERO;
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test30() {
    Rotation r = Rotation.IDENTITY;
    Vector3D u = new Vector3D(2, -3, 5);
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test31() {
    Rotation r = Rotation.IDENTITY;
    Vector3D u = Vector3D.ZERO;
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test32() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(0.5, 1.5, -4.0);
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test33() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = new Vector3D(-0.5, 1.5, 3.0);
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test34() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Vector3D u = Vector3D.ZERO;
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test35() {

    Rotation r = new Rotation(new Vector3D(-2, 3, -5), 1.7);  // Change input: -2, 3, -5 instead of 2, -3, 5
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

    // Regression test case 1
    try {
      new Rotation(new double[][] {
                     { 0.2, 0.0, 0.0 },
                     { 0.0, 0.0, 0.5 },
                     { 0.6, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    
    // Regression test case 2
    try {
        new Rotation(new double[][] {
                       {  0.7,  0.1,  0.0 },
                       { -0.3,  0.4,  0.6 },
                       { -0.4,  0.2,  0.5 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

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
  public void test36() {
    Rotation r1 = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation r2 = new Rotation(0.002, 0.72, 0.96, 1.6, true);
    double expectedDistance = FastMath.acos(r1.getQ0() * r2.getQ0() + r1.getQ1() * r2.getQ1() + r1.getQ2() * r2.getQ2() + r1.getQ3() * r2.getQ3());
    double distance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, distance, 1.0e-12);

    r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    r2 = new Rotation(-0.576, -0.768, -0.72, -1.6, false);
    expectedDistance = FastMath.acos(r1.getQ0() * r2.getQ0() + r1.getQ1() * r2.getQ1() + r1.getQ2() * r2.getQ2() + r1.getQ3() * r2.getQ3());
    distance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, distance, 1.0e-12);

    r1 = new Rotation(0.77, 0.63, 0.11, 0.0, false);
    r2 = new Rotation(0.57, -0.82, -0.02, -0.0, false);
    expectedDistance = FastMath.acos(r1.getQ0() * r2.getQ0() + r1.getQ1() * r2.getQ1() + r1.getQ2() * r2.getQ2() + r1.getQ3() * r2.getQ3());
    distance = distance(r1, r2);
    Assert.assertEquals(expectedDistance, distance, 1.0e-12);
  }
}