import java.util.*;

public class NonPlayableCharacter extends Character{
    public NonPlayableCharacter(String name, int hp, String[] abs){ super(name, hp, abs); }

    public void update_hp(int diff){
        super.hp = super.hp + diff;
    }
}
