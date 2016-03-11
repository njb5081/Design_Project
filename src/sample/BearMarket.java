package sample;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/7/2016.
 */
public class BearMarket implements MarketSimulation {
   @Override
    public float runSimulation(float percentage, ArrayList<equity> EQ, boolean continuous, int stepNum, String timeInterval) {
       float portfolioValue = 0;
       int steps = stepNum;
       //TODO MAKE the simulation able to step through
       for (equity E : EQ) {
           float percentagePaid = E.equityPrice * (percentage / 100);
           if (timeInterval.equals("month")) {
               percentagePaid = percentagePaid * 30;
           } else if (timeInterval.equals("year")) {
               percentagePaid = percentagePaid * 365;
           }
           System.out.println(percentagePaid + " Percent paid");
           while (steps > 0) {
               System.out.println(stepNum + " step num");
               System.out.println(E.equityPrice + " Price");
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
    public ArrayList<equity> reset(ArrayList<equity> EQ) {
        return null;
    }
}
