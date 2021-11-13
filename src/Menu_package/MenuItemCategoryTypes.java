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
     * Initializes two <code>String</code> Arrays that stores <code>Item</code> Category (2D array) and <code>Item</code> Type names relating to the Menu Name specified.
     * If Menu Name is "Promotion Menu", the constructor will store <code>Item</code> Category and <code>Item</code> Type names relating to Set Items.
     * Else, it will store <code>Item</code> Category and <code>Item</code> Type names relating to non-Set Type Items.
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
     * Returns the number of <code>Item</code> Categories stored in <code>this</code> <code>class</code>.
     * @return <code>int</code> Number of <code>Item</code> Categories
     */
    public int getNumberOfCategories(){
        return this.categories.length;
    }
    

    
    /** 
     * Returns <code>String</code> array of <code>Item</code> Types stored in <code>this</code> <code>class</code>.
     * @return <code>String</code>[] <code>String</code> array of <code>Item</code> Types
     */
    public String[] getCategoryTypes(){
        return this.categoryTypes;
    }

    
    /** 
     * Returns <code>String</code> of <code>Item</code> Type at the specified index, in the <code>String</code> array of <code>Item</code> Types.
     * @param index Index
     * @return <code>String</code> <code>String</code> of <code>Item</code> Type at the specified index
     */
    public String indexToCategory(int index){
        return this.categoryTypes[index];
    }

    
    /** 
     * Returns <code>String</code> array of <code>Item</code> Category at the specified index, in the <code>String</code> 2D array of <code>Item</code> Category.
     * @param categoryIndex Index
     * @return <code>String</code>[] <code>String</code> array of <code>Item</code> Category
     */
    public String[] indexToCategoryList(int categoryIndex){
        return this.categories[categoryIndex];
    }

    
    /** 
     * Returns <code>String</code> of <code>Item</code> Type at the specified index, in the specified index that indicates <code>Item</code> Category
     * @param index Index
     * @param categoryIndex Index that indicates <code>Item</code> Category
     * @return <code>String</code> <code>Item</code> Type
     */
    public String indexToSubcategory(int index, int categoryIndex){
        String[] subcategoryList = this.indexToCategoryList(categoryIndex);
        return subcategoryList[index]; 
    }
    
    /**
     * Print the <code>Item</code> Categories in <code>this</code> class's <code>String</code> array of Category Types.
     */
    public void printCategories(){
        int i=1; 
        for (String category : this.categoryTypes){
            System.out.println(i + " : " + category);
            i++;
        }
    }

    
    /** 
     * Prints <code>Item</code> Types in the specified <code>Item</code> Category, indicated by the index
     * @param categoryIndex Index that indicates <code>Item</code> Category
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
