package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.EmptyGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * Unit test for Line
 *
 * @author Mok Ka Kiu
 */
public class LineTest {
    Line L1, L2;

    @Before
    public void init() throws SizeIsZeroException {
        L1 = new Line("test1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        L2 = new Line("test2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
    }

    @Test
    public void testCreate1(){
        assertEquals(0, new BigDecimal("2").compareTo(L1.getSegment().getPoint1().getX()));
        assertEquals(0, new BigDecimal("3").compareTo(L1.getSegment().getPoint1().getY()));
        assertEquals(0, new BigDecimal("8").compareTo(L1.getSegment().getPoint2().getX()));
        assertEquals(0, new BigDecimal("6").compareTo(L1.getSegment().getPoint2().getY()));
        assertEquals("test1", L1.getName());
    }

    @Test
    public void testCreate2(){
        assertEquals(0, new BigDecimal("2.7").compareTo(L2.getSegment().getPoint1().getX()));
        assertEquals(0, new BigDecimal("5.7").compareTo(L2.getSegment().getPoint1().getY()));
        assertEquals(0, new BigDecimal("10.6").compareTo(L2.getSegment().getPoint2().getX()));
        assertEquals(0, new BigDecimal("5.7").compareTo(L2.getSegment().getPoint2().getY()));
        assertEquals("test2", L2.getName());
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new Line("test3", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("2"), new BigDecimal("3"));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException {
        // The size of the segment is 0 (two are same)
        new Line("test3", new BigDecimal("10.6"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
    }

    @Test
    public void testMove1() {
        // Move with positive value
        L1.move(new BigDecimal("10.2"), new BigDecimal("3"));
        assertEquals(0, new BigDecimal("12.2").compareTo(L1.getSegment().getPoint1().getX()));
        assertEquals(0, new BigDecimal("6").compareTo(L1.getSegment().getPoint1().getY()));
        assertEquals(0, new BigDecimal("18.2").compareTo(L1.getSegment().getPoint2().getX()));
        assertEquals(0, new BigDecimal("9").compareTo(L1.getSegment().getPoint2().getY()));
    }

    @Test
    public void testMove2() {
        // Move with negative value
        L1.move(new BigDecimal("-6"), new BigDecimal("-5.9"));
        assertEquals(0, new BigDecimal("-4").compareTo(L1.getSegment().getPoint1().getX()));
        assertEquals(0, new BigDecimal("-2.9").compareTo(L1.getSegment().getPoint1().getY()));
        assertEquals(0, new BigDecimal("2").compareTo(L1.getSegment().getPoint2().getX()));
        assertEquals(0, new BigDecimal("0.1").compareTo(L1.getSegment().getPoint2().getY()));
    }

    @Test
    public void testMove3() {
        // Move with positive value
        L2.move(new BigDecimal("6.2"), new BigDecimal("4"));
        assertEquals(0, new BigDecimal("8.9").compareTo(L2.getSegment().getPoint1().getX()));
        assertEquals(0, new BigDecimal("9.7").compareTo(L2.getSegment().getPoint1().getY()));
        assertEquals(0, new BigDecimal("16.8").compareTo(L2.getSegment().getPoint2().getX()));
        assertEquals(0, new BigDecimal("9.7").compareTo(L2.getSegment().getPoint2().getY()));
    }

    @Test
    public void testMove4() {
        // Move with negative value
        L2.move(new BigDecimal("-12"), new BigDecimal("-23.613"));
        assertEquals(0, new BigDecimal("-9.3").compareTo(L2.getSegment().getPoint1().getX()));
        assertEquals(0, new BigDecimal("-17.913").compareTo(L2.getSegment().getPoint1().getY()));
        assertEquals(0, new BigDecimal("-1.4").compareTo(L2.getSegment().getPoint2().getX()));
        assertEquals(0, new BigDecimal("-17.913").compareTo(L2.getSegment().getPoint2().getY()));
    }

    @Test
    public void testIsIntersect1() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("2"), new BigDecimal("7"), new BigDecimal("9"), new BigDecimal("5"));
        assertTrue(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect2() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("1"), new BigDecimal("4"), new BigDecimal("3"), new BigDecimal("2"));
        assertTrue(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect3() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("10"), new BigDecimal("4"), new BigDecimal("2"), new BigDecimal("0"));
        assertFalse(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect4() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("5.38"), new BigDecimal("3.06"), new BigDecimal("7.0901"), new BigDecimal("-3.3542"));
        assertFalse(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect5() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("2"), new BigDecimal("-1.312"), new BigDecimal("2"), new BigDecimal("2.5"));
        assertFalse(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect6() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("2.7"), new BigDecimal("2.2444043940538"), new BigDecimal("2.7"), new BigDecimal("6.6505436797202"));
        assertTrue(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect7() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("10.6334555381729"), new BigDecimal("4.213"), new BigDecimal("0.6155883675046"), new BigDecimal("4.213"));
        assertFalse(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect8() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("4"), new BigDecimal("3"), new BigDecimal("5"), new BigDecimal("5"));
        assertFalse(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect9() throws SizeIsZeroException {
        UserShape test1 = new Line("test3", new BigDecimal("2.7"), new BigDecimal("5.7213123123"), new BigDecimal("2.7"), new BigDecimal("6.6505436797202"));
        assertFalse(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect10() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("1.8"));
        assertTrue(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect11() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("2.7"));
        assertTrue(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect12() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("0.3123"));
        assertFalse(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect13() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("6.59482"), new BigDecimal("8.67821"), new BigDecimal("3"));
        assertTrue(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect14() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("6.59482"), new BigDecimal("8.67821"), new BigDecimal("3.3213123"));
        assertTrue(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect15() throws SizeIsZeroException {
        UserShape test1 = new Circle("test3", new BigDecimal("6.59482"), new BigDecimal("8.67821"), new BigDecimal("1.321312"));
        assertFalse(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect16() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("4"), new BigDecimal("2"), new BigDecimal("6"), new BigDecimal("4"));
        assertTrue(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect17() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("8"), new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("4"));
        assertTrue(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect18() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("8"), new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("2"));
        assertFalse(L1.isIntersect(test1));
    }

    @Test
    public void testIsIntersect19() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("5.95"), new BigDecimal("2.79"), new BigDecimal("4.65"), new BigDecimal("3.94"));
        assertTrue(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect20() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("5.95"), new BigDecimal("2.79"), new BigDecimal("4.65"), new BigDecimal("2.91"));
        assertTrue(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect21() throws SizeIsZeroException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("5.95"), new BigDecimal("2.79"), new BigDecimal("4.65"), new BigDecimal("2.6"));
        assertFalse(L2.isIntersect(test1));
    }

    @Test
    public void testIsIntersect22() throws SizeIsZeroException, EmptyGroupException {
        Rectangle test1 = new Rectangle("test3", new BigDecimal("8"), new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("4"));
        Rectangle test2 = new Rectangle("test4", new BigDecimal("8"), new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("2"));
        Circle test3 = new Circle("test5", new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("2.7"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertTrue(L1.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect23() throws SizeIsZeroException, EmptyGroupException {
        Rectangle test1 = new Rectangle("test3", new BigDecimal("8"), new BigDecimal("2"), new BigDecimal("2"), new BigDecimal("2"));
        Circle test2 = new Circle("test4", new BigDecimal("4"), new BigDecimal("6"), new BigDecimal("0.3123"));
        Line test3 = new Line("test5", new BigDecimal("2"), new BigDecimal("-1.312"), new BigDecimal("2"), new BigDecimal("2.5"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertFalse(L1.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect24() throws SizeIsZeroException, EmptyGroupException {
        Rectangle test1 = new Rectangle("test3", new BigDecimal("5.95"), new BigDecimal("2.79"), new BigDecimal("4.65"), new BigDecimal("2.6"));
        Circle test2 = new Circle("test4", new BigDecimal("6.59482"), new BigDecimal("8.67821"), new BigDecimal("1.321312"));
        Line test3 = new Line("test5", new BigDecimal("2.7"), new BigDecimal("2.2444043940538"), new BigDecimal("2.7"), new BigDecimal("6.6505436797202"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertTrue(L2.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect25() throws SizeIsZeroException, EmptyGroupException {
        Rectangle test1 = new Rectangle("test3", new BigDecimal("5.95"), new BigDecimal("2.79"), new BigDecimal("4.65"), new BigDecimal("2.6"));
        Circle test2 = new Circle("test4", new BigDecimal("6.59482"), new BigDecimal("8.67821"), new BigDecimal("1.321312"));
        Line test3 = new Line("test5", new BigDecimal("4"), new BigDecimal("3"), new BigDecimal("5"), new BigDecimal("5"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertFalse(L2.isIntersect(testGroup));
    }


    @Test
    public void testIsContains1() {
        // On the segment
        assertTrue(L1.isContains(new Point("4", "4")));
    }

    @Test
    public void testIsContains2() {
        // Next to the segment
        assertTrue(L1.isContains(new Point("2", "3.049")));
    }

    @Test
    public void testIsContains3() {
        // Next to the point
        assertTrue(L1.isContains(new Point("1.998", "2.999")));
    }

    @Test
    public void testIsContains4() {
        // Not Contain
        assertFalse(L1.isContains(new Point("4", "2")));
    }

    @Test
    public void testIsContains5() {
        // On the segment
        assertTrue(L2.isContains(new Point("5.2", "5.7")));
    }

    @Test
    public void testIsContains6() {
        // Next to the segment
        assertTrue(L2.isContains(new Point("5.2", "5.7")));
    }

    @Test
    public void testIsContain7() {
        // Next to the point
        assertTrue(L2.isContains(new Point("10.6009", "5.6995")));
    }

    @Test
    public void testIsContain8() {
        // Not contain
        assertFalse(L2.isContains(new Point("11.9", "5.7")));
    }

    @Test
    public void testBoundingBox1() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("2");
        out[1] = new BigDecimal("3");
        out[2] = new BigDecimal("6");
        out[3] = new BigDecimal("3");
        assertArrayEquals(out, L1.boundingBox());
    }

    @Test
    public void testBoundingBox2() {
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("2.7");
        out[1] = new BigDecimal("5.7");
        out[2] = new BigDecimal("7.9");
        out[3] = new BigDecimal("0.0");
        assertArrayEquals(out, L2.boundingBox());
    }

    @Test
    public void testList1() {
        ArrayList<String> out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Line");
        out.add("  x1: " + "2.0");
        out.add("  y1: " + "3.0");
        out.add("  x2: " + "8.0");
        out.add("  y2: " + "6.0");
        assertEquals(out, L1.list());
    }

    @Test
    public void testList2() {
        ArrayList<String> out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Line");
        out.add("  x1: " + "2.7");
        out.add("  y1: " + "5.7");
        out.add("  x2: " + "10.6");
        out.add("  y2: " + "5.7");
        assertEquals(out, L2.list());
    }

    @Test
    public void testListShort1() {
        ArrayList<String> out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Line");
        assertEquals(out, L1.listShort());
    }

    @Test
    public void testListShort2() {
        ArrayList<String> out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Line");
        assertEquals(out, L2.listShort());
    }
}
