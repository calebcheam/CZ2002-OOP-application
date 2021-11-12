package Menu_package.Menus;

import java.util.ArrayList;

import Menu_package.CSVHandler;
import Menu_package.Item;
import Menu_package.MenuItemCategoryTypes;

/**
 * Abstract class for sub classes that handles operation relating to different types of Menu Items.
 * @see Menus.AlaCarteMenu
 * @see Menus.PromoMenu
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public abstract class GenericMenu {
    protected MenuItemCategoryTypes menuCategories;
    protected CSVHandler csvHandler=new CSVHandler();  //we need this to be shared within the package
    protected int longestStringSize;
    protected String csvPath=null;
    //This message below is later used for the display menu method
    protected final String priceDisclaimerMessage = "* Prices do not include GST & Service charges *";
    protected final String priceTag = "Price: SGD$"; //used for display menu method
    protected String name=null;

    
    /**
     * Returns Item object by the Item Category, Type and the numbering (number).
     * For explanation purposes, numbering will be referred to as 'i'.
     * 'i' indicates the position of the Item, with the Item Type specified.
     * This method will return the 'i'th Item of the Item type specified.
     * For e.g., if Type = "Appetiser", number = 2, then the 2nd Item of Type "Appetiser" wil be returned.
     * @param number Position of Item, of the specified Item Type.
     * @param typeCategory Item Category, to indicate which Item Category the Item (to be returned) is from.
     * @param itemType Item Type, to indicate which Item Type the Item (to be returned) is of.
     * @return Item Item object under Item Category, of Item Type, and numbering specified.
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
     * Abstract method for subclasses to get Item Category of the Item Type specified.
     * @see Menus.AlaCarteMenu#findItemTypeCategory(String itemType)
     * @see Menus.PromoMenu#findItemTypeCategory(String itemType)
     * @param itemType Item Type
     * @return String Item Type
     */
    /////////////////////// methods relating to changing the menu//////////////
    public String findItemTypeCategory(String itemType){
        
    return itemType;
        
    };

    
    /**
     * Abstract method for subclasses to add Item object into ArrayList according to the Item Type.
     * @see Menus.AlaCarteMenu#allocateItem(Item menuItem, String itemType)
     * @see Menus.PromoMenu#allocateItem(Item menuItem, String itemType)
     * @param menuItem Item Object to be added
     * @param itemType Item Type of the Item object to be added
     * @return int Return positive integer if success, negative if failure (refer to above method links)
     */
    public int allocateItem(Item menuItem, String itemType)
    {
        return longestStringSize;

    };

    
    /** 
     * Abstract method for subclasses to find first occurrence index of a specified Item Type in ArrayList that stores Items under Item Category specified.
     * @see Menus.AlaCarteMenu#findFirstTypeOccurrence(String typeCategory, String itemType)
     * @see Menus.PromoMenu#findFirstTypeOccurrence(String typeCategory, String itemType)
     * @param typeCategory Item Category 
     * @param itemType Item Type 
     * @return int Return positive integer if success, negative if failure (refer to above method links)
     */
    public int findFirstTypeOccurrence(String typeCategory, String itemType)
    {
        return longestStringSize;
        
    }

    
    /** 
     * Abstract method for subclasses to return ArrayList containing the Items under the Item Category specified
     * @param typeCategory Item Category
     * @return ArrayList<Item> ArrayList that contains the Items under the Item Category specified
     */
    public ArrayList<Item> returnItemListReference(String typeCategory)
    {
        return null;

    }

    
    /** 
     * Returns String of first Item object's name of specified Item Type in the ArrayList of Items, containing Item under specified Item Category.
     * @param typeCategory Item Category
     * @param itemType Item Type
     * @return String String of first Item object's name
     */
    public String findFriend(String typeCategory, String itemType) { //to insert into correct location in the CSV
       
        int firstOccurIndex = findFirstTypeOccurrence(typeCategory, itemType);
        ArrayList<Item> tempItemArray;
        tempItemArray = returnItemListReference(typeCategory);

        return tempItemArray.get(firstOccurIndex).getName();
    }
    
    
    /** 
     * Add Item with specified attributes, to the csv file given (as the list of information of all Menu Items and Set)
     * @param name Item name
     * @param itemType Item Type
     * @param typeCategory Item Category
     * @param description Item description
     * @param price Item price
     * @param stock Item stock
     * @return int Returns 1 to indicate successful addition of Item to csv, negative integer to indicate failure
     */
    public int add(String name, String itemType, String typeCategory, ArrayList<String> description, float price, int stock){
        Item newItem = new Item(name, itemType, description, price, stock);
        int check = allocateItem(newItem, newItem.getType());
        if (check==1){
            String friendItemName= this.findFriend(typeCategory, itemType);
            //System.out.println("I want to find friend " + friendItemName);
            this.csvHandler.addItemToCSV(newItem, this.csvPath, friendItemName);
        } else System.out.println("ERROR! Item was not created successfully");
        return check;
    }

    
    /** 
     * Remove Item from the csv file (that contains information of all Items and Set Menu)
     * @param item Item to be removed
     */
    public void remove(Item item){
        String lineToDelete = item.getName();
        this.csvHandler.removeItemFromCSV(this.csvPath, lineToDelete);
    }

    
    /** 
     * Get the specified Item according to the type category, Item type, the numbering of the Item in the Menu display,
     * to prepare for removal of the Item.
     * @param number Numbering of the Item in the Menu display
     * @param typeCategory Item Category of specified Item
     * @param itemType Item Type of specified Item
     * @return Item Item to be removed
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
     * All other parameters (other than updateInfo) are used to locate the Item in the ArrayList of Items.
     * "Choice" refers to the first element of updateInfo and it is a String number. If the first element is:
     * "1" - change Item name
     * "2" - change Item Type
     * "3" - change Item description
     * "4" - change Item price
     * "5" - change Item stock
     * @param number Numbering of the Item in the Menu display
     * @param typeCategory Item Category of specified Item
     * @param itemType Item Type of specified Item
     * @param updateInfo ArrayList that contains the "choice" (that indicates which Item attributes to change), and information to replace the specified Item attribute 
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
     * Return length of the longest String to be displayed (in a single line) in the Menu display.
     * This is to format the display positioning of the Menu's words and headers.
     * @param itemsNamesTypesList ArrayList that contains all Strings that will be displayed in the Menu. The max length out of these Strings will be returned.
     * @return int Length of the longest String to be displayed in a single line.
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
     * Returns the longest String size (to be displayed in a single line in the Menu display), stored in this class.
     * @return int Longest String size
     */
    public int getLongestStringSize() {
		return this.longestStringSize;
	}

    
    /** 
     * Returns menuCategories of this class
     * @return MenuItemCategoryTypes menuCategories of this class
     */
    public MenuItemCategoryTypes getMenuItemCategoryTypes(){
        return this.menuCategories;
    }

    
    /** 
     * Returns number of Item Categories associated with this class
     * @return int Number of Item Categories
     */
    public int getNumofCategories(){
        return this.menuCategories.getNumberOfCategories();
    }
	
    /**
     * Abstract method for subclasses to implement a method to display their Menu Items
     * @see Menus.AlaCarteMenu#displayMenu()
     * @see Menus.PromoMenu#displayMenu()
     */
    public void displayMenu()
    {
        return;
    };

    
    /** 
     * Abstract method for subclasses to implement a method to display their Menu Items of a specified Item Category
     * @see Menus.AlaCarteMenu#displayMenu()
     * @see Menus.PromoMenu#displayMenu()
     * @param category Item Category specified to display Items under the Item Category
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
