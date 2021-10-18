package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * A basic circular segment (circle).
 * This is a base shape.
 * This shape should not be created directly through user commands.
 *
 * @author Ho Man Hin
 */
public class CircularSegment implements SegmentInterface {
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
        BigDecimal diff = this.center.getLength(point).subtract(radius).abs().setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP);
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
     * Checks if the given segment intersects this segment.
     *
     * @param intersectSegment the segment to check against
     * @return whether the two intersect
     */
    @Override
    public boolean isIntersect(SegmentInterface intersectSegment) {
        if (intersectSegment instanceof CircularSegment) {
            CircularSegment tempSegment = (CircularSegment) intersectSegment;
            MathContext m = new MathContext(Config.ROUND_BIG_DECIMAL);
            BigDecimal cDiff = this.center.getLength(tempSegment.getCenter());
            BigDecimal rDiff = this.radius.add(tempSegment.getRadius());

            // Inside without touching
            if (cDiff.add(this.radius).compareTo(tempSegment.getRadius()) < 0) {
                return false;
            } else if (cDiff.add(tempSegment.getRadius()).compareTo(this.radius) < 0) {
                return false;
            }

            return cDiff.round(m).compareTo(rDiff.round(m)) <= 0;
        } else {
            StraightSegment segment = (StraightSegment) intersectSegment;
            BigDecimal perpendicularDistance = segment.perpendicularDistance(this.center);
            BigDecimal lengthToPoint1 = this.center.getLength(segment.getPoint1());
            BigDecimal lengthToPoint2 = this.center.getLength(segment.getPoint2());
            MathContext m = new MathContext(Config.ROUND_BIG_DECIMAL);

            // If center
            if (segment.isOnSegment(this.center)) {
                return true;
            }

            // Too far away
            if (perpendicularDistance.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(this.radius.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) > 0) {
                return false;
            }

            // Check the triangle formed is obtuse if both ends of segment outside
            if (lengthToPoint1.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(this.radius.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) > 0
                    && lengthToPoint2.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(this.radius.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) > 0
            ) {
                if (
                        (lengthToPoint1.pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)
                                .add(segment.getLength().pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)))
                                .compareTo(lengthToPoint2.pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) < 0
                                && (lengthToPoint1.pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)
                                .add(segment.getLength().pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)))
                                .compareTo(lengthToPoint2.pow(2).divide(new BigDecimal("2"), m).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) > 0
                ) {
                    return false;
                } else return (lengthToPoint2.pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)
                        .add(segment.getLength().pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)))
                        .compareTo(lengthToPoint1.pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) >= 0
                        || (lengthToPoint2.pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)
                        .add(segment.getLength().pow(2).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)))
                        .compareTo(lengthToPoint1.pow(2).divide(new BigDecimal("2"), m).setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) <= 0;
            }


            return lengthToPoint1.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(this.radius.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) >= 0
                    || lengthToPoint2.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP).compareTo(this.radius.setScale(Config.SCALE_SIZE, RoundingMode.HALF_UP)) >= 0;
        }
    }
}
