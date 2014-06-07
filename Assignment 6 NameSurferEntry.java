/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		String[] dataline=line.split("\\s");
		if(dataline.length != NDECADES+1) {
			throw new Error("This line does not have corredt data!");
		}else{
			name=dataline[0];
			for(int i=1;i<=NDECADES;i++) {
				int rank;
				rank=Integer.parseInt(dataline[i]);
				ranks[i-1]=rank;
			}
		}
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		return ranks[decade];
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String rankline="";
		for(int i=0;i<NDECADES;i++){
			rankline+=" "+ranks[i];
		}
		return name+" ["+ rankline +" ]";
	}
	
	/* Private instance variable*/
	private String name;
	private int[] ranks= new int[NDECADES];
}

