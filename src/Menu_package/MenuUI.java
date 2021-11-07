package Menu_package;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuUI {
	Menu menu = new Menu();
	AlaCarteMenu alaCarteMenu = new AlaCarteMenu(); 
	PromoMenu promoMenu = new PromoMenu(); 
	Scanner sc = new Scanner(System.in);
	int choice;
	
	public void start() {
		do {
		System.out.println("============================");
	        System.out.println("|       Menu Options       |");
	        System.out.println("============================");
	        System.out.println("Options:                    ");
	        System.out.println("1. Display Menu             ");
	        System.out.println("2. Add new item             ");
	        System.out.println("3. Remove existing item     ");
	        System.out.println("4. Update an existing item  ");
	        System.out.println("5. Access an item           ");
	        System.out.println("6. Exit                     ");
	        System.out.println("============================");
	        System.out.print("Enter Option: ");
	        choice = sc.nextInt();
			sc.nextLine(); //consume next line
	        switch(choice) {
	        case 1:	        	
			menu.displayMenu();
			//System.out.println("Which menu to display? (1) Ala Carte (2) Promotional ");
			// int menuChoice = sc.nextInt();
			// sc.nextLine(); //consume next line
			// if (menuChoice == 1){
			// 	alaCarteMenu.displayMenu();
			// } else {
			// 	promoMenu.displayMenu();
			// }
	        break;

	        case 2:
			System.out.println("\n========== Create New Menu Item =========\n");
			System.out.println("\nWhat type of item is this?");
			String typeInput = sc.nextLine();

			String typeCategory = menu.findItemTypeCategory(typeInput);
			int i;
			for (i=0; i<2; i++){
				if (typeCategory=="Invalid Type"){
					System.out.println("Type Idenitifed Unsuccessful - Please enter the type again :");
					typeInput = sc.nextLine();
					typeCategory = menu.findItemTypeCategory(typeInput);
				} else
				{
					System.out.println("Type Identfied Successful - This is a " + typeInput + " from " + typeCategory);
					break;
				} 
			}
			if (i>=2){
				System.out.println("Too many invalid inputs! You are clearly not in the right mind to add a menu item.");
				System.out.println("Quitting...");
				break;
			}
			
			System.out.println("\nWhat is the name of the new " + typeInput + " ?");
			String name = sc.nextLine();

			System.out.println("Enter description for the new item, " + name);
			System.out.println("Enter each ingredient one by one. Enter \'x\' only to indicate end of description");
			ArrayList<String> description = new ArrayList<String>(); 
			String input = sc.nextLine();
			boolean isFirst=true;
			do {
				description.add(input);
				System.out.println(input + " was added! Enter next ingredient (or x to quit) : ");
				input = sc.nextLine(); 
			}
			while (!input.contains("x"));

			System.out.println("Enter price : ");
			float price = sc.nextFloat();
			System.out.println("Enter stock : ");
			int stock = sc.nextInt();
			int check = menu.add(name,typeInput,description,price,stock);
				if (check == 1){
					System.out.println("Enter 0 to add another item");
					System.out.println("Enter 1 to end application");
					i = sc.nextInt();
				}
				else{
					System.out.println("New item not added successfully, please try again");
				}

			break;

	        case 3:
			//menu.removeItem(number, typeCategory, itemType);
	        break;

	        case 4:
	        break;
	        case 5:
	        break;
	        case 6:
	        	System.out.println("Menu Interface closed");
	        break;
	        }
		}while (choice != 6);
	}
	public static void main(String[] args) {
		MenuUI menuui= new MenuUI();
		menuui.start();
	}
}
