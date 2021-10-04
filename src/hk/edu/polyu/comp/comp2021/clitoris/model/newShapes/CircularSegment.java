package hk.edu.polyu.comp.comp2021.clitoris.model.newShapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;

public class CircularSegment {
    private final Point center;
    private final BigDecimal radius;

    public CircularSegment(Point center, BigDecimal radius) throws SizeIsZeroException {
        // Check for exception
        if (radius.compareTo(new BigDecimal("0")) <= 0) {
            throw new SizeIsZeroException();
        }

        this.center = center;
        this.radius = radius;
    }

    public void move(BigDecimal dx, BigDecimal dy) {
        center.move(dx, dy);
    }

    public boolean equals(CircularSegment circularSegment) {
        return this.center.equals(circularSegment.center)
                && this.radius.equals(circularSegment.radius);
    }

    public Point getCenter() {
        return center;
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public boolean isOnSegment(Point point) {
        BigDecimal diff = this.center.getLength(point).subtract(radius).abs();
        return diff.compareTo(new BigDecimal("0")) == 0;
    }

    public boolean isContains(Point point) {
        // Compares < 0.05

        BigDecimal diff = this.center.getLength(point).subtract(radius).abs();
        return diff.compareTo(new BigDecimal("0.05")) < 0;
    }

    public BigDecimal[] boundingBox() {
        // Return [xLeft, yTop, width, height]

        BigDecimal[] temp = new BigDecimal[4];

        temp[0] = center.getX().subtract(radius);
        temp[1] = center.getY().subtract(radius);
        temp[2] = radius.multiply(new BigDecimal("2"));
        temp[3] = radius.multiply(new BigDecimal("2"));

        return temp;
    }
}
