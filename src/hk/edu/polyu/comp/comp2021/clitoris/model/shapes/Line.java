package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;

import java.util.ArrayList;

/**
 * A basic line.
 * The two points are stored as (x1, x2) and (y1, y2).
 *
 * @author Ho Man Hin
 */
public class Line extends Shape {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public Line(String name, double x1, double y1, double x2, double y2) throws DuplicateShapeNameException, SizeIsZeroException {
        super(name);

        this.xLeft = Math.min(x1, x2);
        this.yTop = Math.min(y1, y2);
        this.xRight = Math.max(x1, x2);
        this.yBottom = Math.max(y1, y2);

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        if (Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)) == 0) {
            throw new SizeIsZeroException(name);
        }
    }

    public boolean contains(double x, double y) {
        if (x >= this.xLeft && x <= this.xRight && y >= this.yTop && y <= this.yBottom) {
            double xA = Math.sqrt(Math.pow(x - this.x1, 2) + Math.pow(y - this.y1, 2));
            double xB = Math.sqrt(Math.pow(x - this.x2, 2) + Math.pow(y - this.y2, 2));
            double xAll = Math.sqrt(Math.pow(this.x1 - this.x2, 2) + Math.pow(this.y1 - this.y2, 2));

            return xA + xB == xAll;
        }
        return false;
    }

    public void move(double dx, double dy) {
        super.move(dx, dy);
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: Line");
        out.add("  x1: " + this.x1);
        out.add("  y1: " + this.y1);
        out.add("  x2: " + this.x2);
        out.add("  y2: " + this.y2);

        return out;
    }

    public ArrayList<String> listShort() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: Line");

        return out;
    }
}
