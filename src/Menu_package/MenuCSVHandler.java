package Menu_package;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MenuCSVHandler implements CSVLocatable {

    @Override
    public void readFromCSV() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeToCSVLocation(String path, String friendLine, String newItemString) {
        //adds a new Item to the Menu by writing to specific location in the CSV
        int w = this.copyCSVUntilLine(path, friendLine); //copy original contents to temp csv up to the specific line
        int x = this.copyCSVfromLine(path, friendLine); //copy remaining contents to temp csv from to the specific line

        int y = this.overwriteCSV(path); //rewrite the first part from copied csv

        int z = this.appendNewLineToCSV(path, newItemString); //add new line and append remaining parts from copied csv

        if (w + x + y + z ==4) {
            System.out.println("Item added successfully!");
        } else System.out.println("Item not added successfully.");
        
    }

    @Override
    public void removeFromCSVLocation(String path, String lineToDelete) {
        int w = this.copyCSVUntilLine(path, lineToDelete); //copy original contents to temp csv up to the specific line
        int x = this.copyCSVfromLine(path, lineToDelete); //copy remaining contents to temp csv from to the specific line

        int y = this.overwriteCSV(path); //rewrite the first part from copied csv
        int z = this.appendAfterLine(path, lineToDelete); //skips over the line to delete, then appends the rest
        if (w + x + y + z ==4) { //if all operation above success
            System.out.println("Item removed successfully! ");
        } 
        
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //      from here onwards it is private methods that help to carry out the above functions 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    





    private int copyCSVUntilLine(String path, String friendLine){
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
     * It will copy from a row containing a certain <code>String</code> (specified in parameter), to the last row of the csv file.
     * @param path Path to csv file to copy from
     * @param friendLine <code>String</code>, that indicates where to start copying the csv file's row from
     * @return int Return 1 if success, -1 if failure
     */
    private int copyCSVfromLine(String path, String friendLine){
        //this saves the contents of the CSV from the line we want to start
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
    private int appendAfterLine(String fileToWritepath, String lineToDelete){
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

    

   
}
