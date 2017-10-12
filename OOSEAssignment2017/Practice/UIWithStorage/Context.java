import java.util.*;

public class Context{
    public static void main(String[] args){
        View whoopi = new View();
        String[] pl_names = null;
        String[] non_pl_names = null;

        Ability ab_one = new Ability("Firestorm", 30);
        Ability ab_two = new Ability("Ice Blast", 40);
        Ability ab_three = new Ability("Thundaga", 20);
        Ability ab_four = new Ability("Firaga", 10);
        Ability ab_five = new Ability("Blizzaga", 3);

        PlayableCharacter pl_char_one = new PlayableCharacter("James", 100);
        PlayableCharacter pl_char_two = new PlayableCharacter("Richard", 90);
        PlayableCharacter pl_char_three = new PlayableCharacter("Harry", 80);
        PlayableCharacter pl_char_four = new PlayableCharacter("Michael", 270);
        PlayableCharacter pl_char_five = new PlayableCharacter("Scott", 68888);

        NonPlayableCharacter non_pl_char_one = new NonPlayableCharacter("Nicole", 90);
        NonPlayableCharacter non_pl_char_two = new NonPlayableCharacter("Michelle", 20);
        NonPlayableCharacter non_pl_char_three = new NonPlayableCharacter("Kaytlin", 30);
        NonPlayableCharacter non_pl_char_four = new NonPlayableCharacter("Ashley", 10);
        NonPlayableCharacter non_pl_char_five = new NonPlayableCharacter("Mutuumbuu", 6888868);

        pl_char_one.set_ab_list(new String[]{ab_one.get_name(), ab_three.get_name(), ab_four.get_name()});
        pl_char_two.set_ab_list(new String[]{ab_two.get_name(), ab_five.get_name(), ab_four.get_name()});
        pl_char_three.set_ab_list(new String[]{ab_one.get_name(), ab_three.get_name(), ab_four.get_name()});
        pl_char_four.set_ab_list(new String[]{ab_one.get_name(), ab_two.get_name(), ab_four.get_name()});
        pl_char_five.set_ab_list(new String[]{ab_two.get_name(), ab_three.get_name(), ab_five.get_name()});

        non_pl_char_one.set_ab_list(new String[]{ab_one.get_name(), ab_three.get_name(), ab_four.get_name()});
        non_pl_char_two.set_ab_list(new String[]{ab_two.get_name(), ab_five.get_name(), ab_four.get_name()});
        non_pl_char_three.set_ab_list(new String[]{ab_one.get_name(), ab_three.get_name(), ab_four.get_name()});
        non_pl_char_four.set_ab_list(new String[]{ab_one.get_name(), ab_two.get_name(), ab_four.get_name()});
        non_pl_char_five.set_ab_list(new String[]{ab_two.get_name(), ab_three.get_name(), ab_five.get_name()});

        Storage store = new Storage();

        store.add_ab_to_storage(ab_one.get_name(), ab_one);
        store.add_ab_to_storage(ab_two.get_name(), ab_two);
        store.add_ab_to_storage(ab_three.get_name(), ab_three);
        store.add_ab_to_storage(ab_four.get_name(), ab_four);
        store.add_ab_to_storage(ab_five.get_name(), ab_five);

        store.add_to_storage(pl_char_one.get_name(), pl_char_one);
        store.add_to_storage(pl_char_two.get_name(), pl_char_two);
        store.add_to_storage(pl_char_three.get_name(), pl_char_three);
        store.add_to_storage(pl_char_four.get_name(), pl_char_four);
        store.add_to_storage(pl_char_five.get_name(), pl_char_five);

        store.add_to_storage(non_pl_char_one.get_name(), non_pl_char_one);
        store.add_to_storage(non_pl_char_two.get_name(), non_pl_char_two);
        store.add_to_storage(non_pl_char_three.get_name(), non_pl_char_three);
        store.add_to_storage(non_pl_char_four.get_name(), non_pl_char_four);
        store.add_to_storage(non_pl_char_five.get_name(), non_pl_char_five);

        pl_names = store.get_all_playable_characters(new String[store.get_num_p()]);
        non_pl_names = store.get_all_nonplayable_characters(new String[store.get_num_np()]);

        whoopi.present_option();
        whoopi.print_game(pl_names, non_pl_names);
    }
}
