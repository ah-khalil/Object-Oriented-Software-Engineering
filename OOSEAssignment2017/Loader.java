import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public abstract class Loader{
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

    public void read_file(String file) throws LoadingFileException, FileInputReadingException{
        String[] line_split = null;
        String line = null;
        FileReader fr = null;
        BufferedReader br = null;

        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            line_split = br.readLine().split(",");

            //System.out.println("Length of Line split: " + line_split.length);

            if(line_split.length == char_column_info.length)
                check_header(char_column_info, line_split, "Character");
            else if (line_split.length == ab_column_info.length)
                check_header(ab_column_info, line_split, "Ability");
            else
                throw new LoadingFileException("Invalid File: Make sure the file headers represent the type of file they're in");

            while((line = br.readLine()) != null){
                line_split = line.split(",");

                // for(String el : line_split){
                //     System.out.println("Line Element: " + el);
                // }

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

    private void check_header(String[] final_arr, String[] to_check_arr, String file_type) throws LoadingFileException{
        for(int i = 0; i < final_arr.length; i++){
            //System.out.println("String Split: " + to_check_arr[i]);
            if(!to_check_arr[i].equals(final_arr[i]))
                throw new LoadingFileException(file_type + " File - Invalid Header: Found " + to_check_arr[i] + " where " + final_arr[i] + " should be");
        }
    }

    protected abstract void parse_line(String[] line_split);
    protected abstract void line_checker(String[] line_split, Storage store) throws LoadingFileException;
}
