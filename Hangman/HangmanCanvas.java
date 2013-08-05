/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;
import java.util.*;

public class HangmanCanvas extends GCanvas {

	/** Instance variable for Character Array of incorrect words  */
	char[] storeIncorrectWord = new char[8];

	/** Instance variable to track the current index of incorrect word */
	private int indexOfIncorrectWord = 0;


	/**Instance variable for GLabel of correct words */
	private GLabel correctWords;

	/**Instance variable for GLabel of incorrect words */
	private GLabel incorrectWord;



	/* Resets the display so that only the scaffold appears */
	public void reset() 
	{
		drawScaffold();			
	}


	/*
	 * Updates the word on the screen to correspond to the current
	 * state of the game.  The argument string shows what letters have
	 * been guessed so far; unguessed letters are indicated by hyphens.
	 */
	public void displayWord(String word) 
	{
		if (correctWords != null)
		{
			removeWords(correctWords);	
			correctWords = drawLabel(word, getWidth()/2, getHeight()/10 + SCAFFOLD_HEIGHT + 10);
		}
		else 
		{
			correctWords = drawLabel(word, getWidth()/2, getHeight()/10 + SCAFFOLD_HEIGHT + 10);
		}

	}


	/* This method draws the GLabel on the screen for a given String at a specific position */

	private GLabel drawLabel(String str, double x, double y)
	{
		GLabel label = new GLabel(str, x, y);
		add(label);
		return(label);
	}


	/* Removes the old label before the new label is drawn on the console */

	private void removeWords(GObject obj)
	{
		remove(obj);
	}



	/*
	 * Updates the display to correspond to an incorrect guess by the
	 * user.  Calling this method causes the next body part to appear
	 * on the scaffold and adds the letter to the list of incorrect
	 * guesses that appears at the bottom of the window.
	 */
	public void noteIncorrectGuess(char letter) 
	{
		storeIncorrectWord[indexOfIncorrectWord++] = letter;

		if (incorrectWord != null)
		{
			removeWords(incorrectWord);
			incorrectWord = drawLabel(new String(storeIncorrectWord), getWidth()/2, getHeight()/10 + SCAFFOLD_HEIGHT + 30);
		}
		else 
		{
			incorrectWord = drawLabel(new String(storeIncorrectWord), getWidth()/2, getHeight()/10 + SCAFFOLD_HEIGHT + 30);
		}

		if ( indexOfIncorrectWord == 1)
		{
			drawHead();
		}	

		else if ( indexOfIncorrectWord == 2)
		{
			drawBody();
		}
		else if ( indexOfIncorrectWord == 3)
		{
			drawLeftArm();
		}
		else if ( indexOfIncorrectWord == 4)
		{
			drawRightArm();

		}
		else if ( indexOfIncorrectWord == 5)
		{
			drawLeftLeg();

		}
		else if ( indexOfIncorrectWord == 6)
		{
			drawRightleg();

		}
		else if ( indexOfIncorrectWord == 7)
		{
			drawLeftFoot();

		}
		else if ( indexOfIncorrectWord == 8)
		{
			drawRightFoot();

		}

	}


	/* Constants for the simple version of the picture (in pixels) */

	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;


	/* This method draws the Scaffold*/

	private void drawScaffold()
	{	
		double x1_Scaffold = getWidth()/2 - BEAM_LENGTH ;
		double y1_Scaffold = getHeight()/10;
		double x2_Scaffold = x1_Scaffold;
		double y2_Scaffold = y1_Scaffold + SCAFFOLD_HEIGHT; 

		double x1_Beam = x1_Scaffold;
		double y1_Beam = y1_Scaffold;
		double x2_Beam = getWidth()/2;
		double y2_Beam = y1_Beam;

		double x1_Rope = x2_Beam;
		double y1_Rope = y2_Beam;
		double x2_Rope = x1_Rope;
		double y2_Rope = y2_Beam + ROPE_LENGTH;

		drawLine(x1_Scaffold, y1_Scaffold, x2_Scaffold, y2_Scaffold);
		drawLine(x1_Beam, y1_Beam, x2_Beam, y2_Beam);
		drawLine(x1_Rope, y1_Rope, x2_Rope, y2_Rope);	
	}


	/* This method draw the Lines for the Hangman*/

	private void drawLine (double x1, double y1, double x2, double y2)
	{
		GLine line = new GLine(x1, y1, x2, y2);
		add(line);
	}


	/* This method draws Hangman head*/

	private void drawHead()
	{	
		double x = getWidth()/2 - HEAD_RADIUS;
		double y = getHeight()/10 + ROPE_LENGTH;
		double width = 2 * HEAD_RADIUS;
		double height = 2 * HEAD_RADIUS;

		GOval head = new GOval(x, y, width, height);
		add(head);	
	}

	/* This method draws Hangman body*/

	private void drawBody()
	{	
		double x1_body = getWidth()/2;
		double y1_body = getHeight()/10 + ROPE_LENGTH + (2*HEAD_RADIUS);
		double x2_body = x1_body;
		double y2_body = y1_body + BODY_LENGTH;

		drawLine(x1_body, y1_body, x2_body, y2_body);	
	}


	/* This method draws Hangman LeftArm*/

	private void drawLeftArm()
	{	
		double x1_LeftArm = getWidth()/2;
		double y1_LeftArm = getHeight()/10 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD;
		double x2_LeftArm = x1_LeftArm - UPPER_ARM_LENGTH;
		double y2_LeftArm = y1_LeftArm;
		double x3_LeftArm = x2_LeftArm;
		double y3_LeftArm = y2_LeftArm + LOWER_ARM_LENGTH;

		drawLine(x1_LeftArm, y1_LeftArm, x2_LeftArm, y2_LeftArm);
		drawLine(x2_LeftArm, y2_LeftArm, x3_LeftArm, y3_LeftArm);
	}


	/* This method draws Hangman RightArm*/

	private void drawRightArm()
	{	
		double x1_RightArm = getWidth()/2;
		double y1_RightArm = getHeight()/10 + ROPE_LENGTH + (2*HEAD_RADIUS) + ARM_OFFSET_FROM_HEAD;
		double x2_RightArm = x1_RightArm + UPPER_ARM_LENGTH;
		double y2_RightArm = y1_RightArm; 
		double x3_RightArm = x2_RightArm;
		double y3_RightArm = y2_RightArm + LOWER_ARM_LENGTH;

		drawLine(x1_RightArm, y1_RightArm, x2_RightArm, y2_RightArm);
		drawLine(x2_RightArm, y2_RightArm, x3_RightArm, y3_RightArm);			
	}	


	/* This method draws Hangman LeftLeg*/

	private void drawLeftLeg()
	{	
		double x1_LeftLeg = getWidth()/2;
		double y1_LeftLeg = getHeight()/10 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH ;
		double x2_LeftLeg = x1_LeftLeg - HIP_WIDTH;
		double y2_LeftLeg = y1_LeftLeg;
		double x3_LeftLeg = x2_LeftLeg;
		double y3_LeftLeg = y2_LeftLeg + LEG_LENGTH;

		drawLine(x1_LeftLeg, y1_LeftLeg, x2_LeftLeg, y2_LeftLeg);
		drawLine(x2_LeftLeg, y2_LeftLeg, x3_LeftLeg, y3_LeftLeg);		
	}


	/* This method draws Hangman RightLeg*/

	private void drawRightleg()
	{	
		double x1_RightLeg = getWidth()/2;
		double y1_RightLeg = getHeight()/10 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH ;
		double x2_RightLeg =  x1_RightLeg + HIP_WIDTH;
		double y2_RightLeg = y1_RightLeg;
		double x3_RightLeg = x2_RightLeg;
		double y3_RightLeg = y2_RightLeg + LEG_LENGTH;

		drawLine(x1_RightLeg, y1_RightLeg, x2_RightLeg, y2_RightLeg);
		drawLine(x2_RightLeg, y2_RightLeg, x3_RightLeg, y3_RightLeg);				
	}


	/* This method draws Hangman LeftFoot*/

	private void drawLeftFoot()
	{	
		double x1_LeftFoot = getWidth()/2 - HIP_WIDTH;
		double y1_LeftFoot = getHeight()/10 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH;
		double x2_LeftFoot = x1_LeftFoot - FOOT_LENGTH;
		double y2_LeftFoot = y1_LeftFoot;

		drawLine(x1_LeftFoot, y1_LeftFoot, x2_LeftFoot, y2_LeftFoot);			
	}


	/* This method draws Hangman RightFoot*/

	private void drawRightFoot()
	{	
		double x1_RightFoot = getWidth()/2 + HIP_WIDTH;
		double y1_RightFoot = getHeight()/10 + ROPE_LENGTH + (2*HEAD_RADIUS) + BODY_LENGTH + LEG_LENGTH;
		double x2_RightFoot = x1_RightFoot + FOOT_LENGTH;
		double y2_RightFoot = y1_RightFoot;

		drawLine(x1_RightFoot, y1_RightFoot, x2_RightFoot, y2_RightFoot);	
	}
}
