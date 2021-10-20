package Restaurant_interface_package;

import Reservation_package.Table;

public class Restaurant {
    private int numOfTables; 
    private Table[] tables; 
    private String[] timeslots; 

    public Restaurant(){
        //constructor -- this creates a restaurant from scratch
        //this means 0 reservations have been made, all tables empty, no sales made
        this.numOfTables = 10; 
        tables = new Table[numOfTables];
        for (int i=0; i<10; i++){
            tables[i] = new Table(i+1, 2); 
        }
        this.timeslots = new String[] {"10:00 - 12:00", "12:00 - 14:00", "14:00 - 16:00", "16:00 - 18:00", "18:00 - 20:00", "20:00-22:00"};
    }

    public void printTimeSlots(){
        System.out.println("-------Timeslots------");
        for (int i=0; i<6; i++) {
            System.out.printf("( %d )", i+1);
            System.out.print(timeslots[i] + "\n");
        }
    }

    public void printTableAvailabilityByTime(int time){
        for (Table table : tables) {
            System.out.println(table.getReservations()[time-1]);
        }
    }

    public void printTableAvailabilityByTable(int table){
        tables[table-1].printReservations();;
    }


}

