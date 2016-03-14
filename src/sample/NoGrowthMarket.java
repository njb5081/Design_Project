package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/7/2016.
 */
public class NoGrowthMarket implements MarketSimulation {
    Portfolio port;

    @Override
    public double runSimulation(float percentage, Portfolio EQ, boolean continuous,
                               int stepNum, String timeInterval) {
        float portfolioValue = 0;
        int steps = stepNum;
        float percentagePaid = 0;
        while (steps > 0) {
            steps -= 1;
            }
            steps = stepNum;
        return EQ.getTotalEquities();
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
    public double reset() {
        ;
        return port.getTotalHoldings();
    }
}
