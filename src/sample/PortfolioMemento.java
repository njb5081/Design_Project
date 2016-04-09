package sample;

import sample.Holdings.Equity;

import java.util.ArrayList;

/**
 * @author Nicholas Baxter
 */
public class PortfolioMemento {
    private ArrayList<Equity> equityList;

    /**
     * puts saves the state of thing to be restored
     * @param equityList - the list to be stored
     */
    public PortfolioMemento(ArrayList<Equity> equityList){
        this.equityList = equityList;
    }

    /**
     * gets the stored list
     * @return equity list
     */
    public ArrayList<Equity> getEquityList(){
        return this.equityList;
    }
}
