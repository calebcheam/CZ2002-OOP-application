package SalesReport_package;

import java.util.ArrayList;
/** 
 * <code>Interface</code> for appending lines to end of CSV
 */
public interface CSVAppendable{
    public void writeToCSVEnd(ArrayList<String> lines); //append ie. write at the end of CSV
}
