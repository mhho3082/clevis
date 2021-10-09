package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;

/**
 * A basic circular segment (circle).
 * This is a base shape.
 * This shape should not be created directly through user commands.
 *
 * @author Ho Man Hin
 */
public class CircularSegment {
    private final Point center;
    private final BigDecimal radius;

    /**
     * A circular segment (circle) out of a center and radius
     *
     * @param center the center of the circular segment
     * @param radius the radius of the circular segment
     * @throws SizeIsZeroException warns of radius <= 0
     */
    public CircularSegment(Point center, BigDecimal radius) throws SizeIsZeroException {
        // Check for exception
        if (radius.compareTo(new BigDecimal("0")) <= 0) {
            throw new SizeIsZeroException();
        }

        this.center = center;
        this.radius = radius;
    }

    /**
     * Moves the segment by the given coordinates
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public void move(BigDecimal dx, BigDecimal dy) {
        center.move(dx, dy);
    }

    /**
     * Gives the center.
     *
     * @return center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gives the radius.
     *
     * @return radius
     */
    public BigDecimal getRadius() {
        return radius;
    }

    /**
     * Checks whether a point lies <0.05 of the segment.
     *
     * @param point the point to be checked against
     * @return whether the segment "contains" the point
     */
    public boolean isContains(Point point) {
        BigDecimal diff = this.center.getLength(point).subtract(radius).abs();
        return diff.compareTo(new BigDecimal("0.05")) < 0;
    }

    /**
     * Gives the bounding box of this segment,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    public BigDecimal[] boundingBox() {
        // Return [xLeft, yTop, width, height]

        BigDecimal[] temp = new BigDecimal[4];

        temp[0] = center.getX().subtract(radius);
        temp[1] = center.getY().subtract(radius);
        temp[2] = radius.multiply(new BigDecimal("2"));
        temp[3] = radius.multiply(new BigDecimal("2"));

        return temp;
    }

    /**
     * Checks if a circular segment intersects this segment.
     *
     * @param circularSegment the segment to be checked against
     * @return whether the two segments intersect
     */
    public boolean isIntersect(CircularSegment circularSegment) {
        BigDecimal diff = this.center.getLength(circularSegment.getCenter());

        return diff.compareTo(this.radius.add(circularSegment.getRadius())) <= 0;
    }

    /**
     * Checks if a segment intersects this segment.
     *
     * @param segment the segment to be checked against
     * @return whether the two segments intersect
     */
    public boolean isIntersect(Segment segment) {
        BigDecimal diff = segment.perpendicularDistance(this.center);

        return diff.compareTo(this.radius) <= 0;
    }
}
