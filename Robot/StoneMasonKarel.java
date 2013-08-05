/*
 * File: StoneMasonKarel.java

 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run()
	{
		while (frontIsClear())
		{
		placeStones();
		moveToFirstStreet();
		moveFourBlocks();
		}
			placeStones();
		}
		

		
		
	/*Karel faces North and place the missing stones on the column */
	
	public void placeStones()
	{
		turnLeft();
		while (frontIsClear())
		{	
			if (noBeepersPresent())
			{
				putBeeper();
				move();
			}
			else 
			{
				move();
			}

		}
		if (noBeepersPresent())
		{
			putBeeper();
		}
		
	}
	
	
	
	
/*Karel moves back to Street1 after putting the missing stones */
	
	public void moveToFirstStreet()
	{
		turnAround();
		while (frontIsClear())
		{
			move();
		}
	}
	
	
	
	/* Karel is on the 1st street and moves 4 blocks away from the current position until frontIsClear*/
	
	public void moveFourBlocks()
	{
		turnLeft();
		for (int block = 0; block < 4; block++)
		 {
			 move();
		 }
	}
	
	
	
	

}

