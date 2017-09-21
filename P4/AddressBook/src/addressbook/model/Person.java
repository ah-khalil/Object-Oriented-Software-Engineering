package addressbook.model;

import java.util.*;

public class Person{
    String name;
    Set<String> emails;

    public Person(String inName){
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
