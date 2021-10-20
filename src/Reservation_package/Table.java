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


    public void reserveAtTime(Customer customer, int time)
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
        System.out.printf("Table %d at timeslot %d successfully reserved", getId(), time);
        this.reservations[time] = customer;
    }

    public void removeReservationAtTime(int time)
    {
        this.reservations[time] = null;
    }

    public void printReservations()
    {
        for (int i=0;i<6;i++)
        {
            if (this.reservations[i]!=null)
            {
                System.out.printf("%d : reserved",i);
            }
            else
            {
                System.out.printf("%d : not reserved",i);
            }
        }
    }
    
}
