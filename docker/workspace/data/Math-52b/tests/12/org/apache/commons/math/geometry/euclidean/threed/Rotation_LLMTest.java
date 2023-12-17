package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    // Original test case
    Rotation r1 = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted1 = r1.revert();
    checkRotation(r1.applyTo(reverted1), 1, 0, 0, 0);
    checkRotation(reverted1.applyTo(r1), 1, 0, 0, 0);
    Assert.assertEquals(r1.getAngle(), reverted1.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r1.getAxis(), reverted1.getAxis()), 1.0e-12);
    
    // Additional test cases
    Rotation r2 = new Rotation(0.0, 0.0, 0.0, 0.0, false);
    Rotation reverted2 = r2.revert();
    checkRotation(r2.applyTo(reverted2), 1, 0, 0, 0);
    checkRotation(reverted2.applyTo(r2), 1, 0, 0, 0);
    Assert.assertEquals(r2.getAngle(), reverted2.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r2.getAxis(), reverted2.getAxis()), 1.0e-12);
    
    Rotation r3 = new Rotation(1.0, 1.0, 1.0, 1.0, true);
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
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(2 * r1.getQ0(), 2 * r1.getQ1(),
                               2 * r1.getQ2(), 2 * r1.getQ3(),
                               true);
    Vector3D u = new Vector3D(0, 0, 0);
    Assert.assertEquals(r2.applyTo(u), r1.applyTo(u)); // mutant can be killed
  }
  @Test
  public void test4() {
    Rotation r1 = new Rotation(new Vector3D(-2, 3, -5), 1.7);
    Rotation r2 = new Rotation(-1 * r1.getQ0(), -1 * r1.getQ1(),
                               -1 * r1.getQ2(), -1 * r1.getQ3(),
                               true);
    Vector3D u = new Vector3D(1, 1, 1);
    Assert.assertEquals(r2.applyTo(u), r1.applyTo(u)); // mutant can be killed
  }
  @Test
  public void test5() {
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 1.7);
    Rotation r2 = new Rotation(0 * r1.getQ0(), 0 * r1.getQ1(),
                               0 * r1.getQ2(), 0 * r1.getQ3(),
                               true);
    Vector3D u = new Vector3D(-1, -1, -1);
    Assert.assertEquals(r2.applyTo(u), r1.applyTo(u)); // mutant can be killed
  }
  @Test
  public void test6() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    Rotation r2 = new Rotation(3 * r1.getQ0(), 3 * r1.getQ1(),
                               3 * r1.getQ2(), 3 * r1.getQ3(),
                               true);
    Vector3D u = new Vector3D(0.5, -0.5, 0.5);
    Assert.assertEquals(r2.applyTo(u), r1.applyTo(u)); // mutant can be killed
  }
  @Test
  public void test7() {
    Rotation r1 = new Rotation(new Vector3D(-2, 3, -5), 1.7);
    Rotation r2 = new Rotation(-2 * r1.getQ0(), -2 * r1.getQ1(),
                               -2 * r1.getQ2(), -2 * r1.getQ3(),
                               true);
    Vector3D u = new Vector3D(0, 0, 0);
    Assert.assertEquals(r2.applyTo(u), r1.applyTo(u)); // mutant can be killed
  }
@Test
public void test8() {

   Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 0.0);
   double n = 0.0;
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

   r1 = new Rotation( 0.0,  0.0,  0.0,  0.0, false);
   checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
@Test
public void test9() {

   Rotation r1 = new Rotation(new Vector3D(-1, -2, -3), -4.5);
   double n = -1.5;
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

   r1 = new Rotation(-0.3, -0.4, -0.6, -0.2, false);
   checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
  @Test
  public void test10() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 0);
    double n = 0;
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
    
    r1 = new Rotation(1, 0, 0, 0, true);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    
  }
  @Test
  public void test11() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(-5, 10, -2), 3);
    double n = 11.5;
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
    
    r1 = new Rotation(-0.12, 0.36, -0.48, 0.78, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    
  }
  @Test
  public void test12() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(-3, 5, -7), 2);
    double n = 35.8;
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
    
    r1 = new Rotation(-0.432, -0.7936, -0.0072, 0.1232, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    
  }
  @Test
  public void test13() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(1, -2, 3), 4);
    double n = 0.5;
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
    
    r1 = new Rotation(0.7071, -0.7071, 0, 0, true);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
    
  }
  @Test
  public void test14() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(-7, -6, -1), 5);
    double n = 4;
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

    r1 = new Rotation(-0.8824, -0.3922, -0.1961, 0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test15() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(4, -8, 12), 7);
    double n = 78.5;
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

    r1 = new Rotation(-0.4793, -0.9587, 0, 0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test16() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(10, 15, 25), 0.9);
    double n = 1.5;
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

    r1 = new Rotation(0.7198, 0.08, 0.1535, -0.6618, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test17() {
  
    // Existing test cases
  
    Rotation r1 = new Rotation(new Vector3D(-20, 30, -40), 10);
    double n = 2.1;
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

    r1 = new Rotation(-0.8528, 0.3407, -0.1255, 0.3579, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
@Test
public void test18() {
    // Create a Rotation object with different values for q3
    Rotation rotation1 = new Rotation(0.5, 0.5, 0.5, 0.5, true);
    Rotation rotation2 = new Rotation(0.25, 0.75, 0.25, 0.75, true);
    Rotation rotation3 = new Rotation(0.9, -0.1, -0.2, -0.3, true);
    
    // Verify that the q3 values are correctly returned by the getQ3() method
    assertEquals(0.5, rotation1.getQ3(), 0.0001);
    assertEquals(0.75, rotation2.getQ3(), 0.0001);
    assertEquals(-0.3, rotation3.getQ3(), 0.0001);
}
  @Test
  public void test19() {
    Rotation r = new Rotation(new Vector3D(10, 10, 10), Math.PI / 6);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(1, 0, -1).normalize());
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(1, 1, 0).normalize());
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 1, 1).normalize());
    double s = 1 / Math.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(s, s, s));
    checkAngle(r.getAngle(), Math.PI / 6);

    try {
      new Rotation(new Vector3D(0, 0, 0), Math.PI / 6);
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
  public void test20() {
    Rotation r = new Rotation(0.5, 0.6, -0.8, -0.1, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test21() {
    Vector3D u1 = new Vector3D(-3, 0, 0);
    Vector3D u2 = new Vector3D(0, -5, 0);
    Vector3D v1 = new Vector3D(0, 0, -2);
    Vector3D v2 = new Vector3D(-2, 0, -2);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-1, 0, -1).normalize());
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(1, 0, -1).normalize());

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
                     new Vector3D(0.5, 0.5,  -sqrt),
                     new Vector3D(0.5, 0.5, sqrt));
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
checkRotation(new Rotation(new double[][] {
  { 0.0, 1.0, 0.0 },
  { 1.0, 0.0, 0.0 },
  { 0.0, 0.0, 1.0 }
}, 0), 1, 0, 0, 0);
checkRotation(new Rotation(new double[][] {
  { 0.83203, -0.55012, -0.07139 },
  { 0.48293, 0.78164, -0.39474 },
  { 0.27296, 0.29396, 0.91602 }
}, 0), 1, 0, 0, 0);
checkRotation(new Rotation(new double[][] {
  { 0.4, 0.8, -0.4 },
  { -0.4, 0.6, 0.7 },
  { 0.8, -0.2, 0.5 }
}, 0), 1, 0, 0, 0);
@Test
public void test22() {

  // Additional test case 1
  RotationOrder order1 = RotationOrder.XYZ;
  Rotation r1 = new Rotation(order1, 0.1, 0.9, Math.PI);
  try {
    r1.getAngles(order1);
    Assert.fail("an exception should have been caught");
  } catch (CardanEulerSingularityException cese) {
    // expected behavior
  }

  // Additional test case 2
  RotationOrder order2 = RotationOrder.YXZ;
  Rotation r2 = new Rotation(order2, FastMath.PI, 0.2, FastMath.PI / 2);
  try {
    r2.getAngles(order2);
    Assert.fail("an exception should have been caught");
  } catch (CardanEulerSingularityException cese) {
    // expected behavior
  }

}
@Test
  throws CardanEulerSingularityException {

  // Additional test case 1
  RotationOrder order1 = RotationOrder.XYZ;
  Rotation r1 = new Rotation(order1, 1.5, 0.6, 4.2);
  double[] angles1 = r1.getAngles(order1);
  checkAngle(angles1[0], 1.5);
  checkAngle(angles1[1], 0.6);
  checkAngle(angles1[2], 4.2);

  // Additional test case 2
  RotationOrder order2 = RotationOrder.YZX;
  Rotation r2 = new Rotation(order2, 2.1, FastMath.PI / 4, 1.7);
  double[] angles2 = r2.getAngles(order2);
  checkAngle(angles2[0], 2.1);
  checkAngle(angles2[1], FastMath.PI / 4);
  checkAngle(angles2[2], 1.7);

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
    checkRotation(new Rotation(new double[][] {
                                 { 0.0, -0.923879, 0.382683 },
                                 { -0.382683, 0.0, -0.923879 },
                                 { 0.923879, 0.382683, 0.0 }
                               }, 1.0e-10),
                  0.738460, 0.102980, 0.667650, 0.031770);

    checkRotation(new Rotation(new double[][] {
                                 { -1.0, 0.0, 0.0 },
                                 { 0.0, -1.0, 0.0 },
                                 { 0.0, 0.0, -1.0 }
                               }, 1.0e-10),
                  0.0, 0.0, 0.0, -1.0);
    
    checkRotation(new Rotation(new double[][] {
                                 { -1.0, 0.0, 0.0 },
                                 { 0.0, -0.980785, -0.195090 },
                                 { 0.0, -0.195090, 0.980785 }
                               }, 1.0e-10),
                  0.0, 0.383972, 0.924999, 0.0);

    double[][] m6 = { { 0.0, 1.0, 0.0 },
                      { -1.0, 0.0, 0.0 },
                      { 0.0, 0.0, 1.0 } };
    r = new Rotation(m6, 1.0e-7);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_I);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_K);

    double[][] m7 = { { 0.707106, -0.707106, 0.0 },
                      { 0.707106, 0.707106, 0.0 },
                      { 0.0, 0.0, 1.0 } };
    r = new Rotation(m7, 1.0e-5);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);

    double[][] m8 = { { 0.653281, -0.270598, 0.707107 },
                      { 0.270598, 0.923880, 0.270598 },
                      { -0.707107, 0.270598, 0.653281 } };
    r = new Rotation(m8, 1.0e-10);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);

    double[][] m9 = { { -0.073417, 0.663414, 0.744727 },
                      { 0.194097, 0.730173, -0.654810 },
                      { -0.978009, 0.165131, -0.125333 } };
    r = new Rotation(m9, 1.0e-10);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.PLUS_I);

  }
  @Test
  public void test23() {

    Rotation r = new Rotation(new double[][] {
                     { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 1.0 }
                   }, 1.0e-7);

    Vector3D u = new Vector3D(1, 2, 3);
    Vector3D expected = new Vector3D(8, 2, -2);

    Assert.assertEquals(expected, r.applyTo(u));

  }
  @Test
  public void test24() {

    Rotation r = new Rotation(new double[][] {
                     { 0.756802, -0.655064, 0.0 },
                     { 0.360806, 0.416146, -0.835605 },
                     { 0.543740,  0.630584,  0.554908 }
                   }, 1.0e-7);

    Vector3D u = new Vector3D(-4, 2, 1);
    Vector3D expected = new Vector3D(5, 3, -2);

    Assert.assertEquals(expected, r.applyTo(u));

  }
  @Test
  public void test25() {

    Rotation r = new Rotation(new double[][] {
                    {  0.891007, -0.283062,  0.354455 },
                    {  0.130537,  0.930364,  0.343615 },
                    { -0.434711, -0.233459,  0.869350 }
                  }, 1.0e-7);

    Vector3D u = new Vector3D(3, 5, -2);
    Vector3D expected = new Vector3D(11, 1, -6);

    Assert.assertEquals(expected, r.applyTo(u));

  }
  @Test
  public void test26() {

    Rotation r = new Rotation(new double[][] {
                    { -0.560506, -0.365476, -0.743144 },
                    {  0.786068,  0.080640, -0.613941 },
                    {  0.264899, -0.927184,  0.265628 }
                  }, 1.0e-7);

    Vector3D u = new Vector3D(-3, -1, 4);
    Vector3D expected = new Vector3D(-8, -6, -5);

    Assert.assertEquals(expected, r.applyTo(u));

  }
  @Test
  public void test27() {

    Rotation r = new Rotation(new double[][] {
                    { -0.342020, -0.511370, -0.463525 },
                    {  0.602712,  0.262367, -0.753048 },
                    {  0.721895, -0.819152,  0.415227 }
                  }, 1.0e-7);

    Vector3D u = new Vector3D(0, 0, 0);
    Vector3D expected = new Vector3D(0, 0, 0);

    Assert.assertEquals(expected, r.applyTo(u));

  }
  @Test
  public void test28() {

    Rotation r = new Rotation(new double[][] {
                    { -0.776760,  0.282047, -0.564095 },
                    { -0.232193, -0.935414, -0.264264 },
                    { -0.586678, -0.213043,  0.781900 }
                  }, 1.0e-7);

    Vector3D u = new Vector3D(1, 1, 1);
    Vector3D expected = new Vector3D(-0.452096, -0.935414, 1.382647);

    Assert.assertEquals(expected, r.applyTo(u));

  }
  @Test
  public void test29() {

    Rotation r = new Rotation(new double[][] {
                    {  0.318872, -0.608587,  0.726396 },
                    { -0.938155, -0.304677,  0.162765 },
                    {  0.137267, -0.731630, -0.667768 }
                  }, 1.0e-7);

    Vector3D u = new Vector3D(5, -3, 1);
    Vector3D expected = new Vector3D(0.672206, -3.519670, -2.518810);

    Assert.assertEquals(expected, r.applyTo(u));

  }
  @Test
  public void test30() throws NotARotationMatrixException {
    double[][] m1 = { { 0.8775825618903728, 0.479425538604203, -0.0 },
                      { -0.479425538604203, 0.8775825618903728, -0.0 },
                      { -0.0, -0.0, 1.0 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { 4.606177223735287E-8, -0.577350258827209, 0 },
                      { 0.9999999776502769, 2.8284305856400187E-8, 0 },
                      { 0, 0, 1 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { 4.606177223735287E-8, 0.9999999776502769, 0 },
                                         { -0.577350258827209, 2.8284305856400187E-8, 0 },
                                         { 0, 0, 1 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test31() throws NotARotationMatrixException {
    double[][] m1 = { { -0.036340547077262026, 0.9991532461519383, 0 },
                      { 3.205193575084193E-4, 1.159534822763656E-9, -1.0 },
                      { -0.9993355106517815, -0.03633804695080339, 3.205193575084193E-4 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { -0.5760017673520536, 0.8177873339252816, 0 },
                      { 0.8177883218567864, 0.5760017673520536, -0.0 },
                      { 3.2051935750841747E-4, 0.9993355106517814, 0.03633804695079586 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { -0.5760017673520536, -0.036340547077262026, 3.205193575084193E-4 },
                                         { 0.8177873339252816, 0.9991532461519383, 1.159534822763656E-9 },
                                         { 0, 0, 1 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test32() throws NotARotationMatrixException {
    double[][] m1 = { { 0.6861905005534135, 0.01379042517680531, -0.727548332456176 },
                      { -0.20544726358615835, 0.9709981404022055, 0.12268230356788647 },
                      { 0.6980116927775553, -0.2384871597109845, 0.6757856739530863 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { 0.6864978462704619, -0.2028314032938619, 0.6982068774871298 },
                      { 0.1867778494438485, -0.959361196738699, -0.2101718226860299 },
                      { 0.7019162395699602, 0.20103364791225056, -0.6831999966673685 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { 0.6861905005534135, -0.20544726358615835, 0.6980116927775553 },
                                         { 0.01379042517680531, 0.9709981404022055, -0.2384871597109845 },
                                         { -0.727548332456176, 0.12268230356788647, 0.6757856739530863 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test33() throws NotARotationMatrixException {
    double[][] m1 = { { 0.3596487374481017, 0.04990955029887306, -0.9314231040746072 },
                      { -0.1804614758577606, 0.9827142296135487, 0.03813636789109415 },
                      { 0.9145749910400624, 0.17889062585528048, 0.36261555149064943 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { 0.361114624789013, -0.05317569093032866, 0.9303905474759246 },
                      { -0.28606687863750016, 0.9570310571429118, 0.04210412333712049 },
                      { 0.8862953319989326, 0.28539610311068126, -0.36492855208801823 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { 0.3596487374481017, -0.1804614758577606, 0.9145749910400624 },
                                         { 0.04990955029887306, 0.9827142296135487, 0.17889062585528048 },
                                         { -0.9314231040746072, 0.03813636789109415, 0.36261555149064943 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test34() throws NotARotationMatrixException {
    double[][] m1 = { { 0.9999285317745213, 6.391741964948973E-4, -2.251919652311451E-2 },
                      { -6.217605903481881E-4, 0.9999958362504299, 2.230392597929297E-2 },
                      { 2.253663746936608E-2, -2.2214738509333528E-2, 0.9995277694663571 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { -0.9206103526629737, 0.08682134891260721, -0.3802820125157324 },
                      { 0.3792598784339749, -6.493057365524119E-5, -0.9252806406618796 },
                      { -0.09705826466637462, -0.9927124211609311, -0.07474258937793722 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { -0.9206103526629737, 0.3792598784339749, -0.09705826466637462 },
                                         { 0.08682134891260721, -6.493057365524119E-5, -0.9927124211609311 },
                                         { -0.3802820125157324, -0.9252806406618796, -0.07474258937793722 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test35() throws NotARotationMatrixException {
    double[][] m1 = { { -0.8244593204800389, 0.39232187932015765, -0.4088101668104752 },
                      { 0.5396038266487533, 0.8393974096431973, -0.06591974064204848 },
                      { 0.16617922853429297, -0.37523926579277345, -0.9115862873431979 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { 0.65262058617195, -0.7282362560150563, -0.20796740725778287 },
                      { 0.7420161561230141, 0.6685582877610684, -0.052455098713654586 },
                      { 0.1478438302264541, 0.14995950866893782, 0.9774665125799899 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { 0.65262058617195, 0.7420161561230141, 0.1478438302264541 },
                                         { -0.7282362560150563, 0.6685582877610684, 0.14995950866893782 },
                                         { -0.20796740725778287, -0.052455098713654586, 0.9774665125799899 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test36() throws NotARotationMatrixException {
    double[][] m1 = { { -0.9994483139460488, 0.006023816166338915, 0.03323405967972617 },
                      { -0.032965471122040435, 0.055702141176461495, -0.9980163516579992 },
                      { -0.004515052985304792, -0.9984358430151826, -0.05595497055109835 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { -0.6316476079085331, -0.7717278601089483, 0.07178062925082879 },
                      { -0.2559607291222683, 0.4956440863802248, -0.8292230009630802 },
                      { 0.7329566138963382, -0.3981238257120231, -0.5510946526228846 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { -0.6316476079085331, -0.2559607291222683, 0.7329566138963382 },
                                         { -0.7717278601089483, 0.4956440863802248, -0.3981238257120231 },
                                         { 0.07178062925082879, -0.8292230009630802, -0.5510946526228846 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test37() throws NotARotationMatrixException {
    double[][] m1 = { { -0.44336381387265545, -0.4066462644778689, 0.7971718554792555 },
                      { 0.8454308970470894, -0.18055631336649736, 0.5012037665796436 },
                      { 0.2981192825565982, 0.8954446985983986, 0.33002207357678724 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { -0.751513786157536, 0.6147160862555631, 0.23747819464868307 },
                      { -0.5477772599215769, -0.5863963344363717, 0.596118508962722 },
                      { 0.36614730804677587, 0.5276241348962442, 0.7664242546853464 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { -0.751513786157536, -0.5477772599215769, 0.36614730804677587 },
                                         { 0.6147160862555631, -0.5863963344363717, 0.5276241348962442 },
                                         { 0.23747819464868307, 0.596118508962722, 0.7664242546853464 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test38() throws NotARotationMatrixException {
    double[][] m1 = { { -0.4606840198534011, 0.2281799019734584, -0.8572678229029077 },
                      { -0.8333690448883836, -0.10772640546950134, 0.5425132307245939 },
                      { 0.30467487236044704, 0.9665212247708162, 0.34309108353216027 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { -0.5367612941113755, -0.35868234956028196, -0.7656825297174263 },
                      { 0.35846025584406415, 0.7277107197051433, -0.584759068179148 },
                      { -0.7625275742561443, 0.5852740514569989, 0.2750582513250095 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { -0.5367612941113755, 0.35846025584406415, -0.7625275742561443 },
                                         { -0.35868234956028196, 0.7277107197051433, 0.5852740514569989 },
                                         { -0.7656825297174263, -0.584759068179148, 0.2750582513250095 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
  @Test
  public void test39() throws NotARotationMatrixException {
    double[][] m1 = { { -0.32324929855915286, -0.9038081326350428, 0.27923349317075497 },
                      { -0.4805717282768912, 0.4288411093787125, 0.76580620044584 },
                      { -0.8172830653716154, 0.00215149397781099, -0.5767395030245851 } };
    Rotation r1 = new Rotation(m1, 1.0e-15);
    
    double[][] m2 = { { 0.9922960026624039, 0.11867967471677539, 0.03229827192808298 },
                      { -0.11866073181208547, 0.9929290837528825, -0.0009716134902439457 },
                      { -0.03238730087037442, -0.0004454461218254337, 0.9994747598223133 } };
    Rotation r2 = new Rotation(m2, 1.0e-15);
    
    double[][] expectedInverseMatrix = { { 0.9922960026624039, -0.11866073181208547, -0.03238730087037442 },
                                         { 0.11867967471677539, 0.9929290837528825, -0.0004454461218254337 },
                                         { 0.03229827192808298, -0.0009716134902439457, 0.9994747598223133 } };
    
    Rotation inverseRotation = r1.computeInverseRotation(r2);
    
    assertArrayEquals(expectedInverseMatrix, inverseRotation.getMatrix());
  }
@Test
  throws NotARotationMatrixException {

  try {
    new Rotation(new double[][] {
                   { 0.0, 0.0, 0.0 },
                   { 0.0, 0.0, 0.0 },
                   { 0.0, 0.0, 0.0 }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  try {
    new Rotation(new double[][] {
                   {  0.0,  1.0, 0.0 },
                   {  1.0,  0.0, 0.0 },
                   {  0.0,  0.0, 0.0 }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  try {
      new Rotation(new double[][] {
                     {  0.0,  0.0, 0.0 },
                     { -0.4,  0.6, 0.7 },
                     {  0.8, -0.2, 0.5 }
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
                   { Double.NaN, 1.0, 0.0 },
                   { 1.0, 0.0, 0.0 },
                   { 0.0, 0.0, 1.0 }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  try {
    new Rotation(new double[][] {
                   {  Double.NaN,  0.0, 0.0 },
                   {  0.0, Double.NaN, 0.0 },
                   {  0.0,  0.0, Double.NaN }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  try {
      new Rotation(new double[][] {
                     {  Double.NaN,  0.0, 0.0 },
                     { -0.4,  Double.NaN, 0.7 },
                     {  0.8, -0.2, Double.NaN }
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
                   { Double.POSITIVE_INFINITY, 1.0, 0.0 },
                   { 1.0, 0.0, 0.0 },
                   { 0.0, 0.0, 1.0 }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  try {
    new Rotation(new double[][] {
                   {  Double.NEGATIVE_INFINITY,  0.0, 0.0 },
                   {  0.0, Double.POSITIVE_INFINITY, 0.0 },
                   {  0.0,  0.0, Double.NEGATIVE_INFINITY }
                 }, 1.0e-7);
    Assert.fail("Expecting NotARotationMatrixException");
  } catch (NotARotationMatrixException nrme) {
    // expected behavior
  }

  try {
      new Rotation(new double[][] {
                     {  Double.POSITIVE_INFINITY,  0.0, 0.0 },
                     { -0.4,  Double.NEGATIVE_INFINITY, 0.7 },
                     {  0.8, -0.2, Double.POSITIVE_INFINITY }
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
                     { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    
    // Regression test case for Exception NotARotationMatrixException
    try {
      new Rotation(new double[][] {
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 1.0, 0.0 },
                     { 0.0, 0.0, 2.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    
    ...
    
  }
  @Test
  public void test40() {
    
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
    
    ...
    
  }
  @Test
  public void test41() {
      
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

    // Regression test case for IllegalArgumentException
    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }
    
    ...
    
  }
  @Test
  public void test42() {
    
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
    
    // Regression test case for identity transformation
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
    
    ...
    
  }
  @Test
  public void test43() {
  
    Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
    
    ...
    
  }
  @Test
  public void test44() {
      
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
    
    ...
    
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
    
    ...
    
  }
  @Test
  public void test45() {
  
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
    
    ...
    
  }
  @Test
    throws NotARotationMatrixException {
    double[][] m = { { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 1.0 } };
    double threshold = 1.0e-7;

    double[][] newM = new double[3][3];
    for (int i = 0; i < m.length; i++) {
        newM[i] = Arrays.copyOf(m[i], m[i].length);
    }

    newM[1][1] += threshold;

    try {
      orthogonalizeMatrix(newM, threshold);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException e) {
      // Expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {
    double[][] m = { { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 1.0 } };
    double threshold = 1.0e-7;

    double[][] newM = new double[3][3];
    for (int i = 0; i < m.length; i++) {
        newM[i] = Arrays.copyOf(m[i], m[i].length);
    }

    newM[0][0] += threshold;

    try {
      orthogonalizeMatrix(newM, threshold);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException e) {
      // Expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {
    double[][] m = { { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 1.0 } };
    double threshold = 1.0e-7;

    double[][] newM = new double[3][3];
    for (int i = 0; i < m.length; i++) {
        newM[i] = Arrays.copyOf(m[i], m[i].length);
    }

    newM[2][2] += threshold;

    try {
      orthogonalizeMatrix(newM, threshold);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException e) {
      // Expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {
    double[][] m = { { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 1.0 } };
    double threshold = 1.0e-7;

    double[][] newM = new double[3][3];
    for (int i = 0; i < m.length; i++) {
        newM[i] = Arrays.copyOf(m[i], m[i].length);
    }

    newM[0][1] += threshold;

    try {
      orthogonalizeMatrix(newM, threshold);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException e) {
      // Expected behavior
    }
  }
  @Test
    throws NotARotationMatrixException {
    double[][] m = { { 0.0, 1.0, 0.0 },
                     { 1.0, 0.0, 0.0 },
                     { 0.0, 0.0, 1.0 } };
    double threshold = 1.0e-7;

    double[][] newM = new double[3][3];
    for (int i = 0; i < m.length; i++) {
        newM[i] = Arrays.copyOf(m[i], m[i].length);
    }

    newM[1][0] += threshold;

    try {
      orthogonalizeMatrix(newM, threshold);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException e) {
      // Expected behavior
    }
  }
  @Test
  public void test46() {
    // Test case covering mutant: r1.applyInverseTo(r2).getAngle() -> r2.applyInverseTo(r1).getAngle()
    // Mutant killed with input that involves applying rotation twice
    // Mutant will return wrong result with this input
    Rotation r1 = new Rotation(Vector3D.PLUS_K, FastMath.PI/4);
    Rotation r2 = new Rotation(Vector3D.PLUS_J, FastMath.PI/3);
    Assert.assertEquals(FastMath.PI/4, distance(r1, r2), 1e-6);

    // Test case covering mutant: r1.applyInverseTo(r2).getAngle() -> r2.applyInverseTo(r1).getAngle()
    // Mutant killed with input that involves applying rotation twice
    // Mutant will return wrong result with this input
    r1 = new Rotation(Vector3D.PLUS_J, FastMath.PI/3);
    r2 = new Rotation(Vector3D.PLUS_K, FastMath.PI/4);
    Assert.assertEquals(FastMath.PI/4, distance(r1, r2), 1e-6);

    // Test case covering mutant: r1.applyInverseTo(r2).getAngle() -> r2.applyInverseTo(r1).getAngle()
    // Mutant killed with input that involves applying rotation twice
    // Mutant will return wrong result with this input
    r1 = new Rotation(Vector3D.MINUS_I, FastMath.PI/6);
    r2 = new Rotation(Vector3D.MINUS_I, FastMath.PI/4);
    Assert.assertEquals(FastMath.PI/12, distance(r1, r2), 1e-6);
  }
}