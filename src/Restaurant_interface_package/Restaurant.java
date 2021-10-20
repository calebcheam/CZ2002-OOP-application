package Restaurant_interface_package;

import java.util.Scanner;

import Reservation_package.Table;

public class Restaurant {
    private int numOfTables; 
    private Table[] tables; 

    public Restaurant(int numOfTables){
        //constructor -- this creates a restaurant from scratch
        //this means 0 reservations have been made, all tables empty, no sales made
        this.numOfTables = numOfTables; 
        tables = new Table[numOfTables];

    }

}

