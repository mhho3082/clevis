package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * A basic straight segment (line).
 * This is a base shape.
 * This shape should not be created directly through user commands.
 *
 * @author Ho Man Hin
 */
public class StraightSegment implements SegmentInterface {
    private final Point point1;
    private final Point point2;

    /**
     * A segment (straight line) out of two points
     *
     * @param point1 the first point
     * @param point2 the second point
     * @throws SizeIsZeroException warns of length of segment = 0
     */
    public StraightSegment(Point point1, Point point2) throws SizeIsZeroException {
        // Check for exception
        if (point1.equals(point2)) {
            throw new SizeIsZeroException();
        }

        this.point1 = point1;
        this.point2 = point2;
    }

    /**
     * Moves the segment by the given coordinates
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public void move(BigDecimal dx, BigDecimal dy) {
        point1.move(dx, dy);
        point2.move(dx, dy);
    }

    /**
     * Gives point 1.
     *
     * @return point 1
     */
    public Point getPoint1() {
        return point1;
    }

    /**
     * Gives point 2.
     *
     * @return point 2
     */
    public Point getPoint2() {
        return point2;
    }

    /**
     * Gives the length of this segment.
     *
     * @return The length of the segment
     */
    public BigDecimal getLength() {
        return this.point1.getLength(point2);
    }

    /**
     * Checks whether a point sits exactly on the segment.
     *
     * @param point the point to be checked against
     * @return whether the point is on segment
     */
    public boolean isOnSegment(Point point) {
        MathContext m = new MathContext(Config.ROUND_BIG_DECIMAL);
        BigDecimal tempA = this.point1.getLength(point);
        BigDecimal tempB = this.point2.getLength(point);
        return tempA.add(tempB).round(m).equals(this.getLength().round(m));
    }

    /**
     * Checks whether a point lies <0.05 of the segment.
     *
     * @param point the point to be checked against
     * @return whether the segment "contains" the point
     */
    public boolean isContains(Point point) {
        BigDecimal length1 = this.point1.getLength(point);
        BigDecimal length2 = this.point2.getLength(point);

        if (length1.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(this.getLength().setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) > 0) {
            return length2.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(new BigDecimal("0.05")) < 0;
        } else if (length2.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(this.getLength().setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) > 0) {
            return length1.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(new BigDecimal("0.05")) < 0;
        } else {
            return perpendicularDistance(point).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(new BigDecimal("0.05")) < 0;
        }
    }

    /**
     * Gives the perpendicular distance of a point from this segment.
     *
     * @param point the point to be calculated
     * @return the perpendicular distance
     */
    public BigDecimal perpendicularDistance(Point point) {
        MathContext m = new MathContext(Config.ROUND_BIG_DECIMAL);
        BigDecimal lengthA = this.point1.getLength(point).round(m);
        BigDecimal lengthB = this.point2.getLength(point).round(m);
        BigDecimal lengthAll = this.getLength().round(m);

        BigDecimal s = (lengthA.add(lengthB).add(lengthAll)).divide(new BigDecimal(2), MathContext.DECIMAL128).round(m);

        return new BigDecimal(2)
                .multiply(
                        (
                                s.multiply(s.subtract(lengthA))
                                        .multiply(s.subtract(lengthB))
                                        .multiply(s.subtract(lengthAll))
                        ).sqrt(MathContext.DECIMAL128)).divide(lengthAll, MathContext.DECIMAL128);
    }

    /**
     * Gives the bounding box of this segment,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    public BigDecimal[] boundingBox() {
        BigDecimal[] temp = new BigDecimal[4];

        temp[0] = point1.getX().min(point2.getX());
        temp[1] = point1.getY().min(point2.getY());
        temp[2] = point1.getX().subtract(point2.getX()).abs();
        temp[3] = point1.getY().subtract(point2.getY()).abs();

        return temp;
    }

    /**
     * Checks if the given segment intersects this segment.
     *
     * @param intersectSegment the segment to check against
     * @return whether the two intersect
     */
    @Override
    public boolean isIntersect(SegmentInterface intersectSegment) {
        if (intersectSegment instanceof StraightSegment) {
            StraightSegment segment1 = (StraightSegment) intersectSegment;
            if (
                // If one of the end point lies on the other line
                    this.isOnSegment(segment1.getPoint1()) || this.isOnSegment(segment1.getPoint2())
                            || segment1.isOnSegment(this.point1) || segment1.isOnSegment(this.point2)
            ) {
                return true;
            } else {
                // General case; measure by varying spinning directions
                return this.spinDirection(segment1.getPoint1()) != this.spinDirection(segment1.getPoint2())
                        && segment1.spinDirection(this.point1) != segment1.spinDirection(this.point2);
            }
        } else {
            return intersectSegment.isIntersect(this);
        }
    }

    /**
     * Measures the spin direction from the line towards the given point.
     * The line goes point1 -> point2 -> given point.
     * Returns true if the spinning direction is counterclockwise.
     *
     * @param point the point to be calculated with
     * @return if the spinning direction is counterclockwise
     */
    public boolean spinDirection(Point point) {
        return (
                (point.getY().subtract(this.point1.getY()))
                        .multiply(this.point2.getX().subtract(this.point1.getX()))
        ).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(
                (this.point2.getY().subtract(this.point1.getY()))
                        .multiply(point.getX().subtract(this.point1.getX())).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)
        ) > 0;
    }

    /**
     * Gets the plotting guide.
     *
     * @return the info for GUI to plot
     */
    public double[] getPlot() {
        return new double[]{
                this.point1.getX().doubleValue(),
                this.point1.getY().doubleValue(),
                this.point2.getX().doubleValue(),
                this.point2.getY().doubleValue(),
                0};
    }
}
