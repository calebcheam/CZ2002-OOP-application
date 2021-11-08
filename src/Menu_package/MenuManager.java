package Menu_package;

import java.util.ArrayList;
import java.util.Scanner;

import Menu_package.Menus.AlaCarteMenu;
import Menu_package.Menus.GenericMenu;
import Menu_package.Menus.PromoMenu;

public class MenuManager {
    private Scanner sc;
    private GenericMenu Menu=null; 

    public MenuManager(){
        this.sc = new Scanner(System.in);
    }

    public void viewMenu(){
        System.out.println("Which menu to display? (1) Ala Carte (2) Promotional (3) Return to Main Menu Actions");
        int menuChoice = sc.nextInt();

        if (menuChoice == 1){
            this.Menu=new AlaCarteMenu();
            
        } else if (menuChoice ==2) {
            this.Menu=new PromoMenu();
        } else if (menuChoice==3) {
            return; 
        }

        this.subMenuSelection();
    }

    // for display of Menu
    private void subMenuSelection(){
        if (this.Menu.getNumofCategories()==1) {
            this.Menu.displayMenu();
            
        } else {
            System.out.println("(1) View all Categories (2) View one Category (3) Return to Menu Selection");

            int choice = sc.nextInt();
            if (choice==1){
                    this.Menu.displayMenu();
            } else if (choice==2) {
                int categorySelectedIndex = this.selectCategory();
                String categorySelected = this.Menu.getMenuItemCategoryTypes().indexToCategory(categorySelectedIndex);
                this.Menu.displayMenuCategory(categorySelected);
            } 
        }
    }

    private int selectCategory(){
        System.out.println("=================== MENU CATEGORY SELECTION ====================");
        this.Menu.getMenuItemCategoryTypes().printCategories();
        System.out.println("Which category would you like to view?");
        int categorySelectedIndex = sc.nextInt()-1;
        return categorySelectedIndex;
    }

    public void removeItemFromMenu(){
        System.out.println("Which menu to remove item from? (1) Ala Carte (2) Promotional (3) Return to Main Menu Actions");
        int menuChoice = sc.nextInt();
        Item itemToRemove;
        sc.nextLine(); //consume next line input
        System.out.println("\n========== Remove Menu Item =========\n");
        if (menuChoice == 1){
            this.Menu=new AlaCarteMenu();
            itemToRemove = this.selectAlaCarteItemFromMenu();
            System.out.println("I WANT TO REMOVE " + itemToRemove.getName());
            this.Menu.remove(itemToRemove);
        } else if (menuChoice ==2) {
            this.Menu=new PromoMenu();
            this.Menu.displayMenu();
            System.out.println("Please enter item to be removed : ");
            int itemSelected = sc.nextInt(); 
            itemToRemove = this.Menu.getItemToBeRemoved(itemSelected, "Set", "Set");
        } else {
            return;
        }
        this.Menu.remove(itemToRemove);

        
    }
    public Item orderItemfromMenu(){
        System.out.println("Which menu would you like to order from?");
        System.out.println("(1) Ala Carte (2) Promotional");
        int choice = sc.nextInt();
        Item orderedItem;
        if (choice == 1 ){
            this.Menu = new AlaCarteMenu();
            orderedItem = this.selectAlaCarteItemFromMenu();
            
        } else{
            this.Menu = new PromoMenu();
            this.Menu.displayMenu();
            System.out.println("Please enter set number : ");
            int itemSelected = sc.nextInt(); 
            orderedItem = this.Menu.getItem(itemSelected, "Set", "Set");
        }
       return orderedItem;
    }

    private Item selectAlaCarteItemFromMenu(){
        int categorySelectedIndex = this.selectCategory();
        String categorySelected = this.Menu.getMenuItemCategoryTypes().indexToCategory(categorySelectedIndex);
        int subcategorySelectedIndex = this.selectSubCategory(categorySelectedIndex);
        String subcategory = this.Menu.getMenuItemCategoryTypes().indexToSubcategory(subcategorySelectedIndex, categorySelectedIndex);
        System.out.println("Im sorry our system is  a bit lousy. Please input yourself the item number : ");
        int menuItemNumber = sc.nextInt();
        return this.Menu.getItem(menuItemNumber, categorySelected, subcategory);
    }

    private int selectSubCategory(int categorySelectedIndex){
       
        String categorySelected = this.Menu.getMenuItemCategoryTypes().indexToCategory(categorySelectedIndex);
        this.Menu.displayMenuCategory(categorySelected);

        System.out.println("=================== SUBCATEGORY SELECTION ====================");
        this.Menu.getMenuItemCategoryTypes().printSubCategories(categorySelectedIndex);
        System.out.println("Which subcategory would you like to view?");
        int subcategorySelectedIndex = sc.nextInt()-1; 
        return subcategorySelectedIndex;
    }
    
    private String newItemTypeCategory(String typeInput){
        String typeCategory = this.Menu.findItemTypeCategory(typeInput);
			int i;
			for (i=0; i<2; i++){
				if (typeCategory=="Invalid Type"){
					System.out.println("Type Idenitifed Unsuccessful - Please enter the type again :");
					typeInput = sc.nextLine();
					typeCategory = this.Menu.findItemTypeCategory(typeInput);
				} else
				{
					System.out.println("Type Identfied Successful - This is a " + typeInput + " from " + typeCategory);
					return typeCategory;
				} 
			}
			System.out.println("Too many invalid inputs! You are clearly not in the right mind to add a menu item.");
			System.out.println("Quitting...");
			
            return "Invalid";
    }

    
    public void addItemtoMenu(){
        System.out.println("Which menu to add item to? (1) Ala Carte (2) Promotional (3) Return to Main Menu Actions");
        int menuChoice = sc.nextInt();
        sc.nextLine(); //consume next line input
        System.out.println("\n========== Create New Menu Item =========\n");
        if (menuChoice == 1){
            this.Menu=new AlaCarteMenu();
            this.addAlaCarteItemtoMenu();
        } else if (menuChoice ==2) {
            this.Menu=new PromoMenu();
            this.addSetItemtoMenu();
        } else {
            return;
        }
    
    }

    private void addAlaCarteItemtoMenu(){
        System.out.println("\nWhat type of item is this?");
	    String typeInput = sc.nextLine();
        String typeCategory = this.newItemTypeCategory(typeInput);

        System.out.println("\nWhat is the name of the new " + typeInput + " ?");
		String name = sc.nextLine();
        
		ArrayList<String> description = this.newItemDescription(name);
        System.out.println("Enter price : ");
        float price = sc.nextFloat();
        System.out.println("Enter stock : ");
        int stock = sc.nextInt();
        this.Menu.add(name,typeInput,typeCategory,description,price,stock);
		
    }

    private void addSetItemtoMenu(){
        String name;
        System.out.println("Enter name of the set (eg. Entering 'CNY' will name the set 'Set CNY) :'");
        name = "Set " + sc.nextLine();
        System.out.printf("\n---- Creating %s ----\n", name);
        ArrayList<String> parts = this.newPromoItemParts();
        System.out.println("Enter price : ");
        float price = sc.nextFloat();
        System.out.println("Enter stock : ");
        int stock = sc.nextInt();
        this.Menu.add(name,"Set", "Set",parts,price,stock);
    }

    private ArrayList<String> newPromoItemParts(){
        String[] itemCategories= new String[] {"Appetiser", "Main Course", "Dessert", "Drink"};
        ArrayList<String> parts = new ArrayList<String>();
        
        String itemName;
        String description; 
        String fullItemString;
        for (int i=0; i<4; i++){
            System.out.println("Enter name of " + itemCategories[i]);
            
            itemName = sc.nextLine();
            if (i!=3){
                description = this.newSetItemDescription(itemName);
                fullItemString = itemName + " " + description;
            } else {
                fullItemString = itemName;
            }
            System.out.println(fullItemString);
            parts.add(fullItemString);
        }

        return parts;
    }

    private String newSetItemDescription(String name){
        System.out.printf("Enter description for the item, %s: ", name);
        System.out.println("Enter each descriptor one by one. Enter \'x\' only to indicate end of description");
        String description = "(";
        String input = sc.nextLine();
        boolean isFirst=true;
        while (!input.contains("x")){
            if (isFirst){
                description = description + " " + input;
                isFirst = false;
            } else {
                description = description + " + " + input;
            }
            
            System.out.println(input + " was added! Enter next descriptor (or x to quit) : ");
            input = sc.nextLine(); 
        }
        description = description + " )";
        return description; 
    }

    private ArrayList<String> newItemDescription(String name){
        System.out.println("Enter description for the item "+ name + " : ");
        System.out.println("Enter each descriptor one by one. Enter \'x\' only to indicate end of description");
        ArrayList<String> description = new ArrayList<String>(); 
        String input = sc.nextLine();

        while (!input.contains("x")){
            description.add(input);
            System.out.println(input + " was added! Enter next descriptor (or x to quit) : ");
            input = sc.nextLine(); 
        }
        return description; 
    }
}
