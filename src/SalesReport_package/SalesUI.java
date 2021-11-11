package SalesReport_package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class SalesUI {
    
    private static DailySaleReport dsr=new DailySaleReport();
    private static SaleReports SR=new SaleReports(); 
    private static String[] dates=SR.getReportDates();

    private static ArrayList<String> options=new ArrayList<>(Arrays.asList("View Today's Sales Report","Save Today's Report to Records","View previous All Sales Reports separately","View previous Sales Report (One day)","View previous Sales Report (All days summed up)","View previous Sales Report (Over a period)"));
    private static Scanner sc=new Scanner(System.in);

    public static void start()
    {
        int choice;
        
        
    
        do {
            dsr=new DailySaleReport();
            SR=new SaleReports();
            System.out.println("===============================================");
	        System.out.println("|              Sale Report Options            |");
            System.out.println("|              (Enter -1 to Exit)             |");
	        System.out.println("===============================================");
            int i=0; 
            for (String option : options) {
                i+=1; 
                System.out.println(i + " : " + option); 
            }
            System.out.println("===============================================");
            
            choice = sc.nextInt(); 

            switch(choice){
                 
                case 1:
                    dsr.printFromObject();
                    break; 
                case 2:
                    dsr.printFromObject();
                    System.out.println("\n\nThis is how the current sales report looks like... Do you wish to save this to our records?");
                    System.out.println("(1) Yes (2) No");
                    System.out.println("*****NOTE : Please only do this when the restaurant is closing for the day.*****");
                    if (sc.nextInt()==1)
                    {
                        dsr.writeReportToCSV();
                        refreshSR();
                    } else System.out.println("Did not save to records");
                    break;
                case 3 : 
                    SR.printAllReports();
                    break;
                case 4:
                    System.out.println("Which date would you like to view?");
                    printDates(0);
                    int dateSelected = sc.nextInt(); 
                    SR.printOneReport(dates[dateSelected-1]);
                    break;
                case 5 : 
                    SR.printSomeReports(0, dates.length-1);
                    break;
                case 6: 
                    System.out.println("Select starting date:");
                    printDates(0);
                    int dateStart = sc.nextInt();
                    
                    int dateEnd;
                    if (dateStart < dates.length){
                        System.out.println("Select ending date:");
                        printDates(dateStart);
                        dateEnd = sc.nextInt();
                    } else dateEnd = dateStart; 
                    
                    SR.printSomeReports(dateStart-1, dateEnd-1);
                default:
                    
                    break;
            } 
            
        } while (choice!=-1); 
    }

    private static void printDates(int startIndex){
        if (startIndex==dates.length-1) return;
        for (int i=startIndex; i<dates.length; i++){
            System.out.println((i+1) + " : " + dates[i]);
        }
    }

    private static void refreshSR(){
        SR = new SaleReports(); 
        dates = SR.getReportDates();
    }
}
