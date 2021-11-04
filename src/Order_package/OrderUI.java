package Order_package;
import Reservation_package.Restaurant;
import java.util.Scanner;
import java.util.ArrayList;
import Menu_package.Menu;

public class OrderUI {
    protected Restaurant restaurant;
    private OrderManager manager;
    ArrayList<String> options=new ArrayList<>();
    Scanner sc=new Scanner(System.in);
    public OrderUI(Restaurant res, Menu menu)
    {
        this.restaurant=res;
        this.manager=new OrderManager(this.restaurant, menu); 
           
        options.add("Create new order");
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
                    this.manager.createOrder();
                    break; 
                case 2:
                    this.manager.editOrder();
                    break;
                default:
                    break;
            } 
            
        } while (choice!=-1); 
    }
}
