package hk.edu.polyu.comp.comp2021.clitoris.model.newShapes;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A basic user shape template.
 * This is a base shape.
 * This shape should not be created directly through user commands.
 *
 * @author Ho Man Hin
 */
public abstract class UserShape {
    protected static int zOrderHighest = 0;
    protected final int zOrder;
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
     * Move a shape by dx and dy.
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public abstract void move(BigDecimal dx, BigDecimal dy);

    /**
     * Checks if the two shapes intersect.
     *
     * @param shape the shape to check this shape against
     * @return if the two shapes intersect
     */
    public abstract boolean isIntersect(UserShape shape);

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
    public abstract ArrayList<String> listAll();

    /**
     * For use inside list of a group.
     * No indent included.
     *
     * @return A short list of output, to be merged into list of group
     */
    public abstract ArrayList<String> listShort();
}
