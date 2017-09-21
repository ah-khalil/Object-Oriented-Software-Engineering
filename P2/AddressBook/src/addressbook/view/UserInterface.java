package addressbook.view;

import java.io.*;
import java.util.*;
import addressbook.model.*;

public class UserInterface{
  public static void printToConsole(String message){
      System.out.println(message);
  }

  public static void showMenu(AddressBook addressBook, Scanner input)
  {
      boolean done = false;
      while(!done)
      {
          int option;
          System.out.println("(1) Search by name, (2) Search by email, (3) Number of Entries, (4) Quit");

          try
          {
              switch(Integer.parseInt(input.nextLine()))
              {
                  case 1:
                      System.out.print("Enter name: ");
                      String name = input.nextLine();
                      addressBook.getEntryByName(name);
                      break;

                  case 2:
                      System.out.print("Enter email address: ");
                      String email = input.nextLine();
                      addressBook.getEntryByEmail(email);
                      break;

                  case 3:
                      System.out.println(addressBook.noOfEntries());
                      break;

                  case 4:
                      done = true;
                      break;
              }
          }
          catch(NumberFormatException e)
          {
              // The user entered something non-numerical.
              System.out.println("Enter a number");
          }
      }
  }
}
