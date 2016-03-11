package sample;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer {

    private CashAccount toAccount;
    private CashAccount fromAccount;
    private int amount;

    public Transfer(int amount, CashAccount toAccount, CashAccount fromAccount, Logger log){

        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry(dateFormat.format(date), "Transfer $" + Integer.toString(amount) +
                " from " + fromAccount.toString() + " to " + toAccount.toString());

    }

    public void execute(){

        toAccount.addFunds(amount);
        fromAccount.subtractFunds(amount);

    }

    public void undo(){

        toAccount.subtractFunds(amount);
        fromAccount.addFunds(amount);

    }


}
