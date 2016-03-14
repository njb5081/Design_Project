package sample;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* this class will be resposible for selling the equity
* */
public class SellEquity {

    private CashAccount funds;
    private Asset asset;
    private int amount;
    private Logger log;
    private Portfolio port;

    public SellEquity(int amount, CashAccount funds, Asset asset, Logger log, Portfolio port){

        this.funds = funds;
        this.asset = asset;
        this.amount = amount;
        this.log = log;
        this.port = port;

    }

    /*
    *sells selected asset into selected cash account
    * */
    public void execute(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        funds.addFunds(asset.getSharePrice() * ((double)amount));
        //asset.addSharesHeld(-amount);

        port.subtractEquity(asset, amount);

        log.addEntry("Sold " +
                Integer.toString(amount) +
                " shares of " + asset.getName() +
                " at " +
                Double.toString(asset.getSharePrice()) +
                " deposited into " + funds.toString() +
                " cash account",
                port.getUserID());
    }

    public void undo(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("", port.getUserID());

    }


}