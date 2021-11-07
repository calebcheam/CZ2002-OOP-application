package Menu_package;
public final class MenuItemCategoryTypes {
    private String[] categoryTypes = {"Main Course", "Dessert", "Drink", "Set"};
    public static final String[] mainCourseTypes = {"Appetiser", "Salad", "Soup", "Vegetarian",
            "Local Favourites", "Meat and Seafood", "Pasta", "Pizza", "Burger & Sandwich"};
    public static final String[] dessertTypes = {"Dessert"};
    public static final String[] drinkTypes = {"Fizzy Drinks", "Juices", "Tea", "Coffee"};
    public static final String[] setTypes = {"Set"};

    private String[][] categories = new String[][] { mainCourseTypes, dessertTypes, drinkTypes, setTypes };

    public MenuItemCategoryTypes(){
       
    };

    public String[] getCategoryTypes(){
        return this.categoryTypes;
    }

    public String indexToCategory(int index){
        return this.categoryTypes[index];
    }

    public String[] stringToCategoryList(int categoryIndex){
        return this.categories[categoryIndex];
    }

    public String indexToSubcategory(int index, int categoryIndex){
        String[] subcategoryList = this.stringToCategoryList(categoryIndex);
        return subcategoryList[index]; 
    }
    
    public void printCategories(){
        int i=1; 
        for (String category : this.categoryTypes){
            System.out.println(i + " : " + category);
            i++;
        }
    }

    public void printSubCategories(int categoryIndex){
        String[] subcategoryList = this.stringToCategoryList(categoryIndex);

        int i = 1;
        for (String subcategory : subcategoryList){
            System.out.println(i + " : " + subcategory);
            i++;
        }
    }



}
