import java.util.*;

public class Ability{
    private String  name;
    private int     base_hit;
    private int     num_dice;
    private int     num_dice_face;
    private int     target_type;
    private int     hit_type;

    private final int SINGLE = 0;
    private final int MULTI = 1;
    private final int DAMAGE = 2;
    private final int HEAL = 3;

    public Ability(String name, int base_hit, int num_dice, int num_dice_face, int target_type, int hit_type){
        this.name = name;
        this.base_hit = base_hit;
        this.num_dice = num_dice;
        this.num_dice_face = num_dice_face;
        this.hit_type = (hit_type == 2) ? DAMAGE : HEAL;
        this.target_type = (target_type == 1) ? MULTI : SINGLE;
    }

    public void set_name(String name){ this.name = name; }
    public void set_base_hit(int base){ this.base_hit = base_hit; }
    public void set_num_dice(int dice){ this.num_dice = num_dice; }
    public void set_num_dice_face(int dice){ this.num_dice_face = num_dice_face; }
    public void set_hit_type(int type){ this.hit_type = (hit_type == 2) ? DAMAGE : HEAL; }
    public void set_target_type(int type){ this.target_type = (target_type == 1) ? MULTI : SINGLE; }

    //Getters
    public String get_name(){ return this.name; }
    public int get_hit_type(){ return this.hit_type; }
    public int get_base_hit(){ return this.base_hit; }
    public int get_num_dice(){ return this.num_dice; }
    public int get_target_type(){ return this.target_type; }
    public int get_num_dice_face(){return this.num_dice_face; }
}
