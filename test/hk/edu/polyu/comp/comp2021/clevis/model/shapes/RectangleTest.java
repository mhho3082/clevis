package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.EmptyGroupException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.NegativeSizeException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for rectangle
 *
 * @author Mok Ka Kiu
 */
public class RectangleTest {
    Rectangle r1, r2;
    ArrayList<String> out;

    @Before
    public void init() throws SizeIsZeroException, NegativeSizeException {
        r1 = new Rectangle("test1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        r2 = new Rectangle("test2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
    }

    @Test
    public void testList1() {
        out = new ArrayList<>();

        out.add("  Name: " + r1.getName());
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "-4.00");
        out.add("  yTop: " + "-3.00");
        out.add(" width: " + "7.00");
        out.add("height: " + "4.00");

        assertEquals(out, r1.listAll());

    }

    @Test
    public void testCreate2() {
        out = new ArrayList<>();

        out.add("  Name: " + r2.getName());
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "2.70");
        out.add("  yTop: " + "5.70");
        out.add(" width: " + "10.60");
        out.add("height: " + "5.70");

        assertEquals(out, r2.listAll());
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

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException, NegativeSizeException {
        // Width is 0
        new Rectangle("test3", new BigDecimal("2.1"), new BigDecimal("3.5"), new BigDecimal("0"), new BigDecimal("3.4"));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException, NegativeSizeException {
        // Height is 0
        new Rectangle("test3", new BigDecimal("2.1"), new BigDecimal("3.5"), new BigDecimal("3.4"), new BigDecimal("0"));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException3() throws SizeIsZeroException, NegativeSizeException {
        // Width and Height is 0
        new Rectangle("test3", new BigDecimal("2.1"), new BigDecimal("3.5"), new BigDecimal("0"), new BigDecimal("0"));
    }

    @Test(expected = NegativeSizeException.class)
    public void testNegativeSizeException1() throws SizeIsZeroException, NegativeSizeException {
        // Width is negative
        new Rectangle("test3", new BigDecimal("2.1"), new BigDecimal("3.5"), new BigDecimal("-13.2"), new BigDecimal("3.4"));
    }

    @Test(expected = NegativeSizeException.class)
    public void testNegativeSizeException2() throws SizeIsZeroException, NegativeSizeException {
        // Height is negative
        new Rectangle("test3", new BigDecimal("2.1"), new BigDecimal("3.5"), new BigDecimal("3.4"), new BigDecimal("-213"));
    }

    @Test(expected = NegativeSizeException.class)
    public void testNegativeSizeException3() throws SizeIsZeroException, NegativeSizeException {
        // Width and Height is negative
        new Rectangle("test3", new BigDecimal("2.1"), new BigDecimal("3.5"), new BigDecimal("-0.01"), new BigDecimal("-3.21"));
    }


    @Test
    public void testMove1() {
        // Move with positive value
        r1.move(new BigDecimal("12"),new BigDecimal("3.2344"));

        out = new ArrayList<>();

        out.add("  Name: " + r1.getName());
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "8.00");
        out.add("  yTop: " + "0.23");
        out.add(" width: " + "7.00");
        out.add("height: " + "4.00");

        assertEquals(out, r1.listAll());
    }

    @Test
    public void testMove2() {
        // Move with negative value
        r1.move(new BigDecimal("-6.2131"),new BigDecimal("-23"));

        out = new ArrayList<>();

        out.add("  Name: " + r1.getName());
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "-10.21");
        out.add("  yTop: " + "-26.00");
        out.add(" width: " + "7.00");
        out.add("height: " + "4.00");

        assertEquals(out, r1.listAll());
    }

    @Test
    public void testMove3() {
        // Move with positive value
        r2.move(new BigDecimal("0"),new BigDecimal("1.214"));

        out = new ArrayList<>();

        out.add("  Name: " + r2.getName());
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "2.70");
        out.add("  yTop: " + "6.91");
        out.add(" width: " + "10.60");
        out.add("height: " + "5.70");

        assertEquals(out, r2.listAll());
    }

    @Test
    public void testMove4() {
        // Move with positive value
        r2.move(new BigDecimal("-123.3"),new BigDecimal("-21.4"));

        out = new ArrayList<>();

        out.add("  Name: " + r2.getName());
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "-120.60");
        out.add("  yTop: " + "-15.70");
        out.add(" width: " + "10.60");
        out.add("height: " + "5.70");

        assertEquals(out, r2.listAll());
    }

    @Test
    public void testIsContains1() {
        // Inside the rectangle
        assertFalse(r1.isContains(new Point(new BigDecimal("-1"), new BigDecimal("-1"))));
    }

    @Test
    public void testIsContains2() {
        // Touch the line
        assertTrue(r1.isContains(new Point(new BigDecimal("-4"), new BigDecimal("-1"))));
    }

    @Test
    public void testIsContains3() {
        // Outside the rectangle
        assertFalse(r1.isContains(new Point(new BigDecimal("-4"), new BigDecimal("2"))));
    }

    @Test
    public void testIsContains4() {
        // Inside the rectangle
        assertFalse(r2.isContains(new Point(new BigDecimal("7.3"), new BigDecimal("9.2"))));
    }

    @Test
    public void testIsContains5() {
        // Touch the line
        assertTrue(r2.isContains(new Point(new BigDecimal("5.5"), new BigDecimal("5.7"))));
    }

    @Test
    public void testIsContains6() {
        // Outside the rectangle
        assertFalse(r2.isContains(new Point(new BigDecimal("2.7"), new BigDecimal("11.5"))));
    }

    @Test
    public void testIsContain7() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(r1.isContains(new Point(new BigDecimal("-4.049"), new BigDecimal("0"))));
    }

    @Test
    public void testIsContain8() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(r1.isContains(new Point(new BigDecimal("-4.05"), new BigDecimal("0"))));
    }

    @Test
    public void testIsContain9() {
        // The point have minimum distance from outline of the shape is smaller than 0.05
        assertTrue(r2.isContains(new Point(new BigDecimal("5.2"), new BigDecimal("5.66"))));
    }

    @Test
    public void testIsContain10() {
        // The point have minimum distance from outline of the shape is larger than or equal to 0.05
        assertFalse(r2.isContains(new Point(new BigDecimal("5.2"), new BigDecimal("5.65"))));
    }

    @Test
    public void testBoundingBox1() {
        BigDecimal[] test = new BigDecimal[]{
                new BigDecimal("-4"),
                new BigDecimal("-3"),
                new BigDecimal("7"),
                new BigDecimal("4")
        };
        assertArrayEquals(test, r1.boundingBox());
    }

    @Test
    public void testBoundingBox2() {
        BigDecimal[] test = new BigDecimal[]{
                new BigDecimal("2.7"),
                new BigDecimal("5.7"),
                new BigDecimal("10.6"),
                new BigDecimal("5.7")
        };
        assertArrayEquals(test, r2.boundingBox());
    }

    @Test
    public void testIsIntersect1() throws SizeIsZeroException {
        UserShape test = new Line("test3", new BigDecimal("-5"), new BigDecimal("-1"), new BigDecimal("3"), new BigDecimal("1"));
        assertTrue(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect2() throws SizeIsZeroException {
        UserShape test = new Line("test3", new BigDecimal("-5"), new BigDecimal("2"), new BigDecimal("-4"), new BigDecimal("1"));
        assertTrue(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect3() throws SizeIsZeroException {
        UserShape test = new Line("test3", new BigDecimal("-5"), new BigDecimal("-1"), new BigDecimal("4"), new BigDecimal("-1"));
        assertTrue(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect4() throws SizeIsZeroException {
        UserShape test = new Line("test3", new BigDecimal("-4"), new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("2"));
        assertFalse(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect5() throws SizeIsZeroException {
        UserShape test = new Circle("test3", new BigDecimal("-2"), new BigDecimal("-1"), new BigDecimal("3"));
        assertTrue(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect6() throws SizeIsZeroException {
        UserShape test = new Circle("test3", new BigDecimal("5"), new BigDecimal("-1"), new BigDecimal("2"));
        assertTrue(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect7() throws SizeIsZeroException {
        UserShape test = new Circle("test3", new BigDecimal("-3"), new BigDecimal("4"), new BigDecimal("2.2"));
        assertFalse(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect8() throws SizeIsZeroException, NegativeSizeException {
        UserShape test = new Rectangle("test3", new BigDecimal("2"), new BigDecimal("-1"), new BigDecimal("2.2"), new BigDecimal("5"));
        assertTrue(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect9() throws SizeIsZeroException, NegativeSizeException {
        UserShape test = new Rectangle("test3", new BigDecimal("3"), new BigDecimal("-3"), new BigDecimal("2.2"), new BigDecimal("5"));
        assertTrue(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect10() throws SizeIsZeroException, NegativeSizeException {
        UserShape test = new Rectangle("test3", new BigDecimal("-2"), new BigDecimal("-2"), new BigDecimal("2"), new BigDecimal("1"));
        assertFalse(r1.isIntersect(test));
    }

    @Test
    public void testIsIntersect11() throws SizeIsZeroException, EmptyGroupException, NegativeSizeException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("-2"), new BigDecimal("-2"), new BigDecimal("2"), new BigDecimal("1"));
        UserShape test2 = new Circle("test4", new BigDecimal("5"), new BigDecimal("-1"), new BigDecimal("2"));
        UserShape test3 = new Line("test5", new BigDecimal("-5"), new BigDecimal("-1"), new BigDecimal("3"), new BigDecimal("1"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertTrue(r1.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect12() throws SizeIsZeroException, EmptyGroupException, NegativeSizeException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("-2"), new BigDecimal("-2"), new BigDecimal("2"), new BigDecimal("1"));
        UserShape test2 = new Circle("test3", new BigDecimal("-3"), new BigDecimal("4"), new BigDecimal("2.2"));
        UserShape test3 = new Line("test3", new BigDecimal("-4"), new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("2"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertFalse(r1.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect13() throws SizeIsZeroException {
        UserShape test = new Line("test3", new BigDecimal("5.81451"), new BigDecimal("8.6861"), new BigDecimal("4"), new BigDecimal("0"));
        assertTrue(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect14() throws SizeIsZeroException {
        UserShape test = new Line("test3", new BigDecimal("13.3"), new BigDecimal("5.7"), new BigDecimal("8.47733"), new BigDecimal("3.16562"));
        assertTrue(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect15() throws SizeIsZeroException {
        UserShape test = new Line("test3", new BigDecimal("-1.45954"), new BigDecimal("11.13243"), new BigDecimal("9.68967"), new BigDecimal("12.47467"));
        assertFalse(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect16() throws SizeIsZeroException {
        UserShape test = new Circle("test3", new BigDecimal("5.07844"), new BigDecimal("7.27892"), new BigDecimal("5.8"));
        assertTrue(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect17() throws SizeIsZeroException {
        UserShape test = new Circle("test3", new BigDecimal("15.98515"), new BigDecimal("8.53642"), new BigDecimal("2.7"));
        assertTrue(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect18() throws SizeIsZeroException {
        UserShape test = new Circle("test3", new BigDecimal("-0.59297"), new BigDecimal("14.75205"), new BigDecimal("3.12"));
        assertFalse(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect19() throws SizeIsZeroException, NegativeSizeException {
        UserShape test = new Rectangle("test3", new BigDecimal("7.06969"), new BigDecimal("10.07557"), new BigDecimal("2.212"), new BigDecimal("5.321"));
        assertTrue(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect20() throws SizeIsZeroException, NegativeSizeException {
        UserShape test = new Rectangle("test3", new BigDecimal("13.3"), new BigDecimal("5.7"), new BigDecimal("2.212"), new BigDecimal("5.321"));
        assertTrue(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect21() throws SizeIsZeroException, NegativeSizeException {
        UserShape test = new Rectangle("test3", new BigDecimal("6.3654"), new BigDecimal("6.52596"), new BigDecimal("1.23"), new BigDecimal("0.321"));
        assertFalse(r2.isIntersect(test));
    }

    @Test
    public void testIsIntersect22() throws SizeIsZeroException, EmptyGroupException, NegativeSizeException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("13.3"), new BigDecimal("5.7"), new BigDecimal("2.212"), new BigDecimal("5.321"));
        UserShape test2 = new Circle("test4", new BigDecimal("-0.59297"), new BigDecimal("14.75205"), new BigDecimal("3.12"));
        UserShape test3 = new Line("test5", new BigDecimal("13.3"), new BigDecimal("5.7"), new BigDecimal("8.47733"), new BigDecimal("3.16562"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertTrue(r2.isIntersect(testGroup));
    }

    @Test
    public void testIsIntersect23() throws SizeIsZeroException, EmptyGroupException, NegativeSizeException {
        UserShape test1 = new Rectangle("test3", new BigDecimal("6.3654"), new BigDecimal("6.52596"), new BigDecimal("1.23"), new BigDecimal("0.321"));
        UserShape test2 = new Circle("test4", new BigDecimal("-0.59297"), new BigDecimal("14.75205"), new BigDecimal("3.12"));
        UserShape test3 = new Line("test5", new BigDecimal("-1.45954"), new BigDecimal("11.13243"), new BigDecimal("9.68967"), new BigDecimal("12.47467"));

        ArrayList<UserShape> groupShape = new ArrayList<>();
        groupShape.add(test1);
        groupShape.add(test2);
        groupShape.add(test3);

        UserShape testGroup = new Group("test", groupShape);
        assertFalse(r2.isIntersect(testGroup));
    }
}
