package Reservation_package;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import Order_package.Order;

/**
 * <code>Customer</code> class that contains <code>Customer</code> information such as <code>name</code>, <code>id</code>, <code>pax</code>, and <code>membership</code>.
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class Customer { 

    /**
     * <code>Customer</code> reservation representative name
     */
    private String name;
    /**
     * <code>Customer</code> ID for reservation
     */
    private int customerID; 
    /**
     * Number of <code>pax</code> this <code>Customer</code> is making the reservation for
     */
    private int pax;
    /**
     * Whether this <code>Customer</code> is a <code>member</code> or not
     */
    private Boolean membership;
    /**
     * Stores the <code>Order</code> of this <code>Customer</code>
     */
    private Order order;
    /**
     * Expiry time of reservation if no <code>Order</code> is created
     */
    private LocalDateTime expiry;
    
    

    /** 
     * <code>Customer</code> object constructor 
     * @param name <code>name</code> of this <code>Customer</code>
     * @param pax <code>int</code> number of people this reservation is for
     * @param membership <code>boolean</code> whether the <code>Customer</code> is a member or not
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
     * Assigns an <code>Order</code> object to this <code>Customer</code>
     * @param order <code>Order</code> object to be assigned
     */
    public void addOrder(Order order)
    {
        this.order=order;
    }

    
    /** 
     * Gets the <code>Order</code> of this <code>Customer</code>
     * @return <code>Order</code> of this <code>Customer</code>
     */
    public Order getOrder() 
    {
        return this.order;
    }

    
    /** 
     * Gets the price of this customer's <code>Order</code>, including tax and membership discount (if applicable)
     * @return <code>double</code> total amount payable by this <code>Customer</code>
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
     * Gets amount of money saved by this <code>Customer</code> due to membership discount (if applicable)
     * @return <code>double</code> amount of money saved due to discounts if member, else 0 
     */
    public double getOrderDiscount()
    {
        //for tabulating total discount (in sales report)
        if (this.isMember()) return this.order.nonMemberGstTotal()-this.order.memberGstTotal();
        
        return 0.0; 
    }

    
    /** 
     * Gets <code>name</code> of this <code>Customer</code> 
     * @return String <code>name</code>
     */

    public String getName()
    {
        return this.name;
    }

    /** 
     * Gets expiry of reservation if this <code>Customer</code> does not show up
     * @return <code>LocalDateTime</code> expiry time of the reservation (10 minutes after timeslot)
     */
    public LocalDateTime getExpiry()
    {
        return this.expiry;
    }

    
    /** 
     * Sets the name of this <code>Customer</code>
     * @param name <code>String</code> name to be set
     */
    public void setName(String name){
        this.name = name;
    }

    
    /** 
     * Sets the expiry time of the reservation (10 minutes after creation)
     * @param bookingtime <code>LocalDateTime</code> time of reservation creation
     */
    public void setExpiry(LocalDateTime bookingtime)
    {
        this.expiry=bookingtime.plusMinutes(10);
    }

    
    /**
     * Gets <code>customerID</code> of this <code>Customer</code> 
     * @return <code>int</code> customer Id
     */
    public int getCustomerID()
    {
        return this.customerID;
    }

    
    /** 
     * Gets total number of <code>pax</code> for this reservation
     * @return <code>int</code>  <code>pax</code>
     */
    public int getPax()
    {
        return this.pax;
    }

    
    /** 
     * Gets membership status of this <code>Customer</code>
     * @return <code>boolean</code> true if member, else false
     */
    public boolean isMember()
    {
        return this.membership;
    }

    /** 
     * Displays this customers details along with their reservation details
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
     * Displays this customer's invoice and returns a success value 
     * @return <code>int</code> 1 if invoice successfully printed, and <code>int</code> -1 if there is no <code>Order</code> created and thus no invoice
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
