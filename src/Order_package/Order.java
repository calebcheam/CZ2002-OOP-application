package Order_package;
import java.util.ArrayList;
import java.util.HashMap;
import Menu_package.Item;
import java.util.Scanner;
import java.util.Set;

/**
 * Order class that contains all ordered items for a specific customer, table and timseslot
 * Also includes date of order creation and handler staff name 
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class Order {
    /**
     * HashMap containing ordered Item objects and quantities
     */
    protected HashMap<Item, Integer> orders=new HashMap<Item,Integer>();
    private String date;
    private String staffName;
    private Scanner sc = new Scanner(System.in);
    /** 
     * Order constructor that prompts for name of staff handling the order
     */
    public Order()
    {
        this.date = java.time.LocalDate.now().toString();
        System.out.println("Enter staff name:");
        this.staffName = sc.nextLine();
        System.out.println("This order is handled by "+getStaff());
    }

    
    /** 
     * Gets the date that the order was created 
     * @return String of date the order was created
     */
    public String getDate(){
        return this.date; 
    }

    
    /** 
     * Gets the name of the staff handling the order
     * @return String of staff name that handles order
     */
    public String getStaff()
    {
        return this.staffName;
    }

    
    /** 
     * Gets the HashMap of all ordered items
     * @return HashMap of all ordered items
     */
    public HashMap<Item, Integer> getOrderedItems()
    {
        return this.orders;
    }

    
    /** 
     * Sets quantity of each Menu item in the HashMap
     * @param item Item object 
     * @param quantity Quantity of item object
     */
    public void setOrders(Item item, Integer quantity)
    {
        if (quantity>0){
            this.orders.put(item, quantity);
        } else {
            this.orders.remove(item);
        }
        

    }

    
    /** 
     * Gets an ArrayList of strings of all ordered item names
     * @return ArrayList of names of ordered items
     */
    public ArrayList<String> getOrderedItemsNames(){
        Set<Item> items = this.getOrderedItems().keySet();
        ArrayList<String> nameList = new ArrayList<String>();
        for (Item item : items){
            nameList.add(item.getName());
        }
        return nameList;
    }

    /** 
     * Displays all items in the order and their quantities
     */
    public void viewOrders()
    {
        
        int index=1;
        for (Item item : this.orders.keySet())
        {
            System.out.println(index + " : " + item.getName()+" x"+this.orders.get(item)+" / ");
            index++;
        }
        System.out.println();
        
    }
    
    
    /** 
     * Gets the total price of order before tax and discounts (if any)
     * @return float of raw untaxed and undiscounted price of items
     */
    public float totalPrice()
    {
        float res = 0;
        for (Item item: orders.keySet())
        {
            res+=item.getPrice()*orders.get(item); // get items (keys) and multiply their price by quantity (values)
        }
        return res;
    }

    
    /** 
     * Gets the total price after both tax and member discount (10%)
     * @return double of price of items taxed and with member discount (10%)
     */
    public double memberGstTotal()
    {
        return totalPrice()*0.9*1.17;
    }

    
    /**
     * Gets total price after tax, without discount 
     * @return double of price of items, taxed without member discount
     */
    public double nonMemberGstTotal()
    {
        return totalPrice()*1.17;
    }

    /** 
     * Prints invoice if non-member (no discount)
     */
    public void printInvoiceRaw()
    {
        // name, date, quantity, price, totalAmount
        System.out.println("Order created by: "+this.staffName);
        System.out.println("Invoice for date:");
        System.out.println(this.date);
        System.out.println("============================");
        System.out.println("|Name        |Quantity |Price |Amount |");
        for (Item item: orders.keySet())
        {
            System.out.print(item.getName().substring(0, Math.min(item.getName().length(), 10))+"... ");
            System.out.print(orders.get(item)+"         ");
            System.out.print(item.getPrice()+"     ");
            System.out.println(item.getPrice()*orders.get(item));
        }
        System.out.println("Member? No");
        System.out.println("Total Price with GST = "+nonMemberGstTotal());


    }

    /** 
     * Prints invoice if member (10% discount)
     */
    public void printInvoiceMember()
    {
        // name, date, quantity, price, totalAmount
        System.out.println("Order created by: "+this.staffName);
        System.out.println("Invoice for date:");
        System.out.println(this.date);
        System.out.println("============================");
        System.out.println("|Name        |Quantity |Price |Amount |");

        for (Item item: orders.keySet())
        {
            System.out.print(item.getName().substring(0, Math.min(item.getName().length(), 10))+"... ");
            System.out.print(orders.get(item)+"         ");
            System.out.print(item.getPrice()+"     ");
            System.out.println(item.getPrice()*orders.get(item));
        }
        System.out.println("Member? Yes");
        System.out.println("Total Price with GST and 10% membership discount = "+memberGstTotal());

    }

}
