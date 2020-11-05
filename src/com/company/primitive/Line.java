package com.company.primitive;

import com.company.RealPoint;

public class Line {
    private RealPoint Point1, Point2;

    public Line(RealPoint point1, RealPoint point2) {
        Point1 = point1;
        Point2 = point2;
    }

    public Line(double x1, double y1, double x2, double y2) {
        Point1 = new RealPoint(x1, y1);
        Point2 = new RealPoint(x2, y2);
    }

    public RealPoint getPoint1() {
        return Point1;
    }

    public void setPoint1(RealPoint point1) {
        Point1 = point1;
    }

    public RealPoint getPoint2() {
        return Point2;
    }

    public void setPoint2(RealPoint point2) {
        Point2 = point2;
    }
}