package Reservation_package;

import java.util.ArrayList;
import java.util.Scanner;

public class ReservationUI {
    
    private Restaurant restaurant;
    private ReservationManager manager=new ReservationManager(this.restaurant);
    ArrayList<String> options=new ArrayList<String>();
    Scanner sc=new Scanner(System.in); 


    public ReservationUI(Restaurant res)
    {
        this.restaurant=res;
        options.add("Create new Reservation");
        options.add("Delete Reservation");
        
    }

    public void start()
    {
        int choice;
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
                    this.manager.createReservation();
                    break; 
                case 2:
                    this.manager.removeReservation();
                    break;
            }
        } while (choice!=-1); 
        





    }











}
