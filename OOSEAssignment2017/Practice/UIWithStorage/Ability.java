import java.util.Random;

public class Ability implements Storable{
    private Random rand = new Random();
    private String name;
    private int dmg;

    public Ability(String name, int base_dmg){
        this.name = name;
        this.dmg = base_dmg;
    }

    public String get_name(){
        return this.name;
    }

    public int damage_calc(){
        return this.dmg * rand.nextInt(4) + 1;
    }
}
