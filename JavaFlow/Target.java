/*
 * File: Target.java
 * Name: Deeksha Sharma
 * Section Leader: Ralph Ma
 * -----------------
 * This program draws the Target figure. The outer red circle with radius of one inch (72 pixels), 
 * the white circle with a radius of 0.65 inches, and the inner red circle has a radius of 0.3 inches. The 
figure should be centered in the window of a GraphicsProgram subclass
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	private static final double WIDTH_LARGEST = 144;
	private static final double HEIGHT_LARGEST = 144;

	private static final double WIDTH_MIDDLE = 93.6;
	private static final double HEIGHT_MIDDLE = 93.6;

	private static final double WIDTH_SMALLEST = 43.2;
	private static final double HEIGHT_SMALLEST = 43.2;



	public void run() 
	{
		double x = getWidth()/2.0;
		double y = getHeight()/2.0;
		double radius_largest = WIDTH_LARGEST/2;
		double radius_middle = WIDTH_MIDDLE/2;
		double radius_smallest = WIDTH_SMALLEST/2;

		drawCircle((x-radius_largest),(y-radius_largest),WIDTH_LARGEST,HEIGHT_LARGEST,true);
		drawCircle((x-radius_middle),(y-radius_middle),WIDTH_MIDDLE,HEIGHT_MIDDLE,false);
		drawCircle((x-radius_smallest),(y-radius_smallest),WIDTH_SMALLEST,HEIGHT_SMALLEST,true);



	}	
	
	/*
	 * This method draws the circles at the center of the screen.
	 */

	private void drawCircle(double xcoordinate,double ycoordinate,double width,double height,boolean isRed)
	{
		GOval circle = new GOval(xcoordinate,ycoordinate,width,height);
		add(circle);
		if (isRed)
		{
			circle.setColor(Color.RED);
			circle.setFillColor(Color.RED);
			circle.setFilled(true);
		}
		else
		{
			circle.setColor(Color.WHITE);
			circle.setFillColor(Color.WHITE);
			circle.setFilled(true);
		}
	}



}
