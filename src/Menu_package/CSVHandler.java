package Menu_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class handles all proccesses that relating to the csv file provided (with information of all Menu Items and Set)
 * This class reads and creates Items objects from the csv file. It also edits the csv file itself when Items needs to be added or deleted permanently.
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */
public class CSVHandler {

    /**
     * Constructor for this class
     */
    public CSVHandler(){};

    
    /** 
     * Creates and returns Item object, with attributes information extracted from a single row, from the csv file provided (with information of all Menu Items and Set)
     * @param csvLine String representing the single row in the csv file, where values are separated by commas
     * @return Item New Item object, created using the single row in the csv file
     */
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

    
    /** 
     * Creates a new (temporary) csv file to temporarily copy rows of the csv file (provided by the path in the parameter).
     * It will copy from the first row to the row, before a row containing a certain String (specified in parameter)
     * @param path Path to csv file to copy from
     * @param friendLine String, that indicates where to stop copying the csv file's row 
     * @return int Return 1 if success, -1 if failure
     */
    private int saveItemsBefore(String path, String friendLine){
        //this saves the contents of the CSV before the line we want to stop at 
        try{
            File file = new File(path);
            File tempBefore = new File("TempBefore.csv");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;

            FileWriter fw = new FileWriter(tempBefore,false); // false -- overwrite the file
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            boolean friendFound = false;
            while ((st = br.readLine()) != null){
                if (st.contains(friendLine)){
                    //System.out.println("I'VE FOUND MY FRIEND. Stop writing here!");
                    friendFound = true;
                    break;
                }
                pw.println(st);
            }
            
            br.close();
            bw.close();
            pw.close();
            if (!friendFound){
                //System.out.println("FRIEND NOT FOUND :(");
                return -1;
            }
            return 1; //successful
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       } 
        return -1; 
    }

    
    /** 
     * Creates a new (temporary) csv file to temporarily copy rows of the csv file (provided by the path in the parameter).
     * It will copy from a row containing a certain String (specified in parameter), to the last row of the csv file.
     * @param path Path to csv file to copy from
     * @param friendLine String, that indicates where to start copying the csv file's row from
     * @return int Return 1 if success, -1 if failure
     */
    private int saveItemsAfter(String path, String friendLine){
        //this saves the contents of the CSV before the line we want to stop at 
        try{
            File file = new File(path);
            File tempBefore = new File("TempAfter.csv");

            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;

            FileWriter fw = new FileWriter(tempBefore,false); // false -- overwrite the file
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            boolean friendFound=false;
            while ((st = br.readLine()) != null){
                
                if (st.contains(friendLine)){
                    //System.out.println("I'VE FOUND MY FRIEND. Start writing here!");
                    friendFound = true;
                }
                if (friendFound){
                    //System.out.println(st);
                    pw.println(st);
                }
                
            }
            br.close();
            bw.close();
            pw.close();
            if (friendFound==false){
                //System.out.println("I never found my friend...");
                return -1;
            }
            return 1; 
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       } 
       return -1; 
            
    }

    
    /**
     * Overwrite the contents of a csv file (specified by its path in the parameter) by contents of "TempBefore.csv" (the csv file created by saveItemsBefore() method).
     * @link #saveItemsBefore
     * @param filetoWritepath csv file to overwrite
     * @return int Return 1 if success, -1 if failure
     */
    private int overwriteCSV(String filetoWritepath){
        try{
            File fileToRead = new File("TempBefore.csv");
            File fileToWrite = new File(filetoWritepath);

            BufferedReader br = new BufferedReader(new FileReader(fileToRead));
            String st;

            FileWriter fw = new FileWriter(fileToWrite,false); 
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            while ((st = br.readLine()) != null){
                pw.println(st);
            }
            
            br.close();
            bw.close();
            pw.close();
            return 1;
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       } 
       return -1;
    }

    
    /** 
     * Append contents (excluding first line) of "TempAfter.csv" (the csv file created by saveItemsAfter() method), to the csv file (specified by path of the file in the parameter).
     * @link #saveItemsAfter
     * @param fileToWritepath csv file, that will be appended with contents of "TempAfter.csv"  
     * @param lineToDelete
     * @return int Return 1 if success, -1 if failure
     */
    private int appendToCSV(String fileToWritepath, String lineToDelete){
        // skips over the line to delete
        // then appends the rest as per normal
        try{
            File fileToRead = new File("TempAfter.csv");
            File fileToWrite = new File(fileToWritepath);

            BufferedReader br = new BufferedReader(new FileReader(fileToRead));
            String st;

            FileWriter fw = new FileWriter(fileToWrite,true);  //append
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            br.readLine(); //skip over the line to delete
            
            
            while ((st = br.readLine()) != null){
                pw.println(st);
            }
            
            br.close();
            bw.close();
            pw.close();
            return 1;
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       } 
       return -1;
    }


    
    /**
     * Appends a (String) line (specified in the parameter), followed by the contents of "TempAfter.csv" (the csv file created by saveItemsAfter() method),
     * to the csv file (specified by path of the file in the parameter).
     * @param fileToWritepath csv file, that will be appended with the (String) line and the contents of "TempAfter.csv"
     * @param newLine (String) line to append to the csv file first
     * @return int Return 1 if success, -1 if failure
     */
    private int appendNewLineToCSV(String fileToWritepath, String newLine){
        try{
            File fileToRead = new File("TempAfter.csv");
            File fileToWrite = new File(fileToWritepath);

            BufferedReader br = new BufferedReader(new FileReader(fileToRead));
            String st;

            FileWriter fw = new FileWriter(fileToWrite,true);  //append
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            boolean friendPrinted = false;
            
            while ((st = br.readLine()) != null){

                if (friendPrinted==false){
                    pw.println(newLine);
                    friendPrinted=true;
                }
                pw.println(st);
            }
            
            br.close();
            bw.close();
            pw.close();
            return 1;
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       } 
       return -1;
    }


    
    /** 
     * Remove an Item (by its name) from the csv file (that contains information of all Menu Items and Set),
     * by removing the line that contains the Item's name (specified by the parameter).
     * @param path Path to the csv file that contains information of all Menu Items and Set.
     * @param lineToDelete Item name of Item to be deleted.
     */
    public void removeItemFromCSV(String path, String lineToDelete){
        int w = this.saveItemsBefore(path, lineToDelete); //copy original contents to temp csv up to the specific line
        int x = this.saveItemsAfter(path, lineToDelete); //copy remaining contents to temp csv from to the specific line

        int y = this.overwriteCSV(path); //rewrite the first part from copied csv
        int z = this.appendToCSV(path, lineToDelete); //skips over the line to delete, then appends the rest
        if (w + x + y + z ==4) { //if all operation above success
            System.out.println("Item removed successfully! ");
        } 
        // else {
        //     System.out.println("ERROR : Item was not removed successfully.");
        // }

    }
    
    /** 
     * Add an Item into the csv file (that contains information of all Menu Items and Set),
     * where it will be added above the first Item, that has the same Item Type as the Item to be added.
     * @param item Item to be added
     * @param path Path to the csv file that contains information of all Menu Items and Set
     * @param friendLine Item name of the first Item, that has the same Item Type as the Item to be added.
     */
    public void addItemToCSV(Item item, String path, String friendLine){
  
        int w = this.saveItemsBefore(path, friendLine); //copy original contents to temp csv up to the specific line
        int x = this.saveItemsAfter(path, friendLine); //copy remaining contents to temp csv from to the specific line

        int y = this.overwriteCSV(path); //rewrite the first part from copied csv

        String newItemString; 
        if (item.getType()=="Set"){
            newItemString = item.SetToString();
        } else {
            newItemString = item.AlaCarteToCSVString();
        }
        int z = this.appendNewLineToCSV(path, newItemString); //add new line and append remaining parts from copied csv

        if (w + x + y + z ==4) {
            System.out.println("Item added successfully!");
        } else System.out.println("Item not added successfully.");
    }

    
    /** 
     * Creates and returns ArrayList of String, where each String represents a part of an Item description.
     *
     * The ArrayList is created by splitting the String that contains the description of the Item.
     * If the description comes from a Item under the Set category, the description String is splitted into 7 parts. 
     * 1st to 6th parts are Item name and description from Appetiser, Main Course, Dessert respectively,
     * and 7th part is the Item name of drink Item. There is no description for Items in Drink category as its name is self-explanatory.
     * Hence, ArrayList size for an Item under Set Category is always fixed.
     *
     * If the description comes from a Item not under the Set category, the description String is splitted by " + ".
     * Unlike the Item under the Set Category, the description ArraySize of the Item is not fixed. It can have any size, i.e. description can have 6 parts, 1 parts, etc
     *
     * @param descriptionString String that contains the description of the Item
     * @param type Item Type of the described Item
     * @return ArrayList<String> ArrayList of String, where each String represents a part of an Item description.
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

}
