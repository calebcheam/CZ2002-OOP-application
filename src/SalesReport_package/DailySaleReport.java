package SalesReport_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Menu_package.Item;
import Order_package.Order;
import Reservation_package.Customer;
import Reservation_package.Table;
import Restaurant_package.Restaurant;

// degen way 
// for adding Today / new report --> take in the information as <Item, Integer(quantity)> 
// then convert it into <String, List<Double(quantity, revenue)>>> 
// Because when we read from csv it'll be strings
// and it's too lehceh to cross-check with the Menu to convert it back to Item
// And our only use for it is to tabulate the revenues (I don't have to care about the indiv prices)
// So there is no real need to use Item object
// Except for when adding in new report, because we need to calculate by taking Item.price * quantity


public class DailySaleReport { //this stores one day's worth orders
    private double dailyTotal; // total revenue from one day
    private double dailyDiscount; // total discount given in one day
    private Map<Item, Integer> tempMap; 
    // used to calculate today's addition (will be empty for past reports)
    private Map<String, List<Double>> map;  // total quantity sold by item 
    private String date; 

   
    private Restaurant restaurant; 
    private Table[] restaurantTables;  //for easier referencing in the method functions

    public DailySaleReport()
    {

    }
    public DailySaleReport(double dailyTotal, double dailyDiscount, Map<String, List<Double>> map, String date)
    {
        //constructor for reading in past reports from csv 
        this.dailyTotal = dailyTotal; 
        this.dailyDiscount = dailyDiscount;
        this.map = map;
        this.date = date;
        //the tempMap will be empty 
    }

    public DailySaleReport(Restaurant res){
        // constructor for new report (ie. today's sales)
        this.restaurant = res; 
        this.restaurantTables = this.restaurant.getTables();
        this.date = java.time.LocalDate.now().toString();
        this.calculateMenuItems(); //filling in menu items
        this.tempMapToMap(); //convert Item to Strings
        this.calculateTotals(); //tabulate dailyTotal & total discount given
    }

    public String getDate(){
        return this.date;
    }

    public double getDailyTotal(){
        return this.dailyTotal;
    }

    public double getDiscountTotal(){
        return this.dailyDiscount;
    }

    public Map<String, List<Double>> getMap(){
        return this.map;
    }

    private void calculateMenuItems(){
        // constructor for reading in today's sales 
        
        this.tempMap = new HashMap<Item, Integer>();
        for (Table table : this.restaurantTables ) {
            
            for (int i=0; i<6; i++){ // loop through each time slot of one table

                Customer customer = table.getCustomerAtTime(i);
                if (customer == null){
                    continue; 
                }
                
                // get order associated with the customer
                Order order = customer.getOrder();
                if (order ==null){
                    continue;
                }
                boolean isMember = customer.isMember(); 
                
                HashMap<Item, Integer> orderedItems = order.getOrderedItems();  //the actual menu items ordered

                for (Item item : orderedItems.keySet()){
                    int newQuantity = orderedItems.get(item); 
                    // the quantity of this menu item for this specific order 

                    if (isMember){
                        item.setName("(Discounted)" + item.getName());
                    } 

                    if (this.tempMap.containsKey(item)){
                        //if daily report contains the menu item already
                        
                        int oldQuantity = this.tempMap.get(item); 
                        newQuantity += oldQuantity; 
                    }

                    this.tempMap.put(item, newQuantity);
                }
            }
        }
    }

    private void tempMapToMap(){
        //degen way; converting map with Items to <String, List<Double>>
        this.map = new HashMap<String, List<Double>>(); 
        for (Item item : this.tempMap.keySet()){
            double quantity = this.tempMap.get(item);
            List<Double> list = new ArrayList<Double>();
            list.add(quantity); 
            //System.out.println(quantity);
            list.add(quantity*item.getPrice()); 
            //System.out.println(quantity*item.getPrice());
            this.map.put(item.getName(), list);
        }
    }

    private void calculateTotals(){
        for (Table table : this.restaurantTables) {
            for (int i=0; i<6; i++){
                Customer customer = table.getCustomerAtTime(i);
                if (customer==null){
                    continue;
                }
                if (customer.getOrder()==null){
                    continue; 
                }
                this.dailyTotal += customer.getOrderPrice(); 
                this.dailyDiscount += customer.getOrderDiscount();
            }
        }
    }
    
    public void writeReportToCSV(){ //writes to the csv file
        try{
            File file =new File("SALESREPORT.csv");
            
            if(!file.exists()){
               file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println("Date," + this.date); 
            pw.println("Item" + "," + "Quantity" + "," + "Revenue (w/o Discount)");
            for (Item item : this.tempMap.keySet()){
                int quantity = this.tempMap.get(item);
                pw.println(item.getName() + "," + quantity + "," + item.getPrice()*quantity);
            }
            pw.print('-');
            pw.printf("\nGRAND TOTAL,%.2f\n" , this.dailyTotal);
            pw.printf("TOTAL DISC GIVEN,%.2f\n" , this.dailyDiscount);
            pw.println("=======================================================");
            pw.close();
  
        System.out.println("Successfully added today's report to the CSV!");
  
         }catch(IOException ioe){
             System.out.println("Exception occurred:");
             ioe.printStackTrace();
        }
    }

    public void printFromObject(){
        System.out.printf("\n==============================         DAY REPORT FOR %s      ===========================\n", this.date);
        System.out.println("Item \t\t\t\t\t\t  Quantity \t\t  Revenue (w/o discount)\n");

        for (String key : this.map.keySet()){
            System.out.print(this.padString(key) + " \t\t\t\t ");
            List<Double> list = this.map.get(key);
            System.out.printf(+ list.get(0) + "\t\t\t\t  %.2f\n",list.get(1)); 
        }
        System.out.println();
        System.out.printf("\nGRAND TOTAL : %.2f",this.dailyTotal);
        System.out.printf("\nTOTAL DISCOUNT GIVEN : %.2f\n",this.dailyDiscount);
    }

    private String padString(String original){
        return String.format("%-" + 30 + "s", original);  

    }

    

}

