package Order_package;
import java.util.ArrayList;
import java.util.HashMap;
import Menu_package.Item;
import java.util.Scanner;
import java.util.Set;

/**
 * <code>Order</code> class that contains all ordered items for a specific <code>Customer</code>, <code>Table</code> and timeslot
 * Also includes <code>date</code> of order creation and handler staff name 
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class Order {
    /**
     * <code>HashMap</code> containing ordered <code>Item</code> objects and quantities
     */
    protected HashMap<Item, Integer> orders=new HashMap<Item,Integer>();
    /**
     * Date this <code>Order</code> was created
     */
    private String date;
    /**
     * Name of staff handling this <code>Order</code>
     */
    private String staffName;
    /**
     * <code>Scanner</code> used for user input
     */
    private Scanner sc = new Scanner(System.in);
    /** 
     * <code>Order</code> constructor that prompts for name of staff handling this order
     */
    public Order()
    {
        this.date = java.time.LocalDate.now().toString();
        System.out.println("Enter staff name:");
        this.staffName = sc.nextLine();
        System.out.println("This order is handled by "+getStaff());
    }

    
    /** 
     * Gets the date that this order was created 
     * @return <code>String</code> of date this <code>Order</code> was created
     */
    public String getDate(){
        return this.date; 
    }

    
    /** 
     * Gets the name of the staff handling this <code>Order</code>
     * @return <code>String</code> of staff name that handles this <code>Order</code>
     */
    public String getStaff()
    {
        return this.staffName;
    }

    
    /** 
     * Gets the <code>HashMap</code> of all ordered <code>Item</code> objects and their quantities
     * @return <code>HashMap</code> of <code>Item</code> : quantity 
     */
    public HashMap<Item, Integer> getOrderedItems()
    {
        return this.orders;
    }

    
    /** 
     * Sets quantity of each menu <code>Item</code> in this <code>Order</code>
     * @param item <code>Item</code> object to be set
     * @param quantity <code>int</code> quantity of <code>Item</code> object to be set
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
     * Gets an <code>ArrayList</code> of all ordered <code>Item</code> names
     * @return <code>ArrayList</code> of <code>String</code> names of ordered items
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
     * Gets the total price of this <code>Order</code> before tax and discounts (if any)
     * @return <code>float</code> of raw untaxed and undiscounted price of this <code>Order</code>
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
     * Gets the total price of this <code>Order</code> after both tax and member discount (10%)
     * @return <code>double</code> of price of this <code>Order</code> taxed and with member discount (10%)
     */
    public double memberGstTotal()
    {
        return totalPrice()*0.9*1.17;
    }

    
    /**
     * Gets total price of this <code>Order</code> after tax, without discount 
     * @return <code>double</code> of price of items, taxed without member discount
     */
    public double nonMemberGstTotal()
    {
        return totalPrice()*1.17;
    }

    /** 
     * Prints invoice of this <code>Order</code> if non-member (no discount)
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
     * Prints invoice of this <code>Order</code> if member (10% discount)
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
