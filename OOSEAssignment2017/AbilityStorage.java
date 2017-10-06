import java.util.*;

public class AbilityStorage{
    private static HashMap<String, Ability> ab_store = new HashMap<String, Ability>();

    public void add_to_storage(String ab_name, Ability ab){
        hash.put(ab_name, ab);
    }

    public boolean check_ab_exist(ab_name){
        if(hash.get(ab_name) != null)
            return true;
        else
            return false;
    }

    public void print_all(){
        for(Map.Entry<String, Ability> entry : hash.entrySet()){
            System.out.println("Ability Name: " + entry.getKey());
        }
    }
}
