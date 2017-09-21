package companyfinancials.model;

import java.lang.Exception;
import java.util.*;
import companyfinancials.view.*;

public class Company extends Property{
    protected String name;
    protected String owner;
    protected double value;
    protected double profit;
    protected Bank bank;
    protected HashSet<Property> propertySet;
    protected ErrorMessage ErrorMessage = new ErrorMessage();

    public Company(String inName, String inOwner, double inValue){
        propertySet = new HashSet<Property>();
        name = inName;
        bank = new Bank(this.name);
        owner = (inOwner.equals("")) ? "Unnamed Owner" : inOwner;
        value = inValue;
        profit = 0.00;
    }

    //GETTERS
    public String getName(){
        return name;
    }

    public double getValue(){
        return value;
    }

    public String getOwner(){
        return owner;
    }

    public double getBalance(){
        return bank.getBalance();
    }

    public double getProfit(){
        return profit;
    }

    //This method adds a property to propertySet
    public void addProperty(Property inProp){

        //If this set has it, then drop it in; if not then print an error
        if(!ifContains(inProp))
            propertySet.add(inProp);
        else
            ErrorMessage.alreadyBoughtProperty(inProp.getName());
    }

    //SETTERS
    public void setValue(double inValue){
        value = value * inValue;
    }

    public void setProfit(double inProfit){
        profit = inProfit;
    }

    public void setOwner(String inOwner){
        owner = inOwner;
    }

    public void setBalance(double balanceToAdd){
        bank.setBalance(balanceToAdd);
    }

    /*Calculates profit of the Company*/
    public double profitCalc(){
        double retProfit = 0.0;
        /*calculate and summate the profits of every property this company owns*/
        for(Property entry : propertySet){
            retProfit =+ entry.profitCalc();
        }

        /*If the profit is equal or less than 0 then reduce the balance by that number*/
        if(retProfit <= 0)
            bank.setBalance(bank.getBalance() - retProfit);
        else{

            /*if not then half of the money goes to the balance and half goes to the profit*/
            bank.setBalance(bank.getBalance() * (0.5 * retProfit));
            profit = 0.5 * retProfit;
        }

        return profit;
    }

    //Returns whether or not this Company has bought the subject Property
    protected boolean ifContains(Property inProperty){
        return (propertySet.contains(inProperty));
    }
}
