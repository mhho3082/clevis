package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit test for CircularSegment
 *
 * @author Mok Ka Kiu
 */
public class CircularSegmentTest {
    CircularSegment cs1, cs2;

    @Before
    public void init() throws SizeIsZeroException {
        cs1 = new CircularSegment(new Point(new BigDecimal("-3"), new BigDecimal("5")), new BigDecimal("5"));
        cs2 = new CircularSegment(new Point(new BigDecimal("2.7"), new BigDecimal("-5.7")), new BigDecimal("8.7"));
    }

    @Test
    public void testCreate1() {
        assertEquals(0, new BigDecimal("-3").compareTo(cs1.getCenter().getX()));
        assertEquals(0, new BigDecimal("5").compareTo(cs1.getCenter().getY()));
        assertEquals(0, new BigDecimal("5").compareTo(cs1.getRadius()));
    }

    @Test
    public void testCreate2() {
        assertEquals(0, new BigDecimal("2.7").compareTo(cs2.getCenter().getX()));
        assertEquals(0, new BigDecimal("-5.7").compareTo(cs2.getCenter().getY()));
        assertEquals(0, new BigDecimal("8.7").compareTo(cs2.getRadius()));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new CircularSegment(new Point("2", "3"), new BigDecimal(0));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new CircularSegment(new Point("2", "3"), new BigDecimal(-10));
    }

    @Test
    public void testMove1() {
        cs1.move(new BigDecimal("29"), new BigDecimal("3"));
        assertEquals(0, new BigDecimal("26").compareTo(cs1.getCenter().getX()));
        assertEquals(0, new BigDecimal("8").compareTo(cs1.getCenter().getY()));
    }

    @Test
    public void testMove2() {
        cs1.move(new BigDecimal("-10"), new BigDecimal("-10"));
        assertEquals(0, new BigDecimal("-13").compareTo(cs1.getCenter().getX()));
        assertEquals(0, new BigDecimal("-5").compareTo(cs1.getCenter().getY()));
    }

    @Test
    public void testMove3() {
        cs2.move(new BigDecimal("5.3"), new BigDecimal("10.2"));
        assertEquals(0, new BigDecimal("8").compareTo(cs2.getCenter().getX()));
        assertEquals(0, new BigDecimal("4.5").compareTo(cs2.getCenter().getY()));
    }

    @Test
    public void testMove4() {
        cs2.move(new BigDecimal("-2.3"), new BigDecimal("-9.4"));
        assertEquals(0, new BigDecimal("0.4").compareTo(cs2.getCenter().getX()));
        assertEquals(0, new BigDecimal("-15.1").compareTo(cs2.getCenter().getY()));
    }

    @Test
    public void testIsContains1() {
        // Inside the circle
        assertFalse(cs1.isContains(new Point(new BigDecimal("-2"), new BigDecimal("-3"))));
    }

    @Test
    public void testIsContains2() {
        // Touch the circle
        assertTrue(cs1.isContains(new Point(new BigDecimal("0"), new BigDecimal("9"))));
    }

    @Test
    public void testIsContains3() {
        // Outside the circle
        assertFalse(cs1.isContains(new Point(new BigDecimal("-10"), new BigDecimal("3"))));
    }

    @Test
    public void testIsContains4() {
        // Inside the circle
        assertFalse(cs2.isContains(new Point(new BigDecimal("8.5"), new BigDecimal("-9"))));
    }

    @Test
    public void testIsContains5() {
        // Touch the circle
        assertTrue(cs2.isContains(new Point(new BigDecimal("-4.821"), new BigDecimal("-1.327"))));
    }

    @Test
    public void testIsContains6() {
        // Outside the circle
        assertFalse(cs2.isContains(new Point(new BigDecimal("-6.321"), new BigDecimal("-3.231"))));
    }

    @Test
    public void testIsContain7() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(cs1.isContains(new Point(new BigDecimal("-3"), new BigDecimal("10.049"))));
    }

    @Test
    public void testIsContain8() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(cs1.isContains(new Point(new BigDecimal("-3"), new BigDecimal("10.051"))));
    }

    @Test
    public void testIsContain9() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(cs2.isContains(new Point(new BigDecimal("11.445"), new BigDecimal("-5.7"))));
    }

    @Test
    public void testIsContain10() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(cs2.isContains(new Point(new BigDecimal("11.46"), new BigDecimal("-5.7"))));
    }

    @Test
    public void testBoundingBox1() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("-8");
        out[1] = new BigDecimal("0");
        out[2] = new BigDecimal("10");
        out[3] = new BigDecimal("10");
        assertArrayEquals(out, cs1.boundingBox());
    }

    @Test
    public void testBoundingBox2() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("-6.0");
        out[1] = new BigDecimal("-14.4");
        out[2] = new BigDecimal("17.4");
        out[3] = new BigDecimal("17.4");
        assertArrayEquals(out, cs2.boundingBox());
    }

    @Test
    public void testIsIntersect1() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point(new BigDecimal("6"), new BigDecimal("6")), new BigDecimal("8.2"));
        assertTrue(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect2() throws SizeIsZeroException {
        // cs1 inside test1, but no intersection
        CircularSegment test1 = new CircularSegment(new Point(new BigDecimal("-2"), new BigDecimal("8")), new BigDecimal("8.2"));
        assertFalse(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect3() throws SizeIsZeroException {
        // cs1 inside test1, but no intersection
        CircularSegment test1 = new CircularSegment(new Point(new BigDecimal("-10"), new BigDecimal("-5")), new BigDecimal("3.1"));
        assertFalse(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect4() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point(new BigDecimal("1.412"), new BigDecimal("2.31")), new BigDecimal("5.9"));
        assertTrue(cs2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect5() throws SizeIsZeroException {
        // test1 inside cs2, but no intersection
        CircularSegment test1 = new CircularSegment(new Point(new BigDecimal("2.75844"), new BigDecimal("-2.86041")), new BigDecimal("2"));
        assertFalse(cs2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect6() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point(new BigDecimal("-16.63447"), new BigDecimal("11.99705")), new BigDecimal("6.7"));
        assertFalse(cs2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect7() throws SizeIsZeroException {
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-10"), new BigDecimal("8")), new Point(new BigDecimal("5"), new BigDecimal("8")));
        assertTrue(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect8() throws SizeIsZeroException {
        // Tangent line
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-10"), new BigDecimal("10")), new Point(new BigDecimal("5"), new BigDecimal("10")));
        assertTrue(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect9() throws SizeIsZeroException {
        // Line inside cs1, but no intersection
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-6"), new BigDecimal("6")), new Point(new BigDecimal("-4"), new BigDecimal("8")));
        assertFalse(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect10() throws SizeIsZeroException {
        // Tangent line
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-10"), new BigDecimal("12")), new Point(new BigDecimal("5"), new BigDecimal("12")));
        assertFalse(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect11() throws SizeIsZeroException {
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-3.57167"), new BigDecimal("1.43141")), new Point(new BigDecimal("12.94378"), new BigDecimal("-17.50566")));
        assertTrue(cs2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect12() throws SizeIsZeroException {
        // Tangent line
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-6"), new BigDecimal("0.94708")), new Point(new BigDecimal("-6"), new BigDecimal("-12.75929")));
        assertTrue(cs2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect13() throws SizeIsZeroException {
        // Line inside cs2, but no intersection
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-2"), new BigDecimal("-8")), new Point(new BigDecimal("1.41687"), new BigDecimal("-10.48296")));
        assertFalse(cs2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect14() throws SizeIsZeroException {
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-11.41772"), new BigDecimal("4.04675")), new Point(new BigDecimal("14.44518"), new BigDecimal("9.42275")));
        assertFalse(cs2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect15() throws SizeIsZeroException {
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("-9"), new BigDecimal("5")), new Point(new BigDecimal("-1"), new BigDecimal("5")));
        assertTrue(cs1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect16() throws SizeIsZeroException {
        StraightSegment test1 = new StraightSegment(new Point(new BigDecimal("2.7"), new BigDecimal("-5.7")), new Point(new BigDecimal("2.7"), new BigDecimal("3")));
        assertTrue(cs2.isIntersect(test1));
    }

    @Test
    public void testGetPlot1() {
        double[] out = cs1.getPlot();
        assertEquals(-8, out[0], 0.0001);
        assertEquals(0, out[1], 0.0001);
        assertEquals(10, out[2], 0.0001);
        assertEquals(10, out[3], 0.0001);
        assertEquals(1, out[4], 0.0001);
    }

    @Test
    public void testGetPlot2() {
        double[] out = cs2.getPlot();
        assertEquals(-6, out[0], 0.0001);
        assertEquals(-14.4, out[1], 0.0001);
        assertEquals(17.4, out[2], 0.0001);
        assertEquals(17.4, out[3], 0.0001);
        assertEquals(1, out[4], 0.0001);
    }
}

