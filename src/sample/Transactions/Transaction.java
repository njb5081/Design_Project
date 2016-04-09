package sample.Transactions;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by benjamin on 4/3/16.
 */
public interface Transaction {
    String returnTransDate();
    String returnDescription();
    String returnStatus();
    Boolean isValid();
    void execute();
    void undo();

}
