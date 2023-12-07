package org.apache.commons.math3.geometry.euclidean.threed;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.geometry.euclidean.threed.Line;
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.FastMath;
import org.junit.Assert;
import org.junit.Test;

public class Line_LLMTest {
@Test
public void test0() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1  = new Vector3D (-1.2, -3.4, 5.8);
    Vector3D p2  = new Vector3D (-3.4, 5.8, -1.2);
    Line     lA  = new Line(p1, p2);
    Line     lB  = new Line(p2, p1);
    Assert.assertTrue(lA.isSimilarTo(lB));
    Assert.assertTrue(! lA.isSimilarTo(new Line(p1, p1.add(lA.getDirection().orthogonal()))));
}
@Test
public void test1() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, -1, -1), new Vector3D(0, -2, -2));
    Assert.assertEquals(1.0,
                        l.distance(new Line(new Vector3D(-1, 0, -1), new Vector3D(-1, 0, -2))),
                        1.0e-10);
    Assert.assertEquals(0.5,
                        l.distance(new Line(new Vector3D(0.5, 0, 0), new Vector3D(0.5, 1, 1))),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(l),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(new Line(new Vector3D(0, 4, 4), new Vector3D(0, 5, 5))),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(new Line(new Vector3D(0, 4, 4), new Vector3D(0, 3, 4))),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(new Line(new Vector3D(0, 4, 4), new Vector3D(-1, 4, 4))),
                        1.0e-10);
    Assert.assertEquals(FastMath.sqrt(8),
                        l.distance(new Line(new Vector3D(0, 4, 0), new Vector3D(-1, 4, 0))),
                        1.0e-10);
}
@Test
public void test2() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, -1, -1), new Vector3D(0, -2, -2));
    Assert.assertNull(l.intersection(new Line(new Vector3D(-1, 0, -1), new Vector3D(-1, 0, -2))));
    Assert.assertNull(l.intersection(new Line(new Vector3D(0.5, 0, 0), new Vector3D(0.5, 1, 1))));
    Assert.assertEquals(0.0,
                        l.intersection(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, 4, 4), new Vector3D(0, 5, 5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, 4, 4), new Vector3D(0, 3, 4))).distance(new Vector3D(0, 4, 4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, 4, 4), new Vector3D(-1, 4, 4))).distance(new Vector3D(0, 4, 4)),
                        1.0e-10);
    Assert.assertNull(l.intersection(new Line(new Vector3D(0, 4, 0), new Vector3D(-1, 4, 0))));
}
@Test
public void test3() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, -1, -1), new Vector3D(0, -2, -2));
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(-1, 0, -1), new Vector3D(-1, 0, -2))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.5,
                        l.closestPoint(new Line(new Vector3D(0.5, 0, 0), new Vector3D(0.5, 1, 1))).distance(new Vector3D(0.5, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, 4, 4), new Vector3D(0, 5, 5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, 4, 4), new Vector3D(0, 3, 4))).distance(new Vector3D(0, 4, 4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, 4, 4), new Vector3D(-1, 4, 4))).distance(new Vector3D(0, 4, 4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, 4, 0), new Vector3D(-1, 4, 0))).distance(new Vector3D(0, 2, 2)),
                        1.0e-10);
}
@Test
public void test4() {
    
    // setup
    Line line = new Line(new Vector3D(-1653345.6696423641, -6170370.041579291, -90000),
                         new Vector3D(-1650757.5050732433, -6160710.879908984, -0.9));
    Vector3D expected = line.getDirection().negate();

    // action
    Line reverted = line.revert();

    // verify
    Assert.assertArrayEquals(expected.toArray(), reverted.getDirection().toArray(), 0);

}
@Test
public void test5() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, -1, -1), new Vector3D(0, -2, -2));
    Assert.assertEquals(FastMath.sqrt(3.0 / 2.0), l.distance(new Vector3D(-1, 0, -1)), 1.0e-10);
    Assert.assertEquals(0, l.distance(new Vector3D(0, 4, 4)), 1.0e-10);
}
@Test
public void test6() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1 = new Vector3D(0, 0, -1);
    Line l = new Line(p1, new Vector3D(0, 0, -2));
    Assert.assertTrue(l.contains(p1));
    Assert.assertTrue(l.contains(new Vector3D(1.0, p1, 0.3, l.getDirection())));
    Vector3D u = l.getDirection().orthogonal();
    Vector3D v = Vector3D.crossProduct(l.getDirection(), u);
    for (double alpha = 0; alpha < 2 * FastMath.PI; alpha += 0.3) {
        Assert.assertTrue(! l.contains(p1.add(new Vector3D(FastMath.cos(alpha), u,
                                                           FastMath.sin(alpha), v))));
    }
}

    @Test
    public void test7() {
        
        // setup
        Line line = new Line(new Vector3D(1653345.6696423641, 6170370.041579291, 90000),
                             new Vector3D(1650757.5050732433, 6160710.879908984, 0.9));
        Vector3D expected = line.getDirection().negate();

        // action
        Line reverted = line.revert();

        // verify
        Assert.assertArrayEquals(expected.toArray(), reverted.getDirection().toArray(), 0);

    }
    
    @Test
    public void test8() {
        
        // setup
        Line line = new Line(new Vector3D(123.456, 789.012, 3.14159),
                             new Vector3D(987.654, 321.098, 2.71828));
        Vector3D expected = line.getDirection().negate();

        // action
        Line reverted = line.revert();

        // verify
        Assert.assertArrayEquals(expected.toArray(), reverted.getDirection().toArray(), 0);

    }
    
    @Test
    public void test9() {
        
        // setup
        Line line = new Line(new Vector3D(0, 0, 0),
                             new Vector3D(0, 0, 0));
        Vector3D expected = line.getDirection().negate();

        // action
        Line reverted = line.revert();

        // verify
        Assert.assertArrayEquals(expected.toArray(), reverted.getDirection().toArray(), 0);

    }

@Test
public void test10() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1 = new Vector3D(0, 0, 1);
    Line l = new Line(p1, new Vector3D(0, 0, 2));
    Assert.assertTrue(l.contains(p1));
    Assert.assertTrue(l.contains(new Vector3D(1.0, p1, 0.3, l.getDirection())));
    Vector3D u = l.getDirection().orthogonal();
    Vector3D v = Vector3D.crossProduct(l.getDirection(), u);
    for (double alpha = 0; alpha < 2 * FastMath.PI; alpha += 0.3) {
        Assert.assertTrue(! l.contains(p1.add(new Vector3D(FastMath.cos(alpha), u,
                                                           FastMath.sin(alpha), v))));
    }
}

@Test
public void test11() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1  = new Vector3D (1.2, 3.4, -5.8);
    Vector3D p2  = new Vector3D (3.4, -5.8, 1.2);
    Line     lA  = new Line(p1, p2);
    Line     lB  = new Line(p2, p1);
    Assert.assertTrue(lA.isSimilarTo(lB));
    Assert.assertTrue(! lA.isSimilarTo(new Line(p1, p1.add(lA.getDirection().orthogonal()))));
}

@Test
public void test12() {
    
    // setup
    Line line = new Line(new Vector3D(1653345.6696423641, 6170370.041579291, 90000),
                         new Vector3D(1650757.5050732433, 6160710.879908984, 0.9));
    Vector3D expected = line.getDirection().negate();

    // action
    Line reverted = line.revert();

    // verify
    Assert.assertArrayEquals(expected.toArray(), reverted.getDirection().toArray(), 0);
}

// Regression tests with different inputs

@Test
public void test13() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1 = new Vector3D(0, 0, 1);
    Line l = new Line(p1, new Vector3D(0, 0, 3));
    Assert.assertTrue(l.contains(p1));
    Assert.assertTrue(l.contains(new Vector3D(1.0, p1, 0.3, l.getDirection())));
    Vector3D u = l.getDirection().orthogonal();
    Vector3D v = Vector3D.crossProduct(l.getDirection(), u);
    for (double alpha = 0; alpha < 2 * FastMath.PI; alpha += 0.3) {
        Assert.assertTrue(! l.contains(p1.add(new Vector3D(FastMath.cos(alpha), u,
                                                           FastMath.sin(alpha), v))));
    }
}

@Test
public void test14() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1  = new Vector3D (1.2, 3.4, -5.8);
    Vector3D p2  = new Vector3D (3.9, -1.4, 0.8);
    Line     lA  = new Line(p1, p2);
    Line     lB  = new Line(p2, p1);
    Assert.assertTrue(lA.isSimilarTo(lB));
    Assert.assertTrue(! lA.isSimilarTo(new Line(p1, p1.add(lA.getDirection().orthogonal()))));
}

@Test
public void test15() {
    
    // setup
    Line line = new Line(new Vector3D(-1653345.6696423641, -6170370.041579291, -90000),
                         new Vector3D(-1650757.5050732433, -6160710.879908984, -0.9));
    Vector3D expected = line.getDirection().negate();

    // action
    Line reverted = line.revert();

    // verify
    Assert.assertArrayEquals(expected.toArray(), reverted.getDirection().toArray(), 0);
}

// Test Case 1: Testing the origin of a Vector3D object
Vector3D vector1 = new Vector3D(1, 2, 3);
assertEqual(vector1.getOrigin(), new Vector3D(0, 0, 0));

// Test Case 2: Testing the origin of another Vector3D object
Vector3D vector2 = new Vector3D(-1, -1, -1);
assertEqual(vector2.getOrigin(), new Vector3D(0, 0, 0));

    @Test
    public void test16() throws MathIllegalArgumentException, MathArithmeticException {
        // Original Test Cases
        Vector3D p1  = new Vector3D (1.2, 3.4, -5.8);
        Vector3D p2  = new Vector3D (3.4, -5.8, 1.2);
        Line     lA  = new Line(p1, p2);
        Line     lB  = new Line(p2, p1);
        
        Assert.assertTrue(lA.isSimilarTo(lB));
        Assert.assertTrue(! lA.isSimilarTo(new Line(p1, p1.add(lA.getDirection().orthogonal()))));
        
        // Regression Test Cases
        Vector3D p3  = new Vector3D (2.0, 4.0, 6.0); // different coordinates from p1
        Vector3D p4  = new Vector3D (5.0, -3.0, 7.0); // different coordinates from p2
        Line     lC  = new Line(p2, p1); // same direction, different coordinates
        
        Assert.assertTrue(! lA.isSimilarTo(new Line(p3, p4)));
        Assert.assertTrue(lA.isSimilarTo(lC));
    }

    @Test
    public void test17() throws MathIllegalArgumentException, MathArithmeticException {
        // Existing test cases
        Vector3D p1 = new Vector3D(0, 0, 1);
        Line l = new Line(p1, new Vector3D(0, 0, 2));
        Assert.assertTrue(l.contains(p1));
        Assert.assertTrue(l.contains(new Vector3D(1.0, p1, 0.3, l.getDirection())));
        Vector3D u = l.getDirection().orthogonal();
        Vector3D v = Vector3D.crossProduct(l.getDirection(), u);
        for (double alpha = 0; alpha < 2 * FastMath.PI; alpha += 0.3) {
            Assert.assertTrue(! l.contains(p1.add(new Vector3D(FastMath.cos(alpha), u,
                                                               FastMath.sin(alpha), v)))));
        }

        // Additional regression test cases
        Vector3D p2 = new Vector3D(1, 1, 1);
        Line l2 = new Line(p2, new Vector3D(1, 1, 2));
        Assert.assertTrue(! l2.contains(p1));
        Assert.assertTrue(! l2.contains(new Vector3D(1.0, p1, 0.3, l.getDirection())));
    }
    
    @Test
    public void test18() throws MathIllegalArgumentException, MathArithmeticException {
        // Existing test cases
        Vector3D p1  = new Vector3D (1.2, 3.4, -5.8);
        Vector3D p2  = new Vector3D (3.4, -5.8, 1.2);
        Line     lA  = new Line(p1, p2);
        Line     lB  = new Line(p2, p1);
        Assert.assertTrue(lA.isSimilarTo(lB));
        Assert.assertTrue(! lA.isSimilarTo(new Line(p1, p1.add(lA.getDirection().orthogonal()))));
        
        // Additional regression test cases
        Vector3D p3  = new Vector3D (-1.2, -3.4, 5.8);
        Line     lC  = new Line(p3, p1);
        Assert.assertTrue(! lA.isSimilarTo(lC));
    }
    
    @Test
    public void test19() throws MathIllegalArgumentException {
        // Existing test cases
        Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 2));
        Assert.assertNull(l.intersection(new Line(new Vector3D(1, 0, 1), new Vector3D(1, 0, 2))));
        Assert.assertNull(l.intersection(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))));
        Assert.assertEquals(0.0,
                            l.intersection(l).distance(new Vector3D(0, 0, 0)),
                            1.0e-10);
        Assert.assertEquals(0.0,
                            l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)),
                            1.0e-10);
        Assert.assertEquals(0.0,
                            l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)),
                            1.0e-10);
        Assert.assertEquals(0.0,
                            l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)),
                            1.0e-10);
        Assert.assertNull(l.intersection(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))));
        
        // Additional regression test cases
        Line l2 = new Line(new Vector3D(-1, 0, 0), new Vector3D(-2, 0, 0));
        Assert.assertNull(l.intersection(l2));
        
        Line l3 = new Line(new Vector3D(0, 0, 0), new Vector3D(1, 1, 1));
        Assert.assertNull(l.intersection(l3));
    }

@Test
public void test20() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1  = new Vector3D (1.4, 3.6, -5.0);
    Vector3D p2  = new Vector3D (3.6, -5.0, 1.4);
    Line     lA  = new Line(p1, p2);
    Line     lB  = new Line(p2, p1);
    Assert.assertTrue(lA.isSimilarTo(lB));
    Assert.assertTrue(! lA.isSimilarTo(new Line(p1, p1.add(lA.getDirection().orthogonal()))));
}
@Test
public void test21() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 3));
    Assert.assertEquals(1.5,
                        l.distance(new Line(new Vector3D(1, 0, 1), new Vector3D(1, 0, 3))),
                        1.0e-10);
    Assert.assertEquals(1.0,
                        l.distance(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))),
                        1.0e-10);
    Assert.assertEquals(0.5,
                        l.distance(l),
                        1.0e-10);
    Assert.assertEquals(0.5,
                        l.distance(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))),
                        1.0e-10);
    Assert.assertEquals(0.75,
                        l.distance(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))),
                        1.0e-10);
    Assert.assertEquals(1.0,
                        l.distance(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))),
                        1.0e-10);
    Assert.assertEquals(FastMath.sqrt(10),
                        l.distance(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))),
                        1.0e-10);
}
@Test
public void test22() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 3));
    Assert.assertEquals(0.5,
                        l.closestPoint(new Line(new Vector3D(1, 0, 1), new Vector3D(1, 0, 3))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(1.0,
                        l.closestPoint(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))).distance(new Vector3D(-0.5, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.5,
                        l.closestPoint(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.5,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))).distance(new Vector3D(0, -2, -2)),
                        1.0e-10);
}
@Test
public void test23() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 3));
    Assert.assertNull(l.intersection(new Line(new Vector3D(1, 0, 1), new Vector3D(1, 0, 3))));
    Assert.assertNull(l.intersection(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))));
    Assert.assertEquals(0.5,
                        l.intersection(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.5,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertNull(l.intersection(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))));
}
@Test
public void test24() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 3));
    Assert.assertEquals(FastMath.sqrt(7.0 / 2.0), l.distance(new Vector3D(1, 0, 1)), 1.0e-10);
    Assert.assertEquals(0, l.distance(new Vector3D(0, -4, -4)), 1.0e-10);
}
@Test
public void test25() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1 = new Vector3D(0, 0, 1);
    Line l = new Line(p1, new Vector3D(0, 0, 2));
    Assert.assertTrue(l.contains(p1));
    Assert.assertTrue(l.contains(new Vector3D(1.3, p1, 0.3, l.getDirection())));
    Vector3D u = l.getDirection().orthogonal();
    Vector3D v = Vector3D.crossProduct(l.getDirection(), u);
    for (double alpha = 0; alpha < 2 * FastMath.PI; alpha += 0.3) {
        Assert.assertTrue(! l.contains(p1.add(new Vector3D(FastMath.cos(alpha), u,
                                                           FastMath.sin(alpha), v))));
    }
}

@Test
public void test26() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1  = new Vector3D (2.2, 4.4, -7.8);
    Vector3D p2  = new Vector3D (4.4, -7.8, 2.2);
    Line     lA  = new Line(p1, p2);
    Line     lB  = new Line(p2, p1);
    Assert.assertTrue(lA.isSimilarTo(lB));
    Assert.assertTrue(!lA.isSimilarTo(new Line(p1, p1.add(lA.getDirection().orthogonal()))));
}
@Test
public void test27() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(1, 2, 3), new Vector3D(4, 5, 6));
    Assert.assertEquals(2.0,
                        l.distance(new Line(new Vector3D(3, 2, 1), new Vector3D(2, 1, 0))),
                        1.0e-10);
    Assert.assertEquals(1.5,
                        l.distance(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(l),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.distance(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))),
                        1.0e-10);
    Assert.assertEquals(FastMath.sqrt(10),
                        l.distance(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))),
                        1.0e-10);
}
@Test
public void test28() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(1, 2, 3), new Vector3D(4, 5, 6));
    Assert.assertEquals(0.5,
                        l.closestPoint(new Line(new Vector3D(3, 2, 1), new Vector3D(2, 1, 0))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(1.0,
                        l.closestPoint(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))).distance(new Vector3D(-0.5, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))).distance(new Vector3D(0, -2, -2)),
                        1.0e-10);
}
@Test
public void test29() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(1, 2, 3), new Vector3D(4, 5, 6));
    Assert.assertNull(l.intersection(new Line(new Vector3D(3, 2, 1), new Vector3D(2, 1, 0))));
    Assert.assertNull(l.intersection(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))));
    Assert.assertEquals(0.0,
                        l.intersection(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertNull(l.intersection(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))));
}
@Test
public void test30() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(1, 2, 3), new Vector3D(4, 5, 6));
    Assert.assertEquals(FastMath.sqrt(14.0 / 12.0), l.distance(new Vector3D(1, 0, 1)), 1.0e-10);
    Assert.assertEquals(0, l.distance(new Vector3D(0, -4, -4)), 1.0e-10);
}
@Test
public void test31() throws MathIllegalArgumentException, MathArithmeticException {
    Vector3D p1 = new Vector3D(0, 0, 1);
    Line l = new Line(p1, new Vector3D(0, 0, 2));
    Assert.assertTrue(l.contains(p1));
    Assert.assertTrue(l.contains(new Vector3D(1.0, p1, 0.3, l.getDirection())));
    Vector3D u = l.getDirection().orthogonal();
    Vector3D v = Vector3D.crossProduct(l.getDirection(), u);
    for (double alpha = 0; alpha < 2 * FastMath.PI; alpha += 0.3) {
        Assert.assertTrue(!l.contains(p1.add(new Vector3D(FastMath.cos(alpha), u,
                                                           FastMath.sin(alpha), v)))));
    }
}

@Test
public void test32() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 2));
    // Change the input of the method in the first test case
    Assert.assertNull(l.intersection(new Line(new Vector3D(1, 0, 1), new Vector3D(1, 0, 2))));
    // Change the input of the method in the second test case
    Assert.assertNull(l.intersection(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))));
    Assert.assertEquals(0.0,
                        l.intersection(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    // Change the input of the method in the last test case
    Assert.assertNull(l.intersection(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))));
}

@Test
public void test33() throws MathIllegalArgumentException {
    Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 2));
    // Change the input of the method in the first test case
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(1, 0, 1), new Vector3D(1, 0, 2))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    // Change the input of the method in the second test case
    Assert.assertEquals(0.5,
                        l.closestPoint(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))).distance(new Vector3D(-0.5, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(l).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)),
                        1.0e-10);
    // Change the input of the method in the last test case
    Assert.assertEquals(0.0,
                        l.closestPoint(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))).distance(new Vector3D(0, -2, -2)),
                        1.0e-10);
}

    @Test
    public void test34() throws MathIllegalArgumentException {
        // Original test cases
        Line l = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 2));
        Assert.assertNull(l.intersection(new Line(new Vector3D(1, 0, 1), new Vector3D(1, 0, 2))));
        Assert.assertNull(l.intersection(new Line(new Vector3D(-0.5, 0, 0), new Vector3D(-0.5, -1, -1))));
        Assert.assertEquals(0.0, l.intersection(l).distance(new Vector3D(0, 0, 0)), 1.0e-10);
        Assert.assertEquals(0.0, l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -5, -5))).distance(new Vector3D(0, 0, 0)), 1.0e-10);
        Assert.assertEquals(0.0, l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(0, -3, -4))).distance(new Vector3D(0, -4, -4)), 1.0e-10);
        Assert.assertEquals(0.0, l.intersection(new Line(new Vector3D(0, -4, -4), new Vector3D(1, -4, -4))).distance(new Vector3D(0, -4, -4)), 1.0e-10);
        Assert.assertNull(l.intersection(new Line(new Vector3D(0, -4, 0), new Vector3D(1, -4, 0))));
        
        // Regression test cases
        // Test case where the two lines are parallel and do not intersect
        Line parallelLine = new Line(new Vector3D(0, 0, 0), new Vector3D(1, 0, 0));
        Assert.assertNull(l.intersection(parallelLine));
        
        // Test case where the two lines are coincident
        Line coincidentLine = new Line(new Vector3D(0, 1, 1), new Vector3D(0, 2, 2));
        Assert.assertEquals(0.0, l.intersection(coincidentLine).distance(new Vector3D(0, 1, 1)), 1.0e-10);
        
        // Test case where the two lines intersect at a point other than the origin
        Line intersectingLine = new Line(new Vector3D(1, 1, 1), new Vector3D(2, 2, 2));
        Assert.assertEquals(0.0, l.intersection(intersectingLine).distance(new Vector3D(1, 1, 1)), 1.0e-10);
        
        // Test case where the two lines are skew (do not intersect and are not parallel)
        Line skewLine = new Line(new Vector3D(0, 0, 1), new Vector3D(1, 1, 1));
        Assert.assertNull(l.intersection(skewLine));
    }

}