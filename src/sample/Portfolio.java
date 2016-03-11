package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nick on 3/9/2016.
 */
public class Portfolio {
    //private String userid;
    private User user;
    //private String password?
    //private list of transactions
    private int totalHoldings;
    private int totalCash;
    private ArrayList<Equity> equities;
    private ArrayList<CashAccount> cashAccounts;

    /**
     * Constructor creates new Portfolio
     * @param id userid that links the Portfolio to a user
     * @param importedEquities any equities being imported to a new Portfolio
     * @param importedCashAccounts any cash accounts being imported
     */
    public Portfolio(User user, ArrayList<Equity> importedEquities, ArrayList<CashAccount> importedCashAccounts){
        this.user = user;

        this.equities = new ArrayList<Equity>();
        this.cashAccounts = new ArrayList<CashAccount>();
        int holdings = 0;
        for (Equity e : importedEquities) {
            equities.add(e);
            holdings += (e.getSharesHeld() *e.getSharePrice());
        }
        for (CashAccount c : importedCashAccounts) {
            cashAccounts.add(c);
            holdings += (c.getBalance());
            totalCash += (c.getBalance());
        }
        this.totalHoldings = holdings;
        //add portfolio data to txt file
    }

    /**
     * Gets the total holdings of all holdings in the Portfolio
     * @return int total holdings in all accounts
     */
    public int getTotalHoldings(){
        return this.totalHoldings;
    }

//    public String getUserID(){
//        return this.userid;
//    }

    /**
     * Add an equity to this Portfolio
     * @param ticker equity ticker symbol
     * @param numShares number of shares of this equity
     * @param pricePerShare price per share of this equity
     * @param date date equity was acquired
     * @param cash boolean true if equity is purchased with money in an account associated with this Portfolio
     */
    public boolean addEquity(String ticker, int numShares, int pricePerShare, String date, boolean cash) {
        //change to boolean, false if not enough money in account?
        //create new equity object?
        //add to total holdings
        //add info to txt file
        int totalPrice = (numShares * pricePerShare);
        if (cash && (totalPrice > this.totalCash)) { //if purchasing equity with a cash account
            return false;
        }
        String name = "name";
        //Equity e = new Equity(ticker, )
        calculateTotalHoldings(); //or just update the holdings
        return true;
    }

    /**
     * Sell shares of an equity
     * @param ticker equity ticker symbol
     * @param numSold number of shares sold
     * @param pricePerShare price per share
     * @param date date equity was sold
     * @param cash boolean true if money is being deposited into cash account for this sale
     */
    public void sellEquity(String ticker, int numSold, int pricePerShare, String date, boolean cash){
        //update arrays and shit
        //update txt file
        if (cash) {
            //add cash to cash account
        }
        calculateTotalHoldings(); //or just update the holdings
    }

    /**
     * Add a cash account to this Portfolio
     * @param name name of the cash account
     * @param amount intital amount in the account
     * @param date date account was added
     */
    public void addCashAccount(String name, int amount, String date){
        //add info to txt file
        calculateTotalHoldings(); //or just update the holdings
    }

    /**
     * Delete a cash account from this Portfolio
     * @param name name of the account
     */
    public void deleteCashAccount(String name){
        for (CashAccount c : cashAccounts) {
            if (c.toString().equals(name)) {
                //remove account from list
                //remove info from txt file
                calculateTotalHoldings(); //or just update the holdings
                return;
            }
        }
    }

    /**
     * Checks all Portfolio holdings and updates the Portfolio's total attribute
     */
    public void calculateTotalHoldings(){
        int total = 0;
        for (CashAccount c : cashAccounts) {
            //total += cash account total
        }
        for (Equity e : equities) {
            //total += equity total
        }
        this.totalHoldings = total;
    }

    /**
     * import Transactions into this Portfolio
     */
    public void importTransaction(){
        return;
    }

    /**
     * Transfer funds to a cash account
     */
    public void transferFundsToCashAccount(){
        return;
    }

    /**
     * Create a string representation of the Portfolio
     * @return string representing the Portfolio object
     */
    public String toString(){
        return "Words";
    }

}
