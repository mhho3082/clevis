package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.Config;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.EmptyGroupException;

import java.util.*;

/**
 * Group is a specific subclass of shape,
 * as it is effectively a list of shapes.
 * Member shapes cannot be called directly.
 *
 * @author Ho Man Hin
 */
public class Group extends Shape {
    private ArrayList<Shape> shapes;

    /**
     * Constructs a group.
     * Handles z order.
     * Will throw exception if the name had been used already.
     * The list of shapes is init as empty,
     * please add them by add().
     *
     * @param name the name of the shape.
     */
    public Group(String name, ArrayList<Shape> shapes) throws DuplicateShapeNameException, EmptyGroupException {
        super(name);

        if (shapes.size() == 0) {
            throw new EmptyGroupException(name);
        }

        this.shapes = shapes;

        for (int i = 0; i < this.shapes.size(); i++) {
            if (i == 0) {
                this.xLeft = shapes.get(i).getXLeft();
                this.xRight = shapes.get(i).getXRight();
                this.yTop = shapes.get(i).getYTop();
                this.yBottom = shapes.get(i).getYBottom();
            } else {
                if (this.xLeft > shapes.get(i).getXLeft()) {
                    this.xLeft = shapes.get(i).getXLeft();
                }
                if (this.xRight < shapes.get(i).getXRight()) {
                    this.xRight = shapes.get(i).getXRight();
                }
                if (this.yTop > shapes.get(i).getYTop()) {
                    this.yTop = shapes.get(i).getYTop();
                }
                if (this.yBottom < shapes.get(i).getYBottom()) {
                    this.yBottom = shapes.get(i).getYBottom();
                }
            }
        }
    }

    public void move(int dx, int dy) {
        for (Shape shape : shapes) {
            shape.move(dx, dy);
        }
        super.move(dx, dy);
    }

    /**
     * Removes all names of the member shapes,
     * and that of the group itself.
     * Good for deleting the whole group.
     */
    public void removeName() {
        for (Shape shape : shapes) {
            shape.removeName();
        }
        nameList.remove(this.name);
    }

    /**
     * Removes only the name of the group.
     * Good for ungrouping.
     */
    public void removeGroupName() {
        nameList.remove(this.name);
    }

    public boolean contains(double x, double y) {
        for (Shape shape : shapes) {
            if (shape.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gives a recursive list for listAll.
     * Indents recursively as needed.
     *
     * @return an ArrayList of data
     */
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: Group");
        out.add("");

        for (Shape shape : shapes) {
            ArrayList<String> tempArrayList = shape.list();
            for (String s : tempArrayList) {
                String tempString = Config.INDENT + s;
                out.add(tempString);
            }
            out.add("");
        }

        return out;
    }

    /**
     * Gives a simple list of details.
     * Use for list against this group only.
     *
     * @return an ArrayList of data
     */
    public ArrayList<String> listGroup() {
        ArrayList<String> out = new ArrayList<>();
        ArrayList<String> temp;

        out.add("Name: " + this.name);
        out.add("Type: Group");
        out.add("");

        out.add("Directly contained shapes:");
        for (Shape shape : this.shapes) {
            temp = shape.listShort();
            for (String tempString : temp) {
                out.add(Config.INDENT + tempString);
            }
            out.add("");
        }

        return out;
    }

    public ArrayList<String> listShort() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: Group");
        return out;
    }
}
