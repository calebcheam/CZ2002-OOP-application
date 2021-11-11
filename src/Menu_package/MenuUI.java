package Menu_package;
import java.util.ArrayList;
import java.util.Scanner;




public class MenuUI {
	
	 
	private static Scanner sc = new Scanner(System.in);
	
	
	public static void start() {
		int choice;
		do {
			System.out.println("====================================");
	        System.out.println("|             Menu Options         |");
			System.out.println("|        (Enter -1 to exit)        |");
	        System.out.println("==================================");
	        System.out.println("1. Display Menu             ");
	        System.out.println("2. Add new item             ");
	        System.out.println("3. Remove existing item     ");
	        System.out.println("============================");
	        System.out.print("Enter Option: ");
	        choice = sc.nextInt();
			sc.nextLine(); //consume next line
	        switch(choice) {

	        case 1:	 
			MenuManager.viewMenu();       	
			break;

	        case 2:
			MenuManager.addItemtoMenu();
			break;

	        case 3:
			MenuManager.removeItemFromMenu();
	        break;
	        }
		}while (choice != -1);
        
    }
}

