package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
@Test
public void test0() {
  Rotation r = new Rotation(0.0, 0.0, 0.0, 0.0, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test1() {
  Rotation r = new Rotation(-0.001, -0.36, -0.48, -0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test2() {
  Rotation r = new Rotation(1000.0, 10000.0, 100000.0, 1000000.0, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
  @Test
  public void test3() {

    // Test case 1: Increase the value of n
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n1 = 100.0;
    Rotation r2 = new Rotation(n1 * r1.getQ0(), n1 * r1.getQ1(),
                               n1 * r1.getQ2(), n1 * r1.getQ3(),
                               true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Test case 2: Decrease the value of n
    double n2 = 0.1;
    r2 = new Rotation(n2 * r1.getQ0(), n2 * r1.getQ1(),
                      n2 * r1.getQ2(), n2 * r1.getQ3(),
                      true);
    for (double x = -0.9; x < 0.9; x += 0.2) {
      for (double y = -0.9; y < 0.9; y += 0.2) {
        for (double z = -0.9; z < 0.9; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
    
    // Test case 3: Change the values of r1
    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Test case 4: Change the values of r1 to zeros
    r1 = new Rotation(0.0, 0.0, 0.0, 0.0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Test case 5: Change the values of r1 to ones
    r1 = new Rotation(1.0, 1.0, 1.0, 1.0, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test4(){
      Vector3D u1 = new Vector3D(-1321008684645961.0 /  268435456.0,
                                 -5774608829631843.0 /  268435456.0,
                                 -3822921525525679.0 / 4294967296.0);
      
      // Test case 6: Change the value of u1 to zeros
      u1 = new Vector3D(0.0, 0.0, 0.0);
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
    Rotation r1 = new Rotation(new Vector3D(1, 2, 3), 0.5);
    Rotation r2 = new Rotation(new Vector3D(1, -2, 3), 0.5);
    Rotation r3 = new Rotation(new Vector3D(1, 2, -3), 0.5);

    assertEquals(0.8775825618903728, r1.getQ1(), 0.000001);
    assertEquals(-0.43980107312857, r2.getQ1(), 0.000001);
    assertEquals(0.4398010731285701, r3.getQ1(), 0.000001);
  }
  @Test
  public void test6() {
    Rotation r1 = new Rotation(new Vector3D(0, 0, 0), 0.5);
    Rotation r2 = new Rotation(new Vector3D(-1, -2, -3), 0.5);
    Rotation r3 = new Rotation(new Vector3D(4, 4, 4), 0.5);

    assertEquals(0, r1.getQ1(), 0.000001);
    assertEquals(0.39951929515294943, r2.getQ1(), 0.000001);
    assertEquals(-0.9770434371621776, r3.getQ1(), 0.000001);
  }
@Test
public void test7() {
   // Test case 1: q2 is positive
   Rotation r = new Rotation(0.5, 0.6, 0.7, 0.8, true);
   assertEquals(0.7, r.getQ2(), 0.0001);

   // Test case 2: q2 is negative
   r = new Rotation(0.5, -0.6, 0.7, 0.8, true);
   assertEquals(-0.7, r.getQ2(), 0.0001);

   // Test case 3: q2 is zero
   r = new Rotation(0.5, 0.6, 0.0, 0.8, true);
   assertEquals(0.0, r.getQ2(), 0.0001);
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

    // Additional test cases
    
    // Test when q3 is positive
    r1 = new Rotation(new Vector3D(1, 2, 3), 0.5);
    checkRotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), r1.getQ3());
    
    // Test when q3 is negative
    r1 = new Rotation(new Vector3D(0, 0, 0), 1.0);
    check Rotation(r1, r1.getQ0(), r1.getQ1(), r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test9() {

    ...

    // Regression test case 1
    r = new Rotation(new Vector3D(-10, -10, -10), 2 * FastMath.PI / 3);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_K), Vector3D.MINUS_I);
    double s = 1 / FastMath.sqrt(3);
    checkVector(r.getAxis(), new Vector3D(-s, -s, -s));
    checkAngle(r.getAngle(), 2 * FastMath.PI / 3);

    // Regression test case 2
    r = new Rotation(Vector3D.PLUS_K, FastMath.PI);
    checkVector(r.getAxis(), new Vector3D(0, 0, -1));
    checkAngle(r.getAngle(), FastMath.PI);

    // Regression test case 3
    checkVector(Rotation.IDENTITY.getAxis(), Vector3D.PLUS_I);
  }
  @Test
  public void test10() {
    ...

    // Regression test case 4
    r = new Rotation(-0.001, -0.36, -0.48, -0.8, true);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test11() {

    ...

    // Regression test case 5
    r = new Rotation(new Vector3D(-3, 0, 0), new Vector3D(0, -5, 0), new Vector3D(0, 0, -2), new Vector3D(2, 0, -2));
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.MINUS_K);
    checkVector(r.applyTo(Vector3D.PLUS_J), Vector3D.PLUS_I);
  }
}

  @Test
  public void test12() {
  }
  
  @Test
  public void test13() {
  }
  
  @Test
  public void test14() {
  }

  @Test
  public void test15() {
    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };   
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
  public void test16()
    throws CardanEulerSingularityException {
    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };   
    for (int i = 0; i < CardanOrders.length; ++i) {
      // Regression test case 3
      // Changing the alpha1 upper limit to a smaller number
      for (double alpha1 = 0.1; alpha1 < 3.2; alpha1 += 0.3) {
        // Regression test case 4
        // Changing the alpha2 lower limit to a larger number
        for (double alpha2 = -0.1; alpha2 < 1.55; alpha2 += 0.3) {
          // Regression test case 5
          // Changing the alpha3 lower limit to a larger number
          for (double alpha3 = 0.04; alpha3 < 6.2; alpha3 += 0.3) {
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
      // Regression test case 6
      // Changing the alpha1 upper limit to a smaller number
      for (double alpha1 = 0.1; alpha1 < 3.2; alpha1 += 0.3) {
        // Regression test case 7
        // Changing the alpha2 lower limit to a larger number
        for (double alpha2 = 0.4; alpha2 < 3.1; alpha2 += 0.3) {
          // Regression test case 8
          // Changing the alpha3 lower limit to a larger number
          for (double alpha3 = 0.04; alpha3 < 6.2; alpha3 += 0.3) {
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
  public void test17() throws NotARotationMatrixException {
    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 0.0, 0.0, 1.0 },
                      { 1.0, 0.0, 0.0 } };
  }

  
  @Test
  public void test18() throws NotARotationMatrixException {
    double[][] m2 = { { 0.83203, -0.55012, -0.07139 },
                      { 0.48293,  0.78164, -0.39474 },
                      { 0.27296,  0.29396,  0.91602 } };
  }

  
  @Test
  public void test19() throws NotARotationMatrixException {
    double[][] m3 = { { 0.445888,  0.797184, -0.407040 },
                     { -0.354816,  0.574912,  0.737280 },
                     {  0.821760, -0.184320,  0.539200 }};
  }


  @Test
  public void test20() throws NotARotationMatrixException {
    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 }};
  }

  
  @Test
  public void test21() throws NotARotationMatrixException {
    double[][] m5 = { { 0.0, 0.0, 1.0 },
                      { 0.0, 1.0, 0.0 },
                      { 1.0, 0.0, 0.0 }};
    try {
      Rotation r = new Rotation(m5, 1.0e-7);
      fail("Exception not thrown");
    } catch (NotARotationMatrixException e) {
      // expected
    }
  }

  @Test
  public void test22() {
  }

  @Test
  public void test23() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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



Regression test case 1:
java
  @Test
  public void test24() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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


Regression test case 2:
java
  @Test
  public void test25() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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


Regression test case 3:
java
  @Test
  public void test26() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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


Regression test case 4:
java
  @Test
  public void test27() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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


Regression test case 5:
java
  @Test
  public void test28() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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


Regression test case 6:
java
  @Test
  public void test29() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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
  public void test30()
    throws NotARotationMatrixException {
    try {
      new Rotation(new double[][] {
                     { 0.0, 0.1, 0.0 },
                     { 0.1, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    try {
      new Rotation(new double[][] {
                     {  0.0445888,  0.07971824, -0.040704 },
                     {  0.082176, -0.018432,  0.05392 },
                     { -0.0354816,  0.0574912,  0.0737280 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    try {
        new Rotation(new double[][] {
                       {  0.04,  0.08, -0.04 },
                       { -0.04,  0.06,  0.07 },
                       {  0.08, -0.02,  0.05 }
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }
    checkRotation(new Rotation(new double[][] {
                                 {  0.0445888,  0.07971824, -0.040704 },
                                 { -0.0354816,  0.0574912,  0.073728 },
                                 {  0.082176, -0.018432,  0.05392 }
                               }, 1.0e-10),
    checkRotation(new Rotation(new double[][] {
                                 {  0.05392,  0.073728,  0.040704 },
                                 {  0.018432, -0.0574912,  0.07971824 },
                                 {  0.082176, -0.0354816, -0.0445888 }
                              }, 1.0e-10),
    checkRotation(new Rotation(new double[][] {
                                 { -0.0445888,  0.07971824, -0.040704 },
                                 {  0.0354816,  0.0574912,  0.073728 },
                                 {  0.082176,  0.018432, -0.05392 }
                               }, 1.0e-10),
    checkRotation(new Rotation(new double[][] {
                                 { -0.05392,  0.073728,  0.040704 },
                                 { -0.018432, -0.0574912,  0.07971824 },
                                 {  0.082176,  0.0354816,  0.0445888 }
                               }, 1.0e-10),
    double[][] m1 = { { 0.0, 0.1, 0.0 },
                      { 0.0, 0.0, 0.1 },
                      { 0.1, 0.0, 0.0 } };
    double[][] m2 = { { 0.083203, -0.055012, -0.007139 },
                      { 0.048293,  0.078164, -0.039474 },
                      { 0.027296,  0.029396,  0.091602 } };
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
    double[][] m4 = { { 0.1,  0.0,  0.0 },
                      { 0.0, -0.1,  0.0 },
                      { 0.0,  0.0, -0.1 } };
    try {
      double[][] m5 = { { 0.0, 0.0, 0.1 },
                        { 0.0, 0.1, 0.0 },
                        { 0.1, 0.0, 0.0 } };
      r = new Rotation(m5, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }
  }
  @Test
  public void test31() {
    for (double x = -0.09; x < 0.09; x += 0.02) {
      for (double y = -0.09; y < 0.09; y += 0.02) {
        for (double z = -0.09; z < 0.09; z += 0.02) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test32() {
    for (double x = -0.09; x < 0.09; x += 0.02) {
      for (double y = -0.09; y < 0.09; y += 0.02) {
        for (double z = -0.09; z < 0.09; z += 0.02) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }
  }
  @Test
  public void test33() {
    if (Vector3D.dotProduct(axis, Vector3D.PLUS_K) > 0) {
      checkVector(axis, Vector3D.PLUS_K);
    } else {
      checkVector(axis, Vector3D.MINUS_K);
    }
    try {
        new Rotation(u1, u2, Vector3D.ZERO, v2);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
      // expected behavior
    }
  }
  @Test
  public void test34() {
    try {
        new Rotation(u, Vector3D.ZERO);
        Assert.fail("an exception should have been thrown");
    } catch (IllegalArgumentException e) {
        // expected behavior
    }
  }
  @Test
  public void test35() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
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
  public void test36() {
    try {
      new Rotation(new Vector3D(0, 0, 0), 2 * FastMath.PI / 3);
      Assert.fail("an exception should have been thrown");
    } catch (ArithmeticException e) {
    }
  }
  @Test
  public void test37() {
  }
  @Test
  public void test38() {
    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (int j = 0; j < singularCardanAngle.length; ++j) {
        Rotation r = new Rotation(CardanOrders[i], 0.01, singularCardanAngle[j], 0.03);
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
    for (int i = 0; i < EulerOrders.length; ++i) {
      for (int j = 0; j < singularEulerAngle.length; ++j) {
        Rotation r = new Rotation(EulerOrders[i], 0.01, singularEulerAngle[j], 0.03);
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
  public void test39()
    throws CardanEulerSingularityException {
    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };
    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.01; alpha1 < 6.2; alpha1 += 0.03) {
        for (double alpha2 = -1.55; alpha2 < 1.55; alpha2 += 0.03) {
          for (double alpha3 = 0.01; alpha3 < 6.2; alpha3 += 0.03) {
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
      for (double alpha1 = 0.01; alpha1 < 6.2; alpha1 += 0.03) {
        for (double alpha2 = 0.005; alpha2 < 3.1; alpha2 += 0.03) {
          for (double alpha3 = 0.01; alpha3 < 6.2; alpha3 += 0.03) {
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
  public void test40() {
    for (double x = -0.009; x < 0.009; x += 0.002) {
      for (double y = -0.009; y < 0.009; y += 0.002) {
        for (double z = -0.009; z < 0.009; z += 0.002) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyInverseTo(r1.applyTo(u)), r3.applyTo(u));
        }
      }
    }
  }

  @Test
  public void test41() {
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
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
    for (double lambda = 0; lambda < 6.2; lambda += 0.2) {
      for (double phi = -1.55; phi < 1.55; phi += 0.2) {
          Vector3D u = new Vector3D(FastMath.cos(lambda) * FastMath.cos(phi),
                                    FastMath.sin(lambda) * FastMath.cos(phi),
                                    FastMath.sin(phi));
          checkVector(u, r.applyInverseTo(r.applyTo(u)));
          checkVector(u, r.applyTo(r.applyInverseTo(u)));
      }
    }
    for (double lambda = -6.2; lambda < 0; lambda += 0.2) {
      for (double phi = 1.55; phi > -1.55; phi -= 0.2) {
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
  public void test42()
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
    double[][] m1 = { { 0.0, 1.0, 0.0 },
                      { 0.0, 0.0, 1.0 },
                      { 1.0, 0.0, 0.0 } };
    double[][] m2 = { { 0.83203, -0.55012, -0.07139 },
                      { 0.48293,  0.78164, -0.39474 },
                      { 0.27296,  0.29396,  0.91602 } };
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
    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 } };
    try {
      double[][] m5 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m5, 1.0e-7);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }
    try {
      new Rotation(new double[][] {
                     { 0.0, 1.5, 0.0 }, // change 1.0 to 1.5
                     { 1.0, 0.0, 0.0 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    try {
      new Rotation(new double[][] {
                     {  0.445888,  0.797184, 0.407040 }, // change -0.407040 to 0.407040
                     { -0.354816,  0.574912,  0.737280 },
                     {  0.821760, -0.184320,  0.539200 }
                   }, 1.0e-7);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
    try {
        new Rotation(new double[][] {
                       {  0.4,  0.8, -0.4 },
                       { -0.4,  0.6,  0.7 },
                       { -1.8, -0.2,  0.5 } // change 0.8 to -1.8
                     }, 1.0e-15);
        Assert.fail("Expecting NotARotationMatrixException");
      } catch (NotARotationMatrixException nrme) {
        // expected behavior
      }
  }



Here are the regression test cases that can kill more mutants of the given method by changing the input of the covering tests:

1. Change the value `1.0` to `1.5` in the first row of the matrix in the first test case:
java
try {
  new Rotation(new double[][] {
                 { 0.0, 1.5, 0.0 },
                 { 1.0, 0.0, 0.0 }
               }, 1.0e-7);
}


2. Change the value `-0.407040` to `0.407040` in the second test case:
java
try {
  new Rotation(new double[][] {
                 {  0.445888,  0.797184, 0.407040 },
                 { -0.354816,  0.574912,  0.737280 },
                 {  0.821760, -0.184320,  0.539200 }
               }, 1.0e-7);
}


3. Change the value `0.8` to `-1.8` in the third test case:
java
try {
    new Rotation(new double[][] {
                   {  0.4,  0.8, -0.4 },
                   { -0.4,  0.6,  0.7 },
                   { -1.8, -0.2,  0.5 }
                 }, 1.0e-15);
  }

@Test
public void test43() {
}

}