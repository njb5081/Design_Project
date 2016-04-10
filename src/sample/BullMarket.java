package sample;

import sample.Holdings.CashAccount;
import sample.Holdings.Equity;
import sample.handleData.handleEquity;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author Nicholas Baxter
 */

//equity increases in this simulation
public class BullMarket implements MarketSimulation {
    static handleEquity equityHandler = new handleEquity();
    Portfolio tempPort;
    private ArrayList<Equity> ownedEquity1;
    private double orginalValue;

    //Equity equityOriginator; //have to use a portfolio
  //  EquityCaretaker caretaker = new EquityCaretaker();

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
            for (Equity e : equityHandler.getEquityMap().values());
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
                portVal += equityVal;

                //  System.out.println(equityValue + " final equity value of ALL shares of ");
                steps = stepNum;
            }
         for (CashAccount ca : EQ.getCashAccounts()){
            // System.out.println(ca.getBalance()+ " Balance of cash account");
            portVal += ca.getBalance();
        }
        orginalValue = 0;
        for (CashAccount ca : EQ.getCashAccounts()){
            // System.out.println(ca.getBalance()+ " Balance of cash account");
            orginalValue += ca.getBalance();
        }
        for (Equity e : ownedEquity) {
            double equityVal;
            System.out.println(e.getSharePrice() + " share price " + EQ.getSharesHeld().get(e.getName()) + " SHARES HELD");
            equityVal = e.getSharePrice();
            orginalValue += equityVal;
        }
        System.out.println(orginalValue + " orginal value in run sim");
        //System.out.println(portVal + " orginal portfolio Value of ALL equity and shares");
        return portVal;
    }

//    public Portfolio savePortfolio(Portfolio port){
//
//        tempPort = new Portfolio(port.getUserID(),port.getTotalEquities(),port.getCashAccounts());
//        return tempPort;
//    }

    @Override
    public double reset(Portfolio tempPort) {
        double portVal = 0;
        //   Map<String,Equity> Prices = equityHandler.getEquityMap();
        ArrayList<Equity> ownedEquity = new ArrayList<Equity>();
        //  System.out.println(equityHandler.getIndexMap().values() + "indexes? ");
        for (String s : tempPort.getSharesHeld().keySet()) {
            for (Equity e : equityHandler.getEquityMap().values()) {
                //  System.out.println(f.getSharePrice() + " PRICE OF EQUITY");
                //  System.out.println(f.getName() + " NAME OF EQUITY");
                if (s.equals(e.getName())) {
                    ownedEquity.add(e);
                    //    System.out.println(e.getSharePrice() + " Price of share");
                    // System.out.println("MATCH " + e.getName());
                    portVal += e.getSharePrice();
                }
            }
        }
        for (CashAccount ca : tempPort.getCashAccounts()){
            // System.out.println(ca.getBalance()+ " Balance of cash account");
            portVal += ca.getBalance();
        }


        return portVal;
    }

}
