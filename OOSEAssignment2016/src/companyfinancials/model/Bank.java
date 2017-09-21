package companyfinancials.model;

public class Bank extends Property{
    private double value;
    private double profit;
    private double balance;
    private String owner;

    public Bank(String inOwner){
        value = 0.00;
        profit = 0.00;
        balance = 0.00;
        owner = inOwner;
    }

    //GETTERS
    public String getName(){
        return "Bank";
    }

    public String getOwner(){
        return owner;
    }

    public double getProfit(){
        return profit;
    }

    public double getValue(){
        return value;
    }
    public double getBalance(){
        return balance;
    }

    //SETTERS
    public void setValue(double inValue){
        value = inValue;
    }

    public void setProfit(double inProfit){
        profit = inProfit;
    }

    public void setBalance(double inBalance){
        balance += inBalance;
    }

    public void setOwner(String inOwner){
        owner = inOwner;
    }

    /*Calculates profit of the Bank*/
    public double profitCalc(){
        /*If the balance isn't 0 then add a 5% interest, if not then remove 5%*/
         balance = (balance > 0) ? balance * 1.05 : balance * 0.95;
         return profit;
    }
}
