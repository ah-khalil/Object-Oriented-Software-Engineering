import java.util.*;

public abstract class Character{
    private String name;
    private int hp;
    private ArrayList<String> abilities;
    private int current_hp;

    public Character(String name, int hp, String[] abs){
        this.name = name;
        this.hp = hp;
        this.current_hp = hp;
        this.abilities = new ArrayList<String>;

        this.add_abs(abs);
    }

    public void add_abs(String[] abs){
        for(String name : abs){
            this.abilities.add(name);
        }
    }

    get_name    =   () -> { return this.name; };
    get_max_hp  =   () -> { return this.hp; };
    get_num_abs =   () -> { return this.abilities.size(); };
    get_abs     =   () -> { return this.abilities.toArray(); };

    set_name    =   (String name)   -> { this.name = name; };
    set_max_hp  =   (int hp)        -> { this.hp = hp; };
    set_curr_hp =   (int hp_diff)   -> { this.current_hp = this.current_hp + hp_diff; };


}
