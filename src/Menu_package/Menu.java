package Menu_package;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    private ArrayList<Item> mainCourseItems;
    private ArrayList<Item> dessertItems;
    private ArrayList<Item> drinkItems;
    private ArrayList<Item> setItems;
    private final String[] mainCourseTypes = {"Appetiser", "Salad", "Soup", "Vegetarian",
            "Local Favourites", "Meat and Seafood", "Pasta", "Pizza", "Burger & Sandwich", "Set"};
    private final String[] dessertTypes = {"Dessert"};
    private final String[] drinkTypes = {"Fizzy Drinks", "Juices", "Tea", "Coffee"};
    private final String[] setTypes = {"Set"};

    //Read and Create arrays of menuItems
    public Menu(){
        ArrayList<String> mainCourseTypesList = new ArrayList<String>(Arrays.asList(mainCourseTypes));
        ArrayList<String> dessertTypesList = new ArrayList<String>(Arrays.asList(dessertTypes));
        ArrayList<String> drinkTypesList = new ArrayList<String>(Arrays.asList(drinkTypes));
        ArrayList<String> setTypesList = new ArrayList<String>(Arrays.asList(setTypes));

        mainCourseItems = new ArrayList<Item>();
        dessertItems = new ArrayList<Item>();
        drinkItems = new ArrayList<Item>();
        setItems = new ArrayList<Item>();

        String csvPath = "MenuItem.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            br.readLine();//read first line and ignore as first line is header

            while ((line = br.readLine()) != null){
                String[] csvRow = line.split(",");//get the csv row value into String array

                Item menuItem = new Item(csvRow);//create Item object

                //extract the type of the current row's Menu Item Type
                String itemType = csvRow[1];

                //add menuItem to the items list (eg. mainCourseItems) according to its type
                if (mainCourseTypesList.contains(itemType)){
                    mainCourseItems.add(menuItem);
                }
                else if (dessertTypesList.contains(itemType)){
                    dessertItems.add(menuItem);
                }
                else if (drinkTypesList.contains(itemType)){
                    drinkItems.add(menuItem);
                }
                else if (setTypesList.contains(itemType)) {
                	setItems.add(menuItem);
                }
                else{
                    throw new MenuItemTypeInvalid(itemType);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MenuItemTypeInvalid e) {
            System.out.println(e.getMessage());
        }
    }
    public void add() {
    	Scanner sc = new Scanner(System.in);
    	Item newitem = new Item();
    	System.out.println("Name of new item?: ");
    	newitem.setName(sc.nextLine());
    	System.out.println("Type of new item?: ");
    	newitem.setType(sc.nextLine());
    	System.out.println("Description of new item?: ");
    	newitem.setDescription(sc.nextLine());
    	System.out.println("Price of new item?: ");
    	newitem.setPrice(sc.nextFloat());
    	System.out.println("Amount of stock of new item?: ");
    	newitem.setStock(sc.nextInt());    	
    }
    public void remove(int number, int type) {
    	int index = number-1;
    	if (type == 1) {
    		mainCourseItems.remove(index);    		
    	}
    	if (type == 2) {
    		dessertItems.remove(index);
    	}
    	if (type == 3) {
    		drinkItems.remove(index);
    	}
    	if (type == 4) {
    		setItems.remove(index);
    	}
    }
    public void update(int number, int type) {
    	int choice;
    	do {
    		System.out.println("Update choice?");
        	System.out.println("Name(1)");
        	System.out.println("Type(2)");
        	System.out.println("Description(3)");
        	System.out.println("Price(4)");
        	System.out.println("Stock(5)");
        	Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
        	int index = number-1;
        	switch (choice) {
        	case 1:
    	    	 if (type == 1) {
    	    		 System.out.println("Enter new name:");
    	    		 this.mainCourseItems.get(index).setName(sc.nextLine());
    	    	 }
    	    	 if (type == 2) {
    	    		 System.out.println("Enter new name:");
    	    		 this.dessertItems.get(index).setName(sc.nextLine());
    	    	 }
    	    	 if (type == 3) {
    	    		 System.out.println("Enter new name:");
    	    		 this.drinkItems.get(index).setName(sc.nextLine());
    	    	 }
    	    	 if (type == 4) {
    	    		 System.out.println("Enter new name:");
    	    		 this.setItems.get(index).setName(sc.nextLine());
    	    	 }
    		 break;
    		 case 2:
    			 if (type == 1) {
    	    		 System.out.println("Enter new type:");
    	    		 this.mainCourseItems.get(index).setType(sc.nextLine());
    	    	 }
    	    	 if (type == 2) {
    	    		 System.out.println("Enter new type:");
    	    		 this.dessertItems.get(index).setType(sc.nextLine());
    	    	 }
    	    	 if (type == 3) {
    	    		 System.out.println("Enter new type:");
    	    		 this.drinkItems.get(index).setType(sc.nextLine());
    	    	 }
    	    	 if (type == 4) {
    	    		 System.out.println("Enter new type:");
    	    		 this.setItems.get(index).setType(sc.nextLine());
    	    	 }				 
    		 break;
    		 case 3:
    			 if (type == 1) {
    	    		 System.out.println("Enter new description:");
    	    		 this.mainCourseItems.get(index).setDescription(sc.nextLine());
    	    	 }
    	    	 if (type == 2) {
    	    		 System.out.println("Enter new description:");
    	    		 this.dessertItems.get(index).setDescription(sc.nextLine());
    	    	 }
    	    	 if (type == 3) {
    	    		 System.out.println("Enter new description:");
    	    		 this.drinkItems.get(index).setDescription(sc.nextLine());
    	    	 }
    	    	 if (type == 4) {
    	    		 System.out.println("Enter new description:");
    	    		 this.setItems.get(index).setDescription(sc.nextLine());
    	    	 }				 
    		 break;
    		 case 4: 
    			 if (type == 1) {
    	    		 System.out.println("Enter new price:");
    	    		 this.mainCourseItems.get(index).setPrice(sc.nextFloat());
    	    	 }
    	    	 if (type == 2) {
    	    		 System.out.println("Enter new price:");
    	    		 this.dessertItems.get(index).setPrice(sc.nextFloat());
    	    	 }
    	    	 if (type == 3) {
    	    		 System.out.println("Enter new price:");
    	    		 this.drinkItems.get(index).setPrice(sc.nextFloat());
    	    	 }
    	    	 if (type == 4) {
    	    		 System.out.println("Enter new price:");
    	    		 this.setItems.get(index).setPrice(sc.nextFloat());
    	    	 }				 
    		 break;
    		 case 5: 
    			 if (type == 1) {
    	    		 System.out.println("Enter new stock:");
    	    		 this.mainCourseItems.get(index).setStock(sc.nextInt());
    	    	 }
    	    	 if (type == 2) {
    	    		 System.out.println("Enter new stock:");
    	    		 this.dessertItems.get(index).setPrice(sc.nextInt());
    	    	 }
    	    	 if (type == 3) {
    	    		 System.out.println("Enter new stock:");
    	    		 this.drinkItems.get(index).setPrice(sc.nextInt());
    	    	 }
    	    	 if (type == 4) {
    	    		 System.out.println("Enter new stock:");
    	    		 this.setItems.get(index).setPrice(sc.nextInt());
    	    	 }				 
    		 break;
    		 case 6: 
    			 System.out.println("Update Complete");
        	}
    	}while (choice < 6);
}

    public void testPrint(){
        //this is just to check if the class is working properly, not for the project
        int number = 1;
        for (int i=0; i<this.mainCourseItems.size(); i++){
            System.out.println(number);
            System.out.println(this.mainCourseItems.get(i).getName());
            number++;
        }
        for (int i=0; i<this.dessertItems.size(); i++){
            System.out.println(number);
            System.out.println(this.dessertItems.get(i).getName());
            number++;
        }
        for (int i=0; i<this.drinkItems.size(); i++){
            System.out.println(number);
            System.out.println(this.drinkItems.get(i).getName());
            number++;
        }
    }
    
    public void displaymenu() {
    	String name;
    	System.out.println("============================");
        System.out.println("|           MENU           |");
        System.out.println("============================");
        int i = 0,j;
        String type;
        for (j=0;j<this.mainCourseItems.size();j++) {
        	type = this.mainCourseItems.get(j).getType();
        	System.out.println("| " + type + ":                 |");
        	while (type.equals(this.mainCourseItems.get(j).getType())){
        		i = j+1;
        		System.out.println("|        " + i + ". " + this.mainCourseItems.get(j).getName() +"       |");
        		j = j+1;
        	}
        }
        System.out.println("============================");
        i = 0;
        j = 0;
        for (j=0;j<this.dessertItems.size();j++) {
        	type = this.dessertItems.get(j).getType();
        	System.out.println("| " + type + ":                 |");
        	while (type.equals(this.dessertItems.get(j).getType())){
        		i = j+1;
        		System.out.println("|        " + i + ". " + this.dessertItems.get(j).getName() + "       |");
        		j = j+1;
        	}
        }
        System.out.println("============================");
        i = 0;
        j = 0;
        for (j=0;j<this.drinkItems.size();j++) {
        	type = this.drinkItems.get(j).getType();
        	System.out.println("| " + type + ":                 |");
        	while (type.equals(this.drinkItems.get(j).getType())){
        		i = j+1;
        		System.out.println("|        " + i + ". " + this.drinkItems.get(j).getName() + "       |");
        		j = j+1;
        	}
        }
        System.out.println("============================");
        i = 0;
        j = 0;
        for (j=0;j<this.setItems.size();j++) {
        	type = this.setItems.get(j).getType();
        	System.out.println("| " + type + ":                 |");
        	while (type.equals(this.setItems.get(j).getType())){
        		i = j+1;
        		System.out.println("|        " + i + ". " + this.setItems.get(j).getName() + "       |");
        		j = j+1;
        	}
        }
        System.out.println("============================");
    }

    public static void main(String[] args){
        //this 2 lines are just to check if the class is working properly, not for the project
        Menu menu = new Menu();
        menu.testPrint();
    }

}

