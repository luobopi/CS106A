/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;
	
	public void run() {
		/*
		 * get the height and width of the window.
		 * I'd like change the height as 300 and the width as 500,
		 * it looks comfort and enough to show pyramid
		 */
		int height=getHeight();
		int width=getWidth();
		/*
		 * add blocks from the bottom
		 * use i to count the layers and j to execute blocks in each layer. 
		 */
		for(int i=0;i<BRICKS_IN_BASE;i++){
			for(int j=0;j<(BRICKS_IN_BASE-i);j++) {
				int x=(width/2)-(BRICK_WIDTH*(BRICKS_IN_BASE-i)/2)+j*BRICK_WIDTH;
				int y=height-(i+1)*BRICK_HEIGHT;
				GRect block=new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
				add(block);
			}
		}
	}
}
