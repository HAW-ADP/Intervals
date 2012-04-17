package adintervall;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class ADIntervalTest {

	Interval nai, one, zero, real, iPos, iNeg, iNegPos, Pos, i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13;
    Intervals m1, m2, m3, m4; // supposed to be Intervals
	double nan, d0, d1, d2, d3, d4;
    Random rand;
	
	public ADIntervalTest() {
		nai = Interval.NaI;
        zero = Interval.zeroInterval;
        one = Interval.oneInterval;
        real = Interval.realInterval;
        rand = new Random();
        //iPos = Int(1d, 5d);
        //iNeg = Int(-5d, -1d);
        //iNegPos = Int(-5d, 5d);

        i1 = FactoryInterval.createInterval(1d, 2d);
        i2 = FactoryInterval.createInterval(3d, 4d);
        i3 = FactoryInterval.createInterval(1d, 3d);
        i4 = FactoryInterval.createInterval(4d, 5d);
        i5 = FactoryInterval.createInterval(1d, 5d);
        i6 = FactoryInterval.createInterval(2d, 4d);

        i7 = FactoryInterval.createInterval(0d, 2d);
        i8 = FactoryInterval.createInterval(0d, 4d);
        i9 = FactoryInterval.createInterval(2d, 3d);
        i10 = FactoryInterval.createInterval(4d, 9d);
        i11 = FactoryInterval.createInterval(-2d, 2d);
        i12 = FactoryInterval.createInterval(-2d, 0d);
        i13 = FactoryInterval.createInterval(-3d, -2d);

        nan = Interval.NaN;
        d0 = 0d;
        d1 = 1d;
        d2 = 2d;
        d3 = 3d;
        d4 = 4d;
    
        // ADT requires a shorter way to write this. But this way the code compiles...
        m1 = (Intervals) FactoryInterval.createInterval(i1, FactoryInterval.createInterval(d4)); // [[1,2][4]]
        m2 = (Intervals) FactoryInterval.createInterval(i1, i4); // [[1,2][4,5]]
        m3 = (Intervals) FactoryInterval.createInterval(i4, i1); // [[4,5][1,2]] = [[1,2][4,5]]
        m4 = (Intervals) FactoryInterval.createInterval(FactoryInterval.createInterval(d4), i1);// [[4],[1,2]] = [[1,2][4]]
    }
	
	@Test
    public void test_union() {
    	
    	//NaI is always NaI
		assertTrue(m1.union(nai) == nai);
    	assertTrue(nai.union(m1) == nai);
    	
    	//union(m1, realInterval) = realInterval
    	assertTrue(m1.union(real).equals(real));
    	
    	//union(union(m1,m2),m3) = union(m1,union(m2,m3))    	
    	assertTrue((m1.union(m2)).union(m3).equals( m1.union(m2.union(m3))));
    	
    	//union(m1,m2) = union(m2,m1)
    	assertTrue(m1.union(m2).equals(m2.union(m1)));
    	
    	//union(m1,m1) = m1
    	assertTrue(m1.union(m1).equals(m1));
    	
    	//union(i1,4) = [[1,2][4]] = m1
    	assertTrue(m1.equals(i1.union(FactoryInterval.createInterval(d4))));
    }
    
    @Test
    public void test_intersection() {
    	
    	System.out.println("Interval: "+m1);
    	System.out.println("Ergebnis: "+m1.intersection(m1));
    	
    	//intersection(NaI, m1) = intersection(m1, NaI) ) NaI
    	assertTrue(m1.intersection(nai).equals(nai));
    	assertTrue(nai.intersection(m1).equals(nai));
    	
    	//intersection(real, m1) = intersection(m1, real) = m1
    	assertTrue(m1.intersection(real).equals(m1));
    	assertTrue(real.intersection(m1).equals(m1));
    	
    	//intersection(m1,intersection(m2,m3)) = intersection(intersection(m1,m2),m3)
    	assertTrue(m1.intersection(m2.intersection(m3)).equals((m1.intersection(m2)).intersection(m3)));
    	
    	//intersection(m1,m2) = intersection(m2,m1)
    	assertTrue(m1.intersection(m2).equals(m2.intersection(m1)));
    	
    	//intersection(m1,m1) = m1
    	assertTrue(m1.intersection(m1).equals(m1));
    }

}