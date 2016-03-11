package sample;
import java.io.Serializable;

public class Entry implements Serializable{

    private String date;
    private String description;

    public Entry(String date, String description){

        this.date = date;
        this.description = description;

    }

    public String getDate(){

        return date;

    }

    public String getDescription(){

        return description;

    }

    public String toString(){

        return date + ": " + description;

    }

}
