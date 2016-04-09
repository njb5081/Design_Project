package sample.Holdings;
import sample.Transactions.Transaction;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CashAccount implements  Serializable{

    private Double balance;
    private String name;
    private String openDate;
    private ArrayList<Transaction> observers;

    public CashAccount(Double initialBalance, String name){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.openDate = dateFormat.format(date);
        this.balance = initialBalance;
        this.name = name;
        this.observers = new ArrayList<Transaction>();

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

    public void addObserver(Transaction trans){
        observers.add(trans);
    }

    public void update(){
        for(Transaction o : observers){
            o.update();
        }
    }

}
