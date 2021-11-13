package Reservation_package;

import java.time.LocalDateTime;

/**
 * <code>Table</code> class that contains table information such as table number, max seating capacity of the table, timeslots, and customers reserving this table at each timeslot
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class Table {

    /**
     * The ID of this Table
     */
    private int id;
    /**
     * The maximum capacity (pax) of this table
     */
    private int size;
    /**
     * Array of Customer objects who have reserved this table
     */
    private Customer[] reservations;
    /**
     * Array of LocalDateTime objects that will be used as table timeslots
     */
    private LocalDateTime[] times;

    /** 
     * <code>Table</code> object constructor 
     * @param id <code>int</code> the table ID
     * @param size <code>int</code> the max pax taken by the table
     */
    public Table(int id, int size)
    {
        this.id=id;
        this.size=size;
        this.reservations=new Customer[6]; // indexes denote timeslots, value will be customer if reserved, null if not
        this.times=new LocalDateTime[6];
        
        LocalDateTime now = LocalDateTime.now(); 
        int time=10; //start from 10:00 am
        for(int i=0;i<this.times.length;i++)
        {
            int day=now.getDayOfMonth();
            int month=now.getMonthValue();
            int year=now.getYear();
            LocalDateTime temp= LocalDateTime.of(year,month,day,time,0);
            this.times[i]=temp;
            time+=2; // 2 hour slots; from 10:00 am to 10:00 pm
        }
    }

    
    /** 
     * Gets this <code>Table</code> number
     * @return <code>int</code> table number
     */
    public int getId()
    {
        return this.id;
    }

    
    /** 
     * Gets max size of this <code>Table</code>
     * @return <code>int</code> max size
     */
    public int getSize()
    {
        return this.size;
    }

    
    /**
     * Gets list of reservations for this <code>Table</code>
     * @return <code>Array</code> of <code>Customer</code> objects assigned to this <code>Table</code>
     */
    public Customer[] getReservations()
    {
        return this.reservations;
    }
    
    /** 
     * Gets list of timeslots for this <code>Table</code>
     * @return <code>Array</code> of <code>LocalDateTime</code>
     */
    public LocalDateTime[] getTimeslots(){
        return this.times;
    }

    
    /** 
     * Gets <code>Customer</code> who reserved this table at specific timeslot
     * @param time <code>int</code> index of the timeslot
     * @return <code>Customer</code> object assigned to specified timeslot
     */
    public Customer getCustomerAtTime(int time) // can be used to check reservation status
    {
        try {
            return this.reservations[time];
        } catch (Exception e) {
            System.out.println("Not currently reserved!");
            return null;
        }
        
    }

    
    /** 
     * Gets <code>boolean</code> of whether this table is reserved at specified timeslot
     * @param time <code>int</code> index of the timeslot
     * @return <code>boolean</code> true if reserved, false if not
     */
    public boolean isReservedAtTime(int time)
    {
        return this.reservations[time] != null;
    }
    
    /** 
     * Reserves this <code>Table</code> by assigning <code>Customer</code> object to it at specified time
     * @param customer <code>Customer</code> to be assigned to this table
     * @param time <code>int</code> index of timeslot the customer is reserving
     */
    public void reserveAtTime(Customer customer, int time) //takes in the timeslot INDEX
    {
        if (this.reservations[time] != null)
        {
            System.out.println("Currently occupied!");
            return;
        }
        if (customer.getPax()>this.getSize())
        {
            System.out.println("Too many people!");
            return;
        }
        
        this.reservations[time] = customer;
        customer.setExpiry(this.times[time]);
        System.out.printf("Table %d at timeslot %d successfully reserved%n", getId(), time+1);
        customer.print_customer();
    }

    
    /** 
     * Removes reservation at specified timeslot
     * @param time <code>int</code> index of timeslot to be cleared
     */
    public void removeReservationAtTime(int time)
    {
        if(this.reservations[time]==null)
        {
            System.out.println("There is no customer at this table/timeslot!");
            return;
        }
        String customerName = this.reservations[time].getName();
        this.reservations[time] = null;

        System.out.printf("\nCustomer %s at Table %d at Timeslot %d successfully removed%n", customerName, getId(), time+1);

    }

    
    /** 
     * Prints reservations made for this table for all timeslots, prints <code>Customer</code> name if reserved and nothing if not
     * @param timeslot <code>Array</code> of timeslots to be iterated through to print reservation status
     */
    public void printReservations(String[] timeslot)
    {
        for (int i=0; i<getReservations().length;i++)
        {
            if (this.reservations[i]!=null)
            {
                if (this.reservations[i].getName().equals("Vacated")){
                    System.out.println(timeslot[i] + " : Vacated (" + this.reservations[i].getName() + ")\n");
                }else {
                    System.out.print(timeslot[i] + " : Reserved by " + this.reservations[i].getName()+ "\n");
                }
                
            }
            else
            {
                System.out.print(timeslot[i] + " : Not Reserved\n");
            }
        }
    }
    
}
