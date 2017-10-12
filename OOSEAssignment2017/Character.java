import java.util.*;

//Abstract class representing all the commonalities between all types of Characters
public abstract class Character{
    protected String name;
    protected int hp;
    protected ArrayList<String> abilities;
    protected int current_hp;
    protected int state;

    //States of the Character
    protected final int DEAD = 0;
    protected final int ALIVE = 1;

    //Alternate Constructor
    public Character(String name, int hp, String[] abs){
        this.name = name;
        this.hp = hp;
        this.current_hp = hp;
        this.abilities = new ArrayList<String>();
        this.state = ALIVE;

        this.add_abs(abs);
    }

    //Observer method - calls subclasses
    public abstract void update_hp(int diff);

    //Adds the strings found in the array import into the class ArrayList
    public void add_abs(String[] abs){
        for(String name : abs){
            this.abilities.add(name);
        }
    }

    //Getters
    public String get_name(){ return this.name; }
    public int get_max_hp(){ return this.hp; }
    public int get_curr_hp(){ return this.current_hp; }
    public int get_num_abs(){ return this.abilities.size(); }
    public String[] get_abs(){ return this.abilities.toArray(new String[this.abilities.size()]); }
    public int get_state(){ return this.state; }

    //Setters
    public void set_name(String name){ this.name = name; }
    public void set_max_hp(int hp){ this.hp = hp; }
}
