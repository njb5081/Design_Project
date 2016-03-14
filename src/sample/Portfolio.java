package sample;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 3/9/2016.
 */
public class Portfolio implements Serializable {
    private String userid;
    //private list of transactions
    private double totalHoldings;
    private double totalCash;
    private double totalEquities;
    //private ArrayList<Equity> equities;
    private ArrayList<CashAccount> cashAccounts;
    private CashAccount largest;
    private Map<String, Integer> sharesHeld;
    //private Map<String, Double> sharePrices;

    /**
     * Constructor creates new Portfolio
     * @param userid userid that links the Portfolio to a user
     * @param importedEquities any equities being imported to a new Portfolio
     * @param importedCashAccounts any cash accounts being imported
     */
    public Portfolio(String userid, HashMap<String, Integer> importedEquities, ArrayList<CashAccount> importedCashAccounts){
        this.userid = userid;
        //this.sharePrices = avgSharePrices;
        this.sharesHeld = new HashMap<String, Integer>();
        this.sharePrices = avgSharePrices;

        //this.equities = new ArrayList<Equity>();
        this.cashAccounts = new ArrayList<CashAccount>();
//        for (String s : importedEquities.keySet()) {
//            sharesHeld.put(s, importedEquities.get(s));
//            this.totalHoldings += (importedEquities.get(s) * avgSharePrices.get(s));
//            this.totalEquities += (importedEquities.get(s) * avgSharePrices.get(s));
//        }
        for (CashAccount c : importedCashAccounts) {
            this.cashAccounts.add(c);
            this.totalHoldings += (c.getBalance());
            this.totalCash += (c.getBalance());
        }
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

    public double getTotalEquities() {
        return this.totalEquities;
    }

    //public ArrayList<Equity> getportfolioEquity(){ return this.equities;}

    /**
     * Add an equity to this Portfolio
     * @param ticker equity ticker symbol
     * @param numShares number of shares of this equity
     * @param pricePerShare price per share of this equity
     * @param date date equity was acquired
     * @param cash boolean true if equity is purchased with money in an account associated with this Portfolio
     */
    public boolean addEquity(String ticker, int numShares, double pricePerShare, String date, boolean cash) {
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

        int newShares = sharesHeld.get(ticker) + numShares;
        this.sharesHeld.put(ticker, newShares);

        calculateTotalHoldings();
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
        double totalSalePrice = (numSold * pricePerShare);
        int newShares = this.sharesHeld.get(ticker) - numSold;
        this.sharesHeld.put(ticker, newShares);


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
     */
    public void addCashAccount(String name, double amount){
        CashAccount newAcc = new CashAccount(amount, name);
        cashAccounts.add(newAcc);
        //add info to txt file
//        calculateTotalHoldings(); //or just update the holdings
    }

    /**
     * Delete a cash account from this Portfolio
     * @param name name of the account
     */
    public void deleteCashAccount(String name){
        for (CashAccount c : cashAccounts) {
            if (c.toString().equals(name)) {
                this.cashAccounts.remove(c);
//                calculateTotalHoldings(); //or just update the holdings
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
        double equityTotal = 0;
        for (CashAccount c : this.cashAccounts) {
            holdingTotal += (c.getBalance());
            cashTotal += (c.getBalance());
        }
        if (!this.sharesHeld.isEmpty()) {
            for (String s : this.sharesHeld.keySet()) {
                holdingTotal += (sharesHeld.get(s) * sharePrices.get(s));
                equityTotal += (sharesHeld.get(s) * sharePrices.get(s));
            }
        }
        this.totalHoldings = holdingTotal;
        this.totalCash = cashTotal;
        this.totalEquities = equityTotal;
    }

    public ArrayList<CashAccount> getCashAccounts(){
        return this.cashAccounts;
    }

//    public ArrayList<Equity> getEquities(){
//        return this.equities;
//    }

//    public void setEquities(ArrayList<Equity> updatedEquities){
//       this.equities = updatedEquities;
//    }

    /**
     * Create a string representation of the Portfolio
     * @return string representing the Portfolio object
     */
    public String toString(){
        return "Words";
    }

}
