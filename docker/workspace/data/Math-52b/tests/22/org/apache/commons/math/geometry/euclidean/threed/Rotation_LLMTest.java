package org.apache.commons.math.geometry.euclidean.threed;
import org.apache.commons.math.util.FastMath;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;
import org.junit.Test;

public class Rotation_LLMTest {
  @Test
  public void test0() {
    // Create a new Rotation object with different input values
    Rotation r = new Rotation(value1, value2, value3, value4, booleanValue);
    
    // Call the method under test
    Rotation reverted = r.revert();
    
    // Add assertions to check for expected behavior
    checkRotation(r.applyTo(reverted), expectedValue1, expectedValue2, expectedValue3, expectedValue4);
    checkRotation(reverted.applyTo(r), expectedValue1, expectedValue2, expectedValue3, expectedValue4);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test1() {
    Rotation r = new Rotation(0, 0, 0, 0, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 0, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 0, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test2() {
    Rotation r = new Rotation(Double.NaN, Double.NaN, Double.NaN, Double.NaN, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    checkRotation(reverted.applyTo(r), Double.NaN, Double.NaN, Double.NaN, Double.NaN);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test3() {
    Rotation r = new Rotation(1, 0, 0, 0, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), -1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), -1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test4() {
    Rotation r = new Rotation(1e-12, 1e-12, 1e-12, 1e-12, false);
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), -1e-12, -1e-12, -1e-12, 1e-12);
    checkRotation(reverted.applyTo(r), -1e-12, -1e-12, -1e-12, 1e-12);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
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

}    
@Test
public void test6() {
    Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
    double n = 30.0;
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
public void test7(){
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
public void test8(){
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
  public void test9() {
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

    // Additional regression test case
    r1 = new Rotation(new Vector3D(-2.5, 4.8, 6.2), 0.9);
    n = 17.3;
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

    // Additional regression test case
    r1 = new Rotation(new Vector3D(1.2, -4.7, 3.9), 1.5);
    n = 9.2;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
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

    // Additional regression test case
    r1 = new Rotation(new Vector3D(5.9, 2.3, -1.1), 2.1);
    n = -4.7;
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -0.7; x < 0.7; x += 0.2) {
      for (double y = -0.7; y < 0.7; y += 0.2) {
        for (double z = -0.7; z < 0.7; z += 0.2) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

    // Original test case
    r1 = new Rotation( 0.288,  0.384,  0.36,  0.8, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

    // Additional regression test case
    r1 = new Rotation(-0.468, -0.128, -0.32, 0.824, false);
    checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

  }
  @Test
  public void test10() {

    // Existing test cases
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
    
    // Regression test cases
    r1 = new Rotation(new Vector3D(0, 0, 0), 0); // Zero rotation
    n = 1; // Modify scaling factor to 1
    r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                      n * r1.getQ2(), n * r1.getQ3(),
                      true);
    for (double x = -10; x <= 10; x += 5) {
      for (double y = -10; y <= 10; y += 5) {
        for (double z = -10; z <= 10; z += 5) {
          Vector3D u = new Vector3D(x, y, z);
          checkVector(r2.applyTo(u), r1.applyTo(u));
        }
      }
    }

  }
@Test
public void test11() {

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

  r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
@Test
public void test12() {
  Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
  double n = 23.5;
  Rotation r2 = new Rotation(0.1 * r1.getQ0(), n * r1.getQ1(),
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

  r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
@Test
public void test13() {
  Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
  double n = 23.5;
  Rotation r2 = new Rotation(n * r1.getQ0(), 0.1 * r1.getQ1(),
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

  r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
@Test
public void test14() {
  Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
  double n = 23.5;
  Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                             0.1 * r1.getQ2(), n * r1.getQ3(),
                             true);
  for (double x = -0.9; x < 0.9; x += 0.2) {
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }

  r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
@Test
public void test15() {
  Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
  double n = 23.5;
  Rotation r2 = new Rotation(n * r1.getQ0(), n * r1.getQ1(),
                             n * r1.getQ2(), 0.1 * r1.getQ3(),
                             true);
  for (double x = -0.9; x < 0.9; x += 0.2) {
    for (double y = -0.9; y < 0.9; y += 0.2) {
      for (double z = -0.9; z < 0.9; z += 0.2) {
        Vector3D u = new Vector3D(x, y, z);
        checkVector(r2.applyTo(u), r1.applyTo(u));
      }
    }
  }

  r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
@Test
public void test16() {
  Rotation r1 = new Rotation(new Vector3D(2, -3, 5), 1.7);
  double n = 23.5;
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

  r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());
}
  @Test
  public void test17() {
    // squaredSine = 0
    // q0 >= 0
    Rotation r = new Rotation(1, 0, 0, 0, true); // q0 = 1, q1=q2=q3=0
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));

    r = new Rotation(0, 1, 0, 0, true); // q0 = 0, q1=1, q2=q3=0
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));

    r = new Rotation(0, 0, 1, 0, true); // q0 = 0, q1=q2=0, q3=1
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));
  }
  @Test
  public void test18() {
    // squaredSine > 0
    // q0 < 0
    Rotation r = new Rotation(-1, 1, 0, 0, true); // q0 = -1, q1=0, q2=q3=0
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));

    r = new Rotation(-1, 0, 1, 0, true); // q0 = -1, q1=0, q2=q3=0
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));

    r = new Rotation(-1, 0, 0, 1, true); // q0 = -1, q1=0, q2=0, q3=1
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));
  }
  @Test
  public void test19() {
    // squaredSine > 0
    // q0 > 0
    Rotation r = new Rotation(2, 1, 0, 0, true); // q0 = 2, q1=0, q2=q3=0
    checkVector(r.getAxis(), new Vector3D(0.5, 0, 0));

    r = new Rotation(2, 0, 1, 0, true); // q0 = 2, q1=0, q2=q3=0
    checkVector(r.getAxis(), new Vector3D(0.5, 0, 0));

    r = new Rotation(2, 0, 0, 1, true); // q0 = 2, q1=0, q2=0, q3=1
    checkVector(r.getAxis(), new Vector3D(0.5, 0, 0));
  }
  @Test
  public void test20() {
    // q0 < 0
    Rotation r = new Rotation(-1, 0, 0, 0, true); // q0 = -1, q1=q2=q3=0
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test21() {
    // q0 > 0
    Rotation r = new Rotation(2, 0, 0, 0, true); // q0 = 2, q1=q2=q3=0
    Rotation reverted = r.revert();
    checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
    checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
    Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
    Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
  }
  @Test
  public void test22() {
    // u1 = u2
    // v1 = v2
    Vector3D u1 = new Vector3D(1, 1, 0);
    Vector3D u2 = new Vector3D(1, 1, 0);
    Vector3D v1 = new Vector3D(2, 0, 0);
    Vector3D v2 = new Vector3D(2, 0, 0);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), Vector3D.PLUS_J);
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(-0.5, 0.5, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(-0.5, -0.5, 0));
    checkVector(r.getAxis(), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), FastMath.PI);

    u1 = new Vector3D(0, 0, 3);
    u2 = new Vector3D(0, 0, 3);
    v1 = new Vector3D(0, -2, 0);
    v2 = new Vector3D(0, -2, 0);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-0.5, 0, 0.5));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, 1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(-0.5, 0, -0.5));
    checkVector(r.getAxis(), new Vector3D(0, -1, 0));
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
  public void test23() {
    // u1 = u2
    Vector3D u1 = new Vector3D(1, 2, 3);
    Vector3D u2 = new Vector3D(1, 2, 3);
    Vector3D v1 = new Vector3D(2, 0, 0);
    Vector3D v2 = new Vector3D(0, 2, 0);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(0, 0, 1));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(-1, 0, 0));
    checkVector(r.getAxis(), new Vector3D(1, 0, 0));
    checkAngle(r.getAngle(), FastMath.PI);

    u1 = new Vector3D(0, 0, 4);
    u2 = new Vector3D(0, 0, 4);
    v1 = new Vector3D(-2, 1, 0);
    v2 = new Vector3D(-2, 1, 0);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0.8, 0.6, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, -1));
    checkVector(r.getAxis(), new Vector3D(0, 0, 1));
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
  public void test24() {
    // v1 = v2
    Vector3D u1 = new Vector3D(-2, 1, 0);
    Vector3D u2 = new Vector3D(2, -1, 0);
    Vector3D v1 = new Vector3D(0, 0, 3);
    Vector3D v2 = new Vector3D(0, 0, 3);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(0, -0.6, 0.8));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0.8, 0.6));
    checkVector(r.getAxis(), new Vector3D(-1, 0, 0));
    checkAngle(r.getAngle(), FastMath.PI);

    u1 = new Vector3D(2, -4, 0);
    u2 = new Vector3D(-4, 2, 0);
    v1 = new Vector3D(0, 0, 5);
    v2 = new Vector3D(0, 0, 5);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(0, 1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(-0.8, 0, -0.6));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(-0.6, 0, 0.8));
    checkVector(r.getAxis(), new Vector3D(0, 1, 0));
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
  public void test25() {
    // u1 = u2
    // v1 = v2
    Vector3D u1 = new Vector3D(-1, 0, 0);
    Vector3D u2 = new Vector3D(-1, 0, 0);
    Vector3D v1 = new Vector3D(-1, 0, 0);
    Vector3D v2 = new Vector3D(-1, 0, 0);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, -1));
    checkVector(r.getAxis(), Vector3D.PLUS_I);
    checkAngle(r.getAngle(), FastMath.PI);

    u1 = new Vector3D(0, 0, 1);
    u2 = new Vector3D(0, 0, 1);
    v1 = new Vector3D(0, 0, -1);
    v2 = new Vector3D(0, 0, -1);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, -1));
    checkVector(r.getAxis(), Vector3D.MINUS_K);
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
  public void test26() {
    // u1 = u2
    // q0 < 0
    Vector3D u1 = new Vector3D(-1, 0, 0);
    Vector3D u2 = new Vector3D(-1, 0, 0);
    Vector3D v1 = new Vector3D(0, 1, 0);
    Vector3D v2 = new Vector3D(0, 0, 1);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, -1));
    checkVector(r.getAxis(), Vector3D.PLUS_J);
    checkAngle(r.getAngle(), FastMath.PI);

    u1 = new Vector3D(1, 0, 0);
    u2 = new Vector3D(-1, 0, 0);
    v1 = new Vector3D(0, 1, 0);
    v2 = new Vector3D(0, 0, -1);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, -1));
    checkVector(r.getAxis(), Vector3D.PLUS_J);
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
  public void test27() {
    // v1 = v2
    // q0 < 0
    Vector3D u1 = new Vector3D(0, 1, 0);
    Vector3D u2 = new Vector3D(0, 0, 1);
    Vector3D v1 = new Vector3D(-1, 0, 0);
    Vector3D v2 = new Vector3D(-1, 0, 0);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, 1));
    checkVector(r.getAxis(), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), FastMath.PI);

    u1 = new Vector3D(0, 1, 0);
    u2 = new Vector3D(0, 0, -1);
    v1 = new Vector3D(-1, 0, 0);
    v2 = new Vector3D(-1, 0, 0);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, 1));
    checkVector(r.getAxis(), Vector3D.PLUS_K);
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
  public void test28() {
    // u1 = u2
    // v1 = v2
    // q0 < 0
    Vector3D u1 = new Vector3D(-1, 0, 0);
    Vector3D u2 = new Vector3D(-1, 0, 0);
    Vector3D v1 = new Vector3D(-1, 0, 0);
    Vector3D v2 = new Vector3D(-1, 0, 0);
    Rotation r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, -1));
    checkVector(r.getAxis(), Vector3D.PLUS_I);
    checkAngle(r.getAngle(), FastMath.PI);

    u1 = new Vector3D(1, 0, 0);
    u2 = new Vector3D(-1, 0, 0);
    v1 = new Vector3D(-1, 0, 0);
    v2 = new Vector3D(-1, 0, 0);
    r = new Rotation(u1, u2, v1, v2);
    checkVector(r.applyTo(Vector3D.PLUS_I), new Vector3D(-1, 0, 0));
    checkVector(r.applyTo(Vector3D.PLUS_J), new Vector3D(0, -1, 0));
    checkVector(r.applyTo(Vector3D.PLUS_K), new Vector3D(0, 0, -1));
    checkVector(r.getAxis(), Vector3D.PLUS_I);
    checkAngle(r.getAngle(), FastMath.PI);
  }
  @Test
  public void test29() {
    // Test cases with q0 < -0.1
    Rotation r1 = new Rotation(0.0, 0.8, 0.288, 0.384, false);
    Assert.assertEquals(1.7, r1.getAngle(), 1.0e-12);
    
    Rotation r2 = new Rotation(0.0, -0.8, -0.288, -0.384, true);
    Assert.assertEquals(1.7, r2.getAngle(), 1.0e-12);
    
    // Test case with q0 > 0.1
    Rotation r3 = new Rotation(1.0, 0.3, 0.4, 0.5, false);
    Assert.assertEquals(1.063014581273465, r3.getAngle(), 1.0e-12);
    
    // Test case with q0 < 0
    Rotation r4 = new Rotation(-0.8, 0.3, 0.4, 0.5, true);
    Assert.assertEquals(0.9272952180016122, r4.getAngle(), 1.0e-12);
  }
  @Test
  public void test30() {
    Rotation r = new Rotation(RotationOrder.ZYX, 0.1, 0, 1.2);
    try {
      r.getAngles(RotationOrder.ZYX);
    } catch (CardanEulerSingularityException cese) {
      Assert.fail("No exception should have been caught");
    }
  }
  @Test
    throws CardanEulerSingularityException {
    Rotation r = new Rotation(RotationOrder.XYZ, 0.1, 0.5, 0.9);
    double[] angles = r.getAngles(RotationOrder.XYZ);
    checkAngle(angles[0], 0.1);
    checkAngle(angles[1], 0.5);
    checkAngle(angles[2], 0.9);
  }
  @Test
  public void test31() throws NotARotationMatrixException {
    // Regression test 1
    checkRotation(new Rotation(new double[][] {
                                 { 0.445888, 0.797184, -0.407040 },
                                 { -0.354816, 0.574912, 0.737280 },
                                 { 0.821760, -0.184320, 0.539200 }
                               }, 1.0e-10),
                  0.8, 0.288, 0.384, 0.36);

    // Regression test 2
    checkRotation(new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 { -0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10),
                  0.288, 0.384, 0.36, 0.8);

    // Regression test 3
    checkRotation(new Rotation(new double[][] {
                                 { 0.539201,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  -0.821760,  0.354816,  0.445888 }
                               }, 1.0e-10),
                  0.289, 0.385, 0.361, 0.81);
  }
  @Test
  public void test32() {
    // regression test case
    Vector3D u = new Vector3D(1.0, 0.0, 0.0);
    Vector3D result = new Vector3D(-0.196817, 1.919535, 1.510803);
    Vector3D actual = applyTo(u);
    assertTrue(FastMath.abs(actual.getX() - result.getX()) < 1e-6);
    assertTrue(FastMath.abs(actual.getY() - result.getY()) < 1e-6);
    assertTrue(FastMath.abs(actual.getZ() - result.getZ()) < 1e-6);
  }
  @Test
  public void test33() {
    // regression test case
    Vector3D u = new Vector3D(0.0, 1.0, 0.0);
    Vector3D result = new Vector3D(-1.087229, 1.144638, 0.872868);
    Vector3D actual = applyTo(u);
    assertTrue(FastMath.abs(actual.getX() - result.getX()) < 1e-6);
    assertTrue(FastMath.abs(actual.getY() - result.getY()) < 1e-6);
    assertTrue(FastMath.abs(actual.getZ() - result.getZ()) < 1e-6);
  }
  @Test
  public void test34() {
    // regression test case
    Vector3D u = new Vector3D(0.0, 0.0, 1.0);
    Vector3D result = new Vector3D(1.643967, 0.63685, 1.2757);
    Vector3D actual = applyTo(u);
    assertTrue(FastMath.abs(actual.getX() - result.getX()) < 1e-6);
    assertTrue(FastMath.abs(actual.getY() - result.getY()) < 1e-6);
    assertTrue(FastMath.abs(actual.getZ() - result.getZ()) < 1e-6);
  }
  @Test
  public void test35() {
    // regression test case
    Vector3D u = new Vector3D(5.0, 3.0, 2.0);
    Vector3D result = new Vector3D(-0.669869, 3.215557, 6.209057);
    Vector3D actual = applyTo(u);
    assertTrue(FastMath.abs(actual.getX() - result.getX()) < 1e-6);
    assertTrue(FastMath.abs(actual.getY() - result.getY()) < 1e-6);
    assertTrue(FastMath.abs(actual.getZ() - result.getZ()) < 1e-6);
  }
  @Test
  public void test36() {
    // regression test case
    Vector3D u = new Vector3D(-2.0, -3.0, -5.0);
    Vector3D result = new Vector3D(-1.320878, -2.668933, -8.62124);
    Vector3D actual = applyTo(u);
    assertTrue(FastMath.abs(actual.getX() - result.getX()) < 1e-6);
    assertTrue(FastMath.abs(actual.getY() - result.getY()) < 1e-6);
    assertTrue(FastMath.abs(actual.getZ() - result.getZ()) < 1e-6);
  }
  @Test
  public void test37() {
    // regression test case
    Vector3D u = new Vector3D(0.8, 0.288, 0.384);
    Vector3D result = new Vector3D(-0.195484, -1.154206, -1.399821);
    Vector3D actual = applyTo(u);
    assertTrue(FastMath.abs(actual.getX() - result.getX()) < 1e-6);
    assertTrue(FastMath.abs(actual.getY() - result.getY()) < 1e-6);
    assertTrue(FastMath.abs(actual.getZ() - result.getZ()) < 1e-6);
  }
  @Test
  public void test38() {

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
  public void test39() {
    Rotation r = new Rotation(0.8, 0.288, 0.384, 0.36, false);
    Rotation result = r.applyTo(new Rotation(0.4, 0.5, 0.6, 0.7, true));
    Assert.assertEquals(-0.2304, result.getQ0(), 1e-15);
    Assert.assertEquals(-0.2328, result.getQ1(), 1e-15);
    Assert.assertEquals(-0.64968, result.getQ2(), 1e-15);
    Assert.assertEquals(-0.6876, result.getQ3(), 1e-15);
  }
  @Test
  public void test40() {
    Rotation r = new Rotation(0.384, 0.36, 0.8, 0.288, false);
    Rotation result = r.applyTo(new Rotation(0.2, 0.8, 0.5, 0.4, true));
    Assert.assertEquals(-0.176, result.getQ0(), 1e-15);
    Assert.assertEquals(-0.3544, result.getQ1(), 1e-15);
    Assert.assertEquals(-0.6712, result.getQ2(), 1e-15);
    Assert.assertEquals(-0.6032, result.getQ3(), 1e-15);
  }
  @Test
  public void test41() {
    Rotation r = new Rotation(0.288, 0.384, 0.36, 0.8, false);
    Rotation result = r.applyTo(new Rotation(0.1, 0.9, 0.6, 0.3, true));
    Assert.assertEquals(-0.3756, result.getQ0(), 1e-15);
    Assert.assertEquals(-0.3216, result.getQ1(), 1e-15);
    Assert.assertEquals(-0.2952, result.getQ2(), 1e-15);
    Assert.assertEquals(-0.8292, result.getQ3(), 1e-15);
  }
  @Test
  public void test42() {
    // Regression test case for mutant 1
    // Change input value from 1.0e-7 to 1.0e-8
    checkRotation(new Rotation(new double[][] {
                                 {  0.445888,  0.797184, -0.407040 },
                                 { -0.354816,  0.574912,  0.737280 },
                                 {  0.821760, -0.184320,  0.539200 }
                               }, 1.0e-8),
                  0.8, 0.288, 0.384, 0.36);
  }
  @Test
  public void test43() {
    // Regression test case for mutant 1
    // Change input value from 1.0e-7 to 1.0e-8
    checkRotation(new Rotation(new double[][] {
                                 {  0.539200,  0.737280,  0.407040 },
                                 {  0.184320, -0.574912,  0.797184 },
                                 {  0.821760, -0.354816, -0.445888 }
                              }, 1.0e-8),
                  0.36, 0.8, 0.288, 0.384);
  }
  @Test
  public void test44() {
    // Regression test case for mutant 1
    // Change input value from 1.0e-7 to 1.0e-8
    checkRotation(new Rotation(new double[][] {
                                 { -0.445888,  0.797184, -0.407040 },
                                 {  0.354816,  0.574912,  0.737280 },
                                 {  0.821760,  0.184320, -0.539200 }
                               }, 1.0e-8),
                  0.384, 0.36, 0.8, 0.288);
  }
  @Test
  public void test45() {
    // Regression test case for mutant 1
    // Change input value from 1.0e-7 to 1.0e-8
    checkRotation(new Rotation(new double[][] {
                                 { -0.539200,  0.737280,  0.407040 },
                                 { -0.184320, -0.574912,  0.797184 },
                                 {  0.821760,  0.354816,  0.445888 }
                               }, 1.0e-8),
                  0.288, 0.384, 0.36, 0.8);
  }
  @Test
  public void test46() {
    // Regression test case for mutant 1
    // Change input value from 1.0e-7 to 1.0e-8
    double[][] m2 = { { 0.83203, -0.55012, -0.07139 },
                      { 0.48293,  0.78164, -0.39474 },
                      { 0.27296,  0.29396,  0.91602 } };
    r = new Rotation(m2, 1.0e-8);

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
  public void test47() {
    // Regression test case for mutant 1
    // Change input value from 1.0e-7 to 1.0e-8
    double[][] m4 = { { 1.0,  0.0,  0.0 },
                      { 0.0, -1.0,  0.0 },
                      { 0.0,  0.0, -1.0 } };
    r = new Rotation(m4, 1.0e-8);
    checkAngle(r.getAngle(), FastMath.PI);

    try {
      double[][] m5 = { { 0.0, 0.0, 1.0 },
                        { 0.0, 1.0, 0.0 },
                        { 1.0, 0.0, 0.0 } };
      r = new Rotation(m5, 1.0e-8);
      Assert.fail("got " + r + ", should have caught an exception");
    } catch (NotARotationMatrixException e) {
      // expected
    }
  }
  @Test
  public void test48() {
    // Regression test case for mutant 1
    // Change input value from 1.0e-7 to 1.0e-8
    double[][] m6 = { { 0.4,  0.8, -0.4 },
                      { -0.4,  0.6,  0.7 },
                      {  0.8, -0.2,  0.5 }
                    };
    try {
      new Rotation(m6, 1.0e-8);
      Assert.fail("Expecting NotARotationMatrixException");
    } catch (NotARotationMatrixException nrme) {
      // expected behavior
    }
  }
  @Test
  public void test49() throws CardanEulerSingularityException {

    RotationOrder[] CardanOrders = {
      RotationOrder.XYZ, RotationOrder.XZY, RotationOrder.YXZ,
      RotationOrder.YZX, RotationOrder.ZXY, RotationOrder.ZYX
    };

    for (int i = 0; i < CardanOrders.length; ++i) {
      for (double alpha1 = 0.1; alpha1 < 6.1; alpha1 += 0.2) {
        for (double alpha2 = -1.55; alpha2 < 1.55; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.1; alpha3 += 0.3) {
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
      for (double alpha1 = 0.1; alpha1 < 6.1; alpha1 += 0.3) {
        for (double alpha2 = 0.05; alpha2 < 3.1; alpha2 += 0.3) {
          for (double alpha3 = 0.1; alpha3 < 6.1; alpha3 += 0.3) {
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
public void test50() throws NotARotationMatrixException {
  double[][] m1 = { { 1.0, 0.0, 0.0 },
                    { 0.0, 1.0, 0.0 },
                    { 0.0, 0.0, 1.0 } };
  Rotation r1 = new Rotation(m1, 1.0e-7);
  Assert.assertEquals(0.0, r1.getAngle(), 0.0);

  double[][] m2 = { { 1.0, 0.0, 0.0 },
                    { 0.0, 0.0, -1.0 },
                    { 0.0, 1.0, 0.0 } };
  Rotation r2 = new Rotation(m2, 1.0e-7);
  Assert.assertEquals(Math.PI/2, r2.getAngle(), 0.0);

  double[][] m3 = { { 1.0, 0.0, 0.0 },
                    { 0.0, -1.0, 0.0 },
                    { 0.0, 0.0, -1.0 } };
  Rotation r3 = new Rotation(m3, 1.0e-7);
  Assert.assertEquals(Math.PI, r3.getAngle(), 0.0);

  double[][] m4 = { { -1.0, 0.0, 0.0 },
                    { 0.0, -1.0, 0.0 },
                    { 0.0, 0.0, 1.0 } };
  Rotation r4 = new Rotation(m4, 1.0e-7);
  Assert.assertEquals(Math.PI, r4.getAngle(), 0.0);

  double[][] m5 = { { 1.0, 0.0, 0.0 },
                    { 0.0, 0.0, 1.0 },
                    { 0.0, -1.0, 0.0 } };
  Rotation r5 = new Rotation(m5, 1.0e-7);
  Assert.assertEquals(Math.PI/2, r5.getAngle(), 0.0);
}
@Test
public void test51() throws NotARotationMatrixException {
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
public void test52() {
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
public void test53() {
  Rotation r = new Rotation(0.001, 0.36, 0.48, 0.8, true);
  Rotation reverted = r.revert();
  checkRotation(r.applyTo(reverted), 1, 0, 0, 0);
  checkRotation(reverted.applyTo(r), 1, 0, 0, 0);
  Assert.assertEquals(r.getAngle(), reverted.getAngle(), 1.0e-12);
  Assert.assertEquals(-1, Vector3D.dotProduct(r.getAxis(), reverted.getAxis()), 1.0e-12);
}
@Test
public void test54() {
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

  r1 = new Rotation(0.288, 0.384, 0.36, 0.8, false);
  checkRotation(r1, -r1.getQ0(), -r1.getQ1(), -r1.getQ2(), -r1.getQ3());

}
}