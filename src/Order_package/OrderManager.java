package Order_package;

import Menu_package.Item;
import Menu_package.Menu;
import Restaurant_interface_package.Restaurant;

import java.util.Scanner;

public class OrderManager {
    private Scanner sc;
    private Restaurant tables;
    private Menu menu;

    public OrderManager(Restaurant tables, Menu menu)
    {
        this.tables = tables;
        this.menu = menu;
        this.sc = new Scanner(System.in);
    }

    public void createOrder() // gonna need table id and timeslot
    {
        Order order = new Order();
        System.out.println("Select table id:");
        int tableId = sc.nextInt();
        System.out.println("Select timeslot for table "+tableId+":");
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
        }

    }

    public void editOrder() //add and remove
    {
        System.out.println("Select table id:");
        int tableId = sc.nextInt();
        System.out.println("Select timeslot for table "+tableId+":");
        int timeslot = sc.nextInt();
        // System.out.println("Select item from menu:");
        // Item item = this.menu.getItem();
        /////////////////////////////////////////////
        Item item = new Item();
        item.setName("broliterallyfuckoop");
        item.setPrice(100);
        /////////////////////////////////////////////
        System.out.println("Select quantity of "+item.getName()+" to be added:");
        int quantity = sc.nextInt();

        if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1)!=null)
        {
            
            this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder().setOrders(item, quantity);
            if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder()==null)
            {
                System.out.println("Theres no order...");
            }
            System.out.println("Successfully edited order of "+this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
            System.out.println("Current order is: ");
            this.viewOrder(tableId, timeslot);

        }
        else
        {
            System.out.println("There is no customer at this table/timeslot!");
        }
    }   

    public void viewOrder(int tableId, int timeslot)
    {
        if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1)!=null)
        {
            this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder().viewOrders();
            if(this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder()==null)
            {
                System.out.println("Theres no order...");
            }

        }
        else
        {
            System.out.println("There is no customer at this table/timeslot!");
        }

    }
}
