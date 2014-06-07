/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {


/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	   add(new JLabel("Name: "),SOUTH);
	   nameField=new JTextField(20);
	   add(nameField,SOUTH);
	   nameField.addActionListener(this);
	   //nameField.addActionListener(this);
	   graphButton=new JButton("Graph");
	   add(graphButton,SOUTH);
	   clearButton=new JButton("Clear");
	   add(clearButton,SOUTH);
	   graph=new NameSurferGraph();
	   add(graph);

	   addActionListeners();
	   
	   namedb=new NameSurferDataBase(NAMES_DATA_FILE);
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clearButton){
			graph.clear();
			graph.update();
		}else if(e.getSource()==graphButton || e.getSource()==nameField) {
			String name=nameField.getText();
			NameSurferEntry nameranking=namedb.findEntry(name);
			if(nameranking!=null) {
				graph.addEntry(nameranking);
				graph.update();
			}
		}
	}
	/* Private instance varibales */
	private JTextField nameField;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferDataBase namedb;
	private NameSurferGraph graph;
	
}
