import java.util.*;

public class Ability implements Storable{
    private String name;

    public Ability(String name){
        this.name = name;
    }

    public String get_name(){
        return name;
    }
}
