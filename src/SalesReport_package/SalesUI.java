package SalesReport_package;

import java.util.ArrayList;
import java.util.Scanner;

import Reservation_package.Restaurant;

public class SalesUI {
    protected Restaurant restaurant;
    ArrayList<String> options=new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    public SalesUI(Restaurant res)
    {
        this.restaurant=res;
    
        options.add("");
        options.add("Edit order");
        
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
    
                    break; 
                case 2:
          
                    break;
                default:
                    break;
            } 
            
        } while (choice!=-1); 
    }
}
