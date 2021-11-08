package Menu_package;
import java.util.Scanner;
import Menu_package.Menus.PromoMenu;

public class PromoMenuManager {
    private PromoMenu promo;
    private Scanner sc;

    public PromoMenuManager(){
        this.sc = new Scanner(System.in);
        this.promo = new PromoMenu();
    }

    public Item selectItemFromMenu(){
        System.out.println("=================== PROMOTIONAL ITEMS SELECTION ====================");
        this.promo.displayMenu();
        System.out.println("What set would you like to order?");
        int itemId = sc.nextInt();
        return this.promo.getItem(itemId,"Set","Set");
    }
}
