package sample;
import java.io.Serializable;
import java.util.ArrayList;

public class Logger implements Serializable{

    ArrayList<Entry> entryList = new ArrayList<Entry>();

    public Logger(){}

    //add entry to log
    public void addEntry(String description, String user){

        entryList.add(new Entry(description, user));

    }

    //get all entries
    public ArrayList<Entry> getEntries(){

        return entryList;

    }

}
