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
 * A basic test for Circle.
 *
 * The test covers the Circle object created with integer and double.
 *
 * The IsContains1 check for 4 different cases:
 * 1. The point inside the Circle
 * 2. The point touch the line of the Circle
 * 3. The point outside the Circle
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
public class CircleTest {
    Circle c1, c2;
    ArrayList<String> out;

    @Before
    public void init() throws SizeIsZeroException, DuplicateShapeNameException {
        // All integer
        c1 = new Circle("test1", -3, 5, 5);
        // All double
        c2 = new Circle("test2", 2.7, -5.7, 8.7);
    }

    @After
    public void delete() {
        c1.removeName();
        c2.removeName();
    }

    @Test
    public void testIsContains1() {
        // Inside the circle
        assertTrue(c1.contains(-2, 3));
    }

    @Test
    public void testIsContains2() {
        // Touch the circle
        assertTrue(c1.contains(0, 9));
    }

    @Test
    public void testIsContains3() {
        // Outside the circle
        assertFalse(c1.contains(-10, 3));
    }

    @Test
    public void testIsContains4() {
        // Inside the circle
        assertTrue(c2.contains(8.5, -9));
    }

    @Test
    public void testIsContains5() {
        // Touch the circle
        assertTrue(c2.contains(-4.821, -1.327));
    }

    @Test
    public void testIsContains6() {
        // Outside the circle
        assertFalse(c2.contains(-6.321, -3.231));
    }

    @Test
    public void testIsContain7() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11] (outside)
        assertTrue(c1.contains(-3, 10.049));
    }

    @Test
    public void testIsContain8() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05 [REQ11] (outside)
        assertFalse(c1.contains(-3, 10.051));
    }

    @Test
    public void testIsContain9() {
        // The point have minimum distance from outline of the shape is smaller than 0.05 [REQ11] (outside)
        assertTrue(c2.contains(11.445, -5.7));
    }

    @Test
    public void testIsContain10() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05 [REQ11] (outside)
        assertFalse(c2.contains(11.46, -5.7));
    }

    @Test
    public void testList1() {
        out = new ArrayList<>();

        out.add("   Name: "+"test1");
        out.add("   Type: Circle");
        out.add("xCenter: " + (double)-3);
        out.add("yCenter: " + (double)5);
        out.add(" Radius: " + (double)5);

        assertEquals(out, c1.list());
    }

    @Test
    public void testList2() {
        out = new ArrayList<>();

        out.add("   Name: "+"test2");
        out.add("   Type: Circle");
        out.add("xCenter: " + 2.7);
        out.add("yCenter: " + -5.7);
        out.add(" Radius: " + 8.7);

        assertEquals(out, c2.list());
    }

    @Test
    public void testListShort1() {
        out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Circle");
        assertEquals(out, c1.listShort());
    }

    @Test
    public void testListShort2() {
        out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Circle");
        assertEquals(out, c2.listShort());
    }

    @Test(expected = DuplicateShapeNameException.class)
    public void testDuplicateShapeNameException() throws SizeIsZeroException, DuplicateShapeNameException {
        // Duplicate Name
        new Circle("test1", 2, 3.3, 8);
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException, DuplicateShapeNameException {
        // Width is 0
        new Circle("test3", 2.1, 3.5, 0);
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException, DuplicateShapeNameException {
        // Width is 0
        new Circle("test3", 2.1, 3.5, -1.321);
    }

    @Test
    public void testMove1() {
        // Move with positive integer
        c1.move(29,3);

        assertEquals(21, c1.getXLeft(), 0);
        assertEquals(31, c1.getXRight(), 0);
        assertEquals(3, c1.getYTop(), 0);
        assertEquals(13, c1.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("   Name: "+"test1");
        out.add("   Type: Circle");
        out.add("xCenter: " + (double)26);
        out.add("yCenter: " + (double)8);
        out.add(" Radius: " + (double)5);

        assertEquals(out, c1.list());
    }

    @Test
    public void testMove2() {
        // Move with negative integer
        c1.move(-10,-10);

        assertEquals(-18, c1.getXLeft(), 0);
        assertEquals(-8, c1.getXRight(), 0);
        assertEquals(-10, c1.getYTop(), 0);
        assertEquals(0, c1.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("   Name: "+"test1");
        out.add("   Type: Circle");
        out.add("xCenter: " + (double)-13);
        out.add("yCenter: " + (double)-5);
        out.add(" Radius: " + (double)5);

        assertEquals(out, c1.list());
    }

    @Test
    public void testMove3() {
        // Move with positive value
        c2.move(5.3,10.2);

        assertEquals(-0.7, c2.getXLeft(), 0);
        assertEquals(16.7, c2.getXRight(), 0);
        assertEquals(-4.2, c2.getYTop(), 0);
        assertEquals(13.2, c2.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("   Name: "+"test1");
        out.add("   Type: Circle");
        out.add("xCenter: " + (double)8);
        out.add("yCenter: " + 4.5);
        out.add(" Radius: " + 8.7);

        assertEquals(out, c2.list());
    }

    @Test
    public void testMove4() {
        // Move with negative value
        c2.move(-2.3,-9.4);

        assertEquals(-8.3, c2.getXLeft(), 0);
        assertEquals(9.1, c2.getXRight(), 0);
        assertEquals(-23.8, c2.getYTop(), 0);
        assertEquals(-6.4, c2.getYBottom(), 0);

        out = new ArrayList<>();

        out.add("   Name: "+"test1");
        out.add("   Type: Circle");
        out.add("xCenter: " + 0.4);
        out.add("yCenter: " + -15.1);
        out.add(" Radius: " + 8.7);

        assertEquals(out, c2.list());
    }

}
