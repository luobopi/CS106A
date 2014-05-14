/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while(frontIsClear()) {
			repairColomn();
			moveDown();
			moveFourTimes();
		}
		repairColomn();//avoid OBOB
		moveDown();
	}
	/*
	 * repair a colomn by putting Beepers
	 */
	private void repairColomn() {
		turnLeft();
		while(frontIsClear()){
			checkBeepers();
			move();
		}
		checkBeepers();
	}
	private void checkBeepers() {
		if(noBeepersPresent()){
			putBeeper();
		}
	}
	private void moveFourTimes() {
		for(int i=0;i<4;i++) {
			move();
		}
	}
	private void moveDown() {
		turnAround();
		while(frontIsClear()) {
			move();
		}
		turnLeft();
	}
}
