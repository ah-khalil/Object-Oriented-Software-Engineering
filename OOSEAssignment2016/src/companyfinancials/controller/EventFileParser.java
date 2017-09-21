package companyfinancials.controller;

import java.io.*;
import companyfinancials.view.*;
import companyfinancials.model.*;

public class EventFileParser extends ParentReader{
    private FileReader fr;
    private BufferedReader buf;
    private int lineNo = 1;
    private int prevYear = 0;

    //Use ParentReader's read instead of constructing one here
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
        BusinessUnit[] bArr = null;
        double multiplier = 0.0;
        boolean toSkip = false;
        Property temp = null;
        int curYear = 0;

        //If the line isn't the label line
        if(lineNo > 1){

            //Immediately check if the line is in the valid format
            if(!checkLine(parsedArr))
                curYear = Integer.parseInt(parsedArr[0]);
            else
                toSkip = true;

            //If the year in line is within the domain from the arguments...
            if(super.withinDomain(curYear)){

                //If prevYear was set after parsing the first non-label line
                    if(prevYear != 0 && !toSkip){

                    //If the year changes are not valid
                    if(!super.yearValidate(curYear, prevYear)){
                        ErrorMessage.invalidYear(curYear, prevYear);
                        toSkip = true;
                    }
                    else
                        curYear = Integer.parseInt(parsedArr[0]);
                }

                if(!toSkip && !checkLine(parsedArr)){
                    multiplier = (parsedArr[1].charAt(1) == '+') ? 1.05 : 0.95;

                    //A set of procedures to follow according to the letter in the line
                    switch(parsedArr[1].charAt(0)){
                        case 'R':   PropertyCollection.getBusiness(parsedArr[2]).setRevenue(multiplier);
                                    break;
                        case 'W':   bArr = new BusinessUnit[PropertyCollection.sizeOfBusiness()];
                                    for(BusinessUnit entry : PropertyCollection.getAllBusinesses().toArray(bArr)){
                                        entry.setWages(multiplier);
                                    }
                                    break;
                        case 'V':   PropertyCollection.getProperty(parsedArr[2]).setValue(multiplier);
                                    break;
                        default:    ErrorMessage.invalidEventType(parsedArr[1]);
                                    break;
                    }
                    prevYear = curYear;
                }
            }
        }
        lineNo++;
    }

    protected boolean checkLine(String[] parsedArr){
        boolean toSkip = false;

        //Note: According to the file format, there should be a comma after the Event type, even if
        //is a Wage change with no specifying property. This should make String.split create an empty
        //element appended to the back when delimited by commas (String.split(",", -1))

        //If there isn't three pieces of information in the array
        //then the line doesn't contain sufficient information

        if(parsedArr.length != 3){
            ErrorMessage.missingValues("Year", "Event", "Property (or not if you're changing wages)");
            toSkip = true;
        }

        //If the Event type is either R-/+ or V-/+ and there is no property to apply the event to
        //then the line doesn't contain sufficient information

        else{
            if(parsedArr[1].charAt(0) == 'R' || parsedArr[1].charAt(0) == 'V'){
                if(parsedArr[2].equals("")){
                    ErrorMessage.missingValues("Property");
                    toSkip = true;
                }
            }

            //If the Event type is W-/+ and their is a specifying property
            //then the line doesn't follow the format

            else if(parsedArr[1].charAt(0) == 'W'){
                if(!parsedArr[2].equals("")){
                    ErrorMessage.invalidPropertyWageInfo();
                    toSkip = true;
                }
            }

            //If there is a letter other than the ones aforementioned
            //then the line doesn't follow the format

            else{
                ErrorMessage.invalidEventType(parsedArr[1]);
                toSkip = true;
            }
        }

        return toSkip;
    }
}
