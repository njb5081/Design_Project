package sample.Transactions;
import sample.Holdings.Asset;
import sample.Holdings.CashAccount;
import sample.Log.Logger;
import sample.Portfolio;
import sample.handleData.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.Serializable;
import java.util.List;

public class BuyEquity implements Serializable, Transaction {

    private CashAccount funds;
    private Asset asset;
    private int amount;
    private Logger log;
    private Portfolio port;
    private String status = "undone";
    private String transDate = "";
    private String description = "";

    public BuyEquity(int amount, CashAccount funds, Asset asset, Logger log, Portfolio port){

        this.funds = funds;
        this.asset = asset;
        this.amount = amount;
        this.log = log;
        this.port = port;

    }

    //Executes the command
    public void execute(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        funds.subtractFunds(asset.getSharePrice() * ((double)amount));
        port.addEquity(asset, amount);

        port.addRecentTransaction(this);

        transDate = date.toString();

        this.description = "Bought " +
                            Integer.toString(amount) +
                            " shares of " +
                            asset.getName() +
                            " at " +
                            Double.toString(asset.getSharePrice()) +
                            " using " +
                            funds.toString() +
                            " cash account";

        //create log entry
        log.addEntry(description, port.getUserID());
    }

    public void undo(){

        //create log entry
        log.addEntry("This transaction has been " +
                        status +
                        ": Sold " +
                        Integer.toString(amount) +
                        " shares of " + asset.getName() +
                        " at " +
                        Double.toString(asset.getSharePrice()) +
                        " deposited into " + funds.toString() +
                        " cash account",
                        port.getUserID());


        if(status.equals("undone")){

            funds.addFunds(asset.getSharePrice() * ((double)amount));
            port.subtractEquity(asset, amount);
            status = "redone";

        }else if(status.equals("redone")){

            funds.subtractFunds(asset.getSharePrice() * ((double)amount));
            port.addEquity(asset, amount);
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


}
