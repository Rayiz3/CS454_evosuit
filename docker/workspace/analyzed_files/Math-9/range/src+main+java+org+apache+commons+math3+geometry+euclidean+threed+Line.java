{
  "filepath": "/tmp/Math-9b/src/main/java/org/apache/commons/math3/geometry/euclidean/threed/Line.java",
  "nodes": [
    {
      "type": "class_interface",
      "name": "Line",
      "is_interface": false,
      "parent_types": [
        "org.apache.commons.math3.geometry.partitioning.Embedding\u003corg.apache.commons.math3.geometry.euclidean.threed.Euclidean3D, org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D\u003e"
      ],
      "begin_line": 41,
      "end_line": 226,
      "comment": " The class represent lines in a three dimensional space.\n\n * \u003cp\u003eEach oriented line is intrinsically associated with an abscissa\n * which is a coordinate on the line. The point at abscissa 0 is the\n * orthogonal projection of the origin on the line, another equivalent\n * way to express this is to say that it is the point of the line\n * which is closest to the origin. Abscissa increases in the line\n * direction.\u003c/p\u003e\n\n * @version $Id$\n * @since 3.0\n "
    },
    {
      "type": "field",
      "varNames": [
        "direction"
      ],
      "begin_line": 44,
      "end_line": 44,
      "comment": " Line direction. "
    },
    {
      "type": "field",
      "varNames": [
        "zero"
      ],
      "begin_line": 47,
      "end_line": 47,
      "comment": " Line point closest to the origin. "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.Line(org.apache.commons.math3.geometry.euclidean.threed.Vector3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D)",
      "begin_line": 54,
      "end_line": 56,
      "comment": " Build a line from two points.\n     * @param p1 first point belonging to the line (this can be any point)\n     * @param p2 second point belonging to the line (this can be any point, different from p1)\n     * @exception MathIllegalArgumentException if the points are equal\n     "
    },
    {
      "type": "constructor",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.Line(org.apache.commons.math3.geometry.euclidean.threed.Line)",
      "begin_line": 63,
      "end_line": 66,
      "comment": " Copy constructor.\n     * \u003cp\u003eThe created instance is completely independent from the\n     * original instance, it is a deep copy.\u003c/p\u003e\n     * @param line line to copy\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.reset(org.apache.commons.math3.geometry.euclidean.threed.Vector3D, org.apache.commons.math3.geometry.euclidean.threed.Vector3D)",
      "begin_line": 73,
      "end_line": 81,
      "comment": " Reset the instance as if built from two points.\n     * @param p1 first point belonging to the line (this can be any point)\n     * @param p2 second point belonging to the line (this can be any point, different from p1)\n     * @exception MathIllegalArgumentException if the points are equal\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.revert()",
      "begin_line": 86,
      "end_line": 89,
      "comment": " Get a line with reversed direction.\n     * @return a new instance, with reversed direction\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.getDirection()",
      "begin_line": 94,
      "end_line": 96,
      "comment": " Get the normalized direction vector.\n     * @return normalized direction vector\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.getOrigin()",
      "begin_line": 101,
      "end_line": 103,
      "comment": " Get the line point closest to the origin.\n     * @return line point closest to the origin\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.getAbscissa(org.apache.commons.math3.geometry.euclidean.threed.Vector3D)",
      "begin_line": 112,
      "end_line": 114,
      "comment": " Get the abscissa of a point with respect to the line.\n     * \u003cp\u003eThe abscissa is 0 if the projection of the point and the\n     * projection of the frame origin on the line are the same\n     * point.\u003c/p\u003e\n     * @param point point to check\n     * @return abscissa of the point\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.pointAt(double)",
      "begin_line": 120,
      "end_line": 122,
      "comment": " Get one point from the line.\n     * @param abscissa desired abscissa for the point\n     * @return one point belonging to the line, at specified abscissa\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.toSubSpace(org.apache.commons.math3.geometry.Vector\u003corg.apache.commons.math3.geometry.euclidean.threed.Euclidean3D\u003e)",
      "begin_line": 127,
      "end_line": 129,
      "comment": " {@inheritDoc}\n     * @see #getAbscissa(Vector3D)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.toSpace(org.apache.commons.math3.geometry.Vector\u003corg.apache.commons.math3.geometry.euclidean.oned.Euclidean1D\u003e)",
      "begin_line": 134,
      "end_line": 136,
      "comment": " {@inheritDoc}\n     * @see #pointAt(double)\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.isSimilarTo(org.apache.commons.math3.geometry.euclidean.threed.Line)",
      "begin_line": 145,
      "end_line": 148,
      "comment": " Check if the instance is similar to another line.\n     * \u003cp\u003eLines are considered similar if they contain the same\n     * points. This does not mean they are equal since they can have\n     * opposite directions.\u003c/p\u003e\n     * @param line line to which instance should be compared\n     * @return true if the lines are similar\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.contains(org.apache.commons.math3.geometry.euclidean.threed.Vector3D)",
      "begin_line": 154,
      "end_line": 156,
      "comment": " Check if the instance contains a point.\n     * @param p point to check\n     * @return true if p belongs to the line\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.distance(org.apache.commons.math3.geometry.euclidean.threed.Vector3D)",
      "begin_line": 162,
      "end_line": 166,
      "comment": " Compute the distance between the instance and a point.\n     * @param p to check\n     * @return distance between the instance and the point\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.distance(org.apache.commons.math3.geometry.euclidean.threed.Line)",
      "begin_line": 172,
      "end_line": 186,
      "comment": " Compute the shortest distance between the instance and another line.\n     * @param line line to check against the instance\n     * @return shortest distance between the instance and the line\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.closestPoint(org.apache.commons.math3.geometry.euclidean.threed.Line)",
      "begin_line": 192,
      "end_line": 207,
      "comment": " Compute the point of the instance closest to another line.\n     * @param line line to check against the instance\n     * @return point of the instance closest to another line\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.intersection(org.apache.commons.math3.geometry.euclidean.threed.Line)",
      "begin_line": 214,
      "end_line": 217,
      "comment": " Get the intersection point of the instance and another line.\n     * @param line other line\n     * @return intersection point of the instance and the other line\n     * or null if there are no intersection points\n     "
    },
    {
      "type": "method",
      "signature": "org.apache.commons.math3.geometry.euclidean.threed.Line.wholeLine()",
      "begin_line": 222,
      "end_line": 224,
      "comment": " Build a sub-line covering the whole line.\n     * @return a sub-line covering the whole line\n     "
    }
  ]
}