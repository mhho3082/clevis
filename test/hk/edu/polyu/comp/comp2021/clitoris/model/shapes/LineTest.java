package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A basic test for line.
 *
 * The test covers the line object created with integer and double.
 *
 * The IsContains1 check for 4 different cases:
 * 1. The point intersects with the line within the end and start point of the line
 * 2. The point intersects with the line outside the end and start point of the line
 * 3. The point doesn't intersect with the line. (The point have minimum distance from outline of the shape is larger than 0.05)
 * 4. The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11]
 *
 * The testList and testListShort check the class return correct value.
 *
 * The DuplicateShapeNameException and SizeIsZeroException check for the throw exception when creating the object.
 *
 * The moveTest check the value xLeft, xRight, yTop and yBottom after move.
 * The move test with positive and negative move for both integer and double
 *
 * @author Mok Ka Kiu
 */
public class LineTest {
    Line line1, line2;
    ArrayList<String> out;

    @Before
    public void init() throws SizeIsZeroException, DuplicateShapeNameException {
        // All integer
        line1 = new Line("test1", 2, 3, 8, 6);
        // All double
        line2 = new Line("test2", 2.7, 5.7, 10.6, 5.7);
    }

    @After
    public void delete() {
        line1.removeName();
        line2.removeName();
    }

    @Test
    public void testIsContains1() {
        // The line intersects the point within two point
        assertTrue(line1.contains(4, 4));
    }

    @Test
    public void testIsContains2() {
        // The line intersects the point within two point
        assertTrue(line1.contains(5, 4.5));
    }

    @Test
    public void testIsContains3() {
        // The line intersects the point outside two point
        assertFalse(line1.contains(0, 2));
    }

    @Test
    public void testIsContains4() {
        // The point have minimum distance from outline of the shape is larger than 0.05
        assertFalse(line1.contains(4, 2));
    }

    @Test
    public void testIsContains5() {
        // The line intersects the point within two point
        assertTrue(line2.contains(5.2, 5.7));
    }

    @Test
    public void testIsContains6() {
        // The point have minimum distance from outline of the shape is larger than 0.05
        assertFalse(line2.contains(11.9, 5.7));
    }

    @Test
    public void testIsContains7() {
        // The line not intersect with the point
        assertFalse(line2.contains(4.2, 6.9));
    }

    @Test
    public void testIsContains8() {
        // The point at the starting or ending point
        assertTrue(line1.contains(2, 3));
    }

    @Test
    public void testIsContains9() {
        // The point at the starting or ending point
        assertTrue(line2.contains(10.6, 5.7));
    }

    @Test
    public void testIsContains10() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11]
        assertTrue(line1.contains(2, 3.049));
    }

    @Test
    public void testIsContains11() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11]
        assertTrue(line2.contains(10.64, 5.7));
    }

    @Test
    public void testIsContains12() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11]
        assertTrue(line1.contains(4, 3.951));
    }

    @Test
    public void testIsContains13() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11]
        assertTrue(line2.contains(5.7, 5.749));
    }

    @Test
    public void testList1() {
        out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Line");
        out.add("  x1: " + (double)2);
        out.add("  y1: " + (double)3);
        out.add("  x2: " + (double)8);
        out.add("  y2: " + (double)6);
        assertEquals(out, line1.list());
    }

    @Test
    public void testList2() {
        out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Line");
        out.add("  x1: " + 2.7);
        out.add("  y1: " + 5.7);
        out.add("  x2: " + 10.6);
        out.add("  y2: " + 5.7);
        assertEquals(out, line2.list());
    }

    @Test
    public void testListShort1() {
        out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Line");
        assertEquals(out, line1.listShort());
    }

    @Test
    public void testListShort2() {
        out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Line");
        assertEquals(out, line2.listShort());
    }

    @Test(expected = DuplicateShapeNameException.class)
    public void testDuplicateShapeNameException() throws SizeIsZeroException, DuplicateShapeNameException {
        // Duplicate Name
        new Line("test1", 2, 3, 8, 6);
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException() throws SizeIsZeroException, DuplicateShapeNameException {
        // Size is 0 (two points are same)
        new Line("test3", 2, 3, 2, 3);
    }

    @Test
    public void testMove1() {
        // Move with positive value
        line1.move(10.2,3);

        assertEquals(12.2, line1.getXLeft(), 0);
        assertEquals(18.2, line1.getXRight(), 0);
        assertEquals(6, line1.getYTop(), 0);
        assertEquals(9, line1.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("Name: " + "test1");
        out.add("Type: Line");
        out.add("  x1: " + 12.2);
        out.add("  y1: " + (double)6);
        out.add("  x2: " + 18.2);
        out.add("  y2: " + (double)9);

        assertEquals(out, line1.list());
    }

    @Test
    public void testMove2() {
        // Move with negative value
        line1.move(-6,-5.9);

        assertEquals(-4, line1.getXLeft(), 0);
        assertEquals(2, line1.getXRight(), 0);
        assertEquals(-2.9, line1.getYTop(), 0);
        assertEquals(0.1, line1.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("Name: " + "test1");
        out.add("Type: Line");
        out.add("  x1: " + (double)-4);
        out.add("  y1: " + -2.9);
        out.add("  x2: " + (double)2);
        out.add("  y2: " + 0.2);

        assertEquals(out, line1.list());
    }

    @Test
    public void testMove3() {
        // Move with positive value
        line2.move(6.2,4);

        assertEquals(8.9, line2.getXLeft(), 0);
        assertEquals(16.8, line2.getXRight(), 0);
        assertEquals(9.7, line2.getYTop(), 0);
        assertEquals(9.7, line2.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("Name: " + "test2");
        out.add("Type: Line");
        out.add("  x1: " + 8.9);
        out.add("  y1: " + 9.7);
        out.add("  x2: " + 16.8);
        out.add("  y2: " + 9.7);

        assertEquals(out, line2.list());
    }

    @Test
    public void testMove4() {
        // Move with negative value
        line2.move(-12,-23.613);

        assertEquals(-9.3, line2.getXLeft(), 0);
        assertEquals(-1.4, line2.getXRight(), 0);
        assertEquals(-17.913, line2.getYTop(), 0);
        assertEquals(-17.913, line2.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("Name: " + "test2");
        out.add("Type: Line");
        out.add("  x1: " + -9.3);
        out.add("  y1: " + -17.913);
        out.add("  x2: " + -1.4);
        out.add("  y2: " + -17.913);

        assertEquals(out, line2.list());
    }
}
