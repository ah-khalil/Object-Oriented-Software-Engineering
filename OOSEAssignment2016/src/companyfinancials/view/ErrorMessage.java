package companyfinancials.view;

public class ErrorMessage{
    //ERROR MESSAGES FOR PARENT READER

    public static void domainError(int start, int end){
        System.out.println("ERROR: Please ensure that the years in the input file are between " + start + " and " + end + "\n");
    }

    //ERROR MESSAGES FOR ALL FILE PARSERS

    public static void invalidYear(int curYear, int prevYear){
        System.out.println("ERROR: Please ensure that the years in the Plan and Event file only change by +1 and that they fall in between (inclusively) the period years specified");
    }

    public static void missingValues(String ... obligValues){
        System.out.println("ERROR: The input property file is missing mandatory values; please ensure that the following values are present: ");

        for(String s : obligValues){
            System.out.println("    - " + s);
        }
    }

    //ERROR MESSAGES FOR PROPERTIES FILE PARSER

    public static void invalidPropertyType(){
        System.out.println("ERROR: The input property types should only be either C or B \n");
    }

    public static void negativeOrAboveInt(){
        System.out.println("ERROR: Value, Revenue and Wage should all be positive integers [0 - 2,147,483,647] \n");
    }

    public static void insufficientPropertyInformation(){
        System.out.println("ERROR: Please ensure that all information is present in the file\n(If owner is unknown and/or revenue and wages belong to a company, ensure that this information is represented by \",,\") \n");
    }

    //ERROR MESSAGES FOR EVENT FILE PARSER

    public static void invalidPropertyWageInfo(){
        System.out.println("ERROR: Wages affect every property; please don't specify anything in that column \n");
    }

    public static void invalidEventType(String eventChar){
        System.out.println("ERROR: This event type: " + eventChar + " is invalid. Please select from R-/+, W-/+ or V-/+ \n");
    }

    //ERROR MESSAGES FOR PLAN FILE PARSER

    public static void invalidBuySellType(String bsChar){
        System.out.println("ERROR: This buy/sell type: " + bsChar + " is invalid. Please select from S or B \n");
    }

    //ERROR MESSAGES FOR COMPANY

    public static void alreadyBoughtProperty(String inName){
        System.out.println("ERROR: The primary company has already purchased the following company: " + inName + "\n");
    }

    public static void notOwner(String inName){
        System.out.println("ERROR: The following property is not owned: " + inName + "\n");
    }
}
