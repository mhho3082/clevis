package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;

import java.util.*;

/**
 * A basic rectangle.
 *
 * @author Ho Man Hin
 */
public class Rectangle extends Shape {
    public Rectangle(String name, double xLeft, double yTop, double width, double height) throws DuplicateShapeNameException, SizeIsZeroException {
        super(name);

        this.xLeft = xLeft;
        this.yTop = yTop;
        this.xRight = xLeft + width;
        this.yBottom = yTop + height;

        if (width == 0 || height == 0) {
            throw new SizeIsZeroException(name);
        }
    }

    public boolean contains(double x, double y) {
        return x >= this.xLeft && x <= this.xRight && y >= this.yTop && y <= this.yBottom;
    }

    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + this.name);
        out.add("  Type: Rectangle");
        out.add(" xLeft: " + this.xLeft);
        out.add("  yTop: " + this.yTop);
        out.add(" width: " + Math.abs(xLeft - xRight));
        out.add("height: " + Math.abs(yTop - yBottom));

        return out;
    }

    public ArrayList<String> listShort() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: Rectangle");
        return out;
    }
}
