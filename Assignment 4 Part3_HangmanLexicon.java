/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;

import java.io.*;
import java.util.*;


public class HangmanLexicon {
	
	// build a arraylist to store each line
	private ArrayList<String> wordlist=new ArrayList<String>();
	
	public HangmanLexicon() {
		try{
			BufferedReader list=new BufferedReader(new FileReader("Hangmanlexicon.txt"));
			while(true) {
				String line=list.readLine();
				if(line==null) break;
				wordlist.add(line);
			}
			list.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}

	}


/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		int size=wordlist.size();
		return size;
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		String getword=wordlist.get(index);
		return getword;
	}

}
