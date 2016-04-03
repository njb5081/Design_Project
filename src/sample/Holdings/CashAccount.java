package sample.Holdings;
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

    //add amount to total funds
    public void addFunds(Double amount){

        balance += amount;

    }

    //remove amount from total funds
    public void subtractFunds(Double amount){

        balance -= amount;

    }

    //return total balance
    public Double getBalance(){

        return balance;

    }

    //return date account was opened
    public String getOpenDate(){

        return openDate;

    }

    //get name of string
    public String toString(){

        return name;

    }

}
