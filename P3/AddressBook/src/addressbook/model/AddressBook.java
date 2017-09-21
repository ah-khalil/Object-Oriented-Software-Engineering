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

	public int noOfEntries(){
		return book.size();
	}

	public Set<Map.Entry<String, Entry>> getEntrySet(){
		return book.entrySet();
	}

	public boolean hasKey(String name){
		return book.containsKey(name);
	}

	public Entry getEntry(String key){
		return book.get(key);
	}
}
