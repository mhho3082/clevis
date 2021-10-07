package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;
import hk.edu.polyu.comp.comp2021.clevis.model.shapes.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The general model.
 *
 * @author Ho Man Hin
 */
public class Clevis {
    private final ArrayList<UserShape> shapeList;
    private final ArrayList<UserShape> bin;

    /**
     * Constructs the general model.
     */
    public Clevis() {
        shapeList = new ArrayList<>();

        bin = new ArrayList<>();
    }

    /**
     * Adds a UserShape into the list.
     * As UserShapes should not be created outside the model,
     * so this is private.
     *
     * @param userShape the shape to be added
     * @throws DuplicateShapeNameException the name was already used
     */
    private void add(UserShape userShape) throws DuplicateShapeNameException {
        if (!this.deepContains(userShape.getName())) {
            this.shapeList.add(userShape);
            this.sortZOrder();
        } else {
            throw new DuplicateShapeNameException(userShape.getName());
        }
    }

    /**
     * Adds a rectangle to the shape list.
     * Must be straight without rotation, and cannot be rotated.
     *
     * @param name   the name
     * @param xLeft  the left-most x-coordinate
     * @param yTop   the top-most y-coordinate
     * @param width  the width (horizontal)
     * @param height the height (vertical)
     * @throws DuplicateShapeNameException warns of duplicate name
     * @throws SizeIsZeroException         warns of zero area
     */
    public void addRectangle(String name, BigDecimal xLeft, BigDecimal yTop, BigDecimal width, BigDecimal height)
            throws DuplicateShapeNameException, SizeIsZeroException {
        Rectangle rectangle = new Rectangle(name, xLeft, yTop, width, height);
        this.add(rectangle);
    }

    /**
     * Adds a line to the shape list.
     *
     * @param name the name
     * @param x1   the x-coordinate of the first point
     * @param y1   the y-coordinate of the first point
     * @param x2   the x-coordinate of the second point
     * @param y2   the y-coordinate of the second point
     * @throws SizeIsZeroException         warns of zero area
     * @throws DuplicateShapeNameException warns of duplicate name
     */
    public void addLine(String name, BigDecimal x1, BigDecimal y1, BigDecimal x2, BigDecimal y2)
            throws SizeIsZeroException, DuplicateShapeNameException {
        Line line = new Line(name, x1, y1, x2, y2);
        this.add(line);
    }

    /**
     * Adds a circle to the shape list.
     *
     * @param name    the name
     * @param xCenter the x-coordinate of the center
     * @param yCenter the y-coordinate of the center
     * @param radius  the radius
     * @throws SizeIsZeroException         warns of zero area
     * @throws DuplicateShapeNameException warns of duplicate name
     */
    public void addCircle(String name, BigDecimal xCenter, BigDecimal yCenter, BigDecimal radius)
            throws SizeIsZeroException, DuplicateShapeNameException {
        Circle circle = new Circle(name, xCenter, yCenter, radius);
        this.add(circle);
    }

    /**
     * Adds a square to the shape list.
     *
     * @param name   the name of the square
     * @param xLeft  the left-most x-coordinate
     * @param yTop   the top-most y-coordinate
     * @param length the length
     * @throws SizeIsZeroException         warns of zero area
     * @throws DuplicateShapeNameException warns of duplicate name
     */
    public void addSquare(String name, BigDecimal xLeft, BigDecimal yTop, BigDecimal length)
            throws SizeIsZeroException, DuplicateShapeNameException {
        Square square = new Square(name, xLeft, yTop, length);
        this.add(square);
    }

    /**
     * Creates a group.
     *
     * @param name       the name of the group
     * @param shapeNames the name(s) of grouped shapes
     * @throws DuplicateShapeNameException warns of duplicate names
     * @throws ShapeNotFoundException      warns of grouped shape not found
     * @throws EmptyGroupException         warns of empty group
     * @throws ShapeInsideGroupException   warns of grouped shape already grouped
     */
    public void group(String name, ArrayList<String> shapeNames)
            throws DuplicateShapeNameException, ShapeNotFoundException, EmptyGroupException, ShapeInsideGroupException {
        if (shapeNames.size() == 0) {
            throw new EmptyGroupException();
        }

        if (this.deepContains(name)) {
            throw new DuplicateShapeNameException(name);
        }

        for (String tempString : shapeNames) {
            checkShallowContains(tempString);
        }

        ArrayList<UserShape> temp = new ArrayList<>();
        int i;
        for (String tempString : shapeNames) {
            i = this.findIndex(tempString);
            temp.add(this.shapeList.get(i));
            this.shapeList.remove(i);
        }

        Group group = new Group(name, temp);
        this.add(group);
        this.sortZOrder();
    }

    /**
     * Removes the group,
     * returning grouped shapes into the list.
     *
     * @param name the name
     * @throws ShapeNotFoundException      warns of the group not found
     * @throws DuplicateShapeNameException warns of grouped objects with duplicate names (would not be triggered usually)
     * @throws ShapeInsideGroupException   warns that group to ungroup inside another group
     */
    public void ungroup(String name)
            throws ShapeNotFoundException, DuplicateShapeNameException, ShapeInsideGroupException {
        checkShallowContains(name);

        ArrayList<UserShape> temp = ((Group) this.find(name)).getUserShapes();

        this.remove(name);

        for (UserShape tempShape : temp) {
            this.add(tempShape);
        }

        this.sortZOrder();
    }

    /**
     * Gets the bounding box,
     * in the format of {x, y, w, h}.
     *
     * @param name the name of the shape to be calculated
     * @return the bounding box
     * @throws ShapeInsideGroupException warns of shape inside group
     * @throws ShapeNotFoundException    warns of shape not found
     */
    public BigDecimal[] boundingBox(String name)
            throws ShapeInsideGroupException, ShapeNotFoundException {
        checkShallowContains(name);

        return this.find(name).boundingBox();
    }

    /**
     * Moves the shape by the given coordinates.
     *
     * @param name the name of the shape
     * @param dx   the amount to move rightwards
     * @param dy   the amount to move downwards
     * @throws ShapeNotFoundException    warns of shape not found
     * @throws ShapeInsideGroupException warns of shape inside group
     */
    public void move(String name, BigDecimal dx, BigDecimal dy)
            throws ShapeNotFoundException, ShapeInsideGroupException {
        checkShallowContains(name);

        this.find(name).move(dx, dy);
    }

    /**
     * Finds the uppermost shape "containing" the given point (in terms of zOrder)
     * and moves it.
     *
     * @param x  the x-coordinate of the point
     * @param y  the y-coordinate of the point
     * @param dx the amount to move rightwards
     * @param dy the amount to move downwards
     * @throws NoShapeContainsPointException warns of no shape containing specified point
     */
    public void pickAndMove(BigDecimal x, BigDecimal y, BigDecimal dx, BigDecimal dy)
            throws NoShapeContainsPointException {
        this.sortZOrder();
        Point point = new Point(x, y);

        for (UserShape shape : shapeList) {
            if (shape.isContains(point)) {
                shape.move(dx, dy);
                return;
            }
        }

        throw new NoShapeContainsPointException();
    }

    /**
     * Checks if the given two shapes directly intersects.
     *
     * @param n1 the name of the first shape
     * @param n2 the name of the second shape
     * @return whether the two shapes intersect
     * @throws ShapeNotFoundException    warns of shape(s) not found
     * @throws ShapeInsideGroupException warns of shape(s) inside group
     */
    public boolean intersect(String n1, String n2)
            throws ShapeNotFoundException, ShapeInsideGroupException {
        checkShallowContains(n1);
        checkShallowContains(n2);

        return this.find(n1).isIntersect(this.find(n2));
    }

    /**
     * Gives the details of a particular shape.
     *
     * @param name the name of a shape
     * @return an ArrayList of details
     * @throws ShapeNotFoundException    warns of shape not found
     * @throws ShapeInsideGroupException warns of shape in group
     */
    public ArrayList<String> list(String name)
            throws ShapeNotFoundException, ShapeInsideGroupException {
        checkShallowContains(name);

        return this.find(name).list();
    }

    /**
     * Gives the details of all shapes in the list.
     *
     * @return an ArrayList of details
     */
    public ArrayList<String> listAll() {


        ArrayList<String> out = new ArrayList<>();

        for (int i = 0; i < shapeList.size(); i++) {
            ArrayList<String> temp = shapeList.get(i).listAll();
            out.addAll(temp);
            if (i + 1 < shapeList.size()) {
                out.add("");
            }
        }

        return out;
    }

    /**
     * Disposes the UserShape specified into the bin.
     *
     * @param name the name of the shape
     * @throws ShapeNotFoundException warns of shape not found
     */
    public void remove(String name)
            throws ShapeNotFoundException, ShapeInsideGroupException {
        checkShallowContains(name);

        int i = this.findIndex(name);
        this.bin.add(this.shapeList.get(i));
        this.shapeList.remove(i);
        this.sortZOrder();
    }

    /**
     * Restores a UserShape with the name from the bin.
     * Do not use this for restoring groups.
     *
     * @param name the name of the shape
     * @throws ShapeNotFoundException      warns of shape not found
     * @throws DuplicateShapeNameException warns of duplicate name with currently existing shapes
     */
    public void restore(String name) throws ShapeNotFoundException, DuplicateShapeNameException {
        if (this.binContains(name)) {
            int i = this.findInBinIndex(name);
            this.add(this.bin.get(i));
            this.bin.remove(i);
            this.sortZOrder();
        } else {
            throw new ShapeNotFoundException(name);
        }
    }

    /**
     * Checks if named shape is on shallow level.
     * Does not return anything, but is a snippet of code
     * to throw exceptions.
     *
     * @param name the name to be checked
     * @throws ShapeInsideGroupException the named shape is in a group
     * @throws ShapeNotFoundException    the named shape cannot be found
     */
    public void checkShallowContains(String name)
            throws ShapeInsideGroupException, ShapeNotFoundException {
        if (!this.shallowContains(name)) {
            if (this.deepContains(name)) {
                throw new ShapeInsideGroupException(name);
            }

            throw new ShapeNotFoundException(name);
        }
    }

    /**
     * Checks if any UserShape in the list has the name specified.
     *
     * @param name the name to be checked
     * @return whether the name was found
     */
    public boolean shallowContains(String name) {
        this.sortZOrder();
        for (UserShape userShape : shapeList) {
            if (name.equals(userShape.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if any UserShape in the list
     * (including those in groups) has the name specified.
     *
     * @param name the name to be checked
     * @return whether the name was found
     */
    public boolean deepContains(String name) {
        this.sortZOrder();
        for (UserShape userShape : shapeList) {
            if (name.equals(userShape.getName())) {
                return true;
            }
            if (userShape instanceof Group) {
                ArrayList<UserShape> temp = ((Group) userShape).getUserShapes();

                for (UserShape tempShape : temp) {
                    if (name.equals(tempShape.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if any UserShape in the bin has the name specified.
     *
     * @param name the name to be checked
     * @return whether the name was found
     */
    public boolean binContains(String name) {
        for (UserShape userShape : bin) {
            if (name.equals(userShape.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the shape with the given name
     *
     * @param name the name to be searched for
     * @return the UserShape
     * @throws ShapeNotFoundException warns of no shape with that name
     */
    public UserShape find(String name) throws ShapeNotFoundException {
        this.sortZOrder();
        for (UserShape userShape : shapeList) {
            if (name.equals(userShape.getName())) {
                return userShape;
            }
        }
        throw new ShapeNotFoundException(name);
    }

    /**
     * Finds the shape with the given name
     *
     * @param name the name to be searched for
     * @return the index of the UserShape found
     * @throws ShapeNotFoundException warns of no shape with that name
     */
    public int findIndex(String name) throws ShapeNotFoundException {
        this.sortZOrder();
        for (int i = 0; i < shapeList.size(); i++) {
            if (name.equals(shapeList.get(i).getName())) {
                return i;
            }
        }
        throw new ShapeNotFoundException(name);
    }

    /**
     * Finds the shape with the given name in the bin,
     * in the order the shapes were thrown.
     * More recent ones come up first.
     *
     * @param name the name to be searched for
     * @return the index of the UserShape found
     * @throws ShapeNotFoundException warns of no shape with that name
     */
    public int findInBinIndex(String name) throws ShapeNotFoundException {
        for (int i = bin.size() - 1; i >= 0; i--) {
            if (name.equals(bin.get(i).getName())) {
                return i;
            }
        }
        throw new ShapeNotFoundException(name);
    }

    /**
     * Sorts the list of shapes by decreasing zOrder.
     */
    private void sortZOrder() {
        Collections.sort(shapeList);
        Collections.reverse(shapeList);
    }

}
