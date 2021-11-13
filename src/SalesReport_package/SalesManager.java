package SalesReport_package;

import java.util.Scanner;

/**
 * <code>SalesManager</code> class that handles all sales related activities, including storing and fetching sales data
 * @see DailySaleReport
 * @see SaleReports
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class SalesManager {
    private static DailySaleReport dsr=new DailySaleReport();
    private static SaleReports SR=new SaleReports(); 
    private static Scanner sc=new Scanner(System.in);

    private static SalesCSVHandler salesCSVHandler = new SalesCSVHandler();

    /** 
     * Displays the current day's sales report
     */
    public static void viewTodaySales(){
        dsr = new DailySaleReport();
        dsr.printDSR();
    }

    /** 
     * Saves the current day's sales report to a csv
     */
    public static void saveTodaySales(){
        dsr = new DailySaleReport();
        dsr.printDSR();

        System.out.println("\n\nThis is how the current sales report looks like... Do you wish to save this to our records?");
        System.out.println("(1) Yes (2) No");
        System.out.println("*****NOTE : Please only do this when the restaurant is closing for the day.*****");
        if (sc.nextInt()==1)
        {
            //dsr.writeReportToCSV();
            salesCSVHandler.writeToCSVEnd(dsr.DSRtoStrings());

            //refreshSR();
        } else System.out.println("Did not save to records");

    }

    /** 
     * Displays all sales reports recorded to date
     */
    public static void viewAllSaleReport(){
        SR = new SaleReports();
        SR.printAllReports();
    }

    
    
    /** 
     * Displays sales from a selected date if <code>boolean</code> value of <code>true</code> is passed in as parameters.
     * Else, prompts user to select a time period and displays sales over that period
     * @param viewAll <code>boolean</code> true views all sales, false enables time period selection
     */
    public static void viewAccumulatedSales(boolean viewAll){
        
        SR = new SaleReports();
        String[] datesArray = SR.getReportDates();

        if (viewAll==true){
            SR.printCompiledReport(0, datesArray.length-1);
        } else{

            System.out.println("Select starting date:");
            printDates(datesArray, 0);
                int dateStart = sc.nextInt();
                    
                    int dateEnd;
                    if (dateStart < datesArray.length){
                        System.out.println("Select ending date:");
                        printDates(datesArray, dateStart);
                        dateEnd = sc.nextInt();
                    } else dateEnd = dateStart; 
                    
                    SR.printCompiledReport(dateStart-1, dateEnd-1);


        }
       
                    
        
        
    }

    /** 
     * Displays the sales report of a single selected day
     */
    public static void viewOneSaleReport(){
        SR = new SaleReports();
        String[] datesArray = SR.getReportDates();
        System.out.println("Which date would you like to view?");
        printDates(datesArray, 0);
        int dateSelected = sc.nextInt(); 
        SR.printOneReport(datesArray[dateSelected-1]);

    }
    

    
    /** 
     * Displays the dates of each sales report consecutively
     * @param dates <code>String[]</code> containing dates to be passed in
     * @param startIndex <code>int</code> index of starting date to be printed from
     */
    private static void printDates(String[] dates, int startIndex){
        if (startIndex==dates.length-1) return;
        for (int i=startIndex; i<dates.length; i++){
            System.out.println((i+1) + " : " + dates[i]);
        }
    }

}
