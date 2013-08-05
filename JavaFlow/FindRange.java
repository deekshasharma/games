/*
 * File: FindRange.java
 * Name: Deeksha Sharma
 * Section Leader: Ralph Ma
 * -----------------------------------------
 * This program reads a list of integers, one per line, until a sentinel 
value of 0. When the sentinel is read, the program displays the smallest and largest values in the 
list
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	/** The value of sentinel chosen for this program */
	private static final int sentinel = 0;


	public void run() 
	{
		findRange();
		
	}
	
	
	/* This method find the smallest and greatest number from the list of all integers entered. For this method to execute 
	 * initially "min" and "max" variables are assigned the value of the sentinel.
	 * 
	 */
	
	private void findRange()
	{
		int max = sentinel;
		int min = sentinel;

		while (true)
		{
			int value = readInt("?");
			if (value == sentinel) break;
				if (max == sentinel && min == sentinel)
				{
					max = value;
					min = value;
				}
				else 
				{
					max = (max > value) ? max : value; 
					min = (min < value) ? min : value;
				}

		}
		if (max == sentinel && min == sentinel){
			println("There is no greatest or smallest value");
		}
		else
		{

			println("The largest value is" + max );

			println("The smallest value is" + min );
		}
	}
}

