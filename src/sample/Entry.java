package sample;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry implements Serializable{

    private String date;
    private String description;

    public Entry(String description){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.date = dateFormat.format(date);
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
