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
import sample.Holdings.Equity;
import sample.Holdings.CashAccount;
import sample.Logger.*;
import sample.Transactions.*;
import java.io.IOException;
import java.io.File;
import sample.handleData.handleEquity;

/**
 * Created by minhduong on 3/4/16.
 */
public class  data  {
    static handleEquity handler = new handleEquity();
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
        Map<String,List<String>> sectors = handler.getIndexMap();
        Map<String, Equity> equities = handler.getEquityMap();
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
        if (list != null) {
            for (User u : list) {
                if (u.username().equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }


    /*
    * check if the user has made an account before
    * */
    public boolean isUserExist(User checkUser){
        if (this.listOfUser() == null || !this.listOfUser().contains(checkUser)){
            return false;
        } else {
            return true;
        }
        //return this.listOfUser().contains(checkUser);
    }

    /*c
    * return list of accounts that have been made from the text file
    * */
    public List<User> listOfUser (){
        List<User> listOfAccount = new ArrayList<User>();
        //access the text file employee.text to get the list of User account
        Object list = this.listOfFile("employee.txt");
        if(list != null ) {
            listOfAccount = (ArrayList<User>) list;
        }
        return listOfAccount;
    }

    /*
    * return the lsit of Portfolio account from the portfolio.txt file
    * */
    public List<Portfolio> listOfPortfolio (){
        List<Portfolio> listOfPortfolio = new ArrayList<Portfolio>();
        //access the text file portfolio.text to get the lsit of Portfolio account
        Object list = this.listOfFile("portfolio.txt");
        if(list != null) {
            listOfPortfolio = (ArrayList<Portfolio>) list;
        }
        return listOfPortfolio;
    }

    /*
    * return list of object after open the file
    * */
    public Object listOfFile(String filename){
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



}

