package addressbook.model;

import java.util.*;

public class Entry extends FileElements{
    String name;
    Set<String> emails;

    public Entry(String inName){
        name = inName;
        emails = new HashSet<String>();
    }

    public void addEmail(String inEmail){
        emails.add(inEmail);
    }

    public String getName(){
        return name;
    }

    public Set<String> getEmails(){
        return Collections.unmodifiableSet(emails);
    }
}
