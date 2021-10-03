package hk.edu.polyu.comp.comp2021.clitoris.model.shapes;

import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.DuplicateShapeNameException;
import hk.edu.polyu.comp.comp2021.clitoris.model.exceptions.SizeIsZeroException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * A basic test for Rectangle.
 *
 * The test covers the Rectangle object created with integer and double.
 *
 * The IsContains1 check for 3 different cases:
 * 1. The point inside the Rectangle
 * 2. The point touch the line of the Rectangle
 * 3. The point outside the Rectangle
 *
 * The testList and testListShort check the class return correct value.
 *
 * The DuplicateShapeNameException and SizeIsZeroException check for the throw exception when creating the object.
 *
 *
 * @author Mok Ka Kiu
 */
public class RectangleTest {
    Rectangle r1, r2;
    ArrayList<String> out;

    @Before
    public void init() throws SizeIsZeroException, DuplicateShapeNameException {
        // All integer
        r1 = new Rectangle("test1", -4, -3, 7, 4);
        // All double
        r2 = new Rectangle("test2", 2.7, 5.7, 10.6, 5.7);
    }

    @After
    public void delete() {
        r1.removeName();
        r2.removeName();
    }

    @Test
    public void testIsContains1() {
        // Inside the rectangle
        assertTrue(r1.contains(-1, -1));
    }

    @Test
    public void testIsContains2() {
        // Touch the line
        assertTrue(r1.contains(-4, -1));
    }

    @Test
    public void testIsContains3() {
        // Outside the rectangle
        assertFalse(r1.contains(-4, 2));
    }

    @Test
    public void testIsContains4() {
        // Inside the rectangle
        assertTrue(r2.contains(7.3, 9.2));
    }

    @Test
    public void testIsContains5() {
        // Touch the line
        assertTrue(r2.contains(5.5, 5.7));
    }

    @Test
    public void testIsContains6() {
        // Outside the rectangle
        assertFalse(r2.contains(2.7, 11.5));
    }

    @Test
    public void testList1() {
        out = new ArrayList<>();
        out.add("  Name: " + "test1");
        out.add("  Type: Rectangle");
        out.add(" xLeft: " + (double)-4);
        out.add("  yTop: " + (double)-3);
        out.add(" width: " + (double)7);
        out.add("height: " + (double)4);
        assertEquals(out, r1.list());
    }

    @Test
    public void testList2() {
        out = new ArrayList<>();
        out.add("  Name: " + "test2");
        out.add("  Type: Rectangle");
        out.add(" xLeft: " + 2.7);
        out.add("  yTop: " + 5.7);
        out.add(" width: " + 10.6);
        out.add("height: " + 5.7);
        assertEquals(out, r2.list());
    }

    @Test
    public void testListShort1() {
        out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Rectangle");
        assertEquals(out, r1.listShort());
    }

    @Test
    public void testListShort2() {
        out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Rectangle");
        assertEquals(out, r2.listShort());
    }

    @Test(expected = DuplicateShapeNameException.class)
    public void testDuplicateShapeNameException() throws SizeIsZeroException, DuplicateShapeNameException {
        // Duplicate Name
        new Rectangle("test1", 2, 3.3, 8, 6);
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException, DuplicateShapeNameException {
        // Width is 0
        new Rectangle("test3", 2.1, 3.5, 0, 3.4);
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException, DuplicateShapeNameException {
        // Height is 0
        new Rectangle("test3", 2.1, 3.5, 3.4, 0);
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException3() throws SizeIsZeroException, DuplicateShapeNameException {
        // Width and Height is 0
        new Rectangle("test3", 2.1, 3.5, 0, 0);
    }

}
