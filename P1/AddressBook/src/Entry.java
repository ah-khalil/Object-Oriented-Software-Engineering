import java.util.*;

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
			System.out.println("Email Added!");
		else
			System.out.println("Email already added!");
	}

  public boolean hasEmail(String inEmail){
    return (email.contains(inEmail));
  }

  public void printEntry(){
    System.out.println("Name: " + name);
    email.forEach(System.out::println);
  }

	public String getName(){
		return name;
	}
}
