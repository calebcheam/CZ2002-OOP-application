package Reservation_package;

import java.util.Scanner;

import Restaurant_package.Restaurant;


public class ReservationManager {

    
   private static Scanner sc=new Scanner(System.in); //scanner attribute to read things;


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

    public static void checkbill()
    {
        System.out.println("=====================\nSelect timeslot : ");

        Restaurant.printTimeSlots();

        int time = sc.nextInt();
        Restaurant.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = sc.nextInt();
        sc.nextLine();
        try {
            Restaurant.getTables()[tableChoice-1].getCustomerAtTime(time-1).print_invoice();
            Restaurant.getTables()[tableChoice-1].getCustomerAtTime(time-1).setName("Vacated");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("No reservation in selected slot!");
        }

        

    }


    public static void viewTableAvailability(){
        System.out.println("View by : \n(1) Timeslot \n(2) Table \n(3) All");
        int choice = sc.nextInt();

        if (choice==1){
            Restaurant.printTimeSlots();
            int time = sc.nextInt(); 
            Restaurant.printTableAvailabilityByTime(time);

        } else if (choice==2) {
            System.out.println("Select Table Number [1-10] : "); 
            int table = sc.nextInt();
            Restaurant.printTableAvailabilityByTable(table);
        }
        else if (choice==3) {
            Restaurant.printTableAvailabilityAll();
        }
       
    }



    
}
