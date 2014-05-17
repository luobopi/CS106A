/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/*
		 * I am not sure if pixel can have double,
		 * or even if the values are double they changed
		 * to int when display on window.
		 */
		double radius1=72;
		double radius2=0.65*72;
		double radius3=0.3*72;
		int height=getHeight()/2;//get the center of window
		int width=getWidth()/2;//get the center of window
		addCircle(height,width,radius1,Color.RED);
		addCircle(height,width,radius2,Color.white);
		addCircle(height,width,radius3,Color.RED);
	}
	/*
	 * addCircle is a function to draw circle,
	 * with parameters of position, radius and color 
	 */
	private void addCircle(int h, int w,double r,Color color) {
		GOval circle=new GOval(h-r,w-r,2*r,2*r);
		circle.setColor(color);
		circle.setFilled(true);
		circle.setFillColor(color);
		add(circle);
	}
}
