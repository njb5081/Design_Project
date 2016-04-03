package sample.Logger;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry implements Serializable{

    private String date;
    private String description;
    private String user;

    public Entry(String description, String user){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.date = dateFormat.format(date);
        this.description = description;
        this.user = user;

    }

    //get date of creation
    public String getDate(){

        return date;

    }

    //get description of event
    public String getDescription(){

        return description;

    }

    public String toString(){

        return date + ": " + description;

    }

    //get user that the entry pertains to
    public String getUser(){
        return user;
    }

}
