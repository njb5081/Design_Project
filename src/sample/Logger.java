package sample;
import java.io.Serializable;
import java.util.ArrayList;

public class Logger implements Serializable{

    ArrayList<Entry> entryList = new ArrayList<Entry>();

    public Logger(){}

    public void addEntry(String date, String description){

        entryList.add(new Entry(description));

    }

    public ArrayList<Entry> getEntries(){

        return entryList;

    }

}
