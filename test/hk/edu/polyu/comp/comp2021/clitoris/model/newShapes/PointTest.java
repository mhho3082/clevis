package hk.edu.polyu.comp.comp2021.clitoris.model.newShapes;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;


/**
 * Unit test for Point
 *
 * @author Mok Ka Kiu
 */
public class PointTest {
    Point p1, p2;

    @Before
    public void init() {
        p1 = new Point("12", "4");
        p2 = new Point(new BigDecimal("3.231"), new BigDecimal("-21.31"));
    }

    @Test
    public void testCreate1() {
        assertEquals(new BigDecimal("12"), p1.getX());
        assertEquals(new BigDecimal("4"), p1.getY());
    }

    @Test
    public void testCreate2() {
        assertEquals(new BigDecimal("3.231"), p2.getX());
        assertEquals(new BigDecimal("-21.31"), p2.getY());
    }

    @Test
    public void testMove1() {
        // Move with positive value
        p1.move(new BigDecimal("10"), new BigDecimal("4.123"));

        assertEquals(new BigDecimal("22"), p1.getX());
        assertEquals(new BigDecimal("8.123"), p1.getY());
    }

    @Test
    public void testMove2() {
        // Move with negative value
        p1.move(new BigDecimal("-5.113"), new BigDecimal("-2"));

        assertEquals(new BigDecimal("6.887"), p1.getX());
        assertEquals(new BigDecimal("2"), p1.getY());
    }

    @Test
    public void testMove3() {
        // Move with positive value
        p2.move(new BigDecimal("10"), new BigDecimal("4.123"));

        assertEquals(new BigDecimal("13.231"), p2.getX());
        assertEquals(new BigDecimal("-17.187"), p2.getY());
    }

    @Test
    public void testMove4() {
        // Move with negative value
        p2.move(new BigDecimal("-5.113"), new BigDecimal("-2"));

        assertEquals(new BigDecimal("-1.882"), p2.getX());
        assertEquals(new BigDecimal("-23.31"), p2.getY());
    }

    @Test
    public void testEqual1() {
        Point testPoint1 = new Point(new BigDecimal("12"), new BigDecimal("4"));
        Point testPoint2 = new Point(new BigDecimal("12.21"), new BigDecimal("-4"));
        assertTrue(p1.equals(testPoint1));
        assertFalse(p1.equals(testPoint2));
    }

    @Test
    public void testEqual2() {
        Point testPoint1 = new Point(new BigDecimal("3.231"), new BigDecimal("-21.31"));
        Point testPoint2 = new Point(new BigDecimal("12.21"), new BigDecimal("-4"));
        assertTrue(p2.equals(testPoint1));
        assertFalse(p2.equals(testPoint2));
    }

    @Test
    public void testGetLength1() {
        Point testPoint1 = new Point(new BigDecimal("-12"), new BigDecimal("-2.213"));
        assertEquals(new BigDecimal("24.791155"), p1.getLength(testPoint1));
    }

    @Test
    public void testGetLength2() {
        Point testPoint1 = new Point(new BigDecimal("12"), new BigDecimal("4"));
        assertEquals(new BigDecimal("0"), p1.getLength(testPoint1));
    }

    @Test
    public void testGetLength3() {
        Point testPoint1 = new Point(new BigDecimal("23.412"), new BigDecimal("-21"));
        assertEquals(new BigDecimal("20.183381"), p2.getLength(testPoint1));
    }

    @Test
    public void testGetLength4() {
        Point testPoint1 = new Point(new BigDecimal("3.231"), new BigDecimal("-21.31"));
        assertEquals(new BigDecimal("0"), p2.getLength(testPoint1));
    }

}
