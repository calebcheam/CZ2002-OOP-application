package Order_package;

import Reservation_package.Restaurant;
import Menu_package.Item;
import java.util.Scanner;

public class OrderManager {
    private Scanner sc;
    private Restaurant tables;

    public OrderManager(Restaurant tables)
    {
        this.tables = tables;
        this.sc = new Scanner(System.in);
    }

    public void createOrder() // gonna need table id and timeslot
    {
        Order order = new Order();
        System.out.println("Table Id:");
        int tableId = sc.nextInt();
        System.out.println("Timeslot:");
        int timeslot = sc.nextInt();
        
        if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1)!=null)
        {
            this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).addOrder(order);
            if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder()==null)
            {
                System.out.println("Failed to add order...");
            }
            System.out.println("Successfully added order to "+this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
            
        }
        else
        {
            System.out.println("There is no customer at this table/timeslot!");
            return;
        }

    }

    public void editOrder() //add and remove
    {
        System.out.println("Table id:");
        int tableId = sc.nextInt();
        System.out.println("Timeslot:");
        int timeslot = sc.nextInt();
        System.out.println("Item");
        //Item item = MENU.GET(ITEM)
        System.out.println("Quantity");
        int quantity = sc.nextInt();
        Item dummyItem = new Item();
        dummyItem.setName("broliterallyfuckoop");
        dummyItem.setPrice(100);

        if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1)!=null)
        {
            
            this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder().setOrders(dummyItem, quantity);
            if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder()==null)
            {
                System.out.println("Theres no order...");
            }
            System.out.println("Successfully edited order of "+this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
            this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).print_invoice();

        }
        else
        {
            System.out.println("There is no customer at this table/timeslot!");
            return;
        }
        

    }

    
}
