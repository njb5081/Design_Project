package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/7/2016.
 */
public class NoGrowthMarket extends Application implements MarketSimulation {
    @Override
    public float runSimulation(float percentage, ArrayList<equity> EQ, boolean continuous,
                               int stepNum, String timeInterval) {
        float portfolioValue = 0;
        int steps = stepNum;
        //TODO MAKE the simulation able to step through
        for (equity E : EQ) {
            float percentagePaid = 0;
            while (steps > 0) {
                E.equityPrice -= (percentagePaid);
                if (E.equityPrice < 0){
                    E.equityPrice = 0;
                }
                steps -= 1;
            }
            steps = stepNum;
            portfolioValue += E.equityPrice;
        }
        return portfolioValue;
    }

    @Override
    public ArrayList<equity> reset(ArrayList EQ) {
        return EQ;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
