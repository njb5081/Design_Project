package sample;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CashAccount implements  Serializable{

    private Double balance;
    private String name;
    private String openDate;

    public CashAccount(Double initialBalance, String name){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.openDate = dateFormat.format(date);
        this.balance = initialBalance;
        this.name = name;

    }

    public void addFunds(Double amount){

        balance += amount;

    }

    public void subtractFunds(Double amount){

        balance -= amount;

    }

    public Double getBalance(){

        return balance;

    }

    public String getOpenDate(){

        return openDate;

    }

    public String toString(){

        return name;

    }

}
