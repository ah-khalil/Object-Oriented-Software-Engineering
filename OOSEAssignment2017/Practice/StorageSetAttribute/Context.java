import java.util.*;

public class Context{
    public static void main(String[] args){
        CharacterStorage char_store = new CharacterStorage();
        AbilityStorage ab_store = new AbilityStorage();
        Character charac = new Character("James", 100);
        Ability ab = new Ability("Fireblast");

        char_store.add_to_storage(charac.get_name(), charac);
        Character from_store = char_store.get_charac("James");
        System.out.println("Pre-Damage Health: " + from_store.get_curr_hp());
        from_store.set_curr_hp(-50);
        System.out.println("Current Health: " + char_store.get_charac("James").get_curr_hp());
    }
}
