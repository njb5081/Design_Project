package sample.Transactions;
import sample.Holdings.CashAccount;
import sample.Log.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.Serializable;

/*
* This class will create the Transfer object
* It also create function to deal with transaction between multiple accounts
* */
public class Transfer implements Serializable, Transaction {

    private CashAccount toAccount;
    private CashAccount fromAccount;
    private double amount;
    private Logger log;
    private String user;
    private String status = "undone";

    /*
    * Initial the Transfer object to store the information
    * */
    public Transfer(double amount, CashAccount toAccount, CashAccount fromAccount, Logger log, String user){

        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.log = log;
        this.user = user;

    }

    /*
    * Run the transaction activity. The money will move from one account to another account
    * */
    public void execute(){

        toAccount.addFunds(amount);
        fromAccount.subtractFunds(amount);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("Transfer $" + Double.toString(amount) +
                " from " + fromAccount.toString() + " to " + toAccount.toString(), user);

    }

    /*
    * Undo the action if the user don't want to finish the transaction activity
    * */
    public void undo(){
        toAccount.subtractFunds(amount);
        fromAccount.addFunds(amount);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("This transaction has been reverted" +
                    status +
                    ": Transfer $" + Double.toString(amount) +
                    " from " + fromAccount.toString() + " to " + toAccount.toString(), user);

        if(status.equals("undone")){

            status = "redone";

        }else if(status.equals("redone")){

            status = "undone";

        }

    }


}
