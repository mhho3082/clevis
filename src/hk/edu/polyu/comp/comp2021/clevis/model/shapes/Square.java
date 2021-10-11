package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A square shape.
 * Has to be straight (no rotation)
 * And cannot be rotated.
 *
 * @author Ho Man Hin
 */
public class Square extends Rectangle {
    /**
     * A square shape, defined by xLeft, yTop, and length.
     *
     * @param name   the name of the square
     * @param xLeft  the left-most x-coordinate
     * @param yTop   the top-most y-coordinate
     * @param length the length
     * @throws SizeIsZeroException warns of zero area
     */
    public Square(String name, BigDecimal xLeft, BigDecimal yTop, BigDecimal length)
            throws SizeIsZeroException {
        super(name, xLeft, yTop, length, length);
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
        out.add("  Type: " + "Square");
        out.add(" xLeft: " + Config.roundForOutput(this.segments[0].getPoint1().getX()));
        out.add("  yTop: " + Config.roundForOutput(this.segments[0].getPoint1().getY()));
        out.add("length: " + Config.roundForOutput(this.segments[0].getLength()));

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
        out.add("Type: " + "Square");

        return out;
    }
}
