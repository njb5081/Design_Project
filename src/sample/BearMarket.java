package sample;

import sample.Holdings.CashAccount;
import sample.Holdings.Equity;
import sample.handleData.handleEquity;

import java.util.ArrayList;

/**
 * Class to simulate a bear market in which the prices of equities decease
 * @author Nicholas Baxter
 */

public class BearMarket implements MarketSimulation {
    static handleEquity equityHandler = new handleEquity();
    Portfolio tempPort;

    /**
     * Class to run the simulation, implements Market simulation interface
     * @param percentage - percent the equity should change by
     * @param EQ - the portfolio being changed by the simulation
     * @param continuous - if the simulation is continuous or not
     * @param stepNum - the number of steps to go through
     * @param timeInterval - the time interval (day month year)
     * @return - the updated value of the equities
     */
    @Override
    public double runSimulation(float percentage, Portfolio EQ, boolean continuous, int stepNum, String timeInterval) {
        double portVal = 0;
        //   Map<String,Equity> Prices = equityHandler.getEquityMap();
        ArrayList<Equity> ownedEquity = new ArrayList<Equity>();
        //  System.out.println(equityHandler.getIndexMap().values() + "indexes? ");
        for (String s : EQ.getSharesHeld().keySet()){
            for (Equity e : equityHandler.getEquityMap().values()){
                //  System.out.println(f.getSharePrice() + " PRICE OF EQUITY");
                //  System.out.println(f.getName() + " NAME OF EQUITY");
                if (s.equals(e.getName())){
                    ownedEquity.add(e);
                    //    System.out.println(e.getSharePrice() + " Price of share");
                    // System.out.println("MATCH " + e.getName());
                    portVal += e.getSharePrice();
                }
                //  ownedEquity1 = ownedEquity;
            }
            //  System.out.println(s + " shares you own");
        }

        double equityValue;
        int steps = stepNum;

        tempPort = EQ;
        // System.out.println(EQ.getTotalEquities() + " intial share price of equity");
        for (Equity e : ownedEquity) {
            double equityVal = e.getSharePrice();
            System.out.println(e.getSharePrice() + " share price " + EQ.getSharesHeld().get(e.getName()) + " SHARES HELD");
            equityVal = e.getSharePrice();
            double percentagePaid = e.getSharePrice()*(percentage / 100);
            //   System.out.println(percentagePaid + " earned percentage");
            if (timeInterval.equals("m")) {
                percentagePaid = percentagePaid * 30;
            } else if (timeInterval.equals("y")) {
                percentagePaid = percentagePaid * 365;
            }
            while (steps > 0) {
                equityVal += (percentagePaid);
                // System.out.println(equityVal + " this should be increasing");
                steps -= 1;
            }
            equityVal *= EQ.getSharesHeld().get(e.getName());
            //System.out.println(equityVal + " this should be huge");
            portVal -= equityVal;

            //  System.out.println(equityValue + " final equity value of ALL shares of ");
            steps = stepNum;
        }
        for (CashAccount ca : EQ.getCashAccounts()){
            // System.out.println(ca.getBalance()+ " Balance of cash account");
            portVal += ca.getBalance();
        }

        //System.out.println(portVal + " orginal portfolio Value of ALL equity and shares");
        return portVal;
    }


    /**
     * class to reset the portfolio equities after a simulation
     * @return - the reseted portfolio
     */
    @Override
    public double reset(Portfolio p) {
        //  originator.RestoreFromEquityMemento(caretaker.getMemento());
        // EQ = originator.getState();
        return tempPort.getTotalHoldings();
    }
}




