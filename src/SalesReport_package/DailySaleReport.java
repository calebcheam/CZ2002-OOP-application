package SalesReport_package;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Menu_package.Item;
import Order_package.Order;
import Reservation_package.Customer;
import Reservation_package.Restaurant;
import Reservation_package.Table;

public class DailySaleReport { //this stores one day's worth orders
    private double dailyTotal; // total revenue from one day
    private Map<Item, Integer> map; // total quantity sold by item 
    private String date; 

    //for easier referencing in the method functions
    private Restaurant restaurant; 
    private Table[] restaurantTables; 

    public static void main (String[] args){
        DailySaleReport dsr = new DailySaleReport(); //dummy
        dsr.addToSalesReport();
    }

    public DailySaleReport(){
        //dummy constructor so that i can test out writing to file
        this.date = java.time.LocalDate.now().toString();
        this.map = new HashMap<Item, Integer>(); 
        for (int i=1; i<10; i++){
            Item item = new Item();
            item.setName("Some food " + i);
            map.put(item, (i+10)/2); 
        }
        this.dailyTotal= 100.0; 
    }

    public DailySaleReport(Restaurant restaurant){
        this.restaurant = restaurant; 
        this.restaurantTables = restaurant.getTables(); 
        this.map = new HashMap<Item, Integer>();

        
        for (Table table : this.restaurantTables ) {
            // loop through the restaurant's tables
            for (int i=0; i<6; i++){
                // loop through each time slot of one table

                Customer customer = table.getCustomerAtTime(i);
                if (customer == null){
                    continue; 
                }
                Order order = customer.getOrder();
                this.date = order.getDate(); 
                
                // get order associated with the customer 
                HashMap<Item, Integer> orderedItems = order.getOrderedItems(); 
                //actual list of the menu items ordered

                for (Item item : orderedItems.keySet()){
                    int newQuantity = orderedItems.get(item); 
                    // the quantity of this menu item for this specific order 

                    if (this.map.containsKey(item)){
                        //if daily report contains the menu item already
                        
                        int oldQuantity = this.map.get(item); 
                        newQuantity += oldQuantity; 
                    }
                    this.map.put(item, newQuantity);
                }
            }
        }
    }



    private void calculateDailyTotal(){
        for (Table table : this.restaurantTables) {
            for (int i=0; i<6; i++){
                Customer customer = table.getCustomerAtTime(i);
                this.dailyTotal += customer.getOrderPrice(); 
            }
        }
    }

    public double getDailyTotal(){
        return this.dailyTotal;
    }
    
    private void addToSalesReport(){
        try{
            File file =new File("SALESREPORT.txt");
            
            if(!file.exists()){
               file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println("__________________________________________________");
            pw.println("            DAILY SALES REPORT                    ");
            pw.println("__________________________________________________");
            pw.println("Date : " + this.date);
            pw.println("Item                     | Quantity");
            pw.println("---------------------------------------------------");
            for (Item item : this.map.keySet()){
                pw.println(item.getName() + "\t\t\t\t | " + this.map.get(item));
            }
            pw.printf("\nDAY'S TOTAL : %.2f\n" , this.dailyTotal);
            pw.println("__________________________________________________");
            pw.close();
  
        System.out.println("Data successfully appended at the end of file");
  
         }catch(IOException ioe){
             System.out.println("Exception occurred:");
             ioe.printStackTrace();
        }
    }


}

