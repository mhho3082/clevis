package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.EmptyGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CircleTest {
    Circle c1, c2;

    @Before
    public void init() throws SizeIsZeroException {
        c1 = new Circle("test1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        c2 = new Circle("test2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
    }

    @Test
    public void testCreate1() {
        assertEquals("test1", c1.getName());
        assertEquals(0, new BigDecimal("-3").compareTo(c1.getCircularSegment().getCenter().getX()));
        assertEquals(0, new BigDecimal("5").compareTo(c1.getCircularSegment().getCenter().getY()));
        assertEquals(0, new BigDecimal("5").compareTo(c1.getCircularSegment().getRadius()));
    }

    @Test
    public void testCreate2() {
        assertEquals("test2", c2.getName());
        assertEquals(0, new BigDecimal("2.7").compareTo(c2.getCircularSegment().getCenter().getX()));
        assertEquals(0, new BigDecimal("-5.7").compareTo(c2.getCircularSegment().getCenter().getY()));
        assertEquals(0, new BigDecimal("8.7").compareTo(c2.getCircularSegment().getRadius()));
    }

    @Test
    public void testCompareTo1() {
        assertEquals(-1, c1.compareTo(c2));
    }

    @Test
    public void testCompareTo2() {
        assertEquals(1, c2.compareTo(c1));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new Circle("test3", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("0"));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new Circle("test3", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("-10"));
    }

    @Test
    public void testMove1() {
        c1.move(new BigDecimal("29"), new BigDecimal("3"));
        assertEquals(0, new BigDecimal("26").compareTo(c1.getCircularSegment().getCenter().getX()));
        assertEquals(0, new BigDecimal("8").compareTo(c1.getCircularSegment().getCenter().getY()));
    }

    @Test
    public void testMove2() {
        c1.move(new BigDecimal("-10"), new BigDecimal("-10"));
        assertEquals(0, new BigDecimal("-13").compareTo(c1.getCircularSegment().getCenter().getX()));
        assertEquals(0, new BigDecimal("-5").compareTo(c1.getCircularSegment().getCenter().getY()));
    }

    @Test
    public void testMove3() {
        c2.move(new BigDecimal("5.3"), new BigDecimal("10.2"));
        assertEquals(0, new BigDecimal("8").compareTo(c2.getCircularSegment().getCenter().getX()));
        assertEquals(0, new BigDecimal("4.5").compareTo(c2.getCircularSegment().getCenter().getY()));
    }

    @Test
    public void testMove4() {
        c2.move(new BigDecimal("-2.3"), new BigDecimal("-9.4"));
        assertEquals(0, new BigDecimal("0.4").compareTo(c2.getCircularSegment().getCenter().getX()));
        assertEquals(0, new BigDecimal("-15.1").compareTo(c2.getCircularSegment().getCenter().getY()));
    }

    @Test
    public void testIsContains1() {
        // Inside the circle
        assertFalse(c1.isContains(new Point(new BigDecimal("-2"), new BigDecimal("-3"))));
    }

    @Test
    public void testIsContains2() {
        // Touch the circle
        assertTrue(c1.isContains(new Point(new BigDecimal("0"), new BigDecimal("9"))));
    }

    @Test
    public void testIsContains3() {
        // Outside the circle
        assertFalse(c1.isContains(new Point(new BigDecimal("-10"), new BigDecimal("3"))));
    }

    @Test
    public void testIsContains4() {
        // Inside the circle
        assertFalse(c2.isContains(new Point(new BigDecimal("8.5"), new BigDecimal("-9"))));
    }

    @Test
    public void testIsContains5() {
        // Touch the circle
        assertTrue(c2.isContains(new Point(new BigDecimal("-4.821"), new BigDecimal("-1.327"))));
    }

    @Test
    public void testIsContains6() {
        // Outside the circle
        assertFalse(c2.isContains(new Point(new BigDecimal("-6.321"), new BigDecimal("-3.231"))));
    }

    @Test
    public void testIsContain7() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(c1.isContains(new Point(new BigDecimal("-3"), new BigDecimal("10.049"))));
    }

    @Test
    public void testIsContain8() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(c1.isContains(new Point(new BigDecimal("-3"), new BigDecimal("10.051"))));
    }

    @Test
    public void testIsContain9() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(c2.isContains(new Point(new BigDecimal("11.445"), new BigDecimal("-5.7"))));
    }

    @Test
    public void testIsContain10() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(c2.isContains(new Point(new BigDecimal("11.46"), new BigDecimal("-5.7"))));
    }

    @Test
    public void testBoundingBox1() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("-8");
        out[1] = new BigDecimal("0");
        out[2] = new BigDecimal("10");
        out[3] = new BigDecimal("10");
        assertArrayEquals(out, c1.boundingBox());
    }

    @Test
    public void testBoundingBox2() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("-6.0");
        out[1] = new BigDecimal("-14.4");
        out[2] = new BigDecimal("17.4");
        out[3] = new BigDecimal("17.4");
        assertArrayEquals(out, c2.boundingBox());
    }

    @Test
    public void testIsIntersect1() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("6"), new BigDecimal("6"), new BigDecimal("8.2"));
        assertTrue(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect2() throws SizeIsZeroException {
        // cs1 inside test1, but no intersection
        UserShape test1 = new Circle("test3", new BigDecimal("-2"), new BigDecimal("8"), new BigDecimal("8.2"));
        assertFalse(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect3() throws SizeIsZeroException {
        // cs1 inside test1, but no intersection
        UserShape test1 = new Circle("test3", new BigDecimal("-10"), new BigDecimal("-5"), new BigDecimal("3.1"));
        assertFalse(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect4() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("1.412"), new BigDecimal("2.31"), new BigDecimal("5.9"));
        assertTrue(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect5() throws SizeIsZeroException {
        // test1 inside cs2, but no intersection
        UserShape test1 = new Circle("test3", new BigDecimal("2.75844"), new BigDecimal("-2.86041"), new BigDecimal("2"));
        assertFalse(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect6() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("-16.63447"), new BigDecimal("11.99705"), new BigDecimal("6.7"));
        assertFalse(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect7() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("-10"), new BigDecimal("8"), new BigDecimal("5"), new BigDecimal("8"));
        assertTrue(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect8() throws SizeIsZeroException {
        // Tangent line
        UserShape test1 = new Line("test3", new BigDecimal("-10"), new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("10"));
        assertTrue(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect9() throws SizeIsZeroException {
        // Line inside cs1, but no intersection
        UserShape test1 = new Line("test3", new BigDecimal("-6"), new BigDecimal("6"), new BigDecimal("-4"), new BigDecimal("8"));
        assertFalse(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect10() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("-10"), new BigDecimal("12"), new BigDecimal("5"), new BigDecimal("12"));
        assertFalse(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect11() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("-3.57167"), new BigDecimal("1.43141"), new BigDecimal("12.94378"), new BigDecimal("-17.50566"));
        assertTrue(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect12() throws SizeIsZeroException {
        // Tangent line
        UserShape test1 = new Line("test3", new BigDecimal("-6"), new BigDecimal("0.94708"), new BigDecimal("-6"), new BigDecimal("-12.75929"));
        assertTrue(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect13() throws SizeIsZeroException {
        // Line inside cs2, but no intersection
        UserShape test1 = new Line("test3", new BigDecimal("-2"), new BigDecimal("-8"), new BigDecimal("1.41687"), new BigDecimal("-10.48296"));
        assertFalse(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect14() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("-11.41772"), new BigDecimal("4.04675"), new BigDecimal("14.44518"), new BigDecimal("9.42275"));
        assertFalse(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect15() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("-5"), new BigDecimal("4"), new BigDecimal("7"), new BigDecimal("3"));
        assertTrue(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect16() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("-5"), new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("3"));
        assertFalse(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect17() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("3"), new BigDecimal("7"), new BigDecimal("2"), new BigDecimal("1"));
        assertFalse(c1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect18() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("5.97838"), new BigDecimal("1.03136"), new BigDecimal("10.1"), new BigDecimal("6"));
        assertTrue(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect19() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("5.97838"), new BigDecimal("3.01703"), new BigDecimal("10.1"), new BigDecimal("4"));
        assertFalse(c2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect20() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("3.99161"), new BigDecimal("-9.29789"), new BigDecimal("1.9"), new BigDecimal("4.8"));
        assertFalse(c2.isIntersect(test1));
    }


    @Test
    public void testIsIntersect21() throws SizeIsZeroException, EmptyGroupException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("-5"), new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("3"));
        UserShape test2 = new Line("test4", new BigDecimal("-10"), new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("10"));
        UserShape test3 = new Circle("test5", new BigDecimal("-2"), new BigDecimal("8"), new BigDecimal("8.2"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertTrue(c1.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect22() throws SizeIsZeroException, EmptyGroupException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("-5"), new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("3"));
        UserShape test2 = new Line("test4", new BigDecimal("-6"), new BigDecimal("6"), new BigDecimal("-4"), new BigDecimal("8"));
        UserShape test3 = new Circle("test5", new BigDecimal("-10"), new BigDecimal("-5"), new BigDecimal("3.1"));


        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertFalse(c1.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect23() throws SizeIsZeroException, EmptyGroupException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("5.97838"), new BigDecimal("1.03136"), new BigDecimal("10.1"), new BigDecimal("6"));
        UserShape test2 = new Line("test4", new BigDecimal("-6"), new BigDecimal("0.94708"), new BigDecimal("-6"), new BigDecimal("-12.75929"));
        UserShape test3 = new Circle("test5", new BigDecimal("2.75844"), new BigDecimal("-2.86041"), new BigDecimal("2"));


        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertTrue(c2.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect24() throws SizeIsZeroException, EmptyGroupException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("3.99161"), new BigDecimal("-9.29789"), new BigDecimal("1.9"), new BigDecimal("4.8"));
        UserShape test2 = new Line("test4", new BigDecimal("-2"), new BigDecimal("-8"), new BigDecimal("1.41687"), new BigDecimal("-10.48296"));
        UserShape test3 = new Circle("test5", new BigDecimal("2.75844"), new BigDecimal("-2.86041"), new BigDecimal("2"));


        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertFalse(c2.isIntersect(testGroup));
    }

    @Test
    public void testList1() {
        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + "test1");
        out.add("   Type: Circle");
        out.add("xCenter: " + "-3.00");
        out.add("yCenter: " + "5.00");
        out.add(" radius: " + "5.00");

        assertEquals(out, c1.list());
    }

    @Test
    public void testList2() {
        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + "test2");
        out.add("   Type: Circle");
        out.add("xCenter: " + "2.70");
        out.add("yCenter: " + "-5.70");
        out.add(" radius: " + "8.70");

        assertEquals(out, c2.list());
    }

    @Test
    public void testListShort1() {
        ArrayList<String> out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Circle");
        assertEquals(out, c1.listShort());
    }

    @Test
    public void testListShort2() {
        ArrayList<String> out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Circle");
        assertEquals(out, c2.listShort());
    }
}
