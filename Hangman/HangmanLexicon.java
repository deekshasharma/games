/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will re implement for Part III of the assignment.
 */

import acm.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class HangmanLexicon {

	/** Instance variable for ArrayList that holds all the words from text file */
	ArrayList <String> listAllWords = new ArrayList <String>();


	public HangmanLexicon()
	{
		String fileLine = "";
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true)
			{
				try 
				{
					fileLine = br.readLine();
					listAllWords.add(fileLine);
					if (fileLine == null) break;	
				}

				catch (IOException e) 
				{
					e.printStackTrace();
				}				
			}
			try 
			{
				br.close();
			} catch (IOException e) {
				System.out.println("cannot close file");
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File is not found");
		}

	}


	/** Returns the number of words in the lexicon.  */
	public int getWordCount() 
	{
		return(listAllWords.size());
	}


	/** Returns the word at the specified index. */
	public String getWord(int index) 
	{
		return(listAllWords.get(index));
	}



}