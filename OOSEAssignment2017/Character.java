import java.util.*;

public abstract class Character{
    protected String name;
    protected int hp;
    protected ArrayList<String> abilities;
    protected int current_hp;

    public Character(String name, int hp, String[] abs){
        this.name = name;
        this.hp = hp;
        this.current_hp = hp;
        this.abilities = new ArrayList<String>();

        this.add_abs(abs);
    }

    public abstract void update_hp(int diff);

    public void add_abs(String[] abs){
        for(String name : abs){
            this.abilities.add(name);
        }
    }

    public String get_name(){ return this.name; }
    public int get_max_hp(){ return this.hp; }
    public int get_curr_hp(){ return this.current_hp; }
    public int get_num_abs(){ return this.abilities.size(); }
    public String[] get_abs(){ return this.abilities.toArray(new String[this.abilities.size()]); }

    public void set_name(String name){ this.name = name; }
    public void set_max_hp(int hp){ this.hp = hp; }
}
