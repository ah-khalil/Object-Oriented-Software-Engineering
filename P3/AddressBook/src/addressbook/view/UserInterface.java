package addressbook.view;

import java.io.*;
import java.util.*;
import addressbook.model.*;

public class UserInterface{
  private List<Option> optionList;

  public UserInterface(){
    optionList = new ArrayList<Option>();
  }

  public static void printToConsole(String message){
      System.out.println(message);
  }

  public void addOption(int label, Option op){
    optionList.add(label, op);
  }

  public void showMenu(AddressBook addressBook)
  {
    Scanner input = new Scanner(System.in);
    boolean done = false;
    while(!done)
    {
      try
      {
        System.out.print("Enter a Number (enter '-1' to exit): ");
        int option = input.nextLine();
        System.out.print("Enter Search Term: ");
        String term = input.nextLine();
        if(option == -1)
          System.exit(0);
        optionList.get(option).doOption(term, addressBook);
      }
      catch(NumberFormatException nfe)
      {
          // The user entered something non-numerical.
          System.out.println("Enter a number");
      }
    }
  }
}
