package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Holdings.CashAccount;
import sample.Holdings.Equity;
import sample.handleData.handleEquity;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/7/2016.
 */
public class NoGrowthMarket implements MarketSimulation {
    static handleEquity equityHandler = new handleEquity();
    Portfolio port;

    @Override
    public double runSimulation(float percentage, Portfolio EQ, boolean continuous, int stepNum, String timeInterval) {
        double portVal = 0;
        //   Map<String,Equity> Prices = equityHandler.getEquityMap();
        ArrayList<Equity> ownedEquity = new ArrayList<Equity>();
        //  System.out.println(equityHandler.getIndexMap().values() + "indexes? ");
        for (String s : EQ.getSharesHeld().keySet()) {
            for (Equity e : equityHandler.getEquityMap().values()) {
                if (s.equals(e.getName())) {
                    ownedEquity.add(e);
                    portVal += e.getSharePrice();
                }

            }
        }
        for (CashAccount ca : EQ.getCashAccounts()) {
            // System.out.println(ca.getBalance()+ " Balance of cash account");
            portVal += ca.getBalance();
        }
        return portVal;
    }

    /**
     *
     * @param portfolio
     * @return
     */
    public Portfolio savePortfolio(Portfolio portfolio){
        this.port = portfolio;
        return this.port;
    }

    @Override
    public double reset(Portfolio p) {
        ;
        return port.getTotalHoldings();
    }
}
