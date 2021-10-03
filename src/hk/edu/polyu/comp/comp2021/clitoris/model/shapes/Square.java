package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;

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

        out.add("  Name: " + this.name);
        out.add("  Type: Square");
        out.add(" xLeft: " + this.xLeft);
        out.add("  yTop: " + this.yTop);
        out.add("length: " + (xRight - xLeft));

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
