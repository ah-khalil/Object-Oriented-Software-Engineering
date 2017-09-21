package companyfinancials.controller;

import java.io.*;
import companyfinancials.view.*;
import companyfinancials.model.*;

public class PlanFileParser extends ParentReader{
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
        Company[] companyArr = null;
        String[] parsedArr = strParse.split(",", -1);
        boolean toSkip = false;
        int curYear = 0;

        //If the line isn't the label line
        if(lineNo > 1){

            //if the line is in a valid format then set the year in the input at the current year
            if(!checkLine(parsedArr))
                curYear = Integer.parseInt(parsedArr[0]);
            else
                toSkip = true;

            //if this is the first year or the next year, calculate profit already recieved (previous year)
            if(lineNo == 2 || curYear == prevYear + 1){
                PropertyCollection.getPrime().profitCalc();
                companyArr = new Company[PropertyCollection.sizeOfCompany()];
                for(Company entry : PropertyCollection.getAllCompanies().toArray(companyArr)){
                    entry.profitCalc();
                }
            }
            //If the year in the file is within the domain from the command-line arguments...
            if(super.withinDomain(curYear)){

                //If prevYear was set after parsing the first non-label line
                if(prevYear != 0 && !toSkip){

                    //If the year changes are not valid
                    if(!super.yearValidate(curYear, prevYear)){
                        ErrorMessage.invalidYear(curYear, prevYear);
                        curYear = prevYear;
                        toSkip = true;
                    }
                }

                //if the skip flag hasn't been raised then move forward with the plan actions
                //S to sell, B to buy
                if(!toSkip){
                    switch(parsedArr[1]){
                        case "S":   PropertyCollection.getPrime().sell(PropertyCollection.getProperty(parsedArr[2]));
                                    break;
                        case "B":   PropertyCollection.getPrime().buy(PropertyCollection.getProperty(parsedArr[2]));
                                    break;
                    }

                    //if this is the first year or the next year, print the assets of the companies
                    if(lineNo == 2 || curYear == prevYear + 1){
                        printCompanyAssets(curYear);
                    }
                }
                prevYear = curYear;
            }
        }
        lineNo++;
    }

    /*This method checks the line and returns whether or not it should be skipped*/
    protected boolean checkLine(String[] parsedArr){
        boolean toSkip = false;

        //If there isn't three pieces of information in the array
        //then the line doesn't contain sufficient information
        if(parsedArr.length != 3){
            ErrorMessage.missingValues("Year", "Buy/Sell", "Property");
            toSkip = true;
        }

        //If the buy/sell type isn't a sell (S) or a buy (B)
        //then the line isn't in a correct format
        else{
            if(!parsedArr[1].equals("S") && !parsedArr[1].equals("B")){
                ErrorMessage.invalidBuySellType(parsedArr[1]);
                toSkip = true;
            }
        }

        return toSkip;
    }

    //Prints the assets of the companies (including the primary companies)
    private void printCompanyAssets(int inYear){
        Company[] companyArr = new Company[PropertyCollection.sizeOfCompany()];

        System.out.println(inYear + "\n");

        System.out.println("    Primary Company: " + PropertyCollection.getPrime().getName());
        System.out.println("        * Financials: - Balance = " + PropertyCollection.getPrime().getBalance());

        for(Company entry : PropertyCollection.getAllCompanies().toArray(companyArr)){
            System.out.println("    Company: " + entry.getName());
            System.out.println("        * Financials: - Balance = " + entry.getBalance());
        }
    }
}
