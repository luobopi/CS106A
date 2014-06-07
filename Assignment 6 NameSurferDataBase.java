/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */
import acm.util.*;

import java.util.*;
import java.io.*;

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		try{
			BufferedReader rd=new BufferedReader(new FileReader(filename));
			while(true){
				String line=rd.readLine();
				if(line==null) break;
				NameSurferEntry entry=new NameSurferEntry(line);
				data.put(entry.getName(), entry);
			}
			rd.close();
		}catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		char ch=name.charAt(0);
		if(Character.isLowerCase(ch)==true){
			ch=Character.toUpperCase(ch);
		}
		String others=name.substring(1);
		name=ch+others;
		if(data.containsKey(name)){
			return data.get(name);
		}
		return null;
	}
	/* Private instance variables */
	private HashMap<String,NameSurferEntry> data = new HashMap<String,NameSurferEntry>();
}

