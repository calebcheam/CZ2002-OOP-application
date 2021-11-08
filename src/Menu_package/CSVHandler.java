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
                    System.out.println("I'VE FOUND MY FRIEND. Stop writing here!");
                    friendFound = true;
                    break;
                }
                pw.println(st);
            }
            
            br.close();
            bw.close();
            pw.close();
            if (!friendFound){
                System.out.println("FRIEND NOT FOUND :(");
                return -1;
            }
            return 1; //successful
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       } 
        return -1; 
    }

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
                    System.out.println("I'VE FOUND MY FRIEND. Start writing here!");
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
                System.out.println("I never found my friend...");
                return -1;
            }
            return 1; 
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       } 
       return -1; 
            
    }

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


    public void removeItemFromCSV(String path, String lineToDelete){
        int w = this.saveItemsBefore(path, lineToDelete); //copy original contents to temp csv up to the specific line
        int x = this.saveItemsAfter(path, lineToDelete); //copy remaining contents to temp csv from to the specific line

        int y = this.overwriteCSV(path); //rewrite the first part from copied csv
        int z = this.appendToCSV(path, lineToDelete); //skips over the line to delete, then appends the rest
        if (w + x + y + z ==4) {
            System.out.println("Item removed successfully! ");
        } else System.out.println("ERROR : Item was not removed successfully.");

    }
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
            System.out.println("Item addded successfully! This is what we added : " + newItemString);
        } else System.out.println("Item not added successfully.");
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
