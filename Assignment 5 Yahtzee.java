/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	/*
	 * ask to enter the players number and name players
	 * initial canvas and score card array
	 */
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		initialScore();
		playGame();
	}

	/*
	 * For each turns, players played one by one,
	 * after all turns, show the final score and judge who is the winner
	 */
	private void playGame() {
		for(int i=1;i<=N_SCORING_CATEGORIES;i++){
			for(int j=1;j<=nPlayers;j++) {
				playOneTurn(i,j);
			}
		}
		calculateFinalScore();
		decideWinner();
	}
	/*
	 * for the score card array, initial the scores to -1
	 * it is convenient to judge if the element has been selected to store score.
	 * for some category, the player's score is 0,
	 * so set the initial score as -1. 
	 */
	private void initialScore() {
		scoreCard=new int[nPlayers+1][N_CATEGORIES+1];
		for(int i=1;i<=nPlayers;i++){
			for(int j=1;j<=LOWER_SCORE;j++){
				scoreCard[i][j]=-1;
			}
		}
	}
	private void playOneTurn(int turn,int player) {
		display.printMessage(playerNames[player-1]+"'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(player);
		for(int i=0;i<3;i++){
			if(i==0) {
				rollNewDice();
			}else {
				reRollDice();
			}
		}
		selectCategory(player);
	}
	private void rollNewDice(){
		for(int i=0; i<N_DICE; i++) {
			int roll=rgen.nextInt(1, 6);
			dice[i]=roll;
		}
		display.displayDice(dice);
		
	}
	/*
	 * just roll the selected die and put the result in dice[];
	 */
	private void reRollDice() {
		display.printMessage("Select the dice you wish to re-roll and click\"Roll Again\".");
		display.waitForPlayerToSelectDice();
		for(int i=0;i<N_DICE;i++) {
			if(display.isDieSelected(i)==true){
				int roll=rgen.nextInt(1, 6);
				dice[i]=roll;
			}
		}
		display.displayDice(dice);
	}
	/*
	 * first judge the category is an available category.
	 * player just can selected the 13 category and judge if it been selected
	 * seconed, judge player selected it if match the dice and its politics
	 * then put the relative score.
	 */
	private void selectCategory(int player) {
		display.printMessage("Select a category for this roll");
		while(true) {
			int category=display.waitForPlayerToSelectCategory();
			if(checkIsAvailable(player,category)==true){
				updateScore(player,category);
				break;
			}else{
				display.printMessage("The category has been selected.Please try another category");
			}
		}
	}
	/*
	 * check if player select a available category
	 * the category must in the 13 score category
	 * and if it has been selected.
	 * if the category has been selected, the relative element will be replaced by score
	 * if the element is -1, it means it is a available category 
	 */
	private boolean checkIsAvailable(int player,int category){
		if(category<ONES || category==UPPER_SCORE || category==UPPER_BONUS || category>=LOWER_SCORE) {
			return false;
		}else{
			if (scoreCard[player][category]==-1) {
				return true;
			}else{
				return false;
			}
		}
	}
	/*
	 * if the dice match the category,calculate and 
	 * put the result in the array element,
	 * and display the result
	 */
	private void updateScore(int player, int category){
		if(checkIsCategory(dice, category)){
			int score=calculateScore(dice,category);
			scoreCard[player][category]=score;
			scoreCard[player][TOTAL]+=score;
			display.updateScorecard(category, player, score);
			display.updateScorecard(TOTAL, player, scoreCard[player][TOTAL]);
		}else{
			scoreCard[player][category]=0;
			display.updateScorecard(category, player, 0);
		}
	}
	
	private int calculateScore(int[] dice, int category){
		int sum=0;
		if(category<=SIXES) {
			for(int i=0;i<N_DICE;i++) {
				if(dice[i]==category) {
					sum+=dice[i];
				}
			}
		}else if(category==THREE_OF_A_KIND || category==FOUR_OF_A_KIND || category==CHANCE) {
			for(int i=0;i<N_DICE;i++) {
				sum+=dice[i];
			}
		}else if(category==FULL_HOUSE) {
			sum=25;
		}else if(category==SMALL_STRAIGHT) {
			sum=30;
		}else if(category==LARGE_STRAIGHT) {
			sum=40;
		}else if(category==YAHTZEE) {
			sum=50;
		}
		return sum;
	}
	private void calculateFinalScore() {
		for(int i=1; i<=nPlayers; i++){
			int upper=0;
			for(int j=1;j<=SIXES;j++){
				upper+=scoreCard[i][j];
			}
			scoreCard[i][UPPER_SCORE]=upper;
			
			if(upper>=63){
				scoreCard[i][UPPER_BONUS]=35;
				scoreCard[i][TOTAL]+=35;
			}else{
				scoreCard[i][UPPER_BONUS]=0;
			}
			int lower=0;
			for(int k=THREE_OF_A_KIND;k<=CHANCE;k++){
				lower+=scoreCard[i][k];
			}
			scoreCard[i][LOWER_SCORE]=lower;
			//scoreCard[i][TOTAL]=upper+scoreCard[i][UPPER_BONUS]+lower;
			
			display.updateScorecard(UPPER_SCORE, i, scoreCard[i][UPPER_SCORE]);
			display.updateScorecard(UPPER_BONUS, i, scoreCard[i][UPPER_BONUS]);
			display.updateScorecard(LOWER_SCORE, i, scoreCard[i][LOWER_SCORE]);
			display.updateScorecard(TOTAL, i, scoreCard[i][TOTAL]);
		}
	}
	private void decideWinner() {
		int max=0;
		int name=-1;
		for(int i=1;i<=nPlayers;i++) {
			if(max<scoreCard[i][TOTAL]){
				max=scoreCard[i][TOTAL];
				name=i;
			}
		}
		display.printMessage(playerNames[name - 1]+" is Winner!");
	}
	
	private boolean checkIsCategory(int[] dice, int category) {
		boolean checkis=false;
		if((category>=ONES && category<=SIXES)||category==CHANCE) {
			return checkis=true;
			//return true;
		}
		int[] check=new int[7];
		for(int i=0;i<N_DICE;i++){
			check[dice[i]]++;
		}
		if(category==THREE_OF_A_KIND) {
			if(check[1]>=3||check[2]>=3||check[3]>=3||check[4]>=3||check[5]>=3||check[6]>=3){
				return checkis=true;
			}
		}else if(category==FOUR_OF_A_KIND) {
			if(check[1]>=4||check[2]>=4||check[3]>=4||check[4]>=4||check[5]>=4||check[6]>=4){
				return checkis=true;
			}
		}else if(category==FULL_HOUSE) {
			if((check[1]==3||check[2]==3||check[3]==3||check[4]==3||check[5]==3||check[6]==3)&&(check[1]==2||check[2]==2||check[3]==2||check[4]==2||check[5]==2||check[6]==2)) {
				return checkis=true;
			}
		}else if(category==SMALL_STRAIGHT){
			if((check[1]>=1&&check[2]>=1&&check[3]>=1&&check[4]>=1)||(check[2]>=1&&check[3]>=1&&check[4]>=1&&check[5]>=1)||(check[3]>=1&&check[4]>=1&&check[5]>=1&&check[6]>=1)) {
				return checkis=true;
			}
		}else if(category==LARGE_STRAIGHT){
			if((check[1]>=1&&check[2]>=1&&check[3]>=1&&check[4]>=1&&check[5]>=1)||(check[2]>=1&&check[3]>=1&&check[4]>=1&&check[5]>=1&&check[6]>=1)){
				return checkis=true;
			}
		}else if(category==YAHTZEE) {
			if(check[1]==5||check[2]==5||check[3]==5||check[4]==5||check[5]==5||check[6]==5){
				return checkis=true;
			}
		}
		return checkis;
	}
		
/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice=new  int[N_DICE];
	private int[][] scoreCard;

}
