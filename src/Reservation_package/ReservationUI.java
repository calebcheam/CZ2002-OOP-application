package Reservation_package;

import java.util.ArrayList;
import java.util.Scanner;

import Restaurant_package.Restaurant;

public class ReservationUI {
    
    protected Restaurant restaurant;
    private ReservationManager manager;
    ArrayList<String> options=new ArrayList<String>();
    Scanner sc=new Scanner(System.in); 


    public ReservationUI(Restaurant res)
    {
        this.restaurant=res;
        this.manager=new ReservationManager(this.restaurant);
           
        options.add("Create new Reservation");
        options.add("Delete Reservation");
        options.add("View Reservations");
        options.add("Check Bill");
        
    }

    public void start()
    {
        int choice;
        do {
            System.out.println("");
            System.out.println("Reservation Actions (choose -1 to quit) :");
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
                    this.manager.createReservation();
                    break; 
                case 2:
                    this.manager.removeReservation();
                    break;
                case 3:
                    this.manager.viewTableAvailability();
                    break;
                case 4:
                    this.manager.checkbill();
                    break;
                
            }
        } while (choice!=-1); 
        
    }

}
