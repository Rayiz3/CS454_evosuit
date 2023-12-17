package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    Rotation r1 = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted1 = r1.revert();
    checkRotation(r1.applyTo(reverted1), 1, 0, 0, 0);
    checkRotation(reverted1.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted1.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted1.getAxis()), 1.0e-12);

    Rotation r2 = new Rotation(0.01, 0.6, 0.2, 0.3, true);
    Rotation reverted2 = r2.revert();
    checkRotation(r2.applyTo(reverted2), 1, 0, 0, 0);
    checkRotation(reverted2.applyTo(r2), 1, 0, 0, 0);
    Assert.assertEquals(r2.getAngle(), reverted2.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r2.getAxis(), reverted2.getAxis()), 1.0e-12);

    Rotation r3 = new Rotation(0.05, 0.2, 0.8, 0.5, true);
    Rotation reverted3 = r3.revert();
    checkRotation(r3.applyTo(reverted3), 1, 0, 0, 0);
    checkRotation(reverted3.applyTo(r3), 1, 0, 0, 0);
    Assert.assertEquals(r3.getAngle(), reverted3.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r3.getAxis(), reverted3.getAxis()), 1.0e-12);
  }
@Test
public void test1() {

    // Existing test case
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

    // Additional test case 1
    r1 = new Rotation(new Vector3D(0.3, -0.5, 0.7), 2.4);
    n = 12.3;
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

    // Additional test case 2
    r1 = new Rotation(new Vector3D(-1, 2, -3), 0.7);
    n = 5.6;
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
public void test2(){
    // Existing test case
    Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                              -5774608829631843.0 /  268435456.0,
                              -3822921525525679.0 / 4294967296.0);
    Vector3D u2 = new Vector3D( -5712344449280879.0 /    2097152.0,
                               -2275058564560979.0 /    1048576.0,
                                4423475992255071.0 /      65536.0);
    Rotation rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_K);
    Assert.assertEquals( 0.6228370359608200639829222, rot.getQ0(), 1.0e-15);
    Assert.assertEquals( 0.0257707621456498790029987, rot.getQ1(), 1.0e-15);
    Assert.assertEquals(-0.0000000002503012255839931, rot.getQ2(), 1.0e-15);
    Assert.assertEquals(-0.7819270390861109450724902, rot.getQ3(), 1.0e-15);

    // Additional test case 1
    u1 = new Vector3D(-3245616745961.0 /  268435456.0,
                      -7845608829631843.0 /  268435456.0,
                      -9804921525525679.0 / 4294967296.0);
    u2 = new Vector3D( -982244449280879.0 /    2097152.0,
                      -227960855456097.0 /    1048576.0,
                       442443742255071.0 /      65536.0);
    rot = new Rotation(u1, u2, Vector3D.PLUS_I,Vector3D.PLUS_J);
    Assert.assertEquals( 0.1234567890123456789012345, rot.getQ0(), 1.0e-15);
    Assert.assertEquals( 0.2345678901234567890123456, rot.getQ1(), 1.0e-15);
    Assert.assertEquals( 0.3456789012345678901234567, rot.getQ2(), 1.0e-15);
    Assert.assertEquals( 0.4567890123456789012345678, rot.getQ3(), 1.0e-15);

    // Additional test case 2
    u1 = new Vector3D(-6234567894561.0 /  268435456.0,
                      -2784608829631843.0 /  268435456.0,
                      -1982921525525679.0 / 4294967296.0);
    u2 = new Vector3D( -784479968720879.0 /    2097152.0,
                      -227536492826097.0 /    1048576.0,
                       443208822255071.0 /      65536.0);
    rot = new Rotation(u1, u2, Vector3D.PLUS_J,Vector3D.PLUS_K);
    Assert.assertEquals( 0.9876543210987654321098765, rot.getQ0(), 1.0e-15);
    Assert.assertEquals(-0.9876543210987654321098765, rot.getQ1(), 1.0e-15);
    Assert.assertEquals( 0.9876543210987654321098765, rot.getQ2(), 1.0e-15);
    Assert.assertEquals(-0.9876543210987654321098765, rot.getQ3(), 1.0e-15);
}
@Test
public void test3() {

  // Existing test cases

  Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 1.7); // Change input vector to (0, 0, 0)
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

  r1 = new Rotation(0, 0, 0, 0, false); // Change input quaternions to zeros
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  r1 = new Rotation(new Vector3D(1, 0, 0), 1.7); // Change input vector to (1, 0, 0)
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

}
  @Test
  public void test4() {
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

    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, true);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
  }
  @Test
  public void test5() {
    Rotation r1 = new Rotation(new Vector3D(4, -1, 3), 2.5);
    double n = 10;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               false);
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
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 0);
    double n = 1;
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
  public void test7() {
  
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 1);
    assertEquals(0.0, r1.getQ3(), 0.0);
    
    r1 = new Rotation(new Vector3D(1, 0, 0), 1);
    assertEquals(0.0, r1.getQ3(), 0.0);

    r1 = new Rotation(new Vector3D(0, 1, 0), 1);
    assertEquals(0.0, r1.getQ3(), 0.0);
    
    r1 = new Rotation(new Vector3D(0, 0, 1), 1);
    assertEquals(0.0, r1.getQ3(), 0.0);
    
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    assertEquals(-0.7332083444962897, r1.getQ3(), 0.0);
    
    r1 = new Rotation(new Vector3D(-5, 3, 2), 3.4);
    assertEquals(0.7597156724924522, r1.getQ3(), 0.0);
  
  }
  @Test
  public void test8() {

    // Regression test case 1
    Rotation r = new Rotation(new Vector3D(0, 0, 0), 0);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    checkVector(r.getAxis(), Vector3D.PLUS_I);
    checkAngle(r.getAngle(), 0);

    // Regression test case 2
    r = new Rotation(Vector3D.PLUS_I, 0);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);
    checkVector(r.getAxis(), Vector3D.PLUS_I);
    checkAngle(r.getAngle(), 0);
  }
  @Test
  public void test9() {
    // Regression test case 1
    Rotation r = new Rotation(1, 0, 0, 0, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test10() {

    // Regression test case 1
    Vector3D u1 = new Vector3D(1, 0, 0);
    Vector3D u2 = new Vector3D(0, 1, 0);
    Vector3D v1 = new Vector3D(0, 0, 1);
    Vector3D v2 = new Vector3D(-1, 0, 1);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);

    // Regression test case 2
    Vector3D u1 = new Vector3D(0, 1, 0);
    Vector3D u2 = new Vector3D(1, 0, 0);
    Vector3D v1 = new Vector3D(0, 0, 1);
    Vector3D v2 = new Vector3D(-1, 1, 0);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);

    // Regression test case 3
    Vector3D u1 = new Vector3D(1, 0, 0);
    Vector3D u2 = new Vector3D(0, 1, 0);
    Vector3D v1 = new Vector3D(0, 0, 1);
    Vector3D v2 = new Vector3D(1, 1, 1);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(1, -1, 1).normalize());
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(1, 1, -1).normalize());
  }
  @Test
  public void test11() {
    // Test when q0 is -1
    double q0 = -1;
    double q1 = 0.5;
    double q2 = 0.6;
    double q3 = 0.9;
    double expected = 2 * FastMath.acos(-q0);
    double actual = getAngle(q0, q1, q2, q3);
    assertEquals(expected, actual, 1e-6);

    // Test when q0 is 0.2
    q0 = 0.2;
    q1 = -0.8;
    q2 = 0.5;
    q3 = 0.4;
    expected = 2 * FastMath.acos(q0);
    actual = getAngle(q0, q1, q2, q3);
    assertEquals(expected, actual, 1e-6);
    
    // Test when q0 is between -0.1 and 0.1
    q0 = 0.05;
    q1 = -0.9;
    q2 = 0.2;
    q3 = 0.3;
    expected = 2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3));
    actual = getAngle(q0, q1, q2, q3);
    assertEquals(expected, actual, 1e-6);
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

    // Regression test cases
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = -Math.PI / 2; alpha1 <= Math.PI / 2; alpha1 += Math.PI / 4) {
        for (double alpha2 = -Math.PI / 2; alpha2 <= Math.PI / 2; alpha2 += Math.PI / 4) {
          for (double alpha3 = -Math.PI / 2; alpha3 <= Math.PI / 2; alpha3 += Math.PI / 4) {
            Rotation r = new Rotation(CardanOrders[i], alpha1, alpha2, alpha3);
            try {
              r.getAngles(CardanOrders[i]);
            } catch (CardanEulerSingularityException cese) {
              Assert.fail("no exception should have been caught: " + cese.getMessage());
            }
          }
        }
      }
    }

    for (int i = 0; i < EulerOrders.length; ++i) {
      for (double alpha1 = 0; alpha1 <= Math.PI / 2; alpha1 += Math.PI / 8) {
        for (double alpha2 = 0; alpha2 <= Math.PI / 2; alpha2 += Math.PI / 8) {
          for (double alpha3 = 0; alpha3 <= Math.PI / 2; alpha3 += Math.PI / 8) {
            Rotation r = new Rotation(EulerOrders[i], alpha1, alpha2, alpha3);
            try {
              r.getAngles(EulerOrders[i]);
            } catch (CardanEulerSingularityException cese) {
              Assert.fail("no exception should have been caught: " + cese.getMessage());
            }
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
                                 {  0.5,  0.5,  -0.707 },
                                 { -0.5,  0.5,   0.707 },
                                 {  0.707,  -0.707,  0 }
                               }, 1.0e-10),
                  1, 1, 1, 1);

    checkRotation(new Rotation(new double[][] {
                                 {  -0.5,  0.5,  -0.707 },
                                 { 0.5,  0.5,   0.707 },
                                 {  -0.707,  0.707,  0 }
                               }, 1.0e-10),
                  -1, 1, 1, 1);

    checkRotation(new Rotation(new double[][] {
                                 {  -0.5,  -0.5,  -0.707 },
                                 { -0.5,  0.5,   0.707 },
                                 {  0.707,  0.707,  0 }
                               }, 1.0e-10),
                  -1, -1, 1, 1);

    checkRotation(new Rotation(new double[][] {
                                 {  0.5,  -0.5,  -0.707 },
                                 { 0.5,  0.5,   0.707 },
                                 {  -0.707,  0.707,  0 }
                               }, 1.0e-10),
                  1, -1, 1, 1);

  }
  public void test13() {

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
  public void test14() {
    Rotation r = new Rotation(new Vector3D(1, -1, 1), 0.5);
    Vector3D u = new Vector3D(1, 1, 1);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test15() {
    Rotation r = new Rotation(new Vector3D(-1, 0, 0), 1.0);
    Vector3D u = new Vector3D(2, -1, 3);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test16() {
    Rotation r = new Rotation(new Vector3D(-2, 3, -5), 1.7);
    Vector3D u = new Vector3D(1, 2, 3);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test17() {
    Rotation r = new Rotation(new Vector3D(-1, 3, -2), 0.3);
    Vector3D u = new Vector3D(-2, 1, -3);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test18() {
    Rotation r = new Rotation(new Vector3D(2, -3, 5), 2.0);
    Vector3D u = new Vector3D(3, 4, 5);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test19() {
    Rotation r = new Rotation(new Vector3D(-4, 3, -2), 0.1);
    Vector3D u = new Vector3D(-1, -2, -3);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test20() {
    Rotation r = new Rotation(new Vector3D(3, -2, 1), 0.7);
    Vector3D u = new Vector3D(2, 3, 1);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test21() {
    Rotation r = new Rotation(new Vector3D(-1, 0, 0), 0.5);
    Vector3D u = new Vector3D(1, 1, 3);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test22() {
    Rotation r = new Rotation(new Vector3D(2, -1, 3), 1.0);
    Vector3D u = new Vector3D(-2, -1, -3);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
  }
  @Test
  public void test23() {
    Rotation r = new Rotation(new Vector3D(-2, 3, -5), 2.0);
    Vector3D u = new Vector3D(-3, -4, -5);
    // Expecting u after applying applyInverseTo and applyTo methods
    checkVector(u, r.applyInverseTo(r.applyTo(u)));
    checkVector(u, r.applyTo(r.applyInverseTo(u)));
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
  public void test24() {

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
  public void test25() {

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
  public void test26() {

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
  public void test29() {

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
  public void test30() {

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
  public void test31() {
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test32() {

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
  public void test33() {

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
  public void test34() throws NotARotationMatrixException {
    try {
      new Rotation(new double[][] {
                     { Double.NaN, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test35() {
    try {
      new Rotation(new Vector3D(Double.NaN, 0, 0), FastMath.PI / 2);
      Assert.fail("Expecting IllegalArgumentException");
    } catch (IllegalArgumentException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test36() {
    try {
      new Rotation(0, Double.NaN, 0, 0, false);
      Assert.fail("Expecting IllegalArgumentException");
    } catch (IllegalArgumentException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test37() {
    try {
      new Rotation(new Vector3D(3, 0, 0), new Vector3D(Double.NaN, 0, 0),
                   new Vector3D(0, 0, 2), new Vector3D(0, Double.NaN, 2));
      Assert.fail("Expecting IllegalArgumentException");
    } catch (IllegalArgumentException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test38() {
    try {
      new Rotation(new Vector3D(3, 2, 1), new Vector3D(Double.NaN, 2, 2));
      Assert.fail("Expecting IllegalArgumentException");
    } catch (IllegalArgumentException nrme) {
        // expected behavior
    }
  }
  @Test
  public void test39() {
  
    // Test case 1: Change the input Rotation
    Rotation r = new Rotation(new Vector3D(-2, -3, -5), 1.7);
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
    
    // Test case 2: Change the values of lambda and phi
    r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    for (double lambda = 0; lambda < 6.2; lambda += 0.1) {
      for (double phi = -1.55; phi < 1.55; phi += 0.1) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
    
    // Test case 3: Change the input Rotation
    r = new Rotation(new Vector3D(2, -3, 5), 0.9);
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
    
    // Test case 4: Change the values of lambda and phi
    r = new Rotation(new Vector3D(2, -3, 5), 1.7);
    for (double lambda = 0; lambda < 6.2; lambda += 0.1) {
      for (double phi = -1.55; phi < 1.55; phi += 0.1) {
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

    // Additional regression test 1
    checkRotation(new Rotation(new double[][] {
                                 { -0.38377,  0.46975, 0.79683 },
                                 { 0.85281,  0.51294, 0.09544 },
                                 { -0.35729,  0.71809, -0.59706 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);

    // Additional regression test 2
    checkRotation(new Rotation(new double[][] {
                                 { 0.873437,  0.018273, -0.486998 },
                                 { 0.091532, -0.958721,  0.269535 },
                                 { -0.477908, -0.283315, -0.832556 }
                               }, 1.0e-10),
                  0.36, 0.8, 0.288, 0.384);

    // Additional regression test 3
    checkRotation(new Rotation(new double[][] {
                                 { -0.165243,  0.508967,  0.844119 },
                                 { -0.930636, -0.338227,  0.140159 },
                                 {  0.327342, -0.790010,  0.517045 }
                               }, 1.0e-10),
                  0.384, 0.36, 0.8, 0.288);

    // Additional regression test 4
    checkRotation(new Rotation(new double[][] {
                                 { 0.318990, 0.681110, -0.658645 },
                                 { 0.412829, -0.697795, -0.585321 },
                                 { -0.852201, -0.218854, -0.475402 }
                               }, 1.0e-10),
                  0.288, 0.384, 0.36, 0.8);

    // Additional regression test 5
    double[][] m6 = { { 0.698622,  0.706084,  0.117145 },
                      { 0.637593, -0.676642,  0.367952 },
                      { -0.327941, -0.20464 ,  0.921369 } };
    r = new Rotation(m6, 1.0e-8);
    checkVector(r.applyTo(Vector3D.PLUS_I),
                new Vector3D(m6[0][0], m6[1][0], m6[2][0]));
    checkVector(r.applyTo(Vector3D.PLUS_J),
                new Vector3D(m6[0][1], m6[1][1], m6[2][1]));
    checkVector(r.applyTo(Vector3D.PLUS_K),
                new Vector3D(m6[0][2], m6[1][2], m6[2][2]));

  }
  @Test
  public void test40() {
      Rotation r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      Rotation r2 = new Rotation(1.0, 0.0, 0.0, 0.0, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      Assert.assertEquals(0.0, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(-1.0, 0.0, 0.0, 0.0, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(0.0, 1.0, 0.0, 0.0, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(0.0, -1.0, 0.0, 0.0, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(0.0, 0.0, 1.0, 0.0, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(0.0, 0.0, -1.0, 0.0, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(6.123233995736766E-17, 6.123233995736766E-17, 1.0, 6.123233995736766E-17, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);

      r1 = new Rotation(0.0, 0.0, 0.0, 1.0, true);
      r2 = new Rotation(-6.123233995736766E-17, -6.123233995736766E-17, -1.0, -6.123233995736766E-17, true);
      Assert.assertEquals(1.5707963267948968, distance(r1, r2), 1.0e-15);
  }
}