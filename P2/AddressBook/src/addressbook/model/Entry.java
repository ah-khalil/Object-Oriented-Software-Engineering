package addressbook.model;

import java.util.*;
import static addressbook.view.UserInterface.*;

/**
 * Represents a single address book entry.
 *
 * @author ...
 */
public class Entry
{
	private String name;
	private Set<String> email;

	public Entry(){
		name = "";
		email = new HashSet<String>();
	}

	public Entry(String inName){
		name = inName;
		email = new HashSet<String>();
	}

	public void addEmail(String inEmail){
		if(email.add(inEmail))
			printToConsole("Email Added!");
		else
			printToConsole("Email already added!");
	}

  public boolean hasEmail(String inEmail){
    return (email.contains(inEmail));
  }

  public void printEntry(){
    printToConsole("Name: " + name);
		for(String e : email){
			System.out.println(e);
		}
  }

	public String getName(){
		return name;
	}
}
