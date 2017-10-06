import java.util.*;

public class AbilityLoader extends Loader{
    protected void parse_line(String[] line_split){
        int     ab_type;
        int     ab_tgt_type;
        int     ab_num_dice;
        int     ab_num_face;
        double  ab_base;
        String  ab_name;
        Ability ab;
        AbilityStorage store = new AbilityStorage();

        try{
            line_checker(line_split);
        } catch(LoadingFileException lfe){
            System.out.println("Error: " + lfe);
        }

        ab_type     =   line_split[0].equals('D') ? 1 : 2;
        ab_name     =   line_split[1];
        ab_tgt_type =   line_split[2].equals("S") ? 1 : 2;
        ab_base     =   line_split[3];
        ab_num_dice =   line_split[4];
        ab_num_face =   line_split[5];

        ab = new Ability(ab_type, ab_name, ab_tgt_type, ab_base, ab_num_dice, ab_num_face);
        store.add_to_storage(ab_name, ab);
    }

    protected void line_checker(String[] line_split) throws LoadingFileException{
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

            if(!store.check_ab_exist(line_split[1]))
                throw new DuplicateAbilityException();
        } catch(NumberFormatException nfe){
            throw new LoadingFileException("Make sure the ability base damage, and number of dice and faces are represented as whole numbers");
        } catch(DuplicateAbilityException dae){
            throw new LoadingFileException(dae);
        }
    }
}
