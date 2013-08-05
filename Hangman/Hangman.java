/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.ArrayList;

import java.awt.*;


public class Hangman extends ConsoleProgram {

	private static final int TOTAL_GUESS = 8;

	/** Instance variable  for HangmanLexicon object*/
	HangmanLexicon lexicon = new HangmanLexicon();


	/** Instance variable for the finalWord guessed by the user  */
	StringBuilder finalWord = new StringBuilder( "");


	/** Instance variable for HangmanCanvas class */	
	private HangmanCanvas canvas ;


	/* Initialize HangmanCanvas*/

	public void init()
	{
		canvas = new HangmanCanvas();
		add(canvas);		
	}

	
	/* Run method*/
	
	public void run() 
	{
		canvas.reset();
		println("Welcome To Hangman!");
		String secretWord = selectSecretWord();
		println("The word now look like this: " + initialDisplay(secretWord));
		println("You have now 8 guesses: ");
		playGame(secretWord);

	}	


	/* Returns the random word from the Lexicon */

	private String selectSecretWord()
	{
		RandomGenerator rgen = RandomGenerator.getInstance();

		String secretWord = lexicon.getWord(rgen.nextInt(0,lexicon.getWordCount() - 1)); 
		return(secretWord);
	}


	/* Returns the number of alphabets in secret word*/ ///this will be removed, we dont need it

	private int	countSecretWordLength(String secretWord)
	{
		return(secretWord.length());
	}


	/* Returns the number of blanks required to complete the secret word */

	private StringBuilder initialDisplay(String secretWord)
	{

		int length = secretWord.length();
		String blank = "-" ;
		for (int i = 0 ; i < length; i++)
		{
			finalWord.append(blank);
		}
		return(finalWord);
	}


	/* This method play the game */

	private void playGame(String secretWord)
	{
		ArrayList <Integer> indexOfGuess;
		String guess = "";
		int countIncorrectGuess = 0;

		while (true)
		{
			guess = readLine("Your guess : " ).toUpperCase();
			if (checkGuess(guess))
			{
				println("Illegal input");
				guess = readLine("Your guess : " ).toUpperCase();				
			}
			if (isGuessInSecretWord(secretWord, guess))
			{
				println("That guess is correct.");
				indexOfGuess = getIndexOfGuess(secretWord,guess);
				finalWord = fillGuess(indexOfGuess, guess);				
				canvas.displayWord(finalWord.toString());
				if(finalWord.indexOf("-") == -1)
				{
					{
						println("You guessed the word: " + secretWord);
						println("You win!");
						break;
					}
				}
				else 
				{				
					println("The word now looks like this: "+ finalWord);
				}
			}
			else 
			{
				countIncorrectGuess += 1;
				println("There are no " + guess+"'s" + " in the word");
				println("The word now looks like this " + finalWord);
				println("You have now " + ((TOTAL_GUESS - countIncorrectGuess) + "guesses left" ));
				canvas.noteIncorrectGuess(guess.charAt(0));
			}
			if (countIncorrectGuess == TOTAL_GUESS) break;
		}
		if (finalWord.indexOf("-") != -1)
		{
			println("You Are Completely Hung!");
			println("The word was "+ secretWord);
			println("You lose the game!");
		}
	}


	/* Returns true if the user guesses anything greater than one letter */

	private boolean checkGuess(String guess)
	{
		return (guess.length() > 1);

	}


	/* Return true if secret word contains user's guess  */ 

	private boolean isGuessInSecretWord(String secretWord, String guess)
	{
		return (secretWord.contains(guess.toUpperCase()));	
	}


	/* Returns a list of all indexes where guess occurs in the secretWord.  */

	private ArrayList <Integer> getIndexOfGuess(String secretWord, String guess)
	{
		ArrayList <Integer>indexOfGuess  = new ArrayList <Integer>();
		int length = countSecretWordLength(secretWord);
		for (int i = secretWord.indexOf(guess); i < length ; i++)
		{
			if ((secretWord.charAt(i)) == (guess.charAt(0)))
			{
				indexOfGuess.add(i);		
			}
		}
		return indexOfGuess;

	}


	/* Populate the guess at the appropriate position in the finalWord */

	private StringBuilder fillGuess(ArrayList indexOfGuess, String guess)
	{
		for (int i = 0; i < indexOfGuess.size(); i++)
		{
			finalWord.setCharAt((Integer) indexOfGuess.get(i), guess.charAt(0));
		}
		return(finalWord);
	}


}
