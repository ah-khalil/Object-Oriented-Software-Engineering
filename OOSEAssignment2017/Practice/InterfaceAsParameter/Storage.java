import java.util.*;

public abstract class Storage{
    HashMap<String, Storable> map = new HashMap<String, Storable>();

    public void add_to_storage(String name, Storable item){
        map.put(name, item);
    }

    public void print_all(){
        for(Map.Entry<String, Storable> entry : map.entrySet()){
            System.out.println("Name: " + entry.getValue().get_name());
        }
    }
}
