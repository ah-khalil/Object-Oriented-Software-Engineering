package companyfinancials.model;

public class BusinessUnit extends Property{
    private String name;
    private String owner;
    private double value;
    private double profit;
    private double revenue;
    private double wages;

    public BusinessUnit(String inName, String inOwner, double inValue, double inRevenue, double inWages){
        name = inName;
        owner = (inOwner.equals("")) ? "Unnamed Owner" : inOwner;
        value = inValue;
        revenue = inRevenue;
        wages = inWages;
        profit = 0.0;
    }

    //GETTERS

    public String getName(){
        return name;
    }

    public String getOwner(){
        return owner;
    }

    public double getValue(){
        return value;
    }

    public double getProfit(){
        return profit;
    }

    public double getRevenue(){
        return revenue;
    }

    public double getWages(){
        return wages;
    }

    //SETTERS
    public void setOwner(String inOwner){
        owner = inOwner;
    }

    public void setValue(double inValue){
        value = value * inValue;
    }

    public void setProfit(double inProfit){
        profit = inProfit;
    }

    public void setRevenue(double inRevenue){
        revenue = revenue * inRevenue;
    }

    public void setWages(double inMultiplier){
        wages *= inMultiplier;
    }

    /*Calculates profit of the Business Units*/
    public double profitCalc(){
        /*the profit is simply the revenue minus the wages*/
        profit = revenue - wages;
        return profit;
    }
}
