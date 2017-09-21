package companyfinancials.model;

import java.util.*;

public class PropertyCollection{
    private static PrimaryCompany prime = null;
    private static Map<String, Company> companyCollection = new HashMap<String, Company>();
    private static Map<String, BusinessUnit> businessCollection = new HashMap<String, BusinessUnit>();

    //GETTERS
    public static Property getProperty(String inName){
        Property temp = getBusiness(inName);
        return (temp == null) ? getCompany(inName) : getBusiness(inName);
    }

    public static Company getCompany(String inName){
        return companyCollection.get(inName);
    }

    public static BusinessUnit getBusiness(String inName){
        return businessCollection.get(inName);
    }

    public static Collection<Company> getAllCompanies(){
        return companyCollection.values();
    }

    public static Collection<BusinessUnit> getAllBusinesses(){
        return businessCollection.values();
    }


    public static PrimaryCompany getPrime(){
        return prime;
    }

    //This factory method is reponsible for creating the objects and placing them into the appropriate structure
    public static void createProperty(String[] parsedArr){
        Property retProperty = null;

        /*If the property type is labelled 'B' then create a business unit and place it in the Business unit list*/
        if(parsedArr[1].equals("B")){
            retProperty = new BusinessUnit(parsedArr[0], parsedArr[2], Double.parseDouble(parsedArr[3]), Double.parseDouble(parsedArr[4]), Double.parseDouble(parsedArr[5]));
            businessCollection.put(parsedArr[0], (BusinessUnit)retProperty);
        }

        /*If the property type is labelled 'C' then, if the prime flag hasn't been raised, then create the primary company
        and set it as the primary company. If the flag has been raised then create an ordinary company*/
        else if(parsedArr[1].equals("C")){
            if(prime != null){
                retProperty = new Company(parsedArr[0], parsedArr[2], Double.parseDouble(parsedArr[3]));
                companyCollection.put(parsedArr[0], (Company)retProperty);
            }
            else{
                prime = new PrimaryCompany(parsedArr[0], parsedArr[2], Double.parseDouble(parsedArr[3]));
            }
        }

        /*place the created property into the company that owns it (if it is owned by something)*/
        addToCompany(retProperty, parsedArr[2]);
    }

    public static void addToCompany(Property toAddProp, String parentCompanyName){
        if(parentCompanyName.equals(prime.getName()))
            prime.addProperty(toAddProp);
        else if(!parentCompanyName.equals("") && !parentCompanyName.equals(" ") && parentCompanyName != null){
            if(getCompany(parentCompanyName) != null)
                getCompany(parentCompanyName).addProperty(toAddProp);
        }
    }

    public static int sizeOfCompany(){
        return companyCollection.size();
    }

    public static int sizeOfBusiness(){
        return businessCollection.size();
    }
}
