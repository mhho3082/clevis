package hk.edu.polyu.comp.comp2021.clevis.model;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.*;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for Clevis
 *
 * @author Mok Ka Kiu
 */
public class ClevisTest {
    Clevis test;

    @Before
    public void init() {
        test = new Clevis();
    }

    @Test
    public void testRectangle() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addRectangle("r2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        BigDecimal[] out = new BigDecimal[]{
                new BigDecimal("-4"),
                new BigDecimal("-3"),
                new BigDecimal("7"),
                new BigDecimal("4")
        };
        assertArrayEquals(out, test.boundingBox("r1"));
        out = new BigDecimal[]{
                new BigDecimal("2.7"),
                new BigDecimal("5.7"),
                new BigDecimal("10.6"),
                new BigDecimal("5.7")
        };
        assertArrayEquals(out, test.boundingBox("r2"));
    }

    @Test
    public void testPickRectangle1() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.pick(new BigDecimal("-4"), new BigDecimal("-1"));
    }

    @Test(expected = NoShapeContainsPointException.class)
    public void testPickRectangle2() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.pick(new BigDecimal("-4"), new BigDecimal("2"));
    }

    @Test
    public void testPickRectangle3() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addRectangle("r2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.pick(new BigDecimal("5.5"), new BigDecimal("5.7"));
    }

    @Test(expected = NoShapeContainsPointException.class)
    public void testPickRectangle4() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addRectangle("r2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.pick(new BigDecimal("2.7"), new BigDecimal("11.5"));
    }

    @Test
    public void testIntersectRectangle1() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addRectangle("test3", new BigDecimal("3"), new BigDecimal("-3"), new BigDecimal("2.2"), new BigDecimal("5"));
        assertTrue(test.intersect("r1","test3"));
    }

    @Test
    public void testIntersectRectangle2() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addRectangle("r2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.addLine("test3", new BigDecimal("-1.45954"), new BigDecimal("11.13243"), new BigDecimal("9.68967"), new BigDecimal("12.47467"));
        assertFalse(test.intersect("r2","test3"));
    }

    @Test
    public void testMoveRectangle1() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.move("r1", new BigDecimal("-6.2131"), new BigDecimal("-23"));
        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "r1");
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "-10.21");
        out.add("  yTop: " + "-26.00");
        out.add(" width: " + "7.00");
        out.add("height: " + "4.00");

        assertEquals(out, test.list("r1"));
    }

    @Test
    public void testMoveRectangle2() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.move("r1", new BigDecimal("12"), new BigDecimal("3.2344"));

        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "r1");
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "8.00");
        out.add("  yTop: " + "0.23");
        out.add(" width: " + "7.00");
        out.add("height: " + "4.00");

        assertEquals(out, test.list("r1"));
    }

    @Test
    public void testMoveRectangle3() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addRectangle("r2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.move("r2", new BigDecimal("0"), new BigDecimal("1.214"));

        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "r2");
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "2.70");
        out.add("  yTop: " + "6.91");
        out.add(" width: " + "10.60");
        out.add("height: " + "5.70");

        assertEquals(out, test.list("r2"));
    }

    @Test
    public void testMoveRectangle4() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addRectangle("r2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.move("r2", new BigDecimal("-123.3"), new BigDecimal("-21.4"));

        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "r2");
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "-120.60");
        out.add("  yTop: " + "-15.70");
        out.add(" width: " + "10.60");
        out.add("height: " + "5.70");

        assertEquals(out, test.list("r2"));
    }

    @Test
    public void testLine() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("2");
        out[1] = new BigDecimal("3");
        out[2] = new BigDecimal("6");
        out[3] = new BigDecimal("3");
        assertArrayEquals(out, test.boundingBox("l1"));

        out = new BigDecimal[4];

        out[0] = new BigDecimal("2.7");
        out[1] = new BigDecimal("5.7");
        out[2] = new BigDecimal("7.9");
        out[3] = new BigDecimal("0.0");
        assertArrayEquals(out, test.boundingBox("l2"));
    }

    @Test
    public void testIntersectLine1() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.addLine("test3", new BigDecimal("2"), new BigDecimal("7"), new BigDecimal("9"), new BigDecimal("5"));
        assertTrue(test.intersect("l1", "test3"));
    }

    @Test
    public void testIntersectLine2() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.addCircle("test3", new BigDecimal("6.59482"), new BigDecimal("8.67821"), new BigDecimal("1.321312"));
        assertFalse(test.intersect("l2", "test3"));
    }

    @Test
    public void testPickLine1() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addLine("l1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.pick(new BigDecimal("2"), new BigDecimal("3.049"));
    }

    @Test(expected = NoShapeContainsPointException.class)
    public void testPickLine2() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addLine("l1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.pick(new BigDecimal("4"), new BigDecimal("2"));
    }

    @Test
    public void testPickLine3() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.pick(new BigDecimal("5.2"), new BigDecimal("5.7"));
    }

    @Test(expected = NoShapeContainsPointException.class)
    public void testPickLine4() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.pick(new BigDecimal("11.9"), new BigDecimal("5.7"));
    }

    @Test
    public void testMoveLine1() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.move("l1", new BigDecimal("10.2"), new BigDecimal("3"));

        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + "l1");
        out.add("Type: Line");
        out.add("  x1: " + "12.20");
        out.add("  y1: " + "6.00");
        out.add("  x2: " + "18.20");
        out.add("  y2: " + "9.00");

        assertEquals(out, test.list("l1"));
    }

    @Test
    public void testMoveLine2() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.move("l1", new BigDecimal("-6"), new BigDecimal("-5.9"));

        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + "l1");
        out.add("Type: Line");
        out.add("  x1: " + "-4.00");
        out.add("  y1: " + "-2.90");
        out.add("  x2: " + "2.00");
        out.add("  y2: " + "0.10");

        assertEquals(out, test.list("l1"));
    }

    @Test
    public void testMoveLine3() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.move("l2", new BigDecimal("6.2"), new BigDecimal("4"));

        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + "l2");
        out.add("Type: Line");
        out.add("  x1: " + "8.90");
        out.add("  y1: " + "9.70");
        out.add("  x2: " + "16.80");
        out.add("  y2: " + "9.70");

        assertEquals(out, test.list("l2"));
    }

    @Test
    public void testMoveLine4() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.move("l2", new BigDecimal("-12"), new BigDecimal("-23.613"));

        ArrayList<String> out = new ArrayList<>();

        out.add("Name: " + "l2");
        out.add("Type: Line");
        out.add("  x1: " + "-9.30");
        out.add("  y1: " + "-17.91");
        out.add("  x2: " + "-1.40");
        out.add("  y2: " + "-17.91");

        assertEquals(out, test.list("l2"));
    }

    @Test
    public void testCircle() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.addCircle("c2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));

        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("-8");
        out[1] = new BigDecimal("0");
        out[2] = new BigDecimal("10");
        out[3] = new BigDecimal("10");
        assertArrayEquals(out, test.boundingBox("c1"));

        out = new BigDecimal[4];

        out[0] = new BigDecimal("-6.0");
        out[1] = new BigDecimal("-14.4");
        out[2] = new BigDecimal("17.4");
        out[3] = new BigDecimal("17.4");
        assertArrayEquals(out, test.boundingBox("c2"));
    }

    @Test
    public void testIntersectCircle1() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.addCircle("test3", new BigDecimal("6"), new BigDecimal("6"), new BigDecimal("8.2"));
        assertTrue(test.intersect("c1", "test3"));
    }

    @Test
    public void testIntersectCircle2() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException, NegativeSizeException {
        test.addCircle("c2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
        test.addRectangle("test3", new BigDecimal("5.97838"), new BigDecimal("3.01703"), new BigDecimal("10.1"), new BigDecimal("4"));
        assertFalse(test.intersect("c2", "test3"));
    }

    @Test
    public void testPickCircle1() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.pick(new BigDecimal("0"), new BigDecimal("9"));
    }

    @Test (expected = NoShapeContainsPointException.class)
    public void testPickCircle2() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.pick(new BigDecimal("-10"), new BigDecimal("3"));
    }
    @Test
    public void testPickCircle3() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addCircle("c2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
        test.pick(new BigDecimal("-4.821"), new BigDecimal("-1.327"));
    }

    @Test (expected = NoShapeContainsPointException.class)
    public void testPickCircle4() throws SizeIsZeroException, DuplicateShapeNameException, NoShapeContainsPointException {
        test.addCircle("c2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
        test.pick(new BigDecimal("-6.321"), new BigDecimal("-3.231"));
    }

    @Test
    public void testMoveCircle1() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.move("c1", new BigDecimal("29"), new BigDecimal("3"));

        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + "c1");
        out.add("   Type: Circle");
        out.add("xCenter: " + "26.00");
        out.add("yCenter: " + "8.00");
        out.add(" radius: " + "5.00");

        assertEquals(out, test.list("c1"));
    }

    @Test
    public void testMoveCircle2() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.move("c1", new BigDecimal("-10"), new BigDecimal("-10"));

        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + "c1");
        out.add("   Type: Circle");
        out.add("xCenter: " + "-13.00");
        out.add("yCenter: " + "-5.00");
        out.add(" radius: " + "5.00");

        assertEquals(out, test.list("c1"));
    }

    @Test
    public void testMoveCircle3() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addCircle("c2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
        test.move("c2", new BigDecimal("5.3"), new BigDecimal("10.2"));

        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + "c2");
        out.add("   Type: Circle");
        out.add("xCenter: " + "8.00");
        out.add("yCenter: " + "4.50");
        out.add(" radius: " + "8.70");

        assertEquals(out, test.list("c2"));
    }

    @Test
    public void testMoveCircle4() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addCircle("c2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
        test.move("c2", new BigDecimal("-2.3"), new BigDecimal("-9.4"));

        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + "c2");
        out.add("   Type: Circle");
        out.add("xCenter: " + "0.40");
        out.add("yCenter: " + "-15.10");
        out.add(" radius: " + "8.70");

        assertEquals(out, test.list("c2"));
    }

    @Test
    public void testSquare() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addSquare("s1", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("12"));
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));

        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("1");
        out[1] = new BigDecimal("-7");
        out[2] = new BigDecimal("12");
        out[3] = new BigDecimal("12");
        assertArrayEquals(out, test.boundingBox("s1"));

        out = new BigDecimal[4];

        out[0] = new BigDecimal("0.123");
        out[1] = new BigDecimal("-123.4312");
        out[2] = new BigDecimal("16.2350");
        out[3] = new BigDecimal("16.2350");
        assertArrayEquals(out, test.boundingBox("s2"));
    }

    @Test
    public void testMoveSquare1() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addSquare("s1", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("12"));
        test.move("s1", new BigDecimal("12"), new BigDecimal("3.2344"));

        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "s1");
        out.add("  Type: " + "Square");
        out.add(" xLeft: " + "13.00");
        out.add("  yTop: " + "-3.77");
        out.add("length: " + "12.00");

        assertEquals(out, test.list("s1"));
    }

    @Test
    public void testMoveSquare2() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addSquare("s1", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("12"));
        test.move("s1", new BigDecimal("-6.2131"), new BigDecimal("-23"));

        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "s1");
        out.add("  Type: " + "Square");
        out.add(" xLeft: " + "-5.21");
        out.add("  yTop: " + "-30.00");
        out.add("length: " + "12.00");

        assertEquals(out, test.list("s1"));
    }

    @Test
    public void testMoveSquare3() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
        test.move("s2", new BigDecimal("0"), new BigDecimal("1.214"));

        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "s2");
        out.add("  Type: " + "Square");
        out.add(" xLeft: " + "0.12");
        out.add("  yTop: " + "-122.22");
        out.add("length: " + "16.24");

        assertEquals(out, test.list("s2"));
    }

    @Test
    public void testMoveSquare4() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
        test.move("s2", new BigDecimal("-123.3"), new BigDecimal("-21.4"));

        ArrayList<String> out = new ArrayList<>();

        out.add("  Name: " + "s2");
        out.add("  Type: " + "Square");
        out.add(" xLeft: " + "-123.18");
        out.add("  yTop: " + "-144.83");
        out.add("length: " + "16.24");

        assertEquals(out, test.list("s2"));
    }

    @Test(expected = DuplicateShapeNameException.class)
    public void testDuplicateShapeNameException() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException {
        test.addSquare("test1", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("12"));
        test.addSquare("test1", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
    }

    @Test
    public void testGroup() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
        ArrayList<String> names = new ArrayList<>();
        names.add("r1");
        names.add("l2");
        names.add("c1");
        names.add("s2");
        test.group("g1", names);

        BigDecimal[] out = new BigDecimal[4];

        out[0] = new BigDecimal("-8");
        out[1] = new BigDecimal("-123.4312");
        out[2] = new BigDecimal("24.3580");
        out[3] = new BigDecimal("133.4312");
        assertArrayEquals(out, test.boundingBox("g1"));
    }

    @Test
    public void testMoveGroup() throws SizeIsZeroException, DuplicateShapeNameException, NegativeSizeException, ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException {
        test.addLine("test1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.addCircle("test2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));
        test.addRectangle("test3", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addSquare("test4", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("12"));
        ArrayList<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");
        names.add("test3");
        names.add("test4");
        test.group("g1", names);

        test.move("g1", new BigDecimal("1.5"), new BigDecimal("-1"));

        ArrayList<String> out = new ArrayList<>();

        out.add("Name: g1");
        out.add("Type: Group");
        out.add("");

        out.add(Config.INDENT + "Name: " + "test4");
        out.add(Config.INDENT + "Type: " + "Square");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test3");
        out.add(Config.INDENT + "Type: " + "Rectangle");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test2");
        out.add(Config.INDENT + "Type: Circle");

        out.add(Config.INDENT);

        out.add(Config.INDENT + "Name: " + "test1");
        out.add(Config.INDENT + "Type: Line");


        assertEquals(out, test.list("g1"));
    }

    @Test(expected = ShapeNotFoundException.class)
    public void testShapeNotFoundException() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
        ArrayList<String> names = new ArrayList<>();
        names.add("r1");
        names.add("l1");
        names.add("c1");
        names.add("s2");
        test.group("g1", names);
    }

    @Test(expected = EmptyGroupException.class)
    public void testEmptyGroupException() throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException {
        ArrayList<String> names = new ArrayList<>();
        test.group("g1", names);
    }

    @Test(expected = ShapeInsideGroupException.class)
    public void testShapeInsideGroupException() throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException, NegativeSizeException, SizeIsZeroException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
        ArrayList<String> names = new ArrayList<>();
        names.add("r1");
        names.add("l2");
        names.add("c1");
        test.group("g1", names);
        names = new ArrayList<>();
        names.add("r1");
        names.add("s2");
        test.group("g2", names);
    }

    @Test(expected = DuplicateShapeNameException.class)
    public void testDuplicateShapeNameException2() throws ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException, DuplicateShapeNameException, NegativeSizeException, SizeIsZeroException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
        ArrayList<String> names = new ArrayList<>();
        names.add("l2");
        names.add("c1");
        test.group("g1", names);
        names = new ArrayList<>();
        names.add("r1");
        names.add("s2");
        test.group("g1", names);
    }

    @Test
    public void testUngroup() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, EmptyGroupException, ShapeNotFoundException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.addCircle("c1", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));
        test.addSquare("s2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
        ArrayList<String> names = new ArrayList<>();
        names.add("r1");
        names.add("l2");
        names.add("c1");
        names.add("s2");
        test.group("g1", names);
        test.ungroup("g1");
    }

    @Test
    public void testListAll() throws NegativeSizeException, SizeIsZeroException, DuplicateShapeNameException {
        test.addRectangle("r1", new BigDecimal("-4"), new BigDecimal("-3"), new BigDecimal("7"), new BigDecimal("4"));
        test.addCircle("c2", new BigDecimal("2.7"), new BigDecimal("-5.7"), new BigDecimal("8.7"));

        ArrayList<String> out = new ArrayList<>();

        out.add("   Name: " + "c2");
        out.add("   Type: Circle");
        out.add("xCenter: " + "2.70");
        out.add("yCenter: " + "-5.70");
        out.add(" radius: " + "8.70");

        out.add("");

        out.add("  Name: " + "r1");
        out.add("  Type: " + "Rectangle");
        out.add(" xLeft: " + "-4.00");
        out.add("  yTop: " + "-3.00");
        out.add(" width: " + "7.00");
        out.add("height: " + "4.00");

        assertEquals(out, test.listAll());
    }

    @Test
    public void testRestore1() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.remove("l2");
        assertEquals(0, test.findInBinIndex("l2"));
        test.restore("l2");
        assertEquals(0, test.findIndex("l2"));
    }

    @Test(expected = ShapeNotFoundException.class)
    public void testShapeNotFoundException1() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.remove("l2");
        test.restore("l1");
    }

    @Test(expected = ShapeNotFoundException.class)
    public void testShapeNotFoundException2() throws SizeIsZeroException, DuplicateShapeNameException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.find("l1");
    }

    @Test(expected = ShapeNotFoundException.class)
    public void testShapeNotFoundException3() throws SizeIsZeroException, DuplicateShapeNameException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.findIndex("l1");
    }

    @Test(expected = ShapeNotFoundException.class)
    public void testShapeNotFoundException4() throws SizeIsZeroException, DuplicateShapeNameException, ShapeInsideGroupException, ShapeNotFoundException {
        test.addLine("l2", new BigDecimal("2.7"), new BigDecimal("5.7"), new BigDecimal("10.6"), new BigDecimal("5.7"));
        test.remove("l2");
        test.findInBinIndex("l1");
    }

    // ----------------------------------------------------------------------
    // Some exceptions don't get tested, test it here

    @Test
    public void testDuplicateShapeNameExceptionName() {
        DuplicateShapeNameException test = new DuplicateShapeNameException("test");
        assertEquals("test", test.getName());
    }

    @Test
    public void testShapeInsideGroupExceptionShapeName() {
        ShapeInsideGroupException test = new ShapeInsideGroupException("test");
        assertEquals("test", test.getShapeName());
    }

    @Test
    public void testShapeNotFoundExceptionName() {
        ShapeNotFoundException test = new ShapeNotFoundException("test");
        assertEquals("test", test.getName());
    }

    @Test
    public void testGetPlot() throws SizeIsZeroException, DuplicateShapeNameException {
        test.addLine("test1", new BigDecimal("2"), new BigDecimal("3"), new BigDecimal("8"), new BigDecimal("6"));
        test.addCircle("test2", new BigDecimal("-3"), new BigDecimal("5"), new BigDecimal("5"));

        ArrayList<double[]> out = test.getPlot();
        assertEquals(-8, out.get(0)[0], 0.0001);
        assertEquals(0, out.get(0)[1], 0.0001);
        assertEquals(10, out.get(0)[2], 0.0001);
        assertEquals(10, out.get(0)[3], 0.0001);
        assertEquals(1, out.get(0)[4], 0.0001);

        assertEquals(2, out.get(1)[0], 0.0001);
        assertEquals(3, out.get(1)[1], 0.0001);
        assertEquals(8, out.get(1)[2], 0.0001);
        assertEquals(6, out.get(1)[3], 0.0001);
        assertEquals(0, out.get(1)[4], 0.0001);
    }

}
