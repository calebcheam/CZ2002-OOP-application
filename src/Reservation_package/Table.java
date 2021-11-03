package Reservation_package;

public class Table {
    private int id;
    private int size;
    private Customer[] reservations;

    public Table(int id, int size)
    {
        this.id=id;
        this.size=size;
        this.reservations=new Customer[6]; // indexes denote timeslots, value will be customer if reserved, null if not
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
        System.out.printf("Table %d at timeslot %d successfully reserved%n", getId(), time+1);
    }

    public void removeReservationAtTime(int time)
    {
        if(this.reservations[time]==null)
        {
            System.out.println("There is no customer at this table/timeslot!");
            return;
        }
        this.reservations[time] = null;
        System.out.printf("Customer at Table %d at timeslot %d successfully removed%n", getId(), time+1);
    
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
