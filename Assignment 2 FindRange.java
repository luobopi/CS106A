/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int x=readInt("?");
		int min=x;
		int max=x;
		if(x!=0) {
			while((x=readInt("?"))!=0) {
				if(x>max){
					max=x;
				}else if(x<min) {
					min =x;
				}
			}
			println("smallest: "+min);
			println("largest: "+max);
		}else{
			println("Error!");
		}
	}
}
