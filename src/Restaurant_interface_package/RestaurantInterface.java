package Restaurant_interface_package;

import java.util.ArrayList;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

import Reservation_package.Customer;
import Reservation_package.Table;

public class RestaurantInterface {
    ArrayList<String> options;
    Scanner sc; 
    Restaurant restaurant = new Restaurant(); 

    public void Start(){
        int choice;
        this.sc = new Scanner(System.in); //scanner attribute to read things
        this.options = new ArrayList<String>(); //options for functions staff can use

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

        // Reservation Actions will be replaced with a restaurant UI for this
        options.add("Create new Reservation");
        options.add("Delete Reservation");
        
        
        System.out.println("What would you like to do today?"
                            + "\nSelect one of the following options (choose -1 to quit) : ");
        do {
            System.out.println("===============================================");
            int i=0; 
            for (String option : options) {
                i+=1; 
                System.out.println(i + " : " + option); 
            }
            System.out.println("===============================================");
            choice = sc.nextInt(); 

            switch(choice){
                 
                case 11:
                    this.viewTableAvailability();
                    break; 
                case 12:
                    this.createReservation();
                    break;
            }
            
        } while (choice!=-1); 
    }


    public static void main (String[] args){
        RestaurantInterface restaurantApp = new RestaurantInterface();
        restaurantApp.Start();
    }

    
}
