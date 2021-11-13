package Menu_package;

/**
 * Final <code>class</code> to store Strings arrays of different <code>Item</code> Categories, that stores <code>Item</code> Types under it.
 * This <code>class</code> also contains methods relating to the <code>Item</code> Categories.
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public final class MenuItemCategoryTypes {
    private String[] categoryTypes ;
    public static final String[] mainCourseTypes = {"Appetiser", "Salad", "Soup", "Vegetarian",
            "Local Favourites", "Meat and Seafood", "Pasta", "Pizza", "Burger & Sandwich"};
    public static final String[] dessertTypes = {"Dessert"};
    public static final String[] drinkTypes = {"Fizzy Drinks", "Juices", "Tea", "Coffee"};
    public static final String[] setTypes = {"Set"};

    private String[][] categories;
    

    /**
     * Constructor of <code>this</code> <code>class</code>.
     * Initializes two <code>String</code> Arrays that stores Item Category (2D array) and Item Type names relating to the Menu Name specified.
     * If Menu Name is "Promotion Menu", the constructor will store Item Category and Item Type names relating to Set Items.
     * Else, it will store Item Category and Item Type names relating to non-Set Type Items.
     */
    public MenuItemCategoryTypes(String menuName){
        if (menuName == "Promotion Menu"){
            this.categories = new String[][] {setTypes};
            this.categoryTypes = new String[] {"Set"};
        } else {
            this.categories = new String[][] { mainCourseTypes, dessertTypes, drinkTypes };
            this.categoryTypes = new String[] {"Main Course", "Dessert", "Drink"};
        }
       
    };

    
    /** 
     * Returns the number of Item Categories stored in <code>this</code> <code>class</code>.
     * @return int Number of Item Categories
     */
    public int getNumberOfCategories(){
        return this.categories.length;
    }
    

    
    /** 
     * Returns <code>String</code> array of Item Types stored in <code>this</code> <code>class</code>.
     * @return String[] <code>String</code> array of Item Types
     */
    public String[] getCategoryTypes(){
        return this.categoryTypes;
    }

    
    /** 
     * Returns <code>String</code> of Item Type at the specified index, in the <code>String</code> array of Item Types.
     * @param index Index
     * @return String <code>String</code> of Item Type at the specified index
     */
    public String indexToCategory(int index){
        return this.categoryTypes[index];
    }

    
    /** 
     * Returns <code>String</code> array of Item Category at the specified index, in the <code>String</code> 2D array of Item Category.
     * @param categoryIndex Index
     * @return String[] <code>String</code> array of Item Category
     */
    public String[] indexToCategoryList(int categoryIndex){
        return this.categories[categoryIndex];
    }

    
    /** 
     * Returns <code>String</code> of Item Type at the specified index, in the specified index that indicates Item Category
     * @param index Index
     * @param categoryIndex Index that indicates Item Category
     * @return String Item Type
     */
    public String indexToSubcategory(int index, int categoryIndex){
        String[] subcategoryList = this.indexToCategoryList(categoryIndex);
        return subcategoryList[index]; 
    }
    
    /**
     * Print the Item Categories in <code>this</code> class's <code>String</code> array of Category Types.
     */
    public void printCategories(){
        int i=1; 
        for (String category : this.categoryTypes){
            System.out.println(i + " : " + category);
            i++;
        }
    }

    
    /** 
     * Prints Item Types in the specified Item Category, indicated by the index
     * @param categoryIndex Index that indicates Item Category
     */
    public void printSubCategories(int categoryIndex){
        String[] subcategoryList = this.indexToCategoryList(categoryIndex);

        int i = 1;
        for (String subcategory : subcategoryList){
            System.out.println(i + " : " + subcategory);
            i++;
        }
    }

}
