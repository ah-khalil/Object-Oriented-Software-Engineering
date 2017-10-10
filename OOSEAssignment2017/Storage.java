import java.util.*;

public class Storage implements Subject{
    private static HashMap<String, Character> charac_store = new HashMap<String, Character>();
    private static HashMap<String, Ability> ab_store = new HashMap<String, Ability>();

    public void attach_charac(String charac_name, Character charac){
        charac_store.put(charac_name, charac);
    }

    public void notify(int diff, char target_type){
        if(target_type == 'P'){
            for(Map.Entry<String, Character> entry : charac_store.entrySet()){
                if(entry.getValue() instanceof PlayableCharacter)
                    entry.getValue().update_hp(diff);
            }
        }
        else if(target_type == 'N'){
            for(Map.Entry<String, Character> entry : charac_store.entrySet()){
                if(entry.getValue() instanceof NonPlayableCharacter)
                    entry.getValue().update_hp(diff);
            }
        }
        else
            throw new IllegalArgumentException();
    }

    public void add_ab_to_storage(String ab_name, Ability ab){
        ab_store.put(ab_name, ab);
    }

    public boolean check_charac_exist(String charac_name){
        if(charac_store.get(charac_name) != null)
            return true;
        else
            return false;
    }

    public boolean check_ab_exist(String ab_name){
        if(ab_store.get(ab_name) != null)
            return true;
        else
            return false;
    }

    //to be deleted (in case i forgot to delete it, this is a test thing)
    public void print_all(){
        for(Map.Entry<String, Character> entry : charac_store.entrySet()){
            System.out.println("Character Name: " + entry.getValue().get_name() + "\n\tCharacter Max HP: " + entry.getValue().get_max_hp() + "\n\tCharacter Current HP: " + entry.getValue().get_curr_hp());
            for(int i = 0; i < entry.getValue().get_num_abs(); i++){
                System.out.println("\t\tAbility " + (i + 1) + ": " + entry.getValue().get_abs()[i]);
            }
        }

        for(Map.Entry<String, Ability> entry : ab_store.entrySet()){
            System.out.println("Ability Name: " + entry.getValue().get_name() + "\n\tAbility Base Damage: " + entry.getValue().get_base_hit() + "\n\tAbility Dice Number: " + entry.getValue().get_num_dice() + "\n\tAbility Dice Face Number: " + entry.getValue().get_num_dice_face());
        }
    }
}
