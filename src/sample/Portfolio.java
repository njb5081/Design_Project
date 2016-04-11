/**
 * Portfolio.java
 * Portfolio object that holds information about a portfolio's userid, cash accounts, and equities
 */
package sample;

import sample.Holdings.Asset;
import sample.Holdings.CashAccount;
import sample.Transactions.Transaction;
import sample.Log.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sample.handleData.handleEquity;
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
    private ArrayList<String> sharesHeldTickerSymbols;
    private ArrayList<WatchedAsset> watchlist;
    private ArrayList<Transaction> recentTransactions;
    private Logger log;

    /**
     * Constructor creates new Portfolio
     * @param userid userid that links the Portfolio to a user
     * @param importedEquities any equities being imported to a new Portfolio
     * @param importedCashAccounts any cash accounts being imported
     */
    public Portfolio(String userid, HashMap<String, Integer> importedEquities, ArrayList<CashAccount> importedCashAccounts){
        this.userid = userid;
        this.sharesHeld = new HashMap<String, Integer>();
        this.recentTransactions = new ArrayList<Transaction>();
        this.cashAccounts = new ArrayList<CashAccount>();
        this.sharesHeldTickerSymbols = new ArrayList<String>();
        this.log = new Logger();
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

    public Logger getLog(){return log;}

    public CashAccount getCashAccountByName(String name){

        for(CashAccount ca : getCashAccounts()){
            if(ca.toString().equals(name)){
                return ca;
            }
        }
        return null;
    }

    public void updateLog(String description, String user){
        log.addEntry(description, user);
    }

    public Transaction getActionByDate(String date){

        for(Transaction t : recentTransactions){
            if(t.returnTransDate().equals(date)){
                return t;
            }
        }

        return null;
    }

    public void removeRecentTransaction(int i){
        recentTransactions.remove(i);
    }

    public ArrayList<String> getRecentTransactions(){

        ArrayList<String> recentTransactionsString = new ArrayList<String>();

        for(Transaction t : recentTransactions){
            recentTransactionsString.add(t.returnTransDate());
        }

        return recentTransactionsString;
    }

    public ArrayList<String> getSharesHeldTickerSymbols(){ return sharesHeldTickerSymbols; };

    public void addEquity(Asset asset, int amount) {
        if(sharesHeld.keySet().contains(asset.getName())){
            sharesHeld.put(asset.getName(), sharesHeld.get(asset.getName()) + amount);
        }else{
            sharesHeld.put(asset.getName(), amount);
            sharesHeldTickerSymbols.add(asset.getTickerSymbol());
        }
    }
    public void subtractEquity(Asset asset, int amount) {
        sharesHeld.put(asset.getName(), sharesHeld.get(asset.getName()) - amount);
    }

    public void addRecentTransaction(Transaction newTransaction){

        recentTransactions.add(newTransaction);

        if(recentTransactions.size() > 5){
            recentTransactions.remove(0);
        }

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
     * Updates a cash account in the portfolio
     * @param ca cash account which will be updated
     */
    public void updateCashAccount(CashAccount ca){

        for (CashAccount portCashACcount : cashAccounts){
            if(ca.toString().equals(portCashACcount.toString())){
                portCashACcount = ca;
                return;
            }
        }
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
    public double calculateTotalHoldings(){
        double holdingTotal = 0;
        double cashTotal = 0;
        double equityTotal = 0;
        for (CashAccount c : this.cashAccounts) {
            holdingTotal += (c.getBalance());
            cashTotal += (c.getBalance());
        }
        if (!this.sharesHeld.isEmpty()) {
            for (String s : this.sharesHeld.keySet()) {
              //  holdingTotal += (sharesHeld.get(s) * 10.05);
                equityTotal += (sharesHeld.get(s) * 10.06);
            }
        }
        this.totalHoldings = holdingTotal;
        this.totalCash = cashTotal;
        this.totalEquities = equityTotal;
        return totalHoldings;
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

    public void addToWatchlist(String name, double lowTrigger, double highTrigger, boolean isEquity) {
        //isEquity true when an equity, false when a market average
        if (isEquity) {
            WatchedEquity newEquity = new WatchedEquity(name, lowTrigger, highTrigger);
            watchlist.add(newEquity);
        } else {
            //Then it is a market average, not an equity
            WatchedMarketAverage newMarketAvg = new WatchedMarketAverage(name, lowTrigger, highTrigger);
            watchlist.add(newMarketAvg);
        }
    }

    public void deleteFromWatchlist(String name){
        WatchedAsset asset = this.watchlist.get(0);
        for (WatchedAsset w : this.watchlist) {
            if (w.getName().equals(name)){
                asset = w;
            }
        }
        this.watchlist.remove(asset);
    }

    public void editWatchlistTriggers(String name, double low, double high) {
        for (WatchedAsset w : this.watchlist) {
            if (w.getName().equals(name)) {
                w.editTriggers(low, high);
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
