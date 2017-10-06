import java.util.*;

public class CharacterFactory{
    public Character charac_factory(String type, String name, int hp, String[] abs){
        Character ret_char;

        if(type.equals("N"))
            ret_char = NonPlayableCharacter(name, hp, abs);
        else
            ret_char = PlayableCharacter(name, hp, abs);
    }
}
