package Restaurant_package;

import Reservation_package.Customer;
import Reservation_package.Table;

/** 
 * Restaurant class that stores all reservation information at all timeslots and tables
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class Restaurant {

    private final static int numOfTables=10; 
    private static Table[] tables; 
    private final static String[] timeslots= new String[] {"10:00 - 12:00", 
                                    "12:00 - 14:00", 
                                    "14:00 - 16:00", 
                                    "16:00 - 18:00", 
                                    "18:00 - 20:00", 
                                    "20:00 - 22:00"}; //Timeslots of the restaurant (fixed)
                                    
    /** 
     * Restaurant object constructor, initialises all tables and timeslots to be empty
     */
    public Restaurant(){
        //constructor -- this creates a restaurant from scratch
        //this means 0 reservations have been made, all tables empty, no sales made
         
        tables = new Table[numOfTables];
        int k=2; 
        for (int i=0; i<numOfTables; i++){
            if (i%2==0 && i>0){
                k+=2;
            }
            tables[i] = new Table(i+1, k); 
        }
    }

    
    /** 
     * Gets array of Table objects in the restaurant 
     * @return Table[] of size 10, indexes correspond to (table Id - 1) 
     */
    public static Table[] getTables(){
        return tables; 
    }

    
    /** 
     * Gets array of Strings of fixed timeslots in the restaurant, opening at 10:00 and closing at 22:00
     * @return String[] of size 6, each string has format "XX:00 - YY:00" where XX and YY represent hours in a day and are 2 hours apart
     */
    public static String[] getTimeSlots(){
        return timeslots; 
    }

    /** 
     * Displays the timeslots the restaurant offers
     */
    public static void printTimeSlots(){
        System.out.println("-------Timeslots------");
        for (int i=0; i<6; i++) {
            System.out.printf("( %d ) ", i+1);
            System.out.print(timeslots[i] + "\n");
        }
    }

    
    /** 
     * Checks if a specific timeslot has been fully booked across all tables
     * @param time index of timeslot to be checked
     * @return boolean true if all tables are reserved at that time, else false
     */
    public static boolean checkTimeslotFullyBooked(int time){
        int timeIndex = time-1; 
        int booked=0;
        for (Table table: tables){
            if (table.isReservedAtTime(timeIndex)==true){
                booked+=1; 
            }
        }
        if (booked==tables.length){
            return true;
        }

        return false; 
    }

    
    /** 
     * Displays table availability acc
     * @param time
     */
    public static void printTableAvailabilityByTime(int time){
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
    
    /** 
     * @param table
     */
    public static void printTableAvailabilityByTable(int table){
        System.out.printf("\nFor table %d : \n", table); 
        tables[table-1].printReservations(timeslots);
    }

    public static void printTableAvailabilityAll()
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
