package Order_package;
import java.util.ArrayList;
import java.util.HashMap;
import Menu_package.Item;
import java.util.Scanner;
import java.util.Set;

public class Order {
    protected HashMap<Item, Integer> orders=new HashMap<Item,Integer>();
    private String date;
    private String staffName;
    private Scanner sc = new Scanner(System.in);
    public Order()
    {
        this.date = java.time.LocalDate.now().toString();
        System.out.println("Enter staff name:");
        this.staffName = sc.nextLine();
        System.out.println("This order is handled by "+getStaff());
    }

    public String getDate(){
        return this.date; 
    }

    public String getStaff()
    {
        return this.staffName;
    }

    public HashMap<Item, Integer> getOrderedItems()
    {
        return this.orders;
    }

    public void setOrders(Item item, Integer quantity)
    {
        if (quantity>0){
            this.orders.put(item, quantity);
        } else {
            this.orders.remove(item);
        }
        

    }

    public ArrayList<String> getOrderedItemsNames(){
        Set<Item> items = this.getOrderedItems().keySet();
        ArrayList<String> nameList = new ArrayList<String>();
        for (Item item : items){
            nameList.add(item.getName());
        }
        return nameList;
    }

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
    
    public float totalPrice()
    {
        float res = 0;
        for (Item item: orders.keySet())
        {
            res+=item.getPrice()*orders.get(item); // get items (keys) and multiply their price by quantity (values)
        }
        return res;
    }

    public double memberGstTotal()
    {
        return totalPrice()*0.9*1.17;
    }

    public double nonMemberGstTotal()
    {
        return totalPrice()*1.17;
    }

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
