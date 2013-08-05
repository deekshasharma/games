/*
 * File: PythagoreanTheorem.java
 * Name:Deeksha Sharma
 * Section Leader: Ralph Ma
 * -----------------------------
 * This program accepts values for a and b as doubles (you can 
assume that a and b will be positive) and then calculates the solution of c as a 
double
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	double a;
	double b;

	public void run() 
	{	
		println("Enter values to compute the Pythagorean theorem");
		double a = readDouble("a :");
		double b = readDouble("b :");
		double c = Math.sqrt((a*a) + (b*b));
		println("c =" +c);
	}
	
}
