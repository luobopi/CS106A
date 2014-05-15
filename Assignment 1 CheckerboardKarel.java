/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		if(frontIsClear()) {
			while(frontIsClear()) {
				finishRow();
				upMove();
			}
		}
		else {//will finish a column.
			turnLeft();
			finishRow();
		}
	}
	/*
	 * put Beepers on a Row,
	 * Beepers are seprate by one empty position 
	 */
	private void finishRow() {
		putBeeper();
		while(frontIsClear()) {
			move();
			if(frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}
	/*
	 * check face to east or west and move up,
	 * if face to east and then check if there is a beeper,
	 * if no beeper then put a beeper on the top
	 * if face to west will not check because when it up to next row
	 * the first position should have a beeper
	 */
	private void upMove() {
		if(facingEast()){
			if(leftIsClear()){
				turnLeft();
				if(beepersPresent()){
					move();
					turnLeft();
					move();
				}
				else{
					move();
					turnLeft();	
				}
			}
		}
		else{
			if(rightIsClear()) {
				turnRight();
				move();
				turnRight();
			}
		}
	}
}
