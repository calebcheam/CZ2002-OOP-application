package SalesReport_package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SaleReports class that contains collection of DailySaleReport objects and their respective dates.
 * Handles sales information related activities.
 * @see DailySaleReport
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class SaleReports {
    private ArrayList<DailySaleReport> salereports;
    private String[] dates; 

    /** 
     * SaleReports constructor, initialises collection of DailySaleReports and their dates by reading from csv
     * @see #readReportsFromCSV()
     */
    public SaleReports(){
        this.salereports = new ArrayList<DailySaleReport>(); 
        this.readReportsFromCSV();

        this.dates = new String[salereports.size()]; 
       for (int i=0; i<dates.length; i++){
           dates[i] = salereports.get(i).getDate(); 
       }
    }

    /** 
     * Displays sales reports for all days recorded
     */
    public void printAllReports(){
        
        for (DailySaleReport dailySaleReport : salereports) {
            dailySaleReport.printFromObject();
        }
    }

    
    /** 
     * Displays the sales report for a specified date
     * @param date String of date to be passed in, in the format "2021-11-03"
     */
    public void printOneReport(String date){
        for (DailySaleReport dsr : salereports) {
            if (dsr.getDate().equals(date)){
                dsr.printFromObject();
            }
        } 
    }

   
   /** 
    * Gets array of Strings of dates for all sales reports
    * @return String[] of dates of all sales reports made to date
    */
   public String[] getReportDates(){
    return this.dates; 
   }

   
   /** 
    * Displays sales reports within a specified time period, with the start and end inclusive
    * @param start int index of start date to be printed from
    * @param end int index of end date to be printed from
    */
   public void printSomeReports(int start, int end){
       String date = this.dates[start] + " to " + this.dates[end]; 
       double summedTotal = 0.0;
       double summedDiscount = 0.0; 
       Map<String, List<Double>> compiledMap = new HashMap<String, List<Double>>(); 

       for (int i = start; i<= end; i++){
           summedTotal += this.salereports.get(i).getDailyTotal();
           summedDiscount += this.salereports.get(i).getDiscountTotal();
           double additionalQty=0.0, additionalRevenue=0.0, compiledQty=0.0, compiledRevenue=0.0; 
           List<Double> list = new ArrayList<Double>(); 

           Map<String, List<Double>> csvMap = this.salereports.get(i).getMap();
           for (String key : csvMap.keySet()){ //iterating through the maps of saved reports from the CSV

            
               if (compiledMap.containsKey(key)){ //checking if compiled report has the key (menu item)
                compiledQty = compiledMap.get(key).get(0); //qty previously savedin compiled report
                compiledRevenue = compiledMap.get(key).get(1); //rev previously saved in compiled report
                additionalQty = csvMap.get(key).get(0); //qty from csv report
                additionalRevenue = csvMap.get(key).get(1);

                list.add(compiledQty+additionalQty);
                list.add(compiledRevenue+additionalRevenue);
                compiledMap.put(key, list);
            
               } else{
                   compiledMap.put(key, csvMap.get(key)); 
               }
           }
       }
       
       DailySaleReport compiledReport = new DailySaleReport(summedTotal, summedDiscount, compiledMap, date);
       compiledReport.printFromObject();
   }


   /** 
    * Reads sales reports stored in a csv file and updates overall sales reports to include new additions
    */
    private void readReportsFromCSV(){  //reads in all reports from the CSV file
        try{
            File file = new File("SALESREPORT.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            String date = "error: date not found";
              
            double dailyTotal, dailyDiscount; 
            dailyDiscount=0.0; dailyTotal=0.0; 


            while ((st = br.readLine()) != null){
                
                if (st.contains("Date")){ //read the line for Today's report
                    String[] parts = st.split(",");
                    date = parts[1]; 
                    st = br.readLine(); //skip over the "Item, Quantity, Revenue" header 
                    HashMap<String, List<Double>> map = new HashMap<String, List<Double>>();
                    
                    while (!(st=br.readLine()).contains("-"))
                    {
                
                        String[] csvRow = st.split(",");
                        //split one CSV row into its counter parts (Item, Quantity, Revenue)
                        Double quantity = Double.parseDouble(csvRow[1]); //convert to double
                        Double revenue = Double.parseDouble(csvRow[2]); 
                        List<Double> list = new ArrayList<Double>();
                        list.add(quantity);
                        list.add(revenue); 
                        map.put(csvRow[0], list);  
                      
                    }
                    
                    if (st.contains("-"))
                    {
                        st = br.readLine();
                        String [] totals = st.split(",");
                        dailyTotal = Double.parseDouble(totals[1]); 
                        st = br.readLine();
                        totals = st.split(",");
                        dailyDiscount = Double.parseDouble(totals[1]); 
                        
                        this.salereports.add(new DailySaleReport(dailyTotal, dailyDiscount, map, date));
                        
                    }
                } 

            }

    
        }catch(IOException ioe){
            System.out.println("Exception occurred:");
            ioe.printStackTrace();
       }
    }

    

    
}
