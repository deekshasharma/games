/*
 * File: Breakout.java
 * -------------------
 * Name:Deeksha Sharma		
 * Section Leader: RalphMa
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram 
{

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board
	 *  Should not be used directly (use getWidth()/getHeight() instead).
	 *  * */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
		(WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;


	private RandomGenerator rgen = RandomGenerator.getInstance();
	/** Coordinates of the last mouse click*/
	private double lastX;
	private double lastY;

	/** Holds the Paddle for mouse click  */
	private GObject gobject;

	/** Horizontal velocity of the ball */
	private double velocityX ;

	/** Vertical velocity of the ball */
	private double velocityY = 3.0;

	private GRect paddle;
	private GOval ball;

	/** Label to display message */
	private GLabel message;



	/* Runs the Breakout program. */

	public void run() 
	{
		addMouseListeners();
		drawBricks();
		drawPaddle();
		drawBall();
		playGame();
	}


	/* This method constructs the colored bricks of a given measurement and spacing. */

	private void drawBricks()
	{
		double ycoordinate = BRICK_Y_OFFSET;
		for (int rows = 0; rows < 10 ; rows++)
		{

			double xcoordinate = (getWidth() - (BRICK_WIDTH * NBRICKS_PER_ROW) - (NBRICKS_PER_ROW-1)*BRICK_SEP)/2;
			for (int column = 0 ; column < 10 ; column++)
			{
				GRect brick = new GRect(xcoordinate,ycoordinate,BRICK_WIDTH,BRICK_HEIGHT);
				add(brick);

				if (rows == 0 || rows == 1)
				{
					brick.setFillColor(Color.RED);
					brick.setColor(Color.RED);
					brick.setFilled(true);
				}
				else if (rows == 2 || rows == 3)
				{
					brick.setFillColor(Color.ORANGE);
					brick.setColor(Color.ORANGE);
					brick.setFilled(true);
				}

				else if (rows == 4 || rows == 5)
				{
					brick.setFillColor(Color.YELLOW);
					brick.setColor(Color.YELLOW);
					brick.setFilled(true);
				}

				else if (rows == 6 || rows == 7)
				{
					brick.setFillColor(Color.GREEN);
					brick.setColor(Color.GREEN);
					brick.setFilled(true);
				}
				else if (rows == 8 || rows == 9)
				{
					brick.setFillColor(Color.CYAN);
					brick.setColor(Color.CYAN);
					brick.setFilled(true);
				}
				xcoordinate += BRICK_WIDTH + BRICK_SEP;
			}
			ycoordinate += BRICK_HEIGHT + BRICK_SEP;
		}
	}


	/* This method creates a paddle of given dimension and offset from the bottom of the  wall. */

	private void drawPaddle()
	{
		double ycoordinate = (getHeight() -  PADDLE_Y_OFFSET);
		double xcoordinate = 0;
		paddle = new GRect(xcoordinate, ycoordinate,PADDLE_WIDTH,PADDLE_HEIGHT);
		add (paddle);
		paddle.setFillColor(Color.BLACK);
		paddle.setColor(Color.BLACK);
		paddle.setFilled(true);
	}


	/*  This method draw the ball*/

	private void drawBall()
	{	

		double ball_x = getWidth()/2- BALL_RADIUS ;
		double ball_y = getHeight()/2 - BALL_RADIUS;
		double ball_width = 2*BALL_RADIUS;
		double ball_height = 2*BALL_RADIUS;
		ball = new GOval(ball_x, ball_y, ball_width, ball_height);
		add(ball);
		ball.setFillColor(Color.GREEN);
		ball.setColor(Color.GREEN);
		ball.setFilled(true);
	}


	/* Control the ball movements when the ball hits the paddle, left wall, right wall,top wall and the floor   */

	private void playGame()
	{
		int turnUsed = 1;
		int bricksRemoved = 0;
		int remainingBricks = NBRICKS_PER_ROW * NBRICK_ROWS;
		velocityX = rgen.nextDouble(1.0, 3.0);

		while(true)
		{
			moveBall();
			if (checkBrickCollision())
			{
				if (bricksRemoved < (remainingBricks))
				{
					removeBrick();
					bricksRemoved += 1;
					ballBounceVertical();
				}

				if (bricksRemoved == (remainingBricks))
				{
					displayMessage("Congratulations , You won!");
					return;
				}
			}

			if (ballHitPaddle())
			{
				ballBounceVertical();
			}
			else if (ballHitRightWall())
			{
				ballBounceHorizontal();	
			}

			else if (ballHitTopWall())
			{
				ballBounceVertical();					
			}

			else if (ballHitLeftWall())
			{
				ballBounceHorizontal();
			}

			else if (ballHitFloor())
			{
				if (turnUsed < NTURNS ) 
				{	
					displayMessage(NTURNS - turnUsed + "Ê more turn remaining");
					turnUsed += 1;
					remove(ball);
					drawBall();
					pause(500);
					remove(message);
				}
				else if (turnUsed == NTURNS)
				{
					displayMessage("All the turns are over!!");
					return;
				}
			}	

			else if (ballStickInPaddleTop())
			{
				ballBounceVertical();
			}
		}
	}


	/* This method moves the ball down towards the paddle */

	private void moveBall()
	{
		ball.move(velocityX, velocityY);
		pause (20);
	}


	/* Returns True if there is a collision between ball and the bricks*/

	private boolean checkBrickCollision()
	{
		return((getCollidingObject() != null && getCollidingObject() !=paddle));	
	}


	/* Removes brick that collides with the ball */

	private void removeBrick()
	{
		remove(getCollidingObject());
	}


	/* Returns boolean value if ball hits the paddle */

	private boolean ballHitPaddle()
	{
		return ((ball.getX() >= paddle.getX()) && (ball.getX() <= paddle.getX()+paddle.getWidth()) && ball.getY() + ball.getHeight() - paddle.getY() > 0);

	}


	/*Bounce the ball vertically */ 

	private void ballBounceVertical()
	{	
		velocityY = -velocityY;
	}


	/* Return True if ball hits the Right wall*/

	private boolean ballHitRightWall()
	{

		return (ball.getX()+ ball.getWidth() > getWidth());
	}


	/*Bounce the ball when ball hits left and right wall */	

	private void ballBounceHorizontal()
	{

		velocityX = -velocityX;		
	}


	/* Returns true if ball hits the top wall */

	private boolean ballHitTopWall()
	{
		return (ball.getY() < 0);	
	}


	/* Return true when ball hits the left wall*/

	private boolean ballHitLeftWall()
	{
		return (ball.getX() < 0);
	}

	/* Returns True if ball hits the floor */

	private boolean ballHitFloor()
	{
		return(ball.getY() + ball.getHeight() > getHeight() || (ball.getY() > (paddle.getY() + paddle.getHeight()) && ball.getY() < getHeight()) );
	}


	/* Returns true if ball is stuck on top of the paddle*/

	private boolean ballStickInPaddleTop()
	{

		double ball_left_bottomy = ball.getY() - ball.getHeight();
		double paddle_left_bottomy = paddle.getY() + paddle.getHeight();

		return(ball_left_bottomy > paddle.getY() && ball_left_bottomy < paddle_left_bottomy) ;
	}


	/* Return the GObject in the path of the ball */

	private GObject getCollidingObject()
	{
		double ball_left_topx = ball.getX();
		double ball_left_topy = ball.getY();

		double ball_right_topx = ball_left_topx + ball.getWidth();
		double ball_right_topy = ball_left_topy;

		double ball_left_bottomx = ball_left_topx;
		double ball_left_bottomy = ball_left_topy - ball.getHeight();

		double ball_right_bottomx = ball_left_bottomx + ball.getWidth();
		double ball_right_bottomy = ball_left_bottomy;

		if (getElementAt(ball_left_topx, ball_left_topy) != null)
		{
			return (getElementAt(ball_left_topx, ball_left_topy));
		}

		else if (getElementAt(ball_right_topx, ball_right_topy) != null)
		{

			return (getElementAt(ball_right_topx, ball_right_topy));
		}
		else if (getElementAt(ball_left_bottomx, ball_left_bottomy) != null)
		{
			return(getElementAt(ball_left_bottomx, ball_left_bottomy));
		}
		else if (getElementAt(ball_right_bottomx, ball_right_bottomy) !=null)
		{
			return(getElementAt(ball_right_bottomx, ball_right_bottomy));
		}
		else 
			return (null);

	}


	/* Display the message on the screen for remaining turns and when game is over*/

	private void displayMessage(String str)
	{
		message = new GLabel(str, getWidth()/2, getHeight()/4);
		add(message);

	}

	/* Called on the first Mouse click and returns the paddle at that location  */

	public void mousePressed(MouseEvent e)
	{
		lastX = e.getX();
		lastY = e.getY();
		gobject = getElementAt(lastX , lastY);
		
	}


	/* This method call drags the paddle along the x axis.*/

	public void mouseDragged(MouseEvent e)
	{
		if (gobject != null && gobject == paddle) 
		{	
			if (e.getX() < (getWidth() -paddle.getWidth()) && e.getX() > 0)
			{
				gobject.move(e.getX() - paddle.getX() ,0);
				lastX = e.getX();
			}			
		}
	}

}
















