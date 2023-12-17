package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    Rotation r = new Rotation(0.0, 0.0, 0.0, 1.0, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {
    Rotation r = new Rotation(0.0, 0.0, 0.0, -1.0, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test2() {
    Rotation r = new Rotation(1.0, 0.0, 0.0, 0.0, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test3() {
    Rotation r = new Rotation(1.0, 0.0, 0.0, 0.0, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test4() {

    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 1.7);
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
      Vector3D u1 = new Vector3D(1.0 /  268435456.0,
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
  public void test6() {

    // Test Case 1: Changing input value of x-coordinate
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 23.5;
    Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                               n * r1.getQ2(), n * r1.getQ3(),
                               true);
    for (double x = -1.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Test Case 2: Changing input value of y-coordinate
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -1.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Test Case 3: Changing input value of z-coordinate
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -1.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Test Case 4: Changing input values of x, y, and z-coordinate
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -1.9; x < 0.9; x += 0.2) {
      for (double y = -1.9; y < 0.9; y += 0.2) {
        for (double z = -1.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Test Case 5: Changing input value of n
    r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    n = 21.5; // Changed value of n
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
  r1 = new Rotation(new Vector3D(0, 0, 0), 0);
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

  r1 = new Rotation(new Vector3D(1, 2, 3), 0.5);
  n = 2;
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

  r1 = new Rotation(new Vector3D(1, 2, 3), 1);
  n = 3;
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

  r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  // Regression test case 1
  r1 = new Rotation(new Vector3D(1, 2, 3), 0);
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

  // Regression test case 2
  r1 = new Rotation(new Vector3D(-1, -2, -3), 1);
  n = 12.0;
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

  // Regression test case 3
  r1 = new Rotation(0, 0, 0, 0, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

}
    @Test
    public void test9() {
  
      // Change the input value of getAxis() method
      Rotation r = new Rotation(new Vector3D(20, 10, 10), 2 * FastMath.PI / 3);
      checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
      checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_K);
      checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);
      double s = 1 / FastMath.sqrt(3);
      checkVector(r.getAxis(), new Vector3D(s, 2*s, s));
      checkAngle(r.getAngle(), 2 * FastMath.PI / 3);
  
      try {
        // Change the input value of getAxis() method
        new Rotation(new Vector3D(10, 20, 30), 2 * FastMath.PI / 3);
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
    public void test10() {
      Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
      Rotation reverted = r.revert();
      checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
      checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
      Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
      Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
    }
    @Test
    public void test11() {
  
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
  public void test12() {
    // Test Case 1
    double q0 = 0.2;
    double q1 = 0.1;
    double q2 = 0.3;
    double q3 = 0.4;
    double expectedResult = 2 * FastMath.asin(FastMath.sqrt(q1 * q1 + q2 * q2 + q3 * q3));

    Rotation rotation = new Rotation(q0, q1, q2, q3, true);
    double result = rotation.getAngle();

    assertEquals(expectedResult, result, 1e-15);
  }
  @Test
  public void test13() {
    // Test Case 2
    double q0 = 0.5;
    double q1 = 0.4;
    double q2 = 0.3;
    double q3 = 0.2;
    double expectedResult = 2 * FastMath.acos(q0);

    Rotation rotation = new Rotation(q0, q1, q2, q3, true);
    double result = rotation.getAngle();

    assertEquals(expectedResult, result, 1e-15);
  }
  @Test
  public void test14() {
    // Test Case 3
    double q0 = -0.3;
    double q1 = 0.2;
    double q2 = 0.1;
    double q3 = 0.4;
    double expectedResult = 2 * FastMath.acos(-q0);

    Rotation rotation = new Rotation(q0, q1, q2, q3, true);
    double result = rotation.getAngle();

    assertEquals(expectedResult, result, 1e-15);
  }
@Test
public void test15() {

  // Additional test cases for singularities

  // Test XYZ order
  Rotation r1 = new Rotation(RotationOrder.XYZ, FastMath.asin(1.2), 0.2, 0.3);
  try {
    r1.getAngles(RotationOrder.XYZ);
    Assert.fail("an exception should have been caught");
  } catch (CardanEulerSingularityException cese) {
    // expected behavior
  }

  // Test ZXY order
  Rotation r2 = new Rotation(RotationOrder.ZXY, 0.1, FastMath.acos(1.5), 0.3);
  try {
    r2.getAngles(RotationOrder.ZXY);
    Assert.fail("an exception should have been caught");
  } catch (CardanEulerSingularityException cese) {
    // expected behavior
  }

  // Test ZYX order
  Rotation r3 = new Rotation(RotationOrder.ZYX, 0.1, 0.2, FastMath.asin(2.1));
  try {
    r3.getAngles(RotationOrder.ZYX);
    Assert.fail("an exception should have been caught");
  } catch (CardanEulerSingularityException cese) {
    // expected behavior
  }

}
@Test
public void test16() throws CardanEulerSingularityException {

  // Additional test cases for angles

  // Test XYZ order
  Rotation r4 = new Rotation(RotationOrder.XYZ, FastMath.PI, 0.2, 0.3);
  double[] angles4 = r4.getAngles(RotationOrder.XYZ);
  checkAngle(angles4[0], FastMath.PI);
  checkAngle(angles4[1], 0.2);
  checkAngle(angles4[2], 0.3);

  // Test YXZ order
  Rotation r5 = new Rotation(RotationOrder.YXZ, 0.1, FastMath.PI, 0.3);
  double[] angles5 = r5.getAngles(RotationOrder.YXZ);
  checkAngle(angles5[0], 0.1);
  checkAngle(angles5[1], FastMath.PI);
  checkAngle(angles5[2], 0.3);

  // Test XYX order
  Rotation r6 = new Rotation(RotationOrder.XYX, 0.1, FastMath.PI, 0.3);
  double[] angles6 = r6.getAngles(RotationOrder.XYX);
  checkAngle(angles6[0], 0.1);
  checkAngle(angles6[1], FastMath.PI);
  checkAngle(angles6[2], 0.3);

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
    
    // Additional regression test cases
    try {
      new Rotation(new double[][] {
                     {  0.908307, -0.403972, -0.114543 },
                     {  0.309033,  0.741162,  0.594286 },
                     { -0.281051, -0.535869,  0.796489 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }

    try {
      new Rotation(new double[][] {
                     { -0.391684,  0.751718,  0.529131 },
                     {  0.893871, -0.296297,  0.335428 },
                     {  0.222010,  0.589124, -0.775711 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test17() {

    Vector3D u = new Vector3D(1.1, 2.2, 3.3);
    Rotation r = new Rotation(new double[][] {
                               {  0.445888,  0.797184, -0.407040 },
                               { -0.354816,  0.574912,  0.737280 },
                               {  0.821760, -0.184320,  0.539200 }
                             }, 1.0e-10);
    Vector3D expectedResult = new Vector3D(1.15536, 3.88064, 2.1504);

    checkVector(r.applyTo(u), expectedResult);
  }
  @Test
  public void test18() {

    Vector3D u = new Vector3D(-0.9, -1.8, -2.7);
    Rotation r = new Rotation(new double[][] {
                               { -0.445888,  0.797184, -0.407040 },
                               {  0.354816,  0.574912,  0.737280 },
                               {  0.821760,  0.184320, -0.539200 }
                             }, 1.0e-10);
    Vector3D expectedResult = new Vector3D(-3.36672, 0.46656, -1.62336);

    checkVector(r.applyTo(u), expectedResult);
  }
  @Test
  public void test19() {

    Vector3D u = new Vector3D(0.4, 0.5, 0.6);
    Rotation r = new Rotation(new double[][] {
                               {  0.234819, -0.613249, -0.680426 },
                               { -0.842819, -0.406249,  0.350426 },
                               { -0.484819,  0.676249, -0.319574 }
                             }, 1.0e-10);
    Vector3D expectedResult = new Vector3D(-0.132484, 0.02715, -0.292734);

    checkVector(r.applyTo(u), expectedResult);
  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       { 1.0, 1.0, 0.0 },
                       { 1.0, 0.0, 0.0 },
                       { 0.0, 1.0, 1.0 }
                     }, 1.0e-7);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.445888,  0.797184, -0.407040 },
                       { -0.354816,  0.574912,  0.737280 },
                       {  0.821760, -0.184320,  0.539200 }
                     }, 1.0e-7);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }

  }
  @Test
    throws NotARotationMatrixException {

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
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.2,  0.1, 0.3 },
                       { -0.5,  0.6, 0.4 },
                       {  0.8, -0.9, 0.5 }
                     }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.44485714148,  0.797266386863, -0.40748093649 },
                       { -0.354990382683,  0.574607066048,  0.737129286494 },
                       {  0.822025573192, -0.183558053533,  0.538635432319 }
                     }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.299119203705,  0.819794790817, -0.488749714348 },
                       { -0.489472551916,  0.560297108516,  0.669038706012 },
                       {  0.818412785165, -0.107991897669,  0.564512934651 }
                     }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.3,  0.7, -0.6 },
                       { -0.4,  0.6,  0.7 },
                       {  0.9, -0.2,  0.5 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.2,  0.4, 0.5 },
                       { -0.5,  0.3, 0.1 },
                       {  0.3, -0.8, 0.7 }
                     }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.44417558811,  0.797487133997, -0.407921055007 },
                       { -0.359699947646,  0.573612704475,  0.737271240782 },
                       {  0.821848914846, -0.187534753493,  0.538717154362 }
                     }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.433169125653,  0.805388209966, -0.408545785344 },
                       { -0.377862014581,  0.549799775104,  0.74495267331 },
                       {  0.818412785165, -0.222513535791,  0.531029375457 }
                     }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }

  }
  @Test
    throws NotARotationMatrixException {

    try {
        new Rotation(new double[][] {
                       {  0.199383797248,  0.941830957154, -0.270415140437 },
                       { -0.810410041259,  0.299825993114,  0.50393095383 },
                       {  0.552787017093,  0.14748787011,  0.820323027552 }
                     }, 1.0e-10);
        Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
        // expected behavior
    }

  }
  @Test
  public void test20() {

    // Test with different rotation values
    Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
    Rotation r2 = new Rotation(0.5, 0.6, 0.7, 0.8, true);
    checkRotation(r2, 0.5, 0.6, 0.7, 0.8);
    Rotation result = r1.applyTo(r2);
    checkRotation(result, -0.790, 1.420, -0.516, 0.044);
    
    // Test with different input values
    Vector3D vec1 = new Vector3D(0, 0, 1);
    Vector3D vec2 = new Vector3D(1, 2, 3);
    Vector3D vec3 = new Vector3D(-1, -2, -3);
    result = r1.applyTo(vec1);
    checkVector(result, new Vector3D(0.224, 0.280, 0.336));
    result = r1.applyTo(vec2);
    checkVector(result, new Vector3D(0.284, 1.416, 2.884));
    result = r1.applyTo(vec3);
    checkVector(result, new Vector3D(-0.284, -1.416, -2.884));
  }
  @Test
  public void test21() {
    
    // Test with different rotation values
    Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
    Rotation r2 = new Rotation(0.5, 0.6, 0.7, 0.8, true);
    checkRotation(r2, 0.5, 0.6, 0.7, 0.8);
    Rotation result = r1.applyInverseTo(r2);
    checkRotation(result, 0.575, -0.700, 0.100, 0.444);
    
    // Test with different input values
    Vector3D vec1 = new Vector3D(0, 0, 1);
    Vector3D vec2 = new Vector3D(1, 2, 3);
    Vector3D vec3 = new Vector3D(-1, -2, -3);
    result = r1.applyInverseTo(vec1);
    checkVector(result, new Vector3D(0.224, 0.280, -0.336));
    result = r1.applyInverseTo(vec2);
    checkVector(result, new Vector3D(-0.284, 1.416, -2.884));
    result = r1.applyInverseTo(vec3);
    checkVector(result, new Vector3D(0.284, -1.416, 2.884));
  }
  @Test
  public void test22() {
    
    // Test with different rotation values
    Rotation r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
    Rotation r2 = new Rotation(0.5, 0.6, 0.7, 0.8, true);
    Rotation r3 = r2.applyTo(r1);
    checkRotation(r3, -0.790, 1.420, -0.516, 0.044);
    
    // Test with different input values
    Vector3D vec1 = new Vector3D(0, 0, 1);
    Vector3D vec2 = new Vector3D(1, 2, 3);
    Vector3D vec3 = new Vector3D(-1, -2, -3);
    Vector3D expected = r1.applyTo(r2.applyTo(vec1));
    checkVector(r3.applyTo(vec1), expected);
    expected = r1.applyTo(r2.applyTo(vec2));
    checkVector(r3.applyTo(vec2), expected);
    expected = r1.applyTo(r2.applyTo(vec3));
    checkVector(r3.applyTo(vec3), expected);
  }
  @Test
  public void test23() {
    // Regression test case for mutant 1
    Rotation r = new Rotation(0, 0, 0, 0, true);
    Vector3D result = r.applyInverseTo(new Vector3D(1, 2, 3));
    Assert.assertEquals(result.getX(), -1.0, 1.0e-12);
    Assert.assertEquals(result.getY(), -2.0, 1.0e-12);
    Assert.assertEquals(result.getZ(), -3.0, 1.0e-12);
    
    // Regression test case for mutant 2
    r = new Rotation(1, 0, 0, 0, true);
    result = r.applyInverseTo(new Vector3D(-1, 0, 0));
    Assert.assertEquals(result.getX(), 1.0, 1.0e-12);
    Assert.assertEquals(result.getY(), 0.0, 1.0e-12);
    Assert.assertEquals(result.getZ(), 0.0, 1.0e-12);
    
    // Regression test case for mutant 3
    r = new Rotation(1, 1, 1, 1, true);
    result = r.applyInverseTo(new Vector3D(-1, -1, -1));
    Assert.assertEquals(result.getX(), 3.0, 1.0e-12);
    Assert.assertEquals(result.getY(), 3.0, 1.0e-12);
    Assert.assertEquals(result.getZ(), 3.0, 1.0e-12);
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
    
    // add regression tests here
    
  }
  @Test
  public void test24() {

    Rotation r1 = new Rotation(new Vector3D(1, 0, 0), 0.5);
    Rotation r2 = new Rotation(new Vector3D(0, 1, 0), 0.5);
    Assert.assertEquals(0.0, distance(r1, r2), 1e-10);

    r1 = new Rotation(new Vector3D(0, 0, 1), 0.4);
    r2 = new Rotation(new Vector3D(1, 0, 0), 0.5);
    Assert.assertEquals(0.1, distance(r1, r2), 1e-10);

    r1 = new Rotation(new Vector3D(0, 1, 0), 2.0);
    r2 = new Rotation(new Vector3D(0, 0, 1), 1.0);
    Assert.assertEquals(1.0, distance(r1, r2), 1e-10);

    r1 = new Rotation(new Vector3D(1, 1, 1), 0.3);
    r2 = new Rotation(new Vector3D(1, -1, 1), 0.3);
    Assert.assertEquals(0.0, distance(r1, r2), 1e-10);

  }
  @Test
  public void test25() {

    // Creating two Rotation objects with identical
    // rotation matrices
    Rotation r1 = new Rotation(new double[][] {
      { 0.445888,  0.797184, -0.407040 },
      { 0.821760, -0.184320,  0.539200 },
      { -0.354816,  0.574912,  0.737280 }
    }, 1.0e-10);
    Rotation r2 = new Rotation(new double[][] {
      { 0.445888,  0.797184, -0.407040 },
      { 0.821760, -0.184320,  0.539200 },
      { -0.354816,  0.574912,  0.737280 }
    }, 1.0e-10);
    
    Assert.assertEquals(0.0, distance(r1, r2), 1.0e-10);

    // Creating two Rotation objects with different
    // rotation matrices
    r1 = new Rotation(new double[][] {
      { 0.445888,  0.797184, -0.407040 },
      { 0.821760, -0.184320,  0.539200 },
      { -0.354816,  0.574912,  0.737280 }
    }, 1.0e-10);
    r2 = new Rotation(new double[][] {
      { 0.539200,  0.737280,  0.407040 },
      { 0.184320, -0.574912,  0.797184 },
      { 0.821760, -0.354816, -0.445888 }
    }, 1.0e-10);
    
    Assert.assertEquals(Math.PI/2, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test26() {

    Vector3D u1 = new Vector3D(3, 0, 0);
    Vector3D u2 = new Vector3D(0, 5, 0);
    Vector3D v1 = new Vector3D(0, 0, 2);
    Vector3D v2 = new Vector3D(-2, 0, 2);
    Rotation r1 = new Rotation(u1, u2, v1, v2);
    Rotation r2 = new Rotation(1, 0, 0, 0, true);

    Assert.assertEquals(Math.PI/2, distance(r1, r2), 1.0e-10);

    u1 = new Vector3D(1, 0, 0);
    u2 = new Vector3D(0, 1, 0);
    v1 = new Vector3D(2, 0, 0);
    v2 = new Vector3D(0, 2, 0);
    r1 = new Rotation(u1, u2, v1, v2);
    r2 = new Rotation(0, 0, 0, 1, true);
    
    Assert.assertEquals(Math.PI, distance(r1, r2), 1.0e-10);
    
  }
  @Test
  public void test27() {
    Rotation r1 = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation r2 = new Rotation(0.001, 0.36, 0.48, 0.8, false);
    
    Assert.assertEquals(0.0, distance(r1, r2), 1.0e-10);
    
    r1 = new Rotation(0.1, 0.2, 0.3, 0.4, true);
    r2 = new Rotation(0.4, 0.3, 0.2, 0.1, false);
    
    Assert.assertEquals(Math.PI, distance(r1, r2), 1.0e-10);
  }
  @Test
  public void test28() {

    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(new Vector3D(2, -3, 5), -1.7);

    Assert.assertEquals(0.0, distance(r1, r2), 1.0e-10);
    
    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    r2 = new Rotation(-0.288, -0.384, -0.36, -0.8, false);
    
    Assert.assertEquals(0.0, distance(r1, r2), 1.0e-10);
    
  }
  @Test
  public void test29() {

    Rotation r1 = new Rotation(1, 0, 0, 0, true);
    Rotation r2 = new Rotation(1, 0, 0, 0, false);

    Assert.assertEquals(Math.PI, distance(r1, r2), 1.0e-10);
    
    r1 = new Rotation(0, 1, 0, 0, true);
    r2 = new Rotation(0, 1, 0, 0, false);
    
    Assert.assertEquals(0.0, distance(r1, r2), 1.0e-10);
    
  }
}