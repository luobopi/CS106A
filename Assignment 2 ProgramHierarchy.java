/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	/*
	 * I set the configuration with width is 600 and height is 400
	 * set height, width and gap of each box 
	 * Actually I hope a better program.
	 */
	private static final int HEIGHT=40;
	private static final int WIDTH=120;
	private static final int GAP=20;
	public void run() {
		int h=getHeight();
		int w=getWidth();
		ProgramBox(h,w);
		GraphicsProgramBox(h,w);
		ConsoleProgramBox(h,w);
		DialogProgramBox(h,w);
		Lines(h,w);
	}
	private void ProgramBox(int h, int w) {
		double x1=(w-WIDTH)/2;
		double y1=h/2-1.5*HEIGHT;
		GRect box=new GRect(x1,y1,WIDTH,HEIGHT);
		add(box);
		addLabel("Program",x1,y1);
	}
	private void GraphicsProgramBox(int h, int w) {
		double x1=(w-3*WIDTH-2*GAP)/2;
		double y1=h/2+0.5*HEIGHT;
		GRect box=new GRect(x1,y1,WIDTH,HEIGHT);
		add(box);
		addLabel("GraphicsProgram",x1,y1);
	}
	private void ConsoleProgramBox(int h, int w) {
		double x1=(w-WIDTH)/2;
		double y1=h/2+0.5*HEIGHT;
		GRect box=new GRect(x1,y1,WIDTH,HEIGHT);
		add(box);
		addLabel("ConsoleProgram",x1,y1);
	}
	private void DialogProgramBox(int h, int w) {
		double x1=w/2+0.5*WIDTH+GAP;
		double y1=h/2+0.5*HEIGHT;
		GRect box=new GRect(x1,y1,WIDTH,HEIGHT);
		add(box);
		addLabel("DialogProgram",x1,y1);
	}
	private void Lines(int h,int w) {
		int Program_center_x=w/2;
		int Program_center_y=h/2-HEIGHT/2;
		int Graphic_center_x=(w-3*WIDTH-2*GAP)/2+WIDTH/2;
		int Dialog_center_x=w/2+WIDTH+GAP;
		int ThreeBox_y=h/2+HEIGHT/2;
		GLine ToGraphics=new GLine(Program_center_x, Program_center_y, Graphic_center_x,ThreeBox_y);
		GLine ToConsole=new GLine(Program_center_x, Program_center_y,Program_center_x,ThreeBox_y);
		GLine ToDialog=new GLine(Program_center_x, Program_center_y,Dialog_center_x,ThreeBox_y);
		add(ToGraphics);
		add(ToConsole);
		add(ToDialog);
	}
	/*
	 * the addLabel is function to generate label
	 * make sure the label is in the center of box
	 */
	private void addLabel(String Str,double x1, double y1) {
		GLabel label=new GLabel(Str);
		double x2=x1+(WIDTH-label.getWidth())/2;
		double y2=y1+(HEIGHT-label.getHeight())/2+label.getHeight();
		label.setLocation(x2,y2);
		add(label);
	}
}
