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

                    if(this.restaurant.getTables()[i].getReservations()[j].getExpiry().isAfter(this.currentTime)) //expired
                    {

                        System.out.println("hi");

                    }

                }

            }
        }
    


        
{
    
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        
        e.printStackTrace();
    }
}
           } 
}

}
