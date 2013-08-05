/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() 
	{
		putBeeper();
		while (frontIsClear())
		{
			placeBeepersEast();
			placeBeepersWest();
		}
		placeBeepersEast();

	}


	/* Karel starts to place beepers at alternate corners starting from West to East. 
	 * After the execution all beeper are placed on the street and Karel will move 
	 * to next street from the East end corner.
	 */

	private void placeBeepersEast(){

		placeBeepers();
		if (beepersPresent())
		{
			moveToNextStreetFromEast();
		}
		else 
		{
			moveToNextStreetFromEast();
			if (frontIsClear())
			{
				putBeeper();
			}
		}



	}


	/* Place the beeper at alternate positions on the streets.
	 */

	private void placeBeepers()
	{
		while (frontIsClear()){
			if (beepersPresent())
			{
				move();
			}
			else 
			{
				move();
				putBeeper();
			}
		}

	}



	/* Karel moves to next street from the East end corner.For this method Karel must be facing East
	 * and must have placed all the beepers on the current street.After execution Karel will be on the new 
	 * street and facing West. 
	 */

	private void moveToNextStreetFromEast()
	{
		turnLeft();
		if (frontIsClear())
		{
			move();

			if (leftIsClear())
			{
				turnLeft();
			}

			else 
			{
				while(frontIsClear())
				{
					placeBeepers();

				}
			}


		}
	}



	/* Karel place the beeper at alternate corners in the direction from West To East. 
	 * After the execution all beeper are placed and Karel will move to next street from the West end corner.
	 */
	

	private void placeBeepersWest()
	{
		placeBeepers();
		if (beepersPresent())
		{
			movetoNextStreetFromWest();
		}
		else 
		{
			movetoNextStreetFromWest();
			if (frontIsClear())
			{
				putBeeper();
			}
		}

	}



	/* Karel moves to next street from West end corner.For this method Karel must be facing West and must have placed all the 
	 * beepers on its way.After execution Karel will be on the new street and facing East. 
	 */

	private void movetoNextStreetFromWest()
	{
		turnRight();
		if (frontIsClear())
		{
			move();
			if (rightIsClear())
			{
				turnRight();
			}
		}
	}



}
































