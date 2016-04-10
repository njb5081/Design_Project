package sample.handleData;

import sample.Holdings.Equity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by minhduong on 4/6/16.
 * This
 */
public class shareEquity {

    private List<String> listOfSymbol;
    private handleEquity handler;
    private int index;
    private String name;
    private String ticketSymbol;
    public shareEquity(String ticketSymbol, String equityName, List<String> listOfSymbol){
        handler = new handleEquity();
        this.listOfSymbol = listOfSymbol;
        this.name = equityName;
        this.ticketSymbol = ticketSymbol;
        index = 0;
    }

    public class shareEquityIterator implements Iterator{

        @Override
        /*
        * this method return the next element in the iterator
        * */
        public boolean hasNext() {

            if(ticketSymbol.isEmpty() && name.isEmpty()){
                return false;
            } else {
                return index < listOfSymbol.size();

            }
        }

        @Override
        /*
        * this function will return the name of an index or sector if the user enter the inex or sector
        * Otherwise it will return the name of the ticket symbol
        * */
        public String next() {
            return listOfSymbol.get(index++);
        }
    }

    public Iterator iterator(){
        return new shareEquityIterator();
    }

}
