package Reservation_package;

public class Customer { 

    private String name; //customer reservation representative name 
    private int customerID; //customer ID for reservation
    private int pax; //number of customers for specific reservation
    private Boolean membership; //whether a customer is a member or not

    public Customer(String name,int customerID,int pax,Boolean membership)
    {
        this.name=name;
        this.customerID=customerID;
        this.pax=pax;
        this.membership=membership;

    }

    //getters and setters

    public String getName()
    {
        return this.name;
    }

    public int getCustomerID()
    {
        return this.customerID;
    }

    public int getPax()
    {
        return this.pax;
    }

    public boolean isMember()
    {
        return this.membership;
    }


    




    



    
}
