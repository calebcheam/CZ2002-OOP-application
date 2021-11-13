package SalesReport_package;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/** 
 * <code>SalesCSVHandler</code> class that implements <code>CSVAppendable</code> interface and handles updating the overall sales data with sales data per day
 * @see CSVAppendable
 */
public class SalesCSVHandler implements CSVAppendable{
    
    /** 
     * Writes the contents of an <code>ArrayList</code> to the end of a CSV file
     * @param lines the <code>ArrayList</code> containing the strings to be written to the CSV
     */
    //implements CSV Appendable since we will always add to the end of CSV

    @Override
    public void writeToCSVEnd(ArrayList<String> lines) {
        try{
            File file =new File("SALESREPORT.csv");
            
            if(!file.exists()){
               file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (String line : lines){
                pw.println(line);
            }
            pw.close();
  
        System.out.println("Successfully added today's report to the CSV!");
         }catch(IOException ioe){
             System.out.println("Exception occurred:");
             ioe.printStackTrace();
        }
    }
    

    
    
}
