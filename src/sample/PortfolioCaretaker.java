package sample;

import sample.Holdings.Equity;

import java.util.ArrayList;

/**
 * @author Nicholas Baxter
 * class to perform the memento design pattern
 */
public class PortfolioCaretaker {
    private ArrayList<PortfolioMemento> savedEquities = new ArrayList<PortfolioMemento>();

    /**
     * Adds a memento to the caretaker class
     * @param m - the equity memento to be saved
     */
    public void addMemento(PortfolioMemento m){
        savedEquities.add(m);
        ArrayList<Equity> gh;
        gh = savedEquities.get(0).getEquityList();
        for (Equity fv : gh){
            System.out.println(fv.getSharePrice() + " things saved in list in caretaker");
        }
    }

    /**
     * returns the saved memento to the needed class
     * @return the correct equity memento
     */
    public PortfolioMemento getMemento(){
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
