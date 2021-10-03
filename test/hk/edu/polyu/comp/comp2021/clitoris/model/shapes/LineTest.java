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
 * The IsContains1 check for 3 different cases:
 * 1. The point intersects with the line within the end and start point of the line
 * 2. The point intersects with the line outside the end and start point of the line
 * 3. The point doesn't intersect with the line.
 *
 * The testList and testListShort check the class return correct value.
 *
 * The DuplicateShapeNameException and SizeIsZeroException check for the throw exception when creating the object.
 *
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
        // The line not intersect with the point
        assertFalse(line1.contains(4, 2));
    }

    @Test
    public void testIsContains5() {
        // The line intersects the point within two point
        assertTrue(line2.contains(5.2, 5.7));
    }

    @Test
    public void testIsContains6() {
        // The line intersects the point outside two point
        assertFalse(line2.contains(11.9, 5.7));
    }

    @Test
    public void testIsContains7() {
        // The line not intersect with the point
        assertFalse(line2.contains(4.2, 6.9));
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

}
