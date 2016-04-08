package sample.handleData;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sample.Holdings.Equity;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

/**
 * Created by minhduong on 4/3/16.
 * this class will handle parse the Equity file and update the current price of equity from the webservice
 */
public class handleEquity {

    private shareEquity compareMachine;
    static data accountHandler = new data();
    /*k
* take the equities.csv file and parse the information into Equity object
* Create 2 hashmap to store information about the Equity and its index or sector
*/
    public void parseEquityFile(){
        //map contain the sector or index with the list of ticket symbol
        Map<String, List<String>> indexMap = new HashMap<String, List<String>>();
        //map contain the ticket symbol with the associate Equity
        Map<String, Equity> equityMap = new HashMap<String, Equity>();
        //Open the file and parse data
        FileReader input = null;
        try {
            input = new FileReader("equities.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufRead = new BufferedReader(input);
        String myLine = null;
        try {
            while ((myLine = bufRead.readLine()) != null) {
                List<String> ticketList = new ArrayList<String>();
                myLine = myLine.substring(1,myLine.length()-1);
                String[] equity = myLine.split("\",\"");
                //first map (ticket symbol map to equity)
                Equity newEquity = new Equity(equity[0],equity[1],Double.parseDouble(equity[2]));
                equityMap.put(equity[0],newEquity);
                //second map (index or sector to ticket symbol)
                int size = equity.length;
                for (int k = 3; k < size; k++){
                    //if no index or sector has been read
                    //create a new key for that index or sector to store the list ticket symbol
                    if (!indexMap.containsKey(equity[k])){
                        ticketList.add(equity[0]);
                        indexMap.put(equity[k],ticketList);
                        //add the new ticket symbol to the available index or sector
                    } else {
                        ticketList = indexMap.get(equity[k]);
                        ticketList.add(equity[0]);
                        indexMap.put(equity[k],ticketList);

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Add both hashmap into an array
        List<Map> dataMap = new ArrayList<Map>();
        //Index 0 represent the index map, Index 1 represent the equity Map
        dataMap.add(indexMap);
        dataMap.add(equityMap);
        // serialize the array to store in the equityfile.txt
        this.updateMap(dataMap);
    }

    /*
    * this method will open the file where we store equity information, then it will update the new price from the website
    * */
    private void updateMap(List<Map> map){
        try {
            FileOutputStream fileOut = new FileOutputStream("equityfile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    /*
    * return the hashmap contain the index or sector
    * the key will be the index or sector, the value will be the list of ticket symbol
    * */
    public Map<String,List<String>> getIndexMap(){
        if(this.getMap().size() >1) {
            return (Map<String, List<String>>) this.getMap().get(0);
        } else {
            return new HashMap<String, List<String>>();
        }
    }

    /*
    * return the hashmap contain the ticket symbol and Equity object
    * the key will be the ticket symbol, the value will be the Equity object associate with the ticket symbol
    * */
    public Map<String,Equity> getEquityMap(){
        if(this.getMap().size() >1) {
            return (Map<String, Equity>) this.getMap().get(1);
        } else {
            return new HashMap<String, Equity>();
        }
    }

    /*
    *decrypte the list from the text file
    * return the list of hashmap from the equityfile.txt
    */
    private List<Map> getMap(){
        List<Map> map = (ArrayList<Map>)accountHandler.listOfFile("equityfile.txt");
        if(map != null) {
            return map;
        } else {
            return new ArrayList<Map>();
        }
    }

    /*
    * Update the share price from the webservice
    * */
    public void updateSharePrice() {
        //access the list of ticket symbol from the hash map of ticket symbol - equity
        List<String> equitySymbol = new ArrayList<String>();
        equitySymbol.addAll(this.getEquityMap().keySet());
        //access the hashmap
        Map<String, Equity> mapNeedUpdate = this.getEquityMap();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder buidler = factory.newDocumentBuilder();
            //go through every
            for( int k = 0; k<equitySymbol.size(); k++) {
                String compile = "%22"+equitySymbol.get(k)+"%22";
                String url = "https://query.yahooapis.com/v1/public/yql?q=select%20symbol%2CLastTradePriceOnly%20from%20yahoo.finance.quotes%20where%20symbol%20in%20("+compile+")&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
                Document doc = buidler.parse(url);

                NodeList list = doc.getElementsByTagName("quote");
                //System.out.println(list.getLength());


                for (int i = 0; i < list.getLength(); i++) {
                    Element item = (Element) list.item(i);
                    //check if the hashmap contain the ticket symbol
                    String ticketSymbol = item.getAttribute("symbol");
                    if (mapNeedUpdate.containsKey(ticketSymbol)) {
                        //go through each child of the ticket symbol
                        NodeList childNodes = item.getChildNodes();
                        if (childNodes.item(0).getNodeName().equals("LastTradePriceOnly")) {
                            //get the Equity object from the map
                            Equity temporaryEquity = mapNeedUpdate.get(ticketSymbol);
                            if (!childNodes.item(0).getTextContent().trim().isEmpty()) {
                                temporaryEquity.setSharePrice((Double.parseDouble(childNodes.item(0).getTextContent().trim())));
                                mapNeedUpdate.put(ticketSymbol, temporaryEquity);
                                //System.out.println((Double.parseDouble(childNodes.item(0).getTextContent().trim())));
                            }
                        }
                    }
                }
            }
            List<Map> listOfHashTable = this.getMap();
            listOfHashTable.set(1,mapNeedUpdate);
            this.updateMap(listOfHashTable);
        } catch (ParserConfigurationException e){
            e.printStackTrace();
        } catch (SAXException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    *
    * this function will return the list of ticker symbol which match with the user input.
    * */
    public List<String> searchEquity(String symbol, String name){
        List<String> tickerSymbol = new ArrayList<String>();
        compareMachine = new shareEquity(symbol,name);
        for (Iterator it = compareMachine.iterator(); it.hasNext();){
            String result = (String) it.next();
            if(!symbol.isEmpty()){
                //the result match the input
                if(symbol.equals(result) || result.contains(symbol)){
                    //System.out.println("");
                    //check the name
                    tickerSymbol.add(result);
                }
            } else {
                //System.out.println("no symbol, check string");
                if(!name.isEmpty()){
                    String equityName = this.getEquityMap().get(result).getName();

                    //if the input name is part of the equity name
                    if(equityName.contains(name)){
                        //System.out.println(equityName);
                        tickerSymbol.add(result);
                    }
                }
            }
        }
        //return tickerSymbol;
//        for (String e: tickerSymbol){
//            System.out.println(e);
//        }
        return tickerSymbol;
    }
}
