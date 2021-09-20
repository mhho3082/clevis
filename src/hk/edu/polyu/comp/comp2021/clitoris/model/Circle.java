package hk.edu.polyu.comp.comp2021.clitoris.model;

import java.util.ArrayList;

public class Circle extends Shape {
    private double xCenter;
    private double yCenter;
    private double radius;

    public Circle(String name, double xCenter, double yCenter, double radius)
            throws SizeIsZeroException, DuplicateShapeNameException {
        super(name);
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.radius = radius;

        this.xLeft = xCenter - radius;
        this.xRight = xCenter + radius;
        this.yTop = yCenter - radius;
        this.yBottom = yCenter + radius;
    }

    public boolean contains(double x, double y) {
        if (Math.sqrt(Math.pow(x - xCenter, 2) + Math.pow(y - yCenter, 2)) <= radius) {
            return true;
        }
        return false;
    }

    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: "+this.name);
        out.add("   Type: Circle");
        out.add("xCenter: " + this.xCenter);
        out.add("yCenter: " + this.yCenter);
        out.add(" Radius: " + this.radius);

        return out;
    }

    public ArrayList<String > listShort() {
        ArrayList <String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: Circle");
        return out;
    }
}
