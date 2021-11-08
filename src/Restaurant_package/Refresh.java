package Restaurant_package;


import java.time.LocalDateTime;

public class Refresh implements Runnable{

    LocalDateTime currentTime=null;
    Restaurant restaurant=null;

    public Refresh(Restaurant restaurant)
    {
        this.restaurant=restaurant;
    }

    public void run(){
        while(true)
    {
        System.out.println("\nRefresher is running...");
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
                        System.out.println("Reservation deadline: " + reservationDeadline);
                        if(this.restaurant.getTables()[i].getReservations()[j].getOrder()==null)
                        {
                            System.out.println("AHA! IM GNA RMEMOVE U");
                        } else {
                            System.out.println("Customer showed up and ordered!");
                        }
                    // } else {
                    //     System.out.println("Awaiting customer.. ");
                    }

                }

            }
        }
    

{
    
    try {
        Thread.sleep(10000);
    } catch (InterruptedException e) {
        
        e.printStackTrace();
    }
}
           } 
}

}
