package Order_package;
import Reservation_package.Customer;
import Reservation_package.Table;
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
        System.out.println("table id");
        int tableId = sc.nextInt();
        System.out.println("timeslot");
        int timeslot = sc.nextInt();
        this.tables.getTables()[tableId].getCustomerAtTime(timeslot).addOrder(order);

    }

    public void editOrder()
    {
        System.out.println("table id");
        int tableId = sc.nextInt();
        System.out.println("timeslot");
        int timeslot = sc.nextInt();
        System.out.println("item");
        //Item item = MENU.GET(ITEM)
        System.out.println("quantity");
        int quantity = sc.nextInt();
        Item dummyItem = new Item();
        dummyItem.setName("broliterallyfuckoop");
        dummyItem.setPrice(100);
        this.tables.getTables()[tableId].getCustomerAtTime(timeslot).getOrder().orders.put(dummyItem, quantity);

    }

    
}
