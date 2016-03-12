package sample;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BuyEquity {

    private CashAccount funds;
    private Equity asset;
    private int amount;
    private Logger log;

    public BuyEquity(int amount, CashAccount funds, Equity asset, Logger log){

        this.funds = funds;
        this.asset = asset;
        this.amount = amount;
        this.log = log;

    }

    public void execute(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("", "");

    }

    public void undo(){

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("", "");

    }


}
