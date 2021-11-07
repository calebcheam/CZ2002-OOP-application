package Menu_package.Menus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Menu_package.CSVHandler;
import Menu_package.Item;
import Menu_package.MenuItemCategoryTypes;

public class AlaCarteMenu extends GenericMenu{

    private ArrayList<Item> mainCourseItems=new ArrayList<>();
    private ArrayList<Item> dessertItems=new ArrayList<>();
    private ArrayList<Item> drinkItems=new ArrayList<>();
    
    
    public AlaCarteMenu(){ //constructor

        this.name="Ala Carte Menu";
        
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



    @Override
	public String findItemTypeCategory(String itemType) {
		/* Method to find which category is itemType in.
        * (e.g. itemType = "Appetiser" => Main Course Category)
        * return "Invalid Type" if the type is not in any category*/

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
	
	public int allocateItem(Item menuItem, String itemType) {
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
            default:
                return -1; //return unsuccessful
        }
	}
	
	public int findFirstTypeOccurrence(String typeCategory, String itemType) {
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
	
	public ArrayList<Item> returnItemListReference(String typeCategory) {
		 /*This method returns the reference of ArrayList of Item (one of the attributes of Menu class),
        that contains Items that is classified under "typeCategory" (Item type) category
        * Only use this method when itemType parameter is always a valid Item type*/

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
	
		

	
	public void displayMenu() {
		int spacing = longestStringSize+20; //number of spacing to format the menu display spacing

        printMenuHeader(spacing);
        System.out.printf("| %s%"+(spacing-priceDisclaimerMessage.length()-2)+"c\n", priceDisclaimerMessage, '|');
        printHeaderLines(spacing);

        printItemSection(mainCourseItems, spacing, false);
        printItemSection(dessertItems, spacing, false);
        printItemSection(drinkItems, spacing, true);
		
	}

    public void displayMenuByCategory(int categoryIndex){
        MenuItemCategoryTypes categoryTypes = new MenuItemCategoryTypes(); 
        String[] categories = categoryTypes.getCategoryTypes();
        String category = categories[categoryIndex];
        
        int spacing = longestStringSize+20; //number of spacing to format the menu display spacing
        printMenuHeader(spacing);
        System.out.printf("| %s%"+(spacing-priceDisclaimerMessage.length()-2)+"c\n", priceDisclaimerMessage, '|');
        printHeaderLines(spacing);
        
        ArrayList<Item> categoryList = returnItemListReference(category);
        if (category!="Drink") printItemSection(categoryList, spacing, false);
        else printItemSection(drinkItems, spacing, true);
    }
    
    

   

}
