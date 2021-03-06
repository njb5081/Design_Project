package sample.Transactions;
import sample.Holdings.CashAccount;
import sample.Log.Logger;
import sample.Portfolio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private Portfolio port;
    private String status = "undone";
    private String transDate = "";
    private String description = "";
    private ArrayList<CashAccount> observables;

    /*
    * Initial the Transfer object to store the information
    * */
    public Transfer(double amount, CashAccount toAccount, CashAccount fromAccount, Portfolio port){

        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.port = port;
        this.observables = new ArrayList<CashAccount>();
        observables.add(toAccount);
        observables.add(fromAccount);
        toAccount.addObserver(this);
        fromAccount.addObserver(this);

    }

    /*
    * Run the transaction activity. The money will move from one account to another account
    * */
    public void execute(){

        toAccount.addFunds(amount);
        fromAccount.subtractFunds(amount);

        port.addRecentTransaction(this);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        transDate = date.toString();

        this.description = "Transfer $" +
                            Double.toString(amount) +
                            " from " +
                            fromAccount.toString() +
                            " to " +
                            toAccount.toString();

        port.getLog().addEntry(description, port.getUserID());

    }

    /*
    * Undo the action if the user don't want to finish the transaction activity
    * */
    public void undo(){

        port.getLog().addEntry("This transaction has been " +
                     status +
                     ": Transfer $" + Double.toString(amount) +
                     " from " +
                     fromAccount.toString() +
                     " to " +
                     toAccount.toString(),
                     port.getUserID());

        if(status.equals("undone")){

            toAccount.subtractFunds(amount);
            fromAccount.addFunds(amount);
            status = "redone";

        }else if(status.equals("redone")){

            toAccount.addFunds(amount);
            fromAccount.subtractFunds(amount);
            status = "undone";

        }

    }

    public String returnTransDate(){
        return transDate;
    }

    public String returnDescription(){ return description; }

    public String returnStatus(){
        if(status.equals("undone")){
            return "Undo";
        }else{
            return "Redo";
        }
    }

    public ArrayList<CashAccount> getObservables(){ return  observables; }

    public void update(){
        for(int i = 0; i < port.getRecentTransactions().size(); i++){
            if(port.getRecentTransactions().get(i).equals(this.transDate)){
                port.removeRecentTransaction(i);
            }
        }
    }

}
