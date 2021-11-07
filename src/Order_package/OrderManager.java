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
        while (true){
            System.out.println("(1) Add items (2) Remove items (3) Confirm order (4) Quit");
            int c = sc.nextInt();
            if (c == 4){
                break;
            }
            else if (c==1){
                System.out.println("Select item from menu:");
                // Item item = this.menu.getItem();
                Item item = new Item();
                item.setName("DUMMYITEM");
                item.setPrice(100);
                System.out.println("Select quantity of "+item.getName()+" to be added:");
                int quantity = sc.nextInt();
                order.setOrders(item, quantity);
            }
            else if (c==2){
                order.viewOrders();
                System.out.println("Enter item name to be removed from order:");
                sc.nextLine();
                String remove = sc.nextLine();
                System.out.println("Select quantity of "+remove+" to be removed:");
                int quantity = sc.nextInt();
                for (Item key: order.getOrderedItems().keySet())
                {
                    if (key.getName().equals(remove)){

                        int oldQuant = order.getOrderedItems().get(key);
                        order.setOrders(key, oldQuant-quantity);
                        System.out.println("New quantity of "+key.getName()+" is "+order.getOrderedItems().get(key));
                    }
                }
            }
            else if (c==3){
                System.out.println("Confirm current order to be:");
                order.viewOrders();
                System.out.println("(1) Yes (2) FUck u");
                int cfm = sc.nextInt();
                if (cfm==1){
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
                        else{
                            System.out.println("Successfully added order to "+this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
                            break;
                        }
                        
                    }
                    else
                    {
                        System.out.println("There is no customer at this table/timeslot!");
                    }
                }
                else if (cfm==2){
                    continue;
                }

            }
            else if (c==4)
            {
                break;
            }
        }
    }

    public void editOrder() //add and remove
    {
        System.out.println("Select table id:");
        int tableId = sc.nextInt();
        System.out.println("Select timeslot for table "+tableId+":");
        int timeslot = sc.nextInt();
        System.out.println("Select item from menu:");
        // Item item = this.menu.getItem();
        Item item = new Item();
        item.setName("ANOTHERITEM");
        item.setPrice(700);
        System.out.println("Select quantity of "+item.getName()+":");
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
