package sample;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/8/2016.
 */
public class EquityMemento {
    private ArrayList<Equity> equityList;

    public EquityMemento(ArrayList<Equity> equityList){
        this.equityList = equityList;
    }

    public ArrayList<Equity> getEquityList(){
        return this.equityList;
    }
}
