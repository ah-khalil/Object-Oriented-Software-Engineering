import java.util.*;

public class Starter{
    public static void main(String [] args){
        AbilityLoader   ab_load;
        CharacterLoader charac_load;
        Storage         store;

        if(args.length != 2){
            System.out.println("Please only include the ability and character CSV files, in that order");
            System.exit(1);
        }
        ab_load = new AbilityLoader();
        charac_load = new CharacterLoader();
        store = new Storage();

        try{
            ab_load.read_file(args[0]);
            charac_load.read_file(args[1]);
            store.print_all();
        } catch(LoadingFileException lfe){
            System.out.println(lfe);
        } catch(FileInputReadingException fire){
            System.out.println(fire);
        } finally{
            // System.exit(1);
        }
    }
}
