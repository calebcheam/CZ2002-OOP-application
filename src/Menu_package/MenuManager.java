package Menu_package;

import java.util.ArrayList;
import java.util.Scanner;

import Menu_package.Menus.AlaCarteMenu;
import Menu_package.Menus.GenericMenu;
import Menu_package.Menus.PromoMenu;
/**
 * <code>MenuManager</code> class to implement methods needed for the menu interface
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class MenuManager {
    private static Scanner sc=new Scanner(System.in);
    private static GenericMenu Menu=null;
    /**
     * User is given a choice to display the Ala Carte or Promotional Menu.
     */
    public static void viewMenu(){
        System.out.println("Which menu to display? (1) Ala Carte (2) Promotional (3) Return to Main Menu Actions");
        int menuChoice = sc.nextInt();

        if (menuChoice == 1){
            Menu=new AlaCarteMenu();
            
        } else if (menuChoice ==2) {
            Menu=new PromoMenu();
        
        } else if (menuChoice==3) {
            return; 
        }

        subMenuSelection();
    }

    /**
     *  for <code>viewMenu()<code> function, after the user chooses a menu to display, user needs to choose if they want to view all or one categories.
     */
    private static void subMenuSelection(){
        if (Menu.getNumofCategories()==1) {
            Menu.displayMenu();
            
        } else {
            System.out.println("(1) View all Categories (2) View one Category (3) Return to Menu Selection");

            int choice = sc.nextInt();
            if (choice==1){
                    Menu.displayMenu();
            } else if (choice==2) {
                int categorySelectedIndex = selectCategory();
                String categorySelected = Menu.getMenuItemCategoryTypes().indexToCategory(categorySelectedIndex);
                Menu.displayMenuCategory(categorySelected);
            } 
        }
    }
   /** 
     * returns index of category chosen by the user
     * @return index <code>integer</code> of category selected
     */
    private static int selectCategory(){
        System.out.println("=================== MENU CATEGORY SELECTION ====================");
        Menu.getMenuItemCategoryTypes().printCategories();
        System.out.println("Which category would you like to view?");
        int categorySelectedIndex = sc.nextInt()-1;
        return categorySelectedIndex;
    }
   /**
     * removes item chosen from the user by either the Ala Carte or Promotional Menu
     */
    public static void removeItemFromMenu(){
        System.out.println("Which menu to remove item from? (1) Ala Carte (2) Promotional (3) Return to Main Menu Actions");
        int menuChoice = sc.nextInt();
        Item itemToRemove;
        sc.nextLine(); //consume next line input
        System.out.println("\n========== Remove Menu Item =========\n");
        if (menuChoice == 1){
            Menu=new AlaCarteMenu();
            itemToRemove = selectAlaCarteItemFromMenu();
            //System.out.println("I WANT TO REMOVE " + itemToRemove.getName());
            Menu.remove(itemToRemove);
        } else if (menuChoice ==2) {
            Menu=new PromoMenu();
            Menu.displayMenu();
            System.out.println("Please enter item to be removed : ");
            int itemSelected = sc.nextInt(); 
            itemToRemove = Menu.getItemToBeRemoved(itemSelected, "Set", "Set");
        } else {
            return;
        }
        Menu.remove(itemToRemove);

        
    }
    
   /** 
     * user is asked to input the choice of the type of menu in which the item is from
     * @return <code>Item</code> that user chooses from either the Ala Carte or Promotional Menu
     */
    public static Item orderItemfromMenu(){
        System.out.println("Which menu would you like to order from?");
        System.out.println("(1) Ala Carte (2) Promotional");
        int choice = sc.nextInt();
        Item orderedItem;
        if (choice == 1 ){
            Menu = new AlaCarteMenu();
            orderedItem = selectAlaCarteItemFromMenu();
            
        } else{
            Menu = new PromoMenu();
            Menu.displayMenu();
            System.out.println("Please enter set number : ");
            int itemSelected = sc.nextInt(); 
            orderedItem = Menu.getItem(itemSelected, "Set", "Set");
        }
       return orderedItem;
    }
   /** 
     * returns <code>Item</code> from Ala Carte Menu after user inputs the item number
     * @return <code>Item</code> from Ala Carte Menu
     */
    private static Item selectAlaCarteItemFromMenu(){
        int categorySelectedIndex = selectCategory();
        String categorySelected = Menu.getMenuItemCategoryTypes().indexToCategory(categorySelectedIndex);
        int subcategorySelectedIndex = selectSubCategory(categorySelectedIndex);
        String subcategory = Menu.getMenuItemCategoryTypes().indexToSubcategory(subcategorySelectedIndex, categorySelectedIndex);
        System.out.println("Please input the item number : ");
        int menuItemNumber = sc.nextInt();
        return Menu.getItem(menuItemNumber, categorySelected, subcategory);
    }
    /** 
     * The index of category selected by the user is passed in where the user is asked to input the subcategory the item is from
     * @param categorySelectedIndex <code>int</code> index of category selected 
     * returns the index of the subcategory the item is from
     * @return <code>int</code> index of subcategory selected
     */
    private static int selectSubCategory(int categorySelectedIndex){
       
        String categorySelected = Menu.getMenuItemCategoryTypes().indexToCategory(categorySelectedIndex);
        Menu.displayMenuCategory(categorySelected);

        System.out.printf("\n%s Subcategories : \n", categorySelected);
        Menu.getMenuItemCategoryTypes().printSubCategories(categorySelectedIndex);
        System.out.println("Which subcategory is this item from?");
        int subcategorySelectedIndex = sc.nextInt()-1; 
        return subcategorySelectedIndex;
    }
    /** 
     * the methods checks whether the item type the user has input is valid and exists
     * @param typeInput <code>String</code> type of item that user has input
     * returns Invalid if item type is invalid or returns the item type if it exists
     * @return <code>String<code> "Invalid" or the item's type
     */
    private static String verifyItemTypeCategory(String typeInput){
        String typeCategory = Menu.findItemTypeCategory(typeInput);
        int i;
        for (i=0; i<2; i++){
            if (typeCategory=="Invalid Type"){
                System.out.println("Type Idenitifed Unsuccessful - Please enter the type again :");
                typeInput = sc.nextLine();
                typeCategory = Menu.findItemTypeCategory(typeInput);
            } else
            {
                System.out.println("Type Identfied Successful - This is a " + typeInput + " from " + typeCategory);
                return typeInput;
            } 
        }
        System.out.println("Too many invalid inputs!");
        System.out.println("Quitting...");
        
        return "Invalid";
    }
    /** 
     * checks whether string that is passed in is a valid type or invalid
     * @param typeInput <code>String</code> type of item that user has input
     * returns the item type category if it exists or else it returns "Invalid" to indicate that the item type category does not exist
     * @return item type category or <code>String</code> Invalid
     */
    private static String newItemTypeCategory(String typeInput){
        if (typeInput=="Invalid") {
             return "Invalid";
         }
         return Menu.findItemTypeCategory(typeInput);
    }
   /**
     * prompts user which type of menu would they like to add the item to
     */
    public static void addItemtoMenu(){
        System.out.println("Which menu to add item to? (1) Ala Carte (2) Promotional (3) Return to Main Menu Actions");
        int menuChoice = sc.nextInt();
        sc.nextLine(); //consume next line input
        System.out.println("\n========== Create New Menu Item =========\n");
        if (menuChoice == 1){
            Menu=new AlaCarteMenu();
            addAlaCarteItemtoMenu();
        } else if (menuChoice ==2) {
            Menu=new PromoMenu();
            addSetItemtoMenu();
        } else {
            return;
        }
    
    }
    /**
     * user is prompted to input the necessary information of the Ala Carte item added
     */
    private static void addAlaCarteItemtoMenu(){
        System.out.println("\nWhat type of item is this?");
	    String typeInput = sc.nextLine();
        String correctTypeInput = MenuManager.verifyItemTypeCategory(typeInput);
        String typeCategory = MenuManager.newItemTypeCategory(correctTypeInput);

        if (typeCategory=="Invalid") return; 
        System.out.println("\nWhat is the name of the new " + correctTypeInput + " ?");
		String name = sc.nextLine();
        
		ArrayList<String> description = newItemDescription(name);
        System.out.println("Enter price : ");
        float price = sc.nextFloat();
        System.out.println("Enter stock : ");
        int stock = sc.nextInt();
        Menu.add(name,correctTypeInput,typeCategory,description,price,stock);
		
    }
   /**
     * user is prompted to input the necessary information of the Set item added
     */

    private static void addSetItemtoMenu(){
        String name;
        System.out.println("Enter name of the set (eg. Entering 'CNY' will name the set 'Set CNY) :'");
        name = "Set " + sc.nextLine();
        System.out.printf("\n---- Creating %s ----\n", name);
        ArrayList<String> parts = newPromoItemParts();
        System.out.println("Enter price : ");
        float price = sc.nextFloat();
        System.out.println("Enter stock : ");
        int stock = sc.nextInt();
        Menu.add(name,"Set", "Set",parts,price,stock);
    }
   /** user is prompted to input the name of the new promotional items 
     * @return an array list of promotional items
     */
    private static ArrayList<String> newPromoItemParts(){
        String[] itemCategories= new String[] {"Appetiser", "Main Course", "Dessert", "Drink"};
        ArrayList<String> parts = new ArrayList<String>();
        
        String itemName;
        String description; 
        String fullItemString;
        for (int i=0; i<4; i++){
            System.out.println("Enter name of " + itemCategories[i]);
            
            itemName = sc.nextLine();
            if (i!=3){
                description = newSetItemDescription(itemName);
                fullItemString = itemName + " " + description;
            } else {
                fullItemString = itemName;
            }
            System.out.println(fullItemString);
            parts.add(fullItemString);
        }

        return parts;
    }

    
   /** 
     * passes in the name of the new set item
     * @param name <code>String</code> name of new set item
     * user is prompted to add description for new set item and returns the description of it
     * @return <code>String</code> description of new set item
     */
    private static String newSetItemDescription(String name){
        System.out.printf("Enter description for the item, %s: ", name);
        System.out.println("Enter each descriptor one by one.");
        System.out.println("Enter \'x\' only to indicate end of description");
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
   /**
     * passes in the name of the new menu item
     * @param name <code>String</code> name of new menu item
     * user is prompted to add description for new item and returns the description of it
     * @return <code>String</code> description of the new item 
     */
    private static ArrayList<String> newItemDescription(String name){
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
