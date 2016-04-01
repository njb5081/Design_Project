package sample.handleData;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.sound.sampled.Port;
import java.io.*;
import java.util.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.SAXException;
import sample.*;

import java.io.IOException;
import java.io.File;


/**
 * Created by minhduong on 3/4/16.
 */
public class  data implements subject {

    List<User> userList;

    /*
    * save a new account to data
    */
    public void saveAccount(User newAccount){

        //check wthether the account has been created or not
        if (!isUserExist(newAccount)) {
            //get the list of User account from the text file
            userList = this.listOfUser();
            userList.add(newAccount);
            //open the text file and smave the new account
            this.updateAccountList(userList);
            //create new PortfolioAccount
            this.savePortfolioAccount(newAccount.username());
            newAccount.update("created");
        } else {
            System.out.println("account exists");

        }
    }

    /*
  * pre-condition: the user account is created before the fortfolio account is created
  * Post-condition: save the Portfolio account into the array in the text file
  * */
    public void savePortfolioAccount(String username){
        Map<String,List<String>> sectors = this.getIndexMap();
        Map<String, Equity> equities = this.getEquityMap();
        HashMap<String, Integer> shares = new HashMap<String, Integer>();
        for (String s : sectors.keySet()){
            shares.put(s, 0);
        }
        double cumulative = 0;
        double numEq = 0;

        Portfolio newPortfolio = new Portfolio(username,shares,new ArrayList<CashAccount>());
        List<Portfolio> listOfPortfolio = this.listOfPortfolio();
        listOfPortfolio.add(newPortfolio);
        this.updatePortfolioList(listOfPortfolio);
    }

    /*
    * delete the account and portfolio with the known username
    * */
    public void deleteUserAccount(User account){
        List<User> listOfAccount = this.listOfUser();
        List<Portfolio> listOfPortfolio = this.listOfPortfolio();
        //loop through the list of account and remove the account user
        for (User user: listOfAccount){
            if (user.username().equals(account.username())){
                listOfAccount.remove(user);
                break;
            }
        }
        //loop through the list of portfolio account and remove the account
        for (Portfolio p : listOfPortfolio){
            if(p.getUserID().equals(account.username())){
                listOfPortfolio.remove(p);
                break;
            }
        }

        this.updateAccountList(listOfAccount);
        this.updatePortfolioList(listOfPortfolio);
        account.update("deleted");

    }

    /*
    * update the portfolio list in the text file
    * */
    public void updatePortfolioList(List<Portfolio> listOfPortfolio){
        try {
            FileOutputStream fileOut = new FileOutputStream("portfolio.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfPortfolio);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /*
    * update the logger in the text file
    * */
    public void updateLogger(Logger log){
        try {
            FileOutputStream fileOut = new FileOutputStream("log.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(log);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Logger getLog (){
        Logger log = new Logger();

        try {
            FileInputStream fileOut = new FileInputStream("log.txt");
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                log = (Logger)is.readObject();
                is.close();
            }
        } catch (FileNotFoundException i){
            i.printStackTrace();
        }catch(IOException i)
        {
            i.printStackTrace();
        } catch (ClassNotFoundException i){
            i.printStackTrace();
        }
        return log;
    }

    /**
     * update the account list in the text file
     *
     */
    public void updateAccountList(List<User> listOfAccount){
        try {
            FileOutputStream fileOut = new FileOutputStream("employee.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(listOfAccount);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    /*
    * check if the username has been exsited
    * */
    public boolean usernameExist(String username){
        List<User> list = this.listOfUser();
        for (User u: list){
            if (u.username().equals(username)){
                return true;
            }
        }

        return false;
    }


    /*
    * check if the user has made an account before
    * */
    public boolean isUserExist(User checkUser){
        return this.listOfUser().contains(checkUser);
    }

    /*c
    * return list of accounts that have been made from the text file
    * */
    public List<User> listOfUser (){
        List<User> listOfAccount = new ArrayList<User>();
        //access the text file employee.text to get the list of User account
        listOfAccount = (ArrayList<User>)this.listOfFile("employee.txt");
        return listOfAccount;
    }

    /*
    * return the lsit of Portfolio account from the portfolio.txt file
    * */
    public List<Portfolio> listOfPortfolio (){
        List<Portfolio> listOfPortfolio = new ArrayList<Portfolio>();
        //access the text file portfolio.text to get the lsit of Portfolio account
        listOfPortfolio = (ArrayList<Portfolio>)this.listOfFile("portfolio.txt");
        return listOfPortfolio;
    }

    /*
    * return list of object after open the file
    * */
    private Object listOfFile(String filename){
        Object list = null;
        try {
            FileInputStream fileOut = new FileInputStream(filename);
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                list = is.readObject();
                is.close();
            }
        } catch (FileNotFoundException i){
            i.printStackTrace();
        }catch(IOException i)
        {
            i.printStackTrace();
        } catch (ClassNotFoundException i){
            i.printStackTrace();
        }
        return list;
    }

    /*
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
        return (Map<String,List<String>>)this.getMap().get(0);
    }

    /*
    * return the hashmap contain the ticket symbol and Equity object
    * the key will be the ticket symbol, the value will be the Equity object associate with the ticket symbol
    * */
    public Map<String,Equity> getEquityMap(){
        return (Map<String,Equity>) this.getMap().get(1);
    }

    /*
    *decrypte the list from the text file
    * return the list of hashmap from the equityfile.txt
    */
    private List<Map> getMap(){
        List<Map> map = new ArrayList<Map>();
        map = (ArrayList<Map>)this.listOfFile("equityfile.txt");
        return map;
    }

    /*
    * Update the share price from the webservice
    * */
    public void updateSharePrice() {
        data handler = new data();
        ArrayList<String> equitySymbol = (ArrayList<String>) handler.getEquityMap().keySet();
        String compile = "";
        String firstElement = equitySymbol.get(0);
        compile = compile + "%22"+firstElement+"%22";
        for (int i = 1; i<equitySymbol.size(); i++){

            compile = compile +"%2C%22"+equitySymbol.get(i)+"%22";
        }
        String url = "https://query.yahooapis.com/v1/public/yql?q=select%20symbol%2CLastTradePriceOnly%20from%20yahoo.finance.quotes%20where%20symbol%20in%20("+compile+")&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance().newInstance();
            DocumentBuilder buidler = factory.newDocumentBuilder();
            Document doc = buidler.parse(url);

            NodeList list = doc.getElementsByTagName("quote");
            //System.out.println(list.getLength());
            Map<String,Equity> mapNeedUpdate = this.getEquityMap();

            for (int i = 0; i< list.getLength(); i++){
                Element item = (Element)list.item(i);
                //check if the hashmap contain the ticket symbol
                String ticketSymbol =  item.getAttribute("symbol");
                if (mapNeedUpdate.containsKey(ticketSymbol)) {
                    //go through each child of the ticket symbol
                    NodeList childNodes = item.getChildNodes();
                    if (childNodes.item(0).getNodeName().equals("LastTradePriceOnly")) {
                        //get the Equity object from the map
                        Equity temporaryEquity = mapNeedUpdate.get(ticketSymbol);
                        temporaryEquity.setSharePrice((Double.parseDouble(childNodes.item(0).getTextContent().trim())));
                        mapNeedUpdate.put(ticketSymbol,temporaryEquity);
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

}

