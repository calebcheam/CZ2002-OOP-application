package SalesReport_package;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Menu_package.Item;
import Order_package.Order;
import Reservation_package.Customer;
import Reservation_package.Restaurant;
import Reservation_package.Table;

public class SalesReport {
    
    public void writeDayReport(Restaurant restaurant){
        // to be called at end of the Day
        
        Map<Item, Integer> map = new HashMap<Item, Integer>();
        Table[] restaurantTables = restaurant.getTables();

        for (Table table : restaurantTables ) {
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

                    if (map.containsKey(item)){
                        //if daily report contains the menu item already
                        
                        int oldQuantity = map.get(item); 
                        newQuantity += oldQuantity; 
                    }
                    map.put(item, newQuantity);
                }
            }
        }

        

    }

    private void calculateTotalQuantities(){

    }
    


}

