/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		if(frontIsClear()) {
			LayOutRow();
			moveToMiddle();
			putBeeper();
		}
		else {
			putBeeper();//if only one column.
		}
	}
	/*
	 * put beepers in a row without the very beginning and end.
	 */
	private void LayOutRow() {
		move();
		while(frontIsClear()) {
			putBeeper();
			move();
		}
		if(frontIsClear()) {
			move();
		}
	}
	/*
	 * pick the end of beeper and go to other side and pick one
	 * then turn back until there is no beeper and stop
	 * the stopped point is the middle
	 */
	private void moveToMiddle() {
		turnAround();
		move();
		while(beepersPresent()) {
			pickBeeper();
			move();
			moveToEndBeeper();
		}

	}
	/*
	 * go to other side,
	 * if there is no beeper, turn back
	 */
	private void moveToEndBeeper() {
		while(beepersPresent()) {
			move();
		}
		turnAround();
		move();
	}
}
