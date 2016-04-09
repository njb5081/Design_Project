package sample.handleData;

import sample.Holdings.Equity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by minhduong on 4/6/16.
 */
public class shareEquity {

    private Map<String,List<String>> sectorAndSymbolMap;
    private Map<String,Equity> symbolAndPriceMap;
    private List<String> listOfSectorOrIndex;
    private List<String> listOfSymbol;
    private handleEquity handler;
    private int index;
    private String name;
    //private String indexOrSector;
    private String ticketSymbol;
    public shareEquity(String ticketSymbol, String equityName){
        handler = new handleEquity();
        sectorAndSymbolMap = handler.getIndexMap();
        symbolAndPriceMap = handler.getEquityMap();
        listOfSectorOrIndex = new ArrayList<String>(sectorAndSymbolMap.keySet());
        listOfSymbol = new ArrayList<String>(symbolAndPriceMap.keySet());
        this.name = equityName;
        //this.indexOrSector = indexOrSector;
        this.ticketSymbol = ticketSymbol;
        index = 0;
    }

    public class shareEquityIterator implements Iterator{

        @Override
        /*
        * this method return the next element in the iterator
        * */
        public boolean hasNext() {
            //if there is index or sector then we will check the Index map first
//            if(!indexOrSector.isEmpty()){
//                return index < listOfSectorOrIndex.size();
//            } else {
                //user enter the ticket symbol
                //check the index base of the ticket symbol
            return index < listOfSymbol.size();
//            }

        }

        @Override
        /*
        * this function will return the name of an index or sector if the user enter the inex or sector
        * Otherwise it will return the name of the ticket symbol
        * */
        public String next() {

            String name = "";

//            if (!indexOrSector.isEmpty()) {
//                //user enter the ticket symbol
//                //check the index base of the ticket symbol
//                return listOfSectorOrIndex.get(index++);
//            } else {
            return listOfSymbol.get(index++);
            //}

        }
    }

    public Iterator iterator(){
        return new shareEquityIterator();
    }

}
