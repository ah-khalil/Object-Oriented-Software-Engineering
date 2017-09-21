package addressbook.controller;

import java.io.*;
import java.util.*;
import addressbook.view.*;
import addressbook.model.*;

public class Controller{
  private static Scanner input = new Scanner(System.in);

  public static void main(String[] args)
  {
    String fileName, entryName;
    UserInterface ui = new UserInterface();

    ui.printToConsole("Enter address book filename: ");
    fileName = input.nextLine();

    try
    {
      AddressBook addressBook = readAddressBook(fileName);
      ui.addOption(0, new SearchByName());
      ui.addOption(1, new SearchByEmail());
      ui.showMenu(addressBook);
    }
      catch(IOException e)
      {
        ui.printToConsole("Could not read from " + fileName + ": " + e.getMessage());
      }
    }

  private static AddressBook readAddressBook(String fileName) throws IOException
  {
      AddressBook addressBook = new AddressBook();

      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String line = reader.readLine();
      while(line != null)
      {
          String[] parts = line.split(":");

          Entry entry = new Entry(parts[0]);
          for(int i = 1; i < parts.length; i++){
              entry.addEmail(parts[i]);
          }
          addressBook.addEntry(parts[0], entry);
          line = reader.readLine();
      }
      reader.close();

      return addressBook;
  }
}
