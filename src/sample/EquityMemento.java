package sample;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/8/2016.
 */
public class EquityMemento {
    private ArrayList<equity> equityList;

    public EquityMemento(ArrayList<equity> equityList){

        this.equityList = equityList;
    }

    public ArrayList<equity> getEquityList(){
        return equityList;
    }
}
