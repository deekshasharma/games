/*
 * File: ProgramHierarchy.java
 * Name:Deeksha Sharma
 * Section Leader: Ralph Ma
 * ---------------------------
 * This program draws the program hierarchy in a tree structure, placed at the center of the screen.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram 
{	
	
	/**Width of the each box in pixels */
	private static final int width = 150;
	
	
	/**Height of the each box in pixels */
	private static final int height = 50;
	

	public void run() 
	{
		double x_window = getWidth()/2.0;
		double y_window = getHeight()/2.0;
		
		double x_parent = x_window-width/2;
		double y_parent = y_window-(2*height);
		
		double x_child1 = x_window-(2*width);
		double y_child1 = y_window+height/2;
		
		double x_child2 = x_window-width/2;
		double y_child2 = y_window+height/2;
		
		double x_child3 = x_window+width;
		double y_child3 = y_window+height/2;
		
		
		
		
		drawRectangle(x_parent,y_parent,width,height);	
		drawLabel("Program",x_parent, y_parent);
		
		drawLine(x_parent , y_parent, x_child1, y_child1);
		drawLine(x_parent , y_parent, x_child2, y_child2);
		drawLine(x_parent , y_parent, x_child3, y_child3);
		
		drawRectangle(x_child1,y_child1,width,height);
		drawLabel("GraphicsProgram",x_child1,y_child1);
		
		drawRectangle(x_child2,y_child2,width,height);
		drawLabel("ConsoleProgram",x_child2,y_child2);
		
		drawRectangle(x_child3,y_child3,width,height);
		drawLabel("DialogProgram",x_child3,y_child3);
		
	}
	
	
	/* This method draw rectangular boxes of a given height, width and x-y coordinates
	 */
	
	
	private void drawRectangle(double xcoordinate, double ycoordinate, double width, double height)
	{
		GRect box = new GRect(xcoordinate, ycoordinate, width, height);
		add(box);
		
	}
	
	
	
	/* This methods draw labels at the center of the rectangular boxes.
	 */
	
	private void drawLabel(String str,double xcoordinate,double ycoordinate)
	{
		
		GLabel label = new GLabel(str);	
		
		/** Center location of the rectangular box */
		double centerLabelX = (width - label.getWidth())/2;
		double centerLabelY = 2*label.getHeight();
		
		add(label,(xcoordinate + centerLabelX), (ycoordinate+centerLabelY));
		
	}
	
	
	
	
	/* This method draw the lines between the rectangular boxes to form hierarchy. The lines are drawn 
	 * between the centers of the rectangular box edges. 
	 */
	
	private void drawLine(double x1coordinate, double y1coordinate, double x2coordinate, double y2coordinate)
	{
		GLine line = new GLine ((x1coordinate + width/2), (y1coordinate + height), (x2coordinate + width/2), y2coordinate);
		add(line);
		
	}
}

