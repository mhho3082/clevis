package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.NegativeSizeException;
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
    /**
     * In the format of [Top, Right, Bottom, Left]
     */
    protected final StraightSegment[] segments = new StraightSegment[4];

    /**
     * A rectangle shape, defined by xLeft, yTop, width, and height.
     *
     * @param name   the name
     * @param xLeft  the left-most x-coordinate
     * @param yTop   the top-most y-coordinate
     * @param width  the width (horizontal)
     * @param height the height (vertical)
     * @throws SizeIsZeroException   warns of zero area
     * @throws NegativeSizeException warns of negative-size shape
     */
    public Rectangle(String name, BigDecimal xLeft, BigDecimal yTop, BigDecimal width, BigDecimal height)
            throws SizeIsZeroException, NegativeSizeException {

        super(name);

        if (width.compareTo(BigDecimal.ZERO) < 0 || height.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeSizeException();
        }

        segments[0] = new StraightSegment(new Point(xLeft, yTop), new Point(xLeft.add(width), yTop)); // Top
        segments[1] = new StraightSegment(new Point(xLeft.add(width), yTop), new Point(xLeft.add(width), yTop.add(height))); // Right
        segments[2] = new StraightSegment(new Point(xLeft, yTop.add(height)), new Point(xLeft.add(width), yTop.add(height))); // Bottom
        segments[3] = new StraightSegment(new Point(xLeft, yTop), new Point(xLeft, yTop.add(height))); // Left
    }

    /**
     * Move the rectangle by dx and dy.
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    @Override
    public void move(BigDecimal dx, BigDecimal dy) {
        for (StraightSegment segment : this.segments) {
            segment.move(dx, dy);
        }
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
    @Override
    public boolean isContains(Point point) {
        for (StraightSegment segment : this.segments) {
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
    @Override
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
    @Override
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + this.name);
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + Config.roundForOutput(this.segments[0].getPoint1().getX()));
        out.add("  yTop: " + Config.roundForOutput(this.segments[0].getPoint1().getY()));
        out.add(" width: " + Config.roundForOutput(this.segments[0].getLength()));
        out.add("height: " + Config.roundForOutput(this.segments[1].getLength()));

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
        out.add("Type: " + "Rectangle");

        return out;
    }

    /**
     * Gets the list of intersect segment.
     *
     * @return a list
     */
    @Override
    public ArrayList<SegmentInterface> getIntersectSegment() {
        ArrayList<SegmentInterface> temp = new ArrayList<>();
        temp.add(this.segments[0]);
        temp.add(this.segments[1]);
        temp.add(this.segments[2]);
        temp.add(this.segments[3]);
        return temp;
    }
}
