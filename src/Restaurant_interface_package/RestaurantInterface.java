package Restaurant_interface_package;

import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantInterface {
    ArrayList<String> options;

    public void Start(){
        int choice;
        Scanner sc = new Scanner(System.in); 
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

        System.out.println("What would you like to do today?"
                            + "\nSelect one of the following options (choose -1 to quit) : ");
        do {
            int i=0; 
            for (String option : options) {
                i+=1; 
                System.out.println(i + " : " + option); 
            }
            choice = sc.nextInt(); 
        } while (choice!=-1); 
    }


    public static void main (String[] args){
        RestaurantInterface restaurantApp = new RestaurantInterface();
        restaurantApp.Start();
    }

    public void viewTableAvailability(){
        
    }

}
