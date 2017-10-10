import java.util.*;

public class Character implements Storable{
    private String name;

    public Character(String name){
        this.name = name;
    }

    public String get_name(){
        return name;
    }
}
