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
    
    

    /** 
     * Creates Customer object used for reservation
     * @param name name of the customer
     * @param pax number of people to be seated at table
     * @param membership boolean whether the customer is a member or not
     */
    public Customer(String name,int pax,Boolean membership)
    {
        Random rand=new Random();
        this.name=name;
        this.customerID=rand.nextInt((100000 - 10000) + 1) + 10000;
        this.pax=pax;
        this.membership=membership;
        
    }


    
    /** 
     * Assigns an order object to this customer
     * @param order order object to be assigned
     */
    public void addOrder(Order order)
    {
        this.order=order;
    }

    
    /** 
     * Gets the order of this customer
     * @return order of this customer
     */
    public Order getOrder() 
    {
        return this.order;
    }

    
    /** 
     * Gets the price of this customer's order, including tax and membership discount (if applicable)
     * @return double total amount payable for this customer
     */
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

    
    /**
     * Gets amount of money saved by the customer due to membership discount (if applicable)
     * @return double of amount of money saved due to discounts if member, else 0 
     */
    public double getOrderDiscount()
    {
        //for tabulating total discount (in sales report)
        if (this.isMember()) return this.order.nonMemberGstTotal()-this.order.memberGstTotal();
        
        return 0.0; 
    }

    
    /** 
     * Gets name of customer 
     * @return String name of customer
     */

    public String getName()
    {
        return this.name;
    }

    /** 
     * Gets expiry of reservation if the customer does not show up
     * @return LocalDateTime expiry time of the reservation (10 minutes after timeslot)
     */
    public LocalDateTime getExpiry()
    {
        return this.expiry;
    }

    
    /** 
     * Sets the name of the customer
     * @param name name to be set
     */
    public void setName(String name){
        this.name = name;
    }

    
    /** 
     * Sets the expiry time of the reservation (10 minutes after creation)
     * @param bookingtime time of reservation creation
     */
    public void setExpiry(LocalDateTime bookingtime)
    {
        this.expiry=bookingtime.plusMinutes(10);
    }

    
    /**
     * Gets customer ID of this customer 
     * @return int customer Id
     */
    public int getCustomerID()
    {
        return this.customerID;
    }

    
    /** 
     * Gets total number of pax eating with this customer at the restaurant, including customer
     * @return int total number of pax
     */
    public int getPax()
    {
        return this.pax;
    }

    
    /** 
     * Gets membership status of the customer
     * @return boolean true if member, else false
     */
    public boolean isMember()
    {
        return this.membership;
    }

    /** 
     * Displays the customers details along with their reservation details
     */
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
    
    /** 
     * Displays the customer's invoice and returns a success value 
     * @return int 1 if invoice successfully printed, and -1 if there is no order created and thus no invoice
     */
    public int print_invoice()
    {
        if (this.order == null){
            System.out.println("Order doesn't exist!");
            return -1;
        }
        else if (this.membership==false)
        {
            System.out.println("Customer ID:"+this.customerID);
            this.order.printInvoiceRaw();
        }

        else
        {
            System.out.println("Customer ID:"+this.customerID);
            this.order.printInvoiceMember();
        }
        return 1;
    }

    
}
