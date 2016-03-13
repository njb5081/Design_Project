package sample;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer {

    private CashAccount toAccount;
    private CashAccount fromAccount;
    private double amount;
    private Logger log;
    private String user;

    public Transfer(double amount, CashAccount toAccount, CashAccount fromAccount, Logger log, String user){

        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.log = log;
        this.user = user;

    }

    public void execute(){

        toAccount.addFunds(amount);
        fromAccount.subtractFunds(amount);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        log.addEntry("Transfer $" + Double.toString(amount) +
                " from " + fromAccount.toString() + " to " + toAccount.toString(), user);

    }

    public void undo(){

        toAccount.subtractFunds(amount);
        fromAccount.addFunds(amount);

    }


}
