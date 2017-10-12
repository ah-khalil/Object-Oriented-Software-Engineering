import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

//Parent loader class that represents the commonalities between all loader types
public abstract class Loader{
    //the expected headers in each input file
    private final String[] char_column_info = {
        "Type",
        "Name",
        "HP",
        "Abilities"
    };

    private final String[] ab_column_info = {
        "Type",
        "Name",
        "Target",
        "Base",
        "NumDice",
        "Faces"
    };

    //Reads and seperates the lines by commas, sends it to parse_line for validating
    public void read_file(String file) throws LoadingFileException, FileInputReadingException{
        String[] line_split = null;
        String line = null;
        FileReader fr = null;
        BufferedReader br = null;

        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            line_split = br.readLine().split(",");

            if(line_split.length == char_column_info.length)
                check_header(char_column_info, line_split, "Character");
            else if (line_split.length == ab_column_info.length)
                check_header(ab_column_info, line_split, "Ability");
            else
                throw new LoadingFileException("Invalid File: Make sure the file headers represent the type of file they're in");

            while((line = br.readLine()) != null){
                line_split = line.split(",");

                parse_line(line_split);
            }

        } catch(LoadingFileException lfe){
            System.out.println("Error: " + lfe);
        } catch(FileNotFoundException fnfe){
            System.out.println("Could not find file");
        } catch(IOException ioe){

        } finally{
            try{
                if(br != null)
                    br.close();
                if(fr != null)
                    fr.close();
            } catch(IOException ioe2){
                throw new FileInputReadingException("Error closing file");
            }
        }
    }

    //checks to see if the header of input file matches what is to be expected
    private void check_header(String[] final_arr, String[] to_check_arr, String file_type) throws LoadingFileException{
        for(int i = 0; i < final_arr.length; i++){
            if(!to_check_arr[i].equals(final_arr[i]))
                throw new LoadingFileException(file_type + " File - Invalid Header: Found " + to_check_arr[i] + " where " + final_arr[i] + " should be");
        }
    }

    //Template methods to be implemented by child classes
    protected abstract void parse_line(String[] line_split);
    protected abstract void line_checker(String[] line_split, Storage store) throws LoadingFileException;
}
