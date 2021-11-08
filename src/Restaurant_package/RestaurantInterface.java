package Restaurant_package;

import java.util.ArrayList;
import java.util.Scanner;

import Menu_package.Menu;
import Menu_package.MenuUI;
import Menu_package.wackMenuUI;
import Order_package.OrderUI;
import Reservation_package.ReservationUI;
import SalesReport_package.SalesUI;


public class RestaurantInterface {
    ArrayList<String> options;
    Scanner sc; 
    Restaurant restaurant=new Restaurant();
    Menu menu = new Menu();


    public void Start(){
        

        int choice;
        this.sc = new Scanner(System.in); //scanner attribute to read things
        this.options = new ArrayList<String>(); //options for functions staff can use

        // Menu Actions
        options.add("Menu actions");
        options.add("Order actions");
        options.add("Reservation Actions");
        options.add("Sales Report");


        
        wackMenuUI menuUI = new wackMenuUI(); 

        ReservationUI reservationUI=new ReservationUI(this.restaurant);

        OrderUI orderUI = new OrderUI(this.restaurant);

        SalesUI salesUI = new SalesUI(this.restaurant);

        Thread t1 = new Thread(new Refresh(this.restaurant));
        t1.start();
        
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
                 
                case 1:
                menuUI.start();
                break; 
                case 2:
                orderUI.start();
                break;
                
                case 3: //for reservations
                reservationUI.start();
                break; 
                
                case 4: //for sales report
                salesUI.start(); 
                break;
            }
            
        } while (choice!=-1); 
        
    }


    public static void main (String[] args){
        RestaurantInterface restaurantApp = new RestaurantInterface();
        restaurantApp.Start();
    }

    
}
