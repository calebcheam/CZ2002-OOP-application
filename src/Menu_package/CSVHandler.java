package Menu_package;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CSVHandler {

    public CSVHandler(){};

    public Item createItem(String csvLine){
        //Create an Item object using the csv values
        String[] itemAttributes = csvLine.split(",");//get the csv row value into String array
        String name = itemAttributes[0];
        String type = itemAttributes[1];
        ArrayList<String> description = splitDescription(itemAttributes[2], type);
        float price = Float.parseFloat(itemAttributes[3]);
        int stock = Integer.parseInt(itemAttributes[4]);
        Item item = new Item(name, type, description, price, stock);
        return item;
    }

    public void addItemToCSV(Item item, String path){
        try{
            File file =new File(path);
            
            
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.print(item.toCSVString());
            pw.close();
  
        System.out.println("Successfully added new Item to the CSV! This is what we added : " + item.toCSVString());
  
         }catch(IOException ioe){
             System.out.println("Exception occurred:");
             ioe.printStackTrace();
        }
    }

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

}
