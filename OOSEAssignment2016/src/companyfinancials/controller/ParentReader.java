package companyfinancials.controller;

import java.io.*;
import java.util.*;
import companyfinancials.view.*;

public abstract class ParentReader{
    private ParentReader pr = null;
    private static int startYear;
    private static int endYear;

    //Template Method
    //This method, in the child classes, parse the line from the file sent to it by this class
    protected abstract void parse(String strParse);

    //This method, in the child classes, validates the format of the line
    protected abstract boolean checkLine(String[] parsedArr);

    //This method reads in the file from the stream given by one of the child classes
    //and gives it back to parse
    protected void read(ParentReader pr, FileReader fr, BufferedReader buf) throws FileNotFoundException, IOException{
        String str = null;

        while((str = buf.readLine()) != null){
            pr.parse(str);
        }
    }

    //if the start year is earlier than the end year then set
    //the year variables with the inputs
    protected void setYearDomain(int inStart, int inEnd){
        if(inStart <= inEnd){
            startYear = inStart;
            endYear = inEnd;
        }
        else
            ErrorMessage.domainError(startYear, endYear);
    }

    public int getStartYear(){
        return startYear;
    }

    public int getEndYear(){
        return endYear;
    }

    //Checks to see if the string could validly be represented as an integer
    //Returns a boolean; true if it can, false otherwise
    protected boolean isInteger(String s){
        boolean isInt = false;

        try{
            Integer.parseInt(s);
            isInt = true;
        }catch(NumberFormatException nfe){
            //Empty because the string is not an int
        }

        return isInt;
    }

    //if the current year is within domain [starting year, ending year]
    public boolean withinDomain(int curYear){
        return (curYear >= startYear && curYear < endYear + 1);
    }

    //This method validates the changes of the year
    public boolean yearValidate(int curYear, int prevYear){
        return ((curYear == prevYear || curYear == prevYear + 1));
    }
}
