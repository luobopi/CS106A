/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		scaffold_y=(getHeight()-SCAFFOLD_HEIGHT)/4;
		double scaffold_x=(getWidth()/2-BEAM_LENGTH);
		center_x=getWidth()/2;
		GLine scaffold=new GLine(scaffold_x,scaffold_y,scaffold_x,scaffold_y+SCAFFOLD_HEIGHT);
		GLine beam = new GLine(scaffold_x,scaffold_y,center_x,scaffold_y);
		GLine rope = new GLine(center_x,scaffold_y,center_x,scaffold_y+ROPE_LENGTH);
		add(scaffold);
		add(beam);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		GLabel Hiddenword = new GLabel(word);
		Hiddenword.setFont("The New Times -36");
		double hiddenword_x=getWidth()/4;
		double hiddenword_y=getHeight()/4*3+Hiddenword.getHeight();
		Hiddenword.setLocation(hiddenword_x, hiddenword_y);
		//if the label exist, remove.
		if(getElementAt(hiddenword_x, hiddenword_y)!=null) {
			remove(getElementAt(hiddenword_x, hiddenword_y));
		}
		add(Hiddenword);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		incorrect += letter;
		drawIncorrect(incorrect);
		int step=incorrect.length();
		//according the steps of incorrect letter
		//draw body part one by one.
		switch(step){
		case 1: drawHead();break;
		case 2: drawBody();break;
		case 3: drawLeftArm();break;
		case 4: drawRightArm();break;
		case 5: drawLeftHand();break;
		case 6: drawRightHand();break;
		case 7: drawLeftLeg();break;
		case 8: drawRightLeg();break;
		}
		
	}
	private void drawIncorrect(String incorrect) {
		GLabel incorrectGuess = new GLabel(incorrect);
		incorrectGuess.setFont("The News Man -16");
		double incorrect_x=getWidth()/4;
		double incorrect_y=getHeight()/4*3+GAP_LABEL;
		incorrectGuess.setLocation(incorrect_x, incorrect_y);
		//if the label exist, remove
		if(getElementAt(incorrect_x, incorrect_y)!=null) {
			remove(getElementAt(incorrect_x, incorrect_y));
		}
		add(incorrectGuess);
	}
	private void drawHead() {
		double head_x=center_x-HEAD_RADIUS;
		double head_y=scaffold_y+ROPE_LENGTH;
		GOval head = new GOval(head_x,head_y,HEAD_RADIUS*2,HEAD_RADIUS*2);
		add(head);
	}
	private void drawBody() {
		body_y=scaffold_y+ROPE_LENGTH+HEAD_RADIUS*2;
		GLine body=new GLine(center_x,body_y,center_x,body_y+BODY_LENGTH);
		add(body);
	}
	private void drawLeftArm() {
		arm_y=body_y+ARM_OFFSET_FROM_HEAD;
		double hand_x=center_x-UPPER_ARM_LENGTH;
		GLine arm=new GLine(center_x,arm_y,hand_x,arm_y);
		add(arm);
	}
	private void drawRightArm() {
		double hand_x=center_x+UPPER_ARM_LENGTH;
		GLine arm=new GLine(center_x,arm_y,hand_x,arm_y);
		add(arm);
	}
	private void drawLeftHand() {
		double hand_x=center_x-UPPER_ARM_LENGTH;
		GLine hand=new GLine(hand_x,arm_y,hand_x,arm_y+LOWER_ARM_LENGTH);
		add(hand);
	}
	private void drawRightHand() {
		double hand_x=center_x+UPPER_ARM_LENGTH;
		GLine hand=new GLine(hand_x,arm_y,hand_x,arm_y+LOWER_ARM_LENGTH);
		add(hand);
	}
	private void drawLeftLeg() {
		leg_y=body_y+BODY_LENGTH;
		foot_y=leg_y+LEG_LENGTH;
		double leg_x=center_x-HIP_WIDTH;
		GLine hip=new GLine(center_x,leg_y,leg_x,leg_y);
		GLine leg=new GLine(leg_x,leg_y,leg_x,foot_y);
		GLine foot=new GLine(leg_x,foot_y,leg_x-FOOT_LENGTH,foot_y);
		add(hip);
		add(leg);
		add(foot);
	}
	private void drawRightLeg() {
		double leg_x=center_x+HIP_WIDTH;
		GLine hip=new GLine(center_x,leg_y,leg_x,leg_y);
		GLine leg=new GLine(leg_x,leg_y,leg_x,foot_y);
		GLine foot=new GLine(leg_x,foot_y,leg_x+FOOT_LENGTH,foot_y);
		add(hip);
		add(leg);
		add(foot);
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
	private static final int GAP_LABEL = 80;
	private double scaffold_y;
	private double center_x;
	private double body_y;
	private String incorrect="";
	private double arm_y;
	private double leg_y;
	private double foot_y;
	
}
