package sample;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyEquity {

    private CashAccount funds;
    private Equity asset;
    private int amount;
    private Logger log;
    private Portfolio port;

    public BuyEquity(int amount, CashAccount funds, Equity asset, Logger log, Portfolio port){

        this.funds = funds;
        this.asset = asset;
        this.amount = amount;
        this.log = log;
        this.port = port;

    }

    public void execute(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        funds.subtractFunds(asset.getSharePrice() * ((double)amount));
        asset.addSharesHeld(amount);

        if(!port.getEquities().contains(asset)){
            port.addEquity(asset.getTickerSymbol(), amount, asset.getSharePrice(), dateFormat.format(date), true);
        }else{
            
        }

        log.addEntry(dateFormat.format(date), "Bought " + Integer.toString(amount) +
                " shares of " + asset.getName() + " at " + Double.toString(asset.getSharePrice()) +
                " using " + funds.toString() + " cash account");
    }

    public void undo(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("", "");

    }


}
