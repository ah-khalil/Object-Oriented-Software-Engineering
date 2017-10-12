import java.util.*;

public class NonPlayableCharacter implements Storable{
    private String      name;
    private int         max_hp;
    private int         curr_hp;
    private String[]    ab_list;

    public NonPlayableCharacter(String name, int max_hp){
        this.name = name;
        this.max_hp = max_hp;
        this.curr_hp = max_hp;
    }

    public String get_name(){
        return name;
    }

    public int get_max_hp(){ return this.max_hp; }
    public int get_curr_hp(){ return this.curr_hp; }
    public String[] get_ab_list(){ return this.ab_list; }
    public void set_curr_hp(int diff){
        this.curr_hp = this.curr_hp + diff;
    }
    public void set_ab_list(String[] abs){
        this.ab_list = abs;
    }
}
