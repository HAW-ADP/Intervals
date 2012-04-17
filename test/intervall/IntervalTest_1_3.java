package intervall;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
//Statischer factory und interval import: 
import static main.FactoryInterval.*;
import static main.Interval.*;
import static java.lang.Double.*;

public class IntervalTest_1_3 {
	
	Interval i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12;
	Interval m1, m2, m3, m4, m5;
	double d0, d1, d2, d3, d4;
	Random rand;
	
	
	public IntervalTest_1_3(){
		i1 = createInterval(0,50);
		i2 = createInterval(51,100);
		i3 = createInterval(0,100);
		i4 = createInterval(50,100);
		i5 = createInterval(25, 75);
		i6 = createInterval(1,1);
		i7 = createInterval(0.5,0.5);
		i8 = createInterval(0.25, 0.75);
		i9 = createInterval(Double.NEGATIVE_INFINITY, 0);
		i10 = createInterval(0, Double.POSITIVE_INFINITY);
		
		i11 = createInterval(0,24);
		i12 = createInterval(76,100);
		
		m1 = createInterval(new ArrayList<Interval>(Arrays.asList(new Interval[] {i1, i4})));
		m2 = createInterval(new ArrayList<Interval>(Arrays.asList(new Interval[] {i1, i2})));
		m3 = createInterval(new ArrayList<Interval>(Arrays.asList(new Interval[] {i6, i5})));
		m4 = createInterval(new ArrayList<Interval>(Arrays.asList(new Interval[] {i11, i12})));
		m5 = createInterval(new ArrayList<Interval>(Arrays.asList(
							new Interval[] {createInterval(Double.NEGATIVE_INFINITY, (1/i11.getLowerBound())),
									        createInterval((1/i11.getUpperBound()),Double.POSITIVE_INFINITY)})));
	}

	@Test
	public void pLess_test() {
		//Fall 1:
		assertTrue(i1.pLess(i2) == 100d);
		//Fall 2:
		assertTrue(i2.pLess(i1) == 0d);
		//Fall 3 & 6:
		assertTrue(i3.pLess(i4) == 75d);
		assertTrue(i4.pLess(i3) == 25d);
		//Fall 4 & 5:
		assertTrue(i3.pLess(i5) == 50d);
		assertTrue(i5.pLess(i3) == 50d);
		
		//Gleichheit:
		assertTrue(i1.pLess(i1) == 50d);
		
		//NaI-Faelle:
		assertTrue(Double.isNaN(NaI.pLess(i1)));
		assertTrue(Double.isNaN(i1.pLess(NaI)));
		
		//Infinity-Faelle:
		assertTrue(i6.pLess(i9) == 0d);
		assertTrue(i6.pLess(i10) == 100d);
	}
	
	@Test
	public void double_pLess_test(){
		//Fall 1:
		assertTrue(i6.pLess(1d) == i6.pLess(createInterval(1d)));
		
		//Fall 2:
		assertTrue(i6.pLess(2d) == i6.pLess(createInterval(2d)));
		
		//Fall 3 & 6:
		assertTrue(i3.pLess(50d) == i3.pLess(createInterval(50d)));
		assertTrue(i7.pLess(1d) == i7.pLess(i6));
		
		//Fall 4 & 5:
		assertTrue(createInterval(1d).pLess(i8) == i6.pLess(i8));
		assertTrue(i8.pLess(1d) == i8.pLess(createInterval(1d)));
		
		//Gleichheit:
		assertTrue(i6.pLess(1d) == i6.pLess(i6));
		
		//NaI-Faelle:
		assertTrue(Double.isNaN(NaI.pLess(1d)));
		assertTrue(Double.isNaN(i6.pLess(NaN)));
	}
	
	@Test
	public void pGreater_test(){
		//Fall 1:
		assertTrue(i1.pGreater(i2) == 0d);
		//Fall 2:
		assertTrue(i2.pGreater(i1) == 100d);
		//Fall 3 & 6:
		assertTrue(i3.pGreater(i4) == 25d);
		assertTrue(i4.pGreater(i3) == 75d);
		//Fall 4 & 5:
		assertTrue(i3.pGreater(i5) == 50d);
		assertTrue(i5.pGreater(i3) == 50d);
		
		//Gleichheit:
		assertTrue(i1.pGreater(i1) == 50d);
		
		//NaI-Faelle:
		assertTrue(Double.isNaN(NaI.pGreater(i1)));
		assertTrue(Double.isNaN(i1.pGreater(NaI)));
		
		//Infinity-Faelle:
		assertTrue(i6.pGreater(i9) == 100d);
		assertTrue(i6.pGreater(i10) == 0d);
	}
	
	@Test
	public void double_pGreater_test(){
		//Fall 1:
		assertTrue(i6.pGreater(1d) == i6.pGreater(createInterval(1d)));
		
		//Fall 2:
		assertTrue(i6.pGreater(2d) == i6.pGreater(createInterval(2d)));
		
		//Fall 3 & 6:
		assertTrue(i3.pGreater(50d) == i3.pGreater(createInterval(50d)));
		assertTrue(i7.pGreater(1d) == i7.pGreater(i6));
		
		//Fall 4 & 5:
		assertTrue(createInterval(1d).pGreater(i8) == i6.pGreater(i8));
		assertTrue(i8.pGreater(1d) == i8.pGreater(createInterval(1d)));
		
		//Gleichheit:
		assertTrue(i6.pGreater(1d) == i6.pGreater(i6));
		
		//NaI-Faelle:
		assertTrue(Double.isNaN(NaI.pGreater(1d)));
		assertTrue(Double.isNaN(i6.pGreater(NaN)));
	}
	
	@Test
	public void pLessEqual_test(){
		
		System.out.println("----- Test pLessEqual(Interval i) -----");
		System.out.println();
		
		//Fall 1:
		assertTrue(i1.pLessEqual(i2));
		System.out.println("Fall 1:");
		System.out.println(i1 + ".pLessEqual(" + i2 + ") = " + i1.pLessEqual(i2));
		System.out.println();
		
		//Fall 2:
		assertTrue(!i2.pLessEqual(i1));
		System.out.println("Fall 2:");
		System.out.println(i2 + ".pLessEqual(" + i1 + ") = " + i2.pLessEqual(i1));
		System.out.println();
		
		//Fall 3 & 6:
		assertTrue(i3.pLessEqual(i4));
		assertTrue(!i4.pLessEqual(i3));
		System.out.println("Fall 3 & 6:");
		System.out.println(i3 + ".pLessEqual(" + i4 + ") = " + i3.pLessEqual(i4));
		System.out.println(i4 + ".pLessEqual(" + i3 + ") = " + i4.pLessEqual(i3));
		System.out.println();
		
		//Fall 4 & 5:
		assertTrue(i3.pLessEqual(i5));
		assertTrue(i5.pLessEqual(i3));
		System.out.println("Fall 4 & 5:");
		System.out.println(i3 + ".pLessEqual(" + i5 + ") = " + i3.pLessEqual(i5));
		System.out.println(i5 + ".pLessEqual(" + i3 + ") = " + i5.pLessEqual(i3));
		System.out.println();
		
		//Gleichheit:
		assertTrue(i1.pLessEqual(i1));
		System.out.println("Gleichheit:");
		System.out.println(i1 + ".pLessEqual(" + i1 + ") = " + i1.pLessEqual(i1));
		System.out.println();
		
		//NaI-Faelle:
		assertTrue(!NaI.pLessEqual(i1));
		assertTrue(!i1.pLessEqual(NaI));
		System.out.println("NaI-Faelle:");
		System.out.println(NaI + ".pLessEqual(" + i1 + ") = " + NaI.pLessEqual(i1));
		System.out.println(i1 + ".pLessEqual(" + NaI + ") = " + i1.pLessEqual(NaI));
		System.out.println();
		
		//Infinity-Faelle:
		assertTrue(!i6.pLessEqual(i9));
		assertTrue(i6.pLessEqual(i10));
		System.out.println("Infinity-Faelle:");
		System.out.println(i6 + ".pLessEqual(" + i9 + ") = " + i6.pLessEqual(i9));
		System.out.println(i6 + ".pLessEqual(" + i10 + ") = " + i6.pLessEqual(i10));
		System.out.println();
	}
	
	@Test
	public void double_pLessEqual_test(){
		System.out.println("----- Test pLessEqual(double d) -----");
		System.out.println();
		
		//Fall 1:
		assertTrue(i6.pLessEqual(1d) == i6.pLessEqual(createInterval(1d)));
		System.out.println("Fall 1:");
		System.out.println(i6 + ".pLessEqual(" + 1d + ") = " + i6.pLessEqual(1d));
		System.out.println();
		
		//Fall 2:
		assertTrue(i6.pLessEqual(2d) == i6.pLessEqual(createInterval(2d)));
		System.out.println("Fall 2:");
		System.out.println(i6 + ".pLessEqual(" + 2d + ") = " + i6.pLessEqual(2d));
		System.out.println();
		
		//Fall 3 & 6:
		assertTrue(i6.pLessEqual(0.5) == i6.pLessEqual(i7));
		assertTrue(i7.pLessEqual(1d) == i7.pLessEqual(i6));
		System.out.println("Fall 3 & 6:");
		System.out.println(i6 + ".pLessEqual(" + 0.5 + ") = " + i6.pLessEqual(0.5));
		System.out.println();
		
		//Fall 4 & 5:
		assertTrue(createInterval(1d).pLessEqual(i8) == i6.pLessEqual(i8));
		assertTrue(i8.pLessEqual(1d) == i8.pLessEqual(createInterval(1d)));
		System.out.println("Fall 4 & 5:");
		System.out.println(createInterval(1d) + ".pLessEqual(" + i8 + ") = " + createInterval(1d).pLessEqual(i8));
		System.out.println(i8 + ".pLessEqual(" + 1d + ") = " + i8.pLessEqual(1d));
		System.out.println();
		
		//Gleichheit:
		assertTrue(i6.pLessEqual(1d) == i6.pLessEqual(i6));
		System.out.println("Gleichheit:");
		System.out.println(i6 + ".pLessEqual(" + 1d + ") = " + i6.pLessEqual(1d));
		System.out.println();
		
		//NaI-Faelle:
		assertTrue(NaI.pLessEqual(1d) == NaI.pLessEqual(i6));
		assertTrue(i6.pLessEqual(NaN) == i6.pLessEqual(NaI));
		System.out.println("NaI-Faelle:");
		System.out.println(NaI + ".pLessEqual(" + 1d + ") = " + NaI.pLessEqual(1d));
		System.out.println(i6 + ".pLessEqual(" + NaN + ") = " + i6.pLessEqual(NaN));
		System.out.println();
	}
	
	@Test
	public void pGreaterEqual_test(){		
		System.out.println("----- Test pGreaterEqual(Interval i) -----");
		System.out.println();
		
		//Fall 1:
		assertTrue(!i1.pGreaterEqual(i2));
		System.out.println("Fall 1:");
		System.out.println(i1 + ".pGreaterEqual(" + i2 + ") = " + i1.pGreaterEqual(i2));
		System.out.println();
		
		//Fall 2:
		assertTrue(i2.pGreaterEqual(i1));
		System.out.println("Fall 2:");
		System.out.println(i2 + ".pGreaterEqual(" + i1 + ") = " + i2.pLessEqual(i1));
		System.out.println();
		
		//Fall 3 & 6:
		assertTrue(!i3.pGreaterEqual(i4));
		assertTrue(i4.pGreaterEqual(i3));
		System.out.println("Fall 3 & 6:");
		System.out.println(i3 + ".pGreaterEqual(" + i4 + ") = " + i3.pGreaterEqual(i4));
		System.out.println(i4 + ".pGreaterEqual(" + i3 + ") = " + i4.pGreaterEqual(i3));
		System.out.println();
		
		//Fall 4 & 5:
		assertTrue(i3.pGreaterEqual(i5));
		assertTrue(i5.pGreaterEqual(i3));
		System.out.println("Fall 4 & 5:");
		System.out.println(i3 + ".pGreaterEqual(" + i5 + ") = " + i3.pGreaterEqual(i5));
		System.out.println(i5 + ".pGreaterEqual(" + i3 + ") = " + i5.pGreaterEqual(i3));
		System.out.println();
		
		//Gleichheit:
		assertTrue(i1.pGreaterEqual(i1));
		System.out.println("Gleichheit:");
		System.out.println(i1 + ".pGreaterEqual(" + i1 + ") = " + i1.pGreaterEqual(i1));
		System.out.println();
		
		//NaI-Faelle:
		assertTrue(!NaI.pGreaterEqual(i1));
		assertTrue(!i1.pGreaterEqual(NaI));
		System.out.println("NaI-Faelle:");
		System.out.println(NaI + ".pGreaterEqual(" + i1 + ") = " + NaI.pGreaterEqual(i1));
		System.out.println(i1 + ".pGreaterEqual(" + NaI + ") = " + i1.pGreaterEqual(NaI));
		System.out.println();
		
		//Infinity-Faelle:
		assertTrue(i6.pGreaterEqual(i9));
		assertTrue(!i6.pGreaterEqual(i10));
		System.out.println("Infinity-Faelle:");
		System.out.println(i6 + ".pGreaterEqual(" + i9 + ") = " + i6.pGreaterEqual(i9));
		System.out.println(i6 + ".pGreaterEqual(" + i10 + ") = " + i6.pGreaterEqual(i10));
		System.out.println();
	}
	
	@Test
	public void double_pGreaterEqual_test(){
		//Fall 1:
		assertTrue(i6.pGreaterEqual(1d) == i6.pGreaterEqual(createInterval(1d)));
		
		//Fall 2:
		assertTrue(i6.pGreaterEqual(2d) == i6.pGreaterEqual(createInterval(2d)));
		
		//Fall 3 & 6:
		assertTrue(i6.pGreaterEqual(0.5) == i6.pGreaterEqual(i7));
		assertTrue(i7.pGreaterEqual(1d) == i7.pGreaterEqual(i6));
		
		//Fall 4 & 5:
		assertTrue(createInterval(1d).pGreaterEqual(i8) == i6.pGreaterEqual(i8));
		assertTrue(i8.pGreaterEqual(1d) == i8.pGreaterEqual(createInterval(1d)));
		
		//Gleichheit:
		assertTrue(i6.pGreaterEqual(1d) == i6.pGreaterEqual(i6));
		
		//NaI-Faelle:
		assertTrue(NaI.pGreaterEqual(1d) == NaI.pGreaterEqual(i6));
		assertTrue(i6.pGreaterEqual(NaN) == i6.pGreaterEqual(NaI));
	}
	
	@Test
	public void double_plus_test(){
		System.out.println("----- Test plus(double d) -----");
		System.out.println();
		
		assertTrue(i6.plus(1d).equals(i6.plus(i6)));
		assertTrue(i6.plus(NaN) == i6.plus(NaI));
		
		System.out.println(i6 + ".plus(" + 1d + ") = " + i6.plus(1d));
		System.out.println(i6 + ".plus(" + NaN + ") = " + i6.plus(NaN));
		System.out.println();
		
	}
	
	@Test
	public void double_minus_test(){
		System.out.println("----- Test minus(double) -----");
		System.out.println();
		
		assertTrue(i6.minus(1d) == i6.minus(i6));
		assertTrue(i6.minus(NaN) == i6.minus(NaI));
		
		System.out.println(i6 + ".minus(" + 1d + ") = " + i6.minus(1d));
		System.out.println(i6 + ".minus(" + NaN + ") = " + i6.minus(NaN));
		System.out.println();
	}
	
	@Test
	public void double_multi_test(){
		System.out.println("----- Test multi(double d) -----");
		System.out.println();
		
		assertTrue(i6.multi(1d) == i6.multi(i6));
		assertTrue(i6.multi(NaN) == i6.multi(NaI));
		
		System.out.println(i6 + ".multi(" + 1d + ") = " + i6.multi(1d));
		System.out.println(i6 + ".multi(" + NaN + ") = " + i6.multi(NaN));
		System.out.println();
	}
	
	@Test
	public void double_div_test(){
		System.out.println("----- Test div(double d) -----");
		System.out.println();
		
		assertTrue(i6.div(1d) == i6.div(i6));
		assertTrue(i6.div(NaN) == i6.div(NaI));
		
		System.out.println(i6 + ".div(" + 1d + ") = " + i6.div(1d));
		System.out.println(i6 + ".div(" + NaN + ") = " + i6.div(NaN));
		System.out.println();
	}
	
	@Test
	public void double_contains_test(){
		System.out.println("----- Test contains(double d) -----");
		System.out.println();
		
		assertTrue(i6.contains(1d) == i6.contains(i6));
		assertTrue(i6.contains(NaN) == i6.contains(NaI));
		
		System.out.println(i6 + ".contains(" + 1d + ") = " + i6.contains(1d));
		System.out.println(i6 + ".contains(" + NaN + ") = " + i6.contains(NaN));
		System.out.println();
	}
	
	@Test
	public void double_union_test(){
		System.out.println("----- Test union(double d) -----");
		System.out.println();
		
		assertTrue(i6.union(1d) == i6.union(i6));
		assertTrue(i6.union(NaN) == i6.union(NaI));
		
		System.out.println(i6 + ".union(" + 1d + ") = " + i6.union(1d));
		System.out.println(i6 + ".union(" + NaN + ") = " + i6.union(NaN));
		System.out.println();
	}
	
	@Test
	public void double_intersection_test(){
		System.out.println("----- Test intersection(double d) -----");
		System.out.println();
		
		assertTrue(i6.intersection(1d) == i6.intersection(i6));
		assertTrue(i6.intersection(NaN) == i6.intersection(NaI));
		
		System.out.println(i6 + ".intersection(" + 1d + ") = " + i6.intersection(1d));
		System.out.println(i6 + ".intersection(" + NaN + ") = " + i6.intersection(NaN));
		System.out.println();
	}
	
	@Test
	public void double_difference_test(){
		System.out.println("----- Test difference(double d) -----");
		System.out.println();
		
		assertTrue(i6.difference(1d) == i6.difference(i6));
		assertTrue(i6.difference(NaN) == i6.difference(NaI));
		
		System.out.println(i6 + ".difference(" + 1d + ") = " + i6.difference(1d));
		System.out.println(i6 + ".difference(" + NaN + ") = " + i6.difference(NaN));
		System.out.println();
	}
	
	@Test
	public void lessEqual_test(){
		System.out.println("----- Test lessEqual(Interval i) -----");
		System.out.println();
		
		//Less-Fall:
		assertTrue(i6.lessEqual(i5));
		System.out.println(i6 + ".lessEqual(" + i5 + ") = " + i6.lessEqual(i5));
		System.out.println();
		
		//Equal-Fall:
		assertTrue(i6.lessEqual(i6));
		System.out.println(i6 + ".lessEqual(" + i6 + ") = " + i6.lessEqual(i6));
		System.out.println();
		
		//Greater-Fall:
		assertFalse(i5.lessEqual(i6));
		System.out.println(i5 + ".lessEqual(" + i6 + ") = " + i5.lessEqual(i6));
		System.out.println();
		
		//Infinity-Faelle:
		assertFalse(i6.lessEqual(i9));
		assertTrue(createInterval(-1d).lessEqual(i10));
		System.out.println(i6 + ".lessEqual(" + i9 + ") = " + i6.lessEqual(i9));
		System.out.println();
		
		//NaI-Faelle:
		assertFalse(NaI.lessEqual(i6));
		assertFalse(i6.lessEqual(NaI));
		System.out.println(NaI + ".lessEqual(" + i6 + ") = " + NaI.lessEqual(i6));
		System.out.println(i6 + ".lessEqual(" + NaI + ") = " + i6.lessEqual(NaI));
		System.out.println();
	}
	
	@Test
	public void double_lessEqual_test(){		
		System.out.println("----- Test lessEqual(Double d) -----");
		System.out.println();
		
		assertTrue(i6.lessEqual(1d));
		System.out.println(i6 + ".lessEqual(" + 1d + ") = " + i6.lessEqual(1d));
		//NaI-Faelle:
		assertFalse(NaI.lessEqual(1d));		
		System.out.println(NaI + ".lessEqual(" + 1d + ") = " + NaI.lessEqual(1d));
		System.out.println();
	}
	
	@Test
	public void greaterEqual_test(){
		System.out.println("----- Test greaterEqual(Interval i) -----");
		System.out.println();
		
		//Greater-Fall:
		assertTrue(i5.greaterEqual(i6));
		System.out.println(i5 + ".greaterEqual(" + i6 + ") = " + i5.greaterEqual(i6));
		System.out.println();
		
		//Equal-Fall:
		assertTrue(i6.greaterEqual(i6));
		System.out.println(i6 + ".greaterEqual(" + i6 + ") = " + i6.greaterEqual(i6));
		System.out.println();
		
		//Less-Fall:
		assertFalse(i6.greaterEqual(i5));
		System.out.println(i6 + ".greaterEqual(" + i5 + ") = " + i6.greaterEqual(i5));
		System.out.println();
		
		//Infinity-Faelle:
		assertTrue(i6.greaterEqual(i9));
		assertFalse(createInterval(-1d).greaterEqual(i10));
		System.out.println(i6 + ".greaterEqual(" + i9 + ") = " + i6.greaterEqual(i9));
		System.out.println(createInterval(-1d) + ".greaterEqual(" + i10 + ") = " + createInterval(-1d).greaterEqual(i10));
		System.out.println();
		
		//NaI-Faelle:
		assertFalse(NaI.greaterEqual(i6));
		assertFalse(i6.greaterEqual(NaI));
		System.out.println(NaI + ".greaterEqual(" + i6 + ") = " + NaI.greaterEqual(i6));
		System.out.println(i6 + ".greaterEqual(" + NaI + ") = " + i6.greaterEqual(NaI));
		System.out.println();
	}
		
	@Test
	public void double_greaterEqual_test(){
		assertTrue(i6.greaterEqual(1d));
		//NaI-Faelle:
		assertFalse(NaI.greaterEqual(1d));
	}
	
	@Test
	public void union_test(){
		System.out.println("----- Test Union(Interval i) -----");
		System.out.println();
		
		//Kommutatives-Verhalten:
		assertTrue(m1.union(i1).equals(i1.union(m1)));
		System.out.println(m1 + ".union(" + i1 + ") = " + m1.union(i1));
		System.out.println();
		
		//Entstehen von MultiIntervallen
		assertTrue(i6.union(i5).equals(m3));
		System.out.println(i6 + ".union(" + i5 + ") = " + i6.union(i5));
		System.out.println();
		
		//Entstehen von NormalIntervallen
		assertTrue(i3.equals(i3.union(m3)));
		System.out.println(i3 + ".union(" + m3 + ") = " + i3.union(m3));
		System.out.println(m3 + ".union(" + i3 + ") =" + m3.union(i3));
		System.out.println();
	}
	
	@Test
	public void intersect_test(){
		System.out.println("----- Test intersect(Interval i) -----");
		System.out.println();
		
		//NormalInterval + MultiIntervall
		assertTrue(i6.intersection(m1).equals(i6));
		System.out.println(i6 + ".intersection(" + m1 + ") = " + i6.intersection(m1));
		System.out.println();
		
		//Kommutatives-Verhalten:
		assertTrue(m1.intersection(i6).equals(i6));
		System.out.println(m1 + ".intersection(" + i6 + ") = " + m1.intersection(i6));
		System.out.println();
	}
	
	@Test
	public void difference_test(){
		System.out.println("----- Test difference(Interval i) -----");
		System.out.println();
		
		//Entstehen von NormalIntervallen:
		assertTrue(i3.difference(i5).equals(m4));
		System.out.println(i3 + ".difference(" + i5 + ") = " + i3.difference(i5));
		System.out.println();
		
		//Difference zwischen Multi- und NormalInterval
		assertTrue(m4.difference(i3).equals(i5));
		System.out.println(m4 + ".difference(" + i3 + ") = " + m4.difference(i3));
		System.out.println();
	}
	
	@Test
	public void div_test(){
		System.out.println("----- Test div(Interval i) -----");
		System.out.println();
		
		//Entstehen von MultiIntervallen:
		assertTrue(i6.div(i11).equals(m5));
		System.out.println(i6 + ".div(" + i11 + ") = " + i6.div(i11));
		System.out.println(m5);
		System.out.println();
	}
}