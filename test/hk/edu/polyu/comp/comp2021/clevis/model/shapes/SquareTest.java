package hk.edu.polyu.comp.comp2021.clevis.model.shapes;

import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.NegativeSizeException;
import hk.edu.polyu.comp.comp2021.clevis.model.exceptions.SizeIsZeroException;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit test for Square
 *
 * @author Mok Ka Kiu
 */
public class SquareTest {
    Square s1, s2;
    ArrayList<String> out;

    @Before
    public void inti() throws SizeIsZeroException, NegativeSizeException {
        s1 = new Square("test1", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("12"));
        s2 = new Square("test2", new BigDecimal("0.123"), new BigDecimal("-123.4312"), new BigDecimal("16.235"));
    }

    @Test(expected = SizeIsZeroException.class)
    public void testSizeIsZeroException1() throws SizeIsZeroException, NegativeSizeException {
        // Length is 0
        new Square("test3", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("0"));
    }

    @Test(expected = NegativeSizeException.class)
    public void testSizeIsZeroException2() throws SizeIsZeroException, NegativeSizeException {
        // Length is negative
        new Square("test3", new BigDecimal("1"), new BigDecimal("-7"), new BigDecimal("-13.213"));
    }

    @Test
    public void testList1() {
        out = new ArrayList<>();

        out.add("  Name: " + s1.getName());
        out.add("  Type: " + "Square");
        out.add(" xLeft: " + "1.00");
        out.add("  yTop: " + "-7.00");
        out.add("length: " + "12.00");

        assertEquals(out, s1.listAll());

    }

    @Test
    public void testList2() {
        out = new ArrayList<>();

        out.add("  Name: " + s2.getName());
        out.add("  Type: " + "Square");
        out.add(" xLeft: " + "0.12");
        out.add("  yTop: " + "-123.43");
        out.add("length: " + "16.24");

        assertEquals(out, s2.listAll());

    }

    @Test
    public void testListShort1() {
        out = new ArrayList<>();
        out.add("Name: " + "test1");
        out.add("Type: Square");
        assertEquals(out, s1.listShort());
    }

    @Test
    public void testListShort2() {
        out = new ArrayList<>();
        out.add("Name: " + "test2");
        out.add("Type: Square");
        assertEquals(out, s2.listShort());
    }

}
