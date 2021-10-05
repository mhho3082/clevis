package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.Config;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.EmptyGroupException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * A group shape.
 * Can be "created" by the user.
 * Shapes grouped cannot be accessed by user until ungroup.
 *
 * @author Ho Man Hin
 */
public class Group extends UserShape {
    private final ArrayList<UserShape> userShapes;

    /**
     * A group shape.
     *
     * @param name       the group name
     * @param userShapes the shapes to be grouped
     */
    public Group(String name, ArrayList<UserShape> userShapes) throws EmptyGroupException {
        super(name);
        if (userShapes.size() == 0) {
            throw new EmptyGroupException();
        }
        this.userShapes = userShapes;
    }

    /**
     * Gets the shapes in the group
     *
     * @return an arraylist of the shapes
     */
    public ArrayList<UserShape> getUserShapes() {
        return this.userShapes;
    }

    /**
     * Move the group by dx and dy.
     *
     * @param dx the amount to be moved (rightwards) in the x-axis
     * @param dy the amount to be moved (downwards) in the y-axis
     */
    public void move(BigDecimal dx, BigDecimal dy) {
        for (UserShape userShape : this.userShapes) {
            userShape.move(dx, dy);
        }
    }

    /**
     * Checks if this shape intersect with the line.
     *
     * @param line the line to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Line line) {
        for (UserShape userShape : this.userShapes) {
            if (userShape.isIntersect(line)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this shape intersect with the circle.
     *
     * @param circle the circle to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Circle circle) {
        for (UserShape userShape : this.userShapes) {
            if (userShape.isIntersect(circle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this shape intersect with the rectangle.
     * Also works for square.
     *
     * @param rectangle the rectangle/square to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Rectangle rectangle) {
        for (UserShape userShape : this.userShapes) {
            if (userShape.isIntersect(rectangle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this shape intersect with the group.
     * Also works for square.
     *
     * @param group the group to check this shape against
     * @return if the two shapes intersect
     */
    public boolean isIntersect(Group group) {
        for (UserShape userShape1 : this.userShapes) {
            for (UserShape userShape2 : group.getUserShapes()) {
                if (userShape2 instanceof Line) {
                    if (userShape1.isIntersect((Line) userShape2)) {
                        return true;
                    }
                } else if (userShape2 instanceof Circle) {
                    if (userShape1.isIntersect((Circle) userShape2)) {
                        return true;
                    }
                } else if (userShape2 instanceof Rectangle) {
                    if (userShape1.isIntersect((Rectangle) userShape2)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the group "contains" the point.
     * That is, for some line involved in a shape of the group,
     * if the point lie < 0.05 of that line.
     * <p>
     * This is used mainly for pick-and-move.
     *
     * @param point the point to check for
     * @return if the point is "contained"
     */
    public boolean isContains(Point point) {
        for (UserShape userShape : userShapes) {
            if (userShape.isContains(point)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gives the general bounding box of the shapes in this group,
     * in the form of [xLeft, yTop, width, height].
     *
     * @return an array of data
     */
    public BigDecimal[] boundingBox() {
        BigDecimal[] out = userShapes.get(0).boundingBox();
        BigDecimal[] temp;


        for (UserShape userShape : userShapes) {
            temp = userShape.boundingBox();

            if (temp[0].compareTo(out[0]) < 0) { // Left-er
                out[0] = temp[0];
            }

            if (temp[1].compareTo(out[1]) < 0) { // Top-er
                out[1] = temp[1];
            }

            if ((temp[0].add(temp[2])).compareTo(out[0].add(out[2])) > 0) { // Right-er
                out[2] = temp[0].add(temp[2]).subtract(out[0]);
            }

            if ((temp[1].add(temp[3])).compareTo(out[1].add(out[3])) > 0) { // Bottom-er
                out[3] = temp[1].add(temp[3]).subtract(out[1]);
            }
        }

        return out;
    }

    /**
     * Basic listing function.
     * Would trigger listShort for elements in group.
     *
     * @return A list of output for user
     */
    public ArrayList<String> list() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: " + "Group");
        out.add("");

        for (int i = 0; i < userShapes.size(); i++) {
            for (String temp : userShapes.get(i).listShort()) {
                out.add(Config.INDENT + temp);
            }
            if (i != userShapes.size() - 1)
                out.add(Config.INDENT);
        }

        return out;
    }

    /**
     * For listing all.
     * Indent added automatically for grouped shapes.
     *
     * @return A list of output for user
     */
    public ArrayList<String> listAll() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: " + "Group");
        out.add("");

        for (int i = 0; i < userShapes.size(); i++) {
            for (String temp : userShapes.get(i).listAll()) {
                out.add(Config.INDENT + temp);
            }
            if (i != userShapes.size() - 1)
                out.add(Config.INDENT);
        }

        return out;
    }

    /**
     * For use inside list of a group.
     * No indent included.
     *
     * @return A short list of output, to be merged into list of group
     */
    public ArrayList<String> listShort() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + this.name);
        out.add("Type: " + "Group");

        return out;
    }
}
