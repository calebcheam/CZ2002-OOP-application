package Menu_package.Menus;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Menu_package.Item;
import Menu_package.MenuItemCategoryTypes;

public class PromoMenu extends GenericMenu{

    private ArrayList<Item> setItems;


    public PromoMenu(){
        
        this.name="Promotion Menu";
        this.menuCategories = new MenuItemCategoryTypes(this.name);
        this.csvPath="PromoMenuItem.csv";

        //Create ArrayList to store Item object according to the Item type
        setItems = new ArrayList<>();


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

       
    }

    
    /** 
     * @param setItems
     * @param spacing
     */
    public void printSetSection(ArrayList<Item> setItems, int spacing){
        String type;
        int i;

        for (int j = 0; j < setItems.size(); j++) {
            i = 1;
            if (j > 0) j--;
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

    

	
    /** 
     * @param itemType
     * @return String
     */
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

	
    /** 
     * @param menuItem
     * @param itemType
     * @return int
     */
    @Override
	public int allocateItem(Item menuItem, String itemType) {
        String typeCategory = findItemTypeCategory(itemType);

        if (typeCategory == "Set"){
            setItems.add(menuItem);
            return 1;

        } else return -1;
	}

	
    /** 
     * @param typeCategory
     * @param itemType
     * @return int
     */
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

	
    /** 
     * @param typeCategory
     * @return ArrayList<Item>
     */
    @Override
	public ArrayList<Item> returnItemListReference(String typeCategory) {
		return this.setItems;
	}



	@Override
	
    public void displayMenu() {
		int spacing = this.getLongestStringSize()+20;
        printMenuHeader(spacing);
        System.out.printf("| %s%"+(spacing-priceDisclaimerMessage.length()-2)+"c\n", priceDisclaimerMessage, '|');
        printHeaderLines(spacing);
        printSetSection(setItems, spacing);
		
	}

    

    
}
