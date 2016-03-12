package sample;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/8/2016.
 */
public class EquityOriginator {
    private ArrayList<Equity> equityList;

    public void setEquityList(ArrayList<Equity> equityList){
        for (Equity EEE : equityList) {
            System.out.println("Orginitor setting state to " + EEE.getSharePrice());
        }
        this.equityList = equityList;
    }

    public ArrayList<Equity> getState(){
        return this.equityList;
    }

    public EquityMemento saveToMemento(){
        System.out.println("Orginator saving to memento");
        return new EquityMemento(this.equityList);
    }

    public void RestoreFromEquityMemento(EquityMemento m) {
        System.out.println("FBDNFBBERIJBIBNERINBIE");
        this.equityList = m.getEquityList();
        for (Equity EEE : this.equityList) {
            System.out.println("Orginitor state after restore " + EEE.getSharePrice());
        }
    }
}
