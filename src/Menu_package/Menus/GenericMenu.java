package Menu_package.Menus;

import java.util.ArrayList;

import Menu_package.CSVHandler;
import Menu_package.Item;
import Menu_package.MenuItemCategoryTypes;

public abstract class GenericMenu {
    protected MenuItemCategoryTypes menuCategories;
    protected CSVHandler csvHandler=new CSVHandler();  //we need this to be shared within the package
    protected int longestStringSize;
    protected String csvPath=null;
    //This message below is later used for the display menu method
    protected final String priceDisclaimerMessage = "* Prices do not include GST & Service charges *";
    protected final String priceTag = "Price: SGD$"; //used for display menu method
    protected String name=null;

    public Item getItem(int number, String typeCategory, String itemType){
        //typeCategory referring to categories eg. Main Course / Drink
        //itemType referring to subcategories eg. Appetisers / Salad 
        int firstOccurIndex = findFirstTypeOccurrence(typeCategory, itemType);
        ArrayList<Item> tempItemArray;
        tempItemArray = returnItemListReference(typeCategory);
        return tempItemArray.get(firstOccurIndex + number - 1);
    }
 
    /////////////////////// methods relating to changing the menu//////////////
    public String findItemTypeCategory(String itemType){
        
    return itemType;
        
    };

    public int allocateItem(Item menuItem, String itemType)
    {
        return longestStringSize;

    };

    public int findFirstTypeOccurrence(String typeCategory, String itemType)
    {
        return longestStringSize;
        
    }

    public ArrayList<Item> returnItemListReference(String typeCategory)
    {
        return null;

    }

    public String findFriend(String typeCategory, String itemType) { //to insert into correct location in the CSV
       
        int firstOccurIndex = findFirstTypeOccurrence(typeCategory, itemType);
        ArrayList<Item> tempItemArray;
        tempItemArray = returnItemListReference(typeCategory);

        return tempItemArray.get(firstOccurIndex).getName();
    }
    
    public int add(String name, String itemType, String typeCategory, ArrayList<String> description, float price, int stock){
        Item newItem = new Item(name, itemType, description, price, stock);
        System.out.println("Trying to add this to CSV : " + newItem.toCSVString());
        int check = allocateItem(newItem, newItem.getType());
        if (check==1){

            String friendItemName= this.findFriend(typeCategory, itemType);
            System.out.println("I want to find friend " + friendItemName);
            this.csvHandler.addItemToCSV(newItem, this.csvPath, friendItemName);
        } else System.out.println("ERROR! Item was not created successfully");
        return check;
    }

    public void removeItem(int number, String typeCategory, String itemType) {
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
        tempItemArray.remove(firstOccurIndex + number - 1);
		
	}

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
    
    public int getLongestStringSize() {
		return this.longestStringSize;
	}


    public void displayMenu()
    {
        return;
    };

    public void printHeaderLines(int numOfLines) {
		for (int i = 0; i < numOfLines; i++){
            System.out.printf("=");
        }
        System.out.println();
		
	}
	
	public void printSubHeaderLines(int numOfLines) {
		for (int i = 0; i < numOfLines; i++){
            System.out.printf("-");
        }
        System.out.println();
		
	}
	
	public void printMenuHeader(int spacing) {
        printHeaderLines(spacing);

        //print the line with "MENU"
        System.out.printf("|" +
                "%"+(spacing/2 +2-1)+"s" +
                "%"+(spacing/2 - 2)+"c\n", this.name, '|');

        printHeaderLines(spacing);
		
	}
    
	
		
    
}
