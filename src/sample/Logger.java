package sample;
import java.io.Serializable;
import java.util.ArrayList;

public class Logger implements Serializable{

    ArrayList<Entry> entryList = new ArrayList<Entry>();

    public Logger(){}

    public void addEntry(String description, String user){

        entryList.add(new Entry(description, user));

    }

    public ArrayList<Entry> getEntries(){

        return entryList;

    }

}
