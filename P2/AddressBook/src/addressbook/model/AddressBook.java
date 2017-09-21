package addressbook.model;

import java.util.*;
import static addressbook.view.UserInterface.*;

public class AddressBook
{
	private Map<String, Entry> book;

	public AddressBook(){
		book = new HashMap<String, Entry>();
	}

	public void addEntry(String name, Entry inEntry){
		book.put(name, inEntry);
		printToConsole("Entry added!");
	}

	public Entry getEntryByName(String name){
		Entry retEntry = null;
		if(book.containsKey(name))
		{
			retEntry = book.get(name);
			retEntry.printEntry();
		}
		else
			printToConsole("Entry not found with that name");

		return retEntry;
	}

	public Entry getEntryByEmail(String email){
		Entry retEntry = null;
		for(Map.Entry<String, Entry> bk : book.entrySet()){
			if(bk.getValue().hasEmail(email))
				retEntry = bk.getValue();
				retEntry.printEntry();
		}
		if(retEntry == null)
			printToConsole("Entry not found with that email");

		return retEntry;
	}

	public int noOfEntries(){
		return book.size();
	}
}
