package Order_package;

import java.util.HashMap;
import Menu_package.Item;

public class Order {
    private HashMap<Item, Integer> orders;
    private String date;

    public Order()
    {
        this.date = java.time.LocalDate.now().toString();

        ////
        this.orders = new HashMap<>();
        Item i1 = new Item();
        i1.setName("fkinglongname");
        i1.setPrice(15);
        Item i2 = new Item();
        i2.setName("even longer name");
        i2.setPrice(20);
        this.orders.put(i1,2);
        this.orders.put(i2,1);
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

    public static void main (String[] args){
        Order o1 = new Order();
        System.out.println(o1.totalPrice());
        o1.viewOrders();
        o1.printInvoice();
    }
}
