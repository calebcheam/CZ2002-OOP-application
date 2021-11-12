package SalesReport_package;

import java.util.Scanner;

public class SalesManager {
    private static DailySaleReport dsr=new DailySaleReport();
    private static SaleReports SR=new SaleReports(); 
    private static Scanner sc=new Scanner(System.in);

    public static void viewTodaySales(){
        dsr = new DailySaleReport();
        dsr.printFromObject();
    }

    public static void saveTodaySales(){
        dsr = new DailySaleReport();
        dsr.printFromObject();

        System.out.println("\n\nThis is how the current sales report looks like... Do you wish to save this to our records?");
        System.out.println("(1) Yes (2) No");
        System.out.println("*****NOTE : Please only do this when the restaurant is closing for the day.*****");
        if (sc.nextInt()==1)
        {
            dsr.writeReportToCSV();
            //refreshSR();
        } else System.out.println("Did not save to records");

    }

    public static void viewAllSaleReport(){
        SR = new SaleReports();
        SR.printAllReports();
    }

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

    public static void viewOneSaleReport(){
        SR = new SaleReports();
        String[] datesArray = SR.getReportDates();
        System.out.println("Which date would you like to view?");
        printDates(datesArray, 0);
        int dateSelected = sc.nextInt(); 
        SR.printOneReport(datesArray[dateSelected-1]);

    }
    
    /** 
     * Displays the dates of each sales report
     * @param startIndex
     */
    private static void printDates(String[] dates, int startIndex){
        if (startIndex==dates.length-1) return;
        for (int i=startIndex; i<dates.length; i++){
            System.out.println((i+1) + " : " + dates[i]);
        }
    }

}
