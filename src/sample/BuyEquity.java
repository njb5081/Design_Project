package sample;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyEquity {

    private CashAccount funds;
    private Asset asset;
    private int amount;
    private Logger log;
    private Portfolio port;

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
        //asset.addSharesHeld(amount);


        port.addEquity(asset, amount);

        //create log entry
        log.addEntry("Bought " +
                Integer.toString(amount) +
                " shares of " +
                asset.getName() +
                " at " +
                Double.toString(asset.getSharePrice()) +
                " using " +
                funds.toString() +
                " cash account",
                port.getUserID());
    }

    public void undo(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("", port.getUserID());

    }


}
