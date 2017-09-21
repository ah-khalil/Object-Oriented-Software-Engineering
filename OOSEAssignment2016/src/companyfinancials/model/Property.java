package companyfinancials.model;

public abstract class Property{

    //A method that equates the properties names
    public boolean equals(Property inProperty){
        return (inProperty.getName().equals(this.getName()));
    }
    public abstract String getName();
    public abstract String getOwner();
    public abstract double getProfit();
    public abstract double getValue();
    public abstract void setValue(double inValue);
    public abstract void setOwner(String inOwner);
    public abstract double profitCalc();
    public abstract String printAll();
}
