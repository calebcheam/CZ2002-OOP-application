package Restaurant_interface_package;

import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantInterface {
    ArrayList<String> options;
    Scanner sc; 

    public void Start(){
        int choice;
        sc = new Scanner(System.in); 
        options = new ArrayList<String>();

        // Menu Actions
        options.add("Create new Menu Item (Ala Carte)");
        options.add("Update/Delete Menu Item (Ala Carte)");
        options.add("Create new Promotion ");
        options.add("Update/Delete Promotion");

        options.add("View all Main Courses");
        options.add("View all Desserts");
        options.add("View all Drinks");

        // Order Actions
        options.add("Create new Order");
        options.add("View Order");
        options.add("Edit Order");

        // Table Actions
        options.add("Check Table Availablility");

        // Reservation Actions
        options.add("Create new Reservation");
        options.add("Delete Reservation");
        
        Restaurant restaurant = new Restaurant(); 
        System.out.println("What would you like to do today?"
                            + "\nSelect one of the following options (choose -1 to quit) : ");
        do {
            int i=0; 
            for (String option : options) {
                i+=1; 
                System.out.println(i + " : " + option); 
            }
            choice = sc.nextInt(); 

            switch(choice){
                case 11:
                    this.viewTableAvailability(restaurant);
            }

        } while (choice!=-1); 
    }


    public static void main (String[] args){
        RestaurantInterface restaurantApp = new RestaurantInterface();
        restaurantApp.Start();
    }

    public void viewTableAvailability(Restaurant restaurant){
        System.out.println("View by (1) Timeslot \n(2) Table");
        int choice = this.sc.nextInt();
        if (choice==1){
            restaurant.printTimeSlots();
            int time = this.sc.nextInt(); 
            restaurant.printTableAvailabilityByTime(time);
        } else if (choice==2) {
            System.out.println("Select Table Number [1-10] : "); 
            int table = this.sc.nextInt();
            restaurant.printTableAvailabilityByTable(table);
        }
    }

}
