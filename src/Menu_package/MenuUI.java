package Menu_package;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuUI {
	Menu menu = new Menu();
	Scanner sc = new Scanner(System.in);
	int choice;
	public Item start() {
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
	        switch(choice) {
	        case 1:
			this.menu.displayMenu(this.menu.getLongestStringSize());
	        break;
	        case 2:
			int i = 0;
			while (i == 0){
				System.out.println("Enter new item name:");
			        String name = sc.nextLine();
			        System.out.println("Enter new item type:");
				String type = sc.nextLine();
				System.out.println("Enter new item description:");
				ArrayList<String> temp = new ArrayList<>();
				String description = sc.nextLine();
				temp.add(description);
				System.out.println("Enter new item price:");
				float price = sc.nextFloat();
				System.out.println("Enter new item stock:");
				int stock = sc.nextInt();
				int check = this.menu.add(name,type,temp,price,stock);
				if (check == 1){
					System.out.println("New item added successfully");
					System.out.println("Enter 0 to add another item");
					System.out.println("Enter 1 to end application");
					i = sc.nextInt();
				}
				else{
					System.out.println("New item not added successfully, please try again");
				}
			}		
	        break;
	        case 3:
	        int length = this.menu.getLongestStringSize();
		this.menu.displayMenu(length);
		int j = 0;
		while (j == 0){
			System.out.println("Enter item number to be removed:");
			int number = sc.nextInt();
		        System.out.println("Enter typeCategory of item to be removed:");
			String typeCategory = sc.nextLine();
			System.out.println("Enter itemType of item to be removed");
			String itemType = sc.nextLine();
			while (this.menu.findItemTypeCategory(itemType) == "Invalid Type") {
				System.out.println("Invalid itemType");
				System.out.println("Please Re-Enter itemType of item to be removed");
				itemType = sc.nextLine();
			}
			this.menu.removeItem(number,typeCategory,itemType);
			System.out.println("Item removed successfully");
			System.out.println("Enter 0 to add another item");
			System.out.println("Enter 1 to end application");
			j = sc.nextInt();
		      }		
	        break;
	        case 4:
	        this.menu.displayMenu(this.menu.getLongestStringSize());
	        int k = 0;
		while (k == 0){
			System.out.println("Enter item number to be updated:");
		        int number = sc.nextInt();
	        	System.out.println("Enter typeCategory of item to be updated:");
			String typeCategory = sc.nextLine();
			System.out.println("Enter itemType of item to be updated");
			String itemType = sc.nextLine();
			while (this.menu.findItemTypeCategory(itemType) == "Invalid Type") {
				System.out.println("Invalid itemType");
				System.out.println("Please Re-Enter itemType of item to be updated");
				itemType = sc.nextLine();
			}
			System.out.println("1 - change item name");				
			System.out.println("2 - change item type");
			System.out.println("3 - change item description");
			System.out.println("4 - change item price");
			System.out.println("5 - change item stock");
			System.out.println("Enter choice of item attribute to be updated:");
			String choice = sc.nextLine();
			int checkchoice = Integer.parseInt(choice);
			while ((checkchoice < 1 ) || (checkchoice > 5 )) {
				System.out.println("Invalid choice please re-enter:");
				choice = sc.nextLine();
				checkchoice = Integer.parseInt(choice);
			}
			ArrayList<String> info = new ArrayList<>();
			info.add(choice);
			switch (choice){
			case "1":
				System.out.println("Enter item name");
	            	        info.add(sc.nextLine());
	                break;
	                case "2":
	            	        System.out.println("Enter item type");
	            	        info.add(sc.nextLine());
	                break;
	                case "3":
	            	        System.out.println("Enter item description");
	            	        info.add(sc.nextLine());
	                break;
	                case "4":
	            	        System.out.println("Enter item price");
	            	        info.add(sc.nextLine());
	                break;
	                case "5":
	            	        System.out.println("Enter item stock");
	            	        info.add(sc.nextLine());
	                default: 
	            	        System.out.println("Invalid choice");
	                break;
			}
			this.menu.updateItem(number,typeCategory,itemType,info);
			System.out.println("Item updated successfully");
			System.out.println("Enter 0 to add update another item");
			System.out.println("Enter 1 to end application");
			k = sc.nextInt();
		}
	        break;
	        case 5:
	        	this.menu.displayMenu(this.menu.getLongestStringSize());
		        System.out.println("Enter item number to be accessed:");
			int number = sc.nextInt();
		        System.out.println("Enter typeCategory of item to be accessed:");
			String typeCategory = sc.nextLine();
			System.out.println("Enter itemType of item to be accessed");
			String itemType = sc.nextLine();
			while (this.menu.findItemTypeCategory(itemType) == "Invalid Type") {
				System.out.println("Invalid itemType");
				System.out.println("Please Re-Enter itemType of item to be accessed");
				itemType = sc.nextLine();
			}
			return this.menu.accessItem(number,typeCategory,itemType);
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
