import java.util.*;

//Represents the Non Playable Character type of Character
public class NonPlayableCharacter extends Character{
    public NonPlayableCharacter(String name, int hp, String[] abs){ super(name, hp, abs); }

    //The method to be called to set the HP of the character (used for observer pattern)
    public void update_hp(int diff){
        super.current_hp = super.current_hp + diff;

        //Cap the increase at the max HP of the character
        if(super.current_hp > super.hp)
            super.current_hp = super.hp;

        //Floor the decrease at 0 (can't be any more deader than 0)
        else if(super.current_hp < 0){
            super.current_hp = 0;
            super.state = super.DEAD;
        }
    }
}
