package Order_package;

import Menu_package.Item;

import Menu_package.MenuManager;
import Restaurant_package.Restaurant;

import java.util.ArrayList;
import java.util.Scanner;

public class OrderManager {
    
    private static Scanner sc=new Scanner(System.in);
    
    private static void createOrderGivenReservation(int tableId, int timeslot){
        Order order = new Order();
        System.out.println("--------------------- CREATING ORDER ---------------------");
        System.out.println("                    (Enter -1 to exit)                    ");
        System.out.println("Customer Name : " + Restaurant.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());
        System.out.println("Table ID : " + tableId);
        System.out.println("----------------------------------------------------------");
        while (true){
            System.out.println("(1) Add new item (2) Edit existing item qty (3) Confirm order");
            int c = sc.nextInt();

            if (c == -1){
                break;
            }
            else if (c==1){
                addNewItemToOrder(order);
            }
            else if (c==2){
                updateItemQty(order);
            }
            else if (c==3){
                System.out.println("Confirm current order to be:");
                order.viewOrders();
                System.out.println("(1) Yes (2) No");
                
                int cfm = sc.nextInt();
                if (cfm==1){

                    Restaurant.getTables()[tableId-1].getCustomerAtTime(timeslot-1).addOrder(order);
                    System.out.println("Order created successfully! This is the order added : ");
                    System.out.println(" - - - - - - - - - - - - - - - -\tORDER\t- - - - - - -- - - - - - - - - -");
                    displayOrder(tableId, timeslot);
                    break;

                }
                else{
                    System.out.println("Order was not created");
                    continue;
                }

            }
            else {
                System.out.println("Invalid option, please re-enter.");
                continue;
            }
        }
    }

    public static void createOrder() // gonna need table id and timeslot
    {
        int tableId;
        int timeslot;
        while (true){
            System.out.println("Enter Table Id:");
            tableId = sc.nextInt();
            if (tableId>10 || tableId<1){
                System.out.println("Invalid Table ID, please re-enter.");
                continue;
            }
            break;
        }
        while (true){
            System.out.println("Enter timeslot for table "+tableId+":");
            timeslot = sc.nextInt();
            if (timeslot>6 || timeslot<1){
                System.out.println("Invalid timeslot, please re-enter.");
                continue;
            }
            break;
        }
        System.out.println("________________________________________");
        int check = checkIfOrderExists(tableId, timeslot);
        if (check==1){
            System.out.println("Order already exists!");
            return;
        } else if (check==-2){
            return; 
        }
        createOrderGivenReservation(tableId, timeslot);
    }

    public static void editOrder() //add and remove
    {
        System.out.println("===================== EDIT ORDER =====================");
        int tableId;
        int timeslot;
        while (true){
            System.out.println("Enter Table Id:");
            tableId = sc.nextInt();
            if (tableId>10 || tableId<1){
                System.out.println("Invalid Table ID, please re-enter.");
                continue;
            }
            break;
        }
        while (true){
            System.out.println("Enter timeslot for table "+tableId+":");
            timeslot = sc.nextInt();
            if (timeslot>6 || timeslot<1){
                System.out.println("Invalid timeslot, please re-enter.");
                continue;
            }
            break;
        }
        System.out.println("________________________________________");
        int check = checkIfOrderExists(tableId, timeslot);
        if (check==1){
            Order order = Restaurant.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder();
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
                    addNewItemToOrder(order);
                    System.out.println("Order Changed!");
                    System.out.println(" - - - - - - - - - - - -\tUPDATED ORDER\t- - - - - - - - - - - - -");
                    order.viewOrders();
                    System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                }
                else if (c==2){
                    updateItemQty(order);
                    System.out.println("Order Changed!");
                    System.out.println(" - - - - - - - - - - - -\tUPDATED ORDER\t- - - - - - - - - - - - -");
                    order.viewOrders();
                    System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                }
                else {
                    System.out.println("Invalid option, please re-enter.");
                    continue;
                }
                
            }

        } else if (check==-1){
            
            promptNewOrder(tableId, timeslot);
        } 
        
    }   

    private static void promptNewOrder(int tableId, int timeslot){
        System.out.println("Order does not exist for this customer.Would you like to create a new order now?");
        System.out.println("(1) Yes (2) No ");
        int choice = sc.nextInt();
        if (choice==1){
            createOrderGivenReservation(tableId, timeslot);
        }
    }
   
    public static void viewOrder(){
        System.out.println("===================== VIEW EXISTING ORDER =====================");
        int tableId;
        int timeslot;
        while (true){
            System.out.println("Enter Table Id:");
            tableId = sc.nextInt();
            if (tableId>10 || tableId<1){
                System.out.println("Invalid Table ID, please re-enter.");
                continue;
            }
            break;
        }
        while (true){
            System.out.println("Enter timeslot for table "+tableId+":");
            timeslot = sc.nextInt();
            if (timeslot>6 || timeslot<1){
                System.out.println("Invalid timeslot, please re-enter.");
                continue;
            }
            break;
        }
        System.out.println("________________________________________");
        displayOrder(tableId, timeslot);
    }

    private static void displayOrder(int tableId, int timeslot)
    {
        int check = checkIfOrderExists(tableId, timeslot);
        if (check==1){ // order found
            Restaurant.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder().viewOrders();
        } else if (check==-1){
            promptNewOrder(tableId, timeslot);
        }
    }

    private static void addNewItemToOrder(Order order){
        Item item = MenuManager.orderItemfromMenu();
        System.out.println("Select quantity of "+item.getName()+" to be added:");
        int quantity = sc.nextInt();
        order.setOrders(item, quantity);
    }


    private static void updateItemQty(Order order){
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

                
                order.setOrders(key, quantity);
                System.out.println("======================================================");
                if (order.getOrderedItems().get(key)!=null)
                {
                    System.out.println("Order Edited Successfully!\nNew quantity of "+key.getName()+" is "+order.getOrderedItems().get(key));
                } else {
                    System.out.println("Order Edited Successfully!");
                    System.out.println(key.getName() + " has been removed from the order. ");
                }
                
                System.out.println("");
                order.viewOrders();
            } 
        }
        
    }

    private static int checkIfOrderExists(int tableId, int timeslot){

        if (Restaurant.getTables()[tableId-1].getCustomerAtTime(timeslot-1)!=null){
            System.out.println("Reservation at table " + tableId + " found for " +
            Restaurant.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getName());

            if (Restaurant.getTables()[tableId-1].getCustomerAtTime(timeslot-1).getOrder()!=null)
            {
                return 1; // order found at this slot
            } else return -1; // reservation found, but no order assigned yet
        } else {
            System.out.println("ERROR: No reservation found at this table and timeslot!");
            return -2; // meaning no reservation at this specific slot 
        }
    }
}
