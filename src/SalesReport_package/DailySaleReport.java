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

/** 
 * <code>DailySaleReport</code> class that stores one day's worth of orders
 */
public class DailySaleReport { 

    /** 
     * Total revenue from one day
     */
    private double dailyTotal;
    /** 
     * Total discount given in one day
     */
    private double dailyDiscount;
    /** 
     * Used to calculate today's addition (will be empty for past reports)
     */
    private Map<Item, Integer> tempMap; 
    /** 
     * Total quantity sold by item as well as total revenue from those sales
     */
    private Map<String, List<Double>> map;
    /** 
     * Date of this sales report
     */
    private String date; 

   
    
      //for easier referencing in the method functions

    /** 
     * <code>DailySaleReport</code> constructor for reading in past reports from csv, with parameters
     * @param dailyTotal <code>double</code> of total revenue in a day
     * @param dailyDiscount <code>double</code> of total discounts given in said day
     * @param map <code>Map</code> of <code>String: int</code> with item name as keys, and quantity/revenue of said item sold as values
     * @param date date of this sales report  
     */
    public DailySaleReport(double dailyTotal, double dailyDiscount, Map<String, List<Double>> map, String date)
    {
        
        this.dailyTotal = dailyTotal; 
        this.dailyDiscount = dailyDiscount;
        this.map = map;
        this.date = date;
        //the tempMap will be empty 
    }

    /** 
     * <code>DailySaleReport</code> constructor for new daily sales report (ie. today's sales)
     */
    public DailySaleReport(){
        
        
        this.date = java.time.LocalDate.now().toString();
        this.calculateMenuItems(); //filling in menu items
        this.tempMapToMap(); //convert Item to Strings
        this.calculateTotals(); //tabulate dailyTotal & total discount given
    }

    
    /** 
     * Gets the date of this <code>DailySaleReport</code>
     * @return <code>String</code> date of this report
     */
    public String getDate(){
        return this.date;
    }

    
    /** 
     * Gets the daily total revenue of this <code>DailySaleReport</code>
     * @return <code>double</code> revenue from this day's sales
     */
    public double getDailyTotal(){
        return this.dailyTotal;
    }

    
    /** 
     * Gets total discount given in this <code>DailySaleReport</code>
     * @return <code>double</code> total discounts given in this day's sales
     */
    public double getDiscountTotal(){
        return this.dailyDiscount;
    }

    
    /** 
     * Gets the <code>Map</code> of item names and their quantities sold in this <code>DailySaleReport</code>
     * @return <code>Map</code> with item name as keys and List of quantity sold/revenue as valeus
     */
    public Map<String, List<Double>> getMap(){
        return this.map;
    }

    /** 
     * Calculates the daily sales by tabulating orders made for all tables and timeslots
     * Updates tempMap with tabulated data
     */
    private void calculateMenuItems(){
        // constructor for reading in today's sales 
        
        this.tempMap = new HashMap<Item, Integer>();
        for (Table table : Restaurant.getTables() ) {
            
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
                        if (!item.getName().contains("(Discounted)")){
                            item.setName("(Discounted)" + item.getName());
                        }
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

    /** 
     * Converts data from tempMap of a single day's Item objects and respective quantities sold to a csv readable format of Strings and doubles
     */
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

    /** 
     * Calculates total revenue and discount given in a day (if any)
     */
    private void calculateTotals(){
        for (Table table : Restaurant.getTables()) {
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
    
    
    /** 
     * Converts this <code>DailySaleReport</code> information to <code>String</code> format 
     * @return <code>ArrayList</code> of strings of this daily sales report
     */
    public ArrayList<String> DSRtoStrings(){
        ArrayList<String> dsrAsStrings = new ArrayList<String>();
        dsrAsStrings.add("Date," + this.date);
        dsrAsStrings.add("Item" + "," + "Quantity" + "," + "Revenue (w/o Discount)");
        for (Item item : this.tempMap.keySet()){
            int quantity = this.tempMap.get(item);
            dsrAsStrings.add(item.getName() + "," + quantity + "," + item.getPrice()*quantity);
        }
        dsrAsStrings.add("-");
        String grandTotalString = String.format("GRAND TOTAL,%.2f", this.dailyTotal);
        String totalDiscountString = String.format("TOTAL DISC GIVEN, %.2f", this.dailyDiscount);
        dsrAsStrings.add(grandTotalString);
        dsrAsStrings.add(totalDiscountString);
        dsrAsStrings.add("=======================================================");
        return dsrAsStrings;
    }

    /** 
     * Displays this sales report information in a readable format
     */
    public void printDSR(){
        System.out.printf("\n==============================         DAY REPORT FOR %s      ===========================\n", this.date);
        System.out.println("Item \t\t\t\t\t  Quantity \t\t  Revenue (w/o discount)\n");

        for (String key : this.map.keySet()){
            System.out.print(this.padString(key) + " \t\t\t\t ");
            List<Double> list = this.map.get(key);
            System.out.printf(+ list.get(0) + "\t\t\t\t  %.2f\n",list.get(1)); 
        }
        System.out.println();
        System.out.printf("\nGRAND TOTAL : %.2f",this.dailyTotal);
        System.out.printf("\nTOTAL DISCOUNT GIVEN : %.2f\n",this.dailyDiscount);
    }

    
    /** 
     * Formatting function for readable printing
     * @param original String to be formatted
     * @return String that has been formatted
     */
    private String padString(String original){
        return String.format("%-" + 35 + "s", original);  

    }

    

}

