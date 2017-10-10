import java.util.*;

public class PlayableCharacter extends Character{
    public PlayableCharacter(String name, int hp, String[] abs){ super(name, hp, abs); }

    public void update_hp(int diff){
        super.hp = super.hp + diff;
    }
}
