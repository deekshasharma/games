/*
 * File: Hailstone.java
 * Name:Deeksha Sharma
 * Section Leader: Ralph Ma
 * --------------------
 * This program pick some positive integer and call it number.
If number is even, divide it by two.
If number is odd, multiply it by three and add one.
Continue this process until number is equal to one.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {


	public void run()
	{
		int count = 0;
		int number = readInt("Enter your choice : " );
		while (number > 1)
		{
			if (number % 2==0)
			{
				println (number +" is even, so I take half: " + number/2);
				number = number/2;
			}

			else if (number  % 2 != 0)
			{
				println(number + " is odd,so i make 3n+1: " +((3*number)+1));
				number = (3*number)+1;
			}
			count = count +1;
			
		}

		println("The process took " + count+ "to reach 1");
	}
	
}

