package Menu_package;

import java.util.Scanner;

import Menu_package.Menus.AlaCarteMenu;

public class MenuManager {
    private MenuItemCategoryTypes menuCategories;
    private Scanner sc;
    private AlaCarteMenu menu; 

    public MenuManager(){
        this.menuCategories = new MenuItemCategoryTypes(); 
        this.sc = new Scanner(System.in);
    }

    public void selectItemFromMenu(){
        System.out.println("=================== MENU SELECTION ====================");
        this.menuCategories.printCategories();
        System.out.println("Which category would you like to view?");
        int categorySelected = sc.nextInt();
        
    }
}
