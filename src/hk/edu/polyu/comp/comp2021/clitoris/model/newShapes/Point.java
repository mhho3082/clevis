package hk.edu.polyu.comp.comp2021.clitoris.model.newShapes;

import java.math.BigDecimal;
import java.math.MathContext;

public class Point {
    private BigDecimal x;
    private BigDecimal y;

    /**
     * A point, defined by x- and y- coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(String x, String y) {
        this.x = new BigDecimal(String.valueOf(x));
        this.y = new BigDecimal(String.valueOf(y));
    }

    /**
     * A point, defined by x- and y- coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public Point(BigDecimal x, BigDecimal y) {
        this.x = new BigDecimal(String.valueOf(x));
        this.y = new BigDecimal(String.valueOf(y));
    }

    /**
     * Moves the point by the given coordinates
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public void move(BigDecimal dx, BigDecimal dy) {
        this.x = this.x.add(dx);
        this.y = this.y.add(dy);
    }

    /**
     * Gets the x coordinate
     *
     * @return the x coordinate
     */
    public BigDecimal getX() {
        return x;
    }

    /**
     * Gets the y coordinate
     *
     * @return the y coordinate
     */
    public BigDecimal getY() {
        return y;
    }

    /**
     * Checks if a point is equal to this one.
     *
     * @param point the point to be checked
     * @return if the two points have the same coordinates
     */
    public boolean equals(Point point) {
        return this.x.compareTo(point.x) == 0 && this.y.compareTo(point.y) == 0;
    }

    /**
     * Gets the length between this point and another point.
     *
     * @param point the other point in the calculation
     * @return the length between the two points
     */
    public BigDecimal getLength(Point point) {
        return this.x.subtract(point.x).pow(2).add(this.y.subtract(point.y).pow(2)).sqrt(MathContext.UNLIMITED);
    }
}
