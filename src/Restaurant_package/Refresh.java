package Restaurant_package;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Refresh implements Runnable{

    LocalDateTime currentTime=null;

    Restaurant restaurant=null;

    public Refresh(Restaurant restaurant)
    {
        this.restaurant=restaurant;
        this.currentTime = null;
    }

 
    public void run(){
        while(true)
    {

        this.currentTime=LocalDateTime.now();
    
        for(int i=0;i<10;i++)
        {
            for(int j=0;j<6;j++)
            {
                if(this.restaurant.getTables()[i].getReservations()[j]!=null)
                {

                    //check if the reservation has expired
                    LocalDateTime reservationDeadline = this.restaurant.getTables()[i].getReservations()[j].getExpiry();
                    // give the fella 20 minutes buffer time
                    reservationDeadline = reservationDeadline.plusMinutes(20);

                    if(this.currentTime.isAfter(reservationDeadline)) //expired
                    {
                        if(this.restaurant.getTables()[i].getReservations()[j].getOrder()==null)
                        {
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm"); 
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t << Time : " + this.currentTime.format(format)+ " >>");
                            System.out.println("A customer reservation has just expired!\n");

                            //System.out.printf("\t\t\t\t\t\t\t AHA! U R PAST UR DEADLINE %s IM GNA RMEMOVE U\n", reservationDeadline);
                            this.restaurant.getTables()[i].removeReservationAtTime(j);
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