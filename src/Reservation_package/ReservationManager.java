package Reservation_package;

import java.util.Scanner;

public class ReservationManager {

    private Tables Tables;
    private Scanner sc=new Scanner(System.in); //scanner attribute to read things;


    public ReservationManager(Tables tables)
    {
        this.Tables=tables;
    }



    public Customer createCustomer(){
        System.out.println("Enter customer details for this reservation");
        System.out.println("Customer name : ");
        String name = this.sc.next();
        System.out.println("Number of pax : ");
        int pax = this.sc.nextInt();
        System.out.println("Membership? (Y/N)");
        boolean membership; 
        if (sc.next().charAt(0) == 'Y')
        {
            membership = true; 
        } else {
            membership = false;
        }

        Customer customer = new Customer(name, pax, membership); 
        return customer; 
    }


    public void createReservation (){
        
        Customer customer = this.createCustomer();
        
        System.out.println("=====================\nSelect timeslot : ");
        this.Tables.printTimeSlots();

        int time = this.sc.nextInt();
        this.Tables.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = this.sc.nextInt();
        
        this.Tables.getTables()[tableChoice-1].reserveAtTime(customer, time-1); 
        // need to minus one because the method from Table class takes in the index itself
        
    }

    public void removeReservation (){

        System.out.println("=====================\nSelect timeslot : ");
        this.Tables.printTimeSlots();

        int time = this.sc.nextInt();
        this.Tables.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = this.sc.nextInt();
        
        this.Tables.getTables()[tableChoice-1].removeReservationAtTime(time-1); 
        // need to minus one because the method from Table class takes in the index itself

    }


    public void viewTableAvailability(){
        System.out.println("View by : \n(1) Timeslot \n(2) Table \n(3) All");
        int choice = this.sc.nextInt();

        if (choice==1){
            this.Tables.printTimeSlots();
            int time = this.sc.nextInt(); 
            this.Tables.printTableAvailabilityByTime(time);

        } else if (choice==2) {
            System.out.println("Select Table Number [1-10] : "); 
            int table = this.sc.nextInt();
            this.Tables.printTableAvailabilityByTable(table);
        }
        else if (choice==3) {
            this.Tables.printTableAvailabilityAll();
        }
       
    }



    
}
