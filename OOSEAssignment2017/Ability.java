import java.util.*;

public interface Ability{
    //Setters
    public void set_target_type(int type);
    public void set_name(String name);
    public void set_target_rule(int rule);
    public void set_base_hit(double base);
    public void set_num_dice(int dice);
    public void set_num_dice_face(int dice);

    //Getters
    public int get_target_type(); //using an enum
    public String get_name();
    public int get_target_rule(); //using an enum
    public double get_base_hit();
    public int get_num_dice();
    public int get_num_dice_face();
}
