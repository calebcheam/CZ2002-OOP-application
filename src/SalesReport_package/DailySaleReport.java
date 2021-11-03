package SalesReport_package;

import java.util.HashMap;
import java.util.Map;

import Menu_package.Item;
import Order_package.Order;
import Reservation_package.Customer;
import Reservation_package.Restaurant;
import Reservation_package.Table;

public class DailySaleReport {
    //this stores one day's worth orders
    private double dailyTotal; 

    //for easier referencing in the method functions
    private Restaurant restaurant; 
    private Table[] restaurantTables; 
    private Map<Item, Integer> map;

    public DailySaleReport(Restaurant restaurant){
        this.restaurant = restaurant; 
        this.restaurantTables = restaurant.getTables(); 
        this.map = new HashMap<Item, Integer>();
    }
    public double getDailyTotal(){
        return this.dailyTotal;
    }

    private void calculateDailyTotal(){
        double total = 0.0;
        for (Table table : this.restaurantTables) {
            for (int i=0; i<6; i++){
                Customer customer = table.getCustomerAtTime(i);
                //total += customer.getOrderPrice(); 
            }
        }
        this.dailyTotal = total;
    }
    public void writeDayReport(Restaurant restaurant){
        // to be called at end of the Day

        for (Table table : this.restaurantTables ) {
            // loop through the restaurant's tables
            for (int i=0; i<6; i++){
                // loop through each time slot for a table

                Customer customer = table.getCustomerAtTime(i);
                Order order = customer.getOrder();
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

    private void calculateTotalQuantities(){

    }
    


}

