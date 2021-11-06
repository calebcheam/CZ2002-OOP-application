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
	        break;
	        case 2:
	        break;
	        case 3:
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
