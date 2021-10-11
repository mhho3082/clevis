package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

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
    @Override
    public void move(BigDecimal dx, BigDecimal dy) {
        this.segment.move(dx, dy);
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
    @Override
    public boolean isContains(Point point) {
        return this.segment.isContains(point);
    }

    /**
     * Gives the bounding box of the shape,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    @Override
    public BigDecimal[] boundingBox() {
        return this.segment.boundingBox();
    }

    /**
     * Basic listing function.
     *
     * @return A list of output for user
     */
    @Override
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: " + "Line");
        out.add("  x1: " + Config.roundForOutput(this.segment.getPoint1().getX()));
        out.add("  y1: " + Config.roundForOutput(this.segment.getPoint1().getY()));
        out.add("  x2: " + Config.roundForOutput(this.segment.getPoint2().getX()));
        out.add("  y2: " + Config.roundForOutput(this.segment.getPoint2().getY()));

        return out;
    }

    /**
     * For use inside list of a group.
     * No indent included.
     *
     * @return A short list of output, to be merged into list of group
     */
    @Override
    public ArrayList<String> listShort() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: " + "Line");

        return out;
    }

    /**
     * Gets the list of intersect segment.
     *
     * @return a list
     */
    @Override
    public ArrayList<IntersectSegment> getIntersectSegment() {
        ArrayList<IntersectSegment> temp = new ArrayList<>();
        temp.add(this.segment);
        return temp;
    }
}
