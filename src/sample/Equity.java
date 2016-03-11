package sample;
import java.util.Date;

public class Equity {

    private String tickerSymbol;
    private String name;
    private String index;
    private String sector;

    private Date dateOfBuy;

    private int sharesHeld;
    private int sharePrice;
    private int totalValue;

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
    public int getSharePrice () { return sharePrice; }
    public int getTotalValue () { return totalValue; }

    //Setters
    public void setSharesHeld (int newShares) {
        sharesHeld = newShares;
        totalValue = sharesHeld * sharePrice;
    }
    public void setSharePrice (int newPrice) {
        sharePrice = newPrice;
        totalValue = sharesHeld * sharePrice;
    }

    //Other functions
    public String display () {
        //Displays as: "Symbol name sharesHeld $sharePrice $totalValue"
        return tickerSymbol + " " + name + " " + Integer.toString(sharesHeld) + " $" + Integer.toString(sharePrice) + " $" + Integer.toString(totalValue);
    }

}