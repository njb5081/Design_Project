package sample;

public class CashAccount {

    private int balance;
    private String name;

    public CashAccount(int initialBalance, String name){

        this.balance = initialBalance;
        this.name = name;

    }

    public void addFunds(int amount){

        balance += amount;

    }

    public void subtractFunds(int amount){

        balance -= amount;

    }

    public int getBalance(){

        return balance;

    }

    public String toString(){

        return name;

    }

}
