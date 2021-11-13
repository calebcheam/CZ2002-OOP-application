package Menu_package;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to store Menu Items' and Sets' attributes.
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class Item {
    private String name;
    private String type;
    private ArrayList<String> description;
    private float price;
    private int stock;

    /**
     * Constructor of <code>this</code> <code>class</code>.
     * @param name <code>Item</code> name
     * @param type <code>Item</code> Type
     * @param description <code>Item</code> description
     * @param price <code>Item</code> price
     * @param stock <code>Item</code> stock
     */
    public Item(String name, String type, ArrayList<String> description, float price, int stock){
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Another constructor of <code>this</code> <code>class</code>.
     * @param csvLine <code>String</code> the line from the CSV that will be used to create this item
     */
    public Item (String csvLine){
        //Create an Item object using the csv values
        String[] itemAttributes = csvLine.split(",");//get the csv row value into String array
        this.name = itemAttributes[0];
        this.type = itemAttributes[1];
        this.description = splitDescription(itemAttributes[2], type);
        this.price = Float.parseFloat(itemAttributes[3]);
        this.stock = Integer.parseInt(itemAttributes[4]);
    }
    
    /**
     * Another constructor of <code>this</code> <code>class</code>.
     */
    public Item(){}

    
    /** 
     * Returns <code>String</code> of this class's attributes, where attributes are separated by commas
     * This class's description <code>ArrayList</code> is converted to <code>String</code> by inserting "With " at the start of the string, followed by the elements of the <code>ArrayList</code>, each separated by " + ".
     * @return <code>String</code> of <code>Item</code> attributes, separated by commas
     */
    public String AlaCarteToCSVString(){
        String descriptionString = "";
        boolean isFirst = true;
        for (String string : this.description){
            if (isFirst){
                string = "With " + string + " "; 
                isFirst = false;
            } else {
                string = "+ " + string + " "; 
            }
            descriptionString = descriptionString + string; 
        }
        return this.name + "," + this.type + "," + descriptionString + "," + String.valueOf(this.price) + "," + String.valueOf(this.stock);
    }

    
    
    /** 
     * Splits the description of an <code>Item</code> into strings for storage into an <code>ArrayList</code>, which is returned to the caller
     * @param descriptionString <code>String</code> of <code>Item</code> description to be split
     * @param type <code>Item</code> type 
     * @return <code>ArrayList</code> containing the split description
     */
    public ArrayList<String> splitDescription(String descriptionString, String type){
        //Create ArrayList to return at the end
        ArrayList<String> descriptionList = new ArrayList<>();

        //Get ArrayList of Set types
        ArrayList<String> setTypesList = new ArrayList<>(Arrays.asList(MenuItemCategoryTypes.setTypes));
        ArrayList<String> itemDescription;//array to store split item description

        //Check if description is for Set type or not
        if (setTypesList.contains(type)){//if item is of Set type
            String splitItems = "\\) \\+ ";//String to split each item in the set
            String splitItemAndDescription = "\\(";//String to split item name and description of each item in the set

            itemDescription = new ArrayList<>(Arrays.asList(descriptionString.split(splitItems))); //split string by each item in set

            for (int i = 0; i < 4; i++){
                //split the name of item and its description (no description for drinks,
                //and add both into the return ArrayList
                descriptionList.addAll(Arrays.asList(itemDescription.get(i).split(splitItemAndDescription)));
            }
        }
        else{//if item is not a Set
            String splitItems = " \\+ ";//String to split the description (content) of the item

            //split the item's description
            itemDescription = new ArrayList<>(Arrays.asList(descriptionString.split(splitItems)));

            //add Item description ArrayList into the return ArrayList
            descriptionList.addAll(itemDescription);
        }
        return descriptionList;
    }
    
    /** 
     * Return <code>String</code> of <code>this</code> class's attributes, where attributes are separated by commas
     * This class's description <code>ArrayList</code> is converted to <code>String</code> by joining all elements in the <code>ArrayList</code> by " + "
     * @return <code>String</code> <code>String</code> of <code>Item</code> attributes, separated by commas
     */
    public String SetToString(){
        String descriptionString = String.join(" + ", this.description);
        return this.name + "," + this.type + "," + descriptionString + "," + String.valueOf(this.price) + "," + String.valueOf(this.stock);

    }


    
    /** 
     * Return <code>this</code> class's name.
     * @return <code>String</code> <code>Item</code> name
     */
    public String getName(){
        return this.name;
    }

    
    /** 
     * Return <code>this</code> class's type.
     * @return <code>String</code> <code>Item</code> Type
     */
    public String getType(){
        return this.type;
    }

    
    /** 
     * Return <code>this</code> class's description
     * @return <code>ArrayList</code> of <code>String</code> <code>Item</code> Description
     */
    public ArrayList<String> getDescription(){
        return this.description;
    }

    
    /** 
     * Return <code>this</code> class's price.
     * @return <code>float</code> <code>Item</code> price
     */
    public float getPrice(){
        return this.price;
    }

    
    /** 
     * Return <code>this</code> class's stock.
     * @return <code>int</code> <code>Item</code> stock
     */
    public int getStock(){
        return this.stock;
    }

    
    /** 
     * Set <code>this</code> class's name.
     * @param name <code>Item</code> name to be replaced
     */
    public void setName(String name){
        this.name = name;
    }

    
    /** 
     * Set <code>this</code> class's type.
     * @param type <code>Item</code> Type to be replaced
     */
    public void setType(String type){
        this.type = type;
    }

    
    /** 
     * Set <code>this</code> class's description using a row from the csv file provided (with information of all Menu Items and Set)
     * @param descriptionString <code>String</code> describing <code>Item</code> description, extracted from a single row from csv file
     */
    public void setDescription(String descriptionString){
        //description is csvRow[2]
        boolean isSet;
        if (this.type.equals("Set")) isSet = true;
        else isSet = false;

        this.description = new ArrayList<String>();

        String[] itemDescription;
        if (!isSet){
            itemDescription = descriptionString.split(" \\+ ");
            for (int i = 0; i < itemDescription.length; i++){
                this.description.add(itemDescription[i]);
            }
        }
        else{
            //split the description by ") + " to get each item in the Set
            itemDescription = descriptionString.split("\\) \\+ ");
            for (int i = 0; i < 3; i++){
                this.description.add(itemDescription[i]+ ")");
            }
            this.description.add(itemDescription[3]);
        }
    }

    
    /** 
     * Set <code>this</code> class's description using <code>ArrayList</code> that contains Strings (that describes the <code>Item</code>)
     * @param description <code>ArrayList</code> of <code>String</code> that describes the <code>Item</code>
     */
    public void setDescription(ArrayList<String> description){
        this.description = description;
    }

    
    /** 
     * Set <code>this</code> class's price.
     * @param price <code>Item</code> price
     */
    public void setPrice(float price){
        this.price = price;
    }

    
    /** 
     * Set <code>this</code> class's stock. 
     * @param stock <code>Item</code> stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Print <code>this</code> class's description.
     */
    public void printDescription(){
        for (int i=0; i<this.description.size(); i++){
            System.out.println(this.description.get(i));
        }
    }
}
