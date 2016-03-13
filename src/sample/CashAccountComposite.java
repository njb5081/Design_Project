package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class CashAccountComposite implements Account{

    private HashMap<String, Account> accounts = new HashMap<String, Account>();

    public CashAccountComposite(){

    }

    public void addCashAccount(Account newAccount){
        if(newAccount instanceof CashAccount){

            if(!accounts.keySet().contains(newAccount.toString())){
                accounts.put(newAccount.toString(), newAccount);
            }

        }else if(newAccount instanceof CashAccountComposite){

            for(int i = 0; i < ((CashAccountComposite) newAccount).accounts.size(); i++){

                Account currAccount = ((CashAccountComposite) newAccount).accounts.get(((CashAccountComposite) newAccount).accounts.keySet().toArray()[i]);

                if(!accounts.keySet().contains(currAccount.toString())){
                    accounts.put(newAccount.toString(), newAccount);
                }
            }
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
