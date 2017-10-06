import java.util.*;

public class Storage{
    private static HashMap<String, Integer> hash = new HashMap<String, Integer>();

    public void add_to_storage(String key, int value){
        hash.put(key, new Integer(value));
    }

    public void print_content(){
        String key, value;
        for (Map.Entry<String, Integer> name: hash.entrySet()){
            System.out.println(name.getKey() + " " + name.getValue());
        }
    }
}
