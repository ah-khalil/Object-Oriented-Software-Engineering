import java.util.*;

//Represents the class within which the characters and ability will be stored
public class Storage implements Subject{

    //static in order to preserve data across all instantiations of Storage but private in order to prevent excessive meddling
    private static HashMap<String, Character> charac_store = new HashMap<String, Character>();
    private static HashMap<String, Ability> ab_store = new HashMap<String, Ability>();
    private static int num_p = 0;
    private static int num_np = 0;
    private View view = new View();

    //Places character into the HashMap storage
    public void attach_charac(String charac_name, Character charac){
        charac_store.put(charac_name, charac);

        if(charac instanceof PlayableCharacter)
            num_p++;
        else
            num_np++;
    }

    //Part of the observer pattern; calls all characters of a particular type and updates their HP
    public void notify(int diff, char target_type) throws IllegalArgumentException{
        if(target_type == 'P'){
            for(Map.Entry<String, Character> entry : charac_store.entrySet()){
                if(entry.getValue() instanceof PlayableCharacter){
                    entry.getValue().update_hp(diff);

                    if(diff < 0)
                        view.print_to_console_break(entry.getKey() + " was hit for " + diff + "HP");
                    else
                        view.print_to_console_break(entry.getKey() + " was healed for " + diff + "HP");
                }
            }
        }
        else if(target_type == 'N'){
            for(Map.Entry<String, Character> entry : charac_store.entrySet()){
                if(entry.getValue() instanceof NonPlayableCharacter){
                    entry.getValue().update_hp(diff);

                    if(diff < 0)
                        view.print_to_console_break(entry.getKey() + " was hit for " + diff + "HP");
                    else
                        view.print_to_console_break(entry.getKey() + " was healed for " + diff + "HP");
                }
            }
        }
        else
            throw new IllegalArgumentException();
    }

    //Returns the specified Character to the caller
    public Character get_charac(String name, char type) throws IllegalArgumentException{
        if(check_charac_exist(name)){
            if(type == 'N')
                return (NonPlayableCharacter)this.charac_store.get(name);
            else if(type == 'P')
                return (PlayableCharacter)this.charac_store.get(name);
            else
                throw new IllegalArgumentException();
        }
        else
            throw new IllegalArgumentException();
    }

    //Returns the specified Ability to the caller
    public Ability get_ability(String name) throws IllegalArgumentException{
        if(check_ab_exist(name))
            return ab_store.get(name);
        else
            throw new IllegalArgumentException();
    }

    //Adds ability to the storage
    public void add_ab_to_storage(String ab_name, Ability ab){
        ab_store.put(ab_name, ab);
    }

    //Verifies if a Character (using the input string) already exists within the HashMap
    public boolean check_charac_exist(String charac_name){
        if(charac_store.get(charac_name) != null)
            return true;
        else
            return false;
    }

    //Verifies if a Ability (using the input string) already exists within the HashMap
    public boolean check_ab_exist(String ab_name){
        if(ab_store.get(ab_name) != null)
            return true;
        else
            return false;
    }

    //Returns an array of either all the Non Playable Characters or Playable Characters (based on the type given)
    public String[] get_all_character_names(char type) throws IllegalArgumentException{
        String[] ret_arr = null;
        int idx = 0;

        if(type == 'N'){
            ret_arr = new String[get_num_np()];
            for(Map.Entry<String, Character> entry : charac_store.entrySet()){
                if(entry.getValue() instanceof NonPlayableCharacter){
                    ret_arr[idx] = entry.getKey();
                    idx++;
                }
            }
        }

        else if(type == 'P'){
            ret_arr = new String[get_num_p()];
            for(Map.Entry<String, Character> entry : charac_store.entrySet()){
                if(entry.getValue() instanceof PlayableCharacter){
                    ret_arr[idx] = entry.getKey();
                    idx++;
                }
            }
        }

        else{ throw new IllegalArgumentException(); }

        return ret_arr;
    }

    //Returns number of Playable or NonPlayable Characters in the Character HashMap
    public int get_num_p(){ return num_p; }
    public int get_num_np(){ return num_np; }
}
