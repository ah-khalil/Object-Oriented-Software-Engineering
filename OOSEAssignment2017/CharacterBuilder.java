import java.util.*;

//Java doesn't have default values so Builder pattern was used to simulate it
public class CharacterBuilder{
    Character ret_char;

    private String type = "N";
    private String name = "N/A";
    private int hp = 20;
    private String[] ab_arr = new String[0];

    public void add_abilities(String[] abs){ this.ab_arr = abs; }
    public void add_type(String type){ this.type = type; }
    public void add_name(String name){ this.name = name; }
    public void add_hp(int hp){ this.hp = hp; }

    public Character build_character(){
        return ret_char = (this.type.equals("N")) ? new NonPlayableCharacter(this.name, this.hp, this.ab_arr) : new PlayableCharacter(this.name, this.hp, this.ab_arr);
    }
}
