package sample;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Nick on 3/9/2016.
 */
public class Portfolio {
    private String userid;
    //private String password?
    //private list of transactions
    private double totalHoldings;
    private double totalCash;
    private ArrayList<Equity> equities;
    private ArrayList<CashAccount> cashAccounts;

    /**
     * Constructor creates new Portfolio
     * @param userid userid that links the Portfolio to a user
     * @param importedEquities any equities being imported to a new Portfolio
     * @param importedCashAccounts any cash accounts being imported
     */
    public Portfolio(String userid, ArrayList<Equity> importedEquities, ArrayList<CashAccount> importedCashAccounts){
        this.userid = userid;

        this.equities = new ArrayList<Equity>();
        this.cashAccounts = new ArrayList<CashAccount>();
        double holdings = 0;
        for (Equity e : importedEquities) {
            equities.add(e);
            holdings += (e.getSharesHeld() * e.getSharePrice());
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
    public double getTotalHoldings(){
        return this.totalHoldings;
    }

    public String getUserID(){
        return this.userid;
    }

    public double getTotalCash(){
        return this.totalCash;
    }

    /**
     * Add an equity to this Portfolio
     * @param ticker equity ticker symbol
     * @param numShares number of shares of this equity
     * @param pricePerShare price per share of this equity
     * @param date date equity was acquired
     * @param cash boolean true if equity is purchased with money in an account associated with this Portfolio
     */
    public boolean addEquity(String ticker, int numShares, double pricePerShare, String date, boolean cash) {
        //change to boolean, false if not enough money in account?
        //create new equity object?
        //add to total holdings
        //add info to txt file
        double totalPrice = (numShares * pricePerShare);
        if (cash && (totalPrice > this.totalCash)) { //if purchasing equity with a cash account
            return false;
        }
        if (cash) {
            double max = 0;
            CashAccount largest = cashAccounts.get(0);
            for (CashAccount c : cashAccounts){
                if (c.getBalance() > max) {
                    max = c.getBalance();
                    largest = c;
                }
            }
            //subtract from cash account
            largest.subtractFunds(totalPrice);
        }
        String name = "name"; //placeholder
        String index = "index"; //placeholder
        String sector = "sector"; //placeholder

        Equity e = new Equity(ticker, name, index, sector, numShares, pricePerShare);
        this.equities.add(e);
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
    public boolean sellEquity(String ticker, int numSold, double pricePerShare, String date, boolean cash){
        //update arrays and shit
        //update txt file
        Equity equity = equities.get(0);
        double totalSalePrice = (numSold * pricePerShare);
        for (Equity e : equities){
            if (e.getTickerSymbol().equals(ticker)){
                equity = e;
            }
        }
        equity.setSharesHeld((equity.getSharesHeld() - numSold));
        if (cash && cashAccounts.isEmpty()){ //There are no cash accounts associated with this portfolio
            return false;
        }
        if (cash && !cashAccounts.isEmpty()) {
            cashAccounts.get(0).addFunds(totalSalePrice);
        }
        calculateTotalHoldings(); //or just update the holdings
        return true;
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
     * import Transactions into this Portfolio
     */
    public void importTransaction() {
        return;
    }
    /**
     * Transfer funds to a cash account
     */
    public void transferFundsToCashAccount(){
        return;
    }

    /**
     * Checks all Portfolio holdings and updates the Portfolio's total attribute
     */
    public void calculateTotalHoldings(){
        double holdingTotal = 0;
        double cashTotal = 0;
        for (CashAccount c : cashAccounts) {
            holdingTotal += (c.getBalance());
            cashTotal += (c.getBalance());
        }
        for (Equity e : equities) {
            holdingTotal += (e.getTotalValue());
        }
        this.totalHoldings = holdingTotal;
        this.totalCash = cashTotal;
    }

    /**
     * Create a string representation of the Portfolio
     * @return string representing the Portfolio object
     */
    public String toString(){
        return "Words";
    }

}
