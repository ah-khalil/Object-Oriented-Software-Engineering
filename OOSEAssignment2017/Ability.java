import java.util.*;

public interface Ability{
    public String get_name();
    public int get_is_multi(); //using an enum
    public int get_target_type(); //using an enum
    public float get_base_hit();
    public int get_num_dice();
    public int get_num_dice_face();
}
