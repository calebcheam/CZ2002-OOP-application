package Reservation_package;

import java.time.LocalDateTime;

public class Table {
    private int id;
    private int size;
    private Customer[] reservations;
    private LocalDateTime[] times;

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

    public int getId()
    {
        return this.id;
    }

    public int getSize()
    {
        return this.size;
    }

    public Customer[] getReservations()
    {
        return this.reservations;
    }
    public LocalDateTime[] getTimeslots(){
        return this.times;
    }

    public Customer getCustomerAtTime(int time) // can be used to check reservation status
    {
        try {
            return this.reservations[time];
        } catch (Exception e) {
            System.out.println("Not currently reserved!");
            return null;
        }
        
    }

    public boolean isReservedAtTime(int time)
    {
        return this.reservations[time] != null;
    }

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
