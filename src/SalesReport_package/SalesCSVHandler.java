package SalesReport_package;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SalesCSVHandler implements CSVAppendable{
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
