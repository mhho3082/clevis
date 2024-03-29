package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.Config;
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
    @Override
    public void move(BigDecimal dx, BigDecimal dy) {
        this.circularSegment.move(dx, dy);
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
    @Override
    public boolean isContains(Point point) {
        return this.circularSegment.isContains(point);
    }

    /**
     * Gives the bounding box of the shape,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    @Override
    public BigDecimal[] boundingBox() {
        return this.circularSegment.boundingBox();
    }

    /**
     * Basic listing function.
     *
     * @return A list of output for user
     */
    @Override
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + this.name);
        out.add("   Type: " + "Circle");
        out.add("xCenter: " + Config.roundForOutput(this.circularSegment.getCenter().getX()));
        out.add("yCenter: " + Config.roundForOutput(this.circularSegment.getCenter().getY()));
        out.add(" radius: " + Config.roundForOutput(this.circularSegment.getRadius()));

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
        out.add("Type: " + "Circle");

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
        temp.add(this.circularSegment);
        return temp;
    }

    @Override
    public ArrayList<double[]> getPlot() {
        ArrayList<double[]> temp = new ArrayList<>();
        temp.add(this.circularSegment.getPlot());
        return temp;
    }
}
