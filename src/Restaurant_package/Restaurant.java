package Restaurant_package;

import Reservation_package.Customer;
import Reservation_package.Table;

public class Restaurant {

    private final static int numOfTables=10; 
    private Table[] tables; 
    private final static String[] timeslots= new String[] {"10:00 - 12:00", 
                                    "12:00 - 14:00", 
                                    "14:00 - 16:00", 
                                    "16:00 - 18:00", 
                                    "18:00 - 20:00", 
                                    "20:00 - 22:00"}; //Timeslots of the restaurant (fixed)

    public Restaurant(){
        //constructor -- this creates a restaurant from scratch
        //this means 0 reservations have been made, all tables empty, no sales made
         
        tables = new Table[numOfTables];
        int k=2; 
        for (int i=0; i<numOfTables; i++){
            tables[i] = new Table(i+1, k); 
            if (i%2==0){
                k+=2;
            }
        }
    }

    public Table[] getTables(){
        return this.tables; 
    }

    public String[] getTimeSlots(){
        return timeslots; 
    }

    public void printTimeSlots(){
        System.out.println("-------Timeslots------");
        for (int i=0; i<6; i++) {
            System.out.printf("( %d ) ", i+1);
            System.out.print(timeslots[i] + "\n");
        }
    }
    
    public void printTableAvailabilityByTime(int time){
        int timeIndex = time-1; 
        System.out.println("Table Availability at "+  timeslots[timeIndex]);
        System.out.println("Tables : ");
        for (Table table : tables) {
            System.out.print("\n "  + table.getId());
            if (table.isReservedAtTime(timeIndex) ==true)
            {
                System.out.print("      : " + table.getCustomerAtTime(timeIndex).getName()); 
            } else{
                System.out.printf("      : Unoccupied\t(max %d pax)", table.getSize());
            }
        }
        System.out.println();
    }
    public void printTableAvailabilityByTable(int table){
        System.out.printf("\nFor table %d : \n", table); 
        tables[table-1].printReservations(timeslots);
    }

    public void printTableAvailabilityAll()
    {
        System.out.print("                ");
        for (Table table : tables)
        {
            
            System.out.print("T"+table.getId()+"     ");
        }
        System.out.print("\n");
        for(int i=0;i<6;i++)
            {
                System.out.print(timeslots[i]+":");

                for(Table table : tables)
                {
                    if (table.getReservations()[i]==null)
                    {
                        System.out.print("       ");
                    }
                    else
                    {
                        Customer customer=table.getReservations()[i];
                        System.out.print(" "+customer.getName()+" ");
                    }
                   
                }

                System.out.println("\n");
            }


    }

    

    
}
