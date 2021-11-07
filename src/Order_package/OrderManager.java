package Order_package;

import Menu_package.Item;
import Menu_package.Menu;
import Menu_package.MenuManager;
import Menu_package.Menus.AlaCarteMenu;
import Restaurant_package.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderManager {
    private Scanner sc;
    private Restaurant tables;
    private Menu menu;

    private MenuManager menuManager;

    public OrderManager(Restaurant tables, Menu menu)
    {
        this.tables = tables;
        this.menu = menu;
        this.sc = new Scanner(System.in);
        this.menuManager = new MenuManager(); 
    
    }

    public void createOrder() // gonna need table id and timeslot
    {
        Order order = new Order();
        System.out.println("Enter Table Id:");
        int tableId = sc.nextInt();
        System.out.println("Enter timeslot for table "+tableId+":");
        int timeslot = sc.nextInt();

        int check = this.checkIfOrderExists(tableId, timeslot);
        if (check==1){
            System.out.println("excuse me order alr exists");
            return;
        } else if (check==-2){
            return; 
        }
        System.out.println("--------------------- CREATING ORDER ---------------------");
        System.out.println("Customer Name : " + this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
        System.out.println("Table ID : " + tableId);
        System.out.println("----------------------------------------------------------");
        while (true){
            System.out.println("(1) Add items (2) Remove items (3) Confirm order (4) Quit");
            int c = sc.nextInt();

            if (c == 4){
                break;
            }
            else if (c==1){
                this.addItemToOrder(order);
            }
            else if (c==2){
                this.removeItemFromOrder(order);
            }
            else if (c==3){
                System.out.println("Confirm current order to be:");
                order.viewOrders();
                System.out.println("(1) Yes (2) FUck u");
                
                int cfm = sc.nextInt();
                if (cfm==1){

                    this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).addOrder(order);
                    System.out.println("Order created successfully! This is the order added : ");
                    System.out.println(" - - - - - - - - - - - - - - - -\tORDER\t- - - - - - -- - - - - - - - - -");
                    this.viewOrder(tableId, timeslot);
                    break;

                }
                else{
                    System.out.println("Order was not created");
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
        System.out.println("===================== EDIT ORDER =====================");
        System.out.println("Enter Table Id:");
        int tableId = sc.nextInt();
        System.out.println("Enter timeslot for table "+tableId+":");
        int timeslot = sc.nextInt();

        int check = this.checkIfOrderExists(tableId, timeslot);
        if (check==1){
            Order order = this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder();
            order.viewOrders();
            this.addItemToOrder(order);

        } else if (check==-1){
            System.out.println("ERROR : Reservation found, but there is no order.");

        } 
        
    }   

    public void viewOrder(int tableId, int timeslot)
    {
        int check = checkIfOrderExists(tableId, timeslot);
        if (check==1){ // order found
            this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder().viewOrders();
        } else if (check==-1){
            System.out.println("Reservation at table " + tableId + this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName() + " found, but no order assigned yet.");
            System.out.println("Would you like to create a new order now?\n (Y/N) ");
            char response = sc.next().charAt(0);
            if (response=='Y'){
                this.createOrder();
            } 
        }
    }

    private void addItemToOrder(Order order){
        Item item = this.menuManager.selectItemFromMenu();
        System.out.println("Select quantity of "+item.getName()+" to be added:");
        int quantity = sc.nextInt();
        order.setOrders(item, quantity);
    }

    private void removeItemFromOrder(Order order){
        order.viewOrders();
        int itemIndex;
        
        System.out.println("Which item to remove? (Enter -1 to cancel) ");
        itemIndex = sc.nextInt();
        while (itemIndex>order.getOrderedItems().size() || itemIndex<=0){
            if (itemIndex == -1) return;

            System.out.println("eh hello choose properly. Try again? ");
            itemIndex = sc.nextInt();
        }

        ArrayList<String> itemNames = order.getOrderedItemsNames();
        String remove = itemNames.get(itemIndex-1);
    
        System.out.println("Enter quantity of "+remove+" to be removed:");
        int quantity = sc.nextInt();
        for (Item key: order.getOrderedItems().keySet())
        {
            if (key.getName().equals(remove)){

                int oldQuant = order.getOrderedItems().get(key);
                order.setOrders(key, oldQuant-quantity);
                System.out.println("======================================================");
                System.out.println("Order Edited Successfully! New Quantity of "+key.getName()+" is "+order.getOrderedItems().get(key));
                System.out.println("");
                order.viewOrders();
            } 
        }
        
    }

    private int checkIfOrderExists(int tableId, int timeslot){

        if (this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1)!=null){
            System.out.println("Customer : " + this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
            if (this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder()!=null)
            {
                return 1; // order found at this slot
            } else return -1; // reservation found, but no order assigned yet
        } else {
            System.out.println("ERROR: No reservation found at this table and timeslot!");
            return -2; // meaning no reservation at this specific slot 
        }
    }
}
