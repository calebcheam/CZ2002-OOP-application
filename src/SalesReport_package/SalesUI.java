package SalesReport_package;

import java.util.ArrayList;
import java.util.Scanner;

import Restaurant_interface_package.Restaurant;

public class SalesUI {
    protected Restaurant restaurant;
    private DailySaleReport dsr; 
    private SaleReports SR; 
    private String[] dates; 

    ArrayList<String> options=new ArrayList<>();
    Scanner sc=new Scanner(System.in);

    public SalesUI(Restaurant res)
    {
        this.restaurant=res;
    
        options.add("View Today's Sales Report");
        options.add("Save Today's Report to Records");
        options.add("View previous All Sales Reports separately");
        options.add("View previous Sales Report (One day)"); 
        options.add("View previous Sales Report (All days summed up)");
        options.add("View previous Sales Report (Over a period)");
        
    }
    public void start()
    {
        int choice;
        this.dsr =  new DailySaleReport(this.restaurant);
        this.SR = new SaleReports(); 
        this.dates = this.SR.getReportDates();
       
        do {
            
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
                    System.out.println("\n\nThis is how the current sales report looks like... Do you wish to save this to our records? (Y/N)");
                    System.out.println("*****NOTE : Please only do this when the restaurant is closing for the day.*****");
                    if (sc.next().charAt(0)=='Y')
                    {
                        dsr.writeReportToCSV();
                        this.refreshSR();
                    } else System.out.println("Did not save to records");
                    break;
                case 3 : 
                    this.SR.printAllReports();
                    break;
                case 4:
                    System.out.println("Which date would you like to view?");
                    this.printDates(0);
                    int dateSelected = sc.nextInt(); 
                    this.SR.printOneReport(dates[dateSelected-1]);
                    break;
                case 5 : 
                    this.SR.printSomeReports(0, this.dates.length-1);
                    break;
                case 6: 
                    System.out.println("Select starting date:");
                    this.printDates(0);
                    int dateStart = sc.nextInt();
                    
                    int dateEnd;
                    if (dateStart < this.dates.length){
                        System.out.println("Select ending date:");
                        this.printDates(dateStart);
                        dateEnd = sc.nextInt();
                    } else dateEnd = dateStart; 
                    
                    this.SR.printSomeReports(dateStart-1, dateEnd-1);
                default:
                    
                    break;
            } 
            
        } while (choice!=-1); 
    }

    private void printDates(int startIndex){
        if (startIndex==this.dates.length-1) return;
        for (int i=startIndex; i<this.dates.length; i++){
            System.out.println((i+1) + " : " + dates[i]);
        }
    }

    private void refreshSR(){
        this.SR = new SaleReports(); 
        this.dates = this.SR.getReportDates();
    }
}
