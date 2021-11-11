package Reservation_package;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import Order_package.Order;

public class Customer { 

    private String name; //customer reservation representative name 
    private int customerID; //customer ID for reservation
    private int pax; //number of customers for specific reservation
    private Boolean membership; //whether a customer is a member or not
    private Order order;//stores the orders of that customer
    private LocalDateTime expiry;
    private Boolean arrived=false;
    

    public Customer(String name,int pax,Boolean membership)
    {
        Random rand=new Random();
        this.name=name;
        this.customerID=rand.nextInt((100000 - 10000) + 1) + 10000;
        this.pax=pax;
        this.membership=membership;
        
    }


    public LocalDateTime getExpiry()
    {
        return this.expiry;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setExpiry(LocalDateTime bookingtime)
    {
        this.expiry=bookingtime.plusMinutes(10);
    }

    public void addOrder(Order order)
    {
        this.order=order;
    }

    public Order getOrder() 
    {
        return this.order;
    }

    public double getOrderPrice()
    {
        // for tabulating total (sales report)
        if (this.membership==false)
        {
            
            return this.order.nonMemberGstTotal();
        }

        else
        {
            return this.order.memberGstTotal();
        }

    }

    public double getOrderDiscount()
    {
        //for tabulating total discount (in sales report)
        if (this.isMember()) return this.order.nonMemberGstTotal()-this.order.memberGstTotal();
        
        return 0.0; 
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

    public void print_customer()
    {
        System.out.println("===Customer Details===");
        System.out.println("Customer name:"+this.name);
        System.out.println("Id:"+this.customerID);
        System.out.println("Pax:"+this.pax);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
        System.out.println("Reservation will expire at "+this.expiry.format(format));
        
        if (this.membership==true)
        {
            System.out.println("Member");
        }
        else
        {
            System.out.println("Non-Member");
        }

    }
    public void print_invoice()
    {
        if (this.membership==false)
        {
            System.out.println("Customer ID:"+this.customerID);
            this.order.printInvoiceRaw();
        }

        else
        {
            System.out.println("Customer ID:"+this.customerID);
            this.order.printInvoiceMember();
        }
    }

   







    



    
}
