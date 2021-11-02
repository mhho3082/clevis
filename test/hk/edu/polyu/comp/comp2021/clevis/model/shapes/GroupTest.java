package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.EmptyGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.NegativeSizeException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for Group
 *
 * @author Mok Ka Kiu
 */
public class GroupTest {
    Line l1 = new Line("test1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
    Circle c2 = new Circle("test2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
    Rectangle r1 = new Rectangle("test3", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
    Square s1 = new Square("test4", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("12"));
    ArrayList<UserShape> groupShape;
    Group g1;

    public GroupTest() throws SizeIsZeroException, NegativeSizeException {
    }

    @Before
    public void init() throws EmptyGroupException {
        groupShape = new ArrayList<>();
        groupShape.add(l1);
        groupShape.add(c2);
        groupShape.add(r1);
        groupShape.add(s1);

        g1 = new Group("test", groupShape);
    }

    @Test(expected = EmptyGroupException.class)
    public void testEmptyGroupException() throws EmptyGroupException {
        new Group("test1", new ArrayList<>());
    }

    @Test
    public void testGetUserShapes() {
        assertEquals(groupShape, g1.getUserShapes());
    }

    @Test
    public void testGetUserShapesNames() {
        ArrayList<String> temp = new ArrayList<>();

        temp.add("test4");
        temp.add("test3");
        temp.add("test2");
        temp.add("test1");

        assertEquals(temp, g1.getUserShapesNames());
    }

    @Test
    public void testListShort() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + g1.getName());
        out.add("Type: " + "Group");

        assertEquals(out, g1.listShort());
    }

    @Test
    public void testListAll() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: test");
        out.add("Type: Group");
        out.add("");

        out.add(Config.INDENT + "  Name: " + "test4");
        out.add(Config.INDENT + "  Type: " + "Square");
        out.add(Config.INDENT + " xLeft: " + "1.00");
        out.add(Config.INDENT + "  yTop: " + "-7.00");
        out.add(Config.INDENT + "length: " + "12.00");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "  Name: " + "test3");
        out.add(Config.INDENT + "  Type: " + "Rectangle");
        out.add(Config.INDENT + " xLeft: " + "-4.00");
        out.add(Config.INDENT + "  yTop: " + "-3.00");
        out.add(Config.INDENT + " width: " + "7.00");
        out.add(Config.INDENT + "height: " + "4.00");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "   Name: " + "test2");
        out.add(Config.INDENT + "   Type: Circle");
        out.add(Config.INDENT + "xCenter: " + "2.70");
        out.add(Config.INDENT + "yCenter: " + "-5.70");
        out.add(Config.INDENT + " radius: " + "8.70");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test1");
        out.add(Config.INDENT + "Type: Line");
        out.add(Config.INDENT + "  x1: " + "2.00");
        out.add(Config.INDENT + "  y1: " + "3.00");
        out.add(Config.INDENT + "  x2: " + "8.00");
        out.add(Config.INDENT + "  y2: " + "6.00");

        assertEquals(out, g1.listAll());
    }

    @Test
    public void testList() {
        ArrayList<String> out = new ArrayList<>();

        out.add("Name: test");
        out.add("Type: Group");
        out.add("");

        out.add(Config.INDENT + "Name: " + "test4");
        out.add(Config.INDENT + "Type: " + "Square");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test3");
        out.add(Config.INDENT + "Type: " + "Rectangle");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test2");
        out.add(Config.INDENT + "Type: Circle");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test1");
        out.add(Config.INDENT + "Type: Line");

        assertEquals(out, g1.list());
    }


    @Test
    public void testMove() {
        g1.move(new BigDecimal("1.5"), new BigDecimal("-1"));

        ArrayList<String> out = new ArrayList<>();

        out.add("Name: test");
        out.add("Type: Group");
        out.add("");

        out.add(Config.INDENT + "  Name: " + "test4");
        out.add(Config.INDENT + "  Type: " + "Square");
        out.add(Config.INDENT + " xLeft: " + "2.50");
        out.add(Config.INDENT + "  yTop: " + "-8.00");
        out.add(Config.INDENT + "length: " + "12.00");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "  Name: " + "test3");
        out.add(Config.INDENT + "  Type: " + "Rectangle");
        out.add(Config.INDENT + " xLeft: " + "-2.50");
        out.add(Config.INDENT + "  yTop: " + "-4.00");
        out.add(Config.INDENT + " width: " + "7.00");
        out.add(Config.INDENT + "height: " + "4.00");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "   Name: " + "test2");
        out.add(Config.INDENT + "   Type: Circle");
        out.add(Config.INDENT + "xCenter: " + "4.20");
        out.add(Config.INDENT + "yCenter: " + "-6.70");
        out.add(Config.INDENT + " radius: " + "8.70");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test1");
        out.add(Config.INDENT + "Type: Line");
        out.add(Config.INDENT + "  x1: " + "3.50");
        out.add(Config.INDENT + "  y1: " + "2.00");
        out.add(Config.INDENT + "  x2: " + "9.50");
        out.add(Config.INDENT + "  y2: " + "5.00");

        assertEquals(out, g1.listAll());
    }

    @Test
    public void testIsContains1() {
        // On the segment
        assertTrue(g1.isContains(new Point("4", "4")));
    }

    @Test
    public void testIsContains2() {
        // Next to the segment
        assertTrue(g1.isContains(new Point("2", "3.049")));
    }

    @Test
    public void testIsContains3() {
        // Next to the point
        assertTrue(g1.isContains(new Point("1.998", "2.999")));
    }

    @Test
    public void testIsContains4() {
        // Not Contain
        assertFalse(g1.isContains(new Point("4", "2")));
    }

    @Test
    public void testIsContains5() {
        // Inside the circle
        assertFalse(g1.isContains(new Point(new BigDecimal("8.5"), new BigDecimal("-9"))));
    }

    @Test
    public void testIsContains6() {
        // Touch the circle
        assertTrue(g1.isContains(new Point(new BigDecimal("-4.821"), new BigDecimal("-1.327"))));
    }

    @Test
    public void testIsContains7() {
        // Outside the circle
        assertFalse(g1.isContains(new Point(new BigDecimal("-6.321"), new BigDecimal("-3.231"))));
    }

    @Test
    public void testIsContain8() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(g1.isContains(new Point(new BigDecimal("11.445"), new BigDecimal("-5.7"))));
    }

    @Test
    public void testIsContain9() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(g1.isContains(new Point(new BigDecimal("11.46"), new BigDecimal("-5.7"))));
    }

    @Test
    public void testIsContains10() {
        // Inside the rectangle
        assertFalse(g1.isContains(new Point(new BigDecimal("-1"), new BigDecimal("-1"))));
    }

    @Test
    public void testIsContains11() {
        // Touch the line
        assertTrue(g1.isContains(new Point(new BigDecimal("-4"), new BigDecimal("-1"))));
    }

    @Test
    public void testIsContains12() {
        // Outside the rectangle
        assertFalse(g1.isContains(new Point(new BigDecimal("-4"), new BigDecimal("2"))));
    }

    @Test
    public void testIsContain13() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(g1.isContains(new Point(new BigDecimal("-4.049"), new BigDecimal("0"))));
    }

    @Test
    public void testIsContain14() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(g1.isContains(new Point(new BigDecimal("-4.05"), new BigDecimal("0"))));
    }

    @Test
    public void testBoundingBox() {
        assertEquals(0, new BigDecimal("-6").compareTo(g1.boundingBox()[0]));
        assertEquals(0, new BigDecimal("-14.4").compareTo(g1.boundingBox()[1]));
        assertEquals(0, new BigDecimal("17.4").compareTo(g1.boundingBox()[2]));
        assertEquals(0, new BigDecimal("20.4").compareTo(g1.boundingBox()[3]));
    }

    @Test
    public void testGetPlot() throws EmptyGroupException, NegativeSizeException, SizeIsZeroException {
        ArrayList<UserShape> shapeList = new ArrayList<>();
        Rectangle r1 = new Rectangle("test1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        shapeList.add(r1);
        Group temp = new Group("temp", shapeList);

        ArrayList<double[]> out = temp.getPlot();
        assertEquals(-4, out.get(0)[0], 0.0001);
        assertEquals(-3, out.get(0)[1], 0.0001);
        assertEquals(3, out.get(0)[2], 0.0001);
        assertEquals(-3, out.get(0)[3], 0.0001);
        assertEquals(0.0, out.get(0)[4], 0.0001);

        assertEquals(3, out.get(1)[0], 0.0001);
        assertEquals(-3, out.get(1)[1], 0.0001);
        assertEquals(3, out.get(1)[2], 0.0001);
        assertEquals(1, out.get(1)[3], 0.0001);
        assertEquals(0.0, out.get(1)[4], 0.0001);

        assertEquals(-4, out.get(2)[0], 0.0001);
        assertEquals(1, out.get(2)[1], 0.0001);
        assertEquals(3, out.get(2)[2], 0.0001);
        assertEquals(1, out.get(2)[3], 0.0001);
        assertEquals(0.0, out.get(2)[4], 0.0001);

        assertEquals(-4, out.get(3)[0], 0.0001);
        assertEquals(-3, out.get(3)[1], 0.0001);
        assertEquals(-4, out.get(3)[2], 0.0001);
        assertEquals(1, out.get(3)[3], 0.0001);
        assertEquals(0.0, out.get(3)[4], 0.0001);
    }
}
