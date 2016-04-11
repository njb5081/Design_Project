package sample.handleData;
import sample.Holdings.Equity;
import sample.Portfolio;
import sample.handleData.importFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sample.Holdings.CashAccount;
/**
 * Created by minhduong on 4/10/16.\
 * This class will implement the ImportInfo interface so the user can import file contains cash Accounts information
 */
import sample.handleData.data;

import javax.sound.sampled.Port;

public class importFile implements ImportInfo {

    private data userData;
    private Portfolio currentPortfolio;
    /*
    * parse the account information from the file and add it into cash accounts in the portfolio
    * */
    public void parseImportFile(String filename, String ID){
        FileReader input = null;
        try {
            input = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bufRead = new BufferedReader(input);
        userData = new data();
        List<Portfolio> portList = userData.listOfPortfolio();
        for (Portfolio e: portList){
            if (e.getUserID().equals(ID)){
                currentPortfolio = e;
            }
        }

        String myLine = null;
        Boolean newAccount = true;
        //try to open the file and check if the cash account has been exist
        //add new account if there is no exist account with the same name
        try {
            while ((myLine = bufRead.readLine()) != null) {
                myLine = myLine.substring(1,myLine.length()-1);
                System.out.println(myLine);
                String [] cashAccountList = myLine.split("\",\"");
                System.out.println(cashAccountList[0]+"  "+cashAccountList[1]+"  "+cashAccountList[2]);
                if(cashAccountList[0].contains("cash account") && cashAccountList.length == 3){
                    for (CashAccount e: currentPortfolio.getCashAccounts()){
                        System.out.println("name of cash account in portfolio now  :"+e.toString());
                        if(e.toString().equals(cashAccountList[1])){
                            System.out.println("same account has been existed");
                            newAccount = false;
                            break;
                        }
                    }
                    if(newAccount){
                        //System.out.println("set up new Cash Account");
                        currentPortfolio.addCashAccount(cashAccountList[1],Double.parseDouble(cashAccountList[2]));
                        userData.updatePortfolioList(portList);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
