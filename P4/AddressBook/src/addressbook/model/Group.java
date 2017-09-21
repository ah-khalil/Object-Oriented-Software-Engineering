package addressbook.model;

import java.util.*;

public class Group extends FileElements{
    String name;
    List<FileElements> childList;

    public Group(String inName){
        childList = new ArrayList<FileElements>();
        name = inName;
    }

    public String getName(){
        return name;
    }

    public void addChild(FileElements element){
        childList.add(element);
    }
}
