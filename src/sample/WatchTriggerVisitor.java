package sample;

import sample.Holdings.Equity;
import sample.handleData.handleEquity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 4/8/2016.s
 */
public class WatchTriggerVisitor implements AssetVisitor {
    private handleEquity handler;
    private Map<String, Equity> equityMap;

    public WatchTriggerVisitor() {
        this.handler = new handleEquity();
        this.equityMap = handler.getEquityMap();
    }

    @Override
    public void visit(WatchedEquity e) {
        String eName = e.getName();
        //Equity equity = equityMap.get(0);
        for (String s : equityMap.keySet()) {
            if (s.equals(eName)) {
                Equity equity = equityMap.get(s);
                double price = equity.getSharePrice();
                e.checkEquityTriggers(price);
                return;
            }
        }
//        double price = equity.getSharePrice();
//        e.checkEquityTriggers(price);
    }

    @Override
    public void visit(WatchedMarketAverage avg) {
        /*
         *NEEDS REVISION? GET DIFFERENT MAP FOR MARKET AVG PRICES?
         */
        String eName = avg.getName();
        //Equity equity = equityMap.get(0);
        for (String s : equityMap.keySet()) {
            if (s.equals(eName)) {
                Equity equity = equityMap.get(s);
                double price = equity.getSharePrice();
                avg.checkMarketAverageTriggers(price);
            }
        }
//        double price = equity.getSharePrice();
//        avg.checkMarketAverageTriggers(price);
    }
}
