package companyfinancials.model;

import java.util.*;

public class PrimaryCompany extends Company{
    public PrimaryCompany(String inName, String inOwner, double inValue){
        super(inName, "Unnamed Owner", inValue);
    }

    /*Calculates profit of the PrimaryCompany; just uses the super method*/
    public double profitCalc(){
        return super.profitCalc();
    }

    /*This method validates and processes purchases*/
    public void buy(Property toBuyProp){

        //If the primary company doesnt have the property to be bought
        //if it is, throw an error
        if(!super.ifContains(toBuyProp)){

            /*reduce the bank balance by the value of the bought property*/
            bank.setBalance(bank.getBalance() - toBuyProp.getValue());

            /*if the property's owner is not Unnamed Owner then look for that company
            in the company list and add the value of the bought property to the balance*/
            if(!toBuyProp.getOwner().equals("Unnamed Owner")){
                PropertyCollection.getCompany(toBuyProp.getOwner()).setBalance(toBuyProp.getValue());
            }
            toBuyProp.setOwner(this.name);
            propertySet.add(toBuyProp);
        }
        else
            ErrorMessage.alreadyBoughtProperty(toBuyProp.getName());
    }

    /*This method is responsible for validating and processing selling properties*/
    public void sell(Property toSellProp){

        /*If the primary company actually has the property it wants to sell
        then set the owner of the property to Unnamed Owner and remove it from the list
        after adding the value of the property to the company balance*/
        if(ifContains(toSellProp)){
            bank.setBalance(bank.getBalance() + toSellProp.getValue());
            toSellProp.setOwner("Unnamed Owner");
            propertySet.remove(toSellProp);
        }

        /*If it doesn't have the property, throw an error*/
        else
            ErrorMessage.notOwner(toSellProp.getName());
    }
}
