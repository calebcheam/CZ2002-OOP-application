package Order_package;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


/** 
 * OrderUI that links user to order manager
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class OrderUI {
    
    private static ArrayList<String> options=new ArrayList<>(Arrays.asList("Create new order","Edit existing order","View existing order"));
    private static Scanner sc=new Scanner(System.in);

    /** 
     * Starts the OrderUI and displays user options
     */
    public static void start()
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
                    OrderManager.createOrder();
                    break; 
                case 2:
                    OrderManager.editOrder();
                    break;
                case 3:
                    OrderManager.viewOrder();
                default:
                    break;
            } 
            
        } while (choice!=-1); 
    }
}
