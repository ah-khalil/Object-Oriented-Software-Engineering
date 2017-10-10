import java.util.*;

public class AbilityLoader extends Loader{
    protected void parse_line(String[] line_split){
        int     ab_type;
        int     ab_tgt_type;
        int     ab_num_dice;
        int     ab_num_face;
        int     ab_base;
        String  ab_name;
        Ability ab;
        Storage store = new Storage();

        // System.out.println(line_split[0]);
        // System.out.println(line_split[1]);
        // System.out.println(line_split[2]);
        // System.out.println(line_split[3]);
        // System.out.println(line_split[4]);
        // System.out.println(line_split[5]);

        try{
            line_checker(line_split, store);
        } catch(LoadingFileException lfe){
            System.out.println("Error: " + lfe);
        } finally{
            //System.exit(1);
        }

        ab_type     =   line_split[0].equals("D") ? 3 : 4;
        ab_name     =   line_split[1];
        ab_tgt_type =   line_split[2].equals("S") ? 1 : 2;
        ab_base     =   Integer.parseInt(line_split[3]);
        ab_num_dice =   Integer.parseInt(line_split[4]);
        ab_num_face =   Integer.parseInt(line_split[5]);

        ab = new Ability(ab_name, ab_base, ab_num_dice, ab_num_face, ab_type, ab_tgt_type);
        store.add_ab_to_storage(ab_name, ab);
    }

    protected void line_checker(String[] line_split, Storage store) throws LoadingFileException{
        if(line_split.length != 6)
            throw new LoadingFileException("Make sure all and just the information listed in the header is provided");

        if(!line_split[0].equals("D") && !line_split[0].equals("H"))
            throw new LoadingFileException("Make sure the ability is either a Damage Type (D) or a Healing Type (H)");

        if(line_split[1].equals("") || line_split[1].equals(" "))
            throw new LoadingFileException("Make sure the ability name is not empty or simply whitespace");

        if(!line_split[2].equals("M") && !line_split[2].equals("S"))
            throw new LoadingFileException("Make sure the ability is either a Single-Target Type (S) or a Multi-Target Type (M)");

        try{
            Integer.parseInt(line_split[3]);
            Integer.parseInt(line_split[4]);
            Integer.parseInt(line_split[5]);

            if(store.check_ab_exist(line_split[1]))
                throw new LoadingFileException("Make sure the following ability is not a duplicate: " + line_split[1]);
        } catch(NumberFormatException nfe){
            throw new LoadingFileException("Make sure the ability base damage, and number of dice and faces are represented as whole numbers");
        }
    }
}
