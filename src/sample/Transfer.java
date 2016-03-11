package sample;

public class Transfer {

    private CashAccount toAccount;
    private CashAccount fromAccount;
    private int amount;

    public Transfer(int amount, CashAccount toAccount, CashAccount fromAccount){

        this.toAccount = toAccount;
        this.fromAccount = fromAccount;
        this.amount = amount;


    }

    public void execute(){

        toAccount.addFunds(amount);
        fromAccount.subtractFunds(amount);

    }

    public void undo(){

        toAccount.subtractFunds(amount);
        fromAccount.addFunds(amount);

    }


}
