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
        assertEquals(0, new BigDecimal("12").compareTo(p1.getX()));
        assertEquals(0, new BigDecimal("4").compareTo(p1.getY()));
    }

    @Test
    public void testCreate2() {
        assertEquals(0, new BigDecimal("3.231").compareTo(p2.getX()));
        assertEquals(0, new BigDecimal("-21.31").compareTo(p2.getY()));
    }

    @Test
    public void testMove1() {
        // Move with positive value
        p1.move(new BigDecimal("10"), new BigDecimal("4.123"));

        assertEquals(0, new BigDecimal("22").compareTo(p1.getX()));
        assertEquals(0, new BigDecimal("8.123").compareTo(p1.getY()));
    }

    @Test
    public void testMove2() {
        // Move with negative value
        p1.move(new BigDecimal("-5.113"), new BigDecimal("-2"));

        assertEquals(0, new BigDecimal("6.887").compareTo(p1.getX()));
        assertEquals(0, new BigDecimal("2").compareTo(p1.getY()));
    }

    @Test
    public void testMove3() {
        // Move with positive value
        p2.move(new BigDecimal("10"), new BigDecimal("4.123"));

        assertEquals(0, new BigDecimal("13.231").compareTo(p2.getX()));
        assertEquals(0, new BigDecimal("-17.187").compareTo(p2.getY()));
    }

    @Test
    public void testMove4() {
        // Move with negative value
        p2.move(new BigDecimal("-5.113"), new BigDecimal("-2"));

        assertEquals(0, new BigDecimal("-1.882").compareTo(p2.getX()));
        assertEquals(0, new BigDecimal("-23.31").compareTo(p2.getY()));
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
        assertEquals("24.791155", p1.getLength(testPoint1).toString().substring(0, 9));
    }

    @Test
    public void testGetLength2() {
        Point testPoint1 = new Point(new BigDecimal("12"), new BigDecimal("4"));
        assertEquals("0", p1.getLength(testPoint1).toString().substring(0, 1));
    }

    @Test
    public void testGetLength3() {
        Point testPoint1 = new Point(new BigDecimal("23.412"), new BigDecimal("-21"));
        assertEquals("20.183380", p2.getLength(testPoint1).toString().substring(0, 9));
    }

    @Test
    public void testGetLength4() {
        Point testPoint1 = new Point(new BigDecimal("3.231"), new BigDecimal("-21.31"));
        assertEquals(0, new BigDecimal("0").compareTo(p2.getLength(testPoint1)));
    }

}
