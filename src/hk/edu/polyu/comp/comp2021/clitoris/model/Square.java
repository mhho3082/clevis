package hk.edu.polyu.comp.comp2021.clitoris.model;

import java.util.ArrayList;

/**
 * A square, implemented as a rectangle with w == h.
 * @author Ho Man Hin
 */
public class Square extends Rectangle {
    public Square(String name, double xLeft, double yTop, double length) throws DuplicateShapeNameException, SizeIsZeroException {
        super(name, xLeft, yTop, length, length);
    }

    public ArrayList<String> list() {
        ArrayList <String> out = new ArrayList <>();

        out.add("Name: " + this.name);
        out.add("Type: Square");
        out.add("   x: " + this.xLeft);
        out.add("   y: " + this.yTop);
        out.add("   l: " + Math.abs(xLeft - xRight));

        return out;
    }

    @Override
    public ArrayList<String> listShort() {
        ArrayList <String> out = new ArrayList <>();

        out.add("Name: " + this.name);
        out.add("Type: Square");
        return out;
    }
}
