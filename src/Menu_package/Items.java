public class Item {
    private String name;
    private String type;
    private String description;
    private float price;
    private int stock;

    public Item(String[] csvRow){
        //csvRow is list of String with all values in a single row in the csv file
        this.name = csvRow[0];
        this.type = csvRow[1];
        this.description = csvRow[2];
        this.price = Float.parseFloat(csvRow[3]);
        this.stock = Integer.parseInt(csvRow[4]);
    }
    public Item() {
    	
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }

    public String getDescription(){
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

    public void setDescription(String description){
        this.description = description;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setStock(int stock){
        this.stock = stock;
    }
}
