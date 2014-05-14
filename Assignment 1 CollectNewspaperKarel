/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run() {
		moveToNewpaper();
		pickBeeper();
		moveToOriginalPosition();
	}
	/*
	 * move Karel to the newspaper
	 */
	private void moveToNewpaper() {
		moveToWall();
		move();
		turnLeft();
		move();
	}
	/*
	 * move Karel until front is wall
	 * If the front is a wall then turn right
	 */
	private void moveToWall() {
		while (frontIsClear()) {
			move();
		}
		turnRight();
	}
	/*
	 * move Karel to the original position
	 */
	private void moveToOriginalPosition() {
		turnAround();
		moveToWall();
		move();
		turnRight();
	}
}
