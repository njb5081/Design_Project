package sample.Transactions;

/**
 * Created by benjamin on 4/3/16.
 */
public interface Transaction {
    String returnTransDate();
    String returnDescription();
    String returnStatus();
    void execute();
    void undo();

}
