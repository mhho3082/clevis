package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A line shape.
 * Can be created by the user.
 *
 * @author Ho Man Hin
 */
public class Line extends UserShape {
    private final Segment segment;

    /**
     * A line shape, defined by two points.
     *
     * @param name the name
     * @param x1   the x-coordinate of the first point
     * @param y1   the y-coordinate of the first point
     * @param x2   the x-coordinate of the second point
     * @param y2   the y-coordinate of the second point
     * @throws SizeIsZeroException warns of zero area
     */
    public Line(String name, BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2)
            throws SizeIsZeroException {
        super(name);
        this.segment = new Segment(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * A line shape, defined by two points.
     *
     * @param name   the name
     * @param point1 the first point
     * @param point2 the second point
     * @throws SizeIsZeroException warns of zero area
     */
    public Line(String name, Point point1, Point point2)
            throws SizeIsZeroException {
        super(name);
        this.segment = new Segment(point1, point2);
    }

    /**
     * Gets the segment of this line.
     *
     * @return segment
     */
    public Segment getSegment() {
        return this.segment;
    }

    /**
     * Move the line by dx and dy.
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public void move(BigDecimal dx, BigDecimal dy) {
        this.segment.move(dx, dy);
    }

    /**
     * Checks if this shape intersect with the line.
     *
     * @param line the line to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Line line) {
        return this.segment.isIntersect(line.getSegment());
    }

    /**
     * Checks if this shape intersect with the circle.
     *
     * @param circle the circle to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Circle circle) {
        return this.segment.isIntersect(circle.getCircularSegment());
    }

    /**
     * Checks if this shape intersect with the rectangle.
     * Also works for square.
     *
     * @param rectangle the rectangle/square to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Rectangle rectangle) {
        for (Segment segment1 : rectangle.getSegments()) {
            if (this.segment.isIntersect(segment1)) {
                return true;
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
        for(UserShape userShape : group.getUserShapes()) {
            if (userShape.isIntersect(this)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this line "contains" the point.
     * That is, if the point lie < 0.05 of that line.
     * <p>
     * This is used mainly for pick-and-move.
     *
     * @param point the point to check for
     * @return if the point is "contained"
     */
    public boolean isContains(Point point) {
        return this.segment.isContains(point);
    }

    /**
     * Gives the bounding box of the shape,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    public BigDecimal[] boundingBox() {
        return this.segment.boundingBox();
    }

    /**
     * Basic listing function.
     *
     * @return A list of output for user
     */
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: " + "Line");
        out.add("  x1: " + this.segment.getPoint1().getX().toString());
        out.add("  y1: " + this.segment.getPoint1().getY().toString());
        out.add("  x2: " + this.segment.getPoint2().getX().toString());
        out.add("  y2: " + this.segment.getPoint2().getY().toString());

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
        out.add("Type: " + "Line");

        return out;
    }
}
