package Menu_package;

import java.util.Scanner;

public class MenuUI {
	Menu menu = new Menu();
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
	        switch(choice) {
	        case 1:
			this.menu.displaymenu(this.menu.getLongestStringSize());
	        break;
	        case 2:
			int i = 0;
			while (i == 0){
				System.out.println("Enter new item name:");
			        String name = sc.nextLine();
			        System.out.println("Enter new item type:");
				String type = sc.nextLine();
				System.out.println("Enter new item description:");
				ArrayList<String> description = sc.nextLine();
				System.out.println("Enter new item price:");
				float price = sc.nextFloat();
				System.out.println("Enter new item stock:");
				int stock = sc.nextInt();
				int check = this.menu.add(name,type,description,price,stock);
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
			this.menu.displaymenu(this.menu.getLongestStringSize());
			int i = 0;
			while (i == 0){
				System.out.println("Enter item number to be removed:");
			        int number = sc.nextInt();
			        System.out.println("Enter typeCategory of item to be removed:");
				String typeCategory = sc.nextLine();
				System.out.println("Enter itemType of item to be removed");
				String itemType = sc.nextLine();
			}		
			
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
