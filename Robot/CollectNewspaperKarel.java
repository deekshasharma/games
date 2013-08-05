/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

/* Karel moves from its current position, picks the newspaper and comes back to its initial position */

public class CollectNewspaperKarel extends SuperKarel {
	public void run()
	{
		moveToNewsPaper();
		collectNewspaper();
		comeBackToStartPosition();
			
	}
	
	
	/* Karel moves to the postion where NewsPaper is placed */
	 
	private void moveToNewsPaper()
	{
		while (frontIsClear())
		{
			move();
		}
		turnRight();
		move();
	}
	
	
	/* Karel collects the NewsPaper */
	
	private void collectNewspaper()
	{
		turnLeft();
		move();
		pickBeeper();
	}
	
	
	
	/* Karel comes back to its original position */
	
	private void comeBackToStartPosition()
	{
		turnAround();
		while (frontIsClear())
		{
			move();	
		}
		turnRight();
		while (frontIsClear())
		{
			move();
		}
		turnRight();
		
	}
	
	
	
	

}
