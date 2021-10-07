package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A rectangle shape.
 * Has to be straight (no rotation)
 * And cannot be rotated.
 * Can be created by the user.
 *
 * @author Ho Man Hin
 */
public class Rectangle extends UserShape {
    protected final Segment[] segments = new Segment[4]; // Top, Right, Bottom, Left

    /**
     * A rectangle shape, defined by xLeft, yTop, width, and height.
     *
     * @param name   the name
     * @param xLeft  the left-most x-coordinate
     * @param yTop   the top-most y-coordinate
     * @param width  the width (horizontal)
     * @param height the height (vertical)
     * @throws SizeIsZeroException warns of zero area
     */
    public Rectangle(String name, BigDecimal xLeft, BigDecimal yTop, BigDecimal width, BigDecimal height)
            throws SizeIsZeroException {
        super(name);

        segments[0] = new Segment(new Point(xLeft, yTop), new Point(xLeft.add(width), yTop)); // Top
        segments[1] = new Segment(new Point(xLeft.add(width), yTop), new Point(xLeft.add(width), yTop.add(height))); // Right
        segments[2] = new Segment(new Point(xLeft, yTop.add(height)), new Point(xLeft.add(width), yTop.add(height))); // Bottom
        segments[3] = new Segment(new Point(xLeft, yTop), new Point(xLeft, yTop.add(height))); // Left
    }

    /**
     * Gets the array of segments of this rectangle.
     * The array holds 4 objects,
     * which are the Top, Right, Bottom, Left segments.
     *
     * @return array of segments
     */
    public Segment[] getSegments() {
        return this.segments;
    }

    /**
     * Move the rectangle by dx and dy.
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public void move(BigDecimal dx, BigDecimal dy) {
        for (Segment segment : this.segments) {
            segment.move(dx, dy);
        }
    }

    /**
     * Checks if this shape intersect with the line.
     *
     * @param line the line to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Line line) {
        for (Segment segment : this.segments) {
            if (segment.isIntersect(line.getSegment())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this shape intersect with the circle.
     *
     * @param circle the circle to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Circle circle) {
        for (Segment segment : this.segments) {
            if (segment.isIntersect(circle.getCircularSegment())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this shape intersect with the rectangle.
     * Also works for square.
     *
     * @param rectangle the rectangle/square to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Rectangle rectangle) {
        for (Segment segment1 : this.segments) {
            for (Segment segment2 : rectangle.getSegments()) {
                if (segment1.isIntersect(segment2)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if this shape intersect with the group.
     * Also works for square.
     *
     * @param group the group to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Group group) {
        for (UserShape userShape : group.getUserShapes()) {
            if (userShape.isIntersect(this)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this rectangle "contains" the point.
     * That is, if the point lie < 0.05 of the perimeter of the rectangle.
     * <p>
     * This is used mainly for pick-and-move.
     *
     * @param point the point to check for
     * @return if the point is "contained"
     */
    public boolean isContains(Point point) {
        for (Segment segment : this.segments) {
            if (segment.isContains(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gives the bounding box of the shape,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    public BigDecimal[] boundingBox() {
        return new BigDecimal[]{
                this.segments[0].getPoint1().getX(),
                this.segments[0].getPoint1().getY(),
                this.segments[0].getLength(),
                this.segments[1].getLength()
        };
    }

    /**
     * Basic listing function.
     *
     * @return A list of output for user
     */
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + this.name);
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + ((double) Math.round(this.segments[0].getPoint1().getX().doubleValue() * 100.0) / 100.0));
        out.add("  yTop: " + ((double) Math.round(this.segments[0].getPoint1().getY().doubleValue() * 100.0) / 100.0));
        out.add(" width: " + ((double) Math.round(this.segments[0].getLength().doubleValue() * 100.0) / 100.0));
        out.add("height: " + ((double) Math.round(this.segments[1].getLength().doubleValue() * 100.0) / 100.0));

        return out;
    }

    /**
     * For use inside list of a group.
     * No indent included.
     *
     * @return A short list of output, to be merged into list of group
     */
    public ArrayList<String> listShort() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: " + "Rectangle");

        return out;
    }
}
