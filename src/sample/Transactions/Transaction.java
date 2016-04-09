package sample.Transactions;

import com.sun.org.apache.xpath.internal.operations.Bool;
import sample.Holdings.CashAccount;

import java.util.ArrayList;

/**
 * Created by benjamin on 4/3/16.
 */
public interface Transaction {
    String returnTransDate();
    String returnDescription();
    String returnStatus();
    ArrayList<CashAccount> getObservables();
    void execute();
    void undo();
    void update();

}
