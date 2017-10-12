import java.util.*;

public class Storage{
    private static HashMap<String, Storable> map = new HashMap<String, Storable>();
    private static HashMap<String, Ability> ab_map = new HashMap<String, Ability>();
    private int num_p = 0;
    private int num_np = 0;

    public void add_to_storage(String name, Storable item){
        map.put(name, item);

        if(item instanceof PlayableCharacter)
            num_p++;
        else
            num_np++;
    }

    public void add_ab_to_storage(String name, Ability ab){
        ab_map.put(name, ab);
    }

    public NonPlayableCharacter get_np_charac(String name){
        NonPlayableCharacter np = (NonPlayableCharacter)map.get(name);
        return np;
    }

    public PlayableCharacter get_p_charac(String name){
        PlayableCharacter np = (PlayableCharacter)map.get(name);
        return np;
    }

    public Ability get_ability(String name){ return this.ab_map.get(name); }

    public void print_all(){
        for(Map.Entry<String, Storable> entry : map.entrySet()){
            System.out.println("Name: " + entry.getValue().get_name());
        }
    }

    public String[] get_all_playable_characters(String[] repo){
        ArrayList<String> arr_list = new ArrayList<String>();
        for(Map.Entry<String, Storable> entry : map.entrySet()){
            if(entry.getValue() instanceof PlayableCharacter)
                arr_list.add(entry.getKey());
        }
        return arr_list.toArray(repo);
    }

    public String[] get_all_nonplayable_characters(String[] repo){
        ArrayList<String> arr_list = new ArrayList<String>();
        for(Map.Entry<String, Storable> entry : map.entrySet()){
            if(entry.getValue() instanceof NonPlayableCharacter)
                arr_list.add(entry.getKey());
        }
        return arr_list.toArray(repo);
    }

    public int get_num_p(){
        return num_p;
    }

    public int get_num_np(){
        return num_np;
    }
}
