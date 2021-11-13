package Restaurant_package;

import Reservation_package.Customer;
import Reservation_package.Table;

/** 
 * <code>Restaurant</code> class that stores all reservation information at all timeslots and tables
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class Restaurant { //Singleton class
    
    private static Restaurant restaurant = null;
    private final static int numOfTables=10; 
    private static Table[] tables; 
    private final static String[] timeslots= new String[] {"10:00 - 12:00", 
                                    "12:00 - 14:00", 
                                    "14:00 - 16:00", 
                                    "16:00 - 18:00", 
                                    "18:00 - 20:00", 
                                    "20:00 - 22:00"}; //Timeslots of the restaurant (fixed)
                                    
    /** 
     * Initialises all <code>Table</code> objects in restaurant, sets all tables and timeslots to be empty
     */

    //private constructor to instantiate 1 restaurant
    private Restaurant() {

        tables = new Table[numOfTables];
        int k=2; 
        for (int i=0; i<numOfTables; i++){
            if (i%2==0 && i>0){
                k+=2;
            }
            tables[i] = new Table(i+1, k); 
    }
    } 
    
    public static Restaurant get_instance(){ 
        
        restaurant=new Restaurant();
        return restaurant;
        
    }

    
    /** 
     * Gets <code>Array</code> of <code>Table</code> objects in this <code>Restaurant</code> 
     * @return <code>Table[]</code> of size 10, indexes correspond to (table Id - 1) 
     */
    public static Table[] getTables(){
        return tables; 
    }

    
    /** 
     * Gets <code>Array</code> of <code>Strings</code> of fixed timeslots in this <code>Restaurant</code>, opening at 10:00 and closing at 22:00
     * @return <code>String[]</code> of size 6, each string has format "XX:00 - YY:00" where XX and YY represent hours in a day and are 2 hours apart
     */
    public static String[] getTimeSlots(){
        return timeslots; 
    }

    /** 
     * Displays the timeslots this restaurant offers
     */
    public static void printTimeSlots(){
        System.out.println("-------Timeslots------");
        for (int i=0; i<6; i++) {
            System.out.printf("( %d ) ", i+1);
            System.out.print(timeslots[i] + "\n");
        }
    }

    
    /** 
     * Checks if a specific timeslot has been fully booked across all tables in this <code>Restaurant</code>
     * @param time <code>int</code> index of timeslot to be checked
     * @return <code>boolean</code> true if all tables are reserved at that time, else false
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
     * Displays table availability across all tables for a specific timeslot
     * @param time <code>int</code> index of timeslot to be checked
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
     * Displays table availability across all timeslots for a specific table
     * @param table <code>int</code> ID of <code>Table</code> to be checked
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
