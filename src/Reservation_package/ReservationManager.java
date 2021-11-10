package Reservation_package;

import java.util.Scanner;

import Restaurant_package.Restaurant;


public class ReservationManager {

    private Restaurant restaurant;
    private Scanner sc=new Scanner(System.in); //scanner attribute to read things;


    public ReservationManager(Restaurant res)
    {
        this.restaurant=res;
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
        if (this.restaurant==null){
            System.out.println("Restaurant is empty");
        }
        this.restaurant.printTimeSlots();

        int time = this.sc.nextInt();
        this.restaurant.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = this.sc.nextInt();
        
        this.restaurant.getTables()[tableChoice-1].reserveAtTime(customer, time-1); 

        // need to minus one because the method from Table class takes in the index itself
        
    }

    public void removeReservation (){

        System.out.println("=====================\nSelect timeslot : ");

        this.restaurant.printTimeSlots();

        int time = this.sc.nextInt();
        this.restaurant.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = this.sc.nextInt();
        
        this.restaurant.getTables()[tableChoice-1].removeReservationAtTime(time-1); 
        // need to minus one because the method from Table class takes in the index itself

    }

    public void checkbill()
    {
        System.out.println("=====================\nSelect timeslot : ");

        this.restaurant.printTimeSlots();

        int time = this.sc.nextInt();
        this.restaurant.printTableAvailabilityByTime(time);

        System.out.println("=====================\nSelect table : ");
        int tableChoice = this.sc.nextInt();

        this.restaurant.getTables()[tableChoice-1].getCustomerAtTime(time-1).print_invoice();
        this.restaurant.getTables()[tableChoice-1].removeReservationAtTime(time-1); 

        

    }


    public void viewTableAvailability(){
        System.out.println("View by : \n(1) Timeslot \n(2) Table \n(3) All");
        int choice = this.sc.nextInt();

        if (choice==1){
            this.restaurant.printTimeSlots();
            int time = this.sc.nextInt(); 
            this.restaurant.printTableAvailabilityByTime(time);

        } else if (choice==2) {
            System.out.println("Select Table Number [1-10] : "); 
            int table = this.sc.nextInt();
            this.restaurant.printTableAvailabilityByTable(table);
        }
        else if (choice==3) {
            this.restaurant.printTableAvailabilityAll();
        }
       
    }



    
}
