import java.util.*;

public abstract class Loader{
    String[] final char_column_info = {
        "Type",
        "Name",
        "HP",
        "Abilities"
    };

    String[] final ab_column_info = {
        "Type",
        "Name",
        "Target",
        "Base",
        "NumDice",
        "Faces"
    };

    public void read_file(String file) throws Exception, FileInputReadingException{
        String[] line_split = null;
        String line = null;
        FileReader fr;
        BufferedReader br;

        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            line_split = br.readLine().split(",");

            if(line_split.length == char_column_info)
                check_header(char_column_info, line_split, "Character");
            else if (line_split.length == ab_column_info.length)
                check_header(ab_column_info, line_split, "Ability");
            else
                throw new Exception("Invalid File: Make sure the file headers represent the type of file they're in");

        } catch(Exception e){
            System.out.println("Exception: " + e);
        } catch(IOException ioe){
            throw new FileInputReadingException("Error reading file");
        } finally{
            br.close();
            fr.close();

            catch(IOException ioe2){
                throw new FileInputReadingException("Error reading file");
            }
        }
    }

    private void check_header(String final_arr, String to_check_arr, String file_type) throws Exception{
        for(int i = 0; i < final_arr.length; i++){
            if(!to_check_arr[i].equals(final_arr[i]))
                throw new Exception(file_type + " File - Invalid Header: Found " + to_check_arr[i] + " where " + final_arr[i] + " should be");
        }
    }

    protected abstract void parse_line(String[] line_split);
}
