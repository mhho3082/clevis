package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;

import java.util.*;

/**
 * A basic rectangle.
 * @author Ho Man Hin
 */
public class Rectangle extends Shape {
    public Rectangle(String name, double xLeft, double yTop, double width, double height) throws DuplicateShapeNameException, SizeIsZeroException {
        super(name);

        this.xLeft   = xLeft;
        this.yTop    = yTop;
        this.xRight  = xLeft + width;
        this.yBottom = yTop + height;

        if (width == 0 || height == 0) {
            throw new SizeIsZeroException(name);
        }
    }

    public boolean contains(double x, double y) {
        if (x < this.xLeft || x > this.xRight) {
            return false;
        }

        return !(y < this.yBottom) && !(y > this.yTop);
    }

    public ArrayList <String> list() {
        ArrayList <String> out = new ArrayList <>();

        out.add("Name: " + this.name);
        out.add("Type: Rectangle");
        out.add("   x: " + this.xLeft);
        out.add("   y: " + this.yTop);
        out.add("   w: " + Math.abs(xLeft - xRight));
        out.add("   h: " + Math.abs(yTop - yBottom));

        return out;
    }

    public ArrayList <String> listShort() {
        ArrayList <String> out = new ArrayList <>();

        out.add("Name: " + this.name);
        out.add("Type: Rectangle");
        return out;
    }
}
