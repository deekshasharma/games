/*
 * File: Pyramid.java
 * Name: Deeksha Sharma
 * Section Leader: Ralph Ma
 * ------------------
 * This program draws the pyramid structure at the center of the screen.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	
	
	public void run() 
	{
		buildPyramid();
	}
	
	
	/* This method draws the pyramid from bottom to top, centered at the screen.
	 * 
	 */
	
	private void buildPyramid()
	{
		
		double x_window = getWidth();
		double y_window = getHeight();		
		double start_x = (x_window - (BRICK_WIDTH*BRICKS_IN_BASE))/2;
		double start_y = y_window - (BRICK_HEIGHT);
		
	
		for (int rows = 0; rows < 14 ; rows++) 
		{
			int count = 0;
			for (int col = 14; col > rows ; col--)
			{
				GRect r = new GRect(start_x,start_y,BRICK_WIDTH,BRICK_HEIGHT);
				add(r);
				start_x = start_x + BRICK_WIDTH;
				count++;
				println(count);
			}	
			start_y = start_y - (BRICK_HEIGHT);
			start_x = start_x - (count*BRICK_WIDTH) + BRICK_WIDTH/2;			
			
		}	
		
	}
}

