package CZ2002Project;
public class MenuItemTypeInvalid extends Exception{
    public MenuItemTypeInvalid(String itemType){
        super("Menu Item's Type \"" +itemType+ "\" is invalid!");
    }
}
