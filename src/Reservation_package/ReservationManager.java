package Reservation_package;

import java.util.Scanner;

import Restaurant_package.Restaurant;

/** 
* <code>ReservationManager</code> class that handles all reservation related actions such as creating, removing and viewing reservations
* @author DSAI1 ASSIGNMENT GROUP 3
* @version 1.0
*/
public class ReservationManager {

    /**
     * <code>Scanner</code> used for user input
     */
   private static Scanner sc=new Scanner(System.in); 


   
   /** 
    * Creates a <code>Customer</code> with all necessary details for reservation
    * @return <code>Customer</code> to be allocated a reservation
    */
   public static Customer createCustomer(){
        
        while (true)
        {
             try {
                System.out.println("Enter customer details for this reservation");
                System.out.println("Customer name : ");
                String name = sc.nextLine();
                System.out.println("Number of pax : ");
                int pax = Integer.parseInt(sc.nextLine());
                if (pax<1 || pax>10){
                    System.out.println("Invalid number of people.");
                    continue;
                }
                System.out.println("Membership? (Y/N)");
                boolean membership; 
                String mem = sc.nextLine();
                if (mem.toLowerCase().equals("y")){
                    membership = true;
                }
                else if (mem.toLowerCase().equals("n")){
                    membership = false;
                }
                else {
                    throw new Exception();
                }
        
                Customer customer = new Customer(name, pax, membership); 
                return customer; 
                
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Invalid entries, please re-enter.");
            }
        }
    }

   /** 
    * Assigns a <code>Customer</code> to requested <code>Table</code> and timeslot subject to pax and availability
    */
    public static void createReservation (){
        
        Customer customer = createCustomer();
        
        
        int time;
        int tableChoice;
        System.out.println("=====================\nSelect timeslot : ");
        
        Restaurant.printTimeSlots();

        

        
        while (true){
            time = Integer.parseInt(sc.nextLine());
            if (time>6 || time<1){
                System.out.println("Invalid timeslot, please re-enter.");
                continue;
            }
            break;
        }
        if (Restaurant.checkTimeslotFullyBooked(time)==true){
            System.out.println("This timeslot is fully booked!");
            Restaurant.printTableAvailabilityByTime(time);
            return;
        }

        Restaurant.printTableAvailabilityByTime(time);
        System.out.println("=====================\nSelect table : ");
        
        while (true){
            tableChoice = Integer.parseInt(sc.nextLine());
            if (tableChoice>10 || tableChoice<1)
            {
                System.out.println("Invalid table, please re-enter.");
                continue;
            }
            break;
        }
        
        Restaurant.getTables()[tableChoice-1].reserveAtTime(customer, time-1); 

        // need to minus one because the method from Table class takes in the index itself
        
    }
   /** 
    * Removes a reservation at selected time and table after prompting user for selection
    */
    public static void removeReservation (){

        System.out.println("=====================\nSelect timeslot : ");

        Restaurant.printTimeSlots();

        int time = Integer.parseInt(sc.nextLine());
        Restaurant.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = Integer.parseInt(sc.nextLine());
        
        Restaurant.getTables()[tableChoice-1].removeReservationAtTime(time-1); 
        // need to minus one because the method from Table class takes in the index itself

    }
   /** 
    * Attempts to print the invoice of a <code>Customer</code> at selected timeslot and table. If no reservation or order is present, the user is notified. Else, the invoice is printed and the timeslot is vacated.
    */
    public static void checkBill()
    {
        System.out.println("=====================\nSelect timeslot : ");

        Restaurant.printTimeSlots();

        int time = Integer.parseInt(sc.nextLine());
        Restaurant.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = Integer.parseInt(sc.nextLine());
        //sc.nextLine();
        try {
            int check = Restaurant.getTables()[tableChoice-1].getCustomerAtTime(time-1).print_invoice();
            if (check==1){
                Restaurant.getTables()[tableChoice-1].getCustomerAtTime(time-1).setName("Vacated");
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("No reservation in selected slot!");
        }

        

    }

   /** 
    * Displays table and timeslot availability
    */
    public static void viewTableAvailability(){
        System.out.println("View by : \n(1) Timeslot \n(2) Table \n(3) All");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice==1){
            Restaurant.printTimeSlots();
            int time = Integer.parseInt(sc.nextLine()); 
            Restaurant.printTableAvailabilityByTime(time);

        } else if (choice==2) {
            System.out.println("Select Table Number [1-10] : "); 
            int table = Integer.parseInt(sc.nextLine());
            Restaurant.printTableAvailabilityByTable(table);
        }
        else if (choice==3) {
            Restaurant.printTableAvailabilityAll();
        }
       
    }



    
}
