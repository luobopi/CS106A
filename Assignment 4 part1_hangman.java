/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

	
    public void run() {
		setupGame();
		playGame();
	}
    private void setupGame() {
    	println("Welcome to Hangman!");
    	println("The word now looks like this: "+hiddenWord);
    	println("You have " + guessCount + " guesses left.");
    }
    /**
     * ask player to input letter until win or turns over
     * in each turn report whether the letter is correct or not,
     * report the rest turns
     */
    private void playGame() {
    	while(guessCount>0) {
    		String getChar=readLine("Your guess: ");
    		/*
    		 * if player input more than one letter give a message,
    		 * and ask to input again
    		 */
    		if(getChar.length()>1) {
    			println("Error! You can only guess one letter at a time. Please try a again!");
    			getChar=readLine("Your guess: ");
    		}
    		char ch=getChar.charAt(0);
    		/*
    		 * change letter to upper case ignore the input case.
    		 * because the letter in game is upper case.
    		 */
    		ch=Character.toUpperCase(ch);
    		checkLetter(ch);
    		if(hiddenWord.equals(chosenWord)) {
    			println("You guessed the Word: "+chosenWord);
    			println("You win");
    			break;
    		}
    		println("The word now looks like this: "+hiddenWord);
    		println("You have " + guessCount + " guesses left.");
    		if(guessCount==0) {
    			println("You're completely hung.");
    			println("The word was: "+chosenWord);
    			println("Youlose.");
    		}
    	}
    }
    
     //initate the hiddenword with "-"
    private String hiddenword() {
    	String word="";
    	for(int i=0; i<chosenWord.length(); i++) {
    		word="-"+word;
    	}
    	return word;
    }
    //get word from Hangmanlexicon randomly
    private String chooseWord() {
    	int wordCount=hangmanword.getWordCount();
    	int index=rgen.nextInt(0, wordCount-1);
    	String word=hangmanword.getWord(index);
    	return word;
    }
    /*
     * check the letter input if in the word
     * if not, minus the guesscount
     * if in the word, substitute "-" by the letter
     */
    private String checkLetter(char ch) {
    	int index=chosenWord.indexOf(ch);
    	if(index==-1){
    		guessCount--;
    		println("There is no "+ch+" in the word");
    	}else if (index!=-1) {
    		println("The guess is correct.");
    		//if the letter appears in the word many times,
    		//substitute each of them.
    		for(int i=0; i<chosenWord.length(); i++) {
    			if(ch==chosenWord.charAt(i)) {
    				if(i==0){
    	    			hiddenWord=ch+hiddenWord.substring(i+1);
    	    		}else if(i>0) {
    	    			hiddenWord=hiddenWord.substring(0,i)+ch+hiddenWord.substring(i+1);
    	    		}
    			}
    		}
    	}
    	return hiddenWord;
    }
    private HangmanLexicon hangmanword=new HangmanLexicon();//get new word from HangmanLexicon class
	private RandomGenerator rgen=RandomGenerator.getInstance();
    private String chosenWord=chooseWord();//use chooseWord() to get a random word from list.
    private int guessCount=8;
    private String hiddenWord=hiddenword();//use hiddenword() to initiate hiddenWord
}
