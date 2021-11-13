package Menu_package;

public interface CSVLocatable{
    
    public void writeToCSVLocation(String path, String line, String newLine);
    public void removeFromCSVLocation(String path, String line);
    //path : path of File
    //line : the specific line ie. location
    
}
