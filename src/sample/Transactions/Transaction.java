package sample.Transactions;

/**
 * Created by benjamin on 4/3/16.
 */
public interface Transaction {
    String returnTransDate();
    void execute();
    void undo();

}
