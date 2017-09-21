package addressbook.model;

import java.io.*;
import java.util.*;
import addressbook.view.*;

public class SearchByEmail implements Option{
  public String doOption(String email, AddressBook book){
    String retString = null;
		for(Map.Entry<String, Entry> bk : book.getEntrySet()){
			if(bk.getValue().hasEmail(email))
				retString = bk.getValue().returnEntry();
		}
		if(retString == null)
			UserInterface.printToConsole("Entry not found with that email");

		return retString;
  }
}
