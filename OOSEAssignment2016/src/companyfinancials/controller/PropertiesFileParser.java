package companyfinancials.controller;

import java.io.*;
import companyfinancials.view.*;
import companyfinancials.model.*;

public class PropertiesFileParser extends ParentReader{
    private FileReader fr;
    private BufferedReader buf;
    private ErrorMessage ErrorMessage = new ErrorMessage();
    private int lineNo = 1;

    //This method instantiates a FileReader and a BufferedReader and
    //sends them, as well as itself, over to the super method read()
    //for reading the file

    public void setYearDomain(int inStart, int inEnd){
        super.setYearDomain(inStart, inEnd);
    }

    public void read(String filename) throws IOException{
        try{
            fr = new FileReader(filename);
            buf = new BufferedReader(fr);
            super.read(this, fr, buf);
        }catch(FileNotFoundException fnfe){
            System.out.println("Could not find file: " + fnfe);
        }finally{
            try{
                fr.close();
                buf.close();
            }catch(IOException ioe){
                System.out.println("The stream could not be closed: " + ioe);
                System.exit(0);
            }
        }
    }

    //Template method implementation
    //Splits the line by the commas, sends it over to checkLine() for checking
    protected void parse(String strParse){
        String[] parsedArr = strParse.split(",", -1);
        if(lineNo > 1){
            if(!checkLine(parsedArr))
            {
                PropertyCollection.createProperty(parsedArr);
            }
        }
        lineNo++;
    }

    //Checks to see if the string array has any empty elements
    //Returns a boolean; true if empty elements are present, false otherwise
    private boolean checkEmptyElement(String[] parsedArr){
        boolean check = false;
        for(String s : parsedArr){
            if(s.equals("")){
                check = true;
            }
        }

        return check;
    }

    //This method checks for invalidities and errors in the passed in line.
    //Returns a boolean; true if line is valid, false otherwise
    protected boolean checkLine(String[] parsedArr){
        boolean toSkip = false;

        //If there is an empty element and there is less than 6 values comma-seperated values
        //then there is too little information

        if(parsedArr.length < 6){
            if(!checkEmptyElement(parsedArr)){
                ErrorMessage.insufficientPropertyInformation();
                toSkip = true;
            }
        }

        else{

            //If there is no name, property type and/or monetary value
            //then there are missing values that are necessary

            if(parsedArr[0].equals("") || parsedArr[1].equals("") || parsedArr[3].equals("")){
                ErrorMessage.missingValues("Name", "Property Type", "Monetary Value");
                toSkip = true;
            }
            else{
                //If the property type letter is anything but B or C, or if there is more than one character
                //then property type is represented by an invalid value

                if((!parsedArr[1].equals("B") && !parsedArr[1].equals("C")) || parsedArr[1].length() != 1){
                    ErrorMessage.invalidPropertyType();
                    toSkip = true;
                }

                //if propery type is B and it has it's revenue and wages field missing, throw an error and mark
                //this line to be skipped
                
                if(parsedArr[1].equals("B")){
                    if(parsedArr[4].equals("") || parsedArr[5].equals("")){
                        ErrorMessage.missingValues("Revenue", "Wages");
                        toSkip = true;
                    }
                }

                //If any of the number values in the line are negative or they are above the boundaries of an int
                //then they are invalid

                for(int i = 0; i < parsedArr.length; i++){
                    if(super.isInteger(parsedArr[i])){
                        if(parsedArr[i].charAt(0) == '-' || Integer.parseInt(parsedArr[i]) > 2147483647){
                            ErrorMessage.negativeOrAboveInt();
                            toSkip = true;
                        }
                    }
                }
            }
        }

        return toSkip;
    }
}
