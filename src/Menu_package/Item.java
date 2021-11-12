package Menu_package;
import java.util.ArrayList;

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
     * Constructor of this class.
     * @param name Item name
     * @param itemType Item Type
     * @param description Item description
     * @param price Item price
     * @param stock Item stock
     */
    public Item(String name, String type, ArrayList<String> description, float price, int stock){
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Another constructor of this class.
     */
    public Item(){}

    
    /** 
     * Returns <code>String</code> of this class's attributes, where attributes are separated by commas
     * This class's description <code>ArrayList</code> is converted to <code>String</code> by inserting "With " at the start of the string, followed by the elements of the ArrayList, each separated by " + ".
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
     * Return String of this class's attributes, where attributes are separated by commas
     * This class's description ArrayList is converted to String by joining all elements in the ArrayList by " + "
     * @return String String of Item attributes, separated by commas
     */
    public String SetToString(){
        String descriptionString = String.join(" + ", this.description);
        return this.name + "," + this.type + "," + descriptionString + "," + String.valueOf(this.price) + "," + String.valueOf(this.stock);

    }


    
    /** 
     * Return this class's name.
     * @return String Item name
     */
    public String getName(){
        return this.name;
    }

    
    /** 
     * Return this class's type.
     * @return String Item Type
     */
    public String getType(){
        return this.type;
    }

    
    /** 
     * Return this class's description
     * @return ArrayList<String> Item Description
     */
    public ArrayList<String> getDescription(){
        return this.description;
    }

    
    /** 
     * Return this class's price.
     * @return float Item price
     */
    public float getPrice(){
        return this.price;
    }

    
    /** 
     * Return this class's stock.
     * @return int Item stock
     */
    public int getStock(){
        return this.stock;
    }

    
    /** 
     * Set this class's name.
     * @param name Item name to be replaced
     */
    public void setName(String name){
        this.name = name;
    }

    
    /** 
     * Set this class's type.
     * @param type Item type to be replaced
     */
    public void setType(String type){
        this.type = type;
    }

    
    /** 
     * Set this class's description using a row from the csv file provided (with information of all Menu Items and Set)
     * @param descriptionString String describing Item description, extracted from a single row from csv file
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
     * Set this class's description using ArrayList that contains Strings (that describes the Item)
     * @param description ArrayList of String that describes the Item
     */
    public void setDescription(ArrayList<String> description){
        this.description = description;
    }

    
    /** 
     * Set this class's price.
     * @param price Item price
     */
    public void setPrice(float price){
        this.price = price;
    }

    
    /** 
     * Set this class's stock. 
     * @param stock Item stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }

    /**
     * Print this class's description.
     */
    public void printDescription(){
        for (int i=0; i<this.description.size(); i++){
            System.out.println(this.description.get(i));
        }
    }
}
