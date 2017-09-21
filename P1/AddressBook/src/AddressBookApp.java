import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and ...
 */
public class AddressBookApp
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        String fileName, entryName;

        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();

        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
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
                //System.out.println(i + " th part of Entry");
		            entry.addEmail(parts[i]);
	          }
            addressBook.addEntry(parts[0], entry);
            // Insert your code here to add a new address book entry.
            // Note:
            // parts[0] contains the person's name.
            // parts[1], parts[2], etc. contain the person's email address(es).
            line = reader.readLine();
        }
        reader.close();

        return addressBook;
    }

    /**
     * Show the main menu, offering the user options to (1) search entries by
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
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
