import java.util.*;

//The loader that validates and places characters into the storage
public class CharacterLoader extends Loader{
    //Implementation of template method
    protected void parse_line(String[] line_split){
        int                 charac_hp;
        String              charac_type;
        String              charac_name;
        String[]            charac_abs;
        Character           charac;
        Storage             store = new Storage();
        CharacterBuilder    charac_build;

        //Validate the lines and catch LoadingFileException thrown from invalid lines, then exit
        try{
            line_checker(line_split, store);
        } catch(LoadingFileException lfe){
            System.out.println("Error: " + lfe);
        } finally{
            System.exit(1);
        }

        charac_type =   line_split[0];
        charac_name =   line_split[1];
        charac_hp   =   Integer.parseInt(line_split[2]);

        //because a character may or may not have abilities, and Java doesn't have default parameters, Builder method was used to simulate it
        charac_build = new CharacterBuilder();

        //separates the construction of the character to the Builder class
        charac_build.add_type(charac_type);
        charac_build.add_name(charac_name);
        charac_build.add_hp(charac_hp);

        if(line_split.length > 3){
            charac_abs = new String[(line_split.length) - 3];

            for(int i = 0; i < charac_abs.length; i++){
                charac_abs[i] = line_split[i + 3];
            }

            charac_build.add_abilities(charac_abs);
        }

        //create Character and insert into storage
        charac = charac_build.build_character();
        store.attach_charac(charac.get_name(), charac);
    }

    //Validates the line given
    protected void line_checker(String[] line_split, Storage store) throws LoadingFileException{
        if(line_split.length < 3)
            throw new LoadingFileException("Make sure the type, name and HP are provided");

        if(!line_split[0].equals("N") && !line_split[0].equals("P"))
            throw new LoadingFileException("Make sure the character is either a Non-Playable Type (N) or a Playable Type (P)");

        if(line_split[1].equals("") || line_split[1].equals(" "))
            throw new LoadingFileException("Make sure the character name is not empty or simply whitespace");

        if(store.check_charac_exist(line_split[1]))
            throw new LoadingFileException("Make sure the following character is not a duplicate: " + line_split[1]);

        //Validates ability fields
        if(line_split.length > 3){
            for(int i = 3; i < line_split.length; i++){
                if(line_split[i].equals("") || line_split[i].equals(" "))
                    throw new LoadingFileException("Make sure the ability names are not blank or simply whitespaces");

                if(!store.check_ab_exist(line_split[i]))
                    throw new LoadingFileException("The following ability does not exist: " + line_split[i] + "\nMake sure it exists within the Ability file, and that it is\nformatted properly");
            }
        }

        try{
            if(Integer.parseInt(line_split[2]) < 20)
                throw new LoadingFileException("Make sure HP is above 20");
        } catch(NumberFormatException nfe){
            throw new LoadingFileException("Make sure HP is a whole number");
        }
    }
}
