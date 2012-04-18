/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package intervall;

import adintervall.FactoryInterval;
import adintervall.Interval;
import java.util.HashSet;
import java.util.Set;
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
	Set<Interval> iSet;
    Interval nai = Interval.NaI, zero = Interval.zeroInterval,
            one = Interval.oneInterval, real = Interval.realInterval;
    Interval mi1_3, mi4_5, mi1_5, mi0_15, mi0_30, mi0_60, mi_30_30, mi0_900;
    Interval i0_1, i0_6, i0_60, i1_2, i1_3, i1_5, i1_6, i2_3, i2_5,
            i3_6, i4_6, i4_8, i6_7, i7_8, i10_13, i10_15, i14_15,
            i10_18, i20_30, i_30_30, i0_900;

    @Override
    protected void setUp() throws Exception {
        i0_1 = FactoryInterval.createInterval(0d, 1d);
        i0_6 = FactoryInterval.createInterval(0d, 6d);
        i0_60 = FactoryInterval.createInterval(0d, 60d);
        i1_2 = FactoryInterval.createInterval(1d, 2d);
        i4_8 = FactoryInterval.createInterval(4d, 8d);
        i6_7 = FactoryInterval.createInterval(6d, 7d);
        i7_8 = FactoryInterval.createInterval(7d, 8d);
        i10_13 = FactoryInterval.createInterval(10d, 13d);
        i10_15 = FactoryInterval.createInterval(10d, 15d);
        i10_18 = FactoryInterval.createInterval(10d, 18d);
        i14_15 = FactoryInterval.createInterval(14d, 15d);
        i20_30 = FactoryInterval.createInterval(20d, 30d);

        i1_3 = FactoryInterval.createInterval(1d, 3d);
        i2_5 = FactoryInterval.createInterval(2d, 5d);
        i2_3 = FactoryInterval.createInterval(2d, 3d);
        i1_5 = FactoryInterval.createInterval(1d, 5d);
        i1_6 = FactoryInterval.createInterval(1d, 6d);
        i3_6 = FactoryInterval.createInterval(3d, 6d);
        i4_6 = FactoryInterval.createInterval(4d, 6d);
        i0_60 = FactoryInterval.createInterval(0d, 60d);
        i_30_30 = FactoryInterval.createInterval(-30d, 30d);
        i0_900 = FactoryInterval.createInterval(0d, 900d);

        mi1_3 = FactoryInterval.createInterval(i1_2, i6_7, i4_8);
        mi4_5 = FactoryInterval.createInterval(i10_13, i14_15);
        mi1_5 = FactoryInterval.createInterval(mi4_5, mi1_3);
        mi0_15 = FactoryInterval.createInterval(i0_1, i2_3, i10_15);
        mi0_30 = FactoryInterval.createInterval(i0_6, i10_18, i20_30);
        mi0_60 = FactoryInterval.createInterval(i0_60);
        mi_30_30 = FactoryInterval.createInterval(i_30_30);
        mi0_900 = FactoryInterval.createInterval(i0_900);

        iSet = new HashSet<Interval>();
        iSet.add(i0_1);
        iSet.add(i0_6);

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
        
        assertEquals(mi1_3, FactoryInterval.createInterval(Interval.oneInterval, mi1_3));
 		
        assertEquals(mi0_15, FactoryInterval.createInterval(Interval.zeroInterval, mi0_15));
 	 	
        assertEquals(Interval.realInterval, FactoryInterval.createInterval(Interval.realInterval, mi1_3));
 	 	
        assertEquals(mi1_3, FactoryInterval.createInterval(Interval.emptyInterval, mi1_3));
 	 	
        assertTrue(Interval.NaI == FactoryInterval.createInterval(Interval.NaI, mi1_3));
 	 	
        assertEquals(i0_6, FactoryInterval.createInterval(iSet));

    }

    public void testGetLowerBound() {
        assertEquals(i1_2.getLowerBound(), mi1_3.getLowerBound());
        assertEquals(Interval.emptyInterval.getLowerBound(), Double.NaN);
    }

    public void testGetUpperBound() {
        assertEquals(i4_8.getUpperBound(), mi1_3.getUpperBound());
        assertEquals(Interval.emptyInterval.getUpperBound(), Double.NaN);
    }

    public void testLengthValue() {
        assertEquals(5d, mi1_3.length());
        assertFalse(mi1_3.length() == 7d);
    }

    public void testContainsIntervals() {
        assertTrue(mi1_3.contains(mi1_3));
        assertFalse(mi1_3.contains(mi4_5));
        assertTrue(mi1_5.contains(mi4_5));
    }

    public void testContainsInterval() {
        assertFalse(mi1_3.contains(i10_13));
        assertTrue(mi1_3.contains(i4_6));
    }

    public void testContainsValue() {
        assertTrue(mi1_3.contains(6d));
        assertFalse(mi1_3.contains(10d));
    }
    
    public void testEquals() {
        Interval test_30_30_single = FactoryInterval.createInterval(-30d, 30d);
        Interval test_30_30_multi = FactoryInterval.createInterval(test_30_30_single); 
        assertTrue(mi_30_30.equals(test_30_30_multi));
        assertFalse(mi_30_30.equals(mi1_3));
    }

//Carolla begin
    public void test_plus() {
        System.out.println("#-- Testing Plus --#");

        // Beispiele
        assertEquals(mi0_30, mi0_15.plus(mi0_15));
        System.out.println("mi0_15 +  mi0_15  = " + mi0_30);
        assertEquals(mi0_60, mi0_30.plus(mi0_30));

        // Kommutativ
        assertEquals(mi0_15.plus(mi0_30), mi0_30.plus(mi0_15));
        assertEquals(mi0_15.plus(i7_8), i7_8.plus(mi0_15));
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
        System.out.println("mi0_30 -  mi0_30  = " + mi_30_30);

        // Spezialf lle
        assertEquals(mi0_15, mi0_15.minus(zero));
        assertFalse(nai.equals(mi0_15.minus(nai)));
        assertEquals(real, mi0_15.minus(real));
    }

    public void test_multi() {
        System.out.println("#-- Testing Multi --#");

        // Beispiele
        assertEquals(mi0_900, mi0_30.multi(mi0_30));
        System.out.println("mi0_30 +  mi0_30  = " + mi0_900);

        // Kommutativ
        assertEquals(mi0_15.multi(mi0_30), mi0_30.multi(mi0_15));
        assertEquals(mi0_15.multi(3d), mi0_15.multiKom(3d));

        // Spezialf lle
        assertEquals(zero, mi0_15.multi(zero));
        assertTrue(mi0_15.equals(mi0_15.multi(one)));
        assertFalse(nai.equals(mi0_15.multi(nai)));
    }    //Carola end
    
public void test_union() {
    	
		System.out.println("#-- Testing union --#");
	
    	//NaI is always NaI
		System.out.println("| multiinterval.union(NaI) = NaI");
		assertTrue(mi0_15.union(nai) == nai);
		System.out.println("|--> mi0_15.union(nai) = "+mi0_15.union(nai));
    	assertTrue(nai.union(mi0_15) == nai);
    	System.out.println("|--> nai.union(mi0_15) = "+nai.union(mi0_15));
    	
    	//union(m1, realInterval) = realInterval
    	System.out.println("| union(Multiinterval, realInterval) = realInterval");
    	assertEquals(real, mi0_15.union(real));
    	System.out.println("|--> mi0_15.union(real) = "+mi0_15.union(real));
    	
    	//union(union(m1,m2),m3) = union(m1,union(m2,m3))    	
    	System.out.println("| union(union(m1,m2),m3) = union(m1,union(m2,m3))");
    	assertEquals((mi1_3.union(mi1_5)).union(mi4_5),mi1_3.union(mi1_5.union(mi4_5)));
    	System.out.println("|--> (mi1_3.union(mi1_5)).union(mi4_5) = "+(mi1_3.union(mi1_5)).union(mi4_5));
    	System.out.println("|-->  mi1_3.union(mi1_5.union(mi4_5))  = "+mi1_3.union(mi1_5.union(mi4_5)));
    	
    	
    	//union(m1,m2) = union(m2,m1)
    	System.out.println("| union(m1,m2) = union(m2,m1)");
    	assertEquals(mi1_3.union(mi1_5), mi1_5.union(mi1_3));
    	System.out.println("|--> mi1_3.union(mi1_5) = "+mi1_3.union(mi1_5));
    	System.out.println("|--> mi1_5.union(mi1_3) = "+mi1_5.union(mi1_3));
    	
    	//union(m1,m1) = m1
    	System.out.println("| union(m1,m1) = m1");
    	assertEquals(mi1_3, mi1_3.union(mi1_3));
    	System.out.println("|--> mi1_3.union(mi1_3) = "+mi1_3.union(mi1_3));
    	System.out.println("|--> mi1_3              = "+mi1_3);
    }
    
    public void test_intersection() {
    	
    	System.out.println("#-- Testing intersection --#");
    	
    	//intersection(NaI, m1) = intersection(m1, NaI) = NaI
    	System.out.println("| intersection(NaI, m1) = intersection(m1, NaI) = NaI");
    	assertTrue(nai == mi1_3.intersection(nai));
    	System.out.println("|--> mi1_3.intersection(nai) = "+mi1_3.intersection(nai));
    	assertTrue(nai == nai.intersection(mi1_3));
    	System.out.println("|--> nai.intersection(mi1_3) = "+nai.intersection(mi1_3));
    	
    	//intersection(real, m1) = intersection(m1, real) = m1
    	System.out.println("| intersection(real, m1) = intersection(m1, real) = m1");
    	assertEquals(mi1_3, mi1_3.intersection(real));
    	System.out.println("|--> mi1_3.intersection(real) = "+mi1_3.intersection(real));
    	assertEquals(mi1_3, real.intersection(mi1_3));
    	System.out.println("|--> real.intersection(mi1_3) = "+real.intersection(mi1_3));
    	
    	//intersection(m1,intersection(m2,m3)) = intersection(intersection(m1,m2),m3)
    	System.out.println("| intersection(m1,intersection(m2,m3)) = intersection(intersection(m1,m2),m3)");
    	assertEquals(mi1_3.intersection(mi1_5.intersection(mi4_5)), (mi1_3.intersection(mi1_5)).intersection(mi4_5));
    	System.out.println("|--> mi1_3.intersection(mi1_5.intersection(mi4_5))   = "+mi1_3.intersection(mi1_5.intersection(mi4_5)));
    	System.out.println("|--> (mi1_3.intersection(mi1_5)).intersection(mi4_5) = "+(mi1_3.intersection(mi1_5)).intersection(mi4_5));
    	
    	//intersection(m1,m2) = intersection(m2,m1)
    	System.out.println("| intersection(m1,m2) = intersection(m2,m1)");
    	assertEquals(mi1_3.intersection(mi1_5), mi1_5.intersection(mi1_3));
    	System.out.println("|--> mi1_3.intersection(mi1_5) = "+mi1_3.intersection(mi1_5));
    	System.out.println("|--> mi1_5.intersection(mi1_3) = "+mi1_5.intersection(mi1_3));
    	
    	//intersection(m1,m1) = m1
    	System.out.println("| intersection(m1,m1) = m1");
    	assertEquals(mi1_3, mi1_3.intersection(mi1_3));
    	System.out.println("|--> mi1_3.intersection(mi1_3) = "+mi1_3.intersection(mi1_3));
    	System.out.println("|--> mi1_3                     = "+mi1_3);
    }
    
    
    public void test_intersectionUnion() {
    	
    	System.out.println("#-- Testing union with intersection --#");
    	
    	//union(m1, intersection(m1,m2)) = m1
    	System.out.println("| union(m1, intersection(m1,m2)) = m1");
    	assertEquals(mi1_3, mi1_3.union(mi1_3.intersection(mi1_5)));
    	System.out.println("|--> mi1_3.union(mi1_3.intersection(mi1_5)) = "+mi1_3.union(mi1_3.intersection(mi1_5)));
    	System.out.println("|--> mi1_3                                  = "+mi1_3);
    	
    	//intersection(union(m1,m3), union(m2,m3)) = union(m3, intersection(m1,m2))
    	System.out.println("| intersection(union(m1,m3), union(m2,m3)) = union(m3, intersection(m1,m2))");
    	assertEquals(mi1_3.union(mi1_5).intersection(mi1_5.union(mi4_5)), mi4_5.union(mi1_3.intersection(mi1_5)));
    	System.out.println("|--> mi1_3.union(mi1_5).intersection(mi1_5.union(mi4_5)) = "+mi1_3.union(mi1_5).intersection(mi1_5.union(mi4_5)));
    	System.out.println("|--> mi4_5.union(mi1_3.intersection(mi1_5))              = "+mi4_5.union(mi1_3.intersection(mi1_5)));
    	
    	//intersection(m1, union(m1,m2)) = m1
    	System.out.println("| intersection(m1, union(m1,m2)) = m1");
    	assertEquals(mi1_3, mi1_3.intersection(mi1_3.union(mi1_5)));
    	System.out.println("|--> mi1_3.intersection(mi1_3.union(mi1_5)) = "+mi1_3.intersection(mi1_3.union(mi1_5)));
    	System.out.println("|--> mi1_3                                  = "+mi1_3);
    }
}
