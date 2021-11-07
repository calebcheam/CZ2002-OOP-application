package Menu_package;
import java.util.ArrayList;

public class Item {
    private String name;
    private String type;
    private ArrayList<String> description;
    private float price;
    private int stock;

    public Item(String name, String type, ArrayList<String> description, float price, int stock){
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Item(){}

    public String toCSVString(){
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

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public ArrayList<String> getDescription(){
        return this.description;
    }

    public float getPrice(){
        return this.price;
    }

    public int getStock(){
        return this.stock;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

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

    public void setDescription(ArrayList<String> description){
        this.description = description;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public void printDescription(){
        for (int i=0; i<this.description.size(); i++){
            System.out.println(this.description.get(i));
        }
    }
}
