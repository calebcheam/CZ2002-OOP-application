package SalesReport_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * <code>SalesUI</code> class that links user to the <code>SaleReports</code>
 * @see SaleReports
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class SalesUI {
    
    private static ArrayList<String> options=new ArrayList<>(Arrays.asList("View Today's Sales Report","Save Today's Report to Records","View previous All Sales Reports separately","View previous Sales Report (One day)","View previous Sales Report (All days summed up)","View previous Sales Report (Over a period)"));
    private static Scanner sc=new Scanner(System.in);


    /** 
     * Starts the <code>SalesUI</code> and displays user options
     */
    public static void start()
    {
        int choice;
        do {
    
            System.out.println("===============================================");
	        System.out.println("|              Sale Report Options            |");
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
                SalesManager.viewTodaySales();;
        
                break; 
                case 2:
                SalesManager.saveTodaySales(); 
                break;
                case 3 : 
                    SalesManager.viewAllSaleReport();

                    break;
                case 4:
                    SalesManager.viewOneSaleReport();
                    break;
                case 5 : 
                    SalesManager.viewAccumulatedSales(true);
        
                    break;
                case 6: 
                    SalesManager.viewAccumulatedSales(false);
                default:
                    
                    break;
            } 
            
        } while (choice!=-1); 
    }



}
