package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A basic user shape template.
 * This is a base shape.
 * This shape should not be created directly through user commands.
 *
 * @author Ho Man Hin
 */
public abstract class UserShape implements Comparable<UserShape> {
    protected static long zOrderHighest = 0;
    protected final long zOrder;
    protected final String name;

    /**
     * A base, abstract shape for user-interactive shapes.
     * Duplicate name checks are not carried out here, but in main model.
     *
     * @param name the name of the shape
     */
    public UserShape(String name) {
        this.name = name;
        this.zOrder = zOrderHighest;
        zOrderHighest++;
    }

    /**
     * Gets the UserShape's name.
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Changes how a collection of UserShapes is sorted.
     * The sort now bases itself on zOrder.
     *
     * @param o the UserShape to compare to
     * @return the difference between the two object's zOrder
     */
    public int compareTo(UserShape o) {
        return (int) (this.zOrder - o.zOrder);
    }

    /**
     * Move a shape by dx and dy.
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public abstract void move(BigDecimal dx, BigDecimal dy);

    /**
     * Checks if this shape intersect with the userShape.
     *
     * @param userShape the userShape to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(UserShape userShape) {
        if (userShape instanceof Rectangle) {
            return this.isIntersect((Rectangle) userShape);
        } else if (userShape instanceof Line) {
            return this.isIntersect((Line) userShape);
        } else if (userShape instanceof Circle) {
            return this.isIntersect((Circle) userShape);
        } else {
            return this.isIntersect((Group) userShape);
        }
    }

    /**
     * Checks if this shape intersect with the line.
     *
     * @param line the line to check this shape against
     * @return if the two shapes intersect
     */
    public abstract boolean isIntersect(Line line);

    /**
     * Checks if this shape intersect with the circle.
     *
     * @param circle the circle to check this shape against
     * @return if the two shapes intersect
     */
    public abstract boolean isIntersect(Circle circle);

    /**
     * Checks if this shape intersect with the rectangle.
     * Also works for square.
     *
     * @param rectangle the rectangle/square to check this shape against
     * @return if the two shapes intersect
     */
    public abstract boolean isIntersect(Rectangle rectangle);

    /**
     * Checks if this shape intersect with the group.
     * Also works for square.
     *
     * @param group the group to check this shape against
     * @return if the two shapes intersect
     */
    public abstract boolean isIntersect(Group group);

    /**
     * Checks if the shape "contains" the point.
     * That is, for some line involved in the shape,
     * if the point lie < 0.05 of that line.
     * <p>
     * This is used mainly for pick-and-move.
     *
     * @param point the point to check for
     * @return if the point is "contained"
     */
    public abstract boolean isContains(Point point);

    /**
     * Gives the bounding box of the shape,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    public abstract BigDecimal[] boundingBox();

    /**
     * Basic listing function.
     * Would trigger listShort for elements in group.
     *
     * @return A list of output for user
     */
    public abstract ArrayList<String> list();

    /**
     * For listing all.
     * Indent added automatically for grouped shapes.
     * Would just use list() for non-group shapes.
     *
     * @return A list of output for user
     */
    public ArrayList<String> listAll() {
        return this.list();
    }

    /**
     * For use inside list of a group.
     * No indent included.
     *
     * @return A short list of output, to be merged into list of group
     */
    public abstract ArrayList<String> listShort();
}
