package sample.Holdings;
import sample.EquityMemento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import sample.Holdings.Equity;
public class Equity implements Serializable, Asset {

    private String tickerSymbol;
    private String name;

    private Date dateOfBuy;

    private double sharePrice;

    public Equity (String tickerSymbol, String name, double sharePrice) {

        this.tickerSymbol = tickerSymbol;
        this.name = name;

        this.dateOfBuy = new Date();

        //this.sharesHeld = sharesHeld;
        this.sharePrice = sharePrice;
        //this.totalValue = sharesHeld * sharePrice;

    }

    //Getters
    public String getTickerSymbol () { return tickerSymbol; }

    public String getName () { return name; }
//    public String getIndex () { return index; }
//    public String getSector () { return sector; }

    public Date getDateOfBuy () { return dateOfBuy; }

    public int getSharesHeld () { return 0; }
    public Double getSharePrice () { return sharePrice; }

    //Setters
//    public void setSharesHeld (int newShares) {
//        sharesHeld = newShares;
//        totalValue = sharesHeld * sharePrice;
//    }

//    public void addSharesHeld (int addShares) {
//        sharesHeld += addShares;
//        totalValue = sharesHeld * sharePrice;
//    }

    public void setSharePrice (double newPrice) {
        sharePrice = newPrice;
        //totalValue = sharesHeld * sharePrice;
    }

    //Methods to help with reset functionality
    private ArrayList<Equity> equityList;

    public void setEquityList(ArrayList<Equity> equityList){
        for (Equity EEE : equityList) {
            System.out.println("Orginitor setting state to " + EEE.getSharePrice());
        }
        this.equityList = equityList;
    }

    public ArrayList<Equity> getState(){
        return this.equityList;
    }

    public EquityMemento saveToMemento(){
        System.out.println("Orginator saving to memento");
        return new EquityMemento(this.equityList);
    }

    public void RestoreFromEquityMemento(EquityMemento m) {
        System.out.println("FBDNFBBERIJBIBNERINBIE");
        this.equityList = m.getEquityList();
        for (Equity EEE : this.equityList) {
            System.out.println("Orginitor state after restore " + EEE.getSharePrice());
        }
    }

}