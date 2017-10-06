import java.util.*;

public class CharacterLoader extends Loader{
    protected void parse_line(String[] line_split){
        int             charac_hp;
        int             charac_type;
        String          charac_name;
        String[]        charac_abs;
        Character       charac;
        AbilityStorage  store = new AbilityStorage();
        try{
            line_checker(line_split);
        } catch(LoadingFileException lfe){
            System.out.println("Error: " + lfe);
        }

        charac_type =   line_split[0].equals('N') ? 1 : 2;
        charac_name =   line_split[1];
        charac_hp   =   line_split[2];
        charac_abs  =   new String[(line_split.length - 1) - 3];

        for(int i = 0; i < abs.length; i++){
            charac_abs[i] = line_split[i + 3];
        }

        charac = new Character(charac_type, charac_name, charac_hp, charac_abs);
    }

    protected void line_checker(String[] line_split) throws LoadingFileException{
        if(line_split.length != 4)
            throw new LoadingFileException("Make sure all and just the information listed in the header is provided");

        if(!line_split[0].equals("N") && !line_split[0].equals("P"))
            throw new LoadingFileException("Make sure the character is either a Non-Playable Type (N) or a Playable Type (P)");

        if(line_split[1].equals("") || line_split[1].equals(" "))
            throw new LoadingFileException("Make sure the character name is not empty or simply whitespace");

        for(int i = 3; i < line_split.length; i++){
            if(line_split[i].equals("") || line_split[i].equals(" "))
                throw new LoadingFileException("Make sure the ability names are not blank or simply whitespaces");

            if(!store.check_ab_exist(line_split[i]))
                throw new LoadingFileException("The following ability does not exist: " + line_split[i] + "\n
                                                Make sure it exists within the Ability file, and that it is\n
                                                formatted properly");
        }

        try{
            if(Integer.parseInt(line_split[2]) < 20)
                throw new LoadingFileException("Make sure HP is above 20");
        } catch(NumberFormatException nfe){
            throw new LoadingFileException("Make sure HP is a whole number");
        }
    }
}
