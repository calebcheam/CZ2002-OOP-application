package Menu_package;
public class MenuItemTypeInvalid extends Exception{
    public MenuItemTypeInvalid(String itemType){
        super("Menu Item's Type \"" +itemType+ "\" is invalid!");
    }
}
