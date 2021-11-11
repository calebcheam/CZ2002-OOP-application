package Reservation_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * ReservationUI class that links user and reservation manager
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class ReservationUI {
    
    private static ArrayList<String> options=new ArrayList<String>(Arrays.asList("Create new Reservation","Delete Reservation","View Reservations","Check Bill"));
    private static Scanner sc=new Scanner(System.in); 
    
    


    public static void start()
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
            
            choice = Integer.parseInt(sc.nextLine()); 

            switch(choice){
                 
                case 1:
                    ReservationManager.createReservation();
                    break; 
                case 2:
                    ReservationManager.removeReservation();
                    break;
                case 3:
                    ReservationManager.viewTableAvailability();
                    break;
                case 4:
                    ReservationManager.checkBill();
                    break;
                
            }
        } while (choice!=-1); 
        
    }

}
