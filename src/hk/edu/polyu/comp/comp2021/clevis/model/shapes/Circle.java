package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A circle shape.
 * Can be created by the user.
 *
 * @author Ho Man Hin
 */
public class Circle extends UserShape {
    private final CircularSegment circularSegment;

    /**
     * A circle shape, defined by the center and radius.
     *
     * @param name    the name
     * @param xCenter the x-coordinate of the center
     * @param yCenter the y-coordinate of the center
     * @param radius  the radius
     * @throws SizeIsZeroException warns of zero area
     */
    public Circle(String name, BigDecimal xCenter, BigDecimal yCenter, BigDecimal radius)
            throws SizeIsZeroException {
        super(name);
        this.circularSegment = new CircularSegment(new Point(xCenter, yCenter), radius);
    }

    /**
     * A circle shape, defined by the center and radius.
     *
     * @param name   the name
     * @param center the center
     * @param radius the radius
     * @throws SizeIsZeroException warns of zero area
     */
    public Circle(String name, Point center, BigDecimal radius)
            throws SizeIsZeroException {
        super(name);
        this.circularSegment = new CircularSegment(center, radius);
    }

    /**
     * Gets the circular segment of this circle.
     *
     * @return the circular segment
     */
    public CircularSegment getCircularSegment() {
        return this.circularSegment;
    }

    /**
     * Move the circle by dx and dy.
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public void move(BigDecimal dx, BigDecimal dy) {
        this.circularSegment.move(dx, dy);
    }

    /**
     * Checks if this shape intersect with the line.
     *
     * @param line the line to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Line line) {
        return this.circularSegment.isIntersect(line.getSegment());
    }

    /**
     * Checks if this shape intersect with the circle.
     *
     * @param circle the circle to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Circle circle) {
        return this.circularSegment.isIntersect(circle.getCircularSegment());
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
            if (this.circularSegment.isIntersect(segment1)) {
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
     * Checks if this circle "contains" the point.
     * That is, if the point lie < 0.05 of the perimeter of that circle.
     * <p>
     * This is used mainly for pick-and-move.
     *
     * @param point the point to check for
     * @return if the point is "contained"
     */
    public boolean isContains(Point point) {
        return this.circularSegment.isContains(point);
    }

    /**
     * Gives the bounding box of the shape,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    public BigDecimal[] boundingBox() {
        return this.circularSegment.boundingBox();
    }

    /**
     * Basic listing function.
     *
     * @return A list of output for user
     */
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + this.name);
        out.add("   Type: " + "Circle");
        out.add("xCenter: " + ((double) Math.round(this.circularSegment.getCenter().getX().doubleValue() * 100.0) / 100.0));
        out.add("yCenter: " + ((double) Math.round(this.circularSegment.getCenter().getY().doubleValue() * 100.0) / 100.0));
        out.add(" radius: " + ((double) Math.round(this.circularSegment.getRadius().doubleValue() * 100.0) / 100.0));

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
        out.add("Type: " + "Circle");

        return out;
    }
}
