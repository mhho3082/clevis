package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit test for Segment
 *
 * @author Mok Ka Kiu
 */
public class SegmentTest {
    Segment s1, s2;

    @Before
    public void init() throws SizeIsZeroException {
        s1 = new Segment(new Point("2", "3"), new Point("8", "6"));
        s2 = new Segment(new Point("2.7", "5.7"), new Point("10.6", "5.7"));
    }

    @Test
    public void testCreate1() {
        assertEquals(0, new BigDecimal("2").compareTo(s1.getPoint1().getX()));
        assertEquals(0, new BigDecimal("3").compareTo(s1.getPoint1().getY()));
        assertEquals(0, new BigDecimal("8").compareTo(s1.getPoint2().getX()));
        assertEquals(0, new BigDecimal("6").compareTo(s1.getPoint2().getY()));
    }

    @Test
    public void testCreate2() {
        assertEquals(0, new BigDecimal("2.7").compareTo(s2.getPoint1().getX()));
        assertEquals(0, new BigDecimal("5.7").compareTo(s2.getPoint1().getY()));
        assertEquals(0, new BigDecimal("10.6").compareTo(s2.getPoint2().getX()));
        assertEquals(0, new BigDecimal("5.7").compareTo(s2.getPoint2().getY()));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new Segment(new Point("2", "3"), new Point("2", "3"));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new Segment(new Point("2.7", "5.7"), new Point("2.7", "5.7"));
    }

    @Test
    public void testMove1() {
        // Move with positive value
        s1.move(new BigDecimal("10.2"), new BigDecimal("3"));
        assertEquals(0, new BigDecimal("12.2").compareTo(s1.getPoint1().getX()));
        assertEquals(0, new BigDecimal("6").compareTo(s1.getPoint1().getY()));
        assertEquals(0, new BigDecimal("18.2").compareTo(s1.getPoint2().getX()));
        assertEquals(0, new BigDecimal("9").compareTo(s1.getPoint2().getY()));
    }

    @Test
    public void testMove2() {
        // Move with negative value
        s1.move(new BigDecimal("-6"), new BigDecimal("-5.9"));
        assertEquals(0, new BigDecimal("-4").compareTo(s1.getPoint1().getX()));
        assertEquals(0, new BigDecimal("-2.9").compareTo(s1.getPoint1().getY()));
        assertEquals(0, new BigDecimal("2").compareTo(s1.getPoint2().getX()));
        assertEquals(0, new BigDecimal("0.1").compareTo(s1.getPoint2().getY()));
    }

    @Test
    public void testMove3() {
        // Move with positive value
        s2.move(new BigDecimal("6.2"), new BigDecimal("4"));
        assertEquals(0, new BigDecimal("8.9").compareTo(s2.getPoint1().getX()));
        assertEquals(0, new BigDecimal("9.7").compareTo(s2.getPoint1().getY()));
        assertEquals(0, new BigDecimal("16.8").compareTo(s2.getPoint2().getX()));
        assertEquals(0, new BigDecimal("9.7").compareTo(s2.getPoint2().getY()));
    }

    @Test
    public void testMove4() {
        // Move with negative value
        s2.move(new BigDecimal("-12"), new BigDecimal("-23.613"));
        assertEquals(0, new BigDecimal("-9.3").compareTo(s2.getPoint1().getX()));
        assertEquals(0, new BigDecimal("-17.913").compareTo(s2.getPoint1().getY()));
        assertEquals(0, new BigDecimal("-1.4").compareTo(s2.getPoint2().getX()));
        assertEquals(0, new BigDecimal("-17.913").compareTo(s2.getPoint2().getY()));
    }

    @Test
    public void testEquals1() throws SizeIsZeroException {
        assertTrue(s1.equals(new Segment(new Point("2", "3"), new Point("8", "6"))));
        assertTrue(s1.equals(new Segment(new Point("8", "6"), new Point("2", "3"))));
        assertFalse(s1.equals(new Segment(new Point("8", "-6"), new Point("-2", "3"))));
    }

    @Test
    public void testEquals2() throws SizeIsZeroException {
        assertTrue(s2.equals(new Segment(new Point("2.7", "5.7"), new Point("10.6", "5.7"))));
        assertTrue(s2.equals(new Segment(new Point("10.6", "5.7"), new Point("2.7", "5.7"))));
        assertFalse(s2.equals(new Segment(new Point("-2.7", "5.7"), new Point("10.6", "-5.7"))));
    }

    @Test
    public void testGetLength1() {
        assertEquals("6.708203", s1.getLength().toString().substring(0, 8));
    }

    @Test
    public void testGetLength2() {
        assertEquals(0, new BigDecimal("7.9").compareTo(s2.getLength()));
    }

    @Test
    public void testIsOnSegment1() {
        assertTrue(s1.isOnSegment(new Point("5", "4.5")));
    }

    @Test
    public void testIsOnSegment2() {
        assertFalse(s1.isOnSegment(new Point("0", "2")));
    }

    @Test
    public void testIsOnSegment3() {
        assertTrue(s2.isOnSegment(new Point("5.2", "5.7")));
    }

    @Test
    public void testIsOnSegment4() {
        assertFalse(s2.isOnSegment(new Point("4.2", "6.9")));
    }

    @Test
    public void testIsContains1() {
        // On the segment
        assertTrue(s1.isContains(new Point("4", "4")));
    }

    @Test
    public void testIsContains2() {
        // Next to the segment
        assertTrue(s1.isContains(new Point("2", "3.049")));
    }

    @Test
    public void testIsContains3() {
        // Next to the point
        assertTrue(s1.isContains(new Point("1.998", "2.999")));
    }

    @Test
    public void testIsContains4() {
        // Not Contain
        assertFalse(s1.isContains(new Point("4", "2")));
    }

    @Test
    public void testIsContains5() {
        // On the segment
        assertTrue(s2.isContains(new Point("5.2", "5.7")));
    }

    @Test
    public void testIsContains6() {
        // Next to the segment
        assertTrue(s2.isContains(new Point("5.2", "5.7")));
    }

    @Test
    public void testIsContain7() {
        // Next to the point
        assertTrue(s2.isContains(new Point("10.6009", "5.6995")));
    }

    @Test
    public void testIsContain8() {
        // Not contain
        assertFalse(s2.isContains(new Point("11.9", "5.7")));
    }

    @Test
    public void testBoundingBox1() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("2");
        out[1] = new BigDecimal("3");
        out[2] = new BigDecimal("6");
        out[3] = new BigDecimal("3");
        assertArrayEquals(out, s1.boundingBox());
    }

    @Test
    public void testBoundingBox2() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("2.7");
        out[1] = new BigDecimal("5.7");
        out[2] = new BigDecimal("7.9");
        out[3] = new BigDecimal("0.0");
        assertArrayEquals(out, s2.boundingBox());
    }

    @Test
    public void testIsIntersect1() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("2", "7"), new Point("9", "5"));
        assertTrue(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect2() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("1", "4"), new Point("3", "2"));
        assertTrue(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect3() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("10", "4"), new Point("2", "0"));
        assertFalse(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect4() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("5.38", "3.06"), new Point("7.0901", "-3.3542"));
        assertFalse(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect5() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("2", "-1.312"), new Point("2", "2.5"));
        assertFalse(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect6() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("2.7","2.2444043940538"), new Point("2.7", "6.6505436797202"));
        assertTrue(s2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect7() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("10.6334555381729", "4.213"), new Point("0.6155883675046", "4.213"));
        assertFalse(s2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect8() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("4", "3"), new Point("5", "5"));
        assertFalse(s2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect9() throws SizeIsZeroException {
        Segment test1 = new Segment(new Point("2.7","5.7213123123"), new Point("2.7", "6.6505436797202"));
        assertFalse(s2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect10() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point("4","6"), new BigDecimal("1.8"));
        assertTrue(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect11() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point("4","6"), new BigDecimal("2.7"));
        assertTrue(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect12() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point("4","6"), new BigDecimal("0.3123"));
        assertFalse(s1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect13() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point("6.59482", "8.67821"), new BigDecimal("3"));
        assertTrue(s2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect14() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point("6.59482", "8.67821"), new BigDecimal("3.3213123"));
        assertTrue(s2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect15() throws SizeIsZeroException {
        CircularSegment test1 = new CircularSegment(new Point("6.59482", "8.67821"), new BigDecimal("1.321312"));
        assertFalse(s2.isIntersect(test1));
    }

}
