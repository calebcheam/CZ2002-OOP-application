package Menu_package;

import java.util.Scanner;

import Menu_package.Menus.AlaCarteMenu;
import Menu_package.Menus.GenericMenu;
import Menu_package.Menus.PromoMenu;

public class MenuManager {
    private MenuItemCategoryTypes menuCategories;
    private Scanner sc=new Scanner(System.in);
    private GenericMenu Menu=null; 
    private AlaCarteMenu menu; 

    public MenuManager(){
        
    }
    public void viewMenu(){
        System.out.println("Which menu to display? (1) Ala Carte (2) Promotional ");
        int menuChoice = sc.nextInt();

        if (menuChoice == 1){
            this.Menu=new AlaCarteMenu();
            System.out.println("Which category to display?");
            System.out.println("0 : View All");
            this.Menu.displayMenu();
        } else {
            this.Menu=new PromoMenu();
            this.Menu.displayMenu();

        }
    }

    private int viewOneCategory(){
        
        System.out.println("=================== MENU CATEGORY SELECTION ====================");
        this.menuCategories.printCategories();
        System.out.println("Which category would you like to view?");
        int categorySelectedIndex = sc.nextInt()-1;
        return categorySelectedIndex;
    }

    private int viewOneSubCategory(int categorySelectedIndex){
       
        String categorySelected = this.menuCategories.indexToCategory(categorySelectedIndex);
        this.menu.displayMenuByCategory(categorySelected);


        System.out.println("Which subcategory?");
        this.menuCategories.printSubCategories(categorySelectedIndex);
        int subcategorySelectedIndex = sc.nextInt()-1; 
        return subcategorySelectedIndex;
    }

    public Item selectItemFromMenu(){
        int categorySelectedIndex = this.viewOneCategory();
        String categorySelected = this.menuCategories.indexToCategory(categorySelectedIndex);
        int subcategorySelectedIndex = this.viewOneSubCategory(categorySelectedIndex);
        
        String subcategory = this.menuCategories.indexToSubcategory(subcategorySelectedIndex, categorySelectedIndex);
        System.out.println("Im sorry our system is a bit lousy. Please input yourself the item number : ");
        int menuItemNumber = sc.nextInt();
        return this.menu.getItem(menuItemNumber, categorySelected, subcategory);

    }

    
}
