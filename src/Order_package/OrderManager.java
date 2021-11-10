package Order_package;

import Menu_package.Item;
import Menu_package.Menu;
import Menu_package.MenuManager;
import Menu_package.Menus.AlaCarteMenu;
import Menu_package.Menus.GenericMenu;
import Restaurant_package.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderManager {
    private Scanner sc;
    private Restaurant tables;

    private MenuManager menuManager;

    public OrderManager(Restaurant tables)
    {
        this.tables = tables;
        this.sc = new Scanner(System.in);
        this.menuManager = new MenuManager(); 
    
    }

    private void createOrderGivenReservation(int tableId, int timeslot){
        Order order = new Order();
        System.out.println("--------------------- CREATING ORDER ---------------------");
        System.out.println("Customer Name : " + this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
        System.out.println("Table ID : " + tableId);
        System.out.println("----------------------------------------------------------");
        while (true){
            System.out.println("(1) Add new item (2) Edit existing item qty (3) Confirm order (4) Quit");
            int c = sc.nextInt();

            if (c == 4){
                break;
            }
            else if (c==1){
                this.addNewItemToOrder(order);
            }
            else if (c==2){
                this.updateItemQty(order);
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
                    this.displayOrder(tableId, timeslot);
                    break;

                }
                else{
                    System.out.println("Order was not created");
                    continue;
                }

            }
        }
    }

    public void createOrder() // gonna need table id and timeslot
    {
        System.out.println("Enter Table Id:");
        int tableId = sc.nextInt();
        System.out.println("Enter timeslot for table "+tableId+":");
        int timeslot = sc.nextInt();
        System.out.println("________________________________________");
        int check = this.checkIfOrderExists(tableId, timeslot);
        if (check==1){
            System.out.println("excuse me order alr exists");
            return;
        } else if (check==-2){
            return; 
        }
        this.createOrderGivenReservation(tableId, timeslot);
    }

    public void editOrder() //add and remove
    {
        System.out.println("===================== EDIT ORDER =====================");
        System.out.println("Enter Table Id:");
        int tableId = sc.nextInt();
        System.out.println("Enter timeslot for table "+tableId+":");
        int timeslot = sc.nextInt();
        System.out.println("________________________________________");
        int check = this.checkIfOrderExists(tableId, timeslot);
        if (check==1){
            Order order = this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder();
            System.out.println(" - - - - - - - - - - - -\tCURRENT ORDER\t- - - - - - - - - - - - -");
            order.viewOrders();
            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            
            while (true){
                System.out.println("(1) Add new item (2) Edit existing item qty (3) Quit");
                int c = sc.nextInt();
    
                if (c == 3){
                    break;
                }
                else if (c==1){
                    this.addNewItemToOrder(order);
                    System.out.println("Order Changed!");
                    System.out.println(" - - - - - - - - - - - -\tUPDATED ORDER\t- - - - - - - - - - - - -");
                    order.viewOrders();
                    System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                }
                else if (c==2){
                    this.updateItemQty(order);
                    System.out.println("Order Changed!");
                    System.out.println(" - - - - - - - - - - - -\tUPDATED ORDER\t- - - - - - - - - - - - -");
                    order.viewOrders();
                    System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                }
                
            }

        } else if (check==-1){
            
            this.promptNewOrder(tableId, timeslot);
        } 
        
    }   

    private void promptNewOrder(int tableId, int timeslot){
        System.out.println("Order does not exist for this customer.Would you like to create a new order now?");
        System.out.println("(1) YES! (2) Nah ");
        int choice = sc.nextInt();
        if (choice==1){
            this.createOrderGivenReservation(tableId, timeslot);
        }
    }
   
    public void viewOrder(){
        System.out.println("===================== VIEW EXISTING ORDER =====================");
        System.out.println("Enter Table Id:");
        int tableId = sc.nextInt();
        System.out.println("Enter timeslot for table "+tableId+":");
        int timeslot = sc.nextInt();
        System.out.println("________________________________________");
        this.displayOrder(tableId, timeslot);
    }

    private void displayOrder(int tableId, int timeslot)
    {
        int check = checkIfOrderExists(tableId, timeslot);
        if (check==1){ // order found
            this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder().viewOrders();
        } else if (check==-1){
            this.promptNewOrder(tableId, timeslot);
        }
    }

    private void addNewItemToOrder(Order order){
        Item item = this.menuManager.orderItemfromMenu();
        System.out.println("Select quantity of "+item.getName()+" to be added:");
        int quantity = sc.nextInt();
        order.setOrders(item, quantity);
    }


    private void updateItemQty(Order order){
        order.viewOrders();
        int itemIndex;
        
        System.out.println("Which item to change? (Enter -1 to cancel) ");
        itemIndex = sc.nextInt();
        while (itemIndex>order.getOrderedItems().size() || itemIndex<=0){
            if (itemIndex == -1) return;

            System.out.println("Error. Please try again? ");
            itemIndex = sc.nextInt();
        }

        ArrayList<String> itemNames = order.getOrderedItemsNames();
        String selectedItem = itemNames.get(itemIndex-1);

        System.out.println("Enter new quantity of "+selectedItem + ":");
        int quantity = sc.nextInt();
        for (Item key: order.getOrderedItems().keySet())
        {
            if (key.getName().equals(selectedItem)){

                //int oldQuant = order.getOrderedItems().get(key);
                order.setOrders(key, quantity);
                System.out.println("======================================================");
                System.out.println("Order Edited Successfully!\nNew quantity of "+key.getName()+" is "+order.getOrderedItems().get(key));
                System.out.println("");
                order.viewOrders();
            } 
        }
        
    }

    private int checkIfOrderExists(int tableId, int timeslot){

        if (this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1)!=null){
            System.out.println("Reservation at table " + tableId + " found for " +
                this.tables.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());

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
