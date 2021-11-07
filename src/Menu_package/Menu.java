import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private ArrayList<Item> mainCourseItems;
    private ArrayList<Item> dessertItems;
    private ArrayList<Item> drinkItems;
    private ArrayList<Item> setItems;
    private int longestStringSize;
    private final String csvPath = "MenuItem.csv";
    //This message below is later used for the display menu method
    private final String priceDisclaimerMessage = "* Prices do not include GST & Service charges *";
    private final String priceTag = "Price: SGD$"; //used for display menu method

    //Read and Create arrays of menuItems
    public Menu(){
        //Create ArrayList to store Item object according to the Item type
        mainCourseItems = new ArrayList<>();
        dessertItems = new ArrayList<>();
        drinkItems = new ArrayList<>();
        setItems = new ArrayList<>();

        //A class used to extract attributes from a row in the csv file
        CSVHandler csvHandler = new CSVHandler();

        //This Arraylist is used to get the longest string (from menuItems names and description,
        //to format line spacing for displaying the menu (displayMenu())
        ArrayList<String> itemsStringList = new ArrayList<>();

        String line = ""; //temporary variable to store each line of the csv file

        ArrayList<Item> invalidItems = new ArrayList<>();//ArrayList to store any Item with invalid type

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            br.readLine();//read first line and ignore as first line is header

            while ((line = br.readLine()) != null){//while there is a row of values in the csv
                Item menuItem = csvHandler.createItem(line);//create Item object from current row in the csv file

                //allocate Item into ArrayList according to its type
                int allocSuccess = allocateItem(menuItem, menuItem.getType());

                if (allocSuccess == 1){
                    itemsStringList.add(menuItem.getName());
                    itemsStringList.addAll(menuItem.getDescription());
                }
                else invalidItems.add(menuItem); //add Item with invalid type

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //find the longest string to be displayed in the menu
        longestStringSize = findLongestStringSize(itemsStringList);

        //If there is any Item not added due to its invalid Item type, print message to indicate that
        if (invalidItems.size() > 0) invalidItemMessage(invalidItems);
    }

    public void invalidItemMessage(ArrayList<Item> invalidItems){
        System.out.println("Item(s) listed below is/are not added due to its invalid Item type: ");
        for (int i = 0; i < invalidItems.size(); i++){
            System.out.printf("%d. %s\n", i+1, invalidItems.get(i).getName());
            System.out.printf("\tType: %s\n", invalidItems.get(i).getType());
            System.out.println();
        }
    }

    public String findItemTypeCategory(String itemType){
        /* Method to find which category is itemType in.
        * (e.g. itemType = "Appetiser" => Main Course Category)
        * return "Invalid Type" if the type is not in any category*/

        //Create Arraylists from the item types' array to use
        //for allocation of Item object into its respective ArrayList (the ArrayLists created above)
        ArrayList<String> mainCourseTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.mainCourseTypes));
        ArrayList<String> dessertTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.dessertTypes));
        ArrayList<String> drinkTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.drinkTypes));
        ArrayList<String> setTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.setTypes));

        if (mainCourseTypesList.contains(itemType)){
            return "Main Course";
        }
        else if (dessertTypesList.contains(itemType)){
            return "Dessert";
        }
        else if (drinkTypesList.contains(itemType)){
            return "Drink";
        }
        else if (setTypesList.contains(itemType)) {
            return "Set";
        }
        else{
            return "Invalid Type";
        }
    }

    public int allocateItem(Item menuItem, String itemType){
        /*Allocate Item object into the ArrayList of Items, according to its type*/

        String typeCategory = findItemTypeCategory(itemType);

        //add menuItem to the items list (eg. mainCourseItems) according to its type
        switch(typeCategory){
            case "Main Course":
                mainCourseItems.add(menuItem);
                return 1; //return success
            case "Dessert":
                dessertItems.add(menuItem);
                return 1; //return success
            case "Drink":
                drinkItems.add(menuItem);
                return 1; //return success
            case "Set":
                setItems.add(menuItem);
                return 1; //return success
            default:
                return -1; //return unsuccessful
        }
    }

    public int findFirstTypeOccurrence(String typeCategory, String itemType){
        /*Find the first occurrence of a type (e.g. Appetiser) in the ArrayList of Items of "typeCategory" category
        * This method is used to delete/update an Item*/

        ArrayList<Item> tempItemArray;

        switch(typeCategory){
            case "Main Course":
                tempItemArray = mainCourseItems;
                break;
            case "Dessert":
                tempItemArray = dessertItems;
                break;
            case "Drink":
                tempItemArray = drinkItems;
                break;
            case "Set":
                tempItemArray = setItems;
                break;
            default:
                return -2;//return -2 if itemType is not in any category
        }

        String tempType;
        //Search first occurrence of the Item type
        for (int i = 0; i < tempItemArray.size(); i++){
            tempType = tempItemArray.get(i).getType();
            if (tempType.equals(itemType)) return i;//return index of first occurrence of itemType
        }
        return -1;//return -1 if itemType is in a category but not found
    }

    public ArrayList<Item> returnItemListReference(String typeCategory){
        /*This method returns the reference of ArrayList of Item (one of the attributes of Menu class),
        that contains Items that is classified under "typeCategory" (Item type) category
        * Only use this method when itemType parameter is always a valid Item type*/

        ArrayList<Item> tempItemArray;

        switch(typeCategory) {
            case "Main Course":
                tempItemArray = mainCourseItems;
                break;
            case "Dessert":
                tempItemArray = dessertItems;
                break;
            case "Drink":
                tempItemArray = drinkItems;
                break;
            default: //case "Set":
                tempItemArray = setItems;
                break;
        }
        return tempItemArray;
    }

    public void add(String name, String type, ArrayList<String> description, float price, int stock){
        Item newItem = new Item(name, type, description, price, stock);
        allocateItem(newItem, newItem.getType());
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

    public void updateItem(int number, String typeCategory, String itemType, ArrayList<String> updateInfo)
    {
        /*Update the specified Item's attributes according to the type category, Item type, first element in updateInfo,
            the numbering of the Item in the Menu display.
          The updateInfo's (last parameter) first element will state what attribute to change.
            -if "1" - change Item name
            -if "2" - change Item type
            -if "3" - change Item description
            -if "4" - change Item price
            -if "5" - change Item stock
            -other values will not be considered as invalid input will be taken care by the MenuUI

          For Item type update, it must be a type that is in "typeCategory" category. This must be ensured in the MenuUI

        This method will assume that no invalid "itemCategory" and "itemType" will be passed into the parameter,
         as it will be settled in the MenuUI (boundary) class.
         */

        //find first occurrence of Item type in the category
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

    public int findLongestStringSize(ArrayList<String> itemsNamesTypesList){
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

    public void printHeaderLines(int numOfLines){
        for (int i = 0; i < numOfLines; i++){
            System.out.printf("=");
        }
        System.out.println();
    }

    public void printSubHeaderLines(int numOfLines){
        for (int i = 0; i < numOfLines; i++){
            System.out.printf("-");
        }
        System.out.println();
    }

    public void printMenuHeader(int spacing){
        printHeaderLines(spacing);

        //print the line with "MENU"
        System.out.printf("|" +
                "%"+(spacing/2 +2-1)+"s" +
                "%"+(spacing/2 - 2)+"c\n", "MENU", '|');

        printHeaderLines(spacing);
    }

    public void printItemSection(ArrayList<Item> menuItems, int spacing, boolean isDrinkType){
        //only for non-Set type items
        int i;
        String menuItemName;
        ArrayList<String> menuItemDescription;
        float menuPrice;
        String type;
        for (int j = 0; j < menuItems.size(); j++) {
            i = 1;
            type = menuItems.get(j).getType();
            System.out.printf("| " + type + ":%"+(spacing-type.length()-3)+"c\n", '|');
            printSubHeaderLines(spacing);

            while (type.equals(menuItems.get(j).getType())){ //while the current Item type equals to type
                menuItemName = menuItems.get(j).getName();
                menuItemDescription = menuItems.get(j).getDescription();
                menuPrice = menuItems.get(j).getPrice();

                System.out.printf("|%6d. %s", i, menuItemName);
                System.out.printf("%"+(spacing-menuItemName.length()-9-priceTag.length())+"s %.2f",
                        priceTag, menuPrice);
                if (menuPrice < 10) System.out.printf("%6c\n", '|');
                else System.out.printf("%5c\n", '|');


                if (isDrinkType == false){//if Item is not Drink type
                    for (int k = 0; k < menuItemDescription.size(); k++){
                        int curPartDescriptionLength = menuItemDescription.get(k).length();
                        //9 whitespaces in between left '|' and '-'
                        if (k == 0) System.out.printf("|\t\t - ");
                        else System.out.printf("|\t\t   + ");

                        System.out.printf("%s", menuItemDescription.get(k));
                        if (k == 0){
                            System.out.printf("%"+(spacing-curPartDescriptionLength-11)+"c\n", '|');
                        }
                        else System.out.printf("%"+(spacing-curPartDescriptionLength-13)+"c\n", '|');

                    }
                }

                j++;
                if (j < menuItems.size()) printSubHeaderLines(spacing);
                else break;
                i++;
            }
        }
        printHeaderLines(spacing);
        printHeaderLines(spacing);
    }

    public void printSetSection(ArrayList<Item> setItems, int spacing){
        String type;
        int i;

        for (int j = 0; j < setItems.size(); j++) {
            i = 1;
            type = setItems.get(j).getType();
            System.out.printf("| " + type + ":%"+(spacing-type.length()-3)+"c\n", '|');

            printSubHeaderLines(spacing);

            while (type.equals(setItems.get(j).getType())){
                String setItemName = setItems.get(j).getName();
                ArrayList<String> setItemDescription = setItems.get(j).getDescription();
                float setPrice = setItems.get(j).getPrice();

                System.out.printf("|%6d. %s", i, setItemName);
                System.out.printf("%"+(spacing-setItemName.length()-9-priceTag.length())+"s %.2f",
                        priceTag, setPrice);
                if (setPrice < 10) System.out.printf("%4c\n", '|');//4 is the length of the price (e.g. "4.00")
                else System.out.printf("%5c\n", '|');//5 is the length of the price (e.g. "24.00")

                for (int k = 0; k < setItemDescription.size(); k++){
                    int curPartDescriptionLength = setItemDescription.get(k).length();
                    if (k % 2 == 0){ //if even index
                        System.out.printf("|\t\t - ");
                        System.out.printf("%s", setItemDescription.get(k));
                        System.out.printf("%"+(spacing-curPartDescriptionLength-11)+"c\n", '|');
                    }
                    else{
                        System.out.printf("|\t\t   ");
                        System.out.printf("(%s)", setItemDescription.get(k));
                        System.out.printf("%"+(spacing-curPartDescriptionLength-13)+"c\n", '|');
                    }
                }
                j++;
                if (j < setItems.size()) printSubHeaderLines(spacing);
                else break;
                i++;
            }
        }
        printHeaderLines(spacing);
    }

    public void displayMenu(int longestStringSize) {
        int spacing = longestStringSize+20; //number of spacing to format the menu display spacing

        printMenuHeader(spacing);

        System.out.printf("| %s%"+(spacing-priceDisclaimerMessage.length()-2)+"c\n", priceDisclaimerMessage, '|');
        printHeaderLines(spacing);

        printItemSection(mainCourseItems, spacing, false);
        printItemSection(dessertItems, spacing, false);
        printItemSection(drinkItems, spacing, true);
        printSetSection(setItems, spacing);
    }

    public int getLongestStringSize(){
        return this.longestStringSize;
    }

    public static void main(String[] args){
        //the main method is just to check if the class is working properly, not for the project
        Menu menu = new Menu();
        System.out.printf("Longest string: %d\n", menu.getLongestStringSize());
        menu.displayMenu(menu.getLongestStringSize());
        String[] updateInfoArray = {"1", "Hello World"};
        ArrayList<String> updateInfo = new ArrayList<>(Arrays.asList(updateInfoArray));
        menu.updateItem(1, "Main Course", "Appetiser", updateInfo);
        menu.displayMenu(menu.getLongestStringSize());

    }

}
