package Menu_package;

/** 
 * <code>CSVLocatable</code> interface that allows insertion of data into a CSV at a specific location
 */
public interface CSVLocatable{
    
    public void writeToCSVLocation(String path, String line, String newLine);
    public void removeFromCSVLocation(String path, String line);
    //path : path of File
    //line : the specific line ie. location
    
}
