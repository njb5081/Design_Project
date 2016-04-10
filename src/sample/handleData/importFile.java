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
 * Created by minhduong on 4/10/16.
 */
import sample.handleData.data;

public class importFile implements ImportInfo {

    private data userData;
    public void parseImportFile(String filename, Portfolio currentAccount){
        FileReader input = null;
        try {
            input = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader bufRead = new BufferedReader(input);

        String myLine = null;
        Boolean newAccount = true;
        try {
            while ((myLine = bufRead.readLine()) != null) {
                String [] cashAccountList = myLine.split(",");
                if(cashAccountList[0].equals("cash account") && cashAccountList.length == 3){
                    for (CashAccount e: currentAccount.getCashAccounts()){
                        if(e.toString().equals(cashAccountList[1])){
                            newAccount = false;
                            break;
                        }
                    }
                    if(newAccount){
                        userData = new data();
                        List<Portfolio> portList = userData.listOfPortfolio();
                        currentAccount.addCashAccount(cashAccountList[1],Double.parseDouble(cashAccountList[2]));
                        userData.updatePortfolioList(portList);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
