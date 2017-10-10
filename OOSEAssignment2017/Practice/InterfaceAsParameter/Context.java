import java.util.*;

public class Context{
    public static void main(String[] args){
        CharacterStorage char_store = new CharacterStorage();
        AbilityStorage ab_store = new AbilityStorage();
        Character charac = new Character("James");
        Ability ab = new Ability("Fireblast");

        ab_store.add_to_storage(ab.get_name(), ab);
        ab_store.add_to_storage("Timothy", new Character("Timothy"));

        char_store.add_to_storage(charac.get_name(), charac);
        char_store.add_to_storage("Firaga", new Ability("Firaga"));

        System.out.println("===================CHARACTERS==================");
        char_store.print_all();
        System.out.println("=================END CHARACTERS================");

        System.out.println("===================ABILITIES===================");
        ab_store.print_all();
        System.out.println("=================END ABILITIES=================");
    }
}
