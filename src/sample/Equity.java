package sample;
import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.zip.DeflaterOutputStream;

public class Equity {

    private String tickerSymbol;
    private String name;
    private String index;
    private String sector;

    private Date dateOfBuy;

    private int sharesHeld;
    private double sharePrice;
    private double totalValue;

    public Equity (String tickerSymbol, String name, String index, String sector, int sharesHeld, int sharePrice) {

        this.tickerSymbol = tickerSymbol;
        this.name = name;
        this.index = index;
        this.sector = sector;

        this.dateOfBuy = new Date();

        this.sharesHeld = sharesHeld;
        this.sharePrice = sharePrice;
        this.totalValue = sharesHeld * sharePrice;
    }

    //Getters
    public String getTickerSymbol () {
        return tickerSymbol;
    }
    public String getName () { return name; }
    public String getIndex () { return index; }
    public String getSector () { return sector; }

    public Date getDateOfBuy () { return dateOfBuy; }

    public int getSharesHeld () { return sharesHeld; }
    public double getSharePrice () { return sharePrice; }
    public double getTotalValue () { return totalValue; }

    //Setters
    public void setSharesHeld (int newShares) {
        sharesHeld = newShares;
        totalValue = sharesHeld * sharePrice;
    }
    public void setSharePrice (double newPrice) {
        sharePrice = newPrice;
        totalValue = sharesHeld * sharePrice;
    }

    //Other functions
    public String display () {
        //Displays as: "Symbol name sharesHeld $sharePrice $totalValue"
        return tickerSymbol + " " + name + " " + Integer.toString(sharesHeld) + " $" + Double.toString(sharePrice)
                + " $" + Double.toString(totalValue);
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