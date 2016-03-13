package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/7/2016.
 */
public class NoGrowthMarket extends Application implements MarketSimulation {
    @Override
    public ArrayList<Equity> runSimulation(float percentage, Portfolio EQ, boolean continuous,
                               int stepNum, String timeInterval) {
        float portfolioValue = 0;
        int steps = stepNum;
        //TODO MAKE the simulation able to step through
        for (Equity E : EQ.getportfolioEquity()) {
            float percentagePaid = 0;
            while (steps > 0) {

                steps -= 1;
            }
            steps = stepNum;

        }
        return EQ.getportfolioEquity();
    }

    @Override
    public Portfolio reset(Portfolio EQ) {
        return EQ;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
