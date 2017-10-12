import java.util.*;

//The class representing each ability in the game
public class Ability{
    private Random  rand;
    private String  name;
    private int     base_hit;
    private int     num_dice;
    private int     num_dice_face;
    private int     target_type;
    private int     hit_type;

    public final int SINGLE = 0;
    public final int MULTI = 1;
    public final int DAMAGE = 2;
    public final int HEAL = 3;

    //Alternate Constructor
    public Ability(String name, int base_hit, int num_dice, int num_dice_face, int hit_type, int target_type){
        this.name = name;
        this.base_hit = base_hit;
        this.num_dice = num_dice;
        this.num_dice_face = num_dice_face;
        this.hit_type = (hit_type == 2) ? DAMAGE : HEAL;
        this.target_type = (target_type == 1) ? MULTI : SINGLE;
        this.rand = new Random();
    }

    //Calculate the effect integer to be applied to target
    public int calc_effect(){
        int rand_sum = 0;

        for(int i = 0; i < this.num_dice; i++){
            System.out.println("Num Dice Face: " + this.num_dice_face);
            rand_sum = rand_sum + (rand.nextInt(this.num_dice_face) + 1);
        }

        return (this.base_hit + rand_sum);
    }

    //Setters
    public void set_name(String name){ this.name = name; }
    public void set_base_hit(int base){ this.base_hit = base_hit; }
    public void set_num_dice(int dice){ this.num_dice = num_dice; }
    public void set_num_dice_face(int dice){ this.num_dice_face = num_dice_face; }

    //Getters
    public String get_name(){ return this.name; }
    public int get_hit_type(){ return this.hit_type; }
    public int get_base_hit(){ return this.base_hit; }
    public int get_num_dice(){ return this.num_dice; }
    public int get_target_type(){ return this.target_type; }
    public int get_num_dice_face(){return this.num_dice_face; }
}
