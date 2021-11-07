package Menu_package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PromoMenu implements GenericMenu{

    private ArrayList<Item> setItems;
    private CSVHandler csvHandler;
    private int longestStringSize;
    private final String csvPath = "PromoMenuItem.csv";
    //This message below is later used for the display menu method
    private final String priceDisclaimerMessage = "* Prices do not include GST & Service charges *";
    private final String priceTag = "Price: SGD$"; //used for display menu method


    private String menuName;
    private String termsAndConditions;
    private boolean isValidNow;


    public PromoMenu(){
        this.menuName = "unnamed promo menu";
        this.termsAndConditions = " undefined terms and conditions ";
        this.isValidNow = false; 

        //Create ArrayList to store Item object according to the Item type
        setItems = new ArrayList<>();

        //A class used to extract attributes from a row in the csv file
        this.csvHandler = new CSVHandler();

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

	@Override
	public String findItemTypeCategory(String itemType) {
		ArrayList<String> setTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.setTypes));
        if (setTypesList.contains(itemType)) {
            return "Set";
        }
        else{
            return "Invalid Type";
        }
	}

	@Override
	public int allocateItem(Item menuItem, String itemType) {
        String typeCategory = findItemTypeCategory(itemType);

        if (typeCategory == "Set"){
            setItems.add(menuItem);
            return 1;

        } else return -1;
	}

	@Override
	public int findFirstTypeOccurrence(String typeCategory, String itemType) {
		ArrayList<Item> tempItemArray;
        if (typeCategory=="Set") tempItemArray = setItems;
        else return -2; 
        
        String tempType;
        //Search first occurrence of the Item type
        for (int i = 0; i < tempItemArray.size(); i++){
            tempType = tempItemArray.get(i).getType();
            if (tempType.equals(itemType)) return i;//return index of first occurrence of itemType
        }
        return -1;//return -1 if itemType is in a category but not found
	}

	@Override
	public ArrayList<Item> returnItemListReference(String typeCategory) {
		return this.setItems;
	}

	@Override
	public int add(String name, String type, ArrayList<String> description, float price, int stock){
        Item newItem = new Item(name, type, description, price, stock);
        int check = allocateItem(newItem, newItem.getType());
        if (check==1){
            this.csvHandler.addItemToCSV(newItem, this.csvPath);
        } 
        return check;
    }

	@Override
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

	@Override
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

	@Override
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

	@Override
	public int getLongestStringSize() {
		return this.longestStringSize;
	}

	@Override
	public void printHeaderLines(int numOfLines) {
		for (int i = 0; i < numOfLines; i++){
            System.out.printf("=");
        }
        System.out.println();
		
	}

	@Override
	public void printSubHeaderLines(int numOfLines) {
        for (int i = 0; i < numOfLines; i++){
            System.out.printf("-");
        }
        System.out.println();
		
	}

	@Override
	public void printMenuHeader(int spacing) {
		printHeaderLines(spacing);

        //print the line with "MENU"
        System.out.printf("|" +
                "%"+(spacing/2 +2-1)+"s" +
                "%"+(spacing/2 - 2)+"c\n", "PROMO MENU", '|');

        printHeaderLines(spacing);
		
	}

	@Override
	public void displayMenu() {
		int spacing = this.getLongestStringSize()+20;
        printMenuHeader(spacing);
        System.out.printf("| %s%"+(spacing-priceDisclaimerMessage.length()-2)+"c\n", priceDisclaimerMessage, '|');
        printHeaderLines(spacing);
        printSetSection(setItems, spacing);
		
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
    
}
