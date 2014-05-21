/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int x = readInt("Enter a number:");
		int i=0;
		while(x!=1) {
			if(x%2==0) {
				println(x + " is even, so I take half: " + (x=x/2));
			}else {
				println(x + " is odd, so I make 3n+1: " + (x=3*x+1));
			}
			i++;
		}
		println("The process took "+i+" to reach 1");
	}
}
