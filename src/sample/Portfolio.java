/**
 * Portfolio.java
 * Portfolio object that holds information about a portfolio's userid, cash accounts, and equities
 */
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
    private double totalHoldings;
    private double totalCash;
    private double totalEquities;
    private ArrayList<CashAccount> cashAccounts;
    private CashAccount largest;
    private Map<String, Integer> sharesHeld;
    private ArrayList<WatchedAsset> watchlist;

    /**
     * Constructor creates new Portfolio
     * @param userid userid that links the Portfolio to a user
     * @param importedEquities any equities being imported to a new Portfolio
     * @param importedCashAccounts any cash accounts being imported
     */
    public Portfolio(String userid, HashMap<String, Integer> importedEquities, ArrayList<CashAccount> importedCashAccounts){
        this.userid = userid;
        this.sharesHeld = new HashMap<String, Integer>();

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
        this.watchlist = new ArrayList<WatchedAsset>();
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

    public Map<String, Integer> getSharesHeld(){
        return sharesHeld;
    }



    public void addEquity(Asset asset, int amount) {
        if(sharesHeld.keySet().contains(asset.getName())){
            sharesHeld.put(asset.getName(), sharesHeld.get(asset.getName()) + amount);
        }else{
            sharesHeld.put(asset.getName(), amount);
        }
    }
    public void subtractEquity(Asset asset, int amount) {
        sharesHeld.put(asset.getName(), sharesHeld.get(asset.getName()) - amount);
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
        //calculateTotalHoldings(); //or just update the holdings
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
    /*
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
    */

    public ArrayList<CashAccount> getCashAccounts(){
        return this.cashAccounts;
    }

//    public ArrayList<Equity> getEquities(){
//        return this.equities;
//    }

//    public void setEquities(ArrayList<Equity> updatedEquities){
//       this.equities = updatedEquities;
//    }

    public void addToWatchlist(String name, double lowTrigger, double highTrigger) {
        WatchedAsset newAsset = new WatchedAsset(name, lowTrigger, highTrigger);
        watchlist.add(newAsset);
    }

    public void deleteFromWatchlist(String name){
        for (WatchedAsset w : this.watchlist) {
            if (w.getName().equals(name)){
                this.watchlist.remove(w);
            }
        }
    }

    public void changeLowTrigger(String name, double newLow){
        for (WatchedAsset w : this.watchlist) {
            if (w.getName().equals(name)) {
                w.changeLow(newLow);
                return;
            }
        }
    }

    public void changeHighTrigger(String name, double newHigh) {
        for (WatchedAsset w : this.watchlist) {
            if (w.getName().equals(name)) {
                w.changeHigh(newHigh);
                return;
            }
        }
    }

    public ArrayList<WatchedAsset> getWatchlist() {
        return this.watchlist;
    }

    /**
     * Create a string representation of the Portfolio
     * @return string representing the Portfolio object
     */
    public String toString(){
        return (userid + "'s Portfolio");
    }

}
