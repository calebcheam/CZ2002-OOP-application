package Order_package;

import java.util.HashMap;
import Menu_package.Item;

public class Order {
    protected HashMap<Item, Integer> orders;
    private String date;

    public Order()
    {
        this.date = java.time.LocalDate.now().toString();
    }

    public HashMap<Item, Integer> getOrderedItems()
    {
        return this.orders;
    }

    public void viewOrders()
    {
        for (Item item : this.orders.keySet())
        {
            System.out.print(item.getName()+", ");
        }
        System.out.println();
        
    }
    
    public float totalPrice()
    {
        float res = 0;
        for (Item item: orders.keySet())
        {
            res+=item.getPrice()*orders.get(item); // get items (keys) and multiply their price by quantity (values)
        }
        return res;
    }

    public void printInvoice()
    {
        // name, date, quantity, price, totalAmount
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
        System.out.println("Total Price = "+totalPrice());


    }

}
