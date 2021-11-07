package Menu_package;

import java.util.ArrayList;

public interface GenericMenu {

    /////////////////////// methods relating to changing the menu 
    public String findItemTypeCategory(String itemType);

    public int allocateItem(Item menuItem, String itemType);

    public int findFirstTypeOccurrence(String typeCategory, String itemType);

    public ArrayList<Item> returnItemListReference(String typeCategory);
    
    public int add(String name, String type, ArrayList<String> description, float price, int stock);

    public void removeItem(int number, String typeCategory, String itemType);

    public void updateItem(int number, String typeCategory, String itemType, ArrayList<String> updateInfo);

    ///////////////////// methods relating to displaying the menu
    public int findLongestStringSize(ArrayList<String> itemsNamesTypesList);
    public int getLongestStringSize();

    public void printHeaderLines(int numOfLines);
    public void printSubHeaderLines(int numOfLines);
    public void printMenuHeader(int spacing);

    //public void printItemSection(ArrayList<Item> menuItems, int spacing, boolean isDrinkType); <-- only in AlaCarte
    //public void printSetSection(ArrayList<Item> setItems, int spacing); <-- only in Promotional

    public void displayMenu();
    
}
