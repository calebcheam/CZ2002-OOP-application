package Menu_package;
import java.util.ArrayList;
import java.util.Scanner;

import Menu_package.Menus.AlaCarteMenu;
import Menu_package.Menus.GenericMenu;
import Menu_package.Menus.PromoMenu;


public class MenuUI {
	MenuManager menuManager= new MenuManager();
	GenericMenu Menu=null; 
	Scanner sc = new Scanner(System.in);
	int choice;
	
	public void start() {
		do {
			System.out.println("====================================");
	        System.out.println("|             Menu Options         |");
			System.out.println("|        (Enter -1 to exit)        |");
	        System.out.println("==================================");
	        System.out.println("1. Display Menu             ");
	        System.out.println("2. Add new item             ");
	        System.out.println("3. Remove existing item     ");
	        System.out.println("4. Update an existing item  ");
	        System.out.println("============================");
	        System.out.print("Enter Option: ");
	        choice = sc.nextInt();
			sc.nextLine(); //consume next line
	        switch(choice) {

	        case 1:	 
			this.menuManager.viewMenu();       	
			break;

	        case 2:
			this.menuManager.addItemtoMenu();
			break;

	        case 3:
			this.menuManager.removeItemFromMenu();
	        break;

	        case 4:
	        break;
	        case 5:
	        break;
	
	        }
		}while (choice != -1);
        
    }
    public static void main (String [] args){
        MenuUI menuUI = new MenuUI(); 
        menuUI.start();
    }

}

