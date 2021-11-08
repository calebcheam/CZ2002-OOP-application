package Menu_package;
import java.util.ArrayList;
import java.util.Scanner;

import Menu_package.Menus.AlaCarteMenu;
import Menu_package.Menus.GenericMenu;
import Menu_package.Menus.PromoMenu;


public class wackMenuUI {
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
	        // System.out.println("5. Access an item           ");
	        // System.out.println("6. Exit                     ");
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
			// System.out.println("\n========== Create New Menu Item =========\n");
			// System.out.println("\nWhat type of item is this?");
			// String typeInput = sc.nextLine();

			// String typeCategory = this.Menu.findItemTypeCategory(typeInput);
			// int i;
			// for (i=0; i<2; i++){
			// 	if (typeCategory=="Invalid Type"){
			// 		System.out.println("Type Idenitifed Unsuccessful - Please enter the type again :");
			// 		typeInput = sc.nextLine();
			// 		typeCategory = this.Menu.findItemTypeCategory(typeInput);
			// 	} else
			// 	{
			// 		System.out.println("Type Identfied Successful - This is a " + typeInput + " from " + typeCategory);
			// 		break;
			// 	} 
			// }
			// if (i>=2){
			// 	System.out.println("Too many invalid inputs! You are clearly not in the right mind to add a menu item.");
			// 	System.out.println("Quitting...");
			// 	break;
			// }
			
			// System.out.println("\nWhat is the name of the new " + typeInput + " ?");
			// String name = sc.nextLine();

			// System.out.println("Enter description for the new item, " + name);
			// System.out.println("Enter each ingredient one by one. Enter \'x\' only to indicate end of description");
			// ArrayList<String> description = new ArrayList<String>(); 
			// String input = sc.nextLine();
			// while (!input.contains("x")){
			// 	description.add(input);
			// 	System.out.println(input + " was added! Enter next ingredient (or x to quit) : ");
			// 	input = sc.nextLine(); 
			// }

			// System.out.println("Enter price : ");
			// float price = sc.nextFloat();
			// System.out.println("Enter stock : ");
			// int stock = sc.nextInt();
			// this.Menu.add(name,typeInput,typeCategory,description,price,stock);
		
			break;

	        case 3:
			//menu.removeItem(number, typeCategory, itemType);
	        break;

	        case 4:
	        break;
	        case 5:
	        break;
	
	        }
		}while (choice != -1);
        
    }
    public static void main (String [] args){
        wackMenuUI menuUI = new wackMenuUI(); 
        menuUI.start();
    }

}

