/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		list=new ArrayList<NameSurferEntry>();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		list.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		list.add(entry);
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawBackGround();
		drawEntries();
	}
	
	
	/**
	 * draw the background of canvas with the top and bottom,
	 * show the decade label
	 */
	private void drawBackGround() {
		double height=getHeight();
		double width=getWidth();
		add(new GLine(0,GRAPH_MARGIN_SIZE,width,GRAPH_MARGIN_SIZE));
		add(new GLine(0,height-GRAPH_MARGIN_SIZE,width,height-GRAPH_MARGIN_SIZE));
		double gap=width/NDECADES;
		for(int i=1;i<NDECADES;i++) {
			add(new GLine(i*gap,0,i*gap,height));
		}
		for(int j=0;j<NDECADES;j++) {
			String decade=Integer.toString(START_DECADE+j*10);
			GLabel label=new GLabel(decade);
			add(label,gap*j,height);
		}
	}
	
	/**
	 * when update the graph need redraw the line on the previous canvas
	 * use the ArrayList to store the name typed before and use drawEntry 
	 * method to redraw the lines and the labels
	 */
	private void drawEntries() {
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				NameSurferEntry entries=list.get(i);
				drawEntry(entries,i);
			}
		}
	}
	
	
	/**
	 * To draw ranking lines of a name, if the ranking is 0, lines on 
	 * bottom and labeled by "*", the line is higher if the rank is top
	 * the first name is black, then red, blue and magenta and have a
	 * turn, each name label follwing by it rank in each decade
	 * @param entry a NameSurferEntry class restore the name's information
	 * @param number store its turn when be typed in
	 */
	private void drawEntry(NameSurferEntry entry,int number) {
		for(int i=0;i<NDECADES-1;i++) {
			int rank1=entry.getRank(i);
			int rank2=entry.getRank(i+1);
			double x1=i*getWidth()/NDECADES;
			double x2=(i+1)*getWidth()/NDECADES;
			double y1=0;
			double y2=0;
			if(rank1!=0 && rank2!=0){
				y1=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank1/MAX_RANK;
				y2=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank2/MAX_RANK;
			}else if(rank1==0 && rank2==0){
				y1=getHeight()-GRAPH_MARGIN_SIZE;
				y2=getHeight()-GRAPH_MARGIN_SIZE;
			}else if(rank1==0 && rank2!=0) {
				y1=getHeight()-GRAPH_MARGIN_SIZE;
				y2=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank2/MAX_RANK;
			}else if(rank1!=0 && rank2==0) {
				y1=GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank1/MAX_RANK;
				y2=getHeight()-GRAPH_MARGIN_SIZE;
			}
			GLine line=new GLine(x1,y1,x2,y2);
			if(number%4==1) {
				line.setColor(Color.red);
			}else if(number%4==2) {
				line.setColor(Color.blue);
			}else if(number%4==3) {
				line.setColor(Color.magenta);
			}
			add(line);
		}
		for(int i=0;i<NDECADES;i++) {
			int rank=entry.getRank(i);
			String rankString=Integer.toString(rank);
			String label=entry.getName()+" "+rankString;
			double x = i*getWidth()/NDECADES+LABEL_GAP_LINE;
			double y =GRAPH_MARGIN_SIZE+(getHeight()-GRAPH_MARGIN_SIZE*2)*rank/MAX_RANK-LABEL_GAP_LINE;
			if(rank==0) {
				y=getHeight()-GRAPH_MARGIN_SIZE-LABEL_GAP_LINE;
				label=entry.getName()+"*";
			}
			GLabel namelabel=new GLabel(label,x,y);
			if(number%4==1) {
				namelabel.setColor(Color.red);
			}else if(number%4==2) {
				namelabel.setColor(Color.blue);
			}else if(number%4==3) {
				namelabel.setColor(Color.magenta);
			}
			add(namelabel);
		}
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* Private instance variables */
	ArrayList<NameSurferEntry> list;
}
