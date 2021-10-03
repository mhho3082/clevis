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
 * The IsContains1 check for 4 different cases:
 * 1. The point inside the Rectangle
 * 2. The point touch the line of the Rectangle
 * 3. The point outside the Rectangle
 * 4. The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11]
 *
 * The testList and testListShort check the class return correct value.
 *
 * The DuplicateShapeNameException and SizeIsZeroException check for the throw exception when creating the object.
 *
 * The moveTest check the value xLeft, xRight, yTop and yBottom after move.
 * The move test with positive and negative move for both integer and double
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
    public void testIsContain7() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11] (outside)
        assertTrue(r1.contains(-4.049, 0));
    }

    @Test
    public void testIsContain8() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05 [REQ11] (outside)
        assertFalse(r1.contains(-4.05, 0));
    }

    @Test
    public void testIsContain9() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11] (outside)
        assertTrue(r2.contains(5.2, 5.66));
    }

    @Test
    public void testIsContain10() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05 [REQ11] (outside)
        assertFalse(r2.contains(5.2, 5.65));
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

    @Test
    public void testMove1() {
        // Move with positive value
        r1.move(12,3.2344);

        assertEquals(8, r1.getXLeft(), 0);
        assertEquals(15, r1.getXRight(), 0);
        assertEquals(0.2344, r1.getYTop(), 0);
        assertEquals(4.2344, r1.getYBottom(), 0);
    }

    @Test
    public void testMove2() {
        // Move with negative value
        r1.move(-6.2131,-23);

        assertEquals(-10.2131, r1.getXLeft(), 0);
        assertEquals(-3.2131, r1.getXRight(), 0);
        assertEquals(-26, r1.getYTop(), 0);
        assertEquals(-22, r1.getYBottom(), 0);
    }

    @Test
    public void testMove3() {
        // Move with positive value
        r2.move(0,1.214);

        assertEquals(2.7, r2.getXLeft(), 0);
        assertEquals(13.3, r2.getXRight(), 0);
        assertEquals(6.914, r2.getYTop(), 0);
        assertEquals(12.614, r2.getYBottom(), 0);
    }

    @Test
    public void testMove4() {
        // Move with negative value
        r2.move(-123.3,-21.4);

        assertEquals(-120.6, r2.getXLeft(), 0);
        assertEquals(-110, r2.getXRight(), 0);
        assertEquals(-15.7, r2.getYTop(), 0);
        assertEquals(-10.2, r2.getYBottom(), 0);
    }
}
