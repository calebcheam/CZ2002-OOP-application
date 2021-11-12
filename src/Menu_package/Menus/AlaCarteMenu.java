package Menu_package.Menus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Menu_package.Item;
import Menu_package.MenuItemCategoryTypes;

/**This class prints the Ala Carte Menu and handles processes that involves Items of the Ala Carte Menu.
 * This class creates 3 ArrayLists of Item objects that contains Item objects of Item Category of "Main Course", "Dessert", and "Drink" respectively. 
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class AlaCarteMenu extends GenericMenu{

    private ArrayList<Item> mainCourseItems=new ArrayList<>();
    private ArrayList<Item> dessertItems=new ArrayList<>();
    private ArrayList<Item> drinkItems=new ArrayList<>();
    
    
    /**
     * Constructor for AlaCarteMenu class.
     * Creates 3 ArrayLists of Item objects that contains Item objects of Item Category of "Main Course", "Dessert", and "Drink" respectively,
     * using the information from the csv file (containing Menu Items' information).
     */
    public AlaCarteMenu(){ //constructor

        this.name="Ala Carte Menu";
        this.menuCategories = new MenuItemCategoryTypes(this.name);
        this.csvPath="AlaCarteMenuItem.csv";
        
        
        ArrayList<String> itemsStringList = new ArrayList<>();

        String line = ""; //temporary variable to store each line of the csv file

        ArrayList<Item> invalidItems = new ArrayList<>();//ArrayList to store any Item with invalid type

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            br.readLine();//read first line and ignore as first line is header

            while ((line = br.readLine()) != null){//while there is a row of values in the csv
                Item menuItem = this.csvHandler.createItem(line);//create Item object from current row in the csv file

                //allocate Item into ArrayList according to its type
                int allocSuccess = allocateItem(menuItem, menuItem.getType());

                if (allocSuccess == 1){
                    itemsStringList.add(menuItem.getName());
                    itemsStringList.addAll(menuItem.getDescription());
                    //System.out.println("I CREATED A " + menuItem.getName());
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

        
    }

    
    /** 
     * Prints the Items' name, price, description(excluded for types in Drink category) in the menuItems, separated by their Item types using a header line.
     * For each Item, price and description(excluded for types in Drink category) of Item is printed right and below of the Item name respectively.
     * This method is only used for Item type that do not belong to Set category.
     * @param menuItems List of Item objects
     * @param spacing Used to format the positioning of the displayed words and header in the Menu display.
     * @param isDrinkType To indicate if Items in menuItems are of Drink category or not. "true" if in Drink category, "false" if not in Drink category.
     */
    public void printItemSection(ArrayList<Item> menuItems, int spacing, boolean isDrinkType){
        int i;
        String menuItemName;
        ArrayList<String> menuItemDescription;
        float menuPrice;
        String type;
       
        for (int j = 0; j < menuItems.size(); j++) {
            i = 1;
            if (j > 0) j--;
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



    
    /** 
     * Returns the Category name of an Item type.
     * (e.g. itemType = "Appetiser" => Main Course Category).
     * Return "Invalid Type" if the Item type is not in any category.
     * @param itemType Item type 
     * @return String String to indicate the Category of the Item type.
     */
    @Override
	public String findItemTypeCategory(String itemType) {
		
        //Create Arraylists from the item types' array to use
        //for allocation of Item object into its respective ArrayList (the ArrayLists created above)
        ArrayList<String> mainCourseTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.mainCourseTypes));
        ArrayList<String> dessertTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.dessertTypes));
        ArrayList<String> drinkTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.drinkTypes));
        

        if (mainCourseTypesList.contains(itemType)){
            return "Main Course";
        }
        else if (dessertTypesList.contains(itemType)){
            return "Dessert";
        }
        else if (drinkTypesList.contains(itemType)){
            return "Drink";
        }
        else{
            return "Invalid Type";
        }
	}
	
	
    /**
     * Adds Item object into the ArrayList of Items, that contains Item objects of the same category.
     * @param menuItem Item object to be addede
     * @param itemType Item type of the Item object
     * @return int Return 1 to indicate Item object has been added, -1 to indicate Item object is not added
     */
    public int allocateItem(Item menuItem, String itemType) {

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
            default:
                return -1; //return unsuccessful
        }
	}
	
	
    /**
     * Find the first occurrence of an Item type (e.g. Appetiser) in the ArrayList of Items. The ArrayList contains the Items of the Category, that Item type is under.
     * This method is used in the proccess of deleting/updating an Item's information.
     * @param typeCategory Category the Item type is under
     * @param itemType Item type, to find the first occurrence in the ArrayList
     * @return int Returns the index of the first occurrence of the specified Item type. Returns -2 if Item type is not in any category, -1 if Item type is in a category, but Item type is not found in the ArrayList.
     */
    public int findFirstTypeOccurrence(String typeCategory, String itemType) {

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
	
	
    /**
     * This method returns the reference of ArrayList of Item (one of the attributes of AlaCarteMenu class), that contains Items that is under an Item Category specified.
     * This method can only be used when it can be assumed that Item Category specified is always valid.
     * @param typeCategory Item Category, of the ArrayList wanted.
     * @return ArrayList<Item> ArrayList of Item that contains Items under the specified Item category.
     */
    public ArrayList<Item> returnItemListReference(String typeCategory) {
	    
        ArrayList<Item> tempItemArray;

        switch(typeCategory) {
            case "Main Course":
                tempItemArray = this.mainCourseItems;
                break;
            case "Dessert":
                tempItemArray = this.dessertItems;
                break;
            case "Drink":
                tempItemArray = this.drinkItems;
                break;
            default: //case "Set":
                tempItemArray = this.mainCourseItems;
                break;
        }
        return tempItemArray;
	}
	
    /**
     * Prints Ala Carte Menu.
     */
    public void displayMenu() {
	    
	int spacing = longestStringSize+20; //number of spacing to format the menu display spacing

        printMenuHeader(spacing);
        System.out.printf("| %s%"+(spacing-priceDisclaimerMessage.length()-2)+"c\n", priceDisclaimerMessage, '|');
        printHeaderLines(spacing);

        printItemSection(mainCourseItems, spacing, false);
        printItemSection(dessertItems, spacing, false);
        printItemSection(drinkItems, spacing, true);
		
    }

    
    /**
     * Prints Ala Carte Menu of Items that is under the Item Category specified.
     * @param category Item Category, to specify the Category of Items to be printed
     */
    @Override
    public void displayMenuCategory(String category){ 
        // used in Order section so that we wont print whole menu every time
       
        int spacing = longestStringSize+20; //number of spacing to format the menu display spacing
        printMenuHeader(spacing);
        System.out.printf("| %s%"+(spacing-priceDisclaimerMessage.length()-2)+"c\n", priceDisclaimerMessage, '|');
        printHeaderLines(spacing);
        
        ArrayList<Item> categoryList = returnItemListReference(category);
        if (category!="Drink") printItemSection(categoryList, spacing, false);
        else printItemSection(drinkItems, spacing, true);
    }
}
