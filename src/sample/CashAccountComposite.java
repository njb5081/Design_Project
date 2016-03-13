package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class CashAccountComposite implements Account{

    //private ArrayList<CashAccount> accounts = new ArrayList<CashAccount>();
    private HashMap<String, CashAccount> accounts = new HashMap<String, CashAccount>();

    public CashAccountComposite(){

    }

    public void addCashAccount(Account newAccount){
        if(newAccount instanceof CashAccount){

            if(!accounts.keySet().contains(newAccount.toString())){

            }

        }else if(newAccount instanceof CashAccountComposite){

        }
    }

    public Double getBalance(){

        Double balance = 0.0;

        for(int i = 0; i < accounts.size(); i++){
            balance += accounts.get( accounts.keySet().toArray()[i] ).getBalance();
        }

        return balance;
    }

}
