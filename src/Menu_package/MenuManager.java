package Menu_package;

import java.util.Scanner;

import Menu_package.Menus.AlaCarteMenu;

public class MenuManager {
    private MenuItemCategoryTypes menuCategories;
    private Scanner sc;
    private AlaCarteMenu menu; 

    public MenuManager(){
        this.menuCategories = new MenuItemCategoryTypes(); 
        this.menu = new AlaCarteMenu();
        this.sc = new Scanner(System.in);
    }

    public Item selectItemFromMenu(){
        System.out.println("=================== MENU CATEGORY SELECTION ====================");
        this.menuCategories.printCategories();
        System.out.println("Which category would you like to view?");
        int categorySelectedIndex = sc.nextInt()-1;
        String categorySelected = this.menuCategories.indexToCategory(categorySelectedIndex);
        this.menu.displayMenuByCategory(categorySelected);

        System.out.println("Which subcategory?");
        this.menuCategories.printSubCategories(categorySelectedIndex);
        int subcategorySelectedIndex = sc.nextInt()-1; 
        String subcategory = this.menuCategories.indexToSubcategory(subcategorySelectedIndex, categorySelectedIndex);
        System.out.println("Im sorry our system is a bit lousy. Please input yourself the item number : ");
        int menuItemNumber = sc.nextInt();
        return this.menu.getItem(menuItemNumber, categorySelected, subcategory);

    }

    
}
