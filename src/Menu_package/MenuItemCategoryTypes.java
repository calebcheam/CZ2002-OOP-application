package Menu_package;
public final class MenuItemCategoryTypes {
    private String[] categoryTypes ;
    public static final String[] mainCourseTypes = {"Appetiser", "Salad", "Soup", "Vegetarian",
            "Local Favourites", "Meat and Seafood", "Pasta", "Pizza", "Burger & Sandwich"};
    public static final String[] dessertTypes = {"Dessert"};
    public static final String[] drinkTypes = {"Fizzy Drinks", "Juices", "Tea", "Coffee"};
    public static final String[] setTypes = {"Set"};

    private String[][] categories;
    

    public MenuItemCategoryTypes(String menuName){
        if (menuName == "Promotion Menu"){
            this.categories = new String[][] {setTypes};
            this.categoryTypes = new String[] {"Set"};
        } else {
            this.categories = new String[][] { mainCourseTypes, dessertTypes, drinkTypes };
            this.categoryTypes = new String[] {"Main Course", "Dessert", "Drink"};
        }
       
    };

    public int getNumberOfCategories(){
        return this.categories.length;
    }
    

    public String[] getCategoryTypes(){
        return this.categoryTypes;
    }

    public String indexToCategory(int index){
        return this.categoryTypes[index];
    }

    public String[] indexToCategoryList(int categoryIndex){
        return this.categories[categoryIndex];
    }

    public String indexToSubcategory(int index, int categoryIndex){
        String[] subcategoryList = this.indexToCategoryList(categoryIndex);
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
        String[] subcategoryList = this.indexToCategoryList(categoryIndex);

        int i = 1;
        for (String subcategory : subcategoryList){
            System.out.println(i + " : " + subcategory);
            i++;
        }
    }

}
