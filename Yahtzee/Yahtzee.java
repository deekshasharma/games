/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import acm.graphics.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() 
	{
		IODialog dialog = getDialog();
		do
		{
		nPlayers = dialog.readInt("Enter number of players, Maximum 4 players are allowed! ");
		playerNames = new String[nPlayers];
		}
		while(nPlayers > MAX_PLAYERS);
			
		for (int i = 1; i <= nPlayers; i++) 
		{
			do
			{
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
			}
			while (!(playerNames[i-1].matches(PLAYER_NAME_PATTERN)));			
		}	
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		
		for (int round = 0; round < N_SCORING_CATEGORIES; round++)
		{
			for (int player = 1; player <= nPlayers; player++)
			{
				display.printMessage("Its " +   playerNames[player-1] + "  turn , Click RollDice");
				display.waitForPlayerToClickRoll(player);
				playGame(player);
			}
		}
		updateUpperScore();
		updateUpperBonus();
		updateLowerScore();
		updateFinalTotal();
	}

	
	/* This method controls the game and returns the score for every round of each player*/
	
	private void playGame(int player) 
	{
		int score = 0;
		int indexOfCategory;
				
			rollDice();
			indexOfCategory = display.waitForPlayerToSelectCategory();
			while (scoreBoard[indexOfCategory][player]  != null)
			{
				display.printMessage("You already picked that category, please choose another category!");
				indexOfCategory = display.waitForPlayerToSelectCategory();
			} 
			if (checkCategory(dice, indexOfCategory))
				{
					score = calculateScore(dice, indexOfCategory);		
				}
				else 
				{
					display.updateScorecard(indexOfCategory, player, 0);
				}
			updateScoreboard(player, score, indexOfCategory);	
			}
	
	
	
	/* This methods rolls the dice*/
	
	private void rollDice()
	{
		int remainingRoll = 2;
			for (int i = 0; i < N_DICE; i++)
			{
				dice[i] = rgen.nextInt(1,6);
			}
		display.displayDice(dice);	
		display.printMessage("Select the dice you wish to re-roll and click Roll Again");
		for (int roll = 0; roll < remainingRoll; roll++)
		{
			display.waitForPlayerToSelectDice();
			for (int diceNumber = 0; diceNumber < N_DICE; diceNumber++)
			{
				if (display.isDieSelected(diceNumber))
				{
					//rgen.setSeed(); 
					dice[diceNumber] = rgen.nextInt(1,6);
					display.displayDice(dice);
					display.printMessage("Select the dice you wish to re-roll and click Roll Again");
				}
				else 
				{
					display.printMessage("Select the dice you wish to re-roll and click Roll Again");
				}
			}
		}
		display.printMessage("Your rolls are over, now choose a category!");	

	}
	
	
	/* Returns true if the dice configuration matches a certain category */

	private boolean checkCategory(int [] dice, int indexOfCategory)
	{

		Map<Integer, Integer> map;
	
		switch (indexOfCategory)
		{
			case ONES: 
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(indexOfCategory));
				
			case TWOS:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(indexOfCategory));
			
			case THREES:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(indexOfCategory));
				
			case FOURS:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(indexOfCategory));
				
			case FIVES:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(indexOfCategory));
				
			case SIXES:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(indexOfCategory));
				
			case THREE_OF_A_KIND:
				map = getFrequencyOfDiceRolls(dice);				
				return(map.containsValue(3) || map.containsValue(4) || map.containsValue(5));
			
			case FOUR_OF_A_KIND:
				map = getFrequencyOfDiceRolls(dice);				
				return(map.containsValue(4) || map.containsValue(5));
				
			case FULL_HOUSE:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsValue(3) && map.containsValue(2));
				
			case SMALL_STRAIGHT:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(1) && map.containsKey(2) && map.containsKey(3) && map.containsKey(4) || 
					  map.containsKey(2) && map.containsKey(3) && map.containsKey(4) && map.containsKey(5) || 
					  map.containsKey(3) && map.containsKey(4) && map.containsKey(5) && map.containsKey(6));
							
			case LARGE_STRAIGHT:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(1) && map.containsKey(2) && map.containsKey(3) && map.containsKey(4) && map.containsKey(5) ||
					  map.containsKey(2) && map.containsKey(3) && map.containsKey(4) && map.containsKey(5) && map.containsKey(6));
				
			case YAHTZEE:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsValue(5));
				
			case CHANCE:
				map = getFrequencyOfDiceRolls(dice);
				return(map.containsKey(1) || map.containsKey(2) || map.containsKey(3) || 
					  map.containsKey(4) || map.containsKey(5) || map.containsKey(6));
				
			default : return false;
	}		
		}
		
	
	/* This method returns a map of dice configuration
	 * where key is the number on dice roll and value is the frequency of that number */
	
	private Map<Integer, Integer> getFrequencyOfDiceRolls(int[] dice)
	{
		Map <Integer, Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0; i < N_DICE; i++)
		{
			if (map.containsKey(dice[i]))
			{
				map.put(dice[i], map.get(dice[i])+1);
			}
			else 
			{
				map.put(dice[i], 1);
			}
		} return map;
		
	}
	
	
	/* This method calculate and return the score for each round of game */
	
	private int calculateScore(int [] dice, int indexOfCategory)
	{
		int score = 0;
		
		switch (indexOfCategory)
		{
			case ONES: 
				for (int i = 0; i < N_DICE; i++)
				{
					if(dice[i] == ONES)
					{score += dice[i];}
				}
				break;
				
			case TWOS: 
				for (int i = 0; i < N_DICE; i ++)
				{
					if(dice[i] == TWOS)
					{score += dice[i];}
				}
				break;
				
			case THREES: 
				for (int i = 0; i < N_DICE; i ++)
				{
					if(dice[i] == THREES)
					{score += dice[i];}
				}
				break;
				
			case FOURS: 
				for (int i = 0; i < N_DICE; i ++)
				{
					if(dice[i] == FOURS)
					{score += dice[i];}
				}
				break;
				
			case FIVES: 
				for (int i = 0; i < N_DICE; i ++)
				{
					if(dice[i] == FIVES)
					{score += dice[i];}
				}
				break;
				
			case SIXES: 
				for (int i = 0; i < N_DICE; i ++)
				{
					if(dice[i] == SIXES)
					{score += dice[i];}
				}
				break;
				
			case THREE_OF_A_KIND:
				for (int i = 0; i < N_DICE; i ++)
				{
					score += dice[i];
				}
				break;
				
			case FOUR_OF_A_KIND:
				for (int i = 0; i < N_DICE; i ++)
				{
					score += dice[i];
				}
				break;		
				
			case FULL_HOUSE:
				score = 25;
				break;
				
			case SMALL_STRAIGHT:
				score = 30;
				break;
				
			case LARGE_STRAIGHT:
				score = 40;
				break;
				
			case YAHTZEE:
				score = 50;
				break;
				
			case CHANCE:
				for (int i = 0; i < N_DICE; i ++)
				{
					{score += dice[i];}
				}
				break;				
		}
		return score;
	}
		
	
	/* This method updates the score and Total for each round on the ScoreBoard */
	
	private void updateScoreboard(int player, int score, int indexOfCategory)
	{		
			display.updateScorecard(indexOfCategory, player, score);
			scoreBoard[indexOfCategory][player] = score;
			totalScore = getScoreForCategory(ONES, player) + 
						getScoreForCategory(TWOS, player)+
						getScoreForCategory(THREES, player)+
						getScoreForCategory(FOURS, player)+
						getScoreForCategory(FIVES, player)+
						getScoreForCategory(SIXES, player)+
						getScoreForCategory(THREE_OF_A_KIND, player)+
						getScoreForCategory(FOUR_OF_A_KIND, player)+
						getScoreForCategory(FULL_HOUSE, player)+
						getScoreForCategory(SMALL_STRAIGHT, player)+
						getScoreForCategory(LARGE_STRAIGHT, player)+
						getScoreForCategory(YAHTZEE, player)+
						getScoreForCategory(CHANCE, player);
			
			scoreBoard[TOTAL-1][player] = totalScore;
			display.updateScorecard(TOTAL, player, totalScore);			
	}
	
	
	/* This method returns score for a specific category of a player.It returns 0 if there is no score entered 
	 * for that category */
	
	private int getScoreForCategory(int indexOfCategory, int player) 
	{
		if (scoreBoard[indexOfCategory][player] == null)
		{
			return 0;
		}
		return scoreBoard[indexOfCategory][player];
	}
	
	
	/* This method updates the Upper Score at the end of the game*/
	
	private void updateUpperScore()
	{
		int upperScore;
		for (int player = 1; player <= nPlayers; player++)
		{
			upperScore = scoreBoard[ONES][player] + scoreBoard[TWOS][player]+ scoreBoard[THREES][player]+ scoreBoard[FOURS][player]+ scoreBoard[FIVES][player]+ scoreBoard[SIXES][player];
			scoreBoard[UPPER_SCORE][player] = upperScore;
			display.updateScorecard(UPPER_SCORE, player, upperScore);
		}
	}
	
	
	/* This method updates the UpperBonus at the end of the game*/
	
	private void updateUpperBonus()
	{
		for (int player = 1; player <= nPlayers; player++)
		{
			if (scoreBoard[UPPER_SCORE][player] >= 63)
			{
				scoreBoard[UPPER_BONUS][player] = 35;
				display.updateScorecard(UPPER_BONUS, player, 35);
			}
			else 
			{
				scoreBoard[UPPER_BONUS][player] = 0;
				display.updateScorecard(UPPER_BONUS, player, 0);
			}
		}
	}
	
	/* This method updates the Lower Score at the end of the game*/
	
	private void updateLowerScore()
	{
		int lowerScore;
		for (int player = 1; player <= nPlayers; player++)
		{
			lowerScore = scoreBoard[THREE_OF_A_KIND][player] + scoreBoard[FOUR_OF_A_KIND][player]+ scoreBoard[FULL_HOUSE][player]+ scoreBoard[SMALL_STRAIGHT][player]+ scoreBoard[LARGE_STRAIGHT][player]+ scoreBoard[YAHTZEE][player]+ scoreBoard[CHANCE][player];
			scoreBoard[LOWER_SCORE][player] = lowerScore;
			display.updateScorecard(LOWER_SCORE, player, lowerScore);
		}
	}
	
	
	/* This methods updates the final Total at the end of the game and determines the winner*/
	
	private void updateFinalTotal()
	{
		int greatestScore = 0;
		int winningPlayer = 0;
		for (int player = 1; player <= nPlayers; player++)
		{
			totalScore = scoreBoard[UPPER_SCORE][player] + scoreBoard[UPPER_BONUS][player] + scoreBoard[LOWER_SCORE][player];
			scoreBoard[TOTAL-1][player] = totalScore;
			display.updateScorecard(TOTAL, player, totalScore);		
			if (totalScore > greatestScore)
			{
				greatestScore = totalScore;
				winningPlayer = player;
			}
		}
		display.printMessage("The winning score is " + greatestScore + "and the winner is " + playerNames[winningPlayer - 1] );		
	}
	
		
/* Private instance variables */
	
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	/** Instance variable for the dice array */
	private int [] dice = new int [N_DICE];
	
	/** Instance variable for the Total score*/
	private int totalScore = 0;
	
	/**Instance variable for scoreBoard array */
	private Integer [][] scoreBoard = new Integer [N_CATEGORIES][MAX_PLAYERS+1];
	
	/** The constant for the pattern of a player's name*/
	public static final String PLAYER_NAME_PATTERN = "\\w+\\d*";


}
