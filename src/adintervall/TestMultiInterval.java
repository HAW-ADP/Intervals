/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adintervall;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Fujitsu
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({})
public class TestMultiInterval extends TestCase {

    Interval nai = Interval.NaI, zero = Interval.zeroInterval,
            one = Interval.oneInterval, real = Interval.realInterval;
    Interval mi1_3, mi4_5, mi1_5, mi0_15, mi0_30, mi0_60, mi_30_30, mi0_900;
    Interval i0_1, i0_6, i0_60, i1_2, i1_3, i1_5, i1_6, i2_3, i2_5,
            i3_6, i4_6, i4_8, i6_7, i7_8, i10_13, i10_15, i14_15,
            i10_18, i20_30, i_30_30, i0_900;
    double d0 = 0, d1 = 1, d2 = 2, d3 = 3, d4 = 4, d5 = 5,
            d6 = 6, d7 = 7, d8 = 8, d9 = 9, d10 = 10,
            d11 = 11, d12 = 12, d13 = 13, d14 = 14, d15 = 15, d18 = 18, d20 = 20, d30 = 30,
            d_30 = -30, d60 = 60, d900 = 900;

    @Override
    protected void setUp() throws Exception {
        i0_1 = FactoryInterval.createInterval(d0, d1);
        i0_6 = FactoryInterval.createInterval(d0, d6);
        i0_60 = FactoryInterval.createInterval(d0, d60);
        i1_2 = FactoryInterval.createInterval(d1, d2);
        i4_8 = FactoryInterval.createInterval(d4, d8);
        i6_7 = FactoryInterval.createInterval(d6, d7);
        i7_8 = FactoryInterval.createInterval(d7, d8);
        i10_13 = FactoryInterval.createInterval(d10, d13);
        i10_15 = FactoryInterval.createInterval(d10, d15);
        i10_18 = FactoryInterval.createInterval(d14, d15);
        i20_30 = FactoryInterval.createInterval(d14, d15);

        i1_3 = FactoryInterval.createInterval(d1, d3);
        i2_5 = FactoryInterval.createInterval(d2, d5);
        i2_3 = FactoryInterval.createInterval(d2, d3);
        i1_5 = FactoryInterval.createInterval(d1, d5);
        i1_6 = FactoryInterval.createInterval(d1, d6);
        i3_6 = FactoryInterval.createInterval(d3, d6);
        i4_6 = FactoryInterval.createInterval(d4, d6);
        i0_60 = FactoryInterval.createInterval(d0, d60);
        i_30_30 = FactoryInterval.createInterval(d_30, d30);
        i0_900 = FactoryInterval.createInterval(d0, d900);

        mi1_3 = FactoryInterval.createInterval(i1_2, i6_7, i4_8);
        mi4_5 = FactoryInterval.createInterval(i10_13, i14_15);
        mi1_5 = FactoryInterval.createInterval(mi4_5, mi1_3);
        mi0_15 = FactoryInterval.createInterval(i0_1, i2_3, i10_15);
        mi0_30 = FactoryInterval.createInterval(i0_6, i10_18, i20_30);
        mi0_60 = FactoryInterval.createInterval(i0_60);
        mi_30_30 = FactoryInterval.createInterval(i_30_30);
        mi0_900 = FactoryInterval.createInterval(i0_900);

        super.setUp();
    }

    public void testFactory() {
        assertEquals(i1_5, FactoryInterval.createInterval(i1_3, i2_5));
        assertEquals(i1_5, FactoryInterval.createInterval(i2_5, i1_3));
        assertEquals(i1_5, FactoryInterval.createInterval(i1_5, i2_3));
        assertEquals(i1_5, FactoryInterval.createInterval(i2_3, i1_5));
        assertEquals(i1_3, FactoryInterval.createInterval(i1_2, i1_3));
        assertEquals(i1_3, FactoryInterval.createInterval(i1_3, i1_2));
        assertEquals(i1_3, FactoryInterval.createInterval(i1_3, i2_3));
        assertEquals(i1_3, FactoryInterval.createInterval(i2_3, i1_3));
        assertEquals(i1_6, FactoryInterval.createInterval(i1_2, i2_3, i3_6));
//        Interval i1 = FactoryInterval.createInterval(1, 3);
//        Interval i2 = FactoryInterval.createInterval(2, 7);
//        Interval i3 = FactoryInterval.createInterval(6, 8);
//        Interval inter = FactoryInterval.createInterval(new Interval[]{i1, i2, i3});
//        System.out.println("inter: " + inter);
//        inter = FactoryInterval.createInterval(new Interval[]{i1, i2, i3, Interval.NaI});
//        System.out.println("inter: " + inter);
//        inter = FactoryInterval.createInterval(new Interval[]{Interval.realInterval, i1, Interval.NaI});
//        System.out.println("inter: " + inter);
    }

    public void testGetLowerBound() {
        assertEquals(i1_2.getLowerBound(), mi1_3.getLowerBound());
    }

    public void testGetUpperBound() {
        assertEquals(i4_8.getUpperBound(), mi1_3.getUpperBound());
    }

    public void testLengthValue() {
        assertEquals(d5, mi1_3.length());
        assertFalse(mi1_3.length() == d7);
    }

    public void testContainsIntervals() {
        assertTrue(mi1_3.contains(mi1_3));
        assertFalse(mi1_3.contains(mi4_5));
    }

    public void testContainsInterval() {
        assertFalse(mi1_3.contains(i10_13));
        assertTrue(mi1_3.contains(i6_7));
    }

    public void testContainsValue() {
        assertTrue(mi1_3.contains(d6));
        assertFalse(mi1_3.contains(d10));
    }

    public void testEquals() {
//        TODO
//        assertTrue(mi1_3.contains(d6));
//        assertFalse(mi1_3.contains(d10));
    }

    public void testLess() {
//        TODO
//        assertTrue(mi1_3.contains(d6));
//        assertFalse(mi1_3.contains(d10));
    }

    public void testGreaterEquals() {
//        TODO
//        assertTrue(mi1_3.contains(d6));
//        assertFalse(mi1_3.contains(d10));
    }

    public void testLessEquals() {
//        TODO
//        assertTrue(mi1_3.contains(d6));
//        assertFalse(mi1_3.contains(d10));
    }

    public void testGreater() {
//        TODO
//        assertTrue(mi1_3.contains(d6));
//        assertFalse(mi1_3.contains(d10));
    }

    public void testNotEquals() {
//        TODO
//        assertTrue(mi1_3.contains(d6));
//        assertFalse(mi1_3.contains(d10));
    }

//Carolla begin
    public void test_plus() {
        System.out.println("#-- Testing Plus --#");

        // Beispiele
        assertEquals(mi0_30, mi0_15.plus(mi0_15));
        assertEquals(mi0_60, mi0_30.plus(mi0_30));

        // Kommutativ
        assertEquals(mi0_15.plus(mi0_30), mi0_30.plus(mi0_15));
//        assertEquals(mi0_15.plus(i7_8), mi0_15.plusKom(i7_8));
        assertEquals(mi0_15.plus(3), mi0_15.plusKom(3));

        // Spezialf lle
        assertTrue(mi0_15.equals(mi0_15.plus(zero)));
        assertEquals(real, mi0_15.plus(real));
        assertFalse(nai.equals(mi0_15.plus(nai)));
    }

    public void test_minus() {
        System.out.println("#-- Testing Minus --#");

        // Beispiele
        assertEquals(mi_30_30, mi0_30.minus(mi0_30));

        // Spezialf lle
        assertEquals(mi0_15, mi0_15.minus(zero));
        assertFalse(nai.equals(mi0_15.minus(nai)));
        assertEquals(real, mi0_15.minus(real));
    }

    public void test_multi() {
        System.out.println("#-- Testing Multi --#");

        // Beispiele
        assertEquals(mi0_900, mi0_30.multi(mi0_30));

        // Kommutativ
        assertEquals(mi0_15.multi(mi0_30), mi0_30.multi(mi0_15));
//        assertEquals(mi0_15.multi(i7_8), mi0_15.multiKom(i7_8));
        assertEquals(mi0_15.multi(d3), mi0_15.multiKom(d3));

        // Spezialf lle
        assertEquals(zero, mi0_15.multi(zero));
        assertTrue(mi0_15.equals(mi0_15.multi(one)));
        assertEquals(real, mi0_15.multi(real));
        assertFalse(nai.equals(mi0_15.multi(nai)));
    }
    //Carola end
}
