package sample;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.sound.sampled.Port;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by minhduong on 3/4/16.
 */
public class  data  {

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
        Map<String,Equity> equities = this.getEquityMap();
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
    public void deleteUserAccount(String username){
        List<User> listOfAccount = this.listOfUser();
        List<Portfolio> listOfPortfolio = this.listOfPortfolio();
        //loop through the list of account and remove the account user
        for (User user: listOfAccount){
            if (user.username().equals(username)){
                listOfAccount.remove(user);
                break;
            }
        }
        //loop through the list of portfolio account and remove the account
        for (Portfolio p : listOfPortfolio){
            if(p.getUserID().equals(username)){
                listOfPortfolio.remove(p);
                break;
            }
        }

        this.updateAccountList(listOfAccount);
        this.updatePortfolioList(listOfPortfolio);

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
        //access the text file employee.text
//        Object temporary = this.listOfObject("employee.txt");
//        if (temporary != null){
//            listOfAccount = (ArrayList<User>) temporary;
//        }

        try {
            FileInputStream fileOut = new FileInputStream("employee.txt");
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                listOfAccount = (ArrayList<User>)is.readObject();
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
        return listOfAccount;
    }

    public List<Portfolio> listOfPortfolio (){
        List<Portfolio> listOfPortfolio = new ArrayList<Portfolio>();
        //access the text file portfolio.text
//        Object temporary = this.listOfObject("portfolio.txt");
//        if (temporary != null){
//            listOfPortfolio = (ArrayList<Portfolio>) temporary;
//        }


        try {
            FileInputStream fileOut = new FileInputStream("portfolio.txt");
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                listOfPortfolio = (ArrayList<Portfolio>)is.readObject();
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
        return listOfPortfolio;
    }

//    public Object listOfObject(String text){
//        Object list= null;
//        try {
//            FileInputStream fileOut = new FileInputStream(text);
//            if(fileOut.available() > 0) {
//                ObjectInputStream is = new ObjectInputStream(fileOut);
//                list = is.readObject();
//                is.close();
//            }
//        } catch (FileNotFoundException i){
//            i.printStackTrace();
//        }catch(IOException i)
//        {
//            i.printStackTrace();
//        } catch (ClassNotFoundException i){
//            i.printStackTrace();
//        }
//
//        return list;
//    }

    public void parseEquityFile(){
        Map<String, List<String>> indexMap = new HashMap<String, List<String>>();

        Map<String, Equity> equityMap = new HashMap<String, Equity>();
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
                //System.out.println(myLine.substring(1,myLine.length()-1));
                myLine = myLine.substring(1,myLine.length()-1);
                //System.out.println(myLine);
                String[] equity = myLine.split("\",\"");
                //first map (ticket symbol map to equity)
                Equity newEquity = new Equity(equity[0],equity[1],Double.parseDouble(equity[2]));
                //System.out.println(equity);
                //for (String s : equity){
                  //  System.out.println(s);
                //}
                equityMap.put(equity[0],newEquity);
                //second map (index or sector to ticket symbol)
                int size = equity.length;
                for (int k = 3; k < size; k++){
                    //if no index or sector has been read
                    //create a new key for that index or sector to store the ticket symbol
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

        List<Map> dataMap = new ArrayList<Map>();
        dataMap.add(indexMap);
        dataMap.add(equityMap);


        try {
            FileOutputStream fileOut = new FileOutputStream("equityfile.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dataMap);
            out.close();
            fileOut.close();
        } catch (FileNotFoundException i) {
            i.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("Sucessful");
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
        try {
            FileInputStream fileOut = new FileInputStream("equityfile.txt");
            if(fileOut.available() > 0) {
                ObjectInputStream is = new ObjectInputStream(fileOut);
                map = (ArrayList<Map>)is.readObject();
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
        return map;
    }
    public static void main(String[] args) {
        data a = new data();
        a.parseEquityFile();
    }

}
