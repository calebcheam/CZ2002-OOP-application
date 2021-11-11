package Order_package;
import java.util.Scanner;
import java.util.ArrayList;

import Restaurant_package.Restaurant;

public class OrderUI {
    protected Restaurant restaurant;
    private OrderManager manager;
    ArrayList<String> options=new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    
    public OrderUI(Restaurant res)
    {
        this.restaurant=res;
        this.manager=new OrderManager(); 
           
        options.add("Create new order");
        options.add("Edit existing order");
        options.add("View existing order");
        
    }
    public void start()
    {
        int choice;
        do {
            System.out.println("===============================================");
	        System.out.println("|                 Order Options               |");
            System.out.println("|              (Enter -1 to Exit)             |");
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
                    this.manager.createOrder();
                    break; 
                case 2:
                    this.manager.editOrder();
                    break;
                case 3:
                    this.manager.viewOrder();
                default:
                    break;
            } 
            
        } while (choice!=-1); 
    }
}
