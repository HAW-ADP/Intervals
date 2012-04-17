package intervall;

import adintervall.FactoryInterval;
import adintervall.Interval;
import java.util.Arrays;
import java.util.Random;

public class PVergleiche_syso_test {
	
	public static void main(String[] args){
		
		Interval i1 = FactoryInterval.createInterval(1, 3);
		Interval i2 = FactoryInterval.createInterval(2,4);
		
		Random rand = new Random();
		int[] verteilung = {0,0};
		double zufallsZahlIntervall1;
		double zufallsZahlIntervall2; 
		
		System.out.println("-------------------------------- TEST pLESS --------------------------------");
		
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i2.length()) + i2.getLowerBound();
			if(zufallsZahlIntervall1<zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall:  [ { ] }");
		System.out.println(i1 + ".pless(" + i2 + "): [kleiner, kleiner]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i1.pLess(i2));
		System.out.println();
		
		
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		Interval i4 = FactoryInterval.createInterval(0, 4);
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			if(zufallsZahlIntervall1<zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: { [ ] }   (A in B)");
		System.out.println(i1 + ".pless(" + i4 + "): [kleiner, kleiner]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i1.pLess(i4));
		System.out.println();
		
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			if(zufallsZahlIntervall1<zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: [ { } ]    (B in A)");
		System.out.println(i4 + ".pless(" + i1 + "): [kleiner, kleiner]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i4.pLess(i1));
		System.out.println();
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		Interval i5 = FactoryInterval.createInterval(0,2);
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i5.length()) + i5.getLowerBound();
			if(zufallsZahlIntervall1<zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: [{ } ]    (B in A, lowerbounds gleich)");
		System.out.println(i4 + ".pless(" + i5 + "): [kleiner, kleiner]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i4.pLess(i5));
		System.out.println();
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		i5 = FactoryInterval.createInterval(2,4);
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i5.length()) + i5.getLowerBound();
			if(zufallsZahlIntervall1<zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: [ { }]    (B in A, upperbounds gleich)");
		System.out.println(i4 + ".pless(" + i5 + "): [kleiner, kleiner]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit duchh 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i4.pLess(i5));
		System.out.println();
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		

		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i2.length()) + i2.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			if(zufallsZahlIntervall1<zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: { [ } ]");
		System.out.println(i2 + ".pless(" + i1 + "): [kleiner, kleiner]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i2.pLess(i1));
		System.out.println();
		
		System.out.println("-------------------------------- TEST pGreater --------------------------------");

		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i2.length()) + i2.getLowerBound();
			if(zufallsZahlIntervall1>zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall:  [ { ] }");
		System.out.println(i1 + ".pGreater(" + i2 + "): [groesser, groesser]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i1.pGreater(i2));
		System.out.println();
		
		
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			if(zufallsZahlIntervall1>zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: { [ ] }   (A in B)");
		System.out.println(i1 + ".pGreater(" + i4 + "): [groesser, groesser]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i1.pGreater(i4));
		System.out.println();
		
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			if(zufallsZahlIntervall1>zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: [ { } ]    (B in A)");
		System.out.println(i4 + ".pGreater(" + i1 + "): [groesser, groesser]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i4.pGreater(i1));
		System.out.println();
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i5.length()) + i5.getLowerBound();
			if(zufallsZahlIntervall1>zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: [{ } ]    (B in A, lowerbounds gleich)");
		System.out.println(i4 + ".pGreater(" + i5 + "): [groesser, groesser]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i4.pGreater(i5));
		System.out.println();
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		
		i5 = FactoryInterval.createInterval(2,4);
		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i4.length()) + i4.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i5.length()) + i5.getLowerBound();
			if(zufallsZahlIntervall1>zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: [ { }]    (B in A, upperbounds gleich)");
		System.out.println(i4 + ".pGreater(" + i5 + "): [groesser, groesser]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit duchh 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i4.pGreater(i5));
		System.out.println();
		
		verteilung[0] = 0;
		verteilung[1] = 0;
		

		for(int i = 0; i< 10000; i++){
			zufallsZahlIntervall1 = rand.nextDouble() + rand.nextInt((int)i2.length()) + i2.getLowerBound();
			zufallsZahlIntervall2 = rand.nextDouble() + rand.nextInt((int)i1.length()) + i1.getLowerBound();
			if(zufallsZahlIntervall1>zufallsZahlIntervall2){
				verteilung[0] = verteilung[0] + 1;
			} 
			else{
				verteilung[1] = verteilung[1] + 1;
			}
		}
		System.out.println("Fall: { [ } ]");
		System.out.println(i2 + ".pGreater(" + i1 + "): [groesser, groesser]=  " + Arrays.toString(verteilung));
		System.out.println("Wahrscheinlichkeit durch 10000: " + ((double)(verteilung[0]*100)/(verteilung[0]+verteilung[1])));
		System.out.println("Wahrscheinlichkeit durch Methode: " + i2.pGreater(i1));
		System.out.println();
	}
}
