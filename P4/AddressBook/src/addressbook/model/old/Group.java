package addressbook.model;

import java.util.*;
import static addressbook.view.UserInterface.*;

public class Group{
    private class GroupNode{
        String groupName;
        GroupNode leftchild;
        GroupNode rightchild;

        public GroupNode(String inName){
            groupName = inName;
        }

        public void setLeft(GroupNode left){
            leftchild = left;
        }

        public void setRight(GroupNode right){
            rightchild = right;
        }

        public GroupNode getLeft(){
            return leftchild;
        }

        public GroupNode getRight(){
            return rightchild;
        }
    }

    private class EntryNode{
        String name;
        Set<String> emailSet;

        public EntryNode(String inName){
            name = inName;
            emailSet = new HashSet<String>();
        }

        public void addEmail(String email){
            emailSet.add();
        }

        public String getEmail(){

        }
    }
}
