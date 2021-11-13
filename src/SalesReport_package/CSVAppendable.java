package SalesReport_package;

import java.util.ArrayList;

import Menu_package.CSVHandlerInterface;

public interface CSVAppendable extends CSVHandlerInterface{
    public void writeToCSVEnd(ArrayList<String> lines); //append ie. write at the end of CSV
}
