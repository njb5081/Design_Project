package sample;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/8/2016.
 */
public class EquityCaretaker {
    private ArrayList<EquityMemento> savedEquities = new ArrayList<EquityMemento>();

    public void addMemento(EquityMemento m){
        savedEquities.add(m);
        ArrayList<equity> gh = savedEquities.get(0).getEquityList();
        for (equity fv : gh){
            System.out.println(fv.equityPrice + " things saved in list in caretaker");
        }


    }

    public EquityMemento getMemento(){
        ArrayList<equity> fgd = savedEquities.get(0).getEquityList();
        for (equity ffjn : fgd){
            System.out.println(ffjn.equityPrice + " in get memento");
        }
        return savedEquities.get(0);
    }
}
