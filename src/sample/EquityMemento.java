package sample;

import java.util.ArrayList;

/**
 * @author Nicholas Baxter
 */
public class EquityMemento {
    private ArrayList<Equity> equityList;

    /**
     * puts saves the state of thing to be restored
     * @param equityList - the list to be stored
     */
    public EquityMemento(ArrayList<Equity> equityList){
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
