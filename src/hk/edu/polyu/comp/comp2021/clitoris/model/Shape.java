package hk.edu.polyu.comp.comp2021.clitoris.model;

import java.util.*;

/**
 * The base object for shape objects.
 *
 * @author Ho Man Hin
 */
public abstract class Shape {
    protected final String name;
    protected static ArrayList<String> nameList = new ArrayList<>();

    protected final int zOrder;
    private static int zOrderHighest = 0;

    protected double xLeft;
    protected double xRight;
    protected double yTop;
    protected double yBottom;

    /**
     * Constructs a shape.
     * Handles z order.
     * Will throw exception if the name had been used already.
     *
     * @param name the name of the shape.
     */
    public Shape(String name) throws DuplicateShapeNameException {
        this.name = name;
        this.zOrder = zOrderHighest;

        zOrderHighest++;

        if (nameList.contains(name)) {
            throw new DuplicateShapeNameException(this.name);
        }

        nameList.add(this.name);
    }

    /**
     * returns the name of the shape.
     *
     * @return name of shape
     */
    public String getName() {
        return this.name;
    }

    /**
     * returns the z order of the shape.
     *
     * @return z order
     */
    public int getZOrder() {
        return this.zOrder;
    }

    /**
     * Move the shape by dx and dy.
     *
     * @param dx the amount of distance to be moved in the x-axis
     * @param dy the amount of distance to be moved in the y-axis
     */
    public void move(double dx, double dy) {
        this.xLeft += dx;
        this.xRight += dx;
        this.yTop += dy;
        this.yBottom += dy;
    }

    /**
     * Lists the attributes of the shape.
     *
     * @return a String ArrayList of attributes
     */
    public abstract ArrayList<String> list();

    /**
     * A simple and short version of list for listing a group.
     *
     * @return the name and type of the object in an ArrayList of String
     */
    public abstract ArrayList<String> listShort();

    /**
     * Check if the shape contains the given point.
     *
     * @param x the x coordinate of the point
     * @param y the y coordinate of the point
     * @return whether the shape overlaps with the point
     */
    public abstract boolean contains(double x, double y);

    /**
     * Removes the name from name list.
     * Important to trigger this for deleting the shape.
     */
    public void removeName() {
        nameList.remove(this.name);
    }

    /**
     * Returns [x, y, w, h]
     * showing the minimum bounding box.
     *
     * @return an array of 4 doubles [x, y, w, h]
     */
    public double[] boundingBox() {
        return new double[]{
                this.xLeft,
                this.yTop,
                Math.abs(this.xRight - this.xLeft),
                Math.abs(this.yBottom - this.yTop)
        };
    }

    public double getXLeft() {
        return xLeft;
    }

    public double getXRight() {
        return xRight;
    }

    public double getYTop() {
        return yTop;
    }

    public double getYBottom() {
        return yBottom;
    }
}
