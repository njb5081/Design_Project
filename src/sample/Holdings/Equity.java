package sample.Holdings;
import sample.PortfolioMemento;
import sample.AssetVisitor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Equity implements Serializable, Asset {

    private String tickerSymbol;
    private String name;
    private Date dateOfBuy;
    private double sharePrice;

    public Equity (String tickerSymbol, String name, double sharePrice) {
        this.tickerSymbol = tickerSymbol;
        this.name = name;
        this.dateOfBuy = new Date();
        this.sharePrice = sharePrice;
    }

    //Getters
    public String getTickerSymbol () { return tickerSymbol; }
    public String getName () { return name; }
    public Date getDateOfBuy () { return dateOfBuy; }
    public int getSharesHeld () { return 0; }
    public Double getSharePrice () { return sharePrice; }
    public ArrayList<Equity> getState(){ return this.equityList; }

    //Setters
    public void setSharePrice (double newPrice) {
        sharePrice = newPrice;
    }

    //Methods to help with reset functionality
    private ArrayList<Equity> equityList;

    public void setEquityList(ArrayList<Equity> equityList){
        for (Equity EEE : equityList) {
            System.out.println("Orginitor setting state to " + EEE.getSharePrice());
        }
        this.equityList = equityList;
    }

    public PortfolioMemento saveToMemento(){
        System.out.println("Orginator saving to memento");
        return new PortfolioMemento(this.equityList);
    }

    public void RestoreFromEquityMemento(PortfolioMemento m) {
        System.out.println("FBDNFBBERIJBIBNERINBIE");
        this.equityList = m.getEquityList();
        for (Equity EEE : this.equityList) {
            System.out.println("Orginitor state after restore " + EEE.getSharePrice());
        }
    }
}