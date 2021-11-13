package Menu_package.Menus;

import java.util.ArrayList;

import Menu_package.Item;
import Menu_package.MenuCSVHandler;
import Menu_package.MenuItemCategoryTypes;

/**
 * Abstract <code>class</code> for subclasses that handles operation relating to different types of Menu Items.
 * @see Menu_package.Menus.AlaCarteMenu
 * @see Menu_package.Menus.PromoMenu
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public abstract class GenericMenu {
    protected MenuItemCategoryTypes menuCategories;
    protected MenuCSVHandler menuCSVHandler = new MenuCSVHandler();
    protected int longestStringSize;
    protected String csvPath=null;
    //This message below is later used for the display menu method
    protected final String priceDisclaimerMessage = "* Prices do not include GST & Service charges *";
    protected final String priceTag = "Price: SGD$"; //used for display menu method
    protected String name=null;

    
    /**
     * Returns <code>Item</code> object by the <code>Item</code> Category, Type and the numbering (number).
     * For explanation purposes, numbering will be referred to as 'i'.
     * 'i' indicates the position of the <code>Item</code>, with the <code>Item</code> Type specified.
     * This method will return the 'i'th <code>Item</code> of the <code>Item</code> type specified.
     * For e.g., if Type = "Appetiser", number = 2, then the 2nd <code>Item</code> of Type "Appetiser" wil be returned.
     * @param number Position of <code>Item</code>, of the specified <code>Item</code> Type.
     * @param typeCategory <code>Item</code> Category, to indicate which <code>Item</code> Category the <code>Item</code> (to be returned) is from.
     * @param itemType <code>Item</code> Type, to indicate which <code>Item</code> Type the <code>Item</code> (to be returned) is of.
     * @return Item <code>Item</code> object under <code>Item</code> Category, of <code>Item</code> Type, and numbering specified.
     */
    public Item getItem(int number, String typeCategory, String itemType){
        //typeCategory referring to categories eg. Main Course / Drink
        //itemType referring to subcategories eg. Appetisers / Salad 
        int firstOccurIndex = findFirstTypeOccurrence(typeCategory, itemType);
        ArrayList<Item> tempItemArray;
        tempItemArray = returnItemListReference(typeCategory);
        return tempItemArray.get(firstOccurIndex + number - 1);
    }
 
    
    /** 
     * Abstract method for subclasses to get <code>Item</code> Category of the <code>Item</code> Type specified.
     * @see Menu_package.Menus.AlaCarteMenu#findItemTypeCategory(String itemType)
     * @see Menu_package.Menus.PromoMenu#findItemTypeCategory(String itemType)
     * @param itemType <code>Item</code> Type
     * @return String <code>Item</code> Type
     */
    /////////////////////// methods relating to changing the menu//////////////
    public String findItemTypeCategory(String itemType){
        
    return itemType;
        
    };

    
    /**
     * Abstract method for subclasses to add <code>Item</code> object into <code>ArrayList</code> according to the <code>Item</code> Type.
     * @see Menu_package.Menus.AlaCarteMenu#allocateItem(Item menuItem, String itemType)
     * @see Menu_package.Menus.PromoMenu#allocateItem(Item menuItem, String itemType)
     * @param menuItem <code>Item</code> Object to be added
     * @param itemType <code>Item</code> Type of the <code>Item</code> object to be added
     * @return int Return positive integer if success, negative if failure (refer to above method links)
     */
    public int allocateItem(Item menuItem, String itemType)
    {
        return longestStringSize;

    };

    
    /** 
     * Abstract method for subclasses to find first occurrence index of a specified <code>Item</code> Type in <code>ArrayList</code> that stores Items under <code>Item</code> Category specified.
     * @see Menu_package.Menus.AlaCarteMenu#findFirstTypeOccurrence(String typeCategory, String itemType)
     * @see Menu_package.Menus.PromoMenu#findFirstTypeOccurrence(String typeCategory, String itemType)
     * @param typeCategory <code>Item</code> Category 
     * @param itemType <code>Item</code> Type 
     * @return int Return positive integer if success, negative if failure (refer to above method links)
     */
    public int findFirstTypeOccurrence(String typeCategory, String itemType)
    {
        return longestStringSize;
        
    }

    
    /** 
     * Abstract method for subclasses to <code>return</code> <code>ArrayList</code> containing the Items under the <code>Item</code> Category specified
     * @param typeCategory <code>Item</code> Category
     * @return ArrayList<Item> <code>ArrayList</code> that contains the Items under the <code>Item</code> Category specified
     */
    public ArrayList<Item> returnItemListReference(String typeCategory)
    {
        return null;

    }

    
    /** 
     * Returns String of first <code>Item</code> object's name of specified <code>Item</code> Type in the <code>ArrayList</code> of Items, containing <code>Item</code> under specified <code>Item</code> Category.
     * @param typeCategory <code>Item</code> Category
     * @param itemType <code>Item</code> Type
     * @return String <code>String</code> of first <code>Item</code> object's name
     */
    public String findFriend(String typeCategory, String itemType) { //to insert into correct location in the CSV
       
        int firstOccurIndex = findFirstTypeOccurrence(typeCategory, itemType);
        ArrayList<Item> tempItemArray;
        tempItemArray = returnItemListReference(typeCategory);

        return tempItemArray.get(firstOccurIndex).getName();
    }
    
    
    /** 
     * Add <code>Item</code> with specified attributes, to the csv file given (as the list of information of all Menu Items and Set)
     * @param name <code>Item</code> name
     * @param itemType <code>Item</code> Type
     * @param typeCategory <code>Item</code> Category
     * @param description <code>Item</code> description
     * @param price <code>Item</code> price
     * @param stock <code>Item</code> stock
     * @return int Returns 1 to indicate successful addition of <code>Item</code> to csv, negative integer to indicate failure
     */
    public int add(String name, String itemType, String typeCategory, ArrayList<String> description, float price, int stock){
        Item newItem = new Item(name, itemType, description, price, stock);
        int check = allocateItem(newItem, newItem.getType());
        if (check==1){
            String friendItemName= this.findFriend(typeCategory, itemType);
            //System.out.println("I want to find friend " + friendItemName);
            
            String newItemString; 
            if (newItem.getType()=="Set"){
                newItemString = newItem.SetToString();
            } else {
                newItemString = newItem.AlaCarteToCSVString();
            }
            this.menuCSVHandler.writeToCSVLocation(this.csvPath, friendItemName, newItemString);

        } else System.out.println("ERROR! Item was not created successfully");
        return check;
    }

    
    /** 
     * Remove <code>Item</code> from the csv file (that contains information of all Items and Set Menu)
     * @param item <code>Item</code> to be removed
     */
    public void remove(Item item){
        String lineToDelete = item.getName();
        //this.csvHandler.removeItemFromCSV(this.csvPath, lineToDelete);
        this.menuCSVHandler.removeFromCSVLocation(this.csvPath, lineToDelete);
    }

    
    /** 
     * Get the specified <code>Item</code> according to the type category, <code>Item</code> type, the numbering of the <code>Item</code> in the Menu display,
     * to prepare for removal of the <code>Item</code>.
     * @param number Numbering of the <code>Item</code> in the Menu display
     * @param typeCategory <code>Item</code> Category of specified <code>Item</code>
     * @param itemType <code>Item</code> Type of specified <code>Item</code>
     * @return Item <code>Item</code> to be removed
     */
    public Item getItemToBeRemoved(int number, String typeCategory, String itemType) {
	//remove the specified Item according to the type category, Item type,
        //the numbering of the Item in the Menu display.
        //this method will assume that no invalid "itemType" will be passed into the parameter
        //as it will be settled in the MenuUI (boundary) class

        //Find first occurrence of itemType
        int firstOccurIndex = findFirstTypeOccurrence(typeCategory, itemType);
        //will not consider failure of getting an index number from the above method as the MenuUI will not display
        //any non-existent Item type for selection

        //get ArrayList (Menu attribute) that contains the Item to be removed
        ArrayList<Item> tempItemArray;
        tempItemArray = returnItemListReference(typeCategory);

        //remove Item
        return tempItemArray.get(firstOccurIndex + number - 1);	
	}

    
    /** 
     * Update the specified Item's attribute according to the "choice" in updateInfo.
     * All other parameters (other than updateInfo) are used to locate the <code>Item</code> in the <code>ArrayList</code> of Items.
     * "Choice" refers to the first element of updateInfo and it is a <code>String</code> number. If the first element is:
     * "1" - change <code>Item</code> name
     * "2" - change <code>Item</code> Type
     * "3" - change <code>Item</code> description
     * "4" - change <code>Item</code> price
     * "5" - change <code>Item</code> stock
     * @param number Numbering of the <code>Item</code> in the Menu display
     * @param typeCategory <code>Item</code> Category of specified <code>Item</code>
     * @param itemType <code>Item</code> Type of specified <code>Item</code>
     * @param updateInfo <code>ArrayList</code> that contains the "choice" (that indicates which <code>Item</code> attributes to change), and information to replace the specified <code>Item</code> attribute 
     */
    public void updateItem(int number, String typeCategory, String itemType, ArrayList<String> updateInfo) {
		int firstOccurIndex = findFirstTypeOccurrence(typeCategory, itemType);
        //will not consider failure of getting an index number from the above method as the MenuUI will not display
        //any non-existent Item type for selection

        //get ArrayList (Menu attribute) that contains the Item to be updated
        ArrayList<Item> tempItemArray;
        tempItemArray = returnItemListReference(typeCategory);

        //get the first element of updateInfo, to know which attribute of the Item requires change
        String updateChoice = updateInfo.get(0);

        switch (updateChoice){
            case "1":
                //change Item name of Item specified,to the second element in the updateInfo
                tempItemArray.get(firstOccurIndex).setName(updateInfo.get(1));
                break;
            case "2":
                //change Item type of Item specified,to the second element in the updateInfo
                tempItemArray.get(firstOccurIndex).setType(updateInfo.get(1));
                break;
            case "3":
                //change Item description of Item specified, using all elements in updateInfo,
                // excluding the first element
                updateInfo.remove(0);
                tempItemArray.get(firstOccurIndex).setDescription(updateInfo);
                break;
            case "4":
                //change Item price of Item specified,to the second element in the updateInfo
                tempItemArray.get(firstOccurIndex).setPrice(Integer.parseInt(updateInfo.get(1)));
                break;
            default: //case "5"
                //change Item stock of Item specified,to the second element in the updateInfo
                tempItemArray.get(firstOccurIndex).setStock(Integer.parseInt(updateInfo.get(1)));
                break;
        }
	}

    
    /** 
     * Return length of the longest <code>String</code> to be displayed (in a single line) in the Menu display.
     * This is to format the display positioning of the Menu's words and headers.
     * @param itemsNamesTypesList <code>ArrayList</code> that contains all Strings that will be displayed in the Menu. The max length out of these Strings will be returned.
     * @return int Length of the longest <code>String</code> to be displayed in a single line.
     */
    ///////////////////// methods relating to displaying the menu
    public int findLongestStringSize(ArrayList<String> itemsNamesTypesList) {
		//Find the longest string in the itemsNamesTypesList
        int longestStringSize = 0;
        for (int i = 0; i < itemsNamesTypesList.size(); i++){
            int curStringSize = itemsNamesTypesList.get(i).length();
            if (curStringSize > longestStringSize){
                //if current string longer than current longestStringSize
                longestStringSize = curStringSize;
            }
        }
        if (longestStringSize % 2 != 0){
            //if not even
            longestStringSize++;//make it even for the (menu) display to be symmetric
        }
        if(longestStringSize < priceDisclaimerMessage.length()){
            //if the longestString size is smaller than the price message (message to indicating
            //GST & Service charges are not included) length
            longestStringSize = priceDisclaimerMessage.length();
        }
        return longestStringSize;
	}
    
    
    /** 
     * Returns the longest <code>String</code> size (to be displayed in a single line in the Menu display), stored in <code>this</code> <code>class</code>.
     * @return int Longest <code>String</code> size
     */
    public int getLongestStringSize() {
		return this.longestStringSize;
	}

    
    /** 
     * Returns menuCategories of <code>this</code> <code>class</code>
     * @return MenuItemCategoryTypes menuCategories of <code>this</code> <code>class</code>
     */
    public MenuItemCategoryTypes getMenuItemCategoryTypes(){
        return this.menuCategories;
    }

    
    /** 
     * Returns number of <code>Item</code> Categories associated with <code>this</code> <code>class</code>
     * @return int Number of <code>Item</code> Categories
     */
    public int getNumofCategories(){
        return this.menuCategories.getNumberOfCategories();
    }
	
    /**
     * Abstract method for subclasses to implement a method to display their Menu Items
     * @see Menu_package.Menus.AlaCarteMenu#displayMenu()
     * @see Menu_package.Menus.PromoMenu#displayMenu()
     */
    public void displayMenu()
    {
        return;
    };

    
    /** 
     * Abstract method for subclasses to implement a method to display their Menu Items of a specified <code>Item</code> Category
     * @see Menu_package.Menus.AlaCarteMenu#displayMenu()
     * @see Menu_package.Menus.PromoMenu#displayMenu()
     * @param category <code>Item</code> Category specified to display Items under the <code>Item</code> Category
     */
    public void displayMenuCategory(String category){

    }

    
    /** 
     * Prints header line of length specified
     * @param numOfLines Length of header line to be printed
     */
    public void printHeaderLines(int numOfLines) {
		for (int i = 0; i < numOfLines; i++){
            System.out.printf("=");
        }
        System.out.println();
		
	}
	
	
    /** 
     * Prints sub header line of length specified
     * @param numOfLines Length of sub header line to be printed
     */
    public void printSubHeaderLines(int numOfLines) {
		for (int i = 0; i < numOfLines; i++){
            System.out.printf("-");
        }
        System.out.println();
		
	}
	
	
    /** 
     * Prints Menu header of length specified
     * @param spacing Length of Menu header to be printed
     */
    public void printMenuHeader(int spacing) {
        printHeaderLines(spacing);

        //print the line with "MENU"
        System.out.printf("|" +
                "%"+(spacing/2 +2-1)+"s" +
                "%"+(spacing/2 - 2)+"c\n", this.name, '|');

        printHeaderLines(spacing);
		
	}

}
