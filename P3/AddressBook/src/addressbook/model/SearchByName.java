package addressbook.model;

import java.io.*;
import java.util.*;
import addressbook.view.*;

public class SearchByName implements Option{
  public String doOption(String name, AddressBook book){
    String retString = null;
  	if(book.hasKey(name))
  		retString = book.getEntry(name).returnEntry();
  	else
  		UserInterface.printToConsole("Entry not found with that name");

	  return retString;
  }
}
