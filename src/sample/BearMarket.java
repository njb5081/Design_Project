package sample;
/**
 * Class to simulate a bear market in which the prices of equities decease
 * @author Nicholas Baxter
 */

public class BearMarket implements MarketSimulation {
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
        //originator.setEquityList(EQ);
        //  caretaker.addMemento(originator.saveToMemento());
        float porfolioValue = 0;
        double equityValue;
        int steps = stepNum;
        Portfolio tempPort;
        equityValue = EQ.getTotalEquities();
        double percentagePaid = (EQ.getTotalEquities()) * (percentage / 100);

        if (timeInterval.equals("m")) {
            percentagePaid = percentagePaid * 30;
        } else if (timeInterval.equals("y")) {
            percentagePaid = percentagePaid * 365;
        }
        while (steps > 0) {
            equityValue -= (percentagePaid);
            if (equityValue < 0){
                equityValue = 0;
            }
            steps -= 1;
            porfolioValue += equityValue;
            steps = stepNum;
        }
        return EQ.getTotalEquities();
    }

    /**
     * class to reset the portfolio equities after a simulation
     * @return - the reseted portfolio
     */
    @Override
    public double reset() {
        //  originator.RestoreFromEquityMemento(caretaker.getMemento());
        // EQ = originator.getState();
        return tempPort.getTotalHoldings();
    }
}




