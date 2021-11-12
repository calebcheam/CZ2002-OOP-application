package Restaurant_package;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 
 * <code>Refresh</code> thread that runs separately from main app, periodically fetching system time
 * @author DSAI1 ASSIGNMENT GROUP 3
 * @version 1.0
 */

public class Refresh implements Runnable{

    LocalDateTime currentTime=null;

    
    /** 
     * <code>Refresh</code> constructor
     */
    public Refresh()
    {
        
    }

    /** 
     * Runs the <code>Refresh</code> thread, fetches system time and compares it to reservation expiry times every 15 seconds.
     * If system time exceeds reservation expiry by 10 or more minutes, the reservation is removed.
     * This only applies for "no-show" reservations, where no <code>Order</code> is created within the 10 minute period.
     * @see Order_package.Order
     */ 
    public void run(){
        while(true)
    {

        this.currentTime=LocalDateTime.now();
    
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(Restaurant.getTables()[i].getReservations()[j]!=null)
                {

                    //check if the reservation has expired
                    LocalDateTime reservationDeadline = Restaurant.getTables()[i].getReservations()[j].getExpiry();
                    if(this.currentTime.isAfter(reservationDeadline)) //expired
                    {
                        if(Restaurant.getTables()[i].getReservations()[j].getOrder()==null)
                        {
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm"); 
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t << Time : " + this.currentTime.format(format)+ " >>");
                            System.out.println("A customer reservation has just expired!");

                            //System.out.printf("\t\t\t\t\t\t\t AHA! U R PAST UR DEADLINE %s IM GNA RMEMOVE U\n", reservationDeadline);
                            Restaurant.getTables()[i].removeReservationAtTime(j);
                        } 
                        // else {
                        //     System.out.println("Customer showed up and ordered!");
                        // }
                    // } else {
                    //     System.out.println("Awaiting customer.. ");

                    }

                }

            }
        }
    

{
    
    try {
        Thread.sleep(15000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
           } 
}

}
