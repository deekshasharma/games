/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run()
	{
		placeAlternateBeepers();
		if (frontIsClear())
		{
			pickRightMostBeeper();
			placeAtCenter();
		}
	}



	/* Karel place the Beepers at the alternate avenues till the end of 1st Street, 
	 * starting from West to East. After beepers are placed Karel turn Around facing West.
	 */

	private void placeAlternateBeepers()
	{
		putBeeper();
		if (frontIsClear())
		{
			move();
		}
		else
		{
			turnAround();
		}
		while(frontIsClear())
		{
			move();
			putBeeper();
			if (frontIsClear())
			{
				move();
			}
		}
		turnAround();
	}



	/* Karel picks the right most beeper on 1st street and put it in first gap it finds on the way.
	 * For this method Karel must move from East to West on 1st street. After the execution is 
	 * complete Karel will have filled all the gaps and now the beepers will be placed from West to East 
	 * till the (center-1) of the street.
	 */

	private void pickRightMostBeeper()
	{
		while (frontIsClear())
		{
			if (beepersPresent())
			{
				pickBeeper();
				move();
				findGapAndPlacebeeper();		
			}
			else 
			{
				move();
			}	
		} 

	}


	/* Finds the first gap starting from west to east and place the picked up beeper in to the gap. 
	 */

	private void findGapAndPlacebeeper()
	{
		while (frontIsClear())
		{
			if (noBeepersPresent())
			{
				putBeeper();
				moveBackToEast();
				pickRightMostBeeper();

			}
			else
			{
				move();
			}
		}
	}


	/* Karel moves back to East end of 1st street.
	 */

	private void moveBackToEast()
	{
		turnAround();
		while (frontIsClear())
		{
			move();
		}
		turnAround();
	}


	/* Karel picks all beepers on its way till the position (center-1) and stop at the center with the  
	 * beeper.For this method Karel must move from West to East.
	 */ 


	private void placeAtCenter()
	{
		turnAround();
		while (beepersPresent())
		{
			pickBeeper();
			move();
		}
		putBeeper();
	}




}




