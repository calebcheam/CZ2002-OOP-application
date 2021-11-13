package SalesReport_package;

import java.util.ArrayList;

public interface CSVAppendable{
    public void writeToCSVEnd(ArrayList<String> lines); //append ie. write at the end of CSV
}
