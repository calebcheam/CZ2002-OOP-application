package Reservation_package;

import java.time.LocalDateTime;

/**
 * Table class that contains table information such as table number, max seating capacity of the table, timeslots, and customers reserving this table at each timeslot
 */
public class Table {
    private int id;
    private int size;
    private Customer[] reservations;
    private LocalDateTime[] times;

    /** 
     * Table object constructor 
     * @param id The table number 
     * @param size The max pax taken by the table
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
     * Gets the table number
     * @return int table number
     */
    public int getId()
    {
        return this.id;
    }

    
    /** 
     * Gets max pax of the table
     * @return int max pax
     */
    public int getSize()
    {
        return this.size;
    }

    
    /**
     * Get list of Customers who have reserved this Table
     * @return Array of Customer objects assigned to Table
     */
    public Customer[] getReservations()
    {
        return this.reservations;
    }
    
    /** 
     * Get list of timeslots for this Table
     * @return Array of LocalDateTime for Table
     */
    public LocalDateTime[] getTimeslots(){
        return this.times;
    }

    
    /** 
     * Get Customer who reserved this table at specific timeslot
     * @param time index of the timeslot
     * @return Customer object assigned to specified timeslot
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
     * Get boolean of whether this table is reserved at specified timeslot
     * @param time index of the timeslot
     * @return boolean true if reserved, false if not
     */
    public boolean isReservedAtTime(int time)
    {
        return this.reservations[time] != null;
    }
    
    /** 
     * Reserves this table by assigning Customer object to it at specified time
     * @param customer customer object to be assigned to table
     * @param time index of timeslot the customer is reserving
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
     * @param time index of timeslot to be cleared
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
     * Prints reservations made for this table for all timeslots, prints customer name if reserved and nothing if not
     * @param timeslot Array of timeslots to be iterated through to print reservation status
     */
    public void printReservations(String[] timeslot)
    {
        for (int i=0; i<getReservations().length;i++)
        {
            if (this.reservations[i]!=null)
            {
                System.out.print(timeslot[i] + " : Reserved by " + this.reservations[i].getName()+ "\n");
            }
            else
            {
                System.out.print(timeslot[i] + " : Not Reserved\n");
            }
        }
    }
    
}
