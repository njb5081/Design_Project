package sample;

import java.util.ArrayList;

/**
 * Created by Nicholas on 3/8/2016.
 */
public class EquityCaretaker {
    private ArrayList<EquityMemento> savedEquities = new ArrayList<EquityMemento>();

    public void addMemento(EquityMemento m){
        savedEquities.add(m);
        ArrayList<Equity> gh;
        gh = savedEquities.get(0).getEquityList();
        for (Equity fv : gh){
            System.out.println(fv.getSharePrice() + " things saved in list in caretaker");
        }


    }

    public EquityMemento getMemento(){
        ArrayList<Equity> fgd = savedEquities.get(0).getEquityList();
        for (Equity ffjn : fgd){
            System.out.println(ffjn.getSharePrice()+ " in get memento");
        }
        return savedEquities.get(0);
    }

    public void removeMemento(){
        savedEquities.remove(savedEquities.size()-1);
    }
}
